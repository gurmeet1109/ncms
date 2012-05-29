package in.nic.cmf.sitegeneration;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.HashMap;

public class SiteGenHelper {
    private static HashMap<String, SiteGenHelper> hashSiteGenHelper = new HashMap<String, SiteGenHelper>();
    private String domain;
    private PropertiesUtil proputil;
    private LogTracer log;

    private SiteGenHelper(String domain) {
	this.domain = domain;
	setLogger(domain);
	proputil = PropertiesUtil.getInstanceof(domain, "sitegeneration");
    }

    private void setLogger(String domain) {
	log = new LogTracer(domain, "sitegeneration");
    }

    public static SiteGenHelper getInstanceof(String domain) {
	if (!hashSiteGenHelper.containsKey(domain)) {
	    hashSiteGenHelper.put(domain, new SiteGenHelper(domain));
	}
	return hashSiteGenHelper.get(domain);
    }

}
