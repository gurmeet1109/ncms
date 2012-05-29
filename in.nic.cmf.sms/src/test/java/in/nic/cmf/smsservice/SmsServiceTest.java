package in.nic.cmf.smsservice;

import static org.junit.Assert.assertTrue;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.sms.SmsServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author Bala Murugan.S
 *         SmsService test cases
 */
// extends TestCase
public class SmsServiceTest {

    /**
     * LogTracer object.
     */
    private LogTracer            log;

    /**
     * SmsServiceImpl object.
     */
    private SmsServiceImpl       smsService;
    /**
     * Local variable to assign domainName.
     */
    private String               domainName;
    /**
     * Local variable to assign entity name.
     */
    private String               entity;
    /**
     * PropertiesUtil object.
     */
    private final PropertiesUtil propUtil;
    /**
     * Local variable to store input.
     */
    private String               inputContent;
    /**
     * Local variable to store CollectionInput.
     */
    private String               collectionInputContent;

    /**
     * Constructor to initialise local variables.
     */
    public SmsServiceTest() {

        domainName = "sample.in";
        entity = "sms";
        log = new LogTracer(domainName, "smsservice-test");
        smsService = new SmsServiceImpl();
        smsService.setLogTracer(log);
        propUtil = PropertiesUtil.getInstanceof(domainName, "sms");

        collectionInputContent = "<Collections>"
                + "<Sms><MobileNumber>9840771570</MobileNumber>"
                + "<SmsMessage>You have successfully completed "
                + "PART I Application with "
                + "Reg.No:11111 &amp; DOB:01-01-2001 for SSC</SmsMessage>"
                + "<Signature>SSCREG</Signature></Sms>"

                + "<Sms><MobileNumber>9840771570</MobileNumber>"
                + "<SmsMessage>You have successfully completed "
                + "PART I Application with "
                + "Reg.No:11111 &amp; DOB:01-01-2001 for SSC</SmsMessage>"
                + "<Signature>SSCREG</Signature></Sms>" + "</Collections>";

        inputContent = "<Collections>"
                + "<Sms><MobileNumber>9840771570</MobileNumber>"
                + "<SmsMessage>You have successfully completed "
                + "PART I Application with "
                + "Reg.No:11111 &amp; DOB:01-01-2001 for SSC</SmsMessage>"
                + "<Signature>SSCREG</Signature></Sms>" + "</Collections>";

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

    /**
     * Test SMS sending in positive flow manner in multiple mobile no.
     */
    @Test
    public void sendMultiSms() {
        Map<String, Map<String, Object>> parameters;
        parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", collectionInputContent);
        parameters = smsService.add(domainName, entity, parameters);
        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstance().in(
                        parameters.get("output").get("content").toString());
        List<HashMap<String, String>> smsList = getSMSList(expectedOutputContent);
        for (HashMap<String, String> sms : smsList) {
            assertTrue(sms.get(propUtil.get("statusTag")).equals(
                    propUtil.get("statusTagAcceptedValue")));
        }

    }

    /**
     * Test SMS sending in positive flow manner in single Mobile no.
     */
    @Test
    public void sendSingleSms() {
        Map<String, Map<String, Object>> parameters;
        parameters = new HashMap<String, Map<String, Object>>();
        parameters.put("input", new HashMap<String, Object>());
        parameters.get("input").put("content", inputContent);
        parameters = smsService.add(domainName, entity, parameters);
        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstance().in(
                        parameters.get("output").get("content").toString());
        List<HashMap<String, String>> smsList = getSMSList(expectedOutputContent);
        for (HashMap<String, String> sms : smsList) {
            assertTrue(sms.get(propUtil.get("statusTag")).equals(
                    propUtil.get("statusTagAcceptedValue")));
        }

    }

    /**
     * Test SMS sending in negative flow manner will throws Generic Exception.
     */
    @Test
    public void sendSmsNegativeTest() {
        Map<String, Map<String, Object>> parameters;
        parameters = new HashMap<String, Map<String, Object>>();

        parameters.put("input", new HashMap<String, Object>());

        Map<String, Map<String, Object>> outputParameters = null;
        try {
            /* Parameters passed as null */
            outputParameters = smsService.add(domainName, entity, null);
            assertTrue(outputParameters.equals(null));
        } catch (GenericException e) {
            assertTrue(e.getCmfErrorCode().equals("ERR-SMS-0004"));
        }
        try {
            /* content passed as null */
            parameters.get("input").put("content", null);
            outputParameters = smsService.add(domainName, entity, parameters);
            assertTrue(outputParameters.equals(null));
        } catch (GenericException e) {
            assertTrue(e.getCmfErrorCode().equals("ERR-SMS-0005"));
        }

        /* sending wrong mobile no to get rejected status */
        inputContent = "<Collections>" + "<Sms><MobileNumber>98</MobileNumber>"
                + "<SmsMessage>You have successfully completed"
                + " PART I Application with"
                + "Reg.No:11111 &amp; DOB:01-01-2001 for SSC</SmsMessage>"
                + "<Signature>SSCREG</Signature></Sms>" + "</Collections>";

        parameters.get("input").put("content", inputContent);

        outputParameters = smsService.add(domainName, entity, parameters);
        HashMap<String, Object> expectedOutputContent;
        expectedOutputContent = (HashMap<String, Object>) FormatXml
                .getInstance().in(
                        outputParameters.get("output").get("content")
                                .toString());
        List<HashMap<String, String>> smsList = getSMSList(expectedOutputContent);

        for (HashMap<String, String> sms : smsList) {

            assertTrue(!sms.get(propUtil.get("statusTag")).equals(
                    propUtil.get("statusTagAcceptedValue")));
        }

    }

    /**
     * SMS Service deletion.
     */
    @Test
    public void deleteSms() {

        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            smsService.deleteByID(domainName, entity, new String(), parameters);
        } catch (GenericException e) {
            assertTrue(e.getCmfErrorCode().equals("ERR-SMS-0003"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * SMS Service deletion by query.
     */
    @Test
    public void deleteByQuery() {

        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            smsService.deleteByQuery(domainName, entity, parameters);
        } catch (GenericException e) {

            assertTrue(e.getCmfErrorCode().equals("ERR-SMS-0003"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * SMS Service search by ID.
     */
    @Test
    public void findById() {

        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            smsService.findById(domainName, entity, null, parameters);
        } catch (GenericException e) {

            assertTrue(e.getCmfErrorCode().equals("ERR-SMS-0003"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * SMS Service search.
     */
    @Test
    public void find() {

        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            smsService.find(domainName, entity, parameters);
        } catch (GenericException e) {

            assertTrue(e.getCmfErrorCode().equals("ERR-SMS-0003"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * SMS Service utility function to retrieve SMS data as a List.
     * @param inputData
     *            Input data as a HashMap
     * @return Object
     *         List of HashMap object contains Input data.
     */
    private List<HashMap<String, String>> getSMSList(
            final HashMap<String, Object> inputData) {

        List<HashMap<String, String>> smsList;
        smsList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = null;
        for (String key : inputData.keySet()) {

            if (inputData.get(key) instanceof String) {

                if (map == null) {
                    map = new HashMap<String, String>();
                }
                map.put(key, (String) inputData.get(key));
            } else if (inputData.get(key) instanceof HashMap<?, ?>) {

                smsList.addAll(getSMSList((HashMap<String, Object>) inputData
                        .get(key)));
            } else if (inputData.get(key) instanceof List<?>) {

                for (HashMap<String, Object> list : (List<HashMap<String, Object>>) inputData
                        .get(key)) {
                    smsList.addAll(getSMSList(list));
                }
            }
        }
        if (map != null) {
            smsList.add(map);
        }
        return smsList;
    }
}
