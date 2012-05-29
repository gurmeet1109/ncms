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

public class RuleEngineHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private LogTracer                log;
    private AppflowHelper            appflowHelper;
    private String                   domain;

    public void setLog(LogTracer log) {
        this.log = log;
    }

    public RuleEngineHandler(String domain, StatefulKnowledgeSession ksession) {
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
            log.methodEntry("RuleEngineHandler entry");
            appflowHelper.setLog(log);
            // String contentType = "application/xml";
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);
            ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
                    domain, "ruleengine");
            switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
                    .getVariable("method"))) {
                case post:
                    log.debug("inside post of RuleEngine handler.");
                    responseMap = serviceclient.add((String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap);
                    break;
                case posttodms:
                    log.debug("inside posttodms of RuleEngine handler.");

                   // String mediaOutput = (String) processInstance
                     //       .getVariable("media");
                   // responseMap.get("input").put("content", mediaOutput);

                    log.debug("INPUT TO RE:" + responseMap);

                    responseMap = serviceclient.add((String) processInstance
                            .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            responseMap);
                    log.debug("OUTPUT FROM RE:" + responseMap);





                    break;

                case delete:
                    log.debug("In RE Delete case");
                    responseMap = serviceclient.deleteByID(
                            (String) processInstance
                                    .getVariable(HelperConstant.DOMAIN),
                            (String) processInstance
                                    .getVariable(HelperConstant.ENTITY),
                            (String) processInstance
                                    .getVariable(HelperConstant.ID),
                            responseMap);
                    break;
            }
            log.debug("responseMap:" + responseMap);
/*            processInstance = appflowHelper
                    .finalizer((String) processInstance
                            .getVariable(HelperConstant.METHOD),
                            "ruleengine: ", processInstance, responseMap);
*/
            log.debug("after finalizer1");
            boolean validation = false;
            String code = "";
            if (responseMap.get("output").get("statusCode") instanceof String) {
                log.debug("Inside String ::: ");
                code = (String) responseMap.get("output").get("statusCode");
            } else if (responseMap.get("output").get("statusCode") instanceof Integer) {
                log.debug("Inside Integer ::: ");
                code = responseMap.get("output").get("statusCode").toString();
            }
            log.debug("code:" + code);
            if (!code.equals("200")) {
                log.debug("failure scenario");
                validation = true;
            } else {
                log.debug("success scenario");
                String outputCollections = (String) responseMap.get("output")
                        .get("content");
                processInstance.setVariable("duplication", outputCollections);

            }



   Map<String, Map<String, Object>> toSetReq= (Map<String, Map<String, Object>>) processInstance.getVariable(HelperConstant.REQDETAILS);

log.debug("after set:"+toSetReq);
  //                  toSetReq.get("input").put("content", workflowOutput1);
                    processInstance.setVariable(HelperConstant.REQDETAILS,toSetReq);



responseMap = toSetReq;


log.debug("RuleEngine output after geting valid output:"+responseMap);

  processInstance = appflowHelper
                    .finalizer((String) processInstance
                            .getVariable(HelperConstant.METHOD),
                            "ruleengine: ", processInstance, responseMap);








            processInstance.setVariable("validationerror", validation);
            manager.completeWorkItem(workItem.getId(), null);
            log.debug("after finalizer2");
        } catch (GenericException ge) {
            ge.printStackTrace();
            log.error("In RuleEngine GenericException:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In RuleEngine Exception::" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0008",
                    "Exception at RuleEngine :", ex);
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
