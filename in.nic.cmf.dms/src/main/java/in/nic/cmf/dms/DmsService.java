package in.nic.cmf.dms;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.dms.contract.StorageProvider;
import in.nic.cmf.dms.helper.StorageProviderFactory;
import in.nic.cmf.dms.helper.StorageTypeDeterminer;
import in.nic.cmf.dms.helper.SvnStorage;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DmsService is a service class for @link DmsServiceImpl.
 * @author bala
 * @since Jan 31, 2012
 *        dms;
 */
public class DmsService {

    /**
     * LogTracer object creation and initialisation.
     */
    private LogTracer                          log;

    /**
     * DMSUtils object creation and initialisation.
     */
    private DMSUtils                           dmsutils;
    /**
     * StorageTypeDeterminer object creation and initialisation.
     */
    private StorageTypeDeterminer              storageTypeDeterminer;
    /**
     * StorageTypeDeterminer object creation and initialisation.
     */
    private StorageProviderFactory             storageProviderFactory;

    private static HashMap<String, DmsService> hashDmsService = new HashMap<String, DmsService>();

    /**
     * Private constructor.
     */
    private DmsService(String domain) {
        dmsutils = DMSUtils.getInstanceof(domain);
        storageTypeDeterminer = StorageTypeDeterminer.getInstance();
        storageProviderFactory = StorageProviderFactory.getInstance();
    }

    /**
     * This method initialise LogTracer log.
     * @param log
     *            LogTracer object
     */
    public void setLogger(final LogTracer log) {
        this.log = log;
        dmsutils.setLogTracer(log);
        storageTypeDeterminer.setLogTracer(log);
        storageProviderFactory.setLogTracer(log);
    }

    /**
     * getting instance of DmsService.
     * @return DmsService
     */
    public static DmsService getInstanceof(String domain) {
        if (!hashDmsService.containsKey(domain)) {
            hashDmsService.put(domain, new DmsService(domain));
        }
        return hashDmsService.get(domain);
    }

    /**
     * This will process parameters HashMap and parse input files and
     * collections XML
     * and stored into storage based on domain.
     * @param domain
     *            domain name
     * @param entity
     *            entity
     * @param parameters
     *            parameters
     * @return HashMap<String,HashMap<String,Object>>
     */
    public Map<String, Map<String, Object>> add(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {

        log.methodEntry("add entry");

        log.debug("entity:" + entity);
        Map<String, Boolean> responses = new HashMap<String, Boolean>();
        Map<String, byte[]> inputfiles = validateAddParameters(domain,
                parameters);
        StorageProvider sp = storageProviderFactory.getStorageProvider(domain,
                storageTypeDeterminer.getStorage(domain));
        log.debug("inputfiles:" + inputfiles);
        responses = sp.put(domain, inputfiles);

        log.methodExit("add exit");
        return putBuildResponseMap(domain, entity, responses, parameters);
    }

    public Map<String, Map<String, Object>> read(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("read entry");

        Map<String, Object> inputCollection = getCollectionsXML(domain,
                parameters);
        if (inputCollection.containsKey("Count")
                && inputCollection.get("Count") != null
                && inputCollection.get("Count").equals("0")) {
            return parameters;
        }
        String id = dmsutils.splitCollectionXMLGetId(inputCollection);
        log.methodExit("read exit");
        return findById(domain, entity, id, parameters);
    }

    /**
     * This will process id and retrieve all id(files) data from storage based
     * on domain.
     * @param domain
     *            domain name
     * @param entity
     *            entity type id
     * @param id
     *            single/list of file id's
     * @param parameters
     *            parameters
     * @return HashMap<String,HashMap<String,Object>>
     */
    public Map<String, Map<String, Object>> findById(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("findById entry");
        log.debug("ID:" + id);
        if (dmsutils.isEmpty(id)) {
            throw new GenericException(domain, "ERR-DMS-0004");
        }
        Map<String, byte[]> responses = new HashMap<String, byte[]>();
        StorageProvider sp = storageProviderFactory.getStorageProvider(domain,
                storageTypeDeterminer.getStorage(domain));

        List<String> ids = dmsutils.getEntityIds(id);

        byte[] data = getRevisionFile(sp, domain, ids, parameters);
        if (data != null) {
            responses.put(id, data);
        } else {
            responses = sp.get(domain, ids);
        }

        log.debug("response:" + responses);
        log.methodExit("findById exit:" + responses);
        return getBuildResponseMap(domain, entity, responses, parameters);
    }

    /**
     * Reading a file based on given revision no in SVN storage. if requested
     * revision not available, Exception will thrown
     * @param sp
     *            storage provider
     * @param domain
     *            domain name
     * @param ids
     *            filenames
     * @param parameters
     *            parameters
     * @return byte[] file content
     */
    private byte[] getRevisionFile(final StorageProvider sp,
            final String domain, final List<String> ids,
            final Map<String, Map<String, Object>> parameters) {

        if ((ids.size() == 1) && (sp instanceof SvnStorage)
                && (parameters.get("input") != null)
                && (parameters.get("input").get("queryParams") != null)) {

            HashMap<String, String> queryParams = (HashMap<String, String>) parameters
                    .get("input").get("queryParams");
            String revision = (String) queryParams.get("r");
            if (revision != null) {
                return sp.get(domain, ids.get(0), revision);
            }
        }
        return null;
    }

    /**
     * This will process id and delete all id(files)'s data from storage based
     * on domain.
     * @param domain
     *            domain name
     * @param entity
     *            entity type id
     * @param id
     *            single/list of file id's
     * @param parameters
     *            parameters
     * @return HashMap<String,HashMap<String,Object>>
     */
    public Map<String, Map<String, Object>> deleteByID(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("deleteByID entry");
        if (dmsutils.isEmpty(id)) {
            throw new GenericException(domain, "ERR-DMS-0004");
        }

        Map<String, Boolean> responses = new HashMap<String, Boolean>();

        StorageProvider sp = storageProviderFactory.getStorageProvider(domain,
                storageTypeDeterminer.getStorage(domain));
        responses = sp.delete(domain, dmsutils.getEntityIds(id));
        log.methodExit("deleteByID exit");
        return deleteBuildResponseMap(domain, entity, responses, parameters);
    }

    /**
     * this will return Collections HashMap which will retrieve from parameters.
     * @param parameters
     *            parameters
     * @return HashMap<String,Object>
     */
    private Map<String, Object> getCollectionsXML(String domain,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("getCollectionsXML entry");
        String content = (String) parameters.get("input").get("content");
        log.debug("input.content:" + content);
        if (dmsutils.isEmpty(content)) {
            return null;
            // throw new GenericException(domain,"ERR-DMS-0007");
        }

        Map<String, Object> formatXml = FormatXml.getInstanceof(domain).in(
                content);
        Map<String, Object> collection = (HashMap<String, Object>) formatXml
                .get("Collections");
        log.debug("input.content.Collection:" + collection);
        if (dmsutils.isEmpty(collection)) {
            return null;
            // throw new GenericException(domain,"ERR-DMS -0007");
        }
        log.methodExit("getCollectionsXML exit");
        return collection;
    }

    /**
     * @param parameters
     *            parameters
     * @return HashMap<String,byte[]> byte[] data for binary and XML
     */
    private Map<String, byte[]> validateAddParameters(String domain,
            final Map<String, Map<String, Object>> parameters) {
        log.methodEntry("validateAddParameters entry");
        log.debug("parameters:" + parameters);
        Map<String, byte[]> byteFiles = new HashMap<String, byte[]>();

        Map<String, Object> inputCollection = getCollectionsXML(domain,
                parameters);

        // add binary files from collections

        if (parameters.get("input").get("files") != null) {

            Map<String, byte[]> binaryfiles = (HashMap<String, byte[]>) parameters
                    .get("input").get("files");
            // binary files from input.files direct component post
            // input.files[13digit:byte]
            if (binaryfiles.size() > 0) {
                byteFiles.putAll(binaryfiles);
            }
        }

        byteFiles.putAll(getBinaryFromCollections(domain, parameters));

        log.debug("binary: " + byteFiles);

        if (inputCollection == null && byteFiles == null
                && byteFiles.size() == 0) {
            log.debug("input.files: " + byteFiles);
            log.debug("input.content: " + inputCollection);
            throw new GenericException(domain, "ERR-DMS -0007");
        }

        if (inputCollection != null) {
            Map<String, Object> xmlcontent = dmsutils
                    .splitCollectionXML(inputCollection);
            System.out.println(xmlcontent);
            byteFiles.putAll(dmsutils.getBytesofMap(xmlcontent, true));
        }
        log.methodExit("validateAddParameters exit");
        return byteFiles;
    }

    private Map<String, byte[]> getBinaryFromCollections(String domain,
            final Map<String, Map<String, Object>> parameters) {

        /****************** Search binary files url in XML */
        Map<String, byte[]> output = new HashMap<String, byte[]>();
        String content = (String) parameters.get("input").get("content");
        Map<String, Object> formatXml = FormatXml.getInstanceof(domain).in(
                content);
        Map<String, Object> collection = (HashMap<String, Object>) formatXml
                .get("Collections");

        ConvertorUtils cu = ConvertorUtils.getInstanceof(domain);
        log.debug("before checking fiels:" + collection);
        if (collection.containsKey("files")) {

            Object onlyFiles = collection.get("files");
            log.debug("received in object");

            List<HashMap<String, Object>> hashfiles = new ArrayList<HashMap<String, Object>>();

            if (cu.isArrayList(onlyFiles)) {

                log.debug("inside isArrayList");
                for (HashMap<String, Object> eachmap : (List<HashMap<String, Object>>) onlyFiles) {
                    HashMap<String, Object> eachHash = new HashMap<String, Object>();
                    log.debug("id:" + eachHash);
                    String id = (String) eachmap.get("Id");
                    if (eachmap.get("Dataurl") != null) {
                        String url = (String) eachmap.get("Dataurl");
                        output.put(id,
                                dmsutils.downloadFile(domain, parameters, url));
                    }
                    if (eachmap.get("Data") != null) {
                        String original = (String) eachmap.get("Data");
                        byte[] decodedStr;
                        try {
                            decodedStr = new sun.misc.BASE64Decoder()
                                    .decodeBuffer(original);
                        } catch (IOException e) {
                            throw new GenericException(domain, "ERR-DMS-0020",
                                    e);
                        }
                        output.put(id, decodedStr);
                    }
                }

            } else if (cu.isHashMap(onlyFiles)) {

                log.debug("inside hashmap1");
                HashMap<String, Object> eachmap = (HashMap<String, Object>) onlyFiles;
                String id = (String) eachmap.get("Id");
                String url = (String) eachmap.get("Dataurl");
                output.put(id, dmsutils.downloadFile(domain, parameters, url));
            }
        }
        return output;
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
        String inputContent = (String) parameters.get("input").get("content");
        log.debug("content" + inputContent);
        String count = "0";
        if (inputContent != null && !inputContent.equals("")) {
            FormatXml fxml = FormatXml.getInstanceof(domain);
            HashMap<String, Object> xmlMap = fxml.in(inputContent);
            log.debug("xmlMap:" + xmlMap);
            xmlMap = (HashMap<String, Object>) xmlMap.get("Collections");
            log.debug("after inpoutcontent:" + xmlMap);
            count = (String) xmlMap.get("Count");
        }

        log.debug("count is: " + count);
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");
        HashMap<String, Object> content = new HashMap<String, Object>();
        HashMap<String, Object> multibinary = new HashMap<String, Object>();
        List<HashMap<String, Object>> multiXML = new ArrayList<HashMap<String, Object>>();

        if (fileResponses.isEmpty()) {
            throw new GenericException(domain, "ERR-DMS-0004");
        }

        for (String key : fileResponses.keySet()) {

            String fileid = key;

            byte[] filedata = (byte[]) fileResponses.get(key);
            if (fileResponses.size() == 1) {
                count = "1";
                if (dmsutils.splitFileExtension(fileid) == null
                        || dmsutils.splitFileExtension(fileid)
                                .equalsIgnoreCase("xml")) {
                    // Single XML read
                    String fileDataString = new String(filedata);
                    HashMap<String, Object> fileData = FormatXml.getInstanceof(
                            domain).in(fileDataString);
                    log.debug("fileData:" + fileData);
                    fileData.put("Count", count);
                    log.debug("after fileData:" + fileData);

                    content.put("Collections", fileData);
                    log.debug("data:" + fileid + fileData);
                    output.put("content",
                            FormatXml.getInstanceof(domain).out(content));
                } else {
                    // single binary read
                    output.put("content", filedata);
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", dmsutils.getMimeType(filedata));
                    output.put("headers", headers);
                }

                log.methodExit("in between return:" + parameters);
                return parameters;
            } else {

                if (dmsutils.splitFileExtension(fileid) == null
                        || dmsutils.splitFileExtension(fileid)
                                .equalsIgnoreCase("xml")) {
                    String fileDataString = new String(filedata);
                    HashMap<String, Object> fileData = FormatXml.getInstanceof(
                            domain).in(fileDataString);
                    log.debug("fileData:" + fileData);
                    log.debug("data:" + fileid + fileData);
                    multiXML.add(fileData);
                } else {
                    multibinary.put("file", new HashMap<String, Object>());
                    ((HashMap<String, Object>) multibinary.get("file")).put(
                            "id", fileid);
                    String encodeStr = new sun.misc.BASE64Encoder()
                            .encode(filedata);
                    ((HashMap<String, Object>) multibinary.get("file")).put(
                            "data", encodeStr);
                }
            }
        }

        if (multibinary != null && multibinary.size() > 0) {
            // Collection + binary
            multiXML.add(multibinary);
        }

        log.debug("before inserting count to list:" + count);
        HashMap counts = new HashMap();
        counts.put("Count", count);
        multiXML.add(counts);

        content.put("Collections", multiXML);

        log.debug("collections:" + content);

        output.put("content", FormatXml.getInstanceof(domain).out(content));

        log.methodExit("getBuildResponseMap exit:" + parameters);
        return parameters;

    }// function

    /**
     * This will build response and add that to parameters output content for
     * add.
     * @param domain
     *            domain name
     * @param fileResponses
     *            response from Storage Service
     * @param parameters
     *            parameters
     * @return HashMap<String,HashMap<String,Object>>
     */
    // PUT
    private Map<String, Map<String, Object>> putBuildResponseMap(
            final String domain, String entity,
            final Map<String, ?> fileResponses,
            final Map<String, Map<String, Object>> parameters) {

        log.methodEntry("putBuildResponseMap entry:" + domain + "-" + entity
                + "-" + fileResponses + "-" + parameters);
        parameters.put("output", new HashMap<String, Object>());

        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");

        HashMap<String, Object> content = new HashMap<String, Object>();

        // HashMap<String, Object> fileResponse = new HashMap<String, Object>();
        ArrayList<HashMap<String, Object>> responses = new ArrayList<HashMap<String, Object>>();
        // responses.add(fileResponse);
        for (String key : fileResponses.keySet()) {
            HashMap<String, Object> res = new HashMap<String, Object>();
            Map<String, String> entitytypes = dmsutils.getEntityTypes();
            String tag = "file";
            if (entitytypes.containsKey(key)) {
                tag = entitytypes.get(key);
            }
            res.put(tag, new HashMap<String, Object>());
            ((HashMap<String, Object>) res.get(tag)).put("id", key);
            /*
             * ((HashMap<String, Object>) res.get(tag)).put("status",
             * fileResponses.get(key).toString());
             */
            ((HashMap<String, Object>) res.get(tag)).put("success",
                    fileResponses.get(key).toString());
            responses.add(res);
        }

        // HashMap<String, Object> finalMap = new HashMap<String, Object>();
        // finalMap.put(entity, responses);
        // content.put("Collections", finalMap);
        content.put("Collections", responses);
        log.debug("content is : " + content);
        try {
            output.put("content", FormatXml.getInstanceof(domain).out(content));
        } catch (Exception ex) {
            log.error("In exception :" + ex.getMessage());
        }
        log.methodExit("putBuildResponseMap exit");
        return parameters;
    }

    /**
     * This will build response and add that to parameters output content for
     * delete
     * add.
     * @param domain
     *            domain name
     * @param fileResponses
     *            response from Storage Service
     * @param parameters
     *            parameters
     * @return HashMap<String,HashMap<String,Object>>
     */
    // PUT
    private Map<String, Map<String, Object>> deleteBuildResponseMap(
            final String domain, final String entity,
            final Map<String, ?> fileResponses,
            final Map<String, Map<String, Object>> parameters) {

        log.methodEntry("deleteBuildResponseMap entry");
        parameters.put("output", new HashMap<String, Object>());

        HashMap<String, Object> output = (HashMap<String, Object>) parameters
                .get("output");
        output.put("statusCode", "200");

        HashMap<String, Object> content = new HashMap<String, Object>();

        ArrayList<HashMap<String, Object>> responses = new ArrayList<HashMap<String, Object>>();
        // responses.add(fileResponse);
        for (String key : fileResponses.keySet()) {
            HashMap<String, Object> res = new HashMap<String, Object>();

            res.put("file", new HashMap<String, Object>());
            ((HashMap<String, Object>) res.get("file")).put("id", key);
            ((HashMap<String, Object>) res.get("file")).put("success",
                    fileResponses.get(key).toString());
            responses.add(res);
        }
        content.put("Collections", responses);
        output.put("content", FormatXml.getInstanceof(domain).out(content));
        log.methodExit("deleteBuildResponseMap exit");
        return parameters;

    }
}
