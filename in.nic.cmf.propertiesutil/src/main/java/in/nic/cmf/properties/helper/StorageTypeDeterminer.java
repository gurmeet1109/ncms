package in.nic.cmf.properties.helper;

import in.nic.cmf.logger.LogTracer;

public class StorageTypeDeterminer {
    /**
     * StorageTypeDeterminer initialisation.
     */
    private static final StorageTypeDeterminer STD     = new StorageTypeDeterminer();

    /**
     * LogTracer initialisation.
     */
    private LogTracer                          log;

    private String                             storage = "SqliteStorage";

    /**
     * private constructor StorageTypeDeterminer.
     */
    private StorageTypeDeterminer() {

    }

    /**
     * getting instance of StorageTypeDeterminer.
     * @return StorageTypeDeterminer
     */
    public static StorageTypeDeterminer getInstance() {
        return STD;
    }

    /**
     * Based on domain storage provider object will be return.
     * @param domain
     *            domain name
     * @return StorageProvider
     */
    public String getStorage(final String domain) {

        return storage;
    }

    /**
     * This method initialise LogTracer log.
     * @param arg0
     *            LogTracer object
     */
    public void setLogTracer(final LogTracer arg0) {
        this.log = arg0;
    }
}
