package in.nic.cmf.services.ncmsui;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class UIControllerImplTest.
 */

public class UIControllerImplTest {

    /** The uic. */
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
     *             the exception
     */
    @Test
    public void testGetHtml() throws Exception {
        uic.getHtml("nic.in", null, null);
    }

    /**
     * Test login.
     * @throws Exception
     *             the exception
     */
    @Test
    public void testLogin() throws Exception {
        uic.login("nic.in", null, null);
    }

}
