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

// TODO: Auto-generated Javadoc
/**
 * The Class SEMHandler.
 */
public class SEMHandler implements WorkItemHandler {
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
     * Instantiates a new sEM handler.
     * 
     * @param ksession
     *            the ksession
     */
    public SEMHandler(String domain, StatefulKnowledgeSession ksession) {
	this.ksession = ksession;
	this.domain = domain;
	appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    /**
     * Execute work item. Purpose : Through ServiceClient it triggers SEM
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
	    log.methodEntry("SEMHandler entry");
	    WorkflowProcessInstance processInstance = appflowHelper
		    .initializer(ksession);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
		    domain, "sem");
	    // String contentType = "application/xml";
	    Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
		    .getVariable(HelperConstant.REQDETAILS);
	    try {
		switch (AppflowHelper.SELECTFLOW
			.toFlow((String) processInstance
				.getVariable(HelperConstant.METHOD))) {
		case post:
		    // responseMap.get("input").put("format", contentType);
		    responseMap = serviceclient.add((String) processInstance
			    .getVariable(HelperConstant.DOMAIN),
			    (String) processInstance
				    .getVariable(HelperConstant.ENTITY),
			    responseMap);
		    break;
		}
		processInstance = appflowHelper.finalizer(
			(String) processInstance
				.getVariable(HelperConstant.METHOD), "SEM",
			processInstance, responseMap);
		log.debug("After SEM POST from SC:" + responseMap);
		manager.completeWorkItem(workItem.getId(), null);
	    } catch (GenericException ge) {
		log.error("In Sem GenericException:" + ge.getMessage());
		onErrorProceed = true;
	    } catch (Exception ex) {
		log.error("In Sem Exception :" + ex.getMessage());
		onErrorProceed = true;
	    } finally {
		if (onErrorProceed) {
		    log.error("Exception in Sem Processing, Proceeding forward...:");
		}
	    }
	    log.methodExit("SEMHandler exit");
	} catch (GenericException ge) {
	    log.error("In Sem GenericException:" + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In Sem Exception :" + ex.getMessage());
	    throw new GenericException(domain, "ERR-AF-0008",
		    "Exception at SemHanlder :", ex);
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
