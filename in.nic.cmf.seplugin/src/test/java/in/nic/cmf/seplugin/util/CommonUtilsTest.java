package in.nic.cmf.seplugin.util;

import junit.framework.Assert;

import org.junit.Test;

public class CommonUtilsTest {
	CommonUtils commonUtils = CommonUtils.getInstanceOf();
	
	@Test
	public void testCommonUtilsInstanceNotNull() {
		Assert.assertNotNull(commonUtils);
	}
	
	@Test
	public void testCommonUtilsIsEmptyReturnsNotNullWithValueAsNotNull() {
		Assert.assertNotNull(commonUtils.isEmpty("\""));
	}
	
	@Test
	public void testCommonUtilsIsEmptyReturnsNotNullWithValueAsNull() {
		Assert.assertNotNull(commonUtils.isEmpty(null));
	}
}
