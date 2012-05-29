package in.nic.cmf.dms.helper;

import in.nic.cmf.dms.DMSUtils;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

/**
 * This class will maintain all Storages instances, based on request instance
 * will be return.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
public class StorageTypeDeterminer {

    /**
     * StorageTypeDeterminer initialisation.
     */
    private static final StorageTypeDeterminer STD      = new StorageTypeDeterminer();

    /**
     * PropertyManagement initialisation.
     */
    private PropertyManagement                 propUtil = PropertyManagement
                                                                .getInstance();

    /**
     * DMSUtils object creation and initialisation.
     */
    private DMSUtils                           dmsutils;
    /**
     * LogTracer initialisation.
     */
    private LogTracer                          log;

    /**
     * StorageProvider instances are stored as HashMap.
     */
    // private HashMap<String, StorageProvider> storage = new HashMap<String,
    // StorageProvider>();

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
        // proputil = PropertiesUtil.getInstanceof(domain, "dms");
        // dmsutils = DMSUtils.getInstanceof(domain);
        // String storageRef = proputil.get("storage");
        // if (dmsutils.isEmpty(proputil.get(domain))) {
        // storageRef = proputil.get("default");
        // }
        // return storageRef;
        return propUtil
                .getProperties(domain, "dms", "Configuration", "storage");
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
