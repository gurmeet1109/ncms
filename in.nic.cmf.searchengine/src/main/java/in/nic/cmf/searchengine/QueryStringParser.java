package in.nic.cmf.searchengine;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;
import in.nic.cmf.util.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

public class QueryStringParser {

    /** Field: property to hold {@link}. */
    private PropertyManagement                        proputil              = PropertyManagement
                                                                                    .getInstance();
    // private static final QueryStringParser QUERYSTRINGPARSER = new
    // QueryStringParser();
    public LogTracer                                  log;
    private LocationDetails                           locDetails            = LocationDetails
                                                                                    .getInstance();
    /** Field: iUserData hold (@link ServiceConstants.USER_DATA}. */
    private String                                    iUserData             = ServiceConstants.USER_DATA;
    /** Field: iSolrData hold (@link ServiceConstants.SOLR_DATA}. */
    private String                                    iSolrData             = ServiceConstants.SOLR_DATA;
    /** Field: iSolrKeyList hold (@link ServiceConstants.SOLR_KEY_LIST}. */
    private String                                    iSolrKeyList          = ServiceConstants.SOLR_KEY_LIST;
    /** Field: iCond hold (@link ServiceConstants.COND}. */
    private String                                    iCond                 = ServiceConstants.COND;
    /**
     * Field: iDefaultCondition hold (@link
     * ServiceConstants.DEFAULT_CONDITION}.
     */
    private String                                    iDefaultCondition     = ServiceConstants.DEFAULT_CONDITION;
    /** Field: HY5000 hold (@link ServiceConstants.HYPHEN5000}. */
    private String                                    hY5000                = ServiceConstants.HYPHEN5000;
    /** Field: iComma hold (@link ServiceConstants.COMMA}. */
    private String                                    iComma                = ServiceConstants.COMMA;
    /** Field: iEntityType hold (@link ServiceConstants.ENTITY_TYPE}. */
    private String                                    iEntityType           = ServiceConstants.ENTITY_TYPE;
    private Utils                                     utils;
    private static HashMap<String, QueryStringParser> hashQueryStringParser = new HashMap<String, QueryStringParser>();
    private String                                    domain;

    private SearchEngineHelper                        seHelper;

    /**
     * #define : ORDERBYDIR Types of search options in Solr server. Field
     * :ORDERBY Types of search options in Solr server. Field :LIMIT Types of
     * search options in Solr server. Field :OFFSET Types of search options in
     * Solr server. Field :FIELDS Types of search options in Solr server.
     */
    static enum QueryEnum {
        /** Field: ORDERBYDIR to hold orderby directorion value. */
        ORDERBYDIR,
        /** Field: ORDERBY to hold order by field. */
        ORDERBY,
        /** Field: LIMIT to hold limit. */
        LIMIT,
        /** Field: MAXIMUMLIMIT to hold max limit. */
        MAXIMUMLIMIT,
        /** Field: OFFSET to hold offset. */
        OFFSET,
        /** Field: FIELDS to hold fields. */
        FIELDS,
        /** Field: LATITUDE to hold latitude. */
        LATITUDE,
        /** Field: LONGITUDE to hold long. */
        LONGITUDE,
        /** Field: RADIUS to hold radius. */
        RADIUS,
        /** Field: AUTHUSERNAME to hold username. */
        AUTHUSERNAME,
        /** Field: API_KEY to hold api_key. */
        API_KEY,
        /** Field: FORMAT to hold type of file. */
        FORMAT,
        /** Field: REQUESTER to hold remote address IP. */
        REQUESTER,
        /** Field: QT to hold q. */
        QT,
        /** Field: ACLROLE to hold acl role. */
        ACLROLE,

        TYPE;
    }

    /**
     * Constructor it is loading the properties and creating SolrQuery object.
     * @throws Exception
     *             which is type of {@link java.io.Exception} class.
     */
    private QueryStringParser(String domain) {
        this.domain = domain;
        ArrayList<String> propList = new ArrayList<String>();
        propList.add("config");
        propList.add("querystringparser");

        // proputil = PropertyManagement.getInstanceof(domain, "searchengine",
        // propList);
        // proputil = PropertyManagement.getInstanceOf(domain, "searchengine",
        // "Configuration");
        utils = Utils.getInstanceof(domain);
        seHelper = SearchEngineHelper.getInstance(domain);
    }

    // public static QueryStringParser getInstance() {
    // return QUERYSTRINGPARSER;
    // }
    public static QueryStringParser getInstance(String domain) {
        if (!hashQueryStringParser.containsKey(domain)) {
            hashQueryStringParser.put(domain, new QueryStringParser(domain));
        }
        return hashQueryStringParser.get(domain);
    }

    /**
     * It is getting the solr query while using LocationDetails'
     * addLocationInformation .
     * @param uriinfo
     *            hold the url which is type of {@link UriInfo} class.
     * @return {@link HashMap} class object .
     */
    public HashMap<String, Object> parseSearchUrl(
            final HashMap<String, Object> dataMap, final String entity,
            final String domain, final HashMap<String, String> dataTypeHash)
            throws GenericException {
        HashMap<String, Object> querycoll = new HashMap<String, Object>();
        try {
            HashMap<String, HashMap<String, String>> hmapSplitted = splitMultiValueMap(
                    dataMap, dataTypeHash);

            log.debug("hmapSplitted:" + hmapSplitted);
            log.debug("going to exclude params:");

            hmapSplitted = seHelper.excludeQueryParam(hmapSplitted);

            querycoll.putAll(getSolrUserQry(hmapSplitted.get(iUserData),
                    entity, domain));
            querycoll.putAll(getSolrSpecificQry(hmapSplitted.get(iSolrData),
                    dataTypeHash));

            if (querycoll.containsKey("checkLocationServices")) {
                querycoll.putAll(locDetails.addLocationInformation(querycoll,
                        hmapSplitted.get(iSolrData)));
            }
            return querycoll;
        } catch (GenericException e) {
            log.error("GenericException throws : " + e.toString());
            throw e;
        }
    }

    /**
     * Making the query for the deletion data from the solr server.
     * @param uriinfo
     *            hold the url which is type of {@link UriInfo} class.
     * @return {@link HashMap} class object .
     * @throws GenericException
     */
    public HashMap<String, Object> parseDeleteUrl(
            final HashMap<String, Object> dataMap, final String entityValue,
            final String domainValue, final HashMap<String, String> dataTypeHash)
            throws GenericException {
        try {
            HashMap<String, Object> querycoll = new HashMap<String, Object>();
            HashMap<String, HashMap<String, String>> hmapSplitted = splitMultiValueMap(
                    dataMap, dataTypeHash);
            HashMap<String, String> hSolrUserQuery = getSolrUserQry(
                    hmapSplitted.get(iUserData), entityValue, domainValue);
            HashMap<String, Object> hSolrSpecificQuery = getSolrSpecificQry(
                    removePredefinedValues(hmapSplitted.get(iSolrData)),
                    dataTypeHash);
            querycoll.putAll(hSolrSpecificQuery);
            querycoll.putAll(hSolrUserQuery);
            return querycoll;
        } catch (GenericException e) {
            log.error("GenericException throws : " + e.toString());
            throw e;
        }
    }

    /**
     * Removing the default solrkeylist from the incoming solr query. It is
     * being used from the getDeleteSolrQuery.
     * @param hmapsolrData
     *            hold data to remove from Solar Server which is type of
     *            {@link java.util.HashMap} class.
     * @return hmapsolrData which is type of {@link java.util.HashMap} class.
     * @throws GenericException
     */
    private HashMap<String, String> removePredefinedValues(
            final HashMap<String, String> hmapsolrData) {
        // String solrKeys = proputil.getProperty(iSolrKeyList);
        String solrKeys = proputil.getProperties(domain, "searchengine",
                "Configuration", iSolrKeyList);
        List<String> solrKeysList = Arrays.asList(solrKeys.split(iComma));
        for (String solrkey : solrKeysList) {
            if (hmapsolrData.containsKey(solrkey)) {
                hmapsolrData.remove(solrkey);
            }
        }
        return hmapsolrData;
    }

    /**
     * Split multi value map for making the solr query to get the data from solr
     * server. It is being used from getDeleteSolrQuery and getSolrQuery from
     * this class only.
     * @param mapUriInfo
     *            contain {@link MultivaluedMap} values.
     * @return {@link java.util.HashMap} values.
     */
    private HashMap<String, HashMap<String, String>> splitMultiValueMap(
            final HashMap<String, Object> uriInfoMap,
            final HashMap<String, String> dataTypeHash) throws GenericException {
        try {
            HashMap<String, String> solrSpecificData = new HashMap<String, String>();
            // String solrKeys = proputil.getProperty(iSolrKeyList);
            String solrKeys = proputil.getProperties(domain, "searchengine",
                    "Configuration", iSolrKeyList);
            List<String> solrKeysList = Arrays.asList(solrKeys.split(iComma));
            for (String solrkey : solrKeysList) {
                // solrSpecificData.put(solrkey, proputil.getProperty(solrkey));
                solrSpecificData.put(solrkey, proputil.getProperties(domain,
                        "searchengine", "Configuration", solrkey));
            }
            HashMap<String, String> solrUserData = new HashMap<String, String>();
            // solrUserData.put(iCond, proputil.getProperty(iDefaultCondition)
            // .trim());
            solrUserData.put(
                    iCond,
                    proputil.getProperties(domain, "searchengine",
                            "Configuration", iDefaultCondition).trim());
            HashMap<String, HashMap<String, String>> splitValHash = new HashMap<String, HashMap<String, String>>();
            log.debug("URIInfoMap ::::: " + uriInfoMap);
            log.debug("Solr Specific Data :::::: " + solrSpecificData);
            Iterator<Map.Entry<String, Object>> iterator = uriInfoMap
                    .entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                        .next();
                String keyValue = entry.getKey().toString();
                if (uriInfoMap.containsKey(keyValue)
                        && (!solrSpecificData.containsKey(keyValue
                                .toLowerCase()))) {
                    String[] values = (uriInfoMap.get(entry.getKey()))
                            .toString().split(",");
                    int size = Array.getLength(values);
                    if (size > 1) {
                        // List<String> key = (List<String>)
                        // uriInfoMap.get(entry.getKey()
                        // .toString());
                        String listValue = "";
                        for (int i = 0; i < size; i++) {
                            if (entry.getKey().equals("entitytype")) {
                                listValue += values[i] + ",";
                            } else if (!entry.getKey().equals("q")) {
                                listValue += values[i].trim() + "@#@";
                            } else {
                                listValue += values[i];
                            }
                        }
                        if (!utils.isEmpty(listValue)) {
                            solrUserData.put(keyValue.toLowerCase() + "_"
                                    + dataTypeHash.get(keyValue).toLowerCase(),
                                    listValue);
                        } else {
                            // need to check
                        }
                    } else {
                        String qparamvalue = (String) uriInfoMap.get(keyValue);
                        if (!utils.isEmpty(qparamvalue)
                                && !keyValue.equalsIgnoreCase("cond")) {
                            if (dataTypeHash.containsKey(keyValue)) {
                                solrUserData.put(keyValue.toLowerCase()
                                        + "_"
                                        + dataTypeHash.get(keyValue)
                                                .toLowerCase(), qparamvalue);
                            } else {
                                solrUserData.put(keyValue.toLowerCase(),
                                        qparamvalue);
                            }
                        } else {
                            solrUserData.put(keyValue.toLowerCase(),
                                    qparamvalue);
                        }

                    }
                } else {
                    String qparamvalue = (String) uriInfoMap.get(keyValue);
                    solrSpecificData.put(keyValue.toLowerCase(), qparamvalue);
                }
            }

            log.debug("User Query Map => " + solrUserData);
            log.debug("Solr Specific Query Map => " + solrSpecificData);
            splitValHash.put(iUserData, solrUserData);
            splitValHash.put(iSolrData, solrSpecificData);
            return splitValHash;
        } catch (GenericException e) {
            log.error("Throwing GenericException for :" + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0008", this.getClass()
                    .getSimpleName() + ".splitMultiValueMap()",
                    uriInfoMap.toString(), e);
        }
    }

    /**
     * It is being called from getSolrUserQry from this class itself.
     * @param mapUserData
     *            to map the userdata with query as {@link java.util.HashMap}.
     * @throws GenericException
     */
    private HashMap<String, String> getSolrUserQry(
            final HashMap<String, String> mapUserData,
            final String entityValue, final String domainValue)
            throws GenericException {
        HashMap<String, String> hSolrUserQry = new HashMap<String, String>();
        try {
            // String defaultCond = proputil.getProperty("defaultcondition");
            String defaultCond = proputil.getProperties(domain, "searchengine",
                    "Configuration", "defaultcondition");
            if (mapUserData.containsKey("cond")) {
                defaultCond = " " + mapUserData.get("cond").toUpperCase() + " ";
                mapUserData.remove("cond");
            }
            if (!utils.isEmpty(entityValue)) {
                mapUserData.put("entitytype", entityValue);
            }
            if (!utils.isEmpty(domainValue)) {
                mapUserData.put("site", domainValue);
            }
            HashMap<String, String> entityMap = new HashMap<String, String>();
            if (mapUserData.containsKey("entitytype")) {
                entityMap.put("entitytype", mapUserData.get("entitytype"));
                mapUserData.remove("entitytype");
            }
            if (mapUserData.containsKey("site")) {
                entityMap.put("site", mapUserData.get("site"));
                mapUserData.remove("site");
            }
            if (mapUserData.containsKey("createdby")) {
                entityMap.put("createdby", mapUserData.get("createdby"));
                mapUserData.remove("createdby");
            }
            String solrQueryFormat3 = "";
            if (mapUserData.containsKey("q")) {
                if (!utils.isEmpty(mapUserData.get("q"))) {
                    solrQueryFormat3 = defaultCond + "text:"
                            + mapUserData.get("q");
                } else {
                    solrQueryFormat3 = defaultCond + "*:*";
                }
                mapUserData.remove("q");
            }
            String solrQueryFormat1 = frameSolrQuery(entityMap, "", defaultCond);
            String solrQueryFormat = "";
            String solrQueryFormat2 = frameSolrQuery(mapUserData,
                    solrQueryFormat, defaultCond);
            if (!utils.isEmpty(solrQueryFormat2)) {
                solrQueryFormat2 = " AND " + solrQueryFormat2;
                // solrQueryFormat2 = "AND" + "(" + solrQueryFormat2 + ")";
            }
            solrQueryFormat = "(" + solrQueryFormat1 + solrQueryFormat2
                    + solrQueryFormat3 + ")";
            log.debug("Solr Query Format : " + solrQueryFormat);
            hSolrUserQry.put("query", solrQueryFormat);
            return hSolrUserQry;
        } catch (GenericException e) {
            log.error("GenericException throws :" + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0008", this.getClass()
                    .getSimpleName() + ":setSolrUserQry()",
                    mapUserData.toString(), e);
        }
    }

    private boolean checkQuote(char theChar) {
        switch (theChar) {
            case '"':
                return true;

            case '\'':
                return true;

            default:
                return false;
        }
    }

    private String frameSolrQuery(HashMap<String, String> mapUserData,
            String solrQueryFormat, String defaultcond) {
        Iterator<Map.Entry<String, String>> itr = mapUserData.entrySet()
                .iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>) itr
                    .next();
            boolean qoutesflag = checkQuote(pairs.getValue().toString()
                    .charAt(0));

            if (((String) pairs.getValue()).indexOf(",") > 0 && !qoutesflag
                    && ((String) pairs.getValue()).indexOf("@#@") > 0) {
                String[] multiSearch = ((String) pairs.getValue()).split("@#@");
                for (int i = 0; i < multiSearch.length; i++) {
                    HashMap<String, String> hValue = new HashMap<String, String>();
                    hValue.put((String) pairs.getKey(), multiSearch[i]);
                    solrQueryFormat += frameSolrQuery(hValue, "", defaultcond)
                            + defaultcond;
                }
            } else if (((String) pairs.getValue()).indexOf(",") > 0
                    && !qoutesflag) {
                String[] splitedValues = ((String) pairs.getValue()).split(",");
                solrQueryFormat += splitQryValues(splitedValues, "", " OR ",
                        (String) pairs.getKey());
            } else if (((String) pairs.getValue()).indexOf("@#@") > 0
                    && !qoutesflag) {
                log.debug("index @#@ : " + pairs.getValue() + " :: default: "
                        + defaultcond);
                String[] splitedValues = ((String) pairs.getValue())
                        .split("@#@");
                solrQueryFormat += splitQryValues(splitedValues, "",
                        defaultcond, (String) pairs.getKey());
            } else {
                if (pairs.getKey().equals("entitytype")
                        || pairs.getKey().equals("site")
                        || pairs.getKey().equals("createdby")) {
                    solrQueryFormat += pairs.getKey() + ":" + pairs.getValue()
                            + " AND ";
                } else {
                    solrQueryFormat += pairs.getKey() + ":" + pairs.getValue()
                            + defaultcond;
                }
            }
        }
        log.debug("solrQueryFormat before commons getIns : " + solrQueryFormat);
        if (mapUserData.containsKey("entitytype")
                || mapUserData.containsKey("site")
                || mapUserData.containsKey("createdby")
                & (!utils.isEmpty(solrQueryFormat))) {
            if (!utils.isEmpty(solrQueryFormat)) {
                solrQueryFormat = solrQueryFormat.substring(0,
                        (solrQueryFormat.length() - defaultcond.length()));
            }
            solrQueryFormat = "(" + solrQueryFormat + ") AND ";
        }

        if (!utils.isEmpty(solrQueryFormat)) {
            solrQueryFormat = solrQueryFormat.substring(0,
                    (solrQueryFormat.length() - defaultcond.length()));
        }
        log.debug("solrQueryFormat after commons getIns : " + solrQueryFormat);
        return solrQueryFormat;
    }

    private String splitQryValues(String[] splitedValues,
            String solrQueryFormat, String defaultcond, String key) {
        log.debug("splittedValues ::: " + splitedValues
                + " solrQueryFormat :::: " + solrQueryFormat
                + " defaultCond :::: " + defaultcond + " key ::: " + key);
        solrQueryFormat += "(";
        for (int i = 0; i < splitedValues.length; i++) {
            solrQueryFormat += key + ":" + splitedValues[i];
            if (i < (splitedValues.length - 1)) {
                if (key.equals("site") || key.equals("createdby")
                        || key.equals("entitytype")) {
                    if (key.equals("entitytype"))
                        solrQueryFormat += " OR ";
                    else if (key.equals("createdby")) {
                        solrQueryFormat += defaultcond;
                    } else {
                        solrQueryFormat += " AND ";
                    }
                } else {
                    solrQueryFormat += defaultcond;
                }
            }
        }

        if (key.equals("entitytype") || key.equals("createdby")) {
            solrQueryFormat += ") AND ";
        } else {
            solrQueryFormat += ") " + defaultcond;
        }
        return solrQueryFormat;
    }

    /**
     * Set the solr query having some specific parameters in the query.
     * @param mapSolrData
     *            to map the data and solr field.
     * @throws GenericException
     */
    private HashMap<String, Object> getSolrSpecificQry(
            final HashMap<String, String> mapSolrData,
            final HashMap<String, String> dataTypeHash) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ": getSolrSpecificQry()");
        /** Field:orderByDir as asc or desc. */
        HashMap<String, Object> querycoll = new HashMap<String, Object>();
        try {
            // String orderByDir = proputil
            // .getProperty(ServiceConstants.ORDER_BY_DIR);
            String orderByDir = proputil.getProperties(domain, "searchengine",
                    "Configuration", ServiceConstants.ORDER_BY_DIR);
            HashMap<String, String> ordermap = new HashMap<String, String>();
            if (mapSolrData.get(ServiceConstants.LATITUDE) != null) {
                if ((!mapSolrData.get(ServiceConstants.LATITUDE).equals(hY5000))
                        && (!mapSolrData.get(ServiceConstants.LONGITUDE)
                                .equals(hY5000))) {
                    querycoll.put("checkLocationServices", true);

                }
            }
            String orderBy = "";
            for (String solrDataKey : mapSolrData.keySet()) {
                solrDataKey = solrDataKey.toLowerCase();
                if ((solrDataKey.contentEquals("orderby"))) {
                    // if (mapSolrData.get(solrDataKey).toLowerCase()
                    // .endsWith("date")) {
                    // orderBy = mapSolrData.get(solrDataKey).toLowerCase()
                    // + "_date";
                    // } else
                    if (dataTypeHash.containsKey(mapSolrData.get(solrDataKey)
                            .toLowerCase())) {
                        orderBy = mapSolrData.get(solrDataKey).toLowerCase()
                                + "_"
                                + dataTypeHash.get(mapSolrData.get(solrDataKey)
                                        .toLowerCase());
                    } else {
                        orderBy = mapSolrData.get(solrDataKey).toLowerCase();
                    }
                } else if ((solrDataKey.contentEquals("orderbydir"))) {
                    orderByDir = mapSolrData.get(solrDataKey).toUpperCase();
                } else {
                    HashMap<String, String> hResult = switchByEnum(solrDataKey,
                            mapSolrData.get(solrDataKey).toUpperCase(),
                            dataTypeHash);
                    for (Map.Entry<String, String> e : hResult.entrySet()) {
                        querycoll.put(e.getKey(), e.getValue());
                    }
                }
            }
            ordermap.put(orderBy, orderByDir.toLowerCase());
            querycoll.put("sortfield", ordermap);
            log.debug("Sort by value => " + ordermap);
            log.debug("Query collection in getSolrSpecificQry => " + querycoll);
            return querycoll;
        } catch (GenericException e) {
            log.error("GenericException throws : " + e.toString());
            throw e;
        }
    }

    /**
     * @param tempKey
     *            temp key value.
     * @param tempValue
     *            temp value.
     * @throws GenericException
     */
    private HashMap<String, String> switchByEnum(final String tempKey,
            final String tempValue, final HashMap<String, String> dataTypeHash)
            throws GenericException {
        HashMap<String, String> querycoll = new HashMap<String, String>();
        QueryEnum numeral = null;
        // final int maxLimit = new Integer(
        // proputil.getProperty(ServiceConstants.MAXIMUM_LIMIT));
        final int maxLimit = new Integer(
                proputil.getProperties(domain, "searchengine", "Configuration",
                        ServiceConstants.MAXIMUM_LIMIT));
        numeral = QueryEnum.valueOf(tempKey.toUpperCase());
        switch (numeral) {
            case LIMIT:
                if (new Integer(tempValue) > maxLimit) {
                    querycoll.put("rows", new Integer(maxLimit).toString());
                    log.debug("Limit < default - " + maxLimit);
                } else {
                    log.debug("Limit > default - " + tempValue);
                    querycoll.put("rows", new Integer(tempValue).toString());
                }
                break;
            case OFFSET:
                querycoll.put("start", (new Integer(tempValue).toString()));
                log.debug("Offset - " + tempValue);
                break;
            case FIELDS:
                log.debug("Temp Value :::: " + tempValue);
                List<String> fields = Arrays.asList(tempValue.toLowerCase()
                        .split(iComma));
                String newTempValue = "";
                for (String f : fields) {
                    if (dataTypeHash.containsKey(f)) {
                        newTempValue += f + "_" + dataTypeHash.get(f) + ",";
                    } else {
                        newTempValue += f + ",";
                    }
                }
                newTempValue = newTempValue.substring(0,
                        newTempValue.length() - 1);

                if (Arrays.asList(newTempValue.toLowerCase().split(iComma))
                        .contains(iEntityType)) {
                    querycoll.put("fields", newTempValue.toLowerCase());
                } else {
                    querycoll.put("fields", newTempValue.toLowerCase() + iComma
                            + iEntityType);
                }
                break;
            default:
                break;
        }
        return querycoll;
    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
        locDetails.setLogTracer(log);
    }
}
