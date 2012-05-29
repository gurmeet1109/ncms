/*
 * 
 */
package in.nic.cmf.services.ruleengine;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import in.nic.cmf.domain.ValidationReport;

import org.junit.Test;

/**
 * The Class RuleEngineServiceImplTest.
 * @author sunil.
 * Modified by kavitha on 25th Nov 2011
 */
public class RuleEngineServiceImplTest {

	/** The obj resi. */
	RuleEngineImpl objResi = RuleEngineImpl.getInstance();

	/**
	 * Test execute.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testExecute() throws Exception {
	
		try {
			ValidationReport vldr = new ValidationReport();
			vldr.setMessage(null);
			Object object = null;
			ValidationReport vr = null;
			objResi.execute(object,vr);
		} catch (Exception e) {
			System.out.println("Execute Excep :" + e);
		}

	}

	/**
	 * Test execute exc.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testExecuteExc() throws Exception {
	
		try {
			objResi.execute(null, null);
		} catch (Exception e) {
			System.out.println("Execute Excep :" + e);
		}

	}

	/**
	 * Test read knowlege base.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testReadKnowlegeBase() throws Exception {
		
		try {
			objResi.readKnowlegeBase("initial", new ArrayList<String>());
		} catch (Exception e) {
			System.out.println("testReadKnowlegeBase Excep :" + e);
		}
	}

	/**
	 * Test validate entity.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValidateEntity() throws Exception {
	
		ValidationReport validationReport = new ValidationReport();
		try {
			
			objResi.validateEntity(null, validationReport);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test validate entity exc.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValidateEntityExc() throws Exception {
		
		try {
			objResi.validateEntity("RuleEngineServiceImpl",null);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	/**
	 * Test validate entity returns string.
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValidateEntityReturnsString() throws Exception {
	
		try {
			Object object = new Object();

		//	objResi.validateEntityReturnsString(null, object);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

}
