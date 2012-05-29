package in.nic.cmf.searchengine;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

import java.util.HashMap;
import java.util.Map;

@ServiceName ("searchengine-1.0.0")
public class SearchEngineImpl implements CMFService {

    private static HashMap<String, SearchEngineImpl> hashSearchEngineImpl = new HashMap<String, SearchEngineImpl>();

    public static SearchEngineImpl getInstance(String domain) {
        if (!hashSearchEngineImpl.containsKey(domain)) {
            hashSearchEngineImpl.put(domain, new SearchEngineImpl());
        }
        return hashSearchEngineImpl.get(domain);
    }

    private LogTracer log;

    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        SearchEngineService.getInstance(domain).setLogTracer(log);
        return SearchEngineService.getInstance(domain).add(domain, entity,
                parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String ids,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        SearchEngineService.getInstance(domain).setLogTracer(log);
        return SearchEngineService.getInstance(domain).deleteByID(domain,
                entity, ids, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters)
            throws GenericException {
        SearchEngineService.getInstance(domain).setLogTracer(log);
        return SearchEngineService.getInstance(domain).deleteByQuery(domain,
                entity, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        SearchEngineService.getInstance(domain).setLogTracer(log);
        return SearchEngineService.getInstance(domain).find(domain, entity,
                parameters);
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-SE-0015");
    }

    @Override
    public Map<String, Map<String, Object>> read(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) throws GenericException {
        throw new GenericException(arg0, "ERR-SE-0015");
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Integer result = Management.putProperties(
                domain,
                service,
                entity,
                SearchEngineService.getInstance(domain).getConfigXMLAsMap(
                        domain, parameters));

        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");

        HashMap<String, Object> content = new HashMap<String, Object>();
        HashMap<String, Object> out = new HashMap<String, Object>();
        out.put("Success", result + " rows inserted");
        content.put("Collections", out);
        output.put("content", FormatXml.getInstance(domain).out(content));
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
        output.put("content", FormatXml.getInstance(domain).out(content));
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
        output.put("content", FormatXml.getInstance(domain).out(content));
        log.debug("deleteManage parameters:" + parameters);
        return parameters;
    }

}
