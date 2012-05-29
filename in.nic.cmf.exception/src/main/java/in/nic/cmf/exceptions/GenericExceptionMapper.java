package in.nic.cmf.exceptions;

import in.nic.cmf.logger.LogTracer;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The Class GenericExceptionMapper.
 */
@Provider
public class GenericExceptionMapper implements
        ExceptionMapper<GenericException> {

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    public final Response toResponse(final GenericException exception) {
        String domain = exception.getDomain();
        LogTracer log = new LogTracer(domain, "ExceptionLogger");
        if (exception != null) {
            log.error("Exception Details : " + exception.getExceptionDetails());
        } else {
            log.error("no exception object passed");
        }
        return Response.status(exception.getHttpStatusCode())
                .entity(exception.getMessage()).type("text/plain").build();
    }
}
