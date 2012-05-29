package in.nic.cmf.seplugin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
/**
 * Class for IO Utilities.
 * @author premananda
 */
public class IOUtil {
    /**
     * gets equivalent text.
     * @param input is a type of InputStream.
     * @param encoding is a type of String
     * @return content is a type of String
     */
    public final String toString(final InputStream input,
            final String encoding) {
        String content = null;
        try {
            content = IOUtils.toString(input, encoding);
        } catch (IOException ioe) {
            ioe.getMessage();
        }
        return content;
    }

    /**
     * loads the properties.
     * @param props is a type of Properties
     * @param input is a type of InputStream
     */
    public final void loadProperties(final Properties props,
            final InputStream input) {
        try {
            props.load(input);
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }
}
