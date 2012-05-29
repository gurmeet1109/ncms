package in.nic.cmf.seplugin.util;

import in.nic.cmf.convertors.FormatJson;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Class for HashMap Generation as singleton.
 * @author premananda
 */
public final class HashMapGenerator {
    /**
     * static instance of HashMapGenerator.
     */
    // private static HashMapGenerator hashMapGenerator;
    /**
     * static instance of HashMap<String, Object>.
     */
    private static HashMap<String, Object>           queryKey;
    private static HashMap<String, HashMapGenerator> hashHashMapGenerator = new HashMap<String, HashMapGenerator>();

    /**
     * initializes hashMapGenerator.
     */
    // static {
    // hashMapGenerator = new HashMapGenerator();
    // }

    /**
     * no arg constructor.
     */
    private HashMapGenerator(String domain) {

        InputStream is = getClass().getClassLoader().getResourceAsStream(
                "FieldNameMapping.json");
        String jsonStr = null;
        jsonStr = new IOUtil().toString(is, null);
        queryKey = FormatJson.getInstanceof(domain).in(jsonStr);
    }

    /**
     * method for singleton.
     * @return is of type HashMapGenerator
     */
    // public static HashMapGenerator getInstance() {
    // return hashMapGenerator;
    // }
    public static HashMapGenerator getInstanceof(String domain) {
        if (!hashHashMapGenerator.containsKey(domain)) {
            hashHashMapGenerator.put(domain, new HashMapGenerator(domain));
        }
        return hashHashMapGenerator.get(domain);
    }

    /**
     * get HashMap<String, Object> instance.
     * @return is of type HashMap<String, Object>
     */
    // public static HashMap<String, Object> getQueryKey() {
    // return queryKey;
    // }
    public HashMap<String, Object> getQueryKey() {
        return queryKey;
    }
}
