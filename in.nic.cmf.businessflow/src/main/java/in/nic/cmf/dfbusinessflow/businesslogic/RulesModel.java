package in.nic.cmf.dfbusinessflow.businesslogic;

import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.DirectoryLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public class RulesModel implements BService {
    // private static final RulesModel rModel = new RulesModel();
    private static RulesModel                  rModel;
    private PropertiesUtil                     propUtil;
    private DirectoryLogic                     dirLogic;
    private LogTracer                          log;

    // private Utils util = Utils.getInstance();
    private String                             domain;
    private String                             startupStage      = "";
    private String                             site              = "";
    private String                             id                = "";
    private String                             wName             = "";
    private String                             wType             = "";
    private String                             startType         = "";
    private String                             salience          = "50";
    private String                             stageonlysalience = "10";
    private int                                count             = 0;
    BHelper                                    bhelper;
    private static HashMap<String, RulesModel> hashRulesModel    = new HashMap<String, RulesModel>();

    public RulesModel(String domain) {
        this.domain = domain;
        System.out.println("Rules Model Cons");
        propUtil = PropertiesUtil.getInstanceof(domain, "businessflow");
        dirLogic = DirectoryLogic.getInstanceOf(domain);
        bhelper = BHelper.getInstanceof(domain);
       //  bhelper.readKnowledgeBase(dirLogic.listFilesFromDirectory(propUtil
         //.get("rulepath")));
        bhelper.readKnowledgeBase(dirLogic.listFilesFromDirectory(
        propUtil.get("rulepath"), "workflow"));
    }

    public static RulesModel getInstanceof(String domain) {
        if (!hashRulesModel.containsKey(domain)) {
            hashRulesModel.put(domain, new RulesModel(domain));
        }
        return hashRulesModel.get(domain);
    }

    public boolean generateBusinessRules(String domainName,
            HashMap<String, Object> wflowObj) throws GenericException {
        log.methodEntry("RulesModel.generateBusinessRules");
        List<Map> parentRule = new ArrayList<Map>();
        if (!wflowObj.containsKey("Workflow")) {
            log.info("no workflow collection available");
            if (!bhelper.generateDRL(parentRule, domainName,
                    (String) ((HashMap<String, Object>) wflowObj
                            .get("Workflow")).get("Id"))) {
                throw new GenericException(domain, "ERR-BF-0004");
            }
            return true;
        }
        if (wflowObj.get("Workflow") instanceof HashMap) {

            getRule((HashMap<String, Object>) wflowObj.get("Workflow"),
                    parentRule, domainName);

            if (!bhelper.generateDRL(parentRule, domainName,
                    (String) ((HashMap<String, Object>) wflowObj
                            .get("Workflow")).get("Id"))) {
                throw new GenericException(domain, "ERR-BF-0004");
            }

        } else if (wflowObj.get("Workflow") instanceof List) {
            List<HashMap<String, Object>> workflowlist = (List<HashMap<String, Object>>) wflowObj
                    .get("Workflow");
            for (HashMap<String, Object> eachworkflow : workflowlist) {
                getRule(eachworkflow, parentRule, domainName);

                // if (!bhelper.generateDRL(parentRule, domainName,
                // (String) ((HashMap<String, Object>) wflowObj
                // .get("Workflow")).get("Id"))) {

                if (!bhelper.generateDRL(parentRule, domainName,
                        (String) eachworkflow.get("Id"))) {
                    throw new GenericException(domain, "ERR-BF-0004");
                }

            }
        }
        log.methodExit("RulesModel.generateBusinessRules");
        return true;
    }

    private int getRule(HashMap<String, Object> eachworkflow,
            List<Map> parentRule, String domainName) {
        log.methodEntry("RulesModel.getRule");

        startupStage = "";
        site = (String) eachworkflow.get("Site");
        id = (String) eachworkflow.get("Id");
        wName = (String) eachworkflow.get("WorkflowName");
        wType = (String) eachworkflow.get("WorkflowType");
        startType = propUtil.get("startstagetype");
        log.debug("Domain::" + site + "\tID::" + id + "\tWorkFlowName::"
                + wName + "\tWorkFlowType::" + wType + "\tStartupStage::"
                + startType);

        // boolean isStartupStage = false;

        if (!containsValue(eachworkflow, "WorkflowStages")) {
            log.error("No Stages for given workflow");
            return 0;
        }
        HashMap<String, Object> wStages = (HashMap<String, Object>) eachworkflow
                .get("WorkflowStages");

        if (!containsValue(wStages, "WorkflowStage")) {
            log.error("No Stage for given WorkflowStages");
            return 0;
        }
        boolean isStartStage = false;
        try {
            startupStage = getStartUpStage(
                    (HashMap<String, Object>) eachworkflow
                            .get("WorkflowStages"),
                    startType);
            // Framing Default rule
            Map<String, Object> eachDefault = new HashMap<String, Object>();
            String defaultAction = "";
            String defaultWhen = "";
            Map<String, Object> defaultThen = new HashMap<String, Object>();

            boolean isDefaultMatched = frameAction(wStages, defaultThen,
                    startupStage, startupStage);
            if (null != startupStage && !startupStage.isEmpty()) {
                defaultThen.put("nextstage", startupStage);
            }

            log.debug("Is Default Rule Added:::" + isDefaultMatched);
            eachDefault.put("salience", "salience " + salience);
            defaultThen.put("currentuser", "(String)map.get(\"authusername\")");
            eachDefault.put("then", defaultThen);
            eachDefault.put("halt", "drools.halt()");
            defaultWhen = "Site == '"
                    + site
                    + "',Stage == '"
                    + "',CurrentAction == '"
                    + defaultAction
                    + "',"
                    + "WorkflowId == '"
                    + id
                    + "',"
                    + getContentCreators((HashMap<String, Object>) eachworkflow
                            .get("ContentCreators"));
            eachDefault.put("rulename", "rule '" + domainName + "_"
                    + startupStage + "_" + defaultAction + "_" + id + "_"
                    + wName + "_" + wType + "_" + count++ + "'");
            eachDefault.put("when", defaultWhen);
            parentRule.add(eachDefault);
            count++;
            if (wStages.get("WorkflowStage") instanceof List) {

                List<HashMap<String, Object>> eachStageList = (List<HashMap<String, Object>>) wStages
                        .get("WorkflowStage");

                for (HashMap<String, Object> eachStage : eachStageList) {
                    String stageName = (String) eachStage.get("StageName");

                    if (stageName.equalsIgnoreCase(startupStage)) {
                        isStartStage = true;
                    }
                    iterateStage(eachStage, eachworkflow, isStartStage,
                            parentRule, wStages);

                    isStartStage = false;
                }

            } else if (wStages.get("WorkflowStage") instanceof HashMap) {
                HashMap<String, Object> eachStage = (HashMap<String, Object>) wStages
                        .get("WorkflowStage");

                String stageName = (String) eachStage.get("StageName");
                if (stageName.equalsIgnoreCase(startupStage)) {
                    isStartStage = true;
                }
                iterateStage(eachStage, eachworkflow, isStartStage, parentRule,
                        wStages);

                isStartStage = false;
            }
        } catch (GenericException ge) {
            log.debug("Exception at form BusinessRules: " + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GenericException(domain, "ERR-GEN-0004",
                    "formBusinessRules failed", "domain:" + domainName, ex);
        }

        // FormatFlatten.getInstanceOf().flatten(
        // (HashMap<String, Object>) wflowObj.get("Workflow"), flattened);
        // if (isDefaultMatched) {
        // parentRule.add(eachDefault);
        // }
        Map<String, Object> defaultrule = new HashMap<String, Object>();
        defaultrule.put("rulename", "rule 'Empty Rule'");
        defaultrule.put("salience", "salience " + 0);
        defaultrule.put("defaultwhen", "eval(1==1)");
        String then = "throw new GenericException('" + domain
                + "',\"ERR-BF-0009\")";
        // String then = "throw new GenericException(\"ERR-BF-0009\",'" + domain
        // + "')";
        Map<String, String> thenrule = new HashMap<String, String>();
        thenrule.put("then", then);
        defaultrule.put("then", thenrule);
        // defaultrule.put("then", "");
        parentRule.add(defaultrule);
        return 1;
    }

    private String frameStageOnlyRule(HashMap<String, Object> eachStageFlow) {
        log.methodEntry("RulesModel.frameStageOnlyRule");

        String whenRule = "";
        HashMap<String, String> possActions = new HashMap<String, String>();
        try {
            FormatFlatten.getInstanceOf(domain).flatten(eachStageFlow,
                    possActions, new HashMap<String, Object>());
        } catch (GenericException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }

        whenRule = "(isAuthorisedUser((String)map.get('authusername'),'"
                + possActions.get("PossibleActionUser").toLowerCase() + "')"
                + " || (boolean)map.get('isSuperAdmin'))"
                + "|| isAuthorisedRole((String)map.get('authusername'),'"
                + "')";
        return whenRule;

    }

    private int iterateStage(HashMap<String, Object> eachStage,
            HashMap<String, Object> eachworkflow, boolean isStartStage,
            List<Map> parentRule, HashMap<String, Object> wStages) {
        // Rule without actions
        String stageName = (String) eachStage.get("StageName");
        Map<String, Object> stageonlyrule = new HashMap<String, Object>();

        stageonlyrule.put("rulename", "rule '" + site + "_" + stageName + "_"
                + id + "_" + wName + "_" + wType + "_" + count + "'");
        stageonlyrule.put("salience", "salience " + stageonlysalience);

        String stageonlywhen = "Site == '" + site + "',Stage == '" + stageName
                + "'," + "WorkflowId == '" + id + "',"
                + frameStageOnlyRule(eachStage);
        stageonlyrule.put("when", stageonlywhen);
        stageonlyrule.put("then", "");
        stageonlyrule.put("halt", "drools.halt()");
        parentRule.add(stageonlyrule);

        count++;

        log.debug("Framing ratification rule");

        String ratifyWhen = "";
        Map<String, Object> eachStageRatify = new HashMap<String, Object>();
        Map<String, Object> ratifyThen = new HashMap<String, Object>();
        eachStageRatify.put("salience", "salience " + salience);
        ratifyWhen = "Site == '" + site + "',Stage == '" + stageName
                + "',CurrentAction == '" + propUtil.get("ratifiactionname")
                + "'," + "WorkflowId == '" + id
                + "',(boolean)map.get('isSuperAdmin')";
        eachStageRatify.put("when", ratifyWhen);
        frameActionList(eachStage, ratifyThen, isStartStage, true);

        ratifyThen.put("currentuser", "(String)map.get(\"authusername\")");
        eachStageRatify.put("then", ratifyThen);
        eachStageRatify.put("halt", "drools.halt()");
        eachStageRatify.put("rulename", "rule '" + site + "_" + stageName + "_"
                + propUtil.get("ratifiactionname") + "_" + id + "_" + wName
                + "_" + wType + "_" + count + "'");
        parentRule.add(eachStageRatify);
        count++;

        if (!containsValue(eachStage, "PossibleActions")) {
            log.error("No PossibleActions for given WorkflowStage");
            return 0;
        }
        HashMap<String, Object> possactions = (HashMap<String, Object>) eachStage
                .get("PossibleActions");
        if (!containsValue(possactions, "PossibleAction")) {
            log.error("No PossibleActions for given WorkflowStage");
            return 0;
        }
        if (possactions.get("PossibleAction") instanceof List) {
            List<HashMap<String, Object>> possActionList = (List<HashMap<String, Object>>) possactions
                    .get("PossibleAction");
            for (HashMap<String, Object> possAction : possActionList) {
                Map<String, Object> eachrulemap = new HashMap<String, Object>();
                Map<String, Object> eachThen = new HashMap<String, Object>();
                String eachWhen = "Site == '" + site + "',Stage == '"
                        + (String) eachStage.get("StageName")
                        + "',CurrentAction == '"
                        + (String) possAction.get("PossibleActionName") + "'";
                boolean isStartupStage = false;
                if (possAction.get("NextStage") != null
                        && ((String) possAction.get("NextStage"))
                                .equalsIgnoreCase(startupStage)) {
                    isStartupStage = true;
                }
                // iteratePossAction(possAction, startupStage, wStages);
                if (!isEmpty(wType) && wType.equalsIgnoreCase("Manual")) {
                    eachWhen += "," + "WorkflowId == '" + id + "',"
                            + frameAuthList(possAction, isStartupStage);
                    eachrulemap.put("salience", "salience " + salience);
                    eachrulemap.put("rulename",
                            "rule '" + salience + "_" + stageName + "_"
                                    + possAction.get("PossibleActionName")
                                    + "_" + id + "_" + wName + "_" + wType
                                    + "_" + count + "'");

                }
                eachrulemap.put("when", eachWhen);
                // frameAction(wStages, eachThen,
                // (String) possAction.get("NextStage"), startupStage);
                eachrulemap.put("then",
                        iteratePossAction(possAction, startupStage, wStages));
                eachrulemap.put("halt", "drools.halt()");

                log.debug("EachRuleMap :" + eachrulemap);
                parentRule.add(eachrulemap);
                count++;
            }
        } else if (possactions.get("PossibleAction") instanceof HashMap) {
            HashMap<String, Object> possAction = (HashMap<String, Object>) possactions
                    .get("PossibleAction");
            Map<String, Object> eachrulemap = new HashMap<String, Object>();
            Map<String, Object> eachThen = new HashMap<String, Object>();
            String eachWhen = "Site == '" + site + "',Stage == '"
                    + (String) eachStage.get("StageName")
                    + "',CurrentAction == '"
                    + (String) possAction.get("PossibleActionName") + "'";
            boolean isStartupStage = false;
            if (possAction.get("NextStage") != null
                    && ((String) possAction.get("NextStage"))
                            .equalsIgnoreCase(startupStage)) {
                isStartupStage = true;
            }
            if (!isEmpty(wType) && wType.equalsIgnoreCase("Manual")) {
                eachWhen += "," + "WorkflowId == '" + id + "',"
                        + frameAuthList(possAction, isStartupStage);
                eachrulemap.put("salience", "salience " + salience);
                eachrulemap.put("rulename", "rule '" + site + "_" + stageName
                        + "_" + possAction.get("PossibleActionName") + "_" + id
                        + "_" + wName + "_" + wType + "_" + count + "'");

            }
            eachrulemap.put("when", eachWhen);
            // frameAction(wStages, eachThen,
            // (String) possAction.get("NextStage"), startupStage);
            eachrulemap.put("then",
                    iteratePossAction(possAction, startupStage, wStages));
            eachrulemap.put("halt", "drools.halt()");

            parentRule.add(eachrulemap);
            log.debug("EachRuleMap :" + eachrulemap);
            count++;
        }
        return 1;
    }

    private Map<String, Object> iteratePossAction(
            HashMap<String, Object> possAction, String startupStage,
            HashMap<String, Object> wStages) {

        Map<String, Object> eachThen = new HashMap<String, Object>();
        // HashMap<String, Object> possAction = (HashMap<String, Object>)
        // possactions
        // .get("PossibleAction");
        String nextstage = (String) possAction.get("NextStage");
        String nextStatus = (String) possAction.get("NextContentStatus");
        if (!isEmpty(nextstage)) {

            eachThen.put("nextstage", nextstage);
        }

        if (!isEmpty(nextStatus)) {

            eachThen.put("nextstatus", nextStatus);
        }
        log.debug("Next Stage:::" + nextstage);
        log.debug("Next Status:::" + nextStatus);
        eachThen.put("currentuser", "(String)map.get(\"authusername\")");
        frameAction(wStages, eachThen, (String) possAction.get("NextStage"),
                startupStage);

        return eachThen;

    }

    private boolean frameAction(HashMap<String, Object> wStages,
            Map<String, Object> eachThen, String nextstage, String startupStage) {
        log.methodEntry("RulesModel.frameAction");
        boolean isMatched = false;
        log.debug("nextstage:::" + nextstage + "\n startupTypeFlag:::"
                + startupStage);
        if (wStages.get("WorkflowStage") instanceof List) {
            List<HashMap<String, Object>> eachStageList = (List<HashMap<String, Object>>) wStages
                    .get("WorkflowStage");

            for (HashMap<String, Object> eachStage : eachStageList) {

                if (!isEmpty(((String) eachStage.get("StageName")))
                        && ((String) eachStage.get("StageName"))
                                .equalsIgnoreCase(nextstage)) {
                    isMatched = true;

                    log.debug("inside if loop Stage Match");

                    // If next stage is startup stage then users will be
                    boolean isStartStage = false;
                    if (nextstage.equalsIgnoreCase(startupStage)) {
                        isStartStage = true;
                    }
                    log.debug("eachStage\t" + eachStage);
                    try {
                        isMatched = frameActionList(eachStage, eachThen,
                                isStartStage, false);
                    } catch (GenericException e) {

                        e.printStackTrace();
                    }
                }
            }

        }
        log.methodExit("RulesModel.frameAction");
        return isMatched;
    }

    private boolean frameActionList(HashMap<String, Object> nextStageFlow,
            Map<String, Object> eachThen, boolean startupTypeFlag,
            boolean isratify) {
        log.methodEntry("RulesModel.frameActionList");
        List<HashMap<String, String>> eventlist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> accessDetails = new HashMap<String, String>();

        if (!containsValue(nextStageFlow, "PossibleActions")) {
            log.equals("No Actions for this Stage");
            return true;
        }
        HashMap<String, Object> possactions = (HashMap<String, Object>) nextStageFlow
                .get("PossibleActions");

        if (!containsValue(possactions, "PossibleAction")) {
            log.error("No PossibleAction for given PossibleActions");
            return true;
        }

        if (possactions.get("PossibleAction") instanceof List) {
            List<HashMap<String, Object>> possActionList = (List<HashMap<String, Object>>) possactions
                    .get("PossibleAction");

            for (HashMap<String, Object> possAction : possActionList) {
                accessDetails = new HashMap<String, String>();
                log.debug("Possible Action Name for this Stage"
                        + possAction.get("PossibleActionName"));

                try {
                    FormatFlatten.getInstanceOf(domain).flatten(possAction,
                            accessDetails, new HashMap<String, Object>());
                } catch (GenericException e) {

                    e.printStackTrace();
                } catch (JSONException e) {

                    e.printStackTrace();
                }

                log.debug("accessDetails::" + accessDetails);

                if (startupTypeFlag) {

                    accessDetails.put("PossibleActionUser",
                            "(String)map.get(\"CreatedBy\")");
                    if (!isratify) {
                        if (containsValue(possAction, "NextContentStatus")) {
                            eachThen.put("nextstatus",
                                    possAction.get("NextContentStatus"));
                        }

                    }

                }
                eventlist.add(accessDetails);
            }

        } else {
            accessDetails = new HashMap<String, String>();
            HashMap<String, Object> possAction = (HashMap<String, Object>) possactions
                    .get("PossibleAction");

            try {
                FormatFlatten.getInstanceOf(domain).flatten(possAction,
                        accessDetails, new HashMap<String, Object>());
            } catch (GenericException e) {

                e.printStackTrace();
            } catch (JSONException e) {

                e.printStackTrace();
            }

            if (startupTypeFlag) {

                accessDetails.put("PossibleActionUser",
                        "(String)map.get(\"CreatedBy\")");
                if (!isratify) {
                    if (containsValue(possAction, "NextContentStatus")) {
                        eachThen.put("nextstatus",
                                possAction.get("NextContentStatus"));
                    }

                }

            }
            eventlist.add(accessDetails);
        }

        log.debug("Master::::::::::::::::::::::::::::" + eventlist);
        eachThen.put("nextpossactions", nextPossActions(eventlist));
        accessDetails.clear();
        eventlist.clear();
        return true;

    }

    private String getStartUpStage(HashMap<String, Object> wStages,
            String startType) {
        log.methodEntry("RulerModel:getStartUpStage");
        String tmpStage = "";
        if (wStages.get("WorkflowStage") instanceof List) {

            List<HashMap<String, Object>> eachStageList = (List<HashMap<String, Object>>) wStages
                    .get("WorkflowStage");

            for (HashMap<String, Object> stageEntry : eachStageList) {

                String startStage = (String) stageEntry.get("StageType");

                if (!isEmpty(startStage)
                        && startStage.equalsIgnoreCase(startType)) {
                    tmpStage = (String) stageEntry.get("StageName");
                    break;
                }
            }
        } else if (wStages.get("WorkflowStage") instanceof HashMap) {

            HashMap<String, Object> eachStage = (HashMap<String, Object>) wStages
                    .get("WorkflowStage");
            String startStage = (String) eachStage.get("StageType");

            if (!isEmpty(startStage) && startStage.equalsIgnoreCase(startType)) {
                tmpStage = (String) eachStage.get("StageName");

            }
        }
        log.debug("StartStage for\t" + wName + "::" + tmpStage);
        log.methodExit("RulerModel:getStartUpStage");
        return tmpStage;
    }

    private String nextPossActions(List<HashMap<String, String>> list) {
        // String h =
        // "HashMap<String, String> action = new HashMap<String,String>();";

        log.debug("Checking List of Possible Actions*************************"
                + list);
        // List allowedActions = new ArrayList();
        // allowedActions.add(e)
        String h = "";
        int count = 1;
        if (list.size() > 1) {
            h += "List allowedActions = new ArrayList();\n";
        }
        for (HashMap<String, String> eachaction : list) {
            log.debug("eachaction****************" + eachaction);

            h += "HashMap allowedActions" + count + " = new HashMap();\n";

            h += "\n\t" + "allowedActions" + count
                    + ".put(\"AllowedActionName\"" + ",\""
                    + eachaction.get("PossibleActionName") + "\");\n ";

            if (eachaction.get("PossibleActionUser").equals(
                    "(String)map.get(\"CreatedBy\")")) {

                h += "allowedActions" + count
                        + ".put(\"AssignedToUsers\",getAssignedUser("
                        + eachaction.get("PossibleActionUser") + "));\n\t";
            } else {
                h += "allowedActions" + count
                        + ".put(\"AssignedToUsers\",getAssignedUser(\""
                        + eachaction.get("PossibleActionUser") + "\"));\n\t";
            }
            if (list.size() > 1) {
                h += "allowedActions.add(allowedActions" + count + ");";
            } else {

                h += "map.put(\"AllowedActions\",outw(allowedActions" + count
                        + "));";
            }
            // h +=
            // " action.put(\"AssignedToUsers\",assignedusers); map.put(\"AllowedActions\",outw(action));\n ";
            count++;
        }
        if (list.size() > 1) {
            h += "map.put(\"AllowedActions\",outww(allowedActions));";
        }
        // h += "  map.put(\"AllowedActions\",outw(action));\n ";

        log.debug("H***************Value" + h);
        return h;
    }

    private boolean containsValue(Map<String, Object> object, String key) {

        if (object.containsKey(key) && object.get(key) != null) {
            return true;
        }
        return false;
    }

    private boolean isEmpty(String val) {
        if (val == null || val.isEmpty() || val.equals("null")) {
            return true;
        }
        return false;
    }

    private String frameAuthList(Map<String, Object> possAction,
            boolean isStartStage) {

        String whenRule = "";

        if (isStartStage) {
            return whenRule = "(isAuthorisedUser((String)map.get('authusername'), (String)map.get('CreatedBy'))|| (boolean)map.get('isSuperAdmin'))";
        }

        HashMap<String, String> h = new HashMap<String, String>();

        try {
            FormatFlatten.getInstanceOf(domain).flatten(
                    (HashMap<String, Object>) possAction, h,
                    new HashMap<String, Object>());
        } catch (GenericException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }

        whenRule = "(isAuthorisedUser((String)map.get('authusername'),'"
                + h.get("PossibleActionUser") + "')"
                + " || (boolean)map.get('isSuperAdmin'))"
                + "|| isAuthorisedRole((String)map.get('authusername'),'"
                + "')";

        return whenRule;
    }

    private String getContentCreators(HashMap<String, Object> contentCreators)
            throws GenericException, JSONException {

        log.methodEntry("RulesModel.getContentCreators");
        String users = "";
        HashMap<String, String> cntcreators = new HashMap<String, String>();
        FormatFlatten.getInstanceOf(domain).flatten(contentCreators,
                cntcreators, new HashMap<String, Object>());
        log.debug("Users ::" + users.toLowerCase());
        return "(isAuthorisedUser((String)map.get('authusername'),'"
                + cntcreators.get("ContentCreator").toLowerCase() + "')"
                + " || (boolean)map.get('isSuperAdmin'))";

    }

    public static void main(String args[]) {
        String domainName = "sify.com";
        FormatXml ff = FormatXml.getInstanceof(domainName);
        HashMap<String, String> flattened = new HashMap<String, String>();
        String ss = "<Collections><Count>1</Count><Workflow><Id>mbykytbeabijj</Id><EntityType>Workflow</EntityType>"
                + "<WorkflowName>dontdeletethis</WorkflowName><WorkflowType>Manual</WorkflowType><WorkflowStages><WorkflowStage>"
                + "<StageName>Draft</StageName><StageType>Start</StageType><PossibleActions>"
                + "<PossibleAction><PossibleActionName>Forward</PossibleActionName><PossibleActionUsers><PossibleActionUser>"
                + "creatoruser</PossibleActionUser><PossibleActionUser>suresh</PossibleActionUser></PossibleActionUsers>"
                + "<NextStage>Forwarded</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction><PossibleAction>"
                + "<PossibleActionName>Reject</PossibleActionName><PossibleActionUsers><PossibleActionUser>creatoruser</PossibleActionUser>"
                + "</PossibleActionUsers><NextStage>Rejected</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions>"
                + "</WorkflowStage><WorkflowStage><StageName>Approved</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction>"
                + "<PossibleActionName>Moderate</PossibleActionName><PossibleActionUsers><PossibleActionUser>moderateuser</PossibleActionUser>"
                + "</PossibleActionUsers><NextStage>Moderated</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions>"
                + "</WorkflowStage><WorkflowStage><StageName>Forwarded</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction>"
                + "<PossibleActionName>Approve</PossibleActionName><PossibleActionUsers><PossibleActionUser>approveuser</PossibleActionUser>"
                + "</PossibleActionUsers><NextStage>Approved</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions>"
                + "</WorkflowStage><WorkflowStage><StageName>Moderated</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction>"
                + "<PossibleActionName>Publish</PossibleActionName><PossibleActionUsers><PossibleActionUser>publishuser</PossibleActionUser>"
                + "</PossibleActionUsers><NextStage>Published</NextStage><NextContentStatus>Published</NextContentStatus></PossibleAction>"
                + "<PossibleAction><PossibleActionName>Reject</PossibleActionName><PossibleActionUsers><PossibleActionUser>moderateuser</PossibleActionUser>"
                + "</PossibleActionUsers><NextStage>Rejected</NextStage><NextContentStatus>Draft</NextContentStatus>"
                + "</PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName>Published</StageName>"
                + "<StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Reject</PossibleActionName>"
                + "<PossibleActionUsers><PossibleActionUser>publishuser</PossibleActionUser></PossibleActionUsers><NextStage>Rejected"
                + "</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage>"
                + "<StageName>Rejected</StageName><StageType>End</StageType><PossibleActions><PossibleAction><PossibleActionName>Move to draft"
                + "</PossibleActionName><PossibleActionUsers><PossibleActionUser>publishuser</PossibleActionUser></PossibleActionUsers>"
                + "<NextStage>Draft</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage>"
                + "</WorkflowStages><ContentCreators><ContentCreator>creatoruser</ContentCreator><ContentCreator>suresh</ContentCreator></ContentCreators>"
                + "<CreatedDate>2012-01-24T10:18:49Z</CreatedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy>"
                + "<LastModifiedDate>2012-02-07T20:20:17Z</LastModifiedDate><Site>sify.co.in</Site><Version>1.0</Version></Workflow></Collections>";

        String workflow = "{\"AllowedActions\":{\"AllowedAction\":[{\"AssignedToUsers\":{\"AssignedToUser\":\"sifycoadmin\"},\"AllowedActionName\":\"Forward\"},{\"AssignedToUsers\":{\"AssignedToUser\":\"sifycoadmin\"},\"AllowedActionName\":\"Reject\"}]}}";

        try {

            HashMap<String, String> f = new HashMap<String, String>();
            HashMap<String, Object> h = (HashMap<String, Object>) FormatJson
                    .getInstanceof(domainName).in(workflow);
            System.out.println("Unflatten Map::" + h);
            FormatFlatten.getInstanceOf(domainName).flatten(
                    (HashMap<String, Object>) h.get("AllowedActions"), f,
                    new HashMap<String, Object>());

            System.out.println("Flattened Map::" + f);
            System.out.println("CurrentUser::" + f.get("CurrentUser"));;
            // HashMap<String, Object> toflattened = ff.in(ss);
            // FormatFlatten.getInstanceOf().flatten(toflattened, flattened);
        } catch (GenericException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }

        //
        // List<Map> ss1 = tt.generateBusinessRules("sify.co.in",
        // (HashMap<String, Object>) toflattened.get("Collections"));
        // tt.getSession();
        // for (Map ms : ss1)
        // System.out.println(ms.get("then"));

        // Map<String, Map<String, Object>> param = new HashMap<String,
        // Map<String, Object>>();
        // Map<String, Object> input = new HashMap<String, Object>();
        // HashMap<String, String> userContext = new HashMap<String, String>();
        // userContext.put("authusername", "sifycoadmin");
        // userContext.put("aclrole", "PortalAdmin");
        // input.put("content", ss);
        // input.put("usercontext", userContext);
        // param.put("input", input);
        // RulesModel tt = new RulesModel();
        // tt.setLogTracer(new LogTracer("generate"));
        // tt.process("sify.co.in", param);

    }

    public Map<String, Map<String, Object>> process(String domain,
            Map<String, Map<String, Object>> requestDetails)
            throws GenericException {
        log.methodEntry("RulesModel.process");
        String collection = requestDetails.get("input").get("content")
                .toString();
        HashMap<String, Object> xmlMap = new HashMap<String, Object>();

        xmlMap = FormatXml.getInstanceof(domain).in(collection);

        generateBusinessRules(domain,
                (HashMap<String, Object>) xmlMap.get("Collections"));

        log.debug("List of Files:::"
                + dirLogic.listFilesFromDirectory(propUtil.get("rulepath"),
                        "workflow"));
        bhelper.readKnowledgeBase(dirLogic.listFilesFromDirectory(
                propUtil.get("rulepath"), "workflow"));

        return bhelper.buildResponse(requestDetails);

    }

    public Map<String, Map<String, Object>> addRule(String domain,
            Map<String, Map<String, Object>> requestDetails)
            throws GenericException {
        return process(domain, requestDetails);

    }

    public Map<String, Map<String, Object>> deleteRule(String domain,
            String id, Map<String, Map<String, Object>> requestDetails)
            throws GenericException {

        String path = propUtil.get("dirlocation") + domain
                + propUtil.get("rulepath") + id + ".drl";
        File ruleFile = new File(path);
        if (!ruleFile.delete()) {
            log.debug("Not able to delete the file");
        }
        bhelper.readKnowledgeBase(dirLogic.listFilesFromDirectory(
                propUtil.get("rulepath"), "workflow"));
        log.methodExit("RulesModel.deleteRule");
        return bhelper.buildResponse(requestDetails);
    }

    public void setLogTracer(LogTracer logTracer) {
        this.log = logTracer;
        System.out.println("bhelper:::" + bhelper);
        bhelper.setLog(logTracer);

    }
}
