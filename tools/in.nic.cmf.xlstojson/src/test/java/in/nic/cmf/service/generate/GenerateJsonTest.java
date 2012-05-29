package in.nic.cmf.service.generate;

import junit.framework.*;

/**
 * The class <code>GenerateJsonTest</code> contains tests for the class <code>{@link GenerateJson}</code>.
 */
public class GenerateJsonTest extends TestCase {
	/**
	 * Run the GenerateJson() constructor test.
	 */
	public void testGenerateJson_1()
		throws Exception {
		GenerateJson result = new GenerateJson();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void main(String[]) method test.
	 * @throws Exception
	 */
	public void testMain_1()
		throws Exception {
		try {
			String[] args = new String[] {};

			GenerateJson.main(args);

			fail("The exception java.lang.SecurityException should have been thrown.");
		} catch (java.lang.SecurityException exception) {
		}
	}

	/**
	 * Run the void main(String[]) method test.
	 * @throws Exception
	 */
	public void testMain_2()
		throws Exception {
		try {
			String[] args = new String[] {};

			GenerateJson.main(args);

			fail("The exception java.lang.SecurityException should have been thrown.");
		} catch (java.lang.SecurityException exception) {
			// The test succeeded by throwing the expected exception
		}
	}

	/**
	 * Run the void main(String[]) method test.
	 * @throws Exception
	 */
	public void testMain_3()
		throws Exception {
		try {
			String[] args = new String[] {};

			GenerateJson.main(args);

			fail("The exception java.lang.SecurityException should have been thrown.");
		} catch (java.lang.SecurityException exception) {
			exception.printStackTrace();
		}
	}

 
	/**
	 * Launch the test.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			// Run all of the tests
			junit.textui.TestRunner.run(GenerateJsonTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new GenerateJsonTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}