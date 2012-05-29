package in.nic.cmf.sem;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("sem")
public class SemController implements CMFService {

    private SemService          semService;
    public LogTracer            log;

    public static SemController semController;

    public static SemController getInstance() {

        if (semController == null) {
            semController = new SemController();
        }
        return semController;
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {

        semService = SemService.getInstanceof(domain);
        return semService.post(domain, entity, parameters);

    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {

        throw new GenericException(domain, "ERR-SEM-0001");

    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SEM-0001");
    }

    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SEM-0001");
    }

    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String arg2,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SEM-0001");
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SEM-0001");
    }

    public void setLogTracer(LogTracer sem) {
        log = sem;
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
