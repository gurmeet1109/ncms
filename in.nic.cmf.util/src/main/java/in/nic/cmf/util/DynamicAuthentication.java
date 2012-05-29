package in.nic.cmf.util;

import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class DynamicAuthentication {

    private String authentication = null;
    private String authUsername = null;
    private String authPassword = null;
    private String authURL = null;
    private String domain = null;
    // private LogTracer log = new LogTracer("UtilTraceLogger",
    // true, true, true, true, true);
    private LogTracer log;

    private PropertiesUtil putil = null;
    private List<String> propList = new ArrayList<String>();

    private DynamicAuthentication() {
	propList.add("dynamicauth");
    }

    private void setLogger(String domain) {
	log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
		false);
    }

    public DynamicAuthentication(String role, String domain)
	    throws GenericException {
	this();
	setDomain(domain);
	setLogger(domain);
	putil = PropertiesUtil.getInstanceof(domain, "util", propList);
	setAuthURL(putil.getProperty("authURL"));
	setAuthUsername(putil.getProperty(role + "-authUsername"));
	setAuthPassword(putil.getProperty(role + "-authPassword"));
    }

    public DynamicAuthentication(String authusername, String password,
	    String domain) throws GenericException {
	this();
	setDomain(domain);
	setLogger(domain);
	putil = PropertiesUtil.getInstanceof(domain, "util", propList);
	setAuthURL(putil.getProperty("authURL"));
	setAuthUsername(authusername);
	setAuthPassword(password);
    }

    public boolean autoSignin() throws GenericException {
	log.methodEntry(this.getClass().getSimpleName() + ".autoSignin()");
	log.debug(getAuthURL() + " ::::: " + getAuthUsername() + " ::::: "
		+ getAuthPassword());
	try {
	    HttpClient httpclient = new HttpClient();
	    log.debug("client created" + getAuthURL());
	    PostMethod method = new PostMethod(getAuthURL());
	    method.addParameter("EntityType", "Authentication");
	    method.addParameter("AuthUserName", getAuthUsername());
	    method.addParameter("Password", getAuthPassword());
	    // method.addParameter("accessType", getAccessType());
	    log.debug("parameter framed");
	    int responseCode = httpclient.executeMethod(method);
	    log.debug("response " + responseCode);
	    if (responseCode == 200) {
		String responseValue = method.getResponseBodyAsString();
		log.debug(responseValue);
		// xml to pojo [Authentication.java]
		Convertor xmlconvertor = FormatXml.getInstanceof(domain);
		HashMap<String, Object> authenticationMap = xmlconvertor
			.in(responseValue);

		if (authenticationMap != null) {
		    HashMap authinfo = ((HashMap<String, HashMap<String, Object>>) authenticationMap
			    .get("Collections")).get("Authentication");
		    log.debug("auth info" + authinfo);
		    setAuthentication(responseValue);
		    if (authinfo.get("Success").equals("true")) {
			log.methodExit(this.getClass().getSimpleName()
				+ ".autoSignin()");
			return true;
		    }
		}
	    }
	    log.methodExit(this.getClass().getSimpleName() + ".autoSignin()");
	    return false;
	} catch (IllegalArgumentException e) {
	    log.error(e.getMessage());
	    throw new GenericException(domain, "ERR-UTL-0009", this.getClass()
		    .getSimpleName() + ".autoSignin()", e);
	} catch (HttpException e) {
	    log.error(e.getMessage());
	    throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
		    .getSimpleName() + ".autoSignin()", e);
	} catch (IOException e) {
	    log.error(e.getMessage());
	    throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
		    .getSimpleName() + ".autoSignin()", e);
	}
    }

    private void setAuthentication(String authentication) {
	this.authentication = authentication;
    }

    public String getAuthentication() {
	return authentication;
    }

    private void setAuthUsername(String authUsername) {
	this.authUsername = authUsername;
    }

    private String getAuthUsername() {
	return authUsername;
    }

    private void setAuthPassword(String authPassword) {
	this.authPassword = authPassword;
    }

    private String getAuthPassword() {
	return authPassword;
    }

    private void setAuthURL(String authURL) {
	if (authURL.substring(authURL.length() - 1, authURL.length()).equals(
		"/") == false) {
	    authURL += "/";
	}
	authURL += getDomain() + "/Authentication";
	this.authURL = authURL;
    }

    private String getAuthURL() {
	return authURL;
    }

    private void setDomain(String domain) throws GenericException {
	if (isEmpty(domain)) {
	    throw new GenericException(domain, "ERR-UTL-0008");
	}
	this.domain = domain;
    }

    private String getDomain() {
	return domain;
    }

    private boolean isEmpty(String value) {
	if (value != null) {
	    value = value.trim();
	    return value.isEmpty(); // true if length() is 0, otherwise false
	}
	return true;
    }

}
