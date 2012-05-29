package in.nic.cmf.ldapservices;

import static org.junit.Assert.assertEquals;
import in.nic.cmf.ldap.LdapService;
import in.nic.cmf.logger.LogTracer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

public class UpdateUserLdapServiceTest {

    private LogTracer   log;
    private LdapService ldapService;
    private String      domainName;

    public UpdateUserLdapServiceTest() {
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
    public void find_Single_User_Method_Find_By_ID() {

        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String id = dateFormat.format(new Date());

        String userName = "Xuser" + id;
        id += "X";

        String inputContent, expectedOutputContent, findUserExpectedOutputContent;
        inputContent = expectedOutputContent = findUserExpectedOutputContent = "<Collections>";

        inputContent += "<CmfUserProfile><Id>"
                + id
                + "</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password><UserRole>PortalAdmin</UserRole><IsActive>true</IsActive></CmfUserProfile>";

        expectedOutputContent += "<CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile>";

        inputContent += "</Collections>";
        expectedOutputContent += "</Collections>";

        HashMap<String, HashMap<String, Object>> parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "createuser", parameters);

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

        inputContent = "<Collections><CmfUserProfile><Id>myNewID</Id><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cf99</Password>"
                + "<NewPassword>5f4dcc3b5aa765d61d8327deb882cfXX</NewPassword>"
                + "<UserRole>myNewUserRole</UserRole></CmfUserProfile>"
                + "</Collections>";
        parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "update", parameters);

        expectedOutputContent = "<Collections><CmfUserProfile><Status>true</Status><UserName>"
                + userName + "</UserName></CmfUserProfile></Collections>";

        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

        findUserExpectedOutputContent += "<CmfUserProfile><Status>true</Status><UserRole>myNewUserRole</UserRole><IsActive>true</IsActive><UserName>"
                + userName
                + "</UserName><ProfileId>"
                + id
                + "</ProfileId></CmfUserProfile></Collections>";

        parameters = new HashMap<String, HashMap<String, Object>>();

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.findById(domainName, "CmfUserProfile",
                userName, parameters);
        assertEquals(findUserExpectedOutputContent, parameters.get("output")
                .get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

        inputContent = "<Collections><CmfUserProfile><EntityType>CmfUserProfile</EntityType><UserName>"
                + userName
                + "</UserName><Password>5f4dcc3b5aa765d61d8327deb882cfXX</Password></CmfUserProfile></Collections>";

        expectedOutputContent = "<Collections><CmfUserProfile><Status>true</Status><UserRole>myNewUserRole</UserRole><Id>"
                + id
                + "</Id><UserName>"
                + userName
                + "</UserName></CmfUserProfile></Collections>";

        parameters = new HashMap<String, HashMap<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);

        log.debug("***Input START***\n" + parameters + "\n***Input END***");

        parameters = ldapService.add(domainName, "checkauth", parameters);
        assertEquals(expectedOutputContent,
                parameters.get("output").get("content"));

        log.debug("***Output START***\n" + parameters + "\n***Output END***");

    }

}
