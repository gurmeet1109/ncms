package in.nic.cmf.sem;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.sem.interfaces.IMarketingEngine;
import in.nic.cmf.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

public class SemService {
    private PropertiesUtil                     prop;
    private IMarketingEngine                   marketingengine;
    public LogTracer                           log;
    private static HashMap<String, SemService> hashSemService = new HashMap<String, SemService>();
    private String                             domain;

    private SemService(String domain) {
        this.domain = domain;

        setLogger(domain);
        System.out.println("4444444444");
        marketingengine = TwitterEngine.getInstanceof(domain);
        prop = PropertiesUtil.getInstanceof(domain, "sem");

    }

    public static SemService getInstanceof(String domain) {
        System.out.println("111111111111111111");
        if (!hashSemService.containsKey(domain)) {
            System.out.println("22222222222222");
            hashSemService.put(domain, new SemService(domain));
            System.out.println("333333333333");
        }
        return hashSemService.get(domain);
    }

    public void setLogger(String domain) {
        log = new LogTracer(domain, "sem");
    }

    public Map<String, Map<String, Object>> post(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {

        Map<String, Object> input = parameters.get("input");
        log.debug("input:::" + input);

        FormatXml formatxml = FormatXml.getInstanceof(domain);

        Map<String, Object> collections = (Map<String, Object>) formatxml.in(
                input.get("content")).get("Collections");
        log.debug("collections:::" + collections);
        Iterator<Entry<String, Object>> entitiesItr = collections.entrySet()
                .iterator();
        while (entitiesItr.hasNext()) {
            Map.Entry<String, Object> entityEntry = (Map.Entry<String, Object>) entitiesItr
                    .next();
            if (entityEntry.getValue() instanceof Map) {
                Map<String, Object> entityData = (Map<String, Object>) entityEntry
                        .getValue();

                log.debug("entityData" + entityData);
             boolean str = (Boolean) Semmable(domain, null, entityData);

                log.debug("FINAL" + str);
               

            } else if (entityEntry.getValue() instanceof List) {
                List<Map> li = (List<Map>) entityEntry.getValue();
                for (Map hmap : li) {
                    String des = (String) hmap.get("Description");

                    boolean str = (Boolean) Semmable(domain, li, hmap);
                    log.debug("FINAL" + str);

                }
            }
        }

        return buildResponse(domain, parameters);
    }

    private Object Semmable(String domain, List<Map> li,
            Map<String, Object> userContext) throws GenericException {
        String desc = (String) userContext.get("Description");
        String status = (String) userContext.get("Status");
        String entype = (String) userContext.get("EntityType");
        log.debug("desc" + desc);
        log.debug("status" + status);
        log.debug("entype" + entype);
   
       log.debug("isSemmable:::::::::::"+isSemmable(domain, entype));
        if (isSemmable(domain, entype) ) {
        	if(isPublished(userContext)){

            if (semValidation(domain, userContext)) {
                log.debug("Successfull");
                return marketingengine.processSemable(domain, userContext);
            } else {
                return false;
            }
        } 
        return false;	
        }else {
        	log.debug("This content is not semmable and not published");
            return false;
        }
		
    }
    
  

    private boolean semValidation(String domain, Map<String, Object> userContext) {
        String descr = (String) userContext.get("Description");
        String id = (String) userContext.get("Id");
        String surl = (String) userContext.get("SeoUrl");
        log.debug("descr" + descr);
        log.debug("id" + id);
        log.debug("surl" + surl);

        Utils util = Utils.getInstanceof(domain);

        if (!util.isEmpty(descr) && !util.isEmpty(id)) {
            if (!util.isEmpty(surl)) {
                try {
                    new URL((String) userContext.get("SeoUrl"));
                } catch (MalformedURLException e) {

                    return false;
                }
                return true;
            }
            return false;
        } else return false;
    }

    private boolean isPublished(Map<String, Object> userContext) {
    	log.debug("Enter into isPublished");
        String status = (String) userContext.get("Status");
        if (status.equals("Published")) {
            log.debug("Status" + status);
            return true;
        }
        log.debug("not a published content");

        return false;
    }

    String entityType = "";

    private boolean isSemmable(String domain, String entype) {
        log.debug("Enter into is Semmable");

        entityType = entype;

        FormatJson formatJson = FormatJson.getInstanceof(domain);

        InputStream is = null;
        try {
            log.debug("is" + is);
            log.debug("Location::::" + prop.get("location") + domain + "/"
                    + "resources" + "/" + "Mapping.json");
            is = new FileInputStream(new File(prop.get("location") + domain
                    + "/" + "resources" + "/" + "Mapping.json"));
        } catch (FileNotFoundException e) {
            log.debug("unable to find" + domain + "Mapping.json");
            return false;
        }

        Map h = new HashMap<String, Object>();
        try {
            h = formatJson.in(IOUtils.toString(is));
        } catch (GenericException e) {
            log.debug("GenericException:::" + e.getExceptionDetails());
        } catch (IOException e) {
            log.debug("IOUtils unable to read stream ::" + e.getMessage());
        }
        log.debug("h::;" + h);

        Map mapping = (Map) h.get(entype);
        log.debug("mapping" + mapping);
        Map classInfo = (Map) mapping.get("classInfo");
        List interfaceList = (List) classInfo.get("interfaceInfo");
        if (interfaceList.contains("Semable")) {
            log.debug("Contains Semable");
            return true;
        }
        log.debug("Not a Semable content");

        return false;
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
