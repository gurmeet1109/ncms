package in.nic.cmf.accesscontrol;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.util.DirectoryLogic;

import java.io.File;
import java.io.FileOutputStream;
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
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class ACLService {

	private PropertiesUtil                     proputil;
	private DirectoryLogic                     dirLogic;
	public LogTracer                           log;
	private KnowledgeBase                      kbase;
	private StatelessKnowledgeSession          ksession;
	private ACLHelper                          aclHelper;
	private static HashMap<String, ACLService> hashACLService = new HashMap<String, ACLService>();
	private String                             domain;

	private ACLService(String domain) {
		this.domain = domain;
		setLogger(domain);
		aclHelper = ACLHelper.getInstanceof(domain);
		proputil = PropertiesUtil.getInstanceof(domain, "accesscontrol");
		dirLogic = DirectoryLogic.getInstanceOf(domain);
		ksession = readKnowledgeBase("initial", aclHelper.checkDirectory());

	}

	public static ACLService getInstanceof(String domain) {
		if (!hashACLService.containsKey(domain)) {
			hashACLService.put(domain, new ACLService(domain));
		}
		return hashACLService.get(domain);
	}

	private void setLogger(String domain) {
		log = new LogTracer(domain, "acl");
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
					String createDirectoryPath = proputil.get("directoryPath")
					+ domain + proputil.get("rulePath")
					+ proputil.get("folderName");
					if (dirLogic
							.createDirectoryIfNotExists(createDirectoryPath)) {
						log.info(createDirectoryPath + "directory created");
						String path = createDirectoryPath + id;
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

			List<String> rules = aclHelper.checkDirectory();
			log.info("List of file for read knowledge is " + rules);
			readKnowledgeBase("addall", rules);
			log.methodExit("writeAcl exit");
		} catch (Exception ex) {
			log.error("Write ACL EX:" + ex.getMessage());
		}
		return true;
	}

	public boolean deleteACL(String domainName, String entityType, String id) {
		boolean result = false;
		try {
			log.methodEntry("deleteACL entry");

			String createDirectoryPath = (String) proputil.get("directoryPath")
			+ domainName + proputil.get("rulePath")
			+ proputil.get("folderName");

			String str = createDirectoryPath + id + ".xls";
			File f1 = new File(str);
			log.info("file path here is:::::::" + str);
			result = f1.exists();
			if (result) {
				log.info(f1.getAbsolutePath());
				if (f1.delete()) {
					log.info("delete status ");
					List<String> rules = aclHelper.checkDirectory();
					readKnowledgeBase("addall", rules);
					return result;
				}
			}
		} catch (GenericException ge) {
			log.error("Inside deleteDuplicate GE:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("Inside deleteDuplicate EX:" + ex.getMessage());
			throw new GenericException(domainName, "ERR-ACL-0016",
					"Delete ACL failed.", ex);
		}
		return result;
	}

	/**
	 * Read knowledge base.
	 * @param action
	 *            the action
	 * @param lstRules
	 *            the lst rules
	 * @return the stateless knowledge session
	 * @throws GenericException
	 *             the generic exception
	 */
	public StatelessKnowledgeSession readKnowledgeBase(String action,
			List<String> lstRules) {
		try {
			System.out.println("readknowlwdge");
			log.debug("readknowlwdge");
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
			.newKnowledgeBuilder();
			DecisionTableConfiguration configuration = KnowledgeBuilderFactory
			.newDecisionTableConfiguration();
			configuration.setInputType(DecisionTableInputType.XLS);

			String dirPath = proputil.getProperty("directoryPath") + domain
			+ "/resources/accesscontrol/" + "AccessControl.xls";
			System.out.println("readknowlwdge");
			log.debug("Accesscontrol: " + dirPath);
			lstRules.add(dirPath);

			for (String rule : lstRules) {
				try {
					log.debug("Adding to kbuilder:" + rule);
					kbuilder.add(ResourceFactory.newFileResource(rule),
							ResourceType.DTABLE, configuration);
				} catch (Exception ex) {
					log.error("kbuilder:" + ex.getMessage());
				}
			}

			try {
				if (kbuilder.hasErrors()) {
					KnowledgeBuilderErrors errors = kbuilder.getErrors();
					if (errors.size() > 0) {
						for (KnowledgeBuilderError error : errors) {
							log.debug("KBError:" + error.getMessage());
						}
					}
				}
			} catch (Exception ex) {
				log.error("Exception occured while getting Builder Errors:"
						+ kbuilder.getErrors().toString());
			}
			kbase = KnowledgeBaseFactory.newKnowledgeBase();
			kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
			ksession = kbase.newStatelessKnowledgeSession();

		} catch (GenericException ex) {
			log.error("Generic Catch : " + ex.getMessage());
		} catch (Exception e) {
			log.error("AclExecuteFailure Catch :" + e.getMessage());
		}
		return ksession;
	}

	public boolean execute(Map ac) {
		boolean result = false;

		log.debug("Incoming param for execute:" + ac);
		try {
			ac.put("aclstatus", false);
			List<Command> commandlist = new ArrayList<Command>();
			commandlist.add(CommandFactory.newSetGlobal("fields",
					ac.get("fields")));
			commandlist.add(CommandFactory.newInsert(ac));
			log.debug("Before execute:" + ac);
			ksession.execute(CommandFactory.newBatchExecution(commandlist));
			log.debug("After execute:" + ac);
			result = Boolean.parseBoolean(ac.get("aclstatus").toString());
			System.out.println("result:" + result);

		} catch (Exception ex) {
			log.error("Exception Catch : " + ex.getMessage());
			throw new GenericException(domain, "ERR-ACL-0005", this.getClass()
					.getSimpleName() + ":execute()");
		} finally {
			log.methodExit(this.getClass().getSimpleName() + ".execute()");
		}
		return result;
	}

	public Map<String, Object> getQueryParams(String domain, String entity,
			String id, Map<String, Map<String, Object>> parameters) {
		log.methodEntry("getQueryParams entry");
		Map<String, Object> inputMap = (Map<String, Object>) parameters
		.get("input");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("site", domain);
		queryParams.put("entitytype", entity);
		queryParams.put("action", "get");
		log.debug("incoming parameters:" + domain + "-" + entity + "-" + id
				+ "-" + parameters);

		if (inputMap.containsKey("queryParams")) {
			log.debug("queryParam is there.");
			queryParams.putAll((Map<String, String>) inputMap
					.get("queryParams"));
			log.debug("after putall" + queryParams);
			if (((id != null) || (!id.isEmpty()))
					&& (!queryParams.containsKey("id"))) {
				queryParams.put("id", id);
			}
			// queryParams.put("Site", domain);
		}
		if (inputMap.containsKey("headers")) {
			Map<String, String> headerMap = (Map<String, String>) inputMap
			.get("headers");
			if (headerMap.containsKey("method")) {
				queryParams.put("action", (String) headerMap.get("method"));
			}
		}
		log.debug("inqueryparam hashmap is;" + queryParams);
		log.methodExit("getQueryParams exit");
		return queryParams;
	}

	public Map<String, String> getAclNeededMap(Map<String, Object> inputMap,
			Map usercontext) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			String tempStatus = "false";
			log.debug("In getAclNeededMap:" + inputMap + "; usercontent:"
					+ usercontext);

			inputMap.putAll(usercontext);

			log.debug("before exemption:" + inputMap);
			Map<String, Object> aclNeededMap = aclHelper
			.exemptionForACL(inputMap);
			boolean result = aclHelper.isSkipAcl(aclNeededMap);
			log.debug("Is skipacl : " + result);
			if (result) {
				log.debug("Inside ! skipACL. So validating...");
				if (aclHelper.validateACLDetails(aclNeededMap)) {
					log.debug("validation is true. so invoking setAccessControl:"
							+ aclNeededMap);
					response = aclHelper.setAccessControl(aclNeededMap);
					log.debug("response from set default:" + response);
					response.put("aclStatus", tempStatus);
					return response;
				}
			} else {
				log.debug("No execution allowed.so setted aclStatus true ");
				response.put("aclStatus", "true");
			}

			log.debug("response:" + response);
		} catch (GenericException e) {
			throw new GenericException(domain, "ERR-ACL-0012",
					" ACL validation failed. ", "Input :");
		} catch (Exception e) {
			log.error("In EX:" + e.getMessage());
			throw new GenericException(domain, "ERR-ACL-0012",
					" ACL validation failed. ", "Input :");
		}
		return response;
	}

	public Map<String, Map<String, Object>> get(String domain, String entity,
			String id, Map<String, Map<String, Object>> parameters) {
		log.methodEntry("GET entry");
		boolean result = false;
		Map<String, Object> queryParams = getQueryParams(domain, entity, id,
				parameters);
		log.debug("Query params are :" + queryParams);
		try {
			log.debug("inside get statement.");
			Map<String, String> aclMap = new HashMap<String, String>();
			aclMap = getAclNeededMap(queryParams,
					aclHelper.getUserContext(parameters));
			log.info("ACL NeededMap ::" + aclMap);

			String status = aclMap.get("aclStatus");
			log.debug("aclstatus is :" + status);
			if (!status.equals("true")) {
				List<String> entities = aclHelper
				.getEntityTypes((String) aclMap.get("entitytype"));
				for (String entityName : entities) {
					aclMap.put("entitytype", entityName);
					result = execute(aclMap);
					log.info("Acl response for " + entityName + ":"
							+ result);
				}
			} else {
				result = true;
			}
			log.methodExit("GET exit");

		} catch (GenericException ge) {
			throw ge;
		} catch (Exception ex) {
			log.error("In Ex:" + ex.getMessage());
			throw new GenericException(domain, "ERR-ACL-0013",
					"ACL validation failed.", "Input :" + queryParams);
		}
		return aclHelper.buildResponse(domain, parameters, result);
	}


	public Map<String, Map<String, Object>> delete(String domain, String entity,
			String id, Map<String, Map<String, Object>> parameters) {
		log.methodEntry("DELETE entry");
		log.debug("Delete parameters:"+parameters);
		boolean result = false;
		String queryString = "";
		Map<String, Object> inputMap = (Map<String, Object>) parameters.get("input");
		if(inputMap.containsKey("queryString")){
			queryString = (String) inputMap.get("queryString");
		}
		log.debug("qs:"+queryString);
		if(queryString.contains("readknowledge")){
			log.debug("inside delete statement.");
			result = deleteACL(domain, entity, id);
		}else{
			log.debug("inside default get.");
			return get(domain,entity,id,parameters);
		}
		log.methodEntry("DELETE exit");
		return aclHelper.buildResponse(domain, parameters, result);
	}


	public Map<String, Map<String, Object>> post(String domain, String entity,
			Map<String, Map<String, Object>> parameters) {
		log.methodEntry("POST entry...");
		log.debug("incomping parameters are :" + parameters);
		boolean result = false;
		try {

			Map<String, String> queryParams = new HashMap<String, String>();
			String queryString = "";
			if (parameters.get("input").containsKey("queryString")) {

				log.debug("qs:" + parameters.get("input").get("queryString"));
				queryParams = (Map<String, String>) parameters.get("input")
				.get("queryParams");
				queryString = (String) parameters.get("input").get(
				"queryString");
			}
			// log.debug("queryParams:::::::======>" + queryParams);
			log.debug("Checking for radknowledge::::"
					+ queryString.contains("readknowledge"));
			if (queryString.contains("readknowledge")) {
				log.debug("read knowledgelogic");
				return read(domain, entity, parameters);
			} else {
				log.debug("acc logic");
				// Map<String, Object> map =
				// aclHelper.getFlattenMap(parameters);

				Map<String, Object> map = FormatXml.getInstanceof(domain).in(
						(String) parameters.get("input").get("content"));

				log.debug("map:" + map);

				for (Map.Entry<String, Object> entry : map.entrySet()) {

					log.debug("entry:" + entry);
					if (map.get(entry.getKey().toString()) instanceof List) {
						log.debug("inside if");

						log.debug("key:" + entry.getKey() + "value:"
								+ entry.getValue());

						List<Map<String, Object>> collectionList = (List) map
						.get(entry.getKey().toString());
						log.debug("collectionlist:" + collectionList);
						for (Map singleMap : collectionList) {
							singleMap.put("action", "post");
							result = execute(getAclNeededMap(singleMap,
									aclHelper.getUserContext(parameters)));
							if (!result) {
								return aclHelper.buildResponse(domain,
										parameters, result);
							}
						}
					} else {
						log.debug("in else:" + map);
						Map singleMap = (Map) map.get(entry.getKey());
						log.debug("singleMap:" + singleMap);
						singleMap.put("action", "post");
						log.debug("singleMappppp:" + singleMap);
						result = execute(getAclNeededMap(singleMap,
								aclHelper.getUserContext(parameters)));

					}
				}
			}
		} catch (GenericException ge) {
			throw ge;
		} catch (Exception ex) {
			log.error("In Ex:" + ex.getMessage());
			throw new GenericException(domain, "ERR-ACL-0013",
			"ACL validation failed.");
		}
		log.methodExit("POST exit");
		return aclHelper.buildResponse(domain, parameters, result);

	}

	public Map<String, Map<String, Object>> read(String domain, String entity,
			Map<String, Map<String, Object>> parameters) {
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
			log.debug("Binarry Value::::" + binaryval);
			result = writeAcl(domain, entity, parameters, binaryval);
		}
		// }
		return aclHelper.buildResponse(domain, parameters, result);
	}
}
