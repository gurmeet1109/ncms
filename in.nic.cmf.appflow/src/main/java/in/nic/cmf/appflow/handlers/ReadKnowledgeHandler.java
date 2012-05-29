package in.nic.cmf.appflow.handlers;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

public class ReadKnowledgeHandler implements WorkItemHandler {
	private StatefulKnowledgeSession ksession;
	private AppflowHelper            appflowHelper;
	public LogTracer                 log;
	private String                   domain;
	private ConvertorUtils           cu;

	/**
	 * Sets the log.
	 * @param log
	 *            the new log
	 */
	public void setLog(LogTracer log) {
		this.log = log;
	}

	/**
	 * Instantiates a new search api handler.
	 * @param ksession
	 *            the ksession
	 */
	public ReadKnowledgeHandler(String domain, StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
		this.domain = domain;
		appflowHelper = AppflowHelper.getInstanceof(domain);
		cu = ConvertorUtils.getInstanceof(domain);
	}

	/**
	 * Execute work item.
	 * Purpose : Through ServiceClient it triggers SE service.
	 * Each action (POST/GET/DELETE) triggers respective serviceclient methods.
	 * @param workItem
	 *            the work item
	 * @param manager
	 *            the manager
	 */
	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		try {
			appflowHelper.setLog(log);
			log.methodEntry("ReadKnowledgeHandler entry");
			WorkflowProcessInstance processInstance = appflowHelper
			.initializer(ksession);

			Map<String, Map<String, Object>> inputMap = (Map<String, Map<String, Object>>) processInstance
			.getVariable(HelperConstant.REQDETAILS);

			Map<String, Map<String, Object>> reqDetails = (Map<String, Map<String, Object>>) processInstance
			.getVariable(HelperConstant.REQDETAILS);
			log.debug("After initializer:" + reqDetails);
			String domainName = (String) processInstance
			.getVariable(HelperConstant.DOMAIN);
			String entityType = (String) processInstance
			.getVariable(HelperConstant.ENTITY);
			String id = (String) processInstance.getVariable(HelperConstant.ID);
			log.debug("param:" + domainName + entityType + id);
			if (entityType != null) {
				entityType = entityType.toLowerCase();
			}
			switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
					.getVariable(HelperConstant.METHOD))) {
					case post:

						log.debug("In ReadKnowledge:Handler  POST CASE");
						reqDetails = checkAndReadknowledge("read", domainName,
								entityType, id, reqDetails, processInstance);
						break;

					case posttodms:
						log.debug("In ReadKnowledge:Handler  POST to DMS case :"
								+ reqDetails);
						reqDetails = checkAndReadknowledge("read", domainName,
								entityType, id, reqDetails, processInstance);
						break;

					case delete:
						log.debug("In ReadKnowledge:Handler Delete case:"
								+ domainName + "::" + entityType + "::"
								+ reqDetails);
						reqDetails = checkAndReadknowledge("delete", domainName,
								entityType, id, reqDetails, processInstance);
						break;
			}
			log.debug("After switch case:" + reqDetails);

			reqDetails = inputMap;
			log.debug("before finalizer" + reqDetails);
			processInstance = appflowHelper
			.finalizer((String) processInstance
					.getVariable(HelperConstant.METHOD),
					"ReadKnowledge: ", processInstance, reqDetails);

			manager.completeWorkItem(workItem.getId(), null);
			log.methodExit("ReadKnowledge exit");
		} catch (GenericException ge) {
			log.debug("In GE:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.debug("In EX:" + ex.getMessage());
			throw new GenericException(domain, "ERR-AF-0018",
					"Exception at ReadKnowledge API handler:", ex);
		}
	}

	private Map<String, Map<String, Object>> checkAndReadknowledge(
			String droolsAction, String domainName, String entityType,
			String id, Map<String, Map<String, Object>> reqDetails,
			WorkflowProcessInstance processInstance) {

		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		try {
			log.debug("checkAndReadknowledge incoming parameters: "
					+ reqDetails);
			String qString = (String) reqDetails.get("input")
			.get("queryString");
			log.debug("qstirng is:" + qString);
			if (qString == null || qString.equals("null")) {
				log.debug("1:" + qString);
				qString = "&";
			} else if (!qString.isEmpty()) {
				log.debug("2:" + qString);
				qString += "&";
			}
			qString += "readknowledge=true";
			log.debug("qString:" + qString);
			reqDetails.get("input").put("queryString", qString);
			log.debug("after put queryString:" + reqDetails);

			response = invokeService(droolsAction, domainName, entityType, id,
					reqDetails, processInstance);

			log.debug("response:" + response);
		} catch (Exception ex) {
			log.error("checkReadKnowledge failure:" + ex.getMessage());
		}
		return response;
		// return reqDetails;
	}

	private Map<String, Map<String, Object>> formDuplication(String domainName,
			Map<String, Map<String, Object>> reqDetails,
			WorkflowProcessInstance processInstance) {
		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		log.debug("inside formDuplication:"+reqDetails);
		if( reqDetails.size() != 0 || !reqDetails.equals(null)){
			try {
				ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
						domainName, "ruleengine");
				log.debug("after instance");
				reqDetails.get("input").put("content",
						processInstance.getVariable("duplication"));
				reqDetails.get("input").put("queryString", "&duplication=true");
				log.debug("before res:" + reqDetails);
				response = serviceclient.add(domainName, "duplication", reqDetails);
				log.debug("after res:" + response);

			} catch (Exception ex) {
				log.debug("Error:" + ex.getMessage());
			}
		}
		return response;
	}

	private Map<String, Map<String, Object>> deleteDuplication(
			String domainName, String entityType, String id,
			Map<String, Map<String, Object>> reqDetails,
			WorkflowProcessInstance processInstance) {
		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		try {
			ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(
					domainName, "ruleengine");
			log.debug("after instance");
			reqDetails.get("input").put("queryString", "&duplication=true");
			log.debug("before res:" + reqDetails);
			response = serviceclient.deleteByID(domainName, entityType, id,
					reqDetails);
			log.debug("after res:" + response);
		} catch (Exception ex) {
			log.debug("Error:" + ex.getMessage());
		}
		return response;
	}

	private Map<String, Map<String, Object>> invokeService(String droolsAction,
			String domainName, String entityType, String id,
			Map<String, Map<String, Object>> reqDetails,
			WorkflowProcessInstance processInstance) {

		log.debug("Method entry invokeService :" + reqDetails);
		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		Map<String, Object> contentMap = new HashMap<String, Object>();

		response = reqDetails;

		if (droolsAction.equals("read")) {

			if (response.get("output").get("statusCode").toString()
					.equals("200")) {
				contentMap = FormatXml.getInstanceof(domain).in(
						(String) response.get("input").get("content"));
				log.debug("collections:" + contentMap);
			}

			log.debug("in read:" + entityType);
			Map<String, Object> entities = (HashMap<String, Object>) contentMap
			.get("Collections");

			log.debug("Collections::::" + entities);
			for (Entry<String, Object> eachMap : entities.entrySet()) {
				if (!eachMap.getKey().equals("files")) {
					Object value = eachMap.getValue();
					if (cu.isHashMap(value)) {
						log.debug("hashmap");
						Map<String, Object> map = (Map<String, Object>) value;
						String entity = (String) map.get("EntityType");
						log.debug("read-hashmap:" + entity);

						response = read(domain, entity, response,
								(Map<String, Object>) value);
						log.debug("response:"+response);

					} else if (cu.isArrayList(value)) {
						log.debug("Arraylist");
						response = read(domain, response,
								(List<Map<String, Object>>) value);
					}
				}
			}
			log.debug("for formqueryparam and duplication b4");
			response = formQueryParam(response, HelperConstant.DUP_FLAG);
			log.debug("formQueryParam output:"+response);
			response = formDuplication(domainName, reqDetails, processInstance);
			log.debug("formDuplication output:"+response);
			// boolean result = formDuplication(domainName, response,
			// processInstance);
		} else if (droolsAction.equals("delete")) {
			log.debug("delete:" + entityType + ";Id" + id);
			response = delete(domain, entityType, id, response);
			log.debug("afterr delete:");
			//response = formQueryParam(reqDetails, HelperConstant.DUP_FLAG);
			log.debug("afterr formparam:"+response);
			response = deleteDuplication(domainName, entityType, id, reqDetails,
					processInstance);
			 log.debug("postreponse from formDuplication method  " + response);

		}
		log.debug("OUTPUTOFREADKNOWLEDGE-Response Object:" + response);
		log.debug("OUTPUTOFREADKNOWLEDGE-Request Object:" + response);
		return reqDetails;
	}

	/**
	 * Abort work item.
	 * @param workItem
	 *            the work item
	 * @param manager
	 *            the manager
	 */
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.completeWorkItem(workItem.getId(), null);
	}

	private Map<String, Map<String, Object>> read(String domainName,
			String entityType, Map<String, Map<String, Object>> reqDetails,
			Map<String, Object> value) {
		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		log.methodEntry("read entry:" + entityType);
		if (entityType != null) {
			entityType = entityType.toLowerCase();
			if (entityType.equals("workflow")) {
				log.debug("workflow");
				reqDetails = formQueryParam(reqDetails,
						HelperConstant.READ_KNOW_FLAG);
				ServiceClientImpl serviceclient = ServiceClientImpl
				.getInstance(domainName, "workflow");
				log.debug("after instance");
				response = serviceclient
				.add(domainName, entityType, reqDetails);
				log.debug("res:" + response);
			}

			if (entityType.equals("accesscontrollist")) {
				log.debug("acl");
				reqDetails = formQueryParam(reqDetails,
						HelperConstant.READ_KNOW_FLAG);
				ServiceClientImpl serviceclient = ServiceClientImpl
				.getInstance(domainName, "acl");
				response = serviceclient
				.add(domainName, entityType, reqDetails);
			}
			if (entityType.equals("validations")) {
				log.debug("validations");
				reqDetails = formQueryParam(reqDetails,
						HelperConstant.READ_KNOW_FLAG);
				ServiceClientImpl serviceclient = ServiceClientImpl
				.getInstance(domainName, "ruleengine");
				response = serviceclient
				.add(domainName, entityType, reqDetails);
			}
		}
		log.methodExit("read exit");
		return reqDetails;
	}

	private Map<String, Map<String, Object>> delete(String domainName,
			String entityType, String id,
			Map<String, Map<String, Object>> reqDetails) {
		log.methodEntry("delete entry");
		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		log.debug("in delete:" + entityType);
		if (entityType != null) {
			entityType = entityType.toLowerCase();
			if (entityType.equals("workflow")) {
				log.debug("workflow");
				reqDetails = formQueryParam(reqDetails,
						HelperConstant.READ_KNOW_FLAG);
				ServiceClientImpl serviceclient = ServiceClientImpl
				.getInstance(domainName, "workflow");
				response = serviceclient.deleteByID(domainName, entityType, id,
						reqDetails);
				log.debug("res:" + response);
			}

			if (entityType.equals("accesscontrollist")) {
				log.debug("acl");
				reqDetails = formQueryParam(reqDetails,
						HelperConstant.READ_KNOW_FLAG);
				ServiceClientImpl serviceclient = ServiceClientImpl
				.getInstance(domainName, "acl");
				response = serviceclient.deleteByID(domainName, entityType, id,
						reqDetails);
			}
			if (entityType.equals("validations")) {
				log.debug("validations");
				reqDetails = formQueryParam(reqDetails,
						HelperConstant.READ_KNOW_FLAG);
				ServiceClientImpl serviceclient = ServiceClientImpl
				.getInstance(domainName, "ruleengine");
				response = serviceclient.deleteByID(domainName, entityType, id,
						reqDetails);
			}
		}
		log.methodExit("delete exit");
		return response;
	}

	private Map<String, Map<String, Object>> read(String domainName,
			Map<String, Map<String, Object>> reqDetails,
			List<Map<String, Object>> values) {
		log.debug("read list entry");
		Map<String, Map<String, Object>> response = new HashMap<String, Map<String, Object>>();
		for (Map<String, Object> value : values) {
			String entityType = (String) value.get("EntityType");
			response = read(domainName, entityType, reqDetails,
					(Map<String, Object>) value);
		}
		log.debug("read list exit");
		return response;
	}

	private Map<String, Map<String, Object>> formQueryParam(
			Map<String, Map<String, Object>> reqDetails, String action) {
		log.debug("inside formQueryParam:"+reqDetails+action);
		
		if( reqDetails!= null && reqDetails.containsKey("input")){
			String qString = (String) reqDetails.get("input").get("queryString");
			log.debug("qstirng is:" + qString);
			if (qString == null || qString.equals("null")) {
				log.debug("1:" + qString);
				qString = "&";
			} else if (!qString.isEmpty()) {
				log.debug("2:" + qString);
				qString += "&";
			}
			qString += action;// "readknowledge=true";
			log.debug("qString:" + qString);
			reqDetails.get("input").put("queryString", qString);
			log.debug("after put queryString:" + reqDetails);
		}
		log.debug("formQueryParam exit"+reqDetails); 
		return reqDetails;
	}

}
