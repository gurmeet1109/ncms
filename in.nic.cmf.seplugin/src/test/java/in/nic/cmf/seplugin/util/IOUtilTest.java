package in.nic.cmf.seplugin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import junit.framework.Assert;

public class IOUtilTest {
	IOUtil iOUtil = new IOUtil();
	
	InputStream instreamFieldName = getClass().getClassLoader()
            .getResourceAsStream("FieldNameMapping.json");
	
	InputStream instreamProperties = getClass().getClassLoader()
            .getResourceAsStream("querystringparser.properties");
	
	Properties props = new Properties();
	
	@Test
	public void testIOUtilInstanceNotNull() {
		Assert.assertNotNull(iOUtil);
	}
	
	@Test
	public void testIOUtilInstanceToStringNotNullWithoutIOClosed() {
		Assert.assertNotNull(iOUtil.toString(instreamFieldName, null));
	}
	
	@Test
	public void testIOUtilInstanceToStringNotNullWithIOClosed() throws IOException {
		instreamFieldName.close();
		Assert.assertNull(iOUtil.toString(instreamFieldName, null));
	}
	
	@Test
	public void testIOUtilInstanceGetPropertiesNotNullWithoutIOClosed() {
		iOUtil.loadProperties(props, instreamProperties);
	}
	
	@Test
	public void testIOUtilInstanceGetPropertiesNotNullWithIOClosed() throws IOException {
		instreamProperties.close();
		iOUtil.loadProperties(props, instreamProperties);
	}
}
