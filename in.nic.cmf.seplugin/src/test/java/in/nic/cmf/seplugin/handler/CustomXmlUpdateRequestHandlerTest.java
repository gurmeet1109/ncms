package in.nic.cmf.seplugin.handler;

import org.junit.Test;

import junit.framework.Assert;

public class CustomXmlUpdateRequestHandlerTest {
	CustomXmlUpdateRequestHandler handler = new CustomXmlUpdateRequestHandler();
	
	@Test
	public void testCustomXmlUpdateRequestHandlerInstanceNotNull() {
		Assert.assertNotNull(handler);
	}
	
	@Test
	public void testCustomXmlUpdateRequestHandlerDescriptionNotNull() {
		Assert.assertNotNull(handler.getDescription());
	}
	
	@Test
	public void testCustomXmlUpdateRequestHandlerSourceNotNull() {
		Assert.assertNotNull(handler.getSource());
	}
	
	@Test
	public void testCustomXmlUpdateRequestHandlerSourceIdNotNull() {
		Assert.assertNotNull(handler.getSourceId());
	}
	
	@Test
	public void testCustomXmlUpdateRequestHandlerVersionNotNull() {
		Assert.assertNotNull(handler.getVersion());
	}
}
