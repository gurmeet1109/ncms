package in.nic.cmf.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

// TODO: Auto-generated Javadoc
/**
 * The Interface CMFController.
 */
public interface CMFController {

    /**
     * Gets the entity by search.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param format
     *            the format
     * @return the entity by search
     */
    @GET
    @Path ("{service}/{domain}/{entity}/search")
    @Produces ({"application/xml;charset=UTF-8",
            "application/json;charset=UTF-8" })
    Response getEntityBySearch(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Gets the all by search.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param format
     *            the format
     * @return the all by search
     */
    @GET
    @Path ("{service}/{domain}/search")
    @Produces ({"application/xml;charset=UTF-8",
            "application/json;charset=UTF-8" })
    Response getAllBySearch(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Gets the entity.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param format
     *            the format
     * @return the entity
     */
    @GET
    @Path ("{service}/{domain}/{entity}/{id}")
    @Produces ({"application/xml;charset=UTF-8",
            "application/json;charset=UTF-8" })
    Response getEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity, @PathParam ("id") String id,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Gets the entity by file name.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param filename
     *            the filename
     * @return the entity by file name
     */
    @GET
    @Path ("{service}/{domain}/media/{filename}{param:.*}")
    Response getEntityByFileName(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("filename") String filename);

    /**
     * Gets the entities.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param format
     *            the format
     * @return the entities
     */
    @GET
    @Path ("{service}/{domain}/{entity}")
    @Produces ({"application/xml;charset=UTF-8",
            "application/json;charset=UTF-8" })
    Response getEntities(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Gets the entity id's by collections in POST method.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param format
     *            the format
     * @return the entities
     */
    @POST
    @Path ("{service}/{domain}/{entity}/collections")
    @Produces ({"application/xml;charset=UTF-8",
            "application/json;charset=UTF-8" })
    @Consumes ({"application/json", "application/xml" })
    Response getEntitiesByCollections(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Post entities.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param format
     *            the format
     * @return the response
     */
    @POST
    @Path ("{service}/{domain}")
    @Consumes ({"application/json", "application/xml" })
    Response postEntities(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Post entity by form.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param format
     *            the format
     * @return the response
     */
    @POST
    @Path ("{service}/{domain}/{entity}")
    @Consumes ({"application/x-www-form-urlencoded", "multipart/form-data" })
    Response postEntityByForm(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("ClientExtJs") String format);

    /**
     * Post entity.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param format
     *            the format
     * @return the response
     */
    @POST
    @Path ("{service}/{domain}/{entity}/{id}")
    @Consumes ({"application/json", "application/xml" })
    Response postEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity, @PathParam ("id") String id,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Delete by id.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @return the response
     */
    @DELETE
    @Path ("{service}/{domain}/{entity}/{id}")
    Response deleteById(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity, @PathParam ("id") String id);

    /**
     * Delete by query.
     * @param req
     *            the request
     * @param res
     *            the response
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @return the response
     */
    @DELETE
    @Path ("{service}/{domain}/{entity}")
    Response deleteByQuery(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity);

    /**
     * Post manage entity.
     * @param req
     *            the req
     * @param res
     *            the res
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param format
     *            the format
     * @return the response
     */
    @POST
    @Path ("{service}/{domain}/management/{entity}")
    @Consumes ({"application/json", "application/xml" })
    Response postManageEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Gets the manage entity.
     * @param req
     *            the req
     * @param res
     *            the res
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param format
     *            the format
     * @return the manage entity
     */
    @GET
    @Path ("{service}/{domain}/management/{entity}")
    @Produces ({"application/xml;charset=UTF-8",
            "application/json;charset=UTF-8" })
    Response getManageEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity,
            @QueryParam ("format") @DefaultValue ("xml") String format);

    /**
     * Delete manage entity.
     * @param req
     *            the req
     * @param res
     *            the res
     * @param uriInfo
     *            the uri info
     * @param service
     *            the service
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @return the response
     */
    @DELETE
    @Path ("{service}/{domain}/management/{entity}")
    Response deleteManageEntity(@Context HttpServletRequest req,
            @Context HttpServletResponse res, @Context UriInfo uriInfo,
            @PathParam ("service") String service,
            @PathParam ("domain") String domain,
            @PathParam ("entity") String entity);
}
