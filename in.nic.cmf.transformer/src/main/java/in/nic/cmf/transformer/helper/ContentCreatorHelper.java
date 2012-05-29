package in.nic.cmf.transformer.helper;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentCreatorHelper {
    /** The log. */
    private LogTracer                                    log;
    private String                                       domain;
    private static HashMap<String, ContentCreatorHelper> hashContentCreatorHelper = new HashMap<String, ContentCreatorHelper>();

    private ContentCreatorHelper(String domain) {
        this.domain = domain;
        setLogger(domain);
    }

    public static ContentCreatorHelper getInstance(String domain) {
        if (!hashContentCreatorHelper.containsKey(domain)) {
            hashContentCreatorHelper.put(domain, new ContentCreatorHelper(
                    domain));
        }
        return hashContentCreatorHelper.get(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "transformer");
    }

    public Map<String, Object> addWorkflowContentCreators(
            Map<String, Object> domainEntity) {
        log.methodEntry(this.getClass().getSimpleName()
                + ".addWorkflowContentCreators()");
        log.debug("Collections::::" + domainEntity);
        try {
            String entityType = (String) domainEntity.get("EntityType");
            log.debug("entityType::::" + entityType);
            List<String> users = new ArrayList<String>();
            if (!entityType.equalsIgnoreCase("workflow")) {
                return domainEntity;
            }
            // Workflow workflowObject = (Workflow) workflow;

            String startType = "";

            String startStageName = "";
            HashMap<String, Object> wfStages = (HashMap<String, Object>) domainEntity
                    .get("WorkflowStages");

            if (wfStages.get("WorkflowStage") instanceof ArrayList) {
                List<HashMap<String, Object>> wfStage = (List<HashMap<String, Object>>) wfStages
                        .get("WorkflowStage");

                for (HashMap eachStage : wfStage) {
                    startType = (String) eachStage.get("StageType");

                    if (startType != null
                            && startType.equalsIgnoreCase("Start")) {
                        log.debug("Each Stage:::" + eachStage);
                        startStageName = (String) eachStage.get("StageName");

                        break;
                    }
                }
                log.debug("Startup Type:::" + startType);
                log.debug("Startup Stage:::" + startStageName);
                for (HashMap eachStage : wfStage) {
                    String stageName = (String) eachStage.get("StageName");

                    if (stageName != null
                            && stageName.equalsIgnoreCase(startStageName)) {
                        users = getContentCreators((Map<String, Object>) eachStage
                                .get("PossibleActions"));
                    }
                }

            } else if (wfStages.get("WorkflowStage") instanceof HashMap) {
                HashMap<String, Object> wfStage = (HashMap<String, Object>) wfStages
                        .get("WorkflowStage");
                startType = (String) wfStage.get("StartType");

                if (startType != null && startType.equalsIgnoreCase("Start")) {
                    startStageName = (String) wfStage.get("StageName");
                }
                String stageName = (String) wfStage.get("StageName");
                if (stageName != null
                        && stageName.equalsIgnoreCase(startStageName)) {
                    users = getContentCreators((Map<String, Object>) wfStage
                            .get("PossibleActions"));
                }
            }

            HashMap<String, Object> contentCreator = new HashMap<String, Object>();
            contentCreator.put("ContentCreator", users);
            domainEntity.put("ContentCreators", contentCreator);

        } catch (Exception e) {
            // log.error(e.getMessage());
            throw new GenericException("ERR-DS-0000", this.getClass()
                    .getSimpleName() + ":addWorkflowContentCreators()", e);
        }
        return domainEntity;
    }

    private ArrayList<String> getContentCreators(Map<String, Object> possActions)
            throws Exception {

        Map<String, String> creators = new HashMap<String, String>();
        ArrayList<String> users = new ArrayList<String>();
        if (possActions.get("PossibleAction") instanceof ArrayList) {
            List<HashMap<String, Object>> possAction = (List<HashMap<String, Object>>) possActions
                    .get("PossibleAction");
            for (HashMap eachPossAction : possAction) {
                if (eachPossAction.get("PossibleActionUsers") instanceof ArrayList) {
                    List<HashMap<String, String>> possActionUsers = (List) eachPossAction
                            .get("PossibleActionUsers");
                    for (HashMap pssUser : possActionUsers) {
                        users.add((String) pssUser.get("PossibleActionUser"));
                    }

                } else if (eachPossAction.get("PossibleActionUsers") instanceof HashMap) {
                    HashMap<String, Object> possActionUsers = (HashMap<String, Object>) eachPossAction
                            .get("PossibleActionUsers");

                    users.add((String) possActionUsers
                            .get("PossibleActionUser"));
                }
            }

        } else if (possActions.get("PossibleAction") instanceof HashMap) {

            HashMap<String, Object> possAction = (HashMap<String, Object>) possActions
                    .get("PossibleAction");

            if (possAction.get("PossibleActionUsers") instanceof ArrayList) {
                List<HashMap<String, String>> possActionUsers = (List) possAction
                        .get("PossibleActionUsers");
                for (HashMap pssUser : possActionUsers) {
                    users.add((String) pssUser.get("PossibleActionUser"));
                }
                // users.add((String)
                // eachPossAction.get("PossibleActionUser"));
            } else if (possAction.get("PossibleActionUsers") instanceof HashMap) {
                HashMap<String, Object> possActionUsers = (HashMap<String, Object>) possAction
                        .get("PossibleActionUsers");

                users.add((String) possActionUsers.get("PossibleActionUser"));
            }
        }
        return users;
    }
    // private Storable addWorkflowContentCreators(Storable workflow)
    // throws GenericException {
    // log.methodEntry(this.getClass().getSimpleName()
    // + ".addWorkflowContentCreators()");
    // long methodStart = helper.getTime();
    // Workflow workflowObject = (Workflow) workflow;
    // try {
    // ArrayList wfStages = (ArrayList) workflowObject.getWorkflowStages()
    // .getWorkflowStage();
    // ContentCreators creators = new ContentCreators();
    // ArrayList users = new ArrayList();
    //
    // String creatorStage = "";
    // for (int i = 0; i < wfStages.size(); i++) {
    // WorkflowStage stage = (WorkflowStage) wfStages.get(i);
    // if (stage.getStageType().toLowerCase().equals("start")) {
    //
    // creatorStage = stage.getStageName();
    // break;
    // }
    // }
    // if (!creatorStage.equals(null)) {
    // for (int i = 0; i < wfStages.size(); i++) {
    // WorkflowStage stage = (WorkflowStage) wfStages.get(i);
    // if (stage.getStageName().equals(creatorStage)) {
    // ArrayList possibleactions = (ArrayList) stage
    // .getPossibleActions().getPossibleAction();
    // for (int j = 0; j < possibleactions.size(); j++) {
    // PossibleAction possibleaction = (PossibleAction) possibleactions
    // .get(j);
    // List userList = possibleaction
    // .getPossibleActionUsers()
    // .getPossibleActionUser();
    // for (int k = 0; k < userList.size(); k++) {
    // users.add(userList.get(k));
    // }
    // }
    // }
    // }
    // }
    // HashSet hs = new HashSet();
    // hs.addAll(users);
    // users.clear();
    // users.addAll(hs);
    // creators.setContentCreator(users);
    // workflowObject.setContentCreators(creators);
    // helper.doLog(methodStart, "addWorkflowContentCreators taken");
    // } catch (Exception e) {
    // log.error(e.getMessage());
    // throw new GenericException("ERR-DS-0000", this.getClass()
    // .getSimpleName() + ":addWorkflowContentCreators()", e);
    // }
    // return (Storable) workflowObject;
    // }
}
