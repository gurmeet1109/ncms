package in.nic.cmf.security.owasp;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CrossSiteScript extends Injection {
    private final List<String> unsafeWordList = new ArrayList<String>();
    HashMap<String, String>    hashmap        = new HashMap<String, String>();
    private static LogTracer   log;
    private String             domain;

    public CrossSiteScript(String domain) {
        super(domain);
        this.domain = domain;
        setLogger(domain);
        addToUnsafeWordList(":expr");
        addToUnsafeWordList("?import");
        addToUnsafeWordList("@import");
        addToUnsafeWordList("cmd=");
        addToUnsafeWordList("data:");
        addToUnsafeWordList("dynsrc=");
        addToUnsafeWordList("image/svg+xml");
        addToUnsafeWordList("implementation=");
        addToUnsafeWordList("list-style-image");
        addToUnsafeWordList("livescript:");
        addToUnsafeWordList("text/x-scriptlet");
        addToUnsafeWordList("vbscript:");
        addToUnsafeWordList("javascript:");
        addToUnsafeWordList("xss");
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "Owasp");
    }

    @Override
    protected String validate(String userText) {
        String logmsg = "*<-- CrossSiteScript input :" + userText
                + " \nMatched Un-SafeWords :";
        if (!isEmpty(userText)) {
            Iterator<String> itr = getUnsafeWordList().iterator();
            while (itr.hasNext()) {
                String unsafeword = itr.next();
                userText = userText.toLowerCase();
                if (userText.contains(unsafeword)) {
                    logmsg += unsafeword + "\n";
                    addMatchedUnsafeWord(unsafeword);
                }
            }
        }
        logmsg += " " + matchedUnsafeWordCount() + " Malcious data found...";
        log.debug(logmsg);
        return userText;
    }

    @Override
    protected boolean isSafeResponse() throws GenericException {
        if (matchedUnsafeWordCount() > 0) {
            String maliciousCodes = matchedUnsafeWords();
            deleteMatchedUnsafeWord();
            throw new GenericException(domain, "ERR-OWASP-0001",
                    "Malcious Found : " + maliciousCodes);
        }
        return true;
    }

    // protected boolean isSafeResponse1() throws GenericException {
    // if (matchedUnsafeWordCount() > 0) {
    // String maliciousCodes = matchedUnsafeWords();
    // deleteMatchedUnsafeWord();
    // throw new GenericException("ERR-OWASP-0001", "Malcious Found : "
    // + maliciousCodes);
    // }
    // return true;
    // }

    private void addToUnsafeWordList(String maliciousText) {
        unsafeWordList.add(maliciousText);
    }

    private List<String> getUnsafeWordList() {
        return unsafeWordList;
    }

}
