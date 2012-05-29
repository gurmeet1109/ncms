package in.nic.cmf.security.owasp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

import in.nic.cmf.domain.Article;
import in.nic.cmf.domain.Collections;
import in.nic.cmf.exceptions.GenericException;

import javax.servlet.http.HttpServletRequest;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Test;
import org.owasp.esapi.errors.EncodingException;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.mock.web.MockHttpServletRequest;

public class CrossSiteScriptingTest {

	//CrossSiteScripting xss = new CrossSiteScripting();

	MockHttpServletRequest request = new MockHttpServletRequest();

	/* Malicious found */
	@Test
	public void testCrossSiteScripting() throws GenericException, ValidityException, ParsingException, IOException {
		CrossSiteScripting csscript = new CrossSiteScripting();
		String article = "<Category><Id>xss</Id><EntityType>Category</EntityType><CategoryName>Technology</CategoryName><ParentCategoryId></ParentCategoryId><ParentCategoryName><![CDATA[]]></ParentCategoryName><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site>ig.in</Site><Version>1.0</Version></Category>";
		try{
		boolean status = csscript.isSafe(article);
		}catch(GenericException ge){
			throw new GenericException(domain,"ERR-OWASP-0001","Malcious Found:::"+ge.getMessage());
		}
		
	}
	
	
	/*@Test
	public void testCrossSiteScripting() {
		assertNotNull(new CrossSiteScripting());
	}

	@Test
	public void testIsSafe() {
		assertEquals(true, cScripting.isSafe("ajay"));
	}

	@Test
	public void testIsSafeWithValues() {
		assertEquals(false, cScripting.isSafe("ajay alert"));
	}

	@Test
	public void testGetSafeHTMLInput() {
		assertEquals("&lt;a&gt;ajay&lt;&#x2f;a&gt;",
				cScripting.getSafeHTMLInput("<a>ajay</a>"));
	}

	@Test
	public void testGetSafeHTMLAttribute() {
		assertEquals("&lt;a&gt;ajay&lt;&#x2f;a&gt;",
				cScripting.getSafeHTMLAttribute("<a>ajay</a>"));
	}

	@Test
	public void testGetSafeHTMLJS() {
		assertEquals(
				"\\x3Cjavascript\\x3Evar\\x20x\\x3D0\\x3B\\x3Cjavascript\\x3E\\x3Ca\\x3Eajay\\x3C\\x2Fa\\x3E",
				cScripting
						.getSafeHTMLJS("<javascript>var x=0;<javascript><a>ajay</a>"));
	}

	@Test
	public void testGetSafeHTMLCSS() {
		assertEquals(
				"\\3c style\\3e \\2e a\\7b color\\3a green\\3b \\7d \\3c \\2f style\\3e \\3c a\\3e ajay\\3c \\2f a\\3e ",
				cScripting
						.getSafeHTMLCSS("<style>.a{color:green;}</style><a>ajay</a>"));
	}

	@Test
	public void testGetSafeHTMLURL() throws EncodingException {
		assertEquals("http%3A%2F%2Fwww.google.co.in%2F",
				cScripting.getSafeHTMLURL("http://www.google.co.in/"));
	}

	@Test
	public void testGetAntiSamySafeHTML() throws ScanException, PolicyException {
		assertEquals("<a>ajay</a>",
				cScripting.getAntiSamySafeHTML("<a>ajay</a>"));
	}

//	@Test
//	public void testLoadPolicy() {
//		String filePath = System.getProperty("user.dir")
//				+ "/src/test/resources/validation.properties";
//		Policy p = cScripting.loadPolicy(null);
//	}

	@Test
	public void testIsSafeHttpServletRequest() throws EncodingException,
			ScanException, PolicyException {
		HttpServletRequest hr = cScripting.isSafeHttpServletRequest(request);
		assertNotNull(hr);
	}

	@Test
	public void testGetJsCleanHTML() {
		assertEquals("<a rel=\"nofollow\">ajay</a>",
				cScripting
						.getJsCleanHTML("<javascript><javascript><a>ajay</a>"));
	}

	@Test
	public void testIsSafeCollectionsObject() throws EncodingException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			ClassNotFoundException, ScanException, PolicyException {
		Collections collections = new Collections();
		Article article = new Article();
		article.setArticleType("ajay");
		collections.add(article);
		assertEquals(collections,
				cScripting.isSafeCollectionsObject(collections));
	}

	@Test
	public void testEncodeListWithNull() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, ClassNotFoundException, ScanException,
			PolicyException {
		try {
			Object object = new Object();
			Method method = null;
			cScripting.encodeList(object, "methodName", method);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void testEncodeList() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, ClassNotFoundException, ScanException,
			PolicyException {
		try {
			Collections collections = new Collections();
			Article article = new Article();
			article.setId("112");
			article.setArticleType("ajay");
			collections.add(article);
			
			Object object = new Object();
			Method method =Article.class.getMethods()[0];
			cScripting.encodeList(article,"setArticleType",method);
		} catch (Exception e) {
			System.out.println("--------  "+e);
		}
	}

	@Test
	public void testSetValue() {
		try {
			Object object = new Object();
			Method method = null;
			cScripting.setValue("encodedeString", method, method);
		} catch (Exception e) {

		}

	}
*/
}
