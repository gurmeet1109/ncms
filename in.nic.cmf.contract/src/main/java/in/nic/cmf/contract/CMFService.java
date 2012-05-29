package in.nic.cmf.contract;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

/**
 * The Interface CMFService.
 */
@ServiceName ("")
public interface CMFService {

    /**
     * Sets the log tracer.
     * @param log
     *            the new log tracer
     */
    void setLogTracer(LogTracer log);

    /**
     * Adds the.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @param containsBinary
     *            the contains binary
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters);

    /**
     * reads by POST method using search engine and it implements in dms
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters);

    /**
     * Find.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters);

    /**
     * Find by id.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> findById(String domain, String entity,
            String id, Map<String, Map<String, Object>> parameters);

    /**
     * Delete by query.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return true, if successful
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters);

    /**
     * Delete by id.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return true, if successful
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> deleteByID(String domain, String entity,
            String id, Map<String, Map<String, Object>> parameters);

    /**
     * Adds the Management entity
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> addManage(String domain, String service,
            String entity, Map<String, Map<String, Object>> parameters);

    /**
     * Find the Management entity
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> findManage(String domain, String service,
            String entity, Map<String, Map<String, Object>> parameters);

    /**
     * Delete the Management entity
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return true, if successful
     * @throws GenericException
     *             the generic exception
     */
    Map<String, Map<String, Object>> deleteManage(String domain,
            String service, String entity,
            Map<String, Map<String, Object>> parameters);
}
