package in.nic.cmf.ruleengine;


import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class RuleEngineImplTest extends TestCase {

	

    private Map<String, String> getUserContext() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "PortalAdmin");
        map.put("api_key", "945c7826646aa31c6f76240fd91278249707e685");
        map.put("requester", "210.210.125.149");
        map.put("authusername", "tideladmin");
        return map;
    }
	
	public void testAdd() {
	       Map<String, String> map = getUserContext();
	        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
	        Map<String, Object> inputmap = new HashMap<String, Object>();
	        inputmap.put("userContext", map);
	        //inputmap.put("content","<Collections><Category><Id>lljqSkdacb222</Id><EntityType>Category</EntityType><CategoryName>testcategory</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>tideladmin</LastModifiedBy><Site>tidel.in</Site><Version>1.0</Version></Category></Collections>");
	        inputmap.put("content","<Collections><Category><Id>lljqSkdacb222</Id><EntityType>Category</EntityType><CategoryName>testcategory</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>tideladmin</LastModifiedBy><Site>tidel.in</Site><Version>1.0</Version></Category><Category><Id>lljqSkdacb222</Id><EntityType>Category</EntityType><CategoryName>testcategory</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>tideladmin</LastModifiedBy><Site>tidel.in</Site><Version>1.0</Version></Category></Collections>");
	        
	      //  inputmap.put("queryParams", "q=&entitytype=category");
	        parameters.put("input", inputmap);
	        RuleEngineImpl ruleController = new RuleEngineImpl();
	        Map result = ruleController
	                .add("tidel.in", "category", parameters);
	        // assertEquals(200, Integer.parseInt((String)
	        // result.get("responseCode")));
	        System.out.println("Result:" + result);
	}
	
	public void testDeleteByID() {
		fail("Not yet implemented");
	}

	public void testDeleteByQuery() {
		fail("Not yet implemented");
	}

	public void testFind() {
		fail("Not yet implemented");
	}

	public void testFindById() {
		fail("Not yet implemented");
	}

	public void testRead() {
		fail("Not yet implemented");
	}

	public void testAddManage() {
		fail("Not yet implemented");
	}

	public void testFindManage() {
		fail("Not yet implemented");
	}

	public void testDeleteManage() {
		fail("Not yet implemented");
	}

}
