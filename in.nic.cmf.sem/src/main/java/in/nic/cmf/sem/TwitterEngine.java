package in.nic.cmf.sem;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.sem.interfaces.IMarketingEngine;
import in.nic.cmf.util.Utils;

import java.util.HashMap;
import java.util.Map;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.http.AccessToken;

public class TwitterEngine implements IMarketingEngine {

    private PropertiesUtil                        prop;

    private LogTracer                             log;
    private String                                domain;
    private static HashMap<String, TwitterEngine> hashte = new HashMap<String, TwitterEngine>();

    public TwitterEngine(String domain) {
        System.out.println("7777777777");
        setLogger(domain);
        prop = PropertiesUtil.getInstanceof(domain, "sem");
        prop.getProperty(domain + "-consumerKey");

    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "sem");
    }

    public static TwitterEngine getInstanceof(String domain) {
        System.out.println("5555555555555");
        if (!hashte.containsKey(domain)) {
            System.out.println("666666666666");
            hashte.put(domain, new TwitterEngine(domain));
        }
        return hashte.get(domain);
    }

    public boolean processSemable(String domainName, Map sem) {

        log.debug("Enter into processSemable");
        log.debug("domain" + domainName);
        log.debug("sem" + sem);

        boolean returnValue = false;

        if ((domainName == null) || (domainName.isEmpty())
                || (domainName.trim() == "")) {
            domainName = "default";
        }
        if (prop.get(domainName + "-consumerKey") != null
                && prop.get(domainName + "-consumerSecret") != null
                && prop.get(domainName + "-token") != null
                && prop.get(domainName + "-tokenSecret") != null) {
            log.debug("Consumer Key::::"
                    + prop.get(domainName + "-consumerKey"));
            returnValue = true;
            try {

                if (returnValue) {

                    TwitterEngine twitt = new TwitterEngine(domainName);
                    Runnable runnable = new ThreadInvoke(domainName, sem, twitt);
                    new Thread(runnable).start();
                }
                log.debug("returnValue" + returnValue);
                return returnValue;
            } catch (Exception e) {
                log.error("Inside the catch block of Exception:::"
                        + e.getMessage());
                // throw new GenericException(domainName, "ERR-SEM-0000", this
                // .getClass().getSimpleName()
                // + ".threadAfterReturningAdvice()", e);
            } catch (Throwable e) {
                log.error("Inside the catch block of Throwable:::"
                        + e.getMessage());
            }

        }

        log.debug("returnValue::::;" + returnValue);

        return returnValue;
    }

    public void postMessage(String domainName, Map sem) throws GenericException {

        if ((domainName == null) || (domainName.isEmpty())
                || (domainName.trim() == "")) {
            domainName = "default";
        }

        try {
            log.debug("Enter in post message ");

            AccessToken accesstoken = new AccessToken(prop.get(domainName
                    + "-token"), prop.get(domainName + "-tokenSecret"));
            ConfigurationBuilder confbuilder = new ConfigurationBuilder();
            confbuilder
                    .setOAuthAccessToken(accesstoken.getToken())
                    .setOAuthAccessTokenSecret(accesstoken.getTokenSecret())
                    .setOAuthConsumerKey(prop.get(domainName + "-consumerKey"))
                    .setOAuthConsumerSecret(
                            prop.get(domainName + "-consumerSecret"));

            Twitter twitter = new TwitterFactory(confbuilder.build())
                    .getInstance();
            String description = "";
            description = (String) sem.get("Description");

            String surl = (String) sem.get("SeoUrl");
            Utils util = Utils.getInstanceof(domain);
            if (!util.isEmpty(surl)) {
                description += " " + surl.trim();
            }
            System.out.println(description.length());
            twitter.updateStatus(description);
            log.debug("Posted description" + description);
            log.debug("status updation success");
        } catch (TwitterException e) {
            log.debug("Inside exception:" + e.getMessage());
            e.printStackTrace();
            throw new GenericException(domain, "ERR-SEM-0002");
        }
    }

    public void setLogTracer(LogTracer sem) {

        this.log = sem;

    }

}
