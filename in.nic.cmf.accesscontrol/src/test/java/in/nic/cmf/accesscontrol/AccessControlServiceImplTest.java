/*
 * 
 */
package in.nic.cmf.accesscontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import in.nic.cmf.domain.Article;
import in.nic.cmf.domain.Collections;
import in.nic.cmf.domain.Gallery;
import in.nic.cmf.exceptions.GenericException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * The Class AccessControlServiceTest.
 * @author sunil.
 */
public class AccessControlServiceImplTest {

	/** The acc is reference of AccessControlServiceImpl. */
	AccessControlServiceImpl acc = null;

	/**
	 * Instantiates a new access control service test.
	 */
	public AccessControlServiceImplTest()  {
		try
		{
		acc = AccessControlServiceImpl.getInstance();
		}
		catch (GenericException ge) {
			
			System.out.println(ge.getMessage());
		}
	}

	

	@Test
	public void testFireRules123() throws IOException {
		boolean returnValue = false;
		try {
			Map ac = new HashMap();
			ac.put("action","get");
			ac.put("entity","portfolio");
			ac.put("domain","kavitha.com");
			ac.put("status","");
			ac.put("role","portaladmin");
		//	ac.put("aclstatus",false);
			System.out.println("before:"+ac.get("action")+";"+ac.get("entity")+";"+ac.get("role")+";"+ac.get("domain")+";"+ac.get("aclstatus")+";contentstatus:"+ac.get("status"));
			returnValue = acc.execute(ac);System.out.println(returnValue);
			System.out.println("after:"+ac.get("action")+";"+ac.get("entity")+";"+ac.get("role")+";"+ac.get("domain")+";"+ac.get("aclstatus")+";contentstatus:"+ac.get("status"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(true, returnValue);
	}
	
	
	/**
	 * Test fire get rules.
	 */
	@Test
	public void testFireGetRules() {
		boolean returnValue = false;

		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		try {
			mockrequest
					.setRequestURL("http://124.7.228.200:8080/dataservices/app/nic.in/article/1.xml");
		
		mockrequest.setMethod("GET");
		mockrequest.addParameter("aclrole", "sitebuilder");
		mockrequest.addParameter("entityname", "userprofile");
		returnValue = acc.fireGetRules("nic.in", mockrequest);
		System.out.println("FileGetRules returns:" + returnValue);
		} 
		catch (GenericException e) {
			System.out.println(e.getMessage());
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
	}
		assertEquals(false, returnValue);
	}
	
	/**
	 * Test fire get rules exc.
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@Test
	public void testFireGetRulesExc() throws UnsupportedEncodingException  {
		boolean returnValue = false;

		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		mockrequest
				.setRequestURL("http://124.7.228.200:8080/dataservices/app/nic.in/article/a.xml");
		mockrequest.setMethod("GET");
		mockrequest.addParameter("aclrole", "anonymous");
		try {
			MockHttpServletRequest mockrequest2 = new MockHttpServletRequest();
			mockrequest2
					.setRequestURL("http://124.7.228.200:8080/dataservices/app/nic.in/article/a.xml");
			mockrequest2.setMethod("GET");
			mockrequest2.addParameter("aclrole", "sunil");
			acc = null;
			returnValue = acc.fireGetRules("nic.in", mockrequest2);
		}catch (GenericException e) {
			System.out.println(e.getMessage());
		} 
		catch (Exception e) {
			System.out.println("Sunil returns:" + returnValue);
			assertEquals(false, returnValue);
		}
	}

	/**
	 * Test fire get rules with action.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testFireGetRulesWithAction() throws Exception {
		boolean returnValue = false;
		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		mockrequest.setMethod("GET");
		try {
			returnValue = acc.fireGetRules("nic.in", mockrequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false, returnValue);
	}

	/**
	 * Test fire get rules with all null.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
//	@Test
//	public void testFireGetRulesWithAllNull() throws Exception {
//		boolean returnValue = false;
//		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
//		try {
//			returnValue = acc.fireGetRules("nic.in", mockrequest);
//		} catch (Exception ex) {
//			assertNotNull(ex);
//			throw new in.nic.cmf.exceptions.InternalServerErrorException(
//					"Knowledge base loader failed." + ex.getMessage());
//		}
//		assertEquals(false, returnValue);
//	}

	/**
	 * Test fire get rules with entity.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFireGetRulesWithEntity() throws IOException {
		boolean returnValue = false;
		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		mockrequest.setMethod("GET");
		mockrequest.addParameter("entitytype", "article");

		try {
			returnValue = acc.fireGetRules("nic.in", mockrequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false, returnValue);
	}

	/**
	 * Test fire get rules with role.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFireGetRulesWithRole() throws IOException {
		boolean returnValue = false;
		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		mockrequest.setMethod("GET");
		mockrequest.addParameter("aclrole", "admin");
		mockrequest.addParameter("action", "get");

		try {
			returnValue = acc.fireGetRules("nic.in", mockrequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(true, returnValue);
	}

	/**
	 * Test fire post rules.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFirePostRules() throws IOException {
		boolean returnValue = false;
		
		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		mockrequest.setMethod("POST");
		mockrequest.addParameter("aclrole", "admin");
		mockrequest.addParameter("entitytype", "Article");
		
		List<String> lstEntity = new ArrayList<String>();
		lstEntity.add("Article");
		lstEntity.add("Gallery");
		try {
			returnValue = acc.firePostRules("kavitha.com",lstEntity, mockrequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(false, returnValue);
	}

	/**
	 * Test fire post rules exc.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFirePostRulesExc() throws IOException {
		try {
			acc.firePostRules(null,null, null);
		} catch (Exception e) {
			System.out.println("Sunil Test Case Exception " + e);
			assertNotNull(e);
		}

	}

	/**
	 * Testset access control from request for get.
	 * @throws Exception the exception
	 */
	@Test
	public void testsetAccessControlFromRequestForGet() throws Exception {
		try {
			acc.setAccessControlFromRequestForGet(null, null);
		} catch (Exception e) {
			assertNotNull(e);
		}

	}

	/**
	 * Testset access control from request for get exc.
	 * @throws Exception the exception
	 */
	@Test
	public void testsetAccessControlFromRequestForGetExc() throws Exception {
		try {
			acc.setAccessControlFromRequestForGet(null, "domainName");
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test fire get rules exception.
	 * @throws Exception the exception
	 */
	@Test
	public void testFireGetRulesException() throws Exception {
		try {
			acc.fireGetRules(",,", null);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Testset access control from request for post exc.
	 */
	@Test
	public void testsetAccessControlFromRequestForPostExc() {
		try {
			acc.setAccessControlFromRequestForPost(null,null);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	//to test post for Form (entitytype,request)
	
	/**
	 * Test fire post for form rules.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFirePostForFormRules() throws IOException {
		boolean returnValue = false;
	
		MockHttpServletRequest mockrequest = new MockHttpServletRequest();
		mockrequest.setMethod("POST");
		mockrequest.addParameter("aclrole", "portaladmin");
	
		try {
			returnValue = acc.firePostFormRules("kavitha.com","country", mockrequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("result:"+returnValue);
		assertEquals(true, returnValue);
	}
}
