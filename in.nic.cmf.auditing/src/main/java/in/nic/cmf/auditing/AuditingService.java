package in.nic.cmf.auditing;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.HashMap;
import java.util.Map;

public class AuditingService {
    private PropertiesUtil                          proputil;
    private LogTracer                               log;
    private LogTracer                               auditlogger;
    private static HashMap<String, AuditingService> hashauditinghelper = new HashMap<String, AuditingService>();

    private AuditingService(String domain) {
        proputil = PropertiesUtil.getInstanceof(domain, "auditing");
        setLogger(domain);

    }

    public void setLogger(String domain) {
        log = new LogTracer(domain, "audit");
        auditlogger = new LogTracer(domain, "AuditingTraceLogger", 100,
                proputil.get("directoryPath") + domain + "/"
                        + proputil.get("servicename"));
        log.debug("DirectoryPath::::"+proputil.get("directoryPath"));
        log.debug("auditlogger::" + proputil.get("directoryPath") + domain
                + "/" + proputil.get("servicename"));
        
    }

    public static AuditingService getInstanceof(String domain) {

        if (!hashauditinghelper.containsKey(domain)) {
            hashauditinghelper.put(domain, new AuditingService(domain));
        }
        return hashauditinghelper.get(domain);
    }

    public Map<String, Map<String, Object>> logGet(String domain,
            Map<String, Map<String, Object>> parameters) {
        log.debug("Enter into logGet");
        
        log.debug("Domain::" + domain);
        log.debug("Incoming Parameters are ::"+ parameters);
        
       String queryString = (String) parameters.get(
                "input").get("queryString");
        
        
        log.debug("queryString:::" + queryString);

        String requester = "NULL";
       
  Map<String, String> userContext = (Map<String, String>) parameters.get(
                "input").get("userContext");
  
        log.debug("userContext::"+userContext);
        
        if (userContext.containsKey("requester")) {
            requester = (String) userContext.get("requester");
        }
        log.debug("Requester::" + requester);
        
        
      if(queryString!=null && !queryString.isEmpty() && !queryString.equalsIgnoreCase("null") ){
    	  log.debug("AuditController - " + requester + " - [["
                  + queryString + "]]");
    	  
    	  auditlogger.info("AuditController - " + requester + " - [["
                  + queryString + "]]");
           
        }else{
        	log.debug("AuditController  " + requester);
        	
        	   auditlogger.info("AuditController  " + requester   );
        }
        
        return buildResponse(domain, parameters);
    }

    
    public Map<String, Map<String, Object>> buildResponse(String domain,
            Map<String, Map<String, Object>> parameters) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {

            response.put("statusCode", "200");
            response.put("content", "<Collections><status>success</status></Collections>");
            parameters.put("output", response);
            log.debug("Response Map : " + response);

        }

        catch (GenericException ge) {
            System.out.println("Got an IOException: " + ge.getMessage());
        }
        return parameters;
    }

}