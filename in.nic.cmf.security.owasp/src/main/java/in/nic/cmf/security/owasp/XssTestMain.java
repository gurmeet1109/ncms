//package in.nic.cmf.security.owasp;
//
//import org.owasp.validator.html.AntiSamy;
//
//import org.owasp.validator.html.CleanResults;
//
//import org.owasp.validator.html.Policy;
//
//import org.owasp.validator.html.PolicyException;
//
//import org.owasp.validator.html.ScanException;
//
//public class XssTestMain {
//
//public static void main(String[] args) {
//
//      try {
//
//            String POLICY_FILE_LOCATION = "/home/sunil/Desktop/OWASP/in.nic.cmf.security.owasp/src/main/resources/antisamy-esapi.xml";
//
//            String dirtyInput = "1%27%3balert%281%29)))))))))))))))))))))";
//
//            System.out.println("dirtyInput : \n"+dirtyInput);
//
//            Policy policy = Policy.getInstance(POLICY_FILE_LOCATION);
//
//            AntiSamy as = new AntiSamy(policy);
//
//            CleanResults cr = as.scan(dirtyInput, policy);
//
//            String cleanInput = cr.getCleanHTML();
//
//            System.out.println("\ncleanInput : \n"+cleanInput);
//
//      } catch (PolicyException e) {
//
//            // TODO Auto-generated catch block
//
//            e.printStackTrace();
//
//      } catch (ScanException e) {
//
//            // TODO Auto-generated catch block
//
//            e.printStackTrace();
//
//      }
//
//}
//
//}
//
// 
//
// 
//
//
// 
