package in.nic.cmf.util;
import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class CommonUtils {
    // private LogTracer log = new LogTracer(
    // "UtilTraceLogger",
    // true, true, true,
    // true, true);
    private LogTracer                           log;
    private PropertiesUtil                propUtil  = null;
    private static HashMap<String, CommonUtils> hashCommonUtils = new HashMap<String, CommonUtils>();

    private CommonUtils(String domain) {
        setLogger(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                false);
        propUtil = PropertiesUtil.getInstanceof(domain, "util");
    }

    public static CommonUtils getInstanceOf(String domain) {
        if (!hashCommonUtils.containsKey(domain)) {
            hashCommonUtils.put(domain, new CommonUtils(domain));
        }
        return hashCommonUtils.get(domain);
    }

    public boolean isEmpty(String value) {
        log.methodEntry(this.getClass().getSimpleName() + ".isEmpty()");
        if (value != null) {
            value = value.trim();
            log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
            return value.isEmpty(); // true if length() is 0, otherwise false
        }
        log.debug("isEmpty ==> true");
        log.methodExit(this.getClass().getSimpleName() + ".isEmpty()");
        return true;
    }
    
    public String convertWithCDATA(String domain,
			Map<String, Object> collections){
    	log.methodEntry("convertWithCData entry ");
		FormatJson formatJson = FormatJson.getInstanceof(domain);
		InputStream is;
		String inputContent = "";
		try {
			
			log.debug("input : "+collections);
			String path = propUtil.get("location") + domain + "/resources/"
			+ propUtil.get("fieldMapping");
			log.debug("path is:" + path);
			is = new FileInputStream(new File(path));
			HashMap<String, Object> fieldMap = formatJson.in(IOUtils
					.toString(is));
			log.debug("formatjson.in response fieldMap is : "+fieldMap);
			inputContent = (String) FormatXml.getInstanceof(domain).out(
					(HashMap<String, Object>) collections, fieldMap);
			log.debug("inputcontent:" + inputContent);
		} catch (FileNotFoundException e) {
			log.error("In FFE: " + e.getMessage());
			throw new GenericException(domain, "ERR-CMF-0001", this.getClass()
					.getSimpleName() + ".getInputContent()", e);
		} catch (IOException e) {
			log.error("In IOE: " + e.getMessage());
			throw new GenericException(domain, "ERR-CMF-0001", this.getClass()
					.getSimpleName() + ".getInputContent()", e);
		} catch (GenericException e) {
			log.error("In GE:" + e.getMessage());
			throw e;
		}
		return inputContent;
    	
	}

}
