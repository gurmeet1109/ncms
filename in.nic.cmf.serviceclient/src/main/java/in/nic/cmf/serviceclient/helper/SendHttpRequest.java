package in.nic.cmf.serviceclient.helper;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.util.Utils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

public class SendHttpRequest {

    // private static SendHttpRequest sendHttpRequest = new SendHttpRequest();
    // private HttpHelper httpHelper = new HttpHelper();
    // private LogTracer log = new LogTracer(
    // "serviceclient");
    // private Utils utils = Utils.getInstance();
    private HttpHelper                              httpHelper;
    private LogTracer                               log;
    private Utils                                   utils;

    private static HashMap<String, SendHttpRequest> hashSendHttpRequest = new HashMap<String, SendHttpRequest>();
    private String                                  domain;

    // public static SendHttpRequest getInstance() {
    // return sendHttpRequest;
    // }
    private SendHttpRequest(String domain) {
        this.domain = domain;
        log = new LogTracer(domain, "serviceclient");
        utils = Utils.getInstanceof(domain);
        httpHelper = new HttpHelper(domain);
    }

    public static SendHttpRequest getInstanceof(String domain) {
        if (!hashSendHttpRequest.containsKey(domain)) {
            hashSendHttpRequest.put(domain, new SendHttpRequest(domain));
        }
        return hashSendHttpRequest.get(domain);
    }

    public HttpUriRequest createHeaders(HttpUriRequest httprequest,
            Map<String, String> hRequestParam, String requestId) {

        if (!utils.isEmpty(hRequestParam.get("requester"))) {
            httprequest.setHeader("x-forwarded-for",
                    hRequestParam.get("requester"));
        }
        httprequest.addHeader("Cookie",
                "authusername=" + hRequestParam.get("authusername"));
        httprequest.addHeader("Cookie",
                "api_key=" + hRequestParam.get("api_key"));
        httprequest.addHeader("Cookie",
                "aclrole=" + hRequestParam.get("aclrole"));
        httprequest.addHeader("requestId", requestId);

        log.debug("in create Headers : " + httprequest.getHeaders("Cookie"));
        httprequest.addHeader("Cookie",
                "auth_token=" + hRequestParam.get("auth_token"));
        return httprequest;
    }

    public StringEntity getStringEntity(HttpPost httppost, String collections,
            String contentType) throws GenericException {
        try {
            StringEntity reqEntity = new StringEntity(collections, "utf-8");
            if (contentType != null) {
                reqEntity.setContentType(contentType + ";charset=utf-8");
            } else {
                log.error("Content type null");
                throw new GenericException(domain, "Content type null");
            }
            return reqEntity;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":getStringEntity()",
                    httppost.toString(), e);
        }
    }

    public Map<String, Object> get(String serviceName,
            Map<String, String> baseUrl, String url,
            Map<String, String> hRequestParam, String requestId) {

        if (!url.startsWith("http:")) {
            url = baseUrl.get(serviceName + "_ro") + "/" + url;
        }
        return get(url, hRequestParam, requestId);
    }

    public Map<String, Object> get(String url,
            Map<String, String> hRequestParam, String requestId) {
        try {
            HttpGet httpget = new HttpGet();
            httpget.setURI(new URI(url));
            httpget = (HttpGet) createHeaders(httpget, hRequestParam, requestId);
            Map<String, Object> response = httpHelper.execute(httpget);
            log.debug("after execute:" + response);
            if ((Integer) response.get("statusCode") != HttpStatus.SC_OK) {
                Map<String, Object> resultMap = utils
                        .parsingErrorResponse((String) response
                                .get("responseStringBody"));
                throw new GenericException(domain, resultMap);
            }
            return response;
        } catch (GenericException e) {
            log.error("inside ge:" + e.getMessage());
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0006", this.getClass()
                    .getSimpleName() + ":get()", hRequestParam.toString(), e);
        }
    }

    public Map<String, Object> post(HttpPost httpPost,
            Map<String, String> hRequestParam, String contentType,
            String requestId) {
        log.methodEntry("inner post entry:" + hRequestParam + "-" + contentType);
        try {
            httpPost = (HttpPost) createHeaders(httpPost, hRequestParam,
                    requestId);
            log.debug("after createheaders");
            return invokeHttpPost(httpPost);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":post(HttpPost,Map)",
                    hRequestParam.toString(), e);
        }
    }

    public Map<String, Object> invokeHttpPost(HttpPost httpPost) {
        Map<String, Object> response = httpHelper.execute(httpPost);
        log.debug("after executes:" + response);
        if ((Integer) response.get("statusCode") != HttpStatus.SC_OK) {
            log.debug("statuscode is not success:" + response.get("statusCode"));
            int code = (Integer) response.get("statusCode");
            // should not go inside this below if.
            if (code != 555) {
                log.debug("not 555");
                Map<String, Object> resultMap = utils
                        .parsingErrorResponse((String) response
                                .get("responseStringBody"));
                System.out.println(resultMap);
                throw new GenericException(domain, resultMap);
            }
        }
        log.methodExit("inner post exit");
        return response;
    }

    public Map<String, Object> getAsPost(String url,
            Map<String, Object> inputMap, String requestId) {
        log.methodEntry("postCollections entry.");
        try {

            Map<String, String> usercontext = (Map<String, String>) inputMap
                    .get("userContext");
            String contentType = (String) inputMap.get("format");
            String collections = (String) inputMap.get("content");
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(getStringEntity(httpPost, collections,
                    contentType));
            log.methodExit("postCollections going to exit.");
            return post(httpPost, usercontext, contentType, requestId);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public Map<String, Object> delete(HttpDelete httpdelete,
            Map<String, String> hRequestParam, String requestId) {
        try {
            httpdelete = (HttpDelete) createHeaders(httpdelete, hRequestParam,
                    requestId);
            Map<String, Object> response = httpHelper.execute(httpdelete);
            if ((Integer) response.get("statusCode") != HttpStatus.SC_OK) {
                Map<String, Object> resultMap = utils
                        .parsingErrorResponse((String) response
                                .get("responseStringBody"));
            }
            response.put("statusCode", (Integer) response.get("status"));
            return response;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":delete(httpdelete,Map)",
                    hRequestParam.toString(), e);
        }
    }

}
