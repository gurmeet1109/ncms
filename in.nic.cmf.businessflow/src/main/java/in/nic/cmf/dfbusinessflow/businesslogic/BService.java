package in.nic.cmf.dfbusinessflow.businesslogic;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

public interface BService {
    public Map<String, Map<String, Object>> process(String domain,
            Map<String, Map<String, Object>> requestDetails)
            throws GenericException;

    public void setLogTracer(LogTracer logTracer);
}
