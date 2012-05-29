package in.nic.cmf.service.generate;

import java.io.IOException;
import java.util.Properties;

/**
 * The Class GenerateJson.
 */
public class GenerateJson {

    /** The properties. */
    private Properties properties = null;

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws Exception
     *             the exception
     */
    public static void main(String[] args) throws IOException, Exception {
	String domain = System.getProperty("site");
	if ((domain == null) || (domain.isEmpty())) {
	    domain = "default";
	}
	System.out
		.println("Xls to Domain Json EXPORT Process initialized...\n");
	Generate generate = new Generate(domain);
	generate.generate();
	System.out.println("\n\nXls to Domain Json EXPORT Process END...");
    }
}
