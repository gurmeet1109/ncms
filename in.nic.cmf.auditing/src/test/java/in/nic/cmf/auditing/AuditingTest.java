package in.nic.cmf.auditing;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AuditingTest extends TestCase {
    /**
     * Create the test case
     * @param testName
     *            name of the test case
     */

    public void testLog1() {
        AuditingController impl = new AuditingController();
        System.out.println("Enter");
        Map<String, Map<String, Object>> queryParams = new HashMap<String, Map<String, Object>>();
        System.out.println(queryParams);
        Map<String, Object> usercontextMap = new HashMap<String, Object>();
        HashMap<String, Object> dataQueryMap = new HashMap<String, Object>();
        System.out.println(usercontextMap);
        usercontextMap.put("aclrole", "portaladmin");
        usercontextMap.put("api_key", "portaladmin");
        usercontextMap.put("requester", "portaladmin");
        usercontextMap.put("authusername", "portaladmin");
        queryParams.put("usercontext", usercontextMap);
        dataQueryMap.put("querystring", "q=&entitytype=article");
        queryParams.put("data", dataQueryMap);

        impl.find("nivi.in", "article", queryParams);
    }

}
