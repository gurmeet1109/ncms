package in.nic.cmf.searchengine;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
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
import java.io.StringReader;
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
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

@ServiceName ("searchengine")
public class SearchEngineServiceImpl implements CMFService {

    // private SolrConnection solrconnection = new SolrConnection();
    // private QueryStringParser querystringparser = QueryStringParser
    // .getInstance();
    // private SolrQueryFramer solrqueryframer = SolrQueryFramer
    // .getInstance();
    // private SolrQueryValidation solrqueryvalidaion = SolrQueryValidation

    private SolrConnection          solrconnection;
    private QueryStringParser       querystringparser;
    private SolrQueryFramer         solrqueryframer;
    private SolrQueryValidation     solrqueryvalidaion;
    private HashMap<String, Object> defDataTypeMapping1 = new HashMap<String, Object>();
    private HashMap<String, String> defDataTypeMapping  = new HashMap<String, String>();
    private HashMap<String, String> dataTypeMapping     = new HashMap<String, String>();
    private HashMap<String, Object> dataTypeMapping1    = new HashMap<String, Object>();

    private PropertyManagement      proputil            = PropertyManagement
                                                                .getInstance();
    private SearchEngineHelper      seHelper;

    public SearchEngineServiceImpl() {

        // HashMap<String, String> dataTypeHash = new HashMap<String, String>();
        defDataTypeMapping.put("ismasterdata", "String");
        defDataTypeMapping.put("entitytype", "String");
        defDataTypeMapping.put("entityname", "String");
        defDataTypeMapping.put("entitygroup", "String");
        defDataTypeMapping.put("istreeentity", "String");
        defDataTypeMapping.put("isuientity", "String");
        defDataTypeMapping.put("isautogenuiform", "String");
        defDataTypeMapping.put("editable", "String");
        defDataTypeMapping.put("deletable", "String");
        defDataTypeMapping.put("downloadable", "String");
        defDataTypeMapping.put("fieldname", "String");
        defDataTypeMapping.put("fieldtype", "String");
        defDataTypeMapping.put("generictype", "String");
        defDataTypeMapping.put("iscdatafield", "String");
        defDataTypeMapping.put("isadvancesearchfield", "String");
        defDataTypeMapping.put("key", "String");
        defDataTypeMapping.put("value", "String");
        defDataTypeMapping.put("notnull", "String");
        defDataTypeMapping.put("sizemax", "String");
        defDataTypeMapping.put("createddate", "Date");
        defDataTypeMapping.put("lastmodifieddate", "Date");
        defDataTypeMapping.put("createdby", "String");
        defDataTypeMapping.put("lastmodifiedby", "String");
        defDataTypeMapping.put("site", "String");
        defDataTypeMapping.put("version", "Double");
        defDataTypeMapping.put("validationvalue", "String");
        defDataTypeMapping.put("validationkey", "String");
        defDataTypeMapping1.put("datatype", defDataTypeMapping);

    }

    private void assignDomain(String domain) {
        solrconnection = new SolrConnection(domain);
        querystringparser = QueryStringParser.getInstanceof(domain);
        solrqueryframer = SolrQueryFramer.getInstanceof(domain);
        solrqueryvalidaion = SolrQueryValidation.getInstanceof(domain);
        solrconnection.setLogTracer(log);
        querystringparser.setLogTracer(log);
        solrqueryframer.setLogTracer(log);
        solrqueryvalidaion.setLogTracer(log);
        seHelper = SearchEngineHelper.getInstanceof(domain);

    }

    private LogTracer log;

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
            return FormatJson.getInstanceof(domain).in(IOUtils.toString(is));
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

    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    @Override
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
            xmlMap = FormatXml.getInstanceof(domain).in(collection);
            FormatFlatten f = FormatFlatten.getInstanceOf(domain, true);
            HashMap<String, Object> flattenMap = f.in(xmlMap, true);
            HashMap<String, Object> finalMap = collectionIterator(domain,
                    flattenMap, xmlMap, parameters);
            HashMap<String, Object> keyMap = getFieldNameMapping(domain);
            Collection<SolrInputDocument> solrDocs = FormatSolrXml
                    .getInstanceof(domain).out(
                            finalMap,
                            (HashMap<String, String>) keyMap
                                    .get("reverseFieldNameMapping"), true);
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
                if (defDataTypeMapping.containsKey(e.getKey().toLowerCase())) {
                    newHash.put(
                            e.getKey()
                                    + "_"
                                    + defDataTypeMapping.get(e.getKey()
                                            .toLowerCase()), e.getValue());
                } else {
                    newHash.put(e.getKey(), e.getValue());
                }
            } else {
                // PropertyManagement prop = PropertyManagement.getInstanceOf(
                // domain, "searchengine", key);
                // Map<String, String> propMap = prop.getPropertiesList();

                Map<String, String> propMap = proputil.getProperties(domain,
                        "searchengine", key);
                Map<String, Object> propList = getDataTypeHashWithEntity(propMap);
                ArrayList<Object> dataList = (ArrayList<Object>) propList
                        .get("datatype");
                Map<String, String> dataHash = (Map<String, String>) dataList
                        .get(0);
                if (dataHash.containsKey(e.getKey().toLowerCase())
                        && !e.getKey().equalsIgnoreCase("id")) {
                    newHash.put(
                            e.getKey() + "_"
                                    + dataHash.get(e.getKey().toLowerCase()),
                            e.getValue());
                } else if (!e.getKey().equalsIgnoreCase("Id")) {
                    newHash.put(e.getKey() + "_String", e.getValue());
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
                    dataTypeHash = createDataTypeMappingHash(domain,
                            fieldsHash.get(proputil
                                    .getProperties(domain, "searchengine",
                                            "Configuration", "fieldtag")));
                    dataTypeMapping1.put(entityType, dataTypeHash.toString());
                    createDataTypeMappingHash(entityType, domain, parameters,
                            dataTypeMapping1);
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
                        dataTypeHash = createDataTypeMappingHash(domain,
                                fieldsHash.get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldtag")));
                        dataTypeMapping1.put(entityType,
                                dataTypeHash.toString());
                        createDataTypeMappingHash(entityType, domain,
                                parameters, dataTypeMapping1);
                    }

                }

            }

        }

    }

    private HashMap<String, String> createDataTypeMappingHash(String domain,
            Object value) {
        HashMap<String, String> dataTypeHash = new HashMap<String, String>();
        if (value.getClass().getSimpleName().equalsIgnoreCase("HashMap")) {
            HashMap<String, Object> fieldHash = (HashMap<String, Object>) value;
            dataTypeMapping.put(
                    fieldHash
                            .get(proputil.getProperties(domain, "searchengine",
                                    "Configuration", "fieldnametag"))
                            .toString().toLowerCase(), (String) fieldHash
                            .get(proputil.getProperties(domain, "searchengine",
                                    "Configuration", "fieldtypetag")));
            dataTypeHash.put(
                    fieldHash
                            .get(proputil.getProperties(domain, "searchengine",
                                    "Configuration", "fieldnametag"))
                            .toString().toLowerCase(), (String) fieldHash
                            .get("fieldtypetag"));
        }
        if (value.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
            List<HashMap<String, Object>> fieldHashList = (List<HashMap<String, Object>>) value;
            for (HashMap<String, Object> field : fieldHashList) {
                dataTypeMapping.put(
                        field.get(
                                proputil.getProperties(domain, "searchengine",
                                        "Configuration", "fieldnametag"))
                                .toString().toLowerCase(), (String) field
                                .get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldtypetag")));
                dataTypeHash.put(
                        field.get(
                                proputil.getProperties(domain, "searchengine",
                                        "Configuration", "fieldnametag"))
                                .toString().toLowerCase(), (String) field
                                .get(proputil.getProperties(domain,
                                        "searchengine", "Configuration",
                                        "fieldtypetag")));
            }
        }
        return dataTypeHash;
    }

    private void createDataTypeMappingHash(String entity, String domain,
            Map<String, Map<String, Object>> parameters,
            HashMap<String, Object> dataTypeMapping) {
        HashMap<String, Object> configHash = new HashMap<String, Object>();
        configHash.put("EntityName", entity);
        configHash.put("EntityType", "Configuration");
        configHash.put("ServiceType", "searchengine");
        HashMap<String, Object> configKeyChildHash = new HashMap<String, Object>();
        HashMap<String, Object> configKeyHash = new HashMap<String, Object>();
        configKeyChildHash.put("Key", "DataType");
        configKeyChildHash.put("Value", dataTypeMapping.get(entity));
        configKeyHash.put("ConfigurationKey", configKeyChildHash);
        configHash.put("ConfigurationKeys", configKeyHash);
        Map<String, Object> input = parameters.get("input");
        HashMap<String, Object> collectionHash = new HashMap<String, Object>();
        collectionHash.put(proputil.getProperties(domain, "searchengine",
                "Configuration", "roottag"), configHash);
        String content = (String) FormatXml.getInstanceof(domain).out(
                collectionHash);
        input.put("content", content);
        parameters.put("input", input);

        log.debug("Entity Name in DataType :::: " + entity);
        addManage(domain, "searchengine", entity, parameters);
    }

    @Override
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
            return buildResponse(deleteFromSolr(delIdList, domain), parameters);
        } catch (GenericException e) {
            log.error("Throwing GenericException for :" + e.getMessage());
            throw e;
        }
    }

    @Override
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
                HashMap<String, String> dataTypeHash = new HashMap<String, String>();
                dataTypeHash.putAll(defDataTypeMapping);
                dataTypeHash.putAll(dataTypeMapping);
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

    @Override
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
                Map<String, Object> propList = new HashMap();
                Map<String, Object> dataTypeHash = new HashMap<String, Object>();
                if (null == entity) {
                    // PropertyManagement prop = new PropertyManagement(domain);
                    // Map<String, Map<String, String>> propMap = prop
                    // .getProperties(domain, "searchengine");
                    Map<String, Map<String, String>> propMap = proputil
                            .getProperties(domain, "searchengine");
                    propList = getDataTypeHashWithoutEntity(propMap);
                    dataTypeHash = checkURIInfo(domain, propList,
                            (HashMap<String, Object>) uriInfoMap
                                    .get("queryParams"));
                    Map<String, String> dataHash = new HashMap<String, String>();
                    dataHash.put("site", "String");
                    dataHash.put("entitytype", "String");
                    dataHash.put("createdby", "String");
                    if (dataTypeHash.isEmpty()) {
                        dataTypeHash.put("datatype", dataHash);
                    } else {
                        Map<String, String> d = (Map<String, String>) dataTypeHash
                                .get("datatype");
                        Map<String, String> newDataHash = new HashMap<String, String>();
                        newDataHash.putAll(dataHash);
                        newDataHash.putAll(d);
                        dataTypeHash.put("datatype", newDataHash);
                    }

                } else {
                    // PropertyManagement prop =
                    // PropertyManagement.getInstanceOf(
                    // domain, "searchengine", entity);
                    // Map<String, String> propMap = prop.getPropertiesList();
                    Map<String, String> propMap = proputil.getProperties(
                            domain, "searchengine", entity);
                    propList = getDataTypeHashWithEntity(propMap);
                    ArrayList<Object> dataList = (ArrayList<Object>) propList
                            .get("datatype");
                    Map<String, String> dataHash = (Map<String, String>) dataList
                            .get(0);
                    dataTypeHash.put("datatype", dataHash);
                }

                log.debug("dataTypeHash  :::; " + dataTypeHash + " :::: "
                        + dataTypeHash.get("datatype"));
                querycoll = querystringparser
                        .parseSearchUrl((HashMap<String, Object>) uriInfoMap
                                .get("queryParams"), entity, domain,
                                (HashMap<String, String>) dataTypeHash
                                        .get("datatype"));
                solrquery = solrqueryframer.getSolrQuery(querycoll);
                solrdoclist = solrServer.query(solrquery).getResults();
                HashMap<String, Object> collMap = FormatSolrXml.getInstanceof(
                        domain).in(solrdoclist);
                collMap = addCountToCollection(collMap,
                        solrdoclist.getNumFound());
                HashMap<String, Object> keyMap = getFieldNameMapping(domain);
                return buildResponse(
                        (String) FormatSolrXml.getInstanceof(domain).out(
                                collMap, keyMap), parameters);
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

    private Map<String, Object> checkURIInfo(String domain,
            Map<String, Object> propList, HashMap<String, Object> hashMap) {
        log.debug("QueryParams Map :::: " + hashMap);
        String solrKeys = proputil.getProperties(domain, "searchengine",
                "Configuration", "solrkeylist");
        List<String> solrKeysList = Arrays.asList(solrKeys.split(","));
        Map<String, Object> newHash = new HashMap<String, Object>();
        int hashSize = 0;
        for (Entry<String, Object> e : hashMap.entrySet()) {
            System.out.println(e.getKey());
            if (!e.getKey().equalsIgnoreCase("q")
                    && !solrKeysList.contains(e.getKey())) {
                hashSize++;
            }
        }
        log.debug("URIINFO Map Size ::: " + hashSize);
        for (Entry<String, Object> e : propList.entrySet()) {
            boolean res = false;

            ArrayList<Object> value = (ArrayList) e.getValue();
            log.debug("Length of Array ::: " + value.size());
            for (Object h : value) {
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
                            if (seHelper.isInteger((String) hashMap.get(e1
                                    .getKey()))) {
                                dataType.add("int");
                            }
                            if (seHelper.isFloat((String) hashMap.get(e1
                                    .getKey()))) {
                                dataType.add("float");
                            }
                            if (seHelper.isDouble((String) hashMap.get(e1
                                    .getKey()))) {
                                dataType.add("double");
                            }
                        } catch (ParseException pe) {
                            if (seHelper.isBoolean((String) hashMap.get(e1
                                    .getKey()))) {
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

                }
                log.debug("ValidHash :::: " + validHash);
                if (!validHash.isEmpty()) {
                    if (!validHash.contains("false")
                            && (validHash.size() == hashSize)) {
                        newHash.put("datatype", h1);
                        break;
                    }
                }

            }

        }
        log.debug(" New Hash ::::: " + newHash);
        return newHash;

    }

    private Map<String, Object> getDataTypeHashWithEntity(
            Map<String, String> propList) {
        Map<String, Object> newPropList = new HashMap<String, Object>();
        List<Object> a = new ArrayList<Object>();
        for (Entry<String, String> e : propList.entrySet()) {
            String value = (String) e.getValue();
            Properties props = new Properties();
            try {
                props.load(new StringReader(value.substring(1,
                        value.length() - 1).replace(", ", "\n")));
            } catch (IOException e2) {
                log.error("Throwing IOException for :" + e2.getMessage());
            }
            Map<String, String> map2 = new HashMap<String, String>();
            for (Map.Entry<Object, Object> e1 : props.entrySet()) {
                map2.put((String) e1.getKey(), (String) e1.getValue());
            }
            a.add(map2);
        }
        a.add(defDataTypeMapping1.get("datatype"));
        newPropList.put("datatype", a);
        log.debug("In getDataTypeHashWithEntity :::: " + newPropList);
        return newPropList;
    }

    private Map<String, Object> getDataTypeHashWithoutEntity(
            Map<String, Map<String, String>> propMap) {
        Map<String, Object> newPropList = new HashMap<String, Object>();
        List<Object> a = new ArrayList<Object>();
        for (Entry<String, Map<String, String>> e : propMap.entrySet()) {
            Map<String, String> firstValue = e.getValue();
            for (Entry<String, String> e1 : firstValue.entrySet()) {
                if (e1.getKey().equalsIgnoreCase("datatype")) {
                    String value = (String) e1.getValue();
                    Properties props = new Properties();
                    try {
                        props.load(new StringReader(value.substring(1,
                                value.length() - 1).replace(", ", "\n")));
                    } catch (IOException e2) {
                        log.error("Throwing IOException for :"
                                + e2.getMessage());
                    }
                    Map<String, String> map2 = new HashMap<String, String>();
                    for (Map.Entry<Object, Object> e2 : props.entrySet()) {
                        map2.put((String) e2.getKey(), (String) e2.getValue());
                    }
                    a.add(map2);
                }
            }
        }
        a.add(defDataTypeMapping1.get("datatype"));
        newPropList.put("datatype", a);
        log.debug("In getDataTypeHashWithoutEntity :::: " + newPropList);
        return newPropList;
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-SE-0015");
    }

    @Override
    public Map<String, Map<String, Object>> read(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) throws GenericException {
        throw new GenericException(arg0, "ERR-SE-0015");
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Integer result = Management.putProperties(domain, service, entity,
                getConfigXMLAsMap(domain, parameters));

        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");

        HashMap<String, Object> content = new HashMap<String, Object>();
        HashMap<String, Object> out = new HashMap<String, Object>();
        out.put("Success", result + " rows inserted");
        content.put("Collections", out);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.debug("addManage parameters:" + parameters);
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Map<String, String> propList = Management.getProperties(domain,
                service, entity);
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");
        HashMap<String, Object> content = new HashMap<String, Object>();
        content.put("Collections", propList);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.debug("findManage parameters:" + parameters);
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.debug("ENTITY:" + entity);
        PropertyManagement Management = PropertyManagement.getInstance();
        Integer result = Management.deleteProperties(domain, service, entity);
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");
        HashMap<String, Object> content = new HashMap<String, Object>();
        HashMap<String, Object> out = new HashMap<String, Object>();

        out.put("Success", result + " rows deleted");
        content.put("Collections", out);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.debug("deleteManage parameters:" + parameters);
        return parameters;
    }

    private Map<String, String> getConfigXMLAsMap(final String domain,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("getConfigXMLAsMap entry");
        String content = (String) parameters.get("input").get("content");
        log.debug("input.content:" + content);
        if (content.trim().isEmpty()) {
            throw new GenericException(domain, "ERR-CONFIG-0002");
        }
        Map<String, Object> formatXml = FormatXml.getInstanceof(domain).in(
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
