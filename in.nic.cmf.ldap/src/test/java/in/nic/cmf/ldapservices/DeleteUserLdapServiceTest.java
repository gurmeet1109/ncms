package in.nic.cmf.ldapservices;

import static org.junit.Assert.assertEquals;
import in.nic.cmf.ldap.LdapService;
import in.nic.cmf.logger.LogTracer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

public class DeleteUserLdapServiceTest {

    private LogTracer   log;
    private LdapService ldapService;
    private String      domainName;

    public DeleteUserLdapServiceTest() {
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
    public void delete_Single_User_Method_Delete_By_ID() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String userName = "Xuser" + id;
        id += "X";

        String inputContent, expectedOutputContent, deleteUserExpectedOutputContent;
        inputContent = expectedOutputContent = deleteUserExpectedOutputContent = "<Collections>";

        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";

        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";

        deleteUserExpectedOutputContent += "<CmfUserProfile><Status>true</Status><ProfileId>"
                + id + "</ProfileId></CmfUserProfile>";

        inputContent += "</Collections>";
        expectedOutputContent += "</Collections>";
        deleteUserExpectedOutputContent += "</Collections>";

        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

        parameters = new HashMap<String, HashMap<String, Object>>();

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.deleteByID(domainName, "CmfUserProfile", id,
                parameters);

        assertEquals(deleteUserExpectedOutputContent, parameters.get("output")
                .get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

    @Test
    public void delete_Unknown_Single_User_Method_Delete_By_ID() {
        String profileId = "1201061149XYZ";
        String deleteUserExpectedOutputContent = "<Collections><CmfUserProfile><Status>false</Status><ProfileId>"
                + profileId + "</ProfileId></CmfUserProfile></Collections>";

        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.deleteByID(domainName, "CmfUserProfile",
                profileId, parameters);

        assertEquals(deleteUserExpectedOutputContent, parameters.get("output")
                .get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

}
