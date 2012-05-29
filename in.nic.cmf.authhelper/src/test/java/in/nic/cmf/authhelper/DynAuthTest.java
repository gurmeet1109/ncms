package in.nic.cmf.authhelper;

import static org.junit.Assert.assertTrue;
import in.nic.cmf.exceptions.GenericException;

import org.junit.Test;

/**
 * The Class DynAuthTest.
 */
public class DynAuthTest {

    /**
     * Test dynamic authentication global role.
     */
    @Test
    public final void dynamicAuthwithGlobalRole() {
        DynamicAuthentication dynauth = new DynamicAuthentication(
                "PortalAdmin", "sify.co.in");
        boolean b = dynauth.autoSignin();
        assertTrue(b);
    }

    /**
     * Test dynamic authentication with domain specific role.
     */
    @Test
    public final void dynamicAuthwithDomainRole() {
        DynamicAuthentication dynauth = new DynamicAuthentication(
                "PortalAdmin", "sify.co.in");
        boolean b = dynauth.autoSignin();
        assertTrue(b);
    }

    /**
     * Test dynamic authentication with user/password.
     */
    @Test
    public final void dynamicAuthwithUser() {
        DynamicAuthentication dynauth = new DynamicAuthentication(
                "domainuiadmin", "5f4dcc3b5aa765d61d8327deb882cf99",
                "sify.co.in");
        boolean b = dynauth.autoSignin();
        assertTrue(b);
    }

    /**
     * Test dynamic authentication user negative mode.
     */
    @Test (expected = GenericException.class)
    public final void dynamicAuthwithUserException() {

        DynamicAuthentication dynauth = new DynamicAuthentication(
                "domainuiadmin", "", "sify.co.in");
        dynauth.autoSignin();

    }
}
