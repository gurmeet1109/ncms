package in.nic.cmf.dms;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.properties.PropertyManagement;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.util.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.io.IOUtils;

/**
 * Having utility functions for DMS.
 * @author bala
 * @since Feb 8, 2012
 *        dms;
 */
public class DMSUtils {

    /**
     * PropertyManagement object creation and initialisation.
     */
    private PropertyManagement               propUtil     = PropertyManagement
                                                                  .getInstance();
    /**
     * Local constant variable for default file extension.
     */
    private String                           defaultFileExtension;
    /**
     * directorySeparator variable initialised from proputil.
     */

    private String                           directorySeparator;
    /**
     * LogTracer reference.
     */
    private LogTracer                        log;

    /** The Entity types. */
    private Map<String, String>              EntityTypes  = new HashMap<String, String>();

    private String                           domain;

    private static HashMap<String, DMSUtils> hashDMSUtils = new HashMap<String, DMSUtils>();

    private String                           service      = "dms";

    /*
     * Private constructor
     */
    private DMSUtils(String domain) {
        this.domain = domain;
        defaultFileExtension = propUtil.getProperties(domain, service,
                "Configuration", "defaultfileextension").trim();
        directorySeparator = propUtil.getProperties(domain, service,
                "Configuration", "directoryseparator").trim();
    }

    public String getService() {
        return service;
    }

    /**
     * To get singleton Object for DMSUtils.
     * @return DMSUtils
     */
    public static DMSUtils getInstanceof(String domain) {
        if (!hashDMSUtils.containsKey(domain)) {
            hashDMSUtils.put(domain, new DMSUtils(domain));
        }
        return hashDMSUtils.get(domain);
    }

    public String getDefaultFileExtension() {
        return defaultFileExtension;
    }

    public String getDirectorySeparator() {
        return directorySeparator;
    }

    /**
     * Gets the entity types.
     * @return the entity types
     */
    public Map<String, String> getEntityTypes() {
        return EntityTypes;
    }

    /**
     * LogTracer object.
     * @param arg0
     *            the new log tracer {@link in.nic.cmf.logger.LogTracer}
     */
    public final void setLogTracer(final LogTracer arg0) {
        this.log = arg0;
    }

    /**
     * Return Mimetype of given byte array.
     * @param xmlContent
     *            data as a bytearray
     * @return String Mimetype of the data
     * @throws GenericException
     *             String
     */
    public String getMimeType(final byte[] xmlContent) throws GenericException {
        try {
            MagicMatch match = Magic.getMagicMatch(xmlContent);
            return match.getMimeType();
        } catch (MagicParseException e) {
            log.error("getMimeType() throws : " + e.toString());
            throw new GenericException(domain, "ERR-DMS-0003");
        } catch (MagicMatchNotFoundException e) {
            log.error("getMimeType() throws : " + e.toString());
            throw new GenericException(domain, "ERR-DMS-0003");
        } catch (MagicException e) {
            log.error("getMimeType() throws : " + e.toString());
            throw new GenericException(domain, "ERR-DMS-0003");
        }
    }

    /**
     * It will get MimeType details and compare with file extension if both are
     * same returns true, otherwise false.
     * @param id
     *            filename
     * @param data
     *            byte array filecontent
     * @return boolean
     */
    // private boolean validateMimeType(final String id, final byte[] data) {
    //
    // String[] fileid = id.split(Pattern.quote("."));
    // String[] extns = proputil.get("allowedextn").split(",");
    //
    // if ((Arrays.asList(extns)).contains(fileid[1].toLowerCase())) {
    // Utils utils = Utils.getInstance();
    // String extMime = utils.getMimeInfo(getMimeType(data), "simplename")
    // .toLowerCase();
    // fileid[1] = fileid[1].trim().toLowerCase();
    //
    // if (extMime.contentEquals(fileid[1])) {
    // return true;
    // } else {
    // log.error(id + " - Given File Extension / MimeType Mismatch");
    // throw new GenericException(domain,"ERR-DMS-0014");
    // }
    //
    // } else {
    // log.error(id + " - File Extension not allowed");
    // throw new GenericException(domain,"ERR-DMS-0014");
    // }
    // }

    /**
     * Gets the mime type.
     * @param id
     *            the id
     * @param data
     *            the data
     * @return the mime type
     */
    private String getMimeType(final String id, final byte[] data) {

        // String[] extns = proputil.get("allowedextn").split(",");
        String extMime = Utils.getInstanceof(domain)
                .getMimeInfo(getMimeType(data), "simplename").toLowerCase();
        return extMime;
    }

    /**
     * It will remove file name from given path return only directory path.
     * @param path
     *            file path with directory
     * @return the string
     * @throws GenericException
     *             String
     */
    public String splitDirectoryName(final String path) throws GenericException {
        try {
            return path.substring(0, path.lastIndexOf(directorySeparator));
        } catch (Exception e) {
            log.error("splitDirectoryName() throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-DMS-0006");
        }
    }

    /**
     * It will remove Directories path and return only file name.
     * @param path
     *            filename with directories
     * @return String filename
     * @throws GenericException
     *             String
     */
    public String splitFileName(final String path) throws GenericException {
        try {
            return path.substring(path.lastIndexOf(directorySeparator) + 1);
        } catch (Exception e) {
            log.error("splitFileName() throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-DMS-0006");
        }
    }

    /**
     * It will remove Directories path and file extension return only file id.
     * @param filename
     *            filename with/without directories
     * @return String filename
     * @throws GenericException
     *             String
     */
    public String splitId(final String filename) throws GenericException {
        try {
            String ext = splitFileExtension(filename).trim();

            if (ext.equalsIgnoreCase("xml")) {
                return filename.substring(0, filename.lastIndexOf("."));
            } else {
                return filename;
            }
        } catch (Exception e) {
            log.error("splitFileId() throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-DMS-0006");
        }
    }

    /**
     * It will split filename and return file extension.
     * @param filename
     *            filename with directories
     * @return String filename
     * @throws GenericException
     *             String
     */
    public String splitFileExtension(final String filename)
            throws GenericException {

        try {
            if (filename.contains(".")) {
                return filename.substring(filename.lastIndexOf('.') + 1);
            }
        } catch (Exception e) {
            log.error("splitFileExtension() throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-DMS-0006");
        }
        return null;
    }

    /**
     * create directory names based on given filename(ID).
     * @param id
     *            filename
     * @return String filePath
     * @throws GenericException
     *             String
     */
    public String getDirectoryPath(final String id) throws GenericException {
        // Ex: lhom7xcaiciga ==> [0,1 :l] ; [1,3 :ho] ; [3,5 :m7] inside actual
        // xml will available.
        try {
            return id.substring(0, 1) + directorySeparator + id.substring(1, 3)
                    + directorySeparator + id.substring(3, 5);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("getDirectoryPath() throws : " + e.getMessage());
            throw new GenericException(domain, "ERR-DMS-0006");
        }
    }

    /**
     * convert collectionXML HashMap object to byte array.
     * @param xmlContent
     *            collectionXML as HashMap object
     * @return byte[]
     */
    public byte[] getXmlContent(final HashMap<String, Object> xmlContent) {
        String returnXmlContent = (String) FormatXml.getInstanceof(domain).out(
                (HashMap<String, Object>) xmlContent,
                (HashMap<String, Object>) getFieldNameMapping());
        return returnXmlContent.getBytes();
    }

    /**
     * It will return <id> value from given CollectionXML.
     * @param inMap
     *            CollectionXML as HashMap
     * @return String <id> value
     */
    public String getId(final HashMap<String, Object> inMap) {

        if (inMap.containsKey("id")) {
            return inMap.get("id").toString();
        } else if (inMap.containsKey("Id")) {
            return inMap.get("Id").toString();
        } else if (inMap.containsKey("iD")) {
            return inMap.get("iD").toString();
        } else if (inMap.containsKey("ID")) {
            return inMap.get("ID").toString();
        } else {
            return null;
        }
    }

    /**
     * it will return <EntityType> value from given collectionXML.
     * @param inMap
     *            collectionXML as HashMap
     * @return String
     */
    public String getEntityName(final HashMap<String, Object> inMap) {
        if (inMap.containsKey("EntityType")) {
            return (String) inMap.get("EntityType");
        }
        return null;
    }

    /**
     * This method will compare Collection XML tags to FieldNameMapping.json if
     * any unmentioned tags are appeared in
     * CollectionXML same will be not stored.
     * @return HashMap of collectionXML
     * @throws GenericException
     *             HashMap<String,Object>
     */
    public HashMap<String, Object> getFieldNameMapping()
            throws GenericException {
        try {
            InputStream is = new FileInputStream(getResourcePath()
                    + "FieldNameMapping.json");
            HashMap<String, Object> outputMap = FormatJson
                    .getInstanceof(domain).in(IOUtils.toString(is));
            outputMap.put("fieldNameMapping",
                    (HashMap<String, Object>) outputMap
                            .get("dmsfieldNameMapping"));

            return outputMap;
        } catch (IOException e) {
            log.error("getFieldNameMapping() throws : " + e);
            throw new GenericException(domain, "ERR-SE-0016");
        }
    }

    /**
     * it will return Path of FieldNameMapping.json resource.
     * @return String
     */
    private String getResourcePath() {
        PropertiesUtil proputil = PropertiesUtil.getInstanceof(domain,
                "resources");
        String resourcePath = proputil.getProperty("location") + domain
                + "/resources/";
        if (!resourcePath.endsWith(directorySeparator)) {
            resourcePath += directorySeparator;
        }
        return resourcePath;
    }

    /**
     * It will check given filename(id) extension based on MimeType. if
     * extension is not available append default file extension
     * @param id
     *            given filename
     * @param data
     *            byte array of file
     * @param isCollectionXML
     *            the is collection xml
     * @return String file with extension and directory.
     */
    // public String getFileNameWithExtension(String id, final byte[] data,
    // boolean isCollectionXML) {
    //
    // if (isCollectionXML) {
    // if (id.contains(".")) {
    // validateMimeType(id, data);
    // } else {
    // id = id + defaultFileExtension;
    // }
    // } else {
    // if (id.contains(".")) {
    // validateMimeType(id, data);
    // } else {
    // String ext = getMimeType(id, data);
    // log.debug("Mime: " + ext);
    // id = id + "." + ext;
    // validateMimeType(id, data);
    // }
    //
    // }
    // return id;
    //
    // }

    public String getFileNameWithExtension(String id, final byte[] data,
            boolean isCollectionXML) {
        if (isCollectionXML) {
            if (!id.contains(".")) {
                id = id + defaultFileExtension;
            }
        } else {
            if (!id.contains(".")) {
                String ext = getMimeType(id, data);
                // log.debug("Mime: " + ext);
                id = id + "." + ext;
            }
        }
        return id;

    }

    /**
     * It will check given filename(id) extension if not avilable add
     * DEFAULT_EXTENSION.
     * @param id
     *            given filename
     * @return String file with extension.
     */
    public String getFileNameWithExtension(String id) {

        if (id.contains(".")) {
            return id;
        } else {
            return id + defaultFileExtension;
        }
    }

    /**
     * it will splitCollectionXML as HashMap or ArrayList entity and add each
     * entity into return HashMap.
     * @param collection
     *            CollectionXML
     * @return HashMap<String,Object>
     */
    public Map<String, Object> splitCollectionXML(
            final Map<String, Object> collection) {

        HashMap<String, Object> files = new HashMap<String, Object>();

        for (Entry<String, Object> entry : collection.entrySet()) {
            if (!entry.getKey().equals("files")) {
                if (entry.getValue() instanceof HashMap) {

                    HashMap<String, Object> content = (HashMap<String, Object>) entry
                            .getValue();
                    String id = getId(content);
                    String entityname = getEntityName(content);
                    HashMap<String, Object> entityType = new HashMap<String, Object>();
                    entityType.put(entityname, content);
                    files.put(id, entityType);
                    EntityTypes.put(id, entityname);
                } else if (entry.getValue() instanceof ArrayList) {

                    for (HashMap<String, Object> entity : (ArrayList<HashMap<String, Object>>) entry
                            .getValue()) {
                        String id = getId(entity);
                        String entityname = getEntityName(entity);
                        HashMap<String, Object> entityType = new HashMap<String, Object>();
                        entityType.put(entityname, entity);
                        files.put(id, entityType);
                        EntityTypes.put(id, entityname);
                    }
                }
            }
        }
        return files;
    }

    /**
     * it will splitCollectionXML as HashMap or ArrayList entity and add each
     * entity ID are returned as String (Eg: id1,id2,id3...)
     * @param collection
     *            CollectionXML
     * @return HashMap<String,Object>
     */
    public String splitCollectionXMLGetId(final Map<String, Object> collection) {

        HashMap<String, Object> files = new HashMap<String, Object>();
        List<String> collectionID = new ArrayList<String>();

        for (Entry<String, Object> entry : collection.entrySet()) {
            if (entry.getValue() instanceof HashMap) {
                HashMap<String, Object> content = (HashMap<String, Object>) entry
                        .getValue();

                collectionID.add(getId(content));
            } else if (entry.getValue() instanceof ArrayList) {

                for (HashMap<String, Object> entity : (ArrayList<HashMap<String, Object>>) entry
                        .getValue()) {
                    collectionID.add(getId(entity));
                }
            }
        }
        String[] simpleArray = new String[collectionID.size()];
        simpleArray = collectionID.toArray(simpleArray);
        String output = implodeArray(simpleArray, ",");
        return output;
    }

    /**
     * it will convert given HashMap files to byte array HashMap.
     * @param files
     *            input binary/CollectionXML files
     * @param isCollectionXML
     *            if files are collectionXMl means it's true, otherwise false
     * @return Map<String,byte[]>
     */
    public Map<String, byte[]> getBytesofMap(final Map<String, Object> files,
            final boolean isCollectionXML) {

        Map<String, byte[]> outputMap = new HashMap<String, byte[]>();
        byte[] data = null;
        for (String key : files.keySet()) {
            HashMap<String, Object> xmldata = (HashMap<String, Object>) files
                    .get(key);
            if (isCollectionXML) {
                data = getXmlContent(xmldata);
            } else {
                data = (byte[]) files.get(xmldata);
            }

            // String fileName = DMSUTILS.getFileNameWithExtension(key, data,
            // isCollectionXML);
            outputMap.put(key, data);
        }
        return outputMap;
    }

    /**
     * split comma separated String values into ArraList.
     * @param id
     *            input string comma separated values
     * @return List<String>
     */
    public List<String> getEntityIds(final String id) {
        return Arrays.asList(id.split(","));
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
     * Method to join array elements of type string.
     * @param inputArray
     *            Array which contains strings
     * @param glueString
     *            String between each array element
     * @return String containing all array elements separated by glue string
     */
    public static String implodeArray(final String[] inputArray,
            final String glueString) {

        /** Output variable */
        String output = "";

        if (inputArray.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(inputArray[0]);

            for (int i = 1; i < inputArray.length; i++) {
                sb.append(glueString);
                sb.append(inputArray[i]);
            }

            output = sb.toString();
        }

        return output;
    }

    public byte[] downloadFile(String domain,
            final Map<String, Map<String, Object>> parameters, String URL) {

        log.debug("=====================================" + parameters);

        Map<String, Map<String, Object>> reqParameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("dataurl", URL);
        input.put("userContext", parameters.get("input").get("userContext"));
        input.put("requestId", parameters.get("input").get("requestId"));
        reqParameters.put("input", input);

        log.debug("-----------1");
        ServiceClientImpl client = ServiceClientImpl.getInstance(domain,
                "media");
        log.debug("PARAMETERS::::" + reqParameters);
        Map<String, Map<String, Object>> response = client.findById(domain,
                "media", "", reqParameters);
        byte[] data = (byte[]) response.get("output").get("content");
        return data;
    }
}
