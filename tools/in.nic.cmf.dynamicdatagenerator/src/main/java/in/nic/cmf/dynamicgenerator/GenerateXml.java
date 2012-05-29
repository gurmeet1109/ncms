package in.nic.cmf.dynamicgenerator;

import java.io.File;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.omg.CORBA.SystemException;

import in.nic.cmf.ServiceClient;
import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.domain.Article;
import in.nic.cmf.domain.Authentication;
import in.nic.cmf.domain.Collections;
import in.nic.cmf.domain.DependentItem;
import in.nic.cmf.domain.DependentItems;
import in.nic.cmf.domain.DomainFactory;
import in.nic.cmf.domain.FieldDetails;
import in.nic.cmf.domain.Location;
import in.nic.cmf.domain.NextPossibleEvents;
import in.nic.cmf.domain.PossibleEvents;
import in.nic.cmf.domain.Sector;
import in.nic.cmf.domain.ValidationStatus;
import in.nic.cmf.domain.WorkflowInfo;

import in.nic.cmf.util.DynamicAuthentication;
import in.nic.cmf.util.UserContext;
import in.nic.cmf.util.Utils;

public class GenerateXml {
	private DomainFactory domainfactory;
	private DomainFactory domainfact;
	List<?> classDetailList = new ArrayList();
	private String jsonString = null;
	List<?> classList = null;
	Object clsobject = null;
	Collections collections = null;
	static String site = "";
	static int count = 1;
	static String domainname = null;
	String entityName = "";
	static String checkFor = "";
	HashMap status = null;
	Writer output = null;
	 String id="";
	Map hRequestParam=new HashMap();
	ServiceClient sc=null;
      static String  query ="";
      static String classNames="";
      static String postFor="";
      static FileWriter fw = null;		
      static String url="";
      static String url1="";
      String authUsername ="";
	public static void main(String args[]) throws Exception {
			try {			
				site = args[0];
				count = Integer.parseInt(args[1]);
				domainname = args[2];
				checkFor = args[3];
				postFor=args[4];
			} catch (Exception e) {
				site = "ramya.in";
				count = 1;
				domainname = "article";
				checkFor = "valid";
				postFor="Form";
			}
			GenerateXml Mappingjson = new GenerateXml(GenerateXml.class.getClassLoader().getResource("domain.json").getFile());	
			Mappingjson.getclassfielddetails();		
			fw.close();
	    }
	private static void postusingurlconnections() throws IOException { 		
		url = "http://124.7.228.74:8080/dataservices/app/"+site+"/"+classNames+"/?"+url1;		
		String charset = "UTF-8";
		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true); // Triggers POST.
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=" + charset);
		OutputStream output = null;	
			try {
				output = connection.getOutputStream();			
				System.out.println(query);
				output.write(query.getBytes(charset));			
			}
			catch(Exception e)
			{
				fw.append("Error in Post Form Data ::::::::::::::"+classNames+":::::::::");
				fw.append(e.getMessage());
				fw.append("**************Error End************************");
			}
			finally {
				if (output != null)
					try {
						output.close();
					} 
					catch (IOException e)
					{
						fw.append("Error in Post Form Data ::::::::::::::"+classNames+":::::::::");
						fw.append(e.getMessage());
						fw.append("**************Error End************************");
					}
			}
			try{
				InputStream response = connection.getInputStream();
				int status = ((HttpURLConnection) connection).getResponseCode();
				System.out.println("Status:" + status);
				for (Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
					System.out.println(header.getKey() + "=" + header.getValue());
					fw.append("Error in Post Form Data ::::::::::::::"+classNames+":::::::::");
					fw.append(header.getKey() + "=" + header.getValue());
					fw.append("**************Error End************************");
				}
			}
			catch(Exception e){
				fw.append("Error in Post Form Data ::::::::::::::"+classNames+":::::::::");
				fw.append(e.getMessage());
				fw.append("**************Error End************************");
			}
	}

	private void getclassfielddetails() throws Exception {		
		Json2Java j2j = new Json2Java();
		Map<String, Object> resp = j2j.getMap(getJsonString());
		classList = (ArrayList<?>) resp.get("classname");
		for (int k = 0; k < count; k++) {
		
			exportClassList(classList);
		}				
	}
	public void post() throws IOException
	{		
		try{		
		if(!postFor.equalsIgnoreCase("Form")){			
			sc = ServiceClient.getInstance("dataservices");
			System.out.println(Utils.getInstance().getCollectionsAsStringXml(collections));			
			status = (HashMap) sc.post(site, collections, hRequestParam,	"application/xml");					
			String s = (String) status.get("responseStringBody");		
			Collections collection = Utils.getInstance().getStringXmlAsCollections(s);
			Iterator it = collection.getCollection().iterator();		
			while (it.hasNext()) {			
				ValidationStatus val = (ValidationStatus) it.next();				
				String status = val.getStatus();
				if (status.equalsIgnoreCase("Error")) {
					fw.append("  |||||||||| " + status + "::::");
					fw.append("The Class Name is : " + val.getContentEntityType()+ ",");
					System.out.println(val.getContentEntityType());
					System.out.println("***************************");
					Iterator ite = val.getErrorFields().getFieldDetails().iterator();
					while (ite.hasNext()) {
						FieldDetails fs = (FieldDetails) ite.next();
						System.out.println("The filed name is:  "
								+ fs.getFieldName() + ",");
						fw.append("  The field name is:  " + fs.getFieldName()
								+ ", ");
						System.out.println("The filed error is:  "
								+ fs.getFieldError());
						fw.append("  The Error is:  " + fs.getFieldError());
					}
				}
			}
		  }
		}
		catch(Exception e)
		{
			fw.append("Error in Post Collections ::::::::::::::"+classNames+":::::::::");
			fw.append(e.getMessage());
			fw.append("**************Error End************************");
		}
		

	}
	public void delete() throws IOException 
	{					
			try{
			sc = ServiceClient.getInstance("dataservices");		
			System.out.println(id+"    "+classNames+"   "+hRequestParam);
			status = (HashMap) sc.delete(site, classNames+"/"+id, hRequestParam);		
			System.out.println(" Status:::::::::"+status);
			}
			catch(Exception e)
			{
				fw.append("Error in Delete collections ::::::::::::::"+classNames+":::::::::");
				fw.append(e.getMessage());
				fw.append("**************Error End************************");
			}
			
		}

	private void exportClassList(List<?> clsList)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException	 {
		if (classList instanceof List) {			
			Iterator<Object> iterator = (Iterator<Object>) classList.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> item = (Map<String, Object>) iterator
						.next();
				String className = item.get("name").toString();
				String ui = null;
				String impleme = null;
				ui = item.get("ui").toString();
				String entitygroup=item.get("entitygroup").toString();				
				List ls = (List) item.get("implements");
				if (ls == null) {
					continue;
				}
				if (domainname == null) {
					if ((ui == "true") && (ls.contains("Storable"))) {
						 query ="";
						 collections=new Collections();
						domainfactory = DomainFactory.getInstance();
						domainfactory.setPackageName("in.nic.cmf.domain.");
						clsobject = domainfactory.getInstanceOf(className);
						className = item.get("name").toString();
						classNames=className;						
						classDetailList = (ArrayList<?>) item.get("fields");
						if (classDetailList == null) {
							continue;
						}
						if(!className.equalsIgnoreCase("Validations")){
						collections.add(exportClassDetails(clsobject,className));
						 post();						
						if(postFor.equalsIgnoreCase("Form")){
						postusingurlconnections();
						 
						}
						// delete();
						}
					}
				} else {
					if (className.equalsIgnoreCase(domainname)) {
						if ((ui == "true") && (ls.contains("Storable"))) {
							collections=new Collections();
							 query ="";							
							domainfactory = DomainFactory.getInstance();
							domainfactory.setPackageName("in.nic.cmf.domain.");
							clsobject = domainfactory.getInstanceOf(className);
							className = item.get("name").toString();
							classNames=className;
							System.out.println(className);
							classDetailList = (ArrayList<?>) item.get("fields");							
							if(!className.equalsIgnoreCase("Validations")){
								collections.add(exportClassDetails(clsobject,className));
								 post();
								
							if(postFor.equalsIgnoreCase("Form")){
								postusingurlconnections();
								 
								}
							 //delete();
							}
						}
					}
					
				}

			}
		}
	}

	private Object exportClassDetails(Object clsobj, String className)throws ClassNotFoundException, InstantiationException,IllegalAccessException, IOException, NoSuchMethodException,
			InvocationTargetException {	
		String charset = "UTF-8";
		entityName = className;
		Iterator<Object> iterator = (Iterator<Object>) classDetailList.iterator();
		HashMap object = null;
		String tmpString = null;
		String fieldType = null;
		String jaxbFieldName = null;
		String jaxbfieldType = "";
		String fieldName = null;
		while (iterator.hasNext()) {
			Map<String, Object> item = (Map<String, Object>) iterator.next();
			Map<String, Object> subMap = null;
			HashMap jaxb = new HashMap();
			jaxb = (HashMap) item.get("jaxb");
			fieldName = (String) item.get("name");
			fieldType = item.get("fieldtype").toString();
			jaxbFieldName = jaxb.get("name").toString();
			jaxbfieldType = item.get("type").toString();
			subMap = (Map<String, Object>) item.get("annotations");
			Method method = domainfactory.getMethodInstanceOf(
					"in.nic.cmf.domain." + className, "set" + jaxbFieldName);
			if (method == null) {
				continue;
			}
			if (subMap == null) {
				continue;
			}
			object = (HashMap) subMap.get("Rule");
			if(object==null){
				continue;
			}
			if (object != null) {
				tmpString = object.toString();
				tmpString = exclude("}", exclude("{", tmpString));
				}
			
			if ((jaxbfieldType.equalsIgnoreCase("List"))&& (!fieldType.equalsIgnoreCase("String"))&&(fieldType.equalsIgnoreCase("List<String>"))){
				List ls = new ArrayList();
				Random rs = new Random();
				for (int i = 0; i <= 0; i++) {
				ls.add(methodinvoke(fieldName, tmpString, object,jaxbfieldType));
					if(methodinvoke(fieldName, tmpString, object,jaxbfieldType)!=null){
						query+= "&"+ jaxbFieldName+"="+URLEncoder.encode(methodinvoke(fieldName, tmpString, object,jaxbfieldType), charset);
						}
				}
				method.invoke(clsobj, ls);
			} else if (jaxbfieldType.equalsIgnoreCase("IsMultiObject")) {
				Object objects = null;
				List ls = new ArrayList();
				if (classList instanceof List) {
					Iterator<Object> iter = (Iterator<Object>) classList.iterator();
					while (iter.hasNext()) {
						Map<String, Object> items = (Map<String, Object>) iter.next();
						String clsname = items.get("name").toString();
						if (clsname.equalsIgnoreCase(jaxbFieldName)) {
							classDetailList = (ArrayList<?>) items.get("fields");
							domainfactory = DomainFactory.getInstance();
							domainfactory.setPackageName("in.nic.cmf.domain.");
							objects = domainfactory.getInstanceOf(jaxbFieldName);						
							for (int j = 0; j <= 0; j++) {							
								ls.add(exportClassDetails(objects, clsname));								
							}
							method.invoke(clsobj, ls);
						}
					}
				}
			} else if ((jaxbfieldType.equalsIgnoreCase("JsonString"))
					|| (jaxbfieldType.equalsIgnoreCase("Object"))) {
				Object objects = null;
				if (classList instanceof List) {
					Iterator<Object> iter = (Iterator<Object>) classList
							.iterator();
					while (iter.hasNext()) {
						Map<String, Object> items = (Map<String, Object>) iter
								.next();
						String clsname = items.get("name").toString();
						if (clsname.equalsIgnoreCase(jaxbFieldName)) {
							classDetailList = (ArrayList<?>) items
									.get("fields");
							domainfactory = DomainFactory.getInstance();
							domainfactory.setPackageName("in.nic.cmf.domain.");
							objects = domainfactory.getInstanceOf(jaxbFieldName);								
						    objects = exportClassDetails(objects, clsname);													
							method.invoke(clsobj, objects);
						}
					}
				}
			} else if (jaxbfieldType.equalsIgnoreCase("Boolean")) {
				method.invoke(clsobj,	new Boolean(methodinvoke(fieldName, tmpString, object,jaxbfieldType)));
				if(methodinvoke(fieldName, tmpString, object,jaxbfieldType)!=null){
					query+= "&"+ jaxbFieldName+"="+URLEncoder.encode(methodinvoke(fieldName, tmpString, object,jaxbfieldType), charset);
					}	
			} else if (jaxbfieldType.equalsIgnoreCase("int")) {
				method.invoke(clsobj,	new Integer(methodinvoke(fieldName, tmpString, object,jaxbfieldType)));
				if(methodinvoke(fieldName, tmpString, object,jaxbfieldType)!=null){
					query+= "&"+ jaxbFieldName+"="+URLEncoder.encode(methodinvoke(fieldName, tmpString, object,jaxbfieldType), charset);
					}
			} else if (fieldType.equalsIgnoreCase("String")) {
				if(fieldName.equalsIgnoreCase("id")){
					
					 id=methodinvoke(fieldName, tmpString, object,jaxbfieldType);
					 query+= "&"+ jaxbFieldName+"="+URLEncoder.encode( id, charset);
					 System.out.println("*****"+id);
						  method.invoke(clsobj,id);	
				}	
				else if(fieldName.equalsIgnoreCase("description"))
				{
					method.invoke(clsobj,"");					
				}
				else
				{
					query+= "&"+ jaxbFieldName+"="+URLEncoder.encode(methodinvoke(fieldName, tmpString, object,fieldType), charset);
				method.invoke(clsobj,methodinvoke(fieldName, tmpString, object,jaxbfieldType));
					
				}			
			} 
		}		
		return clsobj;
	}
	private String methodinvoke(String fieldName, String tmpString,	HashMap object, String fieldType) throws IllegalArgumentException,IllegalAccessException, InvocationTargetException,
	ClassNotFoundException, SecurityException, NoSuchMethodException,InstantiationException, IOException {
			Class cls = Classreturn(checkFor, "String");
			Object obj = ObjectReturn(checkFor, "String");
			int length = 0;
			if (tmpString != null) {								
				if (fieldName.equals("id")) {
				Method m = cls.getMethod("get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1), int.class);					
				return (String) m.invoke(obj, length);
				} else if (fieldName.equals("site")) {
					return site;
				} else if (fieldName.equals("entityType")) {					
				   return classNames;
				} else if ((fieldName.equalsIgnoreCase("user"))||(fieldName.equalsIgnoreCase("currentUser")||(fieldName.equalsIgnoreCase("lastModifiedBy")))){
					return authUsername;
				} 			
				else if(fieldName.equalsIgnoreCase("itemEntityType"))
						{
					System.out.println(entityName);
					if(entityName.equalsIgnoreCase("DependentItem")){
					return "Media";
				     }
					else if(entityName.equalsIgnoreCase("RelatedItem")){
						return "Form";
					}
						
				}
				if ((tmpString.contains("sizeMax"))	&& (tmpString.contains("sizeMin"))) {
					if (Integer.parseInt(object.get("sizeMax").toString()) == Integer
							.parseInt(object.get("sizeMin").toString())) {
						length = Integer.parseInt(object.get("sizeMax").toString());
					} else {
						length = Integer.parseInt(object.get("sizeMin").toString());
					}
				} else if ((tmpString.contains("sizeMax"))
						&& (!tmpString.contains("sizeMin"))) {
					length = Integer.parseInt(object.get("sizeMax").toString());					
				} else if ((!tmpString.contains("sizeMax"))
						&& (tmpString.contains("sizeMin"))) {
					length = Integer.parseInt(object.get("sizeMin").toString());
				} else {
					length = 10;
				}
				String[] rule = tmpString.split(",");				
				for(int l=0;l<rule.length;l++){					
				if (rule[l].trim().contains("type")) {	
					if((postFor.equals("Form"))&&(rule[l].trim().contains("type=date"))){
						Method m = cls.getMethod("get"+ object.get("type").toString().substring(0, 1).toUpperCase()	+ object.get("type").toString().substring(1)+"Form",int.class);
						return (String) m.invoke(obj, length);
						}
					else{
						Method m = cls.getMethod("get"+ object.get("type").toString().substring(0, 1).toUpperCase()	+ object.get("type").toString().substring(1),int.class);
						return (String) m.invoke(obj, length);
					}
					
		 	}else if (rule[l].trim().contains("allowedFormat")) {				
					Method m = cls.getMethod("getAllowedFormat", int.class,String.class);		
				  String allowedformat=	object.get("allowedFormat").toString();
				String extension[]=  allowedformat.split(":");
					return (String) m.invoke(obj, length,extension[0]);
				} else if (fieldType.equalsIgnoreCase("List")) {
					Method m = cls.getMethod("getString", int.class);
					return (String) m.invoke(obj, length);
				}  else if((!rule[l].equalsIgnoreCase("unique=true"))&&(!rule[l].contains("size"))){
					Method m = cls.getMethod("get" + fieldType, int.class);
					return (String) m.invoke(obj, length);
				}
			 }
			}
			return null;
	}

	public Class Classreturn(String classname, String instance)throws ClassNotFoundException, InstantiationException,IllegalAccessException, IOException {
		Class validgenerator = null;
		if (classname.equals("valid")) {
			validgenerator = Class
					.forName("in.nic.cmf.dynamicgenerator.validGenerator");
		} else if (classname.equals("invalidtype")) {
			validgenerator = Class
					.forName("in.nic.cmf.dynamicgenerator.badtypeGenerator");
		} else if (classname.equals("invalidvalue")) {
			validgenerator = Class
					.forName("in.nic.cmf.dynamicgenerator.badvalueGenerator");
		}
		return validgenerator;
	}

	public Object ObjectReturn(String classname, String instance)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IOException {
		Generator instancename = null;
		domainfact = DomainFactory.getInstance();
		domainfact.setPackageName("in.nic.cmf.dynamicgenerator.");
		if (classname.equals("valid")) {
			instancename = new validGenerator();
		} else if (classname.equals("invalidtype")) {
			instancename = new badtypeGenerator();
		} else if (classname.equals("invalidvalue")) {
			instancename = new badvalueGenerator();
		}
		return instancename;
	}
	public GenerateXml(String path) throws IOException {
		File file = new File("/tmp/logs/DynamicDataPostingTracer.txt");
	    fw = new FileWriter(file);
		URlmapgeneration();
		String jsonString = getContent(path);
		setJsonString(jsonString);
		}
	private void URlmapgeneration() {
		authUsername = "ramyaadmin";
		String authPassword = "5f4dcc3b5aa765d61d8327deb882cf99";		
		String domain = "ramya.in";
		DynamicAuthentication dyauth = new DynamicAuthentication(authUsername,authPassword,domain);
		if(dyauth.autoSignin()){
			InetAddress inetaddress = null;
			try {
				inetaddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			String requester = inetaddress.getHostAddress();
			System.out.println("requester... "+requester);
			
			System.out.println(authUsername+" authentication is success state....");
			Convertor convertor = FormatXml.getInstance();
			HashMap<String,Object> authmap = convertor.in(dyauth.getAuthentication());
			authmap.put("requester", requester);	
			hRequestParam = UserContext.getInstance().getUserContext((HashMap<String, Object>) authmap.get("Authentication"));
			System.out.println("User Context => "+hRequestParam);
		    url1= "authusername="+hRequestParam.get("authusername")+"&aclrole="+hRequestParam.get("aclrole")+"&api_key="+hRequestParam.get("api_key");
		} else {
			System.out.println("Dynamic authenticaion failure... for username : "+authUsername);
		}			
	}
	
	private String getContent(final String fileName)throws FileNotFoundException {
		try {
			FileInputStream is = new FileInputStream(fileName);
			return IOUtils.toString(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	public String getJsonString() {
		return jsonString;
	}

	private String exclude(String excludeValue, String data) {
		return data.replace(excludeValue, "");
	}
}
