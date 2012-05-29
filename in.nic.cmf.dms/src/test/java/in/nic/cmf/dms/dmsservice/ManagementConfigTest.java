package in.nic.cmf.dms.dmsservice;

import static org.junit.Assert.assertTrue;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.dms.DmsServiceImpl;
import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ManagementConfigTest {
    /**
     * LogTracer object.
     */
    private LogTracer      log;

    /**
     * managementServiceImpl object.
     */
    private DmsServiceImpl dmsServiceImpl;
    /**
     * Local variable to assign domainName.
     */
    private String         domainName;
    /**
     * Local variable to assign entity name.
     */
    private String         entity;
    private String         service;
    /**
     * Local variable to store input.
     */
    private String         inputContent;
    /**
     * Local variable to store CollectionInput.
     */
    private String         singleCollectionInputContent;
    private String         multiCollectionInputContent;

    /**
     * Constructor to initialise local variables.
     */
    public ManagementConfigTest() {

        domainName = "bala.in";
        service = "dms";
        entity = "Configuration";
        log = new LogTracer(domainName, "managementService-test");
        dmsServiceImpl = new DmsServiceImpl();
        dmsServiceImpl.setLogTracer(log);

        singleCollectionInputContent = "<Collections><Id>fghserdswraqw</Id>"
                + "<EntityName>Configuration</EntityName><EntityType>Configuration</EntityType>"
                + "<ServiceType>dms</ServiceType>"
                + "<ConfigurationKeys>"
                + "<ConfigurationKey><Key>username</Key><Value>svnuser</Value>"
                + "</ConfigurationKey>"
                + "<ConfigurationKey><Key>password</Key>"
                + "<Value>svnpassword</Value></ConfigurationKey>"
                + "<ConfigurationKey><Key>url</Key><Value>http://localhost/svn/dms</Value>"
                + "</ConfigurationKey>"
                + "<ConfigurationKey><Key>defaultfileextension</Key>"
                + " <Value>.xml</Value></ConfigurationKey>"
                + "<ConfigurationKey><Key>storage</Key><Value>SvnStorage</Value>"
                + "</ConfigurationKey>"
                + "<ConfigurationKey><Key>directoryseparator</Key>"
                + "<Value>/</Value></ConfigurationKey>"
                + "</ConfigurationKeys>"
                + "<Site>bala.in</Site><Version>1.0</Version>"
                + "</Collections>";

        // singleCollectionInputContent = "<Collections><Id>fghserdswraqw</Id>"
        // +
        // "<EntityName>Configuration</EntityName><EntityType>Configuration</EntityType>"
        // + "<ServiceType>dms</ServiceType>" + "<ConfigurationKeys>"
        // + "<ConfigurationKey><Key>username</Key><Value>svnuser</Value>"
        // + "</ConfigurationKey>" + "</ConfigurationKeys>"
        // + "<Site>bala.in</Site><Version>1.0</Version>"
        // + "</Collections>";

        // singleCollectionInputContent = "<Collections><Id>abcdefghijklmn</Id>"
        // + "<EntityType>Configuration</EntityType><Site>bala.in</Site>"
        // + "<ServiceType>dms</ServiceType>"
        // + "<url>http://localhost/svn/dms</url>"
        // + "<username>svnuser</username>"
        // + "<password>svnpassword</password>"
        // + "<DefaultFileExtension>.xml</DefaultFileExtension>"
        // + "<DirectorySeparator>/</DirectorySeparator>"
        // + "<Storage>SvnStorage</Storage></Collections>";

        // multiCollectionInputContent = "<Collections>"
        // + "<Dms><Id>fghserdswraqw</Id>"
        // + "<EntityType>Configuration</EntityType><Site>bala.in</Site>"
        // + "<Svnurl>http://localhost/svn/dms</Svnurl>"
        // + "<Svnusername>svnuser</Svnusername>"
        // + "<Svnpassword>svnpassword</Svnpassword>"
        // + "<DefaultFileExtension>.xml</DefaultFileExtension>"
        // + "<DirectorySeparator>/</DirectorySeparator>"
        // + "<Storage>SvnStorage</Storage>"
        // + "</Dms>"
        // + "<Searchengine>"
        // + "<Id>fghserdswraaw</Id>"
        // + "<EntityType>Configuration</EntityType>"
        // + "<Site>bala.in</Site>"
        // + "<Solrurl> http://210.210.125.210:8983/solr/</Solrurl>"
        // + "<QueryStringParserConfig>"
        // +
        // "<Solrkeylist>orderby,orderbydir,offset,limit,fields,latitude,longitude,radius,qt,authusername,api_key,format,aclrole,type</Solrkeylist>"
        // + "<Orderby>id</Orderby>" + "<Orderbydir>desc</Orderbydir>"
        // + "<Offset>0</Offset>" + "<Limit>10</Limit>"
        // + "<Maximumlimit>100</Maximumlimit>"
        // + "<Fields>id,entitytype</Fields>"
        // + "<Defaultcondition>AND</Defaultcondition>"
        // + "<Radius>1</Radius>" + "<Qt>geo</Qt>"
        // + "<Latitude>5000</Latitude>" + "<Longitude>5000</Longitude>"
        // + "<Authusername>test</Authusername>"
        // + "<Api_key>test</Api_key>" + "<Format>test</Format>"
        // + "<Aclrole>test</Aclrole>" + "<Type>test</Type>"
        // + "</QueryStringParserConfig>" + "</Searchengine>"
        // + "</Collections>";

        // managementService = new managementServiceImpl();
    }

    /**
     * Junit method, setUp is called before the evaluation of each test.
     */
    protected void setUp() {

    }

    /**
     * Junit method, tearDown which is called after the evaluation of each test.
     */
    protected void tearDown() {

    }

    @Test
    public void getManageConfig() throws Exception {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", "");
        dmsServiceImpl.setLogTracer(log);
        parameters = dmsServiceImpl.findManage(domainName, service, entity,
                parameters);
        System.out.println(parameters);
        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content").toString());
        log.debug("getManageConfig: " + expectedOutputContent.toString());
        assertTrue(!expectedOutputContent.isEmpty());
    }

    @Test
    public void deleteManageConfig() throws Exception {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", "");
        dmsServiceImpl.setLogTracer(log);
        parameters = dmsServiceImpl.deleteManage(domainName, service, entity,
                parameters);
        System.out.println(parameters);
        assertTrue(parameters.get("output").get("statusCode").equals("200"));
    }

    @Test
    public void addManageConfig() throws Exception {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", singleCollectionInputContent);
        dmsServiceImpl.setLogTracer(log);
        parameters = dmsServiceImpl.addManage(domainName, service, entity,
                parameters);
        assertTrue(parameters.get("output").get("statusCode").equals("200"));
    }

    private List<HashMap<String, String>> getFileList(
            final HashMap<String, Object> inputData) {

        List<HashMap<String, String>> dmsList;
        dmsList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = null;
        for (String key : inputData.keySet()) {
            if (inputData.get(key) instanceof String) {

                if (map == null) {
                    map = new HashMap<String, String>();
                }
                map.put(key, (String) inputData.get(key));
            } else if (inputData.get(key) instanceof HashMap<?, ?>) {

                dmsList.addAll(getFileList((HashMap<String, Object>) inputData
                        .get(key)));
            } else if (inputData.get(key) instanceof List<?>) {
                for (HashMap<String, Object> list : (List<HashMap<String, Object>>) inputData
                        .get(key)) {
                    dmsList.addAll(getFileList(list));
                }
            }
        }
        if (map != null) {
            dmsList.add(map);
        }
        return dmsList;
    }

}
