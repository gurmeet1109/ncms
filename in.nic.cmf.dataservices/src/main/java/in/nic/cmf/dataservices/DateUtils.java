package in.nic.cmf.dataservices;

import in.nic.cmf.exceptions.GenericException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The Class DateUtils.
 */
public class DateUtils {

    /**
     * Now.
     * 
     * @param dateFormat
     *            the date format
     * @return the string
     * @throws GenericException
     *             the generic exception
     */
    public static String now(String dateFormat) throws GenericException {
	try {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	} catch (Exception e) {
	    throw new GenericException("ERR-DS-0000", e);
	}

    }
}
