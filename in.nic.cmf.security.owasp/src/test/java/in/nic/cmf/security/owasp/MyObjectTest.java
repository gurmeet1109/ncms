package in.nic.cmf.security.owasp;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>MyObjectTest</code> contains tests for the class <code>{@link MyObject}</code>.
 *
 
 * @author ajay
 */
public class MyObjectTest {
	/**
	 * Run the MyObject() constructor test.
	 *
	 
	 */
	@Test
	public void testMyObject_1()
		throws Exception {
		MyObject result = new MyObject();
		assertNotNull(result);
		
	}

	/**
	 * Run the String getId() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testGetId_1()
		throws Exception {
		MyObject fixture = new MyObject();
		fixture.setId("");
		fixture.setIndirectReference("");

		String result = fixture.getId();

		
		assertEquals("", result);
	}

	/**
	 * Run the String getIndirectReference() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testGetIndirectReference_1()
		throws Exception {
		MyObject fixture = new MyObject();
		fixture.setId("");
		fixture.setIndirectReference("");

		String result = fixture.getIndirectReference();

		
		assertEquals("", result);
	}

	/**
	 * Run the void setId(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testSetId_1()
		throws Exception {
		MyObject fixture = new MyObject();
		fixture.setId("");
		fixture.setIndirectReference("");
		String id = "";

		fixture.setId(id);

		
	}

	/**
	 * Run the void setIndirectReference(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testSetIndirectReference_1()
		throws Exception {
		MyObject fixture = new MyObject();
		fixture.setId("");
		fixture.setIndirectReference("");
		String indirectReference = "";

		fixture.setIndirectReference(indirectReference);

		
	}


	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(MyObjectTest.class);
	}
}