package in.nic.cmf.sitebuilder;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("sitebuilder")
public class SiteBuilderController implements CMFService {

    private SiteBuilderService          sbService;
    private LogTracer                   log;
    public static SiteBuilderController sbController;

    public static SiteBuilderController getInstance() {

        if (sbController == null) {
            sbController = new SiteBuilderController();
        }
        return sbController;
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
            sbService = SiteBuilderService.getInstanceof(domain);
        return sbService.post(domain, entity, parameters);
    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        sbService = SiteBuilderService.getInstanceof(domain);
        return sbService.delete(domain, entity, id, parameters);

    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }

    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }

    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }

    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }

   
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }

   
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-SB-0001");
    }
    public void setLogTracer(LogTracer sitebuilder) {
        log = sitebuilder;
    }
}
