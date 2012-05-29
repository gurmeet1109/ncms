package in.nic.cmf.appflow.handlers;

import java.util.HashMap;
import java.util.Map;

import in.nic.cmf.appflow.AppflowHelper;
import in.nic.cmf.appflow.AppflowHelper.HelperConstant;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.process.WorkItem;
import org.drools.runtime.process.WorkItemHandler;
import org.drools.runtime.process.WorkItemManager;
import org.drools.runtime.process.WorkflowProcessInstance;

public class SiteGenerateHandler implements WorkItemHandler {

	private StatefulKnowledgeSession ksession;
	private AppflowHelper appflowHelper;
	public LogTracer log;
	private String domain;

	/**
	 * Sets the log.
	 * 
	 * @param log
	 *            the new log
	 */
	public void setLog(LogTracer log) {
		this.log = log;
	}

	public SiteGenerateHandler(String domain, StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
		this.domain = domain;
		appflowHelper = AppflowHelper.getInstanceof(domain);
	}

	
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.completeWorkItem(workItem.getId(), null);
		
	}

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		try {
			appflowHelper.setLog(log);
			log.methodEntry("SiteGenerate entry");
			WorkflowProcessInstance processInstance = appflowHelper
			.initializer(ksession);
			log.debug("after ksession initializer");
			ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain, "sitegeneration");
			Map<String, Map<String, Object>> responseMap = (Map<String, Map<String, Object>>) processInstance.getVariable(HelperConstant.REQDETAILS);
			switch (AppflowHelper.SELECTFLOW.toFlow((String) processInstance
					.getVariable(HelperConstant.METHOD))) {
					case post:
						log.debug("In sitegeneration-Handler  POST case: " + responseMap);
						responseMap = serviceclient.add((String) processInstance
								.getVariable(HelperConstant.DOMAIN),
								(String) processInstance
								.getVariable(HelperConstant.ENTITY),
								responseMap);
						break;
					
			}
			
			 String siteOutput = (String) responseMap.get("output").get("content");
			 responseMap.get("input").put("content", siteOutput);
			
			log.debug("After switch case:" + responseMap);
			processInstance = appflowHelper
			.finalizer((String) processInstance
					.getVariable(HelperConstant.METHOD),
					"SiteGeneration: ", processInstance, responseMap);
			
			log.debug("After GENERATE POST from SC:" + responseMap);
			manager.completeWorkItem(workItem.getId(), null);
			log.methodExit("SiteGenerationHandler exit");
		} catch (GenericException ge) {
			log.debug("In GE:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.debug("In EX:" + ex.getMessage());
			throw new GenericException(domain, "ERR-AF-0000",
					"Exception at SiteGenerate handler:", ex);
		}
	}

}
