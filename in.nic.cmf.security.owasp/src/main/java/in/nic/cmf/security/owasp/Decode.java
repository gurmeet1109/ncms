package in.nic.cmf.security.owasp;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Encoder;

public class Decode {

    // public final static Decode decode = new Decode();

    private static LogTracer               log;
    private static HashMap<String, Decode> hashDecode = new HashMap<String, Decode>();

    private Decode(String domain) {
        setLogger(domain);
    }

    // public static Decode getInstance() {
    // return decode;
    // }

    public static Decode getInstanceof(String domain) {
        if (!hashDecode.containsKey(domain)) {
            hashDecode.put(domain, new Decode(domain));
        }
        return hashDecode.get(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "Owasp");
    }

    public String decode(String encodedData) throws GenericException {
        if (isEmpty(encodedData)) {
            return encodedData;
        }
        Encoder instance = ESAPI.encoder();
        String original = instance.canonicalize(encodedData, false);
        log.debug("*--> DECODE Process START\n En-Coded DATA : \n"
                + encodedData + "\nDe-Coded Data : \n" + original
                + "\n*<--DECODE Process END");
        return original;
    }

    private boolean isEmpty(String data) {
        if (data == null) {
            return true;
        }
        return (data.trim()).isEmpty();
    }
}
