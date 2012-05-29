package in.nic.cmf.formatters;

import java.util.Map;

public interface Formatter {

     Map<String, Map<String, Object>> Format(String domain,
            Map<String, Map<String, Object>> input);
     Map<String,Object> convert(String domain, String content);
}
