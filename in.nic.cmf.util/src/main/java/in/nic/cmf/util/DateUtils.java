package in.nic.cmf.util;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class DateUtils {

    private Map<String, SimpleDateFormat>     hSimpleDateFormat = new HashMap();
    private static Date                       date              = new Date();
    private static HashMap<String, DateUtils> hashDateUtils     = new HashMap<String, DateUtils>();
    private String                            domain            = null;
    // private LogTracer log = new LogTracer(
    // "UtilTraceLogger",
    // true, true,
    // true, true,
    // true);
    public LogTracer                          log;

    private DateUtils(String domain) {
        this.domain = domain;
        setLogger(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                false);
    }

    public static DateUtils getInstanceOf(String domain) {
        if (!hashDateUtils.containsKey(domain)) {
            hashDateUtils.put(domain, new DateUtils(domain));
        }
        return hashDateUtils.get(domain);
    }

    public static Date getDateInstanceOf() {
        return date;
    }

    public SimpleDateFormat getSimpleDateFormatInstanceOf(String dateFormat) {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getSimpleDateFormatInstanceOf()");
        try {
            if (hSimpleDateFormat.containsKey(dateFormat)) {
                log.methodExit(this.getClass().getSimpleName()
                        + ".getSimpleDateFormatInstanceOf()");
                return hSimpleDateFormat.get(dateFormat);
            } else {
                hSimpleDateFormat.put(dateFormat, new SimpleDateFormat(
                        dateFormat));
                log.methodExit(this.getClass().getSimpleName()
                        + ".getSimpleDateFormatInstanceOf()");
                return hSimpleDateFormat.get(dateFormat);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".now()", e);
        }
    }

    public String now(String dateFormat) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".now()");
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = DateUtils.getInstanceOf(domain)
                    .getSimpleDateFormatInstanceOf(dateFormat);
            log.methodExit(this.getClass().getSimpleName() + ".now()");
            return sdf.format(cal.getTime());
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0015", this.getClass()
                    .getSimpleName() + ".now()", e);
        }
    }

    public String getSolrFormattedDateByForm(String incomingdate)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getSolrFormattedDateByForm()");
        String returndate = null;
        SimpleDateFormat inputDateFormat, outputDateFormat;
        try {
            Utils utils = Utils.getInstanceof(domain);
            log.debug("Incoming Date ::: " + incomingdate);
            if (!utils.isEmpty(incomingdate)) {
                inputDateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf("yyyy-MM-dd HH:mm:ss");
                log.debug("After Getting Object of Date Format");
                Date inputDate;
                try {
                    log.debug("Before parse Date");
                    inputDate = inputDateFormat.parse(incomingdate);
                    log.debug("In Try Method :::: " + inputDate);
                } catch (ParseException e) {
                    throw new GenericException(domain, "ERR-UTL-0015", this
                            .getClass().getSimpleName()
                            + ".getSolrFormattedDateByForm()", e);
                }
                log.debug("Before OutputDateFormat ::: ");
                // outputDateFormat =
                // DateUtils.getInstanceOf().getSimpleDateFormatInstanceOf("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                outputDateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf(
                                "yyyy-MM-dd'T'HH:mm:ss'Z'");
                log.debug("After OutputDateFormat ::: " + outputDateFormat);
                returndate = outputDateFormat.format(inputDate);
                log.debug("Return Date :::: " + returndate);
            } else {
                log.debug("In Else Method if date is empty");
                // DateFormat dateFormat =
                // DateUtils.getInstanceOf().getSimpleDateFormatInstanceOf("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                DateFormat dateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf(
                                "yyyy-MM-dd'T'HH:mm:ss'Z'");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                returndate = dateFormat.format(new Date()).toString();
                log.debug("Return Date Else ::: " + returndate);
            }
            log.methodExit(this.getClass().getSimpleName()
                    + ".getSolrFormattedDateByForm()");
            return returndate;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getSolrFormattedDateByForm()", e);
        }
    }

    public String getSolrFormattedDateByXml(String incomingdate)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".getSolrFormattedDateByXml()");
        String returndate = null;
        try {
            if (!incomingdate.isEmpty()) {
                // DateFormat dateFormat =
                // DateUtils.getInstanceOf().getSimpleDateFormatInstanceOf("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                DateFormat dateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf(
                                "yyyy-MM-dd'T'HH:mm:ss'Z'");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                returndate = dateFormat.parse(incomingdate).toString();
            }
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0000", this.getClass()
                    .getSimpleName() + ".getSolrFormattedDateByXml()", e);
        }
        log.methodExit(this.getClass().getSimpleName()
                + ".getSolrFormattedDateByXml()");
        return returndate;
    }

    public Date getFormattedDateByXml(String incomingdate) {
        Date returndate = null;
        log.methodEntry(this.getClass().getSimpleName()
                + ".getFormattedDateByXml()");
        try {
            if (!incomingdate.isEmpty()) {
                // DateFormat dateFormat =
                // DateUtils.getInstanceOf().getSimpleDateFormatInstanceOf("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                DateFormat dateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf(
                                "yyyy-MM-dd'T'HH:mm:ss'Z'");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                returndate = dateFormat.parse(incomingdate);
            }
            log.methodExit(this.getClass().getSimpleName()
                    + ".getFormattedDateByXml()");
            return returndate;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0015", this.getClass()
                    .getSimpleName() + ".getFormattedDateByXml()", e);
        }
    }

    public String getISTFormatDate(String value) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ".getISTFormatDate()");
        SimpleDateFormat inputDateFormat, outputDateFormat;
        try {
            inputDateFormat = DateUtils.getInstanceOf(domain)
                    .getSimpleDateFormatInstanceOf("yyyy-MM-dd'T'HH:mm:ss'Z'");// 2011-07-21T11:44:03.000Z
            Date inputDate = null;
            String outputDate = null;
            try {
                inputDate = inputDateFormat.parse(value);
            } catch (ParseException e) {
                log.warn("Parse exception thrown ::::: continued with catch");
                inputDateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf(
                                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                inputDate = inputDateFormat.parse(value);
            }

            if (inputDate != null) {
                outputDateFormat = DateUtils.getInstanceOf(domain)
                        .getSimpleDateFormatInstanceOf("yyyy-MM-dd HH:mm:ss");
                outputDate = outputDateFormat.format(inputDate);
            }
            log.methodExit(this.getClass().getSimpleName()
                    + ".getISTFormatDate()");
            return outputDate;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (ParseException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-UTL-0015", this.getClass()
                    .getSimpleName() + ".getISTFormatDate()", e);
        }
    }
}
