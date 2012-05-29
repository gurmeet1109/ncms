package in.nic.cmf.formatters;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

/**
 * The Class FormatExtJsonp.
 */
public class FormatEXTJSON implements Formatter {

	/** The DEFAUL t_ conten t_ type. */
	public final String DEFAULT_CONTENT_TYPE = "application/json";

	// private final static ConvertorUtils cu = ConvertorUtils
	// .getInstance();

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel
	 * (java.util.Map, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Map<String, Map<String, Object>> Format(String domain,
			Map<String, Map<String, Object>> parameters)
			throws GenericException {
		try {

			System.out.println("ExtJson Started"+domain+parameters);
			Map<String, Object> output = parameters.get("output");
			HashMap<String, String> queryParams = (HashMap<String, String>) parameters
			.get("input").get("queryParams");
			HashMap hm = new HashMap();
			String collString = (String) output.get("content");
			System.out.println("After get String from Model...");

			FormatFlatten f = FormatFlatten.getInstanceOf(domain,false);
			FormatJson fj = FormatJson.getInstanceof(domain);

			System.out.println("f:"+f+"; collection:"+collString);
			HashMap<String, Object> rootMap = new HashMap<String,Object>();
			String identifyJson = "\"Collections\":";
			System.out.println("identity:"+identifyJson);

			if(collString.contains("<Collections>")){
				System.out.println("collections:"+collString);


				rootMap = f.in(collString);
				System.out.println("ttttttttttttttttttttttttttttttttttttttttttttt"+rootMap);
				//fj.out(flattenMap);

				/*	FormatXml fxml =FormatXml.getInstanceof(domain);
				rootMap = fxml.in(collString);
				System.out.println("after format:"+rootMap);
				System.out.println("f2:"+rootMap);*/
			}else if (collString.contains(identifyJson)){
				System.out.println("its json");

				rootMap = fj.in(collString);
				System.out.println("ROOOT:"+rootMap);
			}else{
				System.out.println("no collections so appending:"+collString);
				String appendedString ="<Collections>"+collString+"</Collections>";
				System.out.println("appendedString:"+appendedString);
				rootMap = f.in(appendedString);
			}

			System.out.println("rootMap:"+rootMap);
			
			HashMap<String, Object> finalMap = new HashMap<String, Object>();
			finalMap = rootMap;
			if(!rootMap.containsKey("Success") || !rootMap.containsKey("Success")){
				HashMap<String, Object> inputCollectionMap = (HashMap<String, Object>) rootMap.get("Collections");
				HashMap<String, Object> outputCollectionMap = new HashMap<String, Object>();
				System.out.println("---------------------------");
				String c="";
				if(inputCollectionMap != null){
					for (Entry<String, Object> e : inputCollectionMap.entrySet()) {
						String key = (String) e.getKey();
						System.out.println("key:"+key);
						if (!key.equals("Count")) {
							System.out.println("key:"+key);
							if(key!=null){
								if(key.equalsIgnoreCase("success")){
									System.out.println("In Success:"+key);
									outputCollectionMap.put(key.toLowerCase(), e.getValue());
								}else{
									System.out.println("In Failure:"+key);
									outputCollectionMap.put(key, e.getValue());
								}
							}
						}
					}
					c = (String) inputCollectionMap.get("Count");
					System.out.println("---------------------------"+c);

					outputCollectionMap.put("Count", c);
				}



				HashMap<String, String> queryheadersParams = (HashMap<String, String>) parameters
				.get("input").get("headers");

				String method = queryheadersParams.get("method");
				if(method.equalsIgnoreCase("delete") || (method.equalsIgnoreCase("post") && c!="null")){
					System.out.println("inside post and delete");
					finalMap = outputCollectionMap;
				}else{
					System.out.println("inside else ofpost and delete");
					finalMap.put("Collections", outputCollectionMap);
				}
			}else{
				finalMap.put("success",finalMap.get("Success"));
				finalMap.remove("Success");
			}
			System.out.println("b4 JSONOBJECT:"+finalMap);
			JSONObject json = new JSONObject();
			json.putAll(finalMap);
			String objString = json.toString();
			System.out.println("AFTER JSONOBJECT:"+objString);


			String queryString = (String) parameters.get("input").get("queryString");

			if (queryString!=null && queryString.contains("callback=")){
				String callbackValue = convertQueryStringToHash(queryString);
				objString = callbackValue + "(" + objString + ")";
				System.out.println("after callback appended:"+objString);
			}
			System.out.println("After Callback");
			HashMap<String, String> headers = new HashMap<String, String>();

			if (parameters.get("output").containsKey("headers")) {
				headers = (HashMap<String, String>) parameters.get("output").get("headers");
			}
			headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
			output.put("headers", headers);
			output.put("content", objString);
			parameters.put("output", output);

			System.out.println("Extjson End");

		} catch (GenericException e) {
			throw e;
		} catch (Exception e) {
			throw new GenericException(domain, "ERR-DS-0016", this.getClass()
					.getSimpleName() + ":renderMergedOutputModel()", e);
		}
		return parameters;
	}

	private String convertQueryStringToHash(String queryString) {
		String callback = "";
		if(queryString!=null){
			String[] callback1 = queryString.split("&callback=");
			String[] callback2 = callback1[1].split("&");
			System.out.println("callback:"+callback2[0]);
			callback = callback2[0];
		}
		return callback;
		//queryString=site=tidel.in&limit=100&orderbydir=ASC&q=&callback=Ext.data.JsonP.callback1&orderby=UserName&offset=0&entitytype=CmsUserProfile

	}

	private HashMap<String, Object> getFlatHashWithFormattedDate(String domain,
			String entityName, Object inputHash) {
		System.out.println("inside getFlatHashWithFormattedDate: "+domain+"entity:"+entityName+"inputHash:"+inputHash);
		HashMap<String, Object> flatEntityHashMap = new HashMap<String, Object>();
		List<HashMap<String, String>> finalEntityHashListMap = new ArrayList<HashMap<String, String>>();
		if (inputHash.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
			System.out.println("is arraylist");
			List<HashMap<String, String>> entityHashList = (List<HashMap<String, String>>) inputHash;
			for (HashMap<String, String> eachEntityHash : entityHashList) {
				finalEntityHashListMap.add(getEntityHash(domain, eachEntityHash));
			}
			flatEntityHashMap.put(entityName, finalEntityHashListMap);
		} else if (inputHash.getClass().getSimpleName()
				.equalsIgnoreCase("HashMap")) {
			System.out.println("is hashmap");
			flatEntityHashMap.put(entityName,
					getEntityHash(domain, (HashMap<String, String>) inputHash));
		}
		System.out.println("flatEntityHashMap:"+flatEntityHashMap);
		return flatEntityHashMap;
	}

	private HashMap<String, String> getEntityHash(String domain,
			HashMap<String, String> hash) {
		System.out.println("inside getEntityHash:"+domain+hash);
		HashMap<String, String> entityHash = new HashMap<String, String>();
		ConvertorUtils cu = ConvertorUtils.getInstanceof(domain);
		for (Entry<String, String> e : hash.entrySet()) {
			String key = (String) e.getKey();
			String value = (String) e.getValue();
			System.out.println(key+":"+value);
			if (key.endsWith("Date") && !cu.isEmpty(value)) {
				System.out.println("before:"+value);
				//needs to be checked
				String value1 = DateUtils.getInstanceOf(domain).getISTFormatDate(value);
				System.out.println("after:"+value1);
			}
			entityHash.put(key, value);
		}
		System.out.println("entityHash"+entityHash);
		return entityHash;
	}

	private static void logTime(long startTime, String action) {
		long timeTaken = System.currentTimeMillis() - startTime;
		System.out.println("TimeTaken:" + timeTaken + "; Action:" + action);
	}

	@Override
	public Map<String, Object> convert(String domain, String content) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			System.out.println("---------------------------before formatting:"+content);
			//flatten and change as hashmap

			map = FormatJson.getInstanceof(domain).in(content);
			System.out.println("-------------------map:"+map);
		} catch (Exception e) {
			System.out.println("In EX:"+e.getMessage());
			e.printStackTrace();
		}
		return map;

	}
}
