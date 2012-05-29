package in.nic.cmf.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.Assert;

import org.junit.Test;

public class EHCacheTest {

	@Test
	public void testGetInstance() {
		
		assertNotNull(EHCache.getInstance());
	}

	@Test
	public void testSetStringObject() {
		EHCache.getInstance().set("key", new Object());
		assertNotNull(EHCache.getInstance().get("key"));
	}

	@Test
	public void testSetStringObjectWithException() {
		try {
			EHCache.getInstance().set("nn jjj jjj", null);
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}

	@Test
	public void testSetHashMapOfStringObject() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("key", new Object());
		EHCache.getInstance().set(hashMap);
		assertNotNull(EHCache.getInstance().get("key"));
	}

	@Test
	public void testGetArrayListOfString() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("key", new Object());
		hashMap.put("key1", new Object());
		EHCache.getInstance().set(hashMap);

		ArrayList<String> multiKey = new ArrayList<String>();
		multiKey.add("key");
		multiKey.add("key1");

		assertNotNull(EHCache.getInstance().get(multiKey));

	}

	@Test
	public void testGetWithNull() {
		ArrayList<String> multiKey = new ArrayList<String>();
		assertNull(EHCache.getInstance().get(multiKey));

	}

	@Test
	public void testDeleteString() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("key", new Object());
		EHCache.getInstance().set(hashMap);

		EHCache.getInstance().delete("key");

		Assert.assertNull(EHCache.getInstance().get("key"));
	}

	@Test
	public void testDeleteArrayListOfString() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("key", new Object());
		hashMap.put("key1", new Object());
		EHCache.getInstance().set(hashMap);

		ArrayList<String> multiKey = new ArrayList<String>();
		multiKey.add("key");
		multiKey.add("key1");

		EHCache.getInstance().delete(multiKey);

		Assert.assertNull(EHCache.getInstance().get("key"));
	}

	@Test
	public void testSetStringObjectInt() {

		EHCache.getInstance().set("", new Object(), 0);
	}

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
