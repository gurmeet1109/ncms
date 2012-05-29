package in.nic.cmf.util;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

public class HttpServletUtils {
    // private LogTracer log = new LogTracer("UtilTraceLogger", true, true,
    // true,
    // true, true);
    private LogTracer                                log;
    private static HashMap<String, HttpServletUtils> hashHttpServletUtils = new HashMap<String, HttpServletUtils>();
    private String                                   domain               = "";

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                false);
    }

    private HttpServletUtils(String domain) {
        setLogger(domain);
        this.domain = domain;
    }

    public static HttpServletUtils getInstanceOf(String domain) {
        if (!hashHttpServletUtils.containsKey(domain)) {
            hashHttpServletUtils.put(domain, new HttpServletUtils(domain));
        }
        return hashHttpServletUtils.get(domain);
    }

    /**
     * Gets the response.
     * @param responseMap
     *            the response map
     * @return the response
     *         Usage:build webservice get response
     */
    public Response getResponse(String responseString, String contentType)
            throws GenericException {
        log.methodEntry("Util.getResponse entry");
        Response coreResponse = null;
        try {
            if (!CommonUtils.getInstanceOf(domain).isEmpty(responseString)) {
                coreResponse = Response.status(Response.Status.OK)
                        .entity(responseString).type(contentType).build();
            } else {
                coreResponse = Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(responseString).type(contentType).build();
            }
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-UTIL-0014",
                    "GetResponse failure." + ex.getMessage(), ex);
        }
        log.methodExit("Util.getResponse exit");
        return coreResponse;
    }

    public Response getResponse(Object responseEntity, String contentType)
            throws GenericException {
        log.methodEntry("Util.getResponse entry");
        Response coreResponse = null;
        try {
            if (null != responseEntity) {
                coreResponse = Response.status(Response.Status.OK)
                        .entity(responseEntity).type(contentType).build();
            } else {
                coreResponse = Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(responseEntity).type(contentType).build();
            }
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-UTIL-0014",
                    "GetResponse failure." + ex.getMessage(), ex);
        }
        log.methodExit("Util.getResponse exit");
        return coreResponse;
    }

    /**
     * Gets the response.
     * @param responseMap
     *            the response map
     * @return the response
     *         Usage:build webservice post response
     */
    public Response getResponse(Map responseMap) {
        log.methodEntry("HttpServletUtils.getResponse entry");
        Response coreResponse = null;
        log.debug("responseMap::" + responseMap.toString());
        try {
            if (!CommonUtils.getInstanceOf(domain).isEmpty(
                    responseMap.get("responseCode").toString())) {
                // new Integer((String)responseMap.get("responseCode"))
                coreResponse = Response
                        .status(new Integer((String) responseMap
                                .get("responseCode")))
                        .entity(responseMap.get("responseStringBody"))
                        .type((String) responseMap.get("contentType")).build();
            } else {
                coreResponse = Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(responseMap.get("responseStringBody"))
                        .type("text/plain").build();
            }
        } catch (Exception ex) {
            throw new GenericException(domain, "ERR-UTIL-0014",
                    "GetResponse failure." + ex.getMessage(), ex);

        }
        log.methodExit("HttpServletUtils.getResponse exit");
        return coreResponse;
    }

    public String getBodyStringFromHttpServletResponse(
            HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
