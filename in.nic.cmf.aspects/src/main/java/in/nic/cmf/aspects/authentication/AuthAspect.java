package in.nic.cmf.aspects.authentication;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.util.AuthAspectHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @author Raja R
 */
/**
 * The Class AuthAspect.
 */
public class AuthAspect {

	/** The auth aspect helper. */
	@Autowired
	public AuthAspectHelper authAspectHelper;

	/** The log. */
	@Autowired
	private LogTracer log ;
	
	public void authEntry(JoinPoint joinPoint)	throws GenericException {
		log.debug("Inside AuthEntry");
		log.debug("Inside AuthEntry::"+joinPoint);
		Object[] args = joinPoint.getArgs();
		log.debug("Args are::"+args[0]+"-"+args[1]+"-"+args[2]+"-"+args[3]+"-"+args[4]+"-"+args[5]);
		log.debug("Before status:::");
		System.out.println("before dovalidate()");
		boolean status = authAspectHelper.doValidate((HttpServletRequest)args[0],(HttpServletResponse)args[1],(String)args[4]);
		System.out.println("Auth status - "+status);
		log.debug("Auth status:::"+status);
	}
	

}
