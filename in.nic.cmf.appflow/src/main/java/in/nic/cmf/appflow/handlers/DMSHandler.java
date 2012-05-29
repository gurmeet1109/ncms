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
 * The Class DMSHandler.
 */
public class DMSHandler implements WorkItemHandler {

    private LogTracer log;
    private StatefulKnowledgeSession ksession;
    private AppflowHelper appflowHelper;
    private String domain;

    public void setLog(LogTracer log) {
	this.log = log;
    }

    public DMSHandler(String domain, StatefulKnowledgeSession ksession) {
	this.ksession = ksession;
	this.domain = domain;
	appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    /**
     * Execute work item. Purpose : Through ServiceClient it triggers DMS
     * service. Each action (POST/GET/DELETE) triggers respective serviceclient
     * methods.
     * 
     * @param workItem
     *            the work item
     * @param manager
     *            the manager
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
	try {
	    appflowHelper.setLog(log);
	    log.methodEntry("DMSHandler entry======================================");
	    WorkflowProcessInstance processInstance = appflowHelper
		    .initializer(ksession);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
		    domain, "dms");
	    Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
		    .getVariable("requestDetails");

		String format = (String) processInstance.getVariable("format");
    	log.debug("processinstance get:"+format);
    	format = format.toLowerCase();
    	if(format.equals("json") || format.equals("extjson")){
    		log.debug("before json");
    		responseMap.get("input").put("format", "application/json");
    	}else{
    		log.debug("before xml");
    		responseMap.get("input").put("format", "application/xml");
    		Map<String,String> query = new HashMap<String,String>();
    		query.put("format", "xml");
    		responseMap.get("input").put("queryParams",query);
    	}
		log.debug("responsemap after xml put:"+responseMap);
		

	    switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
		    .getVariable("method"))) {
	    case get:
		log.debug("In DMS-Handler GET case:"+responseMap);
/*
		String format = (String) processInstance.getVariable("format");
    	log.debug("processinstance get:"+format);
    	format = format.toLowerCase();
    	if(format.equals("json") || format.equals("extjson")){
    		log.debug("before json");
    		responseMap.get("input").put("format", "application/json");
    	}else{
    		log.debug("before xml");
    		responseMap.get("input").put("format", "application/xml");
    		Map<String,String> query = new HashMap<String,String>();
    		query.put("format", "xml");
    		responseMap.get("input").put("queryParams",query);
    	}
		log.debug("responsemap after xml put:"+responseMap);
		*/
		responseMap = serviceclient.read((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		log.debug("After DMS GET from SC");
		break;

	    case post:
		log.debug("In DMS-Handler POST case:" + responseMap);
		responseMap = serviceclient.add((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);

		log.debug("After DMS POST from SC:" + responseMap);
		break;

	    case postall:
		log.debug("In DMS-Handler POSTALL case");

		responseMap = serviceclient.add((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		break;

	    case getfromdms:
		log.debug("In DMS-Handler GET from DMS");
		log.debug("params:"
			+ (String) processInstance
				.getVariable(HelperConstant.DOMAIN)
			+ (String) processInstance
				.getVariable(HelperConstant.ENTITY)
			+ (String) processInstance
				.getVariable(HelperConstant.ID) + responseMap);
		responseMap = serviceclient
			.findById((String) processInstance
				.getVariable(HelperConstant.DOMAIN),
				(String) processInstance
					.getVariable(HelperConstant.ENTITY),
				(String) processInstance
					.getVariable(HelperConstant.ID),
				responseMap);
		log.debug("After DMS GETfromDMS from SC");
		break;
	    case getdms:
		log.debug("In DMS-Handler GETDMS");
		log.debug("params:"
			+ (String) processInstance
				.getVariable(HelperConstant.DOMAIN)
			+ (String) processInstance
				.getVariable(HelperConstant.ENTITY)
			+ (String) processInstance
				.getVariable(HelperConstant.ID) + responseMap);
		responseMap = serviceclient
			.findById((String) processInstance
				.getVariable(HelperConstant.DOMAIN),
				(String) processInstance
					.getVariable(HelperConstant.ENTITY),
				(String) processInstance
					.getVariable(HelperConstant.ID),
				responseMap);
		log.debug("After DMS GETDMS from SC");
		break;

	    case delete:
		log.debug("In DMS-Handler DELETE case");

		// String subQuery = (String)
		// processInstance.getVariable("subQuery");
		responseMap = serviceclient
			.deleteByID((String) processInstance
				.getVariable(HelperConstant.DOMAIN),
				(String) processInstance
					.getVariable(HelperConstant.ENTITY),
				(String) processInstance
					.getVariable(HelperConstant.ID),
				responseMap);
		log.debug("After DMS DELETE from SC");
		break;

	    case posttodms:
		log.debug("INSIDE posttodms");
		// responseMap.get("input").put("format", contentType);
		responseMap.get("input").remove("files");
		/*
		 * HashMap<String, Object> tmp = FormatXml.getInstance().in(
		 * responseMap.get("output").get("content"));
		 * responseMap.get("input").put("content", (String)
		 * FormatXml.getInstance().out(tmp));
		 * responseMap.get("output").remove("content");
		 */

	/*	String mediaOutput = (String) processInstance
			.getVariable("media");

		responseMap.get("input").put("content", mediaOutput);
		log.debug("input to sc:" + responseMap);

		log.debug("INPUT to DMS:" + mediaOutput);*/

		log.debug("INPUT to DMS:" + responseMap);
		responseMap = serviceclient.add((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);

		log.debug("After DMS POST from SC:" + responseMap);
		break;
	    }
	    processInstance = appflowHelper.finalizer(
		    (String) processInstance.getVariable("method"), "DMS: ",
		    processInstance, responseMap);
	    processInstance.setVariable("response", responseMap);
	    manager.completeWorkItem(workItem.getId(), null);
	    log.methodExit("DMSHandler exit");
	} catch (GenericException ge) {
	    log.error("In GE : DMSHandler : " + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In EX: DMSHandler : " + ex.getMessage());
	    throw new GenericException(domain, "ERR-AF-0007",
		    "Exception at DMS Handler.", ex);
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
