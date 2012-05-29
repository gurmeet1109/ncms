package in.nic.cmf.transformer.providers;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.transformer.helper.ContentCreatorHelper;
import in.nic.cmf.transformer.helper.SeoUrlHelper;
import in.nic.cmf.transformer.helper.TransformerHelper;
import in.nic.cmf.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.command.Command;
import org.drools.command.CommandFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

/**
 * The Class DroolsProvider.
 */
public class DroolsProvider implements Provider {

    /** The log. */
    private LogTracer                              log;

    /** The prop util. */
    private PropertiesUtil                         propUtil;

    /** The trans helper. */
    private TransformerHelper                      transHelper;

    /** The cu. */
    private ConvertorUtils                         cu;
    /** The Constant droolsProvider. */
    // public static DroolsProvider droolsProvider ;

    /** The kbase. */
    private KnowledgeBase                          kbase              = null;
    /** The ksession. */
    private StatelessKnowledgeSession              ksession           = null;
    /** The seo helper. */
    public SeoUrlHelper                            seoHelper;

    private String                                 domain;

    private static HashMap<String, DroolsProvider> hashDroolsProvider = new HashMap<String, DroolsProvider>();

    ContentCreatorHelper                           cc                 = null;

    /**
     * Instantiates a new drools provider.
     */
    private DroolsProvider(String domain) {
        this.domain = domain;
        setLogger(domain);
        propUtil = PropertiesUtil.getInstanceof(domain, "transformer");
        cu = ConvertorUtils.getInstanceof(domain);
        seoHelper = SeoUrlHelper.getInstanceOf(domain);
        transHelper = TransformerHelper.getInstanceOf(domain);
        cc = ContentCreatorHelper.getInstance(domain);
        // droolsProvider =DroolsProvider.getInstance(domain);
        readKnowledgeBase();
    }

    /**
     * Gets the single instance of DroolsProvider.
     * @return single instance of DroolsProvider
     */
    // public static DroolsProvider getInstance(String domain){
    // return droolsProvider;
    // }
    public static DroolsProvider getInstanceOf(String domain) {
        if (!hashDroolsProvider.containsKey(domain)) {
            hashDroolsProvider.put(domain, new DroolsProvider(domain));
        }
        return hashDroolsProvider.get(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "transformer");
    }

    /**
     * Sets the defaults.
     * @param domain
     *            the domain
     * @param parameters
     *            the parameters
     * @return the map
     */
    public Map<String, Map<String, Object>> setDefaults(String domain,
            Map<String, Map<String, Object>> parameters) {
        log.methodEntry("setDefaults entry.");
        try {
            Map<String, Object> formattedContent = FormatXml.getInstanceof(
                    domain).in((String) parameters.get("input").get("content"));
            log.debug("FormattedContent:" + formattedContent + ";domain:"
                    + domain);
            Map<String, Object> responseMap = process(domain, formattedContent);

            log.methodExit("setDefaults going to exit. Response : "
                    + responseMap);
            return transHelper.getResponseMap(domain,responseMap, parameters);
        } catch (Exception ex) {
            System.out.println("domain in setdefaults:" + domain);
            throw new GenericException(domain, "ERR-TRA-0006", ex);
        }
    }

    /**
     * Process.
     * @param domain
     *            the domain
     * @param content
     *            the content
     * @return the map
     */
    public Map<String, Object> process(String domain,
            Map<String, Object> content) {
        System.out.println("HERE>>>>>>>>>>>>>>>>>>");
        log.methodEntry("process entry");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        try {
            Map<String, Object> entities = (HashMap<String, Object>) content
                    .get("Collections");
            System.out.println("Entity size" + entities.size());

            for (Entry<String, Object> eachMap : entities.entrySet()) {
                Object value = eachMap.getValue();
                System.out.println("Each map:::::" + eachMap);

                if (!eachMap.getKey().equals("files")) {
                    if (cu.isHashMap(value)) {
                        log.debug("object is hashmap");
                        Map<String, Object> filledMap = executeContent(domain,
                                (Map<String, Object>) value);
                        log.debug("filledMap:" + filledMap);
                        Map<String, Object> wrappedEntity = new HashMap<String, Object>();
                        wrappedEntity.put(eachMap.getKey(), filledMap);
                        listMap.add(wrappedEntity);
                        /*
                         * return transHelper.wrapCollections(eachMap.getKey(),
                         * filledMap);
                         */
                    } else if (cu.isArrayList(value)) {
                        log.debug("object is arraylist");
                        List<Map<String, Object>> filledMaps = executeContent(
                                domain, (List<Map<String, Object>>) value);
                        Map<String, Object> entityMap = new HashMap<String, Object>();
                        entityMap.put(eachMap.getKey(), filledMaps);
                        listMap.add(entityMap);
                        // responseMap.put("Collections", entityMap);
                    }
                }
            }

            responseMap.put("Collections", listMap);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-TRA-0007", ex);
        }
        log.methodExit("process exit");
        return responseMap;
    }

    /**
     * Execute content.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @return the map
     */
    public Map<String, Object> executeContent(String domain,
            Map<String, Object> entity) {
        return execute(domain, entity);
    }

    /**
     * Execute content.
     * @param domain
     *            the domain
     * @param entities
     *            the entities
     * @return the list
     */
    public List<Map<String, Object>> executeContent(String domain,
            List<Map<String, Object>> entities) {
        log.methodEntry("executeContent");
        List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
        try {
            for (Map<String, Object> entity : entities) {
                log.debug("going to call execute:" + entity);
                Map<String, Object> entityMap = execute(domain, entity);
                content.add(entityMap);
            }
            log.methodExit("executeContent exit");
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-TRA-0008", ex);
        }
        return content;
    }

    /**
     * Execute.
     * @param domain
     *            the domain
     * @param eachEntity
     *            the each entity
     * @return the map
     */
    public Map<String, Object> execute(String domain,
            Map<String, Object> eachEntity) {
        log.methodEntry("execute entry");
        try {
            List<Command> commandlist = new ArrayList<Command>();
            Map<String, Object> inputMap = new HashMap<String, Object>();
            inputMap.put("domain", domain);
            inputMap.put("propUtil", propUtil);
            inputMap.put("seoUrl", seoHelper);
            inputMap.put("date", DateUtils.getInstanceOf(domain));
            log.debug("INPUTMAP:" + inputMap);
            commandlist.add(CommandFactory.newSetGlobal("inputMap", inputMap));
            commandlist.add(CommandFactory.newInsert(eachEntity));
            ksession.execute(CommandFactory.newBatchExecution(commandlist));
            log.debug("after filling default value : " + eachEntity);
            eachEntity = cc.addWorkflowContentCreators(eachEntity);
            log.debug("after cc : " + eachEntity);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            log.debug("execute throwed exception:" + ex.getMessage());
            throw new GenericException(domain, "ERR-TRA-0009", ex);
        }
        log.methodExit("execute exit:" + eachEntity);
        return eachEntity;
    }

    /**
     * Read knowledge base.
     * @return true, if successful
     */
    public boolean readKnowledgeBase() {
        boolean result = false;
        try {
            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
                    .newKnowledgeBuilder();
            try {
                String rule = propUtil.getProperty("rulepath") + domain
                        + "/resources/" + propUtil.getProperty("rulefilename");
                log.debug("rule is:" + rule);
                kbuilder.add(ResourceFactory.newFileResource(rule),
                        ResourceType.DRL);
            } catch (Exception ex) {
                throw new GenericException(domain, "ERR-TRA-0010", ex);
            }
            try {
                KnowledgeBuilderErrors errors = kbuilder.getErrors();
                if (errors.size() > 0) {
                    for (KnowledgeBuilderError error : errors) {
                        log.debug("KBError:" + error.getMessage());
                    }
                    log.debug("Builder Errors:"
                            + kbuilder.getErrors().toString());
                }
            } catch (Exception ex) {
                log.debug("Exception occured while getting Builder Errors:"
                        + kbuilder.getErrors().toString());
            }
            kbase = KnowledgeBaseFactory.newKnowledgeBase();
            kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
            ksession = kbase.newStatelessKnowledgeSession();
            result = true;
        } catch (Exception ex) {
            log.debug("execute exception:" + ex.getMessage());
            throw new GenericException(domain, "ERR-TRA-0011", ex);
        }
        return result;
    }
}
