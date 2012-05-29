package in.nic.cmf.service.generate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import junit.framework.*;

/**
 * The class <code>GenerateTest</code> contains tests for the class <code>{@link Generate}</code>.
 */
public class GenerateTest extends TestCase {
	/**
	 * Run the Generate() constructor test.
	 * @throws Exception
	 */
	public void testGenerate_1()
		throws Exception {

		Generate result = new Generate();

		assertNotNull(result);
	}

	/**
	 * Run the Generate() constructor test.
	 * @throws Exception
	 */
	public void testGenerate_2()
		throws Exception {

		Generate result = new Generate();

		assertNotNull(result);
	}

	/**
	 * Run the Generate() constructor test.
	 * @throws Exception
	 * @generatedBy CodePro at 25/8/11 11:47 AM
	 */
	public void testGenerate_3()
		throws Exception {

		Generate result = new Generate();

		assertNotNull(result);
	}

	/**
	 * Run the Generate() constructor test.
	 * @throws Exception
	 */
	public void testGenerate_4()
		throws Exception {

		Generate result = new Generate();

		assertNotNull(result);
	}

	/**
	 * Run the Generate(String,String) constructor test.
	 * @throws Exception
	 */
	public void testGenerate_5()
		throws Exception {
		try {
			String sourcePath = "";
			String destinationPath = "";

			Generate result = new Generate(sourcePath, destinationPath);

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
           exception.printStackTrace();
		}
	}

	/**
	 * Run the Generate(String,String) constructor test.
	 * @throws Exception
	 */
	public void testGenerate_6()
		throws Exception {
		try {
			String sourcePath = "";
			String destinationPath = "";

			Generate result = new Generate(sourcePath, destinationPath);

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
			}
	}

	/**
	 * Run the Generate(String,String) constructor test.
	 * @throws Exception
	 */
	public void testGenerate_7()
		throws Exception {
		try {
			String sourcePath = "";
			String destinationPath = "";

			Generate result = new Generate(sourcePath, destinationPath);

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the Generate(String,String) constructor test.
	 * @throws Exception
	 */
	public void testGenerate_8()
		throws Exception {
		try {
			String sourcePath = "";
			String destinationPath = "";

			Generate result = new Generate(sourcePath, destinationPath);

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void Generate(HSSFWorkbook) method test.
	 * @throws Exception
	 */
	public void testGenerate_9()
		throws Exception {
		Generate fixture = new Generate("", "");
		fixture.interfaceList = new ArrayList();
		fixture.classDetails = new ArrayList();
		fixture.classList = new ArrayList();
		fixture.interfaceDetails = new ArrayList();
		HSSFWorkbook workBook = new HSSFWorkbook();

		fixture.Generate(workBook);
 
	}

	/**
	 * Run the void Generate(HSSFWorkbook) method test.
	 * @throws Exception
	 */
	public void testGenerate_10()
		throws Exception {
		Generate fixture = new Generate("", "");
		fixture.interfaceList = new ArrayList();
		fixture.classDetails = new ArrayList();
		fixture.classList = new ArrayList();
		fixture.interfaceDetails = new ArrayList();
		HSSFWorkbook workBook = new HSSFWorkbook();

		fixture.Generate(workBook);
 
	}

	/**
	 * Run the void Generate(HSSFWorkbook) method test.
	 * @throws Exception
	 */
	public void testGenerate_11()
		throws Exception {
		Generate fixture = new Generate("", "");
		fixture.interfaceList = new ArrayList();
		fixture.classDetails = new ArrayList();
		fixture.classList = new ArrayList();
		fixture.interfaceDetails = new ArrayList();
		HSSFWorkbook workBook = new HSSFWorkbook();

		fixture.Generate(workBook);
	}

	/**
	 * Run the void Generate(HSSFWorkbook) method test.
	 * @throws Exception
	 */
	public void testGenerate_12()
		throws Exception {
		Generate fixture = new Generate("", "");
		fixture.interfaceList = new ArrayList();
		fixture.classDetails = new ArrayList();
		fixture.classList = new ArrayList();
		fixture.interfaceDetails = new ArrayList();
		HSSFWorkbook workBook = new HSSFWorkbook();

		fixture.Generate(workBook);

	}

	/**
	 * Run the void generate() method test.
	 * @throws Exception
	 */
	public void testGenerate_13()
		throws Exception {
		try {
			Generate fixture = new Generate("", "");
			fixture.interfaceList = new ArrayList();
			fixture.classDetails = new ArrayList();
			fixture.classList = new ArrayList();
			fixture.interfaceDetails = new ArrayList();

			fixture.generate();

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void generate() method test.
	 * @throws Exception
	 */
	public void testGenerate_14()
		throws Exception {
		try {
			Generate fixture = new Generate("", "");
			fixture.interfaceList = new ArrayList();
			fixture.classDetails = new ArrayList();
			fixture.classList = new ArrayList();
			fixture.interfaceDetails = new ArrayList();

			fixture.generate();

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
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
			junit.textui.TestRunner.run(GenerateTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new GenerateTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}