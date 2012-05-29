package in.nic.cmf.security.owasp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.HTTPUtilities;
import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.errors.AuthenticationException;

// TODO: Auto-generated Javadoc
/**
 * The Class InsufficientTransportLayerProtection.
 * @class
 * @author sunil
 */
public class InsufficientTransportLayerProtection {

    /**
     * Defines the ThreadLocalRequest to store the current request for this
     * thread.
     */
    private class ThreadLocalRequest extends
            InheritableThreadLocal<HttpServletRequest> {

        /**
         * Gets the request.
         * @return the request
         */
        public HttpServletRequest getRequest() {
            return super.get();
        }

        /*
         * (non-Javadoc)
         * @see java.lang.ThreadLocal#initialValue()
         */
        public HttpServletRequest initialValue() {
            return null;
        }

        /**
         * Sets the request.
         * @param newRequest
         *            the new request
         */
        public void setRequest(HttpServletRequest newRequest) {
            super.set(newRequest);
        }
    }

    /**
     * The Class ThreadLocalResponse.
     */
    private class ThreadLocalResponse extends
            InheritableThreadLocal<HttpServletResponse> {

        /**
         * Gets the response.
         * @return the response
         */
        public HttpServletResponse getResponse() {
            return super.get();
        }

        /*
         * (non-Javadoc)
         * @see java.lang.ThreadLocal#initialValue()
         */
        public HttpServletResponse initialValue() {
            return null;
        }

        /**
         * Sets the response.
         * @param newResponse
         *            the new response
         */
        public void setResponse(HttpServletResponse newResponse) {
            super.set(newResponse);
        }
    }

    /** The current response. */
    private ThreadLocalResponse currentResponse = new ThreadLocalResponse();

    /** The current request. */
    private ThreadLocalRequest  currentRequest  = new ThreadLocalRequest();

    /**
     * Secure transport layer protection.
     * @return true, if successful
     * @throws AuthenticationException
     *             the authentication exception
     */
    public static boolean secureTransportLayerProtection()
            throws AuthenticationException {

        try {
            ESAPI.httpUtilities().assertSecureRequest(ESAPI.currentRequest());
        } catch (AccessControlException e) {
            throw new AuthenticationException(
                    "Attempt to login with an insecure request",
                    e.getLogMessage(), e);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void assertSecureChannel() throws AccessControlException {
        assertSecureChannel(getCurrentRequest());
    }

    /**
     * {@inheritDoc} This implementation ignores the built-in isSecure() method
     * and uses the
     * URL to determine if the request was transmitted over SSL. This is because
     * SSL may have been terminated somewhere outside the container.
     */
    public void assertSecureChannel(HttpServletRequest request)
            throws AccessControlException {
        if (request == null) {
            throw new AccessControlException("Insecure request received",
                    "HTTP request was null");
        }
        StringBuffer sb = request.getRequestURL();
        if (sb == null) {
            throw new AccessControlException("Insecure request received",
                    "HTTP request URL was null");
        }
        String url = sb.toString();
        if (!url.startsWith("https")) {
            throw new AccessControlException("Insecure request received",
                    "HTTP request did not use SSL");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void assertSecureRequest() throws AccessControlException {
        assertSecureRequest(getCurrentRequest());
    }

    /**
     * Ensures that the current request uses SSL and POST to protect any
     * sensitive parameters in the querystring from being sniffed or logged. For
     * example, this method should be called from any method that uses sensitive
     * data from a web form.
     * This method uses {@link HTTPUtilities#getCurrentRequest()} to obtain the
     * current {@link HttpServletRequest} object
     * @param request
     *            the request
     * @throws AccessControlException
     *             if security constraints are not met
     */

    public void assertSecureRequest(HttpServletRequest request)
            throws AccessControlException {
        assertSecureChannel(request);
        String receivedMethod = request.getMethod();
        String requiredMethod = "POST";
        if (!receivedMethod.equals(requiredMethod)) {
            throw new AccessControlException("Insecure request received",
                    "Received request using " + receivedMethod + " when only "
                            + requiredMethod + " is allowed");
        }
    }

    /**
     * {@inheritDoc}
     */
    public HttpServletRequest getCurrentRequest() {
        return currentRequest.getRequest();
    }

}
