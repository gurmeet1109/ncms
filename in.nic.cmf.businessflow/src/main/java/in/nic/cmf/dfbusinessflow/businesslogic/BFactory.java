package in.nic.cmf.dfbusinessflow.businesslogic;


import java.util.Map;

public class BFactory {

    public static BService getInstance(String domain,
            Map<String, Map<String, Object>> parameters) {
        Map<String, Object> inputMap = (Map<String, Object>) parameters
                .get("input");
        if (inputMap.containsKey("queryString")
                && ((String)inputMap.get("queryString"))
                        .contains("readknowledge")) {
            return RulesModel.getInstanceof(domain);
        } else {
            return BLogic.getInstanceof(domain);
        }

    }

}
