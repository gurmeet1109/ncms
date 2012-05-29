/*package in.nic.cmf.aspects.acl;

import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import net.sf.json.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


 * @author Raja R
 * Modified by kavitha on 4th Novemver 2011
 * Domain free Added in postCollectionsToACL by Raja R Dec28-2011
 
public class AccessControlAspect {

    // public AccessControlServiceImpl accessControlService =
    // AccessControlServiceImpl .getInstance();

    private LogTracer      log;
    private Utils          utils;
    private PropertiesUtil proputil;
    private List<String>   exemptions = new ArrayList<String>();
    private String         domain;

    public AccessControlAspect(String domain) {
        this.domain = domain;
        setLogger(domain);
        proputil = PropertiesUtil.getInstanceof(domain, "dataservices");
        exemptions = getExemptionList();
        utils = Utils.getInstanceof(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "ACLAspect");
    }

    private Map exemptionForACL(Map requestedMap) {
        try {
            for (String key : exemptions) {
                requestedMap.remove(key);
                log.debug("removed key:" + key);
            }
            log.debug("exemptionforacl are:" + requestedMap);
        } catch (Exception ex) {
            log.error("In EX of exemptionForACL:" + ex.getMessage());
        }
        return requestedMap;

    }

    public List<String> getExemptionList() {
        log.debug("Needs to be removed from ACL Map:"
                + (String) proputil.getProperty("aclexemption"));
        String[] domain = proputil.getProperty("aclexemption").split(",");
        return Arrays.asList(domain);
    }

    private void getCallToACL(HttpServletRequest request, String domain)
            throws GenericException {
        log.methodEntry("AccessControlAspect.getCallToACL entry");
        try {
            boolean flagAcl = true;
            Map aclNeededMap = exemptionForACL(utils.getDetailsFromRequest(
                    request, domain));
            log.debug("Input to acl.fireRules:" + aclNeededMap);
            flagAcl = utils.isSkipAcl(request);
            if (flagAcl) {
                boolean aclStatus = accessControlService
                        .fireRules(aclNeededMap);
                
  
                if (!aclStatus) {
                    log.debug("aclstatus is false, so throwing exception.");
                    throw new GenericException(domain, "ERR-ACL-0011",
                            "fireRules returns false.");
                }
            }
            log.methodExit("AccessControlAspect.getCallToACL exit");
        } catch (GenericException ge) {
            log.error("In getCallToACL GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In getCallToACL EX:" + ex.getMessage());
            throw new GenericException(domain,"ERR-ASP-0001", "getCallToACL failure."
                    + "Input is request and domain.", ex);
        }
    }

    private void postCollectionsToACL(String domainName,
            HttpServletRequest request, String strCollections)
            throws GenericException {
        
         * Domain free added in this part
         
        // String strCollections =
        // Utils.getInstance().getCollectionsAsStringXml(collections);

        log.methodEntry(this.getClass().getSimpleName() + ".postCallToACL()");
        try {
            boolean flagAcl = true;
            List<Map<String, String>> collectionsInMap = new ArrayList<Map<String, String>>();
            Map returnedMap = utils.getDetailsFromRequest(request, domainName);
            log.debug("Before starting ACLEntry Aspect method:" + returnedMap);
            flagAcl = utils.isSkipAcl(request);
            HashMap<String, Object> result = FormatXml.getInstanceof(domain).in(strCollections);
            HashMap<String, Object> xmlContent = FormatFlatten.getInstanceOf(domain).in(result);
            log.debug("xmlContent is::" + xmlContent);
            HashMap<String, Object> firstmap = (HashMap<String, Object>) xmlContent
                    .get("Collections");
            HashMap<String, Object> processedMap = new HashMap<String, Object>();
            if (flagAcl) {
                for (Entry<String, Object> eachStorable : firstmap.entrySet()) {
                    if (eachStorable.getKey() != "Count") {
                        if (eachStorable.getValue().getClass().getSimpleName()
                                .equalsIgnoreCase("hashmap")) {
                            log.debug("Inside hashmap");
                            Map<String, String> flattenedEntityMap = new HashMap<String, String>();
                            Map aclNeededMap = new HashMap();
                            aclNeededMap.putAll(returnedMap);
                            HashMap<String, Object> h = (HashMap<String, Object>) eachStorable
                                    .getValue();
                            for (Entry<String, Object> entry : h.entrySet()) {
                                if (!((String) entry.getValue()).isEmpty()
                                        || ((String) entry.getValue())
                                                .equals("")
                                        || !utils.isEmpty((String) entry
                                                .getValue())) {
                                    aclNeededMap.put(entry.getKey(),
                                            entry.getValue());
                                    log.debug(entry.getKey() + "::"
                                            + entry.getValue());
                                }
                            }
                            flattenedEntityMap = exemptionForACL(aclNeededMap);
                            log.debug("flattenedmap:" + flattenedEntityMap);
                            collectionsInMap.add(flattenedEntityMap);
                        } else if (eachStorable.getValue().getClass()
                                .getSimpleName().equalsIgnoreCase("arraylist")) {
                            log.debug("Inside ArrayList::::");

                            Object value = eachStorable.getValue();
                            List<HashMap<String, Object>> storableList = (List<HashMap<String, Object>>) value;
                            for (HashMap<String, Object> storable : storableList) {

                                Map<String, String> flattenedEntityMap = new HashMap<String, String>();
                                Map aclNeededMap = new HashMap();
                                aclNeededMap.putAll(returnedMap);
                                HashMap<String, Object> h = (HashMap<String, Object>) storable;
                                for (Entry<String, Object> entry : h.entrySet()) {
                                    if (!((String) entry.getValue()).isEmpty()
                                            || ((String) entry.getValue())
                                                    .equals("")
                                            || !utils.isEmpty((String) entry
                                                    .getValue())) {
                                        aclNeededMap.put(entry.getKey(),
                                                entry.getValue());
                                        log.debug(entry.getKey() + "::"
                                                + entry.getValue());
                                    }
                                }
                                flattenedEntityMap = exemptionForACL(aclNeededMap);
                                log.debug("flattenedmap:" + flattenedEntityMap);
                                collectionsInMap.add(flattenedEntityMap);
                            }
                        }

                    }

                }

            }

            log.debug("Final collectionsInMap:::" + collectionsInMap.toString());
            log.debug("Collection size:" + collectionsInMap.size());
            boolean aclStatus = accessControlService
                    .firePostCollectionsRules(collectionsInMap);
            if (!aclStatus) {

                log.debug("aclstatus is false");
                throw new GenericException(domain, "ERR-ACL-0011",
                        "postFormToACL returns false.");
            }

            log.methodExit(this.getClass().getSimpleName() + ".postCallToACL()");
        } catch (GenericException ge) {
            log.error("In getCallToACL GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In getCallToACL EX:" + ex.getMessage());
            throw new GenericException("ERR-ASP-0002",
                    "postCollectionsToACL failure."
                            + "Input is request and domain.", ex);
        }
    }

    private void postFormToACL(HttpServletRequest request, String domain)
            throws GenericException {
        try {
            log.methodEntry(this.getClass().getSimpleName()
                    + ".postCallToACL()");
            boolean flagAcl = true;

            Map<String, Object> processedMap = processUrlCodedContent(request);
            log.debug("proccessedmapurlcoded:" + processedMap);
            Map returnedMap = utils.getDetailsFromRequest(request, domain);
            Utils utils = Utils.getInstanceof(domain);
            log.debug("before skilacl check");
            flagAcl = utils.isSkipAcl(request);
            log.debug("after skilacl check");
            if (flagAcl) {
                log.debug("before iterating processedMap.");
                for (Entry<String, Object> entry : processedMap.entrySet()) {
                    if (entry.getValue() != null
                            && utils.isEmpty((String) entry.getValue())) {
                        returnedMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Map aclNeededMap = exemptionForACL(returnedMap);
                log.debug("Input to acl.firePostFormRules:" + aclNeededMap);
                boolean aclStatus = accessControlService
                        .firePostFormRules(aclNeededMap);
                log.debug("Output from acl.firePostFormRules:" + aclStatus);
                if (!aclStatus) {
                    log.debug("aclstatus is false. So throwing exceptions.");
                    throw new GenericException(domain, "ERR-ACL-0011",
                            "postFormToACL returns false.");
                }
            }
            log.methodExit(this.getClass().getSimpleName() + ".postCallToACL()");
        } catch (GenericException ge) {
            log.error("In postFormToACL GE:" + ge.getMessage());
            throw ge;
        } catch (Exception ex) {
            log.error("In postFormToACL EX:" + ex.getMessage());
            throw new GenericException("ERR-ASP-0003", "postFormToACL failure."
                    + "Input is request and domain.", ex);
        }
    }

    public void getEntitiesACLEntry(JoinPoint thisJoinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String entity,
            String id, String format) throws GenericException {
        getCallToACL(request, domain);
    }

    public void getEntityACLEntry(JoinPoint thisJoinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String entity,
            String id, String format) throws GenericException {
        getCallToACL(request, domain);
    }

    public void getAllBySearchACLEntry(JoinPoint thisJoinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String format)
            throws GenericException {

        getCallToACL(request, domain);

    }

    public void getEntityBySearchACLEntry(JoinPoint thisJoinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String entity,
            String format, String entityType) throws GenericException {

        getCallToACL(request, domain);

    }

    public void getEntityByFileNameACLEntry(JoinPoint thisJoinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String fileName)
            throws GenericException {
        getCallToACL(request, domain);
    }

    public void deleteEntityByIdACLEntry(JoinPoint thisJoinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String entity,
            String id) throws GenericException {
        getCallToACL(request, domain);
    }

    public Response postEntityByFormACLEntry(ProceedingJoinPoint joinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, UriInfo uriinfo,
            String format) throws GenericException {
        try {
            log.debug("landing in postEntityByFormACLEntry");
            postFormToACL(request, domain);
            return (Response) joinPoint.proceed();
        } catch (GenericException e) {
            log.error(e.getMessage());
            if (format.equals("ClientExtJs")) {
                return throwExtException(e);
            }
            throw e;

        } catch (Exception e) {
            log.error("inside Exception::" + e.getMessage());
            GenericException ge = new GenericException(domain, "ERR-DS-0002");
            if (format.equals("ClientExtJs")) {
                return throwExtException(ge);
            }
            throw ge;
        } catch (Throwable e) {
            log.error("inside Exception::" + e.getMessage());
            GenericException ge = new GenericException(domain, "ERR-DS-0002");
            if (format.equals("ClientExtJs")) {
                return throwExtException(ge);
            }
            throw ge;
        }

    }

    public Response postEntityACLEntry(ProceedingJoinPoint joinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String format,
            String collections) throws GenericException {
        try {
            postCollectionsToACL(domain, request, collections);
            return (Response) joinPoint.proceed();
        } catch (GenericException e) {
            log.error(e.getMessage());
            if (format.equals("ClientExtJs")) {
                return throwExtException(e);
            }
            throw e;

        } catch (Exception e) {
            log.error("inside Exception::" + e.getMessage());
            GenericException ge = new GenericException(domain, "ERR-DS-0002");
            if (format.equals("ClientExtJs")) {
                return throwExtException(ge);
            }
            throw ge;
        } catch (Throwable e) {
            log.error("inside Exception::" + e.getMessage());
            GenericException ge = new GenericException(domain, "ERR-DS-0002");
            if (format.equals("ClientExtJs")) {
                return throwExtException(ge);
            }
            throw ge;
        }

    }

    public Response postEntitiesACLEntry(ProceedingJoinPoint joinPoint,
            final HttpServletRequest request,
            final HttpServletResponse response, String domain, String format,
            String collections) throws GenericException {
        try {
            postCollectionsToACL(domain, request, collections);
            return (Response) joinPoint.proceed();
        } catch (GenericException e) {
            log.error(e.getMessage());
            if (format.equals("ClientExtJs")) {
                return throwExtException(e);
            }
            throw e;

        } catch (Exception e) {
            log.error("inside Exception::" + e.getMessage());
            GenericException ge = new GenericException(domain, "ERR-DS-0002");
            if (format.equals("ClientExtJs")) {
                return throwExtException(ge);
            }
            throw ge;
        } catch (Throwable e) {
            log.error("inside Exception::" + e.getMessage());
            GenericException ge = new GenericException(domain, "ERR-DS-0002");
            if (format.equals("ClientExtJs")) {
                return throwExtException(ge);
            }
            throw ge;
        }
    }

    private String getExtjsResponse(String success, String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", "false");
        jsonObject.put("msg", msg);
        return jsonObject.toString();
    }

    private Response throwExtException(GenericException e) {
        log.error(e.getMessage());
        String strJson = getExtjsResponse("false", e.getMessage());
        return Response.status(400).entity(strJson).build();

    }

    public Map processUrlCodedContent(HttpServletRequest request)
            throws GenericException {
        log.methodEntry("AccessControlAspect.processUrlCodedContent entry");
        Map hUrlEncodedContent = new HashMap();
        List<String> valueList;
        Enumeration names = request.getParameterNames();
        try {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                valueList = Arrays.asList(request.getParameterValues(name));
                String value = "";
                for (String val : valueList) {
                    value = val;
                }
                if (!value.isEmpty() || value != "" || !utils.isEmpty(value)) {
                    log.debug("values are:" + name.toLowerCase() + ":"
                            + value.toLowerCase());
                    hUrlEncodedContent.put(name.toLowerCase(),
                            value.toLowerCase());
                }
            }
            log.debug("processUrlCodedContent returns:" + hUrlEncodedContent);
            log.methodExit(this.getClass().getSimpleName()
                    + ".processUrlCodedContent()");
            return hUrlEncodedContent;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-ASP-0000", this.getClass()
                    .getSimpleName() + ":processUrlCodedContent()", e);
        }
    }
}
*/