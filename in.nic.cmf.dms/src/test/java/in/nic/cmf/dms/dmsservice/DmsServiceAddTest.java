package in.nic.cmf.dms.dmsservice;

import static org.junit.Assert.assertTrue;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.dms.DMSUtils;
import in.nic.cmf.dms.DmsServiceImpl;
import in.nic.cmf.logger.LogTracer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class DmsServiceAddTest {

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
     * DMSUtils object creation and initialisation.
     */
    private DMSUtils       DMSUTILS;

    /**
     * Local variable to store CollectionInput.
     */
    private String         singleFileCollectionInput;
    /**
     * Local variable to store CollectionInput.
     */
    private String         multiFileCollectionInput;

    /**
     * Constructor to initialise local variables.
     */
    public DmsServiceAddTest() {

        domainName = "bala.in";
        entity = "PmsMedia";
        log = new LogTracer(domainName, "dms-test");
        dmsService = new DmsServiceImpl();
        dmsService.setLogTracer(log);
        DMSUTILS = DMSUtils.getInstanceof(domainName);
        multiFileCollectionInput = "<Collections>"
                + "<PmsMedia>"
                + "<Id>zbcdefghijklm.xml</Id>"
                + "<EntityType>PmsMedia</EntityType>"
                + "<PmsMediaName>sify.gif</PmsMediaName>"
                + "<SourceName>sify.gif</SourceName>"
                + "<FolderPath>/tmp</FolderPath>"
                + "<FilePath>/sify.co.in/media/mb4p5Libiagch.gif</FilePath>"
                + "<Status>Draft</Status>"
                + "<Md5>dec1941af30954c79fb7c6e4e9c2dfc2</Md5>"
                + "<CreatedDate>2011-09-20T01:15:12Z</CreatedDate>"
                + "<LastModifiedDate>2009-09-20T01:15:12Z</LastModifiedDate>"
                + "<CreatedBy>igportaladmin</CreatedBy>"
                + "<LastModifiedBy>igportaladmin</LastModifiedBy>"
                + "<Site>sify.co.in</Site>"
                + "<Version>1.0</Version>"
                + "</PmsMedia>"
                + "<PmsMedia><Id>abfgjiudsed1</Id><EntityType>PmsMedia</EntityType>"
                + "<LastModifiedDate>2011-09-20T01:15:12Z</LastModifiedDate>"
                + "</PmsMedia></Collections>";

        singleFileCollectionInput = "<Collections><Article><Id>BALAMURUGAN1</Id>"
                + "<EntityType>Article</EntityType>"
                + "<Site>bala3.in</Site>"
                + "<LastModifiedDate>2010-09-20T01:15:12Z</LastModifiedDate>"
                + "</Article></Collections>";

    }

    @Test
    public void addSingleXMLFile() throws Exception {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", singleFileCollectionInput);

        parameters = dmsService.add(domainName, entity, parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content").toString());

        log.debug("addSingleFile: " + expectedOutputContent.toString());

        List<HashMap<String, String>> fileList = getFileList(expectedOutputContent);
        for (HashMap<String, String> file : fileList) {
            if (file.get("status") != null) {
                assertTrue(file.get("status").equals("true"));
            }
        }
    }

    @Test
    public void addMultipleFiles() throws Exception {

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", multiFileCollectionInput);

        ArrayList<HashMap<String, byte[]>> files = new ArrayList<HashMap<String, byte[]>>();

        String[] binaryfiles = {"/home/bala/Pictures/zbcdefghijklm.jpg",
                "/home/bala/Pictures/bsnlbill.png" };

        for (String fileName : binaryfiles) {
            File uploadfile = new File(fileName);
            byte[] b = new byte[(int) uploadfile.length()];
            try {

                FileInputStream fileInputStream = new FileInputStream(
                        uploadfile);
                b = IOUtils.toByteArray(fileInputStream);

            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e1) {
                throw e1;
            }
            HashMap<String, byte[]> f = new HashMap<String, byte[]>();
            f.put(DMSUTILS.splitFileName(fileName), b);
            files.add(f);
        }
        parameters.get("input").put("files", files);
        parameters = dmsService.add(domainName, entity, parameters);

        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstanceof(domainName).in(
                        parameters.get("output").get("content").toString());

        log.debug("addMultipleFiles: " + expectedOutputContent.toString());

        List<HashMap<String, String>> fileList = getFileList(expectedOutputContent);
        for (HashMap<String, String> file : fileList) {
            if (file.get("status") != null) {
                assertTrue(file.get("status").equals("true"));
            }
        }
        System.out.println(expectedOutputContent.toString());
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
