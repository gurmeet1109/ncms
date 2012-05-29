package in.nic.cmf.service.generate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import junit.framework.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 

/**
 * The class <code>JsonToCSVTest</code> contains tests for the class <code>{@link JsonToCSV}</code>.
 */
public class JsonToCSVTest extends TestCase {
	/**
	 * Run the JsonToCSV(String) constructor test.
	 * @throws Exception
	 */
	public void testJsonToCSV_1()
		throws Exception {
		try {
			String jsonFileName = "";

			JsonToCSV result = new JsonToCSV(jsonFileName);

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the JsonToCSV(String) constructor test.
	 * @throws Exception
	 */
	public void testJsonToCSV_2()
		throws Exception {
		try {
			String jsonFileName = "";

			JsonToCSV result = new JsonToCSV(jsonFileName);

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void export() method test.
	 * @throws Exception
	 */
	public void testExport_1()
		throws Exception {
		try {
			JsonToCSV jsonToCSV = new JsonToCSV("");
			jsonToCSV.setJsonString("");
			jsonToCSV.setSplitter("");
			jsonToCSV.rowindexForClassDetails = 1;
			jsonToCSV.wb = new HSSFWorkbook();
			jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
			jsonToCSV.sheet3 = null;
			jsonToCSV.rowindexForInterfaceDetails = 1;
			jsonToCSV.sheet4 = null;
			jsonToCSV.sheet2 = null;
			jsonToCSV.InterFaceListHeader = new ArrayList<String>();
			jsonToCSV.sheet1 = null;

			jsonToCSV.export();

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void export() method test.
	 * @throws Exception
	 */
	public void testExport_2()
		throws Exception {
		try {
			JsonToCSV jsonToCSV = new JsonToCSV("");
			jsonToCSV.setJsonString("");
			jsonToCSV.setSplitter("");
			jsonToCSV.rowindexForClassDetails = 1;
			jsonToCSV.wb = new HSSFWorkbook();
			jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
			jsonToCSV.sheet3 = null;
			jsonToCSV.rowindexForInterfaceDetails = 1;
			jsonToCSV.sheet4 = null;
			jsonToCSV.sheet2 = null;
			jsonToCSV.InterFaceListHeader = new ArrayList<String>();
			jsonToCSV.sheet1 = null;

			jsonToCSV.export();

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void export() method test.
	 * @throws Exception
	 */
	public void testExport_3()
		throws Exception {
		try {
			JsonToCSV jsonToCSV = new JsonToCSV("");
			jsonToCSV.setJsonString("");
			jsonToCSV.setSplitter("");
			jsonToCSV.rowindexForClassDetails = 1;
			jsonToCSV.wb = new HSSFWorkbook();
			jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
			jsonToCSV.sheet3 = null;
			jsonToCSV.rowindexForInterfaceDetails = 1;
			jsonToCSV.sheet4 = null;
			jsonToCSV.sheet2 = null;
			jsonToCSV.InterFaceListHeader = new ArrayList<String>();
			jsonToCSV.sheet1 = null;

			jsonToCSV.export();

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void export() method test.
	 * @throws Exception
	 */
	public void testExport_4()
		throws Exception {
		try {
			JsonToCSV jsonToCSV = new JsonToCSV("");
			jsonToCSV.setJsonString("");
			jsonToCSV.setSplitter("");
			jsonToCSV.rowindexForClassDetails = 1;
			jsonToCSV.wb = new HSSFWorkbook();
			jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
			jsonToCSV.sheet3 = null;
			jsonToCSV.rowindexForInterfaceDetails = 1;
			jsonToCSV.sheet4 = null;
			jsonToCSV.sheet2 = null;
			jsonToCSV.InterFaceListHeader = new ArrayList<String>();
			jsonToCSV.sheet1 = null;

			jsonToCSV.export();

			fail("The exception java.io.FileNotFoundException should have been thrown.");
		} catch (java.io.FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the String getJsonString() method test.
	 * @throws Exception
	 */
	public void testGetJsonString_1()
		throws Exception {
		JsonToCSV jsonToCSV = new JsonToCSV("");
		jsonToCSV.setJsonString("");
		jsonToCSV.setSplitter("");
		jsonToCSV.rowindexForClassDetails = 1;
		jsonToCSV.wb = new HSSFWorkbook();
		jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
		jsonToCSV.sheet3 = null;
		jsonToCSV.rowindexForInterfaceDetails = 1;
		jsonToCSV.sheet4 = null;
		jsonToCSV.sheet2 = null;
		jsonToCSV.InterFaceListHeader = new ArrayList<String>();
		jsonToCSV.sheet1 = null;

		String result = jsonToCSV.getJsonString();
		assertNotNull(result);
	}

	/**
	 * Run the String getSplitter() method test.
	 * @throws Exception
	 */
	public void testGetSplitter_1()
		throws Exception {
		JsonToCSV jsonToCSV = new JsonToCSV("");
		jsonToCSV.setJsonString("");
		jsonToCSV.setSplitter("");
		jsonToCSV.rowindexForClassDetails = 1;
		jsonToCSV.wb = new HSSFWorkbook();
		jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
		jsonToCSV.sheet3 = null;
		jsonToCSV.rowindexForInterfaceDetails = 1;
		jsonToCSV.sheet4 = null;
		jsonToCSV.sheet2 = null;
		jsonToCSV.InterFaceListHeader = new ArrayList<String>();
		jsonToCSV.sheet1 = null;

		String result = jsonToCSV.getSplitter();

		assertNotNull(result);
	}

	/**
	 * Run the void main(String[]) method test.
	 * @throws Exception
	 */
	public void testMain_1()
		throws Exception {
		try {
			String[] a = new String[] {};

			JsonToCSV.main(a);

			fail("The exception java.lang.SecurityException should have been thrown.");
		} catch (java.lang.SecurityException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void main(String[]) method test.
	 * @throws Exception
	 */
	public void testMain_2()
		throws Exception {
		try {
			String[] a = new String[] {};

			JsonToCSV.main(a);

			fail("The exception java.lang.SecurityException should have been thrown.");
		} catch (java.lang.SecurityException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void main(String[]) method test.
	 * @throws Exception
	 */
	public void testMain_3()
		throws Exception {
		try {
			String[] a = new String[] {};

			JsonToCSV.main(a);
			fail("The exception java.lang.SecurityException should have been thrown.");
		} catch (java.lang.SecurityException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Run the void setJsonString(String) method test.
	 * @throws Exception
	 */
	public void testSetJsonString_1()
		throws Exception {
		JsonToCSV jsonToCSV = new JsonToCSV("");
		jsonToCSV.setJsonString("");
		jsonToCSV.setSplitter("");
		jsonToCSV.rowindexForClassDetails = 1;
		jsonToCSV.wb = new HSSFWorkbook();
		jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
		jsonToCSV.sheet3 = null;
		jsonToCSV.rowindexForInterfaceDetails = 1;
		jsonToCSV.sheet4 = null;
		jsonToCSV.sheet2 = null;
		jsonToCSV.InterFaceListHeader = new ArrayList<String>();
		jsonToCSV.sheet1 = null;
		String jsonString = "";

		jsonToCSV.setJsonString(jsonString);
	}

	/**
	 * Run the void setSplitter(String) method test.
	 * @throws Exception
	 */
	public void testSetSplitter_1()
		throws Exception {
		JsonToCSV jsonToCSV = new JsonToCSV("");
		jsonToCSV.setJsonString("");
		jsonToCSV.setSplitter("");
		jsonToCSV.rowindexForClassDetails = 1;
		jsonToCSV.wb = new HSSFWorkbook();
		jsonToCSV.InterFaceDeailHeader = new ArrayList<String>();
		jsonToCSV.sheet3 = null;
		jsonToCSV.rowindexForInterfaceDetails = 1;
		jsonToCSV.sheet4 = null;
		jsonToCSV.sheet2 = null;
		jsonToCSV.InterFaceListHeader = new ArrayList<String>();
		jsonToCSV.sheet1 = null;
		String splitter = "";

		jsonToCSV.setSplitter(splitter);

	}
	
	 
//	
// 	public void testFormatData() throws IllegalArgumentException, FileNotFoundException{
// 		String data = "test";
//		try {
//
//			Method m = JsonToCSV.class.getDeclaredMethod("formatData", String.class);
//			m.setAccessible(true);
//			data = ((String) m.invoke(new JsonToCSV(data), "Test")) ;
//		} catch (NoSuchMethodException nsme) {
//			System.out.println(nsme.toString());
//		} catch (InvocationTargetException ite) {
//			System.out.println(ite.toString());
//		} catch (IllegalAccessException iae) {
//			System.out.println(iae.toString());
//		}
//		assertEquals("Test", data);
//		
//		
//	 
//		
//	}

	


 
	/**
	 * Launch the test.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			// Run all of the tests
			junit.textui.TestRunner.run(JsonToCSVTest.class);
		} else {
			// Run only the named tests
			TestSuite suite = new TestSuite("Selected tests");
			for (int i = 0; i < args.length; i++) {
				TestCase test = new JsonToCSVTest();
				test.setName(args[i]);
				suite.addTest(test);
			}
			junit.textui.TestRunner.run(suite);
		}
	}
}