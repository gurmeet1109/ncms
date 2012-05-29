/*package in.nic.cmf.analytics;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

*//**
 * The Class FileRead.
 *//*
class FileRead implements StatefulJob {
    private static PropertiesUtil proputil;
    private static LogTracer      log;
    private Map<String, Object>   hRequestParam = new HashMap<String, Object>();
    private String                domain        = "";

    private void fileAggregation() throws Exception {
        // FileRead read = new FileRead();
        readFiles();
        processStatsFiles();
    }

    *//**
     * This method will be used triger the main aggregation process.
     * @param cntxt
     *            to hold JobExecutionContext as value.
     *//*
    public final void execute(final JobExecutionContext cntxt) {
        log.debug("Generating report - "
                + cntxt.getJobDetail().getJobDataMap().get("type"));
        try {
            FileRead fread = new FileRead();
            fread.fileAggregation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        // TODO Auto-generated method stub
        doAutoAuth();
        if (hRequestParam.size() > 0) {

            // SchedulerFactory schedFact = new
            // org.quartz.impl.StdSchedulerFactory();
            // Scheduler sched = schedFact.getScheduler();
            // sched.start();
            // JobDetail jobDetail = new JobDetail("Income Report",
            // "Report Generation", FileRead.class);
            // jobDetail.getJobDataMap().put("type", "FULL");
            // CronTrigger trigger = new CronTrigger("Income Report",
            // "Report Generation");
            // trigger.setCronExpression(property
            // .getProperty("org.quartz.CronTrigger.sched"));
            //
            // sched.scheduleJob(jobDetail, trigger);
            try {
                fileAggregation();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            log.debug("Auto Login Failed.");
        }
    }

    *//**
     * This is the main method to execute/trigger the feed aggregation.
     * @param args
     *            as input value which is type of {@link java.io.Exception}
     *            class.
     *//*
    public static void main(final String[] args) {
        try {
            FileRead fread = new FileRead();
            proputil = PropertiesUtil.getInstanceof(args[0], "analyticsapp");
            if (args[0] != null) {
                proputil = PropertiesUtil
                        .getInstanceof(args[0], "analyticsapp");
                log = new LogTracer(args[0], "analyticsapp");
                fread.setDomain(args[0]); // proputil
            }
            fread.initialize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processStatsFiles() throws Exception {
        String statsDir = proputil.getProperty("StatsLogCounterLocation") + "/"
                + getDomain();

        File file = new File(statsDir);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isFile()) {
                    // System.out.println(fileName + " --- " + files[i]);

                    Collections oCollections = getAllDataFromDataservices(fileName);
                    log.debug("Count  : " + oCollections.getCount());
                    log.debug("Size  : " + oCollections.getCollection().size());

                    if (oCollections.getCollection().size() > 0) {
                        Collections oCollection = new Collections();
                        Stats statsObject = (Stats) oCollections
                                .getCollection().get(0);

                        DateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd'T'hh:mm:ss'Z'");
                        Date date = new Date();

                        statsObject.setLastViewedDate(dateFormat.format(date));

                        statsObject.setViews(readStatsCountFile(files[i]
                                .toString()));
                        statsObject.setEntityType("Stats");
                        Collections oCollectionNew = new Collections();
                        oCollectionNew.add(statsObject);
                        writeCollectionData(oCollectionNew);
                        files[i].delete();
                    } else {
                        Collections oCollection = new Collections();
                        Stats statsObject = new Stats();
                        DateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd'T'hh:mm:ss'Z'");
                        Date date = new Date();
                        statsObject.setId(Uniqueid.getId());
                        statsObject.setEntityType("Stats");
                        statsObject.setContentId(fileName);
                        statsObject.setLastViewedDate(dateFormat.format(date));
                        statsObject.setViews(readStatsCountFile(files[i]
                                .toString()));
                        statsObject.setSite(getDomain());
                        oCollection.add(statsObject);
                        writeCollectionData(oCollection);
                        files[i].delete();
                    }

                }
            }
        }
    }

    private final boolean createDirectory(final String directoryName) {
        File dirname = new File(directoryName.trim());
        if (dirname.exists()) {
            return true;
        } else {
            return dirname.mkdirs();
        }
    }

    private final boolean ReadFile() { // Justin
        String directoryPath = proputil.getProperty("directoryPath") + "/"
                + getDomain();
        String copyHere = proputil.getProperty("copyFilePath") + "/"
                + getDomain();
        String statsDir = proputil.getProperty("StatsLogCounterLocation")
                + getDomain() + "/";
        createDirectory(directoryPath);
        createDirectory(copyHere);
        createDirectory(statsDir);

        File file = new File(directoryPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getAbsolutePath();
                if (files[i].isFile() == false) continue;
                if (fileName.endsWith(proputil.getProperty("fileNameEndsWith")))
                    continue;
                BufferedReader bufferedreader = null;
                try {
                    bufferedreader = new BufferedReader(new InputStreamReader(
                            new DataInputStream(new FileInputStream(fileName))));
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String line = null;

                try {
                    while ((line = bufferedreader.readLine()) != null) {
                        if (line.isEmpty()) continue;
                        if (line.contains(proputil.getProperty("contains")) == false)
                            continue;
                        Collections collection = new Collections();
                        EventData eventdata = createEventData(statsDir, line);
                        Analytics analytics = createAnalytics(eventdata);
                        collection.add(analytics);
                        // This will enable once the DS is ready ....
                        writeCollectionData(collection);
                        createArticlexml("/tmp/", analytics.getId(), collection);
                    }
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    if (bufferedreader != null) bufferedreader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private EventData createEventData(final String statsDir, final String line) { // Justin
        int startPoint = line.indexOf(proputil.getProperty("startPoint"));
        int endPoint = line.indexOf(proputil.getProperty("endPoint"));
        String data = line.substring(startPoint + 1, endPoint);
        String splitValue[] = data.split(proputil.getProperty("splitPoint"));
        EventData eventdata = new EventData();
        eventdata = preapreEventObject(splitValue, eventdata); // ***********
        if (isEmpty(eventdata.getContentId()) == false) {
            int views = 0;
            String statsFileName = statsDir + eventdata.getContentId();
            views = getStatsCount(statsFileName);
            if (views == -1) {
                views = getStatsCount(eventdata);
            }
            writeStatsInfo(statsFileName, views);
        }
        return eventdata;
    }

    private Analytics createAnalytics(final EventData eventdata) { // Justin
        Analytics analytics = new Analytics();
        analytics.setId(Uniqueid.getId());
        analytics.setEntityType(proputil.getProperty("entityType"));
        DateFormat dateFormat = new SimpleDateFormat(
                proputil.getProperty("dateFormat"));
        analytics.setCreatedDate(dateFormat.format(new Date()));
        analytics.setType(proputil.getProperty("formatType"));
        analytics.setEventData(eventdata);
        analytics.setSite(getDomain());
        analytics.setVersion("1.0");
        return analytics;
    }

    private void writeStatsInfo(final String statsFileName, final int statsCount) { // Justin
        try {
            BufferedWriter bufferedwritter = new BufferedWriter(new FileWriter(
                    statsFileName));
            bufferedwritter.write(statsCount);
            bufferedwritter.close();
        } catch (IOException ioe) {
            // To Do trace this error information for reference
        }
    }

    private int getStatsCount(final String fileName) { // Justin
        int statsCount = -1;
        if (new File(fileName).isFile()) {
            BufferedReader bufferedreader = null;
            try {
                bufferedreader = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String countValue = null;
            try {
                if ((countValue = bufferedreader.readLine()) != null) {
                    try {
                        statsCount = Integer.parseInt(countValue) + 1;
                    } catch (NumberFormatException nfe) {
                        statsCount = 0; // if exception occurs whats need to
                                        // do...
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if (bufferedreader != null) bufferedreader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return statsCount;
    }

    private int getStatsCount(final EventData eventdata) { // Justin
        int statsCount = -1;
        Collections collections = null;
        try {
            collections = getAllDataFromDataservices(eventdata.getContentId());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (collections.getCollection().size() > 0) {
            Stats stats = (Stats) collections.getCollection().get(0);
            try {
                statsCount = Integer.parseInt(stats.getViews()) + 1;

            } catch (NumberFormatException nfe) {
                statsCount = 0; // if exception occurs whats need to do...
            }
        } else {
            statsCount = 1;
        }
        return statsCount;
    }

    private boolean isEmpty(String value) { // Justin
        if (value != null) {
            value = value.trim();
            return value.isEmpty(); // true if length() is 0, otherwise false
        }
        return true;
    }

    private void readFiles() {
        try {
            log.debug("getDomain: " + getDomain());
            String dirPath = proputil.getProperty("directoryPath") + "/"
                    + getDomain();
            String copyHere = proputil.getProperty("copyFilePath") + "/"
                    + getDomain();
            String statsDir = proputil.getProperty("StatsLogCounterLocation")
                    + getDomain() + "/";
            createDirectory(dirPath);
            createDirectory(copyHere);
            createDirectory(statsDir);
            log.debug(dirPath + " -- " + copyHere + " -- " + statsDir);
            File file = new File(dirPath);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getAbsolutePath();
                    if (!fileName.endsWith("analytics.log")
                            && (files[i].isFile())) {
                        // System.out.println("processing FIle Name :" +
                        // fileName);
                        FileInputStream fstream = new FileInputStream(fileName);
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(in));
                        String strLine;

                        while ((strLine = br.readLine()) != null
                                && (strLine.length() > 2)) {
                            Collections oCollection = new Collections();
                            // System.out.println("String is "
                            // + strLine.toString());
                            if (strLine.toString().contains(
                                    "AnalyticsResourceController")) {
                                Analytics oAnalytics = new Analytics();
                                oAnalytics.setId(Uniqueid.getId());
                                // System.out.println("Analytics ID : "
                                // + oAnalytics.getId());
                                oAnalytics.setEntityType("Analytics");
                                DateFormat dateFormat = new SimpleDateFormat(
                                        "yyyy-MM-dd'T'hh:mm:ss'Z'");
                                Date date = new Date();

                                oAnalytics.setCreatedDate(dateFormat
                                        .format(date));
                                oAnalytics.setType("Web");
                                EventData oEvent = new EventData();
                                int startPoint = strLine.indexOf("[[");
                                int endPoint = strLine.indexOf("]]");
                                String data = strLine.substring(startPoint + 1,
                                        endPoint);
                                System.out.println("DATA :: " + data);
                                String splitValue[] = data.split("&");
                                oEvent = preapreEventObject(splitValue, oEvent);

                                if (oEvent.getContentId() != ""
                                        && oEvent.getContentId() != null) {

                                    int views = 0;
                                    log.debug("statsDir :" + statsDir);
                                    if (new File(statsDir
                                            + oEvent.getContentId()).isFile()) {
                                        log.debug("Already existing Data");
                                        String countValue = readStatsCountFile(statsDir
                                                + oEvent.getContentId());
                                        views = Integer.parseInt(countValue) + 1;
                                        writeStatsCountFile(
                                                statsDir
                                                        + oEvent.getContentId(),
                                                views + "");
                                    } else {
                                        log.debug("Already Not Existing.");
                                        Collections oCollections = getAllDataFromDataservices(oEvent
                                                .getContentId());
                                        if (oCollections != null) {
                                            log.debug("Collections not equal null.");
                                        }
                                        log.debug("Size : "
                                                + oCollections.getCollection()
                                                        .size());
                                        log.debug("getCount : "
                                                + oCollections.getCount());
                                        if (oCollections.getCollection().size() > 0) {
                                            Stats statsObject = (Stats) oCollections
                                                    .getCollection().get(0);
                                            views = Integer
                                                    .parseInt(statsObject
                                                            .getViews()) + 1;
                                            writeStatsCountFile(
                                                    statsDir
                                                            + statsObject
                                                                    .getContentId(),
                                                    views + "");
                                            log.debug("Writing the stats file now.");
                                        } else {
                                            log.debug("Stats Fresh NOW. ");
                                            views = 1;
                                            writeStatsCountFile(statsDir
                                                    + oEvent.getContentId(),
                                                    "1");

                                        }

                                    }
                                }
                                oAnalytics.setEventData(oEvent);
                                oAnalytics.setSite(getDomain());
                                oAnalytics.setVersion("1.0");
                                oCollection.add(oAnalytics);
                                // This will enable once the DS is ready ....
                                writeCollectionData(oCollection);
                                // createArticlexml("/tmp/", oAnalytics.getId(),
                                // oCollection);
                            }
                        }
                        in.close();
                        moveFile(dirPath, copyHere, files[i].getName());
                    }
                }
            }
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    private final void createArticlexml(final String srcDirectory,
            final String id, final Collections collection) throws Exception {
        // initialize();
        JAXBContext jc = JAXBContext.newInstance(Collections.class);
        Marshaller m = jc.createMarshaller();
        FileOutputStream fop = new FileOutputStream(new File(srcDirectory + "/"
                + id));
        m.marshal(collection, fop);

    }

    private String readStatsCountFile(final String fileName) {
        String str = "";
        String countValue = "";
        try {
            log.debug("File name is " + fileName);
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while ((str = in.readLine()) != null) {
                countValue = countValue + str;
            }
            in.close();
        } catch (IOException e) {
        }
        return countValue;
    }

    private void writeStatsCountFile(final String fileName, final String count) {
        try {
            // Create file
            FileWriter fstream = new FileWriter(fileName);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(count);
            // Close the output stream
            out.close();
        } catch (Exception e) {// Catch exception if any
            log.debug("Error: " + e.getMessage());
        }
    }

    private void moveFile(final String inDIr, final String outDir,
            final String filename) throws Exception {
        File file = new File(inDIr + "/" + filename);
        File dir = new File(outDir);
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (!success) {
            // System.out.println("NOT SUCESS");
        }
    }

    private EventData preapreEventObject(final String value[],
            final EventData oEvent) {
        for (int j = 0; j < value.length; j++) {
            System.out.println("VALUE >" + value[j]);
            if (value[j].startsWith("browser")) {
                String browser[] = value[j].split("=");
                oEvent.setBrowser(browser[1]);
            } else if (value[j].startsWith("contentid")) {
                String contentid[] = value[j].split("=");
                oEvent.setContentId(contentid[1]);
            } else if (value[j].startsWith("method")) {
                String method[] = value[j].split("=");
                oEvent.setMethod(method[1]);
            } else if (value[j].startsWith("ip")) {
                String ip[] = value[j].split("=");
                oEvent.setIp(ip[1]);
            } else if (value[j].startsWith("currenturl")) {
                String currenturl[] = value[j].split("=");
                oEvent.setCurrentPage(currenturl[1]);
            } else if (value[j].startsWith("referrer")) {
                String refferelid[] = value[j].split("=");
                oEvent.setReferrerPage(refferelid[1]);
            } else if (value[j].startsWith("protocol")) {
                String protocol[] = value[j].split("=");
                oEvent.setProtocol(protocol[1]);
            }
        }
        return oEvent;
    }

    private Collections getAllDataFromDataservices(String id)
            throws IOException, JAXBException {
        in.nic.cmf.domain.Collections<?> collections = new Collections();
        if (hRequestParam.size() > 1) {
            log.debug("Auto Singn Success -GET");
            ServiceClient sc = ServiceClient.getInstance("dataservices");
            String query = "q=&contentid=" + id + "&entitytype=stats";
            log.debug("Query is " + query);
            Map result = new HashMap();
            result = sc.get(getDomain(), query, hRequestParam);
            // System.out.println("actual result" + result);
            log.debug("Response message : "
                    + result.get("responseStringBody").toString());
            log.debug("Response Code : "
                    + result.get("responseCode").toString());
            JAXBContext jc = JAXBContext.newInstance(Collections.class);
            Unmarshaller um = jc.createUnmarshaller();
            StringBuffer xmlStr = new StringBuffer(result.get(
                    "responseStringBody").toString());
            collections = (Collections) um.unmarshal(new StreamSource(
                    new StringReader(xmlStr.toString())));
            return collections;
        } else {
            log.debug("Auto Login Failed -GET");
        }
        return collections;
    }

    *//**
     * @param domain
     * @return
     *//*
    private Map doAutoAuth() throws GenericException {
        DynamicAuthentication dyauth = new DynamicAuthentication("systemadmin",
                "5f4dcc3b5aa765d61d8327deb882cf99", getDomain());
        // need to handle generic exception - to stop the proces
        // try {
        if (dyauth.autoSignin()) {
            HashMap<String, Object> authResponseMap = (HashMap<String, Object>) FormatXml
                    .getInstance().in(dyauth.getAuthentication());
            log.debug("authResponseMap : " + authResponseMap);
            hRequestParam = UserContext.getInstance().getUserContext(
                    (HashMap<String, Object>) authResponseMap
                            .get("Authentication"));
            log.info("hRequestParam: " + hRequestParam);
            return hRequestParam;
        } else {
            log.info("Auto Login Fail.");
        }
        // } catch (Exception e) {
        // log.info("Exception occured" + e);
        // }
        return hRequestParam;
    }

    *//**
     * Write collection data.
     * @param collection
     *            the collection
     * @throws Exception
     *             the exception
     *//*
    private final void writeCollectionData(final Collections collection)
            throws GenericException {

        if (hRequestParam.size() > 1) {
            ServiceClient sc = ServiceClient.getInstance("dataservices");
            String contentType = "application/xml";

            Map result = new HashMap();
            result = sc.post(getDomain(), collection, hRequestParam,
                    contentType);
            log.debug("Response message : "
                    + result.get("responseStringBody").toString()
                    + "Response Code : "
                    + result.get("responseCode").toString());

            //
            //
            // JAXBContext context = JAXBContext.newInstance(Collections.class);
            // Marshaller marshaller = context.createMarshaller();
            // StringWriter stringWriter = new StringWriter();
            // marshaller.marshal(collection, stringWriter);
            // String body = stringWriter.toString();
            // System.out.println("Content is :" + body);
            // URL postUrl = new URL(property.getProperty("RestUrl")
            // + property.getProperty("domain") + "/" + userDetails);
            // System.out.println(property.getProperty("RestUrl")
            // + property.getProperty("domain") + "/" + userDetails);
            // HttpURLConnection connection = (HttpURLConnection) postUrl
            // .openConnection();
            // connection.setDoOutput(true);
            // connection.setInstanceFollowRedirects(false);
            // connection.setRequestMethod("POST");
            // connection.setRequestProperty("Content-Type", "application/xml");
            // OutputStream os = connection.getOutputStream();
            // os.write(body.getBytes());
            // os.flush();
            // System.out.println("Response message : "
            // + connection.getResponseMessage());
            // System.out.println("Response code : "
            // + connection.getResponseCode());
            // System.out.println(connection.getRequestMethod());
            // connection.disconnect();
        } else {
            log.debug("Auto Sign in Failed. -POST");
        }
    }

    *//**
     * @param domain
     *            the domain to set
     *//*
    public void setDomain(String domain) {
        this.domain = domain;
    }

    *//**
     * @return the domain
     */
    /*public String getDomain() {
        return domain;
    }
}
*/

















































