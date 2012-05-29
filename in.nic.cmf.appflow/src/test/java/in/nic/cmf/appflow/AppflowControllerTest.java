package in.nic.cmf.appflow;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AppflowControllerTest {

    private Map<String, String> getUserContext() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "kavitha");
        return map;
    }
    
    
    @Test
    public void ttet(){
    	Map<String, Object> ss = new HashMap<String,Object>();
    	ss.put("format", "json");		
		System.out.println("after stored:" + ss + "; "+ss.get("format"));
		
		if(ss.containsKey("format")){
			
			System.out.println("entereed");
			
		}
    }

    @Test
    public void testAddStringStringHashMapOfStringHashMapOfStringObject() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        inputmap.put(
                "content",
                "<Collections><Category><Id>lljqSkdacb222</Id><EntityType>Category1</EntityType><CategoryName>testcategory</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>bala.in</Site><Version>1.0</Version></Category></Collections>");
        inputmap.put("queryParams", "q=&entitytype=category");
        parameters.put("input", inputmap);
        AppflowController appflowController = new AppflowController();
        Map result = appflowController
                .add("sify.co.in", "category", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testDeleteByID() {
        Map<String, String> map = getUserContext();
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        parameters.put("input", inputmap);
        AppflowController appflowController = new AppflowController();
        HashMap result = appflowController.deleteByID("sify.co.in", "category",
                "llquIzihhg111", parameters);
        assertEquals(200, Integer.parseInt((String) result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testDeleteByQuery() {
        Map<String, String> map = getUserContext();
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        parameters.put("input", inputmap);
        AppflowController appflowController = new AppflowController();
        HashMap result = appflowController.deleteByQuery("sify.co.in",
                "q=&entitytype=category&id=llquIzihhg111", parameters);
        assertEquals(200, Integer.parseInt((String) result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testFind() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        parameters.put("input", inputmap);
        Map<String, Object> ss = new HashMap<String,Object>();
    	ss.put("format", "json");		
         inputmap.put("queryParams",ss);
        AppflowController appflowController = new AppflowController();
        Map result = appflowController.find("bala.in", "category",
                parameters);
       // assertEquals(200, Integer.parseInt((String) result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testFindById() {
        Map<String, String> map = getUserContext();
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        parameters.put("input", inputmap);
        AppflowController appflowController = new AppflowController();
        HashMap result = appflowController.findById("sify.co.in", "category",
                "llquIzihhg111", parameters);
        assertEquals(200, Integer.parseInt((String) result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testRead() {
        Map<String, String> map = getUserContext();
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        String con = "<Collections><Category><Id>llquIzihhg111</Id><EntityType>Category</EntityType></Category><Category><Id>llquGgaagcfec</Id><EntityType>Category</EntityType></Category><Category><Id>llklh7idjeggi</Id><EntityType>Category</EntityType></Category><Category><Id>llkkWeeiaggcd</Id><EntityType>Category</EntityType></Category><Category><Id>lljqSsedgfdea</Id><EntityType>Category</EntityType></Category><Category><Id>lljqSkdacbcif</Id><EntityType>Category</EntityType></Category><Count>6</Count></Collections>";
        inputmap.put("content", con);
        parameters.put("input", inputmap);
        AppflowController appflowController = new AppflowController();
        HashMap result = appflowController.read("sify.co.in", "category",
                parameters);
        assertEquals(200, Integer.parseInt((String) result.get("responseCode")));
        System.out.println("Result:" + result);
    }

}
