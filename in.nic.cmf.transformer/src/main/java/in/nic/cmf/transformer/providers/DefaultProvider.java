package in.nic.cmf.transformer.providers;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.transformer.helper.SeoUrlHelper;
import in.nic.cmf.transformer.helper.TransformerHelper;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The Class DefaultProvider.
 */
public class DefaultProvider implements Provider {

    /** The log. */
    private LogTracer                               log;

    /** The prop util. */
    private PropertiesUtil                          propUtil;

    /** The trans helper. */
    private TransformerHelper                       transHelper;

    /** The cu. */
    private ConvertorUtils                          cu;

    /** The seo helper. */
    private SeoUrlHelper                            seoHelper;

    private DateUtils                               dUtils;

    /** The Constant defaultProvider. */
    // public static DefaultProvider defaultProvider ;

    private String                                  domain;

    private static HashMap<String, DefaultProvider> hashDefaultProvider = new HashMap<String, DefaultProvider>();

    /**
     * Instantiates a new default provider.
     */
    private DefaultProvider(String domain) {
        this.domain = domain;
        setLogger(domain);
        propUtil = PropertiesUtil.getInstanceof(domain, "transformer");
        cu = ConvertorUtils.getInstanceof(domain);
        seoHelper = SeoUrlHelper.getInstanceOf(domain);
        transHelper = TransformerHelper.getInstanceOf(domain);
        dUtils = DateUtils.getInstanceOf(domain);
        // defaultProvider = DefaultProvider.getInstance(domain);
    }

    /**
     * Gets the single instance of DefaultProvider.
     * @return single instance of DefaultProvider
     */
    public static DefaultProvider getInstanceOf(String domain) {
        if (!hashDefaultProvider.containsKey(domain)) {
            hashDefaultProvider.put(domain, new DefaultProvider(domain));
        }
        return hashDefaultProvider.get(domain);
    }

    // public static DefaultProvider getInstance(String domain){
    //
    // return defaultProvider;
    // }

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
        System.out.println("HERE>>>>>>>>>>>>>>>>>>111S" + parameters);
        try {
            Map<String, Object> formattedContent = FormatXml.getInstanceof(
                    domain).in((String) parameters.get("input").get("content"));
            System.out.println("formattedcontent" + formattedContent);
            Map<String, Object> responseMap = process(domain, formattedContent);
            return transHelper.getResponseMap(domain, responseMap, parameters);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-TRA-0002", ex);
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
        log.methodEntry("process entry");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        try {
            Map<String, Object> entities = (HashMap<String, Object>) content
                    .get("Collections");
            List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
            for (Entry<String, Object> eachMap : entities.entrySet()) {
                Object value = eachMap.getValue();
                // log.debug("value:"+value.toString() );

                if (!eachMap.getKey().equals("files")) {
                    if (cu.isHashMap(value)) {
                        // log.debug("hashmap");
                        Map<String, Object> filledMap = fillDefault(domain,
                                (Map<String, Object>) value);
                        // log.debug("filledMap:" + filledMap);

                        return transHelper.wrapCollections(eachMap.getKey(),
                                filledMap);
                    } else if (cu.isArrayList(value)) {
                        log.debug("Arraylist");
                        List<Map<String, Object>> filledMaps = fillDefault(
                                domain, (List<Map<String, Object>>) value);
                        Map<String, Object> entityMap = new HashMap<String, Object>();
                        entityMap.put(eachMap.getKey(), filledMaps);
                        /*
                         * Map<String, Object> entityMap = new HashMap<String,
                         * Object>(); entityMap.put(eachMap.getKey(),
                         * filledMaps);
                         * responseMap.put("Collections", entityMap);
                         */
                        listMap.add(entityMap);
                    }
                }
            }
            responseMap.put("Collections", listMap);

        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-TRA-0003", ex);
        }
        log.methodExit("process exit");
        return responseMap;
    }

    /**
     * Fill default.
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @return the map
     */
    public Map<String, Object> fillDefault(String domain,
            Map<String, Object> entity) {
        log.methodEntry("fillDefault entry");
        try {
            String site = (String) entity.get("Site");
            String version = (String) entity.get("Version");
            String id = (String) entity.get("Id");
            String seoUrl = (String) entity.get("SeoUrl");
            String createdDate = (String) entity.get("SeoUrl");
            String lastModifiedDate = (String) entity.get("SeoUrl");
            if (site == null || site.equals("")) {
                entity.put("Site", domain);
            }
            if (version == null || version.equals("")) {
                entity.put("Version", propUtil.getProperty("version"));
            }
            if (id == null || id.equals("")) {
                entity.put("Id", Uniqueid.getId());
            }
            if (entity.containsKey("Status")) {
                if (seoUrl == null || seoUrl.equals("")) {
                    String seorul = seoHelper.getSEOURL(domain, entity);
                    entity.put("SeoUrl", seorul);
                }
            }
            if (createdDate == null || createdDate.equals("")) {
                entity.put("CreatedDate", dUtils.getSolrFormattedDateByForm(""));
            }
            if (lastModifiedDate == null || lastModifiedDate.equals("")) {
                entity.put("LastModifiedDate",
                        dUtils.getSolrFormattedDateByForm(""));
            }
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            log.debug("In EX : " + ex.getMessage());
            throw new GenericException(domain, "ERR-TRA-0004", ex);
        }
        log.methodExit("fillDefault exit");
        return entity;
    }

    /**
     * Fill default.
     * @param domain
     *            the domain
     * @param entities
     *            the entities
     * @return the list
     */
    public List<Map<String, Object>> fillDefault(String domain,
            List<Map<String, Object>> entities) {
        log.methodEntry("fillDefault for list entry");
        List<Map<String, Object>> content = new ArrayList<Map<String, Object>>();
        try {
            for (Map<String, Object> entity : entities) {
                Map<String, Object> eachMap = fillDefault(domain, entity);
                content.add(eachMap);
            }
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-TRA-0005", ex);
        }
        log.methodEntry("fillDefault for list entry");
        return content;
    }
}
