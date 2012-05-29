package in.nic.cmf.sms;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * SmsService class.
 * @author Bala Murugan.S
 * @version %I%,%G%
 * @since 2012-01-20
 * @since JDK 1.6
 */
public class SmsService {
    /**
     * SmsService object creation and initialisation.
     */
    // private static final SmsService SMSSERVICE = new SmsService();
    /**
     * PropertiesUtil object creation and initialisation.
     */
    private PropertiesUtil                     propUtil;
    /**
     * LogTracer object creation and initialisation.
     */
    private LogTracer                          log            = null;
    /**
     * HttpClient object creation and initialisation.
     */
    private HttpClient                         httpclient     = new HttpClient();

    private static HashMap<String, SmsService> hashSmsService = new HashMap<String, SmsService>();
    private String                             domain;

    private SmsService(String domain) {
        this.domain = domain;
        propUtil = PropertiesUtil.getInstanceof(domain, "sms");
    }

    /**
     * get the object for SmsService class.
     * @return Object
     *         returns singleton object
     */
    public static SmsService getInstanceof(String domain) {
        if (!hashSmsService.containsKey(domain)) {
            hashSmsService.put(domain, new SmsService(domain));
        }
        return hashSmsService.get(domain);
    }

    /**
     * This method will process the input Map.
     * It contains SMS details in a Specified format and return output Map.
     * @param parameters
     *            the Map parsed from XML data
     * @return Object
     *         Map contains SMS response content
     */
    public Map<String, Map<String, Object>> send(
            final Map<String, Map<String, Object>> parameters) {

        isValidParameters(parameters);
        return buildResponse(sendSMS(buildSMSInput(parameters)), parameters);
    }

    /**
     * This method will process input Map and return output as List Map.
     * @param parameters
     *            the Map created from input XML data
     * @return Object
     */
    private List<Map<String, String>> buildSMSInput(
            final Map<String, Map<String, Object>> parameters) {

        HashMap<String, Object> inputContent = FormatXml.getInstanceof(domain)
                .in(parameters.get("input").get("content").toString());
        return getSMSList(inputContent);
    }

    /**
     * This method will process input SMS list and return output as SMS Response
     * as a List.
     * @param smsList
     *            the Map parsed from XML data
     * @return Object
     */
    private List<Map<String, String>> sendSMS(
            final List<Map<String, String>> smsList) {
        List<Map<String, String>> smsListResponse;
        smsListResponse = new ArrayList<Map<String, String>>();
        for (Map<String, String> sms : smsList) {
            smsListResponse.add(sendSMS(sms));
        }
        return smsListResponse;
    }

    /**
     * This method will process input SMS Map and return output as SMS
     * response as a HashMap.
     * @param sms
     *            the Map parsed from XML data
     * @return Object
     */
    private Map<String, String> sendSMS(final Map<String, String> sms) {

        return parseResponse(execute(buildPostMethod(sms)), sms);
    }

    /**
     * This method will build Post Method with parameters to send SMS. return
     * the PostMethod.
     * @param smsdata
     *            the Map parsed from XML data
     * @return Object
     */
    private PostMethod buildPostMethod(final Map<String, String> smsdata) {

        PostMethod method = new PostMethod(propUtil.get("smsproviderurl"));
        method.addParameter(propUtil.get("aidParam"), propUtil.get("aid"));
        method.addParameter(propUtil.get("pinParam"), propUtil.get("pin"));
        method.addParameter(propUtil.get("mobilenoParam"),
                smsdata.get(propUtil.get("mobilenoTag")));
        method.addParameter(propUtil.get("messageParam"),
                smsdata.get(propUtil.get("messageTag")));
        method.addParameter(propUtil.get("signatureParam"),
                smsdata.get(propUtil.get("signTag")));
        return method;
    }

    /**
     * This method will communicate to SMS Provider URL using HttpClient
     * Post Method with parameters to send SMS. return the Response.
     * @param method
     *            PostMethod
     * @return String
     */
    private String execute(final PostMethod method) {

        try {
            httpclient.executeMethod(method);
            return method.getResponseBodyAsString();

        } catch (HttpException e) {
            throw new GenericException(domain, "ERR-SMS-0001", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-SMS-0002", e);
        } finally {
            method.releaseConnection();
        }
    }

    /**
     * This method Parse SMS provider response and build a Response Map.
     * @param smsResponse
     *            contains SMS provider response details
     * @param smsData
     *            SMS input data
     * @return Object
     */
    private Map<String, String> parseResponse(final String smsResponse,
            final Map<String, String> smsData) {

        smsData.put(propUtil.get("statusTag"),
                propUtil.get("statusTagRejectedValue"));
        smsData.put(propUtil.get("statusmsgTag"), smsResponse);

        if (smsResponse.lastIndexOf(propUtil.get("acceptedstatuscode")) != -1) {

            smsData.put(propUtil.get("statusTag"),
                    propUtil.get("statusTagAcceptedValue"));
        }
        return smsData;
    }

    /**
     * This method Builds the response.
     * @param responseContent
     *            response data
     * @param parameters
     *            collection
     * @return Object
     */
    private Map<String, Map<String, Object>> buildResponse(
            final List<Map<String, String>> responseContent,
            final Map<String, Map<String, Object>> parameters) {

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", "200");

        Map<String, Object> smsResponse = new HashMap<String, Object>();
        smsResponse.put(propUtil.get("entityType"), responseContent);

        HashMap<String, Object> outputContent = new HashMap<String, Object>();
        outputContent.put(propUtil.get("rootTag"), smsResponse);

        output.put("content", FormatXml.getInstanceof(domain)
                .out(outputContent));

        parameters.put("output", new HashMap<String, Object>());
        parameters.get("output").putAll(output);

        log.info("Final Output............... " + output);
        return parameters;
    }

    /**
     * This method iterate inputContent recursively and build a List.
     * @param inputContent
     *            input collection
     * @return ObjectsList<HashMap<String, String>>
     */
    private List<Map<String, String>> getSMSList(
            final Map<String, Object> inputContent) {
        List<Map<String, String>> smsList;
        smsList = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        for (String key : inputContent.keySet()) {

            if (inputContent.get(key) instanceof String) {

                if (map == null) {
                    map = new HashMap<String, String>();
                }
                map.put(key, (String) inputContent.get(key));

            } else if (inputContent.get(key) instanceof HashMap<?, ?>) {

                smsList.addAll(getSMSList((HashMap<String, Object>) inputContent
                        .get(key)));

            } else if (inputContent.get(key) instanceof List<?>) {

                for (HashMap<String, Object> list : (List<HashMap<String, Object>>) inputContent
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

    /**
     * To check input HashMap content is not empty.
     * @param parameters
     *            input collection
     */
    private void isValidParameters(
            final Map<String, Map<String, Object>> parameters) {

        if (isEmpty(parameters)) {
            throw new GenericException(domain, "ERR-SMS-0004");
        }
        if (isEmpty(parameters.get("input").get("content"))) {
            throw new GenericException(domain, "ERR-SMS-0005");
        }
    }

    /**
     * returns true if it's empty, otherwise returns false.
     * @param data
     *            Object the data
     * @return boolean
     */
    private boolean isEmpty(final Object data) {

        if (data == null) {
            return true;
        }
        if (data instanceof String) {
            return (data.toString().trim()).isEmpty();
        }
        return false;
    }

    /**
     * This method initialise LogTracer log.
     * @param arg0
     *            LogTracer object
     */
    public void setLog(final LogTracer arg0) {
        log = arg0;
    }
}
