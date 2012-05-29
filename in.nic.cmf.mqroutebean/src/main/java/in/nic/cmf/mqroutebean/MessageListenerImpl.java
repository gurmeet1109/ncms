package in.nic.cmf.mqroutebean;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.helper.SendHttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Header;
import org.apache.camel.Headers;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class MessageListenerImpl {

    private LogTracer      logger;
    private PropertiesUtil putil;

    /**
     * Initialize.
     */
    private void initialize(String domain) {
        System.out.println("Calling initialize Method::::");;
        putil = PropertiesUtil.getInstanceof(domain, "mqroutebean");
        logger = new LogTracer(domain, "mqroutebeanlog");
        System.out.println("putil::::" + putil);;
        System.out.println("logger::::" + logger);;
    }

    /**
     * On message.
     * @param messageObj
     *            the message obj
     * @param domain
     *            the domain
     * @param jmsDesit
     *            the jms desit
     * @param allData
     *            the all data
     * @param authusername
     *            the authusername
     * @param api_key
     *            the api_key
     * @param requester
     *            the requester
     * @param method
     *            the method
     * @param query
     *            the query
     * @param aclrole
     *            the aclrole
     * @throws Exception
     *             the exception
     */
    public void onMessage(String messageObj,
            @Header ("JMSDestination") String jmsDesit,
            @Headers final HashMap<String, String> allData)
            throws GenericException {

        System.out.println("All Data MAp:::" + allData);
        System.out.println("ACL DETAILS: aclrole: " + allData.get("aclrole")
                + " authusername: " + allData.get("authusername")
                + " api_key: " + allData.get("api_key") + " requester:"
                + allData.get("requester"));
        // putil = PropertiesUtil.getInstanceof(allData.get("domaim"),
        // "mqroutebean");
        // logger = new LogTracer("sify.co.in", "mqroutebeanlog");
        initialize(allData.get("domain"));
        logger.info("Entering into onMessage method");
        boolean retry = true;
        logger.info("ACL DETAILS: aclrole: " + allData.get("aclrole")
                + " authusername: " + allData.get("authusername")
                + " api_key: " + allData.get("api_key") + " requester:"
                + allData.get("requester"));
        logger.info("All Data: " + allData);
        logger.info("allData.type" + allData.get("type"));
        String urlValue = "";

        jmsDesit = jmsDesit.replace("queue://", "");
        if (jmsDesit.indexOf(".") > 0) {
            jmsDesit = jmsDesit.substring(0, jmsDesit.indexOf("."));
        }
        urlValue = putil.getProperty(jmsDesit);
        logger.info("Desit: " + jmsDesit.toString());
        urlValue += allData.get("domain");
        logger.info(" \n TO Url :" + urlValue);// + "message: " + messageObj);

        HttpClient httpclient = new DefaultHttpClient();
        String contentType = "application/xml";

        HttpResponse response = null;
        int counter = 1;
        String method = allData.get("method").toLowerCase();
        SendHttpRequest sc = SendHttpRequest.getInstanceof(allData
                .get("domain"));
        // ServiceClient sc = ServiceClient.getInstance("mqroutebean");
        HttpDelete httpmethodDelete = null;
        HttpPost httpmethodPost = null;
        if (method.toLowerCase().equals("delete")) {
            urlValue = urlValue + "/" + allData.get("query");
            logger.info("urlValue -Delete : " + urlValue);
            httpmethodDelete = new HttpDelete(urlValue);
            Map<String, String> hRequestParam = new HashMap<String, String>();
            hRequestParam.put("x-forwarded-for", allData.get("requester"));
            hRequestParam
                    .put("authusername", hRequestParam.get("authusername"));
            hRequestParam.put("api_key", hRequestParam.get("api_key"));
            hRequestParam.put("aclrole", hRequestParam.get("aclrole"));
            Map responseMap = new HashMap();
            responseMap = sc.delete(httpmethodDelete, hRequestParam, null);
            logger.info(responseMap.toString());
            if (!responseMap.get("responseCode").toString().equals("200")) {
                logger.error("Exception: DELETE - Code: "
                        + responseMap.get("responseCode").toString() + " - ");
                throw new GenericException(allData.get("domain"),
                        "ERR-SC-0000", this.getClass().getSimpleName()
                                + ":get(String,String,Map,String)",
                        "RequestParam:" + hRequestParam.toString());

            }
        } else if (method.toLowerCase().equals("post")) {
            logger.info("POST Entry");
            httpmethodPost = new HttpPost(urlValue);
            Map<String, String> hRequestParam = new HashMap<String, String>();
            hRequestParam.put("x-forwarded-for", allData.get("requester"));
            hRequestParam
                    .put("authusername", hRequestParam.get("authusername"));
            hRequestParam.put("api_key", hRequestParam.get("api_key"));
            hRequestParam.put("aclrole", hRequestParam.get("aclrole"));
            Map responseMap = new HashMap();

            if (allData.get("type").equalsIgnoreCase("bin")) {
                try {
                    byte[] decodedStr = new sun.misc.BASE64Decoder()
                            .decodeBuffer(messageObj);
                    FileItem item = createFileItem(decodedStr);
                    InputStream inputstream = (InputStream) item
                            .getInputStream();
                    MultipartEntity reqEntitys = new MultipartEntity(
                            HttpMultipartMode.BROWSER_COMPATIBLE);
                    reqEntitys.addPart(allData.get("fileName"),
                            new InputStreamKnownSizeBody(inputstream,
                                    decodedStr.length, allData.get("mimeType"),
                                    allData.get("fileName")));
                    httpmethodPost.setEntity(reqEntitys);
                    responseMap = sc.post(httpmethodPost, hRequestParam,
                            contentType, null);
                } catch (IOException e1) {
                    logger.info("CATCHING - BIN " + e1);
                    throw new GenericException(allData.get("domain"),
                            "ERR-MQR-0001", this.getClass().getSimpleName(),
                            "CATCHING - BIN :" + e1.getMessage());
                }

            } else if (allData.get("type").equalsIgnoreCase("xml")) {
                try {
                    StringEntity reqEntity = new StringEntity(messageObj,
                            "utf-8");
                    if (contentType != null) {
                        reqEntity
                                .setContentType(contentType + ";charset=utf-8");
                    }
                    httpmethodPost.setEntity(reqEntity);
                    responseMap = sc.post(httpmethodPost, hRequestParam,
                            contentType, null);
                    logger.info(responseMap.toString());
                } catch (UnsupportedEncodingException e1) {
                    logger.info("CATCHING - XML " + e1);
                    throw new GenericException(allData.get("domain"),
                            "ERR-MQR-0001", this.getClass().getSimpleName(),
                            "CATCHING - XML :" + e1.getMessage());
                }
            }
            if (!responseMap.get("responseCode").toString().equals("200")) {
                logger.error("Exception: POST - Code: "
                        + responseMap.get("responseCode").toString() + " - ");
                throw new GenericException(allData.get("domain"),
                        "ERR-SC-0000", this.getClass().getSimpleName()
                                + ":get(String,String,Map,String)",
                        "RequestParam:" + hRequestParam.toString());

            }
        } else {
            throw new GenericException(allData.get("domain"), "ERR-MQR-0001",
                    this.getClass().getSimpleName(),
                    "HTTP Verb Does not Match:");
        }

    }

    private class InputStreamKnownSizeBody extends InputStreamBody {
        private int size;

        public InputStreamKnownSizeBody(final InputStream in, final int size,
                                        final String mimeType,
                                        final String filename) {
            super(in, mimeType, filename);
            this.size = size;
        }

        @Override
        public long getContentLength() {
            return this.size;
        }
    }

    private FileItem createFileItem(byte[] contentBytes)
            throws GenericException, IOException {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "files";

        FileItem item = factory.createItem(textFieldName, "image/jpeg", true,
                "images.jpeg");
        OutputStream os = (OutputStream) item.getOutputStream();
        os.write(contentBytes);
        os.close();

        return item;
    }

    private static String appendUrl(String url, String value) {
        return null;
    }

}

/*
 * import java.io.UnsupportedEncodingException;
 * import java.util.HashMap;
 * import java.util.Map;
 * import in.nic.cmf.exceptions.GenericException;
 * import in.nic.cmf.logger.LogTracer;
 * import in.nic.cmf.properties.PropertiesUtil;
 * import in.nic.cmf.serviceclient.helper.SendHttpRequest;
 * import in.nic.cmf.util.Utils;
 * import org.apache.camel.Header;
 * import org.apache.camel.Headers;
 * import org.apache.http.HttpResponse;
 * import org.apache.http.HttpStatus;
 * import org.apache.http.client.HttpClient;
 * import org.apache.http.client.methods.HttpDelete;
 * import org.apache.http.client.methods.HttpPost;
 * import org.apache.http.entity.StringEntity;
 * import org.apache.http.impl.client.DefaultHttpClient;
 * public class MessageListenerImpl {
 * LogTracer logger = new LogTracer("mqroutebeanlog");
 * private static PropertiesUtil putil = null;
 * public static void initialize() {
 * putil = new PropertiesUtil("mqroutebean");
 * }
 * public void onMessage(String messageObj,
 * @Header ("domain") final String domain,
 * @Header ("JMSDestination") final String jmsDesit,
 * @Headers final String allData,
 * @Header ("authusername") final String authusername,
 * @Header ("api_key") final String api_key,
 * @Header ("requester") final String requester,
 * @Header ("method") String method,
 * @Header ("query") final String query,
 * @Header ("aclrole") final String aclrole) throws GenericException {
 * logger.info("Entering into onMessage method");
 * initialize();
 * boolean retry = true;
 * logger.info("ACL DETAILS: aclrole: " + aclrole + " authusername: "
 * + authusername + " api_key: " + api_key + " requester:"
 * + requester);
 * logger.info("All Data: " + allData);
 * String urlValue = "";
 * if (jmsDesit.toString().contains("dataq")) {
 * urlValue = putil.getProperty("dataq");
 * } else if (jmsDesit.toString().contains("dmsqmaster")) {
 * urlValue = putil.getProperty("dmsqmaster");
 * } else if (jmsDesit.toString().contains("dmsqslave")) {
 * urlValue = putil.getProperty("dmsqslave");
 * } else if (jmsDesit.toString().contains("searchapiqmaster")) {
 * urlValue = putil.getProperty("searchapiqmaster");
 * } else if (jmsDesit.toString().contains("searchapiqslave")) {
 * urlValue = putil.getProperty("searchapiqslave");
 * } else if (jmsDesit.toString().contains("sitebuilderq")) {
 * urlValue = putil.getProperty("sitebuilderq");
 * } else if (jmsDesit.toString().contains("semq")) {
 * urlValue = putil.getProperty("semq");
 * } else if (jmsDesit.toString().contains("generateqmaster")) {
 * urlValue = putil.getProperty("generateqmaster");
 * } else if (jmsDesit.toString().contains("generateqslave")) {
 * urlValue = putil.getProperty("generateqslave");
 * } else if (jmsDesit.toString().contains("emailq")) {
 * urlValue = putil.getProperty("emailq");
 * } else if (jmsDesit.toString().contains("aclq")) {
 * urlValue = putil.getProperty("aclq");
 * logger.info("ACL Q taken"+putil.getProperty("aclq")+"Url : "+urlValue);
 * } else if (jmsDesit.toString().contains("tranq")) {
 * urlValue = putil.getProperty("tranq");
 * } else if (jmsDesit.toString().contains("req")) {
 * urlValue = putil.getProperty("req");
 * }else if (jmsDesit.toString().contains("bfq")) {
 * urlValue = putil.getProperty("bfq");
 * }else if (jmsDesit.toString().contains("mediaq")) {
 * urlValue = putil.getProperty("mediaq");
 * }
 * // urlValue = putil.getProperty(jmsDesit.toString().substring(9,
 * // jmsDesit.toString().length()));
 * logger.info("Desit: " + jmsDesit.toString());
 * urlValue += domain;
 * logger.info(" \n TO Url :" + urlValue + "message: " + messageObj);
 * HttpClient httpclient = new DefaultHttpClient();
 * String contentType = "application/xml";
 * StringEntity reqEntity = null;
 * try {
 * reqEntity = new StringEntity(messageObj, "utf-8");
 * } catch (UnsupportedEncodingException e1) {
 * e1.printStackTrace();
 * }
 * if (contentType != null) {
 * reqEntity.setContentType(contentType + ";charset=utf-8");
 * }
 * HttpResponse response = null;
 * int counter = 1;
 * method = method.toLowerCase();
 * SendHttpRequest sc = new SendHttpRequest();
 * HttpDelete httpmethodDelete = null;
 * HttpPost httpmethodPost = null;
 * if (method.equals("delete")) {
 * urlValue = urlValue + "/" + query;
 * httpmethodDelete = new HttpDelete(urlValue);
 * Map<String, String> hRequestParam = new HashMap<String, String>();
 * hRequestParam.put("x-forwarded-for", requester);
 * hRequestParam
 * .put("authusername", hRequestParam.get("authusername"));
 * hRequestParam.put("api_key", hRequestParam.get("api_key"));
 * hRequestParam.put("aclrole", hRequestParam.get("aclrole"));
 * Map responseMap = new HashMap();
 * responseMap = sc.delete(httpmethodDelete, hRequestParam);
 * logger.info(responseMap.toString());
 * if (!responseMap.get("responseCode").toString().equals("200")) {
 * logger.error("Exception: DELETE - Code: "
 * + responseMap.get("responseCode").toString() + " - ");
 * throw new GenericException("ERR-SC-0000", this.getClass()
 * .getSimpleName() + ":get(String,String,Map,String)",
 * "RequestParam:" + hRequestParam.toString());
 * // if (jmsDesit.toString().contentEquals("DLQC")) {
 * //
 * // } else {
 * // logger.error("Exception: DELETE (else):");
 * // throw new GenericException("ERR-SC-0000",
 * // this.getClass().getSimpleName()
 * // + ":get(String,String,Map,String)",
 * // "RequestParam:" + hRequestParam.toString());
 * // }
 * //
 * // try {
 * // while (counter < Integer.getInteger(putil
 * // .getProperty("noofretry"))) {
 * // try {
 * // responseMap = sc.delete(httpmethodDelete,
 * // hRequestParam);
 * // if (responseMap.get("responseCode").toString()
 * // .equals("200")) {
 * // break;
 * // } else {
 * // logger.info("inside while and try bloack retry - Resonse Code: "
 * // + responseMap.get("responseCode")
 * // .toString());
 * // Thread.sleep(Integer.getInteger(putil
 * // .getProperty("delayinretry")));
 * // }
 * // } catch (GenericException e) {
 * // logger.error(e.getMessage());
 * // throw e;
 * // } catch (Exception e) {
 * // logger.info("inside while and try bloack retry" + e);
 * // Thread.sleep(Integer.getInteger(putil
 * // .getProperty("delayinretry")));
 * // }
 * // counter++;
 * // }
 * // logger.error("We need to send a mail .. after while loop. Content is: ");
 * // // write a code to send a mail....
 * // } catch (Exception ex) {
 * // logger.error("We need to send a mail " + ex.getMessage());
 * // throw new GenericException("ERR-SC-0000",
 * // this.getClass().getSimpleName()
 * // + ":get(String,String,Map,String)",
 * // "RequestParam:" + hRequestParam.toString(), ex);
 * // }
 * }
 * } else {
 * httpmethodPost = new HttpPost(urlValue);
 * Map<String, String> hRequestParam = new HashMap<String, String>();
 * hRequestParam.put("x-forwarded-for", requester);
 * hRequestParam
 * .put("authusername", hRequestParam.get("authusername"));
 * hRequestParam.put("api_key", hRequestParam.get("api_key"));
 * hRequestParam.put("aclrole", hRequestParam.get("aclrole"));
 * Map responseMap = new HashMap();
 * httpmethodPost.setEntity(reqEntity);
 * responseMap = sc.post(httpmethodPost, hRequestParam, contentType);
 * logger.info(responseMap.toString());
 * if (!responseMap.get("responseCode").toString().equals("200")) {
 * // if (jmsDesit.toString().contentEquals("DLQC")) {
 * //
 * // } else {
 * logger.error("Exception: POST - Code: "
 * + responseMap.get("responseCode").toString() + " - ");
 * throw new GenericException("ERR-SC-0000", this.getClass()
 * .getSimpleName() + ":get(String,String,Map,String)",
 * "RequestParam:" + hRequestParam.toString());
 * // }
 * // try {
 * // while (counter < Integer.getInteger(putil
 * // .getProperty("noofretry"))) {
 * // logger.info("counter value: " + counter);
 * // try {
 * // responseMap = sc.post(httpmethodPost,
 * // hRequestParam, contentType);
 * // if (responseMap.get("responseCode").toString()
 * // .equals("200")) {
 * // break;
 * // } else {
 * // logger.info(
 * "POST:(Response-Code) inside while and try bloack retry - Resonse Code: "
 * // + responseMap.get("responseCode")
 * // .toString());
 * // Thread.sleep(Integer.getInteger(putil
 * // .getProperty("delayinretry")));
 * // }
 * // } catch (Exception e) {
 * // logger.info("POST:(Exception) inside while and try bloack retry"
 * // + e);
 * // try {
 * // Thread.sleep(Integer.getInteger(putil
 * // .getProperty("delayinretry")));
 * // } catch (InterruptedException e1) {
 * // }
 * // }
 * // counter++;
 * // }
 * // logger.error("We need to send a mail .. after while loop. Content is: "
 * // + reqEntity);
 * // // write a code to send a mail....
 * // } catch (Exception ex) {
 * // logger.error("We need to send a mail .. Catch. Content is: "
 * // + reqEntity);
 * // // throw new GenericException("ERR-SC-0000",
 * // // this.getClass().getSimpleName()
 * // // + ":get(String,String,Map,String)",
 * // // "RequestParam:" + hRequestParam.toString(), ex);
 * // }
 * }
 * }
 * }
 * private static String appendUrl(String url, String value) {
 * return null;
 * }
 * }
 */
