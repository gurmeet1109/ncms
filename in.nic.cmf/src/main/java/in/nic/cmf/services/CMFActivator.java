package in.nic.cmf.services;

import javax.ws.rs.ext.RuntimeDelegate;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class CMFActivator implements BundleActivator {	
	public void start(BundleContext context) {
		if(!(RuntimeDelegate.getInstance() instanceof ResteasyProviderFactory)) {
			RuntimeDelegate.setInstance(null);
		}
	}

	public void stop(BundleContext context) {
		
	}
}
