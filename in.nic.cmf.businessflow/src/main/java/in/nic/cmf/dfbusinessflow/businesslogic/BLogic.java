package in.nic.cmf.dfbusinessflow.businesslogic;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.CommonUtils;
import in.nic.cmf.util.DirectoryLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.command.Command;
import org.drools.command.CommandFactory;

public class BLogic implements BService {
    public LogTracer                       log;
    private String                         domain;
    private static HashMap<String, BLogic> hashBLogic = new HashMap<String, BLogic>();
    private CommonUtils util = null;
    // private static BLogic bLogic ;
    private BHelper                        bhelper;
    private PropertiesUtil                 propUtil;
    private DirectoryLogic                 dirLogic;

    private BLogic(String domain) {
        this.domain = domain;
        bhelper = BHelper.getInstanceof(domain);
        util = CommonUtils.getInstanceOf(domain);
        // bLogic = BLogic.getInstanceof(domain);
        // this.domain = domain;
        System.out.println("Rules Model Cons");
        propUtil = PropertiesUtil.getInstanceof(domain, "businessflow");
        dirLogic = DirectoryLogic.getInstanceOf(domain);
        bhelper = BHelper.getInstanceof(domain);
        // bhelper.readKnowledgeBase(dirLogic.listFilesFromDirectory(propUtil
        // .get("rulepath")));
        bhelper.readKnowledgeBase(dirLogic.listFilesFromDirectory(
                propUtil.get("rulepath"), "workflow"));
    }

    public static BLogic getInstanceof(String domain) {
        if (!hashBLogic.containsKey(domain)) {
            hashBLogic.put(domain, new BLogic(domain));
        }
        return hashBLogic.get(domain);
    }

    private Map<String, Object> buildRes(String content) {
        Map<String, Object> output = new HashMap<String, Object>();
        output.put("header", new HashMap<String, Object>());
        
 
        // output.put("content", new HashMap<String, Object>());
        output.put("content", content);
        // ((HashMap<String, Object>) output.get("content")).put("Collections",
        // content);
        ((HashMap<String, Object>) output.get("header")).put("Content-Type",
                "application/xml");
        output.put("statusCode", "200");
        return output;
    }

    public Map<String, Map<String, Object>> process(String domainName,
            Map<String, Map<String, Object>> requestDetails)
            throws GenericException {
        List<HashMap<String, Object>> globalMap = new ArrayList<HashMap<String, Object>>();
        log.methodEntry("BusinessFlowImpl.process entry");

        String collection = requestDetails.get("input").get("content")
                .toString();
        log.debug("Collections Testing:::::" + collection);
        HashMap<String, String> usercontext = (HashMap<String, String>) requestDetails
                .get("input").get("userContext");

        HashMap<String, Object> xmlMap = new HashMap<String, Object>();
        xmlMap = FormatXml.getInstanceof(domain).in(collection);
        if (!bhelper.containsValue(xmlMap, "Collections")) {
            throw new GenericException(domain, "ERR-BF-0001");
        }
        HashMap<String, Object> domainMap = (HashMap<String, Object>) xmlMap
                .get("Collections");

        try {
            for (Map.Entry<String, Object> doaminEntity : domainMap.entrySet()) {
                // log.debug("doaminEntity::" + doaminEntity);
                if (doaminEntity.getValue() instanceof List) {
                    List<HashMap<String, Object>> domainEntityList = (List<HashMap<String, Object>>) doaminEntity
                            .getValue();
                    for (HashMap<String, Object> eachEntity : domainEntityList) {

                        if (!BHelper.getInstanceof(domain).containsValue(
                                eachEntity, "WorkflowInfo")) {
                            log.debug("No Workflow information available for the incoming  entity");
                            globalMap.add(eachEntity);
                            // log.debug("Debugging global map:::" + globalMap);
                            continue;
                        } else {
                            BHelper.getInstanceof(domain).fillRequiredDetails(
                                    eachEntity, usercontext, domainName,
                                    globalMap);

                            execute(eachEntity);
                            BHelper.getInstanceof(domain)
                                    .removeunWantedDeatils(eachEntity);
                            globalMap.add(eachEntity);
                        }
                    }
                } else if (doaminEntity.getValue() instanceof HashMap) {
                    HashMap<String, Object> eachEntity = (HashMap<String, Object>) doaminEntity
                            .getValue();

                    if (!BHelper.getInstanceof(domain).containsValue(
                            eachEntity, "WorkflowInfo")) {
                        log.debug("No Workflow information available for the incoming  entity");
                        globalMap.add(eachEntity);

                    } else {
                        BHelper.getInstanceof(domain).fillRequiredDetails(
                                eachEntity, usercontext, domainName, globalMap);
                        log.debug("\nBefore Executing::" + eachEntity);
                        execute(eachEntity);
                        BHelper.getInstanceof(domain).removeunWantedDeatils(
                                eachEntity);
                        log.debug("\nAfterExecuting::" + eachEntity);
                        globalMap.add(eachEntity);

                    }

                }
                // log.debug("domainMap::" + domainMap);
                /*
                 * log.debug("doaminEntity.getKey()::" + doaminEntity.getKey());
                 * log.debug("globalMap::" + globalMap);
                 */

                // log.debug("domainMap::" + domainMap);

                domainMap.put(doaminEntity.getKey(), globalMap);
                globalMap = new ArrayList<HashMap<String, Object>>();

            }

        } catch (GenericException ge) {

            throw ge;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.debug("execute exception:" + ex.getMessage());
            throw new GenericException(domain, "ERR-BF-0002");
        } finally {
            log.methodExit("BusinessFlowImpl.process exit");
        }
        xmlMap.put("Collections", domainMap);
        log.debug("WorkflowOutputMap:"+xmlMap);
        
        String content = util.convertWithCDATA(domain,xmlMap);
       // String content = (String) FormatXml.getInstanceof(domain).out(xmlMap);
        log.debug("Output Collections:::::" + content);
        requestDetails.put("output", buildRes(content));
        return requestDetails;
    }

    public HashMap<String, Object> execute(
            HashMap<String, Object> flattenedEntity) throws GenericException {
        log.methodEntry("BusinessFlowImpl.execute entry");
        try {
            List<Command> commandlist = new ArrayList<Command>();
            commandlist.add(CommandFactory.newInsert(flattenedEntity));

            BHelper.getInstanceof(domain).getKsession()
                    .execute(CommandFactory.newBatchExecution(commandlist));

        } catch (GenericException ge) {
            System.out.println("calling exception");
            throw ge;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.debug("execute throwed exception:" + ex.getMessage());
            // throw new GenericException("ERR-BF-0006", "execute failed.",
            // "Input is workflowableObject", ex);
            throw new GenericException(domain, "ERR-BF-0007");
        } finally {
            log.methodExit("BusinessFlowImpl.execute exit.");
        }
        return flattenedEntity;
    }

    public void setLogTracer(LogTracer logTracer) {
        this.log = logTracer;
        bhelper.setLog(logTracer);
    }
}
