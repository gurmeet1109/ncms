package in.nic.cmf.seplugin.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class for Property Generation as singleton.
 * @author premananda
 */
public final class PropertyGenerator {
    /**
     * static instance of PropertyGenerator.
     */
    private static PropertyGenerator propertyGenerator;
    /**
     * static instance of Properties.
     */
    private static Properties props;

    /**
     * initializes props and propertyGenerator.
     */
    static {
        props = new Properties();
        propertyGenerator = new PropertyGenerator();
    }

    /**
     * no arg constructor.
     */
    private PropertyGenerator() {
        InputStream is = getClass().getClassLoader()
                .getResourceAsStream("querystringparser.properties");
        new IOUtil().loadProperties(props, is);
    }

    /**
     * method for singleton.
     * @return is of type PropertyGenerator
     */
    public static PropertyGenerator getInstance() {
        return propertyGenerator;
    }

    /**
     * get Properties instance.
     * @return is of type Properties
     */
    public static Properties getProperties() {
        return props;
    }
}
