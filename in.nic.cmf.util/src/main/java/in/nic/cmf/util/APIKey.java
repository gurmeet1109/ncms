package in.nic.cmf.util;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class APIKey {

    private PropertiesUtil                 proputil   = null;
    // private final LogTracer log = new LogTracer("UtilTraceLogger",
    // true, true, true, true, true);
    private LogTracer                      log;
    private static HashMap<String, APIKey> hashApikey = new HashMap<String, APIKey>();
    private String                         domain;

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                false);
    }

    private APIKey(String domain) {
        this.domain = domain;
        setLogger(domain);
        List<String> propList = new ArrayList<String>();
        propList.add("apikeysalt");
        proputil = PropertiesUtil.getInstanceof(domain, "util", propList);
    }

    public static APIKey getInstanceof(String domain) {
        if (!hashApikey.containsKey(domain)) {
            hashApikey.put(domain, new APIKey(domain));
        }
        return hashApikey.get(domain);
    }

    public boolean validateAPIKey(String userId, String remoteIP,
            String aclrole, String apiKey, String domain)
            throws GenericException {

        log.methodEntry(this.getClass().getSimpleName() + ".validateAPIKey()");
        String newApiKey;
        try {
            newApiKey = generateAPIKey(userId, remoteIP, aclrole, domain);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
        log.methodExit(this.getClass().getSimpleName() + ".validateAPIKey()");
        if (newApiKey.equals(apiKey)) {
            return true;
        }
        return false;
    }

    public String generateAPIKey(final String userId, final String remoteIP,
            final String aclrole, final String domain) throws GenericException {

        log.methodEntry(this.getClass().getSimpleName() + ".generateAPIKey()");
        try {
            String salt1 = proputil.getProperty("SALT_KEY_01");
            String salt2 = proputil.getProperty("SALT_KEY_02");
            Calendar now = Calendar.getInstance();
            int day = now.get(Calendar.DATE);
            log.methodExit(this.getClass().getSimpleName()
                    + ".generateAPIKey()");
            return SHA1(salt1 + salt2 + Integer.toString(day) + userId
                    + remoteIP + aclrole + domain);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".generateAPIKey()", e);
        }
    }

    private String convertToHex(byte[] data) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".convertToHex()");
        StringBuffer buf = new StringBuffer();
        try {
            for (int i = 0; i < data.length; i++) {
                int halfbyte = (data[i] >>> 4) & 0x0F;
                int two_halfs = 0;
                do {
                    if ((0 <= halfbyte) && (halfbyte <= 9))
                        buf.append((char) ('0' + halfbyte));
                    else buf.append((char) ('a' + (halfbyte - 10)));
                    halfbyte = data[i] & 0x0F;
                } while (two_halfs++ < 1);
            }
            log.methodExit(this.getClass().getSimpleName() + ".convertToHex()");
            return buf.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".convertToHex()", e);
        }
    }

    private String SHA1(String text) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".SHA1()");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash = new byte[40];
            md.update(text.getBytes(proputil.getProperty("ISO")), 0,
                    text.length());
            sha1hash = md.digest();
            log.methodExit(this.getClass().getSimpleName() + ".SHA1()");
            return convertToHex(sha1hash);
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".SHA1()", e);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0002");
        }
    }
}
