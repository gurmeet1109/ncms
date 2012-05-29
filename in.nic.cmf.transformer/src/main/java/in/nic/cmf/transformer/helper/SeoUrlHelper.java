package in.nic.cmf.transformer.helper;

import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class SeoUrlHelper.
 */
public class SeoUrlHelper {

	/** The log. */
	private LogTracer 	log ;
	
	
	/** The Constant seoUrlHelper. */
	//private static final SeoUrlHelper seoUrlHelper = new SeoUrlHelper();
	private static HashMap<String, SeoUrlHelper> hashSeoUrlHelper = new HashMap<String, SeoUrlHelper>();
    private String domain;
	/**
	 * Instantiates a new seo url helper.
	 * @param domain2 
	 */
	private SeoUrlHelper(String domain){
		this.domain=domain;
		setLogger(domain);
		
	}

	/**
	 * Gets the single instance of SeoUrlHelper.
	 *
	 * @return single instance of SeoUrlHelper
	 */
	
	
	public static SeoUrlHelper getInstanceOf(String domain) {
        if (!hashSeoUrlHelper.containsKey(domain)) {
            hashSeoUrlHelper.put(domain, new SeoUrlHelper(domain));
        }
        return hashSeoUrlHelper.get(domain);
    }
	private void setLogger(String domain) {
        log = new LogTracer(domain, "transformer");
    }
	/**
	 * Gets the sEOURL.
	 *
	 * @param domain the domain
	 * @param h the h
	 * @return the sEOURL
	 */
	public String getSEOURL(String domain, Map<String,Object> h){
		String seoUrl = null;
		try {
			String title = h.get("Title").toString().trim();
			String iaPath = h.get("AssociatedIAPath").toString().trim();
			title = title.replaceAll("[^a-zA-Z0-9 ]+", "");
			seoUrl = title.trim() + "-" + h.get("EntityType").toString().trim()
			+ "-" + h.get("Id").toString().trim() + ".php";
			seoUrl = getSeoUrlWithIaPath(iaPath, domain, seoUrl);
			return seoUrl;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return seoUrl;
	}

	/**
	 * Gets the seo url with ia path.
	 *
	 * @param path the path
	 * @param domain the domain
	 * @param seoUrl the seo url
	 * @return the seo url with ia path
	 */
	private String getSeoUrlWithIaPath(String path, String domain,
			String seoUrl) {
		String seoUrlWithIaPath = "http://" + domain + "/" + seoUrl.replace(" ", "-").toLowerCase();
		if (null != path) {
			String iaPath = getSeoUrlIaPath(path.toLowerCase());
			if (path.equals("") || path.equals("/")) {
				seoUrlWithIaPath = "http://" + domain + "/" + seoUrl.replace(" ", "-").toLowerCase();
			} else {
				seoUrlWithIaPath = "http://" + domain + iaPath + "/" + seoUrl.replace(" ", "-").toLowerCase();
			}
		}
		seoUrlWithIaPath = seoUrlWithIaPath.replaceAll(" ", "_");
		return seoUrlWithIaPath;
	}

	/**
	 * Gets the seo url ia path.
	 *
	 * @param path the path
	 * @return the seo url ia path
	 */
	private String getSeoUrlIaPath(String path) {
		if (path.indexOf("/home") == 0) {
			path = path.substring(5, path.length());
		}
		return path;
	}
	
	/**
	 * Inits the lower case.
	 *
	 * @param value the value
	 * @return the string
	 */
	public String InitLowerCase(String value) {
		return value.substring(0, 1).toLowerCase() + value.substring(1);
	}

}
