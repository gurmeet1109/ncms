package in.nic.cmf.logger.contract;

import org.aspectj.lang.JoinPoint;

/**
 * The Interface Log methods.
 */
public interface Log {

	/**
	 * Debug. The DEBUG Level designates fine-grained informational events that
	 * are most useful to debug an application.
	 * 
	 * @param message
	 *            the message
	 */
	public void debug(String message);

	/**
	 * Info. The INFO level designates informational messages that highlight the
	 * progress of the application at coarse-grained level.
	 * 
	 * @param message
	 *            the message
	 */
	public void info(String message);

	/**
	 * Warn. The WARN level designates potentially harmful situations.
	 * 
	 * @param message
	 *            the message
	 */
	public void warn(String message);

	/**
	 * Error. The ERROR level designates error events that might still allow the
	 * application to continue running.
	 * 
	 * @param message
	 *            the message
	 */
	public void error(String message);

	/**
	 * Fatal. The FATAL level designates very severe error events that will
	 * presumably lead the application to abort
	 * 
	 * @param message
	 *            the message
	 */
	public void fatal(String message);

	/**
	 * Process start.
	 * 
	 * @param joinPoint
	 *            the join point
	 */
	public void processStart(JoinPoint joinPoint);

	/**
	 * Process end.
	 * 
	 * @param joinPoint
	 *            the join point
	 */
	public void processEnd(JoinPoint joinPoint);

	/**
	 * Method entry.
	 * 
	 * @param key
	 *            the key
	 */
	public void methodEntry(String key);

	/**
	 * Method exit.
	 * 
	 * @param key
	 *            the key
	 */
	public void methodExit(String key);
}
