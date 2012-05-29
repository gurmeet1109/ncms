package in.nic.cmf.seplugin.handler;

import in.nic.cmf.convertors.FormatSolrXml;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.seplugin.util.HashMapGenerator;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.handler.XmlUpdateRequestHandler;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.servlet.DirectSolrConnection;

/**
 * Class Custom Xml Update Request Handler.
 * @author premananda
 */
public class CustomXmlUpdateRequestHandler extends XmlUpdateRequestHandler {
    /**
     * variable for log information.
     */
    private static LogTracer log;

    private static String    domain;

    /**
     * initializes log.
     */
    static {
        log = new LogTracer(domain, "seplugin", true, true, true, true, false);
    }

    /**
     * get DirectSolrConnection instance.
     * @param req
     *            is of type SolrQueryRequest
     * @return is of type DirectSolrConnection
     */
    public static DirectSolrConnection getInstance(final SolrQueryRequest req) {
        return new DirectSolrConnection(req.getCore());
    }

    /**
     * get description.
     * @return description is of type String
     */
    public final String getDescription() {
        return "$ Handles cutomize xmlupdate request. $";
    }

    /**
     * get sourceid.
     * @return sourceid is of type String
     */
    public final String getSourceId() {
        return "$ @author premananda $";
    }

    /**
     * get source.
     * @return source is of type String
     */
    public final String getSource() {
        return "$ solr/src/main/java/in/nic"
                + "/cmf/plugins/solr/handler/CustomXmlUpdateRequestHandler $";
    }

    /**
     * get version.
     * @return version is of type String
     */
    public final String getVersion() {
        return "$ 3.4.0 $";
    }

    /**
     * method for actual glue with solr request and response.
     * @param req
     *            is of type SolrQueryRequest
     * @param res
     *            is of type SolrQueryResponse
     * @throws Exception
     *             is Exception
     */
    public final void handleRequestBody(final SolrQueryRequest req,
            final SolrQueryResponse res) throws Exception {
        String updateStr = null;

        String commitStr = "<commit>true</commit><optimize>true</optimize>";

        if (req.getContentStreams() != null) {
            updateStr = handleAddRequest(req, commitStr);
        } else {
            updateStr = handleDeleteRequest(req, commitStr);
        }

        getInstance(req).request("/update", updateStr);
    }

    /**
     * method for add document to solr.
     * @param req
     *            is of type SolrQueryRequest
     * @param commitStr
     *            is of type String
     * @return updateStr is of type String
     * @throws IOException
     *             is InputOutput Exception
     */
    private String handleAddRequest(final SolrQueryRequest req,
            final String commitStr) throws IOException {
        String inputStr = getContentStreamAsText(req);

        String updateStr = "<add>";

        updateStr += getSolrInputDocumentAsText(inputStr);

        updateStr += commitStr + "</add>";

        log.debug("AddRequest text: " + updateStr);

        return updateStr;
    }

    /**
     * method for delete document from solr.
     * @param req
     *            is of type SolrQueryRequest
     * @param commitStr
     *            is of type String
     * @return updateStr is of type String
     */
    private String handleDeleteRequest(final SolrQueryRequest req,
            final String commitStr) {
        SolrParams params = req.getParams();

        String path = req.getContext().get("path").toString();

        String deletepath = path.substring(path.lastIndexOf("/") + 1,
                path.length());

        String[] termArr = null;

        String updateStr = "<update>";

        if (deletepath.equals("deletebyid")) {
            termArr = req.getParams().get("id").toString().split(",");

            for (String term : termArr) {
                updateStr += "<delete><id>" + term + "</id></delete>";
            }
        } else if (deletepath.equals("delete")) {
            updateStr += "<delete>";

            if (params.get("q") != null) {
                updateStr += "<query>q:" + params.get("q") + "</query>";
            }

            Iterator<String> itr = req.getParams().getParameterNamesIterator();

            while (itr.hasNext()) {
                String param = itr.next();

                if (!param.equals("q")) {
                    updateStr += "<query>" + param + ":" + params.get(param)
                            + "</query>";
                }
            }

            updateStr += "</delete>";
        }

        updateStr += commitStr + "</update>";

        log.debug("DeleteRequest text: " + updateStr);

        return updateStr;
    }

    /**
     * method to get content stream as text.
     * @param req
     *            is of type SolrQueryRequest
     * @return inputStr is of type String
     * @throws IOException
     *             is InputOutput Exception
     */
    private String getContentStreamAsText(final SolrQueryRequest req)
            throws IOException {
        String inputStr = null;

        for (ContentStream stream : req.getContentStreams()) {
            InputStream is = stream.getStream();
            int c = -1;

            while ((c = is.read()) != -1) {
                if (inputStr == null) {
                    inputStr = "" + (char) c;
                } else {
                    inputStr += (char) c;
                }
            }
        }

        inputStr = inputStr.trim();

        return inputStr;
    }

    /**
     * method to get solr input document as text.
     * @param inputStr
     *            string
     * @return updateStr as string
     * @throws IOException
     *             ioexception
     */
    private String getSolrInputDocumentAsText(final String inputStr)
            throws IOException {
        HashMap<String, Object> queryResult = FormatSolrXml.getInstanceof(
                domain).in(inputStr);

        ArrayList<SolrInputDocument> docsList = FormatSolrXml.getInstanceof(
                domain).out(
                queryResult,
                (HashMap<String, String>) HashMapGenerator
                        .getInstanceof(domain).getQueryKey()
                        .get("reverseFieldNameMapping"), true);

        String updateStr = "";

        for (SolrInputDocument inputDoc : docsList) {
            // if (updateStr == null) {
            // updateStr = "" + FormatSolrXml.getInstance().out(inputDoc);
            // } else {
            updateStr += FormatSolrXml.getInstanceof(domain).out(inputDoc);
            // }
        }

        return updateStr;
    }
}
