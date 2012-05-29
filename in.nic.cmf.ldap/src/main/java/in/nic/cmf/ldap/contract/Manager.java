package in.nic.cmf.ldap.contract;

import in.nic.cmf.logger.LogTracer;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface Manager.
 */
public interface Manager {

    /** The log. */
    public LogTracer log = null;

    /**
     * Sets the log tracer.
     * @param log
     *            the new log tracer
     */
    public void setLogTracer(LogTracer log);

    /**
     * Search based on username.
     * @param username
     *            the ldap user name
     * @return the map is a search result
     */
    public Map<String, String> search(String username, String entity);

    /**
     * Creates new user in ldap.
     * @param attribute
     *            the map contains userName, password, profileId, userRole,
     *            isActive informations
     * @return true, if successful
     */
    public boolean create(Map<String, String> attribute);

    /**
     * Update user info in ldap server.
     * @param attribute
     *            the map contains userName, password, newPassword, profileId,
     *            userRole, isActive informations
     * @return true, if successful
     */
    public boolean update(Map<String, String> attribute);

    /**
     * Delete user info from ldap server.
     * @param userProfileId
     *            the user profile id to be remove
     * @return true, if successful
     */
    public boolean delete(String userProfileId);

    public Map<String, String> searchById(String profileId);

    public String escapeInjection(String param);
}
