package in.nic.cmf.ldapservices;

import static org.junit.Assert.assertEquals;
import in.nic.cmf.ldap.LdapService;
import in.nic.cmf.logger.LogTracer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CheckAuthLdapServiceTest {

    private LogTracer   log;
    private LdapService ldapService;
    private String      domainName;

    public CheckAuthLdapServiceTest() {
        domainName = "bala.in";
        log = new LogTracer(domainName,"ldap-test");
        ldapService = new LdapService();
        ldapService.setLogTracer(log);
    }

    protected void setUp() {

    }

    protected void tearDown() {

    }

    @Test
    public void check_Single_Auth() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String userName = "Xuser" + id;
        id += "X";

        String inputContent, checkUserInputContent, expectedOutputContent, checkUserExpectedOutputContent;
        inputContent = checkUserInputContent = expectedOutputContent = checkUserExpectedOutputContent = "<Collections>";

        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        checkUserInputContent += "<CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password></CmfUserProfile>";

        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";
        checkUserExpectedOutputContent += "<CmfUserProfile><Status>true</Status><UserRole>PortalAdmin</UserRole><Id>"
                + id
                + "</Id><UserName>"
                + userName
                + "</UserName></CmfUserProfile>";
        inputContent += "</Collections>";
        checkUserInputContent += "</Collections>";
        expectedOutputContent += "</Collections>";
        checkUserExpectedOutputContent += "</Collections>";

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

        parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", checkUserInputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "checkauth", parameters);
        assertEquals(checkUserExpectedOutputContent, parameters.get("output")
                .get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

    @Test
    public void check_Unknown_Auth() {
        String inputContent = "<Collections><CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>ABCDE120105152148</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password></CmfUserProfile></Collections>";
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "checkauth", parameters);
        String expectedOutputContent = "<Collections><CmfUserProfile><Status>false</Status><UserName>ABCDE120105152148</UserName></CmfUserProfile></Collections>";
        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

    @Test
    public void check_Single_Auth_With_Wrong_Password() {
        String inputContent = "<Collections><CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>Xuser120105152148</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf00</Password></CmfUserProfile></Collections>";
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "checkauth", parameters);
        String expectedOutputContent = "<Collections><CmfUserProfile><Status>false</Status><UserName>Xuser120105152148</UserName></CmfUserProfile></Collections>";
        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");
    }

    @Test
    public void check_Multiple_Users_Auth_With_False_Info() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String inputContent, checkUserInputContent, expectedOutputContent, checkUserExpectedOutputContent;
        inputContent = checkUserInputContent = expectedOutputContent = checkUserExpectedOutputContent = "<Collections>";

        String userName = "Xuser" + id;
        id += "X";

        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";
        checkUserInputContent += "<CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password></CmfUserProfile>";
        checkUserExpectedOutputContent += "<CmfUserProfile><Status>true</Status><UserRole>PortalAdmin</UserRole><Id>"
                + id
                + "</Id><UserName>"
                + userName
                + "</UserName></CmfUserProfile>";

        id = dateFormat.format(new Date());
        userName = "Yuser" + id;
        id += "Y";
        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";
        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";
        checkUserInputContent += "<CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password></CmfUserProfile>";
        checkUserExpectedOutputContent += "<CmfUserProfile><Status>true</Status><UserRole>PortalAdmin</UserRole><Id>"
                + id
                + "</Id><UserName>"
                + userName
                + "</UserName></CmfUserProfile>";

        inputContent += "</Collections>";

        expectedOutputContent += "</Collections>";

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

        checkUserInputContent += "<CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>ABCDE120105152148</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password></CmfUserProfile>";
        checkUserExpectedOutputContent += "<CmfUserProfile><Status>false</Status><UserName>ABCDE120105152148</UserName></CmfUserProfile>";

        checkUserInputContent += "</Collections>";
        checkUserExpectedOutputContent += "</Collections>";

        parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", checkUserInputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "checkauth", parameters);

        assertEquals(checkUserExpectedOutputContent, parameters.get("output")
                .get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

    }
}
