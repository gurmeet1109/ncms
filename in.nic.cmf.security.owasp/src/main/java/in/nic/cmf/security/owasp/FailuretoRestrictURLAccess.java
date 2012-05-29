package in.nic.cmf.security.owasp;

import in.nic.cmf.logger.LogTracer;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.User;
import org.owasp.esapi.filters.SecurityWrapperRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class FailuretoRestrictURLAccess.
 */
public class FailuretoRestrictURLAccess {

    /** The log. */
    private LogTracer log;

    public FailuretoRestrictURLAccess(String domain) {
        setLogger(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "Owasp");
    }

    /**
     * Checks if is authorized for url.
     * @param url
     *            the url
     * @return true, if is authorized for url
     */
    public boolean isAuthorizedForURL(HttpServletRequest url) {
        User user = ESAPI.authenticator().getCurrentUser();
        SecurityWrapperRequest safeRequest = new SecurityWrapperRequest(url);
        Map map = safeRequest.getParameterMap();
        if (map.size() != 0) {
            Collection c = map.values();
            Iterator itr = c.iterator();

            while (itr.hasNext()) {
                String a[] = (String[]) itr.next();
                log.debug(a[0]);
            }
            return true;
        }
        return false;
    }
}
