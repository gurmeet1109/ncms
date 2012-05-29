//package in.nic.cmf.accesscontrol;
//
//import in.nic.cmf.domain.Article;
//import in.nic.cmf.domain.Collections;
//import in.nic.cmf.domain.Gallery;
//
//import java.io.IOException;
//
//import junit.framework.TestCase;
//
//// TODO: Auto-generated Javadoc
///**
// * The Class AccessControlServiceTest.
// */
//public class AccessControlServiceTest extends TestCase {
//
//	/** The acc. */
//	AccessControlServiceImpl acc = null;
//
//	/**
//	 * Instantiates a new access control service test.
//	 *
//	 * @throws Exception the exception
//	 */
//	public AccessControlServiceTest() throws Exception{
//		acc = new AccessControlServiceImpl();
//	}
//
//	/**
//	 * Test fire get rules.
//	 *
//	 * @throws Exception the exception
//	 */
//	public void testFireGetRules() throws Exception {
//		boolean returnValue = false;
//
//		MockHttpServletRequest  mockrequest = new MockHttpServletRequest();
//		mockrequest.setRequestURL("http://124.7.228.200:8080/dataservices/app/nic.in/article/1.xml");
//		mockrequest.setMethod("GET");
//		//mockrequest.addParameter("api_key", apiKey.generateAPIKey("thamaiyanthi", "124.7.228.200"));
//		//mockrequest.addParameter("username", "thamaiyanthi");
//		mockrequest.addParameter("aclrole", "portaladmin");
//		//mockrequest.addParameter("entitytype","article");
//		//mockrequest.addParameter("action","get");
//		returnValue = acc.fireGetRules("nic.in",mockrequest);
//		System.out.println("FileGetRules returns:"+returnValue);
//		assertEquals(false,returnValue);
//	}
//
//	//role=null,entity=null,action =GET
//	/**
//	 * Test fire get rules with action.
//	 *
//	 * @throws Exception the exception
//	 */
//	public void testFireGetRulesWithAction() throws Exception {
//		boolean returnValue = false;
//		MockHttpServletRequest  mockrequest = new MockHttpServletRequest();
//		mockrequest.setMethod("GET");
//		try {
//			returnValue = acc.fireGetRules("nic.in",mockrequest);
//			System.out.println("testcase try : "+returnValue);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("FileGetRules returns:"+returnValue);
//		assertEquals(true,returnValue);
//	}
//
//	//role=null, entity = null, action = null
//	/**
//	 * Test fire get rules with all null.
//	 *
//	 * @throws Exception the exception
//	 */
//	public void testFireGetRulesWithAllNull() throws Exception {
//		boolean returnValue = false;
//		MockHttpServletRequest  mockrequest = new MockHttpServletRequest();
//		try {
//			returnValue = acc.fireGetRules("nic.in",mockrequest);
//			System.out.println("testcase try : "+returnValue);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new in.nic.cmf.exceptions.InternalServerErrorException("Knowledge base loader failed."+ex.getMessage());
//		}
//		System.out.println("FileGetRules returns:"+returnValue);
//		assertEquals(true,returnValue);
//	}
//
//	//role = null, entity=article, action=get
//	/**
//	 * Test fire get rules with entity.
//	 *
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 */
//	public void testFireGetRulesWithEntity() throws IOException {
//		boolean returnValue = false;
//		MockHttpServletRequest  mockrequest = new MockHttpServletRequest();
//		mockrequest.setMethod("GET");
//		mockrequest.addParameter("entitytype","article");
//
//		try {
//			returnValue = acc.fireGetRules("nic.in",mockrequest);
//			System.out.println("testcase try : "+returnValue);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("FileGetRules returns:"+returnValue);
//		assertEquals(true,returnValue);
//	}
//
//	//role=admin and entity=null, action=get
//	/**
//	 * Test fire get rules with role.
//	 *
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 */
//	public void testFireGetRulesWithRole() throws IOException {
//		boolean returnValue = false;
//		MockHttpServletRequest  mockrequest = new MockHttpServletRequest();
//		mockrequest.setMethod("GET");
//		mockrequest.addParameter("aclrole","admin");
//		mockrequest.addParameter("action","get");
//
//		try {
//			returnValue = acc.fireGetRules("nic.in",mockrequest);
//			System.out.println("testcase try : "+returnValue);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("FileGetRules returns:"+returnValue);
//		assertEquals(true,returnValue);
//	}
//
//	/**
//	 * Test fire post rules.
//	 *
//	 * @throws IOException Signals that an I/O exception has occurred.
//	 */
//	public void testFirePostRules() throws IOException {
//		boolean returnValue=false;
//		Collections collection = new Collections();
//		Article article = new Article();
//		article.setEntityType("Article");
//		article.setId("thamai");
//		Gallery gall = new Gallery();
//		gall.setEntityType("Gallery");
//		article.setId("thamaiy");
//		System.out.println("enter main1");
//		collection.add(article);
//		collection.add(gall);
//		MockHttpServletRequest  mockrequest = new MockHttpServletRequest();
//		mockrequest.setMethod("POST");
//		mockrequest.addParameter("aclrole", "admin");
//		mockrequest.addParameter("entitytype","Article");
//		//mockrequest.addParameter("action","POST");
//		try {
//			returnValue = acc.firePostRules(collection, mockrequest);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Test Case Exception"+e.getMessage());
//		}
//		System.out.println("FilePostRules returns:"+returnValue);
//		assertEquals(true,returnValue);
//	}
//
//}
