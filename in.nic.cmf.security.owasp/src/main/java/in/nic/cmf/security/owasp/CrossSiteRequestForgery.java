package in.nic.cmf.security.owasp;

import in.nic.cmf.logger.LogTracer;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.reference.DefaultEncoder;

/**
 * The Class CrossSiteRequestForgery.
 */
public class CrossSiteRequestForgery {

    /** The Constant CSRF_TOKEN_NAME. */
    final static String CSRF_TOKEN_NAME = "ctoken";
    private LogTracer   log;
    private String      domain;

    public CrossSiteRequestForgery(String domain) {
        this.domain = domain;
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "Owasp");
    }

    /**
     * Reset csrf token.
     * @return the string
     */
    public String resetCSRFToken() {

        String csrfToken = ESAPI.randomizer().getRandomString(8,
                DefaultEncoder.CHAR_ALPHANUMERICS);
        return csrfToken;

    }

    /**
     * Adds the csrf token.
     * @param href
     *            the href
     * @return the string
     */
    public String addCSRFToken(String href) {
        log.debug("Inside addCSRFToken");
        User user = ESAPI.authenticator().getCurrentUser();
        log.debug("what is the user:::" + user);

        String token = CSRF_TOKEN_NAME + "=" + user.getCSRFToken();
        log.debug("what is the token:::" + token);
        return href.indexOf('?') != -1 ? href + "&" + token : href + "?"
                + token;
    }

    /**
     * Gets the cSRF token.
     * @return the cSRF token
     */
    public String getCSRFToken() {
        User user = ESAPI.authenticator().getCurrentUser();
        if (user == null) {
            return null;
        }
        return user.getCSRFToken();
    }

    /**
     * Verify csrf token.
     * @param request
     *            the request
     * @throws IntrusionException
     *             the intrusion exception
     */
    public void verifyCSRFToken(HttpServletRequest request)
            throws IntrusionException {

        User user = ESAPI.authenticator().getCurrentUser();

        if (request.getAttribute(user.getCSRFToken()) != null) {
            return;
        }

        String token = request.getParameter(CSRF_TOKEN_NAME);

        if (!user.getCSRFToken().equals(token)) {

            throw new IntrusionException("Authentication failed",
                    "Possibly forged HTTP request without proper CSRF token detected");

        }

    }

    /**
     * Gets the safe csrf value.
     * @param href
     *            the href
     * @return the safe csrf value
     */
    public String getSafeCSRFValue(String href) {

        return null;
    }
}
