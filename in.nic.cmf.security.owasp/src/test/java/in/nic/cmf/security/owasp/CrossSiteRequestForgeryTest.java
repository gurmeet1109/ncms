package in.nic.cmf.security.owasp;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import in.nic.cmf.domain.Article;
import in.nic.cmf.domain.Collections;

import org.jboss.resteasy.plugins.server.tjws.PatchedHttpServletRequest;
import org.jboss.resteasy.util.HttpServletRequestDelegate;
import org.junit.Test;
import org.owasp.esapi.errors.EncodingException;
import org.owasp.esapi.filters.SecurityWrapperRequest;
import org.owasp.esapi.waf.internal.InterceptingHTTPServletRequest;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * The class <code>CrossSiteRequestForgeryTest</code> contains tests for the
 * class <code>{@link CrossSiteRequestForgery}</code>.
 * 
 * @author Raja R
 */
public class CrossSiteRequestForgeryTest {
	/**
	 * Run the CrossSiteRequestForgery() constructor test.
	 * 
	 * 
	 *//*
	@Test
	public void testCrossSiteRequestForgery_1() throws Exception {
		CrossSiteRequestForgery result = new CrossSiteRequestForgery();
		assertNotNull(result);
		
	}

	*//**
	 * Run the String addCSRFToken(String) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	@Test
	public void testAddCSRFToken_1() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		String href = "http://rajabank.com/app/transferFunds?amount=1500&destinationAccount=4673243243";

		String unexpectedresult = fixture.addCSRFToken(href);
		System.out.println("what is the testAddCSRFToken_1::::"+unexpectedresult);
		
		assertNotSame("http://rajabank.com/app/transferFunds?amount=1500&destinationAccount=4673243243", unexpectedresult);
	}

	/**
	 * Run the String addCSRFToken(String) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test
	public void testAddCSRFToken_2() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		String href = "";

		String result = fixture.addCSRFToken(href);

		
		assertEquals("", result);
	}

	*//**
	 * Run the String addCSRFToken(String) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test
	public void testAddCSRFToken_3() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		String href = "";

		String result = fixture.addCSRFToken(href);

		
		assertEquals("", result);
	}

	*//**
	 * Run the String getCSRFToken() method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test
	public void testGetCSRFToken_1() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();

		String result = fixture.getCSRFToken();

		
		assertEquals("", result);
	}

	*//**
	 * Run the String getCSRFToken() method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test
	public void testGetCSRFToken_2() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();

		String result = fixture.getCSRFToken();

		
		assertEquals("", result);
	}

	*//**
	 * Run the String getSafeCSRFValue(String) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test
	public void testGetSafeCSRFValue_1() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		String href = "";

		String result = fixture.getSafeCSRFValue(href);

		
		assertEquals(null, result);
	}

	*//**
	 * Run the String resetCSRFToken() method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	// @Test
	// public void testResetCSRFToken_1()
	// throws Exception {
	// CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
	//
	// String result = fixture.resetCSRFToken();
	//
	// 
	// assertEquals("PmVcn0zt", result);
	// }

	*//**
	 * Run the void verifyCSRFToken(HttpServletRequest) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test(expected = org.owasp.esapi.errors.IntrusionException.class)
	public void testVerifyCSRFToken_1() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		HttpServletRequest request = new HttpServletRequestDelegate(
				new SecurityWrapperRequest(new InterceptingHTTPServletRequest(
						new HttpRequestWrapper(new PatchedHttpServletRequest(
								new CrossSiteScripting.RequestWrapper(
										new MockHttpServletRequest()), "")))));

		fixture.verifyCSRFToken(request);

		
	}

	*//**
	 * Run the void verifyCSRFToken(HttpServletRequest) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test(expected = org.owasp.esapi.errors.IntrusionException.class)
	public void testVerifyCSRFToken_2() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		HttpServletRequest request = new HttpServletRequestDelegate(
				new SecurityWrapperRequest(new InterceptingHTTPServletRequest(
						new HttpRequestWrapper(new PatchedHttpServletRequest(
								new CrossSiteScripting.RequestWrapper(
										new MockHttpServletRequest()), "")))));

		fixture.verifyCSRFToken(request);

		
	}

	*//**
	 * Run the void verifyCSRFToken(HttpServletRequest) method test.
	 * 
	 * @throws Exception
	 * 
	 * 
	 *//*
	@Test(expected = org.owasp.esapi.errors.IntrusionException.class)
	public void testVerifyCSRFToken_3() throws Exception {
		CrossSiteRequestForgery fixture = new CrossSiteRequestForgery();
		HttpServletRequest request = new HttpServletRequestDelegate(
				new SecurityWrapperRequest(new InterceptingHTTPServletRequest(
						new HttpRequestWrapper(new PatchedHttpServletRequest(
								new CrossSiteScripting.RequestWrapper(
										new MockHttpServletRequest()), "")))));

		fixture.verifyCSRFToken(request);

		
	}

	*//**
	 * Launch the test.
	 * 
	 * @param args
	 *            the command line arguments
	 * 
	 * 
	 *//*
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(CrossSiteRequestForgeryTest.class);
	}*/
}