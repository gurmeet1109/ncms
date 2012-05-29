package in.nic.cmf.ruleengine;

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

@ServiceName ("ruleengine")
public class RuleEngineImpl implements CMFService {

    // private RuleEngineService ruleEngineService;
    // PropertiesUtil putil;
    private LogTracer                              log;                                                       // =
                                                                                                               // new
                                                                                                               // LogTracer("tidel.in","ruleengine");;

    // RuleEngineHelper ruleEngineHelper = RuleEngineHelper
    // .getInstance();
    // public static final RuleEngineImpl ruleengineImpl = new RuleEngineImpl();
    // private DirectoryLogic dirLogic;
    private static HashMap<String, RuleEngineImpl> hashRuleEngineImpl = new HashMap<String, RuleEngineImpl>();

    public static RuleEngineImpl getInstance(String domain) {
        if (!hashRuleEngineImpl.containsKey(domain)) {
            hashRuleEngineImpl.put(domain, new RuleEngineImpl());
        }
        return hashRuleEngineImpl.get(domain);
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        RuleEngineService.getInstanceof(domain).setLogTracer(log);
        System.out.println("inside add methods");
        return RuleEngineService.getInstanceof(domain).post(domain, entity,
                parameters);
    }

    public Map<String, Map<String, Object>> deleteByID(String arg0,
            String arg1, String arg2, Map<String, Map<String, Object>> arg3) {
        RuleEngineService.getInstanceof(arg0).setLogTracer(log);
        return RuleEngineService.getInstanceof(arg0).deleteByID(arg0, arg1,
                arg2, arg3);
    }

    public Map<String, Map<String, Object>> deleteByQuery(String arg0,
            String arg1, Map<String, Map<String, Object>> arg2)
            throws GenericException {
        throw new GenericException(arg0, "ERR-RE-0024");
    }

    public Map<String, Map<String, Object>> find(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) throws GenericException {
        throw new GenericException(arg0, "ERR-RE-0024");
    }

    public Map<String, Map<String, Object>> findById(String arg0, String arg1,
            String arg2, Map<String, Map<String, Object>> arg3)
            throws GenericException {
        throw new GenericException(arg0, "ERR-RE-0024");
    }

    public Map<String, Map<String, Object>> read(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) {
        throw new GenericException(arg0, "ERR-RE-0024");
    }

    public void setLogTracer(final LogTracer log) {
        this.log = log;
    }

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
