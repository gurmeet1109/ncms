package in.nic.cmf.sitebuilder;

import in.nic.cmf.cache.RedisCache;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;

public class SiteBuilder {
    private PropertiesUtil                      proputil;
    private LogTracer                           log;
    private static HashMap<String, SiteBuilder> hashsb = new HashMap<String, SiteBuilder>();
    private String                              domain;
    private static SiteBuilderProperties        bpProperties;
    private SiteBuilder                         sb;
    private SiteBuilderHelper                   sbHelper;
    private RedisCache                          redisCache;

    private SiteBuilder(String domain) {
        setLogger(domain);
        proputil = PropertiesUtil.getInstanceof(domain, "sitebuilder");
        bpProperties = SiteBuilderProperties.getInstanceof(domain);
        sbHelper = SiteBuilderHelper.getInstanceof(domain);
        redisCache = RedisCache.getInstanceof(domain);

    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "sitebuilder");
    }

    public static SiteBuilder getInstanceof(String domain) {
        if (!hashsb.containsKey(domain)) {
            hashsb.put(domain, new SiteBuilder(domain));
        }
        return hashsb.get(domain);
    }

    public int parsePMSentities(Map<String, Object> incomingEntity,
            String domain, boolean isPublished) throws GenericException {
    	log.debug("Enter into parsePMSentities..");
        log.debug("Incoming Parameters are " + incomingEntity);
        log.debug("Domain::"+domain   +     "isPublished:::" +  isPublished);

        String entitytype = (String) incomingEntity.get("EntityType");
        String entityName = (String) incomingEntity.get(entitytype + "Name");
        String contentFilePath = (String) incomingEntity.get("FilePath");
        String folderpath = (String) incomingEntity.get("FolderPath");
        String id = (String) incomingEntity.get("Id");
        String movedstate = (String) incomingEntity.get("Status");
        
        log.debug("Entitytype        :" + entitytype);
        log.debug("Filepath          :" + contentFilePath);
        log.debug("Id                :" + id);
        log.debug("Folder path       :" + folderpath);
        log.debug("EntityName        :" + entityName);
       

        if (null == folderpath || folderpath.isEmpty()) {

            folderpath = entitytype.toLowerCase().equals("pmsmedia") ? "media"
                    : entitytype.toLowerCase();
            log.debug("Folder Path is null so framing folder path:"
                    + folderpath);

        }

      
  if (null == contentFilePath || contentFilePath.isEmpty()) {
            contentFilePath = domain
                    + bpProperties.getProperty("pathseperator") + "media"
                    + bpProperties.getProperty("pathseperator") + id + "."
                    + entityName.toLowerCase();
        }

        if (bpProperties.getArchivedStatus().contains(movedstate.toLowerCase())) {
            log.debug("Enter in PMS dalete");
            sbHelper.fileDelete(bpProperties.getProperty("abspath") + domain
                    + "/" + folderpath + "/" + entityName);
            log.debug("FolderPath and Entityname in PMS check"
                    + (folderpath + "/" + entityName));
            log.debug("Exit of PMS delete");
            return 1;

        }
       log.debug("Get Response..");
        String responseBody = sbHelper.getResponse(id, domain, entitytype);
        if (null == responseBody) {
            return 0;
        }

        writeEntitesFile(folderpath, entityName, responseBody, isPublished,
                domain);
        return 1;
    }

    private int writeEntitesFile(String folderpath, String entityName,
            String responseBody, boolean isPublished, String domain) {
    	log.debug("Enter into writeEntitesFile..");
    	log.debug("folderpath" + folderpath + "entityname::" + entityName + "Domain::" + domain + "isPublished" + isPublished);
    	log.debug("responseBody::"+responseBody);

        if (isPublished) {
            createEntitiesFile(folderpath, entityName, responseBody, domain);
        }
        return createEntitiesFile(folderpath, entityName, responseBody,
                bpProperties.getProperty("stagingdomainname") + "." + domain);

    }

    private int createEntitiesFile(String folderpath, String entityName,
            String responseBody, String domain) {
        log.debug("Enter into createEntitiesFile");
        log.debug(" Folderpath::" + folderpath  +  "entityName::" + entityName + "Domain" +  domain);
        log.debug("responseBody:::"+responseBody);
        File file = new File(bpProperties.getProperty("abspath")
                + bpProperties.getProperty("pathseperator") + domain
                + bpProperties.getProperty("pathseperator")
                + folderpath.toLowerCase());

        if (!file.exists()) {
            file.mkdirs();
        }
        FileWriter fout = null;
        try {
            log.debug("File Path :" + file.getPath()
                    + bpProperties.getProperty("pathseperator") + entityName);
            fout = new FileWriter(file.getPath()
                    + bpProperties.getProperty("pathseperator") + entityName);

            fout.write(responseBody);

            if (null != fout) {
                fout.flush();
            }
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException:" + e.getMessage());
            log.error("Entity Name Null:---" + entityName + "----");
            return -1;
        } catch (IOException e) {
            log.error("IOException:" + e.getMessage());
            return -1;
        } finally {
            close(fout);
        }
        file = null;
        log.methodExit(this.getClass().getSimpleName()
                + ":write(String folderpath, String entityName, byte[] fileContent)");
        return 1;
    }

    private void close(FileWriter fout) throws GenericException {
        log.methodExit(this.getClass().getSimpleName() + ".close() ");
        try {
            if (fout != null) {
                fout.close();
            }

        } catch (IOException e) {

        }
        log.methodExit( ".close() ");
    }

   

    public int parsePageAssosiator(Map<String, Object> incomingEntity,
            String domain, boolean isPublished) throws GenericException {
    	log.debug("Enter into parsePageAssosiator");
    	log.debug("Incoming Parameters are::"+incomingEntity);
    	log.debug("Domain::"+domain +"isPublished::"+isPublished);

        String iaName = "";
        String pageName = "";
        String movestatus = "";

        iaName = (String) incomingEntity.get("IaName");
        pageName = (String) incomingEntity.get("PageName");
        movestatus = (String) incomingEntity.get("Status");
       log.debug("Movestatus :" + movestatus);
       log.debug("IaName:" + iaName);
       log.debug("PageName :" + pageName);
       
        iaName = sbHelper.buildIaPath(iaName);
        log.debug("Ianame :" + iaName);

           
        if (bpProperties.getArchivedStatus().contains(movestatus.toLowerCase())) {
            log.debug("Enter of PA delete");
             sbHelper.fileDelete(bpProperties.getProperty("abspath") + domain
                    + "/" + iaName + "/index.html");
         
            log.debug("Exit of PA delete");

            return 1;

        }

        if (null != iaName && !iaName.isEmpty()) {
            System.out.println("---inside if loop to enter writeIaListing---");
            log.debug("---inside if loop to enter writeIaListing---");

            writeIaListing(iaName, pageName, isPublished, domain,incomingEntity);
        }
        log.methodExit(this.getClass().getSimpleName()
                + ":Exit");
        return 1;
    }
    

    private int writeIaListing(String iName, String pagename,
            boolean isPublished, String domain,Map<String, Object> incomingEntity) {
    	log.debug("Enter into writeIaListing");
    	log.debug("Incoming Parameters are ::"+incomingEntity);
    	log.debug("Ianame::"+iName + "isPublished::"+isPublished +"Domain::"+domain+"Page Name :" + pagename);

     
        String pagecontent_url = "";
        try {
            pagename = URLEncoder.encode(pagename, "UTF-8");
            pagecontent_url = proputil.getProperty("pageurl") + pagename;
            log.info("pagecontent_url:" + pagecontent_url);

        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException for given page name:"
                    + pagename);
            return -1;
        }

        String pmsEntity = sbHelper.getResponse(pagecontent_url, domain,incomingEntity);
       

        if (null == pmsEntity) {
            log.debug("No Page information avail for given pagename:"
                    + pagename);
            return -1;
        }

        writeSEOOrIAFile("index.html", iName, pmsEntity, isPublished, domain);

        return 1;
    }


    private int writeSEOOrIAFile(String seourl, String ianamePath,
            String content, boolean isPublished, String domain) {
    	log.debug("Enter into writeSEOOrIAFile..");
    	log.debug("seourl::"+seourl+"ianamePath::"+ianamePath+"content"+content+"isPublished::"+isPublished+"Domain::"+domain);

        if (isPublished) {
            createSEOOrIAFile(seourl, ianamePath, content, domain);
        }
        return createSEOOrIAFile(seourl, ianamePath, content,
                bpProperties.getProperty("stagingdomainname") + "." + domain);

    }

    private int createSEOOrIAFile(String seourl, String ianamePath,
            String content, String domain) {
    	
    	log.debug("Enter into writeSEOOrIAFile..");
    	log.debug("seourl::"+seourl+"ianamePath::"+ianamePath+"content"+content+"Domain::"+domain);
    	File fileLocation = null;
        FileWriter fw = null;

        ianamePath = sbHelper.buildIaPath(ianamePath);

        try {
            fileLocation = new File(bpProperties.getProperty("abspath")
                    + bpProperties.getProperty("pathseperator") + domain
                    + bpProperties.getProperty("pathseperator")
                    + ianamePath.trim().toLowerCase());
            if (!fileLocation.exists()) {
                fileLocation.mkdirs();
            }
            log.debug("FilePath:" + fileLocation.getPath());

            fw = new FileWriter(fileLocation.getPath()
                    + bpProperties.getProperty("pathseperator") + seourl);

            content = content.replace(bpProperties.getProperty("startscript"),
                    "<script");
            content = content.replace(bpProperties.getProperty("endscript"),
                    "</script>");
            content = content.replace(bpProperties.getProperty("javascript"),
                    "javascript:");
            content = content.replace("&gt;", ">");
            content = content.replace("&lt;", "<");
            BufferedWriter out = new BufferedWriter(fw);
            out.write(content);
            out.close();
        } catch (IOException e) {
            log.error("Error in creating seourl File");
            return -1;

        } finally {
            fileLocation = null;
            try {
                if (null != fw) {
                    fw.close();
                }
            } catch (IOException e) {
                log.error("Unable to close file object");
                return -1;
            }
        }
        return 1;
    }

    public int parseCMSEntities(Map<String, Object> incomingEntity,
            String domain, String entitytype, boolean isPublished)
            throws Exception {
    	log.debug("Entered into parseCMSEntities");
    	log.debug("Incomind Parameters are :: "+incomingEntity);
    	log.debug("Domain:::"+domain+"entitytype:::"+entitytype+"isPublished:::"+isPublished);
        String entityType = ((String) incomingEntity.get("EntityType")).trim();
        log.debug("entityType" + entityType);
        String serviceurl = "";
        serviceurl = proputil.getProperty("pageassociatorurl") + entityType;
        log.info("Url :::" + serviceurl);
        String pagename = sbHelper.getResponseforpageassociator(serviceurl, domain, incomingEntity);
        log.debug("pagename:::" + pagename);

        if (null == pagename) {
            log.debug("No pageassociator avail for given pageentityname:"
                    + entitytype);
            return -1;
        }

      try {
            pagename = URLEncoder.encode(pagename, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            log.error("Unable to encode the given page name:" + pagename);
            return -1;

        }

        serviceurl = proputil.getProperty("pageurl") + pagename;
        log.info("PageUrl:" + serviceurl);

        pagename = sbHelper.getResponseforPage(serviceurl, domain,pagename);

        if (null == pagename) {
            log.debug("No page avail for given pagename:" + pagename);
            return -1;
        }

        StringWriter writer = new StringWriter();
        VelocityEngine ve = new VelocityEngine();

        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        ve.setProperty("file.resource.loader.class",
                " org.apache.velocity.runtime.resource.loader.FileResourceLoader");

        String dirpath = proputil.get("location") + domain + "/resources/";

        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, dirpath);

        ve.setProperty("runtime.log.logsystem.class",
                "org.apache.velocity.runtime.log.NullLogSystem");
        ve.init();

        Template t = ve.getTemplate(proputil.get("vmfilename"));

        VelocityContext context = new VelocityContext();

        try {
        
            FileWriter fout = new FileWriter(dirpath
                    + proputil.get("vmfilename"));
                    fout.write(pagename);
            fout.flush();
              if (incomingEntity.containsKey("Description")) {
                context.put("description",
                        (String) incomingEntity.get("Description"));
            }
            if (incomingEntity.containsKey("ShortDescription")) {
                context.put("description",
                        (String) incomingEntity.get("ShortDescription"));
            }
            if (incomingEntity.containsKey("Id")) {
                context.put("id", (String) incomingEntity.get("Id"));
            }
            if (incomingEntity.containsKey("Title")) {
                context.put("title", (String) incomingEntity.get("Title"));
            }
            String movedstate = "";
            String seourl = (String) incomingEntity.get("SeoUrl");
            String ianamePath = (String) incomingEntity.get("AssociatedIAPath");
            String id = (String) incomingEntity.get("Id");
            ianamePath = sbHelper.buildIaPath(ianamePath);
            log.debug("seourl" + seourl);
            log.debug("AssociatedIAPath" + ianamePath);
            log.debug("id" + id);
            log.debug("ianamePath" + ianamePath);
            t.merge(context, writer);

            if (null == seourl && seourl.isEmpty()) {
                log.debug("SeoUrl is Null for this Article");
                return 0;
            }

            if (seourl.isEmpty()) {
                log.debug("SeoUrl is empty");
            }
            
            int idx = seourl.indexOf("://");
            String documentRoot = bpProperties.getProperty("abspath");
            log.debug("documentRoot" + documentRoot); 
                                                    
            String fileName = seourl.substring(idx + 3, seourl.length());
            log.debug("fileName" + fileName);

           fileName = fileName.substring(fileName.lastIndexOf("/") + 1,
                    fileName.length());

            String redisKey = id + "-iapath";
            log.debug("redisKey Path:" + redisKey);
            String redisVal = redisCache.get(redisKey);
            log.debug("redis value:" + redisVal);
            int status = 0;
            if (redisVal == null
                    && !bpProperties.getArchivedStatus().contains(
                            movedstate.toLowerCase())) {
                log.debug("Doc is not Archived status");
            
                return writeSEOOrIAFile(fileName, ianamePath,
                        writer.toString(), isPublished, domain);

            }

            if (redisVal != null
                    && !redisVal.equalsIgnoreCase(ianamePath)
                    && !bpProperties.getArchivedStatus().contains(
                            movedstate.toLowerCase())) {
                log.debug("existing doc not archived status");
                String absPathFileName = documentRoot
                        + "/"
                        + domain
                        + "/"
                        + redisVal
                        + "/"
                        + seourl.substring(seourl.lastIndexOf("/"),
                                seourl.length());

                String stageAbsPathFileName = documentRoot
                        + "/"
                        + bpProperties.getProperty("stagingdomainname")
                        + "."
                        + domain
                        + "/"
                        + redisVal
                        + "/"
                        + seourl.substring(seourl.lastIndexOf("/"),
                                seourl.length());
                log.debug("staging file url::" + stageAbsPathFileName);
                log.debug("domain file url::" + absPathFileName);
                sbHelper.fileDelete(absPathFileName);
                sbHelper.fileDelete(stageAbsPathFileName);
                redisCache.set(redisKey, ianamePath);

                return writeSEOOrIAFile(fileName, ianamePath,
                        writer.toString(), isPublished, domain);


            }
            if (bpProperties.getArchivedStatus().contains(
                    movedstate.toLowerCase())
                    && redisVal != null) {
                log.debug("archived content so deleting file for site and redis");
                String absPathFileName = documentRoot
                        + "/"
                        + domain
                        + "/"
                        + redisVal
                        + "/"
                        + seourl.substring(seourl.lastIndexOf("/"),
                                seourl.length());
                log.debug("absPathFileName::" + absPathFileName);
                sbHelper.fileDelete(absPathFileName);
                redisCache.delete(redisKey);

            } else {

                log.debug("not archived and expired status, redis value is not null");
                return writeSEOOrIAFile(fileName, ianamePath,
                        writer.toString(), isPublished, domain);
            }

            return status;

        } catch (ResourceNotFoundException e) {
            log.error("ResourceNotFoundException:unable to locate vm file"
                    + e.getMessage());
        } catch (ParseErrorException e) {
            log.error("ParseErrorException:unable to parse vm file"
                    + e.getMessage());
        } catch (IOException e) {
            log.error("IOException:unable to write page content into vm file"
                    + e.getMessage());
        } catch (ClassCastException e) {
            log.error("ClassCastException occured while fetching details from seoble Entity");
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            log.error("Exception" + e.getMessage());

        }

        return 1;
    }

    public boolean getDeleteFile(Map<String, Object> contentmap, boolean isCms,
            String domain, String entityname) throws GenericException {
      log.debug("Enter into getDeleteFile..");
        boolean flag = false;
        
        log.debug("contentmap::" + contentmap);
        log.debug("isCms" + isCms + "domain" + domain + "entityname" + entityname);
      
        String entity = (String) contentmap.get("EntityType");
        String folderpath = (String) contentmap.get("FolderPath");
        
        log.debug("EntityType ::" + entity);
        log.debug("FolderPath :: " + folderpath);
        
        String fileDetails[] = fetchFileDetails(contentmap, isCms);

        entity = fileDetails[0];
        folderpath = fileDetails[1];
        
        log.debug("EntityName::" + entity);
        log.debug("Dir:::" + folderpath);
        
        String del = bpProperties.getProperty("abspath")
                + bpProperties.getProperty("stagingdomainname") + "." + domain +bpProperties.getProperty("pathseperator")
                +  folderpath;
        log.debug("Path" + del);
        
        File file = new File(bpProperties.getProperty("abspath")
                + bpProperties.getProperty("pathseperator") + domain
                + bpProperties.getProperty("pathseperator") + folderpath
                + bpProperties.getProperty("pathseperator") + entity);
        
        File stagingfile = new File(bpProperties.getProperty("abspath")
                + bpProperties.getProperty("pathseperator")
                + bpProperties.getProperty("stagingdomainname") + "." + domain
                + bpProperties.getProperty("pathseperator") + folderpath
                + bpProperties.getProperty("pathseperator") + entity);

        log.debug("FilePath :" + file);
        log.debug("Staging FilePath :" + stagingfile);
        flag = file.delete();
        sbHelper.deldir(del);
        log.debug("Published file Deletion flag:" + flag);
        flag = stagingfile.delete();
        sbHelper.deldir(del);

        log.debug("Staging file Deletion flag:" + flag);
        log.debug("is File Deleted :" + flag);

        file = null;
        stagingfile = null;

        return flag;
    }

    private String[] fetchFileDetails(Map<String, Object> incomingEntity,
            boolean isCms) throws GenericException {
    	
        log.debug("Enter into fetchFileDetails");
        log.debug("Incoming Parameters are " + incomingEntity);
        log.debug("IsCms::"+isCms);

        String entitytype = (String) incomingEntity.get("EntityType");
        log.debug("entitytype ::" + entitytype);

        String fileDetails[] = new String[2];

        if (isCms) {
            fileDetails[0] = (String) incomingEntity.get("SeoUrl");
            
            if (isCms && null != fileDetails[0]
                    && !fileDetails[0].isEmpty()) {

                fileDetails[0] = fileDetails[0].substring(
                        fileDetails[0].lastIndexOf('/') + 1,
                        fileDetails[0].length());

            }

            log.debug(" fileDetails[0]::" + fileDetails[0]);
            
            fileDetails[1] = (String) incomingEntity.get("AssociatedIAPath");
            
            fileDetails[1] =     sbHelper.buildIaPath(fileDetails[1]);
            log.debug(" fileDetails[1]::" + fileDetails[1]);

        } else {
            log.debug("Incoming entity type is " + entitytype);
            if (entitytype.toLowerCase().equals("pageassociator")) {
            	log.debug("Incoming type is PageAsoociator");

                fileDetails[1] = (String) incomingEntity.get("IaName");
                log.debug("fileDetails[0]" + fileDetails[0]);
            } else {
            log.debug("Incoming type is PMS");

                fileDetails[1] = (String) incomingEntity.get("FolderPath");
                fileDetails[0] = (String) incomingEntity.get(entitytype
                        + "Name");
                log.debug("Entitytype+Name :::"   + incomingEntity.get(entitytype + "Name"));

            }

        }

        if ((null == fileDetails[1] || fileDetails[1].isEmpty())
                && !entitytype.toLowerCase().equals("pageassociator")
                && !bpProperties.getCmsTypes().contains(
                        entitytype.toLowerCase())) {

            fileDetails[1] = entitytype.toLowerCase().equals("pmsmedia") ? "media"
                    : entitytype.toLowerCase();
            log.debug("Folder Path is null so framing folder path:"
                    + fileDetails[1]);
        }

        if (entitytype.toLowerCase().equals("pageassociator")) {
            fileDetails[0] = "index.html";
        }
        
        fileDetails[1] = fileDetails[1].toLowerCase();
        log.info("File Name\t fileDetails[0] \t" + fileDetails[0]);
        log.info("Folder Path\t  fileDetails[1] \t" + fileDetails[1]);

        return fileDetails;
    }

}
