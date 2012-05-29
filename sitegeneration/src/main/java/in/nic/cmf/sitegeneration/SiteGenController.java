package in.nic.cmf.sitegeneration;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

@ServiceName("sitegeneration")
public class SiteGenController implements CMFService {
    private LogTracer log;
    public static SiteGenController sitegenController;
    private SiteGenService sitegenService;

    public static SiteGenController getInstance() {
	if (sitegenController == null) {
	    sitegenController = new SiteGenController();
	}
	return sitegenController;
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
	    Map<String, Map<String, Object>> parameters) {
	sitegenService = SiteGenService.getInstanceof(domain);
	return sitegenService.post(domain, entity, parameters);
    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
	    String entity, String id,
	    Map<String, Map<String, Object>> parameters) {
	sitegenService = SiteGenService.getInstanceof(domain);
	return null;
    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
	    String entity, Map<String, Map<String, Object>> parameters) {
	sitegenService = SiteGenService.getInstanceof(domain);
	return null;
    }

    public Map<String, Map<String, Object>> find(String domain, String entity,
	    Map<String, Map<String, Object>> parameters) {
	sitegenService = SiteGenService.getInstanceof(domain);
	return null;
    }

    public Map<String, Map<String, Object>> findById(String domain,
	    String entity, String id,
	    Map<String, Map<String, Object>> parameters) {
	sitegenService = SiteGenService.getInstanceof(domain);
	return null;
    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
	    Map<String, Map<String, Object>> parameters) {
	throw new GenericException(domain, "ERR-SG-0000");
    }

    public void setLogTracer(LogTracer sitelog) {
	log = sitelog;
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

    public Map<String, Map<String, Object>> addManage(String arg0, String arg1,
	    Map<String, Map<String, Object>> arg2) {
	// TODO Auto-generated method stub
	return null;
    }

    public Map<String, Map<String, Object>> deleteManage(String arg0,
	    String arg1, Map<String, Map<String, Object>> arg2) {
	// TODO Auto-generated method stub
	return null;
    }

    public Map<String, Map<String, Object>> findManage(String arg0,
	    String arg1, Map<String, Map<String, Object>> arg2) {
	// TODO Auto-generated method stub
	return null;
    }
}
