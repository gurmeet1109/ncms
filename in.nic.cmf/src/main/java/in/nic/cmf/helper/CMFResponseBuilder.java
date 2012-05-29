package in.nic.cmf.helper;

import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.formatters.FormatFactory;
import in.nic.cmf.formatters.Formatter;
import in.nic.cmf.formatters.Formatters;
import in.nic.cmf.logger.LogTracer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * The Class CMFResponseBuilder.
 */
public class CMFResponseBuilder {

	/** The Constant cmfResponse. */
	public static final CMFResponseBuilder cmfResponse = new CMFResponseBuilder();

	/** The log. */
	private LogTracer                      log;

	/**
	 * Instantiates a new cMF response builder.
	 */
	private CMFResponseBuilder() {
	}

	/**
	 * Gets the single instance of CMFResponseBuilder.
	 * @return single instance of CMFResponseBuilder
	 */
	public static CMFResponseBuilder getInstance() {
		return cmfResponse;
	}

	public final void setLogTracer(LogTracer log) {
		this.log = log;

	}

	/**
	 * Gets the response format type.
	 * @param format
	 *            the format
	 * @return the response format type
	 */
	private String getResponseFormatType(String neededFormat) {
		log.debug("getResponseFormatType:"+neededFormat);
		String mimeType = "application/xml";
		if (isEmpty(neededFormat)||neededFormat.equals("xml")) {
			return mimeType;
		}
		if ((neededFormat.equalsIgnoreCase("json"))
				|| (neededFormat.equalsIgnoreCase("extjson"))) {
			mimeType = "application/json";
		}
		log.debug("mimetypeis:"+mimeType);
		return mimeType;
	}

	/**
	 * Gets the convertor.
	 * @param format
	 *            the format
	 * @return the convertor
	 */
	private Convertor getConvertor(String domain, String format) {
		if (format.equals("application/json")) {
			return FormatJson.getInstanceof(domain);
		} else {
			return FormatXml.getInstanceof(domain);
		}
	}

	/**
	 * Builds the.
	 * @param parameters
	 *            the parameters
	 * @return the response
	 * @throws GenericException
	 *             the generic exception
	 */
	public Response build(String domain,
			Map<String, Map<String, Object>> parameters,String neededFormat)
	throws GenericException {
		log.debug("In build:"+domain+parameters+neededFormat);

		String responseFormat = "xml";

		if (!isEmpty(parameters.get("input").get("format"))) {
			responseFormat = parameters.get("input").get("format").toString();
			log.debug("responseFormatMsg:"+responseFormat);
		}
		responseFormat = getResponseFormatType(neededFormat);
		log.debug("withneed:" + responseFormat);
		Map<String, Object> output = parameters.get("output");
		if (isEmpty(output)) {
			throw new GenericException(domain, "ERR-CMF-0001",
			"Output Data Map is missing");
		}
		int statusCode = Integer.parseInt(output.get("statusCode").toString());
		log.debug("StatusCode : " + statusCode);
		Object content = output.get("content");
		if ((statusCode != 303) && (isEmpty(content))) {
			throw new GenericException(domain, "ERR-CMF-0001");
		}

		if (statusCode == 303) {
			content = new String("");
			parameters.get("output").put("content", content);
		}
		HashMap<String, String> headers = (HashMap<String, String>) output
		.get("headers");
		if ((!isEmpty(headers)) && (!isEmpty(headers.get("Content-Type")))) {
			responseFormat = headers.get("Content-Type");
		}
		/* if (content instanceof HashMap) {
            log.debug("Hashmap content....");
            content = (getConvertor(domain, responseFormat)
                    .out((HashMap<String, Object>) content)).toString();
        } else*/ 
		if (content instanceof byte[]) { // its a binary content
			log.debug("byte content....");
			return Response.status(statusCode).entity(content)
			.type(responseFormat).build();
		}
		log.debug("String content...." + content + "\n StatusCode : "  + statusCode);	
		//  if (content instanceof String) {
		if ((content instanceof String) && statusCode == 303) {
			try {
				log.debug("step 02 in 303 status ... location : "
						+ headers.get("Location"));
				URI redirectURL = new URI(headers.get("Location"));
				redirectURL = UriBuilder.fromUri(redirectURL).build();
				return Response.seeOther(redirectURL).build();
			} catch (URISyntaxException e) {
				throw new GenericException(domain, "ERR-CMF-0007");
			}
		} //else {
		log.debug("am here...."+neededFormat);

		if(neededFormat.equalsIgnoreCase("xml")){
			log.debug("inside xml condition:"+responseFormat);
			return Response.status(statusCode).entity(content.toString()).type(responseFormat).build();
		}else if(neededFormat.equalsIgnoreCase("json")){
			log.debug("inside json condition");
			try {
				Formatter f = (Formatter) FormatFactory.getFormatInstance(neededFormat);
				log.debug("------------------------------------"+content+"; parameters"+parameters);
				Map<String,Map<String,Object>> formattedOutput = f.Format(domain, parameters);
				content = (String) formattedOutput.get("output").get("content");
				log.debug("strJson:" + content);
				return Response.status(statusCode).entity(content).type("application/json").build();
			} catch (Exception e) {
				log.debug("Inside format for exception:"+e.getMessage());
				e.printStackTrace();
			}
		}else if(neededFormat.equalsIgnoreCase("extjson")){
			log.debug("inside extjson condition");
			try {
				Formatter f = (Formatter) FormatFactory.getFormatInstance(neededFormat);
				log.debug("------------------------------------"+content+"; parameters"+parameters);
				Map<String,Map<String,Object>> formattedOutput = f.Format(domain, parameters);
				log.debug("formattedOutput:"+formattedOutput);
				content = (String) formattedOutput.get("output").get("content");
				log.debug("strextJson:" + content);
				return Response.status(statusCode).entity(content).type("application/json").build();
			} catch (Exception e) {
				log.debug("Inside ext format for exception:"+e.getMessage());
				e.printStackTrace();
			}
		}else{
			log.debug("content in default else:"+content.toString());
			return Response.status(statusCode).entity(content.toString()).type("application/xml").build();
		}
		// }
		//}
		log.debug("hey this null value from CMFResponseBuilder.build()");
		return null;

	}

	/**
	 * Checks if is empty.
	 * @param object
	 *            the object
	 * @return true, if is empty
	 */
	private boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			return ((String) object).isEmpty();
		}
		return false;
	}

}
