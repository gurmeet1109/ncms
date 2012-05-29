package in.nic.cmf.transformer.providers;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.properties.PropertiesUtil;

/**
 * A factory for creating Provider objects.
 */
public class ProviderFactory {
	private static String domain;

	/** The putil. */
	private static PropertiesUtil putil ;

	/**
	 * Gets the provider.
	 *
	 * @return the provider
	 */
	public static Provider getProvider(String domain){
		putil = PropertiesUtil.getInstanceof(domain, "transformer");
		try{
			String provider = (String) putil.getProperty("droolsprovider");
			if(provider.equals("true")){
				return DroolsProvider.getInstanceOf(domain);
			}else{
				return DefaultProvider.getInstanceOf(domain);
			}
		}catch(GenericException ge){
			throw ge;
		}catch(Exception ex){
			throw new GenericException(domain,"ERR-TRA-0012",ex);
		}
	}
}
