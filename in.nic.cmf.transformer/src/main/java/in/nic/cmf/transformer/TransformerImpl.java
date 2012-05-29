package in.nic.cmf.transformer;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.transformer.providers.Provider;
import in.nic.cmf.transformer.providers.ProviderFactory;

import java.util.Map;

@ServiceName ("transformer")
public class TransformerImpl implements CMFService {

    private Provider     provider;
    private LogTracer    log;
    private final String NOT_IMPLEMENTED = "ERR-TRA-0001";

    public Map<String, Map<String, Object>> add(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        log.debug("Inside add");
        provider = ProviderFactory.getProvider(domain);
        return provider.setDefaults(domain, parameters);
    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entityType, String id,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, NOT_IMPLEMENTED);
    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, NOT_IMPLEMENTED);
    }

    public Map<String, Map<String, Object>> find(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, NOT_IMPLEMENTED);
    }

    public Map<String, Map<String, Object>> findById(String domain,
            String entityType, String id,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, NOT_IMPLEMENTED);
    }

    public Map<String, Map<String, Object>> read(String domain,
            String entityType, Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, NOT_IMPLEMENTED);
    }

    public void setLogTracer(LogTracer log) {
        this.log = log;

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
}
