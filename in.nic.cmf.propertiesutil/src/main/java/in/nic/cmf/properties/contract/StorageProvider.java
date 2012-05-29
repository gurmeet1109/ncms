package in.nic.cmf.properties.contract;

import in.nic.cmf.logger.LogTracer;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface StorageProvider.
 */
public interface StorageProvider {

    /**
     * Gets the.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @param key
     *            the key
     * @return the string
     */
    String get(String domain, String service, String entity, String key);

    /**
     * Gets the.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @param key
     *            the key
     * @return the map
     */
    Map<String, String> get(String domain, String service, String entity,
            List<String> key);

    /**
     * Gets the.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @return the map
     */
    Map<String, String> get(String domain, String service, String entity);

    /**
     * Gets the.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @return the map
     */
    Map<String, Map<String, String>> get(String domain, String service);

    /**
     * Put.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @param key
     *            the key
     * @param value
     *            the value
     * @return the boolean
     */
    Integer put(String domain, String service, String entity, String key,
            String value);

    /**
     * Put.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @param keys
     *            the keys
     * @return the boolean
     */
    Integer put(String domain, String service, String entity,
            Map<String, String> keys);

    /**
     * Put.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param collection
     *            the collection
     * @return the boolean
     */
    Integer put(String domain, String service,
            Map<String, Map<String, String>> collection);

    /**
     * Delete.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @param key
     *            the key
     * @return the boolean
     */
    Integer delete(String domain, String service, String entity, String key);

    /**
     * Delete.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @param key
     *            the key
     * @return the map
     */
    Map<String, Integer> delete(String domain, String service, String entity,
            List<String> key);

    /**
     * Delete.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @param entity
     *            the entity
     * @return the map
     */
    Integer delete(String domain, String service, String entity);

    /**
     * Delete.
     * @param domain
     *            the domain
     * @param service
     *            the service
     * @return the map
     */
    Integer delete(String domain, String service);

    void setLogTracer(LogTracer log);

}
