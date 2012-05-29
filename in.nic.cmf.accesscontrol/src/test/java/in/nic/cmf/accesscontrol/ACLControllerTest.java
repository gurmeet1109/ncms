package in.nic.cmf.accesscontrol;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ACLControllerTest {

    private String domain = "sify.com";

    private Map<String, String> getUserContext() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "kavitha");
        return map;
    }

    @Test
    public void testAddStringStringHashMapOfStringHashMapOfStringObject() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        inputmap.put(
                "content",
                "<Collections><Category><Id>lljqSkdacb222</Id><EntityType>PmsMedia</EntityType><CategoryName>testcategory</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version></Category><Category><Id>lljqSkdacb222</Id><EntityType>Category</EntityType><CategoryName>testcategory</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate>2011-11-09T16:54:02Z</CreatedDate><LastModifiedDate>2011-11-09T16:54:02Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version></Category></Collections>");
        // inputmap.put("queryParams", "q=&entitytype=category");
        parameters.put("input", inputmap);
        ACLController ACLController = new ACLController();
        Map result = ACLController.add("bala.in", "category", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testDeleteByID() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        Map queryParams = new HashMap();
        inputmap.put("userContext", map);
        inputmap.put("readknowledge", "true");
        inputmap.put("queryParams", queryParams);
        queryParams.put("Entitytype", "category");
        queryParams.put("Site", domain);
        parameters.put("input", inputmap);
        ACLController ACLController = new ACLController();
        Map result = ACLController.deleteByID(domain, "category",
                "mdtpbLghaebfj", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testDeleteByQuery() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        inputmap.put("queryParams", "q=&entitytype=category");
        parameters.put("input", inputmap);
        ACLController ACLController = new ACLController();
        Map result = ACLController
                .deleteByQuery(domain, "category", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void tet() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        inputmap.put("queryParams", "q=&entitytype=category");
        parameters.put("input", inputmap);
        ACLController ACLController = new ACLController();
        Map result = ACLController
                .deleteByQuery(domain, "category", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testFind() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        parameters.put("input", inputmap);
        // inputmap.put("queryString", "&entitytype=accesscontrollist");
        ACLController ACLController = new ACLController();
        Map result = ACLController.find(domain, "article", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testFindById() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        parameters.put("input", inputmap);
        ACLController ACLController = new ACLController();
        Map result = ACLController.findById(domain, "category",
                "llquIzihhg111", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testRead() {
        Map<String, String> map = getUserContext();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("userContext", map);
        String con = "<Collections><Category><Id>llquIzihhg111</Id><EntityType>Category</EntityType></Category><Category><Id>llquGgaagcfec</Id><EntityType>Category</EntityType></Category><Category><Id>llklh7idjeggi</Id><EntityType>Category</EntityType></Category><Category><Id>llkkWeeiaggcd</Id><EntityType>Category</EntityType></Category><Category><Id>lljqSsedgfdea</Id><EntityType>Category</EntityType></Category><Category><Id>lljqSkdacbcif</Id><EntityType>Category</EntityType></Category><Count>6</Count></Collections>";
        inputmap.put("content", con);
        parameters.put("input", inputmap);
        ACLController ACLController = new ACLController();
        Map result = ACLController.read(domain, "category", parameters);
        // assertEquals(200, Integer.parseInt((String)
        // result.get("responseCode")));
        System.out.println("Result:" + result);
    }

    @Test
    public void testMasterData() {

    	ACLHelper helper = ACLHelper.getInstanceof("bala.in");
    	for(String s : helper.checkDirectory()){
    		System.out.println("..."+s);
    	}
//        ACLHelper aclHelper = ACLHelper.getInstanceof(domain);
        // aclHelper.g
    }

    @Test
    public void testGetbyID() {

        // ACLHelper aclHelper = ACLHelper.getInstance();
        // aclHelper.getAccessControlls();
    }

    @Test
    public void testDire() {
        // ACLHelper aclHelper = ACLHelper.getInstance();
        // aclHelper.checkDirectory();
    }

}
