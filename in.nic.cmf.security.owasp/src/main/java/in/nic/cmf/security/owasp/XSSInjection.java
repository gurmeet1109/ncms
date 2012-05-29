package in.nic.cmf.security.owasp;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.security.contract.Injection.Injection;

import org.owasp.esapi.ESAPI;

// Cross-Site Scripting (XSS)
// https://www.owasp.org/index.php/XSS_%28Cross_Site_Scripting%29_Prevention_Cheat_Sheet
// RULE #0 - Never Insert Untrusted Data Except in Allowed Locations
// RULE #1 - HTML Escape Before Inserting Untrusted Data into HTML Element
// Content
// RULE #2 - Attribute Escape Before Inserting Untrusted Data into HTML Common
// Attributes
// RULE #3 - JavaScript Escape Before Inserting Untrusted Data into JavaScript
// Data Values
// RULE #4 - CSS Escape And Strictly Validate Before Inserting Untrusted Data
// into HTML Style Property Values
// RULE #5 - URL Escape Before Inserting Untrusted Data into HTML URL Parameter
// Values
// RULE #6 - Use an HTML Policy engine to validate or clean user-driven HTML in
// an outbound way
// RULE #7 - Prevent DOM-based XSS
public class XSSInjection extends Injection {

    private LogTracer log;

    public XSSInjection(String domain) {
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
        String canonical = ESAPI.encoder().encodeForHTML(userText);
        if (!userText.equals(canonical)) {
            log.debug("--START-->\nXSSInjection -> userText:\n" + userText
                    + " \n Converted into:\n" + canonical + "\n<--END--");
            addMatchedUnsafeWord(userText);
        }
        return canonical;
    }
}
