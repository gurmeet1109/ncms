package in.nic.cmf.logger;

import org.junit.Test;

/**
 * The Class LoggerSampleStatusSizeTest.
 * @author sunil
 */
public class LoggerSampleStatusSizeTest {

	/** The log trace. */
	public LogTracer logTrace = new LogTracer("samplestatusSIZE", true, true,
			true, true, true, 2);

	/**
	 * Test method entry.
	 */
	@Test
	public void testMethodEntry() {
		logTrace.methodEntry("method going to start");
	}

	/**
	 * Test method exit.
	 */
	@Test
	public void testMethodExit() {
		logTrace.methodExit("method end");
	}

	/**
	 * Test debug.
	 */
	@Test
	public void testDebug() {
		logTrace.debug("debug message");
	}

	/**
	 * Test info.
	 */
	@Test
	public void testInfo() {
		logTrace.info("information message");
	}

	/**
	 * Test warn.
	 */
	@Test
	public void testWarn() {
		logTrace.warn("warning message");
	}

	/**
	 * Test error.
	 */
	@Test
	public void testError() {
		logTrace.error("error message");
	}

	/**
	 * Test fatal.
	 */
	@Test
	public void testFatal() {
		logTrace.fatal("fatal message");
	}

	/**
	 * Test debug loop.
	 */
	@Test
	public void testDebugLoop() {
		for (int i = 1; i < 100000; i++) {
			logTrace.debug("debug message");
		}
	}
}
