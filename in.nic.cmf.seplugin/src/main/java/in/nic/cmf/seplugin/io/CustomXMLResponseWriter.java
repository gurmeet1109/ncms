package in.nic.cmf.seplugin.io;

import java.io.Writer;
import java.io.IOException;

import org.apache.solr.common.util.NamedList;
import org.apache.solr.response.QueryResponseWriter;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;

/**
 * Class Custom XML Response Writer.
 * @author premananda
 */
public class CustomXMLResponseWriter implements QueryResponseWriter {
    /**
     * initializes NamedList args.
     * @param n is of type NamedList
     */
    public void init(final NamedList n) {
        /* NOOP */
    }

    /**
     * writes text as status text into the writer.
     * @param writer is of type Writer
     * @param req is of type SolrQueryRequest
     * @param rsp is of type SolrQueryResponse
     * @throws IOException is InputOutput Exception
     */
    public final void write(final Writer writer,
        final SolrQueryRequest req,
        final SolrQueryResponse rsp) throws IOException {
        if (rsp.getException() == null) {
            writer.write("HTTP/1.1 200 Success");
        } else {
            writer.write("HTTP/1.1 " + rsp.getResponseHeader()
                    .get("status").toString() + " Failure");
        }
    }

    /**
     * writes text as xml text into the writer.
     * @param writer is of type Writer
     * @param xmlContent is of type String
     * @throws IOException is InputOutput Exception
     */
    public final void write(final Writer writer,
        final String xmlContent) throws IOException {
        writer.write(xmlContent);
    }

    /**
     * method to get content type.
     * @param request is of type SolrQueryRequest
     * @param response is of type SolrQueryResponse
     * @return is of type String
     */
    public final String getContentType(final SolrQueryRequest request,
        final SolrQueryResponse response) {
        return CONTENT_TYPE_XML_UTF8;
    }
}
