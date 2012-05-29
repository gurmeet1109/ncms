package in.nic.cmf.service.generate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * The Class JsonToCSV.
 */
public class JsonToCSV {
   
   /** The wb. */
   HSSFWorkbook wb = new HSSFWorkbook();
   
   /** The sheet1. */
   HSSFSheet sheet1 = wb.createSheet("ClassList");
   
   /** The sheet2. */
   HSSFSheet sheet2 = wb.createSheet("ClassDetails");
   
   /** The sheet3. */
   HSSFSheet sheet3 = wb.createSheet("InterfaceList");
   
   /** The sheet4. */
   HSSFSheet sheet4 = wb.createSheet("InterfaceDetails");
   
   /** The rowindex for class details. */
   int rowindexForClassDetails = 1;
   
   /** The rowindex for interface details. */
   int rowindexForInterfaceDetails = 1;
   
   /** The splitter. */
   private String splitter = null;
   
   /** The json string. */
   private String jsonString = null;
   
   /** The Inter face list header. */
   List<String> InterFaceListHeader = getList("Interface Name,Package Name,Imports");
   
   /** The Inter face deail header. */
   List<String> InterFaceDeailHeader = getList("Interface Name,Field,FieldType,Method Name,Return Type,parameter,Parameter Name,Parameter Type");
   
   /**
    * The main method.
    * @param a the arguments
    * @throws Exception the exception
    */
   public static void main(String a[]) throws Exception {
      JsonToCSV j2csv = new JsonToCSV("domain.json");
      j2csv.export();
   }
   
   /**
    * Instantiates a new json to csv.
    * @param jsonFileName the json file name
    * @throws FileNotFoundException the file not found exception
    */
   public JsonToCSV(String jsonFileName) throws FileNotFoundException {
      String jsonString = getContent(jsonFileName);
      setJsonString(jsonString);
      setSplitter(",");
   }
   
   /**
    * Export.
    * @throws Exception the exception
    */
   public void export() throws Exception {
      Json2Java j2j = new Json2Java();
      Map<String, Object> resp = j2j.getMap(getJsonString());
      List<?> classList = (ArrayList<?>) resp.get("classname");
      List<?> interfaceList = (ArrayList) resp.get("interface");
      exportClassList(classList);
      exportInterfaceList(interfaceList);
   }
   
   /**
    * Export interface list.
    * @param InterFaceList the inter face list
    * @throws IOException Signals that an I/O exception has occurred.
    */
   private void exportInterfaceList(List<?> InterFaceList) throws IOException {
      FileOutputStream fileOut = new FileOutputStream("/tmp/domainGenerated.xls");
      List<String> InterfaceListHeader = getList("Name,Package,Import");
      HSSFSheet sheet = wb.getSheet("InterfaceList");
      SaveHeaderDetails(InterfaceListHeader, sheet);
      List<String> keysToRead = getList("name,import");
         if (InterFaceList instanceof List) {
         Iterator<Object> iterator = (Iterator<Object>) InterFaceList.iterator();
         int x = 1;
         StringBuffer lineitem = null;
         Object object = null;
         while (iterator.hasNext()) {
            int c = 0;
            HSSFRow row = sheet.createRow(x);
            lineitem = new StringBuffer();
            Map<String, Object> item = (Map<String, Object>) iterator.next();
            Iterator<String> keyReadIterator = keysToRead.iterator();
            String className = null;
            while (keyReadIterator.hasNext()) {
               String key = keyReadIterator.next();
               className = item.get("name").toString();
               HSSFCell cellHeader = row.createCell(c);
               cellHeader.setCellValue(className);
               object = item.get(key);
               if (key.equals("import")) {
                   HSSFCell cell = row.createCell(c);
                   cell.setCellValue(getString((List<String>) object, "package"));
                   HSSFCell cell1 = row.createCell(c + 1);
                   cell1.setCellValue(formatData(getString((List<String>)object,"import")));
               } else { // other keys "name,ui,implements" continue below.....
                  if (object instanceof List) {
                     HSSFCell cell = row.createCell(c);
                     cell.setCellValue(getString((List<String>) object));
                  } else if (object != null) {
                     HSSFCell cell = row.createCell(c);
                     cell.setCellValue(object.toString());
                  } 
               }
               c++;
            } 
            object = null;
            List<?> InterFaceDetailList = (ArrayList<?>) item.get("methods");
            exportInterFaceDetails(className, InterFaceDetailList);
           x++;
         }
      } else {
         System.out.println("Error : unknown type class information....");
      }
      wb.write(fileOut);
      fileOut.flush();
      fileOut.close();
   }
   
   /**
    * Export inter face details.
    * @param className the class name
    * @param InterfaceDetailList the interface detail list
    */
   private void exportInterFaceDetails(String className,
         List<?> InterfaceDetailList) {
      List<String> InterfaceDetailsHeader = getList("Interface Name,fieldName,fieldType,Method Name,Return Type,parameter,Parameter Name,Parameter Type");
      HSSFSheet sheet = wb.getSheet("InterfaceDetails");
      SaveHeaderDetails(InterfaceDetailsHeader, sheet);
      Iterator<Object> iterator = (Iterator<Object>) InterfaceDetailList.iterator();
      List<String> keysToRead = getList("methodName,returnType,parameter");
      while (iterator.hasNext()) {
         int c = 3;
         HSSFRow row = sheet.createRow(rowindexForInterfaceDetails);
         HSSFCell cellHeader = row.createCell(0);        
         cellHeader.setCellValue(className);        
         HSSFCell cellfield = row.createCell(1);
         cellfield.setCellValue("  ");        
         HSSFCell cellfieldty = row.createCell(2);
         cellfieldty.setCellValue(" ");
         Map<String, Object> item = (Map<String, Object>) iterator.next();
         Iterator<String> keyReadIterator = keysToRead.iterator();
         while (keyReadIterator.hasNext()) {
            String key = keyReadIterator.next();
            Object object = item.get(key);
            int parameterSize = 0;
            Map<String, Object> subMap = null;
            List subList = null;
            if ((key == null)) {
               continue;
            }
            else if (key.equals("parameter")) {
               subList = (List) object;
               c=5;
               HSSFCell cell = row.createCell(c);
               c++;
               if (subList != null) {
                  parameterSize = subList.size();                 
                  cell.setCellValue(parameterSize);
                  if (parameterSize != 0) {
                     for (int i = 0; i <= parameterSize - 1; i++) {
                        Map<String, String> itemmm = (Map<String, String>) subList.get(i);
                        if (i < parameterSize - 1) {
                           HSSFCell cellparamName = row.createCell(c);
                           cellparamName.setCellValue(itemmm.get("paramName"));
                           HSSFCell cellparamType = row.createCell(c+1);
                           cellparamType.setCellValue(itemmm.get("paramType"));
                        } else {
                           HSSFCell cellparamName = row.createCell(c);
                           cellparamName.setCellValue(itemmm.get("paramName"));
                           HSSFCell cellparamType = row.createCell(c + 1);
                           cellparamType.setCellValue(itemmm.get("paramType"));
                        }
                        c+=2;
                     }
                  }

               } else {
                  cell.setCellValue(parameterSize);
               }
            }
            else {
               if (object != null) {
                   HSSFCell cell = row.createCell(c);
                   cell.setCellValue(object.toString());
               } 
            }
            c++;
         }
         rowindexForInterfaceDetails++;
      }
   }
   
   /**
    * Export class details.
    * @param className the class name
    * @param classDetailList the class detail list
    * @throws IOException Signals that an I/O exception has occurred.
    */
   private void exportClassDetails(String className, List<?> classDetailList)throws IOException {
      List<String> classDeailHeader = getList("Class Name,Field Name,Field Type,Jaxb,Type,cdata,defaultSearch,UI Data,Rule Data");
      HSSFSheet sheet = wb.getSheet("ClassDetails");
      SaveHeaderDetails(classDeailHeader, sheet);
      Iterator<Object> iterator = (Iterator<Object>) classDetailList.iterator();
      List<String> keysToRead = getList("name,fieldtype,jaxb,type,cdata,defaultSearch,annotations");
      while (iterator.hasNext()) {
         int c = 1;
         HSSFRow row = sheet.createRow(rowindexForClassDetails);
         HSSFCell cell5 = row.createCell(0);
         cell5.setCellValue(className);
         Map<String, Object> item = (Map<String, Object>) iterator.next();
         Iterator<String> keyReadIterator = keysToRead.iterator();
         while (keyReadIterator.hasNext()) {
            String key = keyReadIterator.next();
            Object object = item.get(key);
            Map<String, Object> subMap = null;
            if (!(key != null)) {
               continue;
            }
            if (key.equals("jaxb")) {
               HSSFCell cell = row.createCell(c);
               subMap = (Map<String, Object>) object;
                  if (subMap == null) {
                  cell.setCellValue("\"\"");
                  continue;
                  }
                  cell.setCellValue(subMap.get("name").toString());
            } else if (key.equals("annotations")) {
               subMap = (Map<String, Object>) object;
               if (subMap == null) {
                  continue;
               }
               HSSFCell cell = row.createCell(c);
               object = subMap.get("UI");
               if (object != null) {
                  String tmpString = object.toString();
                  tmpString = exclude("}", exclude("{", tmpString));
                  cell.setCellValue(tmpString);
               }              
               HSSFCell cell1 = row.createCell(c + 1);
               object = subMap.get("Rule");
               if (object != null) {
                  String tmpString = object.toString();
                  tmpString = exclude("}", exclude("{", tmpString));                 
                  cell1.setCellValue(tmpString);
               } 
            } else {
               HSSFCell cell = row.createCell(c);
               if (object != null) {                 
                  cell.setCellValue(object.toString());
               }
            }
            c++;
         }
         rowindexForClassDetails++;
      }
   }
   
   /**
    * Export class list.
    * @param classList the class list
    * @throws IOException Signals that an I/O exception has occurred.
    */
   private void exportClassList(List<?> classList) throws IOException {
      List<String> keysToRead = getList("name,ui,implements,jaxb,import");
      List<String> classListHeader = getList("Class Name,GUI,Implements,Jaxb,Package Name,Import");
      HSSFSheet sheet = wb.getSheet("ClassList");
      SaveHeaderDetails(classListHeader, sheet);
      FileOutputStream fileOut = new FileOutputStream("/tmp/domainGenerated.xls");
      if (classList instanceof List) {        
         Iterator<Object> iterator = (Iterator<Object>) classList.iterator();
         int x = 1;
         Object object = null;
         while (iterator.hasNext()) {
            int c = 0;
            HSSFRow row = sheet.createRow(x);
            Map<String, Object> item = (Map<String, Object>) iterator.next();
            Map<String, Object> jaxb = new HashMap();
            Iterator<String> keyReadIterator = keysToRead.iterator();
            String className = null;
            while (keyReadIterator.hasNext()) { 
               String key = keyReadIterator.next();
               className = item.get("name").toString();
               object = item.get(key);
               if (key.equals("import")) {
                  HSSFCell cell = row.createCell(c);
                  HSSFCell cell1 = row.createCell(c + 1);
                  cell.setCellValue(getString((List<String>) object, "package"));
                  cell1.setCellValue(getString((List<String>) object, "import"));
               } else if (key.equals("jaxb")) {
                  HSSFCell celljaxb = row.createCell(c);
                  jaxb = (Map<String, Object>) object;
                  if (jaxb == null) {                   
                     continue;                  }
                  celljaxb.setCellValue(jaxb.get("name").toString());                  
               } else { 
                  if (object instanceof List) {
                     HSSFCell cell = row.createCell(c);
                     cell.setCellValue(getString((List<String>) object));                    
                  } else if (object != null) {
                     HSSFCell cell = row.createCell(c);
                     cell.setCellValue(object.toString());                    
                  } 
               }
               c++;
            } 
            object = null;
            List<?> classDetailList = (ArrayList<?>) item.get("fields");
            exportClassDetails(className, classDetailList);
            x++;
         }
      } else {
         System.out.println("Error : unknown type class information....");
      }
      wb.write(fileOut);
      fileOut.flush();
      fileOut.close();
   }
   
   /**
    * Save header details.
    * @param classListHeader the class list header
    * @param sheet the sheet
    */
   private void SaveHeaderDetails(List<String> classListHeader, HSSFSheet sheet) {
      HSSFRow row = sheet.createRow(0);
      for (int j = 0; j < classListHeader.size(); j++) {
         HSSFCell cell = row.createCell(j);
         cell.setCellValue(classListHeader.get(j));
      }
   }
   
   /**
    * Gets the list.
    * @param keyString the key string
    * @return the list
    */
   private List<String> getList(String keyString) {
      List<String> stringList = new ArrayList<String>();
      StringTokenizer stringToken = new StringTokenizer(keyString, ",");
      while (stringToken.hasMoreTokens()) {
         stringList.add(stringToken.nextToken());
      }
      return stringList;
   }
   
   /**
    * Exclude.
    * @param excludeValue the exclude value
    * @param data the data
    * @return the string
    */
   private String exclude(String excludeValue, String data) {
      return data.replace(excludeValue, "");
   }
   
   /**
    * Gets the string.
    * @param stringList the string list
    * @param key the key
    * @return the string
    */
   private String getString(List<String> stringList, String key) {
      String result = null;
      String data = stringList.get(0).trim();
      if (key.equals("package")) {
         if (isStartWith("package", data)) {
            result = data.substring("package".length());
            result = exclude("package ", result); // result.replace("package ","");
         }
      } else if (key.equals("import")) {
         if (isStartWith("package", data)) {
            stringList.remove(0);
         }
         result = getString(stringList);
         result = exclude("import ", result); // result.replace("import ","");
      }
      result = exclude(";", result).trim(); // result.replace(";","").trim();
      return result;
   }
   
   /**
    * Format data.
    * @param stringList the string list
    * @return the string
    */
   private String formatData(List<String> stringList) {
      StringBuffer stringbuffer = new StringBuffer();
      Iterator<String> iterator = stringList.iterator();
      while (iterator.hasNext()) {
         if (stringbuffer.length() > 0) {
            stringbuffer.append(getSplitter());
         }
         stringbuffer.append(formatData(iterator.next()));
      }
      return stringbuffer.toString();
   }
   
   /**
    * Checks if is start with.
    * @param key the key
    * @param data the data
    * @return true, if is start with
    */
   private boolean isStartWith(String key, String data) {
      return data.startsWith(key);
   }
   
   /**
    * Gets the string.
    * @param stringList the string list
    * @return the string
    */
   private String getString(List<String> stringList) {
      StringBuffer stringbuffer = new StringBuffer();
      Iterator<String> iterator = stringList.iterator();
      while (iterator.hasNext()) {
         if (stringbuffer.length() > 0) {
            stringbuffer.append(getSplitter());
         }
         stringbuffer.append(iterator.next());
      }
      return stringbuffer.toString();
   }
   
   /**
    * Format data.
    * @param data the data
    * @return the string
    */
   private String formatData(String data){
      if(data!=null){
          data = data;
      }
      return data;
  }
   
   /**
    * Gets the content.
    * @param fileName the file name
    * @return the content
    * @throws FileNotFoundException the file not found exception
    */
   private String getContent(final String fileName)
         throws FileNotFoundException {
       
       //JsonToCSV.class.getClassLoader().getResourceAs("config.properties");
      File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
      Scanner scanner = new Scanner(file);
      StringBuffer sbuffer = new StringBuffer();
      while (scanner.hasNextLine()) {
         sbuffer.append(scanner.nextLine());
      }
      scanner.close();
      return sbuffer.toString();
   }
   
   /**
    * Sets the splitter.
    * @param splitter the new splitter
    */
   public void setSplitter(String splitter) {
      this.splitter = splitter;
   }
   
   /**
    * Gets the splitter.
    * @return the splitter
    */
   public String getSplitter() {
      return splitter;
   }
   
   /**
    * Sets the json string.
    * @param jsonString the new json string
    */
   public void setJsonString(String jsonString) {
      this.jsonString = jsonString;
   }
   
   /**
    * Gets the json string.
    * @return the json string
    */
   public String getJsonString() {
      return jsonString;
   }
}
