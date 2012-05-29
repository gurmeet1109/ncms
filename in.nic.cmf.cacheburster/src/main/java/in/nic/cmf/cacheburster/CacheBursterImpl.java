package in.nic.cmf.cacheburster;

import java.util.HashMap;
import java.util.Map;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

@ServiceName ("cacheburster")
public class CacheBursterImpl implements CMFService{

	private LogTracer    log;
	private final String NOT_IMPLEMENTED = "ERR-CB-0001";
	private CacheBursterService service = null;
	private static HashMap<String, CacheBursterImpl> cacheImpl = new HashMap<String, CacheBursterImpl>();

	private CacheBursterImpl(String domain){
		service = CacheBursterService.getInstance(domain);
		log = new LogTracer(domain,"cacheburster");
	}

	public static CacheBursterImpl getInstance(String domain) {
		if (!cacheImpl.containsKey(domain)) {
			System.out.println("not contains in object pool.");
			cacheImpl.put(domain, new CacheBursterImpl(domain));
		}
		System.out.println("contains in object pool:"+domain);
		return cacheImpl.get(domain);
	}

	//GET
	public Map<String, Map<String, Object>> find(String domain, String entity,
			Map<String, Map<String, Object>> parameters) {

		//CacheBursterService service = CacheBursterService.getInstanceOf(domain);
		return service.find(domain, entity, parameters);
	}

	//GET_BY_ID
	public Map<String, Map<String, Object>> findById(String domain, String entity,
			String id, Map<String, Map<String, Object>> parameters) {
		//CacheBursterService service = CacheBursterService.getInstanceOf(domain);
		return service.find(domain, entity, parameters);
	}

	//POST
	public Map<String, Map<String, Object>> add(String domain, String entity,
			Map<String, Map<String, Object>> parameters) {
		//CacheBursterService service = CacheBursterService.getInstanceOf(domain);
		return service.add(domain, entity, parameters);
	}

	public Map<String, Map<String, Object>> addManage(String domain, String arg1,
			String arg2, Map<String, Map<String, Object>> arg3) {
		throw new GenericException(domain, NOT_IMPLEMENTED);
	}
	public Map<String, Map<String, Object>> deleteByID(String domain,
			String arg1, String arg2, Map<String, Map<String, Object>> arg3) {
		throw new GenericException(domain, NOT_IMPLEMENTED);
	}
	public Map<String, Map<String, Object>> deleteByQuery(String domain,
			String arg1, Map<String, Map<String, Object>> arg2) {
		throw new GenericException(domain, NOT_IMPLEMENTED);
	}
	public Map<String, Map<String, Object>> deleteManage(String domain,
			String arg1, String arg2, Map<String, Map<String, Object>> arg3) {
		throw new GenericException(domain, NOT_IMPLEMENTED);
	}
	public Map<String, Map<String, Object>> findManage(String domain,
			String arg1, String arg2, Map<String, Map<String, Object>> arg3) {
		throw new GenericException(domain, NOT_IMPLEMENTED);
	}
	public Map<String, Map<String, Object>> read(String domain, String arg1,
			Map<String, Map<String, Object>> arg2) {
		throw new GenericException(domain, NOT_IMPLEMENTED);
	}
	public void setLogTracer(LogTracer log) {
		this.log = log;
	}

}
