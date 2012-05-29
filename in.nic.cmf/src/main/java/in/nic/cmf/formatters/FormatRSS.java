package in.nic.cmf.formatters;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.DateUtils;
import in.nic.cmf.util.Utils;

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

import org.apache.commons.io.IOUtils;

/**
 * The Class FormatRSS.
 */
public class FormatRSS implements Formatters {

    /** The Constant DEFAULT_CONTENT_TYPE. */
    public static final String  DEFAULT_CONTENT_TYPE = "application/xml";

    /** The Constant DATE_FORMAT_NOW. */
    private static final String DATE_FORMAT_NOW      = "EEE, dd MMM yyyy HH:mm:ss zzz"; // RSS
    // FEED
    // Date
    // Format
    /** The formatter. */
    private DateFormat          formatter            = new SimpleDateFormat(
                                                             DATE_FORMAT_NOW);

    /** The PropertiesUtil. */
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
            Map<String, Map<String, Object>> parameters) {
        Map<String, Object> output = parameters.get("output");
        String value = (String) output.get("content");
        Utils utils = Utils.getInstanceof(domain);
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
            // is = new FileInputStream("/opt/cmf/resources/" + fileName);
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
            HashMap<String, Object> rssObject = new HashMap<String, Object>();
            List<String> interfaceList = new ArrayList<String>();
            List<Object> listItem = new ArrayList<Object>();
            HashMap<String, Object> interfaceHash = (HashMap<String, Object>) FormatJson
                    .getInstanceof(domain).in(
                            FileRead(domain, "InterfaceDetails.json"));
            interfaceList = (List<String>) interfaceHash.get("Rssable");
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
                                listItem.add(buildItem(domain, eachEntityHash));
                            }
                        } else if (e.getValue().getClass().getSimpleName()
                                .equalsIgnoreCase("HashMap")) {
                            listItem.add(buildItem(domain,
                                    (HashMap<String, Object>) e.getValue()));
                        }

                    } else {
                        System.out.println(key + " not implemented Rssable");
                    }
                }
            }
            System.out.println("After list");
            HashMap<String, Object> channelObject = buildChannel(domain);
            System.out.println("after listitem");
            channelObject.put("Item", listItem);
            rssObject.put("Channel", channelObject);
            String content = (String) FormatXml.getInstanceof(domain).out(
                    rssObject);
            return content;
        } catch (GenericException e) {
            throw e;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":formatOutput()", e);
        }
    }

    /**
     * Builds the item.
     * @param entityObject
     *            the entity object
     * @return the rss item
     * @throws GenericException
     *             the generic exception
     */

    private HashMap<String, Object> buildItem(String domain,
            HashMap<String, Object> entityObject) throws GenericException {
        try {
            System.out.println("Inside BuildItem");
            System.out.println(entityObject);
            HashMap<String, Object> rssitem = new HashMap<String, Object>();
            rssitem.put("Description", entityObject.get("Description"));
            rssitem.put("Guid", entityObject.get("SeoUrl"));
            rssitem.put("Link", entityObject.get("SeoUrl"));
            if (entityObject.get("CreatedDate") != null) {
                String datevalue = (String) entityObject.get("CreatedDate");
                System.out.println(datevalue);
                if (!datevalue.isEmpty()) {
                    System.out.println("dskfdslfkjdlfjld");
                    DateUtils dateUtils = DateUtils.getInstanceOf(domain);
                    Date pubDate = dateUtils.getFormattedDateByXml(datevalue);
                    System.out.println("After PubDate " + pubDate);
                    rssitem.put("PubDate", pubDate.toGMTString());
                }
            }
            rssitem.put("Title", entityObject.get("Title"));
            System.out.println("After rssItem");
            return rssitem;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":buildItem()", e);
        }
    }

    /**
     * Builds the image.
     * @return the rss image
     * @throws GenericException
     *             the generic exception
     */

    private HashMap<String, Object> buildImage(String domain)
            throws GenericException {
        try {
            HashMap<String, Object> rssimage = new HashMap<String, Object>();
            rssimage.put("Link", propUtil.getProperty("RssImageLink"));
            rssimage.put("Title", propUtil.getProperty("RssImageTitle"));
            rssimage.put("Url", propUtil.getProperty("RssImageUrl"));
            return rssimage;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":buildImage()", e);
        }
    }

    /**
     * Builds the channel.
     * @param channelObject
     *            the channel object
     * @return the rss channel
     * @throws GenericException
     *             the generic exception
     */

    private HashMap<String, Object> buildChannel(String domain)
            throws GenericException {
        try {
            System.out.println("Inside BuildChannel");
            System.out.println(propUtil.getProperty("domain"));
            System.out.println(propUtil.getProperty("RssChannelDescription"));
            System.out.println("After PropertyUtil");
            HashMap<String, Object> channelObject = new HashMap<String, Object>();
            channelObject.put("Description",
                    propUtil.getProperty("RssChannelDescription"));
            channelObject.put("Title", propUtil.getProperty("RssChannelTitle"));
            channelObject.put("Language",
                    propUtil.getProperty("RssChannelLanguage"));
            channelObject.put("LastBuildDate", formatter.format(new Date()));
            channelObject.put("Link", propUtil.getProperty("RssChannelLink"));
            channelObject.put("PubDate", formatter.format(new Date()));
            channelObject.put("Image", buildImage(domain));
            return channelObject;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-DS-0017", this.getClass()
                    .getSimpleName() + ":buildChannel()", e);
        }
    }

}
