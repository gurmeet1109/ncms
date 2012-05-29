package in.nic.cmf.dataservices;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class Helper {

    private PropertiesUtil propertiesutil = new PropertiesUtil("dataservices");

    private LogTracer log = null;

    private Helper() {
	log = new LogTracer("DataServicesTraceLogger", true, true, true, true,
		true);
    }

    private static Helper helper = null;

    public static Helper getInstance() {
	if (helper == null) {
	    helper = new Helper();
	}
	return helper;
    }

    public String getUniqueContent() {
	String jsonTxt = "";
	try {
	    URL domainURI = getClass().getClassLoader().getResource(
		    "unique.json");
	    // String jsonPath = domainURI.getFile();
	    InputStream iStream = new FileInputStream(domainURI.getFile());
	    jsonTxt = IOUtils.toString(iStream);
	} catch (Exception ex) {
	    log.debug("In exception of getContentFromResource:"
		    + ex.getMessage());
	}
	return jsonTxt;
    }

    // http://124.7.228.236:8600/applicationflow-1.0.0/kavitha.com?q=&entitytype=validations,accesscontrollist,workflow,workflowusermap,workflowmodelmap&lastmodifieddate=[2011-11-18T21:32:28Z%20%20TO%202011-11-18T22:32:28Z]
    /*
     * public HashMap checkUpdates(String domainName, Map userContext) {
     * ServiceClient client = ServiceClient.getInstance("applicationflow");
     * DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
     * Date date = new Date(); String currentDateTime = dateFormat.format(date);
     * Calendar cal = Calendar.getInstance(); cal.setTime(date);
     * cal.add(cal.HOUR, -1); Date dDate = cal.getTime(); String diffDateTime =
     * dateFormat.format(dDate); String dateQuery = URLEncoder.encode("[" +
     * diffDateTime + " TO " + currentDateTime + "]");
     * 
     * String generateEntities = ""; for (String eachEntity :
     * propertiesutil.getProperty("generate").split( ",")) {
     * log.debug("eachentity check inside generaterules block:" + eachEntity);
     * generateEntities += eachEntity + ","; }
     * 
     * String subQuery = "q=&entitytype=" + generateEntities +
     * "&lastmodifieddate=" + dateQuery; String strResponse = (String)
     * client.get(domainName, subQuery, userContext).get("responseStringBody");
     * log.debug("string xml in checkUpdates:" + strResponse);
     * System.out.println("before formatting:" + strResponse); HashMap response
     * = (HashMap) FormatXml.getInstance().in(strResponse) .get("Collections");
     * System.out.println("response hashmap:" + response);
     * log.debug("in checkUpdates:" + response); return response; }
     */

}
