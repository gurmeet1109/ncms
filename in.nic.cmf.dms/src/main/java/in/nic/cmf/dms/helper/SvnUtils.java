package in.nic.cmf.dms.helper;

import in.nic.cmf.dms.DMSUtils;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.properties.PropertyManagement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * SVN Server access utility.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
public class SvnUtils {

    /**
     * SVNRepository initialisation.
     */
    private SVNRepository      repository = null;

    /**
     * LogTracer initialisation.
     */
    private LogTracer          log;

    /**
     * PropertiesUtil initialisation.
     */
    private PropertyManagement propUtil   = PropertyManagement.getInstance();

    /**
     * DMSUtils object creation and initialisation.
     */
    private DMSUtils           dmsutils;
    private String             domain;

    /**
     * Constructor as private.
     */
    private SvnUtils(String domain) {
        this.domain = domain;
        dmsutils = DMSUtils.getInstanceof(domain);
    }

    /**
     * getting instance of SvnUtils.
     * @return SvnUtils
     */
    public static SvnUtils getInstanceof(String domain) {
        return new SvnUtils(domain);
    }

    /**
     * This method initialise LogTracer log.
     * @param log
     *            LogTracer object
     */
    public void setLogTracer(final LogTracer log) {
        this.log = log;
    }

    /**
     * @param dirPath
     *            directory path
     * @param fileName
     *            file name
     * @param contents
     *            file data
     * @throws GenericException
     *             void
     */
    public boolean svnAdd(final String dirPath, final String fileName,
            final byte[] contents) throws GenericException {
        ISVNEditor editor = null;
        try {
            setRepository(setupLibrary());
            editor = getRepository().getCommitEditor(
                    "directory and file added", null);
            /*
             * Always called first. Opens the current root directory. It means
             * all modifications will be applied to this directory until a next
             * entry (located inside the root) is opened/added. -1 - revision is
             * HEAD (actually, for a commit editor this number is irrelevant)
             */
            editor.openRoot(-1);
            /*
             * Adds a new directory (in this case - to the root directory for
             * which the SVNRepository was created). Since this moment all
             * changes will be applied to this directory. dirPath is relative to
             * the root directory. copyFromPath (the 2nd parameter) is set to
             * null and copyFromRevision (the 3rd) parameter is set to -1 since
             * the directory is not added with history (is not copied, in other
             * words).
             */
            String[] dirs = dirPath.split("/");
            for (String dir : dirs) {
                try {
                    editor.addDir(dir, null, -1);
                } catch (SVNException svne) {
                    log.warn("svnAdd => addDir() throws : " + svne.toString());
                }
            }
            /*
             * Adds a new file to the just added directory. The file path is
             * also defined as relative to the root directory. copyFromPath (the
             * 2nd parameter) is set to null and copyFromRevision (the 3rd
             * parameter) is set to -1 since the file is not added with history.
             */
            log.debug("svnaddFile:" + fileName);
            editor.addFile(fileName, null, -1);
            /*
             * The next steps are directed to applying delta to the file (that
             * is the full contents of the file in this case).
             */
            editor.applyTextDelta(fileName, null);
            /*
             * Use delta generator utility class to generate and send delta Note
             * that you may use only 'target' data to generate delta when there
             * is no access to the 'base' (previous) version of the file.
             * However, using 'base' data will result in smaller network
             * overhead. SVNDeltaGenerator will call editor.textDeltaChunk(...)
             * method for each generated "diff window" and then
             * editor.textDeltaEnd(...) in the end of delta transmission. Number
             * of diff windows depends on the file size.
             */
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            String checksum = deltaGenerator.sendDelta(fileName,
                    new ByteArrayInputStream(contents), editor, true);
            /*
             * Closes the new added file.
             */
            editor.closeFile(fileName, checksum);
            return true;
        } catch (SVNException svne) {
            log.error("ERR-DMS-0006 Unable to write content dirpath:" + dirPath
                    + "&fileName" + fileName + "Exception:" + svne);
            throw new GenericException(domain, "ERR-DMS-0006",
                    "Unable to write content", "dirPath:" + dirPath
                            + "& filePath:" + fileName, svne);

        } catch (GenericException e) {
            log.error("svnAdd() throws : " + e.toString());
            throw e;

        } finally {
            try {
                /*
                 * Closes the new added directory.
                 */
                editor.closeDir();
                /*
                 * This is the final point in all editor handling. Only now all
                 * that
                 * new information previously described with the editor's
                 * methods is
                 * sent to the server for committing. As a result the server
                 * sends
                 * the new commit information.
                 */
                editor.closeEdit();
                if (this.repository != null) {
                    this.repository = null;
                }
                return true;
            } catch (SVNException e) {
                log.warn("svnAdd() throws : " + e.toString());
            }
        }
    }

    /**
     * Read content for given file.
     * @param filePath
     *            file path with file name
     * @return
     *         content byte[]
     * @throws GenericException
     *             byte[]
     */
    public byte[] svnRead(final String filePath, String limit)
            throws GenericException {
        try {
            setupLibrary();
            SVNProperties fileProperties = new SVNProperties();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long revision = -1;
            if (limit != null && !limit.isEmpty() && limit != "0") {
                log.debug("limit::" + limit);
                revision = getRevs(filePath, Long.parseLong(limit.substring(1)));
            }
            getRepository().getFile(filePath, revision, fileProperties,
                    byteArrayOutputStream);

            log.debug("byte[] data:" + byteArrayOutputStream.toByteArray());
            return byteArrayOutputStream.toByteArray();
        } catch (SVNException svne) {
            log.error("ERR-DMS-0004:svnRead() throws : " + svne.toString());
            // throw new GenericException("ERR-DMS-0004");
        } catch (GenericException e) {

            log.error("svnRead() throws : " + e.getErrorMessage());
            if (e.getCmfErrorCode().equals("ERR-DMS-0008")) {
                throw e;
            }
        } finally {
            if (this.repository != null) {
                this.repository = null;
            }
        }
        return null;
    }

    /**
     * Delete given file from SVN.
     * @param filePath
     *            file path
     * @throws GenericException
     *             void
     */
    public boolean svnDelete(String filePath) throws GenericException {
        ISVNEditor editor = null;
        try {
            setRepository(setupLibrary());
            boolean isPresent = false;

            // delete file & related files
            String Dir = dmsutils.splitDirectoryName(filePath);
            String fname = dmsutils.splitFileName(filePath);
            String ext = dmsutils.splitFileExtension(fname);
            String fileid = dmsutils.splitId(fname);

            Collection<SVNDirEntry> entriesList = getRepository().getDir(Dir,
                    -1, null, (Collection<?>) null);
            editor = getRepository().getCommitEditor(
                    "directory and file deletion", null);
            editor.openRoot(-1);
            if (ext.equalsIgnoreCase("xml")) {
                // Delete all related files starts with file id.
                for (Iterator<?> entries = entriesList.iterator(); entries
                        .hasNext();) {
                    SVNDirEntry entry = (SVNDirEntry) entries.next();
                    String svnname = entry.getName();
                    if (svnname.startsWith(fileid)) {
                        editor.deleteEntry(Dir + "/" + svnname, -1);
                        isPresent = true;
                    }
                }
            } else {
                editor.deleteEntry(Dir + "/" + fname, -1);
                isPresent = true;
            }
            if (!isPresent) {
                log.error("ERR-DMS-0004: svnDelete():");
                throw new GenericException(domain, "ERR-DMS-0004");
            }
            if (editor != null) {
                editor.closeDir();
                editor.closeEdit();
                editor = null;
            }
            deleteEmptyDirectory(Dir);
            return isPresent;
        } catch (SVNException svne) {
            log.error("ERR-DMS-0004 svnDelete() throws : " + svne.toString());
            throw new GenericException(domain, "ERR-DMS-0004",
                    "File not found", "dirPath:" + filePath, svne);
        } catch (GenericException e) {
            log.error("svnDelete() throws  : " + e.toString());
            throw new GenericException(domain, "ERR-DMS-0004",
                    "File not found", "dirPath:" + filePath, e);
        }
    }

    private boolean deleteEmptyDirectory(String Dir) throws GenericException {
        ISVNEditor editor = null;
        try {
            String localDirPath = "";
            boolean isPresent = false;
            Collection<SVNDirEntry> entriesList = getRepository().getDir(Dir,
                    -1, null, (Collection<?>) null);
            editor = getRepository().getCommitEditor(
                    "directory and file deletion", null);
            editor.openRoot(-1);
            if (entriesList != null && entriesList.size() > 0) {
                if (editor != null) {
                    editor.closeDir();
                    editor.closeEdit();
                    editor = null;
                }
                return true;
            } else {
                editor.deleteEntry(Dir, -1);
            }
            if (Dir.lastIndexOf(dmsutils.getDirectorySeparator()) > 0) {
                localDirPath = Dir.substring(0,
                        Dir.lastIndexOf(dmsutils.getDirectorySeparator()));
            }
            if (localDirPath.isEmpty()) {
                return true;
            }
            if (editor != null) {
                editor.closeDir();
                editor.closeEdit();
                editor = null;
            }
            isPresent = deleteEmptyDirectory(localDirPath);

            return isPresent;
        } catch (SVNException svne) {
            log.error("ERR-DMS-0004 svnDelete() throws : " + svne.toString());
            throw new GenericException(domain, "ERR-DMS-0004",
                    "Directory not found", "dirPath:" + Dir, svne);
        } catch (GenericException e) {
            log.error("svnDelete() throws  : " + e.toString());
            throw e;

        } finally {
            try {
                if (editor != null) {
                    editor.closeDir();
                    editor.closeEdit();
                }
                if (this.repository != null) {
                    this.repository = null;
                }
            } catch (SVNException e) {
                log.error("svnDelete()deleteEmptyDirectory throws : "
                        + e.toString());
            }
        }

    }

    /**
     * Initialise SVN Repository authenticate based on Config parameters
     * mentioned in {@link PropertiesUtil} file.
     * @return SVNRepository
     * @throws GenericException
     *             SVNRepository
     */
    public SVNRepository setupLibrary() throws GenericException {
        String svnUrl = null;
        try {
            if (getRepository() == null) {
                svnUrl = propUtil.getProperties(domain, dmsutils.getService(),
                        "Configuration", "url");
                log.debug("SVN URL: " + svnUrl);
                String svnUsrName = propUtil.getProperties(domain,
                        dmsutils.getService(), "Configuration", "username");
                String svnPwd = propUtil.getProperties(domain,
                        dmsutils.getService(), "Configuration", "password");
                DAVRepositoryFactory.setup();
                setRepository(SVNRepositoryFactory.create(SVNURL
                        .parseURIEncoded(svnUrl)));
                ISVNAuthenticationManager authManager = SVNWCUtil
                        .createDefaultAuthenticationManager(svnUsrName, svnPwd);
                getRepository().setAuthenticationManager(authManager);
                SVNNodeKind nodeKind = getRepository().checkPath("", -1);
                if (nodeKind == SVNNodeKind.NONE) {
                    log.error("Unable to setup SVN for " + svnUrl);
                    throw new GenericException(domain, "ERR-DMS-0010",
                            "Unable to setup SVN" + "& svnurl:" + svnUrl);
                } else if (nodeKind == SVNNodeKind.FILE) {
                    log.error("Unable to setup SVN for " + svnUrl);
                    throw new GenericException(domain, "ERR-DMS-0010",
                            "Unable to setup SVN & svnurl:" + svnUrl);
                }
            }
            return getRepository();
        } catch (SVNException svne) {
            log.error("setupLibrary() throws : " + svne.toString());
            throw new GenericException(domain, "ERR-DMS-0010",
                    "Unable to setup SVN & svnurl:" + svnUrl, svne);
        } catch (GenericException e) {
            log.error("setupLibrary() throws : " + e.toString());
            throw new GenericException(domain, "ERR-DMS-0010",
                    "Unable to setup SVN & svnurl:" + svnUrl, e);
        }
    }

    /**
     * Setter method for SVNRepository.
     * @param repository
     *            SVNRepository object
     *            void
     */
    private void setRepository(final SVNRepository repository) {
        this.repository = repository;
    }

    /**
     * Getter method for SVNRepository.
     * @return SVNRepository
     */
    private SVNRepository getRepository() {
        return repository;
    }

    private long getRevs(String workingFileName, long limit) {

        Long thisRevision = -1L;

        LogEntryPreviousRevFinder handler = new LogEntryPreviousRevFinder(
                workingFileName, thisRevision);

        try {
            log.debug("(limit + 1)" + (limit + 1));
            Long revisiontr = repository.log(new String[] {workingFileName },
                    thisRevision, 0L, false, true, (limit + 1), handler);
            log.debug("revisiontr:::" + revisiontr);
        } catch (SVNException e) {
            e.printStackTrace();
        }

        int revisionsize = handler.getRevisions().size();

        if (revisionsize <= limit) {
            log.error("getRevs() Requested File Revision Not Found : "
                    + workingFileName);
            throw new GenericException(domain, "ERR-DMS-0008");
        }

        return handler.getRevisions().get((int) limit);
    }

    private class LogEntryPreviousRevFinder implements ISVNLogEntryHandler {
        private String          interestingFile;
        private long            thisRevision;
        private boolean         isSuccess;
        private ArrayList<Long> revisions = new ArrayList<Long>();

        public LogEntryPreviousRevFinder(String interestingFile, long revision) {

            this.interestingFile = interestingFile;
            this.thisRevision = revision;
            isSuccess = false;
        }

        /**
         * 
         */
        @Override
        public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {

            log.debug("Log Entry::" + logEntry.getRevision());
            revisions.add(logEntry.getRevision());
        }

        public List<Long> getRevisions() {
            log.debug("Revisions::" + revisions);
            return revisions;
        }

    }

}
