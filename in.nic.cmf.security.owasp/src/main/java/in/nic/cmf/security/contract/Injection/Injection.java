package in.nic.cmf.security.contract.Injection;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

public abstract class Injection {
    private LogTracer          log;
    private final List<String> tracedUnsafeWordList = new ArrayList<String>();
    private String             content              = null;
    private String             domain;

    private void setLogger(String domain) {

        log = new LogTracer(domain, "owasp");
    }

    public Injection(String domain) {
        this.domain = domain;
        setLogger(domain);
    }

    protected abstract String validate(String userText);

    private boolean isEmpty(Object value) {
        String data = value.toString();
        return isEmpty(data);
    }

    protected boolean isEmpty(String value) {
        if (value == null) return true;
        if (value.equals("null")) {
            return true;
        }
        return value.isEmpty();
    }

    public boolean isSafe(String xmlContent) throws GenericException {
        deleteMatchedUnsafeWord();
        log.debug("XML content Encode process START....");
        JSONObject jsonObj = getXMLJsonObject(xmlContent);
        HashMap<String, Object> mapResponse = new HashMap<String, Object>();
        jsonValueIterator(jsonObj, mapResponse);
        log.debug("Response MAP : " + mapResponse);
        String encodedData = (String) FormatXml.getInstanceof(domain).out(
                (HashMap<String, Object>) mapResponse);
        String encodedPlusCDATAXML = "";
        try {
            PropertiesUtil propUtil = PropertiesUtil.getInstanceof(domain,
                    "owasp");
            InputStream is = new FileInputStream(new File(
                    propUtil.get("location") + propUtil.get("fieldMapping")));
            FormatJson formatJson = FormatJson.getInstanceof(domain);
            HashMap<String, Object> fieldMap = formatJson.in(IOUtils
                    .toString(is));
            FormatXml formatxml = FormatXml.getInstanceof(domain);
            encodedPlusCDATAXML = (String) formatxml.out(mapResponse, fieldMap);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-OWASP-0002");
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-OWASP-0000");
        }
        log.debug("XML content Encode process END....");
        mapResponse.clear();
        setContent(encodedPlusCDATAXML);
        return isSafeResponse();
    }

    protected JSONObject getXMLJsonObject(String xmlContent)
            throws GenericException {
        try {
            HashMap<String, Object> xmlHashMapValue = FormatXml.getInstanceof(
                    domain).in(xmlContent);
            JSONObject jsonObj = (JSONObject) FormatJson.getInstanceof(domain)
                    .out(xmlHashMapValue);
            xmlHashMapValue.clear();
            return jsonObj;
        } catch (GenericException e) {
            return (JSONObject) null;
        } catch (Exception e) {
            return (JSONObject) null;
        }

    }

    public boolean isSafeString(String value) throws GenericException {
        deleteMatchedUnsafeWord();
        setContent(validate(value));
        return isSafeResponse();
    }

    protected boolean isSafeResponse() throws GenericException {
        if (matchedUnsafeWordCount() > 0) {
            deleteMatchedUnsafeWord();
            return false;
        }
        return true;
    }

    protected void jsonValueIterator(JSONObject o, Map<String, Object> b) {
        Iterator<?> ji = o.keys();
        while (ji.hasNext()) {
            String key = (String) ji.next();
            Object val = o.get(key);
            if (isEmpty(val)) {
                b.put(key, "");
                continue;
            }
            if (val.getClass() == JSONObject.class) {
                Map<String, Object> sub = new HashMap<String, Object>();
                if (isEmpty(val)) {
                    continue;
                } else {
                    jsonValueIterator((JSONObject) val, sub);
                    b.put(key, sub);
                }
            } else if (val.getClass() == JSONArray.class) {
                List<Object> l = new ArrayList<Object>();
                JSONArray arr = (JSONArray) val;
                for (int a = 0; a < arr.size(); a++) {
                    Map<String, Object> sub = new HashMap<String, Object>();
                    Object element = arr.get(a);
                    if (element instanceof JSONObject) {
                        jsonValueIterator((JSONObject) element, sub);
                        l.add(sub);
                    } else {
                        element = validate((String) element);
                        l.add(element);
                    }
                }
                b.put(key, l);
            } else {
                String keyLower = key.toLowerCase();
                if (isEmpty(val)) {
                    val = "";
                }
                if ((!keyLower.endsWith("date"))
                        && (!keyLower.endsWith("filepath"))
                        && (!keyLower.endsWith("seourl"))
                        && (!keyLower.endsWith("associatediapath"))
                        && (!keyLower.endsWith("metadatainfo"))
                        && (!isEmpty(val))) {
                    val = validate((String) val);
                }
                b.put(key, val);
            }
        }
    }

    protected void addMatchedUnsafeWord(String unsafeword) {
        tracedUnsafeWordList.add(unsafeword);
    }

    public void deleteMatchedUnsafeWord() {
        tracedUnsafeWordList.clear();
    }

    protected int matchedUnsafeWordCount() {
        return tracedUnsafeWordList.size();
    }

    protected String matchedUnsafeWords() {
        return tracedUnsafeWordList.toString();
    }

    protected void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
