package in.nic.cmf.cacheburster;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class CacheBursterImplTest extends TestCase {

	public void testAdd() {
		Map<String, String> map = getUserContext();
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		Map<String, Object> inputmap = new HashMap<String, Object>();
		inputmap.put("userContext", map);
		inputmap.put("content","<Collections><Category><Id>lljqSkdacb222</Id><EntityType>Category</EntityType><CategoryName>kavitha</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>successadmin</CreatedBy><LastModifiedBy>tideladmin</LastModifiedBy><Site>success.com</Site><Version>1.0</Version></Category></Collections>");
		inputmap.put("queryParams", "q=&entitytype=category");
		parameters.put("input", inputmap);
		CacheBursterImpl service = CacheBursterImpl.getInstance("success.com");
		Map result = service.add("success.com", "Category", parameters);
		System.out.println("Result:" + result);
	}

	private Map<String, String> getUserContext() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("aclrole", "portaladmin");
		map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
		map.put("requester", "124.7.228.200");
		map.put("authusername", "kavitha");
		return map;
	}
	
	public void testFind() {
		Map<String, String> map = getUserContext();
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		Map<String, Object> inputmap = new HashMap<String, Object>();
		inputmap.put("userContext", map);
		parameters.put("input", inputmap);
		Map<String, Object> ss = new HashMap<String,Object>();
		ss.put("format", "xml");		
		ss.put("entitytype", "Category");	
		ss.put("site", "tidel.in");	
		ss.put("callback", "test");
		ss.put("categoryname", "kavitha");
		inputmap.put("queryParams", ss);
		inputmap.put("queryString","q=&format=xml&version=1.0");
		inputmap.put("uri", "/cmf-1.0.0/appflow/tidel.in/role");
		inputmap.put("url","http: //210.210.125.149/cmf-1.0.0/appflow/tidel.in/category");
		CacheBursterImpl service = CacheBursterImpl.getInstance("tidel.in");
		Map result = service.find("tidel.in", "Category", parameters);
		System.out.println("Result:" + result);
	}
	
	
	public void testFindAndPost() {
		Map<String, String> map = getUserContext();
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		Map<String, Object> inputmap = new HashMap<String, Object>();
		inputmap.put("userContext", map);
		parameters.put("input", inputmap);
		Map<String, Object> ss = new HashMap<String,Object>();
		ss.put("format", "xml");		
		ss.put("entitytype", "Category");	
		ss.put("site", "tidel.in");	
		ss.put("callback", "test");
		ss.put("categoryname", "kavitha");
		inputmap.put("queryParams", ss);
		inputmap.put("queryString","q=&format=xml&version=1.0");
		inputmap.put("uri", "/cmf-1.0.0/appflow/tidel.in/role");
		inputmap.put("url","http: //210.210.125.149/cmf-1.0.0/appflow/tidel.in/category");
		CacheBursterImpl service = CacheBursterImpl.getInstance("tidel.in");
		Map result = service.find("tidel.in", "Category", parameters);
		System.out.println("test");
		System.out.println("-----4--------------------------------------------------------------------------------------------------------------------------------------------------");

		Map<String, String> map1 = getUserContext();
		Map<String, Map<String, Object>> parameters1 = new HashMap<String, Map<String, Object>>();
		Map<String, Object> inputmap1 = new HashMap<String, Object>();
		inputmap1.put("userContext", map1);
		inputmap1.put("content","<Collections><Category><Id>lljqSkdacb222</Id><EntityType>Category</EntityType><CategoryName>kavitha</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>tideladmin</CreatedBy><LastModifiedBy>tideladmin</LastModifiedBy><Site>tidel.in</Site><Version>1.0</Version></Category></Collections>");
	//	inputmap1.put("queryParams", "q=&entitytype=category");
		parameters1.put("input", inputmap1);
		CacheBursterImpl service1 = CacheBursterImpl.getInstance("tidel.in");
		Map result1 = service.add("tidel.in", "Category", parameters1);
		System.out.println("Result:" + result);
	}
	

	public void testFindById() {
		fail("Not yet implemented");
	}

}
