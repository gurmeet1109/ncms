package in.nic.cmf.formatters;

import in.nic.cmf.exceptions.GenericException;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * The Class FormatJS.
 */
public class FormatJS implements Formatters {

    /** The Constant DEFAULT_CONTENT_TYPE. */
    public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
     * (java.util.Map, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Map<String, Map<String, Object>> Format(String domain,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        try {
            JSONObject json = new JSONObject();
            String wid = "";
            String wtype = "";
            String wtitle = "";
            Map<String, Object> output = parameters.get("output");
            HashMap<String, String> queryParams = (HashMap<String, String>) parameters
                    .get("input").get("queryParams");
            String value = (String) output.get("content");
            if (queryParams.get("wid") == null) {
                json.put("wid", "widget1");
                wid = "widget1";
            } else {
                json.put("wid", queryParams.get("wid"));
            }
            if (queryParams.get("wtype") == null) {
                json.put("wtype", "slider");
            } else {
                json.put("wtype", queryParams.get("wtype"));
            }
            if (queryParams.get("wtitle") == null) {
                json.put("wtitle", "WidgetTitle");
            } else {
                json.put("wtitle", queryParams.get("wtitle"));
            }
            json.put("wdata", value.trim());
            String content = "xmlData(" + json.toString() + ")";
            HashMap<String, String> headers = new HashMap<String, String>();
            if (parameters.get("output").containsKey("headers")) {
                headers = (HashMap<String, String>) parameters.get("output")
                        .get("headers");
            }
            headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
            output.put("headers", headers);
            output.put("content", content);
            parameters.put("output", output);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":renderMergedOutputModel()", e);
        }
        return parameters;
    }

}
