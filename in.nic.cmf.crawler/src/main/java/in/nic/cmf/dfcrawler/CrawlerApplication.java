package in.nic.cmf.dfcrawler;




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
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.StatefulJob;
import org.quartz.impl.StdSchedulerFactory;

/**
 * This class is used to aggregate the WebFeeds of RSS Type and Atom Type.
 * 
 * @author SIFY
 */
public class CrawlerApplication implements StatefulJob {

    private Map<String, String> hRequestParam = new HashMap<String, String>();
    private static String domain = "";
    private LogTracer log ;
    private static PropertiesUtil proputil ;
    //private PropertiesUtil proputil = new PropertiesUtil("crawler");
    //private LogTracer log = new LogTracer("CrawlerLogger");
    /**
     * This is the main method to execute/trigger the feed aggregation.
     * 
     * @param args
     *            as input value which is type of {@link java.io.Exception}
     *            class.
     */
    private void setLogger(String domain) {
        log = new LogTracer(domain, "CrawlerLogger");
    }

    public static void main(String[] args) {
        CrawlerApplication crawlerApp = new CrawlerApplication();
        crawlerApp.setDomain(args[0]);
        proputil = PropertiesUtil.getInstanceof(args[0], "crawler");
        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail jobDetail = new JobDetail("Income Report",
                    "Report Generation", CrawlerApplication.class);
            jobDetail.getJobDataMap().put("type", "FULL");
            CronTrigger trigger = new CronTrigger("Income Report",
                    "Report Generation");
            trigger.setCronExpression(crawlerApp.proputil
                    .getProperty("org.quartz.CronTrigger.sched"));
            sched.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            crawlerApp.log.info(e.getMessage());
        } catch (ParseException e) {
            crawlerApp.log.info(e.getMessage());
        }
    }

    /**
     * 
     */
    public void execute(final JobExecutionContext cntxt) {
        DynamicAuthentication dyauth = new DynamicAuthentication("feedadmin",
                "5f4dcc3b5aa765d61d8327deb882cf99", getDomain());
        Map<String, String> hRequestParams = new HashMap<String, String>();
        if (dyauth.autoSignin()) {
            log.info("Auto Login Success.");
            String authResponse = dyauth.getAuthentication();
            HashMap<String, Object> authResponseMap = (HashMap<String, Object>) FormatXml
                    .getInstanceof(domain).in(authResponse);
            hRequestParams = UserContext.getInstanceof(domain).getUserContext(
                    (HashMap<String, Object>) authResponseMap
                            .get("Authentication"));
            log.info("" + hRequestParams);
        } else {
            log.info("Auto Login Fail.");
        }

        sethRequestParam(hRequestParams);
        // log.info("hRequestParam"+gethRequestParam());
        try {
            Calendar cal = Calendar.getInstance();
            log.info("Start Cralwer Application " + cal.getTime());
            HashMap<String, String> urlMap = returnTypeUrls();
            System.out.println("Result final:::::::::::::::::::::::::::::::::::"+urlMap);
            Set set = urlMap.entrySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
            	System.out.println("Entered into while loop");
                Map.Entry me = (Map.Entry) i.next();
                log.info(me.getValue().toString());
                CrawlData(me.getKey().toString(), me.getValue().toString());
            }
        } catch (Exception e) {
            log.info("Error executing urlAggreation: " + e.getMessage());
        }
    }

    private void CrawlData(String Itemurl, String sourceType)// HashMap<String,
                                                             // String>
                                                             // parsedDatas)
            throws MalformedURLException {
        // log.info(" enter into crawler data : gethRequestParam : "+gethRequestParam());
        URL url = new URL(Itemurl);
        if (url != null) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        url.openStream()));

                String inputLine = "";
                String fullContent = "";

                while ((inputLine = in.readLine()) != null) {
                    fullContent += inputLine;
                }
                String md5 = getMD5(fullContent);
                String plainUrlData = (url.toString().replaceAll("/", ""))
                        .replaceAll(":", "");
                log.info("Md5: " + md5 + " Url: " + plainUrlData);
                if (checkMd5(plainUrlData, md5)) {
                    log.info("Same Data is alread available");
                    System.out.println(":::::::::::::::::::::::::::::::;;;;Same Data is alread available");
                } else {
                	 System.out.println(":::::::::::::::::::::::::::::::;;;;before dosearch gethRequestParam");
                    log.info(" before dosearch gethRequestParam : "
                            + gethRequestParam());
                    //Collections oCollection = doSearch("q=&entitytype=news&url="
                          //  + URLEncoder.encode("\"" + url.toString() + "\"",
                                 //   "UTF-8"));
                    HashMap oCollection = doSearch("q=&entitytype=news&url="
                            + URLEncoder.encode("\"" + url.toString() + "\"",
                                    "UTF-8"));
                    HashMap collections=new HashMap();
                    collections=(HashMap) oCollection.get("Collections");
                    
                    System.out.println("Output::::::::::::::::::::::::::::::::::::::::"+oCollection);
                    HashMap oNews = null;
                    HashMap collectionsRoot=new HashMap();
                    if (new Integer(collections.get("Count").toString())>0) {
                    	if(collections.get("News") instanceof HashMap){
                        oNews = (HashMap) collections.get("News");
                     // oNews.setDescription(returnStripContent(fullContent));
                        oNews.put("Description",returnStripContent(fullContent));
                        // log.info(oNews.getDescription());
                       // oNews.setUrl(url.toString());
                        oNews.put("Url",url.toString());
                        SimpleDateFormat df = new SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss'Z'");
                        //oNews.setLastModifiedDate(df.format(new Date()));
                        oNews.put("LastModifiedDate",df.format(new Date()));
                        log.info("Update the exising article  with new description"
                                + oNews.get("Id"));
                        collections.put("News",oNews);
                       
                       
                    	}
                    	else if(collections.get("News") instanceof List)
                    	{
                    		List newsList=(List) oCollection.get("News");
                    		List newsPostList=new ArrayList();
                    		for(int i=0;i<newsList.size();i++){
                    			oNews=	(HashMap) newsList.get(i);
                    			// oNews.setDescription(returnStripContent(fullContent));
                                oNews.put("Description",returnStripContent(fullContent));
                                // log.info(oNews.getDescription());
                               // oNews.setUrl(url.toString());
                                oNews.put("Url",url.toString());
                                SimpleDateFormat df = new SimpleDateFormat(
                                        "yyyy-MM-dd'T'HH:mm:ss'Z'");
                                //oNews.setLastModifiedDate(df.format(new Date()));
                                oNews.put("LastModifiedDate",df.format(new Date()));
                                log.info("Update the exising article  with new description"
                                        + oNews.get("Id"));
                                newsPostList.add(oNews);
                               
                    		}
                    		 collections.put("News",newsPostList);
                    	}
                       
                    } else {
                    	oNews=new HashMap();
                        String Id = Uniqueid.getId();
                        log.info("Data is fresh and Creating Now." + Id);
                        //oNews.setId(Id);
                        oNews.put("Id",Id);
                       // oNews.setEntityType("News");
                        oNews.put("EntityType","News");
                        List<String> category = new ArrayList<String>();
                        category.add("Crawler");
                       // Categories cat = new Categories();
                        HashMap cat = new HashMap();
                       // cat.setCategory(category);
                        cat.put("Category",category);
                       // oNews.setCategories(cat);
                        oNews.put("Categories",cat);
                        SimpleDateFormat df = new SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss'Z'");
                        oNews.put("CreatedDate",df.format(new Date()));
                        oNews.put("LastModifiedDate",df.format(new Date()));
                        oNews.put("PubDate",df.format(new Date()));
                        oNews.put("CreatedBy","feedadmin");
                        oNews.put("LastModifiedBy","feedadmin");
                        oNews.put("AssociatedIAPath","Temp");
                        oNews.put("Version","1.0");
                        oNews.put("Site",getDomain());
                       // oNews.put("AssociatedIAPath","/home/crawler");

                        oNews.put("Title",returnTitle(fullContent));
                        oNews.put("Url",url.toString());
                        oNews.put("Description",returnStripContent(fullContent));                      
                        collections.put("News",oNews);
                       
                    }
                    collectionsRoot.put("Collections", collections);
                    System.out.println("news result here nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnEEEEEEE"+collectionsRoot);
                    writeMD5(plainUrlData, md5);
                    writeCollectionData(collectionsRoot);
                }
            } catch (Exception ex) {
                System.out.println(" ex " + ex);
            }
        }
    }

    // /**
    // * @param domain
    // * @return
    // */
    // private Map<String, String> doAutoAuth() throws GenericException {
    // DynamicAuthentication dyauth = new DynamicAuthentication("feedadmin",
    // "5f4dcc3b5aa765d61d8327deb882cf99", getDomain());
    // Map<String, String> hRequestParams = new HashMap<String, String>();
    // // need to handle generic exception - to stop the proces
    // // try {
    // if (dyauth.autoSignin()) {
    // log.info("Auto Login Success.");
    // String authResponse = dyauth.getAuthentication();
    // HashMap<String, Object> authResponseMap = (HashMap<String, Object>)
    // FormatXml
    // .getInstance().in(authResponse);
    // hRequestParams = UserContext.getInstance().getUserContext(
    // (HashMap<String, Object>) authResponseMap
    // .get("Authentication"));
    // log.info("" + hRequestParams);
    // /*
    // * hRequestParam.put("authusername", "feedadmin");
    // * hRequestParam.put("aclrole", authMap.get("aclrole"));
    // * hRequestParam.put("api_key", authMap.get("api_key"));
    // */
    // return hRequestParams;
    // } else {
    // log.info("Auto Login Fail.");
    // }returnTypeUrls
    // // } catch (Exception e) {
    // // log.info("Exception occured" + e);
    // // }
    // return null;
    // }

    private void writeCollectionData(final HashMap collection)
            throws Exception {
        try {
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
            input.put("Usercontext", gethRequestParam());
            parameters.put("input", input);
            Map map =ServiceClientImpl.getInstance(domain, "dataservices").add(domain, proputil.get("entity"), parameters);
           /* Map map=ServiceClient.getInstance("dataservices").post(getDomain(),
                   (String) FormatXml.getInstanceof(domain).out(collection), gethRequestParam(), "application/xml");*/
         
           log.info("Response message : "
                   + map.get("responseStringBody").toString());
            log.info("posted:::::::::::::::::::::::::::::;;"+map);
        } catch (Exception ex) {
            log.info("Exception occured while writing data :" + ex);
        }
    }

    /**
     * @param query
     * @return
     * @throws Exception
     */
    private HashMap doSearch(final String query) {
        log.info("Query is in do search()" + query + " gethRequestParam : "
                + gethRequestParam());
        HashMap cc = new HashMap();
        Map result = new HashMap();
        try {
        	
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
            input.put("Usercontext", gethRequestParam());
            parameters.put("input", input);
            result =  sc.find(domain, proputil.get("entity"),parameters);
           /* result = ServiceClient.getInstance("dataservices").get(getDomain(),
                    query, gethRequestParam());*/
            log.info("Response message : "
                    + result.get("responseStringBody").toString());
            log.info("Response Code : " + result.get("responseCode").toString());
            if (result.get("responseCode").toString().trim().equals("200")) {
               // cc = Utils.getInstance().getStringXmlAsCollections(
                       // result.get("responseStringBody").toString());
                cc = FormatXml.getInstanceof(domain).in(
                        result.get("responseStringBody").toString());
                System.out.println("do true");
            } else {
                System.out.println("do search else");
            }
        } catch (Exception ex) {
            log.info("do Search Exception occured." + ex + " - query: " + query);
        }
        return cc;
    }

   
	/**
     * @return
     * @throws Exception
     */
    private HashMap<String, String> returnTypeUrls() {
        HashMap<String, String> map = new HashMap<String, String>();
        log.info(" enter into return url : gethRequestParam : "
                + gethRequestParam());
        HashMap cc = doSearch(proputil.getProperty("CPQuery"));
        HashMap collections = (HashMap) cc.get("Collections");
        System.out.println("Output in returntypeURL::::::::::::::::::::::::::::::::::::::::"+cc+" **********"+collections);
        
        int cpCount = new Integer(collections.get("Count").toString());
        
        log.info("Result Count : " + cpCount);

        if (cpCount > 100) {
            int offset = 100;
            double getNoOfRequest = (Math.ceil(cpCount / 100) - 1);
            for (float k = 0; k < getNoOfRequest; k++) {
                log.debug("Offset = " + offset + "NoOfRequest = "
                        + getNoOfRequest);
                String subQuery = proputil.getProperty("CPQuery") + "&offset="
                        + offset + "&limit=100";
                log.info(" subQuery :" + subQuery);
                offset = offset + 100;
            }
        }

        try {	
        	 String sourceType="";
        	 String idValue="";
        	 HashMap contentpartner=null;
        	if(collections.get("ContentPartner") instanceof HashMap){
        		 contentpartner =  (HashMap) collections.get("ContentPartner");
                 
                 log.info("Id : " + contentpartner.get("Id"));
                 sourceType = contentpartner.get("SourceType").toString()
                         .toLowerCase();
                 if (!new Boolean(contentpartner.get("RssAggregated").toString())&& (sourceType.equals("crawler"))) {
                     log.info("Data : "
                             + contentpartner.get("SupplementName").toString().toUpperCase());
                     map.put(contentpartner.get("Url").toString(), contentpartner.get("Source").toString()
                             .trim());
                     
                 }
                 return map;
        	}
        	else if(collections.get("ContentPartner") instanceof List)
        	{       	
        		System.out.println("This is instance of LIST");
        		List contentPartnerList=(List) collections.get("ContentPartner");        	
                for (int j = 0; j < contentPartnerList.size(); j++) {            
                contentpartner =  (HashMap) contentPartnerList.get(j);
              System.out.println("Contetnt parter result"+contentpartner);
                        
                log.info("Id : " + contentpartner.get("Id"));
                //sourceType = contentpartner.get("SourceType").toString()
                      //  .toLowerCase();
                sourceType="crawler";
                if (!new Boolean(contentpartner.get("RssAggregated").toString())&& (sourceType.equals("crawler"))) {
                    log.info("Data : "
                            + contentpartner.get("SupplementName").toString().toUpperCase());
                   // map.put(contentpartner.get("Url").toString(), contentpartner.get("Source").toString()
                           // .trim());
                    map.put(contentpartner.get("Url").toString(), "ddNews");
                    
                
                }
                return map;
                }
            }
        } catch (Exception ex) {
            log.info("Return Type Url Exception" + ex); // generic not to stop
                                                        // the process..
        }
        return map;
    }

    private boolean writeMD5(final String sourceName, final String stringInput) {
        BufferedWriter bufWrite = null;
        try {
            createDirectory(proputil.getProperty("MD5DIR") + sourceName + "/");
            bufWrite = new BufferedWriter(new FileWriter(
                    proputil.getProperty("MD5DIR") + sourceName + "/"
                            + stringInput));
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            try {
                bufWrite.close();
            } catch (IOException e) {
            }
        }
    }

    private boolean checkMd5(final String sourceName, final String stringMd5) {
        try {
            File file = new File(proputil.getProperty("MD5DIR") + sourceName
                    + "/" + stringMd5);
            return file.isFile();
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean createDirectory(final String directoryName) {
        try {
            File dirname = new File(directoryName);
            if (dirname.exists()) {
                return true;
            } else {
                log.info("directoryName : " + directoryName);
                return dirname.mkdirs();
            }
        } catch (Exception ex) {
            return false;
        }
    }

    private String returnTitle(String fullContent) {
        return StringUtils.substringBetween(fullContent.toLowerCase(),
                "<title>", "</title>");
    }

    private String returnStripContent(String fullContent) {
        // Remove style tags & inclusive content
        Pattern style = Pattern.compile("<(?i)style.*?>.*?</style>");
        Matcher mstyle = style.matcher(fullContent);
        while (mstyle.find())
            fullContent = mstyle.replaceAll("");

        // Remove script tags & inclusive content
        Pattern script = Pattern.compile("<(?i)script.*?>.*?</script>");
        Matcher mscript = script.matcher(fullContent);
        while (mscript.find())
            fullContent = mscript.replaceAll("");

        // Remove primary HTML tags
        Pattern tag = Pattern.compile("<.*?>");
        Matcher mtag = tag.matcher(fullContent);
        while (mtag.find())
            fullContent = mtag.replaceAll("");

        // Remove comment tags & inclusive content
        Pattern comment = Pattern.compile("<!--.*?-->");
        Matcher mcomment = comment.matcher(fullContent);
        while (mcomment.find())
            fullContent = mcomment.replaceAll("");

        // Remove special characters, such as &nbsp;
        Pattern sChar = Pattern.compile("&.*?;");
        Matcher msChar = sChar.matcher(fullContent);
        while (msChar.find())
            fullContent = msChar.replaceAll("");

        // Remove the tab characters. Replace with new line characters.
        Pattern nLineChar = Pattern.compile("\t+");
        Matcher mnLine = nLineChar.matcher(fullContent);
        while (mnLine.find())
            fullContent = mnLine.replaceAll("\n");

        if (fullContent.length() > 3999)
            return fullContent.substring(0, 3999);
        else
            return fullContent;
    }

    private String getMD5(final String fullContent)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final Integer ffffCons = 0xffffff00;
        final Integer ffCons = 0x000000ff;
        final int subString = 6;
        String uriInfo = fullContent;
        String uriInfoResult = "";
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(uriInfo.getBytes("UTF8"));
        byte[] s = m.digest();
        for (int i = 0; i < s.length; i++) {
            uriInfoResult += Integer.toHexString((ffCons & s[i]) | ffffCons)
                    .substring(subString);
        }
        return uriInfoResult;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param hRequestParam
     *            the hRequestParam to set
     */
    public void sethRequestParam(Map<String, String> hRequestParam) {
        this.hRequestParam = hRequestParam;
    }

    /**
     * @return the hRequestParam
     */
    public Map<String, String> gethRequestParam() {
        return hRequestParam;
    }
}
