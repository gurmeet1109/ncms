package in.nic.cmf.appflow;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("appflow")
public class AppflowController implements CMFService {

    private LogTracer      log;
    private AppflowService appflowService;

    // public AppflowController() {
    // log = new LogTracer("appflow");
    // setLogTracer(log);
    // }

    public Map<String, Map<String, Object>> add(String domainName,
            String entityType, Map<String, Map<String, Object>> requestDetails) {
        log.debug("In Appflowcontroller.add: " + requestDetails);
        return AppflowService.getInstanceof(domainName).post(domainName,
                entityType, requestDetails);
    }

    public Map<String, Map<String, Object>> deleteByID(String domainName,
            String entityType, String id,
            Map<String, Map<String, Object>> requestDetails) {
        log.debug("In Appflowcontroller.deleteById: " + requestDetails);
        return AppflowService.getInstanceof(domainName).delete(domainName,
                entityType, id, null, requestDetails);
    }

    public Map<String, Map<String, Object>> deleteByQuery(String domainName,
            String subquery, Map<String, Map<String, Object>> requestDetails) {
        log.debug("In Appflowcontroller.deleteByQuery: " + requestDetails);
        return AppflowService.getInstanceof(domainName).delete(domainName,
                null, null, subquery, requestDetails);
    }

    public Map<String, Map<String, Object>> find(String domainName,
            String entityType, Map<String, Map<String, Object>> requestDetails) {
        log.debug("In Appflowcontroller.find: " + requestDetails);
        return AppflowService.getInstanceof(domainName).get(domainName,
                entityType, requestDetails);
    }

    public Map<String, Map<String, Object>> findById(String domainName,
            String entityType, String id,
            Map<String, Map<String, Object>> requestDetails) {
        log.debug("In Appflowcontroller.findById: " + requestDetails);
        return AppflowService.getInstanceof(domainName).getByFilename(
                domainName, entityType, id, requestDetails);
    }

    public Map<String, Map<String, Object>> read(String domainName,
            String entityType, Map<String, Map<String, Object>> requestDetails) {
        throw new GenericException(domainName, "ERR-AF-0000",
                "Unimplemented method.");
    }

    public void setLogTracer(LogTracer logTracer) {
        this.log = logTracer;

    }

    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

}
