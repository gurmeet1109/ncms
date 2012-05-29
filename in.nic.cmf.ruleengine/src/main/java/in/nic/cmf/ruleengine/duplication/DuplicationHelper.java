/***
 * Created on : 4th October 2011
 * Created by : Kavitha
 * Purpose : To Add / remove rules from the knowledgebase to avoid duplicate
 * records into system.
 */
package in.nic.cmf.ruleengine.duplication;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

/**
 * The Class DuplicationHelper.
 */
public class DuplicationHelper {

    /** The putil. */
    PropertyManagement                                putil                 = PropertyManagement
                                                                                    .getInstance();

    /** The log. */
    public static LogTracer                           log                   = null;

    /** The dup impl. */
    // private static DuplicationHelper dupImpl = null;

    /** The format. */
    private final FormatJson                          format;
    private static HashMap<String, DuplicationHelper> hashDuplicationHelper = new HashMap<String, DuplicationHelper>();

    /**
     * Instantiates a new duplication helper.
     */
    private DuplicationHelper(String domain) {
        // putil = PropertyManagement.getInstanceOf(domain, "ruleengine",
        // "Configuration");
        format = FormatJson.getInstanceof(domain);
    }

    public void setLogTracer(final LogTracer log) {
        this.log = log;
    }

    /**
     * Gets the single instance of DuplicationHelper.
     * @return single instance of DuplicationHelper
     */
    public static DuplicationHelper getInstanceof(String domain) {
        if (!hashDuplicationHelper.containsKey(domain)) {
            hashDuplicationHelper.put(domain, new DuplicationHelper(domain));
        }
        return hashDuplicationHelper.get(domain);
    }

    /**
     * Generate rule name.
     * @param storable
     *            the storable
     * @return the string
     */
    public String generateRuleName(HashMap<String, Object> storable) {
        return storable.get("Site") + "_" + storable.get("EntityType") + "_"
                + storable.get("Id");
    }

    /**
     * Generate rule. Purpose : To generate and persist rules in txt file
     * @param domainName
     *            the domain name
     * @param storable
     *            the storable
     * @param uniqueDetails
     *            the unique details
     * @return the string
     */
    public String generateRule(String domainName,
            HashMap<String, Object> entityHash, String uniqueDetails) {
        log.methodEntry("In generateRule entry");
        String rule = "";
        try {

            HashMap<String, Object> ruleData = getRuleData(domainName,
                    entityHash, uniqueDetails);
            log.debug("retrieved ruleData and passing towritToFile. Data is:"
                    + ruleData);
            if (!writeToFile(domainName, ruleData)) {
                throw new GenericException(domainName, "ERR-RE-0005");
            }
            log.debug("Persisted the ruledata. now generating rule. Input is ruleData.");
            rule = generateRule(domainName, ruleData);
            log.debug("Generated rule is :" + rule);
            log.methodExit("generateRule exit");
        } catch (GenericException ge) {
            log.error("In GE - generateRule:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In EX - generateRule:" + ex.getMessage());
            throw new GenericException(domainName, "ERR-RE-0006",
                    "DuplicationHelper.generateRule() failure",
                    "Input is domain:" + domainName
                            + ", storable & uniquejson ");
        }
        return rule;
    }

    /**
     * Write to file. Purpose : To persist the ruleData into txt file.
     * @param domainName
     *            the domain name
     * @param ruleData
     *            the rule data
     * @return true, if successful
     */
    public boolean writeToFile(String domainName, Map ruleData) {
        log.methodEntry("writeToFile entry.");
        boolean result = false;
        try {
            String fileDir = putil.getProperties(domainName, "ruleengine",
                    "Configuration", "rulepath") + domainName + "/" + "unique/";
            String filePath = fileDir
                    + putil.getProperties(domainName, "ruleengine",
                            "Configuration", "duplicatefile");
            if (checkAndCreateDirectory(domainName, fileDir)) {
                log.debug("checkAndCreateDirectory is successful so writing to this path:"
                        + filePath);
                FileWriter fstream = new FileWriter(filePath, true);
                BufferedWriter out = new BufferedWriter(fstream);
                if (ruleData != null && ruleData.containsKey("rulename")) {

                    JSONObject jo = (JSONObject) format.out((HashMap) ruleData);
                    out.write(jo.toString() + "\n");
                }
                out.close();
            }
            result = true;
        } catch (GenericException ge) {
            log.error("In GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In EX:" + ex.getMessage());
            throw new GenericException(domainName, "ERR-RE-0007",
                    "DuplicationHelper.writeToFile() failure:"
                            + ex.getMessage(),
                    "Input is ruleData & domainName", ex);
        }
        log.methodExit("writeToFile exit.");
        return result;
    }

    /**
     * Check and create directory.
     * @param pathName
     *            the path name
     * @return true, if successful
     * @throws GenericException
     *             the generic exception
     */
    public boolean checkAndCreateDirectory(String domain, String pathName)
            throws GenericException {
        try {
            File file = new File(pathName);
            if (!file.exists()) {
                return file.mkdirs();
            } else {
                return true;
            }
        } catch (GenericException ge) {
            log.error("In checkAndCreateDirectory GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In checkAndCreateDirectory EX:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0008",
                    "DuplicationHelper.checkAndCreateDirectory() failure:"
                            + ex.getMessage(), "Input is path:" + pathName, ex);
        } finally {
            log.debug("End of createDirectory.");
        }
    }

    public boolean checkIfUniqueData(Map<String, Object> dataMap,
            String uniqueDetails) {
        log.methodEntry("checkIfUniqueData entry");
        HashMap formattedMap = format.in(uniqueDetails);
        try {
            log.debug("getting hashmap from convertor - unique" + formattedMap);
            formattedMap = (HashMap) formattedMap.get("unique");
            if (formattedMap.containsKey((String) dataMap.get("EntityType"))) {
                return true;
            } else {
                return false;
            }
        } catch (GenericException ge) {
            log.error("In DuplicationHelper.checkIfUniqueData GE: "
                    + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In DuplicationHelper.checkIfUniqueDataEX: "
                    + ex.getMessage());
            // throw new
            // GenericException("ERR-RE-0009","DuplicationHelper.checkIfUniqueData failure:"+ex.getMessage(),"Input is uniquejson string",ex);
        }
        return false;
    }

    /**
     * Gets the rule data.
     * @param dataMap
     *            the data map
     * @param uniqueDetails
     *            the unique details
     * @return the rule data
     */
    public HashMap getRuleData(String domain, HashMap<String, Object> dataMap,
            String uniqueDetails) {
        log.methodEntry("getRuleData entry");
        HashMap ruleMap = new HashMap();
        try {
            // to convert json to hashmap
            HashMap formattedMap = format.in(uniqueDetails);
            try {
                log.debug("getting hashmap from convertor - unique"
                        + formattedMap);
                formattedMap = (HashMap) formattedMap.get("unique");
            } catch (GenericException ge) {
                log.error("In DuplicationHelper.getRule GE: " + ge.getMessage());
                throw ge;
            } catch (Exception ex) {
                log.error("In DuplicationHelper.getRule EX: " + ex.getMessage());
                throw new GenericException(domain, "ERR-RE-0009",
                        "DuplicationHelper.getRuleData failure:"
                                + ex.getMessage(),
                        "Input is uniquejson string", ex);
            }

            // Fill rulename if empty
            String ruleName = "";
            if (dataMap.get("rulename") == null
                    || ((String) dataMap.get("rulename")).isEmpty()) {
                ruleName = generateRuleName(dataMap);
                log.debug("rule name:" + ruleName);
            }
            // filling the rule data map
            if (formattedMap.containsKey((String) dataMap.get("EntityType"))) {
                log.debug("Incoming entitytype is there in unique.json:entitytype is "
                        + (String) dataMap.get("EntityType"));
                List<String> storableUniqueFields = (List<String>) formattedMap
                        .get((String) dataMap.get("EntityType"));
                Map<String, Object> uniqueFields = new HashMap<String, Object>();
                for (String uniqueField : storableUniqueFields) {
                    uniqueFields.put(uniqueField,
                            (String) dataMap.get(uniqueField));
                }
                log.debug("UniqueFields for "
                        + (String) dataMap.get("EntityType") + " is "
                        + uniqueFields);
                Map orMap = new HashMap();
                for (Entry<String, Object> rule : uniqueFields.entrySet()) {
                    orMap.put(rule.getKey(), rule.getValue());
                }

                Map andMap = new HashMap();
                andMap.put("Id", dataMap.get("Id"));
                andMap.put("Site", dataMap.get("Site"));
                andMap.put("EntityType", dataMap.get("EntityType"));

                Map whenMap = new HashMap();
                whenMap.put("Id", dataMap.get("Id"));
                whenMap.put("Site", dataMap.get("Site"));
                whenMap.put("EntityType", dataMap.get("EntityType"));

                whenMap.put("AND", andMap);
                whenMap.put("OR", orMap);

                ruleMap.put("rulename", ruleName);
                ruleMap.put("type", "unique");
                ruleMap.put("when", whenMap);
            } else {
                log.error((String) dataMap.get("EntityType")
                        + "does not contain unique key.");
                // throw new GenericException("ERR-RE-0010",(String)
                // dataMap.get("EntityType") + " is not an unique key.");
            }
        } catch (GenericException ge) {
            log.debug("In GE getRuleData:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("In EX getRuleData:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0009",
                    "DuplicationHelper.getRuledata failure:" + ex.getMessage(),
                    "Input is uniquejson string", ex);
        }
        return ruleMap;
    }

    /**
     * Generate rule. Purpose : This method will undergo change as we write
     * generic map for any formation of rules.
     * @param ruleData
     *            the rule data
     * @return the string
     */
    public String generateRule(String domain, Map<String, Object> ruleData) {
        log.methodEntry("generateRule entry");
        String validRule = "";
        try {
            HashMap whenRuleData = (HashMap) ruleData.get("when");
            HashMap<String, Object> orUniqueField = (HashMap) whenRuleData
                    .get("OR");
            HashMap<String, Object> andUniqueField = (HashMap) whenRuleData
                    .get("AND");
            String ruleName = generateRuleName(andUniqueField);
            String when = "";
            int count = 1;
            log.debug("Incoming ruledata splitted to AND,OR maps.Now iteratingthe AND map.");
            for (Entry<String, Object> rule : andUniqueField.entrySet()) {
                if (rule.getKey().equals("Id")) {
                    when += (String) rule.getKey() + "!=\'"
                            + (String) rule.getValue() + "\',";
                } else {
                    if (rule.getKey().equals("Site")
                            || rule.getKey().equals("EntityType")) {
                        if (andUniqueField.size() == count) {
                            when += (String) rule.getKey() + "==\'"
                                    + (String) rule.getValue() + "\'";
                        } else {
                            when += (String) rule.getKey() + "==\'"
                                    + (String) rule.getValue() + "\',";
                        }
                    }
                }
                count++;
            }
            // to get OR conditions
            log.debug("uniquefields count:" + orUniqueField.size());
            when += "(";
            int j = 1;
            for (Entry<String, Object> rule : orUniqueField.entrySet()) {
                if (orUniqueField.size() == j) {
                    when += (String) rule.getKey() + "==\'"
                            + (String) rule.getValue() + "\'";
                } else {
                    when += (String) rule.getKey() + "==\'"
                            + (String) rule.getValue() + "\'||";
                }
                j++;
            }
            when += ")";
            log.debug("WHEN formed. Now invoking thenFormation(), formRule() & giveRule() methods.");
            validRule = giveRule(formRule(ruleName, when,
                    thenFormation(domain, "unique", ruleName, orUniqueField),
                    ruleData));
            System.out.println("Rule is " + validRule);
        } catch (GenericException ge) {
            log.debug("In GE DuplicationHelper.generateRule:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("In EX DuplicationHelper.generateRule:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0011",
                    "DuplicationHelper.getRuledata failure:" + ex.getMessage(),
                    "Input is uniquejson string", ex);
        }
        log.methodExit("generateRule exit.");
        return validRule;
    }

    /**
     * Form rule. Purpose : Forming the valid WHEN & THEN drools rule.
     * @param ruleName
     *            the rule name
     * @param when
     *            the when
     * @param then
     *            the then
     * @param ruleData
     *            the rule data
     * @return the string
     */
    public String formRule(String ruleName, String when, String then,
            Map<String, Object> ruleData) {
        String eachRule = "";
        eachRule = "rule \'" + ruleName + "\'" + "\n" + "when" + "\n"
                + "map:HashMap(" + when + ");" + "\n" + "then" + then + "\n"
                + "end" + "\n";
        return eachRule;
    }

    /**
     * Then formation. Right now formed the THEN for unique alone. later we have
     * to include for others.
     * @param type
     *            the type
     * @param ruleName
     *            the rule name
     * @param dataMap
     *            the data map
     * @return the string
     */
    public String thenFormation(String domain, String type, String ruleName,
            Map<String, Object> dataMap) {
        String then = "";
        try {
            if (type.equals("unique")) {
                int j = 1;
                for (Entry<String, Object> rule : dataMap.entrySet()) {
                    then += "\n" + "if(map.get(\'" + rule.getKey()
                            + "\').equals(" + "\'" + dataMap.get(rule.getKey())
                            + "\')){" + "System.out.println('Fired Rules : "
                            + ruleName + "');" + "validategreat = addToList('"
                            + rule.getKey() + ":" + rule.getKey()
                            + " duplication occured.',validategreat);" + "}";
                    j++;
                }
            }
            log.debug("In thenformation:" + then);
        } catch (GenericException ge) {
            log.debug("In GE DuplicationHelper.thenFormation:"
                    + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.debug("In EX DuplicationHelperthenFormation:" + ex.getMessage());
            throw new GenericException(domain, "ERR-RE-0012",
                    "DuplicationHelper.thenFormation failure:"
                            + ex.getMessage(),
                    "Input is type,rulename and datamap.", ex);
        }
        return then;
    }

    /**
     * Give rule. Purpose : forming the valid rules.
     * @param rule
     *            the rule
     * @return the string
     */
    public String giveRule(String rule) {
        String formRule = "package in.nic.cmf.services.ruleengine"
                + "\n"
                + "import java.util.HashMap;"
                + "\n"
                + "import java.util.List;"
                + "\n"
                + "import java.util.ArrayList;"
                + "\n"
                + "import in.nic.cmf.domain.ValidationReport;"
                + "\n"
                + "global in.nic.cmf.domain.ValidationReport validategreat;"
                + "\n"
                + "function ValidationReport addToList(String strContent, ValidationReport report)  "
                + "\n" + " {    " + "\n"
                + "	List<String> lstMessage = new ArrayList<String>();" + "\n"
                + " 	if(report.getMessage()!=null)" + "\n" + "{  " + "\n"
                + "	lstMessage = report.getMessage();  " + "\n"
                + "	lstMessage.add(strContent); " + "\n"
                + "	report.setMessage(lstMessage);" + "\n" + " } " + "\n"
                + "else " + "\n" + "{ " + "\n" + "	lstMessage.add(strContent);"
                + "\n" + "	report.setMessage(lstMessage);" + "\n" + "} " + "\n"
                + "return report;  " + "\n" + "}" + "\n" + rule + "\n";
        return formRule;
    }

    /**
     * Adds the rule. Purpose: to add rules to knowledgebase.
     * @param domainName
     *            the domain name
     * @param storable
     *            the storable
     * @param uniqueDetails
     *            the unique details
     * @return true, if successful private boolean addRule(String domainName,
     *         Storable storable, String uniqueDetails) {
     *         log.methodEntry("addRule entry."); try { List<String> rules = new
     *         ArrayList<String>();
     *         log.debug("Invoking generateRule with 3 param.");
     *         rules.add(dupHelper.generateRule(domainName, storable,
     *         uniqueDetails));
     *         log.debug("Invoking ReadKnowledgeBase to addrule.");
     *         readKnowlegeBase("addrule", rules);
     *         log.methodExit("addRule exit."); return true; } catch
     *         (GenericException ge) { log.error("In GE addRule:" +
     *         ge.getMessage()); throw ge; } catch (Exception ex) {
     *         log.error("In EX addRule:" + ex.getMessage()); throw new
     *         GenericException
     *         ("ERR-RE-0013","RuleEngineImpl.addRule failure:"+ex
     *         .getMessage(),"Input is domain,storable&uniquejson",ex); } }
     */

    /**
     * Removes the rule. To remove rules from knowledgebase
     * @param domainName
     *            the domain name
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @return true, if successful public boolean removeRule(String domainName,
     *         String entity, String id) { log.methodEntry("removeRule entry.");
     *         try { List<String> ruleNames = new ArrayList<String>();
     *         ruleNames.add(domainName + "_" + entity + "_" + id);
     *         log.debug("ruleNames:" + ruleNames); readKnowlegeBase("remove",
     *         ruleNames); log.methodExit("removeRule exit."); return true; }
     *         catch (GenericException ge) {
     *         log.error("In GE RuleEngineImpl.removeRule:" + ge.getMessage());
     *         throw ge; } catch (Exception ex) {
     *         log.error("In EX RuleEngineImpl.removeRule:" + ex.getMessage());
     *         throw new GenericException("ERR-RE-0014", "removeRule failure:" +
     *         ex.getMessage(), "Input is storable & domain:" + domainName, ex);
     *         } }
     */
    /**
     * Removes the rule. To remove rules from knowledgebase
     * @param domainName
     *            the domain name
     * @param storable
     *            the storable
     * @return true, if successful public boolean removeRule(String domainName,
     *         Storable storable) { log.methodEntry("removeRule entry."); try {
     *         List<String> ruleNames = new ArrayList<String>();
     *         ruleNames.add(dupHelper.generateRuleName(storable));
     *         log.debug("ruleNames:" + ruleNames); readKnowlegeBase("remove",
     *         ruleNames); log.methodExit("removeRule exit."); return true; }
     *         catch (GenericException ge) {
     *         log.error("In GE RuleEngineImpl.removeRule:" + ge.getMessage());
     *         throw ge; } catch (Exception ex) {
     *         log.error("In EX RuleEngineImpl.removeRule:" + ex.getMessage());
     *         throw new
     *         GenericException("ERR-RE-0014","removeRule failure:"+ex.
     *         getMessage(),"Input is storable & domain:"+domainName, ex); } }
     *         public boolean deleteRuleForDuplication(String domainName, String
     *         entity, String id) {
     *         log.methodEntry("deleteRuleForDuplication entry"); try {
     *         log.debug("inside iteration to remove:" + domainName + ":" + id);
     *         if (!removeRule(domainName, entity, id)) {
     *         log.debug("Remove Rule failed."); }
     *         log.methodExit("deleteRuleForDuplication exit"); return true; }
     *         catch (Exception ex) { log.error(
     *         "In Overall EX : RuleEngineImpl.generateForDuplication:" +
     *         ex.getMessage()); // throw new // GenericException("ERR-RE-0015"
     *         ,"generateForDuplication failure:"+ex
     *         .getMessage(),"Input is collection, jsonstring & domain:"
     *         +domainName, // ex); } return false; }
     */

    /**
     * Generate for duplication. To generate (remove and add the rule.)
     * @param domainName
     *            the domain name
     * @param collections
     *            the collections
     * @param uniqueDetails
     *            the unique details
     * @return true, if successful public boolean generateForDuplication(String
     *         domainName, Collections<Storable> collections, String
     *         uniqueDetails) { log.methodEntry("generateForDuplication entry");
     *         //try{ if(collections.getCollection().size() == 0){ return false;
     *         } for (Storable storable : collections.getCollection()) { try {
     *         log.debug("inside iteration to remove and add:" + domainName +
     *         ":" + storable.getId()); if(!removeRule(domainName, storable)){
     *         log.debug("Remove Rule failed."); } if(!addRule(domainName,
     *         storable, uniqueDetails)){ log.debug("Add Rule failed."); }
     *         log.methodExit("generateForDuplication exit"); } catch
     *         (GenericException ge) { log.error("In GE: occured " +
     *         ge.getMessage()); //throw ge; } catch (Exception ex) {
     *         log.error("In EX RuleEngineImpl.generateForDuplication:" +
     *         ex.getMessage()); //throw new
     *         GenericException("ERR-RE-0015","generateForDuplication failure:"
     *         +ex.getMessage(),"Input is collection, jsonstring & domain:"+
     *         domainName, ex); } } return true; }
     */

    /**
     * Read rules. Purpose : while app startup , it will read rules from the txt
     * file and add the rules if not exists
     * @param filePath
     *            the file path
     * @return the list public List<String> readRules(String filePath) {
     *         log.methodEntry("readRule entry."); List<String> rules = new
     *         ArrayList(); try { InputStream is = new
     *         FileInputStream(filePath); DataInputStream in = new
     *         DataInputStream(is); BufferedReader br = new BufferedReader(new
     *         InputStreamReader(in)); String strLine;
     *         log.debug("before while loop to read rules. Path:" + filePath);
     *         while ((strLine = br.readLine()) != null) {
     *         log.debug("EachRule : " + strLine); FormatJson convertor =
     *         FormatJson.getInstance(); try{ Map ruleData =
     *         convertor.in(strLine); //to remove rule if exists List<String>
     *         ruleNames = new ArrayList<String>();
     *         ruleNames.add((String)ruleData.get("rulename"));
     *         readKnowlegeBase("remove", ruleNames);
     *         log.debug("After ReadKnowledge for remove."); // to add the rule
     *         rules.add(dupHelper.generateRule(ruleData));
     *         readKnowlegeBase("addrule", rules);
     *         log.debug("After Readknowledge for add.");
     *         }catch(GenericException ge){
     *         log.debug("In GE :readRules reading single rule failure:"
     *         +ge.getMessage()); } catch(Exception ex){
     *         log.debug("In EX : readRules reading single rule failure:"
     *         +ex.getMessage()); } } in.close();
     *         log.methodExit("readRule exit."); return rules; } catch
     *         (Exception ex) {
     *         log.debug("In Catch:readRules failed:"+ex.getMessage());
     *         System.out.println(ex.getMessage()); } return rules; }
     */

    /**
     * if(rule.contains(".txt")){ log.debug("Incoming RulePath has txt file:" +
     * rule); File file = new File(rule); if (file.exists()) { log.debug(rule +
     * "file exists."); for (String eachrule : readRules(rule)) {
     * log.debug("readRules iteration:" + eachrule); try {
     * kbuilder.add(ResourceFactory.newReaderResource(new
     * StringReader(eachrule)), ResourceType.DRL); } catch (Exception ex) {
     * log.error("In Ex - txt :" + ex.getMessage()); } } } }
     */
}
