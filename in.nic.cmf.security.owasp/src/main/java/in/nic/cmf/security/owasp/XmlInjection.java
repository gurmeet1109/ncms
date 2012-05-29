package in.nic.cmf.security.owasp;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;

import org.owasp.esapi.ESAPI;

public class XmlInjection extends Injection {

    private LogTracer log;

    public XmlInjection(String domain) {
        super(domain);
        setLogger(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "owasp");
    }

    @Override
    protected String validate(String userText) {
        if (isEmpty(userText)) {
            return userText;
        }
        String canonical = ESAPI.encoder().encodeForXML(userText);
        if (!userText.equals(canonical)) {
            log.debug("--START-->\n XmlInjection -> userText:\n" + userText
                    + " \n Converted into:\n" + canonical + "\n<--END--");
            addMatchedUnsafeWord(userText);
        }
        return canonical;
    }
}
