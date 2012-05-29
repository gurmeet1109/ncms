package in.nic.cmf.appflow;

import in.nic.cmf.appflow.handlers.ACLHandler;
import in.nic.cmf.appflow.handlers.AuditHandler;
import in.nic.cmf.appflow.handlers.DMSHandler;
import in.nic.cmf.appflow.handlers.EmailHandler;
import in.nic.cmf.appflow.handlers.FinalizeHandler;
import in.nic.cmf.appflow.handlers.GenerateHandler;
import in.nic.cmf.appflow.handlers.GeotaggerHandler;
import in.nic.cmf.appflow.handlers.LdapHandler;
import in.nic.cmf.appflow.handlers.MediaHandler;
import in.nic.cmf.appflow.handlers.MetadataExtractorHandler;
import in.nic.cmf.appflow.handlers.ReadKnowledgeHandler;
import in.nic.cmf.appflow.handlers.RuleEngineHandler;
import in.nic.cmf.appflow.handlers.SEMHandler;
import in.nic.cmf.appflow.handlers.SearchEngineHandler;
import in.nic.cmf.appflow.handlers.SiteBuilderHandler;
import in.nic.cmf.appflow.handlers.TransformerHandler;
import in.nic.cmf.appflow.handlers.WorkflowHandler;
import in.nic.cmf.appflow.helper.QueryHelper;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkflowProcessInstance;

public final class AppflowService {

	private LogTracer                              log;
	private KnowledgeBase                          kbase              = null;
	private PropertiesUtil                         propUtil;
	private AppflowHelper                          appflowHelper;
	private static HashMap<String, AppflowService> hashAppflowService = new HashMap<String, AppflowService>();
	private String                                 domain;
	private QueryHelper queryHelper;

	// private final static AppflowService appflowService = new
	// AppflowService();

	private AppflowService(String domain) {
		this.domain = domain;
		this.log = new LogTracer(domain, "appflow");
		propUtil = PropertiesUtil.getInstanceof(domain, "applicationflow");
		appflowHelper = AppflowHelper.getInstanceof(domain);
		 queryHelper = QueryHelper.getInstanceof(domain);
		appflowHelper.setLog(log);
		initializer();
	}

	public static AppflowService getInstanceof(String domain) {
		if (!hashAppflowService.containsKey(domain)) {
			hashAppflowService.put(domain, new AppflowService(domain));
		}
		return hashAppflowService.get(domain);
	}

	// public static AppflowService getInstance() {
	// return appflowService;
	// }

	public void initializer() {
		if (readKnowledgeBase() == null) {
			throw new GenericException(domain, "ERR-AF-0001",
			"Knowledge base failed.");
		}
	}

	/**
	 * Purpose : Based on the requested method, this method will start the
	 * respective process by the first param called 'method'
	 * @param method
	 *            the method
	 * @param domainName
	 *            the domain name
	 * @param entityType
	 *            the entity type
	 * @param query
	 *            the query
	 * @param binaryParam
	 *            the binary param
	 * @param collections
	 *            the collections
	 * @param userContext
	 *            the user context
	 * @return the map
	 * @throws GenericException
	 *             the generic exception
	 */

	public Map<String, Map<String, Object>> processFlow(String method,
			String domainName, String entityType, String id, String subQuery,
			Map<String, Map<String, Object>> requestDetails)
			throws GenericException {
		log.methodEntry("ApplicationFlowService.processFlow entry.");
		appflowHelper.setLog(log);
		if (appflowHelper.isEmpty(domainName)) {
			throw new GenericException(domain, "ERR-AF-0003");
		}

		log.debug("Before::"+requestDetails);
		requestDetails = queryHelper.formQueryParams(requestDetails);    
		log.debug("After:"+requestDetails);
		
		StatefulKnowledgeSession ksession = registerHandlers(kbase);
		WorkflowProcessInstance pi = null;
		Map<String, Object> param = appflowHelper.getParams(method, domainName,
				entityType, id, subQuery, requestDetails);
		log.debug("before starting process:" + method);
		pi = (WorkflowProcessInstance) ksession.startProcess(
				propUtil.getProperty(method), param);
		if (pi == null) {
			log.debug("Processinstance is null so throwing exception.");
			throw new GenericException(domain, "ERR-AF-0004");
		}
		log.methodExit("ApplicationFlowService.processFlow exit.");
		// Map<String, Map<String, Object>> re = (Map<String, Map<String,
		// Object>>) pi.getVariable("response");
		Map<String, Map<String, Object>> re = (Map<String, Map<String, Object>>) pi
		.getVariable(AppflowHelper.HelperConstant.REQDETAILS);
		return re;
	}

	/**
	 * Gets by domain. Purpose: To get the xmls by domain name.
	 * @param domainName
	 *            the domain name
	 * @param subQuery
	 *            the sub query
	 * @param userContext
	 *            the user context
	 * @return the map
	 */
	public Map<String, Map<String, Object>> get(String domainName,
			String entityType, Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.get");
		return processFlow("get", domainName, entityType, null, null,
				requestDetails);
	}

	/**
	 * Purpose : To get the requested file using id, it triggers processflow to
	 * select the respective rf by passing the param "getfromdms".
	 * @param domainName
	 *            the domain name
	 * @param entity
	 *            the entity
	 * @param id
	 *            the id
	 * @param userContext
	 *            the user context
	 * @return the by filename
	 */
	public Map<String, Map<String, Object>> getByFilename(String domainName,
			String entity, String id,
			Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.getByFilename");
		return processFlow("getdms", domainName, entity, id, null,
				requestDetails);
	}

	/**
	 * Purpose : To get the content from SearchEngine, it triggers processflow
	 * to select the respective flow by passing the param "getfromse".
	 * @param domainName
	 *            the domain name
	 * @param subQuery
	 *            the sub query
	 * @param userContext
	 *            the user context
	 * @return the from search api
	 */
	public Map<String, Map<String, Object>> getfromSearchApi(String domainName,
			Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.getfromSearchApi");
		return processFlow("getfromse", domainName, null, null, null,
				requestDetails);
	}

	/**
	 * Purpose : To post the content to SearchEngine, DMS, SiteBuilder & SEM
	 * alone, it triggers processflow to select the respective rf by passing the
	 * param "post".
	 * @param domainName
	 *            the domain name
	 * @param collections
	 *            the collections
	 * @param userContext
	 *            the user context
	 * @return the map
	 */
	public Map<String, Map<String, Object>> post(String domainName,
			String entityType, Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.post");
		if (requestDetails.get("input").containsKey("files")) {
			log.debug("post binary flow selected.");
			return processFlow("posttodms", domainName, entityType, null, null,
					requestDetails);
		} else {
			log.debug("post xml flow selected.");
			return processFlow("post", domainName, entityType, null, null,
					requestDetails);
		}
	}

	/**
	 * Purpose : To post the content to MDE, it triggers processflow to select
	 * the respective rf by passing the param "posttomde".
	 * @param domainName
	 *            the domain name
	 * @param collections
	 *            the collections
	 * @param userContext
	 *            the user context
	 * @return the map
	 */
	public Map<String, Map<String, Object>> postToMDE(String domainName,
			Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.postToMDE");
		return processFlow("posttomde", domainName, null, null, null,
				requestDetails);
	}

	/**
	 * Purpose : To post the content to all component such as MDE, GeoTagger,
	 * SEM etc., it triggers processflow to select the respective rf by passing
	 * the param "postall".
	 * @param domainName
	 *            the domain name
	 * @param collections
	 *            the collections
	 * @param userContext
	 *            the user context
	 * @return the map
	 */
	public Map<String, Map<String, Object>> postall(String domainName,
			Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.postall");
		return processFlow("postall", domainName, null, null, null,
				requestDetails);
	}

	/**
	 * Purpose : To delete the content by id from SearchEngine & DMS, it
	 * triggers processflow to select the respective RF by passing the param
	 * "delete".
	 * @param domainName
	 *            the domain name
	 * @param entity
	 *            the entity
	 * @param id
	 *            the id
	 * @param userContext
	 *            the user context
	 * @return the map
	 */
	public Map<String, Map<String, Object>> delete(String domainName,
			String entityType, String id, String subQuery,
			Map<String, Map<String, Object>> requestDetails) {
		log.debug("In ApplicationFlowService.delete");
		return processFlow("delete", domainName, entityType, id, subQuery,
				requestDetails);
	}

	/**
	 * Purpose : To load all the RF into Knowledge base.
	 * @return the knowledge base
	 */
	private KnowledgeBase readKnowledgeBase() {
		try {
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
			.newKnowledgeBuilder();

			kbuilder.add(ResourceFactory.newClassPathResource("get.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("post.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("postall.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("delete.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("se.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("dms.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("getdms.rf"),
					ResourceType.DRF);
			kbuilder.add(ResourceFactory.newClassPathResource("mde.rf"),
					ResourceType.DRF);
			KnowledgeBuilderErrors errors = kbuilder.getErrors();
			if (errors.size() > 0) {
				for (KnowledgeBuilderError error : errors) {
					System.out.print(error);
				}
				throw new GenericException(domain, "ERR-AF-0001",
						"Could not parse knowledge.");
			}
			kbase = KnowledgeBaseFactory.newKnowledgeBase();
			kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
			// log.debug("In ApplicationFlowService readknowledge");
		} catch (Exception ex) {
			/*
			 * throw new GenericException(domain, "ERR-AF-0001",
			 * "Knowledge builder failure:" + ex.getMessage(), ex);
			 */
		}
		return kbase;
	}

	/**
	 * Purpose : To register all the handlers / services used by the ncms
	 * system.
	 * @param kbase
	 *            * the kbase
	 * @return the stateful knowledge session
	 */
	private StatefulKnowledgeSession registerHandlers(KnowledgeBase kbase) {
		log.methodEntry("ApplicationFlowService.registerHandlers entry.");
		try {
			StatefulKnowledgeSession ksession = kbase
			.newStatefulKnowledgeSession();
			MetadataExtractorHandler metadataExtractorHandler = new MetadataExtractorHandler(
					domain, ksession);
			metadataExtractorHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler(
					"MetadataExtractor", metadataExtractorHandler);

			GeotaggerHandler geotaggerHandler = new GeotaggerHandler(domain,
					ksession);
			geotaggerHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Geotagger",
					geotaggerHandler);

			SearchEngineHandler searchEngineHandler = new SearchEngineHandler(
					domain, ksession);
			searchEngineHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler(
					"SearchEngine", searchEngineHandler);

			DMSHandler dMSHandler = new DMSHandler(domain, ksession);
			dMSHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("DMS",
					dMSHandler);

			EmailHandler emailHandler = new EmailHandler(domain, ksession);
			emailHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Email",
					emailHandler);

			AuditHandler auditHandler = new AuditHandler(domain, ksession);
			auditHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Audit",
					auditHandler);

			SEMHandler semHandler = new SEMHandler(domain, ksession);
			semHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("SEM",
					semHandler);

			SiteBuilderHandler siteBuilderHandler = new SiteBuilderHandler(
					domain, ksession);
			siteBuilderHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler(
					"SiteBuilder", siteBuilderHandler);

			GenerateHandler generateHandler = new GenerateHandler(domain,
					ksession);
			generateHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Generate",
					generateHandler);

			LdapHandler ldapHandler = new LdapHandler(domain, ksession);
			ldapHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Ldap",
					ldapHandler);

			TransformerHandler transformerHandler = new TransformerHandler(
					domain, ksession);
			transformerHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler(
					"Transformer", transformerHandler);

			ACLHandler aclHandler = new ACLHandler(domain, ksession);
			aclHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("ACL",
					aclHandler);

			RuleEngineHandler ruleEngineHandler = new RuleEngineHandler(domain,
					ksession);
			ruleEngineHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("RuleEngine",
					ruleEngineHandler);

			WorkflowHandler workflowHandler = new WorkflowHandler(domain,
					ksession);
			workflowHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Workflow",
					workflowHandler);

			MediaHandler mediaHandler = new MediaHandler(domain, ksession);
			mediaHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Media",
					mediaHandler);

			ReadKnowledgeHandler readHandler = new ReadKnowledgeHandler(domain,
					ksession);
			readHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler(
					"ReadKnowledge", readHandler);

			FinalizeHandler finHandler = new FinalizeHandler(domain, ksession);
			readHandler.setLog(log);
			ksession.getWorkItemManager().registerWorkItemHandler("Finalize",
					finHandler);
			return ksession;
		} catch (Exception ex) {
			throw new GenericException(domain, "ERR-AF-0002",
					"registerHandlers failed:" + ex.getMessage(), ex);
		} finally {
			log.methodExit("ApplicationFlowService.registerHandlers exit.");
		}
	}

}
