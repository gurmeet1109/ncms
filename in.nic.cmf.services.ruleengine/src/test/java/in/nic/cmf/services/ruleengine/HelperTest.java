/*
 * 
 
package in.nic.cmf.services.ruleengine;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

*//**
 * The Class HelperTest.
 * @author sunil.
 *//*
public class HelperTest {

	*//**
	 * Test error details.
	 *//*
	@Test
	public void testErrorDetails() {
		Helper.errorDetails("classname", "methodname", "details");
	}

	*//**
	 * Test get collection.
	 * @throws JAXBException the jAXB exception
	 *//*
	@Test
	public void testGetCollection() throws JAXBException {
		try {
			Helper.getCollection("responseBody");
		} catch (Exception e) {
			System.out.println("Get Collection  :" + e);
		}
	}

	*//**
	 * Test get collection exc.
	 * @throws JAXBException the jAXB exception
	 *//*
	@Test
	public void testGetCollectionExc() throws JAXBException {
		try {
			Helper.getCollection(null);
		} catch (Exception e) {
			System.out.println("Get Collection Exc :" + e);
		}
	}

	*//**
	 * Test get value.
	 *//*
	@Test
	public void testGetValue() {
		try {
			Helper.getValue(null);
		} catch (Exception e) {
			System.out.println("Get Value :" + e);
			assertNotNull(e);
		}

	}

	*//**
	 * Test get value exc.
	 *//*
	@Test
	public void testGetValueExc() {
		try {
			Helper.getValue("key");
		} catch (Exception e) {
			System.out.println("Get Value Ex :" + e);
			assertNotNull(e);
		}

	}

	*//**
	 * Test is empty.
	 *//*
	@Test
	public void testIsEmpty() {
		try {
			Helper.isEmpty(null);
		} catch (Exception e) {
			System.out.println("Is Empty Exc :" + e);
			assertNotNull(e);
		}

	}

	*//**
	 * Test is empty not.
	 *//*
	@Test
	public void testIsEmptyNot() {
		try {
			Helper.isEmpty("test");
		} catch (Exception e) {
			System.out.println("Not Empty Exc :" + e);
			assertNotNull(e);
		}

	}

}
*/