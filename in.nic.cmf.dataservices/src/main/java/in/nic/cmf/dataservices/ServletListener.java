/**
 * Created by kavitha on 11th Sep 2011
 * Purpose : While executing dataservice, all the master data has to be generated based on timer.
 */
/*
 package in.nic.cmf.dataservices;

 import in.nic.cmf.convertors.Convertor;
 import in.nic.cmf.convertors.ConvertorUtils;
 import in.nic.cmf.convertors.FormatXml;
 import in.nic.cmf.generate.GenerateRules;
 import in.nic.cmf.logger.LogTracer;
 import in.nic.cmf.properties.PropertiesUtil;
 import in.nic.cmf.util.DynamicAuthentication;
 import in.nic.cmf.util.UserContext;

 import java.util.Arrays;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Timer;
 import java.util.TimerTask;

 import javax.servlet.ServletContextEvent;
 import javax.servlet.ServletContextListener;

 *//**
 * The listener interface for receiving servlet events. The class that is
 * interested in processing a servlet event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addServletListener<code> method. When
 * the servlet event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see ServletEvent
 */
/*
 public class ServletListener implements ServletContextListener {

 *//** The properties. */
/*
 private PropertiesUtil proputil = new PropertiesUtil("dataservices");
 *//** The log. */
/*
 * private LogTracer log = null; Helper helper = Helper.getInstance();
 * 
 * public void contextInitialized(ServletContextEvent arg0) { log = new
 * LogTracer("DataServicesTraceLogger", true, true, true, true, true);
 * log.methodEntry("ServletListener.contextInitialized entry"); int delay =
 * Integer.parseInt((String) proputil.getProperty("duration")); int period =
 * Integer.parseInt((String) proputil.getProperty("period")); final Timer timer
 * = new Timer(); log.debug("before fixing timer: Delay is " + delay +
 * ";period is " + period); timer.scheduleAtFixedRate(new TimerTask() {
 * 
 * @Override public void run() { for (String eachDomain : getDomain()) {
 * log.debug("before getting dynamic authentication details." + (String)
 * proputil.getProperty("role") + "domain:" + eachDomain); DynamicAuthentication
 * dynamic = new DynamicAuthentication( (String) proputil.getProperty("role"),
 * eachDomain); log.debug("after creating dynamic instance"); Map userContext =
 * new HashMap(); if (dynamic.autoSignin()) { log.debug("autosigin is true");
 * String dyAuth = dynamic.getAuthentication(); log.debug("result:" + dyAuth);
 * Convertor xmlconvertor = FormatXml.getInstance(); userContext =
 * UserContext.getInstance().getUserContext( (HashMap<String, Object>)
 * xmlconvertor.in( dyAuth).get("Authentication")); }
 * log.debug("usercontext is : " + userContext); triggerGenerateAll(eachDomain,
 * userContext); log.debug("after trigger generate"); HashMap<String, Object>
 * responseMap = helper.checkUpdates( eachDomain, userContext);
 * log.debug("result::: " + responseMap); if
 * (!responseMap.get("Count").equals("0")) {
 * log.debug("responsemap count is > 0"); checkResponse(eachDomain, responseMap,
 * userContext); } log.debug("before timer cancel."); } // timer.cancel(); } },
 * delay, period); log.methodExit("ServletListener.contextInitialized exit"); }
 * 
 * boolean start = true;
 * 
 * private void triggerGenerateAll(String eachDomain, Map userContext) { if
 * (start) { log.debug("start is true"); try { GenerateRules generate = new
 * GenerateRules(); generate.generateAllMasters(eachDomain, userContext); start
 * = false; } catch (Exception ex) { System.out
 * .println("in ServletListener catch" + ex.getMessage());
 * log.error("Executing the generateAllMaster fails:" + ex.getMessage()); } }
 * else { log.debug("start is false"); } }
 * 
 * public void contextDestroyed(ServletContextEvent arg0) {
 * System.out.println("ServletListener context destroyed...");
 * log.error("In ServletListener.contextDestroyed..."); }
 * 
 * public List<String> getDomain() { log.methodEntry("getdomain entry.");
 * log.debug("Domains are :" + (String) proputil.getProperty("domain"));
 * String[] domain = proputil.getProperty("domain").split(","); return
 * Arrays.asList(domain); }
 * 
 * private void invokeGenerate(String eachDomain, String entitytype, Map
 * userContext) { log.debug("invokegenerate:" + eachDomain + ";" + entitytype +
 * ";" + userContext); GenerateRules generate = new GenerateRules();
 * generate.generate(eachDomain, entitytype, userContext); }
 * 
 * private void checkResponse(String eachDomain, HashMap<String, Object>
 * responseMap, Map userContext) { log.debug("inside checkresponse");
 * ConvertorUtils cu = ConvertorUtils.getInstance(); for (Entry<String, Object>
 * eachMap : responseMap.entrySet()) { log.debug("eachmap is :" + eachMap);
 * Object value = eachMap.getValue(); if (cu.isArrayList(value)) {
 * log.debug("IsArrayList condition"); List<HashMap<String, Object>> contentMap
 * = (List<HashMap<String, Object>>) value; for (HashMap<String, Object>
 * storableMap : contentMap) { String entitytype = (String)
 * storableMap.get("EntityType"); log.debug("entity:" + entitytype);
 * System.out.println("--------------" + entitytype); invokeGenerate(eachDomain,
 * entitytype, userContext); } } else if (cu.isHashMap(value)) {
 * log.debug("isHashMap condition"); HashMap<String, Object> contentMap =
 * (HashMap<String, Object>) value; log.debug("ContentMap: " + contentMap);
 * String entitytype = (String) contentMap.get("EntityType");
 * invokeGenerate(eachDomain, entitytype, userContext); } } } }
 */