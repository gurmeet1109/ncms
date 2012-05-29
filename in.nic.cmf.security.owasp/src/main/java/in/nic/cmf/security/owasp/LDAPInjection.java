package in.nic.cmf.security.owasp;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;

import org.owasp.esapi.ESAPI;

public class LDAPInjection extends Injection {

    private LogTracer log;

    public LDAPInjection(String domain) {
        super(domain);
        setLogger(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "owasp");
    }

    @Override
    protected String validate(String userText) {
        return encode(userText, "ldap");
    }

    public String encodeForDN(String userText) {
        return encode(userText, "dn");
    }

    private String encode(String userText, String type) {
        if (isEmpty(userText)) {
            return userText;
        }
        String canonical = userText;
        if (type.equalsIgnoreCase("ldap")) {
            canonical = ESAPI.encoder().encodeForLDAP(userText);
        } else if (type.equalsIgnoreCase("dn")) {
            canonical = ESAPI.encoder().encodeForDN(userText);
        }
        if (!userText.equals(canonical)) {
            log.debug("--START-->\n LDAPInjection -> userText:\n" + userText
                    + " \n Converted into:\n" + canonical + "\n<--END--");
            addMatchedUnsafeWord(userText);
        }
        return canonical;
    }
}
