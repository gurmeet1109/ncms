package in.nic.cmf.dataservices;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class DataservicesImpl implements CMFService {

    // private ServiceImpl service = new ServiceImpl();

    @Autowired
    private ServiceHelper service;

    private LogTracer log = null;

    @Override
    public HashMap<String, HashMap<String, Object>> add(String arg0,
	    String arg1, HashMap<String, HashMap<String, Object>> arg2)
	    throws GenericException {
	System.out.println("Inside add");
	log.methodEntry(this.getClass().getSimpleName() + ".postEntities()");
	String strJson = "";
	String result = "";
	String format = "";
	try {
	    HashMap<String, String> userContext = (HashMap<String, String>) arg2
		    .get("input").get("userContext");
	    System.out.println("before result");
	    System.out.println(arg2.get("input").get("content").toString());

	    if (null != arg2.get("input").get("format")) {
		format = arg2.get("input").get("format").toString();
	    }
	    System.out.println(format);
	    result = service.add(userContext, arg0,
		    arg2.get("input").get("content").toString(), format);
	    System.out.println(result);
	    log.methodExit(this.getClass().getSimpleName() + ".postEntities()");
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":postEntities()", arg0, e);
	}
	return buildResponseMap(arg2, result);
    }

    @Override
    public HashMap<String, HashMap<String, Object>> deleteByID(String arg0,
	    String arg1, String arg2,
	    HashMap<String, HashMap<String, Object>> arg3)
	    throws GenericException {
	System.out.println("Inside deleteByID");
	log.methodEntry(this.getClass().getSimpleName() + ".deleteEntityById()");
	Canonical canonical = Canonical.getInstance();
	String domain = canonical.getData(arg0);
	String entity = canonical.getData(arg1);
	String id = canonical.getData(arg2);
	try {
	    service.remove(arg3, domain, entity, id);
	    log.methodExit(this.getClass().getSimpleName()
		    + ".deleteEntityById()");
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":deleteEntityById()", domain, e);
	}
	return buildResponseMap(arg3, arg2);
    }

    @Override
    public HashMap<String, HashMap<String, Object>> deleteByQuery(String arg0,
	    String arg1, HashMap<String, HashMap<String, Object>> arg2)
	    throws GenericException {
	throw new GenericException("ERR-DS-0011");
    }

    @Override
    public HashMap<String, HashMap<String, Object>> find(String arg0,
	    String arg1, HashMap<String, HashMap<String, Object>> arg2)
	    throws GenericException {
	log.debug("Inside Find Method");
	String returnCollection = "";
	try {
	    HashMap<String, Object> input = arg2.get("input");
	    System.out.println(input.get("format"));
	    if (null == input.get("format")) {
		input.put("format", "xml");
	    }
	    arg2.put("input", input);
	    System.out.println(arg2);
	    returnCollection = service.searchAll(arg0, arg1, arg2);
	    // System.out.println("jdfhkdshfkjdsf :::: "+returnCollection);
	    log.methodExit(this.getClass().getSimpleName()
		    + ".getEntityBySearch()");
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":getEntityBySearch()", arg0 + "/"
		    + arg1, e);
	}
	return buildResponseMap(arg2, returnCollection);
    }

    @Override
    public HashMap<String, HashMap<String, Object>> findById(String arg0,
	    String arg1, String arg2,
	    HashMap<String, HashMap<String, Object>> arg3)
	    throws GenericException {
	System.out.println("Inside findById");
	String returnCollection = "";
	log.methodEntry(this.getClass().getSimpleName() + ".getEntity()");
	try {
	    HashMap<String, Object> input = arg3.get("input");
	    Map<String, String> userContext = (Map<String, String>) input
		    .get("userContext");

	    returnCollection = service.getById(arg3, arg0, arg1, arg2);
	    log.methodExit(this.getClass().getSimpleName() + ".getEntity()");
	} catch (GenericException e) {
	    log.error(e.getMessage());
	    throw e;
	} catch (Exception e) {
	    log.error(e.getMessage());
	    throw new GenericException("ERR-DS-0000", this.getClass()
		    .getSimpleName() + ":getEntity()", arg0 + "/" + arg1, e);
	}
	return buildResponseMap(arg3, returnCollection);
    }

    @Override
    public void setLogTracer(LogTracer arg0) {
	this.log = arg0;

    }

    private HashMap<String, HashMap<String, Object>> buildResponseMap(
	    HashMap<String, HashMap<String, Object>> arg0, String content) {
	HashMap<String, Object> response = new HashMap<String, Object>();
	response.put("statusCode", "200");
	response.put("content", content);
	arg0.put("output", response);
	return arg0;
    }

}
