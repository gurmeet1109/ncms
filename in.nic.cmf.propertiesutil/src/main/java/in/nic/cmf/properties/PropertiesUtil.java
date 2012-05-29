package in.nic.cmf.properties;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.contract.PropertyReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * I. Justin Narbert Date : Jan 17, 2012 Purpose : dependency of exception and
 * logger removed. The Class PropertiesUtil.
 */

public class PropertiesUtil implements PropertyReader {

    /** The properties. */
    private Map<String, String>                    properties          = new HashMap<String, String>();

    /** The service name. */
    private String                                 serviceName         = null;

    /** The location. */
    // private String location = "/opt/cmf/properties/";
    private String                                 location            = "/opt/cmf/domains/";
    /** The resource service name. */
    private String                                 resourceServiceName = "resource";

    /** The resource path. */
    private String                                 resourcePath        = "";

    // LogTracer log = new LogTracer("propertyread");

    private LogTracer                              log                 = null;

    // private PropertiesUtil propertiesUtil = new PropertiesUtil();

    // /**
    // * Instantiates a new properties util.
    // */
    // private PropertiesUtil() {
    //
    // }
    private static HashMap<String, PropertiesUtil> proputils           = new HashMap<String, PropertiesUtil>();

    public static PropertiesUtil getInstanceof(String domain, String serviceName) {

        if (domain == null || domain == "") {
            domain = "default";
        }
        String hashkey = domain + serviceName;
        if (!proputils.containsKey(hashkey)) {
            proputils.put(hashkey, new PropertiesUtil(domain, serviceName));
        }
        return proputils.get(hashkey);
    }

    public static PropertiesUtil getInstanceof(String domain,
            String serviceName, List<String> propFileList) {
        Collections.sort(propFileList);
        String hashkey = domain + serviceName;
        if (propFileList.size() == 0) return null;

        for (String val : propFileList) {
            hashkey += val;
        }
        if (!proputils.containsKey(hashkey)) {
            proputils.put(hashkey, new PropertiesUtil(domain, serviceName,
                    propFileList));
        }
        return proputils.get(hashkey);
    }

    /**
     * Instantiates a new properties util.
     */
    private PropertiesUtil(String domain) {
        this.log = new LogTracer(domain, "propertyread");
        setLocation(domain, this.location);
        // System.out.println("path:" + getLocation() + "config.properties");
        loadProperties(getLocation() + "config.properties");
        setResourcePath(properties.get("defaultResourcePath"));
        // Common resource available location
        loadResourceProperty(getResourceServiceName());
        properties.clear();
        // setLocation(getLocation() + properties.get("buildfor"));
    }

    /**
     * Instantiates a new properties util.
     * @param serviceName
     *            the service name
     */
    private PropertiesUtil(String domain, String serviceName) {
        this(domain);
        setServiceName(serviceName);
        loadProperties(getLocation() + getServiceName() + "/config.properties");
    }

    /**
     * Instantiates a new properties util.
     * @param serviceName
     *            the service name
     * @param propFileList
     *            the prop file list
     */
    private PropertiesUtil(String domain, String serviceName,
                           List<String> propFileList) {
        this(domain);
        loadResourceProperty(getResourceServiceName());
        setServiceName(serviceName);
        loadProperties(propFileList);
    }

    /**
     * Load resource property.
     * @param resourceServiceName
     *            the resource service name
     */
    private void loadResourceProperty(String resourceServiceName) {
        setServiceName(resourceServiceName);
        // System.out.println("serivicepath: " + getLocation() +
        // getServiceName()
        // + "/config.properties");
        loadProperties(getLocation() + getServiceName() + "/config.properties");
    }

    /**
     * Load properties.
     * @param propFileList
     *            the prop file list
     */
    private void loadProperties(List<String> propFileList) {
        for (String propertyFileName : propFileList) {
            loadProperties(getLocation() + getServiceName() + "/"
                    + propertyFileName + ".properties");
        }
    }

    /**
     * Load properties.
     * @param propertyFileName
     *            the property file name
     */
    private void loadProperties(String propertyFileName) {
        System.out.println("filename:" + propertyFileName);
        Properties pro = new Properties();
        boolean isResourceAvailable = false;
        Exception exception = null;
        try {
            pro.load(new FileInputStream(propertyFileName));
            propertiesToMap(pro);
            isResourceAvailable = true;
        } catch (FileNotFoundException e) {
            exception = e;
        } catch (IOException e) {
            exception = e;
        } finally {
            if (!isEmpty(getServiceName())) {
                if (getServiceName().equals(getResourceServiceName())) {
                    if (isResourceAvailable) {
                        setResourcePath(properties.get("location"));
                    }
                } else {
                    if (!isResourceAvailable) { // not a resource service
                        log.error("Configuration file \"" + propertyFileName
                                + "\" is un-available. : "
                                + exception.getMessage());
                    }
                }
            }
        }
        properties.put("location", getResourcePath());
    }

    /**
     * Properties to map.
     * @param props
     *            the props
     */
    private void propertiesToMap(Properties props) {
        Enumeration<Object> propKeys = props.keys();
        while (propKeys.hasMoreElements()) {
            String s = (String) propKeys.nextElement();
            properties.put(s, props.getProperty(s));
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#getProperty(java.lang.String
     * )
     */
    @Override
    public String getProperty(String key) {
        return get(key);
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#setProperty(java.lang.String
     * , java.lang.String)
     */
    @Override
    public void setProperty(String key, String value) {
        properties.put(key, value);
    }

    /**
     * Sets the service name.
     * @param serviceName
     *            the new service name
     */
    private void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets the service name.
     * @return the service name
     */
    private String getServiceName() {
        return serviceName;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.properties.contract.PropertyReader#get(java.lang.String)
     */
    @Override
    public String get(String key) {
        String value = properties.get(key);
        if (isEmpty(value)) {
            log.info(getServiceName() + "." + key + " value is NULL.");
        }
        return value;
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#getAsInt(java.lang.String)
     */
    @Override
    public int getAsInt(String key) {
        String value = get(key);
        try {
            if (!isEmpty(value)) {
                return Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            log.error(getServiceName() + "." + key + " value : " + value
                    + " is not a integer format.");
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#getAsBoolean(java.lang.
     * String)
     */
    @Override
    public boolean getAsBoolean(String key) {
        String value = get(key);
        if (!isEmpty(value)) {
            if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")
                    || value.equals("1")) {
                return true;
            } else if (value.equalsIgnoreCase("false")
                    || value.equalsIgnoreCase("no") || value.equals("0")) {
                return false;
            }
        }
        log.error(getServiceName() + "." + key + " value : " + value
                + " is not a boolean format.");
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#getAsLong(java.lang.String)
     */
    @Override
    public long getAsLong(String key) {
        String value = get(key);
        try {
            if (!isEmpty(value)) {
                return Long.parseLong(value);
            }
        } catch (NumberFormatException e) {
            log.error(getServiceName() + "." + key + " value : " + value
                    + " is not a long format.");
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#getAsDouble(java.lang.String
     * )
     */
    @Override
    public double getAsDouble(String key) {
        String value = get(key);
        try {
            if (!isEmpty(value)) {
                return Double.parseDouble(value);
            }
        } catch (NumberFormatException e) {
            log.error(getServiceName() + "." + key + " value : " + value
                    + " is not a double format.");
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.properties.contract.PropertyReader#getAsFloat(java.lang.String
     * )
     */
    @Override
    public float getAsFloat(String key) {
        String value = get(key);
        try {
            if (!isEmpty(value)) {
                return Float.parseFloat(value);
            }
        } catch (NumberFormatException e) {
            log.error(getServiceName() + "." + key + " value : " + value
                    + " is not a float format.");
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.properties.contract.PropertyReader#getAsMap()
     */
    @Override
    public Map<String, String> getAsMap() {
        return properties;
    }

    /**
     * Sets the location.
     * @param location
     *            the new location
     */
    private void setLocation(String domain, String location) {
        if (isEmpty(domain)) {
            domain = "default";
        }
        this.location = getValidPath(location + domain + "/properties/");
    }

    /**
     * Gets the location.
     * @return the location
     */
    private String getLocation() {
        return location;
    }

    /**
     * Gets the resource path.
     * @return the resource path
     */
    private String getResourcePath() {
        return resourcePath;
    }

    /**
     * Sets the resource path.
     * @param resourcePath
     *            the new resource path
     */
    private void setResourcePath(String resourcePath) {
        this.resourcePath = getValidPath(resourcePath);
    }

    /**
     * Gets the valid path.
     * @param resourcePath
     *            the resource path
     * @return the valid path
     */
    private String getValidPath(String resourcePath) {
        if ((!isEmpty(resourcePath)) && (!resourcePath.endsWith("/"))) {
            resourcePath += "/";
        }
        return resourcePath;
    }

    /**
     * Checks if is empty.
     * @param value
     *            the value
     * @return true, if is empty
     */
    private boolean isEmpty(String value) {
        if (value == null) return true;
        value = value.trim();
        return value.isEmpty(); // true if length() is 0, otherwise false
    }

    /**
     * Gets the resource service name.
     * @return the resource service name
     */
    public String getResourceServiceName() {
        return resourceServiceName;
    }

    // /**
    // * The main method.
    // *
    // * @param args
    // * the arguments
    // */
    // public static void main(String args[]) {
    //
    // System.out.println("***** LDAP ******");
    // PropertiesUtil prop = new PropertiesUtil("ldap");
    // System.out.println(prop.get("ldapip"));
    // System.out.println("Location : " + prop.get("location"));
    //
    // System.out.println("***** REsources ******");
    // prop = new PropertiesUtil("resources");
    // System.out.println(prop.get("ldapip"));
    // System.out.println("Location : " + prop.get("location"));
    //
    // System.out.println("***** Auth ******");
    // prop = new PropertiesUtil("auth");
    // System.out.println(prop.get("ldapurl"));
    // System.out.println("Location : " + prop.get("location"));
    //
    // System.out.println("***** Util ******");
    // List<String> propFileList = new ArrayList<String>();
    // propFileList.add("dynamicauth");
    // propFileList.add("mimedetails");
    // propFileList.add("cookie");
    // propFileList.add("demo");
    //
    // prop = new PropertiesUtil("util", propFileList);
    // System.out.println("CookieExpiry : " + prop.getAsInt("CookieExpiry"));
    // System.out.println("authURL : " + prop.get("authURL"));
    // System.out.println("CookieDomain : " + prop.get("CookieDomain"));
    // System.out.println("unavailable : " + prop.get("unavailable"));
    // System.out.println("application/envoy : "
    // + prop.get("application/envoy"));
    // System.out.println("application/envoy : "
    // + prop.get("application/envoy"));
    //
    // }
}
