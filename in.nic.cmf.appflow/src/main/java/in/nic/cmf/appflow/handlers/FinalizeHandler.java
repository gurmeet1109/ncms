package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

public class FinalizeHandler implements WorkItemHandler {
    private StatefulKnowledgeSession ksession;
    private AppflowHelper            appflowHelper;
    public LogTracer                 log;
    private String                   domain;

    /**
     * Sets the log.
     * @param log
     *            the new log
     */
    public void setLog(LogTracer log) {
        this.log = log;
    }

    /**
     * Instantiates a new search api handler.
     * @param ksession
     *            the ksession
     */
    public FinalizeHandler(String domain, StatefulKnowledgeSession ksession) {
        this.ksession = ksession;
        this.domain = domain;
        appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    /**
     * Execute work item.
     * Purpose : Through ServiceClient it triggers SE service.
     * Each action (POST/GET/DELETE) triggers respective serviceclient methods.
     * @param workItem
     *            the work item
     * @param manager
     *            the manager
     */
    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            appflowHelper.setLog(log);
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            Map<String, Map<String, Object>> reqDetails = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);
            processInstance = appflowHelper
                    .finalizer((String) processInstance
                            .getVariable(HelperConstant.METHOD), "Finalize: ",
                            processInstance, reqDetails);

            manager.completeWorkItem(workItem.getId(), null);
        } catch (GenericException ge) {
            log.debug("In GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("In EX:" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0018",
                    "Exception at Finalize handler:", ex);
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
