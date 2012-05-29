package in.nic.cmf.sitebuilder;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.security.owasp.Decode;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;


public class SiteBuilderService {
	
	  private PropertiesUtil                                      proputil;
	  public LogTracer                                            log;
      private static HashMap<String, SiteBuilderService>          hashSiteBuilderService = new HashMap<String, SiteBuilderService>();
	  private String                                              domain;
	  private SiteBuilder                                         sb;
	  private SiteBuilderHelper                                   sbHelper;
	  private   SiteBuilderProperties bpProperties ;
	    
	    private  SiteBuilderService(String domain) {
	    	this.domain=domain;		    	
	    	setLogger(domain);
	       	sbHelper = SiteBuilderHelper.getInstanceof(domain);
        	proputil = PropertiesUtil.getInstanceof(domain, "sitebuilder");
	    	sb = SiteBuilder.getInstanceof(domain);
	    	
	    }
	    
      public static SiteBuilderService getInstanceof(String domain) {
	    
	        if (!hashSiteBuilderService.containsKey(domain)) {
	            hashSiteBuilderService.put(domain, new SiteBuilderService(domain));
	        }
	        return hashSiteBuilderService.get(domain);
	    }

	    public void setLogger(String domain) {
	       log = new LogTracer(domain, "sitebuilder");
	    }
	 
	   public Map<String, Map<String, Object>> post(String domain, String entity,
			Map<String, Map<String, Object>> parameters) {
		   log.methodEntry("POST Entry...");
		   log.debug("Domain::" + domain );
		   log.debug("Entity::" +   entity);
		   log.info("Incoming Parameters are :" + parameters);
       
        
        try{
         
           Map<String, Object> content =  (Map<String, Object>) FormatXml.getInstanceof(domain).in(parameters.get("input").get("content"));
              Map<String, Object> collections = (Map<String, Object>) content.get("Collections");
              log.debug("Collections:::" + collections);
              for (Map.Entry<String, Object> entry : collections.entrySet()) {
             if (entry.getValue() instanceof List) {
                        log.debug("Inside instanceof List");
                     log.debug("key:" + entry.getKey() + "value:" + entry.getValue());
                      List<Map<String, Object>> collectionList = (List<Map<String, Object>>) entry.getValue();
                        log.debug("collectionlist:" + collectionList);
                        for(Map<String,Object> collect:collectionList){
                        processByCollections(collect, domain, entity);
               		}
                       }
                    else if(entry.getValue() instanceof HashMap){
        	    log.debug("Inside instanceof Map");
         		Map<String,Object> collectionMap=(Map<String, Object>) entry.getValue();
         		 log.debug("collectionMap:" + collectionMap);
         		processByCollections(collectionMap, domain, entity);
         	}
                     }
                
                 } catch (GenericException ge) {
         log.error("In Generic Exception"+ge.getMessage());
        } catch (Exception ex) {
            log.error("In Exception:" + ex.getMessage());
         
        }
        log.methodExit("POST Exit...");
     
        return sbHelper.buildResponse(domain, parameters);

   }

	 public int processByCollections(Map<String,Object> content, String domain,
		        String entity)   {
           log.debug("Inside processByCollections");
           log.debug("Domain::"+ domain);
           log.debug("Entity::" +   entity);
		   log.info("content:::" + content);
           
           FormatXml formatxml = FormatXml.getInstanceof(domain);
           FormatJson formatJson = FormatJson.getInstanceof(domain);
           InputStream is = null;
          HashMap<String, Object> fieldMap = new HashMap<String, Object>();
            try {
             	String path = proputil.getProperty("location")+domain+"/"+proputil.get("resource")+"/"+ "FieldNameMapping.json";
            	log.debug("Path::"+path);
	          is = new FileInputStream(path);
	          fieldMap = formatJson.in(IOUtils.toString(is));
	         //  log.debug("fieldMap:::"+fieldMap);
	           
	        } catch (IOException e) {
	            log.fatal("FieldNameMapping.json not found in "
	                    + bpProperties.getProperty("location"));
	        }
	        String newString = (String) formatxml.out((HashMap<String, Object>) content, fieldMap);
	        Decode decoder = Decode.getInstanceof(domain);
	        newString = decoder.decode(newString);
          return processCollections(content, domain);
   }

	 public int processCollections(Map<String, Object> content, String domain)
	              {
		log.debug("Inside processCollections::");
		log.debug("Domain::"+domain);
	    log.info("content:::" + content);
		
		 try{
            int status = 0;
	        String entityGroup = sbHelper.typeOfEntity((String)content.get("EntityType"));
	        log.debug("entityGroup:::::::::"+entityGroup);
	        String entityType = ((String)content.get("EntityType")).trim();
	        log.debug("entityType::::"+entityType);
            boolean isPublished = sbHelper.isPublished((String)content.get("Status"));
            log.debug("isPublished:::"+isPublished);;
            
            
            if(entityGroup.isEmpty()){
    			
 			   log.debug("Not SiteBuilder Entity:::"+entityType);
 			   return 0;
 		   }           
            
            if(!sbHelper.getAuthDetails(domain)){
            	log.debug("Not an AuthorizedUser");
            	return 0;
            }
	        if (entityGroup.equalsIgnoreCase("cms")) {
	        	
	            log.debug("EntityGroup is CMS,So Entering into parseCMSEntities.. ");
	            
	          status = sb.parseCMSEntities(content, domain, entityGroup, isPublished);

	        } else if (entityGroup.equalsIgnoreCase("pms")) {
	        	
	        	if (entityType.equalsIgnoreCase("PageAssociator")) {
		               log.debug("Entity type is pageassociator,,So Entering into parsePageAssosiator..");
		               
		                     status = sb.parsePageAssosiator(content, domain, isPublished);
	            } else {
	         
	          log.debug("EntityGroup is PMS,,So Entering into parsePMSentities..");
	                status = sb.parsePMSentities(content, domain, isPublished);
	               
	            }
	        }
		 }catch (Exception ex){
	    
	      
	    } 
	        return 1;
	 }
	 
	 

	public Map<String, Map<String, Object>> delete(String domain, String entity,
			String id, Map<String, Map<String, Object>> parameters) {
		
		log.methodEntry("Enter into Delete Method..");
		log.debug(" Domain::" + domain +  "entity::" +entity +  "Id::" + id);
		log.debug("Incoming Parameters are"+parameters);
		
	    String entitygroup = sbHelper.typeOfEntity(entity);
        log.debug("Incoming entitygroup is "+entitygroup);
        
        if(entitygroup.isEmpty()){
			
			   log.debug("Its Not a SiteBuilder Entity:::"+entity);
			   return sbHelper.buildResponse(domain, parameters);
		   }           
         
         if(!sbHelper.getAuthDetails(domain)){
         	log.debug("Not an Authorized User");
         	return sbHelper.buildResponse(domain, parameters);
         }  
        
        boolean isCMS = false;
        if (entitygroup.equalsIgnoreCase("cms")) {
            isCMS = true;
        }
       log.debug("Get Response..");
        String  collections = sbHelper.getResponse(id, domain, entity);
        
        HashMap<String, Object>  contentmap = FormatXml.getInstanceof(domain).in(collections);
        
        log.debug("Contentmap value:::::::::::::: "+contentmap.get("Collections"));
        Map<String,Object> collectionMap  =(Map<String, Object>) contentmap.get("Collections");
        for(Entry<String, Object> entityMap : collectionMap.entrySet() ){
        	if(entityMap.getValue() instanceof List){
        		List<Map<String,Object>> listEntityMap=(List<Map<String, Object>>) entityMap.getValue();
        		for(Map<String,Object> singleEntity:listEntityMap){
        			 sb.getDeleteFile(singleEntity, isCMS, domain, entity);
        		}
        		
        	}else if(entityMap.getValue() instanceof HashMap){
        		Map<String,Object> singleEntity=(Map<String, Object>) entityMap.getValue();
        		 sb.getDeleteFile(singleEntity, isCMS, domain,entity);
        	}
        	
        }
     
   
        return sbHelper.buildResponse(domain, parameters);

        
     
	}
}

