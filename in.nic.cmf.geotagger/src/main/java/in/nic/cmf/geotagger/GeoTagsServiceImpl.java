package in.nic.cmf.geotagger;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class GeoTagsServiceImpl.
 */
@ServiceName ("geotagger")
public class GeoTagsServiceImpl implements CMFService {

    private GeoTagsInfo geoinfo;
    private LogTracer   log;

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.contract.CMFService#setLogTracer(in.nic.cmf.logger.LogTracer)
     */
    public void setLogTracer(LogTracer logtracer) {
        this.log = logtracer;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#find(java.lang.String,
     * java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        geoinfo = GeoTagsInfo.getInstanceof(domain);
        geoinfo.setLogTracer(log);
        Map<String, Object> params = (HashMap<String, Object>) queryParams.get(
                "input").get("queryParams");
        String city = (String) params.get("city");
        String ip = (String) params.get("ip");
        log.debug("City = " + city + " :: Ip = " + ip);
        params = (HashMap<String, Object>) queryParams.get("input").get(
                "userContext");
        log.debug("UserContext -- " + params);
        Map<String, Object> response = new HashMap<String, Object>();
        String statusCode = "400";
        if (!(isEmpty(city))) {
            params = geoinfo.processByCity(city);
            statusCode = "200";
        } else {
            if (isEmpty(ip)) {
                ip = (String) params.get("requester");
            }
            params = geoinfo.processByIp(ip);
            statusCode = "200";
        }
        response.put("content", params);
        response.put("statusCode", statusCode);
        queryParams.put("output", response);
        return queryParams;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#add(java.lang.String,
     * java.lang.String, java.util.HashMap, boolean)
     */
    @Override
    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        geoinfo = GeoTagsInfo.getInstanceof(domain);
        geoinfo.setLogTracer(log);
        HashMap<String, Object> input = (HashMap<String, Object>) queryParams
                .get("input");
        String collections = (String) input.get("content");
        log.debug("content -- " + collections);
        queryParams.put("output", geoinfo.updateData(collections));
        return queryParams;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#deleteByID(java.lang.String,
     * java.lang.String, java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        throw new GenericException(domain, "ERR-GEO-0005");
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#deleteByQuery(java.lang.String,
     * java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        throw new GenericException(domain, "ERR-GEO-0005");
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.contract.CMFService#findById(java.lang.String,
     * java.lang.String, java.lang.String, java.util.HashMap)
     */
    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams)
            throws GenericException {
        throw new GenericException(domain, "ERR-GEO-0005");
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

    /**
     * ss
     * Checks if is empty.
     * @param value
     *            the value
     * @return true, if is empty
     */
    private boolean isEmpty(String value) {
        if (value != null) {
            return value.isEmpty(); // true if length() is 0, otherwise false
        }
        return true;
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            String misc, Map<String, Map<String, Object>> parameters)
            throws GenericException {
        throw new GenericException(domain, "ERR-GEO-0005");
    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-GEO-0005");
    }
}
