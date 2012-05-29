package in.nic.cmf.logger;

import in.nic.cmf.logger.contract.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;

// TODO: Auto-generated Javadoc
// modified for explanation
/**
 * Justin Narbert .I Date : Jan 17, 2012 Purpose : logger dependency
 * configuration information readed through propertiesUtils Justin Narbert .I ->
 * on : December 22, 2011 Purpose : default file size, log write path
 * informations are moved into configuration file. Justin Narbert .I -> on :
 * August 26, 2011 purpose : eliminate the log4j errorlog property file to
 * create dynamic log files. Justin Narbert .I -> on : August 09, 2011 purpose :
 * to log DEBUG,INFO,WARN,ERROR,FATAL information using log4j Raja R,Ramu
 * P,Justin Narbert .I Created on:Friday,july,2011
 */
public class LogTracer implements Log {

    /** The logger. */
    private Logger              logger        = null;

    /** The debug status. */
    private boolean             debugStatus   = false;

    /** The info status. */
    private boolean             infoStatus    = false;

    /** The warn status. */
    private boolean             warnStatus    = false;

    /** The error status. */
    private boolean             errorStatus   = false;

    /** The fatal status. */
    private boolean             fatalStatus   = false;

    /** The properties. */
    private Properties          logProperties = null;

    /** The logger name. */
    private String              loggerName    = null;

    /** The prop. */
    private Map<String, String> prop          = null;

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     */
    public LogTracer(String domain, String loggerName) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(true, true, true, true, true);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param logWritePath
     *            the log write path
     */
    public LogTracer(String domain, String loggerName, String logWritePath) {

        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(true, true, true, true, true);
        setLogDirectory(logWritePath, loggerName);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param logFileSizeinMB
     *            the log file size in MB
     */
    public LogTracer(String domain, String loggerName, int logFileSizeinMB) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(true, true, true, true, true);
        setLogFileSize(logFileSizeinMB);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param logFileSizeinMB
     *            the log file size in MB
     * @param logWritePath
     *            the log write path
     */
    public LogTracer(String domain, String loggerName, int logFileSizeinMB,
                     String logWritePath) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(true, true, true, true, true);
        setLogFileSize(logFileSizeinMB);
        setLogDirectory(logWritePath, loggerName);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param logFileSizeinMB
     *            the log file size in MB
     * @param logWritePath
     *            the log write path
     * @param logBackupLimit
     *            the log backup limit
     */
    public LogTracer(String domain, String loggerName, int logFileSizeinMB,
                     String logWritePath, int logBackupLimit) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(true, true, true, true, true);
        setLogFileSize(logFileSizeinMB);
        setLogDirectory(logWritePath, loggerName);
        setLogBackupLimit(logBackupLimit);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param debugStatus
     *            the debug status
     * @param infoStatus
     *            the info status
     * @param warnStatus
     *            the warn status
     * @param errorStatus
     *            the error status
     * @param fatalStatus
     *            the fatal status
     */
    public LogTracer(String domain, String loggerName, Boolean debugStatus,
                     Boolean infoStatus, Boolean warnStatus,
                     Boolean errorStatus, Boolean fatalStatus) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(debugStatus, infoStatus, warnStatus, errorStatus, fatalStatus);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param debugStatus
     *            the debug status
     * @param infoStatus
     *            the info status
     * @param warnStatus
     *            the warn status
     * @param errorStatus
     *            the error status
     * @param fatalStatus
     *            the fatal status
     * @param logWritePath
     *            the log write path
     */
    public LogTracer(String domain, String loggerName, Boolean debugStatus,
                     Boolean infoStatus, Boolean warnStatus,
                     Boolean errorStatus, Boolean fatalStatus,
                     String logWritePath) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(debugStatus, infoStatus, warnStatus, errorStatus, fatalStatus);
        setLogDirectory(logWritePath, loggerName);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param debugStatus
     *            the debug status
     * @param infoStatus
     *            the info status
     * @param warnStatus
     *            the warn status
     * @param errorStatus
     *            the error status
     * @param fatalStatus
     *            the fatal status
     * @param logFileSizeinMB
     *            the log file size in MB
     */
    public LogTracer(String domain, String loggerName, Boolean debugStatus,
                     Boolean infoStatus, Boolean warnStatus,
                     Boolean errorStatus, Boolean fatalStatus,
                     int logFileSizeinMB) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(debugStatus, infoStatus, warnStatus, errorStatus, fatalStatus);
        setLogFileSize(logFileSizeinMB);
        initialize();
    }

    /**
     * Instantiates a new log tracer.
     * @param loggerName
     *            the logger name
     * @param debugStatus
     *            the debug status
     * @param infoStatus
     *            the info status
     * @param warnStatus
     *            the warn status
     * @param errorStatus
     *            the error status
     * @param fatalStatus
     *            the fatal status
     * @param logFileSizeinMB
     *            the log file size in MB
     * @param logWritePath
     *            the log write path
     */
    public LogTracer(String domain, String loggerName, Boolean debugStatus,
                     Boolean infoStatus, Boolean warnStatus,
                     Boolean errorStatus, Boolean fatalStatus,
                     int logFileSizeinMB, String logWritePath) {
        setLoggerName(domain, loggerName);
        setDefaultProperties(domain, loggerName);
        setStatus(debugStatus, infoStatus, warnStatus, errorStatus, fatalStatus);
        setLogFileSize(logFileSizeinMB);
        setLogDirectory(logWritePath, loggerName);
        initialize();
    }

    /**
     * Sets the default properties.
     */
    private void setDefaultProperties(String domain, String loggerName) {

        if (domain == null || domain == "") {
            domain = "default";
        }
        // prop = loadProperty("/opt/cmf/properties/logger/config.properties");
        prop = loadProperty("/opt/cmf/domains/" + domain
                + "/properties/logger/config.properties");

        logProperties = new Properties();
        logProperties.setProperty("log4j.logger.GTL" + getLoggerName(), ","
                + getLoggerName());
        logProperties.setProperty("log4j.appender." + getLoggerName()
                + ".layout", prop.get("layout"));
        // setLogDirectory(prop.get("logWriteLocation");
        setLogDirectory(prop.get("logWriteLocation") + domain + "/logs/",
                loggerName);
        logProperties.setProperty("log4j.appender." + getLoggerName()
                + ".layout.ConversionPattern", prop.get("conversionPattern"));
        logProperties.setProperty("log4j.appender.appenderName.encoding",
                prop.get("encode"));
        try {
            setLogFileSize(Integer.parseInt(prop.get("maxFileSize")));
            setLogBackupLimit(Integer.parseInt(prop.get("maxBackupIndex")));
        } catch (NumberFormatException e) {
            System.out
                    .println("Pre-condition failed : Number Format Exception {maxFileSize/maxBackupIndex}");
            e.printStackTrace();
        }
        logProperties.setProperty("log4j.appender." + getLoggerName(),
                prop.get("logAppender"));
        logProperties.setProperty("log4j.appender." + getLoggerName()
                + ".Append", prop.get("logAppend"));
    }

    /**
     * Initialize.
     */
    private void initialize() {
        PropertyConfigurator.configure(logProperties);
        this.logger = Logger.getLogger("GTL" + getLoggerName());
    }

    /**
     * Sets the log backup limit.
     * @param logBackupLimit
     *            the new log backup limit
     */
    private void setLogBackupLimit(int logBackupLimit) {
        if (logBackupLimit > 0)
            logProperties
                    .setProperty("log4j.appender." + getLoggerName()
                            + ".MaxBackupIndex",
                            new Integer(logBackupLimit).toString());
    }

    /**
     * Sets the status.
     * @param debugStatus
     *            the debug status
     * @param infoStatus
     *            the info status
     * @param warnStatus
     *            the warn status
     * @param errorStatus
     *            the error status
     * @param fatalStatus
     *            the fatal status
     */
    private void setStatus(Boolean debugStatus, Boolean infoStatus,
            Boolean warnStatus, Boolean errorStatus, Boolean fatalStatus) {
        // setDefaultProperties();
        setDebugStatus(debugStatus);
        setInfoStatus(infoStatus);
        setWarnStatus(warnStatus);
        setErrorStatus(errorStatus);
        setFatalStatus(fatalStatus);
    }

    /**
     * Sets the log file size.
     * @param logFileSizeinMB
     *            the new log file size
     */
    private void setLogFileSize(int logFileSizeinMB) {
        logProperties.setProperty("log4j.appender." + getLoggerName()
                + ".MaxFileSize", logFileSizeinMB + "MB");
    }

    /**
     * Sets the log directory.
     * @param logWritePath
     *            the new log directory
     */
    private void setLogDirectory(String logWritePath, String loggerName) {
        boolean status = false;
        try {
            if (isExist(logWritePath)) {
                status = true;

            } else if (new File(logWritePath).mkdirs()) {
                status = true;
            }
        } catch (SecurityException e) {
            System.out
                    .println("Unable to write log file : {SecurityException}");
            e.printStackTrace();
        }

        if (status) {
            if (logWritePath.substring(logWritePath.length() - 1,
                    logWritePath.length()).equals("/") == false) {
                logWritePath += "/";
            }
            logProperties.setProperty("log4j.appender." + getLoggerName()
                    + ".File", logWritePath + loggerName + ".log");
        }
    }

    /**
     * Checks log directory is exist or not.
     * @param FileNameorDirectoryName
     *            the file name or directory name
     * @return true, if is exist
     */
    private boolean isExist(String FileNameorDirectoryName) {
        File file = new File(FileNameorDirectoryName);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#debug(java.lang.String)
     */
    @Override
    public void debug(String message) {
        if (isDebugStatus()) {
            logger.debug(message);
        }
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#info(java.lang.String)
     */
    @Override
    public void info(String message) {
        if (isInfoStatus()) {
            logger.info(message);
        }
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#warn(java.lang.String)
     */
    @Override
    public void warn(String message) {
        if (isWarnStatus()) {
            logger.warn(message);
        }
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#error(java.lang.String)
     */
    @Override
    public void error(String message) {
        if (isErrorStatus()) {
            logger.error(message);
        }
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#fatal(java.lang.String)
     */
    @Override
    public void fatal(String message) {
        if (isFatalStatus()) {
            logger.fatal(message);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.logger.contract.Log#processStart(org.aspectj.lang.JoinPoint)
     */
    @Override
    public void processStart(JoinPoint joinPoint) {
        String key = joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName();
        methodEntry(key);
    }

    /*
     * (non-Javadoc)
     * @see
     * in.nic.cmf.logger.contract.Log#processEnd(org.aspectj.lang.JoinPoint)
     */
    @Override
    public void processEnd(JoinPoint joinPoint) {
        String key = joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName();
        methodExit(key);
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#methodEntry(java.lang.String)
     */
    @Override
    public void methodEntry(String key) {
        long startTime = System.currentTimeMillis();
        info("::Exec~~ Start time : " + startTime + " ==> " + key);
    }

    /*
     * (non-Javadoc)
     * @see in.nic.cmf.logger.contract.Log#methodExit(java.lang.String)
     */
    @Override
    public void methodExit(String key) {
        long endTime = System.currentTimeMillis();
        info("::Exec~~ End time : " + endTime + " ==> " + key);
    }

    /**
     * Sets the debug status.
     * @param debugStatus
     *            the new debug status
     */
    private void setDebugStatus(boolean debugStatus) {
        this.debugStatus = debugStatus;
    }

    /**
     * Checks if is debug status.
     * @return true, if is debug status
     */
    private boolean isDebugStatus() {
        return debugStatus;
    }

    /**
     * Sets the info status.
     * @param infoStatus
     *            the new info status
     */
    private void setInfoStatus(boolean infoStatus) {
        this.infoStatus = infoStatus;
    }

    /**
     * Checks if is info status.
     * @return true, if is info status
     */
    private boolean isInfoStatus() {
        return infoStatus;
    }

    /**
     * Sets the warn status.
     * @param warnStatus
     *            the new warn status
     */
    private void setWarnStatus(boolean warnStatus) {
        this.warnStatus = warnStatus;
    }

    /**
     * Checks if is warn status.
     * @return true, if is warn status
     */
    private boolean isWarnStatus() {
        return warnStatus;
    }

    /**
     * Sets the error status.
     * @param errorStatus
     *            the new error status
     */
    private void setErrorStatus(boolean errorStatus) {
        this.errorStatus = errorStatus;
    }

    /**
     * Checks if is error status.
     * @return true, if is error status
     */
    private boolean isErrorStatus() {
        return errorStatus;
    }

    /**
     * Sets the fatal status.
     * @param fatalStatus
     *            the new fatal status
     */
    private void setFatalStatus(boolean fatalStatus) {
        this.fatalStatus = fatalStatus;
    }

    /**
     * Checks if is fatal status.
     * @return true, if is fatal status
     */
    private boolean isFatalStatus() {
        return fatalStatus;
    }

    /**
     * Sets the logger name.
     * @param loggerName
     *            the new logger name
     */
    private void setLoggerName(String domain, String loggerName) {

        if (domain == null || domain == "") {
            domain = "default";
        }
        this.loggerName = domain + '-' + loggerName;
    }

    /**
     * Gets the logger name.
     * @return the logger name
     */
    private String getLoggerName() {
        return loggerName;
    }

    /**
     * Load property.
     * @param propFileName
     *            the prop file name
     * @return the map
     */
    private Map<String, String> loadProperty(String propFileName) {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream(propFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Map) property;
    }
}
