package in.nic.cmf.dms.helper;

import in.nic.cmf.dms.DMSUtils;
import in.nic.cmf.dms.contract.StorageProvider;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File based Storage.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
public class FileStorage implements StorageProvider {

    /**
     * PropertyManagement initialisation.
     */

    private PropertyManagement propUtil = PropertyManagement.getInstance();

    /**
     * Storage file path.
     */
    private String             rootDirectory;

    /**
     * DMSUtils object creation and initialisation.
     */
    private DMSUtils           dmsutils;

    /**
     * LogTracer initialisation.
     */
    private LogTracer          log;

    private FileStorage(String domain) {

        rootDirectory = propUtil.getProperties(domain, dmsutils.getService(),
                "Configuration", "rootdirectory").trim();
        dmsutils = DMSUtils.getInstanceof(domain);
    }

    /**
     * getting instance of StorageProvider.
     * @return StorageProvider
     */
    public static StorageProvider getInstanceof(String domain) {
        return new FileStorage(domain);
    }

    @Override
    public byte[] get(final String domain, final String fileName) {

        File file = new File(getFullPath(domain, fileName));
        byte[] b = new byte[(int) file.length()];
        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            return b;
        } catch (FileNotFoundException e) {
            log.error("ERR-DMS-0004:FileStorage FileNotFoundException throws:"
                    + e);
            // throw new GenericException(domain,"ERR-DMS-0004");
        } catch (IOException e) {
            log.error("ERR-DMS-0002:FileStorage IOException throws:" + e);
            // throw new GenericException(domain,"ERR-DMS-0002");
        }
        return null;

    }

    @Override
    public byte[] get(String domain, String fileName, String revision) {
        return null;
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
                log.debug("get FileNotFound: " + filename);
            }
        }
        return output;
    }

    @Override
    public Boolean put(final String domain, final String fileName,
            final byte[] data) {

        try {
            String fileNamewithDir = getFullPath(domain, fileName);

            if (!createDirectory(domain, fileNamewithDir)) {
                return false;
            }
            File f = new File(fileNamewithDir);
            OutputStream out = new FileOutputStream(f);
            out.write(data);
            out.close();
            return true;
        } catch (IOException e) {
            log.error("ERR-DMS-0002: FileStorage IOException throws : " + e);
            throw new GenericException(domain, "ERR-DMS-0002");
        }

    }

    @Override
    public Map<String, Boolean> put(final String domain,
            final Map<String, byte[]> files) {

        Map<String, Boolean> output = new HashMap<String, Boolean>();
        for (String key : files.keySet()) {
            Boolean out = put(domain, key, files.get(key));
            output.put(key, out);
        }
        return output;
    }

    @Override
    public Boolean delete(final String domain, final String fileName) {

        try {
            String file = getFullPath(domain, fileName);
            return deleteFileName(file);
        } catch (Exception e) {
            log.error("ERR-DMS-0005:FileStorage delete Exception throws : " + e);
            throw new GenericException(domain, "ERR-DMS-0005");
        }
    }

    @Override
    public Map<String, Boolean> delete(final String domain,
            final List<String> fileNames) {
        Map<String, Boolean> output = new HashMap<String, Boolean>();

        for (String filename : fileNames) {
            output.put(filename, delete(domain, filename));
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
    }

    /**
     * delete given file name and check recursively in directory path and delete
     * it ,
     * if it's empty.
     * @param fileName
     *            file name
     * @return boolean
     */
    private boolean deleteFileName(final String fileName) {

        File file = new File(fileName);
        if (file.listFiles() != null && file.listFiles().length != 0) {
            return true;
        }
        String localFileName = "";
        boolean success = false;
        if (!file.isDirectory()) {
            // Delete exact file and related files id.jpg,id.xls...
            String ext = dmsutils.splitFileExtension(fileName);
            String fileid = dmsutils.splitId(dmsutils.splitFileName(fileName));
            File dir = new File(dmsutils.splitDirectoryName(fileName));
            if (ext.equalsIgnoreCase("xml")) {
                File[] files = dir.listFiles();
                for (File fi : files) {
                    if (fi.getName().startsWith(fileid)) {
                        success = fi.delete();
                    }
                }
            } else {
                // Delete exact file
                success = file.delete();
            }
        } else {
            // delete empty directory
            success = file.delete();
        }

        if (!success) {
            return success;
        }
        if (fileName.lastIndexOf(dmsutils.getDirectorySeparator()) > 0) {
            localFileName = fileName.substring(0,
                    fileName.lastIndexOf(dmsutils.getDirectorySeparator()));
        }
        if (localFileName.isEmpty()) {
            return true;
        }
        success = deleteFileName(localFileName);
        return success;
    }

    /**
     * create directories recursively.
     * @param domain
     *            domain name
     * @param fileName
     *            filename
     * @return String directory path
     */
    private boolean createDirectory(final String domain, final String fileName) {

        try {

            String dirPath = dmsutils.splitDirectoryName(fileName);
            new File(dirPath).mkdirs();
            return true;
        } catch (SecurityException e) {
            log.error("FileStorage createDirectory Exception throws : " + e);
            throw new GenericException(domain, "ERR-DMS-0013");
        }
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
        localFileName = rootDirectory + domain
                + dmsutils.getDirectorySeparator()
                + dmsutils.getDirectoryPath(fileNameExt)
                + dmsutils.getDirectorySeparator() + fileNameExt;
        return localFileName;
    }

}
