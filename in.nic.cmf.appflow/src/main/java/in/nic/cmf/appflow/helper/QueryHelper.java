package in.nic.cmf.appflow.helper;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class QueryHelper {
	private static HashMap<String, QueryHelper> hashQueryHelper = new HashMap<String, QueryHelper>();
	private LogTracer log;
	private String domain;
	public List<String> excludeParams;
	private PropertiesUtil propUtil;
	private Map<String, String> modifyParams;

	public QueryHelper(String domain) {
		this.domain = domain;
		propUtil = PropertiesUtil.getInstanceof(domain, "applicationflow");
		setExcludeParams((String) propUtil.get("exludeParams"));
		setModifyParams((String) propUtil.get("modifyPrams"));
		this.log=new LogTracer(domain, "appflow");
	}

	public void setExcludeParams(String excludeParamsValue) {
		excludeParams = Arrays.asList(excludeParamsValue.split(","));

	}

	/**
	 * Sets the modify params.
	 * 
	 * @param mParams
	 *            the m params
	 */
	public void setModifyParams(String modifyParamsValue) {
		modifyParams = new HashMap();
		List<String> modifyParam = Arrays.asList(modifyParamsValue.split(","));
		for (String param : modifyParam) {
			String[] value = param.split("=");
			modifyParams.put(value[0], value[1]);
		}

	}

	public static QueryHelper getInstanceof(String domain) {
		if (!hashQueryHelper.containsKey(domain)) {
			hashQueryHelper.put(domain, new QueryHelper(domain));
		}
		return hashQueryHelper.get(domain);
	}

	/**
	 * Sets the log.
	 * 
	 * @param log
	 *            the new log
	 */
	public void setLog(LogTracer log) {
		this.log = log;
	}

	public String formQueryParam(String queryString) {
		log.debug("inside formQueryParams."+queryString);
		MultivaluedMapImpl queryMap = stringToMap(queryString);
		queryMap =  applyDefaultParams(applyExcludeParams(applyModifyParams(queryMap)), domain);
		String str = mapToString(queryMap);
		return str;

	}


	public synchronized Map<String,Map<String,Object>> formQueryParams(Map<String,Map<String,Object>> requestDetails) {
		log.debug("inside formQueryParams."+requestDetails);
		MultivaluedMapImpl queryMap = stringToMap((String) requestDetails.get("input").get("queryString"));
		queryMap =  applyDefaultParams(applyExcludeParams(applyModifyParams(queryMap)), domain);
		log.debug("after default,excludeand modigfy"+queryMap);
		String strQuery = mapToString(queryMap);
		log.debug("----------------converttomap-----------------------------");
		Map<String,Object> mm = (Map<String,Object>)requestDetails.get("input").get("queryParams");
		Map<String,Object> qParam = convertToMap(mm);

		Map<String,Object> form = (Map<String,Object>)requestDetails.get("input").get("formParams");
		Map<String,Object> formParam = convertToMap(form);
		requestDetails.get("input").put("queryString",strQuery );
		requestDetails.get("input").put("queryParams",qParam );
		requestDetails.get("input").put("formParams",formParam );
		log.debug("output:::::"+requestDetails);
		return requestDetails;

	}

	private Map<String,Object> convertToMap(Map<String,Object> qp) {
		log.debug("in converttoMap:"+qp);
		Map<String,Object> map = new HashMap<String,Object>();
		if(qp!=null){
			log.debug("qp not null");
		for(Entry e: qp.entrySet()){
			if(!excludeParams.contains(e.getKey())){
				log.debug("map:"+e.getKey());
				map.put((String) e.getKey(), e.getValue());
			}
		}
		}
		log.debug("out converttoMap:"+map);
		return map ;
	}

	public String mapToString(MultivaluedMapImpl map) throws GenericException {
		StringBuilder stringBuilder = new StringBuilder();
		try {

			for (String key : map.keySet()) {
				if (stringBuilder.length() > 0) {
					stringBuilder.append("&");
				}
				String value = "";
				if (map.get(key).size() == 1) {
					value = getStringFromList(map.get(key));
					stringBuilder.append((key != null ? URLEncoder.encode(key,
							"UTF-8") : ""));
					stringBuilder.append("=");
					stringBuilder.append(value != null ? URLEncoder.encode(
							value, "UTF-8") : "");

				} else {
					int i = 0;
					for (Object e : map.get(key)) {
						stringBuilder.append((key != null ? URLEncoder.encode(
								key, "UTF-8") : ""));
						stringBuilder.append("=");
						stringBuilder.append(e != null ? URLEncoder.encode(
								(String) e, "UTF-8") : "");
						if (i < (map.get(key).size() - 1))
							stringBuilder.append("&");
						i++;
					}
				}

			}

			return stringBuilder.toString();
		} catch (UnsupportedEncodingException e) {
			throw new GenericException("ERR-DS-0004",
					"Unsupported Encoding Exception", e);
		}
	}

	private MultivaluedMapImpl stringToMap(String input) {
		MultivaluedMapImpl map = new MultivaluedMapImpl();
		if (input != null) {
			String[] nameValuePairs = input.split("&");
			for (String nameValuePair : nameValuePairs) {
				String[] nameValue = nameValuePair.split("=");
				try {
					map.add(URLDecoder.decode(nameValue[0], "UTF-8")
							.toLowerCase(),
							nameValue.length > 1 ? URLDecoder.decode(
									nameValue[1], "UTF-8") : "");
				} catch (UnsupportedEncodingException e) {
					throw new GenericException("ERR-DMS-0004",
							"Unsupported Encoding Exception", input, e);
				}
			}
		}

		return map;
	}

	public MultivaluedMapImpl applyDefaultParams(MultivaluedMapImpl list,
			String domain) throws GenericException {
		log.methodEntry(this.getClass().getSimpleName()
				+ ".applyDefaultParams()");

		try {
			if (!list.containsKey("orderbydir"))
				list.add("orderByDir", "desc");

			list.add("site", domain);

			log.methodExit(this.getClass().getSimpleName()
					+ ".applyDefaultParams()");
			return list;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GenericException("ERR-DS-0000", this.getClass()
					.getSimpleName() + ".applyDefaultParams()", domain, e);
		}
	}

	public MultivaluedMapImpl applyExcludeParams(MultivaluedMapImpl list)
	throws GenericException {
		log.methodEntry(this.getClass().getSimpleName()
				+ ".applyExcludeParams()");

		try {
			java.util.Iterator<String> items = this.excludeParams.iterator();
			while (items.hasNext()) {
				list.remove(items.next());
			}
			log.methodExit(this.getClass().getSimpleName()
					+ ".applyExcludeParams()");
			return list;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GenericException("ERR-DS-0000", this.getClass()
					.getSimpleName() + ":applyExcludeParams()", e);
		}
	}


	public MultivaluedMapImpl applyModifyParams(MultivaluedMapImpl mParams)
	throws GenericException {
		log.methodEntry(this.getClass().getSimpleName()
				+ ".applyModifyParams()");
		try {
			Set set = modifyParams.entrySet();
			Iterator iter = set.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();

				if (mParams.containsKey(entry.getKey())) {
					String value = getStringFromList(mParams.get(entry.getKey()));
					mParams.remove(entry.getKey());
					mParams.add(entry.getValue().toString(), value);
				}
			}

			log.methodExit(this.getClass().getSimpleName()
					+ ".applyModifyParams()");
			return mParams;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GenericException("ERR-DS-0000", this.getClass()
					.getSimpleName() + ":applyModifyParams()", e);
		}
	}


	private String getStringFromList(Object list) {
		log.debug("getStringFromList entry:"+list);
		LinkedList listvalues = (LinkedList) list;
		log.debug("after casting");
		for (Object e : listvalues) {
			return (String) e;
		}
		return null;
	}
}
