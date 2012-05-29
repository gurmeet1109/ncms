package in.nic.cmf.domaincreation;

import in.nic.cmf.ServiceClient;

import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.domain.Collections;
import in.nic.cmf.domain.DomainEntity;
import in.nic.cmf.domain.DomainFactory;
import in.nic.cmf.exceptions.GenericException;

import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.uniqueid.Uniqueid;
import in.nic.cmf.util.DynamicAuthentication;
import in.nic.cmf.util.UserContext;
import in.nic.cmf.util.Utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.apache.xerces.parsers.DOMParser;

/**
 * Hello world!
 * 
 */

public class domaincreation {	
	String DomainName = "";
	String Version = "1.0";
	String Username = "";
	String authusernmae="";
	String authPassword="";
	String password = "";
	String passwordMD5 = "";
	List classList = null;
	String Role = "";
	String IP="";
	Collections collection = null;
	HashMap CollectionHashMap=null;
	Collections domainEntitycollection = new Collections();	
	List domainEntityList=null;
	HashMap<String, String> hRequestParam = new HashMap<String, String>();
	PropertiesUtil propertiesutil=null;
	HashMap collectionRoot=null;
	public static void main(String[] args) throws Exception {
		domaincreation domaincreation = new domaincreation();
		domaincreation.getClassDetails();
	}
	
	private String getContent(final String fileName)
    throws FileNotFoundException {  
		InputStream is = ClassLoader.getSystemClassLoader().getSystemResourceAsStream(fileName);    
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)));
        StringBuffer sbuffer = new StringBuffer();
     	while (scanner.hasNextLine()) {
     			sbuffer.append(scanner.nextLine());
     		}
     		scanner.close();
     		return sbuffer.toString();
}

	private void getClassDetails() throws Exception {				
		Map<String, Object> resp =FormatJson.getInstance().in(getContent("domain.json"));
		classList = (ArrayList<?>) resp.get("classname");			
		getuserdetails();			
	}
	private void getuserdetails() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, JAXBException, NoSuchAlgorithmException {
		//String role[]={"PortalAdmin","DomainAdmin","SystemAdmin","FeedAdmin","AnalyticsAdmin","SiteBuilder"};
		String role[]={"PortalAdmin"};
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.print("Please Enter Domain   :");
		DomainName = br.readLine();
		while(!isNullCheck(DomainName)){
			System.out.print("You are not entered domain Name,Please Enter Again   :");
			DomainName = br.readLine();
		}
		for(int k=0;k<role.length;k++){					
			    Role=role[k];			
		    	if(!Role.equalsIgnoreCase("portaladmin")){
		    		Username=role[k].toLowerCase();				
					password="password";
					passwordMD5 = "5f4dcc3b5aa765d61d8327deb882cf99";		            
		    		CollectionHashMap=new HashMap();		    		
					getUserProfile();
					getRole();			
					collectionRoot.put("Collections", CollectionHashMap);
					String collectionsgroup=FormatXml.getInstance().out(collectionRoot).toString();
					
					System.out.println("Collection output::::::::::::::::::::::::::::::::"+collectionsgroup);
					postcollections("UserProfile and Role","searchapi",collectionsgroup);
				    postcollections("UserProfile and Role","dms",collectionsgroup);
				   
				  
		    	}		    	
		    	else if(Role.equalsIgnoreCase("portaladmin")){
		    		System.out.print("Please Enter UserName Of PortalAdmin:  ");
					Username=br.readLine();			
					while(!isNullCheck(Username)){			
						System.out.print("You are not Entered User Name,Please Enter UserName Of PortalAdmin Again:  ");
						Username=br.readLine();				
					}	
					System.out.print("Please Enter Password Of PortalAdmin:  ");
					password=br.readLine();
					passwordMD5 = convertMD5format(password);			
					while(!isNullCheck(password)){
						System.out.print("You are Not Entered Password,Please Enter Password Of PortalAdmin Again:  ");
						password=br.readLine();
						passwordMD5 = convertMD5format(password);					
					}
					authusernmae=Username;
					authPassword=passwordMD5;	
					CollectionHashMap=new HashMap();		    		
		    		collectionRoot=new HashMap();
					getUserProfile();
					getRole();	
					getcountry();		
					/*//collectionRoot.put("Collections", CollectionHashMap);
				    postcollections("UserProfile and Role","searchapi",collection);
				    postcollections("UserProfile and Role","dms",collection);	*/			    
			    	getDomainEntity();	
			    	collectionRoot.put("Collections", CollectionHashMap);
					String collectionsgroup=FormatXml.getInstance().out(collectionRoot).toString();
					System.out.println("Collection output::::::::::::::::::::::::::::::::"+collectionsgroup);
			    	postcollections("UserProfile and Role","searchapi",collectionsgroup);
				    postcollections("UserProfile and Role","dms",collectionsgroup);		
					
				   
		    	}    			    	
		 	}		
		CollectionHashMap=new HashMap();		    		
		collectionRoot=new HashMap();
		 postBinarFile();
		 Properties properties = new Properties();           
         FileInputStream in = new FileInputStream ("/opt/cmf/properties/config.properties");           
         properties.load(in);
         String inFoldername=  properties.getProperty("buildfor");
         String[] directoryName={"generate","dataservices"};
         for(int i=0;i<directoryName.length;i++){
         FileInputStream is = new FileInputStream("/opt/cmf/properties/"+inFoldername+"/"+directoryName[i]+"/config.properties");
         properties.load(is);           
         String value=   properties.getProperty("domain");           
         if(!value.contains(DomainName))
         {
         	value+=","+DomainName;
         	properties.setProperty("domain", value);
         }
        properties.save(new FileOutputStream("/opt/cmf/properties/"+inFoldername+"/"+directoryName[i]+"/config.properties"), "New Domain Name updated");
        
         }
	    System.out.println("Process Completed....");   		   
	 }		
	private void postBinarFile() {
		 String returnValue = null;
	        String mimeType = null;	       
	        InputStream fileInputStream = null;	        	       
	        try {        
	        	String entityTypes[]={"Css","Js","Template","Media"};
	        	String fileNames[]={"home.css","jquery.js","index.html","lord-skanda.jpg"};
	        	String fileExtensions[]={".css",".js",".html",".jpg"};
	        	for(int i=0;i<fileNames.length;i++){
	        		String path = ClassLoader.getSystemClassLoader().getResource(fileNames[i]).getFile();	        	
	        	HashMap map=new HashMap();	        	
	        	if(entityTypes[i].equalsIgnoreCase("Media")){
	        		map.put("ShortDescription", "Sample File");
		        	map.put("ContentFormat", "application/xml");
		        	map.put("ContentLanguageId", "en");
		        	map.put("AssociatedIAPath","Topic");
	        	}        	
	        	map.put(entityTypes[i]+"Name", fileNames[i]);
	        	map.put("Status", "draft");
	        	map.put("FilePath", "/Desktop");	        	
	        	CollectionHashMap.put(entityTypes[i], getObjectForCommonFields(entityTypes[i], map));	
	            HashMap idMap=	(HashMap) CollectionHashMap.get(entityTypes[i]);
	        	File file=new File(path);        	
	        	fileInputStream = new FileInputStream(file);	        	
	            byte[] bytes = new byte[(int) file.length()];	          
	            fileInputStream.read(bytes);	           
	            String entityId = idMap.get("Id").toString();	 	            
	            mimeType = Utils.getInstance().getMimeType(new ByteArrayInputStream(bytes), fileNames[i]); 	           
	            String  fileName = entityId + fileExtensions[i];
	            System.out.println("FILE name::::::::::::::::::::"+fileName+" :::::::::::::"+DomainName+"          "+hRequestParam);
	            ServiceClient  client = ServiceClient.getInstance("dms");
	            Map resultMap = client.post(DomainName,fileName,new ByteArrayInputStream(bytes),(int)bytes.length,mimeType, false,hRequestParam, mimeType);	        	
	            collectionRoot.put("Collections", CollectionHashMap);
				String collectionsgroup=FormatXml.getInstance().out(collectionRoot).toString();
	            postcollections("UserProfile and Role","searchapi",collectionsgroup);
			    postcollections("UserProfile and Role","dms",collectionsgroup);
	            System.out.println(resultMap);
	        	}
	        }             
	     catch (Exception e) {
	       System.out.println("Error here:::::::::::::::::::::::::::::::::"+e);
	       
	    }
		
	}
	private String convertMD5format(String password) throws NoSuchAlgorithmException {		
		 MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());	 
	        byte byteData[] = md.digest();	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }	      
		return sb.toString();		
	}
	private void postcollections(String pojoName,String serviceName,String collection) throws JAXBException,IOException {		
	    System.out.println(pojoName +"  of "+Role+" Posting to "+serviceName+".");					
		DynamicAuthentication dyauth = new DynamicAuthentication(authusernmae,authPassword, DomainName);
		if (dyauth.autoSignin()) {
			Convertor convertor = FormatXml.getInstance();
			HashMap<String, Object> authmap = convertor.in(dyauth.getAuthentication());
			hRequestParam = UserContext.getInstance().getUserContext((HashMap<String, Object>) authmap
					.get("Authentication"));
			ServiceClient sc = ServiceClient.getInstance(serviceName);				
			HashMap<String, Object> stat = (HashMap<String, Object>) sc.post(DomainName,collection,
					hRequestParam, "application/xml");			
			System.out.println(pojoName+" posted.");
		} else {
			System.out.println("Authentication failed");
		}
	}
	
private void getcountry() throws ClassNotFoundException,
	InstantiationException, IllegalAccessException, IOException,
	NoSuchMethodException, InvocationTargetException {	  
    InputStream is = ClassLoader.getSystemClassLoader().getSystemResourceAsStream("country.txt");     
    DataInputStream in = new DataInputStream(is);
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String strLine;
    List Country=new ArrayList();
    while ((strLine = br.readLine()) != null)   {	 
      String[] str =strLine.split(","); 
      HashMap country=new HashMap();    
      country.put("CountryName", str[1]);	
      country.put("CountryCode", str[0]);	
      country=getObjectForCommonFields("Country",	country);
      Country.add(country);
      CollectionHashMap.put("Country",Country );     
    }  
   in.close();
   }
	private void getUserProfile() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException,
			NoSuchMethodException, InvocationTargetException, JAXBException {
		HashMap UserProfile=new HashMap();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);		
		List UserProfileList = new ArrayList();	
		Random random=new Random();
		UserProfileList.add("nic"+random.nextInt(100)+"test"+random.nextInt(100)+"@nic.com");	
		UserProfileList.add("Delhi");		
		UserProfileList.add("Delhi");		
		String Entittype[] = {"Email", "City", "State"};
		propertiesutil=new PropertiesUtil("serviceclient");
		String ldap_user_url=	propertiesutil.getProperty("ldap");	
		ldap_user_url +=  "/"+DomainName + "/createuser/";
		if (createLdapUser(ldap_user_url, Username,passwordMD5, Role)) {			
			System.out.println("Ldap User sucessfully created....");
			System.out.println("UserName is:   "+Username);
			System.out.println("Password is:   "+password);
		}else {
			System.out.println("Ldap User Already exists");
		}			
		UserProfile.put("UserName", Username);	
		UserProfile.put("Password", passwordMD5);		
		UserProfile.put("UserRole", Role);		
		UserProfile.put("Title", Username);	
		UserProfile.put("IsActive", true);	
		UserProfile.put("FirstName", Username);		
		UserProfile.put("LastName", Username);	
		UserProfile.put("Status", "Published");		
		for (int k = 0; k < UserProfileList.size(); k++) {
			UserProfile.put(Entittype[k], UserProfileList.get(k));			
		}				
		CollectionHashMap.put("CmsUserProfile", getObjectForCommonFields("CmsUserProfile", UserProfile));
	}

	private boolean isNullCheck(String Value) {
		if(Value==null){
			return false;
		}
		else if(Value.trim()==null){
			return false;
		}
		else if(Value.trim().isEmpty()){
			return false;
		}
		else
		{
		return true;
		}
	}

	private void getRole() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException,
			NoSuchMethodException, InvocationTargetException, JAXBException {
		HashMap role=new HashMap();
		HashMap roleRoot=new HashMap();		
		roleRoot.put("Status", "Published");		
		roleRoot.put("RoleName", Role);	
		roleRoot.put("IsTopLevel", "true");	
		roleRoot.put("IsSuperAdmin", "true");		
		List domainEntitylist = new ArrayList();
		if(Role.equalsIgnoreCase("PortalAdmin")){			
	    getDomainEntity();	   
	    domainEntitylist=domainEntityList;
		}
	    else if (Role.equalsIgnoreCase("DomainPortalAdmin")) {
			domainEntitylist.add("ClassInfo");
			domainEntitylist.add("ClassImport");
			domainEntitylist.add("ClassAttributes");
			domainEntitylist.add("ClassPackage");
			domainEntitylist.add("InterfaceInfo");
			domainEntitylist.add("DomainEntity");
		}
	    else if(Role.equalsIgnoreCase("FeedAdmin")){
	    	domainEntitylist.add("News");
	    }
		else if(Role.equalsIgnoreCase("domainadmin"))
		{
		domainEntitylist.add("DomainEntity");
		domainEntitylist.add("Role");
		domainEntitylist.add("UserProfile");
		}		
		else
		{
			domainEntitylist.add("ArticleType");
		}		
		role.put("DomainEntity", domainEntitylist);	
		roleRoot.put("DomainEntities", role);	
		HashMap uiTabs=new HashMap();		
		List UiTablist = new ArrayList();
		if (Role.equalsIgnoreCase("domainadmin")) {
			UiTablist.add("Domain");
			UiTablist.add("Users And Roles");
			uiTabs.put("UiTab", UiTablist);			
		} else if (Role.equalsIgnoreCase("feedadmin")) {
			UiTablist.add("CMS");
			uiTabs.put("UiTab", UiTablist);
		}
		 else if (Role.equalsIgnoreCase("DomainPortalAdmin")) {
				UiTablist.add("Admin");
				uiTabs.put("UiTab", UiTablist);			
		}	
		 else if(Role.equalsIgnoreCase("PortalAdmin")){
			 UiTablist.add("Admin");
			 UiTablist.add("CMS");
			 UiTablist.add("PMS");
			 UiTablist.add("Domain");
			 UiTablist.add("Feed Automation");
			 UiTablist.add("Users And Roles");
			 UiTablist.add("Workflow");
			 uiTabs.put("UiTab", UiTablist);				
		 }
		 else
		 {
			 UiTablist.add("Domain");
			 uiTabs.put("UiTab", UiTablist);
		 }		
		roleRoot.put("UiTabs", uiTabs);			
		CollectionHashMap.put("Role", getObjectForCommonFields("Role", roleRoot));	
	}
	private void getDomainEntity() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, IOException,
			NoSuchMethodException, InvocationTargetException, JAXBException {
		HashMap domainEntity=new HashMap();
		domainEntityList=new ArrayList();
		OutputStream os = null;
		JAXBContext jc = null;
		List DomainEntittyList=new ArrayList();		
		if (classList instanceof List) {
			Iterator<Object> iterator = (Iterator<Object>) classList.iterator();
			while (iterator.hasNext()) {
				Map<String, Object> item = (Map<String, Object>) iterator
						.next();
				String className = item.get("name").toString();				
				String ui = item.get("ui").toString();
				String entitygroup = item.get("entitygroup").toString();
				List ls = (List) item.get("implements");
				if (ls == null) {
					continue;
				}
				if ((ui == "true") && (ls.contains("Storable"))) {
					HashMap domainentity=new HashMap();					
					domainentity.put("EntityName", className);					
					domainEntityList.add(className);								
					List lis = new ArrayList();					
					lis.add(item.get("entitygroup").toString());
					HashMap EntityGroup=new HashMap();
					EntityGroup.put("EntityGroup", lis);						
					domainentity.put("EntityGroups", EntityGroup);
					domainentity=getObjectForCommonFields("DomainEntity",domainentity);
					DomainEntittyList.add(domainentity);
					CollectionHashMap.put("DomainEntity", DomainEntittyList);
				}
			}
		}
	}
	private HashMap getObjectForCommonFields(String Clsname, HashMap clsobject) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		clsobject.put("Id", Uniqueid.getId());		
		clsobject.put("Site", DomainName);		
		clsobject.put("Version", Version);
		if((Clsname.equalsIgnoreCase("Stage"))||(Clsname.equalsIgnoreCase("Status"))||(Clsname.equalsIgnoreCase("event"))||(Clsname.equalsIgnoreCase("workflow"))||(Clsname.equalsIgnoreCase("workflowmodel"))||(Clsname.equalsIgnoreCase("workflowmodelmap"))){
			clsobject.put("CreatedBy", "portaladmin");
			clsobject.put("LastModifiedBy", "portaladmin");
		}
		else
		{		
		clsobject.put("CreatedBy", "domainadmin");		
		clsobject.put("LastModifiedBy", "domainadmin");		
		}			
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		clsobject.put("CreatedDate", df.format(new Date()).toString());		
		clsobject.put("LastModifiedDate", df.format(new Date()).toString());		
		clsobject.put("EntityType", Clsname);	
		return clsobject;
	}
	private static InputStream getResponse(String surl, OutputStream os,
			boolean ispost, boolean isxml) throws IOException {
		HttpURLConnection urlc = null;
		URL url;
		try {
			url = new URL(surl);
			urlc = (HttpURLConnection) url.openConnection();
			urlc.setDoOutput(ispost);
			urlc.setAllowUserInteraction(false);
			if (ispost) {
				if (isxml) {
					urlc.setRequestProperty("Content-Type", "application/xml");
				}
				byte[] b = ((ByteArrayOutputStream) os).toByteArray();
				urlc.getOutputStream().write(b);
				urlc.getOutputStream().close();
				return urlc.getInputStream();
			}
		} catch (MalformedURLException e) {
			System.out.println("URl not valid");
		} catch (IOException e) {
			System.out.println("Error here    " + e.getMessage());
		}
		return urlc.getInputStream();
	}
	public static boolean createLdapUser(String ldap_user_url, String uname,String pword, String role) {
	    System.out.println("Ldap User Creating Now........");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		boolean created = false;
		try {
			os.write(("&id="+Uniqueid.getId()+"&username=" + uname + "&password=" + pword + "&role=" + role+"&isactive=true")
					.getBytes());
			InputStream is = getResponse(ldap_user_url, os, true, false);
			String text = new Scanner(is).useDelimiter("\\A").next();
			created = (text.contains("true")) ? true : false;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return created;
	}
}
