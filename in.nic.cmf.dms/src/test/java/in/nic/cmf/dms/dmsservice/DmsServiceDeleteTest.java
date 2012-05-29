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

public class DmsServiceDeleteTest {

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

    private String         singleid, multiid;

    /**
     * Constructor to initialise local variables.
     */
    public DmsServiceDeleteTest() {

        domainName = "bala7.in";
        entity = "PmsMedia";
        log = new LogTracer(domainName, "dms-test");
        dmsService = new DmsServiceImpl();
        dmsService.setLogTracer(log);
        singleid = "bsnlbill.png";
        multiid = "zbcdefghijklm,abfgjiudsed1,BALAMURUGAN1";
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
    public void deleteBySingleID() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();

        parameters = dmsService.deleteByID(domainName, entity, singleid,
                parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content").toString());
        log.debug("deleteBySingleID: " + expectedOutputContent.toString());

        List<HashMap<String, String>> fileList = getFileList(expectedOutputContent);
        for (HashMap<String, String> file : fileList) {
            if (file.get("status") != null) {
                assertTrue(file.get("status").equals("true"));
            }
        }
    }

    @Test
    public void deleteByMultiID() {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();

        parameters = dmsService.deleteByID(domainName, entity, multiid,
                parameters);
        System.out.println(parameters);
        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content").toString());

        log.debug("deleteByMultiID: " + expectedOutputContent.toString());

        List<HashMap<String, String>> fileList = getFileList(expectedOutputContent);
        for (HashMap<String, String> file : fileList) {
            if (file.get("status") != null) {
                assertTrue(file.get("status").equals("true"));
            }
        }
    }

}
