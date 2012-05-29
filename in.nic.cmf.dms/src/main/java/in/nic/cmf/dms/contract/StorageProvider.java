package in.nic.cmf.dms.contract;

import in.nic.cmf.logger.LogTracer;

import java.util.List;
import java.util.Map;

/**
 * Interface for various storage services.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
public interface StorageProvider {

    /**
     * Get file data as a byte[] from storage.
     * @param domain
     *            domain name
     * @param fileName
     *            filename
     * @return byte[] data
     */
    byte[] get(String domain, String fileName);

    /**
     * Get file data as a byte[] from storage.
     * @param domain
     *            domain name
     * @param fileName
     *            filename
     * @param revision
     *            revision of the file
     * @return byte[] data
     */
    byte[] get(String domain, String fileName, String revision);

    /**
     * Get List of files data from storage.
     * @param domain
     *            domain name
     * @param fileNames
     *            List of file names
     * @return Map<String,byte[]> List of files names and data
     */
    Map<String, byte[]> get(String domain, List<String> fileNames);

    /**
     * put the data file in storage in given filename.
     * @param domain
     *            domain name
     * @param fileName
     *            filename
     * @param data
     *            byte[]
     * @return Boolean
     */
    Boolean put(String domain, String fileName, byte[] data);

    /**
     * Put the list of data files in storage in given filenames.
     * @param domain
     *            domain name
     * @param fileNames
     *            Map of files with data.
     * @return Map<String,Boolean>
     */
    Map<String, Boolean> put(String domain, Map<String, byte[]> fileNames);

    /**
     * delete the given filename in the storage.
     * @param domain
     *            domain name
     * @param fileName
     *            filename
     * @return Boolean
     */
    Boolean delete(String domain, String fileName);

    /**
     * Delete the list of filenames in the storage.
     * @param domain
     *            domain name
     * @param fileNames
     *            List of file names
     * @return Map<String,Boolean>
     */
    Map<String, Boolean> delete(String domain, List<String> fileNames);

    /** LogTracer reference. */
    LogTracer LOG = null;

    /**
     * Sets the log tracer.
     * @param log
     *            the new log tracer
     */
    void setLogTracer(LogTracer log);
}
