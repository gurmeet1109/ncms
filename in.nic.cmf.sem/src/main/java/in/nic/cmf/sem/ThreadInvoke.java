package in.nic.cmf.sem;

import in.nic.cmf.logger.LogTracer;

import java.util.Map;


public class ThreadInvoke implements Runnable {

   
    private Map           semable;

   
    private TwitterEngine target;
    private String        domain;
 
   
    private LogTracer     log;

    public ThreadInvoke(String domain, Map sem, TwitterEngine engine) {
        semable = sem;
        target = engine;
        this.domain = domain;
    }

    
    @Override
    public synchronized void run() {
        try {
            target.postMessage(domain, semable);
        } catch (Exception e) {
            System.out.println("run catch ");
            // log.error(e.getMessage());
        }
    }

}
