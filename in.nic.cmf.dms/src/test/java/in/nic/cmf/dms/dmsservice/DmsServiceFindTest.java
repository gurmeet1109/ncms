/**
 * DmsServiceGetTest.java
 */
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

/**
 * @author bala
 * @date Feb 17, 2012
 *       dms;
 */
public class DmsServiceFindTest {

    /**
     * LogTracer object.
     */
    private LogTracer      log;

    /**
     * DmsServiceImpl object.
     */
    private DmsServiceImpl dmsService;
    /**
     * Local variable to assign domainName.
     */
    private String         domainName;
    /**
     * Local variable to assign entity name.
     */
    private String         entity;

    /**
     * Local variable to store CollectionInput.
     */
    private String         readFileCollectionInput;

    private String         singlexmlid, singlebinaryid, multixmlid,
            multibinaryid, multixmlbinaryid, singlexmlwithrevisionid;

    public DmsServiceFindTest() {

        domainName = "bala.in";
        // entity = "PmsMedia";
        // domainName = "sify.co.in";
        entity = "Category";
        log = new LogTracer(domainName, "dms-test");
        dmsService = new DmsServiceImpl();
        dmsService.setLogTracer(log);

        readFileCollectionInput = "<Collections>"
                + "<PmsMedia><id>zbcdefghijklm</id><entitytype>PmsMedia</entitytype></PmsMedia>"
                + "<Article><id>abfgjiudsed1</id><entitytype>Article</entitytype></Article>"
                + "<Article><id>BALAMURUGAN1</id><entitytype>Article</entitytype></Article>"
                + "<Article><id>zbcdefghijklm.jpg</id><entitytype>Article</entitytype></Article>"
                + "</Collections>";
        singlexmlwithrevisionid = "BALAMURUGAN1";
        singlexmlid = "BAjnbvghyuioa";
        singlebinaryid = "zbcdefghijklm.jpg";
        multixmlid = "zbcdefghijklm,abfgjiudsed1,BALAMURUGAN1";
        // multixmlid = "mdiptgdajidab,mdgqT8aegjchh";
        multibinaryid = "bsnlbill.png,zbcdefghijklm.jpg";
        multixmlbinaryid = "zbcdefghijklm,abfgjiudsed1,zbcdefghijklm.jpg";

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

    @Test
    public void getSingleXMLFilewithRevision() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("r1", "-2");
        parameters.get("input").put("queryParams", queryParams);
        parameters = dmsService.findById(domainName, entity,
                singlexmlwithrevisionid, parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content"));
        System.out.println(parameters.get("output"));
        log.debug("getSingleXMLFile: " + expectedOutputContent.toString());

        assertTrue(!expectedOutputContent.isEmpty());
    }

    @Test
    public void getSingleBinaryFile() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters = dmsService.findById(domainName, entity, singlebinaryid,
                parameters);

        // HashMap<String, Object> expectedOutputContent;
        // expectedOutputContent = parameters.get("output").get("content");

        assertTrue(!parameters.get("output").get("content").toString()
                .isEmpty());
        log.debug("getSingleBinaryFile: "
                + parameters.get("output").get("content").toString());
    }

    @Test
    public void getSingleXMLFile() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", "");
        parameters = dmsService.findById(domainName, entity, singlexmlid,
                parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content"));
        System.out.println(parameters.get("output"));
        log.debug("getSingleXMLFile: " + expectedOutputContent.toString());

        assertTrue(!expectedOutputContent.isEmpty());
    }

    @Test
    public void getMultipleBinaryFile() {

        String id = "bsnlbill.png,0001.jpg";
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters = dmsService.findById(domainName, entity, multibinaryid,
                parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content"));
        assertTrue(!expectedOutputContent.isEmpty());
        log.debug("getMultipleBinaryFile: " + expectedOutputContent.toString());

    }

    @Test
    public void getMultipleXMLFile() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters = dmsService.findById(domainName, entity, multixmlid,
                parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content"));
        System.out.println(parameters.get("output").get("content"));
        assertTrue(!expectedOutputContent.isEmpty());
        log.debug("getMultipleXMLFile: " + expectedOutputContent.toString());
    }

    @Test
    public void getMultipleXMLBinaryFile() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters = dmsService.findById(domainName, entity, multixmlbinaryid,
                parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content"));
        System.out.println(parameters.get("output").get("content"));
        assertTrue(!expectedOutputContent.isEmpty());
        log.debug("getMultipleXMLBinaryFile: "
                + expectedOutputContent.toString());
    }

    @Test
    public void readbyPOST() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", readFileCollectionInput);
        parameters = dmsService.read(domainName, entity, parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content").toString());

        log.debug("readbyPOST: " + expectedOutputContent.toString());

        List<HashMap<String, String>> fileList = getFileList(expectedOutputContent);
        for (HashMap<String, String> file : fileList) {
            if (file.get("data") != null) {
                assertTrue(!file.get("data").isEmpty());
            }
        }
    }

}
