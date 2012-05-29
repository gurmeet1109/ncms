package in.nic.cmf.rulegenerator;

import in.nic.cmf.properties.PropertiesUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class JsonToRuleTable {
    private HashMap domainJsonHashMap = new HashMap();
    private HashMap metaDataInfoHashMap = new HashMap();
    HSSFWorkbook ruleTableWorkBook = new HSSFWorkbook();
    HSSFSheet ruleTableSheet = null;
    List metaDataInfoList = null;
    int rowIndex = 9;
    String typeCheck = "";
    String className = "";
    String classNames = "";
    static String domainName = "";
    static JsonToRuleTable rule = null;

    public JsonToRuleTable() {

	try {
	    Json2Java j2j = new Json2Java();
	    System.out.println("process initialized");
	    domainJsonHashMap = (HashMap) j2j
		    .getMap(getContentFromJsonFile(getResourcePath()
			    + "domain.json"));

	    metaDataInfoHashMap = (HashMap) j2j
		    .getMap(getContentFromJsonFile(getResourcePath()
			    + "Mapping.json"));
	    metaDataInfoList = (List) metaDataInfoHashMap
		    .get("MetadataInfoList");
	    addRuleTable();
	    exportClassList();
	    System.out.println("process finished...");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private String getProperties(String key) {

	File f = new File(System.getProperty("user.dir")
		+ "/src/main/resources/config.properties");

	Properties pro = new Properties();
	FileInputStream in;
	String keyValue = "";
	try {
	    in = new FileInputStream(f);
	    pro.load(in);
	    if (pro.get(key) != null) {
		keyValue = pro.get(key).toString();
	    }
	    in.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return keyValue;
    }

    public static void main(String args[]) {
	domainName = System.getProperty("domain");
	rule = new JsonToRuleTable();
    }

    private String getContentFromJsonFile(String fileName)
	    throws FileNotFoundException {
	File file = new File(fileName);
	Scanner scanner = new Scanner(file);
	StringBuffer sbuffer = new StringBuffer();
	while (scanner.hasNextLine()) {
	    sbuffer.append(scanner.nextLine());
	}
	scanner.close();
	return sbuffer.toString();
    }

    private static String getResourcePath() {
	PropertiesUtil proputil = PropertiesUtil.getInstanceof(domainName,
		"resource");
	String resourcePath = proputil.getProperty("location");
	if (!resourcePath.endsWith("/")) {
	    resourcePath += "/";
	}
	return resourcePath + "/" + domainName + "/resources/";
    }

    private void addRuleTable() throws FileNotFoundException {
	File file = new File(JsonToRuleTable.class.getClassLoader()
		.getResource("npiMetaData-domainfree.txt").getFile());
	Scanner scanner = new Scanner(file);
	StringBuffer sbuffer = new StringBuffer();
	while (scanner.hasNextLine()) {
	    sbuffer.append(scanner.nextLine());
	}
	scanner.close();
	String functionContent = sbuffer.toString();
	ruleTableSheet = ruleTableWorkBook.createSheet("Tables");
	HSSFCellStyle tCs = ruleTableWorkBook.createCellStyle();
	tCs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	tCs.setFillForegroundColor(HSSFColor.BLACK.index);
	tCs.setFillBackgroundColor(HSSFCellStyle.SPARSE_DOTS);
	HSSFFont tFont = ruleTableWorkBook.createFont();
	tFont.setColor(HSSFColor.WHITE.index);
	tCs.setFont(tFont);
	HSSFRow row = ruleTableSheet.createRow(1);
	HSSFCell cell = row.createCell(1);
	cell.setCellValue("                                             ");
	ruleTableSheet.autoSizeColumn(1);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue("RuleSet");
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell.setCellValue("in.nic.cmf.ruleengine");
	row = ruleTableSheet.createRow(2);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue("Import");
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell.setCellValue("java.net.URL, org.apache.commons.validator.routines.*,in.nic.cmf.util.*, java.util.*,java.util.List,java.util.Arrays");
	row = ruleTableSheet.createRow(3);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue("Sequential");
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell.setCellValue("false");
	row = ruleTableSheet.createRow(4);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue("Notes                                                                                                      ");
	ruleTableSheet.autoSizeColumn(2);
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell.setCellValue("This decision table is for working out some basic prices and pretending actuaries don't exist");
	ruleTableSheet.autoSizeColumn(3);
	row = ruleTableSheet.createRow(6);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue("Variables");
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell.setCellValue("java.util.HashMap validatereport, java.lang.String entitytype");
	row = ruleTableSheet.createRow(7);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue("FUNCTIONS");
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell.setCellValue(functionContent);
    }

    private void exportClassList() throws IOException {
	List entityDetailsList = (List) domainJsonHashMap.get("classname");
	if (entityDetailsList instanceof List) {

	    Iterator<Object> iterator = (Iterator<Object>) entityDetailsList
		    .iterator();
	    while (iterator.hasNext()) {
		Map<String, Object> item = (Map<String, Object>) iterator
			.next();
		HSSFSheet sheet = ruleTableWorkBook.getSheet("Tables");
		String entityType = item.get("name").toString();
		List implementList = (List) item.get("implements");
		if (implementList == null) {
		    continue;
		}
		String ui = item.get("ui").toString();
		if ((ui == "true") && (implementList.contains("Storable"))) {
		    typeCheck = "String";
		    className = item.get("name").toString();
		    List classDetailList = (ArrayList<?>) item.get("fields");
		    rowIndex += 1;
		    exportClassDetails(className, classDetailList);
		}
	    }
	}

	FileOutputStream fileOut = new FileOutputStream(JsonToRuleTable.class
		.getClassLoader().getResource("validation.xls").getFile());
	ruleTableWorkBook.write(fileOut);
	fileOut.flush();
	fileOut.close();
    }

    private void exportClassDetails(String className, List classDetailList)
	    throws IOException {

	Iterator<Object> iterator = (Iterator<Object>) classDetailList
		.iterator();
	while (iterator.hasNext()) {
	    Map<String, Object> item = (Map<String, Object>) iterator.next();
	    Map<String, Object> annotationsHashMap = null;
	    HashMap jaxb = new HashMap();
	    jaxb = (HashMap) item.get("jaxb");
	    String fieldName = (String) item.get("name");
	    String fieldType = item.get("fieldtype").toString();
	    String type = item.get("type").toString();
	    String jxbNameOfField = jaxb.get("name").toString();
	    annotationsHashMap = (Map<String, Object>) item.get("annotations");
	    if (annotationsHashMap == null) {
		continue;
	    }
	    HashMap rules = (HashMap) annotationsHashMap.get("Rule");
	    String tmpString = "notNull=false";
	    if (rules != null) {
		tmpString = rules.toString();
		tmpString = excludeBraces("}", excludeBraces("{", tmpString));
	    } else {
		continue;
	    }
	    if (fieldType.trim().equals("String")) {
		checkRuleTypes(tmpString, fieldType, fieldType, rules,
			jxbNameOfField);
	    } else if (fieldType.trim().contains("Boolean")) {
		checkRuleTypes(tmpString, fieldType, fieldType, rules,
			jxbNameOfField);
	    } else if (fieldType.trim().equals("List<String>")) {
		checkRuleTypes(tmpString, fieldType, "LsString", rules,
			jxbNameOfField);
	    } else if (type.equalsIgnoreCase("IsMultiObject")) {
		typeCheck = "List";
		exportClassList(jxbNameOfField, classDetailList);
	    } else if ((type.equalsIgnoreCase("Object"))
		    || (type.equalsIgnoreCase("JsonString"))) {
		typeCheck = "String";
		exportClassList(jxbNameOfField, classDetailList);
	    }
	}

    }

    private void checkRuleTypes(String tmpString, String fieldType,
	    String type, HashMap rules, String jxbNameOfField) {

	String[] Ruletype = Ruletype = tmpString.split(",");
	String param = "!=$param";
	String valueOfParam = "null";
	String thenconditionString = "validatereport = addToList('$param',validatereport);";
	String combineCondition = "";
	HashMap meta = null;
	if (metaDataInfoHashMap.get(className) != null) {
	    meta = (HashMap) ((HashMap) metaDataInfoHashMap.get(className))
		    .get("fieldMapping");
	}
	if ((meta != null)
		&& (meta.get(jxbNameOfField) != null)
		&& (meta.get(jxbNameOfField).toString()
			.contains("MetadataInfo"))) {
	    combineCondition = " && eval(validateNpiMetadata($"
		    + className.toLowerCase()
		    + ")==true||validateStatus((String)$"
		    + className.toLowerCase() + ".get(\"Stage\"))==true)";

	}

	if (meta != null) {
	    String path = (String) meta.get(jxbNameOfField);

	}

	if ((((HashMap) metaDataInfoHashMap.get(className)).get("fieldMapping") != null)
		&& (((HashMap) ((HashMap) metaDataInfoHashMap.get(className))
			.get("fieldMapping")).get("jxbNameOfField") != null)
		&& ((HashMap) ((HashMap) metaDataInfoHashMap.get(className))
			.get("fieldMapping")).get("jxbNameOfField").toString()
			.contains(("MetadataInfo"))) {
	    combineCondition = " && eval(validateNpiMetadata($"
		    + className.toLowerCase()
		    + ")==true||validateStatus((String)Stage)==true)";
	}

	for (int i = 0; i < Ruletype.length; i++) {
	    if (Ruletype[i].trim().equals("notNull=true")) {
		if (fieldType.equalsIgnoreCase("Boolean")) {
		    createRuleTable("checkEntity(\"" + className
			    + "\", entitytype) &&" + jxbNameOfField
			    + "==$param" + combineCondition,
			    thenconditionString, "RuleTable :" + jxbNameOfField
				    + " Should not be null", jxbNameOfField
				    + ":" + jxbNameOfField
				    + " should not be null", jxbNameOfField
				    + " length validation", "null");
		} else if (fieldType.equalsIgnoreCase("String")) {
		    createRuleTable("checkEntity(\"" + className
			    + "\", entitytype) &&" + jxbNameOfField
			    + ".trim().length()<=$param" + combineCondition,
			    thenconditionString, "RuleTable :" + jxbNameOfField
				    + " Should not be null", jxbNameOfField
				    + ":" + jxbNameOfField
				    + " should not be null", jxbNameOfField
				    + " null validation", "0");

		} else if (fieldType.equalsIgnoreCase("LsString")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField + ".length() > $param"
				    + combineCondition,
			    "List<String> LsString="
				    + "Arrays.asList(((String)$"
				    + className.toLowerCase()
				    + ".get(\""
				    + jxbNameOfField
				    + "\"))"
				    + ".split(\",\"));if(LsString==null){"

				    + thenconditionString
				    + "}else{for(String str:LsString){if(str.length()<=0){"
				    + thenconditionString + "}}}",
			    "RuleTable :" + jxbNameOfField
				    + " Should not be null", jxbNameOfField
				    + ":" + jxbNameOfField
				    + " should not be null", jxbNameOfField
				    + " null validation", "0");
		}
	    } else if (Ruletype[i].trim().contains("sizeMax")) {
		if (fieldType.equalsIgnoreCase("String")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField
				    + ".trim().length() > $param"
				    + combineCondition,
			    thenconditionString,
			    "RuleTable :" + jxbNameOfField
				    + " Should not be more than "
				    + rules.get("sizeMax") + " characters",
			    jxbNameOfField + ":" + jxbNameOfField
				    + " should not be more than "
				    + rules.get("sizeMax").toString()
				    + " characters", jxbNameOfField
				    + " length validation", rules
				    .get("sizeMax").toString());

		} else if (fieldType.equalsIgnoreCase("LsString")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField + ".length() > $param"
				    + combineCondition,
			    "List<String> LsString="
				    + "Arrays.asList(((String)$"
				    + className.toLowerCase()
				    + ".get(\""
				    + jxbNameOfField
				    + "\"))"
				    + ".split(\",\"));if(LsString==null){"
				    + thenconditionString
				    + "}else{for(String str:LsString){if(str.length()>"
				    + rules.get("sizeMax") + "){"
				    + thenconditionString + "}}}",
			    "RuleTable :" + jxbNameOfField
				    + " Should not be more than "
				    + rules.get("sizeMax").toString()
				    + "characters",
			    jxbNameOfField + ":" + jxbNameOfField
				    + " Should not be more than "
				    + rules.get("sizeMax").toString()
				    + " characters", jxbNameOfField
				    + " length validation", "0");

		}

	    } else if (Ruletype[i].trim().contains("sizeMin")) {
		if (fieldType.equalsIgnoreCase("String")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField
				    + ".trim().length() > $param"
				    + combineCondition,
			    "if(((String)$" + className.toLowerCase()
				    + ".get(\"" + jxbNameOfField + "\"))"
				    + ".trim().length() <"
				    + rules.get("sizeMin").toString() + "){"
				    + thenconditionString + "}",
			    "RuleTable :" + jxbNameOfField
				    + " Should not be less than "
				    + rules.get("sizeMin") + " characters",
			    jxbNameOfField + ":" + jxbNameOfField
				    + " should not be less than "
				    + rules.get("sizeMin").toString()
				    + " characters", jxbNameOfField
				    + " length validation", "0");

		} else if (fieldType.equalsIgnoreCase("LsString")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField + ".length() > $param"
				    + combineCondition,
			    "List<String> LsString="
				    + "Arrays.asList(((String)$"
				    + className.toLowerCase()
				    + ".get(\""
				    + jxbNameOfField
				    + "\"))"
				    + ".split(\",\"));if(LsString==null){"
				    + thenconditionString
				    + "}else{for(String str:LsString){if(str.length()<"
				    + rules.get("sizeMin") + "){"
				    + thenconditionString + "}}}",
			    "RuleTable :" + jxbNameOfField
				    + " Should not be less than "
				    + rules.get("sizeMin").toString()
				    + "characters",
			    jxbNameOfField + ":" + jxbNameOfField
				    + " Should not be less than "
				    + rules.get("sizeMin").toString()
				    + " characters", jxbNameOfField
				    + " length validation", "0");
		}

	    } else if (Ruletype[i].trim().contains("type")) {

		if (!getProperties(rules.get("type").toString().trim())
			.isEmpty()) {
		    if (fieldType.equalsIgnoreCase("String")) {

			createRuleTable(
				"checkEntity(\"" + className
					+ "\", entitytype) &&" + jxbNameOfField
					+ ".trim().length() > $param"
					+ combineCondition,
				getProperties(rules.get("type").toString())
					+ "((String)$"
					+ className.toLowerCase() + ".get(\""
					+ jxbNameOfField + "\"))" + ")){"
					+ thenconditionString + "}",
				"RuleTable :"
					+ jxbNameOfField
					+ " "
					+ getProperties(rules.get("type")
						.toString() + "-Title"),
				jxbNameOfField
					+ ":"
					+ jxbNameOfField
					+ " "
					+ getProperties(rules.get("type")
						.toString() + "-Description"),
				jxbNameOfField
					+ " "
					+ getProperties(rules.get("type")
						.toString() + "-validationName"),
				"0");

		    } else if (fieldType.equalsIgnoreCase("LsString")) {
			createRuleTable(
				"checkEntity(\"" + className
					+ "\", entitytype) &&" + jxbNameOfField
					+ ".length() > $param"
					+ combineCondition,
				"List<String> LsString="
					+ "Arrays.asList(((String)$"
					+ className.toLowerCase()
					+ ".get(\""
					+ jxbNameOfField
					+ "\"))"
					+ ".split(\",\"));if(LsString==null){"
					+ thenconditionString
					+ "}else{for(String str:LsString){"
					+ getProperties(rules.get("type")
						.toString()) + "str" + ")){"
					+ thenconditionString + "}}}",
				"RuleTable :"
					+ jxbNameOfField
					+ "  "
					+ " "
					+ getProperties(rules.get("type")
						.toString() + "-Title"),
				jxbNameOfField
					+ ":"
					+ jxbNameOfField
					+ "  "
					+ getProperties(rules.get("type")
						.toString() + "-Description"),
				jxbNameOfField
					+ "  "
					+ getProperties(rules.get("type")
						.toString() + "-validationName"),
				"0");
		    }

		}
	    } else if (Ruletype[i].trim().contains("allowedFormat")) {

		if (fieldType.equalsIgnoreCase("String")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField
				    + ".trim().length() > $param"
				    + combineCondition,
			    "boolean caseSensitive = false; String regex   = \"[^\\\\s]+(\\\\.(?i)("
				    + rules.get("allowedFormat").toString()
					    .replaceAll(":", "|")
				    + "))$\";RegexValidator validator = new RegexValidator(regex, caseSensitive); if(!validator.isValid("
				    + "((String)$" + className.toLowerCase()
				    + ".get(\"" + jxbNameOfField + "\"))"
				    + ")){" + thenconditionString + "}",
			    "RuleTable :" + jxbNameOfField + " "
				    + getProperties("allowedFormat-Title"),
			    jxbNameOfField
				    + ":"
				    + jxbNameOfField
				    + " "
				    + getProperties("allowedFormat-Description"),
			    jxbNameOfField
				    + " "
				    + getProperties("allowedFormat-validationName"),
			    "0");

		} else if (fieldType.equalsIgnoreCase("LsString")) {
		    createRuleTable(
			    "checkEntity(\"" + className + "\", entitytype) &&"
				    + jxbNameOfField + ".length() > $param"
				    + combineCondition,
			    "List<String> LsString="
				    + "Arrays.asList(((String)$"
				    + className.toLowerCase()
				    + ".get(\""
				    + jxbNameOfField
				    + "\"))"
				    + ".split(\",\"));if(LsString==null){"
				    + thenconditionString
				    + "}else{for(String str:LsString){"
				    + "boolean caseSensitive = false; String regex   = \"[^\\\\s]+(\\\\.(?i)("
				    + rules.get("allowedFormat").toString()
					    .replaceAll(":", "|")
				    + "))$\";RegexValidator validator = new RegexValidator(regex, caseSensitive); if(!validator.isValid("
				    + jxbNameOfField + ")){"
				    + thenconditionString + "}}}",
			    "RuleTable :" + jxbNameOfField + "  " + " "
				    + getProperties("allowedFormat-Title"),
			    jxbNameOfField
				    + ":"
				    + jxbNameOfField
				    + "  "
				    + getProperties("allowedFormat-Description"),
			    jxbNameOfField
				    + "  "
				    + getProperties("allowedFormat-validationName"),
			    "0");
		}

	    }
	}
    }

    private void exportClassDetailsForList(String className,
	    List classDetailList) throws IOException {
	// JsonToRuleTable rule = new JsonToRuleTable();
	Iterator<Object> iterator = (Iterator<Object>) classDetailList
		.iterator();
	while (iterator.hasNext()) {
	    Map<String, Object> item = (Map<String, Object>) iterator.next();
	    Map<String, Object> annotationsHashMap = null;
	    HashMap jaxb = new HashMap();
	    jaxb = (HashMap) item.get("jaxb");
	    String fieldName = (String) item.get("name");
	    String fieldType = item.get("fieldtype").toString();
	    String type = item.get("type").toString();
	    String jxbNameOfField = jaxb.get("name").toString();
	    annotationsHashMap = (Map<String, Object>) item.get("annotations");
	    if (annotationsHashMap == null) {
		continue;
	    }
	    HashMap rules = (HashMap) annotationsHashMap.get("Rule");
	    String tmpString = "notNull=false";
	    if (rules != null) {
		tmpString = rules.toString();
		tmpString = excludeBraces("}", excludeBraces("{", tmpString));
	    } else {
		continue;
	    }
	    if (fieldType.trim().equals("String")) {
		checkRuleTypes(tmpString, "LsString", "String", rules,
			jxbNameOfField);
	    } else if (fieldType.trim().contains("Boolean")) {
		checkRuleTypes(tmpString, "LsBoolean", "Boolean", rules,
			jxbNameOfField);
	    } else if (fieldType.trim().equals("List<String>")) {
		checkRuleTypes(tmpString, "LsString", "String", rules,
			jxbNameOfField);
	    } else if (type.equalsIgnoreCase("IsMultiObject")) {
		typeCheck = "List";
		exportClassList(jxbNameOfField, classDetailList);
	    } else if ((type.equalsIgnoreCase("Object"))
		    || (type.equalsIgnoreCase("JsonString"))) {
		typeCheck = "List";
		exportClassList(jxbNameOfField, classDetailList);
	    }
	}
    }

    private void exportClassList(String jxbNameOfEntity, List clslist)
	    throws IOException {
	List classList = (List) domainJsonHashMap.get("classname");
	List entityDetailList = null;
	if (classList instanceof List) {
	    Iterator<Object> iterator = (Iterator<Object>) classList.iterator();
	    while (iterator.hasNext()) {
		Map<String, Object> entityHashMap = (Map<String, Object>) iterator
			.next();
		classNames = entityHashMap.get("name").toString();
		if (classNames.trim().equalsIgnoreCase(jxbNameOfEntity)) {
		    entityDetailList = (ArrayList<?>) entityHashMap
			    .get("fields");
		    if (typeCheck.equalsIgnoreCase("List")) {
			exportClassDetailsForList(classNames, entityDetailList);
		    }
		    exportClassDetails(classNames, entityDetailList);
		}
	    }
	}
    }

    private String excludeBraces(String excludeValue, String data) {
	return data.replace(excludeValue, "");
    }

    private void createRuleTable(String lengthChecking,
	    String splitfieldMapping, String Title, String businessrule,
	    String Businessvalues, String param) {
	HSSFCellStyle tCs = ruleTableWorkBook.createCellStyle();
	tCs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	tCs.setFillForegroundColor(HSSFColor.BLACK.index);
	tCs.setBorderBottom((short) 1);
	tCs.setBorderLeft((short) 1);
	tCs.setBorderRight((short) 1);
	tCs.setBorderTop((short) 1);
	HSSFFont tFont = ruleTableWorkBook.createFont();
	tFont.setColor(HSSFColor.WHITE.index);
	tFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	tCs.setFont(tFont);
	HSSFCellStyle gray = ruleTableWorkBook.createCellStyle();
	gray.setFillPattern(gray.SOLID_FOREGROUND);
	gray.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	gray.setBorderBottom((short) 1);
	gray.setBorderLeft((short) 1);
	gray.setBorderRight((short) 1);
	gray.setBorderTop((short) 1);
	HSSFCellStyle orange = ruleTableWorkBook.createCellStyle();
	orange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	orange.setFillForegroundColor(HSSFColor.TAN.index);
	orange.setBorderBottom((short) 1);
	orange.setBorderLeft((short) 1);
	orange.setBorderRight((short) 1);
	orange.setBorderTop((short) 1);
	HSSFCellStyle paleGreen = ruleTableWorkBook.createCellStyle();
	paleGreen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	paleGreen.setFillForegroundColor(HSSFColor.AQUA.index);
	paleGreen.setBorderBottom((short) 1);
	paleGreen.setBorderLeft((short) 1);
	paleGreen.setBorderRight((short) 1);
	paleGreen.setBorderTop((short) 1);
	HSSFCellStyle turquoise = ruleTableWorkBook.createCellStyle();
	turquoise.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	turquoise.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	// log.debug("the number is"+turquoise.getFillForegroundColor());
	turquoise.setBorderBottom((short) 1);
	turquoise.setBorderLeft((short) 1);
	turquoise.setBorderRight((short) 1);
	turquoise.setBorderTop((short) 1);
	HSSFRow row = ruleTableSheet.createRow(rowIndex);
	HSSFCell cell = row.createCell(1);
	cell.setCellStyle(gray);
	cell = row.createCell(3);
	cell.setCellStyle(tCs);
	cell = row.createCell(2);
	cell.setCellStyle(tCs);
	cell.setCellValue(Title);
	row = ruleTableSheet.createRow(rowIndex + 1);
	cell = row.createCell(1);
	cell.setCellStyle(gray);
	cell = row.createCell(2);
	cell.setCellStyle(orange);
	cell.setCellValue("CONDITION");
	cell = row.createCell(3);
	cell.setCellStyle(orange);
	cell.setCellValue("ACTION");
	cell = row.createCell(4);
	cell.setCellStyle(orange);
	cell.setCellValue("PRIORITY");
	row = ruleTableSheet.createRow(rowIndex + 2);
	cell = row.createCell(1);
	cell.setCellStyle(gray);
	cell = row.createCell(3);
	cell.setCellStyle(orange);
	cell = row.createCell(2);
	cell.setCellValue("$" + className.toLowerCase() + ":HashMap");
	cell.setCellStyle(orange);
	row = ruleTableSheet.createRow(rowIndex + 3);
	cell = row.createCell(1);
	cell.setCellStyle(gray);
	cell = row.createCell(3);
	cell.setCellStyle(orange);
	cell.setCellValue(splitfieldMapping);
	cell = row.createCell(2);
	cell.setCellStyle(orange);
	cell.setCellValue(lengthChecking);
	cell = row.createCell(4);
	cell.setCellStyle(orange);
	cell.setCellValue("$param");
	row = ruleTableSheet.createRow(rowIndex + 5);
	cell = row.createCell(3);
	cell.setCellStyle(turquoise);
	cell.setCellValue(businessrule);
	cell = row.createCell(2);
	cell.setCellStyle(turquoise);
	cell.setCellValue(param);
	cell = row.createCell(1);
	cell.setCellStyle(paleGreen);
	cell.setCellValue("Business values");
	cell = row.createCell(4);
	cell.setCellStyle(orange);
	cell.setCellValue(1);
	row = ruleTableSheet.createRow(rowIndex + 4);
	cell = row.createCell(2);
	cell.setCellStyle(turquoise);
	cell.setCellValue(Businessvalues);
	cell = row.createCell(3);
	cell.setCellStyle(turquoise);
	cell = row.createCell(1);
	cell.setCellStyle(paleGreen);
	cell.setCellValue("Business rules");
	rowIndex += 7;
    }

}