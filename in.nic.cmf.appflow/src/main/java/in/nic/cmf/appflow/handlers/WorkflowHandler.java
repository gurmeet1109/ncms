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

public class WorkflowHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private LogTracer                log;
    private AppflowHelper            appflowHelper;
    private String                   domain;

    public void setLog(LogTracer log) {
        this.log = log;
    }

    public WorkflowHandler(String domain, StatefulKnowledgeSession ksession) {
        this.ksession = ksession;
        this.domain = domain;
        appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    /**
     * Execute work item. Purpose : Through ServiceClient it triggers
     * SiteBuilder service. Each action (POST/GET/DELETE) triggers respective
     * serviceclient methods.
     * @param workItem
     *            the work item
     * @param manager
     *            the manager
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        boolean onErrorProceed = false;
        try {
            log.methodEntry("WorkflowHandler entry");
            appflowHelper.setLog(log);
            // String contentType = "application/xml";
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);
            ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
                    domain, "workflow");
            switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
                    .getVariable("method"))) {
                case post:
                    log.debug("inside post of workflow handler.");
                    responseMap = serviceclient.add((String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap);
                    log.debug("after serviceclient:" + responseMap);
                    String workflowOutput = (String) responseMap.get("output")
                            .get("content");
                    responseMap.get("input").put("content", workflowOutput);
                    break;
                case posttodms:
                    log.debug("inside post of workflow handler.");
			                   String mediaOutput = (String) processInstance
                            .getVariable("media");
                    responseMap.get("input").put("content", mediaOutput);

                    log.debug("before serviceclient in WF:" + responseMap);

                    responseMap = serviceclient.add((String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap);
                    log.debug("after serviceclient:" + responseMap);
                    String workflowOutput1 = (String) responseMap.get("output")
                            .get("content");
                    responseMap.get("input").put("content", workflowOutput1);
log.debug("after setting workflow output");
   Map<String, Map<String, Object>> toSetReq= (Map<String, Map<String, Object>>) processInstance.getVariable(HelperConstant.REQDETAILS);
                    toSetReq.get("input").put("content", workflowOutput1);
                    processInstance.setVariable(HelperConstant.REQDETAILS,toSetReq);



responseMap = toSetReq;
log.debug("after processinstance set:");
                    break;
            }
            processInstance = appflowHelper
                    .finalizer((String) processInstance
                            .getVariable(HelperConstant.METHOD),
                            "workflow: ", processInstance, responseMap);
            manager.completeWorkItem(workItem.getId(), null);

        } catch (GenericException ge) {
            log.error("In workflow GenericException:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In Workflow Exception :" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0008",
                    "Exception at workflow :", ex);
        }
    }

    /**
     * Abort work item.
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
