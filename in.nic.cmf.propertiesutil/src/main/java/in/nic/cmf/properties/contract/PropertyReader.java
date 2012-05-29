package in.nic.cmf.properties.contract;

import java.util.Map;

/**
 * The Interface PropertyReader.
 */
public interface PropertyReader {
	
	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public String getProperty(String key);

	/**
	 * Sets the property.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setProperty(String key, String value);

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String get(String key);

	/**
	 * Gets the as int.
	 *
	 * @param key the key
	 * @return the as int
	 */
	public int getAsInt(String key);

	/**
	 * Gets the as long.
	 *
	 * @param key the key
	 * @return the as long
	 */
	public long getAsLong(String key);

	/**
	 * Gets the as boolean.
	 *
	 * @param key the key
	 * @return the as boolean
	 */
	public boolean getAsBoolean(String key);

	/**
	 * Gets the as double.
	 *
	 * @param key the key
	 * @return the as double
	 */
	public double getAsDouble(String key);

	/**
	 * Gets the as float.
	 *
	 * @param key the key
	 * @return the as float
	 */
	public float getAsFloat(String key);
	
	/**
	 * Gets the as map. will return the readed property key value pair as HashMap
	 *
	 * @return the as map
	 */
	public Map<String, String> getAsMap();

}
