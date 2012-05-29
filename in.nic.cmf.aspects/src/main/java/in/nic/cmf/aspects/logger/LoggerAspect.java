package in.nic.cmf.aspects.logger;

import in.nic.cmf.logger.LogTracer;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Aspect class for the Spring/AspectJ Logging example shown in the blog
 * 
 * @author Raja R Join point: a point during the execution of a program, such as
 *         the execution of a method or the handling of an exception. In Spring
 *         AOP, a join point always represents a method execution.
 */
public class LoggerAspect {
    // Logger logger = Logger.getLogger(LoggerAspect.class);
    @Autowired
    LogTracer logger;

    LoggerAspect() {
	// logger = new LogTracer("logentryexit");
	// System.out.println("logger instance:::"+logger);
    }

    public void logEntry(JoinPoint joinPoint) {

	logger.processStart(joinPoint);
	/*
	 * String printStr = "Entering - " + joinPoint.getTarget().toString() +
	 * "," + joinPoint.getSignature().getName() + "()"; for (int index = 0;
	 * index < joinPoint.getArgs().length; index++) { if (index == 0) {
	 * printStr += ","; } printStr += " " + "\"" +
	 * joinPoint.getArgs()[index] + "\""; } logger.info(printStr);
	 */
    }

    public void logExit(JoinPoint joinPoint) {

	// logger.info("Exiting - " + joinPoint.getSignature().getName());
	logger.processEnd(joinPoint);
    }

}
