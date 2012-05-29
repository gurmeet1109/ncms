package in.nic.cmf.helper;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.Utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;

/**
 * The Class MediaXmlParser.
 */
public class MediaXmlParser {

    /** The utils. */
    // private Utils utils = Utils.getInstanceof();

    /** The allowed file extns. */
    private List<String>   allowedFileExtns = new ArrayList<String>();

    /** The propUtil. */
    private PropertiesUtil propUtil;

    /** The log. */
    private LogTracer      log;

    /**
     * Sets the logger.
     * @param log
     *            the new logger
     */
    public void setLogger(LogTracer log) {
        this.log = log;
    }

    /**
     * Frame media entity.
     * @param domain
     *            the domain
     * @param formParams
     *            the form params
     * @param files
     *            the files
     * @param userContext
     *            the user context
     * @return the hash map
     * @throws GenericException
     *             the generic exception
     */
    public HashMap<String, Object> frameMediaEntity(String domain,
            int reqCount, HashMap<String, String> formParams,
            FileItem fileItem, HashMap<String, String> userContext)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "cmf");

        try {
            String[] fileExtns = propUtil.get("allowedFileExtns").split(",");
            allowedFileExtns.addAll(Arrays.asList(fileExtns));
        } catch (NullPointerException e) {
            throw new GenericException(domain, "ERR-CMF-0008", e);
        }
        log.debug("Allowed file extns -- " + allowedFileExtns);
        String entityType = formParams.get("EntityType");
        if (entityType.equals("Validations")
                || entityType.equals("AccessControlList")) {
            entityType = "Xls";
        }
        String fileName = fileItem.getName();
        if (formParams.containsKey(entityType + "Name")) {
            fileName = formParams.get(entityType + "Name");
            String[] fileNames = fileName.split(",");
            if (fileNames.length <= 1 && reqCount != 0) {
                fileName = fileItem.getName();
            } else {
                formParams.put(entityType + "Name", fileNames[reqCount]);
                fileName = fileNames[reqCount];
            }
        }
        log.debug("File Name -- " + fileName);
        if (!isValidFileName(fileName, fileItem.getName())) {
            throw new GenericException(domain, "ERR-CMF-0008",
                    "Invalid file format");
        }
        List<HashMap<String, String>> mediaEntities = new ArrayList<HashMap<String, String>>();
        HashMap<String, Object> files = new HashMap<String, Object>();
        try {
            if (entityType.equals("Media") || entityType.equals("PmsMedia")) {
                mediaEntities = getMediaEntities(domain, formParams, fileItem,
                        userContext, files);
            } else {
                mediaEntities.add(formParams);
                files = byteConvertion(domain, entityType,
                        formParams.get("Id"), fileItem);
            }
        } catch (GenericException e) {
            throw e;
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-CMF-0008");
        }

        if (mediaEntities.size() == 0) {
            throw new GenericException(domain, "ERR-CMF-0008",
                    "Invalid file format(s)");
        }
        HashMap<String, Object> mediaEntity = new HashMap<String, Object>();
        mediaEntity.put(formParams.get("EntityType"), mediaEntities);
        mediaEntity.put("files", files);
        log.debug("Media Entity ---- " + mediaEntity);
        return mediaEntity;
    }

    private HashMap<String, Object> byteConvertion(String domain,
            String entityType, String id, FileItem fileItem)
            throws GenericException, IOException {
        Utils utils = Utils.getInstanceof(domain);
        HashMap<String, Object> binary = new HashMap<String, Object>();
        String fileExtn = "js";
        if (!entityType.equalsIgnoreCase("js")) {
            String mimeType = utils.getMimeType(fileItem.getInputStream(),
                    fileItem.getName());
            fileExtn = utils.getMimeInfo(mimeType, "simplename");
        }
        binary.put(id + "." + fileExtn, fileItem.get());
        return binary;
    }

    /**
     * Gets the media entities.
     * @param domain
     *            the domain
     * @param formParams
     *            the form params
     * @param files
     *            the files
     * @param userContext
     *            the user context
     * @return the media entities
     * @throws GenericException
     *             the generic exception
     */
    private List<HashMap<String, String>> getMediaEntities(String domain,
            HashMap<String, String> formParams, FileItem fileItem,
            HashMap<String, String> userContext, HashMap<String, Object> files)
            throws GenericException {
        List<HashMap<String, String>> mediaEntities = null;
        try {
            mediaEntities = createMediaEntities(domain, formParams, fileItem,
                    files);
        } catch (GenericException e) {
            throw e;
        }
        if (mediaEntities.size() == 0) {
            throw new GenericException(domain, "ERR-CMF-0008",
                    "Invalid file format(s)");
        }
        return mediaEntities;
    }

    /**
     * Creates the media entities.
     * @param domain
     *            the domain
     * @param formParams
     *            the form params
     * @param files
     *            the files
     * @return the list
     * @throws GenericException
     *             the generic exception
     */
    private List<HashMap<String, String>> createMediaEntities(String domain,
            HashMap<String, String> formParams, FileItem fileItem,
            HashMap<String, Object> files) throws GenericException {
        List<HashMap<String, String>> mediacollection = new ArrayList<HashMap<String, String>>();
        try {
            Utils utils = Utils.getInstanceof(domain);
            String entityType = formParams.get("EntityType");
            String contentType = utils.getMimeType(fileItem.getInputStream(),
                    fileItem.getName());
            String simpleName = utils.getMimeInfo(contentType, "simplename");
            HashMap<String, String> media = new HashMap<String, String>();
            String mimeType = null;
            log.debug("Content Type - " + contentType);
            if (simpleName.equals("zip") && entityType.equals("PmsMedia")) {
                ZipInputStream zipstream = new ZipInputStream(
                        new BufferedInputStream(fileItem.getInputStream()));
                ZipEntry entry;
                int index = 0;
                while ((entry = zipstream.getNextEntry()) != null) {
                    if (entry.getSize() > 0) {
                        String filename = entry.getName().substring(
                                entry.getName().lastIndexOf('/') + 1,
                                entry.getName().length());
                        byte[] bytes = utils.getStreamAsByteArray(zipstream);
                        mimeType = utils.getMimeType(new ByteArrayInputStream(
                                bytes), filename);
                        String simplename = utils.getMimeInfo(mimeType,
                                "simplename");
                        if (allowedFileExtns.contains(simplename)) {
                            media = new HashMap<String, String>();
                            media.putAll(formParams);
                            if (index++ != 0) {
                                media.put("Id", Uniqueid.getId());
                            }
                            media.putAll(createFilePathInfo(mimeType, domain,
                                    simplename, media.get("Id")));
                            media.put("Md5", utils
                                    .getMD5(new ByteArrayInputStream(bytes)));
                            String fileName = filename.substring(0,
                                    filename.lastIndexOf('.'));
                            fileName += "." + simplename;
                            media.put("SourceName", fileName);
                            media.put("PmsMediaName", fileName);
                            mediacollection.add(media);
                            files.put(media.get("Id") + "." + simplename, bytes);
                        }
                    }
                }
            } else {
                media.putAll(formParams);
                byte[] bytes = utils.getStreamAsByteArray(fileItem
                        .getInputStream());
                mimeType = utils.getMimeType(new ByteArrayInputStream(bytes),
                        fileItem.getName());
                String name = fileItem.getName().substring(0,
                        fileItem.getName().lastIndexOf('.'));
                String fileName = name + "."
                        + utils.getMimeInfo(mimeType, "simplename");
                String simplename = utils.getMimeInfo(mimeType, "simplename");
                if (entityType.equals("Media")) {
                    media.put("Size", ((Long) fileItem.getSize()).toString());
                    media.put("MimeType", mimeType);
                    media.put("MimeSimpleName",
                            utils.getMimeInfo(mimeType, "simplename"));
                    setFieldValue(media, "MediaGroup",
                            utils.getMimeInfo(mimeType, "groupname"));
                    if (mimeType.contains("image/")) {
                        BufferedImage image = ImageIO
                                .read(new ByteArrayInputStream(bytes));
                        setFieldValue(media, "Height",
                                ((Integer) image.getHeight()).toString());
                        setFieldValue(media, "Width",
                                ((Integer) image.getWidth()).toString());
                    } else {
                        media.put("Height", "");
                        media.put("Width", "");
                    }
                    simplename = media.get("MimeSimpleName");
                }
                media.put("Md5", utils.getMD5(new ByteArrayInputStream(bytes)));
                media.put("SourceName", fileName);
                media.putAll(createFilePathInfo(mimeType, domain, simplename,
                        media.get("Id")));
                mediacollection.add(media);
                files.put(media.get("Id") + "." + simplename, bytes);
            }
        } catch (GenericException e) {
            throw e;
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-CMF-0008");
        }
        return mediacollection;
    }

    /**
     * Creates the file path info.
     * @param mimeType
     *            the mime type
     * @param domain
     *            the domain
     * @param simplaName
     *            the simpla name
     * @param Id
     *            the id
     * @return the hash map
     */
    private HashMap<String, String> createFilePathInfo(String mimeType,
            String domain, String simplaName, String Id) {
        HashMap<String, String> filePathInfo = new HashMap<String, String>();
        String filePath = "/" + domain + "/media/" + Id + "." + simplaName;
        filePathInfo.put("FilePath", filePath);
        if (mimeType.startsWith("image/")) {
            String thumbPath = "/" + domain + "/media/" + Id + "_thumb."
                    + simplaName;
            filePathInfo.put("ThumbnailPath", thumbPath);
        }
        return filePathInfo;
    }

    /**
     * Checks if is valid file name.
     * @param fileName
     *            the file name
     * @param sourceName
     *            the source name
     * @return true, if is valid file name
     */
    private boolean isValidFileName(String fileName, String sourceName) {
        log.debug("FileName -- " + fileName + " :: SourceName -- " + sourceName);
        String filename = fileName.substring(0, fileName.lastIndexOf('.'));
        String extn = fileName.substring(fileName.lastIndexOf('.') + 1,
                fileName.length());
        if (filename.length() > 0) {
            if (!sourceName.contains("." + extn)) {
                return false;
            }
            Pattern pattern = Pattern.compile("[A-Za-z]{2,4}");
            Matcher matcher = pattern.matcher(extn);
            return matcher.matches();
        } else {
            return false;
        }
    }

    /**
     * Sets the field value.
     * @param setterObject
     *            the setter object
     * @param name
     *            the name
     * @param value
     *            the value
     */
    private void setFieldValue(Map<String, String> setterObject, String name,
            String value) {
        String exp = setterObject.get(name);
        if (exp == null || exp.equals("")) {
            setterObject.put(name, value);
        }
    }

    /**
     * Gets the file item.
     * @param files
     *            the files
     * @return the file item
     */
    // private Object getFileItem(HashMap<String, Object> files) {
    // HashMap<String, Object> binary = new HashMap<String, Object>();
    // binary.putAll(files);
    // Iterator<Map.Entry<String, Object>> fileItr = binary.entrySet()
    // .iterator();
    // if (fileItr.hasNext()) {
    // Map.Entry<String, Object> fileEntry = (Map.Entry<String, Object>) fileItr
    // .next();
    // return fileEntry.getValue();
    // }
    // return null;
    // }
}
