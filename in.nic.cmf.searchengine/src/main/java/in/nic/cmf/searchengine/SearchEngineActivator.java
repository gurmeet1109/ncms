package in.nic.cmf.searchengine;

import in.nic.cmf.contract.ServiceName;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SearchEngineActivator implements BundleActivator {
	private ServiceRegistration registration;
	
	public void start(BundleContext context) {
		SearchEngineImpl service = (SearchEngineImpl) new SearchEngineImpl();
		
		Dictionary<String, String> props = new Hashtable<String, String>();
		props.put("servicename", SearchEngineImpl.class.getAnnotation(ServiceName.class).value());
		
    	registration = context.registerService(SearchEngineImpl.class.getName(), service, props);
    }
    
    public void stop(BundleContext context) {
    	registration.unregister();
    }
}
