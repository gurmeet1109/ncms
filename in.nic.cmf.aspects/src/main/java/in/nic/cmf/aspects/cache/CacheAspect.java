package in.nic.cmf.aspects.cache;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.UserContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import in.nic.cmf.cache.Cachable;
import in.nic.cmf.exceptions.GenericException;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The Class CacheAspect.
 * @author Ramu P Created on : 2nd May 2011
 */
public class CacheAspect {

    /** The cache. */
    @Autowired
    private Cachable                 cache;
    private LogTracer                log;

    private List<String>             exludeParams;
    private Map<String, String>      modifyParams;

    private PropertiesUtil           proputil;
    private static ArrayList<String> adminIpsList      = new ArrayList<String>();
    private static ArrayList<String> nocacheRefrerList = new ArrayList<String>();
    private static ArrayList<String> nocacheRoleList   = new ArrayList<String>();
    private String                   domain;

    public CacheAspect(String domain) {
    	System.out.println("Domain : "+domain);
    	this.domain=domain;
        setLogger(domain);
        proputil = PropertiesUtil.getInstanceof(domain, "aspects");
        init();
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "CacheAspect");
    }

    private void init() {

        loadProperty(proputil.getProperty("nocacheIps"), adminIpsList);
        loadProperty(proputil.getProperty("nocacherefrer"), nocacheRefrerList);
        loadProperty(proputil.getProperty("nocacheroles"), nocacheRoleList);
        /*
         * if (null != cmsadminip) { // String adminIps[] =
         * cmsadminip.split(","); // for (String ip : adminIps) { for (String ip
         * : cmsadminip.split(",")) { adminIpsList.add(ip); } } else {
         * log.info("No Property found for : nocacheIps"); } if (null !=
         * nocacherefererIps) { for (String ip : nocacherefererIps.split(",")) {
         * nocacheRefrerList.add(ip); } } else {
         * log.info("No Property found for : nocacherefrer"); }
         * if (null != nocacheroleIps) { for (String ip :
         * nocacheroleIps.split(",")) { nocacheRoleList.add(ip); } } else {
         * log.info("No Property found for : nocacheroles"); }
         */

    }

    private void loadProperty(String key, ArrayList<String> propList) {
        if (null != key) {
            for (String ip : key.split(",")) {
                propList.add(ip);
            }
        } else {
            log.info("No Property found for :" + key);
        }
        log.debug("Property List::" + propList);
    }

    /**
     * Cache around advice.
     * @param joinPoint
     *            the join point
     * @return the object
     * @throws Throwable
     *             the throwable
     */

    public Object cacheAroundAdvice(ProceedingJoinPoint joinPoint)
            throws Throwable {
        log.debug("Cache Entry");
        Object[] args = joinPoint.getArgs();
        Object collections = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                // log.debug("Inside  HttpServletRequest from own DS identified if condtion");
                collections = getCache(joinPoint, (HttpServletRequest) arg);
                /*
                 * log.debug("After completeting  cache collections :::" +
                 * collections);
                 */
            }
        }
        return collections;
    }

    /**
     * Gets the cache.
     * @param joinPoint
     *            the join point
     * @param request
     *            the request
     * @param key
     *            the key
     * @return the cache
     * @throws Throwable
     *             the throwable
     */
    public Object getCache(ProceedingJoinPoint joinPoint,
            HttpServletRequest request) throws Throwable {
        /*
         * log.debug("inside getCache,request.getQueryString():::" +
         * request.getQueryString());
         */

        Object collection = null;

        if (isNotCachableIps(request) || isNotcachableReffrer(request)
                || isNotCachableRole(request)) {
            collection = joinPoint.proceed();
            log.debug("Not cached result given from the System everytime");
            return collection;
        }

        Map<String, String> list = applyModifyParams(stringToMap(request
                .getQueryString()));
        log.debug("Map : " + list);
        String key = request.getRequestURL() + "?"
                + mapToString(applyExcludeParams(list));

        collection = cache.get(key);
        log.debug("cache key:::" + key);
        if (collection != null) {
            log.debug("\nAvail in Cache");
            return collection;
        }
        collection = joinPoint.proceed();
        cache.set(key, collection);
        log.debug(" Result cached ");
        return collection;
    }

    private boolean isNotCachableRole(HttpServletRequest request) {
        HashMap<String, String> userContextMap = UserContext.getInstanceof(
                domain).getUserContext(request);
        log.debug("nocacheRoleList::" + nocacheRoleList);
        log.debug("userContextMap::" + userContextMap);
        return nocacheRoleList.contains(userContextMap.get("aclrole"));
    }

    private boolean isNotcachableReffrer(HttpServletRequest request) {
        log.debug("nocacheRefrerList::" + nocacheRefrerList);

        String referer = request.getHeader("referer");

        // String referer = "http://124.7.228.168/ncmsui/app/sify.co.in";

        log.debug("request.getHeader(referer)::" + referer);
        if (null != referer) {
            log.debug("Referer Not null-----");
            // for (String param : referer.split("/")) {
            // log.debug("param::" + param);
            // log.debug("-------" + nocacheRefrerList.contains(param));
            // if (nocacheRefrerList.contains(param)) {
            // return true;
            // }
            // }
            for (String param : nocacheRefrerList) {
                if (referer.contains(param)) {
                    return true;
                }

            }
        }
        return false;
    }

    private boolean isNotCachableIps(HttpServletRequest request) {

        String remoteIp = !isEmpty(request.getHeader("x-forwarded-for")) ? request
                .getHeader("x-forwarded-for") : request.getRemoteAddr();
        log.debug("remoteIp::" + remoteIp);
        String remoteIpArray[] = remoteIp.split(",");
        remoteIp = remoteIpArray.length > 0 ? remoteIpArray[0] : "";
        log.debug("::Request from Admin Ip" + remoteIp);
        return adminIpsList.contains(remoteIp);

    }

    public boolean isEmpty(String value) {

        if (value != null) {
            value = value.trim();
            log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
            return value.isEmpty(); // true if length() is 0, otherwise false
        }
        return true;
    }

    public String mapToString(Map<String, String> map) throws GenericException {
        log.debug("inside mapToString,map::::" + map);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (String key : map.keySet()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append("&");
                }
                String value = map.get(key);
                log.debug("value::" + value);
                stringBuilder.append((key != null ? URLEncoder.encode(key,
                        "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value,
                        "UTF-8") : "");
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            throw new GenericException(domain, "ERR-DS-0004",
                    "Unsupported Encoding Exception", e);
        }
    }

    public Map<String, String> stringToMap(String input)
            throws GenericException {
        log.debug("StringToMap::::" + input);
        Map<String, String> map = new HashMap<String, String>();
        if (input != null) {
            String[] nameValuePairs = input.split("&");
            for (String nameValuePair : nameValuePairs) {
                String[] nameValue = nameValuePair.split("=");
                try {
                    map.put(URLDecoder.decode(nameValue[0], "UTF-8"),
                            nameValue.length > 1 ? URLDecoder.decode(
                                    nameValue[1], "UTF-8") : "");
                } catch (UnsupportedEncodingException e) {
                    throw new GenericException(domain, "ERR-DMS-0004",
                            "Unsupported Encoding Exception", input, e);
                }
            }
        }
        log.debug("map from stringtoMap::" + map.toString());
        return map;
    }

    public Map<String, String> applyExcludeParams(Map<String, String> list)
            throws GenericException {
        log.debug("Inside applyExcludeParams");
        exludeParams = new ArrayList<String>();
        exludeParams.add("format");
        exludeParams.add("type");
        exludeParams.add("_dc");
        exludeParams.add("callback");
        exludeParams.add("query");
        exludeParams.add("page");
        exludeParams.add("node");
        exludeParams.add("egroup");
        exludeParams.add("wid");
        exludeParams.add("wtype");
        exludeParams.add("wtitle");
        exludeParams.add("aclrole");
        log.debug("inside applyExcludeParams::::list.toString()"
                + list.toString());
        log.methodEntry(this.getClass().getSimpleName()
                + ".applyExcludeParams()");
        try {
            java.util.Iterator<String> items = this.exludeParams.iterator();
            while (items.hasNext()) {
                list.remove(items.next());
            }
            log.methodExit(this.getClass().getSimpleName()
                    + ".applyExcludeParams()");
            list = new TreeMap<String, String>(list);
            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-DS-0000", this.getClass()
                    .getSimpleName() + ":applyExcludeParams()", e);
        }
    }

    public Map<String, String> applyModifyParams(Map<String, String> mParams)
            throws GenericException {
        log.debug("inside applyModifyParams::" + mParams);
        modifyParams = new HashMap<String, String>();
        modifyParams.put("start", "offset");
        modifyParams.put("sort", "orderBy");
        modifyParams.put("dir", "orderByDir");
        modifyParams.put("query", "q");
        log.debug("after hashmap::::");
        log.debug("applyModifyParams.raja....inside,mParams"
                + mParams.toString());
        log.methodEntry(this.getClass().getSimpleName()
                + ".applyModifyParams()");
        try {
            Set set = modifyParams.entrySet();
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                if (mParams.containsKey(entry.getKey())) {
                    String value = mParams.get(entry.getKey());
                    mParams.remove(entry.getKey());
                    mParams.put(entry.getValue().toString(), value);
                }
            }
            log.debug("Exit boss::::" + this.getClass().getSimpleName());
            log.methodExit(this.getClass().getSimpleName()
                    + ".applyModifyParams()");
            return mParams;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-DS-0000", this.getClass()
                    .getSimpleName() + ":applyModifyParams()", e);
        }
    }

}
