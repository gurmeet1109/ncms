package in.nic.cmf.serviceclient;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.serviceclient.helper.ServiceClientHelper;

import java.util.HashMap;
import java.util.Map;

public class ServiceClientImpl implements CMFService {

    private String                                    service;
    private final static String                       GET    = "get";
    private final static String                       POST   = "post";
    private final static String                       DELETE = "delete";

    private LogTracer                                 log;
    private static ServiceClientHelper                scHelper;
    private static HashMap<String, ServiceClientImpl> scs    = new HashMap<String, ServiceClientImpl>();

    // private ServiceClientProtocolFactory serviceClientProtocolFactory = null;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    private ServiceClientImpl(String service) throws GenericException {
        setService(service);
    }

    public static ServiceClientImpl getInstance(String domain, String service) {

        boolean isEmpty = true;
        if (service != null) {
            service = service.trim();
            isEmpty = service.isEmpty();
        }
        if (isEmpty) {
            throw new GenericException(domain, "ERR-SC-0002",
                    "Service Name Parameter Empty: " + service);
        }
        scHelper = ServiceClientHelper.getInstanceof(domain);
        if (!scs.containsKey(service)) {
            scs.put(service, new ServiceClientImpl(service));
        }
        return scs.get(service);
    }

    // GET
    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        setLogger(domain);
        log.debug("inside ServiceClientImpl.find method...");
        return validate(domain, parameters, GET).find(domain, entity,
                parameters);
    }

    // GETBYID
    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        setLogger(domain);
        log.debug("inside ServiceClientImpl.findById method.");
        return validate(domain, parameters, GET).findById(domain, entity, id,
                parameters);
    }

    // POST
    @Override
    public Map<String, Map<String, Object>> add(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        setLogger(domain);
        log.debug("inside ServiceClientImpladd method.");
        return validate(domain, parameters, POST).add(domain, entityType,
                parameters);
    }

    // POST BY Collections
    @Override
    public Map<String, Map<String, Object>> read(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        setLogger(domain);
        log.debug("inside ServiceClientImpl.read method:" + parameters + domain
                + parameters);
        return validate(domain, parameters, POST).read(domain, entityType,
                parameters);
    }

    // DELETEBYID
    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entityType, String id,
            Map<String, Map<String, Object>> parameters) {
        setLogger(domain);
        log.debug("inside ServiceClientImpl.deleteByID method.");
        return validate(domain, parameters, DELETE).deleteByID(domain,
                entityType, id, parameters);
    }

    // DELETEBYQUERY
    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String query, Map<String, Map<String, Object>> parameters) {
        setLogger(domain);
        log.debug("inside ServiceClientImpl.deleteByQuery method.");
        return validate(domain, parameters, DELETE).deleteByQuery(domain,
                query, parameters);
    }

    @Override
    public void setLogTracer(LogTracer log) {
        if (log != null) this.log = log;
    }

    private void setLogger(String domain) {
        this.log = new LogTracer(domain, "serviceclient");
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    private CMFService validate(String domain,
            Map<String, Map<String, Object>> parameters, String action) {
        log.debug("inside validate:" + getService());
        scHelper.isValidParams(parameters);
        log.debug("After validateparam getting instance of cmfservice");
        // return serviceClientProtocolFactory.(getService(), action);
        return ServiceClientProtocolFactory.getInstance(domain, getService(),
                action);
    }
}
