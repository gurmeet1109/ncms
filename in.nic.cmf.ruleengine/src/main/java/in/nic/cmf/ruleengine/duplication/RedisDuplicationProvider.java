package in.nic.cmf.ruleengine.duplication;

import in.nic.cmf.cache.RedisCache;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RedisDuplicationProvider {

    // private static final RedisDuplicationProvider redis = new
    // RedisDuplicationProvider();
    public static LogTracer log = null;
    RedisCache redisCache;
    private final FormatJson formatJson;
    private static HashMap<String, RedisDuplicationProvider> hashRedisDuplicationProvider = new HashMap<String, RedisDuplicationProvider>();
    private String domain;

    // public static RedisDuplicationProvider getInstance() {
    // return redis;
    // }
    private RedisDuplicationProvider(String domain) {
	this.domain = domain;
	formatJson = FormatJson.getInstanceof(domain);
	redisCache = RedisCache.getInstanceof(domain);
    }

    public static RedisDuplicationProvider getInstanceof(String domain) {
	if (!hashRedisDuplicationProvider.containsKey(domain)) {
	    hashRedisDuplicationProvider.put(domain,
		    new RedisDuplicationProvider(domain));
	}
	return hashRedisDuplicationProvider.get(domain);
    }

    public void setLogTracer(final LogTracer log) {
	this.log = log;
    }

    public String generateKeyWithId(Map<String, String> map) {
	String value = "";
	if (map != null && map.containsKey("value")) {
	    value = map.get("site") + "_" + map.get("entitytype") + "_"
		    + map.get("value");
	}
	return value;
    }

    public HashMap<String, Object> validateDuplicate(
	    HashMap<String, String> entityHash, String uniqueDetails,
	    List<Map> tmpDetails, String errorMsg) {
	log.methodEntry("validateDuplicate entry");
	HashMap<String, Object> resultMap = new HashMap<String, Object>();
	List<HashMap<String, Object>> lstFieldDetails = new ArrayList<HashMap<String, Object>>();
	try {
	    HashMap<String, Object> tmphash = new HashMap<String, Object>();
	    List<HashMap> processedData = processRawData(entityHash,
		    uniqueDetails);
	    String keyWithId = "";
	    for (Map<String, String> map : processedData) {
		if (map != null) {

		    String ids = "";
		    if (map.containsKey("key")) {
			ids = map.get("key").toLowerCase();
		    }
		    log.debug(ids);
		    String existingId = redisCache.get(ids);
		    log.debug("existingid is:" + existingId);
		    boolean checkTemp = checkTmpDuplicate(map.get("key"),
			    tmpDetails);
		    log.debug("checktemp:" + checkTemp);
		    if (existingId != null || checkTemp) {
			log.debug("Inside 1st if condition.");
			if (checkTemp
				|| !map.get("value").toLowerCase()
					.equals(existingId)) {
			    log.debug("Inside 2nd if condition." + existingId
				    + "::" + map.get("value"));
			    log.debug("errorfields is there");
			    HashMap<String, Object> fieldDetails = new HashMap<String, Object>();
			    fieldDetails.put("FieldError", map.get("field")
				    + " " + errorMsg);
			    fieldDetails.put("FieldName", map.get("field"));
			    lstFieldDetails.add(fieldDetails);
			} else {
			    log.debug("in inner else:" + map);
			    tmphash.put(map.get("key").toLowerCase(),
				    map.get("value").toLowerCase());
			}
		    } else {
			log.debug("in outer else:" + map);
			String key = "";
			String value = "";
			if (map.containsKey("key") && map.containsKey("value")) {
			    key = map.get("key").toLowerCase();
			    value = map.get("value").toLowerCase();
			    if (!key.equals("") && !value.equals("")) {
				tmphash.put(key, value);
			    }
			}
		    }
		    keyWithId = generateKeyWithId(map);
		}

	    }
	    log.debug("before pushing tmphash using generateValues:" + tmphash);
	    String key = keyWithId.toLowerCase();

	    tmphash.put(key, generateValues(tmphash));

	    tmpDetails.add(tmphash);
	    // formDuplicationKey(tmpDetails);
	    log.debug("after temp details:");
	    resultMap.put("tmpDetails", tmpDetails);
	    resultMap.put("FieldDetails", lstFieldDetails);
	} catch (GenericException ge) {
	    log.error("In GE : " + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In EX :" + ex.getMessage());
	    throw new GenericException(domain, "ERR-RE-0017",
		    "unable to validate duplication:" + ex.getMessage(),
		    "Input is collections, uniquedetails and tmpdetails.", ex);
	} finally {
	    log.methodExit("validateDuplicate exit");
	}
	return resultMap;
    }

    private String generateValues(HashMap<String, Object> tmphash) {
	String value = "";
	try {
	    log.debug("inside generateValues before for loop:" + tmphash);
	    for (Entry<String, Object> entry : tmphash.entrySet()) {
		log.debug("inside for :" + value);
		value += entry.getKey() + ";";
	    }
	    log.debug("generateValues:" + value.toLowerCase());
	    value = value.toLowerCase();
	} catch (Exception ex) {
	    log.error("In EX :" + ex.getMessage());
	    throw new GenericException(domain, "ERR-RE-0020",
		    "Value formation for duplication failed :"
			    + ex.getMessage(), "Input is temporary hashmap.",
		    ex);
	}
	return value;
    }

    /*
     * public boolean generateForDuplication(List<Map> tmpDetails) { boolean
     * result = false; try {
     * log.methodEntry("generateForDuplication entry.Incoming parameter is :" +
     * tmpDetails); for (Map<String, String> map : tmpDetails) {
     * log.debug("Iterating from tmpDetails.Each map is:" + map); for
     * (Entry<String, String> rule : map.entrySet()) {
     * log.debug("Iterating entryset from map:" + rule); try {
     * deleteDuplicate(rule.getKey().toLowerCase()); } catch (Exception ex) {
     * log.debug("Exception occured at deleteDuplicate:" + ex.getMessage()); }
     * if (!isEmpty(rule.getKey()) && !isEmpty(rule.getValue())) {
     * log.debug("making the key and value tolowercase");
     * redisCache.set(rule.getKey().toLowerCase(), rule
     * .getValue().toLowerCase());
     * log.debug("key and value setted in rediscache."); } } } result = true;
     * log.methodExit("generateForDuplication exit"); } catch (GenericException
     * ge) { log.error("In GE : " + ge.getMessage()); throw ge; } catch
     * (Exception ex) { log.error("In EX Catch of generateForDuplication : " +
     * ex.getMessage()); throw new GenericException("ERR-RE-0015",
     * "Value formation for duplication failed :" + ex.getMessage(),
     * "Input is temporary hashmap.", ex); } return result; }
     */
    /*
     * public boolean generateForDuplicationMap(Map<String,String> tmpDetails) {
     * boolean result = false; try {
     * log.methodEntry("generateForDuplication entry.Incoming parameter is :" +
     * tmpDetails); //Map<String, String> map = getDuplicationMap(tmpDetails);
     * log.debug("Iterating from tmpDetails.Each map is:" + tmpDetails); for
     * (Entry<String, String> rule : tmpDetails.entrySet()) {
     * log.debug("Iterating entryset from map:" + rule); try {
     * deleteDuplicate(rule.getKey().toLowerCase()); } catch (Exception ex) {
     * log.debug("Exception occured at deleteDuplicate:" + ex.getMessage()); }
     * if (!isEmpty(rule.getKey()) && !isEmpty(rule.getValue())) {
     * log.debug("making the key and value tolowercase");
     * redisCache.set(rule.getKey().toLowerCase(), rule
     * .getValue().toLowerCase());
     * log.debug("key and value setted in rediscache."); } result = true;
     * log.methodExit("generateForDuplication exit"); } }catch (GenericException
     * ge) { log.error("In GE : " + ge.getMessage()); throw ge; } catch
     * (Exception ex) { log.error("In EX Catch of generateForDuplicationMap : "
     * + ex.getMessage()); throw new GenericException(domain,"ERR-RE-0015",
     * "Value formation for duplication failed :" + ex.getMessage(),
     * "Input is temporary hashmap.", ex); } return result; }
     */

    public boolean generateForDuplicationMap(Map<String, String> tmpDetails) {
	boolean result = false;
	try {
	    log.methodEntry("generateForDuplication entry.Incoming parameter is :"
		    + tmpDetails);
	    // Map<String, String> map = getDuplicationMap(tmpDetails);
	    log.debug("Iterating from tmpDetails.Each map is:" + tmpDetails);
	    // for (Entry<String, String> rule : tmpDetails.entrySet()) {
	    log.debug("Iterating entryset from map:" + tmpDetails);
	    try {
		// deleteDuplicate(tmpDetails.get("Key").toLowerCase());
		for (Map.Entry<String, String> eachKey : tmpDetails.entrySet()) {
		    log.debug("Key to be delete for edit:::" + eachKey);
		    deleteDuplicate(eachKey.getKey());
		}
	    } catch (Exception ex) {
		log.debug("Exception occured at deleteDuplicate:"
			+ ex.getMessage());
	    }
	    if (!isEmpty(tmpDetails.get("Key"))
		    && !isEmpty(tmpDetails.get("Value"))) {
		log.debug("making the key and value tolowercase:"
			+ tmpDetails.get("Key") + "--"
			+ tmpDetails.get("Value").toLowerCase());
		String key = tmpDetails.get("Key").toLowerCase();
		String value = tmpDetails.get("Value").toLowerCase();
		if (key != null && value != null) {
		    redisCache.set(tmpDetails.get("Key").toLowerCase(),
			    tmpDetails.get("Value").toLowerCase());
		}
		log.debug("key and value setted in rediscache.");
	    }
	    result = true;
	    log.methodExit("generateForDuplication exit");
	    // }
	} catch (GenericException ge) {
	    log.error("In GE : " + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In EX Catch of generateForDuplicationMap : "
		    + ex.getMessage());
	    throw new GenericException(domain, "ERR-RE-0015",
		    "Value formation for duplication failed :"
			    + ex.getMessage(), "Input is temporary hashmap.",
		    ex);
	}

	return result;

    }

    public boolean generateDuplications(Map<String, Object> tmpDetails) {
	try {
	    if (tmpDetails.get("Duplication") instanceof List) {
		for (Map<String, String> map : (List<Map>) tmpDetails
			.get("Duplication")) {
		    generateForDuplicationMap(map);
		}

	    } else if (tmpDetails.get("Duplication") instanceof Map) {
		Map<String, String> duplicationMap = (Map<String, String>) tmpDetails
			.get("Duplication");
		generateForDuplicationMap(duplicationMap);
	    }
	} catch (Exception ex) {
	    throw new GenericException(domain, "ERR-RE-000", ex);
	}
	return true;
    }

    public boolean deleteDuplicate(String key) {
	boolean result = false;
	try {
	    log.methodEntry("deleteDuplicate entry. Input parameter:" + key);
	    if (!isEmpty(key)) {
		String existingKey = redisCache.get(key.toLowerCase());
		log.debug("Existing key is :" + existingKey);
		String dem = ";";
		if (!isEmpty(existingKey)) {
		    log.debug("ExistingKey is not empty.Delimiter is " + dem);
		    String[] keyarray = existingKey.split(dem);
		    log.debug("keyarray is:" + keyarray);
		    for (String delKey : keyarray) {
			log.debug("Going to delete this key :" + delKey);
			redisCache.delete(delKey.toLowerCase());
		    }
		}
		log.debug("Full key including child is going to delete :" + key);
		try {
		    log.debug("before delete");
		    redisCache.delete(key.toLowerCase());
		    log.debug("after delete");
		} catch (Exception ex) {
		    log.error("In EX:" + ex.getMessage());
		}
	    }
	    result = true;
	    log.methodExit("deleteDuplicate exit");
	} catch (GenericException ge) {
	    log.error("In GE : " + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In EX Catch of deleteDuplicate : " + ex.getMessage());
	    throw new GenericException(domain, "ERR-RE-0021",
		    "Deletion for duplication failed :" + ex.getMessage(),
		    "Input is temporary hashmap.", ex);
	}
	return result;
    }

    private boolean checkTmpDuplicate(String strkey, List<Map> tmpMap) {
	log.debug("inside checkTmpDuplicate:" + strkey + ";tmpMap" + tmpMap);
	if (tmpMap != null) {
	    for (Map map : tmpMap) {
		if (map.containsKey(strkey.toLowerCase())) {
		    log.debug("map is :" + map + ";strkey:" + strkey);
		    return true;
		}
	    }
	}
	return false;
    }

    public List<HashMap> processRawData(HashMap<String, String> entityHash,
	    String uniqueDetails) {
	List<HashMap> ruleData = new ArrayList<HashMap>();
	log.methodEntry("processRawData entry");
	try {
	    ruleData = getRuleData(entityHash, uniqueDetails);
	    log.debug("ruleData is:" + ruleData);
	    log.methodExit("processRawData exit");
	} catch (GenericException ge) {
	    log.error("In GE : " + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    ex.printStackTrace();

	    log.error("In processRawData EX Catch :" + ex.getMessage());
	    throw new GenericException(domain, "ERR-RE-0022",
		    "Deletion for duplication failed :" + ex.getMessage(),
		    "Input is storable and uniquedetails.", ex);

	}
	return ruleData;
    }

    public List<HashMap> getRuleData(HashMap<String, String> dataMap,
	    String uniqueDetails) {
	log.methodEntry("getRuleData entry");
	List<HashMap> uniqueListKeys = new ArrayList<HashMap>();
	try {
	    if ((uniqueDetails != null) && (!uniqueDetails.trim().isEmpty())) {
		List<String> storableUniqueFields = new ArrayList();
		storableUniqueFields = Arrays.asList(uniqueDetails.split(","));
		// to convert json to hashmap
		// HashMap formattedMap = formatJson.in(uniqueDetails);
		/*
		 * try { log.debug("getting hashmap from convertor - unique" +
		 * formattedMap); formattedMap = (HashMap)
		 * formattedMap.get("unique"); } catch (GenericException ge) {
		 * log.error("In DuplicationHelper.getRule GE: " +
		 * ge.getMessage()); throw ge; } catch (Exception ex) {
		 * log.error("In DuplicationHelper.getRule EX: " +
		 * ex.getMessage()); throw new GenericException(domain,
		 * "ERR-RE-0009", "DuplicationHelper.getRuleData failure:" +
		 * ex.getMessage(), "Input is uniquejson string", ex); }
		 */
		// filling the rule data map
		/*
		 * if (formattedMap.containsKey((String)
		 * dataMap.get("EntityType"))) {
		 */
		log.debug("Incoming entitytype is there in unique.json:entitytype is "
			+ (String) dataMap.get("EntityType"));
		Map<String, Object> uniqueFields = new HashMap<String, Object>();
		for (String uniqueField : storableUniqueFields) {
		    uniqueFields.put(uniqueField,
			    (String) dataMap.get(uniqueField));
		}
		log.debug("UniqueFields for "
			+ (String) dataMap.get("EntityType") + " is "
			+ uniqueFields);
		for (Entry<String, Object> rule : uniqueFields.entrySet()) {
		    HashMap ruleMap = new HashMap();

		    String k = "";
		    String v = "";
		    String ruleName = "";
		    k = rule.getKey().trim();
		    v = ((String) rule.getValue()).trim();
		    if (!k.equals("") && !v.equals("")) {
			ruleName = dataMap.get("Site") + "_"
				+ dataMap.get("EntityType") + "_" + k + "_" + v;
			ruleMap.put("key", ruleName);
			ruleMap.put("value", dataMap.get("Id"));
		    }
		    ruleMap.put("site", dataMap.get("Site"));
		    ruleMap.put("entitytype",
			    (String) dataMap.get("EntityType"));
		    ruleMap.put("field", rule.getKey());
		    uniqueListKeys.add(ruleMap);

		}
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
	return uniqueListKeys;
    }

    public boolean isEmpty(String value) {
	if (value != null) {
	    value = value.trim();
	    return value.isEmpty();
	}
	return true;
    }
}
