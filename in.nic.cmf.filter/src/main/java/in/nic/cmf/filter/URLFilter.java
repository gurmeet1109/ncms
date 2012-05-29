package in.nic.cmf.filter;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class URLFilter implements Filter {
	private FilterConfig filterConfig = null;
	//private LogTracer log = new LogTracer("filterlog");
	private LogTracer log ;
	protected FilterConfig config;
	private String domain;

	public void init(FilterConfig filterConfig) throws ServletException {
		setFilterConfig(filterConfig);
	}
	
	 private void setLogger(String domain) {
	        log = new LogTracer(domain, "filterlog");
	    }


	private List<String> getRestrictedChar() {
		List<String> restrictedChar = new ArrayList<String>();
		restrictedChar.add("\\");
		restrictedChar.add("{");
		restrictedChar.add("}");
		restrictedChar.add("^");
		restrictedChar.add("|");
		return restrictedChar;
	}

	private boolean isValidUrl(String url) throws GenericException {
		boolean status = true;
		ListIterator<String> itr = getRestrictedChar().listIterator();
		String value = "";
		while (itr.hasNext()) {
			value = itr.next();
			if (url.contains(value)) {
				// log.debug("Restricted Char : "+value);
				status = false;
				break;
			}
		}
		if (!status) {
			String errorMsg = "Invalid character : " + value + " found in ->"
					+ url;
			log.error(errorMsg);
			throw new GenericException(domain,"ERR-SFIL-0001", errorMsg);
		}
		return status;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException,
			GenericException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;

		String requestURI = httpRequest.getRequestURI();
		log.debug("Before ::::::::::::: " + requestURI);
		if (httpRequest.getQueryString() != null) {
			requestURI += '?' + httpRequest.getQueryString();
			 log.debug("Query : " + httpRequest.getQueryString());
		}
		URL reconstructedURL = null;
		try {
			reconstructedURL = new URL(httpRequest.getScheme(),
					httpRequest.getServerName(), httpRequest.getServerPort(),
					requestURI);
			String url = reconstructedURL.toString();
			log.debug("Requested URL : " + url);
			isValidUrl(url);
			chain.doFilter(request, response);
		} catch (GenericException e) {
			httpresponse.sendError(400,"Invalid Character Found");			
			return;
		} catch (MalformedURLException e) {
			log.error("MalformedURLException : [ERR-SFIL-0001] " + e.getMessage());
//			throw new GenericException("ERR-SFIL-0001");
		} catch (IOException e) {
			log.error("IOException : [ERR-SFIL-0002] " + e.getMessage());
//			throw new GenericException("ERR-SFIL-0002");
		} catch (ServletException e) {
			log.error("ServletException : [ERR-SFIL-0003] " + e.getMessage());
//			throw new GenericException("ERR-SFIL-0003");
		}
	}

	public void destroy() {
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

}
