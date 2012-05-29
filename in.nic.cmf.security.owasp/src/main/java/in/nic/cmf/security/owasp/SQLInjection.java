package in.nic.cmf.security.owasp;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

public class SQLInjection extends Injection {

    private LogTracer log;

    public SQLInjection(String domain) {
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
        Codec codec = new MySQLCodec(MySQLCodec.ANSI_MODE);
        String canonical = ESAPI.encoder().encodeForSQL(codec, userText);
        if (!userText.equals(canonical)) {
            log.debug("--START-->\nSQLInjection -> userText:\n" + userText
                    + " \n Converted into:\n" + canonical + "\n<--END--");
            addMatchedUnsafeWord(userText);
        }
        return canonical;
    }
}
