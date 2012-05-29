package in.nic.cmf.searchengine;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.core.MultivaluedMap;

/**
 * SolrQueryValidation class will validate the the query parameter. It will be
 * used from SearchAPIResource to validate the the query parameter.
 * @author Kalidoss.M
 * @author Subodh C. Joshi
 */

public class SolrQueryValidation {
    // public static final SolrQueryValidation SOLRQUERYVALIDATION = new
    // SolrQueryValidation();
    private static HashMap<String, SolrQueryValidation> hashSolrQueryValidation = new HashMap<String, SolrQueryValidation>();
    private String                                      domain;

    private SolrQueryValidation(String domain) {
        this.domain = domain;
    }

    // public static SolrQueryValidation getInstance() {
    // return SOLRQUERYVALIDATION;
    // }

    public LogTracer log;

    public static SolrQueryValidation getInstance(String domain) {
        if (!hashSolrQueryValidation.containsKey(domain)) {
            hashSolrQueryValidation
                    .put(domain, new SolrQueryValidation(domain));
        }
        return hashSolrQueryValidation.get(domain);
    }

    public boolean checkingUrlParam(final HashMap<String, Object> dataMap)
            throws GenericException {
        if (!validateNotNull(dataMap)) {
            log.debug("Some query params value is null or empty");
            return false;
        } else {
            String value = null;
            Set<String> keySet = dataMap.keySet();
            Iterator<String> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                String[] values = ((String) dataMap.get(key)).split(",");
                value = values[0];
                if (key.toString().toLowerCase().equals(ServiceConstants.LIMIT)
                        && value != null) {
                    if (!containsOnlyNumbers(value, false)) {
                        return false;
                    }
                } else if (key.toString().toLowerCase()
                        .equals(ServiceConstants.ORDER_BY_DIR)) {
                    if (value.toLowerCase().equals(ServiceConstants.ASCENDING)
                            || value.toLowerCase().equals(
                                    ServiceConstants.DESCENDING)) {// no
                                                                   // function
                                                                   // given here
                    } else {
                        return false;
                    }
                } else if (key.toString().toLowerCase()
                        .equals(ServiceConstants.OFFSET)) {
                    if (!containsOnlyNumbers(value, false)) {
                        return false;
                    }
                } else if (key.toString().toLowerCase()
                        .equals(ServiceConstants.LATITUDE)) {
                    if (!containsOnlyNumbers(value, true)) {
                        return false;
                    }
                } else if (key.toString().toLowerCase()
                        .equals(ServiceConstants.LONGITUDE)) {
                    if (!containsOnlyNumbers(value, true)) {
                        return false;
                    }
                } else if (key.toString().toLowerCase()
                        .equals(ServiceConstants.COND)) {
                    if (value.toUpperCase().contentEquals(
                            ServiceConstants.CONDAND)
                            || value.toUpperCase().contentEquals(
                                    ServiceConstants.CONDOR)) {
                        return true;
                    } else {
                        return false;
                    }

                } else if (key.toString().toLowerCase()
                        .equals(ServiceConstants.RADIUS)) {
                    if (!containsOnlyNumbers(value, false)
                            || Integer.parseInt(value) < 1
                            || Integer.parseInt(value) > Integer
                                    .parseInt(ServiceConstants.RADIUSMAX)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * Doing the validation for null for query.
     * @param multiVmap
     *            hold map of Url parameters which is a type of
     *            {@link MultivaluedMap} class.
     * @return true or false which is type of {java.lang.Boolean} class.
     */

    public boolean validateNotNull(final HashMap<String, Object> dataMap) {
        // Set<String> keySet = dataMap.keySet();
        Iterator<Entry<String, Object>> keyIterator = dataMap.entrySet()
                .iterator();
        while (keyIterator.hasNext()) {
            Entry<String, Object> keyEntry = keyIterator.next();
            String key = keyEntry.getKey();
            if (key.compareTo(ServiceConstants.Q) != 0) {
                String value = (String) keyEntry.getValue();
                if (value.isEmpty()) {
                    return false;
                }
                /*
                 * while (valuesIterator.hasNext()) {
                 * if (valuesIterator.next().toString().isEmpty()) {
                 * return false;
                 * }
                 * }
                 */
            }
        }
        return true;
    }

    /**
     * It is doing the check for valid number or not.
     * @param str
     *            hold the value which is type of {@link java.util.String}
     *            class.
     * @return true or false which is type of {java.lang.Boolean} class .
     * @throws GenericException
     */

    public boolean containsOnlyNumbers(final String str, final boolean minusCond) {
        /** It can't contain only numbers if it's null or empty... */
        if (str == null || str.length() == 0) {
            log.debug("key is either null or empty - returning false");
            return false;
        }

        for (int i = 0; i < str.length(); i++) {

            /** If we find a non-digit character we return false. */
            if (!Character.isDigit(str.charAt(i))) {

                /**
                 * checking for not digit and hiphen character
                 */
                if (!(minusCond && str.charAt(i) == '-' || str.charAt(i) == '.')) {
                    log.debug("negative number - returning false");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * It is doing the check to validate the float number.
     * @param str
     *            hold the value which is type of {@link java.util.String}
     *            class.
     * @return true or false which is type of {java.lang.Boolean} class .
     * @throws GenericException
     */

    public boolean containsOnlyFloat(final String str) {
        /** It can't contain only numbers if it's null or empty. */
        char dotChar = '.';
        if (str == null || str.length() == 0) {
            log.debug("key is either null or empty - returning false");
            return false;
        }

        for (int i = 0; i < str.length(); i++) {

            /** If we find a non-digit character we return false. */
            if (!Character.isDigit(str.charAt(i))) {
                if (str.charAt(i) != dotChar && str.charAt(i) != '-') {
                    return false;
                }
            }
        }
        return true;

    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
    }
}
