package in.nic.cmf.security.owasp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.owasp.esapi.errors.AccessControlException;

/**
 * Class UnvalidatedRedirectsAndForwards Send the Request to safe Location.
 * @class
 * @author sunil
 */

public class UnvalidatedRedirectsAndForwards extends HttpServletResponseWrapper
        implements HttpServletResponse {

    /** The Constant logger. */
    private static final Logger        logger = ESAPI.getLogger(UnvalidatedRedirectsAndForwards.class);

    /** The response. */
    private static HttpServletResponse response;

    /**
     * Instantiates a new unvalidated redirects and forwards.
     * @param response
     *            the response
     */
    public UnvalidatedRedirectsAndForwards(HttpServletResponse response) {
        super(response);
        this.response = response;
    }

    /**
     * This method generates a redirect response that can only be used to
     * redirect the browser to safe locations, as configured in the ESAPI
     * security configuration. This method does not that redirect requests can
     * be modified by attackers, so do not rely information contained within
     * redirect requests, and do not include sensitive information in a
     * redirect.
     * @param location
     *            the location
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws AccessControlException
     *             the access control exception
     */
    public void sendSafeRedirect(String location) throws IOException,
            AccessControlException {

        if (!ESAPI.validator().isValidRedirectLocation("Redirect", location,
                false)) {
            logger.fatal(Logger.SECURITY_FAILURE, "Bad redirect location: "
                    + location);
        } else {
            ESAPI.httpUtilities().sendRedirect(response, location);
        }
    }

    /**
     * This implementation simply checks to make sure that the forward location
     * starts with "WEB-INF" and is intended for use in frameworks that forward
     * to JSP files inside the WEB-INF folder.
     * @param request
     *            the request
     * @param response
     *            the response
     * @param location
     *            the location
     * @throws AccessControlException
     *             the access control exception
     * @throws ServletException
     *             the servlet exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void sendSafeForward(HttpServletRequest request,
            HttpServletResponse response, String location)
            throws AccessControlException, ServletException, IOException {
        if (!location.startsWith("WEB-INF")) {
            throw new AccessControlException("Forward failed",
                    "Bad forward location: " + location);
        }
        ESAPI.httpUtilities().sendForward(request, response, location);

    }

}
