package in.nic.cmf.helper;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.formatters.FormatJSON;
import in.nic.cmf.formatters.FormatXML;
import in.nic.cmf.formatters.Formatter;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.security.owasp.Canonical;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.DateUtils;
import in.nic.cmf.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class CMFRequestParser.
 */
public class CMFRequestParser {

    /** The Constant parser. */
    private static final CMFRequestParser parser = new CMFRequestParser();

    /** The prop util. */
    private PropertiesUtil                propUtil;
    // = new PropertiesUtil("cmf");
    /** The log. */
    private LogTracer                     log;

    /** The utils. */
    // private Utils utils = Utils.getInstance();

    /**
     * Instantiates a new cMF request parser.
     */
    private CMFRequestParser() {

    }

    /**
     * Gets the single instance of CMFRequestParser.
     * @return single instance of CMFRequestParser
     */
    public static CMFRequestParser getInstance() {
        return parser;
    }

    public final void setLogTracer(LogTracer log) {
        this.log = log;
    }

    /**
     * Gets the list as string.
     * @param arrayList
     *            the array list
     * @return the list as string
     */
    private String getListAsString(List<String> arrayList) {
        String value = "";
        if ((isEmpty(arrayList)) || (arrayList.size() == 0)) {
            return value;
        }
        for (String paramvalue : arrayList) {
            if (value.length() > 0) value += ",";
            value += paramvalue;
        }
        return value;
    }

    public String getContentType(String domain, String input) {
        InputStream is = new ByteArrayInputStream(input.getBytes());
        Utils util = Utils.getInstanceof(domain);
        String mimeType = util.getMimeType(is);
        log.debug("Inside getcontentType : : : MimeType:" + mimeType);
        return mimeType;
    }

    /**
     * Parses the.
     * @param domain
     *            the domain
     * @param req
     *            the req
     * @param uriInfo
     *            the uri info
     * @return the hash map
     * @throws GenericException
     *             the generic exception
     */
    public Map<String, Map<String, Object>> parse(String domain,
            HttpServletRequest req, UriInfo uriInfo, String neededFormat)
            throws GenericException {

        log.debug("neededFormat:" + neededFormat);
        propUtil = PropertiesUtil.getInstanceof(domain, "cmf");

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());

        LinkedHashMap<String, String> queryParams = getUriInfoParams(domain,
                uriInfo);
        queryParams.put("type", "write");

        // parameters.get("input").put("format", "application/xml");

        String contentType = "";

        if (!isEmpty(queryParams.get("format"))) {
            // parameters.get("input").put("format", queryParams.get("format"));
            contentType = queryParams.get("format");
        }

        parameters.get("input").put("queryParams", queryParams);
        parameters.get("input").putAll(getURLInfo(req));
        HashMap<String, String> headers = getRequestHeader(req);
        String remoteIp = !isEmpty(headers.get("x-forwarded-for")) ? headers
                .get("x-forwarded-for") : req.getRemoteAddr();
        String remoteIps[] = remoteIp.split(",");
        remoteIp = remoteIps.length > 0 ? remoteIps[0] : "";
        if (isEmpty(headers.get("requestId"))) {
            headers.put("requestId", Uniqueid.getId());
        }
        parameters.get("input").put("headers", headers);
        HashMap<String, String> cookieParams = getCookieParams(req);
        parameters.get("input").put("cookieParams", cookieParams);
        // HashMap<String, Object> formParams = getFormParams(req);
        HashMap<String, Object> formParams = getFormParams(req);

        if (!isEmpty(getAsString(formParams.get("format")))) {
            // parameters.get("input").put("format", formParams.get("format"));
            contentType = queryParams.get("format");
        }

        /*
         * if(contentType.equals("xml")){
         * contentType = "application/xml";
         * }else if(contentType.equals("json")){
         * contentType = "application/json";
         * }else{
         * contentType = "application/xml";
         * }
         */
        contentType = "application/xml";
        parameters.get("input").put("format", contentType);

        /*
         * try {
         * log.debug("before setting contenttype."+parameters);
         * String inputContent = IOUtils.toString(req.getInputStream()).trim();
         * log.debug("inputcontent:"+inputContent);
         * //parameters.get("input").put("format", getContentType(domain,
         * inputContent));
         * log.debug("after setting contenttype."+parameters);
         * } catch (IOException e) {
         * log.error("In IOEx:"+e.getMessage());
         * e.printStackTrace();
         * }
         */

        parameters.get("input").put("formParams", formParams);
        HashMap<String, String> userContext = getUserContext(domain, remoteIp,
                queryParams, cookieParams);
        parameters.get("input").put("userContext", userContext);
        try {

            HashMap<String, Object> content = getInputContent(domain, req,
                    queryParams, formParams, userContext, headers, neededFormat);
            log.debug("content isempty check before" + content);
            if (!isEmpty(content)) {
                log.debug("putting allcontent");
                parameters.get("input").putAll(content);

            }
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
        log.debug("\n\nParameters.......\n" + parameters);
        return parameters;
    }

    private String getAsString(Object object) {

        if (object != null) {
            if (object instanceof List) {
                List<String> data = (List<String>) object;
                if (data.size() <= 0) return "";
                return data.get(0);
            } else if (object instanceof String) {
                log.debug("incoming is string : " + object.toString());
                String otherData = (String) object;
                log.debug("otherData:" + otherData);
                return otherData;
            }
        }
        return null;

        /*
         * if(object!=null){
         * if(object instanceof List){
         * List<String> data =(List<String>) object;
         * if(data.size()<=0)
         * return "";
         * return data.get(0);
         * }else{
         * log.debug(
         * "getAsString() expected parameter is List<String> but received one is "
         * +object.toString());
         * String otherData =(String) object;
         * log.debug("otherData:"+otherData);
         * return otherData;
         * }
         * }
         * return null;
         */
    }

    /**
     * Gets the form params.
     * @param req
     *            the req
     * @return the form params
     */
    private HashMap<String, Object> getFormParams(HttpServletRequest req) {
        HashMap<String, Object> formParams = new HashMap<String, Object>();
        try {
            Enumeration<?> requestParams = req.getParameterNames();
            log.debug("Form Params...");
            String key;
            List<String> values;
            Object value;
            while (requestParams.hasMoreElements()) {
                key = (String) requestParams.nextElement();
                values = Arrays.asList(req.getParameterValues(key));

                /*
                 * value = new String("");
                 * if (values.size() == 1) {
                 * value = values.get(0);
                 * }
                 * if (values.size() > 1) {
                 * value = values;
                 * }
                 */

                formParams.put(key, values);
                log.debug("Key : " + key + " ::    Value : " + values);
            }
            requestParams = null;
            return formParams;
        } catch (Exception e) {
            log.error("Exception in getFormParams(req) : " + e.getMessage());
            throw new GenericException("ERR-DS-0000", this.getClass()
                    .getSimpleName() + ":processUrlCodedContent()", e);
        }
    }

    /**
     * Gets the uRL info.
     * @param req
     *            the req
     * @return the uRL info
     */
    private HashMap<String, String> getURLInfo(HttpServletRequest req) {
        HashMap<String, String> urlInfo = new HashMap<String, String>();
        urlInfo.put("queryString", req.getQueryString());
        String url = req.getRequestURL().toString();
        if (!isEmpty(urlInfo.get("queryParam"))) {
            url += "?" + urlInfo.get("queryParam");
        }
        urlInfo.put("url", url);
        urlInfo.put("uri", req.getRequestURI());
        urlInfo.put("pathParam", req.getPathInfo());
        return urlInfo;
    }

    /**
     * Gets the request header.
     * @param req
     *            the req
     * @return the request header
     */
    private HashMap<String, String> getRequestHeader(HttpServletRequest req) {
        Enumeration<String> enumeration = req.getHeaderNames();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("method", req.getMethod());
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            headers.put(key, req.getHeader(key));
        }
        return headers;
    }

    /**
     * Gets the uri info params.
     * @param uriInfo
     *            the uri info
     * @return the uri info params
     */
    private LinkedHashMap<String, String> getUriInfoParams(String domain,
            UriInfo uriInfo) {
        MultivaluedMap<String, String> uriInfodata = uriInfo
                .getQueryParameters();
        LinkedHashMap<String, String> queryParams = new LinkedHashMap<String, String>();
        Set<String> keylist = uriInfodata.keySet();
        Iterator<String> iterator = keylist.iterator();
        Canonical canonical = new Canonical(domain);
        log.debug("URIInfo Params...");
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = getListAsString(uriInfodata.get(key));
            String keyLower = key.toLowerCase();
            log.debug("Key : " + key.toLowerCase() + " Value : " + value);
            if ((!keyLower.endsWith("date"))
                    && (!keyLower.endsWith("filepath"))
                    && (!keyLower.endsWith("seourl"))
                    && (!keyLower.endsWith("associatediapath"))
                    && (!keyLower.endsWith("metadatainfo"))
                    && (!isEmpty(value))) {
                try {
                    value = URLDecoder.decode(value, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    log.error("" + e);
                }
                log.debug("URLDecoder.decode(queryParam) : " + value);
                boolean isQuotedString = false;
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    isQuotedString = true;
                    value = value.substring(1, value.length() - 1);
                    log.debug("Value : " + value);
                }
                /*
                 * try { value = URLEncoder.encode(value, "UTF-8"); } catch
                 * (UnsupportedEncodingException e1) { log.error("" + e1); }
                 * log.debug("urlencode : " + value);
                 */
                value = canonical.getData(value);
                if (isQuotedString) {
                    value = "\"" + value + "\"";
                }
            }

            queryParams.put(key.toLowerCase(), value);
        }
        return queryParams;
    }

    /**
     * Gets the user context.
     * @param remoteIP
     *            the remote ip
     * @param queryParams
     *            the query params
     * @param cookieParams
     *            the cookie params
     * @return the user context
     */
    private HashMap<String, String> getUserContext(String domain,
            String remoteIP, HashMap<String, String> queryParams,
            HashMap<String, String> cookieParams) {
        String usercontextKeys = propUtil.get("userContextKeys");
        if (isEmpty(usercontextKeys)) {
            throw new GenericException(domain, "ERR-CMF-0005",
                    "Unable to load Properties");
        }
        HashMap<String, String> usercontext = new HashMap<String, String>();
        String[] keys = usercontextKeys.split(",");
        boolean isAnonymousUser = false;
        for (String key : keys) {
            key = key.toLowerCase(); // {"authusername", "api_key", "aclrole"};
            String value = cookieParams.get(key);
            if (isEmpty(value)) { // cookie value is empty
                value = queryParams.get(key);
            }
            if ((key.equals("authusername"))
                    && ((isEmpty(value)) || (value.equals("anonymous")))) {
                isAnonymousUser = true;
                break;
            }
            usercontext.put(key, value);
        }
        if (isAnonymousUser) {
            usercontext.clear();
            usercontext.put("authusername", "anonymous");
            usercontext.put("aclrole", "anonymous");
            usercontext.put("api_key", null);
        }
        usercontext.put("requester", remoteIP);
        return usercontext;
    }

    /**
     * Checks if is empty.
     * @param object
     *            the object
     * @return true, if is empty
     */
    private boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return (((String) object).trim()).isEmpty();
        }
        return false;
    }

    /**
     * Gets the cookie params.
     * @param req
     *            the req
     * @return the cookie params
     */
    private HashMap<String, String> getCookieParams(HttpServletRequest req) {
        HashMap<String, String> cookieParams = new HashMap<String, String>();
        Cookie cookies[] = req.getCookies();
        if (!isEmpty(cookies)) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie myCookie = cookies[i];
                cookieParams.put(myCookie.getName().toLowerCase(),
                        myCookie.getValue());
                myCookie = null;
            }
        }
        cookies = null;
        return cookieParams;
    }

    /**
     * Gets the input content.
     * @param domain
     *            the domain
     * @param req
     *            the req
     * @param formParams
     *            the form params
     * @param userContext
     *            the user context
     * @param headers
     *            the headers
     * @return the input content
     * @throws GenericException
     *             the generic exception
     */
    private HashMap<String, Object> getInputContent(String domain,
            HttpServletRequest req, HashMap<String, String> queryParams,
            HashMap<String, Object> formParams,
            HashMap<String, String> userContext,
            HashMap<String, String> headers, String format)
            throws GenericException {
        log.debug("format is:" + format);
        try {
            log.debug("isMultiPartContent : "
                    + ServletFileUpload.isMultipartContent(req));

            if (ServletFileUpload.isMultipartContent(req)) {
                return getMultipartContent(domain, req, queryParams,
                        formParams, userContext);
            } else {
                return getUrlEncodedFormContent(domain, req, queryParams,
                        formParams, headers, format);
            }
        } catch (GenericException ge) {
            log.debug("File upload error" + ge);
            throw ge;
        }
    }

    private HashMap<String, Object> getContentAsMap(String domain,
            String input, String format) {
        log.debug("Inside getContentAsMap : domain:" + domain + " ; input : "
                + input + " ; " + format);
        Formatter f = null;
        format = format.toLowerCase();
        Map<String, Object> content = new HashMap<String, Object>();
        if (format.equals("xml") || input.contains("<Collections>")) {
            f = (Formatter) new FormatXML();
            content = f.convert(domain, input);
        } else if (format.equals("json")) {
            f = (Formatter) new FormatJSON();
            log.debug("In formatJSON instance:" + domain + ";;;" + input);
            content = f.convert(domain, input);
            log.debug("after convert:" + content);
        } else if (format.equals("extjson")) {
            f = (Formatter) new FormatJSON();
            log.debug("In formatExtJSON instance:" + domain + ";;;" + input);
            content = f.convert(domain, input);
            log.debug("after convert:" + content);
        }
        log.debug("content is: " + content);
        return (HashMap<String, Object>) content;
    }

    /**
     * Gets the url encoded form content.
     * @param domain
     *            the domain
     * @param req
     *            the req
     * @param formParams
     *            the form params
     * @param headers
     *            the headers
     * @return the url encoded form content
     * @throws GenericException
     *             the generic exception
     */
    private HashMap<String, Object> getUrlEncodedFormContent(String domain,
            HttpServletRequest req, HashMap<String, String> queryParams,
            HashMap<String, Object> formParams,
            HashMap<String, String> headers, String format)
            throws GenericException {
        log.debug("getURLEncoded:" + domain + ";queryParam:" + queryParams
                + ";formParams:" + formParams + "headers:" + headers
                + ";format:" + format);
        Utils utils = Utils.getInstanceof(domain);
        ConvertorUtils cu = ConvertorUtils.getInstanceof(domain);
        if ((isEmpty(getAsString(formParams.get("Id"))))
                && (headers.get("method").equals("POST"))
                && getAsString(formParams.get("EntityType")) != null) {
            formParams.put("Id", Uniqueid.getId());
        }
        HashMap<String, Object> content = new HashMap<String, Object>();
        log.debug("Entity Type : " + getAsString(formParams.get("EntityType")));
        log.debug("Method : " + headers.get("method"));
        log.debug("Content Length : " + headers.containsKey("content-length"));

        if ((getAsString(formParams.get("EntityType")) == null)
                && (headers.get("method").equals("POST"))) {
            try {
                if (headers.containsKey("content-length")) {
                    String inputContent = IOUtils
                            .toString(req.getInputStream()).trim();

                    log.debug("Input Content ::::::::::::::::::::::::::; "
                            + inputContent + ";format:" + format);

                    HashMap<String, Object> inputContentMap = getContentAsMap(
                            domain, inputContent, format);

                    log.debug("for formatting:" + inputContentMap);
                    content.put("content",
                            convertWithCDATA(domain, inputContentMap));
                    log.debug("return content:::" + content);
                }
            } catch (IOException e) {
                throw new GenericException(domain, "ERR-CMF-0003", e);
            }
        } else {
            log.debug("before create Input Content");
            content = createInputContent(domain, queryParams, formParams);
        }
        return content;
    }

    /**
     * Gets the multipart content.
     * @param domain
     *            the domain
     * @param req
     *            the req
     * @param formParams
     *            the form params
     * @param userContext
     *            the user context
     * @return the multipart content
     * @throws GenericException
     *             the generic exception
     */
    private HashMap<String, Object> getMultipartContent(String domain,
            HttpServletRequest req, HashMap<String, String> queryParams,
            HashMap<String, Object> formParams,
            HashMap<String, String> userContext) throws GenericException {
        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());
        Utils utils = Utils.getInstanceof(domain);
        List<FileItem> fileItemList = null;
        try {
            fileItemList = upload.parseRequest(req);
        } catch (FileUploadException e) {
            log.error("FileUploadException throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0006", e);
        }
        if (!isEmpty(fileItemList)) {
            LinkedHashMap<String, Object> files = new LinkedHashMap<String, Object>();

            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField()) {
                    log.debug("Key -- " + fileItem.getFieldName()
                            + " :: Value -- " + fileItem.getString());
                    if (formParams.containsKey(fileItem.getFieldName())) {
                        formParams.put(
                                fileItem.getFieldName(),
                                getAsString(formParams.get(fileItem
                                        .getFieldName()))
                                        + ","
                                        + fileItem.getString());
                    } else {
                        formParams.put(fileItem.getFieldName(),
                                fileItem.getString());
                    }
                }
            }
            int idCount = 0;
            String id = null;
            for (FileItem fileItem : fileItemList) {
                if (fileItem.isFormField()) continue;
                if (!isEmpty(getAsString(formParams.get("Id"))) && idCount == 0) {
                    id = (String) getAsString(formParams.get("Id"));
                    idCount++;
                } else {
                    id = Uniqueid.getId();
                }
                files.put(id, fileItem);
            }
            log.debug("Binary Data -> " + files);
            return createInputContent(domain, queryParams, formParams, files,
                    userContext);
        }
        return null;
    }

    /**
     * Creates the input content.
     * @param domain
     *            the domain
     * @param formParams
     *            the form params
     * @param files
     *            the files
     * @param userContext
     *            the user context
     * @return the hash map
     */
    private HashMap<String, Object> createInputContent(String domain,
            HashMap<String, String> queryParams,
            HashMap<String, Object> formParams, HashMap<String, Object> files,
            HashMap<String, String> userContext) {
        HashMap<String, Object> content = createInputContent(domain,
                queryParams, formParams);
        HashMap<String, Object> entities = new HashMap<String, Object>();
        HashMap<String, Object> binaryFiles = new HashMap<String, Object>();
        if ((!isEmpty(files)) && (files.size() > 0)) {
            HashMap<String, Object> params = new HashMap<String, Object>();
            // HashMap<String, Object> sourceMedia = new HashMap<String,
            // Object>();
            // params.putAll(formParams);
            List<Map<String, Object>> binaryParamsList = new ArrayList<Map<String, Object>>();
            int reqCount = 0;
            for (Map.Entry<String, Object> fileEntry : files.entrySet()) {
                params.clear();
                params.putAll(formParams);
                String fileKey = fileEntry.getKey();
                params.put("Id", fileKey);
                log.debug("Form params -- " + params);
                try {
                    FileItem fileItem = (FileItem) fileEntry.getValue();
                    HashMap<String, Object> fileInfo = new HashMap<String, Object>();
                    // fileInfo.put("id", fileKey);
                    fileInfo.put("Id", fileItem.getName());
                    fileInfo.put("Data", fileItem.get());
                    binaryParamsList.add(fileInfo);
                    // MediaXmlParser mediaxmlparser = new MediaXmlParser();
                    // mediaxmlparser.setLogger(log);
                    // HashMap<String, Object> sourceMedia = mediaxmlparser
                    // .frameMediaEntity(domain, reqCount, params,
                    // / (FileItem) fileEntry.getValue(),
                    // // userContext);
                    // binaryFiles.putAll((Map<String, Object>) sourceMedia
                    // .get("files"));
                    // sourceMedia.remove("files");
                    // log.debug("Media Xml Parser :: Response -- " +
                    // sourceMedia);
                    // addMediaEntity(sourceMedia, entities);
                    // reqCount++;
                } catch (GenericException e) {
                    log.error(e.getMessage());
                    throw e;
                }
            }
            content.put("files", binaryParamsList);
        } else {
            String filePath = null;
            if (!isEmpty(getAsString(formParams.get("SourceName")))) {
                filePath = "/" + getAsString(formParams.get("Site"))
                        + "/media/" + getAsString(formParams.get("Id")) + ".";
                if (getAsString(formParams.get("EntityType")).equals("Media")) {
                    filePath += getAsString(formParams.get("MimeSimpleName"));
                } else if (getAsString(formParams.get("EntityType")).equals(
                        "Template")) {
                    filePath += "html";
                } else {
                    Utils utils = Utils.getInstanceof(domain);
                    filePath += utils
                            .getFileExtnByFileName(((String) formParams
                                    .get("SourceName")));
                }
            }
            formParams.put("FilePath", filePath);
        }
        HashMap<String, Object> collections = new HashMap<String, Object>();
        // collections.put("Collections", entities);
        // collections.put("Collections", content.get("content"));
        // content
        // content.put("content",
        // convertWithCDATA(domain, (String) content.get("content")));
        log.debug("params => " + formParams);
        log.debug("content => " + content);
        return content;
    }

    private void addMediaEntity(HashMap<String, Object> media,
            HashMap<String, Object> entities) {
        if (entities.isEmpty()) {
            entities.putAll(media);
        } else {
            for (Map.Entry<String, Object> entry : media.entrySet()) {
                log.debug("key -- " + entry.getKey() + " :: value -- "
                        + entry.getValue());
                if (entities.containsKey(entry.getKey())) {
                    for (HashMap<String, String> entity : (List<HashMap<String, String>>) entry
                            .getValue()) {
                        ((List<HashMap<String, String>>) entities.get(entry
                                .getKey())).add(entity);
                    }
                }
            }
        }
    }

    /**
     * Creates the input content.
     * @param domain
     *            the domain
     * @param formParams
     *            the form params
     * @return the hash map
     * @throws GenericException
     *             the generic exception
     */
    private HashMap<String, Object> createInputContent(String domain,
            HashMap<String, String> queryParams,
            HashMap<String, Object> formParams) throws GenericException {
        String entityType = (String) getAsString(formParams.get("EntityType"));
        log.debug("Entity Type => " + entityType);
        HashMap<String, Object> content = new HashMap<String, Object>();
        if (isEmpty(entityType)) {
            return content;
        }
        try {
            FormatJson formatJson = FormatJson.getInstanceof(domain);
            InputStream istream = new FileInputStream(getResourcePath(domain)
                    + "Mapping.json");
            HashMap<String, Object> mappingJson = formatJson.in(IOUtils
                    .toString(istream));
            HashMap<String, Object> entityTypeJson = (HashMap<String, Object>) mappingJson
                    .get(entityType);

            log.debug("incoming parameters:" + formParams + ";queryParams:"
                    + queryParams);
            if (entityTypeJson == null) {
                log.debug("entityTypeJson is null : " + entityTypeJson);
                HashMap<String, Object> uniParams = new HashMap<String, Object>();
                for (Entry<String, Object> entry : formParams.entrySet()) {
                    if (queryParams.containsKey(entry.getKey())) continue;
                    uniParams.put(entry.getKey(), entry.getValue());
                }
                formParams = uniParams;
                uniParams = null;
                HashMap<String, Object> entity = new HashMap<String, Object>();
                entity.put(entityType, formParams);

                HashMap<String, Object> collection = new HashMap<String, Object>();
                collection.put("Collections", entity);
                log.debug("Non-Domain entity collection : " + collection);
                String contentStr = (String) FormatXml.getInstanceof(domain)
                        .out(collection);
                entity = null;
                collection = null;
                content.put("content", contentStr);
                log.debug("contentStr" + contentStr);
                return content;
            }
            HashMap<String, Object> typeMapping = (HashMap<String, Object>) entityTypeJson
                    .get("typeMapping");
            HashMap<String, Object> classInfo = (HashMap<String, Object>) entityTypeJson
                    .get("classInfo");
            List<String> interfaceInfo = (List<String>) classInfo
                    .get("interfaceInfo");
            log.debug("before removing removeNonEntityFields : " + formParams);
            formParams = removeNonEntityFields(formParams, typeMapping);
            log.debug("after removing removeNonEntityFields : " + formParams);
            DateUtils dUtils = DateUtils.getInstanceOf(domain);
            Iterator<Entry<String, Object>> formItr = formParams.entrySet()
                    .iterator();
            while (formItr.hasNext()) {
                Map.Entry<String, Object> formEntry = (Map.Entry<String, Object>) formItr
                        .next();
                String fieldName = formEntry.getKey();
                log.debug("field name => " + fieldName);
                if (fieldName.endsWith("Date")) {

                    /*
                     * SimpleDateFormat outputDateFormat =
                     * DateUtils.getInstanceOf
                     * (domain).getSimpleDateFormatInstanceOf
                     * ("yyyy-MM-dd'T'HH:mm:ss'Z'");
                     * DateFormatSymbols dfs =
                     * outputDateFormat.getDateFormatSymbols();
                     */

                    if (!fieldName.equalsIgnoreCase("BirthDate")) {
                        List l = (List) formParams.get(fieldName);
                        if (l.size() > 0) {
                            String value = (String) l.get(0);
                            log.debug("inside BirthDate. " + value);
                            String afterConversion = getAsString(value);
                            log.debug("afterconversion:" + afterConversion);

                            if (!afterConversion.endsWith("Z")) {
                                log.debug("incoming does not belongs to z format");
                                String solrFormat = dUtils
                                        .getSolrFormattedDateByForm(afterConversion);
                                log.debug("solrFormat:" + solrFormat);
                                List convertBack = convertStringtoList(solrFormat);
                                log.debug("convertBack:" + convertBack);

                                formParams.put(fieldName, convertBack);
                            } else {
                                log.debug("incoming has z format");
                                formParams.put(fieldName, l);
                            }
                        }
                    } /*
                       * else if
                       * (!getAsString(formParams.get("BirthDate")).toString()
                       * .equals("")) {
                       * log.debug("inside else if of date check."+formParams.get
                       * (fieldName));
                       * formParams.put(fieldName, convertStringtoList(dUtils
                       * .getSolrFormattedDateByForm(getAsString(formParams.get(
                       * fieldName)).toString())));
                       * }
                       */
                }

            }
            String[] md5entity = propUtil.get("md5entities").split(",");
            List<String> entityList = Arrays.asList(md5entity);
            log.debug("md5 entity list - " + entityList);
            if (entityList.contains(entityType.toLowerCase())) {
                log.debug("entitylist contains incoming entity is same:"
                        + entityType);
                String md5Text = getAsString(formParams.get("Title"))
                        .toString()
                        + getAsString(formParams.get("Description")).toString();
                log.debug("md5:" + md5Text);
                formParams.put("Md5", trimString(Utils.getInstanceof(domain)
                        .getMD5(md5Text)));
                log.debug("after put md5 :" + formParams);
            }

            log.debug("before createContractBaseInfo: " + formParams);
            formParams = createContractBaseInfo(domain, interfaceInfo,
                    formParams);
            log.debug("after createContractBaseInfo: " + formParams);

            // HashMap<String, Object> entities = new HashMap<String, Object>();
            // entities.put((String)getAsString(formParams.get("EntityType")),
            // formParams);
            //
            //
            // log.debug("before forming collections.");

            // @@@@@@@@@@@@@@@@@@@@@@@@@new code for flatten format support
            // START
            log.debug("before forming collections.");
            String entityName = getAsString(formParams.get("EntityType"));
            log.info("Requested Entity Type : " + entityName);
            HashMap<String, Object> requestEntity = getEntityFlatternMap(
                    domain, entityName, formParams);
            HashMap<String, Object> entities = new HashMap<String, Object>();
            if (requestEntity == null)
                entities.put(entityName, formParams);
            else entities.put(entityName, requestEntity);
            log.info("\n::::::After getEntityFlatternMap(" + domain + ","
                    + entityName + "," + formParams
                    + ") available entity content \n"
                    + entities.get(entityName));
            // @@@@@@@@@@@@@@@@@@@@@@@@@new code for flatten format support END
            HashMap<String, Object> collections = new HashMap<String, Object>();
            collections.put("Collections", entities);

            content.put("content", convertWithCDATA(domain, collections));
            log.debug("Content : " + content.get("content"));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0001", this.getClass()
                    .getSimpleName() + ".getInputContent()", e);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0001", this.getClass()
                    .getSimpleName() + ".getInputContent()", e);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
        return content;
    }

    private HashMap<String, Object> getEntityFlatternMap(String domain,
            String entityName, HashMap<String, Object> entityMap) {
        log.info("getEntityFlatternMap(" + domain + "," + entityName + ","
                + entityMap + ")");
        FormatJson formatJson = FormatJson.getInstanceof(domain);
        String path = propUtil.get("location") + domain + "/resources/"
                + propUtil.get("flattenMapping");
        log.info("Mapping.json available Location :" + path);
        InputStream inputStream;
        HashMap<String, Object> entitiesMapping = null;
        boolean status = false;
        try {
            inputStream = new FileInputStream(path);
            entitiesMapping = formatJson.in(IOUtils.toString(inputStream));
            status = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IOException : " + e.getMessage());
        } catch (GenericException e) {
            e.printStackTrace();
            throw e;
        }
        log.info("Mapping.json available status : " + status);
        if (!status) return entityMap;
        HashMap<String, Object> entityMappingJsonMap = (HashMap<String, Object>) entitiesMapping
                .get(entityName);
        if (entityMappingJsonMap == null) return entityMap;
        log.info("In Mapping.json " + entityName
                + " defination is available...");
        FormatFlatten formatFlatten = FormatFlatten.getInstanceOf(domain);
        log.debug(entityMap + "-----------------" + entityMappingJsonMap
                + "--------------------------------------------------------"
                + entityName);
        HashMap<String, Object> flattenEntityMap = formatFlatten.out(entityMap,
                entityMappingJsonMap);

        if (flattenEntityMap == null) return entityMap;
        log.info(entityName + " content converted as flatten map");
        return flattenEntityMap;
    }

    private String convertWithCDATA(String domain,
            HashMap<String, Object> collections) throws GenericException {
        FormatJson formatJson = FormatJson.getInstanceof(domain);
        InputStream is;
        String inputContent = "";
        try {
            log.methodEntry("convertWithCData entry ");
            log.debug("input : " + collections);
            String path = propUtil.get("location") + domain + "/resources/"
                    + propUtil.get("fieldMapping");
            log.debug("path is:" + path);
            is = new FileInputStream(new File(path));
            HashMap<String, Object> fieldMap = formatJson.in(IOUtils
                    .toString(is));
            log.debug("formatjson.in response fieldMap is : " + fieldMap);
            inputContent = (String) FormatXml.getInstanceof(domain).out(
                    collections, fieldMap);
            log.debug("inputcontent:" + inputContent);
        } catch (FileNotFoundException e) {
            log.error("In FFE: " + e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0001", this.getClass()
                    .getSimpleName() + ".getInputContent()", e);
        } catch (IOException e) {
            log.error("In IOE: " + e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0001", this.getClass()
                    .getSimpleName() + ".getInputContent()", e);
        } catch (GenericException e) {
            log.error("In GE:" + e.getMessage());
            throw e;
        }
        return inputContent;
    }

    /**
     * Gets the resource path.
     * @return the resource path
     */
    private String getResourcePath(String domain) {

        PropertiesUtil proputil = PropertiesUtil.getInstanceof(domain,
                "resource");
        String resourcePath = proputil.getProperty("location") + domain
                + "/resources/";
        if (!resourcePath.endsWith("/")) {
            resourcePath += "/";
        }
        return resourcePath;
    }

    /**
     * Creates the contract base info.
     * @param domain
     *            the domain
     * @param contractInfo
     *            the contract info
     * @param formParams
     *            the form params
     * @return the hash map
     */
    private HashMap<String, Object> createContractBaseInfo(String domain,
            List<String> contractInfo, HashMap<String, Object> formParams) {
        if (contractInfo != null) {
            if (contractInfo.contains("Storable")) {
                formParams.put("Site", convertStringtoList(trimString(domain)));
            }
            if (contractInfo.contains("Seoable")) {
                String seoUrl = getSEOURL(formParams, domain);
                if (seoUrl != null) {
                    formParams.put("SeoUrl",
                            convertStringtoList(trimString(seoUrl)));
                }
            }
        }
        return formParams;
    }

    /**
     * Removes the non entity fields.
     * @param formParams
     *            the form params
     * @param typeMapping
     *            the type mapping
     * @return the hash map
     */
    private HashMap<String, Object> removeNonEntityFields(
            HashMap<String, Object> formParams,
            HashMap<String, Object> typeMapping) {
        log.methodEntry("removeNonEntityFields entry");
        log.debug("formParams:" + formParams);
        Iterator<Entry<String, Object>> itr = formParams.entrySet().iterator();
        HashMap<String, Object> filteredParams = new HashMap<String, Object>();
        while (itr.hasNext()) {
            log.debug("inside while.");
            Map.Entry<String, Object> paramEntry = (Map.Entry<String, Object>) itr
                    .next();
            String fieldName = paramEntry.getKey().toString();
            log.debug("fieldName:" + fieldName);
            if (typeMapping.containsKey(fieldName)) {
                log.debug("typeMapping:::" + typeMapping + "fieldName:"
                        + fieldName);
                // filteredParams.put(fieldName, formParams.get(fieldName));
                // @@@@@@@@@@@@@@@@@@@@@@@@@new code for flatten format support
                // START
                Object data = formParams.get(fieldName);
                if (data instanceof String) {
                    data = convertStringtoList((String) data);
                }
                filteredParams.put(fieldName, data);
                // @@@@@@@@@@@@@@@@@@@@@@@@@new code for flatten format support
                // END

            }
        }
        log.debug("filteredParams:" + filteredParams);
        log.methodExit("removeNonEntityFields exit");
        return filteredParams;
    }

    private List<String> convertStringtoList(String data) {
        List<String> input = new ArrayList<String>();
        input.add(data);
        return input;
    }

    /**
     * Trim string.
     * @param str
     *            the str
     * @return the string
     */
    private String trimString(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                String value = str.trim();
                value = value.replaceAll("\\s{2,}", " ");
                return value;
            }
        }
        return str;
    }

    /**
     * Gets the sEOURL.
     * @param hMapping
     *            the h mapping
     * @param domain
     *            the domain
     * @return the sEOURL
     * @throws GenericException
     *             the generic exception
     */
    private String getSEOURL(Map<String, Object> hMapping, String domain)
            throws GenericException {
        String title = hMapping.get("Title").toString().trim();
        String iaPath = hMapping.get("AssociatedIAPath").toString().trim();
        title = title.replaceAll("[^a-zA-Z0-9 ]+", "");
        String seoUrl = title.trim() + "-"
                + hMapping.get("EntityType").toString().trim() + "-"
                + hMapping.get("Id").toString().trim() + ".php";
        return getSeoUrlWithIaPath(iaPath, domain, seoUrl);
    }

    /**
     * Gets the seo url with ia path.
     * @param path
     *            the path
     * @param domain
     *            the domain
     * @param seoUrl
     *            the seo url
     * @return the seo url with ia path
     */
    private String getSeoUrlWithIaPath(String path, String domain, String seoUrl) {
        String seoUrlWithIaPath = "http://" + domain + "/"
                + seoUrl.replace(" ", "-").toLowerCase();
        if (path != null) {
            String iaPath = getSeoUrlIaPath(path.toLowerCase());
            if (path.equals("") || path.equals("/")) {
                seoUrlWithIaPath = "http://" + domain + "/"
                        + seoUrl.replace(" ", "-").toLowerCase();
            } else {
                seoUrlWithIaPath = "http://" + domain + iaPath + "/"
                        + seoUrl.replace(" ", "-").toLowerCase();
            }
        }
        return seoUrlWithIaPath.replaceAll(" ", "");
    }

    /**
     * Gets the seo url ia path.
     * @param path
     *            the path
     * @return the seo url ia path
     */
    private String getSeoUrlIaPath(String path) {
        if (path.indexOf("/home") == 0) {
            path = path.substring(5, path.length());
        }
        return path;
    }

}
