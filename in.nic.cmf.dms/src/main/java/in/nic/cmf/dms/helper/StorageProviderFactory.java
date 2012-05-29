package in.nic.cmf.dms.helper;

import in.nic.cmf.dms.contract.StorageProvider;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Factory Class will produce and release Storage provider instance on request.
 * @author bala
 * @since Feb 10, 2012
 *        dms;
 */
public class StorageProviderFactory {

    /**
     * StorageProviderFactory instance creation.
     */
    private static final StorageProviderFactory STORAGE_PROVIDER_FACTORY = new StorageProviderFactory();
    /**
     * LogTracer object creation and initialisation.
     */
    private LogTracer                           log                      = null;
    /**
     * To store storageProvider instances.
     */
    private HashMap<String, StorageProvider>    storageProviderServices  = new HashMap<String, StorageProvider>();

    /**
     * private constructor StorageProviderFactory.
     */
    private StorageProviderFactory() {
    }

    /**
     * getting instance of StorageProviderFactory.
     * @return StorageProviderFactory
     */
    public static StorageProviderFactory getInstance() {

        return STORAGE_PROVIDER_FACTORY;
    }

    /**
     * Add new storageType instance.
     * @param storageTypeName
     *            Name of the Storage
     * @param storageType
     *            Storagetype instance
     *            void
     */
    private void addStorage(final String storageTypeName,
            final StorageProvider storageType) {
        storageProviderServices.put(storageTypeName, storageType);
    }

    /**
     * Getting storageType instance.
     * @param storageTypeName
     *            Name of the Storage
     * @return StorageProvider Storagetype instance
     */
    private StorageProvider getStorage(final String storageTypeName) {

        return storageProviderServices.get(storageTypeName);
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
     * Based on StorageType name Storage instance will be returned.
     * @param storageTypeName
     *            Name of the Storage Type
     * @return StorageProvider
     */
    public StorageProvider getStorageProvider(final String domain,
            final String storageTypeName) {

        try {

            if (getStorage(storageTypeName) == null) {
                String storageProviderFile = this.getClass().getPackage()
                        .getName()
                        + "." + storageTypeName;
                // StorageProvider sp = (StorageProvider) Class.forName(
                // storageProviderFile).newInstance();
                Constructor<StorageProvider> constructor = (Constructor<StorageProvider>) Class
                        .forName(storageProviderFile).getDeclaredConstructor(
                                String.class);
                constructor.setAccessible(true);
                StorageProvider sp = (StorageProvider) constructor
                        .newInstance(domain);
                Class spclass = Class.forName(storageProviderFile);

                sp.setLogTracer(log);
                addStorage(storageTypeName, sp);
            }
            return getStorage(storageTypeName);

        } catch (InstantiationException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        } catch (IllegalAccessException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        } catch (ClassNotFoundException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        } catch (SecurityException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        } catch (NoSuchMethodException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        } catch (IllegalArgumentException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        } catch (InvocationTargetException e) {
            log.error("StorageTypeDeterminer throws " + e);
            throw new GenericException(domain, "ERR-DMS-0007");
        }

    }
}
