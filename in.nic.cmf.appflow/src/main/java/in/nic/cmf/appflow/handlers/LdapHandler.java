package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.cache.RedisCache;
import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

public class LdapHandler implements WorkItemHandler {

    private StatefulKnowledgeSession ksession;
    private LogTracer                log;
    private RedisCache               redisCache;
    private AppflowHelper            appflowHelper;
    private String                   domain;

    public void setLog(LogTracer log) {
        this.log = log;
    }

    public LdapHandler(String domain, StatefulKnowledgeSession ksession) {
        this.ksession = ksession;
        this.domain = domain;
        appflowHelper = AppflowHelper.getInstanceof(domain);
        redisCache = RedisCache.getInstanceof(domain);
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
            log.methodEntry("LdapHandler entry");
            appflowHelper.setLog(log);
            // String contentType = "application/xml";
            WorkflowProcessInstance processInstance = appflowHelper
                    .initializer(ksession);
            Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance
                    .getVariable(HelperConstant.REQDETAILS);
            ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
                    domain, "ldap");
            try {
                switch (AppflowHelper.SELECTFLOW
                        .toFlow((String) processInstance.getVariable("method"))) {
                    case delete:
                        log.debug("inside delete of Ldap handler:"
                                + responseMap);
                        String subQuery = (String) processInstance
                                .getVariable(HelperConstant.ENTITY)
                                + "/"
                                + (String) processInstance
                                        .getVariable(HelperConstant.ID);
                        responseMap = serviceclient.deleteByID(
                                (String) processInstance
                                        .getVariable(HelperConstant.DOMAIN),
                                (String) processInstance
                                        .getVariable(HelperConstant.ENTITY),
                                (String) processInstance
                                        .getVariable(HelperConstant.ID),
                                responseMap);
                        log.debug("ldaphandler:" + responseMap);
                        break;
                    case post:
                        log.debug("inside post of Ldap handler.");
                        // responseMap.get("input").put("format", contentType);
                        responseMap = serviceclient.add(
                                (String) processInstance
                                        .getVariable(HelperConstant.DOMAIN),
                                (String) processInstance
                                        .getVariable(HelperConstant.ENTITY),
                                responseMap);
                        log.debug("Response of ldap for post:" + responseMap);
                        Map<String, Object> outputMap = responseMap
                                .get("output");
                        if (outputMap.get("statusCode").toString()
                                .equals("200")) {
                            String collections = (String) responseMap.get(
                                    "input").get("content");
                            FormatXml format = FormatXml.getInstanceof(domain);
                            HashMap<String, Object> collectionAsMap = (HashMap<String, Object>) format
                                    .in(collections).get("Collections");
                            ConvertorUtils cu = ConvertorUtils
                                    .getInstanceof(domain);
                            for (Entry<String, Object> eachMap : collectionAsMap
                                    .entrySet()) {
                                Object value = eachMap.getValue();
                                if (cu.isArrayList(value)) {
                                    List<HashMap<String, Object>> contentMap = (List<HashMap<String, Object>>) value;
                                    for (HashMap<String, Object> storableMap : contentMap) {
                                        getRoles(storableMap);
                                    }
                                } else if (cu.isHashMap(value)) {
                                    HashMap<String, Object> storableMap = (HashMap<String, Object>) value;
                                    getRoles(storableMap);
                                }
                            }
                        }
                        break;
                }
                log.debug("before ldap finalizer");
                processInstance = appflowHelper.finalizer(
                        (String) processInstance
                                .getVariable(HelperConstant.METHOD), "ldap: ",
                        processInstance, responseMap);
                processInstance.setVariable("response", responseMap);
            } catch (GenericException ge) {
                log.error("In Ldap GenericException:" + ge.getMessage());
                onErrorProceed = true;
            } catch (Exception ex) {
                log.error("In Ldap Exception :" + ex.getMessage());
                onErrorProceed = true;
            } finally {
                if (onErrorProceed) {
                    log.error("Exception in Generate Processing, But proceeding forward...:");
                }
                manager.completeWorkItem(workItem.getId(), null);
            }
            log.methodExit("GenerateHandler exit");
        } catch (GenericException ge) {
            log.error("In Generate GenericException:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In Generate Exception :" + ex.getMessage());
            throw new GenericException(domain, "ERR-AF-0008",
                    "Exception at Generate :", ex);
        }
    }

    private void getRoles(HashMap<String, Object> storableMap) {
        try {
            log.debug("inside getRoles." + storableMap);
            if (((String) storableMap.get(HelperConstant.ENTITY))
                    .equalsIgnoreCase("Role")) {
                String key = (String) storableMap.get("Site") + "_"
                        + (String) storableMap.get("RoleName");
                String superAdmin = (String) storableMap.get("IsSuperAdmin");
                redisCache.set(key.toLowerCase(), superAdmin.toLowerCase());
                log.debug("roles key inserted:" + key + "-" + superAdmin);
            }
        } catch (Exception ex) {
            log.error("Putting Role into redis exception :" + ex.getMessage());
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
