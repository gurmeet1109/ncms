package in.nic.cmf.dsserver;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.name.DN;
import org.junit.Test;

/**
 * The Class EmbeddedADSTest.
 */
public class EmbeddedADSTest {

	/** The random number length. */
	public static final int MIN_LENGTH = 11;
	/** The random number generator. */
	protected static java.util.Random r = new java.util.Random();
	/**
	 * Set of characters that are valid. Must be printable, memorable.
	 */
	protected static char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
			'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', };

	/**
	 * getNext method Generate a Password object with a random password.
	 *
	 * @return the next
	 */
	public static String getNext() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < MIN_LENGTH; i++) {
			sb.append(goodChar[r.nextInt(goodChar.length)]);
		}
		return sb.toString();

	}

	/**
	 * Test start server.
	 */
	@Test
	public void testStartServer() {
		try {
			File workDir = new File(System.getProperty("java.io.tmpdir")
					+ "/"+getNext());
			workDir.mkdirs();
			// Create the server
			EmbeddedADS ads = new EmbeddedADS(workDir);
			// optionally we can start a server too
			ads.startServer();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}

	/**
	 * Test embedded ads.
	 */
	@Test
	public void testEmbeddedADS() {
		try {
			File workDir = new File(System.getProperty("java.io.tmpdir")
					+ "/serverwork");
			workDir.mkdirs();
			// Create the server
			EmbeddedADS ads = new EmbeddedADS(workDir);
			// optionally we can start a server too
			ads.startServer();
		} catch (Exception e) {
			System.out.println("Error : " + e);
		}
	}
	
	/**
	 * Test main.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testMain() throws Exception {
		File workDir = new File(System.getProperty("java.io.tmpdir")
				+ "/"+getNext());
		workDir.mkdirs();
		// Create the server
		EmbeddedADS ads = new EmbeddedADS(workDir);
		ads.main(null);
	}

}
