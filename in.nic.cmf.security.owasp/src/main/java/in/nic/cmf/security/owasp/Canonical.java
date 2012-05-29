package in.nic.cmf.security.owasp;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Canonical {

    private LogTracer       canlog;
    private List<Injection> injections = new ArrayList<Injection>();

    public Canonical(String domain) {
        setLogger(domain);
        injections.add(new XSSInjection(domain));
        injections.add(new XmlInjection(domain));
        injections.add(new SQLInjection(domain));
    }

    private void setLogger(String domain) {
        canlog = new LogTracer(domain, "owasp");
    }

    public String getData(String data) throws GenericException {
        if (isEmpty(data)) {
            return data;
        }
        Iterator<Injection> itr = injections.iterator();
        boolean isSafeData = true;
        String logmsg = "";
        while (itr.hasNext()) {
            data = data.trim();
            Injection injection = itr.next();
            logmsg = "in " + injection.getClass().getSimpleName() + "...\n";
            if ((data.startsWith("<")) && (data.endsWith(">"))) {
                isSafeData = injection.isSafe(data);
            } else {
                isSafeData = injection.isSafeString(data);
            }
            if (!isSafeData) {
                logmsg += "--START--> %%% \n" + data;
                data = injection.getContent();
                logmsg += " <-- *** translated into *** --> " + data
                        + "\n<--END--";
                canlog.debug(logmsg);
            }
        }
        return data;
    }

    private boolean isEmpty(String data) {
        if (data == null) {
            return true;
        }
        return data.isEmpty();
    }

}
