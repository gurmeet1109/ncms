package in.nic.cmf.services.ruleengine.duplication;

import in.nic.cmf.cache.RedisCache;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.domain.FieldDetails;
import in.nic.cmf.domain.Storable;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.util.ProcessEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RedisDuplicationProvider {

	private static final RedisDuplicationProvider redis = new RedisDuplicationProvider();
	public static LogTracer log = null;
	
	RedisCache redisCache = RedisCache.getInstance();
	private final FormatJson formatJson = FormatJson.getInstance();


	public static RedisDuplicationProvider getInstance() {
		log = new LogTracer("RuleEngineTraceLogger", true, true, true, true,
				true);
		return redis;
	}

	public String generateKeyWithId(Map<String,String> map) {
		return map.get("site") + "_" + map.get("entitytype") + "_"
		+ map.get("value");
	}

	public Map<String,String> validateDuplicate(Storable storable, String uniqueDetails,
			List<Map> tmpDetails, String errorMsg) {
		log.methodEntry("validateDuplicate entry");
		Map resultMap = new HashMap();
		List<FieldDetails> lstFieldDetails = new ArrayList<FieldDetails>();
		try {
			Map<String, Object> tmphash = new HashMap<String, Object>();
			List<Map> processedData = processRawData(storable, uniqueDetails);
			String keyWithId = "";
			for (Map<String, String> map : processedData) {
				if (map != null) {
					String existingId = redisCache.get(map.get("key")
							.toLowerCase());
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
							FieldDetails fieldDetails = new FieldDetails();
							fieldDetails.setFieldError(map.get("field") + " "
									+ errorMsg);
							fieldDetails.setFieldName(map.get("field"));
							lstFieldDetails.add(fieldDetails);

						} else {
							log.debug("in inner else:" + map);
							tmphash.put(map.get("key").toLowerCase(),
									map.get("value").toLowerCase());
						}
					} else {
						log.debug("in outer else:" + map);
						tmphash.put(map.get("key").toLowerCase(),
								map.get("value").toLowerCase());
					}
					keyWithId = generateKeyWithId(map);
				}
			}
			log.debug("before pushing tmphash using generateValues:" + tmphash);
			tmphash.put(keyWithId.toLowerCase(), generateValues(tmphash));
			tmpDetails.add(tmphash);
			resultMap.put("tmpdetails", tmpDetails);
			resultMap.put("fielddetails", lstFieldDetails);
		} catch (GenericException ge) {
			log.error("In GE : " + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("In EX :" + ex.getMessage());
			throw new GenericException("ERR-RE-0017",
					"unable to validate duplication:" + ex.getMessage(),
					"Input is collections, uniquedetails and tmpdetails.", ex);
		} finally {
			log.methodExit("validateDuplicate exit");
		}
		return resultMap;
	}

	private String generateValues(Map<String, Object> tmphash) {
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
			throw new GenericException("ERR-RE-0020",
					"Value formation for duplication failed :"
					+ ex.getMessage(), "Input is temporary hashmap.",
					ex);
		}
		return value;
	}

	public boolean generateForDuplication(List<Map> tmpDetails) {
		boolean result = false;
		try {
			log.methodEntry("generateForDuplication entry.Incoming parameter is :" + tmpDetails);
			for (Map<String, String> map : tmpDetails) {
				log.debug("Iterating from tmpDetails.Each map is:"+map);
				for (Entry<String, String> rule : map.entrySet()) {
					log.debug("Iterating entryset from map:"+rule);
					try{
						deleteDuplicate(rule.getKey().toLowerCase());
					}catch(Exception ex){
						log.debug("Exception occured at deleteDuplicate:"+ex.getMessage());
					}
					if(!isEmpty(rule.getKey()) && !isEmpty(rule.getValue())){
						log.debug("making the key and value tolowercase");
						redisCache.set(rule.getKey().toLowerCase(), rule.getValue()
								.toLowerCase());
						log.debug("key and value setted in rediscache.");
					}
				}
			}
			result = true;
			log.methodExit("generateForDuplication exit");
		} catch (GenericException ge) {
			log.error("In GE : " + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("In EX Catch of generateForDuplication : "
					+ ex.getMessage());
			throw new GenericException("ERR-RE-0015",
					"Value formation for duplication failed :"
					+ ex.getMessage(), "Input is temporary hashmap.",
					ex);
		}
		return result;
	}

	public boolean deleteDuplicate(String key) {
		boolean result = false;
		try {
			log.methodEntry("deleteDuplicate entry. Input parameter:"+key);
			if(!isEmpty(key)){
				String existingKey = redisCache.get(key.toLowerCase());
				log.debug("Existing key is :" + existingKey);
				String dem = ";";
				if (!isEmpty(existingKey)) {
					log.debug("ExistingKey is not empty.Delimiter is "+dem);
					String[] keyarray = existingKey.split(dem);
					log.debug("keyarray is:"+keyarray);
					for (String delKey : keyarray) {
						log.debug("Going to delete this key :"+delKey);
						redisCache.delete(delKey.toLowerCase());
					}
				}
				log.debug("Full key including child is going to delete :" + key);
				try{
					redisCache.delete(key.toLowerCase());
				}catch(Exception ex){
					log.error("In EX:"+ex.getMessage());
				}
			}
			result = true;
			log.methodExit("deleteDuplicate exit");
		} catch (GenericException ge) {
			log.error("In GE : " + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("In EX Catch of deleteDuplicate : " + ex.getMessage());
			throw new GenericException("ERR-RE-0021",
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

	public List<Map> processRawData(Storable storable, String uniqueDetails) {
		List<Map> ruleData = new ArrayList<Map>();
		log.methodEntry("processRawData entry");
		try {
			ProcessEntity processEntity = new ProcessEntity();
			Map flattenedEntity = processEntity.processObject(storable,
					"getflatmap");
			ruleData = getRuleData(flattenedEntity, uniqueDetails);
			log.debug("ruleData is:" + ruleData);
			log.methodExit("processRawData exit");
		} catch (GenericException ge) {
			log.error("In GE : " + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("In processRawData EX Catch :" + ex.getMessage());
			throw new GenericException("ERR-RE-0022",
					"Deletion for duplication failed :" + ex.getMessage(),
					"Input is storable and uniquedetails.", ex);
		}
		return ruleData;
	}

	public List<Map> getRuleData(Map<String, Object> dataMap,
			String uniqueDetails) {
		log.methodEntry("getRuleData entry");
		List<Map> uniqueListKeys = new ArrayList<Map>();
		try {
			// to convert json to hashmap
			HashMap formattedMap = formatJson.in(uniqueDetails);
			try {
				log.debug("getting hashmap from convertor - unique"
						+ formattedMap);
				formattedMap = (HashMap) formattedMap.get("unique");
			} catch (GenericException ge) {
				log.error("In DuplicationHelper.getRule GE: " + ge.getMessage());
				throw ge;
			} catch (Exception ex) {
				log.error("In DuplicationHelper.getRule EX: " + ex.getMessage());
				throw new GenericException("ERR-RE-0009",
						"DuplicationHelper.getRuleData failure:"
						+ ex.getMessage(),
						"Input is uniquejson string", ex);
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
				for (Entry<String, Object> rule : uniqueFields.entrySet()) {
					HashMap ruleMap = new HashMap();
					String ruleName = dataMap.get("Site") + "_"
					+ dataMap.get("EntityType") + "_" + rule.getKey()
					+ "_" + rule.getValue();
					ruleMap.put("key", ruleName);
					ruleMap.put("value", dataMap.get("Id"));
					ruleMap.put("site", dataMap.get("Site"));
					ruleMap.put("entitytype",
							(String) dataMap.get("EntityType"));
					ruleMap.put("field", rule.getKey());
					uniqueListKeys.add(ruleMap);
				}
			} else {
				log.error((String) dataMap.get("EntityType")
						+ "does not contain unique key.");
			}
		} catch (GenericException ge) {
			log.debug("In GE getRuleData:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.debug("In EX getRuleData:" + ex.getMessage());
			throw new GenericException("ERR-RE-0009",
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
