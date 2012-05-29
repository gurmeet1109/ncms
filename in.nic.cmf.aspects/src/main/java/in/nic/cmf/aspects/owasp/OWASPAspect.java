package in.nic.cmf.aspects.owasp;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.owasp.CrossSiteScript;
import in.nic.cmf.security.owasp.Decode;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

public class OWASPAspect {
    private LogTracer log;
  
    
    private String domain;
    private void setLogger(String domain) {
        log = new LogTracer(domain, "cross-site-script");
    }

    public void preCheck(JoinPoint joinpoint) throws GenericException {
        System.out.println("I am being checked for Applicationflow::");
        CodeSignature methodSignature = (CodeSignature) joinpoint
                .getSignature();
        String methodName = methodSignature.getName();
        Object[] paramNames = methodSignature.getParameterNames();
        Object[] args = joinpoint.getArgs();
        // Collections<?> inputCollections;

        String xmlContent = "";
        HashMap<String, String> headers = null;
        HashMap<String, String> parameters = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                System.out.println("inside HttpServletRequest=======");
                isSafeStringCheck(xmlContent);
                headers = getRequestHeader((HttpServletRequest) arg);
                isSafeStringCheck(headers);
                parameters = getRequestParameter((HttpServletRequest) arg);
                isSafeStringCheck(parameters);

            } else if (arg instanceof UriInfo) {
                System.out.println("inside UriInfo============>>>>>>>>>>>>>");
                parameters = getRequestParameter((UriInfo) arg);
                isSafeStringCheck(parameters);
            } /*
               * else if (arg instanceof Collections) {
               * String collectionsStr = Utils.getInstance()
               * .getCollectionsAsStringXml((Collections) arg);
               * log.debug("Before Inside Collections::::" + collectionsStr);
               * isSafeStringCheck(collectionsStr);
               * }
               */else if (arg instanceof String) {
                System.out.println("inside String================");
                log.debug("YEs u got string vlue from add::::" + (String) arg);
                isSafeStringCheck((String) arg);
            }

        }
        // log.debug("headers : " + headers.toString());
        // log.debug("parameters : " + parameters.toString());
    }

    private HashMap<String, String> getRequestParameter(
            HttpServletRequest request) {
        Enumeration en = request.getParameterNames();
        HashMap<String, String> reqParam = new HashMap<String, String>();
        while (en.hasMoreElements()) {
            String key = (String) en.nextElement();
            String value = request.getParameter(key);
            log.debug("RequetParam Security Testing key::" + key
                    + ":::And Value::" + value);
            reqParam.put(key, value);
        }
        return reqParam;
    }

    private void isSafeStringCheck(Object param) throws GenericException {
        CrossSiteScript xss = new CrossSiteScript(domain);
        boolean xssstatus = false;
        if (param instanceof String) {
            String paramvalue = ((String) param).trim();
            if (paramvalue.startsWith("<") && paramvalue.endsWith(">")) {
                xssstatus = xss.isSafe(paramvalue);
            } else {
                xssstatus = xss.isSafeString(paramvalue);
            }
        } else if (param instanceof HashMap) {
            Set<Entry<String, String>> reqparset = ((HashMap<String, String>) param)
                    .entrySet();
            Iterator<Entry<String, String>> reqpariter = reqparset.iterator();
            while (reqpariter.hasNext()) {
                Map.Entry<String, String> entry = reqpariter.next();
                log.debug("isSafeStringCheck HashMap --- key : "
                        + entry.getKey() + " ---- value : " + entry.getValue());
                xssstatus = xss.isSafeString(entry.getValue());
            }
        }
    }

    private HashMap<String, String> getRequestHeader(HttpServletRequest req) {
        Enumeration enumeration = req.getHeaderNames();
        HashMap<String, String> requestValueMap = new HashMap<String, String>();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            String value = req.getHeader(key);
            log.debug("key::" + key + "::::Value::" + value);
            requestValueMap.put(key, value);
        }
        return requestValueMap;
    }

    private HashMap<String, String> getRequestParameter(UriInfo uriInfo) {
        HashMap<String, String> requestValueMap = new HashMap<String, String>();
        MultivaluedMap<String, String> uriInfodata = uriInfo
                .getQueryParameters();
        Set<String> keylist = uriInfodata.keySet();
        Iterator iterator = keylist.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            List<String> object = uriInfodata.get(key);
            log.debug("key::" + key + "::::Value::" + object.toString());
            requestValueMap.put(key.toLowerCase(), object.toString());
        }
        return requestValueMap;
    }

    /*
     * StringBuilder stringBuilder = new StringBuilder(); BufferedReader
     * bufferedReader = null; try { InputStream inputStream =
     * req.getInputStream(); if (inputStream != null) {
     * log.debug("inputstream"); bufferedReader = new BufferedReader(new
     * InputStreamReader( inputStream)); log.debug("afte xyasdfasdf"); char[]
     * charBuffer = new char[128]; log.debug("after 123"); int bytesRead = -1;
     * while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
     * log.debug("after ::: "+bytesRead); stringBuilder.append(charBuffer, 0,
     * bytesRead); log.debug("aft+++++++"+bytesRead); } } else {
     * stringBuilder.append(""); } } catch (IOException ex) { //content read
     * throws exception, so return value is null return null; } finally { if
     * (bufferedReader != null) { try { bufferedReader.close(); } catch
     * (IOException ex) { // no need to handle this exception } } }
     * log.debug("stringBuilder.toString():::"+stringBuilder.toString()); return
     * stringBuilder.toString();
     */

    @SuppressWarnings ("unchecked")
    public void postCheck(Object resulting) throws GenericException {

        System.out.println("Inside postcheck111:::" + resulting);

        if (resulting instanceof String) {
            if (resulting.toString().startsWith("<")
                    && resulting.toString().endsWith(">")) {
                OWASPAspect owasp = new OWASPAspect();
                String str = (String) resulting;
                System.out.println("From String value:::" + str);
                String securedCollectionString = owasp.isDecodeStringCheck(str);
                System.out.println("Secured Collections are:::"
                        + securedCollectionString);

                try {
                    Field value = str.getClass().getDeclaredField("value");
                    Field count = str.getClass().getDeclaredField("count");
                    Field hash = str.getClass().getDeclaredField("hash");
                    Field offset = str.getClass().getDeclaredField("offset");
                    value.setAccessible(true);
                    count.setAccessible(true);
                    hash.setAccessible(true);
                    offset.setAccessible(true);

                    char[] newVal = securedCollectionString.toCharArray();
                    value.set(str, newVal);
                    count.set(str, newVal.length);
                    hash.set(str, 0);
                    offset.set(str, 0);
                } catch (NoSuchFieldException e) {
                } catch (IllegalAccessException e) {
                }

            }

            System.out.println("Final result:" + resulting.toString());
        }

        /*
         * if(resulting instanceof Collections){
         * System.out.println("I am here");
         * if(resulting instanceof Collections){
         * resulting =
         * Utils.getInstance().getCollectionsAsStringXml((Collections<?>)
         * resulting);
         * System.out.println("What is actual:::"+resulting);
         * if (resulting.toString().startsWith("<")) {
         * System.out.println("inside < ");
         * OWASPAspect owasp = new OWASPAspect();
         * String str =(String)resulting;
         * System.out.println("From String instanceof Collections value:::"+str);
         * System.out.println("1");
         * domain
         * String securedCollectionString = owasp.isDecodeStringCheck(str);
         * System.out.println("2");
         * System.out.println("Secured Collections values are:::"+
         * securedCollectionString);
         * try {
         * Field value = str.getClass().getDeclaredField("value");
         * Field count = str.getClass().getDeclaredField("count");
         * Field hash = str.getClass().getDeclaredField("hash");
         * Field offset = str.getClass().getDeclaredField("offset");
         * value.setAccessible(true);
         * count.setAccessible(true);
         * hash.setAccessible(true);
         * offset.setAccessible(true);
         * System.out.println("3");
         * char[] newVal = securedCollectionString.toCharArray();
         * value.set(securedCollectionString,newVal);
         * System.out.println("44");
         * count.set(securedCollectionString,newVal.length);
         * hash.set(securedCollectionString,0);
         * offset.set(securedCollectionString,0);
         * System.out.println("55");
         * } catch (NoSuchFieldException e) {
         * } catch (IllegalAccessException e) {}
         * Collections<Storable> collections = (Collections<Storable>)
         * Utils.getInstance
         * ().getStringXmlAsCollections(securedCollectionString);
         * resulting = (Collections<Storable>)collections;
         * }
         * }
         * }
         */

    }

    private String isDecodeStringCheck(String toDecode) {
        System.out.println("From isDecodeStringCheck");
       // Decode decodeObject = new Decode(domain);
        Decode decodeObject = Decode.getInstanceof(domain);
        String decodedCollectionsXml = decodeObject.decode(toDecode);
        Pattern pattern = Pattern.compile("&#0;");
        Matcher matcher = pattern.matcher(decodedCollectionsXml);
        decodedCollectionsXml = matcher.replaceAll("").trim();
        System.out.println("What is decodedCollectionsXml::::"
                + decodedCollectionsXml);
        return decodedCollectionsXml;
    }

}
