package in.nic.cmf.dataservices;

import in.nic.cmf.ServiceClient;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.UserContext;
import in.nic.cmf.util.Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * The Class ServiceImpl.
 */
public class ServiceHelper {

    /** The Constant PATH_SEPERATOR. */
    private static final String PATH_SEPERATOR = "/";

    /** The log. */
    private LogTracer log;

    /** The media handler. */
    // private MediaHandler mediaHandler = new MediaHandler();

    /** The business flow. */
    // private BusinessFlowImpl businessFlow = BusinessFlowImpl.getInstance();

    /** The rule engine. */
    // private RuleEngineImpl ruleEngine = RuleEngineImpl.getInstance();

    /** The client. */
    ServiceClient client = null;

    /** The propertiesutil. */
    private PropertiesUtil propertiesutil = new PropertiesUtil("dataservices");

    /** The content type. */
    String contentType = "application/xml";

    // taken from SearchClient
    /** The exlude params. */
    public List<String> excludeParams;

    /** The modify params. */
    private Map<String, String> modifyParams;

    /** The utils. */
    private Utils utils = Utils.getInstance();
    private final UserContext usercontext = UserContext.getInstance();

    Helper helper = Helper.getInstance();
    List<Map> tmpDupDetails = null;

    private HashMap<String, Object> interfaceHash = new HashMap<String, Object>();

    private ServiceHelper() {
	log = new LogTracer("dfdataserviceslog", true, true, true, true, true);
    }

    /*
     * @Autowired private Cachable cache;
     */

    /**
     * Sets the exclude params.
     * 
     * @param list
     *            the new exclude params
     */
    public void setExcludeParams(List<String> list) {
	this.excludeParams = list;
    }

    /**
     * Sets the modify params.
     * 
     * @param mParams
     *            the m params
     */
    public void setModifyParams(Map<String, String> mParams) {
	modifyParams = mParams;
    }

    /** The uploaded file name. */
    String uploadedFileName = null;

    public Object FileRead(String fileName) {
	InputStream is;
	String response = "";
	try {
	    is = new FileInputStream("/opt/cmf/resources/" + fileName);
	    response = IOUtils.toString(is);
	} catch (FileNotFoundException e) {
	    throw new GenericException("ERR-GEN-0012", this.getClass()
		    .getSimpleName() + ":FileRead()", e);
	} catch (IOException e) {
	    throw new GenericException("ERR-GEN-0000", this.getClass()
		    .getSimpleName() + ":FileRead()", e);
	}
	return response;
    }

    /**
     * Post Entities as a xml.
     * 
     * @param domain
     *            the domain
     * @param inputCollections
     *            the input collections
     * @param apiKey
     *            the api key
     * @param userName
     *            the user name
     * @param requester
     *            the requester
     * @param aclrole
     *            the aclrole
     * @return A Collection
     * @throws GenericException
     *             the generic exception
     */

    public String add(Map<String, String> userContext, String domain,
	    String content, String format) throws GenericException {
	System.out.println("Inside ServiceImpl add Method");
	interfaceHash = (HashMap<String, Object>) FormatJson.getInstance().in(
		FileRead("InterfaceDetails.json"));
	Canonical canonical = Canonical.getInstance();
	domain = canonical.getData(domain);
	String strCollections = canonical.getData(content);
	List<Object> ldapList = new ArrayList();
	HashMap<String, Object> modifiedHash = new HashMap<String, Object>();
	HashMap<String, Object> collHash = (HashMap<String, Object>) FormatXml
		.getInstance().in(content).get("Collections");
	boolean result = true;
	boolean isNewEntry = false;
	boolean ldapStatus = false;
	log.methodEntry(this.getClass().getSimpleName() + ".add()");
	try {
	    String entityName = "";
	    for (Entry<String, Object> e : collHash.entrySet()) {
		String key = (String) e.getKey();
		if (key.equals("Count")) {
		    modifiedHash.put("Count", e.getValue());
		} else if (!key.equals("Count")) {
		    entityName = key;
		    List<Object> fl = new ArrayList<Object>();
		    if (e.getValue().getClass().getSimpleName()
			    .equalsIgnoreCase("ArrayList")) {
			List<HashMap<String, Object>> entityListOfHash = (List<HashMap<String, Object>>) e
				.getValue();
			for (HashMap<String, Object> eachEntityHash : entityListOfHash) {

			    if (entityName.equalsIgnoreCase("userprofile")
				    || entityName
					    .equalsIgnoreCase("cmsuserprofile")) {
				ldapList.add(eachEntityHash);
			    }
			    fl.add(getModifiedHash(eachEntityHash, entityName,
				    domain));
			}
			modifiedHash.put(entityName, fl);
		    } else if (e.getValue().getClass().getSimpleName()
			    .equalsIgnoreCase("HashMap")) {
			if (entityName.equalsIgnoreCase("userprofile")
				|| entityName
					.equalsIgnoreCase("cmsuserprofile")) {
			    ldapList.add((HashMap<String, Object>) e.getValue());
			}
			fl.add(getModifiedHash(
				(HashMap<String, Object>) e.getValue(),
				entityName, domain));
			modifiedHash.put(entityName, fl);
		    }
		}
	    }
	    collHash.put("Collections", modifiedHash);

	    HashMap<String, Object> collectionHash = collHash;

	    String collectionXml = (String) FormatXml.getInstance().out(
		    collectionHash);

	    // Collections modifiedCollections = utils
	    // .getStringXmlAsCollections(collectionXml); // need to remove
	    // domain
	    // dependency
	    // modifiedCollections = businessFlow.process(domain,
	    // modifiedCollections, userContext);

	    log.debug("After validation");
	    Map resultMap = new HashMap();
	    /*
	     * Map resultMap = new HashMap(); HashMap<String, Object>
	     * errorCollections = dataValidation(collectionHash);
	     * HashMap<String, Object> xmlmap = (HashMap<String, Object>)
	     * errorCollections .get("Collections"); for (Entry<String, Object>
	     * e : xmlmap.entrySet()) { String key = (String) e.getKey(); if
	     * (key.equals("ValidationStatus")) { HashMap<String, String>
	     * errorHash = (HashMap<String, String>) e .getValue(); if
	     * (errorHash.get("Status").equals("Error")) { return
	     * getJsonResponse(errorCollections, format, true); } } }
	     */

	    /*
	     * if (ldapList.size() > 0) { for (Object obj : ldapList) {
	     * HashMap<String, Object> ldapObject = (HashMap<String, Object>)
	     * obj; ldapStatus = ldapUserEntry(ldapObject); if (!ldapStatus) {
	     * throw new GenericException("ERR-DS-0012"); } } }
	     */
	    client = ServiceClient.getInstance("applicationflow");
	    // if (result && modifiedCollections.getCollection().size() > 0) {
	    log.debug("DS-workflow:add - existing entry");
	    // resultMap = client.post(domain,
	    // utils.getCollectionsAsStringXml(modifiedCollections),
	    // userContext, contentType);
	    // }

	    if (resultMap.get("responseCode").toString().equals("401")
		    || resultMap.get("responseCode").toString().equals("500")) {
		log.error("ApplicationFlow failure ==> "
			+ resultMap.get("responseCode").toString());
		throw new GenericException("ERR-DS-0001");
	    } else if (!resultMap.get("responseCode").toString().equals("200")) {
		log.error("ApplicationFlow post failure ==> "
			+ resultMap.get("responseCode").toString());
		throw new GenericException("ERR-DS-0000",
			"ApplicationFlow post failure");
	    }

	    // for master generations
	    if (resultMap.get("responseCode").toString().equals("200")) {
		log.debug("Appflow returns 200. so executing for duplication check.");
		log.debug("before generate:" + tmpDupDetails);
		// ruleEngine.generateForDuplication(tmpDupDetails);
		log.debug("going to enter into master logic" + resultMap);
		generateMasters(domain, collectionHash);
	    }
	    log.methodExit(this.getClass().getSimpleName() + ".add()");
	    return getJsonResponse(collectionHash, format, false);
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    if (format.equals("ClientExtJs")) {
		return getExtjsResponse("false", e.getMessage());
	    } else {
		throw e;
	    }
	} catch (Exception e) {
	    log.error(e.getMessage());
	    if (format.equals("ClientExtJs")) {
		return getExtjsResponse("false", e.getMessage());
	    } else {
		throw new GenericException("ERR-DS-0000", this.getClass()
			.getSimpleName() + ":add()", e);
	    }
	}
    }

    private HashMap<String, Object> getModifiedHash(
	    HashMap<String, Object> eachEntityHash, String entityName,
	    String domain) {
	HashMap<String, Object> modifiedHash = new HashMap<String, Object>();
	List<String> seoableList = new ArrayList<String>();
	eachEntityHash.put("Site", domain);
	eachEntityHash.put("Version", propertiesutil.getProperty("Version"));
	if (utils.isEmpty(eachEntityHash.get("Id").toString())) {
	    modifiedHash.put("isNewEntry", "true");
	    eachEntityHash.put("Id", Uniqueid.getId());
	} else {
	    modifiedHash.put("isNewEntry", "false");
	}

	if (seoableList.contains(entityName)) {
	    String seoUrl = getSEOURL(eachEntityHash, domain);
	    if (seoUrl != null) {
		eachEntityHash.put("SeoUrl", seoUrl);
	    }
	}
	return eachEntityHash;
    }

    /**
     * Gets the json response.
     * 
     * @param resultantCollections
     *            the resultant collections
     * @return the json response
     */
    public String getJsonResponse(HashMap<String, Object> resultantCollections,
	    String format, boolean errFlag) {
	JSONObject jsonObject = new JSONObject();
	JSONObject msgObject = new JSONObject();

	String outString = "";
	HashMap<String, Object> collHash = (HashMap<String, Object>) resultantCollections
		.get("Collections");
	for (Entry<String, Object> e : collHash.entrySet()) {
	    String key = (String) e.getKey();
	    if (!key.equals("Count")) {
		HashMap<String, String> fl = new HashMap<String, String>();
		List<HashMap<String, Object>> entityListOfHash = (List<HashMap<String, Object>>) e
			.getValue();
		for (HashMap<String, Object> eachEntityHash : entityListOfHash) {
		    if (eachEntityHash.get("EntityType").equals(
			    "ValidationStatus")) {
			if (eachEntityHash.get("Status").equals("Error")) {
			    HashMap<String, Object> errorFields = (HashMap<String, Object>) eachEntityHash
				    .get("ErrorFields");
			    List<HashMap<String, Object>> fieldDetails = (List<HashMap<String, Object>>) errorFields
				    .get("FieldDetails");
			    for (HashMap<String, Object> obj : fieldDetails) {
				outString += obj.get("FieldError") + "<br>";
				msgObject.put("ValidationError", outString);
			    }
			} else {
			    if (eachEntityHash.containsKey("SeoUrl")) {
				msgObject.put("SeoUrl",
					eachEntityHash.get("SeoUrl"));
			    }
			    msgObject.put("Id", eachEntityHash.get("Id"));
			}
		    }
		}
	    }
	}
	log.debug("ErrorString **** " + outString);
	if (format.equals("ClientExtJs")) {
	    if (errFlag) {
		jsonObject.put("success", "false");
	    } else {
		jsonObject.put("success", "true");
	    }
	    jsonObject.put("msg", msgObject);
	    return jsonObject.toString();
	} else {
	    return msgObject.toString();
	}
    }

    /**
     * Gets the extjs response.
     * 
     * @param success
     *            the success
     * @param msg
     *            the msg
     * @return the extjs response
     */
    public String getExtjsResponse(String success, String msg) {
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("success", success);
	jsonObject.put("msg", msg);
	return jsonObject.toString();
    }

    /**
     * Ldap user entry.
     * 
     * @param storable
     *            the storable
     * @return true, if successful
     */
    @SuppressWarnings("unchecked")
    private boolean ldapUserEntry(HashMap<String, Object> storable) {
	log.debug("inside ldap user entry!");
	try {
	    String domainName = "";
	    String passWord = "";
	    HashMap params = new HashMap();
	    params.put("username", storable.get("UserName"));
	    params.put("role", storable.get("UserRole"));
	    params.put("isactive", storable.get("IsActive").toString());
	    params.put("id", storable.get("Id"));
	    domainName = storable.get("Site").toString();
	    passWord = storable.get("Password").toString();
	    client = ServiceClient.getInstance("ldap");
	    String format = "json";
	    params.put("format", format);
	    log.debug("ldap params :::::: " + params);
	    Map resultMap = new HashMap(); // need to remove this
	    // Map resultMap = client.post(params, domainName, "checkuser");
	    // need to implement
	    log.debug("Result Map :::::: " + resultMap);
	    log.debug("check username :::::: " + params);
	    Map result = new HashMap();
	    if (resultMap.containsKey("responseStringBody")) {
		String st = (String) resultMap.get("responseStringBody");
		log.debug("ResponseString body ::::: " + st);
		client = ServiceClient.getInstance("ldap");
		log.debug("After ServiceClient Instance");
		JSONObject json = (JSONObject) JSONSerializer.toJSON(st);
		JSONObject response = json.getJSONObject("Response");
		log.debug("JsonObject Response ::::: " + response);
		String status = response.getString("Status");
		if (status.equals("false")) {
		    // if ((Long) response.get("responseCode") == 200) {
		    params.put("password", passWord);
		    // result = client.post(params, domainName, "createuser");
		    // need to implement this
		    log.debug("create user :::::: " + result);
		} else {
		    if (response.getString("ProfileId")
			    .equals(params.get("id"))) {
			params.put("newpassword", passWord);
			// result = client.post(params, domainName, "update");
			// need to implement
			log.debug("change password :::::: " + result);
		    } else {
			return false;
		    }
		}
	    }
	    if (result.containsKey("responseCode")) {
		String res = (String) result.get("responseStringBody");
		log.debug("response ::::: " + res);
		log.debug("responseCode :::::: " + result.get("responseCode"));
		if ((Integer) result.get("responseCode") == 200) {
		    return true;
		} else {
		    return false;
		}
	    }
	} catch (GenericException e) {
	    throw e;
	} catch (Exception ex) {
	    log.debug("ldapUserEntry:" + ex.getMessage());
	}
	return false;
    }

    @SuppressWarnings("unchecked")
    private boolean ldapUserRemove(String domain, String entity, String id) {
	log.debug("inside ldap user remove !");
	try {
	    client = ServiceClient.getInstance("ldap");
	    String format = "json";
	    log.debug("ldap params :::::: domainName *** " + domain
		    + " entityName *** " + entity + " id *** " + id);
	    Map result = new HashMap(); // need to remove this
	    // result = client.delete(domain, "delete", id); // need to
	    // implement
	    if (result.containsKey("responseCode")) {
		String res = (String) result.get("responseStringBody");
		log.debug("response ::::: " + res);
		log.debug("responseCode :::::: " + result.get("responseCode"));
		if ((Integer) result.get("responseCode") == 200
			|| (Integer) result.get("responseCode") == 404) {
		    return true;
		} else {
		    return false;
		}
	    }
	} catch (GenericException e) {
	    throw e;
	} catch (Exception ex) {
	    log.debug("ldapUserRemove:" + ex.getMessage());
	}
	return false;
    }

    private void generateMasters(String domain,
	    HashMap<String, Object> modifiedHash) {
	try {
	    log.debug("inside success of 200 from appflow");
	    List<String> generateList = new ArrayList<String>();
	    for (String eachEntity : propertiesutil.getProperty("generate")
		    .split(",")) {
		log.debug("eachentity check inside generaterules block:"
			+ eachEntity);
		generateList.add(eachEntity);
	    }
	    HashMap<String, Object> collHash = (HashMap<String, Object>) modifiedHash
		    .get("Collections");
	    for (Entry<String, Object> e : collHash.entrySet()) {
		String key = (String) e.getKey();
		if (!key.equals("Count")) {
		    if (generateList.contains(key)) {
			log.debug("matches:" + key.toLowerCase());
			log.debug("before thread call" + domain
				+ key.toLowerCase());
			log.debug("after runnable instance creation and before starting the thread");
			// Thread t = new Thread(new ThreadInvoke(domain, key));
			// t.start();
			log.debug("after thread execution");
		    }
		}
	    }
	} catch (Exception ex) {
	    log.debug("executing the master logic:" + ex.getMessage());
	}
    }

    /**
     * Data validation.
     * 
     * @param collections
     *            the collections
     * @return the collections
     * @throws GenericException
     *             the generic exception
     */
    /*
     * private HashMap<String, Object> dataValidation( HashMap<String, Object>
     * collectionHash) throws GenericException {
     * log.methodEntry(this.getClass().getSimpleName() + ".dataValidation()");
     * tmpDupDetails = new ArrayList<Map>(); try { String collectionXml =
     * (String) FormatXml.getInstance().out( collectionHash);
     * 
     * Collections collections = utils
     * .getStringXmlAsCollections(collectionXml); // need to remove // domain //
     * dependency Map ruleEngineResult = ruleEngine.validate(collections,
     * helper.getUniqueContent(), tmpDupDetails); Collections errorCollections =
     * new Collections(); if (ruleEngineResult != null) { errorCollections =
     * (Collections) ruleEngineResult .get("errorcollections"); } String
     * errorXml = utils.getCollectionsAsStringXml(errorCollections);
     * HashMap<String, Object> errorHash = FormatXml.getInstance().in(
     * errorXml); tmpDupDetails = (List<Map>)
     * ruleEngineResult.get("tmpdetails"); log.debug("tmpdupdetails:" +
     * tmpDupDetails); log.methodExit(this.getClass().getSimpleName() +
     * ".dataValidation()"); return errorHash; } catch (GenericException e) {
     * log.error(e.getMessage()); throw e; } catch (Exception e) {
     * log.error(e.getMessage()); throw new GenericException("ERR-DS-0000",
     * this.getClass() .getSimpleName() + ".dataValidation()", e); } }
     */
    /*
     * (non-Javadoc)
     * 
     * @see in.nic.cmf.services.dataservices.Service#getById(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */

    public String getById(HashMap<String, HashMap<String, Object>> parameters,
	    String domain, String entity, String id) throws GenericException {
	try {
	    log.methodEntry(this.getClass().getSimpleName() + ".getById()");
	    HashMap<String, Object> input = parameters.get("input");
	    String[] tokens = id.split("\\.");
	    String fileName = id;
	    try {
		if (tokens[1] != null)
		    fileName = id;
	    } catch (ArrayIndexOutOfBoundsException ae) {
		fileName = id + ".xml";
		log.warn("Array index out of bounds exception for " + fileName);
	    }
	    log.debug("filename ==> " + fileName);

	    log.info("Auth Map ==> " + input.get("userContext"));
	    client = ServiceClient.getInstance("applicationflow");

	    HashMap<String, HashMap<String, Object>> ResultMap = client
		    .findById(domain, entity, id, parameters);

	    if (ResultMap.containsKey("output")) {
		if (isEmpty(ResultMap.get("output").get("content").toString())) {
		    throw new GenericException("ERR-DS-0000",
			    "Output Data Map is missing");
		}
	    } else {
		throw new GenericException("ERR-DS-0001",
			"Output Data Map is missing");
	    }

	    if (null == ResultMap.get("output").get("statusCode")) {
		log.error("Authentication failure");
		throw new GenericException("ERR-SE-0001");
	    } else if (!ResultMap.get("output").get("statusCode").equals("200")) {
		log.error("Authentication failure");
		throw new GenericException("ERR-SE-0001");
	    }
	    log.methodExit(this.getClass().getSimpleName() + ".getById()");

	    return "<Collections>" + ResultMap.get("output").get("content")
		    + "</Collections>";

	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ".getById()", domain + "/" + entity, e);
	}
    }

    /**
     * Gets the cache key.
     * 
     * @param domain
     *            the domain name as String
     * @param filename
     *            the file name as String
     * @return cache value
     */
    private String getCacheKey(String domain, String filename) {
	return domain + "_" + filename;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.nic.cmf.services.dataservices.Service#getBinaryByFileName(java.lang
     * .String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String)
     */
    /*
     * public byte[] getBinaryByFileName(Map<String, String> userContext, String
     * domain, String filename) throws GenericException {
     * log.methodEntry(this.getClass().getSimpleName() +
     * ".getBinaryByFileName()"); try { client =
     * ServiceClient.getInstance("applicationflow"); // Map resultMap =
     * appFlow.getBinaryByFileName(domain, filename, // userContext); Map
     * resultMap = client.getByFileName(domain, "media", filename, userContext);
     * log.methodExit(this.getClass().getSimpleName() +
     * ".getBinaryByFileName()"); return (byte[])
     * resultMap.get("responseByteBody"); } catch (GenericException e) {
     * log.error(e.getMessage()); throw e; } catch (Exception e) {
     * log.error(e.getMessage()); throw new GenericException("ERR-DS-0000",
     * this.getClass() .getSimpleName() + ".getBinaryByFileName()", domain, e);
     * } }
     */

    /*
     * (non-Javadoc)
     * 
     * @see in.nic.cmf.services.dataservices.Service#searchAll(java.lang.String,
     * java.lang.String, javax.servlet.http.HttpServletRequest)
     */

    public String searchAll(String domain, String entity,
	    HashMap<String, HashMap<String, Object>> arg0)
	    throws GenericException {
	System.out.println("Inside SearchAll");
	log.methodEntry(this.getClass().getSimpleName() + ".searchAll()");
	HashMap<String, Object> input = arg0.get("input");
	String outputCollections = null;

	Map<String, String> userContext = (Map<String, String>) input
		.get("userContext");
	log.debug("UserContext :::: " + userContext);
	System.out.println("UserContext ::: " + userContext);
	System.out
		.println("Before QueryString ::: " + input.get("queryString"));
	BufferedReader reader = null;
	StringBuilder stringBuilder = null;
	try {
	    MultivaluedMapImpl queryString = new MultivaluedMapImpl();
	    if (null != input.get("queryString")) {
		queryString = stringToMap(input.get("queryString").toString());
		System.out.println("after stringtomap :::: " + queryString);
		queryString = applyDefaultParams(
			applyExcludeParams(applyModifyParams(queryString)),
			domain);
		System.out.println("after applydefaultParams ::: "
			+ queryString);

		log.debug("searchAll entity ==> " + entity);
	    }

	    if (!utils.isEmpty(entity)) {
		queryString.add("entitytype", entity);
	    }
	    System.out.println("QueryString :::: " + queryString);

	    log.debug("queryString ==> " + queryString);
	    String urlstr = mapToString(queryString);
	    log.debug("urlstr in searchAll ==>" + urlstr);
	    log.debug("DS-workflow :: Entry");
	    client = ServiceClient.getInstance("applicationflow");
	    log.info("inside searchall - subquery is:" + urlstr);
	    if (!urlstr.isEmpty()) {
		input.put("queryString", urlstr);
	    }
	    arg0.put("input", input);
	    HashMap<String, HashMap<String, Object>> resultMap = client.find(
		    domain, entity, arg0);
	    log.debug("resultMap ==> " + resultMap);
	    if (resultMap.containsKey("output")) {
		if (isEmpty(resultMap.get("output").get("content").toString())) {
		    throw new GenericException("ERR-DS-0000",
			    "Output Data Map is missing");
		}
	    } else {
		throw new GenericException("ERR-DS-0001",
			"Output Data Map is missing");
	    }
	    outputCollections = (String) resultMap.get("output").get("content");
	    log.debug(outputCollections);
	    log.debug("Status Code ::: "
		    + resultMap.get("output").get("statusCode"));
	    if (null == resultMap.get("output").get("statusCode")) {
		log.error("Authentication failure");
		throw new GenericException("ERR-SE-0001");
	    } else if (!resultMap.get("output").get("statusCode").equals("200")) {
		log.error("Authentication failure");
		throw new GenericException("ERR-SE-0001");
	    }

	    log.debug("DS-workflow:search all - exit");
	    log.methodExit(this.getClass().getSimpleName() + ".searchAll()");
	    return outputCollections;
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":searchAll()", domain + "/" + entity, e);
	}

    }

    public void remove(String domain, String entity, String id,
	    HashMap<String, HashMap<String, Object>> parameters) {
	log.methodEntry(this.getClass().getSimpleName()
		+ ".remove() with Four Parameters");
	try {
	    client = ServiceClient.getInstance("applicationflow");
	    HashMap<String, HashMap<String, Object>> resultMap = client
		    .deleteByID(domain, entity, id, parameters);

	    if (resultMap.containsKey("output")) {
		if (isEmpty(resultMap.get("output").get("content").toString())) {
		    throw new GenericException("ERR-DS-0000",
			    "Output Data Map is missing");
		}
	    } else {
		throw new GenericException("ERR-DS-0001",
			"Output Data Map is missing");
	    }
	    log.debug("DS-workflow :: remove - exit");

	    // for duplication check

	    if (null == resultMap.get("output").get("statusCode")) {
		log.error("Authentication failure");
		throw new GenericException("ERR-SE-0001");
	    } else if (!resultMap.get("output").get("statusCode").equals("200")) {
		log.error("Authentication failure");
		throw new GenericException("ERR-SE-0001");
	    }
	    if (resultMap.get("output").get("statusCode").equals("200")) {
		log.debug("deleting duplicate from the ruleengine");
		// ruleEngine.deleteDuplicate(domain, entity, id); //need to
		// implement
	    }

	    // for master generations
	    /*
	     * if (resultMap.get("responseCode").toString().equals("200")) {
	     * log.debug("going to enter into master logic for delete" +
	     * resultMap); removeMasters(domain, entity, id); }
	     */
	    log.methodExit(this.getClass().getSimpleName()
		    + ".remove() With Four Parameters");
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ".remove()", domain + "/" + entity, e);
	}

    }

    /**
     * (non-Javadoc)
     * 
     * @see in.nic.cmf.services.dataservices.Service#remove(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void remove(HashMap<String, HashMap<String, Object>> parameters,
	    String domain, String entity, String id) throws GenericException {
	try {
	    log.methodEntry(this.getClass().getSimpleName() + ".remove()");
	    if (entity.equals("UserProfile") || entity.equals("CmsUserProfile")) {
		if (ldapUserRemove(domain, entity, id)) {
		    remove(domain, entity, id, parameters);
		}
	    } else {
		remove(domain, entity, id, parameters);
	    }
	    log.methodExit(this.getClass().getSimpleName() + ".remove()");
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ".remove()", domain + "/" + entity, e);
	}
    }

    /**
     * Sets the property for object.
     * 
     * @param setterObject
     *            the setter object
     * @param name
     *            the name
     * @param objectlist
     *            the objectlist
     * @return the object
     * @throws GenericException
     *             the generic exception
     */
    /*
     * public Object setPropertyForObject(Object setterObject, String name,
     * Object objectlist) throws GenericException {
     * log.methodEntry(this.getClass().getSimpleName() +
     * ".setPropertyForObject()"); // try {
     * 
     * 
     * if(InitLowerCase(name).equals("id") &&
     * utils.isEmpty(objectlist.toString())){ objectlist = Uniqueid.getId(); }
     * if(name.endsWith("Date")){ DateUtils dUtils = DateUtils.getInstanceOf();
     * objectlist = dUtils.getSolrFormattedDateByForm(objectlist.toString()); }
     * 
     * 
     * log.debug(name + " :: setPropertyForObject ==>" + objectlist);
     * 
     * Field field = null; try { // String class_name =
     * setterObject.getClass().getSimpleName();
     * 
     * field = setterObject.getClass().getDeclaredField( InitLowerCase(name));
     * field.setAccessible(true); field.set(setterObject, objectlist); } catch
     * (SecurityException e) { throw new GenericException("ERR-DS-0011",
     * this.getClass() .getSimpleName() + ":setPropertyForObject()",
     * "SecurityException : Reflected Class :" +
     * setterObject.getClass().getSimpleName() + "Field :" + field.getName(),
     * e); } catch (NoSuchFieldException e) { throw new
     * GenericException("ERR-DS-0011", this.getClass() .getSimpleName() +
     * ":setPropertyForObject()", "NoSuchFieldException : Reflected Class :" +
     * setterObject.getClass().getSimpleName() + "Field :" + field.getName(),
     * e); } catch (IllegalArgumentException e) { throw new
     * GenericException("ERR-DS-0011", this.getClass() .getSimpleName() +
     * ":setPropertyForObject()", "IllegalArgumentException : Reflected Class :"
     * + setterObject.getClass().getSimpleName() + "Field :" + field.getName(),
     * e); } catch (IllegalAccessException e) { throw new
     * GenericException("ERR-DS-0011", this.getClass() .getSimpleName() +
     * ":setPropertyForObject()", "IllegalAccessException :Reflected Class :" +
     * setterObject.getClass().getSimpleName() + "Field :" + field.getName(),
     * e); }
     * 
     * log.methodExit(this.getClass().getSimpleName() +
     * ".setPropertyForObject()"); return setterObject;
     * 
     * } catch (GenericException e) { log.error(e.getMessage()); throw e; }
     * catch (Exception e) { log.error(e.getMessage()); throw new
     * GenericException
     * ("ERR-DS-0000",this.getClass().getSimpleName()+":setPropertyForObject()"
     * ,e); }
     * 
     * 
     * }
     */

    /**
     * Process multipart content.
     * 
     * @param request
     *            the request
     * @return the map
     * @throws GenericException
     *             the generic exception
     */
    public Map processMultipartContent(HttpServletRequest request)
	    throws GenericException {
	log.methodEntry(this.getClass().getSimpleName()
		+ ".processMultipartContent()");
	FileItem item = null;
	try {
	    ServletFileUpload upload = new ServletFileUpload(
		    new DiskFileItemFactory());
	    List<FileItem> items = null;
	    List valueList;
	    Map hMultiPartContent = new HashMap();
	    try {
		items = upload.parseRequest(request);
	    } catch (FileUploadException e) {
		log.error("File upload failure");
		throw new GenericException("ERR-DS-0006");
	    }

	    if (items != null) {
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
		    item = iter.next();
		    if (item.isFormField()) {
			valueList = Arrays.asList(item.getString());
			log.debug(item.getFieldName() + " ==> " + valueList);
			if (hMultiPartContent.containsKey(item.getFieldName()
				.toString())) {
			    log.debug("If field already exist in map ::: "
				    + hMultiPartContent.get(item.getFieldName()
					    .toString()));
			    List<String> vlist = (List) hMultiPartContent
				    .get(item.getFieldName().toString());
			    List<String> value = new ArrayList();
			    for (String s : vlist) {
				value.add(s);
			    }
			    value.add((String) valueList.get(0));
			    log.debug("After Value List ::: " + value);
			    hMultiPartContent.put(item.getFieldName()
				    .toString(), value);
			} else {
			    hMultiPartContent.put(item.getFieldName()
				    .toString(), valueList);
			}

		    } else {
			valueList = Arrays.asList(item);
			log.debug(item.getFieldName() + " ==> " + valueList);
			hMultiPartContent.put(item.getFieldName(), valueList);
		    }
		}
	    }
	    // log.debug("Testing hMultiPartContent Map=====>" +
	    // hMultiPartContent);

	    log.methodExit(this.getClass().getSimpleName()
		    + ".processMultipartContent()");
	    return hMultiPartContent;
	} catch (Exception e) {
	    e.printStackTrace();
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":processMultipartContent()", e);
	}
    }

    /**
     * Process url coded content.
     * 
     * @param request
     *            the request
     * @return the map
     * @throws GenericException
     *             the generic exception
     */
    public Map processUrlCodedContent(HttpServletRequest request)
	    throws GenericException {
	log.methodEntry(this.getClass().getSimpleName()
		+ ".processUrlCodedContent()");
	Map hUrlEncodedContent = new HashMap();
	List valueList;
	Enumeration names = request.getParameterNames();
	try {
	    while (names.hasMoreElements()) {
		String name = (String) names.nextElement();

		valueList = Arrays.asList(request.getParameterValues(name));
		log.debug(name + " ==> " + valueList);
		hUrlEncodedContent.put(name, valueList);
	    }
	    log.methodExit(this.getClass().getSimpleName()
		    + ".processUrlCodedContent()");
	    return hUrlEncodedContent;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":processUrlCodedContent()", e);
	}
    }

    /**
     * Gets the value fromh multi part content.
     * 
     * @param valueList
     *            the value list
     * @return the value fromh multi part content
     */
    private Object getValueFromhMultiPartContent(List valueList) {
	return valueList.get(0);
    }

    /**
     * Trim string.
     * 
     * @param str
     *            the str
     * @return the string
     */
    public String trimString(String str) {
	if (str != null) {
	    if (!str.isEmpty()) {
		String value = str.trim();
		value = value.replaceAll("\\s{2,}", " ");
		return value;
	    }
	}
	return str;
	/*
	 * if(str != null && !str.isEmpty()){ String value = str.trim(); value =
	 * value.replaceAll("\\s{2,}"," "); return value; }
	 * 
	 * return str;
	 */
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.nic.cmf.services.dataservices.Service#UnmarshalFormDataForMutiPartType
     * (javax.servlet.http.HttpServletRequest, java.lang.String,
     * javax.ws.rs.core.MultivaluedMap)
     */
    /*
     * public Collections UnmarshalFormDataForMutiPartType( HttpServletRequest
     * request, String domain, MultivaluedMap uriinfo) throws GenericException {
     * ArrayList<String> objectListNames = new ArrayList<String>(
     * Arrays.asList("DependentItems", "RelatedItems", "Properties",
     * "WorkflowEvents", "WorkflowEventUserMaps"));
     * log.methodEntry(this.getClass().getSimpleName() +
     * ".UnmarshalFormDataForMutiPartType()"); try {
     * 
     * Map userContext = usercontext.getUserContext(request);
     * 
     * String uploadField = "UploadField"; boolean flagForBinary = false;
     * FileItem inputFileContent = null; Map hMultiPartContent = new HashMap();
     * String entityType = "";
     * 
     * if (ServletFileUpload.isMultipartContent(request)) {
     * log.debug("Entry :: processMultipartContent"); hMultiPartContent =
     * processMultipartContent(request); } else {
     * log.debug("Entry :: processUrlCodedContent"); hMultiPartContent =
     * processUrlCodedContent(request); }
     * 
     * log.debug("Before hMultiPartContent ==>" + hMultiPartContent); entityType
     * = getValueFromhMultiPartContent( (List)
     * hMultiPartContent.get("EntityType")).toString(); FormatJson formatJson =
     * FormatJson.getInstance(); InputStream is = new
     * FileInputStream(getClass().getClassLoader()
     * .getResource("Mapping.json").getFile()); HashMap h =
     * formatJson.in(IOUtils.toString(is));
     * 
     * FormatFlatten f = FormatFlatten.getInstanceOf();
     * 
     * HashMap mapping = (HashMap) h.get(getValueFromhMultiPartContent( (List)
     * hMultiPartContent.get("EntityType")).toString()); HashMap fieldTypes =
     * (HashMap) mapping.get("typeMapping"); HashMap classInfo = (HashMap)
     * mapping.get("classInfo"); List interfaceList = (List)
     * classInfo.get("interfaceInfo");
     * 
     * Iterator i = hMultiPartContent.entrySet().iterator(); List fileItem = new
     * ArrayList(); String filepath = null; while (i.hasNext()) { Map.Entry
     * pairs = (Map.Entry) i.next(); String name = pairs.getKey().toString();
     * log.debug("Processing **********" + name);
     * log.debug("Listing File Types**********" + fieldTypes); if
     * (fieldTypes.get(name).equals("File")) { Object obj =
     * getValueFromhMultiPartContent((List) hMultiPartContent .get(name));
     * log.debug("Obj Value FilePath :::: " + obj + " :::: " + obj.toString());
     * if (obj instanceof String) { log.debug("Instance Of String ::: file");
     * filepath = obj.toString(); } else if
     * (fieldTypes.get(name).equals("File")) {
     * log.debug("Instance Of String elseeeee ::: file");
     * fileItem.add((FileItem) getValueFromhMultiPartContent((List)
     * hMultiPartContent .get(name))); i.remove(); } } if
     * (InitLowerCase(name).equals("FilePath")) { Object obj =
     * getValueFromhMultiPartContent((List) hMultiPartContent .get(name));
     * log.debug("FilePath ==> ************************ " + obj.toString()); if
     * (obj instanceof String) { filepath = obj.toString(); } }
     * 
     * if (name.endsWith("Date")) { DateUtils dUtils =
     * DateUtils.getInstanceOf(); if (!name.equals("BirthDate")) {
     * hMultiPartContent .put(name, Arrays.asList(dUtils
     * .getSolrFormattedDateByForm((String) getValueFromhMultiPartContent((List)
     * hMultiPartContent .get(name))))); } else { if
     * (!hMultiPartContent.get("BirthDate").toString() .equals("")) {
     * hMultiPartContent .put(name, Arrays.asList(dUtils
     * .getSolrFormattedDateByForm((String) getValueFromhMultiPartContent((List)
     * hMultiPartContent .get(name))))); } } }
     * 
     * } log.debug("After hMultiPartContent ==>" + hMultiPartContent);
     * log.debug("After Executing While Loop");
     * 
     * h = f.out(hMultiPartContent, mapping); log.debug("Final Hash Map ***b " +
     * h);
     * 
     * if (entityType.toLowerCase().equals("article")) { String md5Text =
     * h.get("Title").toString() + h.get("Description").toString(); h.put("Md5",
     * trimString(Utils.getInstance().getMD5(md5Text))); }
     * 
     * if (fileItem.size() > 0) { for (int k = 0; k < fileItem.size(); k++) { if
     * (null != fileItem.get(0)) {
     * 
     * log.info("api_key ==> " + apiKey + ":: authusername ==> " + userName);
     * 
     * Map hBinaryMap = null; inputFileContent = (FileItem) fileItem.get(k);
     * log.debug("File Item ::::: " + fileItem); log.debug("File Name ::::: " +
     * inputFileContent.getName()); if (inputFileContent.getSize() > 0) { if
     * (entityType.equalsIgnoreCase("Css") || entityType.equalsIgnoreCase("Js"))
     * { log.error("Inside Css/Js entitytype if condition"); String FileExtn =
     * mediaHandler .getFileSimpleName(inputFileContent .getName()); // String
     * mimeType = //
     * MimeUtil.getFirstMimeType(MimeUtil.getExtensionMimeTypes(inputFileContent
     * .getName())); // log.debug("MimeType : "+mimeType); // if (mimeType ==
     * null) { // MagicMatch match = //
     * Magic.getMagicMatch(inputFileContent.get()); // mimeType =
     * match.getMimeType(); // log.debug("MimeType : "+mimeType); // }
     * 
     * if (entityType.equalsIgnoreCase(FileExtn)) { hBinaryMap =
     * mediaHandler.binaryUpload( userContext, domain, inputFileContent,
     * entityType, h); } else { log.error("File format not allowed"); throw new
     * GenericException("ERR-DS-0007"); } } else if
     * (entityType.equalsIgnoreCase("Template")) { String fileExtn =
     * utils.getMimeInfo(utils .getMimeType(inputFileContent .getInputStream(),
     * inputFileContent.getName()), "simplename"); log.debug("file extension" +
     * fileExtn); if (fileExtn.equalsIgnoreCase("html")) { hBinaryMap =
     * mediaHandler.binaryUpload( userContext, domain, inputFileContent,
     * entityType, h); } else { log.error("File format not allowed"); throw new
     * GenericException("ERR-DS-0007"); } } else if (entityType
     * .equalsIgnoreCase("Validations") || entityType
     * .equalsIgnoreCase("AccessControlList")) { String fileExtn =
     * utils.getMimeInfo(utils .getMimeType(inputFileContent .getInputStream()),
     * "simplename"); if (fileExtn.equalsIgnoreCase("xls")) { hBinaryMap =
     * mediaHandler.binaryUpload( userContext, domain, inputFileContent,
     * entityType, h); } else { log.error("File format not allowed"); throw new
     * GenericException("ERR-DS-0007"); } } else { hBinaryMap =
     * mediaHandler.binaryUpload( userContext, domain, inputFileContent,
     * entityType, h);
     * 
     * if(entityType.equals("Media")){ List mediaCollections = (List)
     * hBinaryMap.get("collections");
     * System.out.println(mediaCollections.size()); HashMap h1 = (HashMap)
     * mediaCollections.get(0); System.out.println("Media HashMap **** "+h1); }
     * 
     * } log.debug("Before Assigning Value of hBinaryMap to h==> " + h); h =
     * (HashMap) hBinaryMap; log.debug("hBinaryMap ==> " + hBinaryMap);
     * log.debug("After Assigning Value of hBinaryMap to h==> " + h);
     * 
     * if (null != h.get("Collections") && h.get("Collections") instanceof List)
     * { List<Object> ll = new ArrayList<Object>(); for (Object eachMap : (List)
     * h .get("Collections")) {
     * 
     * HashMap h1 = new HashMap(); HashMap tmpeachMap = (HashMap) eachMap;
     * byte[] bytes = utils .getStreamAsByteArray(inputFileContent
     * .getInputStream()); if (null == tmpeachMap.get("MD5") ||
     * tmpeachMap.get("MD5").toString() .isEmpty()) tmpeachMap .put("Md5",
     * utils.getMD5(new ByteArrayInputStream( bytes))); if (null ==
     * tmpeachMap.get("SourceName") || tmpeachMap.get("SourceName")
     * .toString().isEmpty()) tmpeachMap.put("SourceName",
     * trimString(inputFileContent .getName()));
     * 
     * if (null == tmpeachMap.get("Site") || tmpeachMap.get("Site")
     * .toString().isEmpty()) tmpeachMap.put("Site", trimString(domain)); if
     * (null != interfaceList && interfaceList .contains("Seoable")) { String
     * seoUrl = getSEOURL(tmpeachMap, domain); if (null != seoUrl) { //
     * h.put("SeoUrl", // trimString(seoUrl)); tmpeachMap.put("SeoUrl",
     * trimString(seoUrl)); } } h1.put(getValueFromhMultiPartContent( (List)
     * hMultiPartContent .get("EntityType")) .toString(), tmpeachMap);
     * ll.add(h1); } h.put("Collections", ll); } else if (null ==
     * h.get("Collections")) { HashMap h1 = new HashMap(); HashMap tmpeachMap =
     * (HashMap) h .get("Collections"); byte[] bytes = utils
     * .getStreamAsByteArray(inputFileContent .getInputStream()); if (null ==
     * tmpeachMap.get("MD5") || tmpeachMap.get("MD5").toString() .isEmpty())
     * tmpeachMap.put("Md5", utils .getMD5(new ByteArrayInputStream( bytes)));
     * if (null == tmpeachMap.get("SourceName") || tmpeachMap.get("SourceName")
     * .toString().isEmpty()) tmpeachMap.put("SourceName",
     * trimString(inputFileContent .getName()));
     * 
     * if (null == tmpeachMap.get("Site") || tmpeachMap.get("Site").toString()
     * .isEmpty()) tmpeachMap.put("Site", trimString(domain)); if (null !=
     * interfaceList && interfaceList.contains("Seoable")) { String seoUrl =
     * getSEOURL(tmpeachMap, domain); if (null != seoUrl) { // h.put("SeoUrl",
     * // trimString(seoUrl)); tmpeachMap.put("SeoUrl", trimString(seoUrl)); } }
     * h1.put(getValueFromhMultiPartContent( (List) hMultiPartContent
     * .get("EntityType")).toString(), tmpeachMap); h.put("Collections", h1); }
     * 
     * // log.debug("Before Value of h ==> " + h);
     * 
     * if(entityType.equals("Media")){ filepath = (String)
     * hBinaryMap.get("returnValue");
     * 
     * }else filepath ="/"+ (String) getFieldValue(hEntityObjects
     * .get(entityType),"Site")+"/media/"+(String) getFieldValue(
     * hEntityObjects.get(entityType),"Id")+"."+(String) getFieldValue
     * (hEntityObjects.get(entityType),"MimeSimpleName") ; //
     * Site/media/Id.MimeSimpleName
     * 
     * // For checking
     * 
     * 
     * if (entityType.equals("Js") || entityType.equals("Css") ||
     * entityType.equals("Validations") ||
     * entityType.equals("AccessControlList")) {
     * 
     * h.put("SourceName", trimString(inputFileContent.getName())); }
     * 
     * 
     * } else { try { if (!h.get("SourceName").equals("")) { filepath = "/" +
     * (String) h.get("Site") + "/media/" + (String) h.get("Id") + "."; if
     * (entityType.equals("Media")) { filepath += (String) h
     * .get("MimeSimpleName");// Site/media/Id.MimeSimpleName } else if
     * (entityType.equals("Template")) { filepath += "html";//
     * Site/media/Id.MimeSimpleName } else { filepath += utils
     * .getFileExtnByFileName((String) h .get("SourceName"));//
     * Site/media/Id.MimeSimpleName } } } catch (Exception e) {
     * log.error(e.getMessage()); } } } } }
     * 
     * log.debug("File Path After Processing FilePath **** " + filepath); if
     * (!utils.isEmpty(filepath)) { h.put("FilePath", trimString(filepath)); }
     * 
     * // System.out.println("Interface List *** " + interfaceList); // if (null
     * != interfaceList && null != h.get("Collections")) { // if (null !=
     * interfaceList) { // List<Object> ll = new ArrayList<Object>(); // for
     * (Object eachCollections : (List) h.get("Collections")) { // for (Object
     * eachCollections : (List) h.get("Collections")) {
     * 
     * if (interfaceList.contains("Storable")) { h.put("Site",
     * trimString(domain)); }
     * 
     * // HashMap h1 = new HashMap(); // HashMap<String, String> eachMap =
     * (HashMap<String, String>) // eachCollections; //
     * log.debug("Before EachMap Checking------" + eachMap);
     * 
     * // if (interfaceList.contains("Seoable")) { // String seoUrl =
     * getSEOURL(h, domain); // String seoUrl = getSEOURL(eachMap, domain); //
     * if (null != seoUrl) { // h.put("SeoUrl", trimString(seoUrl)); //
     * eachMap.put("SeoUrl", trimString(seoUrl)); // } // } //
     * h1.put(getValueFromhMultiPartContent( // (List)
     * hMultiPartContent.get("EntityType")) // .toString(), eachMap); //
     * ll.add(h1); // log.debug("List------" + ll); // } // h.put("Collections",
     * ll); // }
     * 
     * // System.out.println("Final Hash Map *** " + hBinaryMap);
     * 
     * if (null == h.get("Collections")) {
     * 
     * HashMap h1 = new HashMap(); HashMap root = new HashMap();
     * h1.put(getValueFromhMultiPartContent( (List)
     * hMultiPartContent.get("EntityType")).toString(), h); //
     * log.debug("*********************111" + h1);
     * 
     * root.put("Collections", h1); //
     * log.debug("********************* rooooooooot222" + root); h = root; //
     * log.debug("*********************222" + h);
     * 
     * } // System.out.println("Final Hash Map *** " + h);
     * log.debug("Final Hash Map *** " + h);
     * 
     * // log.debug("Entity Type Checking------------" + h); //
     * root.put("Collections", h1); // root.put("Collections", h); // String x =
     * (String) FormatXml.getInstance().out(root); String x = (String)
     * FormatXml.getInstance().out(h); // log.debug("XXXXXXXXXXXXXX:::" + x);
     * Collections collections = Utils.getInstance()
     * .getStringXmlAsCollections(x);
     * 
     * log.debug("----------->collections<------" +
     * collections.getCollection());
     * 
     * // System.out.println(x); return collections; // return null; } catch
     * (GenericException e) { e.printStackTrace(); log.error(e.toString());
     * throw e; } catch (Exception e) { e.printStackTrace();
     * log.error(e.toString()); throw new GenericException("ERR-DS-0000",
     * this.getClass() .getSimpleName() + ":UnmarshalFormDataForMutiPartType()",
     * e); } }
     */
    /*
     * (non-Javadoc)
     * 
     * @see in.nic.cmf.services.dataservices.Service#getUploadedFileName()
     */

    /*
     * public String getUploadedFileName() { return uploadedFileName; }
     */

    /**
     * Sets the uploaded file name.
     * 
     * @param returnValue
     *            the new uploaded file name
     */
    /*
     * public void setUploadedFileName(String returnValue) { uploadedFileName =
     * returnValue; }
     */

    /**
     * Gets the marshall.
     * 
     * @param collections
     *            the collections
     * @return the marshall
     * @throws GenericException
     *             the generic exception
     */
    /*
     * private void getMarshall(Collections collections) throws GenericException
     * { log.methodEntry(this.getClass().getSimpleName() + ".getMarshall()");
     * try { JAXBContext jc = JAXBContext.newInstance(Collections.class);
     * javax.xml.bind.Marshaller m = jc.createMarshaller();
     * m.marshal(collections, System.out);
     * log.methodExit(this.getClass().getSimpleName() + ".getMarshall()"); }
     * catch (Exception e) { System.out.println(e); log.error(e.getMessage());
     * throw new GenericException("ERR-DS-0000", this.getClass()
     * .getSimpleName() + ":getMarshall()", e); } }
     */

    /**
     * Inits the lower case.
     * 
     * @param value
     *            the value
     * @return the string
     * @throws GenericException
     *             the generic exception
     */
    public String InitLowerCase(String value) throws GenericException {
	log.methodEntry(this.getClass().getSimpleName() + ".InitLowerCase()");
	try {
	    String retVal = value.substring(0, 1).toLowerCase()
		    + value.substring(1);
	    log.methodExit(this.getClass().getSimpleName() + ".InitLowerCase()");
	    return retVal;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":InitLowerCase()", e);
	}
    }

    /**
     * Gets the sEOURL.
     * 
     * @param h
     *            the h
     * @param domain
     *            the domain
     * @return the sEOURL
     * @throws GenericException
     *             the generic exception
     */
    private String getSEOURL(Map h, String domain) throws GenericException {
	log.methodEntry(this.getClass().getSimpleName()
		+ ".getSEOURL(Map, String)");
	String seoUrl = null;
	try {
	    String title = h.get("Title").toString().trim();
	    String iaPath = h.get("AssociatedIAPath").toString().trim();

	    title = title.replaceAll("[^a-zA-Z0-9 ]+", "");
	    seoUrl = title.trim() + "-" + h.get("EntityType").toString().trim()
		    + "-" + h.get("Id").toString().trim() + ".php";
	    seoUrl = getSeoUrlWithIaPath(iaPath, domain, seoUrl);
	    log.methodExit(this.getClass().getSimpleName()
		    + ".getSEOURL(Map, String)");
	    return seoUrl;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":getSEOURL()", e);
	}
    }

    /**
     * Gets the sEOURL.
     * 
     * @param setterObject
     *            the setter object
     * @param domain
     *            the domain
     * @return the sEOURL
     * @throws GenericException
     *             the generic exception
     */
    /*
     * private String getSEOURL(Object setterObject, String domain) throws
     * GenericException { log.methodEntry(this.getClass().getSimpleName() +
     * ".getSEOURL(Object, String)"); String seoUrl = null; try { String title =
     * ((String) getFieldValue(setterObject, "Title")) .trim(); String iaPath =
     * ((String) getFieldValue(setterObject, "AssociatedIAPath")).trim(); title
     * = title.replaceAll("[^a-zA-Z0-9 ]+", ""); seoUrl = title + "-" +
     * ((String) getFieldValue(setterObject, "EntityType")) .trim() + "-" +
     * ((String) getFieldValue(setterObject, "Id")).trim() + ".php"; seoUrl =
     * getSeoUrlWithIaPath(iaPath, domain, seoUrl);
     * log.methodExit(this.getClass().getSimpleName() +
     * ".getSEOURL(Object, String)");
     * 
     * return seoUrl; } catch (GenericException e) { log.error(e.getMessage());
     * throw e; } catch (Exception e) { log.error(e.getMessage()); throw new
     * GenericException("ERR-DS-0000", this.getClass() .getSimpleName() +
     * ":getSEOURL()", e); } }
     */

    public static String getSeoUrlWithIaPath(String path, String domain,
	    String seoUrl) {
	String seoUrlWithIaPath = "";
	seoUrlWithIaPath = "http://" + domain + "/"
		+ seoUrl.replace(" ", "-").toLowerCase();
	if (null != path) {
	    String iaPath = getSeoUrlIaPath(path.toLowerCase());
	    if (path.equals("") || path.equals("/")) {
		seoUrlWithIaPath = "http://" + domain + "/"
			+ seoUrl.replace(" ", "-").toLowerCase();
	    } else {
		seoUrlWithIaPath = "http://" + domain + iaPath + "/"
			+ seoUrl.replace(" ", "-").toLowerCase();
	    }
	}
	seoUrlWithIaPath = seoUrlWithIaPath.replaceAll(" ", "_");
	return seoUrlWithIaPath;
    }

    public static String getSeoUrlIaPath(String path) {
	if (path.indexOf("/home") == 0) {
	    path = path.substring(5, path.length());
	}
	return path;
    }

    /**
     * Gets the field value.
     * 
     * @param setterObject
     *            the setter object
     * @param name
     *            the name
     * @return the field value
     * @throws GenericException
     *             the generic exception
     */
    /*
     * public Object getFieldValue(Object setterObject, String name) throws
     * GenericException { log.methodEntry(this.getClass().getSimpleName() +
     * ".getFieldValue()"); try {
     * DomainFactory.getInstance().setPackageName("in.nic.cmf.domain."); Method
     * invokeMeth = DomainFactory.getInstance() .getMethodInstanceOf(
     * setterObject.getClass().getSimpleName(), "get" + name);
     * log.methodExit(this.getClass().getSimpleName() + ".getFieldValue()");
     * return invokeMeth.invoke(setterObject); } catch (Exception e) {
     * log.error(e.getMessage()); throw new GenericException("ERR-DS-0000",
     * this.getClass() .getSimpleName() + ".getFieldValue()", e); } }
     */
    /**
     * String to map.
     * 
     * @param input
     *            the input
     * @return the multivalued map impl
     * @throws GenericException
     *             the generic exception
     */
    public static MultivaluedMapImpl stringToMap(String input)
	    throws GenericException {
	MultivaluedMapImpl map = new MultivaluedMapImpl();
	if (input != null) {
	    String[] nameValuePairs = input.split("&");
	    for (String nameValuePair : nameValuePairs) {
		String[] nameValue = nameValuePair.split("=");
		try {
		    map.add(URLDecoder.decode(nameValue[0], "UTF-8")
			    .toLowerCase(),
			    nameValue.length > 1 ? URLDecoder.decode(
				    nameValue[1], "UTF-8") : "");
		} catch (UnsupportedEncodingException e) {
		    throw new GenericException("ERR-DMS-0004",
			    "Unsupported Encoding Exception", input, e);
		}
	    }
	}
	return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.nic.cmf.services.dataservices.Service#mapToString(com.sun.jersey.core
     * .util.MultivaluedMapImpl)
     */
    public String mapToString(MultivaluedMapImpl map) throws GenericException {
	StringBuilder stringBuilder = new StringBuilder();
	try {
	    for (String key : map.keySet()) {
		if (stringBuilder.length() > 0) {
		    stringBuilder.append("&");
		}
		String value = "";
		if (map.get(key).size() == 1) {
		    value = getStringFromList(map.get(key));
		    stringBuilder.append((key != null ? URLEncoder.encode(key,
			    "UTF-8") : ""));
		    stringBuilder.append("=");
		    stringBuilder.append(value != null ? URLEncoder.encode(
			    value, "UTF-8") : "");

		} else {
		    int i = 0;
		    for (Object e : map.get(key)) {
			stringBuilder.append((key != null ? URLEncoder.encode(
				key, "UTF-8") : ""));
			stringBuilder.append("=");
			stringBuilder.append(e != null ? URLEncoder.encode(
				(String) e, "UTF-8") : "");
			if (i < (map.get(key).size() - 1))
			    stringBuilder.append("&");
			i++;
		    }
		}

	    }
	    return stringBuilder.toString();
	} catch (UnsupportedEncodingException e) {
	    throw new GenericException("ERR-DS-0004",
		    "Unsupported Encoding Exception", e);
	}
    }

    /**
     * Gets the string from list.
     * 
     * @param list
     *            the list
     * @return the string from list
     */
    private String getStringFromList(Object list) {
	LinkedList listvalues = (LinkedList) list;
	for (Object e : listvalues) {
	    return (String) e;
	}
	return null;
    }

    /**
     * Apply default params.
     * 
     * @param list
     *            the list
     * @param domain
     *            the domain
     * @return the multivalued map impl
     * @throws GenericException
     *             the generic exception
     */
    public MultivaluedMapImpl applyDefaultParams(MultivaluedMapImpl list,
	    String domain) throws GenericException {
	log.methodEntry(this.getClass().getSimpleName()
		+ ".applyDefaultParams()");
	try {
	    if (!list.containsKey("orderbydir"))
		list.add("orderByDir", "desc");

	    list.add("site", domain);
	    /*
	     * if(!list.containsKey("orderBy"))
	     * list.put("orderBy","CreatedDate");
	     */
	    log.methodExit(this.getClass().getSimpleName()
		    + ".applyDefaultParams()");
	    return list;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ".applyDefaultParams()", domain, e);
	}
    }

    /**
     * Checks if is empty.
     * 
     * @param value
     *            the value
     * @return true, if is empty
     */
    private boolean isEmpty(String value) {
	if (value == null || value.isEmpty()) {
	    return true;
	}
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.nic.cmf.services.dataservices.Service#applyExcludeParams(com.sun.jersey
     * .core.util.MultivaluedMapImpl)
     */
    public MultivaluedMapImpl applyExcludeParams(MultivaluedMapImpl list)
	    throws GenericException {
	log.methodEntry(this.getClass().getSimpleName()
		+ ".applyExcludeParams()");
	System.out.println("Inside applyExcludeParams");

	try {
	    System.out.println(this.excludeParams);
	    java.util.Iterator<String> items = this.excludeParams.iterator();
	    while (items.hasNext()) {
		list.remove(items.next());
	    }
	    log.methodExit(this.getClass().getSimpleName()
		    + ".applyExcludeParams()");
	    return list;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":applyExcludeParams()", e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * in.nic.cmf.services.dataservices.Service#applyModifyParams(com.sun.jersey
     * .core.util.MultivaluedMapImpl)
     */

    public MultivaluedMapImpl applyModifyParams(MultivaluedMapImpl mParams)
	    throws GenericException {
	System.out.println("Inside applyModifyParams");
	log.methodEntry(this.getClass().getSimpleName()
		+ ".applyModifyParams()");
	try {
	    Set set = modifyParams.entrySet();
	    Iterator iter = set.iterator();
	    while (iter.hasNext()) {
		Map.Entry entry = (Map.Entry) iter.next();
		/*
		 * if(entry.getValue().getClass().getSimpleName().equalsIgnoreCase
		 * ("arraylist")){
		 * log.debug("inside arraylist::::::"+entry.getValue()); }
		 */
		if (mParams.containsKey(entry.getKey())) {
		    String value = getStringFromList(mParams
			    .get(entry.getKey()));
		    mParams.remove(entry.getKey());
		    mParams.add(entry.getValue().toString(), value);
		}
	    }
	    log.methodExit(this.getClass().getSimpleName()
		    + ".applyModifyParams()");
	    System.out.println("After :::: " + mParams);
	    return mParams;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":applyModifyParams()", e);
	}
    }
}
