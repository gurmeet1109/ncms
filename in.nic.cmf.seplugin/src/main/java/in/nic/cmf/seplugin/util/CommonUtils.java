package in.nic.cmf.seplugin.util;

import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;

/**
 * Class for common utilities.
 * @author premananda
 */
public final class CommonUtils {
    /**
     * static instance of the class CommonUtils.
     */
    // private static final CommonUtils COMMONUTILS = new CommonUtils();
    private static HashMap<String, CommonUtils> hashCommonUtils = new HashMap<String, CommonUtils>();
    /**
     * variable for log information.
     */
    private LogTracer                           log;

    private CommonUtils(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                true);
    }

    /**
     * method for singleton.
     * @return is of type CommonUtils
     */
    // public static CommonUtils getInstanceOf() {
    // return COMMONUTILS;
    // }
    public static CommonUtils getInstanceof(String domain) {
        if (!hashCommonUtils.containsKey(domain)) {
            hashCommonUtils.put(domain, new CommonUtils(domain));
        }
        return hashCommonUtils.get(domain);
    }

    /**
     * checks whether the string is empty or not.
     * @param value
     *            as string
     * @return is of type boolean
     */
    public boolean isEmpty(final String value) {
        log.methodEntry(this.getClass().getSimpleName() + ".isEmpty()");
        if (value != null) {
            // value = value.trim();
            log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
            // true if length() is 0, otherwise false
            return value.trim().isEmpty();
        }
        log.debug("isEmpty ==> true");
        log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
        return true;
    }
}
