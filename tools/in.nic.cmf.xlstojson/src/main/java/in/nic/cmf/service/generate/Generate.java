package in.nic.cmf.service.generate;

import in.nic.cmf.properties.PropertiesUtil;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * The Class Generate.
 */
public class Generate {

    /** The work book. */
    private HSSFWorkbook workBook = null;

    /** The root. */
    private Map<String, Object> root = new LinkedHashMap<String, Object>();

    /** The rootitem. */
    private Map<String, Object> rootitem = new LinkedHashMap<String, Object>();

    /** The class list. */
    ArrayList<String> classList = null;

    /** The class details. */
    ArrayList<String> classDetails = null;

    /** The interface list. */
    ArrayList<String> interfaceList = null;

    /** The interface details. */
    ArrayList<String> interfaceDetails = null;

    /** The parameter index. */
    private int parameterIndex = 0;

    /** The json. */
    private JSONObject json = null;

    /** The source path. */
    private String sourcePath = null;

    /** The destination path. */
    private static String destinationPath = null;
    PropertiesUtil proputil = null;
    static String domain = "";

    /**
     * Instantiates a new generate.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public Generate(String domain) throws IOException {
	this.domain = domain;
	proputil = PropertiesUtil.getInstanceof(domain, "xlstojson");
	json = new JSONObject();
	/*
	 * sourcePath = System.getProperty("user.dir"); sourcePath +=
	 * "/src/main/resources/";
	 */

	/*
	 * InputStream inStream =
	 * Generate.class.getClassLoader().getResourceAsStream
	 * ("config.properties"); Properties prop=new Properties();
	 * prop.load(inStream);
	 */

	// sourcePath += proputil.getProperty("sourceFileName").toString();
	sourcePath = proputil.get("filePath") + domain + "/resources/"
		+ proputil.get("sourceFileName");
	destinationPath = proputil.get("filePath") + domain + "/resources/"
		+ proputil.get("targetFileName");
	InputStream inputstream = new FileInputStream(sourcePath);
	workBook = new HSSFWorkbook(inputstream);
	Generate(workBook);
	List<String> list = interfaceDetails;
	parameterIndex = interfaceDetails.indexOf("parameter");
    }

    /**
     * Instantiates a new generate.
     * 
     * @param sourcePath
     *            the source path
     * @param destinationPath
     *            the destination path
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public Generate(String sourcePath, String destinationPath)
	    throws IOException {
	json = new JSONObject();
	this.sourcePath = sourcePath;
	this.destinationPath = destinationPath;
	InputStream inputstream = new FileInputStream(sourcePath);
	workBook = new HSSFWorkbook(inputstream);
	Generate(workBook);
	List<String> list = interfaceDetails;
	parameterIndex = interfaceDetails.indexOf("parameter");
	Generate gener = new Generate(domain);

    }

    /**
     * Generate.
     * 
     * @throws Exception
     *             the exception
     */
    public void generate() throws Exception {
	// json.put("domainname",
	// getDomainName(workBook.getSheet("DomainName")));
	getLists("ClassList", "ClassDetails");
	insert("classname", rootitem);
	getLists("InterfaceList", "InterfaceDetails");
	insert("interface", rootitem);
	save(destinationPath, json.toString());
	// System.out.println("Finally : "+json.toString());
    }

    /**
     * Generate.
     * 
     * @param workBook
     *            the work book
     */
    public void Generate(HSSFWorkbook workBook) {
	classList = new ArrayList();
	classDetails = new ArrayList();
	interfaceList = new ArrayList();
	interfaceDetails = new ArrayList();
	List SheetHeaderList = new ArrayList();
	SheetHeaderList.add(classList);
	SheetHeaderList.add(classDetails);
	SheetHeaderList.add(interfaceList);
	SheetHeaderList.add(interfaceDetails);
	String[] SheetList = { "ClassList", "ClassDetails", "InterfaceList",
		"InterfaceDetails" };
	for (int i = 0; i < SheetHeaderList.size(); i++) {

	    HSSFSheet sheet = workBook.getSheet(SheetList[i]);
	    int NumberOfColoumnused = sheet.getRow(0).getLastCellNum();
	    for (int j = 0; j < NumberOfColoumnused; j++) {
		HSSFCell cell = sheet.getRow(0).getCell(j);

		if (cell != null) {
		    ((ArrayList<String>) SheetHeaderList.get(i)).add(cell
			    .toString());
		} else {
		    continue;
		}
	    }
	}
    }

    /*
     * private String getDomainName(HSSFSheet sheet) {
     * if(isEmpty(sheet.getRow(1).getCell(0).toString())==false){ return
     * sheet.getRow(1).getCell(0).toString();} return null; }
     */

    /**
     * Insert.
     * 
     * @param key
     *            the key
     * @param valueMap
     *            the value map
     */
    private void insert(String key, Map<String, Object> valueMap) {
	Set set = valueMap.entrySet();
	Iterator iterator = set.iterator();
	int x = 0;
	Object[] xsample = new Object[valueMap.size()];
	while (iterator.hasNext()) {
	    Map.Entry map = (Map.Entry) iterator.next();
	    xsample[x++] = map.getValue();
	}
	if (xsample.length > 0)
	    json.put(key, xsample);
    }

    /**
     * Save.
     * 
     * @param FilePath
     *            the file path
     * @param Content
     *            the content
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static void save(String FilePath, String Content)
	    throws IOException {
	System.out
		.println(("HELOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" + destinationPath));
	FileWriter fstream = new FileWriter(destinationPath);

	BufferedWriter out = new BufferedWriter(fstream);
	out.write(Content);
	out.flush();
	out.close();

    }

    /**
     * Gets the work book sheet.
     * 
     * @param sheetName
     *            the sheet name
     * @return the work book sheet
     */
    private HSSFSheet getWorkBookSheet(String sheetName) {
	HSSFSheet sheet = workBook.getSheet(sheetName);

	if (isEmpty(sheet) == true)
	    return null;
	return sheet;
    }

    /**
     * String to list.
     * 
     * @param data
     *            the data
     * @param splitToken
     *            the split token
     * @return the list
     */
    private List<String> stringToList(String data, String splitToken) {
	List<String> valueList = new ArrayList<String>();
	String[] values = data.split(",");
	for (int x = 0; x < values.length; x++) {
	    String value = values[x];
	    if (isEmpty(value) == false)
		valueList.add(value);
	}
	if (valueList.size() > 0)
	    return valueList;
	return null;
    }

    /**
     * String to hash map.
     * 
     * @param data
     *            the data
     * @return the map
     */
    private Map<String, String> stringToHashMap(String data) {
	if (isEmpty(data) == true)
	    return null;
	String[] valueInfo = data.split(",");
	Map<String, String> annotation = new HashMap<String, String>();
	for (int x = 0; x < valueInfo.length; x++) {
	    String[] keyvalue = valueInfo[x].split("=");

	    if (keyvalue.length > 1)
		annotation.put(keyvalue[0].trim(), keyvalue[1].trim());
	}
	if (annotation.size() > 0)
	    return annotation;
	return null;
    }

    /**
     * Gets the lists.
     * 
     * @param list
     *            the list
     * @param detail
     *            the detail
     * @return the lists
     */
    private void getLists(String list, String detail) {
	rootitem = new LinkedHashMap<String, Object>();
	String sheetName = list;
	HSSFSheet sheet = getWorkBookSheet(sheetName);
	if (isEmpty(sheet) == false) {
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
		Map<String, String> rowData = readRow(sheetName,
			sheet.getRow(i));
		if (isEmpty(rowData) == true)
		    continue;
		Map<String, Object> classMap = convertData(sheetName, rowData);
		rootitem.put(rowData.get("name"), classMap);
	    }
	}
	sheetName = detail;// "ClassDetails";
	sheet = getWorkBookSheet(sheetName);
	if (isEmpty(sheet) == false) {
	    getDetails(sheetName, sheet);
	}
    }

    /**
     * Gets the details.
     * 
     * @param sheetName
     *            the sheet name
     * @param sheet
     *            the sheet
     * @return the details
     */
    private void getDetails(String sheetName, HSSFSheet sheet) {
	for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	    HSSFRow currentRow = sheet.getRow(i);
	    Map<String, String> cls = readRow(sheetName, currentRow);
	    if ((cls.size() <= 0)
		    || (isEmpty(rootitem.get(cls.get("name"))) == true))
		continue;
	    Map<String, Object> singleCls = (Map<String, Object>) rootitem
		    .get(cls.get("name"));
	    if (isEmpty(singleCls) == true)
		continue;

	    if (isEmpty(cls.get("fieldName")) == false) {
		if (isEmpty(singleCls.get("fields")) == true) {
		    singleCls.put("fields", new ArrayList<Object>());
		}
		Map<String, Object> field = getField(cls);
		((List<Object>) singleCls.get("fields")).add(field);
	    }
	    /*
	     * if (isEmpty(cls.get("methodName")) == false) { if
	     * (isEmpty(singleCls.get("methods")) == true) {
	     * singleCls.put("methods", new ArrayList<Object>()); } Map<String,
	     * Object> method = getMethod(cls, currentRow);
	     * 
	     * ((List<Object>) singleCls.get("methods")).add(method); }
	     */
	}
    }

    /*
     * private Map<String, Object> getMethod(Map<String, String> cls, HSSFRow
     * row) { Map<String, Object> method = new LinkedHashMap<String, Object>();
     * method.put("fieldName", cls.get("fieldName")); method.put("fieldType",
     * cls.get("fieldType"));
     * 
     * if (isEmpty(cls.get("parameter")) == false) { int parameterCount = (int)
     * (Float.parseFloat(cls.get("parameter"))); if (parameterCount > 0) {
     * List<Map<String, String>> parameterList = new ArrayList<Map<String,
     * String>>(); int length = parameterIndex + (parameterCount * 2); for (int
     * x = parameterIndex + 1; x < length; x += 2) { Map<String, String> param =
     * new HashMap<String, String>(); param.put("paramName",
     * row.getCell(x).toString()); param.put("paramType", row.getCell(x +
     * 1).toString()); parameterList.add(param); } method.put("parameter",
     * parameterList); } } return method; }
     */

    /**
     * Gets the field.
     * 
     * @param cls
     *            the cls
     * @return the field
     */
    private Map<String, Object> getField(Map<String, String> cls) {
	List ListItems = new ArrayList();
	Map<String, Object> field = new LinkedHashMap<String, Object>();
	ListItems = ListitemsAdd(ListItems);
	field.put("fieldtype", cls.get("fieldType"));
	field.put("name", cls.get("fieldName"));
	Map<String, Object> annotation = new HashMap<String, Object>();
	if (isEmpty(cls.get("UI")) == false) {
	    Map<String, String> annotationInfo = stringToHashMap(cls.get("UI"));
	    if (isEmpty(annotationInfo) == false)
		annotation.put("UI", annotationInfo);
	}
	if (isEmpty(cls.get("Rule")) == false) {
	    Map<String, String> annotationInfo = stringToHashMap(cls
		    .get("Rule"));
	    if (isEmpty(annotationInfo) == false)
		annotation.put("Rule", annotationInfo);
	}
	if ((isEmpty(annotation.get("UI")) == false)
		|| (isEmpty(annotation.get("Rule")) == false))
	    field.put("annotations", annotation);
	if (isEmpty(cls.get("type")) == false)
	    field.put("type", cls.get("type"));
	if (isEmpty(cls.get("cdata")) == false)
	    field.put("cdata", new Boolean(cls.get("cdata"))); // new
							       // Boolean(cls.get("cdata")));
	if (isEmpty(cls.get("defaultSearch")) == false)
	    field.put("defaultSearch", new Boolean(cls.get("defaultSearch"))); // new
									       // Boolean(cls.get("defaultSearch")));
	if (isEmpty(cls.get("jaxb")) == false)
	    field.put("jaxb", getJaxb(cls.get("jaxb"), "@XmlElement"));
	return field;
    }

    /**
     * Listitems add.
     * 
     * @param listItems
     *            the list items
     * @return the list
     */
    private List ListitemsAdd(List listItems) {
	listItems.add("type");
	listItems.add("cdata");
	listItems.add("defaultSearch");
	listItems.add("jaxb");
	return listItems;
    }

    /**
     * Convert data.
     * 
     * @param sheetName
     *            the sheet name
     * @param rowData
     *            the row data
     * @return the map
     */
    private Map<String, Object> convertData(String sheetName,
	    Map<String, String> rowData) {
	Map<String, Object> classMap = new LinkedHashMap<String, Object>();
	List<String> valueList = null;
	List keys = getKeys(sheetName);
	for (int x = 0; x < keys.size(); x++) {
	    String key = keys.get(x).toString();
	    String value = rowData.get(key);
	    if (isEmpty(value) == true)
		continue;
	    if (key.equals("import") || key.equals("packageName")) {
		valueList = stringToList(value, ",");
		if (isEmpty(valueList) == true || valueList.size() <= 0)
		    continue;
		if (classMap.containsKey("import") == false)
		    classMap.put("import", new ArrayList<String>());
		Iterator<String> iterator = valueList.iterator();
		while (iterator.hasNext()) {
		    value = "package ";
		    if (key.equals("import")) {
			value = "import ";
		    }
		    value += iterator.next().toString().trim() + ";";
		    ((List<String>) classMap.get("import")).add(value);
		}
	    } else if (key.equals("implements")) {
		valueList = stringToList(value, ",");
		if (isEmpty(valueList) == false)
		    classMap.put("implements", valueList);
	    } else if (key.equals("jaxb")) {
		classMap.put(key,
			getJaxb(rowData.get("jaxb"), "@XmlRootElement"));
	    } else if (key.equals("ui")) {
		classMap.put(key, new Boolean(value)); // new Boolean(value));
	    } else {
		classMap.put(key, value);
	    }
	}
	return classMap;
    }

    /**
     * Gets the keys.
     * 
     * @param sheetName
     *            the sheet name
     * @return the keys
     */
    private List getKeys(String sheetName) {
	if (sheetName.equals("ClassDetails")) {
	    return classDetails;
	} else if (sheetName.equals("ClassList")) {
	    return classList;
	} else if (sheetName.equals("InterfaceList")) {
	    return interfaceList;
	} else if (sheetName.equals("InterfaceDetails")) {
	    return interfaceDetails;
	}
	return null;
    }

    /**
     * Read row.
     * 
     * @param sheetName
     *            the sheet name
     * @param row
     *            the row
     * @return the map
     */
    private Map<String, String> readRow(String sheetName, HSSFRow row) {
	List keyList = getKeys(sheetName);
	if (keyList.size() <= 0)
	    return null;
	Map<String, String> rowMap = new HashMap<String, String>();
	int blank = 0;
	for (int i = 0; i < keyList.size(); i++) {
	    rowMap.put(keyList.get(i).toString(), getValue(row, i));
	    if ((rowMap.get(keyList.get(i).toString()) == null)
		    || (rowMap.get(keyList.get(i).toString()).equals(""))) {
		blank++;
	    }
	}
	if (blank == keyList.size()) {
	    return null;
	}
	return rowMap;
    }

    /**
     * Checks if is empty.
     * 
     * @param value
     *            the value
     * @return true, if is empty
     */
    private boolean isEmpty(String value) {
	if ((value == null) || value.equals("")) {
	    return true;
	}
	return false;
    }

    /**
     * Checks if is empty.
     * 
     * @param object
     *            the object
     * @return true, if is empty
     */
    private boolean isEmpty(Object object) {
	if ((object == null) || object.equals("")) {
	    return true;
	}
	return false;
    }

    /**
     * Gets the jaxb.
     * 
     * @param name
     *            the name
     * @param key
     *            the key
     * @return the jaxb
     */
    private Map<String, String> getJaxb(String name, String key) {
	Map<String, String> jaxb = new HashMap<String, String>();
	jaxb.put("name", name);
	jaxb.put("anno", key);
	return jaxb;
    }

    /**
     * Gets the value.
     * 
     * @param row
     *            the row
     * @param column
     *            the column
     * @return the value
     */
    private String getValue(HSSFRow row, int column) {
	if (isEmpty(row.getCell(column)) == false) {
	    return row.getCell(column).toString().trim();
	}
	return null;
    }

}
