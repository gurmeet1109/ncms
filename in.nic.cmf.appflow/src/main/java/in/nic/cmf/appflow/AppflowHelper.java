package in.nic.cmf.appflow;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;

/**
 * The Class AppflowHelper.
 */
public class AppflowHelper {

    /** The log. */
    private LogTracer                             log;

    /** The Constant appflowHelper. */
    // private final static AppflowHelper appflowHelper = new AppflowHelper();
    private static HashMap<String, AppflowHelper> hashAppflowHelper = new HashMap<String, AppflowHelper>();
    private String                                domain;

    /**
     * Gets the single instance of AppflowHelper.
     * @return single instance of AppflowHelper
     */
    // public static AppflowHelper getInstance() {
    // return appflowHelper;
    // }
    private AppflowHelper(String domain) {
        this.domain = domain;
        // this.log=new LogTracer(domain, "appflow");
    }

    public static AppflowHelper getInstanceof(String domain) {
        if (!hashAppflowHelper.containsKey(domain)) {
            hashAppflowHelper.put(domain, new AppflowHelper(domain));
        }
        return hashAppflowHelper.get(domain);
    }

    /**
     * Sets the log.
     * @param log
     *            the new log
     */
    public void setLog(LogTracer log) {
        this.log = log;
    }

    /**
     * Checks if is empty.
     * @param value
     *            the value
     * @return true, if is empty
     */
    public boolean isEmpty(String value) {
        if (value != null) {
            value = value.trim();
            log.debug("In isEmpty : " + value);
            return value.isEmpty();
        }
        return true;
    }

    /**
     * Gets the params.
     * @param method
     *            the method
     * @param domainName
     *            the domain name
     * @param entityType
     *            the entity type
     * @param id
     *            the id
     * @param subQuery
     *            the sub query
     * @param requestDetails
     *            the request details
     * @return the params
     */
    public Map<String, Object> getParams(String method, String domainName,
            String entityType, String id, String subQuery,
            Map<String, Map<String, Object>> requestDetails) {
        log.methodEntry("getParams entry:" + requestDetails);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("method", method.toLowerCase());
        params.put("domainName", domainName);
        params.put("entityType", entityType);
        params.put("id", id);
        params.put("subQuery", subQuery);
        log.debug("before putting files:" + params);
        if (requestDetails.get("input").containsKey("files")) {
            List<Map<String, Object>> fileMap = new ArrayList<Map<String, Object>>();
            fileMap = (List<Map<String, Object>>) requestDetails.get("input")
                    .get("files");
            params.put("files", fileMap);
            log.debug("files are : " + params);
        }

        requestDetails.get("input").remove("files");
        log.debug("files removed from requestDetails: : " + requestDetails);
        params.put("requestDetails", requestDetails);
        params.put("status", "");

        log.debug("Get Params:" + params);
        log.methodExit("getParams exit");
        return params;
    }

    /**
     * Purpose : Whenever any handler triggers, process instance are initialized
     * here.
     * @param ksession
     *            the ksession
     * @return the workflow process instance
     */
    public WorkflowProcessInstance initializer(StatefulKnowledgeSession ksession) {
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) ksession
                .getProcessInstance(1);
        if (processInstance == null) {
            throw new GenericException(domain, "ERR-AF-0004",
                    "In HandlersHelper.initializer condition.",
                    "Input is ksession.");
        }
        return processInstance;
    }

    /**
     * Finalizer.
     * @param method
     *            the method
     * @param componentNameAndStatus
     *            the component name and status
     * @param processInstance
     *            the process instance
     * @param responseMap
     *            the response map
     * @return the workflow process instance
     */
    public WorkflowProcessInstance finalizer(String method,
            String componentNameAndStatus,
            WorkflowProcessInstance processInstance,
            Map<String, Map<String, Object>> responseMap) {
        try {
            Map<String, Object> outputMap = responseMap.get("output");
            if (outputMap.get("statusCode").toString().equals("200")) {
                componentNameAndStatus += "success; ";
            } else {
                componentNameAndStatus += "failure; ";
            }
            responseMap.get("output").put(
                    "workflowStatus",
                    processInstance.getVariable("status")
                            + componentNameAndStatus);
            processInstance.setVariable("status", responseMap.get("output")
                    .get("workflowStatus"));
            processInstance.setVariable("requestDetails", responseMap);
            return processInstance;
        } catch (GenericException ge) {
            log.error("In Generate GenericException:" + ge.getMessage());
            return processInstance;
        } catch (Exception ex) {
            log.error("In Generate Exception :" + ex.getMessage());
            return processInstance;
        }
    }

    /*
     * needs to be deleted
     * //responseMap.put("workflowStatus", processInstance.getVariable("status")
     * + componentNameAndStatus);
     * //log.debug("Workflow Status: " + responseMap.get("workflowStatus"));
     * //log.methodExit("HandlersHelper.finalizer exit");
     */

    /**
     * The Enum SELECTFLOW.
     */
    public enum SELECTFLOW {

        /** The get. */
        get,
        /** The post. */
        post,
        /** The postall. */
        postall,
        /** The delete. */
        delete,
        /** The getfromdms. */
        getfromdms,
        /** The posttodms. */
        posttodms,
        /** The getfromse. */
        getfromse,
        /** The posttomde. */
        posttomde, getdms,
        /** The novalue. */
        novalue;

        /**
         * To flow.
         * @param flowname
         *            the flowname
         * @return the SELECTFLOW
         */
        public static SELECTFLOW toFlow(String flowname) {
            try {
                return valueOf(flowname);
            } catch (Exception ex) {
                return novalue;
            }
        }
    }

    /**
     * The Class HelperConstant.
     */
    public final class HelperConstant {

        /** The Constant DOMAIN. */
        public static final String DOMAIN         = "domainName";

        /** The Constant ENTITY. */
        public static final String ENTITY         = "entityType";

        /** The Constant ID. */
        public static final String ID             = "id";

        /** The Constant SUBQUERY. */
        public static final String SUBQUERY       = "subQuery";

        /** The Constant REQDETAILS. */
        public static final String REQDETAILS     = "requestDetails";

        /** The Constant METHOD. */
        public static final String METHOD         = "method";
        public static final String READ_KNOW_FLAG = "readknowledge=true";
        public static final String DUP_FLAG       = "duplication=true";

    }
}
