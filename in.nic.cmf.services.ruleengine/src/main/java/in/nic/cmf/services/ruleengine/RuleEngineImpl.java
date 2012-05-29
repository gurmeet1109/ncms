package in.nic.cmf.services.ruleengine;

import in.nic.cmf.domain.Collections;
import in.nic.cmf.domain.ErrorFields;
import in.nic.cmf.domain.FieldDetails;
import in.nic.cmf.domain.Storable;
import in.nic.cmf.domain.ValidationReport;
import in.nic.cmf.domain.ValidationStatus;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.services.ruleengine.duplication.RedisDuplicationProvider;
import in.nic.cmf.util.ProcessEntity;
import in.nic.cmf.util.Utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * The Class RuleEngineImpl.
 */
public class RuleEngineImpl implements RuleEngine {
	

    /** The str error. */
    String                           strError      = "";

    /** The putil. */
    PropertiesUtil                   putil         = new PropertiesUtil("ruleengine");

    /** The log. */
    public static LogTracer          log           = new LogTracer("RuleEngineTraceLogger");

    /** The rule impl. */
    private static RuleEngineImpl    ruleImpl      = null;

    /** The ksession. */
    private StatelessKnowledgeSession ksession;

    /** The kbase. */
    private KnowledgeBase            kbase;

    /** The format. 
    private final FormatJson         format        = FormatJson.getInstance();*/

    /** The dup helper. */
    RedisDuplicationProvider         redis         = RedisDuplicationProvider
                                                           .getInstance();

    /** The process entity. */
    ProcessEntity                    processEntity = new ProcessEntity();

    /**
     * Instantiates a new rule engine impl.
     */
    private RuleEngineImpl() {
        log.debug("Inside RuleEngineImpl parameterless constructor.");
        ksession = readKnowlegeBase("initial", new ArrayList<String>());
    }

    /**
     * Instantiates a new rule engine impl.
     * 
     * @param fromComponent
     *            the from component
     */
    private RuleEngineImpl(String fromComponent) {
        log.debug("Inside RuleEngineImpl constructor with param:"
                + fromComponent);
    }

    /**
     * Gets the single instance of RuleEngineImpl.
     * 
     * @return single instance of RuleEngineImpl
     */
    public static RuleEngineImpl getInstance() {
        if (ruleImpl == null) {
            ruleImpl = new RuleEngineImpl();
        }
        return ruleImpl;
    }

    /**
     * Gets the single instance of RuleEngineImpl.
     * 
     * @param fromComponent
     *            the from component
     * @return single instance of RuleEngineImpl
     */
    public static RuleEngineImpl getInstance(String fromComponent) {
        if (ruleImpl == null) {
            ruleImpl = new RuleEngineImpl(fromComponent);
        }
        return ruleImpl;
    }


    // this validate method is called after businessflow along with duplication
    // data maintanence.
    public Map<String,Object> validate(Collections<Storable> collections,
            String uniqueDetails, List<Map> tmpMap) throws GenericException {
        log.methodEntry("validate entry");
        Collections<ValidationStatus> errorCollections = new Collections<ValidationStatus>();
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            for (Storable eachObject : collections.getCollection()) {
                ValidationStatus validationStatus = new ValidationStatus();
                List<FieldDetails> fieldDetailsList = new ArrayList<FieldDetails>();
                ErrorFields errorFields = new ErrorFields();
                List<String> validateList = validateEntity(eachObject
                        .getClass().getSimpleName(), eachObject);
                log.debug("Before filling an validationStatus..");
                validationStatus = fillValidationStatus(validateList,
                        validationStatus, fieldDetailsList);

                String errorMessage = "";
                try {
                    errorMessage = (String) putil
                            .getProperty("duplicatemessage");
                    log.debug("error message from util is:" + errorMessage);
                } catch (Exception ex) {
                    log.error("EX occured while getting duplicate message from util.");
                    throw new GenericException("ERR-RE-0023",
                            "Accessing properties failed.",
                            "duplicatemessage", ex);
                }

                // for duplications
                Map resultMap = redis.validateDuplicate(eachObject,
                        uniqueDetails, tmpMap, errorMessage);
                log.debug("after validating Duplications.");
                List<FieldDetails> dupFieldDetails = (List<FieldDetails>) resultMap
                        .get("fielddetails");

                log.debug("before iterating fielddetails");
                for (FieldDetails fd : dupFieldDetails) {
                    fieldDetailsList.add(fd);
                    validationStatus.setStatus("Error");
                }
                errorFields.setFieldDetails(fieldDetailsList);
                validationStatus.setErrorFields(errorFields);
                validationStatus.setContentEntityType(eachObject
                        .getEntityType());
                validationStatus.setContentId(eachObject.getId());
                errorCollections.add(validationStatus);
            }
            result.put("errorcollections", errorCollections);
            result.put("tmpdetails", tmpMap);
        } catch (GenericException ge) {
            log.debug("Inside GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("Inside EX:" + ex.getMessage());
            throw new GenericException("ERR-RE-0019",
                    "unable to validate rules/duplication:" + ex.getMessage(),
                    "Input is collections, uniquedetails and tmpdetails.", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".postReturnsValidationStatus().");
        }
        return result;
    }

    public boolean deleteDuplicate(String domainName, String entityType,
            String id) {
        boolean result = false;
        try {
            result = redis.deleteDuplicate(domainName + "_" + entityType + "_"
                    + id);
        } catch (GenericException ge) {
            log.error("Inside deleteDuplicate GE:" + ge.getMessage());
        } catch (Exception ex) {
            log.error("Inside deleteDuplicate EX:" + ex.getMessage());
        }
        return result;
    }

    public boolean generateForDuplication(List<Map> tmpDetails) {
        boolean result = false;
        try {
            result = redis.generateForDuplication(tmpDetails);
            log.debug("generateForDuplication result:" + result);
            System.out.println("Duplication result:" + result);
        } catch (GenericException ge) {
            log.error("Inside generateForDuplication GE:" + ge.getMessage());
        } catch (Exception ex) {
            log.error("Inside generateForDuplication EX:" + ex.getMessage());
        }
        return result;
    }

    private ValidationStatus fillValidationStatus(List<String> validateList,
            ValidationStatus validationStatus,
            List<FieldDetails> fieldDetailsList) {
        try {
            FieldDetails fieldDetails = null;
            if (validateList.size() > 0) {
                for (String strEachField : validateList) {
                    fieldDetails = new FieldDetails();
                    if (strEachField.contains(":")) {
                        String[] fieldSplit = strEachField.split(":");
                        fieldDetails.setFieldName(fieldSplit[0]);
                        fieldDetails.setFieldError(fieldSplit[1]);
                        System.out.println(fieldSplit[0] + "-" + fieldSplit[1]);
                        fieldDetailsList.add(fieldDetails);
                        validationStatus.setStatus("Error");
                    }
                    log.debug("Eachfield:" + strEachField);
                }
                
                log.debug("validation has errors.");
            } else {
                validationStatus.setStatus("Success");
                log.debug("Validation is successful");
            }
        } catch (GenericException ge) {
            throw new GenericException("ERR-RE-0018",
                    "Unable to fill Validation Status:" + ge.getMessage(),
                    "Input is validatelist", ge);
        }
        return validationStatus;
    }

    /**
     * Read knowlege base.
     * 
     * @param action
     *            the action
     * @param rules
     *            the rules
     * @return the stateless knowledge session
     * @throws GenericException
     *             the generic exception
     */
    public StatelessKnowledgeSession readKnowlegeBase(String action,
            List<String> rules) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".readKnowlegeBase()");
        try {
            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
                    .newKnowledgeBuilder();
            if (action.equals("addall") || action.equals("initial")) {
                log.debug("inside addall / initial condition.");
                DecisionTableConfiguration config = KnowledgeBuilderFactory
                        .newDecisionTableConfiguration();
                config.setInputType(DecisionTableInputType.XLS);
                kbase = KnowledgeBaseFactory.newKnowledgeBase();
                log.debug("Configured to load excels. Now loading the dummy drl.");
                kbuilder.add(ResourceFactory.newClassPathResource("dummy.drl"),
                        ResourceType.DRL);

                /*kbuilder.add(ResourceFactory.newClassPathResource("now.xls"),
                        ResourceType.DTABLE, config);
                  kbuilder.add(
                  ResourceFactory.newClassPathResource("metadatavalidation.xls"
                  ), ResourceType.DTABLE, config);
                 

                final SpreadsheetCompiler converter = new SpreadsheetCompiler();
                final String drl = converter.compile(ResourceFactory
                        .newClassPathResource("now.xls").getInputStream(),
                        InputType.XLS);
                System.out.println(drl);
				log.debug(drl);*/
                
                for (String rule : rules) {
                    try {
                        if (rule.contains(".xls")) {
                            log.debug("Incoming RulePath has xls file:" + rule);
                            try {
                                kbuilder.add(
                                        ResourceFactory.newFileResource(rule),
                                        ResourceType.DTABLE, config);
                            } catch (Exception ex) {
                                log.error("In Ex - xls :" + ex.getMessage());
                            }
                        }
                    } catch (Exception ex) {
                        log.error("In Ex : kbuilder addall : "
                                + ex.getMessage());
                    }
                }
                if (validateBuilder(kbuilder)) {
                    log.error("Validating the builder is successfull in addall/initial condition.");
                }
                kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
                log.debug("ksession updated - addall/initial");
            }
            if (action.equals("add")) {
                log.debug("inside add condition.");
                for (String rule : rules) {
                    log.debug("Rule is :" + rule);
                    try {
                        if (!rule.trim().equals("")) {
                            log.debug("rule is not empty, So adding the drl file:"
                                    + rule);
                            kbuilder.add(ResourceFactory.newFileResource(rule),
                                    ResourceType.DRL);
                        }
                    } catch (Exception ex) {
                        log.debug("In EX : add condition." + ex.getMessage());
                    }
                }
                if (validateBuilder(kbuilder)) {
                    log.error("Validating the builder failed - add condition.");
                }
                kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
                log.debug("ksession updated - add");
            }
            if (action.equals("addrule")) {
                log.debug("inside addrule condition.");
                for (String rule : rules) {
                    log.debug("Incoming Rule :" + rule);
                    try {
                        kbuilder.add(ResourceFactory
                                .newReaderResource(new StringReader(rule)),
                                ResourceType.DRL);
                    } catch (Exception ex) {
                        log.debug("In Ex : :addrule condition."
                                + ex.getMessage());
                    }
                }
                if (validateBuilder(kbuilder)) {
                    log.error("Validating the builder failed - addrule condition.");
                }
                kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
                log.debug("ksession updated - addrule");
            }
            if (action.equals("remove")) {
                log.debug("inside remove condition.");
                for (String rule : rules) {
                    log.debug("RuleName to remove: " + rule);
                    try {
                        kbase.removeRule("in.nic.cmf.services.ruleengine", rule);
                    } catch (Exception ex) {
                        log.debug("In Ex - remove : " + ex.getMessage());
                    }
                }
                log.debug("remove condition exit.");
            }
            ksession = kbase.newStatelessKnowledgeSession();
            log.debug("ksession updated - end of readknowledge.");
        } catch (GenericException ge) {
            log.error("In GE : " + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("Unable to create knowledge Base : " + ex.getMessage());
            throw new GenericException("ERR-RE-0003",
                    "Unable to readknowledge:" + ex.getMessage(),
                    "Input is validatelist", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".readKnowlegeBase()");
        }
        return ksession;
    }

    /**
     * Validate builder.
     * 
     * @param kbuilder
     *            the kbuilder
     * @return true, if successful
     */
    private boolean validateBuilder(KnowledgeBuilder kbuilder) {
        log.methodEntry("validateBuilder entry");
        try {
            if (kbuilder.hasErrors()) {
                KnowledgeBuilderErrors errors = kbuilder.getErrors();
                if (errors.size() > 0) {
                    for (KnowledgeBuilderError error : errors) {
                        System.err.println(error);
                        log.debug("KBError:" + error.getMessage());
                    }
                }
                log.debug("Builder Errors:" + kbuilder.getErrors().toString());
            }
            log.methodExit("validateBuilder exit.");
            return true;
        } catch (GenericException ge) {
            log.error("In GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In EX:" + ex.getMessage());
            throw new GenericException("ERR-RE-0016",
                    "validateBuilder failure:" + ex.getMessage(),
                    "Input is kbuilder.", ex);
        }

    }

    /**
     * Execute.
     * 
     * @param a
     *            the a
     * @param validationReport
     *            the validation report
     * @return the validation report
     * @throws GenericException
     *             the generic exception
     */
    public ValidationReport execute(Object a,
            ValidationReport validationReport)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + "." + "execute()");
        try {
            List<Command> commandlist = new ArrayList<Command>();
            commandlist.add(CommandFactory.newSetGlobal("validatereport",
            validationReport)); commandlist.add(CommandFactory.newInsert(a));
            ksession.execute(CommandFactory.newBatchExecution(commandlist));
            ksession.setGlobal("validatereport", validationReport);
            log.debug("Inserted an entity & validategreat variable into commandfactory and executed.");
        } catch (GenericException ge) {
            log.error("In GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In Ex:" + ex.getMessage());
            throw new GenericException("ERR-RE-0001", this.getClass()
                    .getSimpleName() + ":execute()", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName() + "." + "execute()");
        }
        return validationReport;
    }

    /**
     * Validate entity.
     * 
     * @param classname
     *            the classname
     * @param object
     *            the object
     * @return the list
     * @throws GenericException
     *             the generic exception
     */
    public List<String> validateEntity(String classname, Object object) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".validateEntity()");
        List<String> errorMessages = new ArrayList<String>();
        ValidationReport validationReport = new ValidationReport();
        try {
            validationReport = execute(object, validationReport);
            if (validationReport.getMessage() != null) {
                for (String eachError : validationReport.getMessage()) {
                    errorMessages.add(eachError);
                }
                log.debug("ErrorMessage : " + errorMessages);
            }
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            log.error("In Ex:" + ex.getMessage());
            throw new GenericException("ERR-RE-0001", this.getClass()
                    .getSimpleName() + ":validateEntity", ex);
        } finally {
           // validationReport = null;
            log.methodExit(this.getClass().getSimpleName()
                    + ".validateEntity()");
        }
        return errorMessages;
    }
    
    // unused methods. this validate is used for validating the metadata before business flow.
    public Collections<ValidationStatus> validate(Collections<Storable> collections)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".postReturnsValidationStatus()");
        Collections<ValidationStatus> errorCollections = new Collections<ValidationStatus>();
        try {
            for (Storable eachObject : collections.getCollection()) {
                ValidationStatus validationStatus = new ValidationStatus();
                List<FieldDetails> fieldDetailsList = new ArrayList<FieldDetails>();
                ErrorFields errorFields = new ErrorFields();
                List<String> validateList = validateEntity(eachObject
                        .getClass().getSimpleName(), eachObject);
                log.debug("Before filling an validationStatus.");
                validationStatus = fillValidationStatus(validateList,
                        validationStatus, fieldDetailsList);
                errorFields.setFieldDetails(fieldDetailsList);
                validationStatus.setErrorFields(errorFields);
                validationStatus.setContentEntityType(eachObject
                        .getEntityType());
                validationStatus.setContentId(eachObject.getId());
                errorCollections.add(validationStatus);
            }
        } catch (GenericException ge) {
            log.debug("Inside GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("Inside EX:" + ex.getMessage());
            throw new GenericException("ERR-RE-0019",
                    "unable to validate for metadata:" + ex.getMessage(),
                    "Input is collections.", ex);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".postReturnsValidationStatus()");
        }
        return errorCollections;
    }


   /*
    
    *  public String post(Collections collections) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".post");
        String consolidatedErrors = "";
        String lstErrors = "";
        String eachObjectError = "";
        try {
            for (Object eachObject : collections.getCollection()) {
                eachObjectError = validateEntityReturnsString(eachObject
                        .getClass().getSimpleName(), eachObject);
                consolidatedErrors = lstErrors + " - " + eachObjectError;
                log.debug("eachObject Error:" + consolidatedErrors);
            }
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            log.error("Exception at RuleEngineImpl.post:" + ex.getMessage());
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".post(collections)");
        }
        return consolidatedErrors;
    }

    public HashMap postCollections(Collections collections)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".postCollections()");
        HashMap<String, Object> lstErrors = new HashMap<String, Object>();
        try {
            for (Object eachObject : collections.getCollection()) {
                String validations = validateEntityReturnsString(eachObject
                        .getClass().getSimpleName(), eachObject);
                if (validations != "") {
                    lstErrors.put(eachObject.getClass().getSimpleName(),
                            validations);
                }
            }
            if (lstErrors.size() > 0) {
                lstErrors.put("Status", "Error");
            } else {
                lstErrors.put("Status", "Success");
            }
            log.debug("Error List" + lstErrors.toString());
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".postCollections()");
        }
        return lstErrors;
    }

    public List<String> postReturnsList(Collections collections)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".postReturnsList()");
        String errors = "";
        String eachObjectError = "";
        List<String> consolidatedErrors = new ArrayList<String>();
        try {
            for (Object eachObject : collections.getCollection()) {
                eachObjectError = validateEntityReturnsString(eachObject
                        .getClass().getSimpleName(), eachObject);
                consolidatedErrors.add(errors + " - " + eachObjectError);
                log.debug("Errors:" + consolidatedErrors);
            }
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".postReturnsList()");
        }
        return consolidatedErrors;
    }

    public String validateEntityReturnsString(String classname, Object object)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".validateEntityReturnsString()");
        try {
            ValidationReport validationReport = new ValidationReport();
            validationReport = execute(object, validationReport);
            log.debug("ValidationReport Result - "
                    + validationReport.getMessage());
            if (validationReport.getMessage() != null) {
                for (String eachError : validationReport.getMessage()) {
                    strError += eachError + ";";
                }
            }
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ".validateEntityReturnsString()");
        }
        return strError;
    }
    

    */


}
