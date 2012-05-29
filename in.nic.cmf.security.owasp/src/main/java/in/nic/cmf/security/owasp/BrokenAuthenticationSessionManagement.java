package in.nic.cmf.security.owasp;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.Authenticator;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.HTTPUtilities;
import org.owasp.esapi.Logger;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.errors.AuthenticationCredentialsException;
import org.owasp.esapi.errors.AuthenticationException;
import org.owasp.esapi.errors.AuthenticationLoginException;
import org.owasp.esapi.errors.EnterpriseSecurityException;
import org.owasp.esapi.reference.DefaultUser;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * The Class BrokenAuthenticationSessionManagement.
 */
public class BrokenAuthenticationSessionManagement {

	/** The Constant USER. */
	protected static final String USER = "ESAPIUserSessionKey";

	/** The Constant logger. */
	private static final Logger logger = ESAPI.getLogger("Authenticator");

	/** The current user. */
	private final ThreadLocalUser currentUser = new ThreadLocalUser();

	/**
	 * The Class ThreadLocalUser.
	 */
	private class ThreadLocalUser extends InheritableThreadLocal<User> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		public User initialValue() {
			return User.ANONYMOUS;
		}

		/**
		 * Gets the user.
		 * 
		 * @return the user
		 */
		public User getUser() {
			return super.get();
		}

		/**
		 * Sets the user.
		 * 
		 * @param newUser
		 *            the new user
		 */
		public void setUser(User newUser) {
			super.set(newUser);
		}
	}

	/**
	 * Authenticate user.
	 * 
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	public static void main(String f[]){
		BrokenAuthenticationSessionManagement bm=new BrokenAuthenticationSessionManagement();
		//bm.isAuthenticated();
	}
	public static boolean isAuthenticated(MockHttpServletRequest request) {
		System.out.println("what is the value is::::"+request.getParameter("username") + "Password is::::"+request.getParameter("password"));
	//public static boolean isAuthenticated() {
		System.out.println("Inside isAuthenticated");
		try {
			String username = "";
			String password = "";
			/*MockHttpServletRequest request = null;
			Map map = request.getParameterMap();
			if (map.size() != 0) {
				Collection c = map.values();
				Iterator itr = c.iterator();
				String value[] = null;
				String username = "raja1";
				String password = "raja1";
				int i = 0;
				while (itr.hasNext()) {
					value = (String[]) itr.next();
					if (i == 0) {
						username = value[0];
						++i;
					} else
						password = value[0];

				}*/
			
				// DefaultUser user1 = (DefaultUser)
				// ESAPI.authenticator().createUser(username, password,
				// password);
				// To checkin the User we have to add user first in user.txt
				// file so it will verify it So when we will add
				// User in Ldap Server at the same time we will add user in
				// user.txt file by above line Code.
                 /*
				 * One possible implementation relies on the use of a thread local variable to
				 * store the current user's identity. The application is responsible for calling
				 * setCurrentUser() as soon as possible after each HTTP request is received. The
				 * value of getCurrentUser() is used in several other places in this API. This
				 * eliminates the need to pass a user object to methods throughout the library.
				 * For example, all of the logging, access control, and exception calls need
				 * access to the currently logged in user.
				 * 
				 * 
				 * The goal is to minimize the responsibility of the developer for
				 * authentication. In this example, the user simply calls authenticate with the
				 * current request and the name of the parameters containing the username and
				 * password. The implementation should verify the password if necessary, create
				 * a session if necessary, and set the user as the current user.
				 */
				
				Authenticator instance = ESAPI.authenticator();
				instance.getCurrentUser();
				 if (instance.exists(request.getParameter("username"))) {
					 
					 System.out.println("inside if condition");
 	            		instance.createUser(request.getParameter("username"),request.getParameter("password"),request.getParameter("password"));
 	            		username = request.getParameter("username");
 	            		password = request.getParameter("password");
 					}
				  if (!instance.exists(request.getParameter("username"))) {
					 
					 System.out.println("inside if condition");
 	            		instance.createUser(request.getParameter("username"),request.getParameter("password"),request.getParameter("password"));
 	            		username = request.getParameter("username");
 	            		password = request.getParameter("password");
 					}
				  if (!instance.getUser(request.getParameter("username")).isEnabled()){
					 System.out.println("get current User:::"+instance.getCurrentUser().toString());
					 System.out.println("inside getuser if condition");
 						instance.getUser(request.getParameter("username")).enable();
 					}
				  if (username != null && password != null) {
					System.out.println("not null");
					   if (instance.exists(username)) {
						System.out.println("yes ur username exists");
						User user = instance.getUser(username);
						System.out.println("User name is:::"+user.getAccountName());
						String accountName = user.getAccountName();
						
						logger.info(Logger.EVENT_SUCCESS, accountName);
						Date lastLoginTime = user.getLastLoginTime();
						System.out.println("Your login time is::::"+lastLoginTime.toGMTString());
						logger.info(Logger.EVENT_SUCCESS,lastLoginTime.toString());
						Date lastFailedLogin = user.getLastFailedLoginTime();
						System.out.println("your failed login is:::"+lastFailedLogin.toGMTString());
						logger.info(Logger.EVENT_FAILURE,lastFailedLogin.toString());
						int failedLoginCount = user.getFailedLoginCount();
						System.out.println("Failed login count is::::"+failedLoginCount);
						Set<String> roles = user.getRoles();
						String lastHost = user.getLastHostAddress();
						System.out.println("what is the lastHost is::::"+lastHost);
						 if (instance.verifyPassword(user,password)) {
							System.out.println("username&password not valid from verifyPassword");
							logger.info(Logger.EVENT_FAILURE,"username&password not valid");
							return true;
						}
						 if (instance.getUser(username).isEnabled()) {
							System.out.println("user account locked");
							logger.info(Logger.EVENT_FAILURE,"user account locked");
							return true;
						}
					}/* else {
						System.out.println("username not available");
						logger.info(Logger.EVENT_FAILURE,"username not available");
						return false;
					}*/
				} /*else {
					System.out.println("username or password missing");
					logger.info(Logger.EVENT_FAILURE,"username or password missing");
					return false;
				}*/
			//}
				return true;
		} 
		catch (AuthenticationException e) {
			System.out.println("Authentication Exception is:::"+e.getLogMessage());
			//logger.info(Logger.EVENT_FAILURE, e.getLogMessage());
		}
		return true;
	}

	/**
	 * Safe login.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the user
	 * @throws AuthenticationException the authentication exception
	 */
	public User safeLogin(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		if (request == null || response == null) {
			throw new AuthenticationCredentialsException("Invalid request",
					"Request or response objects were null");
		}

		// if there's a user in the session then use that
		DefaultUser user = (DefaultUser) getUserFromSession();

		// else if there's a remember token then use that
		if (user == null) {
			user = getUserFromRememberToken();
		}

		// else try to verify credentials - throws exception if login fails
		if (user == null) {
		//	user = (DefaultUser) loginWithUsernameAndPassword(request);
		}

		// set last host address
		user.setLastHostAddress(request.getRemoteHost());

		// warn if this authentication request was not POST or non-SSL
		// connection, exposing credentials or session id
		try {
			ESAPI.httpUtilities().assertSecureRequest(ESAPI.currentRequest());
		} catch (AccessControlException e) {
			throw new AuthenticationException(
					"Attempt to login with an insecure request",
					e.getLogMessage(), e);
		}

		// don't let anonymous user log in
		if (user.isAnonymous()) {
			user.logout();
			throw new AuthenticationLoginException("Login failed",
					"Anonymous user cannot be set to current user. User: "
							+ user.getAccountName());
		}

		// don't let disabled users log in
		if (!user.isEnabled()) {
			user.logout();
			user.incrementFailedLoginCount();
			user.setLastFailedLoginTime(new Date());
			throw new AuthenticationLoginException("Login failed",
					"Disabled user cannot be set to current user. User: "
							+ user.getAccountName());
		}

		// don't let locked users log in
		if (user.isLocked()) {
			user.logout();
			user.incrementFailedLoginCount();
			user.setLastFailedLoginTime(new Date());
			throw new AuthenticationLoginException("Login failed",
					"Locked user cannot be set to current user. User: "
							+ user.getAccountName());
		}

		// don't let expired users log in
		if (user.isExpired()) {
			user.logout();
			user.incrementFailedLoginCount();
			user.setLastFailedLoginTime(new Date());
			throw new AuthenticationLoginException("Login failed",
					"Expired user cannot be set to current user. User: "
							+ user.getAccountName());
		}

		// check session inactivity timeout
		if (user.isSessionTimeout()) {
			user.logout();
			user.incrementFailedLoginCount();
			user.setLastFailedLoginTime(new Date());
			throw new AuthenticationLoginException("Login failed",
					"Session inactivity timeout: " + user.getAccountName());
		}

		// check session absolute timeout
		if (user.isSessionAbsoluteTimeout()) {
			user.logout();
			user.incrementFailedLoginCount();
			user.setLastFailedLoginTime(new Date());
			throw new AuthenticationLoginException("Login failed",
					"Session absolute timeout: " + user.getAccountName());
		}

		// set Locale to the user object in the session from request
		user.setLocale(request.getLocale());

		// create new session for this User
		HttpSession session = request.getSession();
		user.addSession(session);
		session.setAttribute(USER, user);
		setCurrentUser(user);
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public void logout() {
		User user = getCurrentUser();
		if (user != null && !user.isAnonymous()) {
			user.logout();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p/>
	 * Returns the currently logged user as set by the setCurrentUser() methods.
	 * Must not log in this method because the logger calls getCurrentUser() and
	 * this could cause a loop.
	 */
	public User getCurrentUser() {
		User user = currentUser.get();
		if (user == null) {
			user = User.ANONYMOUS;
		}
		return user;
	}

	/**
	 * Gets the user from session.
	 * 
	 * @return the user from session or null if no user is found in the session
	 */
	protected User getUserFromSession() {
		HttpSession session = ESAPI.httpUtilities().getCurrentRequest().getSession(false);
		if (session == null)
			return null;
		return ESAPI.httpUtilities().getSessionAttribute(USER);
	}

	/**
	 * Returns the user if a matching remember token is found, or null if the
	 * token is missing, token is corrupt, token is expired, account name does
	 * not match and existing account, or hashed password does not match user's
	 * hashed password.
	 * 
	 * @return the user if a matching remember token is found, or null if the
	 *         token is missing, token is corrupt, token is expired, account
	 *         name does not match and existing account, or hashed password does
	 *         not match user's hashed password.
	 */
	protected DefaultUser getUserFromRememberToken() {
		try {
			String token = ESAPI.httpUtilities().getCookie(ESAPI.currentRequest(),HTTPUtilities.REMEMBER_TOKEN_COOKIE_NAME);
			Authenticator instance = ESAPI.authenticator();
			if (token == null)
				return null;

			// TODO - kww - URLDecode token first, and THEN unseal. See Google
			// Issue 144.

			String[] data = ESAPI.encryptor().unseal(token).split("\\|");
			if (data.length != 2) {
				logger.warning(Logger.SECURITY_FAILURE,
						"Found corrupt or expired remember token");
				ESAPI.httpUtilities().killCookie(ESAPI.currentRequest(),
						ESAPI.currentResponse(),
						HTTPUtilities.REMEMBER_TOKEN_COOKIE_NAME);
				return null;
			}

			String username = data[0];
			String password = data[1];
			System.out.println("DATA0: " + username);
			System.out.println("DATA1:" + password);
			DefaultUser user = (DefaultUser) instance.getUser(username);
			if (user == null) {
				logger.warning(Logger.SECURITY_FAILURE,
						"Found valid remember token but no user matching "
								+ username);
				return null;
			}

			logger.info(
					Logger.SECURITY_SUCCESS,
					"Logging in user with remember token: "
							+ user.getAccountName());
			user.loginWithPassword(password);
			return user;
		} catch (AuthenticationException ae) {
			logger.warning(Logger.SECURITY_FAILURE,
					"Login via remember me cookie failed", ae);
		} catch (EnterpriseSecurityException e) {
			logger.warning(Logger.SECURITY_FAILURE,
					"Remember token was missing, corrupt, or expired");
		}
		ESAPI.httpUtilities().killCookie(ESAPI.currentRequest(),
				ESAPI.currentResponse(),
				HTTPUtilities.REMEMBER_TOKEN_COOKIE_NAME);
		return null;
	}

	/**
	 * Utility method to extract credentials and verify them.
	 * 
	 * @param request
	 *            The current HTTP request
	 * @return The user that successfully authenticated
	 * @throws AuthenticationException
	 *             if the submitted credentials are invalid.
	 */
	/*public static void main(String g[]) throws AuthenticationException{
		BrokenAuthenticationSessionManagement bm =new BrokenAuthenticationSessionManagement();
		Authenticator instance = ESAPI.authenticator();
		//instance.get
		bm.loginWithUsernameAndPassword("raja","welcomedelhi");
	}*/
	//private User loginWithUsernameAndPassword(HttpServletRequest request) throws AuthenticationException
	private User loginWithUsernameAndPassword(String username1,String password1)
			throws AuthenticationException {
		String username = username1;
		String password = password1;
		//String username = request.getParameter(ESAPI.securityConfiguration().getUsernameParameterName());
		//String password = request.getParameter(ESAPI.securityConfiguration().getPasswordParameterName());

		// if a logged-in user is requesting to login, log them out first
		User user = getCurrentUser();
		Authenticator instance = ESAPI.authenticator();
		if (user != null && !user.isAnonymous()) {
			logger.warning(Logger.SECURITY_SUCCESS,
					"User requested relogin. Performing logout then authentication");
			user.logout();
		}

		// now authenticate with username and password
		if (username == null || password == null) {
			if (username == null) {
				username = "unspecified user";
			}
			throw new AuthenticationCredentialsException(
					"Authentication failed", "Authentication failed for "
							+ username
							+ " because of null username or password");
		}
		user = instance.getUser(username);
		if (user == null) {
			throw new AuthenticationCredentialsException(
					"Authentication failed",
					"Authentication failed because user " + username
							+ " doesn't exist");
		}
		user.loginWithPassword(password);

		//request.setAttribute(user.getCSRFToken(), "authenticated");
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCurrentUser(User user) {
		currentUser.setUser(user);
	}

}
