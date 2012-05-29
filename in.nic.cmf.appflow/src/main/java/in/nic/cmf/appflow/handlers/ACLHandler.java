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

public class ACLHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private LogTracer log;
    private AppflowHelper appflowHelper;
    private String domain;

    public void setLog(LogTracer log) {
	this.log = log;
    }

    public ACLHandler(String domain, StatefulKnowledgeSession ksession) {
	this.ksession = ksession;
	this.domain = domain;
	appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    /**
     * Execute work item. Purpose : Through ServiceClient it triggers
     * SiteBuilder service. Each action (POST/GET/DELETE) triggers respective
     * serviceclient methods.
     * 
     * @param workItem
     *            the work item
     * @param manager
     *            the manager
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
	boolean onErrorProceed = false;
	try {
	    log.methodEntry("ACLHandler entry");
	    appflowHelper.setLog(log);
	    // String contentType = "application/xml";
	    WorkflowProcessInstance processInstance = appflowHelper
		    .initializer(ksession);
	    Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
		    .getVariable(HelperConstant.REQDETAILS);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
		    domain, "acl");

	    log.debug("method::"
		    + (String) processInstance.getVariable("method"));
	    switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
		    .getVariable("method"))) {
	    case get:
		log.debug("In ACL-Handler  GET case");
		// responseMap.get("input").put("format", contentType);
		responseMap = serviceclient.find((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		log.debug("in getacl:" + responseMap);
		break;

	    case getdms:
		log.debug("In ACL-Handler  GETfromdms case");
		// responseMap.get("input").put("format", contentType);
		responseMap = serviceclient.find((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		log.debug("in getacl:" + responseMap);
		break;

	    case post:
		log.debug("In ACL-Handler  POST case");
		responseMap = serviceclient.add((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		log.debug("in postacl:" + responseMap);

		break;

	    case posttodms:
		log.debug("In ACL-Handler  POST case");
		responseMap = serviceclient.add((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		log.debug("in posttodms:" + responseMap);

		break;

	    case delete:
		log.debug("In ACL-Handler Delete case");
		responseMap = serviceclient
			.deleteByID((String) processInstance
				.getVariable(HelperConstant.DOMAIN),
				(String) processInstance
					.getVariable(HelperConstant.ENTITY),
				(String) processInstance
					.getVariable(HelperConstant.ID),
				responseMap);
		log.debug("in delete acl:" + responseMap);
		break;
	    }
	    
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


	    Map<String, Object> outputMap = responseMap.get("output");
	    log.debug("outputMap:" + outputMap);
	    if (!outputMap.get("statusCode").toString().equals("200")) {
		log.debug("Acl status check:" + responseMap);
		throw new GenericException(domain, "ERR-AF-0000");
	    }

	    processInstance = appflowHelper
		    .finalizer((String) processInstance
			    .getVariable(HelperConstant.METHOD), "ACL: ",
			    processInstance, responseMap);

	    manager.completeWorkItem(workItem.getId(), null);

	} catch (GenericException ge) {
	    log.error("In ACLHandler GenericException:" + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In ACLHandler Exception :" + ex.getMessage());
	    throw new GenericException(domain, "ERR-AF-0008",
		    "Exception at ACLHandler :", ex);
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
