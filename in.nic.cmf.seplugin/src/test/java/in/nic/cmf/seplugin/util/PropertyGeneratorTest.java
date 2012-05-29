package in.nic.cmf.seplugin.util;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

public class PropertyGeneratorTest {
	PropertyGenerator generator = PropertyGenerator.getInstance();
	
	@Test
	public void testPropertyGeneratorInstanceNotNull() throws IOException {
		Assert.assertNotNull(generator);
	}
}
