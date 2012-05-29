package in.nic.cmf.sitegeneration;

import static org.junit.Assert.fail;
import in.nic.cmf.exceptions.GenericException;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;

public class sitegenControllerTest {

    @Test
    public void testAdd() throws GenericException, JSONException {
	SiteGenController sitegencon = SiteGenController.getInstance();
	Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
	Map<String, Object> input = new HashMap<String, Object>();
	HashMap<String, Object> content = new HashMap<String, Object>();
	String contentval = "<Collections><Domain><IsActive>true</IsActive><SuperAdminUserName>bookadmin</SuperAdminUserName><DomainName>book.in</DomainName><EntityType>Domain</EntityType><SuperAdminPassword>password@1</SuperAdminPassword><SuperAdminRole>PortalAdmin</SuperAdminRole><UiTabs><UiTab>Admin</UiTab><UiTab>CMS</UiTab><UiTab>PMS</UiTab><UiTab>Domain</UiTab><UiTab>Feed</UiTab><UiTab>Automation</UiTab><UiTab>Users And Roles</UiTab><UiTab>Workflow</UiTab></UiTabs><DomainEntities><DomainEntity>AccessControlList-admin</DomainEntity><DomainEntity>Address-cms</DomainEntity><DomainEntity>AppAdmin-admin</DomainEntity><DomainEntity>Article-cms</DomainEntity><DomainEntity>ArticleType-cms</DomainEntity><DomainEntity>AudienceCategory-cms</DomainEntity><DomainEntity>Awards-cms</DomainEntity><DomainEntity>Banner-cms</DomainEntity><DomainEntity>Book-cms</DomainEntity><DomainEntity>Category-cms</DomainEntity><DomainEntity>City-cms</DomainEntity><DomainEntity>ClassAttributes-admin</DomainEntity><DomainEntity>ClassImport-admin</DomainEntity><DomainEntity>ClassInfo-admin</DomainEntity><DomainEntity>ClassPackage-admin</DomainEntity><DomainEntity>ContactNumber-cms</DomainEntity><DomainEntity>ContactType-cms</DomainEntity><DomainEntity>ContentPartner-feed</DomainEntity><DomainEntity>Country-cms</DomainEntity><DomainEntity>Css-pms</DomainEntity><DomainEntity>Designation-cms</DomainEntity><DomainEntity>Domain-admin</DomainEntity><DomainEntity>DomainEntity-domain</DomainEntity><DomainEntity>UserProfile-user</DomainEntity><DomainEntity>Action-workflow</DomainEntity><DomainEntity>Feedback-admin</DomainEntity><DomainEntity>Form-cms</DomainEntity><DomainEntity>Format-cms</DomainEntity><DomainEntity>Gallery-cms</DomainEntity><DomainEntity>GoiDirCat-cms</DomainEntity><DomainEntity>GoiDirItem-cms</DomainEntity><DomainEntity>InformationArchitecture-cms</DomainEntity><DomainEntity>InterfaceInfo-admin</DomainEntity><DomainEntity>Js-pms</DomainEntity><DomainEntity>JurisdictionType-cms</DomainEntity><DomainEntity>Language-cms</DomainEntity><DomainEntity>Media-cms</DomainEntity><DomainEntity>MediaGroup-cms</DomainEntity><DomainEntity>News-cms</DomainEntity><DomainEntity>OfficeLocation-cms</DomainEntity><DomainEntity>Page-pms</DomainEntity><DomainEntity>PageAssociator-pms</DomainEntity><DomainEntity>Parliament-cms</DomainEntity><DomainEntity>PmsMedia-pms</DomainEntity><DomainEntity>PortFolio-cms</DomainEntity><DomainEntity>Prefix-cms</DomainEntity><DomainEntity>Role-user</DomainEntity><DomainEntity>Scheme-cms</DomainEntity><DomainEntity>Sector-cms</DomainEntity><DomainEntity>Service-cms</DomainEntity><DomainEntity>ServiceType-cms</DomainEntity><DomainEntity>Source-cms</DomainEntity><DomainEntity>SourceType-cms</DomainEntity><DomainEntity>Stage-workflow</DomainEntity><DomainEntity>Status-workflow</DomainEntity><DomainEntity>Template-pms</DomainEntity><DomainEntity>CmsUserProfile-user</DomainEntity><DomainEntity>Validations-admin</DomainEntity><DomainEntity>Viewer-cms</DomainEntity><DomainEntity>WhosWhoCat-cms</DomainEntity><DomainEntity>Whoswhoprofile-cms</DomainEntity><DomainEntity>Widget-pms</DomainEntity><DomainEntity>Workflow-workflow</DomainEntity><DomainEntity>NpiMetadata-cms</DomainEntity><DomainEntity>Currency-cms</DomainEntity><DomainEntity>Crawleddata-cms</DomainEntity></DomainEntities><Roles><RoleName>PortalAdmin</RoleName><RoleName>SystemAdmin</RoleName><RoleName>DomainCreator</RoleName></Roles></Domain></Collections>";
	Map<String, Object> usercontext = new HashMap();
	usercontext.put("aclrole", "DomainCreator");
	input.put("content", contentval);
	input.put("userContext", usercontext);
	Map<String, Object> ss = new HashMap<String, Object>();
	ss.put("format", "xml");
	// input.put("queryParams", ss);
	parameters.put("input", input);

	sitegencon.add("nemo.in", "Domain", parameters);
    }

    @Test
    public void testDeleteByID() {
	fail("Not yet implemented");
    }

    @Test
    public void testDeleteByQuery() {
	fail("Not yet implemented");
    }

    @Test
    public void testFind() {
	fail("Not yet implemented");
    }

    @Test
    public void testFindById() {
	fail("Not yet implemented");
    }

    @Test
    public void testRead() {
	fail("Not yet implemented");
    }

}
