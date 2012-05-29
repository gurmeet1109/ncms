package in.nic.cmf.services.ncmsui;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The Class UIControllerImplTest.
 */
public class UIControllerImplTest {
	UIControllerImpl uic = new UIControllerImpl();
	/**
	 * Test ui controller impl.
	 */
	@Test
	public void testUIControllerImpl() {
		assertNotNull(uic);
	}

	/**
	 * Test get html.
	 * @throws Exception 
	 */
	@Test
	public void testGetHtml() throws Exception {
		uic.getHtml("nic.in", null, null);
	}

	/**
	 * Test login.
	 * @throws Exception 
	 */
	@Test
	public void testLogin() throws Exception {
		uic.login("nic.in", null, null);
	}

}
