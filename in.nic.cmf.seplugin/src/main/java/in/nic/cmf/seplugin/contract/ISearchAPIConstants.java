package in.nic.cmf.seplugin.contract;

/**
 * Interface for search API constants.
 * @author premananda
 */
public interface ISearchAPIConstants {
    /**
     * constant to hold cached data.
     */
    String CACHED_DATA = "CACHED_DATA";
    /**
     * constant to hold properties.
     */
    String PROPERTIES_EXT = ".properties";
    /**
     * constant to hold configuration.
     */
    String CONFIG = "config";
    /**
     * constant to hold entity type.
     */
    String ENTITY_TYPE = "entitytype";
    /**
     * constant to hold article.
     */
    String ARTICLE = "Article";
    /**
     * constant to hold gallery.
     */
    String GALLERY = "Gallery";
    /**
     * constant to hold package name.
     */
    String PACKAGE_NAME = "packagename";
    /**
     * constant to hold method prefix.
     */
    String METHOD_PREFIX = "set";
    /**
     * constant to hold success.
     */
    String SUCCESS = "success";
    /**
     * constant to hold mime type.
     */
    String MIME_TYPE_TEXT = "text/plain";
    /**
     * constant to hold latitude in short.
     */
    String LAT = "lat";
    /**
     * constant to hold longitude in short.
     */
    String LONG = "long";
    /**
     * constant to hold latitude.
     */
    String LATITUDE = "latitude";
    /**
     * constant to hold longitude.
     */
    String LONGITUDE = "longitude";
    /**
     * constant to hold radius.
     */
    String RADIUS = "radius";
    /**
     * constant to hold geographic details.
     */
    String GEO = "geo";
    /**
     * constant to hold query type.
     */
    String QT = "qt";
    /**
     * constant to hold user data.
     */
    String USER_DATA = "userData";
    /**
     * constant to hold solr data.
     */
    String SOLR_DATA = "solrData";
    /**
     * constant to hold solr key list.
     */
    String SOLR_KEY_LIST = "solrkeylist";
    /**
     * constant to hold default condition.
     */
    String DEFAULT_CONDITION = "defaultcondition";
    /**
     * constant to hold and condition.
     */
    String CONDAND = "AND";
    /**
     * constant to hold or condition.
     */
    String CONDOR = "OR";
    /**
     * constant to hold condition.
     */
    String COND = "cond";
    /**
     * constant to hold text.
     */
    String TEXT = "text";
    /**
     * constant to hold hyphen5000.
     */
    String HYPHEN5000 = "5000";
    /**
     * constant to hold query.
     */
    String QUERY = "q";
    /**
     * constant to solr query format.
     */
    String SOLR_QUERY_FORMAT = "*:*";
    /**
     * constant to hold maximum limit.
     */
    String MAXIMUM_LIMIT = "maximumlimit";
    /**
     * constant to hold limit.
     */
    String LIMIT = "limit";
    /**
     * constant to hold offset.
     */
    String OFFSET = "offset";
    /**
     * constant to hold order by directive.
     */
    String ORDER_BY_DIR = "orderbydir";
    /**
     * constant to hold colon.
     */
    String COLON = ":";
    /**
     * constant to hold ascending.
     */
    String ASCENDING = "asc";
    /**
     * constant to hold descending.
     */
    String DESCENDING = "desc";
    /**
     * constant to hold text colon.
     */
    String TEXTC = "text:";
    /**
     * constant to hold empty string.
     */
    String EMPTY_STRING = "";
    /**
     * constant to hold solr url.
     */
    String SOLRURL = "-solrurl";
    /**
     * constant to hold hyphen.
     */
    String HIFEN = "-";
    /**
     * constant to hold comma.
     */
    String COMMA = ",";
    /**
     * constant to hold q.
     */
    String Q = "q";
    /**
     * constant to hold dot.
     */
    String DOT = ".";
    /**
     * constant to hold minimum radius.
     */
    String RADIUSMIN = "1";
    /**
     * constant to hold maximum radius.
     */
    String RADIUSMAX = "250";
    /**
     * constant to hold solr exception.
     */
    String SOLREXCEPTION = "500";
    /**
     * constant to hold MD5.
     */
    String MD5 = "MD5";
    /**
     * constant to hold UTF8.
     */
    String UTF8 = "UTF8";
    /**
     * constant to hold OK status.
     */
    String OK_STATUS = "200";
    /**
     * constant to bad request status.
     */
    String BAD_REQUEST_STATUS = "400";
    /**
     * constant to hold id length.
     */
    String ID_LENGTH = "13";

    /**
     * abstract method to implement.
     */
    void sample();
}
