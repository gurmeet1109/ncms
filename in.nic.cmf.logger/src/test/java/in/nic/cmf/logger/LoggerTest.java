package in.nic.cmf.logger;

import org.junit.Test;

/**
 * The Class LoggerTest.
 */
public class LoggerTest {

	// public LogTracer logTrace = new LogTracer("sample");
	// public LogTracer logTrace = new LogTracer("samplewithpath","/tmp/");
	// public LogTracer logTrace = new LogTracer("samplewithsize",1);
	// public LogTracer logTrace = new
	// LogTracer("samplewithsizeandpath",1,"/tmp");
	// public LogTracer logTrace = new
	// LogTracer("samplestatus",true,true,true,true,true);
	// public LogTracer logTrace = new
	// LogTracer("samplestatusPATH",true,true,true,true,true,"/tmp");
	// public LogTracer logTrace = new
	// LogTracer("samplestatusSIZE",true,true,true,true,true,2);
	/** The log trace. */
	public LogTracer logTrace = new LogTracer("samplestatusSIZE-PATH", true,
			true, true, true, true, 2, "/tmps/");

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
