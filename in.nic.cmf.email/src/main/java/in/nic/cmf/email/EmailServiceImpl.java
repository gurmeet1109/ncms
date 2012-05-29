package in.nic.cmf.email;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@ServiceName ("email")
public class EmailServiceImpl implements CMFService {

    public LogTracer           log = null;

    private ConvertorUtils     cu;
    public static EmailService emailService;
    FormatXml                  formatXml;
    private PropertiesUtil     propUtil;
    private String             domain;

    private void setLogger(String domain) {
        log = new LogTracer(domain, "acl");
    }

    private void process(List<Map<String, Object>> storableList,
            Map<String, String> userContext,
            Map<String, Map<String, Object>> parameters) {
        for (Map<String, Object> storable : storableList) {
            process(storable, userContext, parameters);
        }
    }

    private void process(Map<String, Object> storable,
            Map<String, String> userContext,
            Map<String, Map<String, Object>> parameters) {
        if (storable.containsKey("WorkflowInfo")) {
            log.debug("Incoming Entity is workflowable.so trying to send email");

            Map<String, Object> wfInfo = (Map<String, Object>) storable
                    .get("WorkflowInfo");
            wfInfo = (Map<String, Object>) wfInfo.get("AssignedTo");
            String strUser = (String) wfInfo.get("User");
            log.debug("assignedto :" + strUser);
            try {
                if (!emailService.sendEmail(storable, userContext, strUser,
                        parameters)) {
                    log.debug("sendEmail failed.");
                }
            } catch (GenericException ge) {
                log.error("In GE: sendEmail failed." + ge.getMessage());
            } catch (Exception ex) {
                log.error("In EX: sendEmail failed." + ex.getMessage());
            }
            log.debug("After sending email.");
        } else {
            if (storable.get("EntityType").equals("CmsUserProfile")
                    || storable.get("EntityType").equals("UserProfile")) {
                System.out.println("outside process method");
                if (storable.containsKey("Email")) {
                    log.info("its contain email key" + storable);
                    ArrayList email = new ArrayList();
                    email.add(storable.get("Email"));
                    log.info("Email id is " + storable.get("Email"));
                    try {
                        emailService.sendSSLMessage(email,
                                propUtil.getProperty("sub"),
                                propUtil.getProperty("msg"),
                                propUtil.getProperty("fromemailid"));
                    } catch (GenericException ge) {
                        log.error("In GE: sendEmail failed." + ge.getMessage());
                    } catch (Exception ex) {
                        log.error("In EX: sendEmail failed." + ex.getMessage());
                    }
                }
            }
        }

    }

    private Map<String, Map<String, Object>> buildResponseMap() {

        Map<String, Map<String, Object>> responseMap = new HashMap<String, Map<String, Object>>();
        Map<String, Object> outputMap = new HashMap<String, Object>();
        outputMap.put("content", "Posted Successfully");
        outputMap.put("statusCode", "200");
        responseMap.put("output", outputMap);
        return responseMap;
    }

    public Map<String, Map<String, Object>> add(String domainName,
            String entitType, Map<String, Map<String, Object>> parameters)
            throws GenericException {
        cu = ConvertorUtils.getInstanceof(domain);
        formatXml = FormatXml.getInstanceof(domain);
        propUtil = PropertiesUtil.getInstanceof(domain, "email");
        String strCollections = (String) parameters.get("input").get("content");
        Map<String, String> usercontext = (Map<String, String>) parameters.get(
                "input").get("usercontext");
        Map<String, Object> storableMaps = formatXml.in(strCollections);
        storableMaps = (HashMap<String, Object>) storableMaps
                .get("Collections");
        for (Entry<String, Object> eachMap : storableMaps.entrySet()) {
            try {
                Object value = eachMap.getValue();
                if (cu.isArrayList(value)) {
                    process((List<Map<String, Object>>) value, usercontext,
                            parameters);
                } else if (cu.isHashMap(value)) {
                    process((Map<String, Object>) value, usercontext,
                            parameters);
                }

            } catch (GenericException ge) {
                log.error("In GE:" + ge.getMessage());

            } catch (Exception ex) {
                log.error("In EX:" + ex.getMessage());
            }
        }
        return buildResponseMap();
    }

    public Map<String, Map<String, Object>> deleteByID(String arg0,
            String arg1, String arg2, Map<String, Map<String, Object>> arg3)
            throws GenericException {
        throw new GenericException(domain, "ERR-EMAIL-0001");
    }

    public Map<String, Map<String, Object>> deleteByQuery(String arg0,
            String arg1, Map<String, Map<String, Object>> arg2)
            throws GenericException {
        throw new GenericException(domain, "ERR-EMAIL-0001");
    }

    public Map<String, Map<String, Object>> find(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) throws GenericException {
        throw new GenericException(domain, "ERR-EMAIL-0001");
    }

    public Map<String, Map<String, Object>> findById(String arg0, String arg1,
            String arg2, Map<String, Map<String, Object>> arg3)
            throws GenericException {
        throw new GenericException(domain, "ERR-EMAIL-0001");
    }

    public void setLogTracer(LogTracer arg0) {
        this.log = arg0;
    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-EMAIL-0001");
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

}
