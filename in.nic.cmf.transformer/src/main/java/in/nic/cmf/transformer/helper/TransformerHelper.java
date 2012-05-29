package in.nic.cmf.transformer.helper;

import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.transformer.providers.DefaultProvider;
import in.nic.cmf.util.CommonUtils;
import in.nic.cmf.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

/**
 * The Class TransformerHelper.
 */
public class TransformerHelper {

	/** The Constant transformerHelper. */
	//private static TransformerHelper transformerHelper ;
	/** The log. */
	private LogTracer log;
	private String domain;
	private static HashMap<String, TransformerHelper> hashTransformerHelper    = new HashMap<String, TransformerHelper>();
	private PropertiesUtil                         propUtil;
	private CommonUtils util = null;
	/**
	 * Instantiates a new transformer helper.
	 */
	private TransformerHelper(String domain){
		this.domain=domain;
		propUtil = PropertiesUtil.getInstanceof(domain, "transformer");
		setLogger( domain);
		util = util.getInstanceOf(domain);
		//transformerHelper =  TransformerHelper.getInstance(domain);
	}

	/**
	 * Gets the single instance of TransformerHelper.
	 * @param domain 
	 *
	 * @return single instance of TransformerHelper
	 */
	//	public static TransformerHelper getInstance(String domain){
	//		return transformerHelper;
	//	}

	public static TransformerHelper getInstanceOf(String domain) {
		if (!hashTransformerHelper.containsKey(domain)) {
			hashTransformerHelper.put(domain, new TransformerHelper(domain));
		}
		return hashTransformerHelper.get(domain);
	}


	private void setLogger(String domain) {
		log = new LogTracer(domain, "transformer");
	}
	/**
	 * Wrap collections.
	 *
	 * @param key the key
	 * @param incomingContent the incoming content
	 * @return the map
	 */
	public Map<String,Object> wrapCollections(String key, Map<String,Object> incomingContent){
		//	log.debug("key:"+key);
		//	log.debug("incomingcontent:"+incomingContent);
		Map<String,Object> output = new HashMap<String,Object>();
		Map<String,Object> input = new HashMap<String,Object>();
		input.put(key, incomingContent);			
		output.put("Collections", input);
		//	log.debug("output:"+output);
		return output;
	}

	/**
	 * Gets the response map.
	 *
	 * @param responseMap the response map
	 * @param parameters the parameters
	 * @return the response map
	 */
	public Map<String, Map<String, Object>> getResponseMap(String domain, Map<String, Object> responseMap,  Map<String, Map<String, Object>> parameters){
		Map<String,Object> outputMap = new HashMap<String,Object>();

		try{
			log.debug("getResponseMap:"+responseMap);

			String output = util.convertWithCDATA(domain,responseMap);
			//String xml = (String) FormatXml.getInstanceof(domain).out((HashMap<String, Object>) responseMap);
			log.debug("xml:"+output);
			outputMap.put("content", output);
			if(responseMap!=null){
				outputMap.put("statusCode", "200");
			}else{
				outputMap.put("statusCode", "501");
			}
			parameters.put("output", outputMap);
			log.debug("Response:"+parameters);
		}catch(Exception ex){
			log.error("In Exception:"+ex.getMessage());
		}
		return parameters;
	}



}
