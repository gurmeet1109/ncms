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
 * The Class Email.
 */
public class EmailHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private AppflowHelper appflowHelper;
    public LogTracer log;
    private String domain;

    public void setLog(LogTracer log) {
	this.log = log;
    }

    /**
     * Instantiates a new email handler.
     * 
     * @param ksession
     *            the ksession
     */
    public EmailHandler(String domain, StatefulKnowledgeSession ksession) {
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
	    log.methodEntry("EmailHandler entry");
	    WorkflowProcessInstance processInstance = appflowHelper
		    .initializer(ksession);
	    Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
		    .getVariable(HelperConstant.REQDETAILS);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
		    domain, "email");
	    // String contentType = "application/xml";
	    try {
		// responseMap.get("input").put("format", contentType);
		responseMap = serviceclient.add((String) processInstance
			.getVariable(HelperConstant.DOMAIN),
			(String) processInstance
				.getVariable(HelperConstant.ENTITY),
			responseMap);
		processInstance = appflowHelper.finalizer(
			(String) processInstance
				.getVariable(HelperConstant.METHOD), "Email:",
			processInstance, responseMap);
	    } catch (GenericException ge) {
		log.error("In Email GenericException:" + ge.getMessage());
		onErrorProceed = true;
	    } catch (Exception ex) {
		log.error("In Email Exception :" + ex.getMessage());
		onErrorProceed = true;
	    } finally {
		if (onErrorProceed) {
		    log.error("Exception in Email Processing, Proceeding forward...:");
		}
		manager.completeWorkItem(workItem.getId(), null);
	    }
	    log.methodExit("EmailHandler exit");
	} catch (GenericException ge) {
	    log.error("In GE: Email : " + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In EX : Email : " + ex.getMessage());
	    throw new GenericException(domain, "ERR-AF-0008",
		    "Exception at EmailHandler :", ex);
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
