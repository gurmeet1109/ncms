package in.nic.cmf.searchengine;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * Class is used to frame solrquery from HashMap given which will consist of
 * method name as key and actual data as value
 * @author Thamaiyanthi P
 */
public class SolrQueryFramer {

    /** Field: solrQuery to hold {@link java.util.Properties} object. */
    // public static final SolrQueryFramer SOLRQUERYFRAMER = new
    // SolrQueryFramer();

    public LogTracer                                log;
    private static HashMap<String, SolrQueryFramer> hashSolrQueryFramer = new HashMap<String, SolrQueryFramer>();
    private String                                  domain;

    private SolrQueryFramer(String domain) {
        this.domain = domain;
    }

    // public static SolrQueryFramer getInstance() {
    // return SOLRQUERYFRAMER;
    // }

    public static SolrQueryFramer getInstance(String domain) {
        if (!hashSolrQueryFramer.containsKey(domain)) {
            hashSolrQueryFramer.put(domain, new SolrQueryFramer(domain));
        }
        return hashSolrQueryFramer.get(domain);
    }

    /**
     * Enum is used to switch the appropriate method call
     * @author thamaiyanthi
     */
    static enum SolrEnum {
        /** Field: PARAM to hold location details value */
        PARAM,
        /** Field: START to hold offset */
        START,
        /** Field: QUERY to hold query. */
        QUERY,
        /** Field: SORTFIELD to hold field to be sort. */
        SORTFIELD,
        /** Field: FIELDS to hold fields to index. */
        FIELDS,
        /** Field: ROWS to hold fields. */
        ROWS
    }

    /**
     * Method will return SolrQuery when given hashmap as a collection of query
     * Hashmap is the collection on of method name as key and vale is the data
     * to be indexed
     * @param querycoll
     * @return SolrQuery
     */
    public SolrQuery getSolrQuery(HashMap<String, Object> querycoll)
            throws GenericException {
        try {
            SolrQuery solrQuery = new SolrQuery();
            Iterator<Map.Entry<String, Object>> iterator = querycoll.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                        .next();
                String keyValue = entry.getKey().toString();
                solrQuery = frameSolrQuery(keyValue, querycoll, solrQuery);
            }
            return solrQuery;
        } catch (GenericException e) {
            log.error("GenericException throws :" + e.getMessage());
            throw new GenericException(domain, "ERR-SE-0008", this.getClass()
                    .getSimpleName() + ".getSolrQuery()", querycoll.toString(),
                    e);
        }
    }

    /**
     * Method used to frame query for every given key
     * @param key
     * @throws GenericException
     */
    private SolrQuery frameSolrQuery(String key,
            HashMap<String, Object> querycoll, SolrQuery solrQuery)
            throws GenericException {
        log.debug("In FrameSolrQuery :::: " + key + " ::::: " + querycoll
                + " ::::: " + solrQuery);
        SolrEnum numeral = null;
        numeral = SolrEnum.valueOf(key.toUpperCase());
        switch (numeral) {
            case PARAM:
                Iterator<Map.Entry<String, Object>> parmiterator = querycoll
                        .entrySet().iterator();
                while (parmiterator.hasNext()) {
                    Map.Entry<String, Object> paramentry = (Map.Entry<String, Object>) parmiterator
                            .next();
                    String paramkey = paramentry.getKey().toString();
                    solrQuery.setParam(paramkey,
                            (String) querycoll.get(paramkey));
                }
                break;
            case START:
                solrQuery.setStart(Integer.parseInt(querycoll.get(key)
                        .toString()));
                break;
            case QUERY:
                solrQuery.setQuery(querycoll.get(key).toString());
                break;
            case SORTFIELD:
                HashMap<String, String> sortfieldmap = (HashMap<String, String>) querycoll
                        .get(key);
                for (Map.Entry<String, String> e : sortfieldmap.entrySet()) {
                    solrQuery.setSortField(e.getKey(),
                            SolrQuery.ORDER.valueOf(e.getValue()));
                }
                break;
            case FIELDS:
                solrQuery.setFields(querycoll.get(key).toString());
                break;
            case ROWS:
                solrQuery.setRows(Integer.parseInt(querycoll.get(key)
                        .toString()));
                break;
        }
        log.debug("solrQuery in frameSolrQuery() => " + solrQuery);
        return solrQuery;
    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
    }
}
