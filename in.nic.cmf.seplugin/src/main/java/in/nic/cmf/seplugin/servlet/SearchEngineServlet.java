package in.nic.cmf.seplugin.servlet;

import in.nic.cmf.convertors.FormatSolrXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.seplugin.io.CustomXMLResponseWriter;
import in.nic.cmf.seplugin.util.HashMapGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.document.Document;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.core.SolrConfig;
import org.apache.solr.core.SolrCore;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.request.SolrRequestHandler;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.search.DocIterator;
import org.apache.solr.search.DocSlice;
import org.apache.solr.search.SolrCache;
import org.apache.solr.servlet.SolrRequestParsers;
import org.apache.solr.servlet.cache.HttpCacheHeaderUtil;
import org.apache.solr.servlet.cache.Method;

/**
 * Class Search Engine Servlet.
 * @author premananda
 */
public class SearchEngineServlet extends HttpServlet implements
        SingleThreadModel {
    /**
     * holds serial version UID.
     */
    static final long                                          serialVersionUID  = 1234567890L;

    /**
     * holds the http error code.
     */
    static final int                                           ERRNO             = 412;
    /**
     * holds for index of forwardslash.
     */
    private static final int                                   FORWARDSLASHINDEX = 5;
    /**
     * map holds solr parsers related key and value pairs.
     */
    protected static final Map<SolrConfig, SolrRequestParsers> PARSERS           = new WeakHashMap<SolrConfig, SolrRequestParsers>();

    /**
     * static instance of solr core.
     */
    private static SolrCore                                    core              = SolrCore
                                                                                         .getSolrCore();
    /**
     * static instance of solr config.
     */
    private static SolrConfig                                  solrConfig;
    /**
     * static instance of solr request parser.
     */
    private static SolrRequestParsers                          parser;

    /**
     * holds XML start definition.
     */
    private static final String                                XML_START         = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    /**
     * map holds query results and query keys respectively.
     */
    private HashMap<String, Object>                            queryResult,
            queryKey;
    /**
     * holds query result cache and user cache.
     */
    private static SolrCache<Object, Object>                   queryResultCache,
            cmfUserCache;

    /**
     * holds document index.
     */
    private Vector<Integer>                                    documentIndexList;
    /**
     * holds query result cache entry in terms of
     * document index list and previous hit's result.
     */
    private Vector<Object>                                     queryResultCacheEntry;
    /**
     * holds count related collection.
     */
    private Hashtable<String, String>                          countEntry;

    /**
     * holds solr request handler.
     */
    private SolrRequestHandler                                 handler;
    /**
     * holds solr query request.
     */
    private SolrQueryRequest                                   solrReq;
    /**
     * holds solr query response.
     */
    private SolrQueryResponse                                  solrRsp;

    /**
     * holds solr query result in terms of xml.
     */
    private String                                             xmlContent;

    /**
     * initializes solr core, solr config, solr request parser,
     * query result cache, user cache, and query keys.
     */
    static {
        solrConfig = core.getSolrConfig();

        parser = new SolrRequestParsers(solrConfig);
        PARSERS.put(solrConfig, parser);

        queryResultCache = solrConfig.queryResultCacheConfig.newInstance();

        cmfUserCache = core.getSearcher().get().getCache("cmfUserCache");

        // queryKey = HashMapGenerator.getInstance().getQueryKey();
    }

    /**
     * servlet's post method.
     * @param req
     *            is of type HttpServletRequest
     * @param resp
     *            is of type HttpServletResponse
     * @throws IOException
     *             is InputOutput Exception
     * @throws ServletException
     *             is Servlet Exception
     */
    public final void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException,
            ServletException {
        setSolrHandlerAndRequest(req, resp);

        solrRsp = new SolrQueryResponse();

        execute(req, solrReq, solrRsp);

        core.getQueryResponseWriter(solrReq).write(resp.getWriter(), solrReq,
                solrRsp);
    }

    /**
     * validates http request.
     * @param req
     *            is of type HttpServletRequest
     * @param resp
     *            is of type HttpServletResponse
     * @param parameter
     *            is of type String
     * @return is a type of boolean
     * @throws IOException
     *             is InputOutput Exception
     */
    private boolean validateRequest(final HttpServletRequest req,
            final HttpServletResponse resp, final String parameter)
            throws IOException {
        String parameterValue = req.getParameter(parameter);

        Pattern pattern = Pattern.compile("[aA-zZ]");
        Matcher matcher = pattern.matcher(parameterValue);

        if (matcher.find()) {
            GenericException ge = new GenericException("ERR-SE-0012",
                    "Invalid request because of " + parameter
                            + " is not a number.", "");
            resp.getWriter().write(ge.getMessage());
            resp.setStatus(ERRNO);
            return true;
        }

        if (parameter.equals("limit") && Integer.parseInt(parameterValue) <= 0) {
            GenericException ge = new GenericException("ERR-SE-0012",
                    "Invalid request because of " + parameter
                            + " is not a number > 0.", "");
            resp.getWriter().write(ge.getMessage());
            resp.setStatus(ERRNO);
            return true;
        }

        if (parameter.equals("offset") && Integer.parseInt(parameterValue) < 0) {
            GenericException ge = new GenericException("ERR-SE-0012",
                    "Invalid request because of " + parameter
                            + " is not a number >= 0.", "");
            resp.getWriter().write(ge.getMessage());
            resp.setStatus(ERRNO);
            return true;
        }

        return false;
    }

    /**
     * servlet's get method.
     * @param req
     *            is of type HttpServletRequest
     * @param resp
     *            is of type HttpServletResponse
     * @throws IOException
     *             is InputOutput Exception
     * @throws ServletException
     *             is Servlet Exception
     */
    @SuppressWarnings ("unchecked")
    public final void doGet(String domain, final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException,
            ServletException {

        if (req.getQueryString() != null) {
            Enumeration paramEnum = req.getParameterNames();
            while (paramEnum.hasMoreElements()) {
                String param = paramEnum.nextElement().toString();
                if (!param.equals("q") && req.getParameter(param).equals("")) {
                    GenericException ge = new GenericException("ERR-SE-0012",
                            "Parameter " + param + " is empty.", "Parameter "
                                    + param + " is empty.");
                    resp.getWriter().write(ge.getMessage());
                    resp.setStatus(ERRNO);
                    return;
                }

                if (param.equals("limit")
                        && validateRequest(req, resp, "limit")) {
                    return;
                }

                if (param.equals("offset")
                        && validateRequest(req, resp, "offset")) {
                    return;
                }
            }
        }

        try {
            setSolrHandlerAndRequest(req, resp);

            final Method reqMethod = Method.getMethod(req.getMethod());
            HttpCacheHeaderUtil.setCacheControlHeader(solrConfig, resp,
                    reqMethod);

            if (solrConfig.getHttpCachingConfig().isNever304()
                    || !HttpCacheHeaderUtil.doCacheHeaderValidation(solrReq,
                            req, reqMethod, resp)) {
                String key = solrReq
                        .getContext()
                        .get("path")
                        .toString()
                        .substring(
                                FORWARDSLASHINDEX,
                                solrReq.getContext().get("path").toString()
                                        .indexOf("/", FORWARDSLASHINDEX));
                queryResultCacheEntry = (Vector<Object>) cmfUserCache.get(key);
                getSolrIndexDocuments(req);
                if (queryResultCacheEntry == null) {
                    addToQueryResultCache(domain, solrReq, solrRsp, key);
                } else {
                    List<Integer> cachedDocumentIndexList = (List<Integer>) queryResultCacheEntry
                            .get(0);
                    if (!compare(cachedDocumentIndexList)) {
                        addToQueryResultCache(domain, solrReq, solrRsp, key);
                    } else {
                        xmlContent += queryResultCacheEntry.get(1).toString();
                    }
                }

                HttpCacheHeaderUtil.checkHttpCachingVeto(solrRsp, resp,
                        reqMethod);

                ((CustomXMLResponseWriter) core.getQueryResponseWriter(solrReq))
                        .write(resp.getWriter(), xmlContent);
            }
            return;
        } catch (Exception e) {
            GenericException ge = new GenericException("ERR-SE-0012", "", "", e);
            if (resp.getWriter() != null) {
                resp.getWriter().write(ge.getMessage());
            }
            resp.setStatus(ERRNO);
        }
    }

    /**
     * sets solr handler and solr request.
     * @param req
     *            is a type of HttpServletRequest
     * @param resp
     *            is a type of HttpServletResponse
     */
    private void setSolrHandlerAndRequest(final HttpServletRequest req,
            final HttpServletResponse resp) {
        String path = req.getServletPath();

        if (req.getPathInfo() != null) {
            path += req.getPathInfo();
        }

        handler = core.getRequestHandler(req.getMethod().toLowerCase()
                + "handler");

        try {
            solrReq = parser.parse(core, path, req);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * get solr index documents.
     * @param req
     *            is a type of HttpServletRequest
     */
    private void getSolrIndexDocuments(final HttpServletRequest req) {
        solrRsp = new SolrQueryResponse();

        execute(req, solrReq, solrRsp);

        documentIndexList = addDocumentIndexToList(solrRsp);

        xmlContent = XML_START;
    }

    /**
     * compares current and cached document index list.
     * @param cachedDocumentIndexList
     *            is a type of List<Integer>
     * @return is a type of boolean
     */
    private boolean compare(final List<Integer> cachedDocumentIndexList) {
        return (documentIndexList.containsAll(cachedDocumentIndexList) && cachedDocumentIndexList
                .containsAll(documentIndexList));
    }

    /**
     * add solr index documents from solr response.
     * @param rsp
     *            is a type SolrQueryResponse
     * @return is a type of Vector<Integer>
     */
    private Vector<Integer> addDocumentIndexToList(final SolrQueryResponse rsp) {
        documentIndexList = new Vector<Integer>();
        DocSlice slice = (DocSlice) rsp.getValues().get("response");

        countEntry = new Hashtable<String, String>();
        countEntry.put("Count", new Long(slice.matches()).toString());

        DocIterator itr = slice.iterator();
        int cnt = 0;
        while (itr.hasNext()) {
            documentIndexList.add(itr.next());
            cnt++;
        }

        return documentIndexList;
    }

    /**
     * add hit's result to the user cache.
     * @param req
     *            is a type of SolrQueryRequest
     * @param rsp
     *            is a type of SolrQueryResponse
     * @param key
     *            is a type of String
     * @throws IOException
     *             is InputOutput Exception
     */
    private void addToQueryResultCache(String domain,
            final SolrQueryRequest req, final SolrQueryResponse rsp,
            final String key) throws IOException {
        queryResultCacheEntry = new Vector<Object>();

        queryResultCacheEntry.add(documentIndexList);

        String value = getQueryResultAsXML(domain, req,
                getSolrDocumentList(req, rsp));

        queryResultCacheEntry.add(value);

        cmfUserCache.put(key, queryResultCacheEntry);

        xmlContent += value;
    }

    /**
     * method to get solr documents from query response.
     * @param req
     *            is of type SolrQueryRequest
     * @param rsp
     *            is of type SolrQueryResponse
     * @return is of type List<SolrDocument>
     * @throws IOException
     *             is InputOutput Exception
     */
    private List<SolrDocument> getSolrDocumentList(final SolrQueryRequest req,
            final SolrQueryResponse rsp) throws IOException {
        List<SolrDocument> cacheSolrDocumentList = new ArrayList<SolrDocument>();

        for (Integer idx : documentIndexList) {
            Document document = req.getSearcher().doc(idx);
            if (cmfUserCache.get(idx) == null) {
                SolrDocument solrDocument = new SolrDocument();

                for (String field : rsp.getReturnFields()) {
                    solrDocument.addField(field, document.get(field));
                }

                cmfUserCache.put(idx, solrDocument);
                cacheSolrDocumentList.add(solrDocument);
            } else {
                cacheSolrDocumentList.add((SolrDocument) cmfUserCache.get(idx));
            }
        }

        return cacheSolrDocumentList;
    }

    /**
     * method to get resultant XML from solr including total records found.
     * @param req
     *            is of type SolrQueryRequest
     * @param cacheEntryList
     *            is of type List<SolrDocument>
     * @return is of type String
     * @throws IOException
     *             is InputOutput Exception
     */
    @SuppressWarnings ("unchecked")
    private String getQueryResultAsXML(String domain,
            final SolrQueryRequest req, final List<SolrDocument> cacheEntryList)
            throws IOException {
        queryKey = HashMapGenerator.getInstanceof(domain).getQueryKey();
        queryResult = FormatSolrXml.getInstanceof(domain).in(cacheEntryList);

        Collection<Object> tempCollection = (Collection<Object>) queryResult
                .remove("collections");

        tempCollection.add(countEntry);

        queryResult = new HashMap<String, Object>();

        queryResult.put("Collections", tempCollection);

        return FormatSolrXml.getInstanceof(domain).out(queryResult, queryKey)
                .toString();
    }

    /**
     * executes the request with solr query request, solr query response.
     * @param req
     *            is of type HttpServletRequest
     * @param sreq
     *            is of type SolrQueryRequest
     * @param rsp
     *            is of type SolrQueryResponse
     */
    private void execute(final HttpServletRequest req,
            final SolrQueryRequest sreq, final SolrQueryResponse rsp) {
        sreq.getContext().put("webapp", req.getContextPath());
        sreq.getCore().execute(handler, sreq, rsp);
    }
}
