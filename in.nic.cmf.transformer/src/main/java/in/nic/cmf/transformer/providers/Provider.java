package in.nic.cmf.transformer.providers;

import java.util.Map;

/**
 * The Interface Provider.
 */
public interface Provider {
	
	/**
	 * Sets the defaults.
	 *
	 * @param domain the domain
	 * @param parameters the parameters
	 * @return the map
	 */
	Map<String, Map<String, Object>> setDefaults(String domain, Map<String, Map<String, Object>> parameters);
	
	/**
	 * Process.
	 *
	 * @param domain the domain
	 * @param content the content
	 * @return the map
	 */
	Map<String,Object>  process(String domain, Map<String,Object> content);
}
