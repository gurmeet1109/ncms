package in.nic.cmf.formatters;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class FormatXML.
 */
public class FormatXML implements Formatter {

	/** The Constant DEFAULT_CONTENT_TYPE. */
	public static final String DEFAULT_CONTENT_TYPE = "application/xml";

	@Override
	public Map<String, Map<String, Object>> Format(String domain,
			Map<String, Map<String, Object>> parameters) {
		try {
			System.out.println("Inside Format Xml Class");
			HashMap<String, String> headers = new HashMap<String, String>();
			if (parameters.get("output").containsKey("headers")) {
				headers = (HashMap<String, String>) parameters.get("output")
				.get("headers");
			}
			headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
			Map<String, Object> output = parameters.get("output");
			output.put("headers", headers);
			parameters.put("output", output);
		} catch (Exception e) {
			throw new GenericException(domain, "ERR-DS-0017", this.getClass()
					.getSimpleName() + ":renderMergedOutputModel()", e);
		}
		return parameters;
	}

	public HashMap<String,Object> convert(String domain, String content){
		return FormatXml.getInstanceof(domain).in(content);
	}
}
