package in.nic.cmf.sitegeneration;

import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.DynamicAuthentication;
import in.nic.cmf.xlstoxml.xlstoxml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteGenService {
    private static HashMap<String, SiteGenService> hashSiteGenService = new HashMap<String, SiteGenService>();
    private String oldDomain;
    private PropertiesUtil proputil;
    public LogTracer log;
    private String newDomainName = "";
    ServiceClientImpl serviclientImpl = null;
    FormatXml formatXml = null;
    FormatFlatten formatFlattern = null;
    private Map userContextMap = new HashMap();
    HashMap<String, Object> domainEntitycollectionsMap = null;

    private SiteGenService(String domain) {
	oldDomain = domain;
	proputil = PropertiesUtil.getInstanceof(domain, "sitegeneration");
	formatXml = FormatXml.getInstanceof(domain);
	formatFlattern = FormatFlatten.getInstanceOf(domain, true);
	setLogger(domain);
    }

    private void setLogger(String domain) {
	log = new LogTracer(domain, "sitegeneration");
    }

    public static SiteGenService getInstanceof(String domain) {
	if (!hashSiteGenService.containsKey(domain)) {
	    hashSiteGenService.put(domain, new SiteGenService(domain));
	}
	return hashSiteGenService.get(domain);
    }

    public Map<String, Map<String, Object>> post(String domain, String entity,
	    Map<String, Map<String, Object>> parameters)
	    throws GenericException {
	int statusCode = 0;
	log.methodEntry("POST entry...");
	Map<String, Map<String, Object>> result = null;
	HashMap<String, Object> collectionsMap = new HashMap<String, Object>();
	domainEntitycollectionsMap = new HashMap<String, Object>();
	HashMap domainEntitycollectionsMapRoot = new HashMap<String, Object>();
	List<Map<String, Object>> collectionsMapList = new ArrayList();
	try {
	    log.debug("incomping parameters are :"
		    + parameters.get("input").get("queryString"));
	    Map<String, Object> map = formatXml.in((String) parameters.get(
		    "input").get("content"));
	    for (Map.Entry<String, Object> entry : map.entrySet()) {
		log.debug("entry>>>>>>>>:" + entry.getKey());
		Map collectionMap = new HashMap();
		if (map.get((String) entry.getKey()) instanceof List) {
		    log.debug("key:" + entry.getKey() + "value:"
			    + entry.getValue());
		    List<Map<String, Object>> collectionList = (List) map
			    .get(entry.getKey().toString());
		    log.debug("collectionlist:" + collectionList);
		    for (Map<String, Object> singleMap : collectionList) {
			try {
			    collectionsMapList.add(getCollections(
				    getFlatternCollection(singleMap),
				    collectionMap));
			} catch (GenericException e) {
			    throw e;
			}
		    }
		    collectionsMap.put("Collections", collectionsMapList);
		} else if (map.get(entry.getKey().toString()) instanceof Map) {
		    collectionsMap.put(
			    "Collections",
			    getCollections(getFlatternCollection(map),
				    collectionMap));

		}

		domainEntitycollectionsMapRoot.put("Collections",
			domainEntitycollectionsMap);

		writeXmlToFile(domainEntitycollectionsMapRoot, collectionsMap);

		result = postCollectionsToAppflow(parameters,
			domainEntitycollectionsMapRoot);
		log.debug("after posting domainEntity to appflow,the result is "
			+ result);
		if (getresponseMap(result) == 200) {
		    log.debug("DomainEntity posted sucessfully");
		    log.debug("CmsUSerpRofile and Role posting to appflow ::: "
			    + parameters.get("input").get("queryString"));
		    String queryString = (String) parameters.get("input").get(
			    "queryString");
		    queryString = queryString.replace("&duplication=true", "");
		    parameters.get("input").put("queryString", queryString);
		    result = postCollectionsToAppflow(parameters,
			    collectionsMap);
		    log.debug("after posting CmsUSerpRofile and Role, the result is "
			    + result);
		    statusCode = getresponseMap(result);
		}

		log.info("Result after Posting to Appflow" + result);

	    }
	} catch (GenericException e) {
	    log.error("ERROR IN post method" + e.getMessage());
	    e.printStackTrace();
	    throw e;
	}

	return buildResponse(domain, parameters, statusCode);

    }

    private void writeXmlToFile(HashMap domainEntitycollectionsMapRoot,
	    HashMap<String, Object> collectionsMap) {
	try {
	    String destinationPath = "/opt/cmf/domains" + "/" + newDomainName
		    + "/resources/" + "siteGenOutPut-DomainEntity.xml";
	    String destination = "/opt/cmf/domains" + "/" + newDomainName
		    + "/resources/" + "siteGenOutPut-UserProandRole.xml";
	    FileWriter fstream = new FileWriter(destinationPath);
	    BufferedWriter out = new BufferedWriter(fstream);
	    String data = (String) formatXml.getInstanceof(newDomainName).out(
		    domainEntitycollectionsMapRoot);
	    System.out.println(data);
	    out.write(data);
	    out.flush();
	    fstream = new FileWriter(destination);
	    out = new BufferedWriter(fstream);
	    data = (String) formatXml.getInstanceof(newDomainName).out(
		    collectionsMap);

	    out.write(data);
	    out.flush();
	    out.close();
	} catch (IOException e) {

	}

    }

    private int getresponseMap(Map<String, Map<String, Object>> result) {
	String content = "";
	int statusCode = 0;
	if (result.get("output").containsKey("statusCode")) {
	    if (result.get("output").get("statusCode") instanceof String) {
		statusCode = Integer.parseInt(((String) result.get("output")
			.get("statusCode")));
	    } else if (result.get("output").get("statusCode") instanceof Integer) {
		statusCode = (Integer) result.get("output").get("statusCode");
	    }
	}
	return statusCode;
    }

    private Map<String, Map<String, Object>> postCollectionsToAppflow(
	    Map<String, Map<String, Object>> parameters,
	    Map<String, Object> paramMap) throws GenericException {
	try {
	    parameters.get("input").put("content",
		    formatXml.out((HashMap) paramMap));
	    if (!userContextMap.isEmpty()) {
		Map<String, Object> auth = (Map<String, Object>) userContextMap
			.get("Collections");
		Map authRoot = new HashMap();
		Map authenticationMap = (Map) auth.get("Authentication");
		authRoot.put("api_key", authenticationMap.get("ApiKey"));
		authRoot.put("aclrole", authenticationMap.get("AclRole"));
		authRoot.put("authusername",
			authenticationMap.get("AuthUserName"));
		parameters.get("input").put("userContext", authRoot);
	    }
	    System.out.println("Prameter>>>>>>>>>>>>>>" + parameters);
	    serviclientImpl = ServiceClientImpl.getInstance(newDomainName,
		    "appflow");
	    // log.debug("before Posting to appflow" + parameters);
	    return serviclientImpl.add(newDomainName, "", parameters);

	} catch (GenericException e) {
	    throw e;
	}
    }

    private Map<String, Object> getFlatternCollection(
	    Map<String, Object> unflatternMap) throws GenericException {
	HashMap<String, Object> flatternCollection = new HashMap<String, Object>();
	try {
	    flatternCollection = formatFlattern.in(unflatternMap);
	    return flatternCollection;
	} catch (GenericException e) {
	    log.debug("ERROr in flattern collections" + e.getErrorMessage());
	    e.printStackTrace();
	}
	return flatternCollection;
    }

    public Map<String, Map<String, Object>> buildResponse(String domain,
	    Map<String, Map<String, Object>> parameters, int statusCode)
	    throws GenericException {
	HashMap<String, Object> response = new HashMap<String, Object>();
	if (statusCode == 200) {
	    response.put("statusCode", "200");
	    response.put("content", "<Status>Success</Status>");
	} else {
	    response.put("statusCode", "400");
	    response.put("content", "<Status>Failure</Status>");
	}

	parameters.put("output", response);
	return parameters;
    }

    private Map getCollections(Map<String, Object> singleMap, Map collectionMap)
	    throws GenericException {
	List roleLists = new ArrayList();
	try {
	    Map flatCollMap = (Map) singleMap.get("Collections");
	    Map flatternCollection = (Map) flatCollMap.get("Domain");
	    String roleName = (String) flatternCollection.get("RoleName");
	    String superAdminRole = (String) flatternCollection
		    .get("SuperAdminRole");
	    String domainEntity = "";
	    String UiTabs = "";
	    boolean isSuperAdmin = false;
	    newDomainName = (String) flatternCollection.get("DomainName");
	    Map<String, Object> roleRootMap = new HashMap();
	    List cmsUserprofileLists = new ArrayList();
	    List<String> roleList = Arrays.asList(roleName.split(","));
	    for (String role : roleList) {
		Map<String, Object> roleMap = new HashMap();
		roleMap.putAll(getCommonFields("Role",
			(String) flatternCollection.get("SuperAdminUserName"),
			newDomainName));
		roleMap.put("RoleName", role);
		roleMap.put("ParentRoleId", "");
		roleMap.put("ParentRoleName", "");
		String userName = role.toLowerCase();
		String password = "Password@1";
		if (role.equalsIgnoreCase(superAdminRole)) {
		    userName = (String) flatternCollection
			    .get("SuperAdminUserName");
		    password = (String) flatternCollection
			    .get("SuperAdminPassword");
		    isSuperAdmin = true;
		    domainEntity = (String) flatternCollection
			    .get("DomainEntity");
		    UiTabs = (String) flatternCollection.get("UiTab");
		    xlstoxml xls = new xlstoxml(newDomainName);
		    domainEntitycollectionsMap.put("DomainEntity", xls
			    .getReponse().get("Collections"));
		} else {
		    domainEntity = proputil.get(role + "-DomainEntities");
		    UiTabs = proputil.get(role + "-UiTabs");
		}
		roleMap.put("isTopLevel", isSuperAdmin);
		roleMap.put("IsSuperAdmin", isSuperAdmin);
		Map<String, Object> UiTabsMap = new HashMap();
		UiTabsMap.put("UiTab", getList(UiTabs));
		roleMap.put("UiTabs", UiTabsMap);
		Map<String, Object> domainEntitiesMap = new HashMap();
		domainEntitiesMap.put("DomainEntity", getList(domainEntity));
		roleMap.put("DomainEntities", domainEntitiesMap);
		if (!role.equalsIgnoreCase("DomainCreator")) {
		    cmsUserprofileLists.add(getCmsUseprofileCollection(
			    userName, password, newDomainName,
			    (String) flatternCollection
				    .get("SuperAdminUserName"), role));
		} else {
		    getCmsUseprofileCollection(userName, password,
			    newDomainName,
			    (String) flatternCollection
				    .get("SuperAdminUserName"), role);

		}
		roleLists.add(roleMap);
	    }
	    collectionMap.put("CmsUserProfile", cmsUserprofileLists);
	    log.debug("ROLE map is:::::::::::::::" + roleLists);
	    collectionMap.put("Role", roleLists);

	} catch (GenericException e) {
	    log.error("ERROR IN getCollections method" + e.getMessage());
	    e.printStackTrace();
	    throw e;

	} catch (IOException e) {
	    throw new GenericException(newDomainName, "ERR-SG-0005");

	}
	return collectionMap;
    }

    private List<String> getList(String value) throws GenericException {
	try {
	    List<String> valueList = new ArrayList();
	    String valueArray[] = value.split(",");
	    for (String val : valueArray) {
		String[] entityName = val.split("-");
		valueList.add(entityName[0]);
	    }

	    return valueList;
	} catch (Exception e) {
	    throw new GenericException(newDomainName, "ERR-SG-0004");
	}
    }

    private Map<String, Object> getCmsUseprofileCollection(String userName,
	    String password, String domainName, String createdBy, String role)
	    throws GenericException {
	Map<String, Object> cmsUserProfileMap = new HashMap();
	try {
	    cmsUserProfileMap.putAll(getCommonFields("CmsUserProfile",
		    createdBy, domainName));
	    cmsUserProfileMap.put("Email", userName + "@" + domainName);
	    cmsUserProfileMap.put("UserName", userName);
	    cmsUserProfileMap.put("Password", convertMD5format(password));
	    cmsUserProfileMap.put("IsActive", "true");
	    cmsUserProfileMap.put("UserRole", role);
	    if (role.equalsIgnoreCase("DomainCreator")) {
		getUserContext(cmsUserProfileMap, domainName);

	    }

	} catch (GenericException e) {
	    log.error("ERROR IN getCmsUseprofileCollection method"
		    + e.getMessage());
	    throw e;
	}
	return cmsUserProfileMap;
    }

    private void getUserContext(Map<String, Object> cmsUserProfileMap,
	    String domainName) throws GenericException {
	try {
	    DynamicAuthentication dyn = new DynamicAuthentication(
		    (String) cmsUserProfileMap.get("UserName"),
		    (String) cmsUserProfileMap.get("Password"), newDomainName);
	    log.debug("UserName" + cmsUserProfileMap.get("UserName"));
	    log.debug("Password" + cmsUserProfileMap.get("Password"));
	    Map<String, Object> userContext = new HashMap();
	    if (dyn.autoSignin()) {
		String authStatus = dyn.getAuthentication();
		userContext = FormatXml.getInstanceof(newDomainName).in(
			authStatus);
		userContextMap = userContext;
		System.out.println("USER context" + userContext);
	    } else {
		createLdapUserFordomainCreatorRole(cmsUserProfileMap,
			domainName);
	    }
	} catch (GenericException e) {
	    throw e;
	}
    }

    private void createLdapUserFordomainCreatorRole(
	    Map<String, Object> cmsUserProfileMap, String newDomain)
	    throws GenericException {
	try {
	    serviclientImpl = ServiceClientImpl.getInstance(newDomain, "ldap");
	    System.out.println(serviclientImpl.getService());
	    HashMap<String, Object> collectionMap = new HashMap();
	    HashMap<String, Object> cmsUserProfileRootMap = new HashMap();
	    cmsUserProfileRootMap.put("CmsUserProfile", cmsUserProfileMap);
	    collectionMap.put("Collections", cmsUserProfileRootMap);
	    Map<String, Map<String, Object>> parameters = new HashMap();
	    Map<String, Object> content = new HashMap();
	    Map<String, Object> userContext = new HashMap();
	    content.put("content", formatXml.out(collectionMap));
	    content.put("userContext", userContext);
	    parameters.put("input", content);
	    log.debug("before create ldap user");
	    Map<String, Map<String, Object>> ldapResultMap = serviclientImpl
		    .add(newDomainName, "CmsUserProfile", parameters);
	    log.debug("after create ldap user");
	    String ldapStatus = (String) ldapResultMap.get("output").get(
		    "statusCode");
	    System.out.println("STATUS" + ldapStatus);
	    if (ldapStatus.equalsIgnoreCase("200")) {
		log.info("auth sucess");
		getUserContext(cmsUserProfileMap, newDomain);
		serviclientImpl = ServiceClientImpl.getInstance(newDomain,
			"searchengine");
		Map<String, Map<String, Object>> seResultMap = serviclientImpl
			.add(newDomainName, "CmsUserProfile", parameters);
		serviclientImpl = ServiceClientImpl.getInstance(newDomain,
			"dms");
		Map<String, Map<String, Object>> dmsResultMap = serviclientImpl
			.add(newDomainName, "CmsUserProfile", parameters);
		log.info("USERCONTEXT result ::::::::" + userContextMap
			+ "  SearchEngine result>>>>>>" + seResultMap
			+ "Dms result is>>>>>" + dmsResultMap);
		System.out.println("USER context" + userContext);
	    }

	} catch (GenericException e) {
	    log.error("ERROR IN createLdapUserFordomainCreatorRole method"
		    + e.getMessage());
	    throw e;
	}
    }

    private Map<String, String> getCommonFields(String entityType,
	    String createdBy, String domainName) throws GenericException {
	Map<String, String> commonFieldsMap = new HashMap();
	try {

	    DateFormat dateFormat = new SimpleDateFormat(
		    "yyyy-MM-dd'T'HH:mm:ss'Z'");
	    commonFieldsMap.put("Id", Uniqueid.getId());
	    commonFieldsMap.put("EntityType", entityType);
	    commonFieldsMap.put("CreatedDate", dateFormat.format(new Date())
		    .toString());
	    commonFieldsMap.put("LastModifiedDate",
		    dateFormat.format(new Date()).toString());
	    commonFieldsMap.put("CreatedBy", createdBy);
	    commonFieldsMap.put("LastModifiedBy", createdBy);
	    commonFieldsMap.put("Site", domainName);
	    commonFieldsMap.put("Version", "1.0");
	} catch (GenericException e) {
	    throw e;
	}
	return commonFieldsMap;

    }

    private String convertMD5format(String password) throws GenericException {
	try {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(password.getBytes());
	    byte byteData[] = md.digest();
	    // convert the byte to hex format method 1
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++) {
		sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
			.substring(1));
	    }
	    return sb.toString();
	} catch (NoSuchAlgorithmException e) {
	    throw new GenericException(newDomainName, "");
	}
    }
}
