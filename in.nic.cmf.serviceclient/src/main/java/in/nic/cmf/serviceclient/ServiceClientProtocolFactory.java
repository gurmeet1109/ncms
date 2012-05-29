package in.nic.cmf.serviceclient;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.providers.CMFServiceScanner;
import in.nic.cmf.serviceclient.providers.HttpProvider;
import in.nic.cmf.serviceclient.providers.MQProvider;

import java.util.Arrays;
import java.util.List;

public class ServiceClientProtocolFactory {
    // private static PropertiesUtil putil = new
    // PropertiesUtil("serviceclient");
    // private static LogTracer log = new LogTracer("serviceclient");
    private static PropertiesUtil putil;
    private static LogTracer      log;

    public static CMFService getInstance(String domain, String serviceName,
            String action) {
        putil = PropertiesUtil.getInstanceof(domain, "serviceclient");
        log = new LogTracer(domain, "serviceclient");
        return findProtocol(domain, serviceName, action);
    }

    private static CMFService findProtocol(String domain, String serviceName,
            String action) {
        log.debug("inside findProtocol");
      //  CMFService cmf = null;
        
        CMFService cmf = CMFServiceScanner.getInstanceOf(domain, serviceName);
        if(cmf==null){
         
        log.debug("In else of ServiceClientFactory");

        String key = "async" + action + "=";
        String checkAsync = "false";
        String eachService = putil.getProperty(serviceName);
        if (eachService.contains(key)) {
            checkAsync = ((String[]) eachService.split(key))[1];
            if (checkAsync.contains(putil.getProperty("delimiter"))) {
                List<String> prop = Arrays.asList(checkAsync.split("\\"
                        + putil.getProperty("delimiter")));
                checkAsync = prop.get(0);
            }
        }

        if (checkAsync.equals("false")) {
            log.debug("In HTTP," + action + "," + serviceName);
            cmf = (CMFService) HttpProvider.getInstanceOf(domain, serviceName);
        } else {
            log.debug("In MQ," + action + "," + serviceName);
            cmf = (CMFService) MQProvider.getInstanceOf(domain, serviceName);
        }
         }
        return cmf;
    }
}
