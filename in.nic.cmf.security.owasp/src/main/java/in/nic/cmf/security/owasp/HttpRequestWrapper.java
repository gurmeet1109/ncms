package in.nic.cmf.security.owasp;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * The Class HttpRequestWrapper.
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

    /** The params. */
    private HashMap    params = new HashMap();

    /** The request. */
    HttpServletRequest request;

    /**
     * Instantiates a new http request wrapper.
     * @param request
     *            the request
     */
    public HttpRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = (HttpServletRequest) getRequest();

        init();
    }

    /**
     * Inits the.
     */
    public void init() {

        Enumeration enumuration = request.getParameterNames();

        while (enumuration.hasMoreElements()) {
            String key = enumuration.nextElement().toString();
            String value = request.getParameter(key);
            params.put(key, value);
        }

    }

    /**
     * Gets the keys.
     * @return the keys
     */
    public Set getKeys() {
        return params.keySet();
    }

    /**
     * Gets the param.
     * @param name
     *            the name
     * @return the param
     */
    public Object getParam(String name) {
        return params.get(name);
    }

    /**
     * Sets the param.
     * @param key
     *            the key
     * @param value
     *            the value
     */
    public void setParam(String key, Object value) {
        params.put(key, value);
    }

}
