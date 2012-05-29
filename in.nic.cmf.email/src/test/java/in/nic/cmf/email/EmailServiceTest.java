package in.nic.cmf.email;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.util.DynamicAuthentication;
import in.nic.cmf.util.UserContext;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.junit.Test;

import junit.framework.Assert;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class EmailServiceTest {
    
	EmailServiceImpl email = new EmailServiceImpl();
	
	
	  @Test
	  public void testLog1() {
		    DynamicAuthentication dyauth = new DynamicAuthentication("sitebuilder","5f4dcc3b5aa765d61d8327deb882cf99","seahorse.in");	  
	       	Map<String, String> hRequestParams = new HashMap<String, String>();
	        if (dyauth.autoSignin()) {
	           System.out.println("auth sucess");
	            String authResponse = dyauth.getAuthentication();
	            HashMap<String, Object> authResponseMap = (HashMap<String, Object>) FormatXml
	                    .getInstance().in(authResponse);
	            hRequestParams = UserContext.getInstance().getUserContext(
	                    (HashMap<String, Object>) authResponseMap
	                            .get("Authentication"));
	            System.out.println(hRequestParams);
	        } else {
	            System.err.println("Auto Login Fail.");
	        }
		  HashMap queryParams=new HashMap();
		  HashMap data=new HashMap();
		  queryParams.put("data", data);
		  queryParams.put("usercontext", hRequestParams);
		  String reqbodycont="<Collections><CmsUserProfile><Id>llrq3hagjcaii</Id><EntityType>CmsUserProfile</EntityType><AliasId/><UserName>sitebuilder</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>SiteBuilder</UserRole><Title>sitebuilder</Title><FirstName>sitebuilder</FirstName><MiddleName/><LastName>sitebuilder</LastName><Email>nivetha.sridev@gmail.com</Email><CityId/><City>Delhi</City><StateId/><State>Delhi</State><Designation/><AssociatedMin/><AssociatedDeptOrg/><ContactNumber/><IsActive>true</IsActive><Status>Published</Status><CreatedDate>2011-11-17T16:29:07Z</CreatedDate><LastModifiedDate>2011-11-17T16:29:07Z</LastModifiedDate><CreatedBy>domainadmin</CreatedBy><LastModifiedBy>domainadmin</LastModifiedBy><Site>seahorse.in</Site><Version>1.0</Version></CmsUserProfile></Collections>";
		  data.put("requestbodycontent",reqbodycont);
		  email.add("seahorse.in", "CmsUserProfile", queryParams, true);
		  
	  }
  
    
}