package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

/**
 * The Class GeotaggerHandler.
 */
public class GeotaggerHandler implements WorkItemHandler {

    /** The ksession. */
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
     * Instantiates a new geotagger handler.
     * 
     * @param ksession
     *            the ksession
     */
    public GeotaggerHandler(String domain, StatefulKnowledgeSession ksession) {
	this.ksession = ksession;
	this.domain = domain;
	appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    /**
     * Execute work item. Purpose : Through ServiceClient it triggers GeoTagger
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
	boolean onErrorProceed = false;
	try {

	    appflowHelper.setLog(log);
	    WorkflowProcessInstance processInstance = appflowHelper
		    .initializer(ksession);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
		    domain, "geotagger");
	    // String contentType = "application/xml";
	    Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
		    .getVariable(HelperConstant.REQDETAILS);
	    try {
		switch (AppflowHelper.SELECTFLOW
			.toFlow((String) processInstance
				.getVariable(HelperConstant.METHOD))) {
		case get:
		    log.debug("inside GET of Geotagger handler.");
		    // responseMap.get("input").put("format", contentType);
		    responseMap = serviceclient.find((String) processInstance
			    .getVariable(HelperConstant.DOMAIN),
			    (String) processInstance
				    .getVariable(HelperConstant.ENTITY),
			    responseMap);
		    String searchEngineOutput = (String) responseMap.get(
			    "output").get("content");
		    responseMap.get("input").put("content", searchEngineOutput);
		    break;
		case post:
		    log.debug("inside POST of Geotagger handler.");
		    // responseMap.get("input").put("format", contentType);
		    responseMap = serviceclient.add((String) processInstance
			    .getVariable(HelperConstant.DOMAIN),
			    (String) processInstance
				    .getVariable(HelperConstant.ENTITY),
			    responseMap);
		    Map<String, Object> outputMap = responseMap.get("output");
		    if (outputMap.get("statusCode").toString().equals("200")) {
			String geotaggerOutput = (String) outputMap
				.get("content");
			responseMap.get("input")
				.put("content", geotaggerOutput);
		    }
		    break;
		}
		processInstance = appflowHelper.finalizer(
			(String) processInstance
				.getVariable(HelperConstant.METHOD),
			"Geotagger", processInstance, responseMap);
	    } catch (GenericException ge) {
		log.error("In Geotagger GenericException:" + ge.getMessage());
		onErrorProceed = true;
	    } catch (Exception ex) {
		log.error("In Geotagger Exception :" + ex.getMessage());
		onErrorProceed = true;
	    } finally {
		if (onErrorProceed) {
		    log.error("Exception in Geotagger Processing, Proceeding forward.:");
		}
		manager.completeWorkItem(workItem.getId(), null);
	    }
	} catch (GenericException ge) {
	    log.error("In Geotagger GenericException:" + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In Geotagger Exception :" + ex.getMessage());
	    throw new GenericException(domain, "ERR-AF-0008",
		    "Exception at GeotaggerHanlder :", ex);
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
