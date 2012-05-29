package in.nic.cmf.security.owasp;

import junit.framework.Assert;

import org.junit.Test;
import org.owasp.esapi.filters.SecurityWrapperRequest;
import org.springframework.mock.web.MockHttpServletRequest;

public class FailuretoRestrictURLAccessTest {
	FailuretoRestrictURLAccess furlobj = new FailuretoRestrictURLAccess();

    @Test
    public void testIsAuthorizedForURL() {
        MockHttpServletRequest request  = new MockHttpServletRequest();        
        request.addParameter("username","raja");
        request.addParameter("pasword","jarababa");
        /*request.addParameter("pas","rajaking");
        request.addParameter("user","jara"); */
        SecurityWrapperRequest safeRequest = new SecurityWrapperRequest(request);
        System.out.println("what is the safeRequest url::::"+safeRequest);
       // boolean status = furlobj.isAuthorizedForURL(safeRequest);
        boolean status = furlobj.isAuthorizedForURL(safeRequest);
        System.out.println("Status Checking====" +status);   
        Assert.assertTrue("Value is true", status);
             
    }
    
}
