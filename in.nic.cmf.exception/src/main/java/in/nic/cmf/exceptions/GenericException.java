package in.nic.cmf.exceptions;

import in.nic.cmf.properties.PropertiesUtil;

import java.util.Map;

/**
 * The Class GenericException.
 */
public class GenericException extends RuntimeException {

    private String domain;

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain
     *            the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     */
    public GenericException(String domain, String cmfErrorCode) {
        super(cmfErrorCode);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        buildException();
    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     * @param errorMessage
     *            the error message
     */
    public GenericException(String domain, String cmfErrorCode,
                            String errorMessage) {
        super(cmfErrorCode + ": " + errorMessage);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        setErrorMessage(errorMessage);
    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     * @param errorMessage
     *            the error message
     * @param input
     *            the input
     */
    public GenericException(String domain, String cmfErrorCode,
                            String errorMessage, String input) {
        super(cmfErrorCode + ": " + errorMessage + " Input:" + input);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        setErrorMessage(errorMessage);
        setInputParams(input);
    }

    /**
     * Instantiates a new generic exception.
     * @param errorResponseMap
     *            the error response map
     */
    public GenericException(String domain, Map<String, Object> errorResponseMap) {
        super(errorResponseMap.get("httpCode") + " : "
                + errorResponseMap.get("errorCode"));
        setDomain(domain);
        // System.out.println("after super:" + errorResponseMap);
        setCmfErrorCode(errorResponseMap.get("errorCode").toString());
        setErrorMessage(errorResponseMap.get("simpleMessage").toString());
        // setInputParams(errorResponseMap.get("input").toString());
    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     * @param exception
     *            the exception
     */
    public GenericException(String domain, String cmfErrorCode,
                            Exception exception) {
        super(cmfErrorCode, exception);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        setException(exception);
    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     * @param errorMessage
     *            the error message
     * @param exception
     *            the exception
     */
    public GenericException(String domain, String cmfErrorCode,
                            String errorMessage, Exception exception) {
        super(cmfErrorCode + ": " + errorMessage, exception);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        setErrorMessage(errorMessage);
        setException(exception);

    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     * @param errorMessage
     *            the error message
     * @param input
     *            the input
     * @param exception
     *            the exception
     */
    public GenericException(String domain, String cmfErrorCode,
                            String errorMessage, String input,
                            Exception exception) {
        super(cmfErrorCode + ": " + errorMessage + " Input:" + input, exception);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        setErrorMessage(errorMessage);
        setInputParams(input);
        setException(exception);
    }

    /**
     * Instantiates a new generic exception.
     * @param cmfErrorCode
     *            the cmf error code
     * @param errorMessage
     *            the error message
     * @param input
     *            the input
     * @param exception
     *            the exception
     */
    public GenericException(String domain, String cmfErrorCode,
                            String errorMessage, Map<String, String> input,
                            Exception exception) {
        super(cmfErrorCode + ": " + errorMessage, exception);
        setDomain(domain);
        setCmfErrorCode(cmfErrorCode);
        setErrorMessage(errorMessage);
        setException(exception);
    }

    /** The cmf error code. */
    private String            cmfErrorCode     = "";

    /** The error message. */
    private String            errorMessage     = "";

    /** The input params. */
    private String            inputParams      = "";

    /** The exception. */
    private Exception         exception        = null;

    /** The exception details. */
    private ExceptionDetails  exceptionDetails = null;

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The prop util. */
    // private PropertiesUtil propUtil = new PropertiesUtil(
    // "exceptions");
    private PropertiesUtil    propUtil;

    /*
     * Builds the exception.
     */
    private void buildException() {
        if (exceptionDetails == null) {
            if (getCmfErrorCode() == null || getCmfErrorCode().equals("")) {
                setCmfErrorCode("ERR-0000");
            }
            String message = null;
            propUtil = PropertiesUtil.getInstanceof(domain, "exceptions");
            Map<String, String> propertyMap = propUtil.getAsMap();
            message = propertyMap.get(getCmfErrorCode());
            String[] codeAndMessage = new String[2];
            if (!propertyMap.containsKey("delimiter"))
                propertyMap.put("delimiter", "\\|");
            // should change this code or should send the errorcode
            if (message == null || message.equals("")) {
                message = "500|Unknown error occured";
            }
            codeAndMessage = message.split(propertyMap.get("delimiter"));
            exceptionDetails = new ExceptionDetails();
            try {
                exceptionDetails.setHttpCode(Integer
                        .parseInt(codeAndMessage[0]));
                exceptionDetails.setSimpleMessage(codeAndMessage[1]);
            } catch (Exception ex) {
                exceptionDetails.setHttpCode(Integer.parseInt("400"));
                exceptionDetails.setSimpleMessage("Unknown error");
            }
            exceptionDetails.setInputParams(getInputParams());
            exceptionDetails.setCmfErrorCode(getCmfErrorCode());
            exceptionDetails.setErrorMessage(getErrorMessage());
            exceptionDetails.setException(getException());
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        buildException();
        return exceptionDetails.getMessage();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getStackTrace()
     */
    @Override
    public StackTraceElement[] getStackTrace() {
        buildException();
        return exceptionDetails.getException().getStackTrace();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        return getMessage();
    }

    /**
     * Gets the exception details.
     * @return the exception details
     */
    public String getExceptionDetails() {
        buildException();
        return exceptionDetails.toString();
    }

    /**
     * Gets the http status code.
     * @return the http status code
     */
    public int getHttpStatusCode() {
        buildException();
        return exceptionDetails.getHttpCode();
    }

    /**
     * Sets the cmf error code.
     * @param cmfErrorCode
     *            the new cmf error code
     */
    public void setCmfErrorCode(String cmfErrorCode) {
        this.cmfErrorCode = cmfErrorCode;
    }

    /**
     * Gets the cmf error code.
     * @return the cmf error code
     */
    public String getCmfErrorCode() {
        return cmfErrorCode;
    }

    /**
     * Sets the error message.
     * @param errorMessage
     *            the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the input params.
     * @param inputParams
     *            the new input params
     */
    public void setInputParams(String inputParams) {
        this.inputParams = inputParams;
    }

    /**
     * Gets the input params.
     * @return the input params
     */
    public String getInputParams() {
        return inputParams;
    }

    /**
     * Sets the exception.
     * @param exception
     *            the new exception
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Gets the exception.
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }
}
