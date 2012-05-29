package in.nic.cmf.formatters;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.DateUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;

/**
 * The Class FormatSiteMap.
 */
public class FormatSITEMAP implements Formatters {

    /** The Constant DEFAULT_CONTENT_TYPE. */
    public static final String  DEFAULT_CONTENT_TYPE = "application/xml";

    /** The Constant DATE_FORMAT_NOW. */
    private static final String DATE_FORMAT_NOW      = "EEE, dd MMM yyyy HH:mm:ss zzz";

    /** The formatter. */
    private DateFormat          formatter            = new SimpleDateFormat(
                                                             DATE_FORMAT_NOW);

    /** The rendered attributes. */
    private Set<String>         renderedAttributes;

    /** The propertiesutil. */
    private PropertiesUtil      propUtil;

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
        Map<String, Object> output = parameters.get("output");
        String value = (String) output.get("content");
        // Utils utils = Utils.getInstanceof(domain);
        FormatXml f = FormatXml.getInstanceof(domain);
        HashMap<String, Object> h = f.in(value);
        try {
            String content = formatOutput(domain, h);
            HashMap<String, String> headers = new HashMap<String, String>();
            if (parameters.get("output").containsKey("headers")) {
                headers = (HashMap<String, String>) parameters.get("output")
                        .get("headers");
            }
            headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
            output.put("headers", headers);
            output.put("content", content);
            parameters.put("output", output);
        } catch (GenericException e) {
            throw e;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":renderMergedOutputModel()", e);
        }
        return parameters;
    }

    public Object FileRead(String domain, String fileName) {
        InputStream is;
        String response = "";
        try {
            is = new FileInputStream("/opt/cmf/" + domain + "/resources/"
                    + fileName);
            response = IOUtils.toString(is);
        } catch (FileNotFoundException e) {
            throw new GenericException(domain, "ERR-GEN-0012", this.getClass()
                    .getSimpleName() + ":FileRead()", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-GEN-0000", this.getClass()
                    .getSimpleName() + ":FileRead()", e);
        }
        return response;
    }

    /**
     * Format output.
     * @param listEntities
     *            the list entities
     * @param response
     *            the response
     * @throws GenericException
     *             the generic exception
     */
    private String formatOutput(String domain, HashMap<String, Object> hash)
            throws GenericException {
        try {
            HashMap<String, Object> sitemapObject = new HashMap<String, Object>();
            List<String> interfaceList = new ArrayList<String>();
            List<Object> listUrl = new ArrayList<Object>();
            HashMap<String, Object> interfaceHash = (HashMap<String, Object>) FormatJson
                    .getInstanceof(domain).in(
                            FileRead(domain, "InterfaceDetails.json"));
            interfaceList = (List<String>) interfaceHash.get("Sitemappable");
            System.out.println(interfaceList);
            HashMap<String, Object> collHash = (HashMap<String, Object>) hash
                    .get("Collections");
            for (Entry<String, Object> e : collHash.entrySet()) {
                String key = (String) e.getKey();
                if (!key.equals("Count")) {
                    if (interfaceList.contains(key)) {
                        System.out.println(key);
                        if (e.getValue().getClass().getSimpleName()
                                .equalsIgnoreCase("ArrayList")) {
                            List<HashMap<String, Object>> entityListOfHash = (List<HashMap<String, Object>>) e
                                    .getValue();
                            for (HashMap<String, Object> eachEntityHash : entityListOfHash) {
                                listUrl.add(buildUrl(domain, eachEntityHash));
                            }
                        } else if (e.getValue().getClass().getSimpleName()
                                .equalsIgnoreCase("HashMap")) {
                            listUrl.add(buildUrl(domain,
                                    (HashMap<String, Object>) e.getValue()));
                        }

                    } else {
                        System.out.println(key
                                + " not implemented Sitemappable");
                    }
                }
            }
            sitemapObject.put("Url", "");
            if (listUrl.size() > 0) {
                sitemapObject.put("Url", listUrl);
            }
            String content = (String) FormatXml.getInstanceof(domain).out(
                    sitemapObject);
            return content;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":formatOutput()", e);
        }
    }

    /**
     * Builds the url.
     * @param entityObject
     *            the entity object
     * @return the sitemap url
     * @throws GenericException
     *             the generic exception
     */
    private HashMap<String, Object> buildUrl(String domain,
            HashMap<String, Object> entityObject) throws GenericException {
        try {
            HashMap<String, Object> sitemapUrl = new HashMap<String, Object>();
            sitemapUrl.put("Changefreq",
                    propUtil.getProperty("SitemapChangefreq"));

            if (entityObject.get("CreatedDate") != null) {
                String datevalue = (String) entityObject.get("CreatedDate");
                if (!datevalue.isEmpty()) {
                    DateUtils dateUtils = DateUtils.getInstanceOf(domain);
                    Date pubDate = dateUtils.getFormattedDateByXml(datevalue);
                    sitemapUrl.put("Lastmod", pubDate.toGMTString());
                }
            }

            sitemapUrl.put("Loc", entityObject.get("SeoUrl"));
            sitemapUrl.put("Priority", propUtil.getProperty("SitemapPriority"));
            return sitemapUrl;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":buildUrl()", e);
        }
    }
}
