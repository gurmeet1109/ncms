package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.util.List;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

public class MediaHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private LogTracer                log;
    private AppflowHelper            appflowHelper;
    private String                   domain;

    public void setLog(LogTracer log) {
        this.log = log;
    }

    public MediaHandler(String domain, StatefulKnowledgeSession ksession) {
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
            log.methodEntry("MediaHandler entry");
            appflowHelper.setLog(log);
            // String contentType = "application/xml";
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);
            ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
                    domain, "media");
            switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
                    .getVariable("method"))) {
                case post:
                    log.debug("inside post of media handler.");
                    responseMap = serviceclient.add((String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap);
                    break;
                case posttodms:
                    log.debug("inside postdms of Media handler.");

                    List<Map<String, Object>> files = (List<Map<String, Object>>) processInstance
                            .getVariable("files");
                    responseMap.get("input").put("files", files);
                    log.debug("input to sc for media : " + responseMap);
                    responseMap = serviceclient.add((String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap);

                    log.debug("responseMap from Media::::" + responseMap);

                    responseMap.get("input").remove("files");

                    String outputCollections = (String) responseMap.get(
                            "output").get("content");

                    processInstance.setVariable("media", outputCollections);

                    log.debug("Output of MEDIA:" + outputCollections);

                    break;

            }
            processInstance = appflowHelper
                    .finalizer((String) processInstance
                            .getVariable(HelperConstant.METHOD), "media: ",
                            processInstance, responseMap);
            manager.completeWorkItem(workItem.getId(), null);

        } catch (GenericException ge) {
            log.error("In Media GenericException:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In Media Exception :" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0008",
                    "Exception at media :", ex);
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
