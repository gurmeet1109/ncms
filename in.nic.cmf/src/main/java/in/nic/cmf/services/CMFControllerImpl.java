package in.nic.cmf.services;

import in.nic.cmf.cache.EHCache;
import in.nic.cmf.contract.CMFController;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.helper.CMFRequestParser;
import in.nic.cmf.helper.CMFResponseBuilder;
import in.nic.cmf.helper.ServiceFactory;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.util.AuthAspectHelper;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class CMFControllerImpl.
 */
@Controller
@Path (CMFControllerImpl.APP_URL)
public class CMFControllerImpl implements CMFController {

    /** The Constant APP_URL. */
    public static final String APP_URL        = "/";

    /** The log. */
    // @Autowired
    public LogTracer           log;

    /** The service factory. */
    // @Autowired
    public ServiceFactory      serviceFactory = new ServiceFactory();

    /** The cmf request. */
    private CMFRequestParser   cmfRequest     = CMFRequestParser.getInstance();

    /** The cmf response. */
    private CMFResponseBuilder cmfResponse    = CMFResponseBuilder
                                                      .getInstance();

    private final void setLogger(String domain) {

        log = new LogTracer(domain, "CMFControllerLog");
        serviceFactory.setLogTracer(log);
        cmfRequest.setLogTracer(new LogTracer(domain, "cmf-request-parser"));
        cmfResponse.setLogTracer(new LogTracer(domain, "cmf-response-builder"));

        BeanFactory beanFactory = new ClassPathXmlApplicationContext(
                "springconfig.xml");
        
        AuthAspectHelper helper = (AuthAspectHelper) beanFactory
                .getBean("authAspectHelper");
        helper.setDomain(domain);
        
        log.debug("after AUTH ASPECT");
        
       /* EHCache ehCacheHelper = (in.nic.cmf.cache.EHCache) beanFactory.getBean("ehCache");
        ehCacheHelper.setDomain(domain);*/
        
        log.debug("after EHCACHE ASPECT");
/*        
        CacheBursterService cacheBurster = (CacheBursterService) beanFactory.getBean("cacheBurster");
        cacheBurster.setDomain(domain);
        log.debug("after CACHEBURSTER ASPECT");*/

    }

    @Override
    public Response deleteById(HttpServletRequest req, HttpServletResponse res,
            UriInfo uriInfo, String service, String domain, String entity,
            String id) throws GenericException {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, "");
            parameters = serviceFactory.getService(domain, service, req)
                    .deleteByID(domain, entity, id, parameters);
            return cmfResponse.build(domain, parameters, "xml");
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response deleteByQuery(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String entity) throws GenericException {
        setLogger(domain);
        throw new GenericException(domain, "ERR-CMF-0002");
    }

    @Override
    public Response getAllBySearch(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String format) throws GenericException {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req).find(
                    domain, null, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response getEntities(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String entity, String format)
            throws GenericException {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req).find(
                    domain, entity, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response getEntity(HttpServletRequest req, HttpServletResponse res,
            UriInfo uriInfo, String service, String domain, String entity,
            String id, String format) throws GenericException {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req)
                    .findById(domain, entity, id, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response getEntityByFileName(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String filename) throws GenericException {
        setLogger(domain);
        throw new GenericException(domain, "ERR-CMF-0002");
    }

    @Override
    public Response getEntityBySearch(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String entity, String format)
            throws GenericException {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req).find(
                    domain, entity, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response postEntities(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String format) throws GenericException {
        try {
            setLogger(domain);
            log.debug(service + " request in postEntities");
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req).add(
                    domain, null, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response postEntity(HttpServletRequest req, HttpServletResponse res,
            UriInfo uriInfo, String service, String domain, String entity,
            String id, String format) throws GenericException {
        setLogger(domain);
        log.debug(service + " request in postEntity");
        throw new GenericException(domain, "ERR-CMF-0002");
    }

    @Override
    public Response postEntityByForm(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String entity, String format)
            throws GenericException {
        try {
            setLogger(domain);
            log.debug(service + " request in postEntityByForm");
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            log.debug("\nService : " + service + ".... \nParameters...."
                    + parameters);
            parameters = serviceFactory.getService(domain, service, req).add(
                    domain, entity, parameters);
            log.debug("Response before build response .... " + parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response getEntitiesByCollections(HttpServletRequest req,
            HttpServletResponse res, UriInfo uriInfo, String service,
            String domain, String entity, String format)
            throws GenericException {
        try {
            setLogger(domain);
            log.debug(service + " request in getEntitiesByCollections");
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            log.debug("after parse:" + parameters);
            parameters = serviceFactory.getService(domain, service, req).read(
                    domain, entity, parameters);
            log.debug("after read:" + parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response postManageEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format) {
        try {
            setLogger(domain);
            log.debug(service + " request in postManageEntity");
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req)
                    .addManage(domain, service, entity, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }
    }

    @Override
    public Response getManageEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format) {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, format);
            parameters = serviceFactory.getService(domain, service, req)
                    .findManage(domain, service, entity, parameters);
            return cmfResponse.build(domain, parameters, format);
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }

    }

    @Override
    public Response deleteManageEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity) {
        try {
            setLogger(domain);
            Map<String, Map<String, Object>> parameters = cmfRequest.parse(
                    domain, req, uriInfo, "");
            parameters = serviceFactory.getService(domain, service, req)
                    .deleteManage(domain, service, entity, parameters);
            return cmfResponse.build(domain, parameters, "xml");
        } catch (GenericException ge) {
            log.error("GenericException throws : " + ge.getMessage());
            throw ge;
        }

    }
}
