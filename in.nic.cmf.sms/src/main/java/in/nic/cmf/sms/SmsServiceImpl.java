package in.nic.cmf.sms;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.Map;

/**
 * SmsServiceImpl class implements CMFService.
 * @author Bala Murugan.S
 * @version 1.0.0
 * @since 12 ,January,2012
 */
@ServiceName ("sms")
public class SmsServiceImpl implements CMFService {

    /**
     * Generic Exception error code.
     */
    private String    err3 = "ERR-SMS-0003";

    private LogTracer log;

    /**
     * SmsService instance variable.
     */
    // private SmsService smsService = SmsService.getInstance();

    /**
     * @see in.nic.cmf.contract.CMFService#add(java.lang.String,
     *      java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return Object
     */
    @Override
    public Map<String, Map<String, Object>> add(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {
        SmsService.getInstanceof(domain).setLog(log);
        return SmsService.getInstanceof(domain).send(parameters);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#deleteByID(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return Object true, if successful
     */
    @Override
    public Map<String, Map<String, Object>> deleteByID(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {

        throw new GenericException(domain, err3);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#deleteByQuery(java.lang.String,
     *      java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return true, if successful
     */
    @Override
    public Map<String, Map<String, Object>> deleteByQuery(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {

        throw new GenericException(domain, err3);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#find(java.lang.String,
     *      java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return the Object
     */
    @Override
    public Map<String, Map<String, Object>> find(final String domain,
            final String entity,
            final Map<String, Map<String, Object>> parameters) {

        throw new GenericException(domain, err3);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#findById(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param id
     *            the id
     * @param parameters
     *            the Servlet Request information
     * @return the Object
     */
    @Override
    public Map<String, Map<String, Object>> findById(final String domain,
            final String entity, final String id,
            final Map<String, Map<String, Object>> parameters) {

        throw new GenericException(domain, err3);
    }

    /**
     * @see in.nic.cmf.contract.CMFService#setLogTracer
     *      (in.nic.cmf.logger.LogTracer)
     * @param arg0
     *            the new LogTracer
     */
    @Override
    public void setLogTracer(final LogTracer log) {
        this.log = log;
    }

    /**
     * @see in.nic.cmf.contract.CMFService#read(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.Map)
     * @param domain
     *            the domain
     * @param entity
     *            the entity
     * @param parameters
     *            the Servlet Request information
     * @return the Object
     */
    @Override
    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, err3);
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
