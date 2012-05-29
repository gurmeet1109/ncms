package in.nic.cmf.analytics;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AnalyticsServiceImpl implements CMFService {

    private PropertiesUtil proputil;
    private LogTracer      log;

    public void setLogTracer(LogTracer log) {
        // TODO Auto-generated method stub
        this.log = log;
    }

    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        return putXml(domain, queryParams);

    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    public Map<String, Map<String, Object>> putXml(String domain,
            Map<String, Map<String, Object>> queryParams) {
        proputil = PropertiesUtil.getInstanceof(domain, "analytics");
        LogTracer logtracer = new LogTracer(domain,
                proputil.getProperty("logFileName"), 1,
                proputil.getProperty("directoryPath") + domain + "/",
                Integer.parseInt(proputil.getProperty("noOfFileToMaintain")));

        Map param_map = (Map) queryParams.get("input").get("queryParams");

        String str;
        StringBuilder builder = new StringBuilder();
        if (param_map != null) {
            Iterator iterator = param_map.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry me = (Map.Entry) iterator.next();
                if (me.getKey().equals("browser") && me.getValue() != null) {
                    str = ((String) me.getValue());

                    if (str != null)

                    builder.append("##browser=" + str);

                } else if (me.getKey().equals("method")
                        && me.getValue() != null) {
                    str = (String) me.getValue();

                    if (str != null)

                    builder.append("##method=" + str);
                } else if (me.getKey().equals("contentid")
                        && me.getValue() != null) {
                    str = (String) me.getValue();

                    if (str != null)

                    builder.append("##contentid=" + str);

                } else if (me.getKey().equals("ip") && me.getValue() != null) {

                    str = (String) me.getValue();

                    if (str != null) builder.append("##ip=" + str);

                } else if (me.getKey().equals("currenturl")
                        && me.getValue() != null) {

                    str = null;
                    if (str != null)

                    builder.append("##currenturl=" + str);

                } else if (me.getKey().equals("refferelid")
                        && me.getValue() != null) {

                    str = (String) me.getValue();
                    if (str != null)

                    builder.append("##refferelid=" + str);
                } else if (me.getKey().equals("protocol")
                        && me.getValue() != null) {

                    str = (String) me.getValue();
                    if (str != null)

                    builder.append("##protocol=" + str);
                }
            }

            logtracer.info("AnalyticsResourceController  [["
                    + builder.toString() + "]]");

        }
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", "200");
        output.put("content", "<status>success</status>");
        queryParams.put("output", output);

        return queryParams;

    }

    @Override
    public Map<String, Map<String, Object>> read(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) {
        // TODO Auto-generated method stub
        return null;
    }
}
