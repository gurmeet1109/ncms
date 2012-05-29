package in.nic.cmf.geotagger;

import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

/**
 * The Class GeoTagsInfo.
 */
public class GeoTagsInfo {

    /** The log. */
    private LogTracer                           log;
    private static PropertiesUtil               propUtil;
    private String                              domain;
    private static HashMap<String, GeoTagsInfo> hashGeoTagsInfo = new HashMap<String, GeoTagsInfo>();

    public GeoTagsInfo(String domain) {
        this.domain = domain;
        propUtil = PropertiesUtil.getInstanceof(domain, "geotagger");

    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    public static GeoTagsInfo getInstanceof(String domain) {
        if (!hashGeoTagsInfo.containsKey(domain)) {
            hashGeoTagsInfo.put(domain, new GeoTagsInfo(domain));
        }
        return hashGeoTagsInfo.get(domain);
    }

    /**
     * Process by city.
     * @param cities
     *            the cities
     * @return the map
     * @throws GenericException
     *             the generic exception
     */
    public HashMap<String, Object> processByCity(final String cities)
            throws GenericException {

        String pattern = propUtil.getProperty("cityPattern");
        String customizedCity = cities.replaceAll(pattern, "");
        if (customizedCity.length() == 0) {
            log.error("city info missing.");
            throw new GenericException(domain, "ERR-GEO-0002");
        }
        String citiesArray[] = cities.split(",");
        ArrayList<Object> locations = new ArrayList<Object>();
        for (String city : citiesArray) {
            HashMap<String, Object> location = new HashMap<String, Object>();
            location.put("City", city.trim().toLowerCase());
            location.put("Ip", "");
            locations.add(location);
        }
        return getCollections((ArrayList<Object>) locations);
    }

    /**
     * Gets the collections.
     * @param dataList
     *            the data list
     * @return the collections
     */
    private HashMap<String, Object> getCollections(ArrayList<Object> dataList) {
        HashMap<String, Object> collection = new HashMap<String, Object>();
        HashMap<String, Object> geotags = new HashMap<String, Object>();
        HashMap<String, Object> locations = new HashMap<String, Object>();
        if (dataList.size() > 1) {
            ArrayList<Object> locationList = new ArrayList<Object>();
            for (Object city : dataList) {
                locationList
                        .add(getLocationDetails((HashMap<String, Object>) city));
                locations.put("Location", locationList);
            }
            geotags.put("GeoTags", locations);
        } else {
            locations.put("Location",
                    getLocationDetails((Map<String, Object>) dataList.get(0)));
            geotags.put("GeoTags", locations);
        }
        collection.put("Collections", geotags);
        return collection;
    }

    /**
     * Process by ip.
     * @param ip
     *            the ip
     * @return the map
     * @throws GenericException
     *             the generic exception
     */
    public HashMap<String, Object> processByIp(String ip)
            throws GenericException {
        String ipArray[] = ip.split(",");
        if (ip.contains(",")) {
            ipArray = ip.split(",");
        } else {
            ipArray = new String[1];
            ipArray[0] = ip.trim().toLowerCase();
        }
        return getCollections(getCitiesByIp(ipArray));

    }

    /**
     * Update data.
     * @param collection
     *            the collection
     * @return the map
     * @throws GenericException
     *             the generic exception
     */
    public HashMap<String, Object> updateData(String collection)
            throws GenericException {

        Convertor xmlconvertor = FormatXml.getInstanceof(domain);
        HashMap<String, Object> xmlMap = xmlconvertor.in(collection);
        HashMap<String, Object> collectionMap = (HashMap<String, Object>) xmlMap
                .get("Collections");
        try {
            for (Map.Entry<String, Object> entityType : collectionMap
                    .entrySet()) {
                if (entityType.getValue() instanceof HashMap<?, ?>) {
                    HashMap<String, Object> entityMap = (HashMap<String, Object>) entityType
                            .getValue();
                    if (entityMap.containsKey("GeoTags")) {
                        entityMap
                                .put("GeoTags",
                                        updateGeoTagInfo((HashMap<String, Object>) entityMap
                                                .get("GeoTags")));
                    }
                } else if (entityType.getValue() instanceof ArrayList<?>) {
                    for (Object obj : (ArrayList<?>) entityType.getValue()) {
                        HashMap<String, Object> entityMap = (HashMap<String, Object>) obj;
                        entityMap
                                .put("GeoTags",
                                        updateGeoTagInfo((HashMap<String, Object>) entityMap
                                                .get("GeoTags")));
                    }
                }
            }
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.toString());
            throw new GenericException(domain, "ERR-GEO-0000", this.getClass()
                    .getSimpleName() + ".updateData()", e);
        }
        return buildResponseMap(xmlMap);
    }

    /**
     * Update geo tag info.
     * @param entity
     *            the entity
     * @return the object
     */
    private Object updateGeoTagInfo(HashMap<String, Object> entity) {
        if (entity.get("Location") instanceof HashMap<?, ?>) {
            HashMap<String, Object> locations = (HashMap<String, Object>) entity
                    .get("Location");
            locations.put("Ip", "");
            if (isEmpty(locations.get("City"))) {
                return entity;
            }
            locations.put("City", locations.get("City").toString()
                    .toLowerCase());
            return getLocationDetails(locations);
        } else if (entity.get("Location") instanceof ArrayList<?>) {
            ArrayList<HashMap<String, Object>> locationList = new ArrayList<HashMap<String, Object>>();
            ArrayList<HashMap<String, Object>> locations = (ArrayList<HashMap<String, Object>>) entity
                    .get("Location");
            for (HashMap<String, Object> location : locations) {
                location.put("Ip", "");
                if (isEmpty(location.get("City"))) {
                    return entity;
                }
                location.put("City", location.get("City").toString()
                        .toLowerCase());
                locationList
                        .add((HashMap<String, Object>) getLocationDetails(location));
            }
            return locationList;
        }
        return entity;
    }

    /**
     * Gets the cities by ip.
     * @param ips
     *            the ips
     * @return the cities by ip
     * @throws GenericException
     *             the generic exception
     */
    public ArrayList<Object> getCitiesByIp(String[] ips)
            throws GenericException {
        ArrayList<Object> loctionList = new ArrayList<Object>();
        try {
            String path = getResourcePath() + "GeoLiteCity.dat";
            log.info("GeoLiteCity.dat - location : " + path);
            LookupService lookup = new LookupService(path,
                    LookupService.GEOIP_MEMORY_CACHE);
            final String ipPattern = propUtil.getProperty("ipPattern");
            Pattern pattern = Pattern.compile(ipPattern);
            for (String ip : ips) {
                HashMap<String, Object> locationsMap = new HashMap<String, Object>();
                if (!(pattern.matcher(ip).matches())) {
                    log.error("Invalid IP(s) found");
                    throw new GenericException(domain, "ERR-GEO-0003");
                }
                Location loc = lookup.getLocation(ip);
                String city = loc.city.toLowerCase();
                locationsMap.put("City", city);
                locationsMap.put("Ip", ip);
                loctionList.add(locationsMap);
            }
            return loctionList;
        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            throw new GenericException(domain, "ERR-GEO-0003");
        } catch (Exception e) {
            log.error(e.toString());
            throw new GenericException(domain, "ERR-GEO-0004");
        }
    }

    /**
     * Gets the resource path.
     * @return the resource path
     */
    private String getResourcePath() {
        // PropertiesUtil proputil = new PropertiesUtil("resource");
        PropertiesUtil proputil = PropertiesUtil.getInstanceof(domain,
                "resource");
        String resourcePath = proputil.getProperty("location");
        if (!resourcePath.endsWith("/")) {
            resourcePath += "/";
        }
        return resourcePath;
    }

    /**
     * Gets the location details.
     * @param location
     *            the location
     * @return the location details
     * @throws GenericException
     *             the generic exception
     */
    public final Map<String, Object> getLocationDetails(
            Map<String, Object> location) throws GenericException {
        Connection connection = null;
        try {
            if (connection == null) {
                Class.forName("org.sqlite.JDBC");
                String path = getResourcePath() + "geoipcity.db";
                log.info("geoipcity.db - location--" + path);
                connection = DriverManager.getConnection("jdbc:sqlite:" + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEO-0003");
        }

        try {
            Statement statement = connection.createStatement();
            String sqlStr = "SELECT * FROM citytable WHERE lower(city)='"
                    + location.get("City") + "'";
            log.debug("SQL Query : " + sqlStr);
            ResultSet resultSet = statement.executeQuery(sqlStr);

            while (resultSet.next()) {
                location.put("City", resultSet.getString("city"));
                location.put("District", resultSet.getString("district"));
                location.put("State", resultSet.getString("state"));
                location.put("Country", resultSet.getString("country"));
                location.put("Latitude", resultSet.getString("Latitude"));
                location.put("Longitude", resultSet.getString("longitude"));
                location.put("Coords", resultSet.getString("Latitude") + ","
                        + resultSet.getString("longitude"));

            }

        } catch (Exception e) {
            // e.printStackTrace();
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEO-0003");
        }

        return location;
    }

    /**
     * Builds the response map.
     * @param respObject
     *            the resp object
     * @return the map
     */
    private HashMap<String, Object> buildResponseMap(
            HashMap<String, Object> respObject) {
        String responseXML = (String) FormatXml.getInstanceof(domain).out(
                (HashMap<String, Object>) respObject);
        HashMap<String, Object> response = new HashMap<String, Object>();
        response.put("statusCode", "200");
        response.put("content", responseXML);
        response.put("format", "application/xml");
        return response;
    }

    /**
     * Checks if is empty.
     * @param value
     *            the value
     * @return true, if is empty
     */
    private boolean isEmpty(Object value) {
        if (value != null) {
            return value.toString().isEmpty();
        }
        return true;
    }
}
