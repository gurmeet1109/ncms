package in.nic.cmf.mediahandler;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("media")
public class MediaHandlerServiceImpl implements CMFService {

    private LogTracer log;

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        log.debug("add entry.");
        MediaService.getInstanceof(domain).setLog(log);
        log.debug("After setting log.");
        MediaService.getInstanceof(domain).processRequest(domain, entity,
                parameters);
        return parameters;
    }

    public Map<String, Map<String, Object>> deleteByID(String arg0,
            String arg1, String arg2, Map<String, Map<String, Object>> arg3) {
        throw new GenericException(arg0, "ERR-MH-0000", "Unimplemented method.");
    }

    public Map<String, Map<String, Object>> deleteByQuery(String arg0,
            String arg1, Map<String, Map<String, Object>> arg2) {
        throw new GenericException(arg0, "ERR-MH-0000", "Unimplemented method.");
    }

    public Map<String, Map<String, Object>> find(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) {
        throw new GenericException(arg0, "ERR-MH-0000", "Unimplemented method.");
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        MediaService.getInstanceof(domain).setLog(log);
        return MediaService.getInstanceof(domain).findById(domain, entity, id,
                parameters);
    }

    public Map<String, Map<String, Object>> read(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) {
        throw new GenericException(arg0, "ERR-MH-0000", "Unimplemented method.");
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
