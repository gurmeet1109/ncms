package in.nic.cmf.sem.interfaces;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

/**
 * The Interface IMarketingEngine use to update the messages on social sites.
 * [Like Twitter, FaceBook, Digg]
 * @author Ramu P
 *         Created On: 17th April 2011
 */
public interface IMarketingEngine {

    /**
     * Validate the the marketing engine access tokens & consumer details.
     * @param userContext
     * @param sem
     *            the semable object
     * @return true, if successful
     */
    boolean processSemable(String domain, Map<String, Object> userContextsem);

    /**
     * Post messages to marketing engines [like Twitter, FaceBook, Digg].
     * @param sem
     *            the semable object
     * @throws Exception
     *             If an error occurs
     */

    void postMessage(String domain, Map sem) throws GenericException;

    void setLogTracer(LogTracer log);
}
