package in.nic.cmf.serviceclient.providers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;
import net.sf.extcos.internal.ArraySet;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

public class CMFServiceScanner {

	
	private LogTracer log;
	private PropertiesUtil putil;
	private String serviceName = null;
	private Map<String, CMFService> cmfServiceInVM = new HashMap<String, CMFService>();

	public static final CMFServiceScanner compScanner = new CMFServiceScanner();


	private CMFServiceScanner() {
		/*log = new LogTracer(domain, "serviceclient");
		putil = PropertiesUtil.getInstanceof(domain, "serviceclient");
		setServiceName(service);*/
	}
	
	public static CMFService getInstanceOf(String domainName, String serviceName) {
		
		return compScanner.getService(domainName, serviceName);
	}

	public CMFService getService( String domainName, String serviceName) {
		log = new LogTracer(domainName, "serviceclient");
		putil = PropertiesUtil.getInstanceof(domainName, "serviceclient");
		serviceName = serviceName.toLowerCase();
		if (cmfServiceInVM.get(serviceName) == null) {
			String packageName = putil.get("package");
			log.methodEntry("Component Scan process START from VM");
			Set<Class<? extends CMFService>> cmfComponents = scanComponents(packageName);
			Iterator<Class<? extends CMFService>> compIteration = cmfComponents
					.iterator();
			while (compIteration.hasNext()) {
				Class<? extends CMFService> component = compIteration.next();
				boolean isAnnotationPresent = component
						.isAnnotationPresent(ServiceName.class);
				log.info(component.toString()
						+ " **** @serviceName availability : "
						+ isAnnotationPresent);
				if (!isAnnotationPresent)
					continue;
				String annotatedServiceName = component.getAnnotation(
						ServiceName.class).value();
				if ((isEmpty(annotatedServiceName))
						|| (!isEmpty(cmfServiceInVM.get(annotatedServiceName))))
					continue;

				annotatedServiceName = annotatedServiceName.toLowerCase();
				try {
					CMFService cmfService = component.newInstance();
					LogTracer cmfServiceLogTracer = new LogTracer(domainName,
							annotatedServiceName, putil.getAsBoolean("debug"),
							putil.getAsBoolean("info"),
							putil.getAsBoolean("warn"),
							putil.getAsBoolean("error"),
							putil.getAsBoolean("fatal"));
					cmfService.setLogTracer(cmfServiceLogTracer);
					cmfServiceInVM.put(annotatedServiceName, cmfService);
					log.info(annotatedServiceName + " service loaded");
				} catch (InstantiationException e) {
					log.error(annotatedServiceName
							+ " InstantiationException: " + e);
				} catch (IllegalAccessException e) {
					log.error(annotatedServiceName
							+ " IllegalAccessException: " + e);
				}
			}
			log.methodExit("Component Scan process END from VM");
		}
		
		if (isEmpty(cmfServiceInVM.get(serviceName))) {
			log.error("Requested Service [" + serviceName + "] unavailable...");
			return null;
		}
		log.error("Requested Service [" + serviceName + "] available...");
		return cmfServiceInVM.get(serviceName);
	}

	private Set<Class<? extends CMFService>> scanComponents(
			final String packageName) {
		final Set<Class<? extends CMFService>> cmfComponents = new ArraySet<Class<? extends CMFService>>();
		ComponentScanner scanner = new ComponentScanner();
		Set<Class<?>> classes = scanner.getClasses(new ComponentQuery() {
			protected void query() {
				select().from(packageName).andStore(thoseImplementing(CMFService.class).into(cmfComponents));
			}
		}, this.getClass().getClassLoader());
		return cmfComponents;
	}

	private boolean isEmpty(Object value) {
		if (value == null)
			return true;

		if (value instanceof String)
			return (value.toString()).isEmpty();

		return false;
	}
	
}
