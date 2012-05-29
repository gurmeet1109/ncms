package in.nic.cmf.ruleengine;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;
import in.nic.cmf.ruleengine.duplication.RedisDuplicationProvider;
import in.nic.cmf.security.owasp.Decode;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.util.DirectoryLogic;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class RuleEngineService {
    // private static final RuleEngineService ruleEngineService = new
    // RuleEngineService();
    private LogTracer                                 log;

    private PropertyManagement                        putil                 = PropertyManagement
                                                                                    .getInstance();
    private StatelessKnowledgeSession                 ksession;
    /** The kbase. */
    private KnowledgeBase                             kbase;
    RedisDuplicationProvider                          redis;
    RuleEngineHelper                                  ruleEngineHelper;
    public static final String                        VALIDATIONS           = "Validations";
    private DirectoryLogic                            dirLogic;
    private ConvertorUtils                            cu;
    private static HashMap<String, RuleEngineService> hashRuleEngineService = new HashMap<String, RuleEngineService>();
    private String                                    domain;

    private String                                    successResponse       = "<Collections><Success>true</Success></Collections>";

    private RuleEngineService(String domain) {
        this.domain = domain;
        cu = ConvertorUtils.getInstanceof(domain);
        dirLogic = DirectoryLogic.getInstanceOf(domain);
        ruleEngineHelper = RuleEngineHelper.getInstanceof(domain);
        redis = RedisDuplicationProvider.getInstanceof(domain);
        // putil = PropertyManagement.getInstanceOf(domain, "ruleengine",
        // "Configuration");
        ksession = readKnowledgeBase("initial",
                ruleEngineHelper.checkDirectory());

    }

    public static RuleEngineService getInstanceof(String domain) {
        if (!hashRuleEngineService.containsKey(domain)) {
            hashRuleEngineService.put(domain, new RuleEngineService(domain));
        }
        return hashRuleEngineService.get(domain);
    }

    public void setLogTracer(final LogTracer log) {
        this.log = log;
        ruleEngineHelper.setLogTracer(log);
        redis.setLogTracer(log);
    }

    public Map<String, Map<String, Object>> deleteByID(String arg0,
            String arg1, String arg2,
            Map<String, Map<String, Object>> parameters) {
        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, Object> result = new HashMap<String, Object>();
        boolean status = false;
        log.debug("Inside deleteByID of RuleEngineService");
        String queryString = "";
        Map<String, Object> inputMap = (Map<String, Object>) parameters
                .get("input");

        log.debug("queryString:" + queryString);
        if (inputMap.containsKey("queryString")) {
            queryString = (String) inputMap.get("queryString");
        }
        log.debug("after assigning qs:" + queryString);
        if (queryString.contains("readknowledge")) {
            log.debug("inside delete statement for readknowledge.");
            status = deleteValidations(arg0, arg1, arg2);
        } else if (queryString.contains("duplication")) {
            log.debug("delete logic for duplication");
            if (deleteDuplicate(arg0, arg1, arg2)) {
                response.put("statusCode", "200");
                result.put("Message", "deleted sucessfully");
                response.put("content", result);
                status = true;
            } else {
                response.put("statusCode", "401");
                result.put("Message", "not deleted");
                response.put("content", result);
                status = false;
            }
        }
        return buildResponseReadKnowledge(parameters, successResponse, status);
    }

    public boolean deleteValidations(String domain, String entityType, String id) {
        boolean result = false;
        try {
            log.methodEntry("deleteACL entry");
            String Path = putil.getProperties(domain, "ruleengine",
                    "Configuration", "dirlocation")
                    + domain
                    + putil.getProperties(domain, "ruleengine",
                            "Configuration", "rulePath")
                    + putil.getProperties(domain, "ruleengine",
                            "Configuration", "folderName") + "/" + id + ".xls";

            File f1 = new File(Path);
            log.info("file path here is:::::::" + Path);
            result = f1.exists();
            if (result) {
                log.info(f1.getAbsolutePath());
                if (f1.delete()) {
                    log.info("delete status ");
                    List<String> rules = ruleEngineHelper.checkDirectory();
                    readKnowledgeBase("addall", rules);
                    result = true;
                }
            }
        } catch (GenericException ge) {
            log.error("Inside deleteDuplicate GE:" + ge.getMessage());
        } catch (Exception ex) {
            log.error("Inside deleteDuplicate EX:" + ex.getMessage());
        }
        return result;

    }

    public Map<String, Map<String, Object>> buildResponse(
            Map<String, Map<String, Object>> parameters,
            Map<String, Object> content, boolean result) {
        log.debug("Inside buildResponse:" + parameters + "..." + content
                + "..." + result);
        Map<String, Object> response = new HashMap<String, Object>();
        String contentRes = ((String) FormatXml.getInstanceof(domain).out(
                (HashMap<String, Object>) content));
        if (result) {
            log.debug("inside result true of build responsemap");
            response.put("statusCode", "200");
            response.put("content", contentRes);
            response.put("success", "true");
            parameters.put("output", response);
        } else {

            log.debug("error xml:" + contentRes);
            response.put("statusCode", "555");
            response.put("success", "false");
            response.put("content", contentRes);
            parameters.put("output", response);
        }
        log.debug("reponse from RE" + parameters);
        System.out.println("00000000000000000:" + parameters);
        return parameters;
    }

    public Map<String, Map<String, Object>> buildResponseReadKnowledge(
            Map<String, Map<String, Object>> parameters, String content,
            boolean result) {
        log.debug("Inside buildResponse:" + parameters + "..." + content
                + "..." + result);
        Map<String, Object> response = new HashMap<String, Object>();

        if (result) {
            log.debug("inside result true of build responsemap");
            response.put("statusCode", "200");
            response.put("success", true);
            response.put("content", content);
            parameters.put("output", response);
        } else {

            log.debug("error xml:" + content);
            response.put("statusCode", "555");
            response.put("success", false);
            response.put("content", content);
            parameters.put("output", response);
        }
        log.debug("reponse from RE" + parameters);
        return parameters;
    }

    public StatelessKnowledgeSession readKnowledgeBase(String action,
            List<String> lstRules) throws GenericException {
        try {
            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
                    .newKnowledgeBuilder();
            DecisionTableConfiguration configuration = KnowledgeBuilderFactory
                    .newDecisionTableConfiguration();
            configuration.setInputType(DecisionTableInputType.XLS);
            // String dirPath = putil.get("dirlocation") + domain
            // + putil.get("directoryPath") + putil.get("dummyxls");
            String dirPath = putil.getProperties(domain, "ruleengine",
                    "Configuration", "dirlocation")
                    + domain
                    + putil.getProperties(domain, "ruleengine",
                            "Configuration", "directoryPath")
                    + putil.getProperties(domain, "ruleengine",
                            "Configuration", "dummyxls");

            System.out.println("dirPath:" + dirPath);
            lstRules.add(dirPath);
            for (String rule : lstRules) {
                try {
                    kbuilder.add(ResourceFactory.newFileResource(rule),
                            ResourceType.DTABLE, configuration);
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }

            try {
                if (kbuilder.hasErrors()) {
                    KnowledgeBuilderErrors errors = kbuilder.getErrors();
                    if (errors.size() > 0) {
                        for (KnowledgeBuilderError error : errors) {
                            int errorLine[] = error.getErrorLines();
                            System.out.println("KBError:" + error.getMessage());
                            System.out
                                    .println("ERROR line in validation excel "
                                            + errorLine[0]);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out
                        .println("Exception occured while getting Builder Errors:"
                                + kbuilder.getErrors().toString());
            }
            kbase = KnowledgeBaseFactory.newKnowledgeBase();
            if (action.equals("addall") || action.equals("add")
                    || action.equals("initial")) {
                System.out.println("inside addall/add/initial");
                kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
            }
            ksession = kbase.newStatelessKnowledgeSession();
        } catch (GenericException ex) {
            System.out.println("Generic Catch : " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("AclExecuteFailure Catch :" + e.getMessage());
        }
        System.out.println("read knowledge complete");
        return ksession;
    }

    public boolean deleteDuplicate(String domainName, String entityType,
            String id) {
        log.info("inside delete dublicate entry");
        log.debug("param:" + domainName + entityType + id);
        boolean result = false;
        try {
            if (entityType != null) {
                result = redis.deleteDuplicate(domainName + "_" + entityType
                        + "_" + id);
            }

        } catch (GenericException ge) {
            log.error("Inside deleteDuplicate GE:" + ge.getMessage());
        } catch (Exception ex) {
            log.error("Inside deleteDuplicate EX:" + ex.getMessage());
        }
        return result;
    }

    public Map<String, Map<String, Object>> readKnowledge(String domain,
            String entity, Map<String, Map<String, Object>> parameters) {
        log.methodEntry("read entry");
        boolean result = false;
        log.debug("param:" + domain + entity);
        log.debug("Input Content:::" + parameters.get("input").get("content"));
        // if ((entity != null) &&
        // (entity.equalsIgnoreCase("AccessControlList"))) {

        Map<String, Object> collections = (Map<String, Object>) FormatXml
                .getInstanceof(domain)
                .in((String) parameters.get("input").get("content"))
                .get("Collections");
        log.debug("IS Content contains files????????????"
                + collections.containsKey("files"));
        log.debug("IS params contains files????????????"
                + parameters.get("input").containsKey("files"));
        if (parameters.get("input").containsKey("files")
                || collections.containsKey("files")) {

            Object binaryval = null;

            if (parameters.get("input").containsKey("files")) {

                binaryval = parameters.get("input").get("files");
            } else {

                binaryval = collections.get("files");
            }

            result = writeAcl(domain, entity, parameters, binaryval);
        }
        return buildResponseReadKnowledge(parameters, successResponse, result);
    }

    public Map<String, Map<String, Object>> post(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, Object> input = parameters.get("input");
            log.info("input is " + input);
            String query = (String) parameters.get("input").get("queryString");
            log.debug("qs:" + parameters.get("input").get("queryString"));
            System.out.println("qs:"
                    + parameters.get("input").get("queryString"));
            /*
             * Map<String, String> queryParams = new HashMap<String, String>();
             * Map<String, String> queryParams1 = (Map<String, String>)
             * parameters .get("input").get("queryParams"); if (queryParams1 !=
             * null) { queryParams = queryParams1; }
             */
            if (query == null) {
                query = "?q=";
            }

            boolean status = false;
            if (query != null) {
                if (query.contains("readknowledge")) {
                    log.debug("read knowledgelogic");
                    return readKnowledge(domain, entity, parameters);
                } else {
                    if (query.contains("duplication")
                            && input.get("content") instanceof String) {
                        log.debug("inside duplication conditiaon");
                        String strCollections = (String) parameters
                                .get("input").get("content");
                        log.debug("after getting content" + strCollections);
                        Map<String, Object> collectionMap = (Map<String, Object>) FormatXml
                                .getInstanceof(domain).in(strCollections)
                                .get("Collections");
                        log.debug("after get collections" + collectionMap);
                        Map<String, Object> tmpdetailList = (Map<String, Object>) collectionMap
                                .get("Duplications");
                        log.debug("tmp:" + tmpdetailList);

                        return buildDuplicationResponse(parameters,
                                generateForDuplication(tmpdetailList));

                    } else if (input.get("content") instanceof String) {
                        log.debug("inside validate condition");
                        String content = (String) input.get("content");
                        log.info("before Content" + content);
                        content = Decode.getInstanceof(domain).decode(content);
                        log.info("Decoded Content" + content);
                        HashMap<String, Object> collections = FormatXml
                                .getInstanceof(domain).in(content);
                        log.info("After format xml>>>" + collections);
                        result = validate(domain, parameters, collections,
                                ruleEngineHelper.getUniqueContent(),
                                new ArrayList<Map>());
                        System.out.println("result here" + result);

                    }
                }
            }
        } catch (GenericException ge) {
            throw ge;
        }

        return getValidationStatus(parameters, result);
    }

    private Map<String, Map<String, Object>> getValidationStatus(
            Map<String, Map<String, Object>> parameters,
            Map<String, Object> result) {
        log.debug("result map is>>>>>>>>>>>>>>>>>>>>>" + result);
        boolean flag = false;
        String validationRes = "";
        Map<String, Object> entities = ((HashMap) ((HashMap<String, Object>) result
                .get("Collections")).get("Validations"));
        for (Entry<String, Object> eachMap : entities.entrySet()) {
            Object value = eachMap.getValue();
            log.debug("eachMap::::" + eachMap);
            if (cu.isHashMap(value)) {
                log.debug("object is hashmap::" + value);
                validationRes += ((Map) value).get("Status").toString()
                        .equalsIgnoreCase("success");
            } else if (cu.isArrayList(value)) {
                log.debug("object is arraylist");
                List<Map<String, Object>> filledMaps = (List<Map<String, Object>>) value;
                // List<Map<String,Object>> filledMaps =((List)
                // (List<Map<String, Object>>) value).get("ValidationStatus");
                for (Map<String, Object> valueMap : filledMaps) {
                    log.debug("value" + valueMap);
                    validationRes += valueMap.get("Status").toString()
                            .equalsIgnoreCase("success");
                }
            }
            if (validationRes.contains("false")) {
                log.debug("Validation has error");
                return buildResponse(parameters, (Map<String, Object>) result,
                        false);
            } else {
                log.debug("Validation sucess...no error in collections");
                return buildResponse(parameters, (Map<String, Object>) result,
                        true);
            }
        }
        /*
         * Map<String, Object> map = FormatXml.getInstance().in( (String)
         * parameters.get("input").get("Collections"));
         * if(result.get("Collections") instanceof List){ List
         * collectionList=(List) result.get("Collections"); for(int
         * i=0;i<collectionList.size();i++){ if(collectionList.get(i)instanceof
         * HashMap){ Map<String, Object> resultMap=(Map) collectionList.get(i);
         * if(((HashMap) ((HashMap) resultMap.get("Collections"))
         * .get("ValidationStatus")).get("Status").toString()
         * .equalsIgnoreCase("success")){ flag=true; return
         * buildResponse(parameters, (Map<String, Object>) resultMap, flag); } }
         * } } else if (result.get("Collections") instanceof HashMap) {
         * if(((HashMap) ((HashMap) result.get("Collections"))
         * .get("ValidationStatus")).get("Status").toString()
         * .equalsIgnoreCase("success")){ flag=true; return
         * buildResponse(parameters, (Map<String, Object>) result, flag); } }
         */
        return buildResponse(parameters, (Map<String, Object>) result, flag);
    }

    private Map<String, Map<String, Object>> buildDuplicationResponse(
            Map<String, Map<String, Object>> parameters, boolean b) {
        try {
            log.debug("inside buildDuplicationResponse function" + parameters);
            Map<String, Object> outputMap = new HashMap<String, Object>();

            log.debug("om:" + outputMap);
            if (b) {
                log.debug("inside true");
                outputMap.put("content", successResponse);
                // build it
                outputMap.put("statusCode", 200);
            } else {
                log.debug("inside false");
                outputMap.put("statusCode", 555);
            }
            parameters.put("output", outputMap);
            log.debug(":::" + parameters);
        } catch (Exception e) {
            log.error("ERROR in buildDuplicationResponse method"
                    + e.getMessage());
        }
        return parameters;
    }

    public HashMap<String, Object> validate(String domain,
            Map<String, Map<String, Object>> arg2,
            HashMap<String, Object> collections, String uniqueDetails,
            List<Map> tmpMap) throws GenericException {
        log.methodEntry("validate entry");
        HashMap<String, Object> errorCollections = new HashMap<String, Object>();
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> resultMap = null;
        try {
            HashMap<String, Object> collHash = (HashMap<String, Object>) FormatFlatten
                    .getInstanceOf(domain, true).in(collections)
                    .get("Collections");
            log.debug("Complete flattern here>" + collHash);
            getUniqueJsonList(collections);
            List<HashMap<String, Object>> validationStatusList = new ArrayList<HashMap<String, Object>>();
            List<HashMap<String, String>> entityList = new ArrayList<HashMap<String, String>>();
            for (Entry<String, Object> e : collHash.entrySet()) {
                String key = (String) e.getKey();
                log.debug("E:" + e);
                if (!key.equals("Count")) {
                    log.debug("No Count");
                    if (!key.equals("files")) {
                        log.debug("No files");
                        if (e.getValue().getClass().getSimpleName()
                                .equalsIgnoreCase("HashMap")) {
                            log.debug("E12");
                            entityList.add((HashMap<String, String>) e
                                    .getValue());
                            log.debug("E1:" + e.getValue());
                        } else if (e.getValue().getClass().getSimpleName()
                                .equalsIgnoreCase("ArrayList")) {
                            log.debug("in list");
                            for (HashMap<String, String> entity : (List<HashMap<String, String>>) e
                                    .getValue()) {
                                log.debug("not added to list for:" + entity);
                                entityList.add(entity);
                            }
                        }
                    }

                }
            }

            List<Map<String, String>> tmpKeys = new ArrayList<Map<String, String>>();
            log.debug("entityList before iteration:" + entityList);
            for (HashMap<String, String> entityHash : entityList) {
                log.debug("E3" + entityHash);
                /*
                 * if (!key.equals("Count")) { }
                 */
                HashMap<String, Object> validationStatus = new HashMap<String, Object>();
                List<HashMap<String, Object>> fieldDetailsList = new ArrayList<HashMap<String, Object>>();
                HashMap<String, Object> errorFields = new HashMap<String, Object>();
                List<String> validateList = validateEntity(
                        entityHash.get("EntityType").toString(), entityHash);
                log.debug("Before filling an validationStatus..");
                validationStatus = ruleEngineHelper.fillValidationStatus(
                        domain, validateList, validationStatus,
                        fieldDetailsList);
                String errorMessage = "";
                try {
                    errorMessage = (String) putil.getProperties(domain,
                            "ruleengine", "Configuration", "duplicatemessage");
                    log.debug("error message from util is:" + errorMessage);
                } catch (Exception ex) {
                    log.error("EX occured while getting duplicate message from util.");
                    throw new GenericException(domain, "ERR-RE-0023",
                            "Accessing properties failed.", "duplicatemessage",
                            ex);
                }
                // for duplications
                // PropertyManagement pmManagement = new
                // PropertyManagement(domain);
                // String value = pmManagement.getProperties(domain,
                // "ruleengine",
                // (String) entityHash.get("EntityType"), "uniquefields");
                String value = putil.getProperties(domain, "ruleengine",
                        (String) entityHash.get("EntityType"), "uniquefields");
                System.out.println("unique fields are>>>>>>>>>" + value);
                log.info("Unique Fields are::::" + value);
                resultMap = redis.validateDuplicate(entityHash, value,
                        new ArrayList<Map>(), errorMessage);
                log.debug("OUTPUT of VALIDATE EXCEL ::" + validationStatus);
                log.debug("OUTPUT of DUPLICATE :: " + resultMap);
                List<Map<String, String>> tmpKeys1 = (List<Map<String, String>>) resultMap
                        .get("tmpDetails");
                log.debug("after validating Duplications.");
                List<HashMap<String, Object>> dupFieldDetails = (List<HashMap<String, Object>>) resultMap
                        .get("FieldDetails");
                log.debug("before iterating fielddetails" + dupFieldDetails);
                // validationStatus.put("Status", "success");
                for (HashMap<String, Object> fd : dupFieldDetails) {
                    log.debug("Inside for loop:" + fd);
                    fieldDetailsList.add(fd);
                    validationStatus.put("Status", "Error");
                }
                errorFields.put("FieldDetails", fieldDetailsList);
                validationStatus.put("ErrorFields", errorFields);
                validationStatus.put("ContentEntityType",
                        entityHash.get("EntityType"));
                validationStatus.put("ContentId", entityHash.get("Id"));
                validationStatusList.add(validationStatus);
                tmpKeys.addAll(tmpKeys1);
            }
            HashMap<String, Object> finalValidationStatus = new HashMap<String, Object>();
            if (validationStatusList.size() > 1) {
                finalValidationStatus.put("ValidationStatus",
                        validationStatusList);
            } else if (validationStatusList.size() == 1) {
                finalValidationStatus.put("ValidationStatus",
                        validationStatusList.get(0));
            }

            Map finalMap = new HashMap();
            log.debug("tmp details map after validate duplicate is>>>"
                    + tmpKeys);
            finalMap.put("Validations", finalValidationStatus);
            finalMap.put("Duplications", formDuplicationKey(tmpKeys));
            result.put("Collections", finalMap);
            System.out.println("finalmap::::::::::::::::::::::::::::::"
                    + result);
            // result.put("tmpdetails", tmpMap);
        } catch (GenericException ge) {
            log.debug("Inside GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("Inside EX:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0019",
                    "unable to validate rules/duplication:" + ex.getMessage(),
                    "Input is collections, uniquedetails and tmpdetails.", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".postReturnsValidationStatus().");
        }
        return result;
    }

    private void getUniqueJsonList(HashMap<String, Object> collections) {
        log.debug("inside getUniqueJsonList");
        Map<String, Object> collHash = (Map<String, Object>) collections
                .get("Collections");
        Map<String, Object> uniqueJsonMapRoot = new HashMap();
        List<Map<String, String>> unquieJsonList = new ArrayList();
        for (Entry<String, Object> e : collHash.entrySet()) {
            log.debug("after for loop map " + e);
            if (e.getValue() instanceof HashMap) {
                Map<String, Object> domainEntityMap = (Map<String, Object>) e
                        .getValue();
                if (domainEntityMap.containsKey("EntityType")) {
                    if (((String) domainEntityMap.get("EntityType"))
                            .equalsIgnoreCase("DomainEntity")) {
                        String result = generateUniqueJson(domainEntityMap);
                        setUniqueJsonInPropertyManagement(result,
                                (String) domainEntityMap.get("EntityName"));
                    }
                }
                log.debug("E1:" + e.getValue());
            } else if (e.getValue() instanceof List) {
                log.debug("in list");
                for (Map<String, Object> entity : (List<Map<String, Object>>) e
                        .getValue()) {
                    log.debug("not added to list for:" + entity);
                    if (entity.containsKey("EntityType")) {
                        if (((String) entity.get("EntityType"))
                                .equalsIgnoreCase("DomainEntity")) {
                            String result = generateUniqueJson(entity);
                            setUniqueJsonInPropertyManagement(result,
                                    (String) entity.get("EntityName"));

                        }
                    }

                }
            }
        }

    }

    private void setUniqueJsonInPropertyManagement(String result,
            String entityName) {
        if ((result != null) && (!result.trim().isEmpty())) {
            Map<String, String> uniqueMap = new HashMap();
            uniqueMap.put("uniqueFields", result);
            System.out.println("before getiing value from pro>>>>>>>"
                    + uniqueMap);
            putil.putProperties(domain, "ruleengine", entityName, uniqueMap);

        }

    }

    private String generateUniqueJson(Map<String, Object> entityHash) {

        String uniqueFields = "";
        if (entityHash.containsKey("Fields")) {
            Map<String, Object> uniqueMap = new HashMap();
            if (entityHash.get("Fields") instanceof HashMap) {
                Map<String, Object> fieldsMap = (Map<String, Object>) entityHash
                        .get("Fields");
                log.debug("Fileds MAp is::::::" + fieldsMap);
                List<Map<String, Object>> fieldsList = new ArrayList();
                if (fieldsMap.containsKey("Field")) {
                    if (fieldsMap.get("Field") instanceof List) {
                        fieldsList = (List<Map<String, Object>>) fieldsMap
                                .get("Field");
                        log.debug("fieldsList  is::::::" + fieldsList);

                    } else if (fieldsMap.get("Field") instanceof HashMap) {
                        fieldsList.add((Map<String, Object>) fieldsMap
                                .get("Field"));
                    }
                    uniqueFields = getUniqueJsonMap(fieldsList);
                }
                System.out.println("uniquefields>>>>>" + uniqueFields);
                log.debug("uniquefields>>>>>" + uniqueFields);

            }
        }
        return uniqueFields;
    }

    private String getUniqueJsonMap(List<Map<String, Object>> fieldsList) {
        String valueList = "";

        List<String> uniqueFields = new ArrayList();
        for (Map<String, Object> fieldMap : fieldsList) {

            Map<String, Object> uniqueMap = new HashMap();
            Map<String, Object> validateConstraintsMap = (Map<String, Object>) fieldMap
                    .get("ValidationConstraints");
            if (validateConstraintsMap != null) {
                List<Map<String, Object>> validateConstraints = new ArrayList<Map<String, Object>>();
                if (validateConstraintsMap.get("ValidationConstraint") instanceof List) {
                    validateConstraints = (List<Map<String, Object>>) validateConstraintsMap
                            .get("ValidationConstraint");
                } else if (validateConstraintsMap.get("ValidationConstraint") instanceof HashMap) {
                    validateConstraints
                            .add((Map<String, Object>) validateConstraintsMap
                                    .get("ValidationConstraint"));
                }

                if ((validateConstraints != null)
                        && (!validateConstraints.equals(null))
                        && (!validateConstraints.isEmpty())) {
                    for (Map<String, Object> validateConstsMap : validateConstraints) {
                        String ruleKey = (String) validateConstsMap
                                .get("ValidationKey");
                        String ruleValue = (String) validateConstsMap
                                .get("ValidationValue");
                        /*
                         * System.out.println(fieldMap.get("FieldName") +
                         * "  Keys and fields>>>>>>>>>>>>>>>>>>>>>>>>" + ruleKey
                         * + "::::::::::::::::::::::::::::::::" + ruleValue);
                         */
                        if ((ruleKey.trim().equalsIgnoreCase("unique"))
                                && (ruleValue.trim().equalsIgnoreCase("true"))) {
                            String fieldName = (String) fieldMap
                                    .get("FieldName");

                            uniqueFields.add(fieldName.trim());

                        } else if ((ruleKey.trim().equalsIgnoreCase("unique"))
                                && (!ruleValue.trim().equalsIgnoreCase("true"))) {
                            String fieldName = (String) fieldMap
                                    .get("FieldName");
                            uniqueFields
                                    .add(fieldName.trim() + ":" + ruleValue);

                        }

                    }

                }

            }

        }
        valueList = getlistAsString(uniqueFields);
        return valueList;

    }

    private String getlistAsString(List<String> valueList) {
        System.out.println("value list is" + valueList);
        String value = "";
        String comma = ",";
        int size = valueList.size();
        for (int i = 0; i < valueList.size(); i++) {
            if (i != size - 1) {
                value += valueList.get(i) + comma;
            } else {
                value += valueList.get(i);
            }

        }
        return value;
    }

    private Map<String, Object> formDuplicationKey(
            List<Map<String, String>> tmpDetails) {
        log.debug("Inside formduplicationKey Method::::" + tmpDetails);
        System.out.println("fdfdf::::" + tmpDetails);
        Map<String, List<Map>> duplications = new HashMap<String, List<Map>>();
        List<Map> duplicationList = new ArrayList<Map>();
        Map<String, Object> duplication = new HashMap<String, Object>();
        List<Map> keysList = new ArrayList();
        for (Map<String, String> dup : tmpDetails) {
            for (Entry<String, String> value : dup.entrySet()) {
                Map<String, String> each = new HashMap<String, String>();
                each.put("Key", value.getKey());
                each.put("Value", value.getValue());
                keysList.add(each);
            }
        }
        duplication.put("Duplication", keysList);
        return duplication;

    }

    public boolean generateForDuplication(Map<String, Object> tmpDetails) {
        log.debug("inside generateForDuplication " + tmpDetails);
        boolean result = false;
        try {
            result = redis.generateDuplications(tmpDetails);
            /*
             * for (Entry<String, Object> eachMap : tmpDetails.entrySet()) {
             * Object value = eachMap.getValue(); log.debug("eachMap::::" +
             * eachMap); if (cu.isHashMap(value)) { log.debug("object is map");
             * result = redis.generateForDuplicationMap(tmpDetails); } else if
             * (cu.isArrayList(value)) { log.debug("object is arraylist");
             * List<Map> lst = (List<Map>) value; for (Map redisResultMap : lst)
             * { result = redis .generateForDuplicationMap(redisResultMap); } }
             * }
             */

            log.debug("generateForDuplication result:" + result);
            System.out.println("Duplication result:" + result);
        } catch (GenericException ge) {
            log.error("Inside generateForDuplication GE:" + ge.getMessage());
        } catch (Exception ex) {
            log.error("Inside generateForDuplication EX:" + ex.getMessage());
        }
        return result;
    }

    public HashMap<String, Object> execute(String entityType,
            HashMap<String, String> a, HashMap<String, Object> validationReport)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + "." + "execute()");
        // HashMap<String, Object> entityHash = new HashMap<String, Object>();
        // entityHash.put(entityType, a);
        log.debug("Entitytype ***** " + entityType);
        try {
            List<Command> commandlist = new ArrayList<Command>();
            commandlist.add(CommandFactory.newSetGlobal("validatereport",
                    validationReport));
            commandlist.add(CommandFactory.newSetGlobal("entitytype",
                    entityType));
            commandlist.add(CommandFactory.newInsert(a));
            ksession.execute(CommandFactory.newBatchExecution(commandlist));
            ksession.setGlobal("validatereport", validationReport);
            log.debug("Inserted an entity & validategreat variable into commandfactory and executed.");
        } catch (GenericException ge) {
            log.error("In GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In Ex:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0001", this.getClass()
                    .getSimpleName() + ":execute()", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName() + "." + "execute()");
        }

        return validationReport;
    }

    public void fileWrite(String domain, Map<String, Object> fileItemMap,
            Map<String, Map<String, Object>> parameters) {
        try {
            log.debug("inside fileWrite: FlattenedMap: entry" + fileItemMap);
            String id = (String) fileItemMap.get("Id");
            log.debug("id is :" + id);
            for (Map.Entry<String, Object> fileEntry : fileItemMap.entrySet()) {
                byte[] data = null;
                log.debug("fileEntry :" + fileEntry.getKey() + "-"
                        + fileEntry.getValue());
                // if (fileEntry.getKey().equals("data")) {

                if (fileEntry.getKey().equalsIgnoreCase("dataurl")
                        || fileEntry.getKey().equalsIgnoreCase("data")) {
                    if (fileEntry.getKey().equalsIgnoreCase("dataurl")) {
                        // parameters.get("input").put("dataurl",
                        // (String) fileEntry.getValue());
                        ServiceClientImpl client = ServiceClientImpl
                                .getInstance(domain, "media");
                        Map<String, Map<String, Object>> reqParameters = new HashMap<String, Map<String, Object>>();
                        Map<String, Object> input = new HashMap<String, Object>();
                        input.put("dataurl", (String) fileEntry.getValue());
                        input.put("userContext",
                                parameters.get("input").get("userContext"));
                        input.put("requestId",
                                parameters.get("input").get("requestId"));
                        reqParameters.put("input", input);

                        Map<String, Map<String, Object>> response = client
                                .findById(domain, "media", "", reqParameters);
                        data = (byte[]) response.get("output").get("content");

                    } else if (fileEntry.getKey().equalsIgnoreCase("data")) {
                        data = (byte[]) fileEntry.getValue();
                    }
                    String createDirectoryPath = putil.getProperties(domain,
                            "ruleengine", "Configuration", "dirlocation")
                            + domain
                            + putil.getProperties(domain, "ruleengine",
                                    "Configuration", "rulePath")
                            + putil.getProperties(domain, "ruleengine",
                                    "Configuration", "folderName");
                    log.debug("DirectoryPath::::" + createDirectoryPath);
                    if (dirLogic
                            .createDirectoryIfNotExists(createDirectoryPath)) {
                        log.info(createDirectoryPath + "directory created");
                        String path = createDirectoryPath + "/" + id;
                        File file = new File(path);
                        if (file.exists()) {
                            log.debug("files exist so deleting : " + path);
                            file.delete();
                        }
                        log.debug("acl path:" + path);
                        FileOutputStream fos = new FileOutputStream(path);
                        fos.write(data);
                    }
                }
            }
        } catch (Exception ex) {
            log.error("file write EX:" + ex.getMessage());
        }
    }

    public void fileWrite(String domain, Map<String, Object> fileItemMap) {
        try {

            String id = (String) fileItemMap.get("id");
            log.debug("id is :" + id);
            for (Map.Entry<String, Object> fileEntry : fileItemMap.entrySet()) {

                if (fileEntry.getKey().equals("data")) {
                    String createDirectoryPath = putil.getProperties(domain,
                            "ruleengine", "Configuration", "dirlocation")
                            + domain
                            + putil.getProperties(domain, "ruleengine",
                                    "Configuration", "rulePath")
                            + putil.getProperties(domain, "ruleengine",
                                    "Configuration", "folderName");

                    if (dirLogic
                            .createDirectoryIfNotExists(createDirectoryPath)) {
                        log.info(createDirectoryPath + "directory created");
                        String path = createDirectoryPath + "/" + id;
                        File file = new File(path);
                        if (file.exists()) {
                            log.debug("files exist so deleting : " + path);
                            file.delete();
                        }
                        log.debug("ruleengine path:" + path);
                        FileOutputStream fos = new FileOutputStream(path);
                        fos.write((byte[]) fileEntry.getValue());
                    }
                }
            }
        } catch (Exception ex) {
            log.error("file write EX:" + ex.getMessage());
        }
    }

    public List<String> validateEntity(String classname,
            HashMap<String, String> object) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".validateEntity()");
        List<String> errorMessages = new ArrayList<String>();
        HashMap<String, Object> validationReport = new HashMap<String, Object>();
        try {
            log.debug("before call excute method in validateEntity method");

            validationReport = execute(classname, object, validationReport);
            log.debug("after call excute method in validateEntity method and reponse map here>>>"
                    + validationReport);
            if (validationReport.containsKey("Message")) {
                List<String> messageList = (List<String>) validationReport
                        .get("Message");
                if (!messageList.isEmpty()) {
                    for (String eachError : messageList) {
                        errorMessages.add(eachError);
                    }
                    log.debug("ErrorMessage : " + errorMessages);
                }
            }
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            log.error("In Ex:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0001", this.getClass()
                    .getSimpleName() + ":validateEntity", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".validateEntity()");
        }
        return errorMessages;

    }

    public boolean writeAcl(String domain, String entity,
            Map<String, Map<String, Object>> parameters, Object binaryval) {
        log.methodEntry("writeAcl entry:" + parameters);

        try {
            ConvertorUtils cu = ConvertorUtils.getInstanceof(domain);

            // if (cu.isArrayList(parameters.get("input").get("files"))) {
            if (cu.isArrayList(binaryval)) {
                log.debug("inside arraylist condition");
                // List<Map<String, Object>> lstfiles = (List<Map<String,
                // Object>>) parameters
                // .get("input").get("files");

                List<Map<String, Object>> lstfiles = (List<Map<String, Object>>) binaryval;
                for (Map<String, Object> fileItemMap : lstfiles) {
                    log.debug("inside for loop : " + fileItemMap);
                    fileWrite(domain, fileItemMap, parameters);
                }
            } else {
                log.debug("inside else - map condition");
                // Map<String, Object> fileItemMap = (Map<String, Object>)
                // parameters
                // .get("input").get("files");
                Map<String, Object> fileItemMap = (Map<String, Object>) binaryval;
                fileWrite(domain, fileItemMap, parameters);
            }

            List<String> rules = ruleEngineHelper.checkDirectory();
            log.info("List of file for read knowledge is " + rules);
            readKnowledgeBase("addall", rules);
            log.methodExit("writeAcl exit");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Write ACL EX:" + ex.getMessage());
        }

        return true;
    }

}
