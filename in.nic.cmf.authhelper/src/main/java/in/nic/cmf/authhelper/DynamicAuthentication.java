package in.nic.cmf.authhelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Dynamic authentication will be used to authenticate users based on
 * domain,role or username,password,domain. authhelper;
 */
public class DynamicAuthentication {
    /**
     * It will store authentication response from signin authentication system.
     */
    private String         authentication = null;
    /**
     * It will store authentication user name.
     */
    private String         authUsername   = null;
    /**
     * It will store authentication user password.
     */
    private String         authPassword   = null;
    /**
     * It will store domain name.
     */
    private String         domain         = null;
    /**
     * It will store {@link PropertiesUtil} object.
     */
    private PropertiesUtil propUtil;
    /**
     * LogTracer Object {@link LogTracer}.
     */
    private LogTracer      log;

    /**
     * private constructor.
     */
    private DynamicAuthentication() {
    }

    /**
     * Argument Constructor.
     * @param role
     *            user role
     * @param domain
     *            domain name
     * @throws GenericException
     *             {@link GenericException}
     */
    public DynamicAuthentication(final String role, final String domain)
                                                                        throws GenericException {
        setDomain(domain);
        setDomainAuth(role, domain);
    }

    /**
     * Argument Constructor.
     * @param authusername
     *            user name
     * @param password
     *            password
     * @param domain
     *            domain anme
     * @throws GenericException
     *             {@link GenericException}
     */
    public DynamicAuthentication(final String authusername,
                                 final String password, final String domain)
                                                                            throws GenericException {
        setDomain(domain);
        setAuthUsername(authusername);
        setAuthPassword(password);
    }

    /**
     * It will communicate authentication system based on authentication URL and
     * set authentication
     * @return boolean
     * @throws GenericException
     *             {@link GenericException}
     */
    public boolean autoSignin() throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(getDomain(), "authhelper");
        log = new LogTracer(getDomain(), "authhelper");

        log.debug("autoSignin() process start.... " + getAuthUsername() + "/"
                + getAuthPassword());
        if (isEmpty(getAuthUsername()) || isEmpty(getAuthPassword())) {
            throw new GenericException("ERR-AUH-0003", this.getClass()
                    .getSimpleName()
                    + ".autoSignin() username/password is null");
        }
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Convertor convertor = null;
        Map<String, Object> authinfo = null;
        try {
            String responseCode = "";
            String responseValue = "";
            log.debug(getDomain() + " ::: " + getAuthUsername() + " ::: "
                    + getAuthPassword());
            log.debug("before auth sc instance");

            // Authentication GET Request to fetch Dynamic SALT key
            log.debug("after auth sc instance");
            authinfo = getDynamicSaltInfo();
            log.debug("Auth information :- " + authinfo);
            if (isEmpty(authinfo)) {
                log.debug("Dynamic SALT key get return is null");
            }
            String dynaSalt = (String) authinfo.get("DynaSalt");
            log.debug("DynaSalt -- " + dynaSalt);
            Utils utils = Utils.getInstanceof(getDomain());
            log.info("Reset AuthPassword : " + getAuthPassword() + ":::"
                    + dynaSalt);
            setAuthPassword(utils.getMD5(getAuthPassword() + dynaSalt));

            HashMap<String, Object> entity = new HashMap<String, Object>();
            entity.put("EntityType", propUtil.get("entityType"));
            entity.put("AuthUserName", getAuthUsername());
            entity.put("Password", getAuthPassword());
            log.debug("Auth Parameter :::: " + entity);

            HashMap<String, Object> entityType = new HashMap<String, Object>();
            entityType.put(propUtil.get("entityType"), entity);
            HashMap<String, Object> collection = new HashMap<String, Object>();
            collection.put("Collections", entityType);
            String content = (String) FormatXml.getInstanceof(getDomain()).out(
                    collection);
            entity = null;
            entityType = null;
            collection = null;
            String auth_token = (String) authinfo.get("AuthToken");
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("queryString", "?auth_token=" + auth_token);
            input.put("requestType", "auth");
            input.put("content", content);
            parameters.put("input", input);
            ServiceClientImpl serviceClientImpl = ServiceClientImpl
                    .getInstance(getDomain(), "auth");

            parameters = serviceClientImpl.add(getDomain(),
                    propUtil.get("entityType"), parameters);
            responseCode = (String) parameters.get("output").get("statsCode");
            log.debug("Auth response code ::: " + responseCode);
            if (!responseCode.equals("200")) {
                return false;
            }
            responseValue = (String) parameters.get("output").get("content");
            log.debug("Auth Response : " + responseValue);
            convertor = FormatXml.getInstanceof(getDomain());
            authinfo.clear();
            authinfo = convertor.in(responseValue);
            if (isEmpty(authinfo)) {
                return false;
            }
            authinfo = (HashMap<String, Object>) authinfo.get(propUtil
                    .get("rootTag"));
            authinfo = (HashMap<String, Object>) authinfo.get(propUtil
                    .get("entityType"));
            if (((String) authinfo.get("success")).equalsIgnoreCase("true")) {
                setAuthentication(responseValue);
                return true;
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        } catch (GenericException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("ERR-AUH-0002", this.getClass()
                    .getSimpleName() + ".autoSignin()", e);
        } finally {
            parameters = null;
            convertor = null;
            authinfo = null;
        }
    }

    private Map<String, Object> getDynamicSaltInfo() {
        log.debug("request for : " + getDomain() + ":"
                + propUtil.get("afterDomainAuthUrl"));
        ServiceClientImpl serviceClientImpl = ServiceClientImpl.getInstance(
                getDomain(), "auth");
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters = serviceClientImpl.find(domain, propUtil.get("entityType"),
                parameters);
        String responseCode = (String) parameters.get("output").get(
                "statusCode");
        if (!responseCode.equals("200")) {
            return null;
        }
        String responseValue = (String) parameters.get("output").get("content");
        if (isEmpty(responseValue)) {
            return null;
        }
        Map<String, Object> authinfo = FormatJson.getInstanceof(getDomain())
                .in(responseValue);
        return authinfo;
    }

    /**
     * It will set authentication.
     * @param authentication
     *            authentication void
     */
    private void setAuthentication(final String authentication) {
        this.authentication = authentication;
    }

    /**
     * it will return authentication
     * @return String authentication
     */
    public String getAuthentication() {
        return authentication;
    }

    /**
     * It will set authUsername.
     * @param authUsername
     *            user name void
     */
    private void setAuthUsername(final String authUsername) {
        this.authUsername = authUsername;
    }

    /**
     * it will return authentication user name.
     * @return String authUsername
     */
    private String getAuthUsername() {
        return authUsername;
    }

    /**
     * it will set authentication password.
     * @param authPassword
     *            authPassword void
     */
    private void setAuthPassword(final String authPassword) {
        log.debug("setAuthPassword(): " + authPassword);
        this.authPassword = authPassword;
    }

    /**
     * it will return password.
     * @return String authPassword
     */
    private String getAuthPassword() {
        return authPassword;
    }

    /**
     * it will set domain name
     * @param domain
     *            domain name
     * @throws GenericException
     *             {@link GenericException} void
     */
    private void setDomain(final String domain) throws GenericException {
        if (isEmpty(domain)) {
            throw new GenericException(domain, "ERR-AUH-0001");
        }
        this.domain = domain;
    }

    /**
     * Gets the domain.
     * @return the domain
     */
    private String getDomain() {
        return domain;
    }

    /**
     * it will validate given String is null/empty return true, otherwise false.
     * @param value
     *            String
     * @return boolean
     */
    private boolean isEmpty(Object value) {
        if (value == null) return true;
        // true if length() is 0, otherwise false
        if (value instanceof String)
            return (((String) value).trim()).isEmpty();
        return false;
    }

    /**
     * this will set authentication(user,password) based on role for specific
     * domain name,if it not available global user/password will be set for
     * authentication.
     * @param role
     *            user role
     * @param domain
     *            domain name void
     */
    private void setDomainAuth(final String role, final String domain) {
        String username = propUtil.get(domain + "-" + role + "-authUsername");
        String password = propUtil.get(domain + "-" + role + "-authPassword");
        if (isEmpty(username) && isEmpty(password)) {
            username = propUtil.get(role + "-authUsername");
            password = propUtil.get(role + "-authPassword");
        }
        setAuthUsername(username);
        setAuthPassword(password);
    }
}
