package in.nic.cmf.dffeedautomation;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.DynamicAuthentication;
import in.nic.cmf.util.UserContext;
import in.nic.cmf.util.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.StatefulJob;
import org.quartz.impl.StdSchedulerFactory;

public class FeedAggregation implements StatefulJob {

    private static String domain ;
  //  private LogTracer log = new LogTracer("feedautomation");
  //  private PropertiesUtil proputil = new PropertiesUtil("feedautomation");
    private static PropertiesUtil proputil;
    private LogTracer log;
   
    private Map<String, String> hUserContext = new HashMap<String, String>();
  
    
    
    private String[] masks = { "EEE, dd MMM yyyy HH:mm:ss z",
            "dd MMM yyyy HH:mm z", "dd/MM/yyyy HH:mm:ss z", "dd MMM yyyy",
            "-yy-MM-dd", "-yy-MM", "-yymm", "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSSz",
            "yyyy-MM-dd't'HH:mm:ss.SSSz", // invalid
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd't'HH:mm:ss.SSS'z'", // invalid
            "yyyy-MM-dd'T'HH:mm:ssz", "yyyy-MM-dd't'HH:mm:ssz", // invalid
            "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd't'HH:mm:ss'Z'", // invalid
            "yyyy-MM-dd't'HH:mm:ss'z'", // invalid
            "yyyy-MM-dd'T'HH:mm:ss'z'", // invalid
            "yyyy-MM-dd'T'HH:mm:ss'z'", // invalid
            "yyyy-MM-dd'T'HH:mmz", // invalid
            "yyyy-MM-dd't'HH:mmz", // invalid
            "yyyy-MM-dd'T'HH:mm'Z'", // invalid
            "yyyy-MM-dd't'HH:mm'z'", // invalid
            "yyyy-MM-dd", "yyyy-MM", "yyyyMMdd", "yyMMdd", "yyyy" };
    
    private void setLogger(String domain) {
        log = new LogTracer(domain, "feedautomation");
    }

        
    public static void main(final String[] args) {
        FeedAggregation feedagg = new FeedAggregation();
        setDomain(args[0]);
        proputil = PropertiesUtil.getInstanceof(args[0], "feedautomation");
      
        try {
            SchedulerFactory schedulerfactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerfactory.getScheduler();
            scheduler.start();
            JobDetail jobdetail = new JobDetail("Income Report",
                    "Report Generation", FeedAggregation.class);
            jobdetail.getJobDataMap().put("type", "FULL");
            CronTrigger trigger = new CronTrigger("Income Report",
                    "Report Generation");
            trigger.setCronExpression(feedagg.proputil
                    .getProperty("org.quartz.CronTrigger.sched"));
            scheduler.scheduleJob(jobdetail, trigger);
        } catch (SchedulerException e) {
            feedagg.log.error(e.getMessage());
        } catch (ParseException e) {
            feedagg.log.error(e.getMessage());
        }
    }
    
    public void execute(JobExecutionContext jobcontext) throws JobExecutionException, GenericException {
        DynamicAuthentication dyauth = new DynamicAuthentication("feedadmin",
                "5f4dcc3b5aa765d61d8327deb882cf99", getDomain());
        Map<String, String> hUserContext = new HashMap<String, String>();
        if (dyauth.autoSignin()) {
            log.info("Auto Login Success.");
            String authResponse = dyauth.getAuthentication();
            HashMap<String, Object> authResponseMap = (HashMap<String, Object>) FormatXml.getInstanceof(domain).in(authResponse);
            hUserContext = UserContext.getInstanceof(domain).getUserContext(
                    (HashMap<String, Object>) authResponseMap.get("Authentication"));
            log.info("User Context => "+hUserContext);
        } else {
            log.info("Auto Login Failed.");
        }
        
        sethUserContext(hUserContext);
        
        log.info("Feed Aggregation - Start Time : " + Calendar.getInstance().getTime());
        HashMap<String, HashMap<String, String>> contentMap = null;
        try {
            contentMap = getContentPartner();
        } catch (GenericException e) {
            throw e;
        }
        Set<Entry<String, HashMap<String, String>>> set = contentMap.entrySet();
        Iterator<Entry<String, HashMap<String, String>>> itr = set.iterator();
        while (itr.hasNext()) {
            Map.Entry<String, HashMap<String, String>> entryMap = (Map.Entry<String, HashMap<String, String>>) itr.next();
            log.info(entryMap.getValue().toString());
            HashMap<String, String> parsedData = (HashMap<String, String>) entryMap.getValue();
            try {
                parseContent(new URL(parsedData.get("Itemurl")),
                        parsedData.get("Itemtype"), parsedData.get("SourceName")
                                .trim());
            } catch (MalformedURLException e) {
                log.error(""+e.getMessage());
            }
        }
    }

    private HashMap<String, HashMap<String, String>> getContentPartner() throws GenericException {
        HashMap<String, HashMap<String, String>> contentMap = new HashMap<String, HashMap<String, String>>();
      
        
        HashMap<String, Object> collXmlMap = null;
        try {
            collXmlMap = doSearch(proputil.getProperty("DsQuery"));
        } catch (GenericException e) {
            throw e;
        }
        System.out.println("DS coll - "+collXmlMap);
        Object cObject = ((HashMap)collXmlMap.get("Collections")).get("ContentPartner");
        
        if (cObject instanceof HashMap) {
            contentMap.put(((HashMap<String, Object>)cObject).get("Url").toString(), getContentMap((HashMap<String, Object>) cObject));
        } else if (cObject instanceof List) {
            ArrayList<HashMap<String, Object>> hMapList = ((ArrayList<HashMap<String, Object>>)cObject);
            for (HashMap<String, Object> xmlMap : hMapList) {
                System.out.println("xml map -  "+xmlMap);
                contentMap.put(xmlMap.get("Url").toString(), getContentMap(xmlMap));
            }
        }
        return contentMap;
    }
    
    private HashMap<String, String> getContentMap(HashMap<String, Object> contentMap) {
        contentMap.put("SourceType", "rss");
        contentMap.put("Source", contentMap.get("Url"));
        HashMap<String, String> contentDetailMap = null;
        if (contentMap.get("RssAggregated").toString().toLowerCase().equals("false")
                && (!contentMap.get("SourceType").toString().toLowerCase().equals("crawler"))) {
            log.info("Supplement Name : "
                    + contentMap.get("SupplementName").toString().toUpperCase());
            contentDetailMap = new HashMap<String, String>();
            contentDetailMap.put("SourceName", contentMap.get("Source").toString().toUpperCase().trim());
            contentDetailMap.put("Itemurl", contentMap.get("Url").toString());
            contentDetailMap.put("Itemtype", contentMap.get("SourceType").toString());
            contentDetailMap.put("ItemToMaintain", "15");
        }
        return contentDetailMap;
    }
    
    private HashMap<String, Object> doSearch(final String query) throws GenericException {
        log.info("Query : " + query + " :: Domain : "+getDomain() + " :: RequestParam : "+gethUserContext());
        Map searchMap = null;
        HashMap<String, Object> xmlMap = null;
        try {
          //  ServiceClient sc = ServiceClient.getInstance("dataservices");
        	/* searchMap = sc.get(getDomain(),
            query, gethUserContext());*/
            ServiceClientImpl sc = ServiceClientImpl.getInstance(domain, "dataservices");
            Map<String, Map<String,Object>> parameters = new HashMap<String, Map<String, Object>>();
            Map<String, Object> queryParams = new HashMap<String, Object>();
            HashMap<String, Object> userContext = new HashMap<String, Object>();
            HashMap<String, Object> input = new HashMap<String, Object>();
  
            queryParams.put("key", proputil.get("key1"));
            queryParams.put("key", proputil.get("key2"));
            queryParams.put("key", proputil.get("key3"));
            queryParams.put("key", proputil.get("key4"));
            queryParams.put("key", proputil.get("key5"));
            input.put("queryparams", queryParams);
            input.put("Usercontext", gethUserContext());
            parameters.put("input", input);
            searchMap =  sc.find(domain, proputil.get("entity"),parameters);
            
            log.info("Response message : "
                   + searchMap.get("responseStringBody").toString());
            log.info("SC Response : " + searchMap);
            if (searchMap.get("responseCode").toString().trim().equals("200")) {
                xmlMap = FormatXml.getInstanceof(domain).in(searchMap.get("responseStringBody").toString());
                System.out.println("Collection xml map => "+searchMap);
            } else {
                System.out.println("Sc Response : "+searchMap.get("responseCode").toString());
            }
        } catch (GenericException e) {
            log.warn("GenericException throws : " + e.getMessage());
            throw e;
        }
        return xmlMap;
    }
    
    private void parseContent(URL url, final String urltype, final String sourceName) {
        if (url != null) {
            try {
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String aLine = "";
                String content = "";
                while ((aLine = buffReader.readLine()) != null) {
                    content += aLine;
                }
                HashMap<String, Object> hFormatXml = FormatXml.getInstanceof(domain).in(content);
                if (urltype.equalsIgnoreCase("RSS")) {
                    HashMap<String, Object> channelMap = (HashMap<String, Object>) ((HashMap<String, Object>) hFormatXml.get("rss")).get("channel");
                    ArrayList<Object> channelMapList = (ArrayList<Object>) channelMap.get("item");
                    for (int start = channelMapList.size() - 1; start >= 0; start--) {
                        HashMap<String, Object> hNews = getNewsMap(sourceName, urltype);
                        hNews.put("Id", Uniqueid.getId());
                        hNews.put("AssociatedIAPath", "/home/rss");
                        HashMap<String, Object> itemData = (HashMap<String, Object>) channelMapList.get(start);
                        Set<?> set = itemData.entrySet();
                        Iterator<?> itr = set.iterator();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        String pubDate = dateFormat.format(new Date()).toString();
                        String title = null;
                        String description = null;
                        String link = null;
                        while (itr.hasNext()) {
                            Map.Entry entry = (Map.Entry) itr.next();
                            if (entry.getKey().toString().toLowerCase().equals("title")) {
                                title = entry.getValue().toString();
                            } else if (entry.getKey().toString().toLowerCase().equals("description")) {
                                description = entry.getValue().toString();
                            } else if (entry.getKey().toString().toLowerCase()
                                    .trim().equalsIgnoreCase("link")) {
                                link = entry.getValue().toString();
                            } else if (entry.getKey().toString().toLowerCase()
                                    .trim().equalsIgnoreCase("pubdate")) {
                                for (String mask : masks) {
                                    SimpleDateFormat sDateFormat = new SimpleDateFormat(
                                            mask);
                                    sDateFormat.setTimeZone(TimeZone
                                            .getTimeZone("GMT"));
                                    Date date = sDateFormat.parse(entry.getValue()
                                            .toString());
                                    DateFormat dDateFormat = new SimpleDateFormat(
                                            "yyyy-MM-dd'T'HH:mm:ss'Z'");
                                    pubDate = dDateFormat.format(date).toString();
                                }

                            }
                        }
                        hNews.put("Description", description);
                        hNews.put("Title", title);
                        hNews.put("PubDate", pubDate);
                        hNews.put("Url", link);
                        HashMap<String, Object> hImageMap = (HashMap<String, Object>) itemData.get("image");
                        HashMap<String, Object> hImage = new HashMap<String, Object>();
                        if (hImageMap.get("url").toString() != null) {
                            hImage.put("ImageUrl", hImageMap.get("url").toString());
                        } else {
                            hImage.put("ImageUrl", description);
                        }
                        HashMap<String, Object> hDimensionMap = ((HashMap<String, Object>) hImageMap.get("dimension"));
                        HashMap<String, Object> hDimension = new HashMap<String, Object>();
                        hDimension.put("Height", hDimensionMap.get("height").toString());
                        hDimension.put("Width", hDimensionMap.get("width").toString());
                        hImage.put("Dimension", hDimension);
                        hNews.put("Image", hImage);
                        String md5 = Utils.getInstanceof(domain).getMD5(title + sourceName);
                        HashMap<String, Object> collMap = new HashMap<String, Object>();
                        if (!(checkMd5(sourceName, md5))) {
                            collMap.put("Collections", hNews);
                            write(collMap);
                            write(sourceName, md5);
                        }
                    }
                } else if (urltype.equalsIgnoreCase("ATOM")) {
                    HashMap<String, Object> feedMap = (HashMap<String, Object>) hFormatXml.get("feed");
                    ArrayList<Object> feedMapList = (ArrayList<Object>) feedMap.get("entry");
                    for (int start = feedMapList.size() - 1; start >= 0; start--) {
                        HashMap<String, Object> hNews = getNewsMap(sourceName, urltype);
                        HashMap<String, Object> itemData = (HashMap<String, Object>) feedMapList.get(start);
                        Set<?> set = itemData.entrySet();
                        Iterator<?> itr = set.iterator();
                        DateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                        String pubDate = sDateFormat.format(new Date()).toString();
                        String title = "";
                        String link = "";
                        String summary = "";
                        String category = "";
                        String id = "";
                        while (itr.hasNext()) {
                            Map.Entry mEntry = (Map.Entry) itr.next();
                            if (mEntry.getKey().toString().trim().equalsIgnoreCase("title")) {
                                title = mEntry.getValue().toString();
                            } else if (mEntry.getKey().toString().trim().equalsIgnoreCase("content")) {
                                content = mEntry.getValue().toString();
                            } else if (mEntry.getKey().toString().trim().equalsIgnoreCase("link")) {
                                link = mEntry.getValue().toString();
                            } else if (mEntry.getKey().toString().trim().equalsIgnoreCase("id")) {
                                id = mEntry.getValue().toString();
                            } else if (mEntry.getKey().toString().trim().equalsIgnoreCase("update")) {
                                for (String mask : masks) {
                                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                            mask);
                                    dateFormat.setTimeZone(TimeZone
                                            .getTimeZone("GMT"));
                                    Date date = dateFormat.parse(mEntry.getValue()
                                            .toString());
                                    DateFormat dDateFormat = new SimpleDateFormat(
                                            "yyyy-MM-dd'T'HH:mm:ss'Z'");
                                    pubDate = dDateFormat.format(date).toString();
                                }
                            } else if (mEntry.getKey().toString().toLowerCase() == "summary") {
                                summary = mEntry.getValue().toString();
                            } else if (mEntry.getKey().toString().toLowerCase() == "category") {
                                category = mEntry.getValue().toString();
                            }
                        }
                        hNews.put("Summary", summary) ;
                        hNews.put("AtomId", id);
                        hNews.put("Title", title);
                        hNews.put("CreatedDate", pubDate);
                        hNews.put("Description", content.replaceAll("\\<.*?>", ""));
                        hNews.put("Url", link.replaceAll("\\<.*?>", ""));
                        hNews.put("AssociatedIAPath", "/home/atom/");
                        hNews.put("Id", Uniqueid.getId());
                        String md5 = Utils.getInstanceof(domain).getMD5(title + sourceName);
                        HashMap<String, Object> collMap = new HashMap<String, Object>();
                        if (!(checkMd5(sourceName, md5))) {
                            collMap.put("Collections", hNews);
                            write(collMap);
                            write(sourceName, md5);
                        }
                    }   
                }
            } catch (IOException e) {
                log.error("IOException throws : "+e.getMessage());
                e.printStackTrace();
            } catch (ParseException e) {
                log.error("ParseException throws : "+e.getMessage());
                e.printStackTrace();
            }
        } else {
            log.warn("Url is Empty - "+url);
        }
    }
    
    private void write(HashMap<String, Object> collMap) {
    	 Map<String, Map<String,Object>> parameters = new HashMap<String, Map<String, Object>>();
         Map<String, Object> queryParams = new HashMap<String, Object>();
         HashMap<String, Object> input = new HashMap<String, Object>();
         queryParams.put("key", proputil.get("key1"));
         queryParams.put("key", proputil.get("key2"));
         queryParams.put("key", proputil.get("key3"));
         queryParams.put("key", proputil.get("key4"));
         queryParams.put("key", proputil.get("key5"));
         
         input.put("queryparams", queryParams);
         input.put("Usercontext", gethUserContext());
         parameters.put("input", input);
    	 Map<?, ?> response= ServiceClientImpl.getInstance(domain, "dataservices").add(domain, proputil.get("entity"), parameters);
      /*  Map<?, ?> response  = ServiceClient.getInstance("dataservices").post(getDomain(),
                (String) FormatXml.getInstanceof(domain).out(collMap), gethUserContext(), "application/xml");*/
        log.info("Sc post response : "+response.get("responseStringBody").toString());
    }
    
    private boolean write(final String sourceName, final String md5) {
        BufferedWriter bufWriter = null;
        String sName = sourceName;
        String md5Dir = proputil.getProperty("MD5DIR");
        if (!md5Dir.endsWith("/")) {
            sName = "/" + sName;
        }
        try {
            createDirectory( md5Dir + sName + "/");
            bufWriter = new BufferedWriter(new FileWriter(md5Dir + sourceName + "/"
                            + md5));
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                bufWriter.close();
            } catch (IOException e) {
            }
        }
    }
    
    private boolean createDirectory(final String dirName) {
        File file = new File(dirName);
        if (file.exists()) {
            return true;
        } else {
            log.info("Directory Name : " + dirName);
            return file.mkdirs();
        }
    }
    
    private HashMap<String, Object> getNewsMap(String urlType, String sourceName) {
        HashMap<String, Object> hNews = new HashMap<String, Object>();
        HashMap<String, Object> hCategories = new HashMap<String, Object>();
        HashMap<String, Object> hCategory = new HashMap<String, Object>();
        List<String> categoryList = new ArrayList<String>();
        categoryList.add(sourceName);
        categoryList.add(urlType);
        hCategory.put("Category", categoryList);
        hCategories.put("Categories", hCategory);
        hNews.put("Categories", hCategories);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        hNews.put("CreatedDate", dateFormat.format(new Date()));
        hNews.put("LastModifiedDate", dateFormat.format(new Date()));
        hNews.put("CreatedBy", "feedadmin");
        hNews.put("LastModifiedBy", "feedadmin");
        hNews.put("EntityType", "News");
        hNews.put("Version", "1.0");
        hNews.put("Site", getDomain());
        return hNews;
    }
    
    private boolean checkMd5(final String sourceName, final String md5) {
        String sName = sourceName;
        String md5Dir = proputil.getProperty("MD5DIR");
        if (!md5Dir.endsWith("/")) {
            sName = "/" + sName;
        }
        File file = new File(proputil.getProperty("MD5DIR") + sName
                + "/" + md5);
        return file.isFile();
    }
    
    public static void setDomain(String domainname) {
        domain = domainname;
    }

    public static String getDomain() {
        return domain;
    }
    
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    public void sethUserContext(Map<String, String> hUserContext) {
        this.hUserContext = hUserContext;
    }

    public Map<String, String> gethUserContext() {
        return hUserContext;
    }

}
