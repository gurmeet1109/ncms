package in.nic.cmf.auditing;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("audit")
public class AuditingController implements CMFService {

    private LogTracer       log;
    private AuditingService auditingService;

    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-AUD-0001");
    }

    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> queryParams) {
        auditingService = AuditingService.getInstanceof(domain);
        return auditingService.logGet(domain, queryParams);
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-AUD-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-AUD-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-AUD-0001");
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-AUD-0001");
    }

    @Override
    public void setLogTracer(LogTracer auditingLog) {
        this.log = auditingLog;

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
