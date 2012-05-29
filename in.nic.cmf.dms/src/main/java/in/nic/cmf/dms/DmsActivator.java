package in.nic.cmf.dms;

import in.nic.cmf.contract.ServiceName;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class DmsActivator implements BundleActivator {
	private ServiceRegistration registration;
	
	public void start(BundleContext context) {
		DmsServiceImpl service = (DmsServiceImpl) new DmsServiceImpl();
		
		Dictionary<String, String> props = new Hashtable<String, String>();
		props.put("servicename", DmsServiceImpl.class.getAnnotation(ServiceName.class).value());
		
    	registration = context.registerService(DmsServiceImpl.class.getName(), service, props);
    }
    
    public void stop(BundleContext context) {
    	registration.unregister();
    }
}
