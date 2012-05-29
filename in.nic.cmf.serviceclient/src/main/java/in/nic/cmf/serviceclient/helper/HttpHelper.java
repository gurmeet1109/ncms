package in.nic.cmf.serviceclient.helper;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class HttpHelper {

    public HttpHelper(String domain) {
        this.domain = domain;
        // util = Utils.getInstanceof(domain);
        log = new LogTracer(domain, "serviceclient");
        pu = PropertiesUtil.getInstanceof(domain, "serviceclient");
        setUp();
        System.out
                .println("Thread name, connection time, execution time, hashcode, connInPool");
    }

    // private Utils util;
    private static LogTracer     log;
    private final PropertiesUtil pu;
    private String               domain;

    public Map<String, Object> execute(HttpUriRequest req) {
        return execute(req, "NONE");
    }

    public Map<String, Object> execute(HttpUriRequest req, String nameForLog) {
        int retryCounter = pu.getAsInt("retrycounter");
        for (int i = 1; i <= retryCounter; i++) {
            try {
                return execute(req, true, nameForLog);
            } catch (Exception e) {
                if (i < retryCounter) {
                    System.out.println("Inside Retry");
                    continue;
                }
                throw new GenericException(domain, "ERR-SC-0020",
                        "HttpHelper::execute() failed getting data", e);
            }
        }
        return null;
    }

    public Map<String, Object> execute2(HttpUriRequest req, boolean useless)
            throws Exception {

        Map<String, Object> response = null;
        try {
            Long l = System.currentTimeMillis();
            HttpResponse res = client.execute(req);
            logTime(l, "Time Taken for complete execute");
            response = asHash(res);
            return response;

        } catch (Exception e) {
            throw e;
        }
    }

    public Map<String, Object> execute(HttpUriRequest req, boolean useless,
            String nameForLog) throws Exception {
        log.methodEntry("execute entry");
        final HttpHost target = determineTarget(domain, req);
        final HttpRoute route = new HttpRoute(target, null, false);
        Map<String, Object> response = null;
        Long l = System.currentTimeMillis();
        ManagedClientConnection conn = getConnection(mgr, route);

        String logmsg = "  " + nameForLog + " Connection: "
                + (System.currentTimeMillis() - l) + " HashCode: "
                + conn.hashCode() + " ConnectionsInPool: "
                + mgr.getConnectionsInPool();
        l = System.currentTimeMillis() - l;
        try {

            Long l1 = System.currentTimeMillis();
            log.debug("Before execute:");
            HttpResponse res = execute(req, conn, target, httpExecutor,
                    httpProcessor, defaultParams, httpContext, nameForLog);
            log.debug("After execute:" + res);
            response = asHash(res);

            log.debug("After Hash:" + response);
            logTime(l1, "Execute: " + logmsg);

            conn.markReusable();
            conn.releaseConnection();
            // mgr.releaseConnection(conn, -1, null);
            log.methodExit("execute exit");
            return response;

        } catch (Exception e) {
            conn.abortConnection();
            throw e;
        }
    }

    public Map<String, Object> asHash(HttpResponse res) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            byte[] content = EntityUtils.toByteArray(res.getEntity());
            EntityUtils.consume(res.getEntity());
            Header[] headers = res.getAllHeaders();
            for (Header h : headers) {
                response.put(h.getName(), h.getValue());
            }
            response.put("statusCode", res.getStatusLine().getStatusCode());
            response.put("responseCode", res.getStatusLine().getStatusCode());
            response.put("responseStringBody",
                    IOUtils.toString(new ByteArrayInputStream(content)));
            response.put("responseByteBody", content);
        } catch (ParseException e) {
            throw new GenericException(domain, "ERR-SC-0020",
                    "Error parsing HTTP Response Content.", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-SC-0021",
                    "Error reading HTTP Response Content.", e);
        }
        return response;
    }

    /** The available schemes. */
    protected SchemeRegistry            supportedSchemes;

    /** The default parameters for the client side. */
    protected HttpParams                defaultParams;

    /** The HTTP processor for the client side. */
    protected BasicHttpProcessor        httpProcessor;

    /** The default context for the client side. */
    protected BasicHttpContext          httpContext;

    /** The request executor for the client side. */
    protected HttpRequestExecutor       httpExecutor;

    private ThreadSafeClientConnManager mgr;

    private ConnectionMonitorThread     monitor;

    private DefaultHttpClient           client;

    private void setUp() {

        if (defaultParams == null) {
            defaultParams = new SyncBasicHttpParams();
            // HttpProtocolParams.setVersion(defaultParams,
            // HttpVersion.HTTP_1_0);
        }

        if (supportedSchemes == null) {
            supportedSchemes = new SchemeRegistry();
            SchemeSocketFactory sf = PlainSocketFactory.getSocketFactory();
            // supportedSchemes.register(new Scheme("http", 80, sf));
            supportedSchemes.register(new Scheme(pu.get("scheme"), pu
                    .getAsInt("port"), sf));
        }

        if (httpProcessor == null) {
            httpProcessor = new BasicHttpProcessor();
            httpProcessor.addInterceptor(new RequestDefaultHeaders());

            // Required protocol interceptors
            httpProcessor.addInterceptor(new RequestContent());
            httpProcessor.addInterceptor(new RequestConnControl()); // optional
            httpProcessor.addInterceptor(new RequestTargetHost());
            // Recommended protocol interceptors
            httpProcessor.addInterceptor(new RequestClientConnControl());
            httpProcessor.addInterceptor(new RequestUserAgent());
            httpProcessor.addInterceptor(new RequestExpectContinue());
            // HTTP state management interceptors
            httpProcessor.addInterceptor(new RequestAddCookies());
            httpProcessor.addInterceptor(new ResponseProcessCookies());
        }

        // the context is created each time, it may get modified by test cases
        httpContext = new BasicHttpContext(null);

        if (httpExecutor == null) {
            httpExecutor = new HttpRequestExecutor();
        }

        mgr = createTSCCM();
        client = new DefaultHttpClient(mgr); // not used by default.

        monitor = new ConnectionMonitorThread(domain, mgr);
        // monitor.start();
    }

    /**
     * Executes a request.
     */
    private static HttpResponse execute(HttpRequest req,
            HttpClientConnection conn, HttpHost target,
            HttpRequestExecutor exec, HttpProcessor proc, HttpParams params,
            HttpContext ctxt, String nameForLog) throws Exception {

        ctxt.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
        ctxt.setAttribute(ExecutionContext.HTTP_TARGET_HOST, target);
        ctxt.setAttribute(ExecutionContext.HTTP_REQUEST, req);
        req.setParams(new DefaultedHttpParams(req.getParams(), params));

        exec.preProcess(req, proc, ctxt);

        Long l = System.currentTimeMillis();
        HttpResponse rsp = exec.execute(req, conn, ctxt);
        logTime(l, "Time Taken for actual Execute. " + nameForLog);

        rsp.setParams(new DefaultedHttpParams(rsp.getParams(), params));
        exec.postProcess(rsp, proc, ctxt);

        return rsp;
    }

    private boolean isPooled = true;

    private ManagedClientConnection getConnection(
            final ThreadSafeClientConnManager mgr, final HttpRoute route,
            long timeout, TimeUnit unit) throws ConnectionPoolTimeoutException,
            InterruptedException, IOException {

        ClientConnectionRequest connRequest = mgr
                .requestConnection(route, null);

        if (!isPooled) {
            synchronized (this) {
                Long l = System.currentTimeMillis();
                for (int i = 1; i < 100; i++) {
                    connRequest.getConnection(timeout, unit);
                }
                isPooled = true;
                logTime(l, "Time Taken for pre opening connections.");
            }
        }

        ManagedClientConnection conn = connRequest.getConnection(timeout, unit);
        if (conn.isStale()) {
            log.debug("Stale connection found. Closing & Reopening Connection.");
            conn.close();
        }

        if (!conn.isOpen()) {
            conn.open(route, httpContext, defaultParams);
        }

        return conn;
    }

    private ManagedClientConnection getConnection(
            final ThreadSafeClientConnManager mgr, final HttpRoute route)
            throws ConnectionPoolTimeoutException, InterruptedException,
            IOException {
        return getConnection(mgr, route, -1, null);
    }

    private ThreadSafeClientConnManager createTSCCM() {
        SchemeRegistry schreg = supportedSchemes;
        return createTSCCM(schreg);
    }

    private ThreadSafeClientConnManager createTSCCM(SchemeRegistry schreg) {
        if (schreg == null) schreg = supportedSchemes;
        return createTSCCM(schreg, pu.getAsLong("connttl"), TimeUnit.SECONDS);
    }

    private ThreadSafeClientConnManager createTSCCM(SchemeRegistry schreg,
            long connTTL, TimeUnit connTTLTimeUnit) {

        if (schreg == null) schreg = supportedSchemes;

        ThreadSafeClientConnManager cm = null;
        cm = new ThreadSafeClientConnManager(schreg, connTTL, connTTLTimeUnit);
        cm.setDefaultMaxPerRoute(pu.getAsInt("maxperroute"));
        cm.setMaxTotal(pu.getAsInt("maxtotal"));

        return cm;
    }

    private static HttpHost determineTarget(String domain,
            HttpUriRequest request) throws ClientProtocolException {
        // A null target may be acceptable if there is a default target.
        // Otherwise, the null target is detected in the director.
        HttpHost target = null;

        URI requestURI = request.getURI();
        if (requestURI.isAbsolute()) {
            target = URIUtils.extractHost(requestURI);
            if (target == null) {
                throw new GenericException(domain, "ERR-SC-0022",
                        "URI does not specify a valid host name: " + requestURI);
            }
        }
        return target;
    }

    protected void finalize() throws Throwable {
        monitor.shutdown();
        mgr.shutdown();
    }

    private static class ConnectionMonitorThread extends Thread {

        private final ClientConnectionManager connMgr;
        private volatile boolean              shutdown;

        private final PropertiesUtil          pu;

        public ConnectionMonitorThread(String domain,
                                       ClientConnectionManager connMgr) {
            super();
            pu = PropertiesUtil.getInstanceof(domain, "serviceclient");
            this.connMgr = connMgr;
        }

        public void run() {
            try {
                Long sleep = pu.getAsLong("idleconncheckfreq") * 1000;
                Long timeout = pu.getAsLong("idleconntimeout");
                while (!shutdown) {
                    synchronized (this) {
                        wait(sleep);
                        connMgr.closeExpiredConnections();
                        connMgr.closeIdleConnections(timeout, TimeUnit.SECONDS);
                    }
                }
            } catch (Exception ex) {
                // just ignore.
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        String domain = "sify.in";
        HttpHelper h = new HttpHelper(domain);
        try {
            HttpGet httpget = new HttpGet();
            String uri;
            // uri =
            // "http://124.7.228.172:8080/dms-1.0.0/ramya.in/lj0wdbeiiibib.xml";
            uri = "http://124.7.228.172:8080/dms-1.0.0/ramya.in/lj1n0Beedghfc.xls";
            // uri =
            // "http://124.7.228.172:8080/dms-1.0.0/ramya.in/lj1njHeiebhab.xml";
            // uri =
            // "http://124.7.228.236:8080/dataservices/kavitha.com/media/llolzeihjegig.xls";
            // uri =
            // "http://124.7.228.236:8600/dms-1.0.0/kavitha.com/llolzeihjegig.xls";

            // uri =
            // "http://119.226.225.127:8080/dataservices/app/honda.in/llorHShhgbgij.xls";
            // uri =
            // "http://124.7.228.172:8080/dms-1.0.0/tngov.in/llorHShhgbgij.xml";

            // uri = "http://localhost/1.html";
            // uri =
            // "http://124.7.228.58:8983/solr/cmf/nic.in/select?q=&entitytype=DomainEntity";
            // uri =
            // "http://124.7.228.172:8080/searchengine-1.0.0/ramya.in/search?q=";

            httpget.setURI(new URI(uri));
            httpget.addHeader("Connection", HTTP.CONN_KEEP_ALIVE);
            Long l1 = System.currentTimeMillis();
            for (int i = 0; i < 30; i++) {
                Long l = System.currentTimeMillis();
                Map<String, Object> hm = h.execute(httpget);
                System.out.println("Count:" + i + " : "
                        + hm.get("responseCode"));
                System.out.println(hm.get("responseStringBody"));

                logTime(l, "Count:>" + i + "< ");
                // System.out.println(hm);
            }
            System.out.println((System.currentTimeMillis() - l1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void logTime(long startTime, String action) {
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println("TimeTaken: " + timeTaken + "; " + action);
        log.debug("CurrentTime: " + System.currentTimeMillis() + "; TimeTaken:"
                + timeTaken + "; " + action);

    }

}
