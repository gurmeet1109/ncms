package in.nic.cmf.util;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthAspectHelper {

    private LogTracer                                log;
    private PropertiesUtil                           proputil             = null;
    private Utils                                    utils                = null;

    private static HashMap<String, AuthAspectHelper> hashAuthAspectHelper = new HashMap<String, AuthAspectHelper>();
    public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	private String domain;

    public AuthAspectHelper(String domain) {
    	System.out.println("AuthAspectHelper entry:"+domain);
        setDomain(domain);
        setLogger(domain);
        List<String> propList = new ArrayList<String>();
        propList.add("cookie");
        proputil = PropertiesUtil.getInstanceof(domain, "util", propList);
        utils = Utils.getInstanceof(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                false);
    }

    public static AuthAspectHelper getInstanceof(String domain) {
    	System.out.println("inside getInstanceOf entry");
        if (!hashAuthAspectHelper.containsKey(domain)) {
            hashAuthAspectHelper.put(domain, new AuthAspectHelper(domain));
        }
        System.out.println("inside getInstanceOf exit: "+domain);
        return hashAuthAspectHelper.get(domain);
    }

    public boolean doValidate(HttpServletRequest request,
            HttpServletResponse response, String domain)
            throws GenericException {
     	
    	System.out.println("doValidate entry.");
        String requestMethod = request.getMethod().toLowerCase().trim();
        System.out.println("~~~Requested method ==> " + requestMethod);
        /**** get Access for all End Users for generating widget ****/
        int clt = 60 * 60;
        if (!utils.isEmpty(getProperty("CookieExpiry"))) {
            clt = Integer.parseInt(getProperty("CookieExpiry"));
        }
        HashMap<String, String> userContext = UserContext.getInstanceof(domain)
                .getUserContext(request);
        System.out.println("usercontext is :"+userContext);
        String authusername = userContext.get("authusername");
        String api_key = userContext.get("api_key");
        String aclrole = userContext.get("aclrole");
        String remoteIP = userContext.get("requester");
        System.out.println("usercontext is :"+api_key+aclrole);
        if ((utils.isEmpty(authusername)) || (authusername.equals("anonymous"))) {
            log.debug("~~~Auth authusername is null same is converted as anonymous");
            response.addCookie(formatCookie("authusername", "anonymous", clt));
            response.addCookie(formatCookie("aclrole", "anonymous", clt));
            response.addCookie(formatCookie("api_key", null, 0));
            System.out.println("1");
            return true;
        }

        System.out.println("~~~Auth Aspect RECEIVED :: authusername ==> " + authusername
                + " :: api_key ==> " + api_key + " :: remoteIP ==> " + remoteIP
                + " :: aclrole ==>" + aclrole + " :: domain ==>" + domain);
        boolean valueincookie = false;
        if (!utils.isEmpty(authusername) && !utils.isEmpty(api_key)
                && !utils.isEmpty(aclrole)) {
        	 System.out.println("2");
            valueincookie = true;
        }
        boolean authorized = false;
        try {
            authorized = validateAPIKey(authusername, remoteIP, aclrole,
                    api_key, domain);
        } catch (GenericException e) {
            log.error("~~~Auth Aspect validate APIKey : " + e.getMessage());
            throw e;
        }
        log.debug("~~~AUTH validate Status ==> " + authorized
                + " :: authusername ==> " + authusername + " :: api_key ==> "
                + api_key + " :: remoteIP ==> " + remoteIP + " :: aclrole ==>"
                + aclrole + " :: domain ==>" + domain);
        
        
        System.out.println("~~~AUTH validate Status ==> " + authorized
                + " :: authusername ==> " + authusername + " :: api_key ==> "
                + api_key + " :: remoteIP ==> " + remoteIP + " :: aclrole ==>"
                + aclrole + " :: domain ==>" + domain);
        
        
        System.out.println("3");
        if (!authorized) {
        	 System.out.println("4");
            response.addCookie(formatCookie("authusername", null, 0));
            response.addCookie(formatCookie("api_key", null, 0));
            response.addCookie(formatCookie("aclrole", null, 0));
            if (requestMethod.equals("post")) {
                log.error("~~~Authentication failure");
                throw new GenericException(domain, "ERR-UTL-0001");
            }
            log.methodExit(this.getClass().getSimpleName() + ".doValidate()");
            throw new GenericException(domain, "ERR-UTL-0007");
        }
        if ((valueincookie == false) && (authorized == true)) {
        	 System.out.println("5");
            response.addCookie(formatCookie("authusername", authusername, clt));
            response.addCookie(formatCookie("api_key", api_key, clt));
            response.addCookie(formatCookie("aclrole", aclrole, clt));
            log.debug("~~~AUTH info written in cookie :: auth status ==> "
                    + authorized);
        }
        log.methodExit(this.getClass().getSimpleName() + ".doValidate()");
        System.out.println("6");
        return authorized;
    }

    private Cookie formatCookie(String key, String value, int clt)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".formatCookie()");
        try {
            Cookie cookie = new Cookie(key, value);
            cookie.setMaxAge(clt);
            if (!utils.isEmpty(getProperty("CookiePath"))) {
                cookie.setPath(getProperty("CookiePath"));
            }
            if (!utils.isEmpty(getProperty("CookieDomain"))) {
                cookie.setDomain(getProperty("CookieDomain"));
            }
            log.methodExit(this.getClass().getSimpleName() + ".formatCookie()");
            return cookie;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public boolean validateAPIKey(String authusername, String remoteIP,
            String aclrole, String api_key, String domain)
            throws GenericException {

        log.methodEntry(this.getClass().getSimpleName() + ".validateAPIKey()");
        try {
            APIKey apikey = APIKey.getInstanceof(domain);
            boolean status = apikey.validateAPIKey(authusername, remoteIP,
                    aclrole, api_key, domain);
            log.methodExit(this.getClass().getSimpleName()
                    + ".validateAPIKey()");
            apikey = null;
            return status;
        } catch (GenericException e) {
            log.error(this.getClass().getSimpleName() + ".validateAPIKey()"
                    + e.getMessage());
            throw e;
        }
    }

    private String getProperty(String key) throws GenericException {
        try {
            String value = proputil.getProperty(key);
            if (utils.isEmpty(value) == false) {
                return value.trim();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".doValidate()", e);
        }
        return null;
    }
}
