package in.nic.cmf.xlstoxml;

import in.nic.cmf.properties.PropertiesUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class xlstoxml {
    private HSSFWorkbook workBook = null;
    private String sourcePath = null;
    HashMap<String, Object> response = new HashMap();
    PropertiesUtil proputil = null;

    public xlstoxml(String domain) throws IOException {
	proputil = PropertiesUtil.getInstanceof(domain, "xlstoxml");
	sourcePath = proputil.get("filePath") + domain + "/resources/"
		+ proputil.get("sourceFileName");
	InputStream inputstream = new FileInputStream(sourcePath);
	workBook = new HSSFWorkbook(inputstream);

	setReponse(xlstoxml(workBook, domain));
    }

    public void setReponse(HashMap<String, Object> responseMap) {
	response = responseMap;
    }

    public HashMap<String, Object> getReponse() {
	return response;
    }

    private HashMap<String, Object> xlstoxml(HSSFWorkbook wb, String domain) {
	HSSFSheet classListsheet = wb.getSheet("ClassList");
	HSSFSheet classDetailssheet = wb.getSheet("ClassDetails");
	String classname = "";
	int numberOfRowUsed = classListsheet.getLastRowNum();
	HashMap<String, Object> Collection = new HashMap();
	List domainEntityMapList = new ArrayList();
	for (int i = 1; i <= numberOfRowUsed; i++) {
	    Map<String, Object> domainEntityRootMap = new HashMap();
	    Map<String, Object> domainEntityMap = new HashMap();
	    domainEntityMap.put("Id", "");
	    domainEntityMap.put("EntityType", "DomainEntity");
	    domainEntityMap.put("CreatedDate", "");
	    domainEntityMap.put("LastModifiedDate", "");
	    domainEntityMap.put("CreatedBy", "");
	    domainEntityMap.put("LastModifiedBy", "");
	    domainEntityMap.put("Version", "");
	    domainEntityMap.put("Site", domain);
	    HSSFCell cell = classListsheet.getRow(i).getCell(0);
	    classname = cell.toString().trim();
	    domainEntityMap.put("EntityName", classname);
	    cell = classListsheet.getRow(i).getCell(1);
	    domainEntityMap.put("IsMasterData", cell.toString());
	    cell = classListsheet.getRow(i).getCell(2);
	    domainEntityMap.put("IsTreeEntity", cell.toString());
	    cell = classListsheet.getRow(i).getCell(3);
	    domainEntityMap.put("IsUIEntity", cell.toString());
	    cell = classListsheet.getRow(i).getCell(4);
	    domainEntityMap.put("IsAutoGenUIForm", cell.toString());
	    cell = classListsheet.getRow(i).getCell(5);
	    Map<String, Object> entityGroups = new HashMap();
	    List<String> entityGroup = new ArrayList();
	    List<String> entityGro = new ArrayList();
	    if ((cell != null) && (!cell.toString().isEmpty())) {
		entityGroup = (List<String>) Arrays.asList(cell.toString()
			.split(","));
		for (String val : entityGroup) {
		    entityGro.add(val);

		}
	    }
	    entityGroups.put("EntityGroup", entityGro);
	    domainEntityMap.put("EntityGroups", entityGroups);
	    Map<String, Object> GridProperties = new HashMap<String, Object>();
	    cell = classListsheet.getRow(i).getCell(6);
	    GridProperties.put("Editable", cell.toString());
	    cell = classListsheet.getRow(i).getCell(6);
	    GridProperties.put("Deletable", "");
	    cell = classListsheet.getRow(i).getCell(7);
	    GridProperties.put("Downloadable", cell.toString());
	    domainEntityMap.put("GridProperties", GridProperties);
	    int numberOfRow = classDetailssheet.getLastRowNum();
	    List fieldList = new ArrayList();
	    Map FieldRootMap = new HashMap();
	    for (int j = 1; j <= numberOfRow; j++) {
		HSSFCell celldetail = classDetailssheet.getRow(j).getCell(0);
		String ClassNamedetail = celldetail.toString().trim();
		if (ClassNamedetail.equalsIgnoreCase(classname)) {
		    Map FieldMap = new HashMap();
		    celldetail = classDetailssheet.getRow(j).getCell(3);
		    FieldMap.put("FieldName", celldetail.toString());
		    celldetail = classDetailssheet.getRow(j).getCell(4);
		    FieldMap.put("FieldType", celldetail.toString());
		    celldetail = classDetailssheet.getRow(j).getCell(5);
		    FieldMap.put("IsCDATAField", celldetail.toString());
		    celldetail = classDetailssheet.getRow(j).getCell(6);
		    FieldMap.put("IsAdvanceSearchField", celldetail.toString());
		    celldetail = classDetailssheet.getRow(j).getCell(7);
		    Map<String, Object> FormPropertiesRoot = new HashMap();
		    List<Map<String, Object>> FormPropertiesList = new ArrayList<Map<String, Object>>();

		    if ((celldetail != null) && (celldetail.toString() != null)
			    && (!celldetail.toString().isEmpty())) {
			String Value = celldetail.toString();
			List<String> valueList = Arrays
				.asList(Value.split(","));
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<"
				+ valueList);
			for (String value : valueList) {
			    String valuearray[] = value.split("=");
			    Map<String, Object> FormProperties = new HashMap();
			    Map<String, Object> FormPropertyMap = new HashMap<String, Object>();
			    System.out.println(">>>>>>>>>>>>" + valuearray[0]
				    + "      ");
			    FormPropertyMap.put("key", valuearray[0]);
			    FormPropertyMap.put("Value", valuearray[1]);
			    FormProperties.put("FormProperty", FormPropertyMap);
			    FormPropertiesList.add(FormProperties);
			}

		    }

		    FieldMap.put("FormProperties", FormPropertiesList);
		    fieldList.add(FieldMap);

		    celldetail = classDetailssheet.getRow(j).getCell(8);
		    List<Map<String, Object>> ValidationConstraintsMapList = new ArrayList<Map<String, Object>>();
		    Map<String, Object> ValidationConstraintsMap = new HashMap<String, Object>();
		    if ((celldetail != null) && (celldetail.toString() != null)
			    && (!celldetail.toString().isEmpty())) {
			System.out.println("value::::::" + celldetail);
			String Valuesss = celldetail.getStringCellValue();
			List<String> valueList = Arrays.asList(Valuesss
				.split(","));
			Map<String, Object> ValidationsRoot = new HashMap();
			for (String value : valueList) {
			    Map<String, Object> ValidationConstraintsRoot = new HashMap();
			    Map<String, Object> ValidationConstraints = new HashMap<String, Object>();
			    String valuearray[] = value.split("=");
			    ValidationConstraints.put("ValidationKey",
				    valuearray[0].trim());
			    ValidationConstraints.put("ValidationValue",
				    valuearray[1].trim());
			    ValidationConstraintsMapList
				    .add(ValidationConstraints);
			}
			ValidationsRoot.put("ValidationConstraint",
				ValidationConstraintsMapList);
			FieldMap.put("ValidationConstraints", ValidationsRoot);
		    }

		}

	    }
	    FieldRootMap.put("Field", fieldList);
	    domainEntityMap.put("Fields", FieldRootMap);
	    domainEntityMapList.add(domainEntityMap);
	}
	Collection.put("Collections", domainEntityMapList);
	// FormatXml.getInstanceof("sify.co.in").in(Collection);
	/*
	 * System.out.println("Result>>>" +
	 * FormatXml.getInstanceof("sify.co.in").out(Collection));
	 */
	return Collection;
    }

    public static void main(String args[]) throws IOException {
	// xlstoxml xls = new xlstoxml("nemo.in");
    }
}
