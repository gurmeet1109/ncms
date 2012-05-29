package in.nic.cmf.seplugin.parser;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.seplugin.contract.ISearchAPIConstants;

import java.util.HashMap;

/**
 * Class for location details.
 * @author premananda
 */
public class LocationDetails {
    /**
     * variable for log information.
     */
    private static LogTracer log;
    private static String    domain;
    static {
        log = new LogTracer(domain, "searchengine", true, true, true, true,
                false);
    }

    /**
     * add location information.
     * @param querycoll
     *            is of type HashMap<String, Object> querycoll
     * @param mapsolrData
     *            is of type HashMap<String, String>
     * @return is of type HashMap<String, Object>
     * @throws Exception
     *             is Exception
     */
    public final HashMap<String, Object> addLocationInformation(
            final HashMap<String, Object> querycoll,
            final HashMap<String, String> mapsolrData) throws Exception {
        HashMap<String, String> locationMap = new HashMap<String, String>();
        locationMap.put(ISearchAPIConstants.LAT,
                mapsolrData.get(ISearchAPIConstants.LATITUDE));
        locationMap.put(ISearchAPIConstants.LONG,
                mapsolrData.get(ISearchAPIConstants.LONGITUDE));
        locationMap.put(ISearchAPIConstants.RADIUS,
                mapsolrData.get(ISearchAPIConstants.RADIUS));
        locationMap.put(ISearchAPIConstants.QT, ISearchAPIConstants.GEO);
        querycoll.put("param", locationMap);
        log.debug("param : " + locationMap);
        return querycoll;
    }
}
