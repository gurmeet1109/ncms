package in.nic.cmf.services.ncmsui;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Interface UIController.
 */
public interface UIController {
	
	/**
	 * Gets the html.
	 *
	 * @param domain the domain
	 * @param request the request
	 * @param response the response
	 * @return the html
	 * @throws Exception the exception
	 */
	public ModelAndView getHtml(String domain,HttpServletRequest request,final HttpServletResponse response) throws Exception;
	
	/**
	 * Login.
	 *
	 * @param domain the domain
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 * @throws Exception the exception
	 */
	public ModelAndView login(String domain,HttpServletRequest request,final HttpServletResponse response) throws Exception;
}
