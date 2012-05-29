package in.nic.cmf.util;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import eu.medsea.util.MimeUtil;

/**
 * The Class Utils.
 * @author Ramu P Created on : 29th April 2011
 */
public class Utils {
    private Utils                         util;
    // private LogTracer log = new LogTracer("UtilTraceLogger");
    private LogTracer                     log;
    private PropertiesUtil                proputil  = null;
    private static HashMap<String, Utils> hashUtils = new HashMap<String, Utils>();
    private String                        domain    = null;

    private Utils(String domain) { // singleton constructor
        this.domain = domain;
        setLogger(domain);
        List<String> propList = new ArrayList<String>();
        propList.add("mimedetails");
        proputil = PropertiesUtil.getInstanceof(domain, "util", propList);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger");
    }

    public static Utils getInstanceof(String domain) {
        if (!hashUtils.containsKey(domain)) {
            hashUtils.put(domain, new Utils(domain));
        }
        return hashUtils.get(domain);
    }

    public String getMimeInfo(String mimeType, String mimeTypeName) {
        String mimeInfo = proputil.get(mimeType);
        if (mimeInfo != null) {
            String[] mimeDetail = mimeInfo.split(",");
            if (mimeTypeName.equalsIgnoreCase("simplename")) {
                return mimeDetail[0];
            } else if (mimeTypeName.equalsIgnoreCase("groupname")) {
                return mimeDetail[1];
            }
        }
        return null;
    }

    public String getMimeType(String filename) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getMimeType(String)");
        File file = null;
        try {
            file = new File(filename);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-UTL-0004");
        }
        log.debug("File length ==> " + file.length());
        String mimeTypes = MimeUtil.getExtensionMimeTypes(file);
        log.debug("Mime Type ==> " + mimeTypes);
        log.methodExit(this.getClass().getSimpleName() + ".getMimeType()");
        return MimeUtil.getFirstMimeType(mimeTypes);

    }

    public String getMimeType(InputStream inputstream, String filename)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getMimeType(InputStream, String)");
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        String mimeType = null;
        try {
            byte[] bytes = getStreamAsByteArray(inputstream,
                    inputstream.available());
            mimeType = getMimeType(new ByteArrayInputStream(bytes));
            if (mimeType.equals("text/plain")) {
                metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, filename);
                parser.parse(new ByteArrayInputStream(bytes),
                        new DefaultHandler(), metadata, new ParseContext());
                mimeType = metadata.get(Metadata.CONTENT_TYPE);
            } else if (mimeType.toLowerCase().contains("text/html; charset=")) {
                mimeType = "text/html";
            }
            inputstream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMimeType(InputStream, String)", e);
        } catch (SAXException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMimeType(InputStream, String)", e);
        } catch (TikaException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMimeType(InputStream, String)", e);
        }
        log.methodExit(this.getClass().getSimpleName()
                + ".getMimeType(InputStream, String)");
        return mimeType;
    }

    public String getMimeType(InputStream inputstream) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getMimeType(InputStream)");
        AutoDetectParser parser = new AutoDetectParser();
        Metadata metadata = new Metadata();
        try {
            parser.parse(inputstream, new DefaultHandler(), metadata,
                    new ParseContext());
            inputstream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMimeType(InputStream)", e);
        } catch (SAXException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMimeType(InputStream)", e);
        } catch (TikaException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMimeType(InputStream)", e);
        }
        log.methodExit(this.getClass().getSimpleName()
                + ".getMimeType(InputStream)");
        return metadata.get(Metadata.CONTENT_TYPE);
    }

    /**
     * This method is used to convert String to an ArrayList of strings.
     * @param values
     *            The list of value
     * @return ArrayList of String
     * @throws Exception
     *             the exception
     */
    public ArrayList<String> getAsList(String values) {
        log.methodEntry(this.getClass().getSimpleName() + ".getAsList()");
        try {
            ArrayList<String> valuelist = new ArrayList<String>();
            for (String value : values.trim().split(",")) {
                valuelist.add(value);
            }
            log.methodExit(this.getClass().getSimpleName() + ".getAsList()");
            return valuelist;
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getAsList()", e);
        }
    }

    public String getFileExtnByFileName(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1,
                    fileName.length());
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getFileExtnByFileName()", e);
        }
    }

    /**
     * Gets the MD5.
     * @param fileContent
     *            the file content
     * @return MD5 value as String
     */
    public String getMD5(String fileContent) throws GenericException {
        try {
            return DigestUtils.md5Hex(fileContent);
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new GenericException(domain,"ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMD5(String)", e);
        }
    }

    /**
     * Gets the MD5.
     * @param inputstream
     *            the inputstream
     * @return the md5
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public String getMD5(InputStream inputstream) throws GenericException {
        try {
            return DigestUtils.md5Hex(inputstream);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMD5(InputStream)", e);
        }
    }

    /**
     * Gets the MD5.
     * @param data
     *            the data
     * @return the md5
     */
    public String getMD5(byte[] data) throws GenericException {
        try {
            return DigestUtils.md5Hex(data);
        } catch (NullPointerException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getMD5(byte[])", e);
        }
    }

    public byte[] getStreamAsByteArray(InputStream inputstream)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getStreamAsByteArray(InputStream)");
        int size = 0;
        ByteArrayOutputStream bos = null;
        try {
            byte[] bytes = new byte[inputstream.available()];
            bos = new ByteArrayOutputStream();
            while ((size = inputstream.read(bytes, 0, bytes.length)) != -1) {
                bos.write(bytes, 0, size);
            }
            bos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getStreamAsByteArray(InputStream)", e);
        }
        log.methodExit(this.getClass().getSimpleName()
                + ".getStreamAsByteArray(InputStream)");
        return bos.toByteArray();
    }

    public byte[] getStreamAsByteArray(InputStream inputstream, long len)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getStreamAsByteArray(InputStream, long)");
        int size = 0;
        ByteArrayOutputStream bos = null;
        try {
            byte[] bytes = new byte[(int) len];
            bos = new ByteArrayOutputStream();
            while ((size = inputstream.read(bytes, 0, bytes.length)) != -1) {
                bos.write(bytes, 0, size);
            }
            bos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName()
                    + ".getStreamAsByteArray(InputStream, long)", e);
        }
        log.methodExit(this.getClass().getSimpleName()
                + ".getStreamAsByteArray(InputStream, long)");
        return bos.toByteArray();
    }

    public double getFileSize(String urlPath) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".getFileSize()");
        double filelength = 0;
        byte buf[] = new byte[1024];
        int len;
        try {
            if (urlPath.startsWith("http://") || urlPath.startsWith("https://")) {
                URL url = new URL(urlPath);
                URLConnection uc = url.openConnection();
                InputStream stream = uc.getInputStream();

                while ((len = stream.read(buf)) > 0) {
                    filelength += len;
                }
            } else {
                File file = new File(urlPath);
                InputStream stream = new FileInputStream(file);

                while ((len = stream.read(buf)) > 0) {
                    filelength += len;
                }
            }
            log.methodExit(this.getClass().getSimpleName() + ".getFileSize()");
            return filelength;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getFileSize()", e);
        }
    }

    public String encodeUrl(String partUrl, String encodeUrl)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".encodeUrl()");
        try {
            log.methodExit(this.getClass().getSimpleName() + ".encodeUrl()");
            return partUrl + URLEncoder.encode(encodeUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0002");
        }
    }

    public boolean isSkipAcl(HttpServletRequest request)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".isSkipAcl()");
        boolean flagAcl = true;
        try {
            // Map hMap = getQryParamsAndCookieValuesAsMap(request);
            // String EntityType = null;
            // String roleName = null;
            // String userName = null;
            // String authusername = null;
            // String aclRoleName = null;
            Map<String, String> hMap = getQryParamsAndCookieValuesAsMap(request);
            String EntityType = hMap.get("entitytype");
            String roleName = hMap.get("role");
            String userName = hMap.get("username");
            String authusername = hMap.get("authusername");
            String aclRoleName = hMap.get("aclrole");

            // if (hMap.containsKey("entitytype")) {
            // EntityType = hMap.get("entitytype").toString();
            // }
            // if (hMap.containsKey("aclrole")) {
            // aclRoleName = hMap.get("aclrole").toString();
            // }
            // if (hMap.containsKey("role")) {
            // roleName = hMap.get("role").toString();
            // }
            //
            // if (hMap.containsKey("username")) {
            // userName = hMap.get("username").toString();
            // }
            //
            // if (hMap.containsKey("authusername")) {
            // authusername = hMap.get("authusername").toString();
            // }

            log.info("EntityType ==> " + EntityType + " ::::: roleName ==> "
                    + roleName);
            log.info("userName ==> " + userName + " ::::: authusername ==> "
                    + authusername);
            if (EntityType != null) {
                if (EntityType.equals("cmsuserprofile")) {
                    if ((authusername != null && userName != null)) {
                        if (userName.equals(authusername)) {
                            flagAcl = false;
                        }
                    }

                }

                if (EntityType.equals("role")) {
                    if (aclRoleName != null && roleName != null) {
                        if (roleName.equals(aclRoleName)) {
                            flagAcl = false;
                        }
                    }

                }

            }
            log.debug("flagAcl ==> " + flagAcl);
            log.methodExit(this.getClass().getSimpleName() + ".isSkipAcl()");
            return flagAcl;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".isSkipAcl()", e);
        }
    }

    public boolean isEmpty(String value) {
        log.methodEntry(this.getClass().getSimpleName() + ".isEmpty()");
        if (value != null) {
            value = value.trim();
            log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
            return value.isEmpty(); // true if length() is 0, otherwise false
        }
        log.debug("isEmpty ==> true");
        log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
        return true;
    }

    public Map<String, Object> parsingErrorResponse(String jsonTxt)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".parsingErrorResponse()");
        try {
            JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
            log.methodExit(this.getClass().getSimpleName()
                    + ".parsingErrorResponse()");
            return getHashMapFromJson(json);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0005", this.getClass()
                    .getSimpleName() + ".parsingErrorResponse()", e);
        }
    }

    public HashMap<String, Object> getHashMapFromJson(
            JSONObject incomingJsonObject) {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getHashMapFromJson()");
        try {
            Iterator<?> itr = incomingJsonObject.keys();
            HashMap<String, Object> hJsonDetails = new HashMap<String, Object>();
            while (itr.hasNext()) {
                Object element = itr.next();
                hJsonDetails.put(element.toString(),
                        incomingJsonObject.get(element.toString()));
            }
            log.debug("Json hashmap ==> " + hJsonDetails);
            log.methodExit(this.getClass().getSimpleName()
                    + ".getHashMapFromJson()");
            return hJsonDetails;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0006", this.getClass()
                    .getSimpleName() + ".getHashMapFromJson()", e);
        }
    }

    public Map<String, String> getUserContext(HttpServletRequest request) {
        return getQryParamsAndCookieValuesAsMap(request);
    }

    public String getTransactionTime(long time) {
        long currentTime = System.currentTimeMillis();
        long timer = currentTime - time;
        return (new Date()) + " ; CurrentTime:" + currentTime + " ; TimeTaken:"
                + timer + " ; ";
    }

    public Map<String, String> getDetailsFromRequest(
            HttpServletRequest request, String domain) {
        Map<String, String> requestMap = getQryParamsAndCookieValuesAsMap(request);
        if (!requestMap.containsKey("site") || isEmpty(requestMap.get("site"))) {
            requestMap.put("site", domain.toLowerCase());
        }
        if (!requestMap.containsKey("action")
                || isEmpty(requestMap.get("action"))) {
            requestMap.put("action", request.getMethod().toLowerCase());
        }
        if (!requestMap.containsKey("entitytype")
                || isEmpty(requestMap.get("entitytype"))) {
            String entityFrompath = getEntityFromPath(request.getPathInfo(),
                    domain.toLowerCase());
            requestMap.put("entitytype", entityFrompath.toLowerCase());
        }

        log.debug("requestedDetails are :" + requestMap);
        return requestMap;
    }

    public String getEntityFromPath(String path, String domainName)
            throws GenericException {
        String entityName = "";
        String domainAsDelimiter = "/" + domainName + "/";
        if (path != null) {
            String[] query = path.split(domainAsDelimiter);
            String[] entity = new String[2];
            for (String querySearch : query) {
                entity = querySearch.split("/");
                entityName = entity[0];
            }
        }
        return entityName;
    }

    public Map<String, String> getQryParamsAndCookieValuesAsMap(
            HttpServletRequest request) throws GenericException {
        return getQryParamsAndCookieValuesAsMap(request, false);
    }

    public Map<String, String> getQryParamsAndCookieValuesAsMap(
            HttpServletRequest request, boolean isLower)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getQryParamsAndCookieValuesAsMap()");
        try {
            Map<String, String> hQryParamsAndCookies = UserContext
                    .getInstanceof(domain).getUserContext(request);
            Enumeration<String> reqparams = request.getParameterNames();
            ArrayList<String> aList = java.util.Collections.list(reqparams);

            for (String keyValue : aList) {
                if (isLower) {
                    hQryParamsAndCookies.put(keyValue.toLowerCase(), request
                            .getParameter(keyValue).toLowerCase());
                } else {
                    hQryParamsAndCookies.put(keyValue.toLowerCase(),
                            request.getParameter(keyValue));
                }
            }
            // included below line on 17th july 2011 by kavitha
            hQryParamsAndCookies.put("action", request.getMethod()
                    .toLowerCase());

            log.debug("getQryParamsAndCookieValuesAsMap output:"
                    + hQryParamsAndCookies);
            log.methodExit(this.getClass().getSimpleName()
                    + ".getQryParamsAndCookieValuesAsMap()");

            return hQryParamsAndCookies;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getQryParamsAndCookieValuesAsMap()", e);
        }
    }

}
