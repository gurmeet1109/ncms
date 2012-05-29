package in.nic.cmf.sitebuilder;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.junit.Test;

public class SiteBuilderTest {
	
	private String domain = "sify.co.in";
	private String role = "Sitebuilder";
	
	@Test
    public void testdynamic() {
		
	    SiteBuilderHelper sb =new SiteBuilderHelper(domain);
    	System.out.println("Status:::"+sb.getAuthDetails(domain));
		System.out.println("Result::::"+sb.getResponse("abcdefghijklm", domain, "css"));
		
	}

	

 /* @Test
    public void Posttest() {
	  
	  
		Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
		Map<String, Object> input = new HashMap<String, Object>();
		HashMap<String, String> userContext = new HashMap<String, String>();
	    userContext = authParam;
		userContext.put("requester", "210.210.125.21");
		userContext.put("aclrole", "SiteBuilder");
		userContext.put("api_key", "24236546");
		userContext.put("authusername", "sitebuilder");
		input.put("userContext", userContext);
		parameters.put("input", input);
	    ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain, "appflow");
		parameters.put("input", input);
		Map<String, Map<String, Object>> responseMap = serviceclient.find(domain,"Page", parameters);
		System.out.println("responseMap:::"+responseMap);            
        SiteBuilderController  sb = new SiteBuilderController();
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        HashMap<String, Object> userContext = new HashMap<String, Object>();
        Map<String, Object> input = new HashMap<String, Object>();
        queryParams.put("site", "sify.co.in");
        queryParams.put("entitytype", "article");
        userContext.put("requester", "124.7.228.74");
        userContext.put("aclrole", "Portaladmin");
        userContext.put("api_key", "24236546");
        userContext.put("authusername", "anonymous");
        String col = "<Collections><Css><Id>meulORgaecggj</Id><EntityType>Css</EntityType><CssName>widgets.css</CssName><SourceName>widgets.css</SourceName><FolderPath>css</FolderPath><FilePath>/eclipse.in/media/meulORgaecggj.css</FilePath><Md5>af5d21e63ae115f1428b23453c507df8</Md5><WorkflowInfo><WorkflowId>md0qyEhcibaea</WorkflowId><WorkflowName>DFP_Workflow</WorkflowName><CurrentUser>eclipseadmin</CurrentUser><CurrentAction>Publish</CurrentAction><Stage>Published</Stage><AllowedActions><AllowedAction><AllowedActionName/><AssignedToUsers><AssignedToUser/></AssignedToUsers></AllowedAction></AllowedActions></WorkflowInfo><Status>Published</Status><CreatedDate>2012-04-20T11:50:25Z</CreatedDate><LastModifiedDate>2012-04-20T12:36:39Z</LastModifiedDate><CreatedBy>eclipseadmin</CreatedBy><LastModifiedBy>eclipseadmin</LastModifiedBy><Site>eclipse.in</Site><Version>1.0</Version><Weightage/></Css></Collections>";
        input.put("userContext", userContext);
        input.put("queryParams", queryParams);
        input.put("content", FormatXml.getInstanceof(domain).in(col));
        parameters.put("input", input);
        parameters.get("input");
        System.out.println("Entry of add");
        System.out.println(sb.add("sify.co.in", "css", parameters));
        System.out.println("Exit of add");
        
    }*/
}
