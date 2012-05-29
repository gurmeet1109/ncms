package in.nic.cmf;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.helper.HttpHelper;
import in.nic.cmf.util.Utils;

import java.net.URI;
import java.util.HashMap;
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
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

@Deprecated
//implements CMFService 
public class ServiceClient {
 /*
    private Map<String, String> baseUrl = new HashMap<String, String>();

    private static HashMap<String, ServiceClient> scs = new HashMap<String, ServiceClient>();


    private String serviceName = null;

    private LogTracer log = new LogTracer("ServiceTraceLogger");
  
    private PropertiesUtil putil = new PropertiesUtil("serviceclient");

    private Connection mqConnection = null;

    private Utils utils = Utils.getInstance();

    private String destination;

    private final HttpHelper helper = new HttpHelper();

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

    private ServiceClient(String service) throws GenericException {
	setServiceName(service);
	try {
	    setBaseUrlFromProperty(service);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	}
    }

    private void setBaseUrlFromProperty(String service) throws GenericException {
	String baseUrlProperty = putil.getProperty(service);
	setDestination(putil.getProperty("mq" + service));
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
	    if (urlArray[3].indexOf("async=") != -1) {
		setBaseUrl(urlArray[3], "async", service);
	    } else {
		setBaseUrl(service + "_async", "false");
	    }
	} catch (Exception ex) {
	    setBaseUrl(service + "_mq", "DLC.Q");
	    setBaseUrl(service + "_async", "false");
	}
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


    public static ServiceClient getInstance(String service)
	    throws GenericException {
	boolean isEmpty = true;
	if (service != null) {
	    service = service.trim();
	    isEmpty = service.isEmpty();
	}
	if (isEmpty) {
	    throw new GenericException("ERR-SC-0002",
		    "Service Name Parameter Empty: " + service);
	}
	if (!scs.containsKey(service)) {
	    scs.put(service, new ServiceClient(service));
	}
	return scs.get(service);
    }


    private boolean sendingAsyncMessage(String collection,
	    Map<String, String> hRequestParam) throws Exception {
	MessageProducer producer = null;
	Session session = null;
	try {
	    String mqUrl = utils.isEmpty(putil.getProperty("mqurl")) ? ActiveMQConnection.DEFAULT_BROKER_URL
		    : putil.getProperty("mqurl");
	    log.debug("mqUrl : " + mqUrl);
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
	    String collection, Map<String, String> hRequestParam)
	    throws Exception {
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

    private void logTime(long startTime, String action) {
	long timeTaken = System.currentTimeMillis() - startTime;
	log.debug("CurrentTime:" + System.currentTimeMillis() + "; TimeTaken:"
		+ timeTaken + "; Action:" + action);
    }

    private HttpUriRequest createHeaders(HttpUriRequest httprequest,
	    Map<String, String> hRequestParam) {

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
	return httprequest;
    }


    private StringEntity getStringEntity(HttpPost httppost, String collections,
	    String contentType) throws GenericException {
	try {
	    StringEntity reqEntity = new StringEntity(collections, "utf-8");
	    if (contentType != null) {
		reqEntity.setContentType(contentType + ";charset=utf-8");
	    } else {
		log.error("Content type null");
		throw new GenericException("Content type null");
	    }
	    return reqEntity;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":getStringEntity()",
		    httppost.toString(), e);
	}
    }

    private Map<String, Object> post(HttpPost httpPost,
	    Map<String, String> hRequestParam, String contentType){

	try {
	    httpPost = (HttpPost) createHeaders(httpPost, hRequestParam);
	    long l = System.currentTimeMillis();
	    HashMap<String, Object> response = helper.execute(httpPost);
	    if ((Integer) response.get("status-code") != HttpStatus.SC_OK) {
		Map<String, Object> resultMap = utils
			.parsingErrorResponse((String) response
				.get("responseStringBody"));
		// throw new GenericException(resultMap.toString());
	    }
	    return response;
	} catch (GenericException ge) {
	    throw ge;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":post(HttpPost,Map)",
		    hRequestParam.toString(), e);
	}
    }


    private Map<String, Object> delete(HttpDelete httpdelete,
	    Map<String, String> hRequestParam) {
	try {
	    log.debug("SERVICE Name !!: " + getServiceName());
	    httpdelete = (HttpDelete) createHeaders(httpdelete, hRequestParam);
	    long l = System.currentTimeMillis();
	    Map<String, Object> response = helper.execute(httpdelete);
	    logTime(l, "time Taken for Helper Execute");
	    if ((Integer) response.get("status-code") != HttpStatus.SC_OK) {
		Map<String, Object> resultMap = utils
			.parsingErrorResponse((String) response
				.get("responseStringBody"));
		// throw new GenericException(resultMap);
	    }
	    return response;
	} catch (GenericException ge) {
	    throw ge;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":delete(httpdelete,Map)",
		    hRequestParam.toString(), e);
	}
    }

    private void isValidParams(Object... params) throws GenericException {
	for (Object param : params) {
	    if (param instanceof String) {
		boolean status = utils.isEmpty(param.toString());
		if (status) {
		    throw new GenericException("ERR-SC-0003",
			    "IllegalArgumentException", param.toString());
		}
	    }
	}
    }

    public void setDestination(String destination) {
	this.destination = destination;
    }

    public String getDestination() {
	return destination;
    }

    public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
    }

    public String getServiceName() {
	return serviceName;
    }

    public void setMqConnection(Connection mqConnection) {
	this.mqConnection = mqConnection;
    }

    public Connection getMqConnection() {
	return mqConnection;
    }

    // GET

    private Map<String, Object> get(String url,
	    Map<String, String> hRequestParam) throws GenericException {

	if (!url.startsWith("http:")) {
	    isValidParams(url);
	    url = baseUrl.get(getServiceName() + "_ro") + "/" + url;
	}
	try {
	    HttpGet httpget = new HttpGet();
	    httpget.setURI(new URI(url));
	    httpget = (HttpGet) createHeaders(httpget, hRequestParam);

	    Long l = System.currentTimeMillis();
	    Map<String, Object> response = helper.execute(httpget);
	    logTime(l, "time Taken for Helper Execute");
	    if ((Integer) response.get("status-code") != HttpStatus.SC_OK) {
		Map<String, Object> resultMap = utils
			.parsingErrorResponse((String) response
				.get("responseStringBody"));
		throw new GenericException(resultMap);
	    }

	    return response;
	} catch (GenericException e) {
	    log.error("inside ge:" + e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0006", this.getClass()
		    .getSimpleName() + ":get()", hRequestParam.toString(), e);
	}
    }

    private Map<String, Object> get(String domain,
	    Map<String, Map<String, Object>> parameters) {
	String partUrl = (String) parameters.get("input").get("queryParams");
	Map<String, String> userContext = new HashMap<String, String>();
	try {
	    isValidParams(partUrl);
	    String url = null;
	    if (getServiceName() == "searchapi") {
		if (partUrl.indexOf("q=") == -1) {
		    url = baseUrl.get(getServiceName() + "_ro") + "/" + domain
			    + "/search?q=&" + partUrl;
		} else {
		    url = baseUrl.get(getServiceName() + "_ro") + "/" + domain
			    + "/search?" + partUrl;
		}
	    } else if (getServiceName() == "dms") {
		url = baseUrl.get(getServiceName() + "_ro") + "/" + domain
			+ "/" + partUrl;
	    } else if (getServiceName() == "metadata") {
		url = baseUrl.get(getServiceName() + "_ro")
			+ "/collections/getmetadata";
	    } else if (getServiceName() == "geotagger") {
		url = baseUrl.get(getServiceName() + "_ro")
			+ "/collections/getinfo";
	    } else if (getServiceName() == "dataservices") {
		if (partUrl.indexOf("q=") == -1) {
		    url = baseUrl.get(getServiceName() + "_ro") + "/" + domain
			    + "/search?q=" + partUrl;
		} else {
		    url = baseUrl.get(getServiceName() + "_ro") + "/" + domain
			    + "/search?" + partUrl;
		}
	    } else if ((getServiceName() == "applicationflow")
		    || (getServiceName() == "sitebuilder")
		    || (getServiceName() == "audit")) {
		url = baseUrl.get(getServiceName() + "_ro") + "/" + domain
			+ "/" + partUrl;
	    }
	    log.debug(getServiceName() + " partUrl : " + partUrl
		    + " ***  target URI is " + url);
	    return get(url, userContext);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":get(String,String,Map,String)",
		    "partUrl:" + partUrl + "RequestParam:"
			    + userContext.toString(), e);
	}
    }

    private Map<String, Object> getByFileName(String domainName,
	    String entityType, String partUrl,
	    Map<String, String> hRequestParam) throws GenericException {
	try {
	    String url = "";
	    url = baseUrl.get(getServiceName() + "_ro") + "/" + domainName;
	    if (!utils.isEmpty(entityType)) {
		url += "/" + entityType;
	    }
	    if (!utils.isEmpty(partUrl)) {
		url += "/" + partUrl;
	    }
	    log.debug("getByFileName() url : " + url);
	    return get(url, hRequestParam);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":get(String,Map,String)", "partUrl:"
		    + partUrl + "RequestParam:" + hRequestParam.toString(), e);
	}
    }

    private Map<String, Object> postXml(String domain, String collections,
	    Map<String, String> usercontext, String contentType) {
	try {
	    isValidParams(domain, collections, contentType);
	    usercontext.put("domain", domain);
	    if (baseUrl.get(getServiceName() + "_async").toString()
		    .equals("true")) {
		initializeMQConnection();
		usercontext.put("method", "post");
		HashMap<String, Object> hResponse = new HashMap<String, Object>();
		if (sendingAsyncMessage(collections, usercontext)) {
		    log.debug("Post request sent to MQ successfully.");
		    hResponse.put("responseCode", 200);
		} else {
		    hResponse.put("responseCode", 404);
		}
		return hResponse;
	    }
	    String url = baseUrl.get(getServiceName() + "_rw") + "/" + domain;
	    log.debug("serviceclient url ==> " + url);
	    HttpPost httpPost = new HttpPost(url);
	    httpPost.setEntity(getStringEntity(httpPost, collections,
		    contentType));
	    return post(httpPost, usercontext, contentType);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":post(String,Collections,Map,String)",
		    "RequestParam:" + usercontext.toString(), e);
	}
    }

    private Map<String, Object> delete(String domain, String entity,
	    String id, HashMap<String, String> hRequestParam)
	    throws GenericException {
	try {
	    isValidParams(domain, entity, id);
	    String query = "";
	    log.debug("SERVICE Name **: " + getServiceName());
	    // if (getServiceName() == "applicationflow") {
	    query = entity + "/" + id;
	    // }
	    hRequestParam.put("domain", domain);
	    if (baseUrl.get(getServiceName() + "_async").toString()
		    .equals("true")) {
		initializeMQConnection();
		hRequestParam.put("method", "Delete");
		hRequestParam.put("query", query);
		Map<String, Object> hResponse = new HashMap<String, Object>();
		if (sendingAsyncMessage("", hRequestParam)) { //
		    log.methodEntry("Delete request sent to MQ successfully.");
		    hResponse.put("responseCode", 200);
		} else {
		    hResponse.put("responseCode", 404);
		}
		return hResponse;
	    }
	    log.debug("after appending entitytype with id :" + query);
	    String url = baseUrl.get(getServiceName() + "_rw") + "/" + domain
		    + "/" + query;
	    log.debug("serviceclient url ==> " + url);
	    HttpDelete httpdelete = new HttpDelete(new URI(url));
	    return delete(httpdelete, hRequestParam);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":delete(String,String,Map)",
		    "RequestParam:" + hRequestParam, e);
	}
    }

    private Map<String, Object> delete(String domain, String Query,
	    Map<String, String> hRequestParam) throws GenericException {
	try {
	    log.debug("SERVICE Name @@: " + getServiceName());
	    isValidParams(domain, Query);
	    hRequestParam.put("domain", domain);
	    if (baseUrl.get(getServiceName() + "_async").toString()
		    .equals("true")) {
		initializeMQConnection();
		hRequestParam.put("method", "Delete");
		hRequestParam.put("query", Query);
		Map<String, Object> hResponse = new HashMap<String, Object>();
		if (sendingAsyncMessage("", hRequestParam)) { //
		    log.debug("Delete request sent to MQ successfully.");
		    hResponse.put("responseCode", 200);
		} else {
		    hResponse.put("responseCode", 404);
		}
		return hResponse;
	    }
	    String url = baseUrl.get(getServiceName() + "_rw") + "/" + domain
		    + "/" + Query;
	    log.debug("serviceclient url : " + url);
	    HttpDelete httpdelete = new HttpDelete(new URI(url));
	    return delete(httpdelete, hRequestParam);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-SC-0000", this.getClass()
		    .getSimpleName() + ":delete(String,String,Map)",
		    "RequestParam:" + hRequestParam, e);
	}
    }

    public Map<String, Object> post(String domain,
	    HashMap<String, HashMap<String, Object>> parameters) {
	String collections = (String) parameters.get("input").get("content");
	Map<String, String> usercontext = (HashMap<String, String>) parameters
		.get("input").get("usercontext");
	String contentType = (String) parameters.get("input").get("format");

	return postXml(domain, collections, usercontext, contentType);

    }

    // POST
    public Map<String, Object> postCollections(String domain,
	    String collections, Map<String, String> hRequestParam,
	    String contentType) throws GenericException {
	try {
	    isValidParams(domain, collections, contentType);
	    String url = baseUrl.get(getServiceName() + "_rw") + "/" + domain
		    + "/collections";
	    log.debug("serviceclient url ==> " + url);
	    HttpPost httpPost = new HttpPost(url);
	    httpPost.setEntity(getStringEntity(httpPost, collections,
		    contentType));
	    return post(httpPost, hRequestParam, contentType);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	}
    }

    // GET
    @Override
    public Map<String, Map<String, Object>> find(String domain,
	    String entity, Map<String, Map<String, Object>> parameters) {
	Map<String, Object> outputMap = new HashMap<String, Object>();
	Map getReturnMap = get(domain,parameters);
	outputMap.put("content", getReturnMap.get("responseStringBody"));
	outputMap.put("statusCode", getReturnMap.get("statusCode"));
	parameters.put("output", outputMap);
	return parameters;
    }

    // GETBYID
    @Override
    public Map<String, Map<String, Object>> findById(String domain,
	    String entity, String id,
	    Map<String, Map<String, Object>> parameters) {
	Map<String, String> userContext = (Map<String, String>) parameters
		.get("input").get("usercontext");
	Map<String, Object> outputMap = new HashMap<String, Object>();
	Map getReturnMap = getByFileName(domain, entity, id, userContext);
	outputMap.put("content", getReturnMap.get("responseStringBody"));
	outputMap.put("statusCode", getReturnMap.get("statusCode"));
	parameters.put("output", outputMap);
	return parameters;
    }

    // POST
    @Override
    public Map<String, Map<String, Object>> add(String domain,
	    String entityType,
	    Map<String, Map<String, Object>> parameters) {
	Map<String, Object> outputMap = new HashMap<String, Object>();
	// HashMap<String,Byte> hashParam = (HashMap<String, Byte>)
	// parameters.get("input").get("file");
	// HashMap getReturnMap = new HashMap();
	// if(hashParam.size()>0){
	// System.out.println("binary is there.");
	// }else{
	// getReturnMap = post(domain,parameters);
	// }

	// outputMap.put("content", getReturnMap.get("responseStringBody"));
	// outputMap.put("status", getReturnMap.get("responseCode"));

	outputMap.put("status", 200);
	parameters.put("output", outputMap);
	return parameters;
    }

    // DELETEBYID
    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
	    String entityType, String id,
	    Map<String, Map<String, Object>> parameters)
	    {
	Map<String, String> usercontext = (Map<String, String>) parameters
		.get("input").get("usercontext");
	// HashMap getReturnMap = delete(domain,entityType,id,usercontext);
	// HashMap<String,Object> outputMap = new HashMap<String,Object>();
	// outputMap.put("content",getReturnMap.get("responseStringBody") );
	// outputMap.put("status", getReturnMap.get("status-code"));

	Map<String, Object> outputMap = new HashMap<String, Object>();
	outputMap.put("status", 200);
	parameters.put("output", outputMap);
	return parameters;
    }

    // DELETEBYQUERY
    @Override
    public Map<String, HashMap<String, Object>> deleteByQuery(
	    String domain, String query,
	    Map<String, Map<String, Object>> parameters)
	    {
	Map<String, String> usercontext = (Map<String, String>) parameters
		.get("input").get("usercontext");
	// String query = (String) parameters.get("input").get("queryString");
	Map<String, Object> outputMap = new HashMap<String, Object>();
	outputMap.put("content", delete(domain, query, usercontext));
	outputMap.put("status", "");
	parameters.put("output", outputMap);
	return parameters;
    }

    @Override
    public void setLogTracer(LogTracer log) {
	this.log = log;
    }

	@Override
	public Map<String, Map<String, Object>> read(String domain,
			String entityType, Map<String, Map<String, Object>> parameters)
			{
		// TODO Auto-generated method stub
		return null;
	}*/

}
