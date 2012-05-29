package in.nic.cmf.serviceclient.providers;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientProtocol;
import in.nic.cmf.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MQProvider implements ServiceClientProtocol, CMFService {

    // private LogTracer log = new LogTracer("serviceclient");
    private LogTracer           log;
    private Map<String, String> baseUrl      = new HashMap<String, String>();
    // private PropertiesUtil putil = new PropertiesUtil(
    // "serviceclient");
    private PropertiesUtil      putil;
    // private Utils utils = Utils.getInstance();
    private Utils               utils;
    private Connection          mqConnection = null;
    private String              serviceName  = null;
    private static MQProvider   scMQProtocol = null;
    private String              destination;

    private void assignDomain(String domain) {
        // log = new LogTracer(domain, "serviceclient");
        putil = PropertiesUtil.getInstanceof(domain, "serviceclient");
        utils = Utils.getInstanceof(domain);
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setMqConnection(Connection mqConnection) {
        this.mqConnection = mqConnection;
    }

    public Connection getMqConnection() {
        return mqConnection;
    }

    private MQProvider(String domain, String service) throws GenericException {
        try {
            log = new LogTracer(domain, "serviceclient");
            putil = PropertiesUtil.getInstanceof(domain, "serviceclient");
            utils = Utils.getInstanceof(domain);
            setServiceName(service);
            setBaseUrlFromProperty(service);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public static MQProvider getInstanceOf(String domain, String serviceName) {
        // if(scMQProtocol == null){
        scMQProtocol = new MQProvider(domain, serviceName);
        // }
        return scMQProtocol;
    }

    @Override
    public ServiceClientProtocol getInstance(String domain, String serviceName) {
        // if(scMQProtocol == null){
        scMQProtocol = new MQProvider(domain, serviceName);
        // }
        return scMQProtocol;
    }

    private void setBaseUrl(String urlValue, String strIndex, String service)
            throws GenericException {
        if (urlValue.indexOf(strIndex + "=") != -1) {
            baseUrl.put(service + "_" + strIndex,
                    urlValue.replace(strIndex + "=", ""));
        }
    }

    private void setBaseUrl(String key, String value) {
        baseUrl.put(key, value);
    }

    public void setBaseUrlFromProperty(String service) throws GenericException {

        String baseUrlProperty = putil.getProperty(service);
        log.debug("Service :::" + service);
        // setDestination(putil.getProperty("mq" + service));
        if (utils.isEmpty(baseUrlProperty)) {
            return;
        }
        String[] urlArray = baseUrlProperty.split("\\|");
        try {
            if (urlArray[0].indexOf("rw=") != -1) {
                setBaseUrl(urlArray[0], "rw", service);
            } else {
                setBaseUrl(service + "_rw", baseUrlProperty);
            }
            if (urlArray[1].indexOf("ro=") != -1) {
                setBaseUrl(urlArray[1], "ro", service);
            } else {
                setBaseUrl(service + "_r0", baseUrlProperty);
            }
        } catch (Exception ex) {
            setBaseUrl(service + "_rw", baseUrlProperty);
            setBaseUrl(service + "_r0", baseUrlProperty);
        }

        try {
            if (urlArray[2].indexOf("mq=") != -1) {
                setBaseUrl(urlArray[2], "mq", service);
            } else {
                setBaseUrl(service + "_mq", "DLC.Q");
            }
            /*
             * if (urlArray[3].indexOf("async=") != -1) {
             * setBaseUrl(urlArray[3], "async", service); } else {
             * setBaseUrl(service + "_async", "false"); }
             */

            if (urlArray[3].indexOf("asyncpost=") != -1) {
                setBaseUrl(urlArray[3], "asyncpost", service);
            } else {
                setBaseUrl(service + "_asyncpost", "false");
            }

            if (urlArray[4].indexOf("asyncdelete=") != -1) {
                setBaseUrl(urlArray[4], "asyncdelete", service);
            } else {
                setBaseUrl(service + "_asyncdelete", "false");
            }
        } catch (Exception ex) {
            setBaseUrl(service + "_mq", "DLC.Q");

            setBaseUrl(service + "_asyncpost", "false");
            setBaseUrl(service + "_asyncdelete", "false");
        }
    }

    /*
     * public void setBaseUrlFromProperty(String service) throws
     * GenericException { String baseUrlProperty = putil.getProperty(service);
     * setDestination(putil.getProperty("mq" + service)); if
     * (utils.isEmpty(baseUrlProperty)) { return; } String[] urlArray =
     * baseUrlProperty.split("\\|"); // try { // if (urlArray[0].indexOf("rw=")
     * != -1) { // setBaseUrl(urlArray[0], "rw", service); // } else { //
     * setBaseUrl(service + "_rw", baseUrlProperty); // } // if
     * (urlArray[1].indexOf("ro=") != -1) { // setBaseUrl(urlArray[1], "ro",
     * service); // } else { // setBaseUrl(service + "_r0", baseUrlProperty); //
     * } // } catch (Exception ex) { // setBaseUrl(service + "_rw",
     * baseUrlProperty); // setBaseUrl(service + "_r0", baseUrlProperty); // }
     * try { if (urlArray[2].indexOf("mq=") != -1) { setBaseUrl(urlArray[2],
     * "mq", service); } else { setBaseUrl(service + "_mq", "DLC.Q"); } if
     * (urlArray[3].indexOf("async=") != -1) { setBaseUrl(urlArray[3], "async",
     * service); } else { setBaseUrl(service + "_async", "false"); } } catch
     * (Exception ex) { setBaseUrl(service + "_mq", "DLC.Q"); setBaseUrl(service
     * + "_async", "false"); } }
     */
    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;

    }

    private void initializeMQConnection() {
        if (getMqConnection() == null) {
            try {
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                        "admin", "admin", putil.getProperty("mqurl"));
                log.info("admin-admin-" + putil.getProperty("mqurl"));
                setMqConnection(connectionFactory.createConnection());
                getMqConnection().start();
            } catch (JMSException e) {
                log.error("initializeMQConnection throws : " + e);
            }
        }
    }

    private boolean sendingAsyncMessage(String serviceName, String collection,
            Map<String, String> hRequestParam, String queryString)
            throws Exception {
        MessageProducer producer = null;
        Session session = null;
        try {

            String mqUrl = utils.isEmpty(putil.getProperty("mqurl")) ? ActiveMQConnection.DEFAULT_BROKER_URL
                    : putil.getProperty("mqurl");

            log.debug("mqUrl : " + mqUrl);

            if (queryString != null) {
                mqUrl += "?" + queryString;
            }
            log.debug("after appending query string:" + mqUrl);

            session = getMqConnection().createSession(true,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(baseUrl.get(
                    getServiceName() + "_mq").toString());
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            sendLoop(producer, session, collection, hRequestParam);
        } catch (Exception e) {
            log.info(e.getStackTrace().toString());
            return false;
        } finally {
            producer.close();
            session.close();
        }
        return true;
    }

    private boolean sendLoop(MessageProducer producer, Session session,
            String collection, Map<String, String> hRequestParam) {
        try {
            TextMessage txtMessage = session.createTextMessage(collection);
            for (Map.Entry<String, String> entry : hRequestParam.entrySet()) {
                txtMessage.setStringProperty(entry.getKey(), entry.getValue());
            }
            producer.send(txtMessage);
            session.commit();
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return false;
        }
    }

    public Map<String, Object> post(String serviceName, String domain,
            String collections, Map<String, String> usercontext,
            String queryString) {
        log.methodEntry("post entry");
        try {
            Map<String, Object> hResponse = new HashMap<String, Object>();
            initializeMQConnection();
            if (usercontext != null) {
                usercontext.put("domain", domain);
                usercontext.put("method", "post");
                usercontext.put("type", "xml");
            }
            if (sendingAsyncMessage(serviceName, collections, usercontext,
                    queryString)) {
                log.debug("Post request sent to MQ successfully.");
                hResponse.put("responseCode", 200);
            } else {
                hResponse.put("responseCode", 404);
            }
            log.methodExit("post exit");
            return hResponse;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":post(String,Collections,Map,String)",
                    "RequestParam:" + usercontext.toString(), e);
        }
    }

    public Map<String, Object> delete(String serviceName, String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        Map<String, String> hRequestParam = null;
        log.methodEntry("delete entry");
        try {
            hRequestParam = (Map<String, String>) parameters.get("input").get(
                    "userContext");
            String queryString = (String) parameters.get("input").get(
                    "queryString");
            log.debug("param:" + serviceName + domain + entity + id
                    + hRequestParam);
            String query = "";
            log.debug("SERVICE Name **: " + serviceName + ";" + domain + ";"
                    + entity + ";" + id + ";" + hRequestParam);
            query = entity + "/" + id;
            Map<String, Object> hResponse = new HashMap<String, Object>();
            initializeMQConnection();
            if (hRequestParam != null) {
                hRequestParam.put("domain", domain);
                hRequestParam.put("method", "Delete");
                hRequestParam.put("query", query);
            }
            if (sendingAsyncMessage(serviceName, "", hRequestParam, queryString)) { //
                log.methodEntry("Delete request sent to MQ successfully.");
                hResponse.put("responseCode", 200);
            } else {
                hResponse.put("responseCode", 404);
            }

            /*
             * if(hRequestParam!=null){ hRequestParam.put("domain", domain);
             * hRequestParam.put("method", "Delete"); hRequestParam.put("query",
             * query); } initializeMQConnection(); HashMap<String, Object>
             * hResponse = new HashMap<String, Object>(); if
             * (sendingAsyncMessage(serviceName,"", hRequestParam)) { //
             * log.methodEntry("Delete request sent to MQ successfully.");
             * hResponse.put("responseCode", 200); } else {
             * log.methodEntry("Delete request sent to MQ failed.");
             * hResponse.put("responseCode", 404); }
             */
            log.methodExit("delete exit");
            return hResponse;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":delete(String,String,Map)",
                    "RequestParam:" + hRequestParam, e);
        }
    }

    public Map<String, Object> delete(String serviceName, String domain,
            String query, Map<String, Map<String, Object>> parameters) {
        log.methodEntry("delete with query entry");
        Map<String, String> hRequestParam = null;
        try {
            hRequestParam = (Map<String, String>) parameters.get("input").get(
                    "userContext");
            String queryString = (String) parameters.get("input").get(
                    "queryString");
            log.debug("In delete :" + serviceName + ";domain:" + domain
                    + ";Query:" + query + "hRequestParam:" + hRequestParam);
            Map<String, Object> hResponse = new HashMap<String, Object>();
            if (baseUrl.get(getServiceName() + "_asyncdelete").toString()
                    .equalsIgnoreCase("true")) {
                initializeMQConnection();
                if (hRequestParam != null) {
                    hRequestParam.put("domain", domain);
                    hRequestParam.put("method", "Delete");
                    hRequestParam.put("query", query);
                }

                if (sendingAsyncMessage(serviceName, "", hRequestParam,
                        queryString)) { //
                    log.methodEntry("Delete request sent to MQ successfully.");
                    hResponse.put("responseCode", 200);
                } else {
                    hResponse.put("responseCode", 404);
                }
            }
            /*
             * if(hRequestParam!=null){ hRequestParam.put("domain", domain);
             * hRequestParam.put("method", "Delete"); hRequestParam.put("query",
             * Query); } initializeMQConnection(); HashMap<String, Object>
             * hResponse = new HashMap<String, Object>(); if
             * (sendingAsyncMessage(serviceName, "", hRequestParam)) {
             * log.debug("Delete request sent to MQ successfully.");
             * hResponse.put("responseCode", 200); } else {
             * log.debug("Delete request sent to MQ failed.");
             * hResponse.put("responseCode", 404);s }
             */
            log.methodExit("delete with query exit");
            return hResponse;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":delete(String,String,Map)",
                    "RequestParam:" + hRequestParam, e);
        }
    }

    public Map<String, Map<String, Object>> getBinaryContent(String domainName,
            String entityType, Map<String, Map<String, Object>> parameters) {
        Map<String, Object> inputMap = (Map<String, Object>) parameters
                .get("input");
        Map<String, String> userContext = (HashMap<String, String>) inputMap
                .get("userContext");
        String collections = (String) inputMap.get("content");
        Map<String, Object> contentMap = new HashMap<String, Object>();
        if (collections != null) {
            HashMap<String, Object> contentMapForCollection = FormatXml
                    .getInstanceof(domainName).in(collections);
            log.debug("before picking:" + contentMapForCollection);
            contentMap = (Map<String, Object>) contentMapForCollection
                    .get("Collections");
            log.debug("Incoming collections:" + contentMap);
        }
        log.methodEntry("In ServiceClientHttp entry");
        if (inputMap.containsKey("files")) {
            log.debug("inside binary post logic.");
            List<Map<String, Object>> binaryParamsList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> fileMap = (List<Map<String, Object>>) inputMap
                    .get("files");
            if (fileMap != null && fileMap.size() > 0) {
                log.debug("inside filemap sixe > 0 " + fileMap.size());
                for (Map<String, Object> map : fileMap) {
                    for (String key : map.keySet()) {
                        log.debug("inside for");

                        try {
                            System.out.println(map.get(key));
                            byte[] filedata = (byte[]) map.get(key);
                            log.debug("length:" + filedata.length);
                            log.debug("File info:" + key + ";byte:" + filedata);
                            HashMap<String, Object> fileInfo = new HashMap<String, Object>();
                            fileInfo.put("id", key);
                            // log.debug("before encoding:"+filedata);
                            String encodeStr = new sun.misc.BASE64Encoder()
                                    .encode(filedata);
                            // log.debug("before setting content:"+encodeStr);
                            fileInfo.put("data", encodeStr);
                            binaryParamsList.add(fileInfo);

                        } catch (Exception e) {
                            log.error("Exception:" + e.getMessage());
                        }
                    }
                }
                contentMap.put("files", binaryParamsList);
            }
        }

        Map<String, Object> original = new HashMap<String, Object>();
        original.put("Collections", contentMap);
        log.debug("binaryxml hashmap:" + original);
        String binaryXml = (String) FormatXml.getInstanceof(domainName).out(
                (HashMap<String, Object>) original);

        // put that check here

        String queryString = (String) parameters.get("input")
                .get("queryString");
        Map<String, Object> responseMap = null;

        log.debug("!=null:" + queryString);
        responseMap = post(getServiceName(), domainName, binaryXml,
                userContext, queryString);
        return getOutput(responseMap, parameters);

    }

    @Override
    public Map<String, Map<String, Object>> add(String domainName,
            String entityType, Map<String, Map<String, Object>> parameters) {
        log.methodEntry("add entry:" + parameters);
        // assignDomain(domainName);
        return getBinaryContent(domainName, entityType, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domainName,
            String entityType, String id,
            Map<String, Map<String, Object>> parameters) {
        log.methodEntry("deleteByID entry:" + parameters);
        // assignDomain(domainName);
        Map<String, Object> responseMap = delete(getServiceName(), domainName,
                entityType, id, parameters);
        return getOutput(responseMap, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domainName,
            String query, Map<String, Map<String, Object>> parameters) {

        log.methodEntry("deleteByQuery entry:" + parameters);
        // assignDomain(domainName);
        Map<String, String> usercontext = (Map<String, String>) parameters.get(
                "input").get("userContext");
        Map<String, Object> responseMap = delete(getServiceName(), domainName,
                query, parameters);
        return getOutput(responseMap, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> find(String domainName,
            String entityType, Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domainName, "ERR-SC-0008");
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domainName,
            String entityType, String parameters,
            Map<String, Map<String, Object>> arg3) {
        throw new GenericException(domainName, "ERR-SC-0008");
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        log.methodEntry("read entry:" + parameters);
        // assignDomain(domain);
        Map<String, Object> responseMap = new HashMap<String, Object>();
        Map<String, Object> inputMap = (Map<String, Object>) parameters
                .get("input");
        String collections = (String) inputMap.get("content");
        Map<String, String> usercontext = (Map<String, String>) inputMap
                .get("userContext");
        String queryString = (String) inputMap.get("queryString");

        // String contentType = (String) parameters.get("input").get("format");
        responseMap = post(getServiceName(), domain, collections, usercontext,
                queryString);
        return getOutput(responseMap, parameters);
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

    private Map<String, Map<String, Object>> getOutput(
            Map<String, Object> responseMap,
            Map<String, Map<String, Object>> parameters) {
        try {
            Map<String, Object> outputMap = new HashMap<String, Object>();
            outputMap.put("content", responseMap.get("responseStringBody"));
            outputMap.put("statusCode", responseMap.get("responseCode"));
            parameters.put("output", outputMap);
            log.debug("Output:" + parameters);
        } catch (Exception ex) {
            log.error("In EX getOutput:" + ex.getMessage());
        }
        return parameters;
    }
}
