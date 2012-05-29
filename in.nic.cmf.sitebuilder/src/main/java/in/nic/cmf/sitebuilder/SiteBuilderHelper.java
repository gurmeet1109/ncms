package in.nic.cmf.sitebuilder;

import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import in.nic.cmf.util.DynamicAuthentication;
import in.nic.cmf.util.UserContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteBuilderHelper {
    private PropertiesUtil                            proputil;
    private LogTracer                                 log;
    private static HashMap<String, SiteBuilderHelper> hashsbhelper = new HashMap<String, SiteBuilderHelper>();
    private String                                    domain;
    private static SiteBuilderProperties              bpProperties;
    private SiteBuilder                               sb;
    private ServiceClientImpl                         serviceclient;

    HashMap<String, String>                           authParam    = new HashMap<String, String>();

    public SiteBuilderHelper(String domain) {
        setLogger(domain);
        proputil = PropertiesUtil.getInstanceof(domain, "sitebuilder");
        bpProperties = SiteBuilderProperties.getInstanceof(domain);

    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "sitebuilder");
    }

    public static SiteBuilderHelper getInstanceof(String domain) {
        System.out.println("Domain in sbheler getinstance" + domain);
        if (!hashsbhelper.containsKey(domain)) {
            hashsbhelper.put(domain, new SiteBuilderHelper(domain));
        }
        return hashsbhelper.get(domain);
    }

    public boolean getAuthDetails(String domain)  {
       log.debug("Enter into getAuthDetails");
        
        DynamicAuthentication dynamicAuth = new DynamicAuthentication(bpProperties.getProperty("role"),domain);
        log.info("Role :::" + bpProperties.getProperty("role"));
		log.debug("Domain:::::"+domain);
    
    	if (dynamicAuth.autoSignin()) {
    		log.debug("Enter into autoSignin");
    	    String authStatus = dynamicAuth.getAuthentication();
    	    log.debug("authStatus::"+authStatus);
    	  
    	} 
        boolean authStatus = false;
       try {
            authStatus = dynamicAuth.autoSignin();
            log.info("Auth status:::" + authStatus);
            if (authStatus) {
                setAuthParam(dynamicAuth.getAuthentication());
            }
        } catch (GenericException e) {
            log.info("Catch Auth status---------" + authStatus);
            log.error("Auth Failed" + e.getMessage());
        }
       log.debug("Exit from getAuthDetails");
        return authStatus;

    }
    
    private void setAuthParam(String authDetails)  {
        Convertor converter = FormatXml.getInstanceof(domain);
       log.debug("Auth map is"+converter.in(authDetails));
        HashMap<String, String> authMap =(HashMap<String, String>) ((HashMap)( (HashMap<String, Object>) (converter
                .in(authDetails))).get("Collections")).get("Authentication");
          authParam = authMap;
    }

    public String typeOfEntity(String entity) {
    	log.debug("Enter into typeOfEntity");
    	log.debug("entity:::"+entity);

        String type = "";
        if (bpProperties.getCmsTypes().contains(entity.toLowerCase())) {
            type = "cms";
        } else if (bpProperties.getFileType().contains(entity.toLowerCase())) {
            type = "pms";
        }
        log.info("type:" + type);
        return type;
    }

    public Map<String, String> getAuthParam() {

        log.debug("Auth details:::" + authParam);
        return authParam;
    }

    public boolean isPublished(String status) {
    	log.debug("Enter into isPublished");
    	log.debug("status:::"+status);
   return bpProperties.getWorkFlowStates().contains(status.toLowerCase());

    }

    public String buildIaPath(String iapath) {
    	log.debug("Enter into buildIaPath");

        String eliminateWord1 = "home";
        String eliminateWord2 = "/home";

        if (iapath.toLowerCase().startsWith(eliminateWord1)) {

            iapath = iapath.substring(eliminateWord1.length(), iapath
                    .toLowerCase().length());

        } else if (iapath.toLowerCase().startsWith(eliminateWord2)) {
            iapath = iapath.substring(eliminateWord2.length(), iapath
                    .toLowerCase().length());
        }
          iapath = iapath.replaceAll("\\s+",
                bpProperties.getProperty("ianameseperator")).trim();
        log.debug("IaPath-----" + iapath);

        return iapath;

    }

    public String getResponse(String id, String domain, String entitytype)
            throws GenericException {
    	log.debug("Enter into getResponse..");
    	log.debug("Domain::"+domain);
    	log.debug("entitytype::"+entitytype);
    	
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> input = new HashMap<String, Object>();
        HashMap<String, String> userContext = new HashMap<String, String>();
        userContext.put("aclrole",authParam.get("AclRole"));
        userContext.put("api_key",authParam.get("ApiKey"));
        userContext.put("authusername",authParam.get("AuthUserName"));
        input.put("userContext", userContext);
        parameters.put("input", input);
        log.info("Enter into Serviceclient:::::");
        ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain,
                "appflow");
            Map<String, Map<String, Object>> responseMap = serviceclient.findById(
                domain, entitytype, id, parameters);
        log.debug("responseMap:::" + responseMap);
        log.info("Exit from Serviceclient:::::");

        String responsedata = "";

        if (responseMap.get("output").get("content") instanceof String) {
            responsedata = (String) responseMap.get("output").get("content");

        }

        else if (responseMap.get("output").get("content") instanceof byte[]) {
            byte[] responsedata1 = (byte[]) responseMap.get("output").get(
                    "content");
            
            responsedata = new String(responsedata1);
            
         }
      log.debug("responsedata:::"+responsedata);
        return responsedata;
    }

    public String getResponse(String url, String domain, Map<String, Object> incomingEntity)
            throws GenericException {
    	log.debug("Enter into getResponse");
    	log.debug("Incoming Parameters are "+incomingEntity);
        log.debug("Domain::"+domain + "Url::"+url);
     

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> input = new HashMap<String, Object>();
        HashMap<String, String> userContext = new HashMap<String, String>();
        Map<String,Object> hm = new HashMap<String, Object>();
        String  pagename= (String) incomingEntity.get("PageName");
        log.debug("Pagename"+pagename);
        userContext.put("aclrole",authParam.get("AclRole"));
        userContext.put("api_key",authParam.get("ApiKey"));
        userContext.put("authusername",authParam.get("AuthUserName"));
        hm.put("Pagename", pagename);
        hm.put("EntityType", "Page");
        input.put("userContext", userContext);
        input.put("queryString", url);
        input.put("queryParams", hm);
        parameters.put("input", input);
        
        log.debug("Enter into ServiceClient");
        ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain,
                "appflow");
     
        Map<String, Map<String, Object>> responseMap = serviceclient.find(
                domain, "Page", parameters);
        log.debug("Exit from ServiceClient:::");
        log.debug("responseMap:::" + responseMap);
        HashMap<String, Object> content = FormatXml.getInstanceof(domain).in(
                responseMap.get("output").get("content"));
        log.debug("Content:::::::::::::::::" + content);
        HashMap<String, Object> PageMap = (HashMap<String, Object>) content
                .get("Collections");
        log.debug("PageMap" + PageMap);
        String contentval = (String) ((HashMap<String, Object>) PageMap
                .get("Page")).get("Content");
        
  
        log.debug("contentval" + contentval);


        return contentval;

    }
    public String getResponseforPage(String url, String domain, String pagename )
            throws GenericException {
    	
    	log.debug("Enter into getResponseforPage..");
        log.debug("Domain:"+domain+ "Url::"+url+ "Pagename::"+pagename);
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> input = new HashMap<String, Object>();
        HashMap<String, String> userContext = new HashMap<String, String>();
        Map<String,Object> hm = new HashMap<String, Object>();
        userContext.put("aclrole",authParam.get("AclRole"));
        userContext.put("api_key",authParam.get("ApiKey"));
        userContext.put("authusername",authParam.get("AuthUserName"));
        hm.put("Pagename", pagename);
        hm.put("EntityType", "Page");
        input.put("queryString", url);
        input.put("queryParams", hm);
        input.put("userContext", userContext);
        parameters.put("input", input);
        
        log.debug("Enter into ServiceClient");
        ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain, "appflow");
        Map<String, Map<String, Object>> responseMap = serviceclient.find(
                domain, "Page", parameters);
        log.debug("responseMap:::" + responseMap);
        log.debug("Exit from ServiceClient");
        HashMap<String, Object> content = FormatXml.getInstanceof(domain).in(
                responseMap.get("output").get("content"));
        log.debug("Content:::::::::::::::::" + content);
        HashMap<String, Object> PageMap = (HashMap<String, Object>) content
                .get("Collections");
        log.debug("PageMap" + PageMap);
        String contentval = (String) ((HashMap<String, Object>) PageMap
                .get("Page")).get("Content");
        log.debug("contentval" + contentval);
        return contentval;

    }
    
    

    public String getResponseforpageassociator(String url, String domain, Map<String, Object> incomingEntity)
            throws GenericException {
    	log.debug("Enter into getResponseforpageassociator..");
    	log.debug("Incoming Parameters are:::"+incomingEntity);
    	log.debug("Domain::"+domain+"Url:::"+url);
    	
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> input = new HashMap<String, Object>();
        HashMap<String, String> userContext = new HashMap<String, String>();
        Map<String,Object> hmap = new HashMap<String, Object>();
        String entitytype = (String) incomingEntity.get("EntityType");
        log.debug("entitytype::::"+entitytype);
        userContext.put("aclrole",authParam.get("AclRole"));
        userContext.put("api_key",authParam.get("ApiKey"));
        userContext.put("authusername",authParam.get("AuthUserName"));
        hmap.put("EntityType", "PageAssociator");
        hmap.put("PageEntityName", entitytype);
        input.put("userContext", userContext);
        input.put("queryParams", hmap);
        input.put("queryString", url);
        parameters.put("input", input);
        
        log.debug("Enter into ServiceClient");
        ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain, "appflow");
        Map<String, Map<String, Object>> responseMap = serviceclient.find(
                domain, "PageAssociator", parameters);
        log.debug("responseMap:::" + responseMap);
        log.debug("Exit from ServiceClient");
        HashMap<String, Object> content = FormatXml.getInstanceof(domain).in(
                responseMap.get("output").get("content"));
        log.debug("Content:::::" + content);
        HashMap<String, Object> PageMap = (HashMap<String, Object>) content
                .get("Collections");
        log.debug("PageMap" + PageMap);
        String contentval = (String) ((HashMap<String, Object>) PageMap
                .get("PageAssociator")).get("PageName");
        log.debug("contentval" + contentval);

           return contentval;

    }

    public void fileDelete(String fileName) {
        log.info("Inside Deletion for fileDelete");
        File file = new File(fileName);
        log.info("file path check " + file);
          if (file.exists()) file.delete();
        String path = fileName.substring(0, fileName.lastIndexOf("/"));
        log.debug("Path ::::" + path);
        deldir(path);
    }

    public String deldir(String dirName) {
    	log.debug("Enter into deldir");
    	log.debug("dirName"+dirName);
    	
        List<String> excludePaths = new ArrayList<String>();
        String sitePath = bpProperties.getProperty("abspath");
        log.debug("Sitepath :::::" + sitePath);

        if (!isEmpty(bpProperties.getProperty("abspath"))) {
            excludePaths = Arrays.asList(bpProperties.getProperty("abspath")
                    .split(","));
            log.debug("excludePaths" + excludePaths);
        }
        
        
        if (dirName.lastIndexOf("/") == -1) dirName += "/";
        
        while (dirName.lastIndexOf("/") >= 0) {
        	log.debug("dirName.lastIndexOf");
            if (excludePaths.contains(dirName)) {
               log.debug("DirectoryName!::" + dirName);
                    break;
            }

            if (dirName.equalsIgnoreCase(sitePath)
                    || dirName.equalsIgnoreCase(sitePath.substring(0,
                            sitePath.lastIndexOf("/"))))

            {
                
                log.debug("DirectoryName!!::" + dirName);
                break;
            }
            
            dirName = dirName.substring(0, dirName.lastIndexOf("/"));
            log.debug("DirectoryName!!!:::" + dirName);
            File recTestFile = new File(dirName);
            recursiveTraversal(recTestFile);

        }
        return dirName;
    }

    public void recursiveTraversal(File fileObject) {
    	log.debug("Enter into recursiveTraversal..");
        if (fileObject.isDirectory()) {
            File allFiles[] = fileObject.listFiles();
            if (null != allFiles && allFiles.length == 0) {
                log.debug("File Length::::" + allFiles.length);
                log.debug("File Name::" + fileObject.getAbsoluteFile());
                fileObject.delete();
                log.debug("Successfully Deleted");
                return;
            }
            for (File aFile : allFiles) {
                recursiveTraversal(aFile);
            }

        }
    }

    private boolean isEmpty(String value) {
        if (value == null) {
            return true;
        }
        return value.isEmpty();
    }

    public Map<String, Map<String, Object>> buildResponse(String domain,
            Map<String, Map<String, Object>> parameters) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {

            response.put("statusCode", "200");
            response.put("content", "<Collections><Success>true</Success></Collections>");
            parameters.put("output", response);
            log.debug("Response Map : " + response);

        }

        catch (GenericException ge) {
           log.debug("Got an IOException: " + ge.getMessage());
        }
        return parameters;
    }
    

    

}
