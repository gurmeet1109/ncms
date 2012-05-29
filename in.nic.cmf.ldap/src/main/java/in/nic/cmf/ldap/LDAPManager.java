package in.nic.cmf.ldap;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.ldap.contract.Manager;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.security.owasp.LDAPInjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPModification;
import com.novell.ldap.LDAPSearchResults;

/**
 * The Class LDAPManager for apacheDS server its implements Manager interface.
 */
public class LDAPManager implements Manager {

    /** The ldap conn. */
    private LDAPConnection                  ldapConn        = new LDAPConnection();
    /** The log. */
    public LogTracer                        log             = null;
    /** The prop util. */
    private PropertiesUtil                  propUtil;
    private LDAPInjection                   ldapinject;
    private String                          connectionDN    = null;
    private static HashMap<String, Manager> hashLDAPManager = new HashMap<String, Manager>();
    private String                          domain;

    private LDAPManager(String domain) {
        this.domain = domain;
        this.log = new LogTracer(domain, "ldap");
        ldapinject = new LDAPInjection(domain);
        propUtil = PropertiesUtil.getInstanceof(domain, "ldap");
        connectionDN = getDN();
    }

    /**
     * Gets the single instance of LDAPManager.
     * @return single instance of LDAPManager
     */
    public static Manager getInstance(String domain) {
        if (!hashLDAPManager.containsKey(domain)) {
            hashLDAPManager.put(domain, new LDAPManager(domain));
        }
        return hashLDAPManager.get(domain);
    }

    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    @Override
    public Map<String, String> searchById(String profileId) {
        try {
            LDAPSearchResults searchResults = getConnection().search(
                    connectionDN, LDAPConnection.SCOPE_SUB,
                    "(cn=" + profileId + ")", null, false);
            if (!searchResults.hasMore()) return null;
            LDAPEntry entry = searchResults.next();
            Map<String, String> attribute = new HashMap<String, String>();
            attribute.put("userName", getData(entry, "street"));
            attribute.put("entity", getData(entry, "l"));
            return attribute;
        } catch (LDAPException e) {
            log.error("LDAPException in Search profileId : " + profileId
                    + " \n" + e.getMessage());
        } catch (Exception e) {
            log.error("Exception in Search profileId : " + profileId + " \n"
                    + e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, String> search(String userName, String entity) {
        try {
            LDAPSearchResults searchResults = getConnection().search(
                    connectionDN, LDAPConnection.SCOPE_SUB,
                    "(&(street=" + userName + ")(l=" + entity + "))", null,
                    false);
            log.debug("Ldap search query -- " + "(&(street=" + userName
                    + ")(l=" + entity + "))");
            if (searchResults.hasMore()) {
                LDAPEntry entry = searchResults.next();
                Map<String, String> attribute = new HashMap<String, String>();
                attribute.put("isActive", getData(entry, "Title"));
                attribute.put("role", getData(entry, "sn"));
                attribute.put("profileId", getData(entry, "cn"));
                attribute.put("userName", getData(entry, "street"));
                attribute.put("password", getData(entry, "userPassword"));
                attribute.put("entity", getData(entry, "l"));
                log.debug("search data --- " + attribute);
                return attribute;
            } else {
                log.debug("No data found in search....");
            }
        } catch (LDAPException e) {
            log.error("LDAPException when Searching username : " + userName
                    + " \n" + e.getMessage());
        } catch (GenericException e) {
            log.fatal("GenericException throws -- " + e.getErrorMessage());
            throw e;
        } catch (Exception e) {
            log.fatal("Exception throws -- " + e.getMessage());
            throw new GenericException(domain, "ERR-LDA-0000", e);
        }
        return null;
    }

    @Override
    public boolean create(Map<String, String> attribute) {
        if ((attribute == null) || (attribute.size() == 0)) {
            return false;
        }
        String profileId = attribute.get("profileId");
        attribute.remove("profileId");
        log.debug("Create -> profileId : " + profileId);
        LDAPAttributeSet ldapAttribute = new LDAPAttributeSet();

        for (Map.Entry<String, String> entry : attribute.entrySet()) {
            log.debug("Received value : " + entry.getKey() + "::::"
                    + entry.getValue());
            ldapAttribute.add(new LDAPAttribute(entry.getKey(), entry
                    .getValue()));
        }
        ldapAttribute.add(new LDAPAttribute("objectClass", propUtil
                .getProperty("objectClass")));
        String addDN = "cn=" + profileId + "," + connectionDN;
        LDAPEntry newEntry = new LDAPEntry(addDN, ldapAttribute);
        try {
            getConnection().add(newEntry);
            return true;
        } catch (LDAPException e) {
            log.fatal("LDAPException throws -- " + e);
            throw new GenericException(domain, "ERR-LDA-0001");
        } catch (GenericException e) {
            log.fatal("GenericException throws - " + e.getErrorMessage());
            throw e;
        }
    }

    @Override
    public boolean update(Map<String, String> attribute) {
        if (attribute.size() > 0) {
            String profileId = attribute.get("profileId");
            attribute.remove("profileId");
            List<LDAPModification> modList = new ArrayList<LDAPModification>();
            for (Map.Entry<String, String> entry : attribute.entrySet()) {
                modList.add(new LDAPModification(LDAPModification.REPLACE,
                        new LDAPAttribute(entry.getKey(), entry.getValue())));
            }
            LDAPModification[] modsadd = new LDAPModification[modList.size()];
            if (modsadd.length == 0) {
                return false;
            }
            modsadd = modList.toArray(modsadd);
            String dn = "cn=" + profileId + "," + connectionDN;
            try {
                getConnection().modify(dn, modsadd);
            } catch (LDAPException e) {
                log.fatal("LDAPException throws - " + e.getMessage());
                throw new GenericException(domain, "ERR-LDA-0001");
            } catch (GenericException e) {
                log.fatal("GenericException throws - " + e.getErrorMessage());
                throw e;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String userProfileId) {
        try {
            Map<String, String> attribute = searchById(userProfileId);
            if (attribute == null) {
                log.debug("search profileId : " + userProfileId
                        + " not found...");
                return false;
            }
            log.debug("ldap user delete profileId : " + userProfileId
                    + " actual username : "
                    + getUserName(attribute.get("userName")) + " EntityType : "
                    + attribute.get("entity"));
            String addDN = "cn=" + userProfileId + "," + connectionDN;
            getConnection().delete(addDN);
            return true;
        } catch (LDAPException e) {
            log.fatal("LDAPException throws - " + e);
            throw new GenericException(domain, "ERR-LDA-0001");
        } catch (GenericException e) {
            log.fatal("GenericException throws - " + e.getErrorMessage());
            throw e;
        }
    }

    /**
     * Checks if the given string is empty or not.
     * @param data
     *            the String value
     * @return true, if is empty
     */
    private boolean isEmpty(String data) {
        if (data != null) {
            return data.isEmpty();
        }
        return true;
    }

    private String getUserName(String userName) {
        return userName.substring(0, userName.lastIndexOf("@"));
    }

    /**
     * Gets the data.
     * @param entry
     *            the entry
     * @param key
     *            the key
     * @return the data
     */
    private String getData(LDAPEntry entry, String key) {
        String value = "";
        try {
            LDAPAttribute ldapAtt = entry.getAttribute(key);
            value = ldapAtt.getStringValue();
            log.debug(key + " : " + value);
            if (isEmpty(value)) {
                return "";
            }
            return value;
        } catch (Exception e) {
            log.error(key + " read from LDAPEntry throws - " + e.getMessage());
        }
        return "";
    }

    /**
     * Gets the LDAP Server Connection.
     * @return the connection
     */
    private LDAPConnection getConnection() {
        if (ldapConn.isConnected()) {
            log.info("Connection already established....");
            return ldapConn;
        }
        try {
            String server = propUtil.getProperty("ldapip");
            int port = propUtil.getAsInt("ldapport");
            log.info("Connection .... : " + server + ":" + port + "  /   "
                    + connectionDN);
            ldapConn.connect(server, port);
            ldapConn.bind(
                    LDAPConnection.LDAP_V3,
                    connectionDN,
                    escapeInjection(propUtil.get("userPassword")).getBytes(
                            "UTF-8"));
            return ldapConn;
        } catch (NumberFormatException e) {
            log.fatal("NumberFormatException throws - " + e.getMessage());
            throw new GenericException(domain, "ERR-LDA-0001",
                    "LDAPConnectionManager->getInstance() Invalid port number",
                    e);
        } catch (LDAPException e) {
            log.fatal("LDAPException throws - " + e);
            throw new GenericException(domain, "ERR-LDA-0001",
                    "LDAPConnectionManager->getInstance()", e);
        } catch (Exception e) {
            log.fatal("Exception throws - " + e.getMessage());
            throw new GenericException(domain, "ERR-LDA-0001",
                    "LDAPConnectionManager->getInstance()", e);
        }
    }

    @Override
    public String escapeInjection(String param) {
        if (!ldapinject.isSafeString(param)) {
            return ldapinject.getContent();
        }
        return param;
    }

    private String getDN() {
        String first_dn = ldapinject.encodeForDN(propUtil.get("dn_firstname"));
        String middle_dn = ldapinject
                .encodeForDN(propUtil.get("dn_middlename"));
        String last_dn = ldapinject.encodeForDN(propUtil.get("dn_lastname"));
        String dn = first_dn + "," + middle_dn + "," + last_dn;
        if (isEmpty(first_dn)) {
            dn = middle_dn + "," + last_dn;
        }
        return dn;
    }
}
