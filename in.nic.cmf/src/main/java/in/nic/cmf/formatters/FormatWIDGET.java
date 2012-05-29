package in.nic.cmf.formatters;

import in.nic.cmf.exceptions.GenericException;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * The Class FormatWidget.
 */
public class FormatWIDGET implements Formatters {

    /** The Constant DEFAULT_CONTENT_TYPE. */
    public static final String DEFAULT_CONTENT_TYPE = "application/html";

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
            Map<String, Object> output = parameters.get("output");
            VelocityEngine velocity = new VelocityEngine();
            velocity.setProperty(Velocity.RESOURCE_LOADER, "class");
            velocity.setProperty("class.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            velocity.init();
            Template tpl = velocity
                    .getTemplate("formatters/widget/vm/widget.vm");
            VelocityContext context = new VelocityContext();
            StringWriter writer = new StringWriter();
            tpl.merge(context, writer);
            String content = writer.toString();
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
