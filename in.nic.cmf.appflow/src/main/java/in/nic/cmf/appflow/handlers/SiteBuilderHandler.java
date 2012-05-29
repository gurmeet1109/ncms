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
 * The Class SiteBuilderHandler.
 */
public class SiteBuilderHandler implements WorkItemHandler {

    /** The ksession. */
    private StatefulKnowledgeSession ksession;
    private AppflowHelper appflowHelper;
    /** The log. */
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
     * Instantiates a new site builder handler.
     * 
     * @param ksession
     *            the ksession
     */
    public SiteBuilderHandler(String domain, StatefulKnowledgeSession ksession) {
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
	    log.methodEntry("SiteBuilderHandler entry.");
	    appflowHelper.setLog(log);
	    WorkflowProcessInstance processInstance = appflowHelper
		    .initializer(ksession);
	    Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
		    .getVariable(HelperConstant.REQDETAILS);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
		    domain, "sitebuilder");
	    // String contentType = "application/xml";
	    try {
		switch (AppflowHelper.SELECTFLOW
			.toFlow((String) processInstance
				.getVariable(HelperConstant.METHOD))) {
		case delete:
		    log.debug("inside delete of SiteBuilder handler.");
		    String subQuery = (String) processInstance
			    .getVariable(HelperConstant.ENTITY)
			    + "/"
			    + (String) processInstance
				    .getVariable(HelperConstant.ID);
		  /*  responseMap = serviceclient.deleteByQuery(
			    (String) processInstance
				    .getVariable(HelperConstant.DOMAIN),
			    subQuery, responseMap);*/
		    
		    responseMap = serviceclient.deleteByID(
                    (String) processInstance
                            .getVariable(HelperConstant.DOMAIN), (String) processInstance
                            .getVariable(HelperConstant.ENTITY), (String) processInstance
                            .getVariable(HelperConstant.ID),
                    responseMap);
		    break;
		case post:
		    log.debug("inside post of SiteBuilder handler.");
		    // responseMap.get("input").put("format", contentType);
		    responseMap = serviceclient.add((String) processInstance
			    .getVariable(HelperConstant.DOMAIN),
			    (String) processInstance
				    .getVariable(HelperConstant.ENTITY),
			    responseMap);
		    log.debug("Response of site builder for post:"
			    + responseMap);
		    break;
		case posttodms:
            log.debug("inside postdms of SiteBuilder handler.");
            responseMap = serviceclient.add(
                    (String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                    (String) processInstance
                            .getVariable(HelperConstant.ENTITY),
                    responseMap);
            log.debug("Response of site builder for post:"
                    + responseMap);
            break; 
		}
		
		processInstance = appflowHelper.finalizer(
			(String) processInstance
				.getVariable(HelperConstant.METHOD),
			"SiteBuilder: ", processInstance, responseMap);
		processInstance.setVariable("response", responseMap);
		
		log.debug("After SiteBuilder POST from SC:" + responseMap);
		
	

	    } catch (GenericException ge) {
		log.error("In Site GenericException:" + ge.getMessage());
		onErrorProceed = true;
	    } catch (Exception ex) {
		log.error("In Site Exception :" + ex.getMessage());
		onErrorProceed = true;
	    } finally {
		if (onErrorProceed) {
		    log.error("Exception in Sitebuilder Processing, But proceeding forward.");
		}
	    }
		manager.completeWorkItem(workItem.getId(), null);
	    log.methodExit("SiteBuilderHandler exit.");
	} catch (GenericException ge) {
	    log.error("In SiteBuilderHandler GenericException:" + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In SiteBuilderHandler Exception::::::::::::::::::::::::::::::::: :" + ex.getMessage());
	    ex.printStackTrace();
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
