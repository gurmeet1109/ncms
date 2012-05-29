package in.nic.cmf.security.owasp;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.junit.Test;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.AuthenticationException;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.filters.SecurityWrapperRequest;
import org.springframework.mock.web.MockHttpServletRequest;

public class BrokenAuthenticationSessionManagementTest {
	protected static final String USER = "ESAPIUserSessionKey";
	BrokenAuthenticationSessionManagement bManagement=new BrokenAuthenticationSessionManagement();
	MockHttpServletRequest request = new MockHttpServletRequest();	
	MockHttpServletResponse response  = new MockHttpServletResponse();   
	
	
	@Test
	public void testIsAuthenticated() throws AuthenticationException,EncryptionException {
		
		User user= null;		
		request.addParameter("username","rajaNEwRaja2");
		request.addParameter("password","12345678912345678934");
		bManagement.setCurrentUser(user);
		boolean status = BrokenAuthenticationSessionManagement.isAuthenticated(request);
		assertTrue("your Username & Password is verified", status);
		
	}
	
}
