package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

public class AuditHandler implements WorkItemHandler {

    public LogTracer                 log;
    private StatefulKnowledgeSession ksession;
    private AppflowHelper            appflowHelper;
    private String                   domain;

    public AuditHandler(String domain, StatefulKnowledgeSession ksession) {
        this.ksession = ksession;
        this.domain = domain;
        appflowHelper = AppflowHelper.getInstanceof(domain);
    }

    public void setKsession(StatefulKnowledgeSession kSession) {
        this.ksession = kSession;
    }

    public void setLog(LogTracer log) {
        this.log = log;
    }

    @Override
    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        try {
            appflowHelper.setLog(log);
            log.methodEntry("AuditHandler entry");
            boolean onErrorProceed = true;
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            // String contentType = "application/xml";
            Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);
            ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
                    domain, "audit");
            log.debug("ServiceClient audit instance created.");
            try {
                Map<String, String> userContext = (Map<String, String>) responseMap
                        .get("input").get("userContext");
                String query = "";
                switch (AppflowHelper.SELECTFLOW
                        .toFlow((String) processInstance
                                .getVariable(HelperConstant.METHOD))) {
                    case delete:
                        query = "auditlog?username="
                                + userContext.get("authusername")
                                + "&action="
                                + processInstance
                                        .getVariable(HelperConstant.METHOD)
                                + "&document="
                                + processInstance
                                        .getVariable(HelperConstant.SUBQUERY)
                                + "&message="
                                + processInstance
                                        .getVariable(HelperConstant.ENTITY);
                        log.debug("In Switch delete, query is " + query);
                        break;
                    case post:
                        query = "auditlog?username="
                                + userContext.get("authusername")
                                + "&action="
                                + processInstance
                                        .getVariable(HelperConstant.METHOD)
                                + "&document=";
                        String inputCollections = (String) responseMap.get(
                                "input").get("content");
                        if (inputCollections.length() > 200) {
                            query += URLEncoder
                                    .encode(inputCollections.substring(0, 200),
                                            "UTF-8");
                        } else {
                            query += URLEncoder.encode(inputCollections,
                                    "UTF-8");
                        }
                        query += "&message="
                                + processInstance
                                        .getVariable(HelperConstant.ENTITY);
                        log.debug("In Switch post, query is " + query);
                        break;
                    case get:
                        query = "auditlog?username="
                                + userContext.get("authusername")
                                + "&action="
                                + processInstance
                                        .getVariable(HelperConstant.METHOD)
                                + "&document=";
                        // String inputCollections = (String) responseMap.get(
                        // "input").get("content");
                        // if (inputCollections.length() > 200) {
                        // query += URLEncoder
                        // .encode(inputCollections.substring(0, 200),
                        // "UTF-8");
                        // } else {
                        // query += URLEncoder.encode(inputCollections,
                        // "UTF-8");
                        // }
                        query += "&message="
                                + processInstance
                                        .getVariable(HelperConstant.ENTITY);
                        log.debug("In Switch get, query is " + query);
                        break;
                }
                Map<String, Map<String, Object>> tmpResponseMap = new HashMap<String, Map<String, Object>>();
                tmpResponseMap.putAll(responseMap);

                tmpResponseMap = serviceclient.find((String) processInstance
                        .getVariable(HelperConstant.DOMAIN),
                        (String) processInstance
                                .getVariable(HelperConstant.ENTITY),
                        tmpResponseMap);

                log.debug("tmpResponseMap:::" + tmpResponseMap);
                log.debug("responseMap:::" + responseMap);
                onErrorProceed = false;
            } catch (GenericException ge) {
                log.error("In AuditHandler GenericException:" + ge.getMessage());
            } catch (Exception ex) {
                log.error("In AuditHandler Exception :" + ex.getMessage());
            } finally {
                if (onErrorProceed) {
                    log.error("Exception in AuditHandler Processing, Proceeding forward...:");
                }
                manager.completeWorkItem(workItem.getId(), null);
            }
            log.methodExit("AuditHandler exit");
        } catch (GenericException ge) {
            log.debug("In GE:Audit:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("In Ex:Audit:" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0006",
                    "Exception at Audit handler:", ex);
        }
    }

    @Override
    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        log.debug("In AuditHandler - abortWorkItem");
        manager.completeWorkItem(workItem.getId(), null);
    }
}
