package in.nic.cmf.security.owasp;


import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.owasp.esapi.errors.AccessControlException;
import org.springframework.mock.web.MockHttpServletRequest;

public class UnvalidatedRedirectsAndForwardsTest {

	@Test
	public void testSendSafeRedirect() throws IOException {
		System.out.println("testSendSafeRedirect..........start");

		MockHttpServletResponse response = new MockHttpServletResponse();
		UnvalidatedRedirectsAndForwards safeResponse = new UnvalidatedRedirectsAndForwards(
				response);
		try {
			safeResponse.sendSafeRedirect("/test1/abcdefg");
			safeResponse.sendSafeRedirect("/test2/1234567");
			safeResponse.sendSafeRedirect("http://www.aspectsecurity.com");
			safeResponse.sendSafeRedirect("/ridiculous");
		} catch (IOException e) {
//			fail();
		} catch (AccessControlException e) {
			e.printStackTrace();
//			fail();
		}
		System.out.println("testSendSafeRedirect..........end");
	}
	
	@Test
	public void testSendSafeForward() throws IOException, ServletException {
		System.out.println("testSendSafeRedirect..........start");

		MockHttpServletResponse response = new MockHttpServletResponse();
		MockHttpServletRequest request = new MockHttpServletRequest();
		//UnvalidatedRedirectsAndForwards safeResponse = new UnvalidatedRedirectsAndForwards(request, response, "/test1/abcdefg");
		try {
			UnvalidatedRedirectsAndForwards.sendSafeForward(request,response,"/test1/abcdefg");
			//UnvalidatedRedirectsAndForwards.sendSafeRedirect(request,response,"/test2/1234567");
			//UnvalidatedRedirectsAndForwards.sendSafeRedirect(request,response,"http://www.aspectsecurity.com");
			//UnvalidatedRedirectsAndForwards.sendSafeRedirect(request,response,"/ridiculous");
		} catch (IOException e) {
		} catch (AccessControlException e) {
			e.printStackTrace();
		}
		System.out.println("testSendSafeRedirect..........end");
	}

}
