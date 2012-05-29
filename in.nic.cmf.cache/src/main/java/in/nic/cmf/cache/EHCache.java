package in.nic.cmf.cache;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * The Class EHCache.
 */
public class EHCache implements Cachable {

	/** The singleton cache manager. */
	private static CacheManager singletonCacheManager = CacheManager.create();

	/** The log. */
	private LogTracer           log;

	/** The cache. */
	private static Cache        cache                 = null;

	/** The eh cache. */
	private static EHCache      ehCache;
	private String              domain;

	private void setLogger(String domain) {
		log = new LogTracer(domain, "CacheTraceLogger", true, true, true, true,
				true);
	}

	/**
	 * Instantiates a new eHcache.
	 * @throws GenericException
	 *             the generic exception
	 */
	private EHCache(String domain) throws GenericException {
		try {
			setDomain(domain);
			setLogger(domain);
			URL url = getClass().getClassLoader().getResource("ehcache.xml");
			singletonCacheManager = new CacheManager(url);
			cache = singletonCacheManager.getCache("nicdefault");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GenericException(domain, "ERR-CA-0001");
		}
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Instantiates a new eHcache.
	 * @param configXML
	 *            the config xml
	 * @param cacheName
	 *            the cache name
	 * @throws GenericException
	 *             the generic exception
	 */
	private EHCache(String domain, String configXML, String cacheName)
	throws GenericException {
		try {
			setLogger(domain);
			URL url = getClass().getClassLoader().getResource(configXML);
			singletonCacheManager = new CacheManager(url);
			cache = singletonCacheManager.getCache(cacheName);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GenericException(domain, "ERR-CA-0001");
		}
	}

	private static HashMap<String, EHCache> ehCacheMap    = new HashMap<String, EHCache>();


	/**
	 * Gets the single instance of EHCache.
	 * @return single instance of EHCache
	 * @throws GenericException
	 *             the generic exception
	 */
	public static synchronized EHCache getInstanceof(String domain)
	throws GenericException {
		try {
			if (!ehCacheMap.containsKey(domain)) {
				ehCacheMap.put(domain, new EHCache(domain));
			}
			return ehCacheMap.get(domain);
		} catch (GenericException e) {
			throw e;
		}
	}

	/**
	 * Gets the single instance of EHCache.
	 * @param configXML
	 *            the config xml
	 * @param cacheName
	 *            the cache name
	 * @return single instance of EHCache
	 * @throws GenericException
	 *             the generic exception
	 */
	public static synchronized EHCache getInstanceof(String domain,
			String configXML, String cacheName) throws GenericException {
		try {
			if (ehCache == null) {
				ehCache = new EHCache(domain, configXML, cacheName);
			}
			return ehCache;
		} catch (GenericException e) {
			throw e;
		}
	}

	/**
	 * Sets the value in cache.
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @throws GenericException
	 *             the generic exception
	 */
	public void set(String key, Object value) throws GenericException {
		log.methodEntry(this.getClass().getSimpleName()
				+ ".set(String, Object)");
		try {
			Element element = new Element(key, value);
			cache.put(element);
			log.methodExit(this.getClass().getSimpleName()
					+ ".set(String, Object)");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GenericException(domain,"ERR-CA-0000", this.getClass()
					.getSimpleName() + ".set(String, Object)", e);
		}
	}

	/**
	 * Sets the value in cache.
	 * @param hashMap
	 *            the hash map
	 * @throws GenericException
	 *             the generic exception
	 */
	public void set(HashMap<String, Object> hashMap) throws GenericException {
		log.methodEntry(this.getClass().getSimpleName() + ".set(HashMap)");
		try {
			Set<Entry<String, Object>> set = hashMap.entrySet(); // Get a set of
			// the
			// entries
			Iterator<Entry<String, Object>> iterator = set.iterator(); // Get an
			// iterator
			while (iterator.hasNext()) { // fetch elements
				Map.Entry<String, Object> mapEntry = (Map.Entry<String, Object>) iterator
				.next();
				set(mapEntry.getKey().toString(), mapEntry.getValue());
			}
			log.methodExit(this.getClass().getSimpleName() + ".set(HashMap)");
		} catch (GenericException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Gets the value from cache..
	 * @param key
	 *            the key
	 * @return the object
	 */
	public Object get(String key) {
		log.methodEntry(this.getClass().getSimpleName() + ".get(String)");
		try {
			Element element = cache.get(key);
			Object valueObject = element.getObjectValue();
			log.methodExit(this.getClass().getSimpleName() + ".get(String)");
			return valueObject;
		} catch (Exception e) {
			log.warn(e.getMessage() + " ::::: returned null");
			return null;
		}
	}

	/**
	 * Gets the value from cache..
	 * @param multiKey
	 *            the multi key
	 * @return the hash map
	 */
	public HashMap<String, Object> get(ArrayList<String> multiKey) {
		log.methodEntry(this.getClass().getSimpleName() + ".get(ArrayList)");
		Iterator<String> iterator = multiKey.iterator();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object object = get(key);
			if (object != null) {
				hashMap.put(key, object);
			}
		}
		log.methodExit(this.getClass().getSimpleName() + ".get(ArrayList)");
		if (hashMap.size() > 0) {
			return hashMap;
		}
		return null;
	}

	/**
	 * Delete the value from cache..
	 * @param key
	 *            the key
	 * @throws GenericException
	 *             the generic exception
	 */
	public void delete(String key) throws GenericException {
		log.methodEntry(this.getClass().getSimpleName() + ".delete(String)");
		try {
			cache.remove(key);
			log.methodExit(this.getClass().getSimpleName() + ".delete(String)");
		} catch (IllegalStateException e) {
			log.error(e.getMessage());
			throw new GenericException(domain,"ERR-CA-0000", this.getClass()
					.getSimpleName() + ".delete(String)", e);
		}
	}

	/**
	 * Delete the value from cache..
	 * @param multiKey
	 *            the multi key
	 * @throws GenericException
	 *             the generic exception
	 */
	public void delete(ArrayList<String> multiKey) throws GenericException {
		log.methodEntry(this.getClass().getSimpleName() + ".delete(ArrayList)");
		try {
			Iterator<String> iterator = multiKey.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				delete(key);
			}
			log.methodExit(this.getClass().getSimpleName()
					+ ".delete(ArrayList)");
		} catch (GenericException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Sets the.
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @param ttl
	 *            the ttl
	 */
	public void set(String key, Object value, int ttl) {
	}

}
