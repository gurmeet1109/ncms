package in.nic.cmf.ruleengine;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;
import in.nic.cmf.util.DirectoryLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;

public class RuleEngineHelper {

    /** The str error. */
    String                                           strError             = "";

    /** The putil. */
    private PropertyManagement                       putil                = PropertyManagement
                                                                                  .getInstance();
    /** The log. */
    // public static LogTracer log;
    private LogTracer                                log;

    /** The rule impl. */
    // private static RuleEngineHelper ruleImpl = new RuleEngineHelper();
    // RuleEngineImpl ruleEngineimpl = RuleEngineImpl
    // .getInstance();
    /** The ksession. */

    private DirectoryLogic                           dirLogic;
    private static HashMap<String, RuleEngineHelper> hashRuleEngineHelper = new HashMap<String, RuleEngineHelper>();
    private String                                   domain;

    /**
     * The format. private final FormatJson format = FormatJson.getInstance();
     */

    /** The dup helper. */

    /**
     * Instantiates a new rule engine impl.
     */
    // public RuleEngineHelper() {
    // }
    //
    // public static RuleEngineHelper getInstance() {
    // return ruleImpl;
    // }
    private RuleEngineHelper(String domain) {
        this.domain = domain;
        dirLogic = DirectoryLogic.getInstanceOf(domain);
        // putil = PropertyManagement.getInstanceOf(domain, "ruleengine",
        // "Configuration");
        this.log = new LogTracer(domain, "ruleengine");
    }

    public static RuleEngineHelper getInstanceof(String domain) {
        if (!hashRuleEngineHelper.containsKey(domain)) {
            hashRuleEngineHelper.put(domain, new RuleEngineHelper(domain));
        }
        return hashRuleEngineHelper.get(domain);
    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    public List<String> checkDirectory() {
        List<String> rules = new ArrayList<String>();
        try {
            String path = putil.getProperties(domain, "ruleengine",
                    "Configuration", "location")
                    + domain
                    + putil.getProperties(domain, "ruleengine",
                            "Configuration", "rulePath");

            // System.out.println("path here is"
            // + path
            // + (String) putil.getProperties(domain, "ruleengine",
            // "Configuration", "folderName"));
            rules = dirLogic.listFilesFromDirectory(
                    path
                            + (String) putil
                                    .getProperties(domain, "ruleengine",
                                            "Configuration", "folderName"),
                    "validations");
            System.out.println(rules);
            log.debug("Returns:" + rules);
        } catch (Exception ex) {
            log.error("In EX of CheckDirectory:" + ex.getMessage());
        }
        return rules;
    }

    // this validate method is called after businessflow along with duplication
    // data maintanence.

    public HashMap<String, Object> fillValidationStatus(String domain,
            List<String> validateList,
            HashMap<String, Object> validationStatus,
            List<HashMap<String, Object>> fieldDetailsList) {
        try {
            HashMap<String, Object> fieldDetails = null;
            if (validateList.size() > 0) {
                for (String strEachField : validateList) {
                    fieldDetails = new HashMap<String, Object>();
                    if (strEachField.contains(":")) {
                        String[] fieldSplit = strEachField.split(":");
                        fieldDetails.put("FieldName", fieldSplit[0]);
                        fieldDetails.put("FieldError", fieldSplit[1]);
                        fieldDetailsList.add(fieldDetails);
                        validationStatus.put("Status", "Error");
                    }
                    log.debug("Eachfield:" + strEachField);
                }

                log.debug("validation has errors.");
            } else {
                validationStatus.put("Status", "Success");
                log.debug("Validation is successful");
            }
        } catch (GenericException ge) {
            throw new GenericException(domain, "ERR-RE-0018",
                    "Unable to fill Validation Status:" + ge.getMessage(),
                    "Input is validatelist", ge);
        }
        return validationStatus;
    }

    /**
     * Validate builder.
     * @param kbuilder
     *            the kbuilder
     * @return true, if successful
     */
    private boolean validateBuilder(String domain, KnowledgeBuilder kbuilder) {
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
            throw new GenericException(domain, "ERR-RE-0016",
                    "validateBuilder failure:" + ex.getMessage(),
                    "Input is kbuilder.", ex);
        }

    }

    /**
     * Execute.
     * @param a
     *            the a
     * @param validationReport
     *            the validation report
     * @return the validation report
     * @throws GenericException
     *             the generic exception
     */

    /**
     * Validate entity.
     * @param classname
     *            the classname
     * @param object
     *            the object
     * @return the list
     * @throws GenericException
     *             the generic exception
     */

    public String getUniqueContent() {
        String jsonTxt = "";
        try {

            InputStream iStream = new FileInputStream(new File(
                    putil.getProperties(domain, "ruleengine", "Configuration",
                            "dirlocation")
                            + domain
                            + putil.getProperties(domain, "ruleengine",
                                    "Configuration", "uniquefileread")));
            jsonTxt = IOUtils.toString(iStream);
        } catch (Exception ex) {
            log.debug("In exception of getContentFromResource:"
                    + ex.getMessage());
        }
        return jsonTxt;
    }

    public Map<String, Object> getFlattenMap(String domain,
            Map<String, Object> fileItemMap) {
        Map<String, Object> xmlMap = new HashMap<String, Object>();
        xmlMap = FormatXml.getInstanceof(domain).in(fileItemMap);
        Map<String, Object> map = (Map<String, Object>) xmlMap
                .get("Collections");
        return map;
    }

    public boolean isValidMimeType(String value) {
        String mimeTypes = putil.getProperties(domain, "ruleengine",
                "Configuration", "mimeTypeList");

        List<String> mimeList = Arrays.asList(mimeTypes.split(","));
        if (mimeList.contains(value.trim())) {
            return true;
        }
        return false;
    }

    public boolean isValidLanguageCode(String value) {
        String languageCode = putil.getProperties(domain, "ruleengine",
                "Configuration", "languageCodeList");
        List<String> langCodeList = Arrays.asList(languageCode.split(","));
        if (langCodeList.contains(value.trim())) {
            return true;
        }
        return false;
    }

    public boolean isValidExtension(String value) {
        String extension = putil.getProperties(domain, "ruleengine",
                "Configuration", "extensionList");
        List<String> extensionList = Arrays.asList(extension.split(","));
        if (extensionList.contains(value.trim())) {
            return true;
        }
        return false;
    }

}
