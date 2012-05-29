package in.nic.cmf.security.owasp;

import java.util.Collection;
import org.jsoup.select.Elements;
import org.junit.*;
import static org.junit.Assert.*;
import org.owasp.esapi.AccessReferenceMap;
import org.owasp.esapi.reference.IntegerAccessReferenceMap;

/**
 * The class <code>InsecureDirectObjectReferencesTest</code> contains tests for the class <code>{@link InsecureDirectObjectReferences}</code>.
 *
 
 * @author ajay
 * @version $Revision: 1.0 $
 */
public class InsecureDirectObjectReferencesTest {
	/**
	 * Run the InsecureDirectObjectReferences() constructor test.
	 *
	 
	 */
	
	@Test
	public void testInsecureInDirectObjectReferences()
		throws Exception {
		InsecureDirectObjectReferences result = new InsecureDirectObjectReferences();
		String expected  = result.indirectReference();		
		assertEquals(expected, "raja");
		
	}
	

	/**
	 * Run the void indirectReference() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
//	@Test
//	public void testIndirectReference_1()
//		throws Exception {
//		InsecureDirectObjectReferences fixture = new InsecureDirectObjectReferences();
//		fixture.coll = new Elements();
//		MyObject myObject = new MyObject();
//		myObject.setId("");
//		fixture.obj = myObject;
//		fixture.map = new IntegerAccessReferenceMap();
//
//		fixture.indirectReference();
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.ClassCastException: in.nic.cmf.security.owasp.MyObject cannot be cast to org.jsoup.nodes.Element
//		//       at org.jsoup.select.Elements.add(Elements.java:12)
//		//       at in.nic.cmf.security.owasp.InsecureDirectObjectReferences.indirectReference(InsecureDirectObjectReferences.java:34)
//	}

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
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(InsecureDirectObjectReferencesTest.class);
	}
}