package in.nic.cmf.mediahandler;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.Utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import sun.misc.BASE64Encoder;

/**
 * The Class MediaXmlParser.
 */
public class MediaXmlParser {

    /** The utils. */
    private Utils                                  utils;

    /** The allowed file extns. */
    private List<String>                           allowedFileExtns   = new ArrayList<String>();
    private List<String>                           thumbnailEntity    = new ArrayList<String>();
    HashMap<String, String>                        extens             = new HashMap<String, String>();
    /** The proputil. */
    private PropertiesUtil                         propUtil;
    private static HashMap<String, MediaXmlParser> hashMediaXmlParser = new HashMap<String, MediaXmlParser>();
    private String                                 domain;

    /** The log. */
    private LogTracer                              log;

    private MediaXmlParser(String domain) {
        this.domain = domain;
        propUtil = PropertiesUtil.getInstanceof(domain, "mediahandler");
        utils = Utils.getInstanceof(domain);
        extens.put("js", "js");
        extens.put("css", "css");
        // try {
        String[] fileExtns = propUtil.get("allowedFileExtns").split(",");
        allowedFileExtns.addAll(Arrays.asList(fileExtns));
        String[] thumbnailEntities = propUtil.get("thumbnailEntity").split(",");
        thumbnailEntity.addAll(Arrays.asList(thumbnailEntities));
        // } catch (NullPointerException e) {
        // throw new GenericException(domain, "ERR-MH-0001", e);
        // }
    }

    /**
     * getting instance of MediaService.
     * @return DmsService
     */
    public static MediaXmlParser getInstance(String domain) {
        if (!hashMediaXmlParser.containsKey(domain)) {
            hashMediaXmlParser.put(domain, new MediaXmlParser(domain));
        }
        return hashMediaXmlParser.get(domain);
    }

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
            int binaryCount, HashMap<String, String> formParams,
            byte[] fileItem, HashMap<String, String> userContext)
            throws GenericException {

        log.debug("Allowed file extns -- " + allowedFileExtns);
        log.debug("Form Params::::" + formParams);
        String entityType = formParams.get("EntityType");
        if (entityType.equals("Validations")
                || entityType.equals("AccessControlList")) {
            entityType = "Xls";
        }
        // Utils.getInstance().
        String fileName = formParams.get("SourceName");

        if (formParams.containsKey(entityType + "Name")) {
            fileName = formParams.get(entityType + "Name");
            String[] fileNames = fileName.split(",");
            if (fileNames.length <= 1 && binaryCount != 0) {
                fileName = formParams.get("SourceName");
            } else {
                formParams.put(entityType + "Name", fileNames[binaryCount]);
                fileName = fileNames[binaryCount];
            }
        }
        log.debug("File Name -- " + fileName);
        // if (!isValidFileName(fileName, formParams.get("SourceName"))
        // && binaryCount == 1) {
        // throw new GenericException(domain, "ERR-MH-0001",
        // "Invalid file format");
        // }

        if (!isValidFileName(fileName, formParams.get("SourceName"))) {
            throw new GenericException(domain, "ERR-MH-0001",
                    "Invalid file format");
        }
        // if (!isValidFileName(fileName, fileItem.getName()) && binaryCount ==
        // 1) {
        // throw new GenericException("ERR-MH-0001", "Invalid file format");
        // }
        List<HashMap<String, String>> mediaEntities = new ArrayList<HashMap<String, String>>();
        List<HashMap<String, Object>> files = new ArrayList<HashMap<String, Object>>();
        try {

            String mimeType = utils.getMimeType(new ByteArrayInputStream(
                    fileItem), formParams.get("SourceName"));
            // if (entityType.equalsIgnoreCase("Media")
            // || entityType.equalsIgnoreCase("PmsMedia")) {
            if (mimeType.contains("image/")) {

                mediaEntities = getMediaEntities(domain, formParams, fileItem,
                        userContext, files);
            } else {

                if (formParams.get("Id") == null) {
                    formParams.put("Id", Uniqueid.getId());
                }
                byteConvertion(entityType, formParams.get("Id"), fileItem,
                        formParams, files);
                formParams.put("Md5",
                        utils.getMD5(new ByteArrayInputStream(fileItem)));
                mediaEntities.add(formParams);

            }
        } catch (GenericException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GenericException(domain, "ERR-MH-0001");
        }

        if (mediaEntities.size() == 0) {
            throw new GenericException(domain, "ERR-MH-0001",
                    "Invalid file format(s)");
        }
        HashMap<String, Object> mediaEntity = new HashMap<String, Object>();
        mediaEntity.put(formParams.get("EntityType"), mediaEntities);
        mediaEntity.put("files", files);
        return mediaEntity;
    }

    private HashMap<String, String> byteConvertion(String entityType,
            String id, byte[] fileItem, HashMap<String, String> formParams,
            List<HashMap<String, Object>> files) throws GenericException,
            IOException {

        HashMap<String, Object> binary = new HashMap<String, Object>();
        String fileExtn = "js";
        log.debug("Entity Type:::" + entityType.toLowerCase());
        log.debug("Entity Type:::" + extens);
        if (extens.containsKey(entityType.toLowerCase())) {
            log.debug("If check");
            fileExtn = extens.get(entityType.toLowerCase());
        } else {
            // if (!entityType.equalsIgnoreCase("js")) {
            log.debug("Source Name Testing:::" + formParams.get("SourceName"));
            log.debug("byte array len -- " + fileItem.length);
            String mimeType = utils.getMimeType(new ByteArrayInputStream(
                    fileItem), formParams.get("SourceName"));
            log.debug("Mime Type::::" + mimeType);
            fileExtn = utils.getMimeInfo(mimeType, "simplename");
        }
        // }
        log.debug("File Extension:::::::::::" + fileExtn);
        String filePath = "/" + domain + "/" + formParams.get("EntityType")
                + "/" + formParams.get("Id") + "." + fileExtn;
        formParams.put("FilePath", filePath);
        HashMap<String, Object> file = new HashMap<String, Object>();
        // formparam.put("data","val");
        file.put("Id", id + "." + fileExtn);
        // file.put("data", new BASE64Encoder().encode(fileItem));
        file.put(
                "Dataurl",
                createDataUrl(domain, entityType, id + "." + fileExtn, fileItem));

        files.add(file);

        // binary.put(id + "." + fileExtn, fileItem.get());
        return formParams;
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
            HashMap<String, String> formParams, byte[] fileItem,
            HashMap<String, String> userContext,
            List<HashMap<String, Object>> files) throws GenericException {
        List<HashMap<String, String>> mediaEntities = null;
        try {
            mediaEntities = createMediaEntities(domain, formParams, fileItem,
                    files);
        } catch (GenericException e) {
            throw e;
        }
        if (mediaEntities.size() == 0) {
            throw new GenericException(domain, "ERR-MH-0001",
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
            HashMap<String, String> formParams, byte[] fileItem,
            List<HashMap<String, Object>> files) throws GenericException {
        List<HashMap<String, String>> mediacollection = new ArrayList<HashMap<String, String>>();

        try {
            String contentType = utils.getMimeType(new ByteArrayInputStream(
                    fileItem), formParams.get("SourceName"));
            String simpleName = utils.getMimeInfo(contentType, "simplename");
            HashMap<String, String> media = new HashMap<String, String>();
            media.putAll(formParams);
            String mimeType = null;
            log.debug("Content Type - " + contentType);
            log.debug("simpleName - " + simpleName);

            if (simpleName.equalsIgnoreCase("zip")) {
                log.debug("Zip od PmsMedia if loop");
                ZipInputStream zipstream = new ZipInputStream(
                        new BufferedInputStream(new ByteArrayInputStream(
                                fileItem)));

                ZipEntry entry;
                // Index is not required as by default all the binaries is been
                // strored
                // int index = 0;
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

                        if (!allowedFileExtns.contains(simplename)) {
                            throw new GenericException(domain, "ERR-MH-0001",
                                    "Invalid file format(s)");
                        }
                        // formParams.put("SourceName", filename);
                        HashMap<String, String> compressedElements = new HashMap<String, String>();

                        compressedElements.putAll(formParams);
                        compressedElements.put("SourceName", filename);
                        compressedElements.put(
                                compressedElements.get("EntityType") + "Name",
                                filename);
                        // if (index++ != 0) {
                        compressedElements.put("Id", Uniqueid.getId());
                        // }
                        log.debug("TESTING MEDIA COMPRESSED ELEMENTS:::"
                                + compressedElements);
                        frameMediaXML(compressedElements, bytes, files);
                        mediacollection.add(compressedElements);
                    }
                }
            }
            frameMediaXML(media, fileItem, files);
            mediacollection.add(media);

        } catch (GenericException e) {
            throw e;
        } catch (IOException e) {

            throw new GenericException(domain, "ERR-MH-0001");
        }
        return mediacollection;
    }

    private void frameMediaXML(HashMap<String, String> media, byte[] bytes,
            List<HashMap<String, Object>> files) {
        log.debug("Media XML TESTING::::" + media);
        String entityType = media.get("EntityType");
        BASE64Encoder baseEncoder = new BASE64Encoder();
        String mimeType = utils.getMimeType(new ByteArrayInputStream(bytes),
                media.get("SourceName"));
        String simplename = utils.getMimeInfo(mimeType, "simplename");

        media.putAll(createFilePathInfo(mimeType, domain, simplename, media));
        media.put("Md5", utils.getMD5(new ByteArrayInputStream(bytes)));
        String fileName = media.get("SourceName").substring(0,
                media.get("SourceName").lastIndexOf('.'));
        fileName += "." + simplename;
        log.debug("File Name:::" + fileName);
        media.put("SourceName", fileName);
        // media.put("PmsMediaName", fileName);

        // if (entityType.equals("Media")) {
        if (thumbnailEntity.contains(media.get("EntityType"))) {

            media.put("Size", String.valueOf(bytes.length));
            media.put("MimeType", mimeType);
            media.put("MimeSimpleName",
                    utils.getMimeInfo(mimeType, "simplename"));
            setFieldValue(media, "MediaGroup",
                    utils.getMimeInfo(mimeType, "groupname"));
            if (mimeType.contains("image/")) {

                BufferedImage image = null;
                try {
                    image = ImageIO.read(new ByteArrayInputStream(bytes));
                } catch (IOException e) {
                    throw new GenericException(domain, "ERR-MH-0001");
                }

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
        if (media.containsKey("ThumbnailPath") && mimeType.startsWith("image/")) {
            // if (mimeType.contains("image/")) {
            InputStream thumbstream = getThumbnailInputStream(simplename,
                    new ByteArrayInputStream(bytes));

            byte[] thumb_bytes = utils.getStreamAsByteArray(thumbstream);
            HashMap<String, Object> file = new HashMap<String, Object>();
            // file.put("data", baseEncoder.encode(thumb_bytes));
            String Id = media.get("Id") + "_thumb." + simplename;
            file.put("Id", Id);
            file.put("Dataurl",
                    createDataUrl(domain, entityType, Id, thumb_bytes));
            files.add(file);
        }

        /*
         * if (media.containsKey("SeoUrl")) {
         * media.put("SeoUrl", getSeoUrl(formParams));
         * }
         */

        // log.debug("Collections:::" + media);

        HashMap<String, Object> file = new HashMap<String, Object>();
        String Id1 = media.get("Id") + "." + simplename;
        file.put("Id", Id1);
        // file.put("data", baseEncoder.encode(bytes));
        file.put("Dataurl", createDataUrl(domain, entityType, Id1, bytes));
        files.add(file);

    }

    // /**
    // * Creates the media entities.
    // * @param domain
    // * the domain
    // * @param formParams
    // * the form params
    // * @param files
    // * the files
    // * @return the list
    // * @throws GenericException
    // * the generic exception
    // */
    // private List<HashMap<String, String>> createMediaEntities1(String domain,
    // HashMap<String, String> formParams, byte[] fileItem,
    // List<HashMap<String, Object>> files) throws GenericException {
    // List<HashMap<String, String>> mediacollection = new
    // ArrayList<HashMap<String, String>>();
    // BASE64Encoder baseEncoder = new BASE64Encoder();
    // try {
    // String entityType = formParams.get("EntityType");
    // // String contentType = utils.getMimeType(fileItem.getInputStream(),
    // // fileItem.getName());
    //
    // String contentType = utils.getMimeType(new ByteArrayInputStream(
    // fileItem), formParams.get("SourceName"));
    // String simpleName = utils.getMimeInfo(contentType, "simplename");
    // HashMap<String, String> media = new HashMap<String, String>();
    //
    // String mimeType = null;
    // log.debug("Content Type - " + contentType);
    // log.debug("simpleName - " + simpleName);
    // // if (simpleName.equalsIgnoreCase("zip")
    // // && entityType.equalsIgnoreCase("PmsMedia")) {
    //
    // if (simpleName.equalsIgnoreCase("zip")) {
    // log.debug("Zip od PmsMedia if loop");
    // ZipInputStream zipstream = new ZipInputStream(
    // new BufferedInputStream(new ByteArrayInputStream(
    // fileItem)));
    // // ZipInputStream zipstream = new ZipInputStream(
    // // new BufferedInputStream(fileItem.getInputStream()));
    // ZipEntry entry;
    // int index = 0;
    // while ((entry = zipstream.getNextEntry()) != null) {
    // if (entry.getSize() > 0) {
    // String filename = entry.getName().substring(
    // entry.getName().lastIndexOf('/') + 1,
    // entry.getName().length());
    // byte[] bytes = utils.getStreamAsByteArray(zipstream);
    // mimeType = utils.getMimeType(new ByteArrayInputStream(
    // bytes), filename);
    // String simplename = utils.getMimeInfo(mimeType,
    // "simplename");
    // if (allowedFileExtns.contains(simplename)) {
    // media = new HashMap<String, String>();
    // media.putAll(formParams);
    // if (index++ != 0) {
    // media.put("Id", Uniqueid.getId());
    // }
    // media.putAll(createFilePathInfo(mimeType, domain,
    // simplename, media));
    // media.put("Md5", utils
    // .getMD5(new ByteArrayInputStream(bytes)));
    // String fileName = filename.substring(0,
    // filename.lastIndexOf('.'));
    // fileName += "." + simplename;
    // log.debug("File Name:::" + fileName);
    // media.put("SourceName", fileName);
    // // media.put("PmsMediaName", fileName);
    // media.put(formParams.get("EntityType"), fileName);
    // if (entityType.equals("Media")) {
    //
    // media.put("Size",
    // String.valueOf(fileItem.length));
    // media.put("MimeType", mimeType);
    // media.put("MimeSimpleName", utils.getMimeInfo(
    // mimeType, "simplename"));
    // setFieldValue(
    // media,
    // "MediaGroup",
    // utils.getMimeInfo(mimeType, "groupname"));
    // if (mimeType.contains("image/")) {
    // InputStream thumbstream = getThumbnailInputStream(
    // simpleName,
    // new ByteArrayInputStream(fileItem));
    //
    // byte[] thumb_bytes = utils
    // .getStreamAsByteArray(thumbstream);
    // HashMap<String, Object> file = new HashMap<String, Object>();
    //
    // file.put("Id", media.get("Id") + "_thumb."
    // + simplename);
    // // file.put("data",
    // // baseEncoder.encode(thumb_bytes));
    // file.put(
    // "Dataurl",
    // createDataUrl(domain, entityType,
    // media.get("Id") + "_thumb."
    // + simplename,
    // thumb_bytes));
    // files.add(file);
    // BufferedImage image = ImageIO
    // .read(new ByteArrayInputStream(
    // fileItem));
    //
    // setFieldValue(media, "Height",
    // ((Integer) image.getHeight())
    // .toString());
    // setFieldValue(media, "Width",
    // ((Integer) image.getWidth())
    // .toString());
    //
    // } else {
    // media.put("Height", "");
    // media.put("Width", "");
    // }
    // }
    // if (mimeType.contains("image/")) {
    // InputStream thumbstream = getThumbnailInputStream(
    // simpleName, new ByteArrayInputStream(
    // fileItem));
    //
    // byte[] thumb_bytes = utils
    // .getStreamAsByteArray(thumbstream);
    // HashMap<String, Object> file = new HashMap<String, Object>();
    // // file.put("data",
    // // baseEncoder.encode(thumb_bytes));
    // file.put("Id", media.get("Id") + "_thumb."
    // + simplename);
    // file.put(
    // "Dataurl",
    // createDataUrl(domain, entityType,
    // media.get("Id") + "_thumb."
    // + simplename,
    // thumb_bytes));
    // files.add(file);
    // }
    // simplename = media.get("MimeSimpleName");
    //
    // /*
    // * if (media.containsKey("SeoUrl")) {
    // * media.put("SeoUrl", getSeoUrl(formParams));
    // * }
    // */
    //
    // // log.debug("Collections:::" + media);
    //
    // HashMap<String, Object> file = new HashMap<String, Object>();
    // file.put("Id", media.get("Id") + "." + simplename);
    // // file.put("data", baseEncoder.encode(bytes));
    // file.put(
    // "Dataurl",
    // createDataUrl(domain, entityType,
    // media.get("Id") + "." + simplename,
    // bytes));
    // files.add(file);
    // mediacollection.add(media);
    //
    // }
    // }
    // }
    // } else {
    // log.debug("Media if loop");
    // media.putAll(formParams);
    // if (formParams.get("Id") == null) {
    // media.put("Id", Uniqueid.getId());
    // }
    //
    // mimeType = utils.getMimeType(
    // new ByteArrayInputStream(fileItem),
    // formParams.get("SourceName"));
    //
    // String name = formParams.get("SourceName").substring(0,
    // formParams.get("SourceName").lastIndexOf('.'));
    //
    // String fileName = name + "."
    // + utils.getMimeInfo(mimeType, "simplename");
    //
    // String simplename = utils.getMimeInfo(mimeType, "simplename");
    // // if (entityType.equals("Media")) {
    // //
    // // media.put("Size", String.valueOf(fileItem.length));
    // // media.put("MimeType", mimeType);
    // // media.put("MimeSimpleName",
    // // utils.getMimeInfo(mimeType, "simplename"));
    // // setFieldValue(media, "MediaGroup",
    // // utils.getMimeInfo(mimeType, "groupname"));
    // // if (mimeType.contains("image/")) {
    // // InputStream thumbstream = getThumbnailInputStream(
    // // simpleName, new ByteArrayInputStream(fileItem));
    // //
    // // byte[] thumb_bytes = utils
    // // .getStreamAsByteArray(thumbstream);
    // // HashMap<String, Object> file = new HashMap<String, Object>();
    // // file.put("data", baseEncoder.encode(thumb_bytes));
    // // file.put("id", media.get("Id") + "_thumb." + simplename);
    // // files.add(file);
    // // BufferedImage image = ImageIO
    // // .read(new ByteArrayInputStream(fileItem));
    // //
    // // setFieldValue(media, "Height",
    // // ((Integer) image.getHeight()).toString());
    // // setFieldValue(media, "Width",
    // // ((Integer) image.getWidth()).toString());
    // //
    // // } else {
    // // media.put("Height", "");
    // // media.put("Width", "");
    // // }
    // // simplename = media.get("MimeSimpleName");
    // // }
    //
    // media.put("Md5",
    // utils.getMD5(new ByteArrayInputStream(fileItem)));
    // if (!media.containsKey("MediaName")) {
    // media.put("MediaName", fileName);
    // }
    // media.put("SourceName", fileName);
    // /*
    // * if (media.containsKey("SeoUrl")) {
    // * media.put("SeoUrl", getSeoUrl(media));
    // * }
    // */
    //
    // media.putAll(createFilePathInfo(mimeType, domain, simplename,
    // media));
    // // log.debug("eachCollections:::" + media);
    // HashMap<String, Object> file = new HashMap<String, Object>();
    // file.put("Id", media.get("Id") + "." + simplename);
    // file.put(
    // "Dataurl",
    // createDataUrl(domain, entityType, media.get("Id") + "."
    // + simplename, fileItem));
    // // file.put("data", baseEncoder.encode(fileItem));
    // files.add(file);
    // mediacollection.add(media);
    // }
    // } catch (GenericException e) {
    // throw e;
    // } catch (IOException e) {
    //
    // throw new GenericException(domain, "ERR-MH-0001");
    // }
    // return mediacollection;
    // }

    // private String getSeoUrl(Map<String, String> formparams) {
    //
    // String seourl = formparams.get("SeoUrl").substring(0,
    // formparams.get("SeoUrl").lastIndexOf("."));
    // log.debug("SEO URL:::" + seourl);
    // log.debug("ID:::" + formparams.get("Id"));
    // return seourl + formparams.get("Id") + ".php";
    //
    // }

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
            String domain, String simplaName, HashMap<String, String> media) {

        HashMap<String, String> filePathInfo = new HashMap<String, String>();
        String filePath = "/" + domain + "/" + media.get("EntityType") + "/"
                + media.get("Id") + "." + simplaName;
        filePathInfo.put("FilePath", filePath);

        if (media.containsKey("ThumbnailPath") && mimeType.startsWith("image/")) {
            String thumbPath = "/" + domain + "/" + media.get("EntityType")
                    + "/" + media.get("Id") + "_thumb." + simplaName;
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
     * Gets the thumbnail input stream.
     * @param thumbImgExt
     *            the thumb img ext
     * @param sourcestream
     *            the sourcestream
     * @return the thumbnail input stream
     * @throws GenericException
     *             the generic exception
     */
    private InputStream getThumbnailInputStream(String thumbImgExt,
            InputStream sourcestream) throws GenericException {
        int sourceImgHeight = 0;
        int sourceImgWidth = 0;
        BufferedImage sourceimage = null;
        try {
            sourceimage = ImageIO.read(sourcestream);
            sourceImgWidth = sourceimage.getWidth();
            sourceImgHeight = sourceimage.getHeight();
        } catch (IOException e1) {
            log.error("Thumbnail image creation process [source stream read error]...."
                    + e1.getMessage());
            throw new GenericException(domain, "ERR-DS-0009",
                    "Thumbnail generation failure in "
                            + this.getClass().getSimpleName()
                            + ":getThumbnailInputStream()", e1);
        } finally {
            try {
                sourcestream.close();
            } catch (IOException e) {

            }
        }
        int thumbnailWidth = Integer.parseInt(propUtil
                .getProperty("thumbnailWidth"));
        int thumbnailHeight = Integer.parseInt(propUtil
                .getProperty("thumbnailHeight"));

        if (thumbnailWidth > sourceImgWidth) {
            thumbnailWidth = sourceImgWidth;
        }
        if (thumbnailHeight > sourceImgHeight) {
            thumbnailHeight = sourceImgHeight;
        }
        BufferedImage destinationimage = new BufferedImage(thumbnailWidth,
                thumbnailHeight, BufferedImage.TYPE_INT_RGB);
        destinationimage.getGraphics().drawImage(sourceimage, 0, 0,
                thumbnailWidth, thumbnailHeight, null);
        ByteArrayOutputStream barrayoutstream = null;
        try {
            barrayoutstream = new ByteArrayOutputStream();
            ImageIO.write(destinationimage, thumbImgExt, barrayoutstream);
        } catch (IOException e) {
            log.error("Thumbnail image creation process...." + e.getMessage());
            throw new GenericException(domain, "ERR-DS-0009",
                    "Thumbnail generation failure in "
                            + this.getClass().getSimpleName()
                            + ":getThumbnailInputStream()", e);
        }
        InputStream thumbinputsream = new ByteArrayInputStream(
                barrayoutstream.toByteArray());
        try {
            destinationimage.flush();
            sourceimage.flush();
            barrayoutstream.close();
            sourcestream.close();
        } catch (IOException e) {

        }

        return thumbinputsream;
    }

    // private String thumbFileUpload(Map<String, String> userContext,
    // String domain, String entityId, byte[] bytes, String mimeType,
    // String entityType) throws GenericException {
    // try {
    //
    // log.debug("Inside Thumb FileUpload ==> " + mimeType);
    // String thumbFileName = entityId + "_thumb."
    // + utils.getMimeInfo(mimeType, "simplename");
    // log.debug("thumb FileName ==> " + thumbFileName);
    // InputStream instream = null;
    //
    // // = getThumbnailInputStream(
    // // utils.getMimeInfo(mimeType, "simplename"),
    // // new ByteArrayInputStream(bytes));
    // //
    // Map<String, Object> binaryParams = new HashMap<String, Object>();
    // binaryParams.put("fileName", thumbFileName);
    // binaryParams.put("inputStream", instream);
    // binaryParams.put("bytesLength", instream.available());
    // binaryParams.put("mimeType", mimeType);
    // binaryParams.put("collectionsFlag", false);
    //
    // // return resultMap.get("responseCode").toString();
    // } catch (GenericException e) {
    // log.error(e.getMessage());
    // throw e;
    // } catch (IOException e) {
    // log.fatal(e.getMessage());
    // throw new GenericException(domain, "ERR-DS-0014", e);
    // }
    // return null;
    // }

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

    private String createDataUrl(String domain, String entity, String filename,
            byte[] content) {

        String dirPath = propUtil.get("binaryfilelocation") + domain;
        String filepath = propUtil.get("binaryfilelocation") + domain + "/"
                + filename;
        String url = propUtil.get("binaryfilestoreurl") + domain + "/" + entity
                + "/" + filename;
        FileOutputStream fos;
        try {
            new File(dirPath).mkdirs();
            File file = new File(filepath);
            file.createNewFile();
            fos = new FileOutputStream(filepath);
            fos.write(content);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new GenericException(domain, "ERR-MH-0006");
        } catch (IOException e) {
            e.printStackTrace();
            throw new GenericException(domain, "ERR-MH-0007");
        }
        return url;
    }

}
