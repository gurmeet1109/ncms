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

public class TransformerHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private LogTracer                log;
    private AppflowHelper            appflowHelper;
    private String                   domain;

    public void setLog(LogTracer log) {
        this.log = log;
    }

    public TransformerHandler(String domain, StatefulKnowledgeSession ksession) {
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
            log.methodEntry("TransformerHandler entry");
            appflowHelper.setLog(log);
            // String contentType = "application/xml";
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);

            switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
                    .getVariable("method"))) {
                case post:
                    log.debug("inside post of Transformer handler:"
                            + responseMap);
                    responseMap = setDefault(
                            (String) processInstance
                                    .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap, processInstance);
                    log.debug("Transformer output:" + responseMap);
                    break;
                case posttodms:
                    log.debug("inside posttodms of Transformer handler:"
                            + responseMap);
                    responseMap = setDefault(
                            (String) processInstance
                                    .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap, processInstance);
                    break;
            }
            processInstance = appflowHelper
                    .finalizer((String) processInstance
                            .getVariable(HelperConstant.METHOD),
                            "transformer: ", processInstance, responseMap);
            manager.completeWorkItem(workItem.getId(), null);
        } catch (GenericException ge) {
            log.error("In Transformer GenericException:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In Transformer Exception :" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0008",
                    "Exception at Generate :", ex);
        }
    }

    private Map<String, Map<String, Object>> setDefault(String domainName,
            String entityType, Map<String, Map<String, Object>> responseMap,
            WorkflowProcessInstance processInstance) {
        ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
                domainName, "transformer");
        responseMap = serviceclient.add(domainName, entityType, responseMap);
        log.debug("After add method:" + responseMap);
        String transformerOutput = (String) responseMap.get("output").get(
                "content");
        responseMap.get("input").put("content", transformerOutput);
        responseMap.get("input").remove("files");
        log.debug("Transformer output:" + responseMap);
        processInstance.setVariable(HelperConstant.REQDETAILS, responseMap);
        log.debug("processinstance setvariable:"
                + processInstance.getVariable(HelperConstant.REQDETAILS));

        return responseMap;
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
