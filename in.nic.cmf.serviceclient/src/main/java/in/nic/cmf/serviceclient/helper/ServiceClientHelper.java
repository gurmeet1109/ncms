package in.nic.cmf.serviceclient.helper;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.util.Utils;

import java.util.HashMap;

import org.apache.http.entity.ByteArrayEntity;

public class ServiceClientHelper {

    // private Utils utils = Utils.getInstance();
    // private final static ServiceClientHelper scHelper = new
    // ServiceClientHelper();
    private Utils                                       utils;
    private static HashMap<String, ServiceClientHelper> hashServiceClientHelper = new HashMap<String, ServiceClientHelper>();
    private String                                      domain;

    // public static ServiceClientHelper getInstance() {
    // return scHelper;
    // }
    private ServiceClientHelper(String domain) {
        this.domain = domain;
        utils = Utils.getInstanceof(domain);
    }

    public static ServiceClientHelper getInstanceof(String domain) {
        if (!hashServiceClientHelper.containsKey(domain)) {
            hashServiceClientHelper
                    .put(domain, new ServiceClientHelper(domain));
        }
        return hashServiceClientHelper.get(domain);
    }

    public void isValidParams(Object... params) throws GenericException {
        try {
            for (Object param : params) {
                if (param instanceof String) {
                    boolean status = utils.isEmpty(param.toString());
                    if (status) {
                        throw new GenericException(domain, "ERR-SC-0003",
                                "IllegalArgumentException", param.toString());
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("error:" + ex.getMessage());
        }
    }

    public ByteArrayEntity getStreamEntity(String collections,
            String contentType) throws GenericException {
        try {
            ByteArrayEntity byteEntity = new ByteArrayEntity(
                    collections.getBytes());
            return byteEntity;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-SC-0000", this.getClass()
                    .getSimpleName() + ":getStreamEntity()", "", e);
        }
    }

    public boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return ((String) value).isEmpty();
        }
        return false;
    }

}
