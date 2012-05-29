package in.nic.cmf.helper;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;
import net.sf.extcos.internal.ArraySet;

import org.eclipse.osgi.framework.internal.core.BundleContextImpl;
import org.osgi.framework.ServiceReference;

/**
 * A factory for creating Service objects.
 */
public class ServiceFactory {

    /** The log. */
    private LogTracer                   log         = null;

    private PropertiesUtil              propUtil;

    /** The cmfservices. */
    private HashMap<String, CMFService> cmfservices = new HashMap<String, CMFService>();

    /**
     * Sets the services.
     * @param cmfservices
     *            the cmfservices
     */
    public final void setServices(final HashMap<String, CMFService> cmfservices) {
        this.cmfservices = cmfservices;
    }

    /**
     * Adds the service.
     * @param serviceName
     *            the service name
     * @param cmfservice
     *            the cmfservice
     */
    public void addService(String serviceName, CMFService cmfservice) {
        cmfservices.put(serviceName, cmfservice);
    }

    public CMFService getService(String domain, String serviceName) {
        log.debug("Required Service Name: " + serviceName);
        propUtil = PropertiesUtil.getInstanceof(domain, "cmf");
        try {

        	if (cmfservices.get(serviceName) == null) {
                String packageName = propUtil.get("package");
                log.methodEntry("Component Scan process START");
                Set<Class<? extends CMFService>> cmfComponents = scanComponents(packageName);
                Iterator<Class<? extends CMFService>> compIteration = cmfComponents
                        .iterator();
                while (compIteration.hasNext()) {
                    Class<? extends CMFService> component = compIteration
                            .next();
                    boolean isAnnotationPresent = component
                            .isAnnotationPresent(ServiceName.class);
                    log.info(component.toString()
                            + " **** @serviceName availability : "
                            + isAnnotationPresent);
                    if (!isAnnotationPresent) continue;
                    String annotatedServiceName = component.getAnnotation(
                            ServiceName.class).value();
                    if ((isEmpty(annotatedServiceName))
                            || (!isEmpty(cmfservices.get(annotatedServiceName))))
                        continue;
                    annotatedServiceName = annotatedServiceName.toLowerCase();
                    try {
                        CMFService cmfService = component.newInstance();
                        LogTracer cmfServiceLogTracer = new LogTracer(domain,
                                annotatedServiceName,
                                propUtil.getAsBoolean("debug"),
                                propUtil.getAsBoolean("info"),
                                propUtil.getAsBoolean("warn"),
                                propUtil.getAsBoolean("error"),
                                propUtil.getAsBoolean("fatal"));
                        cmfService.setLogTracer(cmfServiceLogTracer);
                        
                        addService(annotatedServiceName, cmfService);
                        // cmfservices.put(annotatedServiceName, cmfService);
                        log.info(annotatedServiceName + " service loaded");
                    } catch (InstantiationException e) {
                        log.error(annotatedServiceName
                                + " InstantiationException: " + e);
                    } catch (IllegalAccessException e) {
                        log.error(annotatedServiceName
                                + " IllegalAccessException: " + e);
                    }
                }
                log.methodExit("Component Scan process END");
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0004", e);
        }

        if (cmfservices.get(serviceName) == null) {
            log.error("Requested Service [" + serviceName + "] unavailable...");
            throw new GenericException(domain, "ERR-CMF-0004");
        }
        return cmfservices.get(serviceName);
    }

    
    
    
    /**
     * Gets the service.
     * @param serviceName
     *            the service name
     * @return the service
     */
    public CMFService getService(String domain, String serviceName,
            final HttpServletRequest req) {
        log.debug("Required Service Name: " + serviceName);
        propUtil = PropertiesUtil.getInstanceof(domain, "cmf");
        // Try getting the service from osgi bundle context
        try {
/*            BundleContextImpl context = (BundleContextImpl) req.getSession()
                    .getServletContext().getAttribute("osgi-bundlecontext");

            if (context != null) {
                CMFService srv = null;

                outer: for (ServiceReference sr : context
                        .getAllServiceReferences(null, null)) {
                    for (String key : sr.getPropertyKeys()) {

                        if (sr.getProperty(key.toString()).equals(serviceName)) {
                            log.debug("Registered Service Name: " + serviceName);
                            srv = (CMFService) context.getService(sr);

                            srv.setLogTracer(new LogTracer(domain, sr
                                    .getProperty("servicename").toString()));
                            break outer;
                        }
                    }
                }

                cmfservices.put(serviceName, srv);
            } else {*/
        	if (cmfservices.get(serviceName) == null) {
                String packageName = propUtil.get("package");
                log.methodEntry("Component Scan process START");
                Set<Class<? extends CMFService>> cmfComponents = scanComponents(packageName);
                Iterator<Class<? extends CMFService>> compIteration = cmfComponents
                        .iterator();
                while (compIteration.hasNext()) {
                    Class<? extends CMFService> component = compIteration
                            .next();
                    boolean isAnnotationPresent = component
                            .isAnnotationPresent(ServiceName.class);
                    log.info(component.toString()
                            + " **** @serviceName availability : "
                            + isAnnotationPresent);
                    if (!isAnnotationPresent) continue;
                    String annotatedServiceName = component.getAnnotation(
                            ServiceName.class).value();
                    if ((isEmpty(annotatedServiceName))
                            || (!isEmpty(cmfservices.get(annotatedServiceName))))
                        continue;
                    annotatedServiceName = annotatedServiceName.toLowerCase();
                    try {
                        CMFService cmfService = component.newInstance();
                        LogTracer cmfServiceLogTracer = new LogTracer(domain,
                                annotatedServiceName,
                                propUtil.getAsBoolean("debug"),
                                propUtil.getAsBoolean("info"),
                                propUtil.getAsBoolean("warn"),
                                propUtil.getAsBoolean("error"),
                                propUtil.getAsBoolean("fatal"));
                        cmfService.setLogTracer(cmfServiceLogTracer);
                        
                        addService(annotatedServiceName, cmfService);
                        // cmfservices.put(annotatedServiceName, cmfService);
                        log.info(annotatedServiceName + " service loaded");
                    } catch (InstantiationException e) {
                        log.error(annotatedServiceName
                                + " InstantiationException: " + e);
                    } catch (IllegalAccessException e) {
                        log.error(annotatedServiceName
                                + " IllegalAccessException: " + e);
                    }
                }
                log.methodExit("Component Scan process END");
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new GenericException(domain, "ERR-CMF-0004", e);
        }

        if (cmfservices.get(serviceName) == null) {
            log.error("Requested Service [" + serviceName + "] unavailable...");
            throw new GenericException(domain, "ERR-CMF-0004");
        }
        return cmfservices.get(serviceName);
    }

    /**
     * Sets the log tracer.
     * @param logtracer
     *            the new log tracer
     */
    public void setLogTracer(LogTracer logtracer) {
        this.log = logtracer;
    }

    private Set<Class<? extends CMFService>> scanComponents(
            final String packageName) {
        final Set<Class<? extends CMFService>> cmfComponents = new ArraySet<Class<? extends CMFService>>();
        ComponentScanner scanner = new ComponentScanner();
        Set<Class<?>> classes = scanner.getClasses(new ComponentQuery() {
            protected void query() {
                select().from(packageName)
                        .andStore(
                                thoseImplementing(CMFService.class).into(
                                        cmfComponents));
            }
        }, this.getClass().getClassLoader());
        return cmfComponents;
    }

    private boolean isEmpty(Object value) {
        if (value == null) return true;

        if (value instanceof String) return (value.toString()).isEmpty();

        return false;
    }
}
