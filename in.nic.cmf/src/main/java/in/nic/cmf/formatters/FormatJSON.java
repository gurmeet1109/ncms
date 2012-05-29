package in.nic.cmf.formatters;

import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * The Class FormatJson.
 */
public class FormatJSON implements Formatter {

	/** The Constant DEFAULT_CONTENT_TYPE. */
	public static final String DEFAULT_CONTENT_TYPE = "application/json";

	@Override
	public Map<String, Map<String, Object>> Format(String domain,
			Map<String, Map<String, Object>> parameters)
			throws GenericException {
		try {
			System.out.println("format entry:"+parameters);
			Map<String, Object> output = parameters.get("output");
			System.out.println("after getoutput"+output);
			System.out.println("output:"+output.get("content").getClass().getSimpleName());
			String value = (String) output.get("content");
			System.out.println("VALUE:"+value);

			if(output.get("content") instanceof String){
				System.out.println("inside string content:");
				value = (String) output.get("content");
				System.out.println("value:"+value);



				if(value.startsWith("<") && value.endsWith(">") ){
					System.out.println("bbbb json:"+value);
					JSONObject json = XML.toJSONObject(value);
					value = json.toString();
				}


			}else if(output.get("content") instanceof HashMap){
				System.out.println("inside hash content:");
				HashMap<String,Object> value1 = FormatXml.getInstanceof(domain).in(value);
				System.out.println("value1:"+value1);
				value= (String) FormatJson.getInstanceof(domain).out(value1);
				System.out.println("str:"+value);
				/*	value = (String) FormatXml.getInstanceof(domain).out((HashMap<String, Object>) output.get("content"));
				System.out.println("valu:"+value);*/
			}




			System.out.println("json::::::::::::::::::::::::"+value);

			HashMap<String, String> headers = new HashMap<String, String>();
			if (parameters.get("output").containsKey("headers")) {
				headers = (HashMap<String, String>) parameters.get("output")
				.get("headers");
			}
			System.out.println("after header");
			headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
			output.put("headers", headers);
			output.put("content", value);
			parameters.put("output", output);
			System.out.println("parameters");
		} catch (Exception e) {
			throw new GenericException(domain, "ERR-DS-0017", this.getClass()
					.getSimpleName() + ":renderMergedOutputModel()", e);
		}
		return parameters;
	}

	@Override
	public Map<String, Object> convert(String domain, String content) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			//JSONObject json = XML.toJSONObject(content);
			System.out.println("before formatting:"+content);
			map = FormatJson.getInstanceof(domain).in(content);
			System.out.println("map:"+map);
		} catch (Exception e) {
			System.out.println("In EX:"+e.getMessage());
			e.printStackTrace();
		}
		return map;

	}
}
