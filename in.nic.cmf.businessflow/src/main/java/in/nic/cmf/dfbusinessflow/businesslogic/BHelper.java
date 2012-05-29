package in.nic.cmf.dfbusinessflow.businesslogic;

import in.nic.cmf.cache.RedisCache;
import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;
import org.json.JSONException;

public class BHelper {
    // private static BHelper bhelper ;
    private StatelessKnowledgeSession ksession = null;
    private KnowledgeBase kbase = null;

    private PropertiesUtil propUtil;
    private String domain;
    private static HashMap<String, BHelper> hashBHelper = new HashMap<String, BHelper>();
    private RedisCache redisCache;

    private BHelper(String domain) {
	this.domain = domain;
	System.out.println("BS flow Domain checking222222222222222222222222222"
		+ domain + "\n next line");
	propUtil = PropertiesUtil.getInstanceof(domain, "businessflow");
	redisCache = RedisCache.getInstanceof(domain);

    }

    public static BHelper getInstanceof(String domain) {
	if (!hashBHelper.containsKey(domain)) {
	    System.out
		    .println("BS flow Domain checking111111111111111111111111"
			    + domain + "\n next line");
	    hashBHelper.put(domain, new BHelper(domain));
	}
	return hashBHelper.get(domain);
    }

    public LogTracer log;

    public void setLog(LogTracer log) {
	this.log = log;
    }

    public StatelessKnowledgeSession getKsession() {

	return ksession;
    }

    public boolean generateDRL(List<Map> masterMap, String domain, String id)
	    throws GenericException {
	boolean result = false;
	try {
	    VelocityEngine ve = new VelocityEngine();

	    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
	    ve.setProperty("file.resource.loader.class",
		    " org.apache.velocity.runtime.resource.loader.FileResourceLoader");

	    String dirpath = propUtil.get("dirlocation") + domain
		    + "/resources/";

	    ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, dirpath);

	    ve.setProperty("runtime.log.logsystem.class",
		    "org.apache.velocity.runtime.log.NullLogSystem");
	    ve.init();

	    Template t = ve.getTemplate(propUtil.get("ruletemplatename"));

	    VelocityContext context = new VelocityContext();
	    log.debug("before going to template:" + masterMap);
	    context.put("map", masterMap);
	    StringWriter writer = new StringWriter();
	    t.merge(context, writer);

	    File drlloc = new File(propUtil.get("dirlocation") + domain
		    + propUtil.get("rulepath"));
	    if (!drlloc.exists()) {
		drlloc.mkdirs();
	    }
	    File drlfile = new File(drlloc.getPath() + "/" + id + ".drl");
	    if (drlfile.exists()) {
		log.debug("Deleting the file::" + drlfile.getPath());
		drlfile.delete();
	    }

	    // dirLogic.createDirectoryIfNotExists(propUtil.get("rulepath") +
	    // "/"
	    // + domain + "/workflow/");
	    // log.debug("is File exists ::" + drlloc.exists());

	    // if (!drlloc.exists()) {
	    // boolean isfilecreated = drlloc.mkdirs();
	    // log.debug("Is File Created::" + isfilecreated);

	    // }
	    // FileWriter fw = new FileWriter(drlloc.getPath() + "/" + id +
	    // ".drl");

	    FileWriter fw = new FileWriter(drlloc.getPath() + "/" + id + ".drl");
	    fw.write(writer.toString());
	    fw.close();
	    drlloc = null;
	    drlfile = null;
	    result = true;
	} catch (Exception ex) {

	    log.error("Error while generating DRL:" + ex.getMessage());
	    result = false;
	    // throw new GenericException("ERR-BF-0004", "generateDRL failed.",
	    // ex);
	} finally {
	    log.methodExit("generateDRL exit");
	}
	return result;
    }

    public void readKnowledgeBase(List<String> lstRules)
	    throws GenericException {
	System.out.println("BusinessFlowImpl.readKnowledgeBase entry");

	try {
	    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
		    .newKnowledgeBuilder();

	    try {

		if (lstRules.size() <= 0) {
		    System.out.println("Rules List::" + lstRules.size());

		    kbuilder.add(ResourceFactory.newFileResource(propUtil
			    .get("dirlocation")
			    + domain
			    + "/resources/"
			    + "dummy.drl"), ResourceType.DRL);

		} else {
		    for (String rules : lstRules) {
			System.out.println("Incoming rule path is: " + rules);

			kbuilder.add(ResourceFactory.newFileResource(rules),
				ResourceType.DRL);
		    }
		}
	    } catch (Exception ex) {
		kbuilder.add(ResourceFactory.newFileResource(propUtil
			.get("dirlocation")
			+ domain
			+ "/resources/"
			+ "dummy.drl"), ResourceType.DRL);

		System.out
			.println("Exception occured while adding drl to kbuilder.Dummy drl loaded.");
	    }

            try {
                KnowledgeBuilderErrors errors = kbuilder.getErrors();
                if (errors.size() > 0) {
                    for (KnowledgeBuilderError error : errors) {
                        System.err.println(error);
                        System.out.println("KBError:" + error.getMessage());
                    }
                    System.out.println("KBuilder Errors:"
                            + kbuilder.getErrors().toString());
                }
            } catch (Exception ex) {
                System.out
                        .println("Exception occured while getting Builder Errors:"
                                + kbuilder.getErrors().toString());
                throw new GenericException(domain, "ERR-BF-0006",
                        "readKnowledgeBase failed.", ex);
            }
            kbase = KnowledgeBaseFactory.newKnowledgeBase();

	    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
	    ksession = kbase.newStatelessKnowledgeSession();

	    System.out.println("ksession assigned successfully");

        } catch (GenericException ex) {
            throw ex;
        } catch (Exception ex) {
            System.out.println("In EX: " + ex.getMessage());
            throw new GenericException(domain, "ERR-BF-0006",
                    "readKnowledgeBase failed.", ex);
        } finally {
            System.out.println("BusinessFlowImpl.readKnowledgeBase exit");
        }

    }

    public void fillRequiredDetails(HashMap<String, Object> eachEntity,
	    HashMap<String, String> usercontext, String domainName,
	    List<HashMap<String, Object>> globalMap) {

	System.out.println("Testing::"
		+ containsValue(
			(Map<String, Object>) eachEntity.get("WorkflowInfo"),
			"CurrentAction"));
	if (((Map<String, Object>) eachEntity.get("WorkflowInfo"))
		.containsKey("CurrentAction")) {
	    String currentAction = (String) ((Map<String, Object>) eachEntity
		    .get("WorkflowInfo")).get("CurrentAction");
	    System.out.println("Current Action:::" + currentAction);
	    if (currentAction == null || currentAction.equals("")) {
		((HashMap<String, Object>) eachEntity.get("WorkflowInfo")).put(
			"CurrentAction", "");
		((HashMap<String, Object>) eachEntity.get("WorkflowInfo")).put(
			"Stage", "");
	    }

        }
        HashMap<String, Object> workfloInfo = (HashMap<String, Object>) eachEntity
                .get("WorkflowInfo");
        HashMap<String, String> f = new HashMap<String, String>();
        try {
            FormatFlatten.getInstanceOf(domain, false).flatten(workfloInfo, f, new HashMap<String, Object>());
        } catch (GenericException e) {
            throw e;
        } catch (JSONException e) {
            throw new GenericException(domain, "ERR-BF-0006");
        }
        log.debug("Flattened Map::" + f);

	workfloInfo.put("AllowedActions", workfloInfo.get("AllowedActions"));
	eachEntity.put("AllowedActions", f.get("AllowedActions"));
	eachEntity.put("CurrentUser", workfloInfo.get("CurrentUser"));
	eachEntity.put("WorkflowName", workfloInfo.get("WorkflowName"));
	eachEntity.put("WorkflowId", workfloInfo.get("WorkflowId"));
	eachEntity.put("Stage", workfloInfo.get("Stage"));
	eachEntity.put("CurrentAction", workfloInfo.get("CurrentAction"));
	eachEntity.put("authusername", usercontext.get("authusername"));
	eachEntity.put("aclrole", usercontext.get("aclrole"));
	eachEntity.put("isSuperAdmin",
		isSuperAdmin(domainName, usercontext.get("aclrole")));
        log.debug("Checking Is there required Details===" + eachEntity);
	// return eachEntity;
    }

    public void removeunWantedDeatils(HashMap<String, Object> eachEntity) {
        HashMap<String, Object> workfloInfo = (HashMap<String, Object>) eachEntity
                .get("WorkflowInfo");
        workfloInfo.put("CurrentUser", eachEntity.get("CurrentUser"));
        workfloInfo.put("Stage", eachEntity.get("Stage"));
        workfloInfo.put("CurrentAction", eachEntity.get("CurrentAction"));
        if(eachEntity.get("AllowedActions") instanceof String){
        	
        	workfloInfo.put("AllowedActions", FormatJson.getInstanceof(domain).in(eachEntity.get("AllowedActions")));
        }else{
        	workfloInfo.put("AllowedActions", eachEntity.get("AllowedActions"));
        }        
        eachEntity.put("WorkflowInfo", workfloInfo);
        eachEntity.remove("authusername");
        eachEntity.remove("aclrole");
        eachEntity.remove("AllowedActions");
        eachEntity.remove("CurrentAction");
        eachEntity.remove("CurrentUser");
        eachEntity.remove("WorkflowName");
        eachEntity.remove("WorkflowId");
        eachEntity.remove("Stage");
        eachEntity.remove("isSuperAdmin");

    }

    public boolean isSuperAdmin(String domainName, String loggedInRole) {
	// boolean result = false;
	try {
	    log.debug("Is SuperAdmin check-domainName::" + domainName
		    + ",Role::" + loggedInRole);
	    String key = domainName.toLowerCase() + "_"
		    + loggedInRole.toLowerCase();

	    boolean isSuperAdmin = Boolean.parseBoolean(redisCache.get(key));
	    log.debug("Key:::" + key + ", Value:::" + isSuperAdmin);
	    return isSuperAdmin;

	} catch (Exception ex) {
	    log.error("failed to get value for isSuperAdmin key Ex:"
		    + ex.getMessage());

	}
	return false;
    }

    public boolean containsValue(Map<String, Object> object, String key) {

	if (object.containsKey(key) && object.get(key) != null) {

	    return true;
	}
	return false;
    }

    public Map<String, Map<String, Object>> buildResponse(
	    Map<String, Map<String, Object>> requestDetails) {
	HashMap<String, Object> output = new HashMap<String, Object>();
	output.put("header", new HashMap<String, Object>());
	output.put("content", "success");

	((HashMap<String, Object>) output.get("header")).put("Content-Type",
		"application/xml");
	output.put("statusCode", "200");
	requestDetails.put("output", output);
	return requestDetails;
    }
}
