package in.nic.cmf.dms;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * DmsServiceImpl implements {@link CMFService} for service.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
@ServiceName ("dms")
public class DmsServiceImpl implements CMFService {

    /**
     * Generic Exception error code.
     */
    private String    err11 = "ERR-DMS-0011";

    private LogTracer log;

    /**
     * @see in.nic.cmf.contract.CMFService#add(java.lang.String,
     *      java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return Object
     */
    @Override
    public Map<String, Map<String, Object>> add(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {
        DmsService.getInstanceof(domain).setLogger(log);
        return DmsService.getInstanceof(domain).add(domain, entity, parameters);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#add(java.lang.String,
     *      java.lang.String,java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param misc
     *            the contains misc
     * @param parameters
     *            the Servlet Request information
     * @return Object
     */
    @Override
    public Map<String, Map<String, Object>> read(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {
        DmsService.getInstanceof(domain).setLogger(log);
        return DmsService.getInstanceof(domain)
                .read(domain, entity, parameters);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#deleteByID(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return Object
     *         true, if successful
     */
    @Override
    public Map<String, Map<String, Object>> deleteByID(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {
        DmsService.getInstanceof(domain).setLogger(log);
        return DmsService.getInstanceof(domain).deleteByID(domain, entity, id,
                parameters);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#deleteByQuery(java.lang.String,
     *      java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return true, if successful
     */
    @Override
    public Map<String, Map<String, Object>> deleteByQuery(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, err11);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#find(java.lang.String,
     *      java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return the Object
     */
    @Override
    public Map<String, Map<String, Object>> find(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, err11);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#findById(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return the Object
     */
    @Override
    public Map<String, Map<String, Object>> findById(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {
        DmsService.getInstanceof(domain).setLogger(log);
        return DmsService.getInstanceof(domain).findById(domain, entity, id,
                parameters);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#setLogTracer
     *      (in.nic.cmf.logger.LogTracer)
     * @param log
     *            the new LogTracer
     */
    @Override
    public void setLogTracer(final LogTracer log) {
        this.log = log;
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Integer result = Management.putProperties(domain, service, entity,
                getConfigXMLAsMap(domain, parameters));

        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");

        HashMap<String, Object> content = new HashMap<String, Object>();
        HashMap<String, Object> out = new HashMap<String, Object>();
        out.put("Success", result + " rows inserted");
        content.put("Collections", out);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.debug("addManage parameters:" + parameters);
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Map<String, String> propList = Management.getProperties(domain,
                service, entity);
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("Collections", propList);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.debug("findManage parameters:" + parameters);
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Integer result = Management.deleteProperties(domain, service, entity);
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");
        HashMap<String, Object> content = new HashMap<String, Object>();
        HashMap<String, Object> out = new HashMap<String, Object>();

        out.put("Success", result + " rows deleted");
        content.put("Collections", out);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.debug("deleteManage parameters:" + parameters);
        return parameters;
    }

    // private Map<String, String> getConfigXMLAsMap(String domain,
    // final Map<String, Map<String, Object>> parameters) {
    // log.methodEntry("getConfigXMLAsMap entry");
    // String content = (String) parameters.get("input").get("content");
    // log.debug("input.content:" + content);
    // if (content.trim().isEmpty()) {
    // throw new GenericException(domain, "ERR-CONFIG-0002");
    // }
    // Map<String, Object> formatXml = FormatXml.getInstanceof(domain).in(
    // content);
    // Map<String, String> collection = (HashMap<String, String>) formatXml
    // .get("Collections");
    //
    // log.debug("input.content.Collection:" + collection);
    // if (collection == null) {
    // throw new GenericException(domain, "ERR-CONFIG-0002");
    // }
    // log.methodExit("getConfigXMLAsMap exit");
    // return collection;
    // }

    private Map<String, String> getConfigXMLAsMap(final String domain,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("getConfigXMLAsMap entry");
        String content = (String) parameters.get("input").get("content");
        log.debug("input.content:" + content);
        if (content.trim().isEmpty()) {
            throw new GenericException(domain, "ERR-CONFIG-0002");
        }
        Map<String, Object> formatXml = FormatXml.getInstanceof(domain).in(
                content);
        Map<String, Object> collection = (HashMap<String, Object>) formatXml
                .get("Collections");
        if (collection == null) {
            throw new GenericException(domain, "ERR-CONFIG-0002");
        }
        log.debug("input.content.Collection:" + collection);
        Map<String, String> output = new HashMap<String, String>();
        List<String> KEY = new ArrayList<String>();
        List<String> VALUE = new ArrayList<String>();

        for (Entry<String, Object> entry : collection.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("ConfigurationKeys")
                    && entry.getValue() instanceof HashMap) {
                HashMap<String, Object> confighash = (HashMap<String, Object>) entry
                        .getValue();
                for (String key : confighash.keySet()) {
                    if (key.equalsIgnoreCase("ConfigurationKey")
                            && confighash.get(key) instanceof ArrayList<?>) {

                        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) confighash
                                .get(key);
                        Iterator it = list.iterator();
                        for (; it.hasNext();) {
                            HashMap<String, Object> hash = (HashMap<String, Object>) it
                                    .next();
                            for (Entry<String, Object> key1 : hash.entrySet()) {
                                if (key1.getKey().equalsIgnoreCase("key")) {
                                    KEY.add((String) key1.getValue());
                                } else if (key1.getKey().equalsIgnoreCase(
                                        "value")) {
                                    VALUE.add((String) key1.getValue());
                                }
                            }
                        }
                    } else if (key.equalsIgnoreCase("ConfigurationKey")
                            && confighash.get(key) instanceof HashMap<?, ?>) {

                        HashMap<String, Object> list = (HashMap<String, Object>) confighash
                                .get(key);

                        for (Entry<String, Object> key1 : list.entrySet()) {
                            if (key1.getKey().equalsIgnoreCase("key")) {
                                KEY.add((String) key1.getValue());
                            } else if (key1.getKey().equalsIgnoreCase("value")) {
                                VALUE.add((String) key1.getValue());
                            }

                        }
                    }

                }

            }
        }
        for (int i = 0; i < KEY.size(); i++) {
            output.put(KEY.get(i), VALUE.get(i));
        }
        log.methodExit("getConfigXMLAsMap exit");
        return output;
    }
}
