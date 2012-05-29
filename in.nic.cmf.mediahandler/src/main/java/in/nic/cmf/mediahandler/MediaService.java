package in.nic.cmf.mediahandler;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaService {
    // private static final MediaService mHelper = new MediaService();
    /** The log. */
    private LogTracer                            log;

    /** The prop util. */
    private PropertiesUtil                       propUtil;
    /** The utils. */
    private Utils                                utils;
    private ConvertorUtils                       cu;

    private static HashMap<String, MediaService> hashMediaService = new HashMap<String, MediaService>();
    private String                               domain;

    private MediaService(String domain) {
        this.domain = domain;
        propUtil = PropertiesUtil.getInstanceof(domain, "mediahandler");
        utils = Utils.getInstanceof(domain);
        cu = ConvertorUtils.getInstanceof(domain);
    }

    /**
     * getting instance of MediaService.
     * @return MediaService
     */
    public static MediaService getInstanceof(String domain) {
        if (!hashMediaService.containsKey(domain)) {
            hashMediaService.put(domain, new MediaService(domain));
        }
        return hashMediaService.get(domain);
    }

    public void setLog(LogTracer log) {
        this.log = log;
    }

    private Map<String, Object> buildRes(HashMap<String, Object> globalMap) {

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("header", new HashMap<String, Object>());

        // included to return it in xml
        String xml = (String) FormatXml.getInstanceof(domain).out(
                (HashMap<String, Object>) globalMap);
        // log.debug("xml:" + xml);

        output.put("content", xml);
        // ((HashMap<String, Object>) output.get("content")).putAll(globalMap);
        // output.putAll(globalMap);
        ((HashMap<String, Object>) output.get("header")).put("Content-Type",
                "application/xml");
        output.put("statusCode", "200");
        return output;
    }

    public Map<String, Map<String, Object>> processRequest(String domain,
            String entity, Map<String, Map<String, Object>> parameters) {

        /*
         * if (!parameters.get("input").containsKey("files")) {
         * return parameters;
         * }
         */
        List<Map<String, Object>> binaryContent = new ArrayList<Map<String, Object>>();
        log.debug("Parameters:::" + parameters);
        HashMap<String, Object> content = FormatXml.getInstanceof(domain).in(
                (String) parameters.get("input").get("content"));
        HashMap<String, Object> collections = (HashMap<String, Object>) content
                .get("Collections");
        if (parameters.get("input").containsKey("files")) {

            binaryContent = (List<Map<String, Object>>) parameters.get("input")
                    .get("files");
        } else {
            if (collections.get("files") instanceof HashMap) {
                binaryContent.add((Map<String, Object>) collections
                        .get("files"));
            } else if (collections.get("files") instanceof List) {
                binaryContent = (List<Map<String, Object>>) collections
                        .get("files");
            }

        }

        // log.debug("Content:::::" + content);
        MediaXmlParser mediaxmlparser = MediaXmlParser.getInstance(domain);
        mediaxmlparser.setLogger(log);

        HashMap<String, Object> sourceMedia = new HashMap<String, Object>();
        // HashMap<String, String> formparams = new HashMap<String, String>();

        for (Map.Entry eachentity : collections.entrySet()) {
            byte[] decodedStr = null;
            if (!eachentity.getKey().equals("files")) {

                for (Map<String, Object> eachBinary : binaryContent) {
                    int reqCount = 0;

                    // for (Map.Entry<String, Object> eachBinary : binarycontent
                    // .entrySet()) {
                    // log.debug("Key for Binary :::::" + eachBinary.get("id"));
                    HashMap<String, String> formparams = (HashMap<String, String>) eachentity
                            .getValue();

                    // collections.entrySet();
                    formparams.put("SourceName", (String) eachBinary.get("Id"));

                    try {

                        if (eachBinary.get("Data") instanceof String) {
                            decodedStr = new sun.misc.BASE64Decoder()
                                    .decodeBuffer((String) eachBinary
                                            .get("Data"));
                        } else if (eachBinary.get("Data") instanceof byte[]) {

                            log.debug("instance of byte array.");
                            decodedStr = (byte[]) eachBinary.get("Data");
                        }
                    } catch (IOException e) {
                        log.error("Unable to decode ");
                    }
                    // else {
                    // formparams.putAll((HashMap<String, String>) eachentity
                    // .getValue());
                    // }

                    log.debug("formparams::::" + formparams);
                    sourceMedia = mediaxmlparser.frameMediaEntity(domain,
                            reqCount, formparams, decodedStr,
                            (HashMap<String, String>) parameters.get("input")
                                    .get("userContext"));
                    reqCount++;
                }
            }
        }
        content.put("Collections", sourceMedia);

        parameters.put("output", buildRes(content));
        parameters.get("input").remove("files");
        // log.debug("Out put from Media Handler:::" + parameters);

        return parameters;

    }

    public Map<String, Map<String, Object>> findById(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("findById entry");

        String entityId = id;
        log.debug("ID:" + entityId);
        if (isEmpty(entityId)
                && !parameters.get("input").containsKey("dataurl")) {
            throw new GenericException(domain, "ERR-MH-0005");
        }
        if (isEmpty(entityId) && parameters.get("input").containsKey("dataurl")) {
            String dataurl = (String) parameters.get("input").get("dataurl");
            if (!isEmpty(dataurl)) {
                entityId = dataurl.substring(dataurl.lastIndexOf("/"),
                        dataurl.length());
            }
        }
        Map<String, byte[]> responses = new HashMap<String, byte[]>();
        List<String> ids = Arrays.asList(entityId.split(","));

        responses = readBinayFiles(domain, ids);

        log.debug("response:" + responses);
        log.methodExit("findById exit:" + responses);
        return getBuildResponseMap(domain, entity, responses, parameters);
    }

    private Map<String, byte[]> readBinayFiles(String domain,
            List<String> fileNames) {

        Map<String, byte[]> output = new HashMap<String, byte[]>();

        for (String filename : fileNames) {
            byte[] filedata = readBinayFiles(domain, filename);
            if (filedata != null) {
                output.put(filename, filedata);
            } else {
                log.debug("Media get FileNotFound: " + filename);
            }
        }
        return output;

    }

    private byte[] readBinayFiles(String domain, String fileName) {
        String filepath = propUtil.get("binaryfilelocation") + domain + "/"
                + fileName;
        File file = new File(filepath);
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            log.error("ERR-MH-0006:Binary Storage FileNotFoundException throws:"
                    + e);
            // throw new GenericException(domain,"ERR-DMS-0004");
        } catch (IOException e) {
            log.error("ERR-DMS-0002:Binary Storage IOException throws:" + e);
            // throw new GenericException(domain,"ERR-DMS-0002");
        }
        return b;
    }

    /**
     * returns true if it's empty, otherwise returns false.
     * @param data
     *            Object the data
     * @return boolean
     */
    public boolean isEmpty(final Object data) {

        if (data == null) {
            return true;
        }
        if (data instanceof String) {
            return (data.toString().trim()).isEmpty();
        }
        return false;
    }

    /**
     * This will build response and add that to parameters output content get
     * @param domain
     *            domain name
     * @param fileResponses
     *            response from Storage Service
     * @param parameters
     *            parameters
     * @return HashMap<String,HashMap<String,Object>>
     */
    private Map<String, Map<String, Object>> getBuildResponseMap(
            final String domain, final String entity,
            final Map<String, ?> fileResponses,
            final Map<String, Map<String, Object>> parameters) {

        log.methodEntry("getBuildResponseMap entry:" + parameters);

        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");
        HashMap<String, Object> content = new HashMap<String, Object>();
        List<HashMap<String, Object>> multibinary = new ArrayList<HashMap<String, Object>>();

        if (fileResponses.isEmpty()) {
            throw new GenericException(domain, "ERR-MH-0005");
        }

        for (String key : fileResponses.keySet()) {

            String fileid = key;

            byte[] filedata = (byte[]) fileResponses.get(key);
            if (fileResponses.size() == 1) {
                // single binary read
                output.put("content", filedata);
                HashMap<String, String> headers = new HashMap<String, String>();
                String contentType = utils.getMimeType(
                        new ByteArrayInputStream(filedata), key);
                // String simpleName = utils
                // .getMimeInfo(contentType, "simplename");
                headers.put("Content-Type", contentType);
                output.put("headers", headers);
                log.methodExit("in between return:" + parameters);
                return parameters;
            } else {
                HashMap<String, Object> res = new HashMap<String, Object>();
                res.put("files", new HashMap<String, Object>());
                String encodeStr = new sun.misc.BASE64Encoder()
                        .encode(filedata);
                ((HashMap<String, Object>) res.get("files")).put("Id", fileid);
                ((HashMap<String, Object>) res.get("files")).put("Data",
                        encodeStr);
                multibinary.add(res);
            }
        }

        content.put("Collections", multibinary);
        // log.debug("collections:" + content);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        // log.methodExit("getBuildResponseMap exit:" + parameters);
        return parameters;
    }// function
}
