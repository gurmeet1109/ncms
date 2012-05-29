package in.nic.cmf.security.owasp;

import javax.servlet.http.HttpServletRequest;
import org.jboss.resteasy.plugins.server.tjws.PatchedHttpServletRequest;
import org.jboss.resteasy.util.HttpServletRequestDelegate;
import org.junit.*;
import static org.junit.Assert.*;
import org.owasp.esapi.errors.AccessControlException;
import org.owasp.esapi.errors.AuthenticationException;
import org.owasp.esapi.filters.SecurityWrapperRequest;
import org.owasp.esapi.waf.internal.InterceptingHTTPServletRequest;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * The class <code>InsufficientTransportLayerProtectionTest</code> contains tests for the class <code>{@link InsufficientTransportLayerProtection}</code>.
 *
 
 * @author ajay
 * @version $Revision: 1.0 $
 */
public class InsufficientTransportLayerProtectionTest {
	/**
	 * Run the InsufficientTransportLayerProtection() constructor test.
	 *
	 
	 */
	@Test
	public void testInsufficientTransportLayerProtection_1()
		throws Exception {
		InsufficientTransportLayerProtection result = new InsufficientTransportLayerProtection();
		assertNotNull(result);
		
	}

	/**
	 * Run the void assertSecureChannel() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureChannel_1()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();

		fixture.assertSecureChannel();

		
	}

	/**
	 * Run the void assertSecureChannel() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureChannel_2()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();

		fixture.assertSecureChannel();

		
	}

	/**
	 * Run the void assertSecureChannel(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureChannel_3()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = null;

		fixture.assertSecureChannel(request);

		
	}

	/**
	 * Run the void assertSecureChannel(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureChannel_4()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = new HttpServletRequestDelegate(new SecurityWrapperRequest(new InterceptingHTTPServletRequest(new HttpRequestWrapper(new PatchedHttpServletRequest(new CrossSiteScripting.RequestWrapper(new MockHttpServletRequest()), "")))));

		fixture.assertSecureChannel(request);

		
	}

	/**
	 * Run the void assertSecureChannel(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureChannel_5()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = new HttpServletRequestDelegate(new SecurityWrapperRequest(new InterceptingHTTPServletRequest(new HttpRequestWrapper(new PatchedHttpServletRequest(new CrossSiteScripting.RequestWrapper(new MockHttpServletRequest()), "")))));

		fixture.assertSecureChannel(request);

		
	}

	/**
	 * Run the void assertSecureChannel(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureChannel_6()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = new HttpServletRequestDelegate(new SecurityWrapperRequest(new InterceptingHTTPServletRequest(new HttpRequestWrapper(new PatchedHttpServletRequest(new CrossSiteScripting.RequestWrapper(new MockHttpServletRequest()), "")))));

		fixture.assertSecureChannel(request);

		
	}

	/**
	 * Run the void assertSecureRequest() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureRequest_1()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();

		fixture.assertSecureRequest();

		
	}

	/**
	 * Run the void assertSecureRequest() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureRequest_2()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();

		fixture.assertSecureRequest();

		
	}

	/**
	 * Run the void assertSecureRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureRequest_3()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = new HttpServletRequestDelegate(new SecurityWrapperRequest(new InterceptingHTTPServletRequest(new HttpRequestWrapper(new PatchedHttpServletRequest(new CrossSiteScripting.RequestWrapper(new MockHttpServletRequest()), "")))));

		fixture.assertSecureRequest(request);

		
	}

	/**
	 * Run the void assertSecureRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureRequest_4()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = new HttpServletRequestDelegate(new SecurityWrapperRequest(new InterceptingHTTPServletRequest(new HttpRequestWrapper(new PatchedHttpServletRequest(new CrossSiteScripting.RequestWrapper(new MockHttpServletRequest()), "")))));

		fixture.assertSecureRequest(request);

		
	}

	/**
	 * Run the void assertSecureRequest(HttpServletRequest) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AccessControlException.class)
	public void testAssertSecureRequest_5()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();
		HttpServletRequest request = new HttpServletRequestDelegate(new SecurityWrapperRequest(new InterceptingHTTPServletRequest(new HttpRequestWrapper(new PatchedHttpServletRequest(new CrossSiteScripting.RequestWrapper(new MockHttpServletRequest()), "")))));

		fixture.assertSecureRequest(request);

		
	}

	/**
	 * Run the HttpServletRequest getCurrentRequest() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testGetCurrentRequest_1()
		throws Exception {
		InsufficientTransportLayerProtection fixture = new InsufficientTransportLayerProtection();

		HttpServletRequest result = fixture.getCurrentRequest();

		assertEquals(null, result);
	}

	/**
	 * Run the boolean secureTransportLayerProtection() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AuthenticationException.class)
	public void testSecureTransportLayerProtection_1()
		throws Exception {

		boolean result = InsufficientTransportLayerProtection.secureTransportLayerProtection();

		
		assertTrue(result);
	}

	/**
	 * Run the boolean secureTransportLayerProtection() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test(expected = org.owasp.esapi.errors.AuthenticationException.class)
	public void testSecureTransportLayerProtection_2()
		throws Exception {

		boolean result = InsufficientTransportLayerProtection.secureTransportLayerProtection();

		
		assertTrue(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 
	 */
	@Before
	public void setUp()
		throws Exception {
		
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 
	 */
	@After
	public void tearDown()
		throws Exception {
		
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(InsufficientTransportLayerProtectionTest.class);
	}
}