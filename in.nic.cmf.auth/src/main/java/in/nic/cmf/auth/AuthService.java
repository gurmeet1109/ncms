package in.nic.cmf.auth;

import in.nic.cmf.cache.RedisCache;
import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.util.APIKey;
import in.nic.cmf.util.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServiceName ("auth")
public class AuthService implements CMFService {

    private LogTracer      log = null;
    private PropertiesUtil propUtil;

    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "auth");
        if ((isEmpty(parameters))
                || (isEmpty(parameters.get("input").get("content")))) {
            log.fatal("Input content is empty");
            throw new GenericException(domain, "ERR-AU-0002");
        }
        String format = (String) parameters.get("input").get("format");
        if (isEmpty(format)) {
            log.fatal("Response format is empty");
            throw new GenericException(domain, "ERR-AU-0002");
        }
        log.debug("\n\n Inside Auth service : Input content -- "
                + (String) parameters.get("input").get("content"));
        HashMap<String, Object> inputContent = FormatXml.getInstanceof(domain)
                .in(parameters.get("input").get("content").toString());
        Object content = null;
        if (inputContent.containsKey(propUtil.get("rootTag"))) {
            inputContent = (HashMap<String, Object>) inputContent.get(propUtil
                    .get("rootTag"));
        }
        if (inputContent.containsKey(propUtil.get("entityType"))) {
            content = inputContent.get(propUtil.get("entityType"));
        } else {
            return buildUnMatchedEntityResponse(parameters);
        }
        if (content instanceof List) {
            log.fatal("Multiple user check not allowed");
            throw new GenericException(domain, "ERR-AU-0006");
        }

        log.debug("Input collections : " + content);
        HashMap<String, Object> actualContent = (HashMap<String, Object>) content;
        String authUserName = (String) actualContent.get("AuthUserName");
        String authPassword = (String) actualContent.get("Password");
        String ldapEntityType = (String) actualContent.get("LdapEntityType");

        String ldapAuth = "";
        String api_key = "";
        String aclrole = "";
        try {
            if (isEmpty(ldapEntityType)) {
                ldapEntityType = propUtil.get("ldapEntityType");
            }
            String remoteIP = ((HashMap<String, String>) parameters
                    .get("input").get("userContext")).get("requester");
            Map<String, String> queryParams = (Map<String, String>) parameters
                    .get("input").get("queryParams");
            String auth_token = (String) queryParams.get("auth_token");
            Map<String, String> cookieParams = (Map<String, String>) parameters
                    .get("input").get("cookieParams");
            if (cookieParams.get("auth_token") != null) {
                auth_token = (String) cookieParams.get("auth_token");
            }
            log.debug("auth_token -- " + auth_token + " :: remoteIP -- "
                    + remoteIP);
            RedisCache redisCache = RedisCache.getInstanceof(domain);
            String authToken = null;
            synchronized (redisCache) {
                authToken = redisCache.get(auth_token + "_" + remoteIP);
            }
            if (authToken == null) {
                log.fatal("Auth token is empty");
                throw new GenericException(domain, "ERR-AU-0005");
            }
            String dynaSalt = Utils.getInstanceof(domain).getMD5(
                    propUtil.get("encodeChars") + auth_token + remoteIP);
            log.debug("saltkey -- " + dynaSalt);
            String ldapResponse = doLdapAuth(authUserName, authPassword,
                    domain, ldapEntityType, dynaSalt, parameters);
            HashMap<String, Object> ldapResponseMap = FormatXml.getInstanceof(
                    domain).in(ldapResponse);
            ldapResponseMap = (HashMap<String, Object>) ldapResponseMap
                    .get(propUtil.get("rootTag"));
            ldapResponseMap = (HashMap<String, Object>) ldapResponseMap
                    .get(ldapEntityType);
            ldapResponse = null;
            ldapAuth = (String) ldapResponseMap.get("Status");
            if (ldapAuth.equals("true")) {
                aclrole = (String) ldapResponseMap.get("UserRole");
                ldapResponseMap = null;
                api_key = generateAPIKey(authUserName + auth_token, remoteIP,
                        aclrole, domain);
                parameters.put(
                        "output",
                        generateSuccessOutput(authUserName, api_key, aclrole,
                                format, auth_token));
                synchronized (redisCache) {
                    redisCache.set(auth_token + "_" + remoteIP, domain,
                            propUtil.getAsInt("max_ttl"));
                }
                return parameters;
            }
            ldapResponseMap = null;
        } catch (GenericException e) {
            log.fatal("GenericException  throws : " + e.getMessage());
            throw e;
        }
        log.fatal(authUserName + "[" + domain + "]" + " 401 Unauthorized user");
        throw new GenericException(domain, "ERR-AU-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-AU-0004");
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "auth");
        Map<String, String> queryParams = (Map<String, String>) parameters.get(
                "input").get("queryParams");
        Map<String, String> userContext = (Map<String, String>) parameters.get(
                "input").get("userContext");
        String requesterIP = userContext.get("requester");
        String auth_token = queryParams.get("auth_token");
        RedisCache redisCache = RedisCache.getInstanceof(domain);
        synchronized (redisCache) {
            redisCache.delete(auth_token + "_" + requesterIP);
        }
        log.debug("Auth token :: " + auth_token + "_" + requesterIP
                + " is deleted from redis!");
        return buildResponse("200", "success", "application/json", parameters);
    }

    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "auth");
        String auth_token = generateAuthToken(domain);
        log.debug("auth_token -- " + auth_token);
        HashMap<String, String> userContext = (HashMap<String, String>) parameters
                .get("input").get("userContext");
        String requesterIP = userContext.get("requester");
        if (isEmpty(auth_token) || isEmpty(requesterIP)) {
            throw new GenericException(domain, "ERR-AU-0002");
        }
        log.debug("redis cache key -- " + auth_token + " :: min_ttl - "
                + propUtil.get("min_ttl") + " :: " + propUtil.get("max_ttl"));
        log.debug("Before in redis set : " + auth_token + " :::: "
                + propUtil.get("min_ttl"));
        RedisCache redisCache = RedisCache.getInstanceof(domain);
        synchronized (redisCache) {
            redisCache.set(auth_token + "_" + requesterIP, domain,
                    propUtil.getAsInt("min_ttl"));
        }
        log.debug("auth_token stroed @ redis! - " + auth_token);
        log.debug("auth_token : " + auth_token + " :: remoteIP : "
                + requesterIP);
        String dynaSaltMD5 = Utils.getInstanceof(domain).getMD5(
                propUtil.get("encodeChars") + auth_token + requesterIP);
        String content = "{\"AuthToken\":\"" + auth_token
                + "\",\"DynaSalt\":\"" + dynaSaltMD5 + "\"}";
        return buildResponse("200", content, "application/json", parameters);
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-AU-0004");
    }

    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-AU-0004");
    }

    /**
     * Builds the un matched entity response.
     * @param parameters
     *            the parameters
     * @return the hash map
     */
    private Map<String, Map<String, Object>> buildUnMatchedEntityResponse(
            Map<String, Map<String, Object>> parameters) {
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", "200");
        output.put("content", parameters.get("input").get("content").toString());
        parameters.get("output").putAll(output);
        return parameters;
    }

    private String generateAuthToken(String domain) throws GenericException {
        SecureRandom prng;
        byte[] result = null;
        try {
            prng = SecureRandom.getInstance("SHA1PRNG");
            String randomNum = new Integer(prng.nextInt()).toString();
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            result = sha.digest(randomNum.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new GenericException(domain, "ERR-AU-0002");
        }
        return hexEncode(result);
    }

    private String hexEncode(byte[] aInput) {
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f' };
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }

    private Map<String, Map<String, Object>> buildResponse(String statusCode,
            String content, String contentType,
            Map<String, Map<String, Object>> parameters) {
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", statusCode);
        output.put("content", content);
        parameters.put("output", output);
        HashMap<String, Object> headers = new HashMap<String, Object>();
        headers.put("Content-Type", contentType);
        parameters.get("output").put("headers", headers);
        return parameters;
    }

    /**
     * Generate success output.
     * @param authusername
     *            the authusername
     * @param api_key
     *            the api_key
     * @param aclrole
     *            the aclrole
     * @param format
     *            the format
     * @return the hash map
     */
    private HashMap<String, Object> generateSuccessOutput(String authusername,
            String api_key, String aclrole, String format, String auth_token) {
        HashMap<String, String> element = new HashMap<String, String>();
        element.put("AuthUserName", authusername);
        element.put("ApiKey", api_key);
        element.put("AclRole", aclrole);
        element.put("AuthToken", auth_token);
        element.put("success", "true");
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", 200);
        if (format.equalsIgnoreCase("extjson")) {
            output.put("content", element);
        } else {
            HashMap<String, Object> authentication = new HashMap<String, Object>();
            authentication.put(propUtil.get("entityType"), element);
            HashMap<String, Object> collection = new HashMap<String, Object>();
            collection.put(propUtil.get("rootTag"), authentication);
            output.put("content", collection);
        }
        return output;
    }

    /**
     * Generate api key.
     * @param username
     *            the username
     * @param remoteIP
     *            the remote ip
     * @param aclrole
     *            the aclrole
     * @param domain
     *            the domain
     * @return the string
     * @throws GenericException
     *             the generic exception
     */
    private String generateAPIKey(String username, String remoteIP,
            String aclrole, String domain) throws GenericException {
        APIKey apikey = APIKey.getInstanceof(domain);
        String generatedApikey = apikey.generateAPIKey(username, remoteIP,
                aclrole, domain);
        apikey = null;
        return generatedApikey;
    }

    private String doLdapAuth(String username, String password, String domain,
            String ldapEntityType, String dynaSalt,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        log.debug("doLdapAuth : " + username + "@" + domain);
        parameters.get("input").put("queryString",
                propUtil.get("afterDomainldapurl"));
        ((Map<String, String>) parameters.get("input").get("queryParams")).put(
                "action", "checkauth");
        HashMap<String, Object> entity = new HashMap<String, Object>();
        entity.put("EntityType", ldapEntityType);
        entity.put("UserName", username);
        entity.put("Password", password);
        entity.put("SaltKey", dynaSalt);
        HashMap<String, Object> entityType = new HashMap<String, Object>();
        entityType.put(ldapEntityType, entity);
        HashMap<String, Object> collection = new HashMap<String, Object>();
        collection.put("Collections", entityType);
        String content = (String) FormatXml.getInstanceof(domain).out(
                collection);
        entity = null;
        entityType = null;
        collection = null;
        parameters.get("input").put("content", content);
        ServiceClientImpl serviceClientImpl = ServiceClientImpl.getInstance(
                domain, "ldap");

        parameters = serviceClientImpl.add(domain, ldapEntityType, parameters);

        log.debug("Service client response -- " + parameters);
        String response = (String) parameters.get("output").get("content");
        int responseCode = (Integer) parameters.get("output").get("statusCode");
        log.debug("doLdapAuth : " + username + "@" + domain
                + " ... Responsecode ::: " + responseCode);
        log.debug("doLdapAuth : " + username + "@" + domain
                + " ... Response : " + response);
        if (responseCode == 200) {
            return response;
        } else {
            HashMap<String, Object> formattedError = FormatJson.getInstanceof(
                    domain).in(response);
            if (!formattedError.get("httpCode").equals("500")) {
                throw new GenericException(domain, "ERR-AU-0002");
            }
            throw new GenericException(domain, "ERR-AU-0003");
        }
    }

    private boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return ((String) object).isEmpty();
        }
        return false;
    }
}
