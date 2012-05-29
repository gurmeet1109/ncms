package in.nic.cmf.generate;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName ("generate")
public class GenerateImpl implements CMFService {

    // private Helper h = Helper.getInstance();
    /*
     * public GenerateImpl() {
     * }
     */
    private GenerateService generateService;
    private LogTracer       log;

    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {

        generateService = GenerateService.getInstance(domain);
        generateService.setLogTracer(log);
        return generateService.add(domain, entity, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {

        generateService = GenerateService.getInstance(domain);

        generateService.setLogTracer(log);
        return generateService.delete(domain, entity, id, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String arg0,
            String arg1, Map<String, Map<String, Object>> arg2)
            throws GenericException {
        throw new GenericException(arg0, "ERR-GEN-0003");
    }

    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {

        generateService = GenerateService.getInstance(domain);
        generateService.setLogTracer(log);
        return generateService.get(domain, entity, parameters);
    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-GEN-0003");
    }

    @Override
    public void setLogTracer(LogTracer arg0) {
        this.log = arg0;
    }

    @Override
    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-GEN-0003");
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0003");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0003");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0003");
    }

}
