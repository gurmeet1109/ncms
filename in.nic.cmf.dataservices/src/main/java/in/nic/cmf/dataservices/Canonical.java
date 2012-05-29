package in.nic.cmf.dataservices;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;
import in.nic.cmf.security.owasp.SQLInjection;
import in.nic.cmf.security.owasp.XmlInjection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Canonical {
    public static Canonical canonical = null;
    private LogTracer canlog = new LogTracer("searchengine");
    private List<Injection> injections = new ArrayList<Injection>();

    public Canonical() {
	injections.add(new SQLInjection());
	injections.add(new XmlInjection());
    }

    public static Canonical getInstance() {
	if (canonical == null) {
	    canonical = new Canonical();
	}
	return canonical;
    }

    public String getData(String data) throws GenericException {
	Iterator<Injection> itr = injections.iterator();
	boolean isSafeData = true;
	String logmsg = "";
	while (itr.hasNext()) {
	    Injection injection = itr.next();
	    logmsg = "";
	    if (injection instanceof SQLInjection) {
		logmsg = "in SQLInjection...\n";
	    } else if (injection instanceof XmlInjection) {
		logmsg = "in XMLInjection...\n";
	    }

	    if (isEmpty(data)) {
		continue;
	    }

	    // if (data.isEmpty())
	    // continue;
	    data = data.trim();
	    if ((data.startsWith("<")) && (data.endsWith(">"))) {
		isSafeData = injection.isSafe(data);
	    } else {
		isSafeData = injection.isSafeString(data);
	    }

	    logmsg += data;
	    if (!isSafeData) {
		data = injection.getContent();
		logmsg += " <--- **** translated into **** ---> " + data;
		canlog.debug(logmsg);
	    }
	}

	return data;
    }

    private boolean isEmpty(String data) {
	if (data != null) {
	    return data.isEmpty();
	}
	return true;
    }
}