package in.nic.cmf.cache;

import in.nic.cmf.exceptions.GenericException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Interface Cachable.
 */
public interface Cachable {
	
	/**
	 * Sets the.
	 *
	 * @param key the key
	 * @param value the value
	 * @throws GenericException the generic exception
	 */
	public void set(String key, Object value) throws GenericException;
	
	/**
	 * Sets the.
	 *
	 * @param key the key
	 * @param value the value
	 * @param ttl the ttl
	 */
	public void set(String key, Object value, int ttl);
	
	/**
	 * Sets the.
	 *
	 * @param hashMap the hash map
	 * @throws GenericException the generic exception
	 */
	public void set(HashMap<String,Object> hashMap) throws GenericException;

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the object
	 */
	public Object get(String key);
	
	/**
	 * Gets the.
	 *
	 * @param multiKey the multi key
	 * @return the hash map
	 */
	public HashMap<String,Object> get(ArrayList<String> multiKey);

	/**
	 * Delete.
	 *
	 * @param key the key
	 * @throws GenericException the generic exception
	 */
	public void delete(String key) throws GenericException;
	
	/**
	 * Delete.
	 *
	 * @param multiKey the multi key
	 * @throws GenericException the generic exception
	 */
	public void delete(ArrayList<String> multiKey) throws GenericException;
}


