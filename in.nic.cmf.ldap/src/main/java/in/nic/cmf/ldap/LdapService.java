package in.nic.cmf.ldap;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.ldap.contract.Manager;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * The Class LdapService.
 */
@ServiceName ("ldap")
public class LdapService implements CMFService {

    /** The log. */
    public LogTracer                log;

    /** The prop util. */
    private PropertiesUtil          propUtil;

    /** The mgr. */
    private Manager                 mgr = null;
    private HashMap<String, String> entitytype;

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#add(java.lang.String,
     * java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "ldap");
        entitytype = getEntityType();
        mgr = LDAPManager.getInstance(domain);
        log = new LogTracer(domain, "ldap");
        mgr.setLogTracer(log);

        log.debug("domain : " + domain + " :: entity : " + entity);
        if (isEmpty(parameters)) {
            log.debug("parameter is empty @ add()");
            throw new GenericException(domain, "ERR-LDA-0009");
        }
        if (isEmpty(parameters.get("input").get("content"))) {
            log.debug("content is empty @ add()");
            throw new GenericException(domain, "ERR-LDA-0009");
        }
        log.debug("Parameters - " + parameters);

        HashMap<String, Object> inputContent = FormatXml.getInstanceof(domain)
                .in(parameters.get("input").get("content"));
        try {
            if (!inputContent.containsKey(propUtil.get("rootTag"))) {
                log.info("Root tag \"" + propUtil.get("rootTag")
                        + "\" missing, so return the same content");
                return buildUnMatchedEntityResponse(parameters);
            }
            inputContent = (HashMap<String, Object>) inputContent.get(propUtil
                    .get("rootTag"));
            HashMap<String, String> queryParams = (HashMap<String, String>) parameters
                    .get("input").get("queryParams");
            String action = "";
            if (!isEmpty(queryParams)) {
                action = queryParams.get("action");
            }
            Iterator<Entry<String, Object>> itr = inputContent.entrySet()
                    .iterator();
            Entry<String, Object> pairs = (Entry<String, Object>) itr.next();
            String saltKey = (String) ((HashMap<String, String>) pairs
                    .getValue()).get("SaltKey");
            String responseContent = "";
            log.debug("content -- " + inputContent);
            log.debug("list of entity -- " + getEntityTypeList());
            for (String entityType : getEntityTypeList()) {
                if (!inputContent.containsKey(entityType)) continue;
                Object content = inputContent.get(entityType);
                String result = getResponseContent(domain, entityType, content,
                        action, saltKey);
                if (isEmpty(result)) continue;
                responseContent += result;
            }
            log.debug("Response Content -- " + responseContent);
            if (isEmpty(responseContent)) {
                return buildUnMatchedEntityResponse(parameters);
            }
            responseContent = "<" + propUtil.get("rootTag") + ">"
                    + responseContent + "</" + propUtil.get("rootTag") + ">";
            log.debug("pes content -- " + responseContent);
            return createOutputParameters(domain,
                    FormatXml.getInstanceof(domain).in(responseContent),
                    parameters);
        } catch (GenericException e) {
            log.fatal("GenericException throws - " + e.getMessage());
            throw e;
        }
    }

    private String getResponseContent(String domain, String entity,
            Object content, String action, String saltKey)
            throws GenericException {
        String response = null;
        if (content instanceof HashMap) {
            response = performAction(domain, entity,
                    (HashMap<String, Object>) content, action, saltKey);
        } else if (content instanceof List) {
            for (HashMap<String, Object> hashContent : (List<HashMap<String, Object>>) content) {
                response += performAction(domain, entity, hashContent, action,
                        saltKey);
            }
        }
        return response;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#deleteByID(java.lang.String,
     * java.lang.String, java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "ldap");
        entitytype = getEntityType();
        mgr = LDAPManager.getInstance(domain);
        log = new LogTracer(domain, "ldap");
        mgr.setLogTracer(log);

        if (!entitytype.containsKey(entity.toLowerCase())) {
            log.fatal("Entity mismatch found");
            throw new GenericException(domain, "ERR-LDA-0010");
        }
        boolean status = isValidParameter(id, propUtil.get("profileIdPattern"));
        if (!status) {
            log.fatal("Profile id pattern mismatch found");
            throw new GenericException(domain, "ERR-LDA-0011");
        }
        id = mgr.escapeInjection(id);
        status = mgr.delete(id);
        HashMap<String, Object> responseAttribute = new HashMap<String, Object>();
        responseAttribute.put("ProfileId", id);
        responseAttribute.put("Status", Boolean.toString(status));
        log.debug("Delete status :- " + responseAttribute);
        return createOutputParameters(
                domain,
                getCollections(entitytype.get(entity.toLowerCase()),
                        responseAttribute), parameters);
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#deleteByQuery(java.lang.String,
     * java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-LDA-0007");
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#find(java.lang.String,
     * java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        propUtil = PropertiesUtil.getInstanceof(domain, "ldap");
        entitytype = getEntityType();
        mgr = LDAPManager.getInstance(domain);
        log = new LogTracer(domain, "ldap");
        mgr.setLogTracer(log);

        HashMap<String, Object> formParams = (HashMap<String, Object>) parameters
                .get("input").get("formParams");
        if (isEmpty(formParams)) {
            log.fatal("Form params are empty");
            throw new GenericException(domain, "ERR-LDA-0009");
        }
        List<String> userList = (List<String>) formParams.get("username");
        String userName = userList.get(0);
        if (isEmpty(userName)) {
            log.fatal("User name is empty");
            throw new GenericException(domain, "ERR-LDA-0009");
        }
        if (!isEmpty(entity) && !entitytype.containsKey(entity.toLowerCase())) {
            log.fatal("Entity mismatch found");
            throw new GenericException(domain, "ERR-LDA-0010");
        }
        if (isEmpty(entity)) {
            entity = getEntityTypeList().get(0);
        }
        userName += "@" + domain;
        userName = mgr.escapeInjection(userName);
        entity = mgr.escapeInjection(entity);
        boolean status = isValidParameter(userName,
                propUtil.get("usernamePattern"));
        if (!status) {
            log.fatal("Username pattern mismatch found");
            throw new GenericException(domain, "ERR-LDA-0011");
        }
        Map<String, String> attribute = mgr.search(userName, entity);
        HashMap<String, Object> responseAttribute = new HashMap<String, Object>();
        responseAttribute.put("UserName", getUserName(userName));
        responseAttribute.put("Status", "false");
        entity = entitytype.get(entity.toLowerCase());
        if (!isEmpty(attribute)) {
            responseAttribute.put("Status", "true");
            responseAttribute.put("Id", attribute.get("profileId"));
            responseAttribute.put("UserRole", attribute.get("role"));
            responseAttribute.put("IsActive", attribute.get("isActive"));
            String entityStr = attribute.get("entity");
            if (!isEmpty(entityStr) && !entityStr.equalsIgnoreCase(entity)) {
                log.fatal("Entity mismatch found");
                throw new GenericException(domain, "ERR-LDA-0010");
            }
            entity = entityStr;
        }
        log.debug(userName + " find response : " + responseAttribute.toString());
        return createOutputParameters(domain,
                getCollections(entity, responseAttribute), parameters);
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#findById(java.lang.String,
     * java.lang.String, java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {

        propUtil = PropertiesUtil.getInstanceof(domain, "ldap");
        entitytype = getEntityType();
        mgr = LDAPManager.getInstance(domain);
        log = new LogTracer(domain, "ldap");
        mgr.setLogTracer(log);

        if (!entitytype.containsKey(entity.toLowerCase())) {
            log.fatal("Entity mismatch found");
            throw new GenericException(domain, "ERR-LDA-0010");
        }
        String userName = id;
        userName += "@" + domain;
        userName = mgr.escapeInjection(userName);
        entity = mgr.escapeInjection(entitytype.get(entity.toLowerCase()));
        boolean status = isValidParameter(userName,
                propUtil.get("usernamePattern"));
        if (!status) {
            log.fatal("Username pattern mismatch found");
            throw new GenericException(domain, "ERR-LDA-0011");
        }
        Map<String, String> attribute = mgr.search(userName, entity);

        HashMap<String, Object> responseAttribute = new HashMap<String, Object>();
        responseAttribute.put("UserName", getUserName(userName));
        responseAttribute.put("Status", "false");
        if (!isEmpty(attribute)) {
            if (!(attribute.get("entity").equals(entity))) {
                log.fatal("Entity mismatch found");
                throw new GenericException(domain, "ERR-LDA-0010");
            }
            responseAttribute.put("Status", "true");
            responseAttribute.put("Id", attribute.get("profileId"));
            responseAttribute.put("UserRole", attribute.get("role"));
            responseAttribute.put("IsActive", attribute.get("isActive"));
        }
        log.debug(userName + " find response : " + responseAttribute.toString());
        return createOutputParameters(domain,
                getCollections(entity, responseAttribute), parameters);
    }

    private HashMap<String, Object> getCollections(String entity,
            HashMap<String, Object> responseAttr) {
        HashMap<String, Object> collections = new HashMap<String, Object>();
        HashMap<String, Object> cmsUserProfile = new HashMap<String, Object>();
        cmsUserProfile.put(entity, responseAttr);
        collections.put(propUtil.get("rootTag"), cmsUserProfile);
        return collections;
    }

    private List<String> getEntityTypeList() {
        String[] entityTypes = propUtil.get("entityType").split(",");
        return Arrays.asList(entityTypes);
    }

    private HashMap<String, String> getEntityType() {
        HashMap<String, String> entityInfo = new HashMap<String, String>();
        for (String entityType : getEntityTypeList()) {
            entityInfo.put(entityType.toLowerCase(), entityType);
        }
        return entityInfo;
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.contract.CMFService#setLogTracer(in.nic.cmf.logger.LogTracer)
     */
    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    /**
     * Perform action. Its a supportive method to invoke create, update, and
     * check auth functions.
     * @param domain
     *            the user data posted domain name
     * @param entity
     *            the user request action create, update and check
     *            authentication
     * @param actualContent
     *            the actual content is an HashMap which is contain
     *            CmfUserProfile XML data
     * @param action
     *            the action
     * @return the string the requested actions create, update, and check auth
     *         functional response as xml string.
     */
    private String performAction(String domain, String entity,
            HashMap<String, Object> actualContent, String action, String saltKey)
            throws GenericException {
        String userName, password, profileId, aclRole, isActive, oldPassword;
        userName = password = profileId = aclRole = isActive = oldPassword = null;
        userName = (String) actualContent.get("UserName");
        if (!isValidParameter(userName, propUtil.get("usernamePattern"))) {
            log.fatal("Username pattern mismatch found");
            throw new GenericException(domain, "ERR-LDA-0011",
                    "Username mismatch with pattern");
        }
        userName += "@" + domain;
        log.debug("Entity : " + entity + " UserName : " + userName);
        log.debug("Action : " + action);
        entity = mgr.escapeInjection(entity);
        userName = mgr.escapeInjection(userName);
        oldPassword = mgr.escapeInjection((String) actualContent
                .get("OldPassword"));
        password = mgr.escapeInjection((String) actualContent.get("Password"));
        profileId = mgr.escapeInjection((String) actualContent.get("Id"));
        aclRole = mgr.escapeInjection((String) actualContent.get("UserRole"));
        isActive = mgr.escapeInjection((String) actualContent.get("IsActive"));
        if ((!isEmpty(action)) && (action.equalsIgnoreCase("checkauth"))) {
            return checkAuth(domain, entity, userName, password, saltKey);
        }
        Map<String, String> attribute = mgr.search(userName, entity);
        if (isEmpty(attribute)) {
            return createUser(domain, entity, userName, password, profileId,
                    aclRole, isActive);
        }
        log.info("Parameter - Input ProfileId : " + profileId + " Search of : "
                + userName + "profile id : " + attribute.get("profileId"));
        if (profileId.equals(attribute.get("profileId"))) {
            return updateUserInfo(domain, entity, userName, oldPassword,
                    password, profileId, aclRole, isActive);
        }
        log.info("UserName : " + userName
                + " already exist... Current user ProfileId is : " + profileId);
        throw new GenericException(domain, "ERR-LDA-0005");
    }

    /**
     * Checks if is valid parameter.
     * @param data
     *            the data
     * @param patternFormat
     *            the pattern format
     * @return true, if is valid parameter
     * @throws GenericException
     *             the generic exception
     */
    private boolean isValidParameter(String data, String patternFormat)
            throws GenericException {
        boolean status = false;
        try {
            if (!isEmpty(data)) {
                CharSequence dataSequence = data;
                Pattern pattern = Pattern.compile(patternFormat);
                Matcher matcher = pattern.matcher(dataSequence);
                status = matcher.matches();
            }
        } catch (PatternSyntaxException e) {
            log.fatal("PatternSyntaxException throws - " + e.getMessage());
            throw new GenericException("ERR-LDA-0011",
                    "LdapService->isValidParameter() pattern mismatched", e);
        }
        return status;
    }

    /**
     * Checks if is valid parameter.
     * @param parameters
     *            the parameters
     * @return true, if is valid parameter
     * @throws GenericException
     *             the generic exception
     */
    private boolean isValidParameter(Map<String, String> parameters)
            throws GenericException {
        boolean status = false;
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String patternFormat = propUtil.get(key + "Pattern");
            status = isValidParameter(value, patternFormat);
            if (!status) {
                break;
            }
        }
        return status;
    }

    /**
     * Checks if is empty.
     * @param data
     *            the data
     * @return true, if is empty
     */
    private boolean isEmpty(Object data) {
        if (data != null) {
            if (data instanceof String) {
                return (data.toString()).isEmpty();
            }
            return false;
        }
        return true;
    }

    /**
     * Creates the user.
     * @param userName
     *            the user name
     * @param password
     *            the password
     * @param profileId
     *            the profile id
     * @param aclrole
     *            the aclrole
     * @param isActive
     *            the is active
     * @return the string
     * @throws GenericException
     *             the generic exception
     */
    private String createUser(String domain, String entity, String userName,
            String password, String profileId, String aclrole, String isActive)
            throws GenericException {
        Map<String, String> attribute = new HashMap<String, String>();
        attribute.put("username", userName);
        attribute.put("password", password);
        attribute.put("profileId", profileId);

        boolean status = isValidParameter(attribute);
        attribute.clear();
        if (!status) {
            log.fatal("Username/Password/ProfileId pattern mismatch found");
            throw new GenericException(domain, "ERR-LDA-0011");
        }
        attribute.put("profileId", profileId);
        attribute.put("userPassword", password);
        attribute.put("sn", aclrole);
        attribute.put("Title", isActive);
        attribute.put("Street", userName);
        attribute.put("l", entity);
        status = mgr.create(attribute);
        attribute = null;

        log.info("username : " + userName + " **** Status : " + status);
        return buildResponse(domain, entity, userName, status);
    }

    /**
     * Check auth.
     * @param userName
     *            the user name
     * @param password
     *            the password
     * @return the string
     * @throws GenericException
     *             the generic exception
     */
    private String checkAuth(String domain, String entity, String userName,
            String password, String saltKey) throws GenericException {
        Map<String, String> attribute = new HashMap<String, String>();
        attribute.put("username", userName);
        attribute.put("password", password);
        boolean status = isValidParameter(attribute);
        attribute.clear();
        if (!status) {
            log.fatal("Username/Password pattern mismatch found!");
            throw new GenericException(domain, "ERR-LDA-0011");
        }
        attribute = mgr.search(userName, entity);
        if (attribute == null) {
            log.debug("No search data found");
            return buildResponse(domain, entity, userName, "false");
        }
        String userPassword = attribute.get("password");
        log.debug("SaltKey -- " + saltKey);
        log.debug("Query password -- " + password);
        log.debug("Openldap password - md5 : " + userPassword);
        userPassword = Utils.getInstanceof(domain).getMD5(
                userPassword + saltKey);
        log.debug("openldap password with salt key :- " + userPassword);
        if (!(password.equals(userPassword))) {
            log.debug("current password is not matched");
            return buildResponse(domain, entity, userName, "false");
        }
        String userStatus = attribute.get("isActive");
        String role = attribute.get("role");
        String profileId = attribute.get("profileId");
        attribute = null;
        HashMap<String, String> responseAttribute = new HashMap<String, String>();
        responseAttribute.put("UserName", getUserName(userName));
        responseAttribute.put("Status", userStatus);
        if (userStatus.equalsIgnoreCase("true")) {
            responseAttribute.put("Id", profileId);
            responseAttribute.put("UserRole", role);
        }
        return buildResponse(domain, entity, responseAttribute);
    }

    /**
     * Update user info.
     * @param userName
     *            the user name
     * @param password
     *            the password
     * @param profileId
     *            the profile id
     * @param aclrole
     *            the aclrole
     * @param activeState
     *            the active state
     * @return the string
     */
    private String updateUserInfo(String domain, String entity,
            String userName, String oldPassword, String password,
            String profileId, String aclrole, String activeState)
            throws GenericException {
        Map<String, String> attribute = mgr.search(userName, entity);
        if (attribute == null) {
            log.fatal("Invalid username found for update.");
            throw new GenericException(domain, "ERR-LDA-0002");
        }
        if (!(attribute.get("entity").equalsIgnoreCase(entity))) {
            log.fatal("EntityType mismatch found.");
            throw new GenericException(domain, "ERR-LDA-0010");
        }
        log.debug("search userinfo : " + attribute);
        String searchPassword = attribute.get("password");
        attribute.clear();

        attribute.put("profileId", profileId);
        // Reset Password
        if (isEmpty(oldPassword) && !(isEmpty(password))) {
            log.info("Reset password invoked!");
            if (isValidParameter(password, propUtil.get("passwordPattern"))) {
                attribute.put("userPassword", password);
            }
        } else if (!(isEmpty(oldPassword)) && !(isEmpty(password))) { // Change
                                                                      // Password
            log.info("Change password invoked!");
            if (!(oldPassword.equals(searchPassword))) {
                log.fatal("Invalid old password");
                throw new GenericException(domain, "ERR-LDA-0004");
            }
            if (isValidParameter(password, propUtil.get("passwordPattern"))) {
                attribute.put("userPassword", password);
            }
        } else { // Edit Profile
            log.info("Edit profile invoked!");
            if (!isEmpty(aclrole)) {
                attribute.put("sn", aclrole);
            }
            if (!isEmpty(activeState)) {
                attribute.put("Title", activeState);
            }
        }
        log.debug("Update content -- " + attribute);
        boolean status = mgr.update(attribute);
        attribute = null;
        return buildResponse(domain, entity, userName, status);
    }

    /**
     * Builds the response.
     * @param attribute
     *            the attribute
     * @return the string
     */
    private String buildResponse(String domain, String entity,
            HashMap<String, String> attribute) {
        HashMap<String, Object> root = new HashMap<String, Object>();
        root.put(entity, attribute);
        Object response = FormatXml.getInstanceof(domain).out(root);
        root = null;
        return response.toString();
    }

    /**
     * Builds the response.
     * @param userName
     *            the user name
     * @param responseMsg
     *            the response msg
     * @return the string
     */
    private String buildResponse(String domain, String entity, String userName,
            String responseMsg) {
        HashMap<String, String> attribute = new HashMap<String, String>();
        attribute.put("UserName", getUserName(userName));
        attribute.put("Status", responseMsg);
        return buildResponse(domain, entity, attribute);
    }

    /**
     * Builds the response.
     * @param userName
     *            the user name
     * @param status
     *            the status
     * @return the string
     */
    private String buildResponse(String domain, String entity, String userName,
            boolean status) {
        return buildResponse(domain, entity, userName, Boolean.toString(status));
    }

    /**
     * Gets the user name.
     * @param userName
     *            the user name
     * @return the user name
     */
    private String getUserName(String userName) {
        return userName.substring(0, userName.lastIndexOf("@"));
    }

    /**
     * Creates the output parameters.
     * @param response
     *            the response
     * @param parameters
     *            the parameters
     * @return the hash map
     */
    private Map<String, Map<String, Object>> createOutputParameters(
            String domain, HashMap<String, Object> response,
            Map<String, Map<String, Object>> parameters) {
        parameters.put("output", new HashMap<String, Object>());
        parameters.get("output").put("statusCode", 200);
        log.debug("response" + response);
        FormatXml formatXml = FormatXml.getInstanceof(domain);
        String output = (String) formatXml.out(response);
        parameters.get("output").put("content", output);
        return parameters;
    }

    /**
     * Builds the unmatched entity response.
     * @param parameters
     *            the parameters
     * @return the hash map
     */
    private Map<String, Map<String, Object>> buildUnMatchedEntityResponse(
            Map<String, Map<String, Object>> parameters) {
        parameters.put("output", new HashMap<String, Object>());
        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", 200);
        output.put("content", (String) parameters.get("input").get("content"));
        parameters.get("output").putAll(output);
        return parameters;
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> arg2) {
        throw new GenericException(domain, "ERR-LDA-0007");
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
}
