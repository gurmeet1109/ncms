package in.nic.cmf.serviceclient.providers;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientProtocol;
import in.nic.cmf.serviceclient.helper.SendHttpRequest;
import in.nic.cmf.serviceclient.helper.ServiceClientHelper;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;

public class HttpProvider implements ServiceClientProtocol, CMFService {

    private ServiceClientHelper scHelper;
    private LogTracer           log;
    private PropertiesUtil      putil;
    private SendHttpRequest     httpReq;
    private Map<String, String> baseUrl        = new HashMap<String, String>();
    private static HttpProvider scHttpProtocol = null;
    private String              serviceName    = null;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    private HttpProvider(String domain, String service) {
        try {
            scHelper = ServiceClientHelper.getInstanceof(domain);
            log = new LogTracer(domain, "serviceclient");
            putil = PropertiesUtil.getInstanceof(domain, "serviceclient");
            httpReq = SendHttpRequest.getInstanceof(domain);
            setServiceName(service);
            setBaseUrlFromProperty(service);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public static ServiceClientProtocol getInstanceOf(String domain,
            String serviceName) {
        scHttpProtocol = new HttpProvider(domain, serviceName);
        return scHttpProtocol;
    }

    @Override
    public ServiceClientProtocol getInstance(String domain, String serviceName) {
        if (scHttpProtocol == null) {
            scHttpProtocol = new HttpProvider(domain, serviceName);
        }
        return scHttpProtocol;
    }

    private void setBaseUrl(String urlValue, String strIndex, String service) {
        if (urlValue.indexOf(strIndex + "=") != -1) {
            baseUrl.put(service + "_" + strIndex,
                    urlValue.replace(strIndex + "=", ""));
        }
    }

    private void setBaseUrl(String key, String value) {
        baseUrl.put(key, value);
    }

    public void setBaseUrlFromProperty(String service) {
        String baseUrlProperty = putil.getProperty(service);
        if (scHelper.isEmpty(baseUrlProperty)) {
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
                setBaseUrl(service + "_ro", baseUrlProperty);
            }
        } catch (Exception ex) {
            setBaseUrl(service + "_rw", baseUrlProperty);
            setBaseUrl(service + "_ro", baseUrlProperty);
        }
    }

    public Map<String, Object> get(String serviceName, String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {

        String partUrl = (String) parameters.get("input").get("queryString");

        Map<String, String> headers = new HashMap<String, String>();
        headers = (Map<String, String>) parameters.get("input").get("headers");
        String requestId = headers.get("requestId");

        Map<String, String> userContext = new HashMap<String, String>();
        userContext = (Map<String, String>) parameters.get("input").get(
                "userContext");
        log.debug("Inside get SC:" + parameters);
        if (partUrl == null) {
            partUrl = "q=";
        }
        try {
            String url = "";
            url = baseUrl.get(serviceName + "_ro") + "/" + domain;
            if (!scHelper.isEmpty(entityType)) {
                url += "/" + entityType;
            } else {
                url += "/search";
            }

            if (partUrl.indexOf("q=") == -1) {
                url = url + "?q=&" + partUrl;
            } else {
                url = url + "?" + partUrl;
            }

            log.debug("URL:::::::::" + url);
            return httpReq.get(serviceName, baseUrl, url, userContext,
                    requestId);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":get(String,String,Map,String)",
                    "partUrl:" + partUrl + "RequestParam:"
                            + userContext.toString(), e);
        }
    }

    public Map<String, Object> getByFileName(String domain, String serviceName,
            String domainName, String entityType, String partUrl,
            Map<String, String> hRequestParam, String requestId) {
        try {

            String url = "";
            url = baseUrl.get(serviceName + "_ro") + "/" + domainName;
            if (!scHelper.isEmpty(entityType)) {
                url += "/" + entityType;
            }
            if (!scHelper.isEmpty(partUrl)) {
                url += "/" + partUrl;
            }
            log.debug("getByFileName() url : " + url);
            return httpReq.get(serviceName, baseUrl, url, hRequestParam,
                    requestId);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":get(String,Map,String)", "partUrl:"
                    + partUrl + "RequestParam:" + hRequestParam.toString(), e);
        }
    }

    public Map<String, Object> postXml(String serviceName, String domain,
            String entity, String collections, Map<String, String> usercontext,
            String contentType, String requestId) {
        try {
            log.debug("inside postXml");
            usercontext.put("domain", domain);
            String url = baseUrl.get(serviceName + "_rw") + "/" + domain;

            log.debug("postXml:serviceclient url ==> " + url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(scHelper.getStreamEntity(collections,
                    contentType));
            httpPost.addHeader("Content-Type", "application/xml");
            log.debug("http set entity and header");
            return httpReq.post(httpPost, usercontext, contentType, requestId);
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

    public Map<String, Object> postXml(String serviceName, String domain,
            String entity, String collections, Map<String, String> usercontext,
            String contentType, String queryString, String requestId) {
        try {
            log.debug("inside postXml");
            usercontext.put("domain", domain);
            String url = baseUrl.get(serviceName + "_rw") + "/" + domain + "?"
                    + queryString;

            log.debug("postXml:serviceclient url ==> " + url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(scHelper.getStreamEntity(collections,
                    contentType));
            httpPost.addHeader("Content-Type", "application/xml");
            log.debug("http set entity and header");
            return httpReq.post(httpPost, usercontext, contentType, requestId);
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
        try {
            Map<String, String> headers = new HashMap<String, String>();
            headers = (Map<String, String>) parameters.get("input").get(
                    "headers");
            String requestId = headers.get("requestId");
            Map<String, String> usercontext = (Map<String, String>) parameters
                    .get("input").get("userContext");

            String query = entity + "/" + id;

            log.debug("in delete:" + parameters);
            String queryString = (String) parameters.get("input").get(
                    "queryString");
            log.debug("queryString:" + queryString);
            if (queryString != null) {
                log.debug("queryString not equal to null" + queryString);
                query += "?q=&" + queryString;
                log.debug("!=null:" + query);
            }

            log.debug("SERVICE Name **: " + serviceName);
            usercontext.put("domain", domain);
            log.debug("after appending entitytype with id :" + query);
            String url = baseUrl.get(serviceName + "_rw") + "/" + domain + "/"
                    + query;

            log.debug("serviceclient url ==> " + url);
            HttpDelete httpdelete = new HttpDelete(new URI(url));

            return httpReq.delete(httpdelete, usercontext, requestId);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":delete(String,String,Map)", e);
        }
    }

    public Map<String, Object> delete(String serviceName, String domain,
            String Query, Map<String, String> hRequestParam, String requestId) {
        try {
            log.debug("SERVICE Name @@: " + serviceName);
            hRequestParam.put("domain", domain);
            String url = baseUrl.get(serviceName + "_rw") + "/" + domain + "/"
                    + Query;
            log.debug("serviceclient url : " + url);
            HttpDelete httpdelete = new HttpDelete(new URI(url));
            return httpReq.delete(httpdelete, hRequestParam, requestId);
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

    // override methods

    @Override
    public Map<String, Map<String, Object>> add(String domainName,
            String entityType, Map<String, Map<String, Object>> parameters) {
        log.debug("Service Client Add() ywahoooooooooooooooooooooooooooooo:  domainName "
                + domainName
                + " entityType : "
                + entityType
                + " Parameters : "
                + parameters);
        Map<String, String> headers = new HashMap<String, String>();
        headers = (Map<String, String>) parameters.get("input").get("headers");
        String requestId = headers.get("requestId");
        Map<String, Object> inputMap = (Map<String, Object>) parameters
                .get("input");
        Map<String, Object> outputMap = new HashMap<String, Object>();
        Map<String, Object> responseMap = new HashMap<String, Object>();
        Map<String, String> usercontext = (Map<String, String>) inputMap
                .get("userContext");
        String contentType = (String) inputMap.get("format");

        log.debug("CLASSNAME of content:"
                + inputMap.get("content").getClass().getSimpleName());

        Map<String, Object> contentMap = new HashMap<String, Object>();
        Map<String, Object> globalCollectedMap = new HashMap<String, Object>();

        if (inputMap.get("content") instanceof String) {
            log.debug("instance of string");
            String collections = (String) inputMap.get("content");
            if (collections != null) {
                HashMap<String, Object> contentMapForCollection = FormatXml
                        .getInstanceof(domainName).in(collections);
                log.debug("before picking:" + contentMapForCollection);
                contentMap = (Map<String, Object>) contentMapForCollection
                        .get("Collections");
                log.debug("Incoming collections:" + contentMap);
            }

        } else if (inputMap.get("content") instanceof HashMap) {
            log.debug("instance of hashmap");
            contentMap = (Map<String, Object>) ((Map<String, Object>) inputMap
                    .get("content")).get("Collections");
            log.debug("instance of hashmap:" + contentMap);
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

                    if (map.get("Data") instanceof String) {
                        String original = (String) map.get("Data");
                        try {
                            log.debug("instance of string in add");
                            byte[] decodedStr = new sun.misc.BASE64Decoder()
                                    .decodeBuffer(original);
                            String encodeStr = new sun.misc.BASE64Encoder()
                                    .encode(decodedStr);
                            map.put("Data", encodeStr);
                        } catch (IOException e) {
                            log.debug("in e:" + e.getMessage());
                            e.printStackTrace();
                        }
                    } else if (map.get("Data") instanceof byte[]) {

                        log.debug("instance of byte array.");
                        byte[] filedata = (byte[]) map.get("Data");
                        String encodeStr = new sun.misc.BASE64Encoder()
                                .encode(filedata);
                        map.put("Data", encodeStr);
                    }
                    binaryParamsList.add(map);
                    contentMap.put("files", binaryParamsList);
                }
            }
        }

        Map<String, Object> original = new HashMap<String, Object>();
        original.put("Collections", contentMap);
        log.debug("binaryxml hashmap:" + original);
        String binaryXml = (String) FormatXml.getInstanceof(domainName).out(
                (HashMap<String, Object>) original);

        String queryString = (String) parameters.get("input")
                .get("queryString");
        log.debug("queryString:" + queryString);
        if (queryString != null) {
            log.debug("!=null:" + queryString);
            responseMap = postXml(getServiceName(), domainName, entityType,
                    binaryXml, usercontext, contentType, queryString, requestId);
        } else {
            log.debug("else:" + queryString);
            responseMap = postXml(getServiceName(), domainName, entityType,
                    binaryXml, usercontext, contentType, requestId);
        }

        log.debug("After postXml.");
        outputMap.put("content", responseMap.get("responseStringBody"));
        log.debug("response:" + responseMap);

        outputMap.put("statusCode", responseMap.get("responseCode"));
        parameters.put("output", outputMap);
        log.methodExit("In ServiceClientHttp exit");
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> find(String domainName,
            String entityType, Map<String, Map<String, Object>> parameters) {
        Map<String, Object> responseMap = get(getServiceName(), domainName,
                entityType, parameters);
        return getOutput(responseMap, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domainName,
            String entityType, String id,
            Map<String, Map<String, Object>> parameters) {

        Map<String, String> headers = new HashMap<String, String>();
        headers = (Map<String, String>) parameters.get("input").get("headers");
        String requestId = headers.get("requestId");
        Map<String, String> userContext = (Map<String, String>) parameters.get(
                "input").get("userContext");
        Map<String, Object> outputMap = new HashMap<String, Object>();

        Map<String, Object> responseMap = new HashMap<String, Object>();

        if (parameters.get("input").containsKey("Dataurl")) {
            responseMap = httpReq.get(
                    (String) parameters.get("input").get("Dataurl"),
                    userContext, requestId);
        } else {
            responseMap = getByFileName(domainName, getServiceName(),
                    domainName, entityType, id, userContext, requestId);
        }
        log.debug("response map in findbyid:" + responseMap);
        if (((String) responseMap.get("Content-Type"))
                .contains("application/xml")) {
            log.debug("in xml logic");
            outputMap.put("content", responseMap.get("responseStringBody"));
        } else {
            log.debug("in binary logic");
            if (responseMap.get("responseCode").toString().trim().equals("200")) {
                log.debug("success so it is building the response for bytes");
                outputMap.put("content",
                        (byte[]) responseMap.get("responseByteBody"));
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type",
                        (String) responseMap.get("Content-Type"));
                outputMap.put("headers", headerMap);
            }
        }
        outputMap.put("statusCode", responseMap.get("statusCode"));
        parameters.put("output", outputMap);
        log.debug("findbyid output:" + parameters);
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domainName,
            String entityType, String id,
            Map<String, Map<String, Object>> parameters) {

        Map<String, Object> responseMap = delete(getServiceName(), domainName,
                entityType, id, parameters);
        return getOutput(responseMap, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domainName,
            String query, Map<String, Map<String, Object>> parameters) {

        Map<String, String> headers = new HashMap<String, String>();
        headers = (Map<String, String>) parameters.get("input").get("headers");
        String requestId = headers.get("requestId");
        Map<String, String> usercontext = (Map<String, String>) parameters.get(
                "input").get("userContext");

        Map<String, Object> responseMap = delete(getServiceName(), domainName,
                query, usercontext, requestId);
        return getOutput(responseMap, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        log.debug("read entry." + parameters);
        Map<String, String> headers = new HashMap<String, String>();
        headers = (Map<String, String>) parameters.get("input").get("headers");
        String requestId = headers.get("requestId");
        Map<String, Object> inputMap = (Map<String, Object>) parameters
                .get("input");
        log.debug("before forming url." + getServiceName());
        String format = "xml";
        if (inputMap.containsKey("queryParams")) {
            Map<String, String> queryParam = (Map<String, String>) inputMap
                    .get("queryParams");
            String ent = "entitytype";
            if (queryParam.containsKey(ent)) {
                entityType = queryParam.get(ent);
            }
            format = queryParam.get("format");
            log.debug("format in read is:" + format);
        }

        String url = baseUrl.get(getServiceName() + "_rw") + "/" + domain + "/"
                + entityType + "/collections?format=" + format;
        log.debug("url:" + url);
        Map<String, Object> responseMap = httpReq.getAsPost(url, inputMap,
                requestId);
        log.debug("responseMap:" + responseMap);
        return getOutput(responseMap, parameters);
    }

    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
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
        } catch (Exception ex) {
            log.error("In EX getOutput:" + ex.getMessage());
        }
        return parameters;
    }

}
