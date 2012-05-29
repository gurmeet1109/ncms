package in.nic.cmf.searchengine;

import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatSolrXml;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.properties.PropertyManagement;
import in.nic.cmf.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SearchEngineService {

    private SolrConnection                              solrconnection;
    private QueryStringParser                           querystringparser;
    private SolrQueryFramer                             solrqueryframer;
    private SolrQueryValidation                         solrqueryvalidaion;
    private HashMap<String, Object>                     defDataTypeMapping      = new HashMap<String, Object>();
    private HashMap<String, String>                     dataTypeMapping         = new HashMap<String, String>();
    private Map<String, String>                         defFieldsMap            = new HashMap<String, String>();

    private PropertyManagement                          proputil                = PropertyManagement
                                                                                        .getInstance();
    private static HashMap<String, SearchEngineService> hashSearchEngineService = new HashMap<String, SearchEngineService>();

    private SearchEngineHelper                          seHelper;

    public SearchEngineService(String domain) {

        dataTypeMapping.put("ismasterdata", "boolean");
        dataTypeMapping.put("entityname", "string");
        dataTypeMapping.put("entitygroup", "string");
        dataTypeMapping.put("istreeentity", "boolean");
        dataTypeMapping.put("isuientity", "boolean");
        dataTypeMapping.put("isautogenuiform", "boolean");
        dataTypeMapping.put("editable", "boolean");
        dataTypeMapping.put("deletable", "boolean");
        dataTypeMapping.put("downloadable", "boolean");
        dataTypeMapping.put("fieldname", "string");
        dataTypeMapping.put("fieldtype", "string");
        dataTypeMapping.put("generictype", "string");
        dataTypeMapping.put("iscdatafield", "boolean");
        dataTypeMapping.put("isadvancesearchfield", "boolean");
        dataTypeMapping.put("key", "string");
        dataTypeMapping.put("value", "string");
        dataTypeMapping.put("notnull", "boolean");
        dataTypeMapping.put("sizemax", "string");
        dataTypeMapping.put("createddate", "date");
        dataTypeMapping.put("lastmodifieddate", "date");
        dataTypeMapping.put("createdby", "string");
        dataTypeMapping.put("lastmodifiedby", "string");
        dataTypeMapping.put("validationvalue", "string");
        dataTypeMapping.put("validationkey", "string");
        defDataTypeMapping.put("domainentity", dataTypeMapping);

        defFieldsMap.put("id", "string");
        defFieldsMap.put("entitytype", "string");
        defFieldsMap.put("site", "string");
        defFieldsMap.put("version", "string");

    }

    public static SearchEngineService getInstance(String domain) {
        if (!hashSearchEngineService.containsKey(domain)) {
            hashSearchEngineService
                    .put(domain, new SearchEngineService(domain));
        }
        return hashSearchEngineService.get(domain);
    }

    private void assignDomain(String domain) {
        solrconnection = new SolrConnection(domain);
        querystringparser = QueryStringParser.getInstance(domain);
        solrqueryframer = SolrQueryFramer.getInstance(domain);
        solrqueryvalidaion = SolrQueryValidation.getInstance(domain);
        solrconnection.setLogTracer(log);
        querystringparser.setLogTracer(log);
        solrqueryframer.setLogTracer(log);
        solrqueryvalidaion.setLogTracer(log);
        seHelper = SearchEngineHelper.getInstance(domain);

    }

    private LogTracer log;

    public void setLogTracer(final LogTracer log) {
        this.log = log;
    }

    private Map<String, Map<String, Object>> buildResponse(
            int solrResponseStatus, Map<String, Map<String, Object>> parameters) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        if (solrResponseStatus == 0) {
            this.log.debug("Solr - successful response");
            response.put("statusCode", "200");
            response.put("content", "Success");
        }
        parameters.put("output", response);
        log.debug("response -- " + response);
        return parameters;
    }

    private Map<String, Map<String, Object>> buildResponse(String collections,
            Map<String, Map<String, Object>> parameters) {
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("statusCode", "200");
        response.put("content", collections);
        parameters.put("output", response);
        log.debug("" + parameters);
        return parameters;
    }

    private HashMap<String, Object> getFieldNameMapping(String domain)
            throws GenericException {
        try {
            // String jsonContect = this.getClass().getClassLoader()
            // .getResource("FieldNameMapping.json").getFile();
            File file = new File(getResourcePath(domain)
                    + "FieldNameMapping.json");
            InputStream is = new FileInputStream(file);
            return FormatJson.getInstance(domain).in(IOUtils.toString(is));
        } catch (GenericException e) {
            log.error("GenericException throws : " + e.getMessage());
            throw e;
        } catch (IOException e) {
            log.error("IOException throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0016");
        }
    }

    private String getResourcePath(String domain) {
        PropertiesUtil proputil = PropertiesUtil.getInstanceof(domain,
                "resources");
        String resourcePath = proputil.getProperty("location") + domain
                + "/resources/";
        if (!resourcePath.endsWith("/")) {
            resourcePath += "/";
        }
        return resourcePath;
    }

    private HashMap<String, Object> addCountToCollection(
            HashMap<String, Object> collMap, Long count) {
        ArrayList<HashMap<String, String>> collection = null;
        if (collMap.containsKey("collections")) {
            collection = (ArrayList<HashMap<String, String>>) collMap
                    .remove("collections");
        }

        HashMap<String, String> hCount = new HashMap<String, String>();
        hCount.put("Count", "0");
        if (count > 0) {
            hCount.put("Count", count.toString());
        }
        collection.add(hCount);
        collMap.put("Collections", collection);
        return collMap;
    }

    private int deleteFromSolr(Object data, String domain)
            throws GenericException {
        UpdateResponse solrResponse = null;
        SolrServer solrServer = null;
        SolrQuery solrQuery = null;
        try {
            solrServer = solrconnection.getSolrInstance((domain + "-solrurl")
                    .toLowerCase().trim());
            if (data.getClass().getSimpleName().equals("SolrQuery")) {
                solrQuery = (SolrQuery) data;
                solrResponse = solrServer.deleteByQuery(solrQuery.getQuery());
            } else {
                solrResponse = solrServer.deleteById((List<String>) data);
            }
            solrResponse = solrServer.commit();
            return solrResponse.getStatus();
        } catch (IOException e) {
            log.error("IOException throws : " + e.toString());
            throw new GenericException(domain, "ERR-SE-0006",
                    "Unable to connect solr in deleteSolrDataQuery", e);
        } catch (SolrServerException e) {
            log.error("SolrServerException throws :" + e.toString());
            throw new GenericException(domain, "ERR-SE-0006",
                    "Unable to connect solr in add", e);
        } finally {
            solrResponse = null;
            solrServer = null;
            solrQuery = null;
        }

    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        assignDomain(domain);
        Map<String, Object> input = parameters.get("input");
        String collection = input.get("content").toString();
        log.debug("collection -- " + collection);
        HashMap<String, Object> xmlMap = new HashMap<String, Object>();
        UpdateResponse update = null;
        try {
            xmlMap = FormatXml.getInstance(domain).in(collection);
            FormatFlatten f = FormatFlatten.getInstance(domain, true);
            HashMap<String, Object> flattenMap = f.in(xmlMap, true);
            HashMap<String, Object> finalMap = collectionIterator(domain,
                    flattenMap, xmlMap, parameters);
            HashMap<String, Object> keyMap = getFieldNameMapping(domain);
            Collection<SolrInputDocument> solrDocs = FormatSolrXml.getInstance(
                    domain).out(
                    finalMap,
                    (HashMap<String, String>) keyMap
                            .get("reverseFieldNameMapping"), true);

            log.debug("SolrDocList :::: " + solrDocs);
            SolrServer solrserver = solrconnection
                    .getSolrInstance((domain + "-solrurl").toLowerCase().trim());
            solrserver.add(solrDocs);
            update = solrserver.commit();
            input.put("content", collection);
            parameters.put("input", input);
            log.debug("Solr Doc successfully Commited");
            return buildResponse(update.getStatus(), parameters);
        } catch (GenericException e) {
            log.error("Throwing GenericException for :" + e.getMessage());

            throw e;
        } catch (SolrServerException e) {
            log.error("Throwing SolrServerException for :" + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0006",
                    "Unable to connect solr in add", collection.toString(), e);
        } catch (IOException e) {
            log.error("Throwing IOException for :" + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0010", getClass()
                    .getSimpleName() + ":add()", domain, e);
        } finally {
            xmlMap.clear();
            update = null;
        }

    }

    private HashMap<String, Object> collectionIterator(String domain,
            HashMap<String, Object> flattenMap, HashMap<String, Object> xmlMap,
            Map<String, Map<String, Object>> parameters) {
        HashMap<String, Object> collHash = (HashMap<String, Object>) flattenMap
                .get(proputil.getProperties(domain, "searchengine",
                        "Configuration", "roottag"));

        HashMap<String, Object> newHash = new HashMap<String, Object>();
        for (Entry<String, Object> e : collHash.entrySet()) {
            HashMap<String, Object> modifiedHash = new HashMap<String, Object>();
            List<Object> newHashList = new ArrayList<Object>();
            String key = (String) e.getKey();
            if (!key.equalsIgnoreCase("Count")) {
                if (e.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("HashMap")) {
                    modifiedHash = getModifiedFieldHash(domain,
                            (HashMap<String, Object>) e.getValue(), key);
                    newHashList.add(modifiedHash);
                } else if (e.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("ArrayList")) {
                    List<HashMap<String, Object>> entityHashList = (List<HashMap<String, Object>>) e
                            .getValue();
                    for (HashMap<String, Object> entity : entityHashList) {
                        modifiedHash = getModifiedFieldHash(domain, entity, key);
                        newHashList.add(modifiedHash);
                    }

                }
                if (key.equalsIgnoreCase(proputil.getProperties(domain,
                        "searchengine", "Configuration", "entitytag"))) {
                    sendToCreateDataTypeMappingHash(domain,
                            (HashMap<String, Object>) xmlMap.get(proputil
                                    .getProperties(domain, "searchengine",
                                            "Configuration", "roottag")),
                            parameters);
                }
            }
            newHash.put(key, newHashList);
        }
        collHash.put(proputil.getProperties(domain, "searchengine",
                "Configuration", "roottag"), newHash);
        return collHash;
    }

    private HashMap<String, Object> getModifiedFieldHash(String domain,
            HashMap<String, Object> value, String key) {
        HashMap<String, Object> hash = (HashMap<String, Object>) value;
        HashMap<String, Object> newHash = new HashMap<String, Object>();
        for (Entry<String, Object> e : hash.entrySet()) {
            if (key.equalsIgnoreCase(proputil.getProperties(domain,
                    "searchengine", "Configuration", "entitytag"))) {
                if (dataTypeMapping.containsKey(e.getKey().toLowerCase())) {
                    newHash.put(
                            e.getKey()
                                    + "_"
                                    + dataTypeMapping.get(e.getKey()
                                            .toLowerCase()), e.getValue());
                } else {
                    newHash.put(e.getKey(), e.getValue());
                }
            } else {
                if (!defFieldsMap.containsKey(e.getKey().toLowerCase())) {
                    if (null != proputil.getProperties(domain, "searchengine",
                            key.toLowerCase(), e.getKey().toLowerCase())) {
                        newHash.put(
                                e.getKey()
                                        + "_"
                                        + proputil
                                                .getProperties(domain,
                                                        "searchengine", key,
                                                        e.getKey()),
                                e.getValue());
                    } else {
                        newHash.put(e.getKey() + "_string", e.getValue());
                    }
                } else {
                    newHash.put(e.getKey(), e.getValue());
                }
            }
        }
        return newHash;
    }

    private void sendToCreateDataTypeMappingHash(String domain,
            HashMap<String, Object> xmlMap,
            Map<String, Map<String, Object>> parameters) {
        for (Entry<String, Object> e : xmlMap.entrySet()) {
            HashMap<String, Object> modifiedHash = new HashMap<String, Object>();
            HashMap<String, Object> dataTypeMappingHash = new HashMap<String, Object>();
            HashMap<String, String> dataTypeHash = new HashMap<String, String>();
            String entityType = "";
            if (!e.getKey().equalsIgnoreCase("Count")
                    && e.getKey().equalsIgnoreCase(
                            proputil.getProperties(domain, "searchengine",
                                    "Configuration", "entitytag"))) {
                if (e.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("HashMap")) {
                    modifiedHash = (HashMap<String, Object>) e.getValue();
                    entityType = (String) modifiedHash.get(proputil
                            .getProperties(domain, "searchengine",
                                    "Configuration", "entitynamekey"));
                    HashMap<String, Object> fieldsHash = (HashMap<String, Object>) modifiedHash
                            .get(proputil.getProperties(domain, "searchengine",
                                    "Configuration", "fieldstag"));
                    createDataTypeMappingHash(domain, entityType,
                            fieldsHash.get(proputil
                                    .getProperties(domain, "searchengine",
                                            "Configuration", "fieldtag")),
                            parameters);
                } else if (e.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("ArrayList")) {
                    List<HashMap<String, Object>> entityHashList = (List<HashMap<String, Object>>) e
                            .getValue();
                    for (HashMap<String, Object> entity : entityHashList) {
                        entityType = (String) entity.get(proputil
                                .getProperties(domain, "searchengine",
                                        "Configuration", "entitynamekey"));

                        log.debug("Entity Hash :::: " + entity);
                        HashMap<String, Object> fieldsHash = (HashMap<String, Object>) entity
                                .get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldstag"));
                        createDataTypeMappingHash(domain, entityType,
                                fieldsHash.get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldtag")), parameters);
                    }

                }

            }

        }

    }

    private void createDataTypeMappingHash(String domain, String entityType,
            Object value, Map<String, Map<String, Object>> parameters) {
        HashMap<String, String> dataTypeHash = new HashMap<String, String>();
        if (value.getClass().getSimpleName().equalsIgnoreCase("HashMap")) {
            HashMap<String, Object> fieldHash = (HashMap<String, Object>) value;
            if (!defFieldsMap.containsKey((String) fieldHash.get(proputil
                    .getProperties(domain, "searchengine", "Configuration",
                            "fieldnametag")))) {
                createDataTypeMappingHash(
                        entityType,
                        domain,
                        parameters,
                        fieldHash
                                .get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldnametag")).toString()
                                .toLowerCase(),
                        fieldHash
                                .get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldtypetag")).toString()
                                .toLowerCase());

            }

        }
        if (value.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
            List<HashMap<String, Object>> fieldHashList = (List<HashMap<String, Object>>) value;
            for (HashMap<String, Object> field : fieldHashList) {
                if (!defFieldsMap.containsKey((String) field
                        .get(proputil.getProperties(domain, "searchengine",
                                "Configuration", "fieldnametag")).toString()
                        .toLowerCase())) {
                    createDataTypeMappingHash(
                            entityType,
                            domain,
                            parameters,
                            field.get(
                                    proputil.getProperties(domain,
                                            "searchengine", "Configuration",
                                            "fieldnametag")).toString()
                                    .toLowerCase(),
                            field.get(
                                    proputil.getProperties(domain,
                                            "searchengine", "Configuration",
                                            "fieldtypetag")).toString()
                                    .toLowerCase());

                }
            }
        }
    }

    private void createDataTypeMappingHash(String entity, String domain,
            Map<String, Map<String, Object>> parameters, String key,
            String value) {
        HashMap<String, Object> configHash = new HashMap<String, Object>();
        configHash.put("EntityName", entity);
        configHash.put("EntityType", "Configuration");
        configHash.put("ServiceType", "searchengine");
        HashMap<String, Object> configKeyChildHash = new HashMap<String, Object>();
        HashMap<String, Object> configKeyHash = new HashMap<String, Object>();
        configKeyChildHash.put("Key", key);
        configKeyChildHash.put("Value", value);
        configKeyHash.put("ConfigurationKey", configKeyChildHash);
        configHash.put("ConfigurationKeys", configKeyHash);
        Map<String, Object> input = parameters.get("input");
        HashMap<String, Object> collectionHash = new HashMap<String, Object>();
        collectionHash.put(proputil.getProperties(domain, "searchengine",
                "Configuration", "roottag"), configHash);
        String content = (String) FormatXml.getInstance(domain).out(
                collectionHash);
        input.put("content", content);
        parameters.put("input", input);

        log.debug("Entity Name in DataType :::: " + entity);
        proputil.putProperties(domain, "searchengine", entity,
                getConfigXMLAsMap(domain, parameters));
        // /addManage(domain, "searchengine", entity, parameters);
    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String ids,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        assignDomain(domain);
        List<String> delIdList = new ArrayList<String>();
        if (!Utils.getInstanceof(domain).isEmpty(ids)) {
            for (String id : ids.split(",")) {
                delIdList.add(id);
            }
            log.debug("List of ids to be deleted :" + delIdList);
        } else {
            throw new GenericException(domain, "ERR-SE-0005",
                    "MalformedURLException", ids);
        }
        try {
            int responseStatus = deleteFromSolr(delIdList, domain);
            if (responseStatus == 0) {
                proputil.deleteProperties(domain, "searchengine", entity);
            }
            return buildResponse(deleteFromSolr(delIdList, domain), parameters);
        } catch (GenericException e) {
            log.error("Throwing GenericException for :" + e.getMessage());
            throw e;
        }
    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters)
            throws GenericException {
        assignDomain(domain);
        SolrQuery solrQuery = null;
        HashMap<String, Object> querycoll = new HashMap<String, Object>();
        Map<String, Object> uriInfoMap = parameters.get("input");
        try {
            if (solrqueryvalidaion
                    .validateNotNull((HashMap<String, Object>) uriInfoMap
                            .get("queryParams"))) {
                log.debug("Valied url params");
                HashMap<String, String> dataTypeHash = getDataTypeHash(domain,
                        entity, uriInfoMap);

                // if (dataTypeHash.isEmpty()) {
                // dataTypeHash.put("datatype", new HashMap<String, String>());
                // }
                // HashMap<String, String> dataHash = (HashMap<String, String>)
                // dataTypeHash
                // .get("datatype");
                querycoll = querystringparser
                        .parseDeleteUrl((HashMap<String, Object>) uriInfoMap
                                .get("queryParams"), entity, domain,
                                dataTypeHash);
                log.debug("querycoll in deleteByQuery::" + querycoll.toString());
                solrQuery = solrqueryframer.getSolrQuery(querycoll);
                log.debug("solrQuery:::" + solrQuery);
            } else {
                throw new GenericException(domain, "ERR-SE-0005",
                        "MalformedURLException", parameters.toString());
            }
            return buildResponse(deleteFromSolr(solrQuery, domain), parameters);
        } catch (GenericException e) {
            log.error("Throwing GenericException for :" + e.getMessage());
            throw e;
        }
    }

    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        assignDomain(domain);
        HashMap<String, Object> querycoll = new HashMap<String, Object>();
        SolrServer solrServer = null;
        SolrQuery solrquery = null;
        SolrDocumentList solrdoclist = null;
        Map<String, Object> uriInfoMap = parameters.get("input");
        try {
            if (solrqueryvalidaion
                    .checkingUrlParam((HashMap<String, Object>) uriInfoMap
                            .get("queryParams"))) {
                solrServer = solrconnection
                        .getSolrInstance((domain + "-solrurl").toLowerCase()
                                .trim());
                log.debug("Entity Name :::: " + entity);

                HashMap<String, String> dataTypeHash = getDataTypeHash(domain,
                        entity, uriInfoMap);

                // if (dataTypeHash.isEmpty()) {
                // dataTypeHash.put("datatype", new HashMap<String, String>());
                // }
                // HashMap<String, String> dataHash = (HashMap<String, String>)
                // dataTypeHash
                // .get("datatype");

                log.debug("dataTypeHash  :::; " + dataTypeHash + " :::: "
                        + dataTypeHash);
                querycoll = querystringparser
                        .parseSearchUrl((HashMap<String, Object>) uriInfoMap
                                .get("queryParams"), entity, domain,
                                dataTypeHash);
                solrquery = solrqueryframer.getSolrQuery(querycoll);

                log.debug("IN Find Mehtod Solr Query :::: " + solrquery);
                solrdoclist = solrServer.query(solrquery).getResults();

                log.debug("Solr Doc List :::: " + solrdoclist);
                HashMap<String, Object> collMap = FormatSolrXml.getInstance(
                        domain).in(solrdoclist);
                collMap = addCountToCollection(collMap,
                        solrdoclist.getNumFound());
                HashMap<String, Object> keyMap = getFieldNameMapping(domain);
                return buildResponse((String) FormatSolrXml.getInstance(domain)
                        .out(collMap, keyMap), parameters);
            }
            log.error("URL not valid.");
            throw new GenericException(domain, "ERR-SE-0005",
                    "MalformedURLException", uriInfoMap.toString());
        } catch (GenericException e) {
            log.error("Throwing GenericException for :" + e.getMessage());
            throw e;
        } catch (SolrServerException e) {
            log.error("Throwing SolrServerException for :" + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0010",
                    "Error from solr while executing query", e);
        } finally {
            querycoll.clear();
            solrServer = null;
        }
    }

    private HashMap<String, String> getDataTypeHash(String domain,
            String entity, Map<String, Object> uriInfoMap) {
        List<Object> propList = new ArrayList<Object>();
        HashMap<String, String> dataTypeHash = new HashMap<String, String>();
        HashMap<String, Object> queryParams = (HashMap<String, Object>) uriInfoMap
                .get("queryParams");
        if (queryParams.containsKey("entitytype")) {
            entity = (String) queryParams.get("entitytype");
        }
        if (null == entity) {
            Map<String, Map<String, String>> propMap = proputil.getProperties(
                    domain, "searchengine");
            System.out.println("Properties Map :::: " + propMap);
            propList = getDataTypeHashWithoutEntity(propMap);
            dataTypeHash = checkURIInfo(domain, propList, queryParams);

        } else {
            dataTypeHash = (HashMap<String, String>) proputil.getProperties(
                    domain, "searchengine", entity);
        }
        return dataTypeHash;
    }

    private HashMap<String, String> checkURIInfo(String domain,
            List<Object> propList, HashMap<String, Object> hashMap) {
        log.debug("QueryParams Map :::: " + hashMap);
        String solrKeys = proputil.getProperties(domain, "searchengine",
                "Configuration", "solrkeylist");
        List<String> solrSearchKeys = Arrays.asList(proputil.getProperties(
                domain, "searchengine", "Configuration", "solrsearchkeys")
                .split(","));
        List<String> solrKeysList = Arrays.asList(solrKeys.split(","));
        Map<String, String> newHash = new HashMap<String, String>();
        Map<String, String> solrSearchKeyMap = new HashMap<String, String>();
        HashMap<String, String> finalHash = new HashMap<String, String>();
        int hashSize = 0;
        for (Entry<String, Object> e : hashMap.entrySet()) {
            System.out.println(e.getKey());
            if (!e.getKey().equalsIgnoreCase("q")
                    && !solrKeysList.contains(e.getKey())
                    && !defFieldsMap.containsKey(e.getKey().toLowerCase())) {
                hashSize++;
            }
        }
        List<String> solrSearchValues = new ArrayList<String>();
        for (String s : solrSearchKeys) {
            if (hashMap.containsKey(s)) {
                String v = (String) hashMap.get(s);
                if (v.contains(",")) {
                    List<String> val = Arrays.asList(v.split(","));
                    for (String v1 : val) {
                        if (!defFieldsMap.containsKey(v1))
                            solrSearchValues.add(v1);
                    }
                } else {
                    if (!defFieldsMap.containsKey(v)) solrSearchValues.add(v);
                }
            }
        }
        log.debug("Solr SearchValues :::: " + solrSearchValues);
        log.debug("URIINFO Map Size ::: " + hashSize);
        log.debug("Length of Array ::: " + propList.size());
        for (Object h : propList) {
            HashMap<String, String> h1 = (HashMap) h;
            log.debug("Inside Hash :::: " + h1);
            List<String> validHash = new ArrayList<String>();
            for (Entry<String, String> e1 : h1.entrySet()) {
                String val = e1.getValue();
                List<String> dataType = new ArrayList<String>();
                if (hashMap.containsKey(e1.getKey())
                        && !solrKeysList.contains(e1.getKey())) {
                    log.debug("hashmap containskey ::: " + e1.getKey());
                    try {
                        NumberFormat.getInstance().parse(
                                (String) hashMap.get(e1.getKey()));
                        if (seHelper
                                .isInteger((String) hashMap.get(e1.getKey()))) {
                            dataType.add("int");
                        }
                        if (seHelper.isFloat((String) hashMap.get(e1.getKey()))) {
                            dataType.add("float");
                        }
                        if (seHelper
                                .isDouble((String) hashMap.get(e1.getKey()))) {
                            dataType.add("double");
                        }
                    } catch (ParseException pe) {
                        if (seHelper
                                .isBoolean((String) hashMap.get(e1.getKey()))) {
                            dataType.add("boolean");
                        } else {
                            dataType.add("string");
                        }
                    }
                    log.debug(val + " :::::: " + dataType);
                    if (dataType.contains(val.toLowerCase())) {
                        validHash.add("true");

                    } else {
                        validHash.add("false");
                    }
                }
                for (String s : solrSearchValues) {
                    if (e1.getKey().equals(s)) {
                        if (null != solrSearchKeyMap.get(s)
                                && !val.equals(solrSearchKeyMap.get(s))) {
                            throw new GenericException(domain, "ERR-SE-0017");
                        } else {
                            solrSearchKeyMap.put(s, val);
                        }
                    }
                }
            }
            log.debug("ValidHash :::: " + validHash);
            if (!validHash.isEmpty()) {
                if (!validHash.contains("false")
                        && (validHash.size() == hashSize)) {
                    newHash = h1;
                    // break;
                }
            }
            finalHash = new HashMap<String, String>();
            finalHash.putAll(newHash);
            finalHash.putAll(solrSearchKeyMap);

        }
        log.debug(" New Hash ::::: " + finalHash);
        return finalHash;

    }

    private List<Object> getDataTypeHashWithoutEntity(
            Map<String, Map<String, String>> propMap) {
        List<Object> a = new ArrayList<Object>();
        for (Entry<String, Map<String, String>> e : propMap.entrySet()) {
            if (!e.getKey().equalsIgnoreCase("configuration")) {
                Map<String, String> value = e.getValue();
                a.add(value);
            }
        }
        a.add(defDataTypeMapping.get("domainentity"));
        return a;
    }

    public Map<String, String> getConfigXMLAsMap(final String domain,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("getConfigXMLAsMap entry");
        String content = (String) parameters.get("input").get("content");
        log.debug("input.content:" + content);
        if (content.trim().isEmpty()) {
            throw new GenericException(domain, "ERR-CONFIG-0002");
        }
        Map<String, Object> formatXml = FormatXml.getInstance(domain).in(
                content);
        Map<String, Object> collection = (HashMap<String, Object>) formatXml
                .get("Collections");
        if (collection == null) {
            throw new GenericException(domain, "ERR-CONFIG-0002");
        }
        log.debug("input.content.Collection:" + collection);
        Map<String, String> output = new HashMap<String, String>();
        List<String> KEY = new ArrayList<String>();
        List<String> VALUE = new ArrayList<String>();

        for (Entry<String, Object> entry : collection.entrySet()) {
            if (entry.getKey().equalsIgnoreCase("ConfigurationKeys")
                    && entry.getValue() instanceof HashMap) {
                HashMap<String, Object> confighash = (HashMap<String, Object>) entry
                        .getValue();
                for (String key : confighash.keySet()) {
                    if (key.equalsIgnoreCase("ConfigurationKey")
                            && confighash.get(key) instanceof ArrayList<?>) {

                        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) confighash
                                .get(key);
                        Iterator it = list.iterator();
                        for (; it.hasNext();) {
                            HashMap<String, Object> hash = (HashMap<String, Object>) it
                                    .next();
                            for (Entry<String, Object> key1 : hash.entrySet()) {
                                if (key1.getKey().equalsIgnoreCase("key")) {
                                    KEY.add((String) key1.getValue());
                                } else if (key1.getKey().equalsIgnoreCase(
                                        "value")) {
                                    VALUE.add((String) key1.getValue());
                                }
                            }
                        }
                    } else if (key.equalsIgnoreCase("ConfigurationKey")
                            && confighash.get(key) instanceof HashMap<?, ?>) {

                        HashMap<String, Object> list = (HashMap<String, Object>) confighash
                                .get(key);

                        for (Entry<String, Object> key1 : list.entrySet()) {
                            if (key1.getKey().equalsIgnoreCase("key")) {
                                KEY.add((String) key1.getValue());
                            } else if (key1.getKey().equalsIgnoreCase("value")) {
                                VALUE.add((String) key1.getValue());
                            }

                        }
                    }

                }

            }
        }
        for (int i = 0; i < KEY.size(); i++) {
            output.put(KEY.get(i), VALUE.get(i));
        }
        log.methodExit("getConfigXMLAsMap exit");
        return output;
    }

}
