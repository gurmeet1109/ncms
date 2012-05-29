package in.nic.cmf.searchengine;

import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;

/**
 * 
 * @author Sify
 * 
 */
public class LocationDetails {
	/**
     * 
     */
	private static final LocationDetails LOCDETAILS = new LocationDetails();
	
	public LogTracer log;

	private LocationDetails() {
	}

	public static LocationDetails getInstance() {
		return LOCDETAILS;
	}

	/**
	 * 
	 * @param querycoll
	 * @param mapsolrData
	 * @return
	 */
	public final HashMap<String, Object> addLocationInformation(
			final HashMap<String, Object> querycoll,
			final HashMap<String, String> mapsolrData) {
		HashMap<String, String> locationMap = new HashMap<String, String>();
		locationMap.put(ServiceConstants.LAT,
				mapsolrData.get(ServiceConstants.LATITUDE));
		locationMap.put(ServiceConstants.LONG,
				mapsolrData.get(ServiceConstants.LONGITUDE));
		locationMap.put(ServiceConstants.RADIUS,
				mapsolrData.get(ServiceConstants.RADIUS));
		locationMap.put(ServiceConstants.QT, ServiceConstants.GEO);
		querycoll.put("param", locationMap);
		log.debug("LocationDetails :: Param : " + locationMap);
		return querycoll;
	}

	public void setLogTracer(LogTracer log) {
		this.log = log;
	}
}
