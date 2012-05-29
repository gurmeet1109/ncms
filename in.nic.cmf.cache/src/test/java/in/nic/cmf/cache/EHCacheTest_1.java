package in.nic.cmf.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

public class EHCacheTest_1 {

	
	@Test
	public void testGetInstanceWithConfigXML() throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {

		Field privateStringField = EHCache.class.getDeclaredField("ehCache");

		privateStringField.setAccessible(true);
		System.out.println("object   " + EHCache.getInstance());
		EHCache fieldValue = (EHCache) privateStringField.get(EHCache
				.getInstance());
		System.out.println("11111  " + fieldValue);
		fieldValue = null;
		System.out.println("fieldValue = " + fieldValue);

		String path = System.getProperty("user.dir")
				+ "/target/classes/ehcache.xml";
		assertNotNull(EHCache.getInstance(path, "ajayCacheName"));

	}

	@Test
	public void testGetInstanceWithException() {
		try {

			EHCache privateObject = null;

			Field privateStringField = EHCache.class
					.getDeclaredField("ehCache");

			privateStringField.setAccessible(true);

			EHCache fieldValue = (EHCache) privateStringField
					.get(privateObject);
			System.out.println("fieldValue = " + fieldValue);

			String path = null;
			EHCache.getInstance(path, "ajayCacheName");
		} catch (Exception e) {
			assertEquals(true,
					e.getMessage().contains("Unable to load properties info"));
		}
	}

}
