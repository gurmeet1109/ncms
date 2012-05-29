package in.nic.cmf;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * This class use for generate the Extjs from xls file. The Class DSUtil.
 */
public class DSUtil {

    /** The type mapping. */
    static JSONObject typeMapping;

    /** The field mapping. */
    static JSONObject fieldMapping;

    static JSONObject classInfoMapping;
    /** The ods util. */

    private static DSUtil odsUtil = new DSUtil();

    private static PropertiesUtil putil = null;

    private static LogTracer log = null;
    private static String site = "";
    static PropertiesUtil proputil = null;

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws ResourceNotFoundException
     *             the resource not found exception
     * @throws ParseErrorException
     *             the parse error exception
     * @throws MethodInvocationException
     *             the method invocation exception
     * @throws Exception
     *             the exception
     */
    public static void main(String[] args) throws ResourceNotFoundException,
	    ParseErrorException, MethodInvocationException, Exception {
	proputil = PropertiesUtil.getInstanceof(site, "dsutil");
	site = System.getProperty("site");
	if ((site == null) || (site.isEmpty())) {
	    site = "default";
	}
	log = new LogTracer(site, "DSUtilTraceLogger", true, true, true, true,
		true);
	log.methodEntry("Dsutil Generation Started");
	JSONObject object = new JSONObject();
	Map objectsmap = new HashMap();
	List masterList = new ArrayList();
	VelocityContext context = new VelocityContext();
	InputStream is = new FileInputStream(getResourcePath());
	String jsonTxt = IOUtils.toString(is);
	JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
	JSONArray jsonarray = json.getJSONArray("classname");
	for (Object j : jsonarray) {
	    JSONObject jsonobj = (JSONObject) j;
	    objectsmap.put(jsonobj.get("name"), j);
	    if (jsonobj.get("masterdata").equals("true")) {
		masterList.add(jsonobj.get("name"));
	    }
	}
	context.put("classesobj", objectsmap);
	StringBuilder finalOutput = new StringBuilder();
	try {
	    finalOutput.append(getExtjsForm(jsonarray, context, masterList)
		    + "\n");

	    finalOutput.append(getExtjsColumn(jsonarray, context) + "\n");
	    finalOutput.append(getEntityFields(jsonarray, context, objectsmap)
		    + "\n");
	    finalOutput.append(getMasterAndTreeEntityDetails(jsonarray) + "\n");
	    finalOutput.append(getWorkFlowEntityDetails(jsonarray) + "\n");
	    finalOutput
		    .append(generateUniqueAndMetadataInfoEntityDetails(jsonarray));
	    String destinationPath = proputil.get("filePath") + site
		    + "/resources/js/" + "autogen-all.js";
	    FileWriter fstream = new FileWriter(destinationPath);
	    BufferedWriter out = new BufferedWriter(fstream);
	    out.write(finalOutput.toString().trim());
	    out.close();

	    getModelData(jsonarray, context);
	    getMappingJson(jsonarray, objectsmap);
	    getFieldNameMappingJson(jsonarray);
	    getEntityTreeGrid(jsonarray, context);
	    generateUniqueAndMetadataInfoEntityDetails(jsonarray);
	    getInterfaceDetails(jsonarray);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new Exception("" + e);
	}
	log.methodExit("Dsutil Generation Finished");
    }

    private static void getInterfaceDetails(JSONArray jsonarray)
	    throws IOException {
	JSONObject finalHash = new JSONObject();
	for (Object jobj : jsonarray) {
	    JSONObject j = (JSONObject) jobj;
	    if (j.get("implements") != null) {
		JSONArray implementsArray = (JSONArray) j.get("implements");
		for (int i = 0; i < implementsArray.size(); i++) {
		    List<String> interfaceList = new ArrayList<String>();
		    if (finalHash.containsKey(implementsArray.get(i))) {
			interfaceList = (List) finalHash.get(implementsArray
				.get(i));
			interfaceList.add(j.get("name").toString());
			finalHash.put((String) implementsArray.get(i),
				interfaceList);
		    } else {
			interfaceList.add(j.get("name").toString());
			finalHash.put((String) implementsArray.get(i),
				interfaceList);
		    }
		}
	    }
	}
	FileWrite(finalHash.toString(), "InterfaceDetails.json");
    }

    private static void FileWrite(String data, String fileName)
	    throws IOException {
	String destinationPath = destinationPath = proputil.get("filePath")
		+ "/" + site + "/resources/" + fileName;
	List<String> filesExcludes = new ArrayList();
	filesExcludes.add("modelnew.js");
	filesExcludes.add("entitytreegrid.json");
	if ((fileName != null) && (filesExcludes.contains(fileName))) {
	    destinationPath = proputil.get("filePath") + site
		    + "/resources/js/" + fileName;
	}
	log.methodEntry("FileWrite Method Start ::: " + fileName);
	FileWriter fstream = new FileWriter(destinationPath);
	BufferedWriter out = new BufferedWriter(fstream);
	out.write(data.trim());
	out.close();
	log.methodExit("FileWrite Method End ::: " + fileName);
    }

    private static String getWorkFlowEntityDetails(JSONArray jsonarray)
	    throws IOException {
	log.methodEntry("getWorkFlowEntityDetails Method Start");
	String workflowData = "";
	workflowData += "['any'],";
	for (Object jobj : jsonarray) {
	    JSONObject j = (JSONObject) jobj;
	    if (j.get("implements") != null) {
		JSONArray implementsArray = (JSONArray) j.get("implements");
		if (implementsArray.contains("Workflowable")) {
		    workflowData += "['" + (String) j.get("name") + "'],";
		}
	    }
	}
	String finalData = "Ext.WorkflowEntities = ["
		+ workflowData.substring(0, workflowData.lastIndexOf(","))
		+ "]";
	log.methodExit("getWorkFlowEntityDetails Method End");
	return finalData;
    }

    public static void getEntityTreeGrid(JSONArray jsonarray,
	    VelocityContext context) throws Exception {
	log.methodEntry("getEntityTreeGrid Method Start");
	VelocityEngine ve = new VelocityEngine();
	ve.setProperty(Velocity.RESOURCE_LOADER, "class");
	ve.setProperty("class.resource.loader.class",
		"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	ve.setProperty("runtime.log.logsystem.class",
		"org.apache.velocity.runtime.log.NullLogSystem");
	ve.init();
	List<String> seoableEntityList = new ArrayList();
	try {
	    Template formt = ve.getTemplate("entitytreegrid.vm");
	    for (Object jobj : jsonarray) {
		JSONObject j = (JSONObject) jobj;
		if (j.get("implements") != null) {
		    JSONArray interfacearr = j.getJSONArray("implements");
		    if (interfacearr.contains("Seoable")) {
			seoableEntityList.add((String) j.get("name"));
		    }
		}
	    }
	    context.put("seoablelist", seoableEntityList);
	    StringWriter writer = new StringWriter();
	    formt.merge(context, writer);
	    if (!writer.toString().trim().isEmpty()) {
		String data = writer.toString();
		FileWrite(data, "entitytreegrid.json");
	    }
	} catch (Exception e) {
	    throw new Exception("" + e);
	}
	log.methodExit("getEntityTreeGrid Method End");
    }

    /**
     * Gets the mapping json.
     * 
     * @param jsonarray
     *            the jsonarray
     * @param objectsmap
     *            the objectsmap
     * @return the mapping json
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void getMappingJson(JSONArray jsonarray, Map objectsmap)
	    throws IOException {
	log.methodEntry("getMappingJson Method Start");
	JSONObject entitymap = new JSONObject();

	for (Object jobj : jsonarray) {
	    typeMapping = new JSONObject();
	    fieldMapping = new JSONObject();
	    classInfoMapping = new JSONObject();
	    JSONObject j = (JSONObject) jobj;
	    if (j.get("ui").equals(true)) {
		classInfoMapping.put("interfaceInfo", j.get("implements"));
		rec("", j, objectsmap);
		JSONObject mapping = new JSONObject();
		mapping.put("typeMapping", typeMapping);
		mapping.put("fieldMapping", fieldMapping);
		mapping.put("classInfo", classInfoMapping);
		entitymap.put(j.get("name"), mapping);
	    }
	}
	FileWrite(entitymap.toString(), "Mapping.json");
	log.methodExit("getMappingJson Method End");
    }

    public static void getFieldNameMappingJson(JSONArray jsonarray)
	    throws IOException {
	log.methodEntry("getFieldNameMappingJson Method Start");
	JSONObject fieldNameMapping = new JSONObject();
	JSONObject dmsfieldNameMapping = new JSONObject();
	JSONObject reverseFieldMapping = new JSONObject();
	JSONObject cdataFieldMapping = new JSONObject();
	JSONObject finalMap = new JSONObject();
	ArrayList alistcdata = new ArrayList();
	for (Object jobj : jsonarray) {
	    JSONObject j = (JSONObject) jobj;
	    JSONArray fieldArray = j.getJSONArray("fields");
	    for (Object f : fieldArray) {
		JSONObject fobj = (JSONObject) f;
		JSONObject fieldJaxb = fobj.getJSONObject("jaxb");
		fieldNameMapping.put(fobj.get("name").toString().toLowerCase(),
			fieldJaxb.get("name"));
		reverseFieldMapping.put(fieldJaxb.get("name"), fobj.get("name")
			.toString().toLowerCase());
		dmsfieldNameMapping.put(fieldJaxb.get("name"),
			fieldJaxb.get("name"));
		if (fobj.get("cdata").equals(true)) {
		    alistcdata.add(fobj.get("name").toString().toLowerCase());
		    alistcdata.add(fieldJaxb.get("name"));
		}
	    }
	}
	finalMap.put("fieldNameMapping", fieldNameMapping);
	finalMap.put("reverseFieldNameMapping", reverseFieldMapping);
	finalMap.put("cdataFieldMapping", alistcdata);
	finalMap.put("dmsfieldNameMapping", dmsfieldNameMapping);
	FileWrite(finalMap.toString(), "FieldNameMapping.json");
	log.methodExit("getFieldNameMappingJson Method End");
    }

    public static String getMasterAndTreeEntityDetails(JSONArray jsonarray)
	    throws IOException {
	log.methodEntry("getMasterEntityDetails Method Start");
	List<String> masterDataList = new ArrayList();
	List<String> treeDataList = new ArrayList();
	List<String> workflowDataList = new ArrayList();
	String masterData = "";
	JSONObject finalMap = new JSONObject();
	List jsonString = new ArrayList();
	JSONArray jsonStringFields = new JSONArray();
	int k = 0;
	for (Object jobj : jsonarray) {
	    JSONObject j = (JSONObject) jobj;
	    if (j.get("masterdata").equals("true")) {
		k++;
		masterDataList.add((String) j.get("name"));
		masterData += "'" + (String) j.get("name") + "',";
	    }
	    if (j.get("treeentity").equals("true")) {
		if (j.get("implements") != null) {
		    JSONArray implementsArray = (JSONArray) j.get("implements");
		    if (implementsArray.contains("Workflowable")) {
			workflowDataList.add((String) j.get("name"));
		    }
		}
		treeDataList.add((String) j.get("name"));
	    }
	    JSONArray fieldArray = j.getJSONArray("fields");
	    for (Object f : fieldArray) {
		JSONObject fobj = (JSONObject) f;
		String fieldType = fobj.get("type").toString().toLowerCase();
		JSONObject fieldJaxb = fobj.getJSONObject("jaxb");
		if (fieldType.equals("jsonstring")) {
		    HashMap h = new HashMap();
		    h.put("ClassName", j.get("name").toString());
		    h.put("FieldName", fieldJaxb.get("name").toString());
		    jsonString.add(h);
		    if (!jsonStringFields.contains(fieldJaxb.get("name")
			    .toString()))
			jsonStringFields.add(fieldJaxb.get("name").toString());
		}
	    }
	}
	JSONObject jsonStringFieldHash = new JSONObject();
	jsonStringFieldHash.put("JsonStringFields", jsonStringFields);
	FileWrite(jsonStringFieldHash.toString(), "JsonStringFields.json");
	finalMap.put("masterEntities", masterDataList);
	finalMap.put("jsonString", jsonString);
	FileWrite(finalMap.toString(), "MasterEntities.json");
	finalMap.clear();
	finalMap.put("treeEntities", treeDataList);
	finalMap.put("treeWorkflowEntities", workflowDataList);
	FileWrite(finalMap.toString(), "TreeEntities.json");
	String finalData = "var masterEntities = ["
		+ masterData.substring(0, masterData.lastIndexOf(",")) + "]";
	log.methodExit("getMasterEntityDetails Method End");
	return finalData;
    }

    /**
     * Gets the extjs form.
     * 
     * @param jsonarray
     *            the jsonarray
     * @param context
     *            the context
     * @return the extjs form
     * @throws Exception
     *             the exception
     */
    public static String getExtjsForm(JSONArray jsonarray,
	    VelocityContext context, List masterList) throws Exception {
	log.methodEntry("getExtjsForm Method Start");
	String formdata = "";
	VelocityEngine ve = new VelocityEngine();
	ve.setProperty(Velocity.RESOURCE_LOADER, "class");
	ve.setProperty("class.resource.loader.class",
		"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	ve.setProperty("runtime.log.logsystem.class",
		"org.apache.velocity.runtime.log.NullLogSystem");
	ve.init();
	try {
	    Template formt = ve.getTemplate("extjsform.vm");
	    context.put("dsutilobj", odsUtil);
	    for (Object jo : jsonarray) {
		JSONObject jsonobj = (JSONObject) jo;
		context.put("classobj", jsonobj);
		context.put("masterList", masterList);
		StringWriter writer = new StringWriter();
		formt.merge(context, writer);
		if (!writer.toString().trim().isEmpty()) {
		    String data = writer.toString();
		    formdata += data.substring(0, data.lastIndexOf(","))
			    + " ]}];";
		}
	    }
	} catch (Exception e) {
	    throw new Exception("" + e);
	}
	log.methodExit("getExtjsForm Method End");
	return formdata;
    }

    public static String getEntityFields(JSONArray jsonarray,
	    VelocityContext context, Map objectsMap) throws Exception {

	log.methodEntry("getEntityFields Method Start");
	StringBuilder fieldsData = new StringBuilder();
	VelocityEngine ve = new VelocityEngine();
	ve.setProperty(Velocity.RESOURCE_LOADER, "class");
	ve.setProperty("class.resource.loader.class",
		"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	ve.setProperty("runtime.log.logsystem.class",
		"org.apache.velocity.runtime.log.NullLogSystem");
	ve.init();

	try {
	    Template formt = ve.getTemplate("entityfields.vm");
	    context.put("dsutilobj", odsUtil);
	    Map fieldmap = new HashMap();
	    for (Object jo : jsonarray) {
		HashMap<String, String> fieldMap = new HashMap<String, String>();
		List<String> fieldList = new ArrayList();
		JSONObject jsonobj = (JSONObject) jo;

		if (jsonobj.getBoolean("ui")) {
		    fieldmap = getFieldMap(jsonobj, objectsMap, fieldMap);

		    java.util.Iterator<Entry<String, String>> it = fieldmap
			    .entrySet().iterator();
		    while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			fieldList.add(pairs.getValue().toString());

		    }
		}
		context.put("classobj", jsonobj);
		context.put("fieldlist", fieldList);
		StringWriter writer = new StringWriter();

		formt.merge(context, writer);
		if (!writer.toString().trim().isEmpty()) {
		    fieldsData.append(writer.toString());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new Exception("" + e);
	}
	log.methodExit("getEntityFields Method End");
	return fieldsData.toString();

    }

    public static HashMap getFieldMap(JSONObject fieldObject, Map objectsMap,
	    HashMap fieldMap) {
	log.methodEntry("getFieldMap Method Start");
	if (fieldObject != null) {
	    JSONArray fieldArray = fieldObject.getJSONArray("fields");
	    for (Object fjo : fieldArray) {
		JSONObject fieldObj = (JSONObject) fjo;
		if (!fieldObj.get("type").equals("Object")
			&& !fieldObj.get("type").equals("IsMultiObject")
			&& !fieldObj.get("type").equals("JsonString")) {
		    if (fieldObj.get("annotations") != null
			    && fieldObj.getJSONObject("annotations").get("UI") != null
			    && fieldObj.getJSONObject("annotations")
				    .getJSONObject("UI").get("advancedSearch") != null
			    && fieldObj.getJSONObject("annotations")
				    .getJSONObject("UI").get("advancedSearch")
				    .equals("true")) {
			fieldMap.put(
				fieldObj.getJSONObject("jaxb")
					.getString("name"), fieldObj
					.getJSONObject("jaxb")
					.getString("name"));
		    }
		} else {
		    JSONObject objfield = (JSONObject) objectsMap.get(fieldObj
			    .getJSONObject("jaxb").get("name"));
		    getFieldMap(objfield, objectsMap, fieldMap);
		}
	    }
	}
	log.methodExit("getFieldMap Method End");
	return fieldMap;

    }

    /**
     * Gets the label.
     * 
     * @param fieldLabel
     *            the field label
     * @return the label
     */
    public static String getLabel(String fieldLabel) {
	log.methodEntry("getLabel Method Start");
	String value = "";
	if (fieldLabel.endsWith("* ")) {
	    value = fieldLabel.substring(0, fieldLabel.lastIndexOf("* "));
	} else if (fieldLabel.endsWith("*")) {
	    value = fieldLabel.substring(0, fieldLabel.lastIndexOf("*"));
	} else {
	    value = fieldLabel;
	}
	log.methodExit("getLabel Method End");
	return value.trim();
    }

    /**
     * Gets the label mandatory * as red color.
     * 
     * @param fieldLabel
     *            the field label
     * @return the label
     */
    public static String getLabelWithAsterisk(String fieldLabel) {
	log.methodEntry("getLabelWithAsterisk Method Start");
	String value = "";
	if (fieldLabel.endsWith("* ")) {
	    value = fieldLabel.substring(0, fieldLabel.lastIndexOf("* "));
	    value = value
		    + " <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>";
	} else if (fieldLabel.endsWith("*")) {
	    value = fieldLabel.substring(0, fieldLabel.lastIndexOf("*"));
	    value = value
		    + " <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>";
	} else {
	    value = fieldLabel;
	}
	log.methodExit("getLabelWithAsterisk Method End");
	return value.trim();
    }

    /**
     * Gets the extjs column.
     * 
     * @param jsonarray
     *            the jsonarray
     * @param context
     *            the context
     * @return the extjs column
     * @throws ResourceNotFoundException
     *             the resource not found exception
     * @throws ParseErrorException
     *             the parse error exception
     * @throws Exception
     *             the exception
     */
    public static String getExtjsColumn(JSONArray jsonarray,
	    VelocityContext context) throws ResourceNotFoundException,
	    ParseErrorException, Exception {
	log.methodEntry("getExtjsColumn Method Start");
	StringBuilder columndata = new StringBuilder();

	VelocityEngine ve = new VelocityEngine();
	ve.setProperty(Velocity.RESOURCE_LOADER, "class");
	ve.setProperty("class.resource.loader.class",
		"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	ve.setProperty("runtime.log.logsystem.class",
		"org.apache.velocity.runtime.log.NullLogSystem");

	ve.init();
	try {
	    context.put("dsutilobj", odsUtil);
	    Template columnt = ve.getTemplate("extjscolumn.vm");
	    for (Object jo : jsonarray) {
		JSONObject jsonobj = (JSONObject) jo;

		context.put("classobj", jsonobj);

		StringWriter writer = new StringWriter();
		columnt.merge(context, writer);
		if (!writer.toString().trim().isEmpty()) {
		    String data = writer.toString();
		    columndata.append(data.substring(0, data.lastIndexOf(","))
			    + " ];");
		}

	    }
	} catch (Exception e) {
	    throw new Exception("" + e);
	}
	log.methodExit("getExtjsColumn Method End");
	return columndata.toString();
    }

    /**
     * Rec.
     * 
     * @param ParentPath
     *            the parent path
     * @param j
     *            the j
     * @param master
     *            the master
     */
    public static void rec(String ParentPath, JSONObject j, Map master) {
	log.methodEntry("rec Method Start");
	if (j != null) {
	    if (j.getJSONArray("fields") != null) {
		JSONArray fs = j.getJSONArray("fields");
		for (Object f : fs) {
		    JSONObject fc = (JSONObject) f;
		    JSONObject fj = (JSONObject) fc.get("jaxb");

		    typeMapping.put(fj.get("name"), fc.get("type"));
		    if (fc.get("type").equals("Object")
			    || fc.get("type").equals("IsMultiObject")) {
			JSONObject j1 = (JSONObject) master.get(fj.get("name"));
			/*
			 * if (fc.get("type").equals("JsonString")) { String
			 * pmap = (ParentPath + "/" + fj.get("name"))
			 * .substring(1); fieldMapping.put(fj.get("name"),
			 * pmap); }
			 */
			rec(ParentPath + "/" + fj.get("name"), j1, master);
		    } else {
			if (ParentPath != "") {
			    String pmap = (ParentPath + "/" + fj.get("name"))
				    .substring(1);
			    fieldMapping.put(fj.get("name"), pmap);
			}
		    }
		}
	    }
	}
	log.methodExit("rec Method End");

    }

    /**
     * Gets the model data.
     * 
     * @param jsonarray
     *            the jsonarray
     * @param context
     *            the context
     * @return the model data
     * @throws ResourceNotFoundException
     *             the resource not found exception
     * @throws ParseErrorException
     *             the parse error exception
     * @throws Exception
     *             the exception
     */
    public static void getModelData(JSONArray jsonarray, VelocityContext context)
	    throws ResourceNotFoundException, ParseErrorException, Exception {
	log.methodEntry("getModelData Method Start");
	StringBuilder modelData = new StringBuilder();
	StringBuilder finalData = new StringBuilder();
	String workflowData = "";
	List list = new ArrayList();
	VelocityEngine ve = new VelocityEngine();
	ve.setProperty(Velocity.RESOURCE_LOADER, "class");
	ve.setProperty("class.resource.loader.class",
		"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	ve.setProperty("runtime.log.logsystem.class",
		"org.apache.velocity.runtime.log.NullLogSystem");

	ve.init();
	try {
	    Template columnt = ve.getTemplate("model.vm");
	    context.put("list", list);
	    for (Object jo : jsonarray) {
		JSONObject jsonobj = (JSONObject) jo;
		context.put("classobj", jsonobj);

		StringWriter writer = new StringWriter();
		columnt.merge(context, writer);
		if (!writer.toString().trim().isEmpty()) {
		    modelData.append(writer.toString().trim());
		}

		if (jsonobj.get("implements") != null) {
		    JSONArray implementsArray = (JSONArray) jsonobj
			    .get("implements");
		    if (implementsArray.contains("Workflowable")) {
			workflowData += "'" + (String) jsonobj.get("name")
				+ "',";
		    }
		}
	    }
	} catch (Exception e) {
	    throw new Exception("" + e);
	}
	finalData
		.append("Ext.define('NcmsUIModel', {extend: 'Ext.data.Model', fields: ["
			+ modelData.substring(0, modelData.lastIndexOf(","))
			+ "]});");
	finalData.append("var workflowEntities = ["
		+ workflowData.substring(0, workflowData.lastIndexOf(","))
		+ "];");
	FileWrite(finalData.toString(), "modelnew.js");
	log.methodExit("getModelData Method End");
    }

    /**
     * Replace string.
     * 
     * @param value
     *            the value
     * @return the string
     */
    public static String replaceString(String value) {
	log.methodEntry("replaceString Method Start");
	String val = value.replaceAll(":", "=");
	// if (val.contains("&")) {
	// val = val.replaceAll("&", ",");
	// }
	log.methodExit("replaceString Method End");
	return val;
    }

    public static String getSplitString(String value) {
	log.methodEntry("getSplitString Method Start");
	String val = "";
	if (value.contains(":")) {
	    String[] sval = value.split(":");
	    for (String s : sval) {
		val += "." + s + "|";
	    }
	    val = val.substring(0, val.lastIndexOf("|"));
	} else {
	    val = "." + value;
	}
	log.methodExit("getSplitString Method End");
	return val;
    }

    // Created on 27th sep 2011 by kavitha to generate unique fields as json
    public static String generateUniqueAndMetadataInfoEntityDetails(
	    JSONArray json) {
	log.methodEntry("generateUniqueAndMetadataInfoEntityDetails Method Start");
	JSONObject mapWithoutValue = new JSONObject();
	JSONObject metadataInfoJson = new JSONObject();
	List<String> metadataInfoList = new ArrayList<String>();
	String metaDataInfoList = "";
	try {
	    Map eachMap = new HashMap();
	    for (Object eachClass : json) {
		JSONObject eachField = (JSONObject) eachClass;
		String entityType = "";
		List lstField = new ArrayList();
		for (Object eachAnnoObject : eachField.getJSONArray("fields")) {
		    JSONObject kj = (JSONObject) eachField.get("jaxb");

		    JSONObject eachAnnoParent = (JSONObject) eachAnnoObject;
		    if (eachAnnoParent.get("name").equals("metadataInfo")) {
			metaDataInfoList += "'" + (String) kj.get("name")
				+ "',";
			metadataInfoList.add((String) kj.get("name"));
		    }
		    if ((JSONObject) eachAnnoParent.get("annotations") != null) {
			JSONObject eachRule = (JSONObject) ((JSONObject) eachAnnoParent
				.get("annotations")).get("Rule");
			if (eachRule != null) {
			    String eachUnique = (String) eachRule.get("unique");
			    if (eachUnique != null) {

				if (eachUnique.equals("true")) {
				    entityType = (String) kj.get("name");
				    JSONObject eachFieldJaxb = (JSONObject) eachAnnoParent
					    .get("jaxb");
				    lstField.add(eachFieldJaxb.get("name"));
				} else if (!eachUnique.equals("false")) {
				    entityType = (String) kj.get("name");
				    JSONObject eachFieldJaxb = (JSONObject) eachAnnoParent
					    .get("jaxb");
				    lstField.add(eachFieldJaxb.get("name")
					    + ":" + eachUnique);
				}
			    }
			}
		    }
		}
		if (entityType != "") {
		    eachMap.put(entityType, lstField);
		}
	    }
	    mapWithoutValue.put("unique", eachMap);
	    metadataInfoJson.put("MetadataInfoList", metadataInfoList);
	    FileWrite(mapWithoutValue.toString(), "unique.json");
	    FileWrite(metadataInfoJson.toString(), "metadatainfo.json");
	} catch (Exception ex) {
	}
	log.methodExit("generateUniqueAndMetadataInfoEntityDetails Method End");

	return "var metaDataInfoList = ["
		+ metaDataInfoList.substring(0,
			metaDataInfoList.lastIndexOf(",")) + "]";
    }

    /**
     * Gets the resource path.
     * 
     * @return the resource path
     */
    private static String getResourcePath() {
	PropertiesUtil proputil = PropertiesUtil.getInstanceof(site, "dsutil");
	String resourcePath = proputil.getProperty("location");
	if (!resourcePath.endsWith("/")) {
	    resourcePath += "/";
	}
	return resourcePath += site + "/resources/domain.json";
    }
}

