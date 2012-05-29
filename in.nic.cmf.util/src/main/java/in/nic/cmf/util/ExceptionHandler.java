package in.nic.cmf.util;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.JoinPoint;

public class ExceptionHandler {

    private LogTracer logger;
    private String    domain;

    // public ExceptionHandler() {
    // }

    public ExceptionHandler(String domain) {
        this.domain = domain;
    }

    public ExceptionHandler(LogTracer logger) {
        this.logger = logger;
    }

    public void trace(JoinPoint joinPoint, Throwable ex)
            throws GenericException {
        logger.methodEntry(this.getClass().getSimpleName() + ".trace()");
        String exceptionmsg = "";
        try {
            exceptionmsg = ":::Error," + ex + "From the Class name is:::"
                    + joinPoint.getTarget().getClass().getName()
                    + "and  Method :::," + joinPoint.getSignature().getName();
            logger.debug("exceptionmsg ::::: " + exceptionmsg);
            for (int index = 0; index < joinPoint.getArgs().length; index++) {
                logger.debug(index + ")");
                paramLog(joinPoint.getArgs()[index]);
                exceptionmsg += " " + "param" + index + "="
                        + joinPoint.getArgs()[index] + "  ";
            }
        } catch (GenericException e) {
            logger.error(e.getMessage());
            throw e;
        }
        logger.debug("With Parameters:::," + exceptionmsg);
        logger.methodExit(this.getClass().getSimpleName() + ".trace()");
    }

    public void paramLog(Object obj) throws GenericException {
        logger.methodEntry(this.getClass().getSimpleName() + ".paramLog()");
        if (obj instanceof HashMap) {
            logger.debug("param is hashmap");
            HashMap hm = (HashMap) obj;
            Set keys = hm.keySet();
            for (Iterator i = keys.iterator(); i.hasNext();) {
                Integer key = (Integer) i.next();
                String value = (String) hm.get(key);
                logger.debug("HashMap Params ==> " + value);
            }
        } else if (obj instanceof ArrayList) {
            logger.debug("param is ArrayList");
            List listValue = new ArrayList();
            Iterator i = listValue.iterator();
            while (i.hasNext()) {
                logger.debug("Param value is ==> " + i.next().toString());
            }
        } /*
           * else if (obj instanceof Collections) {
           * logger.debug("param is Collections");
           * Collections inputCollections = (Collections) obj;
           * String xml = "";
           * try {
           * Utils utils = Utils.getInstance();
           * xml = utils.getCollectionsAsStringXml(inputCollections);
           * logger.debug("Collectons value is ==> " + xml);
           * } catch (GenericException e) {
           * logger.error(e.getMessage());
           * throw e;
           * }
           * }
           */
    }
}
