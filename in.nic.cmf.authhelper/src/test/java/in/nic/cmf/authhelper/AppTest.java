package in.nic.cmf.authhelper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */

    public void aApp() {
        // assertTrue( true );
        DynamicAuthentication dyn = new DynamicAuthentication("sifycoadmin",
                "5f4dcc3b5aa765d61d8327deb882cf99", "sify.co.in");
        System.out.println("Status : " + dyn.autoSignin());

    }

    public void testdynamicApp() {
        // assertTrue( true );
        DynamicAuthentication dyn = new DynamicAuthentication("sifycoadmin",
                "5f4dcc3b5aa765d61d8327deb882cf99", "sify.co.in");
        System.out.println("Status : " + dyn.autoSignin());
    }

    public void testDynamicAuthRole() {
        DynamicAuthentication dynauth = new DynamicAuthentication(
                "PortalAdmin", "sify.co.in");
        dynauth.autoSignin();
    }

}
