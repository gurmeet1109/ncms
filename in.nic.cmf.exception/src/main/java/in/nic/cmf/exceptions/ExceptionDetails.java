package in.nic.cmf.exceptions;

import org.json.JSONObject;

/**
 * The Class ExceptionDetails.
 */
public class ExceptionDetails {
	
	/**
	 * Gets the cmf error code.
	 *
	 * @return the cmf error code
	 */
	public String getCmfErrorCode() {
		return cmfErrorCode;
	}

	/**
	 * Sets the cmf error code.
	 *
	 * @param cmfErrorCode the new cmf error code
	 */
	public void setCmfErrorCode(String cmfErrorCode) {
		this.cmfErrorCode = cmfErrorCode;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the input params.
	 *
	 * @return the input params
	 */
	public String getInputParams() {
		return inputParams;
	}

	/**
	 * Sets the input params.
	 *
	 * @param inputParams the new input params
	 */
	public void setInputParams(String inputParams) {
		this.inputParams = inputParams;
	}

	/**
	 * Gets the simple message.
	 *
	 * @return the simple message
	 */
	public String getSimpleMessage() {
		return simpleMessage;
	}

	/**
	 * Sets the simple message.
	 *
	 * @param simpleMessage the new simple message
	 */
	public void setSimpleMessage(String simpleMessage) {
		this.simpleMessage = simpleMessage;
	}

	/**
	 * Gets the http code.
	 *
	 * @return the http code
	 */
	public int getHttpCode() {
		return httpCode;
	}

	/**
	 * Sets the http code.
	 *
	 * @param httpCode the new http code
	 */
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * Sets the exception.
	 *
	 * @param exception the new exception
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	/** The cmf error code. */
	private String cmfErrorCode;
	
	/** The error message. */
	private String errorMessage;
	
	/** The input params. */
	private String inputParams;
	
	/** The simple message. */
	private String simpleMessage;
	
	/** The http code. */
	private int httpCode;
	
	/** The exception. */
	private Exception exception;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		JSONObject errorJson = new JSONObject();
		try {
			errorJson.put("httpCode", this.httpCode);
			errorJson.put("simpleMessage", this.simpleMessage);
			errorJson.put("input", this.inputParams);
			errorJson.put("errorCode", this.cmfErrorCode);
			errorJson.put("errorMessage", this.errorMessage);
			if (this.exception != null) {
				errorJson.put("stacktrace", this.exception.getStackTrace());
				errorJson.put("rootCause", this.exception.getCause());
			}
		} catch (Exception ex) {
			System.out
					.println("Exception occured at toString() of ExceptionDetails class.");
		}
		return errorJson.toString();
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		JSONObject errorJson = new JSONObject();
		try {
			errorJson.put("httpCode", this.httpCode);
			errorJson.put("simpleMessage", this.simpleMessage);
			errorJson.put("errorCode", this.cmfErrorCode);
		} catch (Exception ex) {
			System.out
					.println("Exception occured at getMessage() of ExceptionDetails class.");
		}
		return errorJson.toString();
	}

}
