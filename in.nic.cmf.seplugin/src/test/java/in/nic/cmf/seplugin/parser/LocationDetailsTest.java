package in.nic.cmf.seplugin.parser;

import java.util.HashMap;

import org.junit.Test;

import junit.framework.Assert;

public class LocationDetailsTest {
	LocationDetails locationDetails = new LocationDetails();
	
	@Test
	public void testLocationDetailsInstanceNotNull() {
		Assert.assertNotNull(locationDetails);
	}
	
	@Test
	public void testLocationDetailsAddLocationInformationWithQueryCollAndMapSolrDataBothNotNull() throws Exception {
		HashMap<String, Object> querycoll = new HashMap<String, Object>();
		HashMap<String, String> mapsolrData = new HashMap<String, String>();
		
		mapsolrData.put("LATITUDE", "66 degree 27 minute");
		mapsolrData.put("LONGITUDE", "42 degree 31 minute");
		mapsolrData.put("RADIUS", "34 degree");
		
		Assert.assertNotNull(locationDetails.addLocationInformation(querycoll, mapsolrData));
	}
}
