package in.nic.cmf.util;

import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * UserContex Class to frame user credentials for validation.
 * @author Ramu P Date: 14.11.2011
 */
public class UserContext {

	private Utils                               utils;
	// private LogTracer log = new LogTracer("UtilTraceLogger");
	private LogTracer                           log;
	private List<String>                        userContextKey  = new ArrayList<String>();
	private static HashMap<String, UserContext> hashUserContext = new HashMap<String, UserContext>();
	private String                              domain          = "";

	private UserContext(String domain) {
		this.domain = domain;

		System.out.println("incoming domain name is"+domain);
		String[] usrCtxKey = {"api_key", "authusername", "aclrole" };
		userContextKey = Arrays.asList(usrCtxKey);
		System.out.println("keys:"+userContextKey);
		setLogger(domain);
		utils = Utils.getInstanceof(domain);
	}

	private void setLogger(String domain) {
		log = new LogTracer(domain, "UtilTraceLogger");
	}

	public static UserContext getInstanceof(String domain) {
		System.out.println("inside getInstanceof:"+domain);
		if (!hashUserContext.containsKey(domain)) {
			System.out.println("contains:"+domain);
			hashUserContext.put(domain, new UserContext(domain));
		}
		return hashUserContext.get(domain);
	}

	private List<String> getUserContextKey() {
		return userContextKey;
	}

	public HashMap<String, String> getUserContext(HttpServletRequest request) {

		System.out.println("getUserContext entry");

		log.methodEntry("getUsercontext entry");
		HashMap<String, String> userContext = new HashMap<String, String>();
		Iterator<String> itr = getUserContextKey().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			String value = null;
			if (!utils.isEmpty(request.getParameter(key))) {
				value = request.getParameter(key);
				log.debug("KV:"+key+"--"+value);
				System.out.println("KV:"+key+"--"+value);
			}
			userContext.put(key, value);
			log.debug("usercontext:"+userContext);
			System.out.println("usercontext:"+userContext);
		}
		itr = null;

		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String key = cookies[i].getName();
				if (userContextKey.contains(key)) {
					
					System.out.println("containskey:"+key+"; value:"+cookies[i].getValue());

					String cookie = cookies[i].getValue().trim();

					System.out.println("coookie:::"+cookie);

					if(cookie!=null && !cookie.isEmpty() && !cookie.equals("null")){
						System.out.println("not equal to null:"+cookies[i].getValue());
						if (!utils.isEmpty(cookies[i].getValue())) {
							userContext.put(key, cookies[i].getValue());
							System.out.println("In cookie:"+userContext);
						}
					}

					/*
					if((cookie != null) || (!cookie.equals("")) || (!cookie.equals("null"))){
						System.out.println("not equal to null:"+cookies[i].getValue());
						if (!utils.isEmpty(cookies[i].getValue())) {
							userContext.put(key, cookies[i].getValue());
							System.out.println("In cookie:"+userContext);
						}
					}
					 */

				}
			}
		}
		String remoteIP = request.getRemoteAddr();
		log.info("readed remoteIP : " + remoteIP);
		System.out.println("readed remoteIP : " + remoteIP);
		if (!utils.isEmpty(request.getHeader("x-forwarded-for"))) {
			remoteIP = request.getHeader("x-forwarded-for");
			log.info("x-forwarded-for ==> " + remoteIP);
			int idx = remoteIP.indexOf(",");
			if (idx > -1) {
				remoteIP = remoteIP.substring(0, idx).trim();
			}
		}
		log.info("in user context RemoteIP : " + remoteIP);
		userContext.put("requester", remoteIP);
		System.out.println("USERCONTEXT:"+userContext);
		return userContext;
	}

	public HashMap<String, String> getUserContext(
			Map<String, Object> authContext) {
		HashMap<String, String> userContext = new HashMap<String, String>();
		log.debug("Auth Context => " + authContext);
		Iterator<String> itr = getUserContextKey().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			String value = (String) authContext.get(key);
			if (value != null) {
				userContext.put(key, value);
			}
		}
		if (authContext.get("requester") != null) {
			userContext.put("requester", authContext.get("requester")
					.toString());
		}
		itr = null;
		return userContext;
	}
}
