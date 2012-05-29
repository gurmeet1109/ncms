package in.nic.cmf.formatters;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

public class FormatMEDIA extends AbstractView {

    @Autowired
    private LogTracer   log;
    /** The rendered attributes. */
    private Set<String> renderedAttributes;
    private String      domain;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
     * (java.util.Map, javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            HashMap hMap = (HashMap) model.get("mediaContent");
            log.info("Filename ---" + (String) hMap.get("filename"));
            OutputStream out = response.getOutputStream();
            byte[] content = (byte[]) hMap.get("content");
            log.info("Content size ---" + content.length);
            for (int i = 0; i < content.length; i++) {
                out.write(content[i]);
            }
            out.close();
        } catch (IOException ioe) {
            throw new GenericException(domain, "ERR-DS-0015", this.getClass()
                    .getSimpleName() + ":renderMergedOutputModel()", "", ioe);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0014", this.getClass()
                    .getSimpleName() + ":renderMergedOutputModel()", "", e);
        }
    }

}
