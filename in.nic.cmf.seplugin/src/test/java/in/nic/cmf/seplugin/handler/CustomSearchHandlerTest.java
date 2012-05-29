package in.nic.cmf.seplugin.handler;

import junit.framework.TestCase;

import org.apache.solr.response.SolrQueryResponse;
import org.junit.Assert;
import org.junit.Test;

public class CustomSearchHandlerTest extends TestCase {
	CustomSearchHandler handler = new CustomSearchHandler();
	
	@Test
	public void testCustomSearchHandlerInstanceNotNull() {
		Assert.assertNotNull(handler);
		handler.handleRequestBody(null, new SolrQueryResponse());
	}
	
	@Test
	public void testCustomSearchHandlerDescriptionNotNull() {
		Assert.assertNotNull(handler.getDescription());
	}
	
	@Test
	public void testCustomSearchHandlerSourceNotNull() {
		Assert.assertNotNull(handler.getSource());
	}
	
	@Test
	public void testCustomSearchHandlerSourceIdNotNull() {
		Assert.assertNotNull(handler.getSourceId());
	}
	
	@Test
	public void testCustomSearchHandlerVersionNotNull() {
		Assert.assertNotNull(handler.getVersion());
	}
}
