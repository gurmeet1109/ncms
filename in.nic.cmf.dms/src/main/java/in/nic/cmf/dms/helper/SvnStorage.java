package in.nic.cmf.dms.helper;

import in.nic.cmf.dms.DMSUtils;
import in.nic.cmf.dms.contract.StorageProvider;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SVN based storage.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
public class SvnStorage implements StorageProvider {

    /**
     * SvnUtils initialisation.
     */
    private SvnUtils  svnutils;
    /**
     * DMSUtils initialisation.
     */
    private DMSUtils  dmsutils;

    /**
     * PropertiesUtil initialisation.
     */
    // private PropertiesUtil propUtil = new PropertiesUtil("dms");

    /**
     * LogTracer initialisation.
     */
    private LogTracer log;

    private SvnStorage(String domain) {
        // propUtil = PropertiesUtil.getInstanceof(domain, "dms");
        // rootDirectory = propUtil.get("FileStorageRootDirectory");
        dmsutils = DMSUtils.getInstanceof(domain);
        svnutils = SvnUtils.getInstanceof(domain);
    }

    /**
     * getting instance of StorageProvider.
     * @return StorageProvider
     */
    public static StorageProvider getInstanceof(String domain) {
        return new SvnStorage(domain);
    }

    /**
     * It will read file based on given SVN revision value
     * @param domain
     *            domainname
     * @param fileName
     *            filename
     * @param revision
     *            revision no
     * @return byte[]
     *         file content
     */
    public byte[] get(final String domain, final String fileName,
            final String revision) {

        log.debug("SVN GET fileName: " + fileName);
        log.debug("SVN FULL PATH :" + getFullPath(domain, fileName));
        return svnutils.svnRead(getFullPath(domain, fileName), revision);
    }

    @Override
    public byte[] get(final String domain, final String fileName) {

        log.debug("SVN GET fileName: " + fileName);
        log.debug("SVN FULL PATH :" + getFullPath(domain, fileName));
        return svnutils.svnRead(getFullPath(domain, fileName), null);
    }

    @Override
    public Map<String, byte[]> get(final String domain,
            final List<String> fileNames) {
        Map<String, byte[]> output = new HashMap<String, byte[]>();

        for (String filename : fileNames) {
            byte[] filedata = get(domain, filename);
            if (filedata != null) {
                output.put(filename, filedata);
            } else {
                log.debug("SVN FileNotFound: " + filename);
            }
        }
        return output;
    }

    @Override
    public Boolean put(final String domain, final String fileName,
            final byte[] data) {
        String fileNamewithDir = getFullPath(domain, fileName);
        String dirPath = dmsutils.splitDirectoryName(fileNamewithDir);
        String fileNameExt = dmsutils.getFileNameWithExtension(fileName);
        return svnutils.svnAdd(dirPath, fileNameExt, data);

    }

    @Override
    public Map<String, Boolean> put(final String domain,
            final Map<String, byte[]> fileNames) {
        Map<String, Boolean> output = new HashMap<String, Boolean>();
        for (String key : fileNames.keySet()) {
            Boolean out = put(domain, key, fileNames.get(key));
            output.put(key, out);

        }
        return output;
    }

    @Override
    public Boolean delete(final String domain, final String fileName) {
        return svnutils.svnDelete(getFullPath(domain, fileName));

    }

    @Override
    public Map<String, Boolean> delete(final String domain,
            final List<String> fileNames) {
        Map<String, Boolean> output = new HashMap<String, Boolean>();
        for (String filename : fileNames) {
            boolean out = delete(domain, filename);
            output.put(filename, out);
        }
        return output;
    }

    /**
     * This method initialise LogTracer log.
     * @param log
     *            LogTracer object
     */
    @Override
    public void setLogTracer(final LogTracer log) {
        this.log = log;
        svnutils.setLogTracer(log);
    }

    /**
     * get the file full directory path based on file name and domain.
     * @param domain
     *            domain name
     * @param fileName
     *            file name
     * @return String directory path
     */
    private String getFullPath(final String domain, final String fileName) {

        String localFileName = "";
        String fileNameExt = dmsutils.getFileNameWithExtension(fileName);
        localFileName = domain + dmsutils.getDirectorySeparator()
                + dmsutils.getDirectoryPath(fileNameExt)
                + dmsutils.getDirectorySeparator() + fileNameExt;
        return localFileName;
    }
}
