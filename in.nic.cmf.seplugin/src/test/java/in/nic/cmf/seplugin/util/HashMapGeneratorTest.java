package in.nic.cmf.seplugin.util;

import org.junit.Test;

import junit.framework.Assert;

public class HashMapGeneratorTest {
	HashMapGenerator generator = HashMapGenerator.getInstance();
	
	@Test
	public void testHashMapGeneratorInstanceNotNull() {
		Assert.assertNotNull(generator);
	}
	
	@Test
	public void testHashMapGeneratorQueryKeyNotNull() {
		Assert.assertNotNull(generator.getQueryKey());
	}
}
