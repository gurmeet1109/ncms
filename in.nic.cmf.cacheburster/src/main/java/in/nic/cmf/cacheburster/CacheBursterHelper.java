package in.nic.cmf.cacheburster;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class CacheBursterHelper {

	private LogTracer                                    log;
	public List<String>                                excludeParams;
	private PropertiesUtil                         propUtil;
	private static HashMap<String, CacheBursterHelper> helper = new HashMap<String, CacheBursterHelper>();

	private CacheBursterHelper(String domain) {
		propUtil = PropertiesUtil.getInstanceof(domain, "cacheburster");
		setLogger(domain);
		log.debug("------------------------inside CacheBursterHelper constructor----------------------------");
		
		log.debug("excludeparam from config : Domain :" + domain);
		setExcludeParams();
	}

	public static CacheBursterHelper getInstance(String domain) {	
		return new CacheBursterHelper(domain);
	}

	private void setLogger(String domain) {
		log = new LogTracer(domain, "cacheburster");
	}

	public void setExcludeParams() {
		String excludeParamsValue = propUtil.get("excludeParams");
		log.debug("inside setExcludeParams:"+excludeParamsValue);
		if(excludeParamsValue != null){
			excludeParams = Arrays.asList(excludeParamsValue.split(","));
		}
	}

	public Map<String, String> applyExcludeParams(String domainName, Map<String, String> list) {
		log.methodEntry("Inside applyExcludeParams");
		propUtil = PropertiesUtil.getInstanceof(domainName, "cacheburster");
		System.out.println("//////////////"+propUtil.get("excludeParams"));
		setExcludeParams();
		log.debug("after exclude param");
		try {
			java.util.Iterator<String> items = this.excludeParams.iterator();
			while (items.hasNext()) {
				list.remove(items.next());
			}
			log.debug("after excludeparam: "+list);
			log.methodExit("applyExcludeParams exit");
			return list;
		} catch (Exception e) {
			log.error("In Ex : " + e.getMessage());
			throw new GenericException(domainName, "ERR-CB-0000", "applyExcludeParams thrown error.", e);
		}
	}
	//should do
	public Map<String, String> sortParam(Map<String, String> queryParam) {
		log.debug("before SORT:"+queryParam);
		Map<String, String> sortedMap = new TreeMap<String, String>(queryParam);
		log.debug("after SORT:"+sortedMap);
		return sortedMap;
	}

	public String formRule(Map<String, String> queryParam,String url) {
		String ruleName =  "rule '" + getRuleName(queryParam)+"'";
		String when = formWhen(queryParam);
		String then = formThen(url);
		String rule = ruleName + "\nwhen \n " + when + "\nthen \n " + then+"\nend";
		log.debug("RULE:" + rule);
		return rule;
	}


	public String getRuleName(Map<String, String> queryParams) {

		//queryParams = sortParam(queryParams);
		String ruleName = "";
		int count = 1;
		for(Entry e : queryParams.entrySet()){
			if (queryParams.size() == count) {
				ruleName += e.getValue();	
			}else{
				ruleName += e.getValue()+"_";	
			}
			count++;
		}
		ruleName=ruleName.toLowerCase();
		log.debug("getRuleName:"+ruleName);		
		//String ruleName = queryParam.get("site")+"_"+queryParam.get("entitytype") ;
		return ruleName;
	}

	public String formWhen(Map<String, String> queryParams) {
		log.methodEntry("generateRule entry");
		String whenParams = "map:HashMap(";
		int count = 1;
		log.debug("totalCount:"+queryParams.entrySet().size());
		for(Entry e : queryParams.entrySet()){
			if (queryParams.size() == count) {
				whenParams += e.getKey() +" == '"+e.getValue()+"'";
			}else{
				whenParams += e.getKey() +" == '"+e.getValue()+"',";
			}
			count++;
		}
		whenParams+=");";
		log.debug("whenParams:\n"+whenParams);
		return whenParams;
	}

	public String formThen(String url) {
		log.methodEntry("formThen entry");
		String thenParams = "";
		int count = 1;
		thenParams = "urls = addToUrlList('"+url+"',urls);";
		thenParams += "System.out.println('inside url');";
		log.debug("ThenParams:" + thenParams);
		return thenParams;
	}



	public boolean writeToFile(String domainName, String rule) {
		log.methodEntry("writeToFile entry.");
		boolean result = false;
		try {
			String fileDir = propUtil.getProperty("rulepath") + domainName + "/resources/";
			String filePath = fileDir +  propUtil.getProperty("rulefilename");
			if (checkAndCreateDirectory(fileDir)) {
				log.debug("checkAndCreateDirectory is successful so writing to this path:"
						+ filePath);
				FileWriter fstream = new FileWriter(filePath, true);
				BufferedWriter out = new BufferedWriter(fstream);
				if (rule != null) {
					out.write(rule + "\n");
				}
				out.close();
			}
			result = true;
		} catch (GenericException ge) {
			log.error("In GE:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("In EX:" + ex.getMessage());
			throw new GenericException("ERR-RE-0007",
					"DuplicationHelper.writeToFile() failure:"
					+ ex.getMessage(),
					"Input is ruleData & domainName", ex);
		}
		log.methodExit("writeToFile exit.");
		return result;
	}

	public boolean checkAndCreateDirectory(String pathName)
	{
		try {
			File file = new File(pathName);
			if (!file.exists()) {
				return file.mkdirs();
			} else {
				return true;
			}
		} catch (GenericException ge) {
			log.error("In checkAndCreateDirectory GE:" + ge.getMessage());
			throw ge;
		} catch (Exception ex) {
			log.error("In checkAndCreateDirectory EX:" + ex.getMessage());
			throw new GenericException("ERR-RE-0008",
					"DuplicationHelper.checkAndCreateDirectory() failure:"
					+ ex.getMessage(), "Input is path:" + pathName, ex);
		} finally {
			log.debug("End of createDirectory.");
		}
	}


	public String generateRule(String domain, String rule) {
		String perfectRule = "package in.nic.cmf.cacheburster"
			+ "\n"
			+ "import java.util.HashMap;"
			+ "\n"
			+ "import java.util.List;"
			+ "\n"
			+ "import java.util.ArrayList;"
			+ "\n"
			+ "import java.util.Map;"
			+ "\n"
			+ "global List urls;"
			+ "\n"
			+ "function List<String> addToUrlList(String url, List urls) "
			+ "\n" + " {    " + "\n"
			+ "	urls.add(url);" + "\n"
			+ "	return urls; " + "\n" 
			+ " } " +"\n"
			+ rule;
		return perfectRule;
	}



}
