package in.nic.cmf.ldapservices;

import static org.junit.Assert.assertEquals;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.ldap.LdapService;
import in.nic.cmf.logger.LogTracer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

public class CreateUserLdapServiceTest {

    private LogTracer   log;
    private LdapService ldapService;
    private String      domainName;

    public CreateUserLdapServiceTest() {
        domainName = "sample.in";
        log = new LogTracer("ldap-test");
        ldapService = new LdapService();
        ldapService.setLogTracer(log);
    }

    protected void setUp() {

    }

    protected void tearDown() {

    }

    @Test
    public void create_New_User_without_CmfUserProfile() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());
        id += "X";

        String inputContent = "<Collections><Article><Id>"
                + id
                + "</Id><EntityType>Article</EntityType><Title>Un-known entity xml for user creation</Title><Description>Simple test function which is test the unknow entity for ldap user creation</Description></Article></Collections>";
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);
        assertEquals(inputContent, parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

    @Test
    public void create_New_User() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String userName = "Xuser" + id;
        id += "X";

        String inputContent = "<Collections><CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile></Collections>";
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        String expectedOutputContent = "<Collections><CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile></Collections>";
        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

    @Test
    public void create_Existing_User() {
        String inputContent = "<Collections><CmfUserProfile><Id>1234567890272</Id><EntityType>CmfUserProfile</EntityType><UserName>xuser2</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile></Collections>";
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");
        String receivedErrorCode = null;
        try {
            parameters = ldapService.add(domainName, "createuser", parameters);
        } catch (GenericException ge) {
            receivedErrorCode = ge.getCmfErrorCode();
        } finally {

            assertEquals(receivedErrorCode, "ERR-LDA-0005");
            log.debug("***Output START***\n" + parameters
                    + "\n***Output END***");
        }

    }

    @Test
    public void create_New_Multiple_Users() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String userName = "Xuser" + id;
        id += "X";

        String expectedOutputContent = "<Collections>";
        String inputContent = "<Collections>";
        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";
        id = dateFormat.format(new Date());
        userName = "Yuser" + id;
        id += "Y";
        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        inputContent += "</Collections>";
        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";
        expectedOutputContent += "</Collections>";
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

    }

    @Test (expected = GenericException.class)
    public void create_New_Multiple_Users_With_Existing_User() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String userName = "Auser" + id;
        id += "A";

        String expectedOutputContent = "<Collections>";
        String inputContent = "<Collections>";
        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";

        inputContent += "<CmfUserProfile><Id>1234567890272</Id><EntityType>CmfUserProfile</EntityType><UserName>xuser2</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        expectedOutputContent += "<CmfUserProfile><Status>false</Status><Message>already exist</Message><UserName>xuser2</UserName></CmfUserProfile>";

        id = dateFormat.format(new Date());
        userName = "Buser" + id;
        id += "B";
        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        inputContent += "</Collections>";
        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";

        expectedOutputContent += "</Collections>";
        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

    }

}
