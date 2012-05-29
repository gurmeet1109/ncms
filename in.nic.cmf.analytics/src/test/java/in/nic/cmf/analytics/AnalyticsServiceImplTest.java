package in.nic.cmf.analytics;

import java.util.HashMap;

import junit.framework.TestCase;

public class AnalyticsServiceImplTest extends TestCase {

	public void testFind() {
		AnalyticsServiceImpl impl= new AnalyticsServiceImpl();
		HashMap<String,HashMap<String,Object>> queryParams= new HashMap<String,HashMap<String,Object>>();
    	HashMap<String,Object> usercontextMap = new HashMap<String,Object>();
		HashMap<String,Object> dataQueryMap = new HashMap<String,Object>();
		
		usercontextMap.put("aclrole", "portaladmin");
		usercontextMap.put("api_key",  "portaladmin");
		usercontextMap.put("requester",  "portaladmin");
		usercontextMap.put("authusername",  "portaladmin");
		queryParams.put("usercontext", usercontextMap);
		dataQueryMap.put("querystring", "q=&entitytype=article");
		queryParams.put("data", dataQueryMap);
		
    	impl.find("nivi.in", "article",queryParams);
		
	}

}
