package in.nic.cmf.dfbusinessflow;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.dfbusinessflow.businesslogic.BFactory;
import in.nic.cmf.dfbusinessflow.businesslogic.BService;
import in.nic.cmf.dfbusinessflow.businesslogic.RulesModel;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("workflow")
public class BusinessFlowImpl implements CMFService {

    private BService   bservice;
    private LogTracer  log;
    private RulesModel rulesModel;

    public void setLogTracer(LogTracer logTracer) {
        this.log = logTracer;
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> requestDetails) {
        log.debug("bussinessflow add domain:" + domain);
        bservice = BFactory.getInstance(domain, requestDetails);
        bservice.setLogTracer(log);
        return bservice.process(domain, requestDetails);
    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        rulesModel = RulesModel.getInstanceof(domain);
        rulesModel.setLogTracer(log);
        return rulesModel.deleteRule(domain, id, parameters);
    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> requestDetails) {
        throw new GenericException(domain, "ERR-BF-0000");
    }

    public Map<String, Map<String, Object>> find(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) {
        throw new GenericException(arg0, "ERR-BF-0000");
    }

    public Map<String, Map<String, Object>> findById(String arg0, String arg1,
            String arg2, Map<String, Map<String, Object>> arg3) {
        throw new GenericException(arg0, "ERR-BF-0000");
    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> requestDetails) {
        throw new GenericException(domain, "ERR-BF-0000");
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
