package in.nic.cmf.seplugin.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import org.junit.Test;

import junit.framework.Assert;

public class SearchEngineServletTest {
	SearchEngineServlet searchEngineServlet = new SearchEngineServlet();
	
	final File file = new File("/home/premananda/workspace/apache-solr-3.4.0/example/exampledocs/cmsperfectalldata2.xml");
	
	@Test
	public void testSearchEngineServletInstanceNotNull() {
		Assert.assertNotNull(searchEngineServlet);
	}
	
	@Test
	public void testSearchEngineServletGetWithQueryAsEntityType() throws ServletException, IOException {		
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/select?q=&entitytype=DomainEntity,Stage&limit=10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithExceededMaxLimit() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/select?q=&entitytype=DomainEntity&limit=120");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithNoProperDomain() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/agar.in/");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithoutPathInfo() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithQueryString() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/nic.in/select?q=&entitytype=DomainEntity");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithSolrRequestAsException() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/");
		testAltGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletPostFile() throws ServletException, IOException {
		PostMethod method = new PostMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/");
		testPostMethod(searchEngineServlet, method, file);
	}
	
	@Test
	public void testSearchEngineServletGet() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/nic.in/");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithCache() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/nic.in/");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletDeleteId() throws ServletException, IOException {
		PostMethod method = new PostMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/deletebyid?id=wfm3lkggyKfeaciab");
		testPostMethod(searchEngineServlet, method, null);
	}
	
	@Test
	public void testSearchEngineServletDeleteWithIdAndEntityType() throws ServletException, IOException {
		PostMethod method = new PostMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/delete?q=wfm3lkggyKfeaciab2&entitytype=WorkflowUserMap");
		testPostMethod(searchEngineServlet, method, null);
	}
	
	@Test
	public void testSearchEngineServletDeleteWithId() throws ServletException, IOException {
		PostMethod method = new PostMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/delete?entitytype=WorkflowUserMap");
		testPostMethod(searchEngineServlet, method, null);
	}
	
	@Test
	public void testSearchEngineServletDeleteWithNoDeleteOperation() throws ServletException, IOException {
		PostMethod method = new PostMethod("http://124.7.228.58:8080/solr/cmf/sagar.in/deletealt?q=wfm3lkggyKfeaciab&entitytype=WorkflowUserMap");
		testPostMethod(searchEngineServlet, method, null);
	}
	
	@Test
	public void testSearchEngineServletGetWithEntity() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/nic.in/article/");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithValidQueryValues() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ig.in/select?q=&cond=and&orderby=createddate&createdby=igportaladmin");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithAlternativeValidQueryValues() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&title=%22am,%20ok%22");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithValidSolrQuery() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&channel=news,movies&cond=and&channel=arts");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithCond() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&channel=news,movies&cond=or&channel=arts");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithOrderbydir() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=asc");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithCreatedby() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?cond=and&createdby=%22am,%20ok%22");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletGetWithFields() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=desc&fields=test&limit=2&offset=10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithNonQParameterWithValueAsEmpty() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=&orderby=createddate&orderbydir=desc&fields=test,entitytype&limit=2&offset=10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithLimitParamWithValueAsAlphanumeric() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=desc&fields=test,entitytype&limit=2asd&offset=10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithLimitParamWithValueAsLessThanZero() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=desc&fields=test,entitytype&limit=-2&offset=10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithOffsetParamWithValueAsAlphanumeric() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=desc&fields=test,entitytype&limit=2&offset=as10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithOffsetParamWithValueAsLessThanEqualZero() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=desc&fields=test,entitytype&limit=2&offset=-10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithOrderbydirParamWithValueAsNeitherAscNotDescAndNonEmpty() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/ramya.in/select?q=&cond=and&orderby=createddate&orderbydir=descasc&fields=test,entitytype&limit=2&offset=10");
		testGetMethod(searchEngineServlet, method);
	}
	
	@Test
	public void testSearchEngineServletValidityWithCondParamWithValueAsNeitherAndNorOrAndNonEmpty() throws ServletException, IOException {
		GetMethod method = new GetMethod("http://124.7.228.58:8080/solr/cmf/nic.in/select?q=&cond=andor&orderby=createddate");
		testGetMethod(searchEngineServlet, method);
	}
	
	private void testGetMethod(SearchEngineServlet searchEngineServlet, final GetMethod method) throws ServletException, IOException {
		HttpServletRequest req = new HttpServletRequest() {
			
			public void setCharacterEncoding(String arg0)
					throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void removeAttribute(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public int getServerPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getServerName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getScheme() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public RequestDispatcher getRequestDispatcher(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getRemotePort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getRemoteHost() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRemoteAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRealPath(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public BufferedReader getReader() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getProtocol() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String[] getParameterValues(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getParameterNames() {
				// TODO Auto-generated method stub
				Hashtable ht = new Hashtable();
				ht.putAll(getParameterMap());
				return ht.keys();
			}
			
			public Map getParameterMap() {
				// TODO Auto-generated method stub
				Map map =new HashMap();
				if(getQueryString() != null) {
					String[] params = getQueryString().split("&");
					for (String param : params) {
						//System.out.println(param.substring(0, param.indexOf("=")) + "\t\t" + param.substring(param.indexOf("=")+1, param.length()));
						map.put(param.substring(0, param.indexOf("=")), new String[]{param.substring(param.indexOf("=")+1, param.length())});
					}
				}
				return map;
			}
			
			public String getParameter(String arg0) {
				// TODO Auto-generated method stub
				return ((String[])getParameterMap().get(arg0))[0];
			}
			
			public Enumeration getLocales() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getLocalPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLocalAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public ServletInputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getContentLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Object getAttribute(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean isUserInRole(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdValid() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromUrl() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromURL() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromCookie() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public HttpSession getSession(boolean arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public HttpSession getSession() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getServletPath() {
				// TODO Auto-generated method stub
				//System.out.println(method.getPath());
				return method.getPath().substring(5, method.getPath().indexOf("/",6));
			}
			
			public String getRequestedSessionId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public StringBuffer getRequestURL() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRequestURI() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getQueryString() {
				// TODO Auto-generated method stub
				return method.getQueryString();
			}
			
			public String getPathTranslated() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getPathInfo() {
				// TODO Auto-generated method stub
				String pathInfo = method.getPath().substring(method.getPath().indexOf("/",6), method.getPath().length());
				if (pathInfo.equals("/")) {
					return null;
				}
				return pathInfo;
			}
			
			public String getMethod() {
				// TODO Auto-generated method stub
				return "GET";
			}
			
			public int getIntHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Enumeration getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return method.getRequestHeader(arg0).getName();
			}
			
			public long getDateHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Cookie[] getCookies() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContextPath() {
				// TODO Auto-generated method stub
				return method.getPath().substring(0,5);
			}
			
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		HttpServletResponse res = new HttpServletResponse() {
			
			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setContentType(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public PrintWriter getWriter() throws IOException {
				// TODO Auto-generated method stub
				return new PrintWriter(System.out);
			}
			
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void sendRedirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void sendError(int arg0, String arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		searchEngineServlet.doGet(req, res);
	}
	
	private void testPostMethod(SearchEngineServlet searchEngineServlet, final PostMethod method, final File file) throws ServletException, IOException {
		HttpServletRequest req = new HttpServletRequest() {
			
			public void setCharacterEncoding(String arg0)
					throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void removeAttribute(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public int getServerPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getServerName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getScheme() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public RequestDispatcher getRequestDispatcher(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getRemotePort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getRemoteHost() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRemoteAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRealPath(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public BufferedReader getReader() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getProtocol() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String[] getParameterValues(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getParameterNames() {
				// TODO Auto-generated method stub
				Hashtable ht = new Hashtable();
				ht.putAll(getParameterMap());
				return ht.keys();
			}
			
			public Map getParameterMap() {
				// TODO Auto-generated method stub
				Map map =new HashMap();
				if(getQueryString() != null) {
					//System.out.println("getQueryString(): " + getQueryString());
					String[] params = getQueryString().split("&");
					//System.out.println("params size: " + params.length);
					for (String param : params) {
						//System.out.println("param: " + param);
						
						map.put(param.substring(0, param.indexOf("=")), new String[]{param.substring(param.indexOf("=")+1, param.length())});
					}
				}
				return map;
			}
			
			public String getParameter(String arg0) {
				// TODO Auto-generated method stub
				return ((String[])getParameterMap().get(arg0))[0];
			}
			
			public Enumeration getLocales() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getLocalPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLocalAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public ServletInputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				if(file != null) {
					final InputStream is = new FileInputStream(file);
					
					return new ServletInputStream() {
						
						@Override
						public int read() throws IOException {
							// TODO Auto-generated method stub
							return is.read();
						}
					};
				} else {
					/*return new ServletInputStream() {
						
						@Override
						public int read() throws IOException {
							// TODO Auto-generated method stub
							return 0;
						}
					};*/
					return null;
				}
			}
			
			public String getContentType() {
				// TODO Auto-generated method stub
				if(file !=null ) {
					return "application/xml";
				}
				return "application/x-www-form-urlencoded";
			}
			
			public int getContentLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Object getAttribute(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean isUserInRole(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdValid() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromUrl() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromURL() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromCookie() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public HttpSession getSession(boolean arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public HttpSession getSession() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getServletPath() {
				// TODO Auto-generated method stub
				//System.out.println(method.getPath());
				return method.getPath().substring(5, method.getPath().indexOf("/",6));
			}
			
			public String getRequestedSessionId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public StringBuffer getRequestURL() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRequestURI() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getQueryString() {
				// TODO Auto-generated method stub
				return method.getQueryString();
			}
			
			public String getPathTranslated() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getPathInfo() {
				// TODO Auto-generated method stub
				String pathInfo = method.getPath().substring(method.getPath().indexOf("/",6), method.getPath().length());
				if (pathInfo.equals("/")) {
					return null;
				}
				return pathInfo;
			}
			
			public String getMethod() {
				// TODO Auto-generated method stub
				return "POST";
			}
			
			public int getIntHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Enumeration getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Content-Length: " + method.getRequestHeader("Content-Length").getName());
				if(arg0.equals("Content-Length")) {
					if(file != null) {
						return ""+file.length();
					}else{
						return null;
					}
				}
				return method.getRequestHeader(arg0).getName();
			}
			
			public long getDateHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Cookie[] getCookies() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContextPath() {
				// TODO Auto-generated method stub
				return method.getPath().substring(0,5);
			}
			
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		HttpServletResponse res = new HttpServletResponse() {
			
			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setContentType(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public PrintWriter getWriter() throws IOException {
				// TODO Auto-generated method stub
				return new PrintWriter(System.out);
			}
			
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void sendRedirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void sendError(int arg0, String arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		searchEngineServlet.doPost(req, res);
	}
	
	private void testAltGetMethod(SearchEngineServlet searchEngineServlet, final GetMethod method) throws ServletException, IOException {
		HttpServletRequest req = new HttpServletRequest() {
			
			public void setCharacterEncoding(String arg0)
					throws UnsupportedEncodingException {
				// TODO Auto-generated method stub
				
			}
			
			public void setAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void removeAttribute(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public int getServerPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getServerName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getScheme() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public RequestDispatcher getRequestDispatcher(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getRemotePort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getRemoteHost() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRemoteAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRealPath(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public BufferedReader getReader() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getProtocol() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String[] getParameterValues(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getParameterNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Map getParameterMap() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getParameter(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getLocales() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getLocalPort() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getLocalName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getLocalAddr() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public ServletInputStream getInputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getContentLength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Object getAttribute(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean isUserInRole(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdValid() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromUrl() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromURL() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean isRequestedSessionIdFromCookie() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Principal getUserPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public HttpSession getSession(boolean arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public HttpSession getSession() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getServletPath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRequestedSessionId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public StringBuffer getRequestURL() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRequestURI() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getRemoteUser() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getQueryString() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getPathTranslated() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getPathInfo() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getMethod() {
				// TODO Auto-generated method stub
				return "GET";
			}
			
			public int getIntHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Enumeration getHeaders(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Enumeration getHeaderNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getHeader(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public long getDateHeader(String arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public Cookie[] getCookies() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContextPath() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getAuthType() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		HttpServletResponse res = new HttpServletResponse() {
			
			public void setLocale(Locale arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setContentType(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setContentLength(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setCharacterEncoding(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setBufferSize(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void resetBuffer() {
				// TODO Auto-generated method stub
				
			}
			
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			public boolean isCommitted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public PrintWriter getWriter() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public ServletOutputStream getOutputStream() throws IOException {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Locale getLocale() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getContentType() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String getCharacterEncoding() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public int getBufferSize() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public void flushBuffer() throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void setStatus(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setStatus(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void setIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void setDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void sendRedirect(String arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void sendError(int arg0, String arg1) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public void sendError(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			
			public String encodeUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeRedirectUrl(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String encodeRedirectURL(String arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean containsHeader(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void addIntHeader(String arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addHeader(String arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addDateHeader(String arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			
			public void addCookie(Cookie arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		searchEngineServlet.doGet(req, res);
	}
}
