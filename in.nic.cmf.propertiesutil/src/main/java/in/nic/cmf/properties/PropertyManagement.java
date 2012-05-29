package in.nic.cmf.properties;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.contract.StorageProvider;
import in.nic.cmf.properties.helper.StorageProviderFactory;
import in.nic.cmf.properties.helper.StorageTypeDeterminer;

import java.util.List;
import java.util.Map;

public class PropertyManagement {

    private LogTracer                 log                = null;

    private StorageTypeDeterminer     storageTypeDeterminer;

    private StorageProviderFactory    storageProviderFactory;

    private static PropertyManagement propertyManagement = new PropertyManagement();

    public static PropertyManagement getInstance() {
        return propertyManagement;
    }

    private PropertyManagement() {
        storageTypeDeterminer = StorageTypeDeterminer.getInstance();
        storageProviderFactory = StorageProviderFactory.getInstance();
    }

    private void setLog(String domain) {
        log = new LogTracer(domain, "propertymanage");
        setLogger(log);
    }

    public void setLogger(final LogTracer log) {
        storageTypeDeterminer.setLogTracer(log);
        storageProviderFactory.setLogTracer(log);
    }

    private StorageProvider getStorageProvider(String domain) {
        setLog(domain);
        return storageProviderFactory.getStorageProvider(domain,
                storageTypeDeterminer.getStorage(domain));
    }

    public String getProperties(String domain, String service, String entity,
            String key) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.get(domain, service, entity, key);
    }

    public Map<String, String> getProperties(String domain, String service,
            String entity, List<String> keys) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.get(domain, service, entity, keys);
    }

    public Map<String, String> getProperties(String domain, String service,
            String entity) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.get(domain, service, entity);
    }

    public Map<String, Map<String, String>> getProperties(String domain,
            String service) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.get(domain, service);
    }

    public Integer putProperties(String domain, String service, String entity,
            String key, String value) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.put(domain, service, entity, key, value);
    }

    public Integer putProperties(String domain, String service, String entity,
            Map<String, String> keys) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.put(domain, service, entity, keys);
    }

    public Integer putProperties(String domain, String service,
            Map<String, Map<String, String>> collection) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.put(domain, service, collection);
    }

    public Integer deleteProperties(String domain, String service,
            String entity, String key) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.delete(domain, service, entity, key);
    }

    public Map<String, Integer> deleteProperties(String domain, String service,
            String entity, List<String> keys) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.delete(domain, service, entity, keys);
    }

    public Integer deleteProperties(String domain, String service, String entity) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.delete(domain, service, entity);
    }

    public Integer deleteProperties(String domain, String service) {
        StorageProvider sp = getStorageProvider(domain);
        return sp.delete(domain, service);
    }
}
