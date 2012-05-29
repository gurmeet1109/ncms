package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.util.HashMap;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

/**
 * The Class SearchApiHandler.
 */
public class SearchEngineHandler implements WorkItemHandler {

	private StatefulKnowledgeSession ksession;
	private AppflowHelper appflowHelper;
	public LogTracer log;
	private String domain;

	/**
	 * Sets the log.
	 * 
	 * @param log
	 *            the new log
	 */
	public void setLog(LogTracer log) {
		this.log = log;
	}

	/**
	 * Instantiates a new search api handler.
	 * 
	 * @param ksession
	 *            the ksession
	 */
	public SearchEngineHandler(String domain, StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
		this.domain = domain;
		appflowHelper = AppflowHelper.getInstanceof(domain);
	}

	/**
	 * Execute work item. Purpose : Through ServiceClient it triggers SE
	 * service. Each action (POST/GET/DELETE) triggers respective serviceclient
	 * methods.
	 * 
	 * @param workItem
	 *            the work item
	 * @param manager
	 *            the manager
	 */

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		try {
			appflowHelper.setLog(log);
			log.methodEntry("SearchApPIandler entry");
			WorkflowProcessInstance processInstance = appflowHelper
			.initializer(ksession);
			log.debug("after ksession initializer");
			ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
					domain, "searchengine");
			Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
			.getVariable(HelperConstant.REQDETAILS);

	
			// String contentType = "application/xml";
			switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
					.getVariable(HelperConstant.METHOD))) {
					case get:
						log.debug("In Searchapi-Handler  GET case");
						
					/*	
						log.debug("queryParams:"+responseMap.get("input").get("queryParams"));
						HashMap<String, String> formatMap = (HashMap<String, String>) responseMap.get("input").get("queryParams");
						if(formatMap.containsKey("format")){
							String format = formatMap.get("format");
							processInstance.setVariable("format",format);
							log.debug("Processinstance setted: "+format);
						}else{
							processInstance.setVariable("format","xml");
							log.debug("Processinstance setted.");
						}
*/
						// responseMap.get("input").put("format", contentType);
						responseMap = serviceclient.find((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								responseMap);
						String searchEngineOutput = (String) responseMap.get("output").get("content");
						responseMap.get("input").put("content", searchEngineOutput);
						break;
					case post:
						log.debug("In Searchapi-Handler  POST case: " + responseMap);
						// responseMap.get("input").put("format", contentType);
						responseMap = serviceclient.add((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								responseMap);
						break;
					case postall:
						log.debug("In Searchapi-Handler POST ALL case");
						responseMap = serviceclient.add((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								responseMap);
						break;
					case posttodms:
						log.debug("In Searchapi-Handler  POSTtodms case: "
								+ responseMap);
						// responseMap.get("input").put("format", contentType);
						responseMap.get("input").remove("files");

						//String mediaOutput = (String) processInstance.getVariable("media");

//						responseMap.get("input").put("content", mediaOutput);

						responseMap = serviceclient.add((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								responseMap);
						break;
					case delete:
						log.debug("In Searchapi-Handler Delete case");
						responseMap = serviceclient
						.deleteByID((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								(String) processInstance
								.getVariable(HelperConstant.ID),
								responseMap);
						break;
					case getfromse:
						log.debug("In Searchapi-Handler getfromse case");
						responseMap = serviceclient.find((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								responseMap);
						break;
			}
			log.debug("After switch case:" + responseMap);
			processInstance = appflowHelper
			.finalizer((String) processInstance
					.getVariable(HelperConstant.METHOD),
					"SearchEngine: ", processInstance, responseMap);
			manager.completeWorkItem(workItem.getId(), null);
			log.methodExit("SearchEngineHandler exit");
		} catch (GenericException ge) {
			log.debug("In GE:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.debug("In EX:" + ex.getMessage());
			throw new GenericException(domain, "ERR-AF-0010",
					"Exception at Search API handler:", ex);
		}
	}

	/**
	 * Abort work item.
	 * 
	 * @param workItem
	 *            the work item
	 * @param manager
	 *            the manager
	 */
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.completeWorkItem(workItem.getId(), null);
	}

}
