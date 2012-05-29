package in.nic.cmf.service.generate;

import java.util.Map;
import net.sf.json.JSONException;
import junit.framework.*;

/**
 * The class <code>Json2JavaTest</code> contains tests for the class <code>{@link Json2Java}</code>.
 */
public class Json2JavaTest extends TestCase {
	/**
	 * Run the Map<String, Object> getMap(String) method test.
	 * @throws Exception
	 */
	public void testGetMap_1()
		throws Exception {
		try {
			Json2Java fixture = new Json2Java();
			String jsonResponse = "";

			Map<String, Object> result = fixture.getMap(jsonResponse);

			fail("The exception java.lang.Exception should have been thrown.");
		} catch (java.lang.Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the Map<String, Object> getMap(String) method test.
	 * @throws Exception
	 */
	public void testGetMap_2()
		throws Exception {
		try {
			Json2Java fixture = new Json2Java();
			String jsonResponse = "{";

			Map<String, Object> result = fixture.getMap(jsonResponse);

			fail("The exception net.sf.json.JSONException should have been thrown.");
		} catch (net.sf.json.JSONException exception) {
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
			junit.textui.TestRunner.run(Json2JavaTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new Json2JavaTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}