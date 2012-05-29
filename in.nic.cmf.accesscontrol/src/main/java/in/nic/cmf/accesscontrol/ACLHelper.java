package in.nic.cmf.accesscontrol;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.DirectoryLogic;
import in.nic.cmf.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ACLHelper {

	private DirectoryLogic dirLogic;

	private PropertiesUtil proputil;
	private LogTracer log;
	private List<String> exemptions = new ArrayList<String>();
	private ConvertorUtils cu;
	private Utils utils;

	public static final String ACL = "accesscontrollist";
	private static HashMap<String, ACLHelper> hashACLHelper = new HashMap<String, ACLHelper>();

	private String domain;

	private ACLHelper(String domain) {
		this.domain = domain;
		setLogger(domain);
		proputil = PropertiesUtil.getInstanceof(domain, "accesscontrol");
		cu = ConvertorUtils.getInstanceof(domain);
		utils = Utils.getInstanceof(domain);
		dirLogic = DirectoryLogic.getInstanceOf(domain);
		exemptions = getExemptionList();
	}

	private void setLogger(String domain) {
		log = new LogTracer(domain, "acl");
	}

	public static ACLHelper getInstanceof(String domain) {
		if (!hashACLHelper.containsKey(domain)) {
			hashACLHelper.put(domain, new ACLHelper(domain));
		}
		return hashACLHelper.get(domain);
	}

	public boolean isSkipAcl(Map<String, Object> request) {
		boolean flagAcl = true;
		log.info("inside isSkipAcl");
		try {
			String EntityType = (String) request.get("entitytype");
			String roleName = (String) request.get("role");
			String userName = (String) request.get("username");
			String authusername = (String) request.get("authusername");
			String aclRoleName = (String) request.get("aclrole");
			if (EntityType != null) {
				if (EntityType.equals("cmsuserprofile")) {
					if ((authusername != null && userName != null)) {
						if (userName.equals(authusername)) {
							flagAcl = false;
						}
					}
				}

				if (EntityType.equals("role")) {
					if (aclRoleName != null && roleName != null) {
						if (roleName.equals(aclRoleName)) {
							flagAcl = false;
						}
					}
				}
			}
			log.methodExit("inside isSkipAcl returns " + flagAcl);
			return flagAcl;
		} catch (Exception e) {
			log.error("Exception inside isSkipAcl " + e.getMessage());
			throw new GenericException(domain, "ERR-ACL-0000", this.getClass()
					.getSimpleName() + ".isSkipAcl()", e);
		}
	}

	public boolean validateACLDetails(Map requestDetails) {
		log.info("inside validateACLDetails" + requestDetails);
		boolean result = false;
		try {
			if (!requestDetails.containsKey("action")
					|| utils.isEmpty((String) requestDetails.get("action"))) {
				throw new GenericException(domain, "ERR-ACL-0006",
						"action does not exist.", "Input :" + requestDetails);
			}
			result = true;
		} catch (GenericException ge) {
			throw new GenericException(domain, " ERR-ACL-0012",
					"ACL validation failed.", "Input :" + requestDetails);
		}
		return result;
	}

	public Map setAccessControl(Map<String, Object> accessControl) {
		log.methodEntry("setAccessControl entry");
		Map aclMap = new HashMap<String, String>();
		log.info("Incoming map:" + accessControl);
		try {
			log.debug("before entry");
			for (Entry<String, Object> acl : accessControl.entrySet()) {
				log.debug("in acl:" + acl);
				if (acl.getValue() != null) {
					log.debug("getvalue is not null");
					if (cu.isString(acl.getValue())) {
						String key = acl.getKey();
						log.debug("key:" + key);
						String value = (String) acl.getValue();
						log.debug("value:" + value);
						aclMap.put(key.toLowerCase(), value.toLowerCase());
					} else {

						if (acl.getValue() instanceof List) {
							List vlueList = (List) acl.getValue();
							for (int i = 0; i < vlueList.size(); i++) {
								Map valueMap = (Map) vlueList.get(i);
								log.debug("Final map is_>>>>>>>>>>" + valueMap);
								aclMap.putAll(getdefaultMap(valueMap));
							}

						} else if (acl.getValue() instanceof HashMap) {
							Map valueMap = (Map) acl.getValue();
							aclMap.putAll(getdefaultMap(valueMap));
							log.debug(" included in hash:" + valueMap);
						}

					}
				}
			}

			log.info("after for loop" + aclMap);
			if (!aclMap.containsKey("entitytype")
					|| utils.isEmpty((String) aclMap.get("entitytype"))
					|| ((String) aclMap.get("entitytype")).equals("search")) {
				aclMap.put("entitytype", proputil.get("default-entitytype"));
			}
			if (!aclMap.containsKey("aclrole")
					|| (aclMap.get("aclrole").equals("null") || utils
							.isEmpty((String) aclMap.get("aclrole")))) {
				aclMap.put("aclrole", proputil.get("default-aclrole"));
			}
			if (!aclMap.containsKey("status") || (aclMap.get("status") == null)
					|| (aclMap.get("status").equals("null"))
					|| utils.isEmpty((String) aclMap.get("status"))) {
				aclMap.put("status", proputil.get("default-status"));
			}
			if (!aclMap.containsKey("authusername")
					|| (aclMap.get("authusername").equals("null"))
					|| utils.isEmpty((String) aclMap.get("authusername"))) {
				aclMap.put("authusername", "");
			}
			if (!aclMap.containsKey("createdby")
					|| (aclMap.get("createdby").equals(null))
					|| utils.isEmpty((String) aclMap.get("createdby"))) {
				aclMap.put("createdby", "");
			}
			if (!aclMap.containsKey("assignedto")
					|| (aclMap.get("assignedto").equals(null))
					|| utils.isEmpty((String) aclMap.get("assignedto"))) {
				aclMap.put("assignedto", "");
			}
			if (!aclMap.containsKey("site")
					|| (aclMap.get("site").equals(null))
					|| utils.isEmpty((String) aclMap.get("site"))) {
				aclMap.put("site", "");
			}
			if (!aclMap.containsKey("fields")
					|| (aclMap.get("fields").equals(null))
					|| utils.isEmpty((String) aclMap.get("fields"))) {
				aclMap.put("fields", proputil.get("default-fields"));
			}
			aclMap.put("aclstatus", false);
			log.info("after fill empty fields******* the map is" + aclMap);
		} catch (GenericException ge) {
			throw ge;
		} catch (Exception ex) {
			log.error("In Ex:" + ex.getMessage());
			throw new GenericException(domain, " ERR-ACL-0015",
					"Set Accesscontrol failed.", "Input : " + accessControl);
		}
		return aclMap;
	}

	private Map<String, String> getdefaultMap(Map<String, String> valueMap) {
		log.debug("Inside getdefaultMap>>>>>>>>" + valueMap);
		Map<String, String> defaultMap = new HashMap();
		for (Entry<String, String> value : valueMap.entrySet()) {
			String key = value.getKey();
			log.debug("key:" + key);
			if (value.getValue() != null && value.getValue() instanceof String) {
				String value1 = (String) value.getValue();
				log.debug("value:" + value);
				defaultMap.put(key.toLowerCase(), value1.toLowerCase());
			}

		}
		return defaultMap;

	}

	public Map<String, Object> exemptionForACL(Map requestedMap) {
		log.info("before romving exemptions list" + requestedMap);
		try {
			for (String key : exemptions) {
				if (requestedMap.containsKey(key)) {
					requestedMap.remove(key);
					log.info("reomoved key is" + key);
				}
			}

		} catch (Exception ex) {
			throw new GenericException(domain, ex.getMessage());
		}
		log.info("After romving exemptions list" + requestedMap);
		return requestedMap;
	}

	private List<String> getExemptionList() {
		String[] domain = proputil.getProperty("aclexemption").split(",");
		return Arrays.asList(domain);
	}

	public List<String> getEntityTypes(String entitytype) {
		String[] domain = entitytype.split(",");
		return Arrays.asList(domain);
	}

	public Map<String, Map<String, Object>> buildResponse(String domain,
			Map<String, Map<String, Object>> parameters, boolean result) {
		Map<String, Object> response = new HashMap<String, Object>();
		if (result) {
			response.put("statusCode", "200");
			response.put("content", "<Collections><Success>true</Success></Collections>");
		} else {
			throw new GenericException(domain, "ERR-ACL-0011");
		}
		parameters.put("output", response);
		log.debug("Response Map : " + response);
		return parameters;
	}

	public Map<String, Object> getFlattenMap(Map<String, Object> parameters) {
		Map<String, Object> xmlMap = new HashMap<String, Object>();
		try {
			xmlMap = FormatFlatten.getInstanceOf(domain, true).in(xmlMap);
			log.debug("FormatFlatten returns xmlMap");
		} catch (Exception ex) {
			log.error("In Ex:" + ex.getMessage());
		}
		Map<String, Object> map = (Map<String, Object>) xmlMap
		.get("Collections");
		return map;
	}

	public Map<String, String> getUserContext(
			Map<String, Map<String, Object>> parameters) {
		Map<String, String> usercontext = (Map<String, String>) parameters.get(
		"input").get("userContext");
		return usercontext;
	}

	/*
	 * public List<String> getAccessControlls() { ServiceClientImpl
	 * serviceClient = ServiceClientImpl.getInstance("applicationflow"); //can
	 * use dynamic auth for (String eachDomain : getDomain()) {
	 * Map<String,Map<String,Object>> parameters = new
	 * HashMap<String,Map<String,Object>>(); Map<String, Object> inputmap = new
	 * HashMap<String, Object>(); inputmap.put("queryString",
	 * proputil.get("query") + ACL); // inputmap.put("readKnowledge", true);
	 * parameters.put("input", inputmap); log.debug("before serviceClient:" +
	 * parameters); Map<String,Map<String,Object>>response =
	 * serviceClient.find(eachDomain, ACL, parameters);
	 * System.out.println(response); List<String> files = getFilePath(response);
	 * System.out.println(files); List<String> lst =
	 * retrieveFilePath(eachDomain,files); System.out.println(lst); } return
	 * null; } private List<String> getDomain() {
	 * log.methodEntry("getdomain entry."); log.debug("Domains are :" + (String)
	 * proputil.getProperty("domain")); String[] domain =
	 * proputil.getProperty("domain").split(","); return Arrays.asList(domain);
	 * } public List<String> getFilePath(Map<String,Map<String,Object>> map){
	 * List<String> listFilePath = new ArrayList<String>(); Map<String, Object>
	 * outputMap = map.get("output"); if(outputMap.containsKey("content")){
	 * String content = (String) outputMap.get("content"); Map<String, Object>
	 * collections = (HashMap)
	 * FormatXml.getInstance().in(content).get("Collections"); for
	 * (Entry<String, Object> eachMap : collections.entrySet()) { Object value =
	 * eachMap.getValue(); if (cu.isArrayList(value)) { for (Map<String, Object>
	 * storableMap : (List<HashMap<String, Object>>) value) {
	 * listFilePath.add((String) storableMap.get("FilePath")); } } else if
	 * (cu.isHashMap(value)) { Map<String, Object> storableMap =
	 * (HashMap<String, Object>) value; listFilePath.add((String)
	 * storableMap.get("FilePath")); } } } return listFilePath; } private
	 * List<String> retrieveFilePath(String domainName, List<String>
	 * listFilePath) { List<String> files = new ArrayList<String>(); try {
	 * ServiceClientImpl client =
	 * ServiceClientImpl.getInstance("applicationflow"); for (String file :
	 * listFilePath) { String[] bits = file.split("/"); String eachfile =
	 * bits[bits.length - 1]; if (eachfile.contains(".xls")) {
	 * log.debug("File contains xls."); try { Map<String,Map<String,Object>>
	 * parameters = new HashMap<String,Map<String,Object>>(); Map<String,
	 * Map<String, Object>> resultMap = client.findById( domainName,
	 * "accesscontrollist", eachfile, parameters);
	 * log.debug("After GetByFileName and before writing excel."); if
	 * (resultMap.get("output").get("statusCode").toString().equals("200")) {
	 * String pathName = proputil.get("directoryPath")+domainName; writeExcel(
	 * (byte[]) resultMap.get("output").get("content"), pathName + eachfile);
	 * files.add(pathName + eachfile); log.debug("After writing excel:" +
	 * pathName + eachfile); } } catch (GenericException ge) {
	 * log.error("In GE of Model.retrieveFilePath : " + ge.getMessage()); //
	 * throw ge; } catch (Exception ex) {
	 * log.error("In Exception : GetByFilename failure:" + ex.getMessage()); //
	 * throw new GenericException("ERR-GEN-0000", //
	 * " Retrieve file path failed.", ex); } } } } catch (Exception ex) {
	 * log.error("RetriveFilePath failed : " + ex.getMessage()); } return files;
	 * } public void writeExcel(byte[] data, String filename) { try {
	 * log.methodEntry("writeExcel entry"); FileOutputStream fos = new
	 * FileOutputStream(filename); fos.write(data); fos.close();
	 * log.methodExit("writeExcel exit"); } catch (Exception ex) {
	 * log.debug("Exception occured in writeExcel:" + ex.getMessage()); throw
	 * new GenericException("ERR-GEN-0009", "Failed to create file excel.", ex);
	 * } }
	 */
	/*
	 * public List<String> listFilesFromDirectory(String directoryPath) {
	 * List<String> fullpath = new ArrayList<String>();
	 * log.debug("Incoming path:" + directoryPath); File dir = new
	 * File(directoryPath); String[] childrens = dir.list();
	 * log.debug("Childrens in " + directoryPath + " : " + childrens); if
	 * (childrens != null && !(childrens.length <= 0)) {
	 * log.debug("Child directory is there"); for (String eachDomain :
	 * childrens) { String eachfullpath = directoryPath + "/" + eachDomain +
	 * "/"; log.debug("Fullpath:" + eachfullpath); for (String inner :
	 * findInnerchild(eachfullpath)) { fullpath.add(inner); } } }
	 * log.debug("All files are : " + fullpath); return fullpath; } public
	 * List<String> findInnerchild(String eachfullpath) { File dir = new
	 * File(eachfullpath); String[] children = dir.list(); List<String> fullpath
	 * = new ArrayList(); if (children != null && !(children.length <= 0)) { for
	 * (String eachFile1 : children) { String eachfullpath1 = eachfullpath +
	 * eachFile1; System.out.println("innerFullpath:" + eachfullpath1);
	 * fullpath.add(eachfullpath1); } } return fullpath; }
	 */
	public List<String> checkDirectory() {
		List<String> rules = new ArrayList<String>();
		try {
			String path = proputil.get("directoryPath") + domain
			+ proputil.get("rulePath");
			log.debug("PATH:" + path);
			rules = dirLogic.listFilesFromDirectory(path + ACL,
			"accesscontrollist");

			System.out.println(rules);
			log.debug("Returns:" + rules);
		} catch (Exception ex) {
			log.error("In EX of CheckDirectory:" + ex.getMessage());
		}
		return rules;
	}
}
