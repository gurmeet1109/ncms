/** Created by kavitha on 11th Sep 2011
 * Purpose : For each and every Master entity POST/DELETE this thread gets executed.
 */
/*
 package in.nic.cmf.dataservices;

 import in.nic.cmf.convertors.Convertor;
 import in.nic.cmf.convertors.FormatXml;
 import in.nic.cmf.domain.Collections;
 import in.nic.cmf.generate.GenerateRules;
 import in.nic.cmf.logger.LogTracer;
 import in.nic.cmf.properties.PropertiesUtil;
 import in.nic.cmf.util.DynamicAuthentication;
 import in.nic.cmf.util.UserContext;

 import java.util.HashMap;
 import java.util.Map;

 *//**
 * The Class ThreadInvoke.
 */
/*
 public class ThreadInvoke implements Runnable {

 *//** The log. */
/*
 private LogTracer log = null;

 *//** The properties. */
/*
 private PropertiesUtil proputil = new PropertiesUtil("dataservices");

 Helper helper = Helper.getInstance();

 *//**
 * Instantiates a new thread invoke.
 * 
 * @param domainName
 *            the domain name
 * @param entityType
 *            the entity type
 */
/*
 public ThreadInvoke(String domainName, String entityType) {
 log = new LogTracer("DataServicesTraceLogger", true, true, true, true,
 true);
 // properties = ResourceBundle.getBundle("config");
 log.methodEntry("ThreadInvoke constr entry.param :" + domainName
 + entityType);
 this.domainName = domainName;
 this.entityType = entityType;
 log.debug("ThreadInvoke constr exit");
 }

 public ThreadInvoke(String domainName, String entityType,
 Collections collections) {
 log = new LogTracer("DataServicesTraceLogger", true, true, true, true,
 true);
 // properties = ResourceBundle.getBundle("config");
 log.methodEntry("ThreadInvoke constr entry.param :" + domainName
 + entityType);
 this.domainName = domainName;
 this.entityType = entityType;
 this.collections = collections;
 log.debug("ThreadInvoke constr exit");
 }

 @Override
 public void run() {
 try {
 log.methodEntry("Run thread entry. going for sleep.");
 if (generateMasters()) {
 log.debug("generateMasters successfully executed.");
 } else {
 log.debug("generate Masters are not successful.");
 }
 log.methodExit("Run thread exit");
 } catch (Exception ex) {

 }
 }

 *//**
 * Generate masters.
 * 
 * @return true, if successful
 */
/*
 private boolean generateMasters() {
 boolean result = false;
 try {
 log.methodEntry("generateMasters entry.");
 GenerateRules generate = new GenerateRules();
 log.debug("before calling generate method" + domainName
 + this.entityType.toLowerCase());

 * if(this.entityType.toLowerCase().equals("unique")){
 * log.debug("inside unique start"); result =
 * generate.generateForValidations(domainName, entityType,
 * collections,getUserContext(),helper.getContentFromResource());
 * log.debug("inside unique end"); }else{

 do {
 log.debug("inside do");
 int duration = Integer.parseInt(proputil
 .getProperty("duration"));
 Thread.sleep(duration);
 result = generate.generate(domainName,
 this.entityType.toLowerCase(), getUserContext());
 log.debug("inside do result:" + result);
 } while (false);
 // }
 log.methodExit("generateMasters exit. result:" + result);
 } catch (Exception ex) {
 log.debug("ThreadInvoke.generateMasters - exception:"
 + ex.getMessage());
 }
 return result;
 }




 *//**
 * Gets the user context.
 * 
 * @return the user context
 */
/*
 public Map getUserContext() {
 log.debug("before getting dynamic authentication details."
 + (String) proputil.getProperty("role") + "; domain:"
 + domainName);
 DynamicAuthentication dyauth = new DynamicAuthentication(
 (String) proputil.getProperty("role"), domainName);
 log.debug("after dynamic auth instance creation");
 Map userContext = new HashMap();
 if (dyauth.autoSignin()) {
 log.debug("autosigin is true");
 String auth = dyauth.getAuthentication();
 log.debug("result : " + auth);
 Convertor xmlconvertor = FormatXml.getInstance();
 userContext = UserContext.getInstance().getUserContext(
 (HashMap<String, Object>) (xmlconvertor.in(auth))
 .get("Authentication"));
 log.debug("User Context => " + userContext);
 } else {
 log.debug("autosignin is false.");
 }
 return userContext;
 }


 * public String getContentFromResource(){ String jsonTxt = ""; try { URL
 * domainURI = getClass().getClassLoader().getResource("unique.json");
 * String jsonPath = domainURI.getFile(); InputStream iStream; iStream = new
 * FileInputStream(jsonPath); jsonTxt = IOUtils.toString(iStream); } catch
 * (Exception ex) { log.debug("In exception."+ex.getMessage()); } return
 * jsonTxt; }


 *//**
 * Gets the domain name.
 * 
 * @return the domain name
 */
/*
 public String getDomainName() {
 return domainName;
 }

 *//**
 * Sets the domain name.
 * 
 * @param domainName
 *            the new domain name
 */
/*
 public void setDomainName(String domainName) {
 this.domainName = domainName;
 }

 *//**
 * Gets the entity type.
 * 
 * @return the entity type
 */
/*
 public String getEntityType() {
 return entityType;
 }

 *//**
 * Sets the entity type.
 * 
 * @param entityType
 *            the new entity type
 */
/*
 public void setEntityType(String entityType) {
 this.entityType = entityType;
 }

 *//** The entity type. */
/*
 private String entityType = "";

 *//** The domain name. */
/*
 * private String domainName = "";
 * 
 * private Collections collections = new Collections();
 * 
 * public Collections getCollections() { return collections; }
 * 
 * public void setCollections(Collections collections) { this.collections =
 * collections; }
 * 
 * }
 */