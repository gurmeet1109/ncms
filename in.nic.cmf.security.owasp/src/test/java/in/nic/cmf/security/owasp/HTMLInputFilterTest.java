package in.nic.cmf.security.owasp;

import java.util.List;
import java.util.Map;
import org.jboss.resteasy.util.CaseInsensitiveMap;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>HTMLInputFilterTest</code> contains tests for the class <code>{@link HTMLInputFilter}</code>.
 *
 
 * @author ajay
 */
public class HTMLInputFilterTest {
	/**
	 * Run the HTMLInputFilter() constructor test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testHTMLInputFilter_1()
		throws Exception {

		HTMLInputFilter result = new HTMLInputFilter();

		
		assertNotNull(result);
	}

	/**
	 * Run the HTMLInputFilter(boolean) constructor test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testHTMLInputFilter_2()
		throws Exception {
		boolean debug = true;

		HTMLInputFilter result = new HTMLInputFilter(debug);

		
		assertNotNull(result);
	}

	/**
	 * Run the String balanceHTML(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testBalanceHTML_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.balanceHTML(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String balanceHTML(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testBalanceHTML_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.balanceHTML(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String checkEntity(String,String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testCheckEntity_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String preamble = "";
		String term = "";

		String result = fixture.checkEntity(preamble, term);

		
		assertEquals("&amp;", result);
	}

	/**
	 * Run the String checkEntity(String,String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testCheckEntity_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String preamble = "";
		String term = ";";

		String result = fixture.checkEntity(preamble, term);

		
		assertEquals("&amp;", result);
	}

	/**
	 * Run the String checkEntity(String,String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testCheckEntity_3()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String preamble = "";
		String term = ";";

		String result = fixture.checkEntity(preamble, term);

		
		assertEquals("&amp;", result);
	}

	/**
	 * Run the String checkTags(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testCheckTags_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.checkTags(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String checkTags(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testCheckTags_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.checkTags(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String checkTags(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testCheckTags_3()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.checkTags(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String chr(int) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testChr_1()
		throws Exception {
		int decimal = 1;

		String result = HTMLInputFilter.chr(decimal);

		
		assertEquals("", result);
	}

	/**
	 * Run the void debug(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDebug_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String msg = "";

		fixture.debug(msg);

		
	}

	/**
	 * Run the void debug(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDebug_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(false);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String msg = "";

		fixture.debug(msg);

		
	}

	/**
	 * Run the String decodeEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDecodeEntities_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.decodeEntities(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String decodeEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDecodeEntities_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.decodeEntities(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String decodeEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDecodeEntities_3()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.decodeEntities(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String decodeEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDecodeEntities_4()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.decodeEntities(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String decodeEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testDecodeEntities_5()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.decodeEntities(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String escapeComments(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testEscapeComments_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.escapeComments(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String escapeComments(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testEscapeComments_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.escapeComments(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String filter(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testFilter_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String input = "";

		String result = fixture.filter(input);

		
		assertEquals("", result);
	}

	/**
	 * Run the String htmlSpecialChars(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testHtmlSpecialChars_1()
		throws Exception {
		String s = "";

		String result = HTMLInputFilter.htmlSpecialChars(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the boolean isValidEntity(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testIsValidEntity_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String entity = "";

		boolean result = fixture.isValidEntity(entity);

		
		assertEquals(false, result);
	}

	/**
	 * Run the boolean isValidEntity(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testIsValidEntity_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String entity = "";

		boolean result = fixture.isValidEntity(entity);

		
		assertEquals(false, result);
	}

	/**
	 * Run the String processParamProtocol(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessParamProtocol_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processParamProtocol(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processParamProtocol(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessParamProtocol_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processParamProtocol(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processParamProtocol(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessParamProtocol_3()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processParamProtocol(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processParamProtocol(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessParamProtocol_4()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processParamProtocol(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processRemoveBlanks(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessRemoveBlanks_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processRemoveBlanks(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processRemoveBlanks(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessRemoveBlanks_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processRemoveBlanks(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_3()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_4()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_5()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_6()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_7()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_8()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String processTag(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testProcessTag_9()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.processTag(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String regexReplace(String,String,String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testRegexReplace_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String regex_pattern = "";
		String replacement = "";
		String s = "";

		String result = fixture.regexReplace(regex_pattern, replacement, s);

		
		assertEquals("", result);
	}

	/**
	 * Run the void reset() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testReset_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};

		fixture.reset();

		
	}

	/**
	 * Run the String validateEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testValidateEntities_1()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.validateEntities(s);

		
		assertEquals("", result);
	}

	/**
	 * Run the String validateEntities(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testValidateEntities_2()
		throws Exception {
		HTMLInputFilter fixture = new HTMLInputFilter(true);
		fixture.vSelfClosingTags = new String[] {};
		fixture.vAllowed = new CaseInsensitiveMap();
		fixture.vAllowedEntities = new String[] {};
		fixture.vTagCounts = new CaseInsensitiveMap();
		fixture.vNeedClosingTags = new String[] {};
		fixture.vRemoveBlanks = new String[] {};
		fixture.vAllowedProtocols = new String[] {};
		fixture.vProtocolAtts = new String[] {};
		String s = "";

		String result = fixture.validateEntities(s);

		
		assertEquals("", result);
	}

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
		// add additional set up code here
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
		new org.junit.runner.JUnitCore().run(HTMLInputFilterTest.class);
	}
}