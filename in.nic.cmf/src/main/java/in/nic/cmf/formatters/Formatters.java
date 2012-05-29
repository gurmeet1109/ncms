package in.nic.cmf.formatters;

import java.util.Map;

public interface Formatters {

    public Map<String, Map<String, Object>> Format(String domain,
            Map<String, Map<String, Object>> input);
}
