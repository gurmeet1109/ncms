package in.nic.cmf.cacheburster;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import in.nic.cmf.cache.EHCache;
import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.DateUtils;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.definition.rule.Rule;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public class CacheBursterService {

	private LogTracer                              log;
	private PropertiesUtil                         propUtil;
	private KnowledgeBase                          kbase              = null;
	private StatelessKnowledgeSession              ksession           = null;
	private CacheBursterHelper 					   helper 			  = null;
	private ConvertorUtils                         cu;
	private static HashMap<String, CacheBursterService> cacheService = new HashMap<String, CacheBursterService>();
	
	private String domain;
	
	public void setDomain(String domain) {
		this.domain = domain;
	}

	private CacheBursterService(String domain) {
		setDomain(domain);
		log = new LogTracer(domain, "cacheburster");
		System.out.println("inside CacheBursterService");
		propUtil = PropertiesUtil.getInstanceof(domain, "cacheburster");
		helper = CacheBursterHelper.getInstance(domain);
		cu = ConvertorUtils.getInstanceof(domain);
		readKnowledgeBase(domain);
	}
	public static CacheBursterService getInstance(String domain) {
		if (!cacheService.containsKey(domain)) {
			System.out.println("not contains in object pool.");
			cacheService.put(domain, new CacheBursterService(domain));
		}
		System.out.println("contains in object pool.");
		return cacheService.get(domain);
		//return new CacheBursterService(domain);
	}

	public Map<String, Map<String, Object>> find(String domain, String entity,
			Map<String, Map<String, Object>> parameters){
		log.methodEntry("find entry.");
		log.debug("Incoming params:" + domain + entity + parameters);
		Map<String,Object> input = parameters.get("input");
		Map<String,String> queryParam = new HashMap<String,String>();
		queryParam.put("site", domain);
		queryParam.put("entitytype", entity);
		if(input.containsKey("queryParams")){
			queryParam = (Map<String, String>) input.get("queryParams");
		}
		queryParam = helper.applyExcludeParams(domain, queryParam);
		queryParam = helper.sortParam(queryParam);
		String rulename = helper.getRuleName(queryParam);
		log.debug("Searching rule name ...." + rulename);
		Rule r = kbase.getRule("in.nic.cmf.cacheburster", rulename);
		if(r == null){
			StringBuffer url = (StringBuffer) input.get("url");	
			if(input.containsKey("queryString")){
				String queryString = "?"+ (String) input.get("queryString");	
				url.append(queryString);
			}
			log.debug("url:"+url);
			persistRule(domain, queryParam, url.toString());
		}else{
			log.debug("Rule name exist :"+r.getName());
			return parameters;
		}
		log.methodExit("find exit.");
		return parameters;
	}

	private boolean persistRule(String domain, Map<String, String> queryParam,
			String url) {
		try{
			String rule = helper.formRule(queryParam,url);
			String perfectRule = helper.generateRule(domain,rule);
			log.debug("PerfectRule:"+perfectRule);
			if(readKnowledgeBase(domain, perfectRule,"add")){
				log.debug("ReadKnowledge is successful.");
				helper.writeToFile(domain,rule);
			}
			return true;
		}catch(Exception ex){
			log.error("IN EX:"+ex.getMessage());
		}
		return false;
	}

	public Map<String,Map<String,Object>> add(String domain, String entity,
			Map<String, Map<String, Object>> parameters){
		Map<String,Object> input = parameters.get("input");
		String content = (String) input.get("content");
		FormatXml formatXml = FormatXml.getInstanceof(domain);
		HashMap<String,Object>  collections = formatXml.in(content);
		log.debug("content in hashmap:"+collections);
		List<String> urls= process(domain,collections);
		log.debug("URLS:"+urls);
		
		EHCache ehCache = EHCache.getInstanceof(domain);
		ehCache.delete((ArrayList<String>)urls);
		
		log.debug("bursted the ehcache and exited.");
		return parameters;
	}

	public List<String> process(String domain,
			Map<String, Object> content) {
		log.methodEntry("process entry");
		List<String> urlList = new ArrayList<String>();
		try {
			Map<String, Object> entities = (HashMap<String, Object>) content.get("Collections");
			System.out.println("Entity size" + entities.size());
			for (Entry<String, Object> eachMap : entities.entrySet()) {
				Object value = eachMap.getValue();
				System.out.println("Each map:::::" + eachMap);
				if (!eachMap.getKey().equals("files")) {
					if (cu.isHashMap(value)) {
						log.debug("object is hashmap");
						urlList.addAll(executeContent(domain,
								(Map<String, Object>) value));
					} else if (cu.isArrayList(value)) {
						log.debug("object is arraylist");
						urlList.addAll(executeContent(
								domain, (List<Map<String, Object>>) value));
					}
				}
			}
			log.debug("ListOfUrl:"+urlList);
		} catch (GenericException ge) {
			throw ge;
		} catch (Exception ex) {
			throw new GenericException(domain, "ERR-CB-0007", ex);
		}
		log.methodExit("process exit");
		return urlList;
	}

	public List<String> executeContent(String domain,
			Map<String, Object> entity) {
		return execute(domain, entity);
	}

	public List<String> executeContent(String domain,
			List<Map<String, Object>> entities) {
		log.methodEntry("executeContent");

		List<String> urlList = new ArrayList<String>();
		try {
			for (Map<String, Object> entity : entities) {
				log.debug("going to call execute:" + entity);
				urlList.addAll(execute(domain, entity));
			}
			log.methodExit("executeContent exit");
		} catch (GenericException ge) {
			throw ge;
		} catch (Exception ex) {
			throw new GenericException(domain, "ERR-TRA-0008", ex);
		}
		return urlList;
	}

	public List<String> execute(String domain,
			Map<String, Object> eachEntity) {
		log.methodEntry("execute entry");
		Map<String,Object> keyEntities = new HashMap<String,Object>();
		List<String> urls = new ArrayList<String>();
		for(Entry e : eachEntity.entrySet()){
			if(e.getKey() != null && e.getValue() != null){
				String key = e.getKey().toString().toLowerCase();
				String value = e.getValue().toString().toLowerCase();
				log.debug("key:"+key);
				keyEntities.put(key,value);
			}
		}
		try {
			List<Command> commandlist = new ArrayList<Command>();
			log.debug("EachEntity:" + keyEntities);
			commandlist.add(CommandFactory.newSetGlobal("urls", urls));
			commandlist.add(CommandFactory.newInsert(keyEntities));
			log.debug("urls before execute : " + urls);
			ksession.execute(CommandFactory.newBatchExecution(commandlist));
			log.debug("urls after execute : " + urls);
		} catch (GenericException ge) {
			throw ge;
		} catch (Exception ex) {
			log.debug("execute throwed exception:" + ex.getMessage());
			throw new GenericException(domain, "ERR-TRA-0009", ex);
		}
		log.methodExit("execute exit:" + urls);
		return urls;
	}

	public boolean readKnowledgeBase(String domain) {
		boolean result = false;
		log.methodEntry("readKnowledgeBase entry");
		try {
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
			.newKnowledgeBuilder();
			try {
				log.debug("before forming rulePath.domain : "+domain);
				if(domain != null && domain != "" && !domain.equals("null") && !domain.equals("")){
					String rule = propUtil.getProperty("rulepath") + domain + "/resources/" + propUtil.getProperty("rulefilename");
					log.debug("rule is:" + rule);
					kbuilder.add(ResourceFactory.newFileResource(rule), ResourceType.DRL);
				}
			} catch (Exception ex) {
				throw new GenericException(domain, "ERR-CB-0000", ex);
			}
			validateBuilder(kbuilder);
			kbase = KnowledgeBaseFactory.newKnowledgeBase();
			kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
			ksession = kbase.newStatelessKnowledgeSession();
			result = true;
		} catch (Exception ex) {
			log.debug("execute exception:" + ex.getMessage());
			throw new GenericException(domain, "ERR-CB-0000", ex);
		}
		log.methodExit("readKnowledgeBase exit");
		return result;
	}

	public boolean readKnowledgeBase(String domain, String rule, String action) {
		boolean result = false;
		log.methodEntry("readKnowledgeBase entry");

		try{
			KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
			.newKnowledgeBuilder();

			if (action.equals("add")) {
				log.debug("inside addrule condition.");
				//	for (String rule : rules) {
				log.debug("Incoming Rule :" + rule);
				try {
					kbuilder.add(ResourceFactory
							.newReaderResource(new StringReader(rule)),
							ResourceType.DRL);
				} catch (Exception ex) {
					log.debug("In Ex : :addrule condition."
							+ ex.getMessage());
				}
				//}
				if (validateBuilder(kbuilder)) {
					log.error("Validating the builder failed - addrule condition.");
				}
				kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
				log.debug("kbase updated - addrule");
			}
			if (action.equals("remove")) {
				log.debug("inside remove condition.");
				//for (String rule : rules) {
				log.debug("RuleName to remove: " + rule);
				try {
					kbase.removeRule("in.nic.cmf.services.ruleengine", rule);
				} catch (Exception ex) {
					log.debug("In Ex - remove : " + ex.getMessage());
				}
				//}
				log.debug("remove condition exit.");
			}
			ksession = kbase.newStatelessKnowledgeSession();
			log.methodExit("readKnowledgeBase exit");
			result = true;
		}catch(Exception ex){
			log.error("In Exception:"+ex.getMessage());
		}
		return result;
	}


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




}
