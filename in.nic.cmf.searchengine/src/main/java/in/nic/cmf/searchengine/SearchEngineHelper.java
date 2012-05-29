package in.nic.cmf.searchengine;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SearchEngineHelper {

    private static HashMap<String, SearchEngineHelper> seHelper = new HashMap<String, SearchEngineHelper>();
    private LogTracer                                  log;
    private String                                     domain;
    public List<String>                                excludeParams;
    private PropertyManagement                         propUtil = PropertyManagement
                                                                        .getInstance();
    private Map<String, String>                        modifyParams;

    public SearchEngineHelper(String domain) {
        this.domain = domain;

        // propUtil = PropertiesUtil.getInstanceof(domain, "searchengine");
        setExcludeParams((String) propUtil.getProperties(domain,
                "searchengine", "Configuration", "excludeparams"));

        this.log = new LogTracer(domain, "searchengine");
    }

    public static SearchEngineHelper getInstance(String domain) {

        if (!seHelper.containsKey(domain)) {
            seHelper.put(domain, new SearchEngineHelper(domain));
        }
        return seHelper.get(domain);
    }

    public void setExcludeParams(String excludeParamsValue) {
        excludeParams = Arrays.asList(excludeParamsValue.split(","));
    }

    public HashMap<String, HashMap<String, String>> excludeQueryParam(
            HashMap<String, HashMap<String, String>> hmapSplitted) {
        log.methodEntry("excludeQueryParam entry");
        log.debug("need to exclude:" + hmapSplitted);
        log.debug("exclud list:" + excludeParams);
        HashMap<String, String> userDataMap = new HashMap<String, String>();
        for (Entry e : hmapSplitted.get("userData").entrySet()) {
            log.debug("in for" + e);
            for (String exclude : excludeParams) {
                log.debug("in exclude:" + exclude);
                String entryKey = (String) e.getKey();
                String entryValue = (String) e.getValue();
                log.debug("entry:" + entryKey);
                if (!exclude.equalsIgnoreCase(entryKey)) {
                    log.debug("removing key:" + entryKey);
                    userDataMap.put(entryKey, entryValue);
                    // hmapSplitted.remove(entry);
                }
            }
        }
        log.debug("userDataMap:" + userDataMap);
        hmapSplitted.put("userData", userDataMap);
        log.debug("output:" + hmapSplitted);
        log.methodExit("excludeQueryParam exit");
        return hmapSplitted;
    }

    public MultivaluedMapImpl applyExcludeParams(MultivaluedMapImpl list)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".applyExcludeParams()");

        try {
            java.util.Iterator<String> items = this.excludeParams.iterator();
            while (items.hasNext()) {
                list.remove(items.next());
            }
            log.methodExit(this.getClass().getSimpleName()
                    + ".applyExcludeParams()");
            return list;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("ERR-DS-0000", this.getClass()
                    .getSimpleName() + ":applyExcludeParams()", e);
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isBoolean(String input) {
        if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
            return true;
        } else {
            return false;
        }

    }
}
