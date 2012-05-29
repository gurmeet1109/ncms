package in.nic.cmf.services.ncmsui;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller for processing RESTful web service requests for the cms service.
 *
 * @author Kalpana Rajendran
 */
@Controller
@Path(UIControllerImpl.APP_URL)
public class UIControllerImpl implements UIController
{

	/**
	 * Instantiates a new uI controller impl.
	 */
	public UIControllerImpl(){}
	
	/** The Constant APP_URL. */
	public static final String APP_URL = "/app";

	/* (non-Javadoc)
	 * @see in.nic.cmf.services.ncmsui.UIController#getHtml(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@GET
	@Path("{domain}")
	@Produces("text/html")
	public ModelAndView getHtml(@PathParam("domain") String domain,@Context HttpServletRequest request,@Context HttpServletResponse response) throws Exception {
		ModelAndView modelandview = new ModelAndView("cmshome");
		return modelandview;
	}

	/* (non-Javadoc)
	 * @see in.nic.cmf.services.ncmsui.UIController#login(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@GET
	@Path("{domain}/login")
	@Produces("text/html")
	public ModelAndView login(@PathParam("domain") String domain, @Context HttpServletRequest request,@Context HttpServletResponse response) throws Exception {		
		ModelAndView modelandview = new ModelAndView("login");		
		return modelandview;
	}

	

}
