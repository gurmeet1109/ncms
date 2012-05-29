package in.nic.cmf.seplugin.handler;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.seplugin.contract.ISearchAPIConstants;
import in.nic.cmf.seplugin.parser.LocationDetails;
import in.nic.cmf.seplugin.util.CommonUtils;
import in.nic.cmf.seplugin.util.PropertyGenerator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

import org.apache.solr.common.util.NamedList;
import org.apache.solr.handler.component.SearchHandler;
import org.apache.solr.request.LocalSolrQueryRequest;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.jboss.resteasy.specimpl.UriInfoImpl;

/**
 * Class Custom Search Handler.
 * @author premananda
 */
public class CustomSearchHandler extends SearchHandler {
    /**
     * holds for index of forwardslash.
     */
    private static final int       FORWARDSLASHINDEX = 5;

    /**
     * holds location details.
     */
    private static LocationDetails locDetails;

    /**
     * holds user data search API constant.
     */
    private static String          iUserData         = ISearchAPIConstants.USER_DATA;
    /**
     * holds solr data search API constant.
     */
    private static String          iSolrData         = ISearchAPIConstants.SOLR_DATA;
    /**
     * holds solr key list search API constant.
     */
    private static String          iSolrKeyList      = ISearchAPIConstants.SOLR_KEY_LIST;
    /**
     * holds condition search API constant.
     */
    private static String          iCond             = ISearchAPIConstants.COND;

    /**
     * holds user default condition search API constant.
     */
    private static String          iDefaultCondition = ISearchAPIConstants.DEFAULT_CONDITION;

    /**
     * holds hyphen5000 search API constant.
     */
    private static String          hY5000            = ISearchAPIConstants.HYPHEN5000;

    /**
     * holds comma search API constant.
     */
    private static String          iComma            = ISearchAPIConstants.COMMA;

    /**
     * holds entity type search API constant.
     */
    private static String          iEntityType       = ISearchAPIConstants.ENTITY_TYPE;

    /**
     * variable for log information.
     */
    private static LogTracer       log;

    /**
     * static instance of properties.
     */
    private static Properties      props;

    private static String          domain;

    /**
     * establishes query enum.
     * @author premananda
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
        /** Field: RADIUS to hold radius. */
        AUTHUSERNAME,
        /** Field: RADIUS to hold radius. */
        API_KEY,
        /** Field: RADIUS to hold radius. */
        FORMAT,
        /** Field: RADIUS to hold radius. */
        QT;
    }

    static {
        log = new LogTracer(domain, "seplugin", true, true, true, true, false);
        props = PropertyGenerator.getInstance().getProperties();
    }

    /**
     * get description.
     * @return description as string
     */
    public final String getDescription() {
        return "$ Handles cutomize search request. $";
    }

    /**
     * get sourceid.
     * @return sourceid as string
     */
    public final String getSourceId() {
        return "$ @author premananda $";
    }

    /**
     * get source.
     * @return source as string
     */
    public final String getSource() {
        return "$ solr/src/main/java/in/nic"
                + "/cmf/plugins/solr/handler/CustomSearchHandler $";
    }

    /**
     * get version.
     * @return version as string
     */
    public final String getVersion() {
        return "$ 3.4.0 $";
    }

    /**
     * method for actual glue with solr request and response.
     * @param req
     *            is a type of SolrQueryRequest
     * @param res
     *            is a type of SolrQueryResponse
     */
    public final void handleRequestBody(final SolrQueryRequest req,
            final SolrQueryResponse res) {

        try {
            super.handleRequestBody(new LocalSolrQueryRequest(req.getCore(),
                    getSolrEquivalentParams(req)), res);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * method to get site name.
     * @param path
     *            is a type of String
     * @param forwardSlashIndex
     *            is a type of int
     * @return is type of String
     */
    private String getDomain(final String path, final int forwardSlashIndex) {
        // if (forwardSlashIndex >= 0) {
        return path.substring(FORWARDSLASHINDEX, forwardSlashIndex);
        // }
        // return null;
    }

    /**
     * method to get entity type.
     * @param path
     *            is a type of String
     * @param forwardSlashIndex
     *            is a type of int
     * @return is a type of String
     */
    private String getEntity(final String path, final int forwardSlashIndex) {
        if (path.indexOf("/", forwardSlashIndex + 1) != -1) {
            return path.substring(path.indexOf("/", FORWARDSLASHINDEX) + 1,
                    path.indexOf("/", forwardSlashIndex + 1));
        }
        return null;
    }

    /**
     * method to customise query parameters.
     * @param req
     *            is a type of SolrQueryRequest
     * @return is a type of NamedList<Object>
     * @throws URISyntaxException
     *             is URI Syntax Exception
     */
    @SuppressWarnings ("unchecked")
    private NamedList<Object> getSolrEquivalentParams(final SolrQueryRequest req)
            throws URISyntaxException {
        String path = req.getContext().get("path").toString();

        int forwardSlashIndex = path.indexOf("/", FORWARDSLASHINDEX);

        UriInfoImpl uriinfo = new UriInfoImpl(new URI(path), new URI("/"),
                path, req.getParamString().replace("\"", "'"),
                new ArrayList<PathSegment>());

        String domain = getDomain(path, forwardSlashIndex);

        String entity = getEntity(path, forwardSlashIndex);

        Map<String, Object> args = null;

        args = parseSearchUrl(uriinfo, entity, domain);

        log.debug("application equivalent query: " + args);

        args.put("q", (Object) args.get("query").toString().replace("'", "\""));

        args.remove("query");

        args.put("fl", args.get("fields"));
        args.remove("fields");

        Map<String, String> sortFieldMap = (Map<String, String>) args
                .get("sortfield");
        for (String field : sortFieldMap.keySet()) {
            args.put("sort", (Object) (field + " " + sortFieldMap.get(field)
                    .toString()));
        }
        args.remove("sortfield");

        NamedList<Object> finalArgs = new NamedList<Object>();

        finalArgs.addAll(args);

        log.debug("solr equivalent query: " + finalArgs);

        return finalArgs;
    }

    /**
     * It is getting the solr query while using LocationDetails'
     * addLocationInformation.
     * @param uriinfo
     *            hold the url which is type of {@link UriInfo} class.
     * @param entityValue
     *            is a type of String
     * @param domainValue
     *            is a type of String
     * @return {@link HashMap} class object .
     */
    private HashMap<String, Object> parseSearchUrl(final UriInfo uriinfo,
            final String entityValue, final String domainValue) {
        HashMap querycoll = new HashMap();
        log.methodEntry(this.getClass().getSimpleName() + "parseSearchUrl()");
        // try {
        HashMap<String, HashMap> hmapSplitted = splitMultiValueMap(uriinfo
                .getQueryParameters());
        querycoll.putAll(getSolrUserQry(hmapSplitted.get(iUserData),
                entityValue, domainValue));
        querycoll.putAll(getSolrSpecificQry(hmapSplitted.get(iSolrData)));

        /*
         * if (querycoll.containsKey("checkLocationServices")) {
         * querycoll.putAll(locDetails.addLocationInformation(querycoll,
         * hmapSplitted.get(iSolrData)));
         * }
         */
        log.debug("parseSearchUrl querycoll:::::" + querycoll);
        return querycoll;
        /*
         * } catch (GenericException e) {
         * throw e;
         * } catch (Exception e) {
         * log.error("Throwing GenericException for :" + e.getMessage());
         * throw new GenericException("ERR-SE-0000", this.getClass().
         * getSimpleName() + ":parseSearchUrl()", domainValue + "/"
         * + entityValue, e);
         * } finally {
         * log.methodExit(this.getClass().getSimpleName()
         * + ": parseSearchUrl()");
         * }
         */
    }

    /**
     * splitting multi values map.
     * @param mapUriInfo
     *            is the type of MultivaluedMap<String, String>
     * @return is the type of HashMap<String, HashMap>
     */
    private HashMap<String, HashMap> splitMultiValueMap(
            final MultivaluedMap<String, String> mapUriInfo) {
        log.debug("mapUriInfo in splitMultiValueMap:::::::::" + mapUriInfo);
        log.methodEntry(this.getClass().getSimpleName()
                + ": splitMultiValueMap()");
        // try {
        HashMap<String, String> solrSpecificData = new HashMap<String, String>();
        String solrKeys = props.getProperty(iSolrKeyList);
        List<String> solrKeysList = Arrays.asList(solrKeys.split(iComma));
        for (String solrkey : solrKeysList) {
            solrSpecificData.put(solrkey, props.getProperty(solrkey));
        }
        HashMap<String, Object> solrUserData = new HashMap<String, Object>();
        solrUserData.put(iCond, props.getProperty(iDefaultCondition).trim());

        HashMap<String, HashMap> splitValHash = new HashMap<String, HashMap>();
        /*
         * List map = new ArrayList<String>();
         * map.add("entitytype2");
         * map.add("entitytype3");
         * mapUriInfo.put("entitytype", map);
         */
        // System.out.println("mapUriInfo: " + mapUriInfo.entrySet());
        Iterator iterator = mapUriInfo.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String keyValue = entry.getKey().toString();

            if (mapUriInfo.containsKey(keyValue)
                    && (!solrSpecificData.containsKey(keyValue.toLowerCase()))) {
                // System.out.println("mapUriInfo size: "
                // + mapUriInfo.get(entry.getKey().
                // toString()).size());
                if (mapUriInfo.get(entry.getKey().toString()).size() > 1) {
                    log.debug("value of key::::::::" + entry.getKey());
                    List<String> key = mapUriInfo
                            .get(entry.getKey().toString());
                    String listValue = "";
                    for (int i = 0; i < key.size(); i++) {
                        if (entry.getKey().equals("entitytype")) {
                            listValue += key.get(i).toString() + ",";
                        } else if (!entry.getKey().equals("q")) {
                            log.debug("key.size()::::::" + key.size());
                            /*
                             * if (key.get(i).toString().indexOf(",") > 0) {
                             * String keyString = key.get(i).toString();
                             * listValue += keyString.replace(",", "@#@")+
                             * "@#@"; } else {
                             */
                            listValue += key.get(i).toString().trim() + "@#@";
                            // }
                        } else {
                            listValue += key.get(i).toString();
                        }
                    }
                    if (!CommonUtils.getInstanceof(domain).isEmpty(listValue)) {
                        solrUserData.put(keyValue.toLowerCase(), listValue);
                    }
                } else {
                    solrUserData.put(keyValue.toLowerCase(), mapUriInfo
                            .getFirst(keyValue).toString());
                }
            } else {
                solrSpecificData.put(keyValue.toLowerCase(),
                        mapUriInfo.getFirst(keyValue));
            }
        }

        log.debug("User Query Map :" + solrUserData);
        log.debug("Solr Specific Query Map :" + solrSpecificData);
        splitValHash.put(iUserData, solrUserData);
        splitValHash.put(iSolrData, solrSpecificData);
        return splitValHash;
        /*
         * } catch (Exception e) {
         * log.error("Throwing GenericException for :" + e.getMessage());
         * throw new GenericException("ERR-SE-0000", this.getClass()
         * .getSimpleName() + ":splitMultiValueMap()",
         * mapUriInfo.toString(), e);
         * } finally {
         * log.methodExit(this.getClass().getSimpleName()
         * + ": splitMultiValueMap()");
         * }
         */
    }

    /**
     * get user solr query.
     * @param mapUserData
     *            is the type of HashMap<String, String>
     * @param entityValue
     *            is the type of String
     * @param domainValue
     *            is the type of String
     * @return is the type of HashMap
     */
    private HashMap getSolrUserQry(final HashMap<String, String> mapUserData,
            final String entityValue, final String domainValue) {
        log.methodEntry(this.getClass().getSimpleName() + ": setSolrUserQry()");
        HashMap hSolrUserQry = new HashMap();
        // try {
        String defaultcondition = props.getProperty("defaultcondition");
        String solrQueryFormat = "";

        // if (mapUserData.containsKey("cond")) {
        if (!mapUserData.get("cond").equalsIgnoreCase("and")
                && !mapUserData.get("cond").equalsIgnoreCase("or")) {
            defaultcondition = " " + "or".toUpperCase() + " ";
        } else {
            defaultcondition = " " + mapUserData.get("cond").toUpperCase()
                    + " ";
        }
        mapUserData.remove("cond");
        // }
        if (!CommonUtils.getInstanceof(domain).isEmpty(entityValue)) {
            mapUserData.put("entitytype", entityValue);
        }
        // if (!CommonUtils.getInstanceOf().isEmpty(domainValue)) {
        mapUserData.put("site", domainValue);
        // }
        HashMap<String, String> entityHash = new HashMap();
        if (mapUserData.containsKey("entitytype")) {
            entityHash.put("entitytype", mapUserData.get("entitytype"));
            mapUserData.remove("entitytype");
        }
        // if (mapUserData.containsKey("site")) {
        entityHash.put("site", mapUserData.get("site"));
        mapUserData.remove("site");
        // }

        if (mapUserData.containsKey("createdby")) {
            entityHash.put("createdby", mapUserData.get("createdby"));
            mapUserData.remove("createdby");
        }
        String solrQueryFormat3 = "";
        if (mapUserData.containsKey("q")) {
            /*
             * if (!CommonUtils.getInstanceOf().
             * isEmpty(mapUserData.get("q"))) {
             * solrQueryFormat3 = defaultcondition + "text:"
             * + mapUserData.get("q");
             * } else {
             */
            solrQueryFormat3 = defaultcondition + "*:*";
            // }
            mapUserData.remove("q");
        }

        log.debug("Map User Data:::::::::" + mapUserData);
        String solrQueryFormat1 = frameSolrQuery(entityHash, defaultcondition);

        String solrQueryFormat2 = frameSolrQuery(mapUserData, defaultcondition);
        if (solrQueryFormat2 != "") {
            // ///solrQueryFormat2 = defaultcondition
            // + solrQueryFormat2;
            solrQueryFormat2 = " AND " + solrQueryFormat2;
        }
        solrQueryFormat = "(" + solrQueryFormat1 + solrQueryFormat2
                + solrQueryFormat3 + ")";
        log.debug("solrQueryFormat1::::::" + solrQueryFormat1);
        log.debug("solrQueryFormat2::::::::::::::" + solrQueryFormat2);
        log.debug("solrQueryFormat3::::::::::::::" + solrQueryFormat3);
        log.debug("solrQueryFormat:::::::" + solrQueryFormat);

        log.debug("final Qry:::::" + solrQueryFormat);
        hSolrUserQry.put("query", solrQueryFormat);
        return hSolrUserQry;
        /*
         * } catch (Exception e) {
         * log.error("Throwing GenericException for :" + e.getMessage());
         * throw new GenericException("ERR-SE-0008", this.getClass()
         * .getSimpleName() + ":setSolrUserQry()",
         * mapUserData.toString(), e);
         * } finally {
         * log.methodExit(this.getClass().getSimpleName()
         * + ": setSolrUserQry()");
         * }
         */
    }

    /**
     * formating solr query.
     * @param mapUserData
     *            is the type of HashMap<String, String>
     * @param defaultcondition
     *            is the type of String
     * @return is the type of String
     */
    private String frameSolrQuery(final HashMap<String, String> mapUserData,
            final String defaultcondition) {
        String solrQueryFormat = "";
        final int conditionSubstring = 3;
        Iterator it = mapUserData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            boolean qoutesflag = checkQuote(pairs.getValue().toString()
                    .charAt(0));

            if (((String) pairs.getValue()).indexOf(",") > 0 && !qoutesflag
                    && ((String) pairs.getValue()).indexOf("@#@") > 0) {
                String[] multiSearch = ((String) pairs.getValue()).split("@#@");
                log.debug("multiSearch:::::" + multiSearch);
                for (int i = 0; i < multiSearch.length; i++) {
                    log.debug("test alist:::::::" + multiSearch[i]);
                    HashMap hValue = new HashMap();
                    hValue.put((String) pairs.getKey(), multiSearch[i]);
                    log.debug(hValue.toString());
                    solrQueryFormat += frameSolrQuery(hValue, defaultcondition)
                            + defaultcondition;
                }

            } else if (((String) pairs.getValue()).indexOf(",") > 0
                    && !qoutesflag) {
                String[] splitedValues = ((String) pairs.getValue()).split(",");
                solrQueryFormat += splitQryValues(splitedValues, " OR ",
                        (String) pairs.getKey());
            } else if (((String) pairs.getValue()).indexOf("@#@") > 0
                    && !qoutesflag) {
                log.debug("inside else::::index @#@:::" + pairs.getValue()
                        + "::default::" + defaultcondition);
                String[] splitedValues = ((String) pairs.getValue())
                        .split("@#@");
                solrQueryFormat += splitQryValues(splitedValues,
                        defaultcondition, (String) pairs.getKey());
            } else {
                if (pairs.getKey().equals("entitytype")
                        || pairs.getKey().equals("site")
                        || pairs.getKey().equals("createdby")) {
                    solrQueryFormat += pairs.getKey() + ":" + pairs.getValue()
                            + " AND ";
                } else {
                    solrQueryFormat += pairs.getKey() + ":" + pairs.getValue()
                            + defaultcondition;
                }
            }
        }
        log.debug("solrQueryFormat b4 commons getIns:::::::" + solrQueryFormat);
        if (mapUserData.containsKey("entitytype")
                || mapUserData.containsKey("site")
                || mapUserData.containsKey("createdby")
                & (!CommonUtils.getInstanceof(domain).isEmpty(solrQueryFormat))) {
            if (!CommonUtils.getInstanceof(domain).isEmpty(solrQueryFormat)) {
                solrQueryFormat = solrQueryFormat.substring(0,
                        (solrQueryFormat.length() - defaultcondition.length()));
            }
            solrQueryFormat = "(" + solrQueryFormat + ") AND ";
        }

        if (!CommonUtils.getInstanceof(domain).isEmpty(solrQueryFormat)) {
            solrQueryFormat = solrQueryFormat.substring(0,
                    (solrQueryFormat.length() - defaultcondition.length()));
        }
        log.debug("solrQueryFormat after commons getIns:::::::"
                + solrQueryFormat);
        return solrQueryFormat;
    }

    /**
     * splitting query values.
     * @param splitedValues
     *            is the type of String[]
     * @param defaultcondition
     *            is the type of String
     * @param key
     *            is the type of String
     * @return is the type of String
     */
    private String splitQryValues(final String[] splitedValues,
            final String defaultcondition, final String key) {
        String solrQueryFormat = "";
        // try {
        log.debug("splitedValues:::::::::" + splitedValues.length + ":::::"
                + splitedValues[1]);

        solrQueryFormat += "(";

        for (int i = 0; i < splitedValues.length; i++) {
            solrQueryFormat += key + ":" + splitedValues[i];
            if (i < (splitedValues.length - 1)) {
                if (/* key.equals("site") || */key.equals("createdby")
                        || key.equals("entitytype")) {
                    if (key.equals("entitytype")) {
                        solrQueryFormat += " OR ";
                    } else { // if (key.equals("createdby")) {
                        solrQueryFormat += defaultcondition;
                    } /*
                       * else {
                       * solrQueryFormat += " AND ";
                       * }
                       */
                } else {
                    solrQueryFormat += defaultcondition;
                }
            }
        }

        if (key.equals("entitytype") || key.equals("createdby")) {
            solrQueryFormat += ") AND ";
        } else {
            solrQueryFormat += ") " + defaultcondition;
        }
        log.debug("solrQueryFormat:::::::" + solrQueryFormat);

        /*
         * } catch (Exception e) {
         * log.debug(e.getMessage());
         * }
         */
        return solrQueryFormat;
    }

    /**
     * Set the solr query having some specific parameters in the query.
     * @param mapSolrData
     *            to map the data and solr field.
     * @return is a type of HashMap
     */
    private HashMap getSolrSpecificQry(final HashMap<String, String> mapSolrData) {
        log.methodEntry(this.getClass().getSimpleName()
                + ": setSolrSpecificQry()");
        /** Field:orderByDir as asc or desc. */
        HashMap querycoll = new HashMap();
        // try {
        String orderByDir = props.getProperty(ISearchAPIConstants.ORDER_BY_DIR);
        HashMap<String, String> ordermap = new HashMap<String, String>();
        /*
         * if (mapSolrData.get(ISearchAPIConstants.LATITUDE) != null) {
         * if ((!mapSolrData.get(ISearchAPIConstants.LATITUDE).equals(
         * hY5000))
         * && (!mapSolrData.get(ISearchAPIConstants.LONGITUDE)
         * .equals(hY5000))) {
         * log.debug("Checking location sevices and set to true");
         * querycoll.put("checkLocationServices", true);
         * }
         * }
         */

        String orderBy = "";
        for (String solrDataKey : mapSolrData.keySet()) {
            solrDataKey = solrDataKey.toLowerCase();
            if ((solrDataKey.contentEquals("orderby"))) {
                /*
                 * if (mapSolrData.get(solrDataKey).equals("")) {
                 * orderBy = "createddate".toLowerCase();
                 * } else {
                 */
                orderBy = mapSolrData.get(solrDataKey).toLowerCase();
                // }
            } else if ((solrDataKey.contentEquals("orderbydir"))) {
                if (!mapSolrData.get(solrDataKey).equals("asc")
                        && !mapSolrData.get(solrDataKey).equals("desc")) {
                    orderByDir = "desc".toUpperCase();
                } else {
                    orderByDir = mapSolrData.get(solrDataKey).toUpperCase();
                }
            } else {
                HashMap<String, String> hResult = switchByEnum(solrDataKey,
                        mapSolrData.get(solrDataKey).toUpperCase());
                log.debug("getSolrSpecificQry  querycollection:::"
                        + querycoll.toString());
                for (Map.Entry<String, String> e : hResult.entrySet()) {
                    querycoll.put(e.getKey(), e.getValue());
                }
            }
        }

        ordermap.put(orderBy, orderByDir.toLowerCase());

        querycoll.put("sortfield", ordermap);
        log.debug("Order by value" + orderByDir.toLowerCase());
        log.debug("Sort by value" + ordermap);
        log.debug("querycoll in getSolrSpecificQry:::::::::::::::::"
                + querycoll);
        return querycoll;
        /*
         * } catch (GenericException e) {
         * throw e;
         * } catch (Exception e) {
         * log.error("Throwing GenericException for :" + e.getMessage());
         * throw new GenericException("ERR-SE-0008", this.getClass()
         * .getSimpleName() + ":setSolrSpecificQry()",
         * mapSolrData.toString(), e);
         * } finally {
         * log.methodExit(this.getClass().getSimpleName()
         * + ": setSolrSpecificQry()");
         * }
         */
    }

    /**
     * Query enum based key-value pairs filter.
     * @param tempKey
     *            is a type of String
     * @param tempValue
     *            is a type of String
     * @return is a type of HashMap<String, String>
     */
    private HashMap<String, String> switchByEnum(final String tempKey,
            final String tempValue) {
        HashMap<String, String> querycoll = new HashMap();
        log.methodEntry(this.getClass().getSimpleName() + ": switchByEnum()");
        // try {
        QueryEnum numeral = null;
        // final int defaultLimit = new Integer(
        // props.getProperty(ISearchAPIConstants.LIMIT));
        final int maxLimit = new Integer(
                props.getProperty(ISearchAPIConstants.MAXIMUM_LIMIT));

        numeral = QueryEnum.valueOf(tempKey.toUpperCase());
        switch (numeral) {

            case LIMIT:

                if (new Integer(tempValue) > maxLimit) {
                    querycoll.put("rows", new Integer(maxLimit).toString());
                    log.debug("incoming limit is lesser "
                            + "than default so setting to default" + maxLimit);

                } else {
                    log.debug("incoming limit is gratter than "
                            + "default so setting to user limt" + tempValue);
                    querycoll.put("rows", new Integer(tempValue).toString());
                }

                break;
            case OFFSET:
                querycoll.put("start", (new Integer(tempValue).toString()));
                log.debug("Offset" + tempValue);
                break;
            case FIELDS:
                if (Arrays.asList(tempValue.toLowerCase().split(iComma))
                        .contains(iEntityType)) {
                    querycoll.put("fields", tempValue.toLowerCase());

                } else {
                    querycoll.put("fields", tempValue.toLowerCase() + iComma
                            + iEntityType);

                }
                log.debug("Fields to be displayed :" + tempValue);
                break;

            default:
                break;
        }
        return querycoll;
        /*
         * } catch (Exception e) {
         * log.error("Throwing GenericException for :" + e.getMessage());
         * throw new GenericException("ERR-SE-0008", this.getClass()
         * .getSimpleName() + ":switchByEnum()", "tempKey:" + tempKey
         * + "tempValue:" + tempValue, e);
         * } finally {
         * log.methodExit(this.getClass().getSimpleName()
         * + ": switchByEnum()");
         * }
         */
    }

    /**
     * checks the single or double quote.
     * @param theChar
     *            is the type of String
     * @return is the type of boolean
     */
    private boolean checkQuote(final char theChar) {
        switch (theChar) {
        /*
         * case '"':
         * return true;
         * case '\'':
         * return true;
         */

            default:
                return false;
        }
    }
}
