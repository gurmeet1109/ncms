package in.nic.cmf.uniqueid;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.spi.InternalServerErrorException;

/**
 * The Class UniqueIDLogic creates the UniqueId with time based on every call of
 * this class.
 * @author Subodh C. Joshi
 */
public final class Uniqueid implements CMFService {
    /** The uniqueid. */
    private static Uniqueid uniqueid = null;
    /** The alphanum. */
    private static String[] alphanum = {"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
            "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"                };

    /** To make this as singleton object. */
    private Uniqueid() {

    }

    /**
     * get Unique id object.
     * @return the id
     */
    public static Uniqueid getInstance() {
        if (uniqueid == null) {
            uniqueid = new Uniqueid();
        }
        return uniqueid;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public static synchronized String getId() {
        return getDateId().concat(getMicroTimeId());
    }

    /**
     * Gets the id.
     * @param count
     *            the count
     * @return the id
     */
    public static synchronized String[] getId(final int count) {
        String[] gUniqueId = new String[count];
        for (int i = 0; i < count; i++) {
            gUniqueId[i] = getId();
        }
        // System.out.println(gUniqueId);
        return gUniqueId;
    }

    /**
     * Gets the date id.
     * @return the date id
     */
    public static String getDateId() {
        Date date = new Date();
        String dateFormatStyle = "yy-MM-dd-HH-mm-ss";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStyle);
        String dateFormat = sdf.format(date);
        String[] formatedDate = dateFormat.split("-");
        int i = 0;
        String year = formatedDate[i];
        String month = formatedDate[++i];
        String cDate = formatedDate[++i];
        String hour = formatedDate[++i];
        String minute = formatedDate[++i];
        String second = formatedDate[++i];
        return alphanum[Integer.parseInt(year)]
                .concat(alphanum[Integer.parseInt(month)])
                .concat(alphanum[Integer.parseInt(cDate)])
                .concat(alphanum[Integer.parseInt(hour)])
                .concat(alphanum[Integer.parseInt(minute)])
                .concat(alphanum[Integer.parseInt(second)]);
    }

    /**
     * Gets the micro time id.
     * @return the micro time id
     */
    public static String getMicroTimeId() {
        Long time = System.nanoTime();
        String timeValue = time.toString();
        String[] splitedValue = timeValue.split("");
        int i = 2 + 2 + 2;
        String m1 = "0" + splitedValue[i];
        String m2 = "0" + splitedValue[++i];
        String m3 = "0" + splitedValue[++i];
        String m4 = "0" + splitedValue[++i];
        String m5 = "0" + splitedValue[++i];
        String m6 = "0" + splitedValue[++i];
        String m7 = "0" + splitedValue[++i];
        return alphanum[Integer.parseInt(m1)]
                .concat(alphanum[Integer.parseInt(m2)])
                .concat(alphanum[Integer.parseInt(m3)])
                .concat(alphanum[Integer.parseInt(m4)])
                .concat(alphanum[Integer.parseInt(m5)])
                .concat(alphanum[Integer.parseInt(m6)])
                .concat(alphanum[Integer.parseInt(m7)]);
    }

    public Object add(String domain, String entity,
            HashMap<String, HashMap<String, Object>> queryParams,
            boolean containsBinary) {

        throw new GenericException(domain, "ERR-UNIQ-0001");

    }

    @Override
    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-UNIQ-0001");

    }

    @Override
    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-UNIQ-0001");

    }

    @Override
    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> queryParams) {

        Map<String, String> format = (Map<String, String>) queryParams.get(
                "input").get("queryParams");
        int count = Integer.parseInt(format.get("count"));
        // HashMap<String, Object> count1 = (HashMap<String, Object>)
        // count1.get("count");
        System.out.println("format=" + format.get("count"));
        /*
         * int count =
         * Integer.parseInt(queryParams.get("data").get("count").toString());
         * System.out.println("count="+count);
         * //String format = (String) queryParams.get("data").get("format");
         * if(null == format){
         * format = "xml";
         * }
         */
        Uniqueid uniqId = Uniqueid.getInstance();
        if (count == 0) {
            count = 1;
        }
        String[] uidData = new String[count];
        uidData = uniqId.getId(count);

        /*
         * for(int i=0;i<uidData.length;i++)
         * {
         * HashMap hashMap = new HashMap();
         * hashMap.put("Uniqueid", uidData[i]);
         * System.out.println(hashMap.get("Uniqueid"));
         * }
         * return uidData;
         */

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<Uniqueids>");
        try {
            for (int i = 0; i < uidData.length; i++) {
                String xmlFormUnique = "<Uniqueid>" + uidData[i]
                        + "</Uniqueid>";
                stringBuffer.append(xmlFormUnique);
            }
            stringBuffer.append("</Uniqueids>");
        } catch (Exception e) {
            throw new InternalServerErrorException("Internal Server Error");
        }

        Map<String, Object> output = new HashMap<String, Object>();
        output.put("statusCode", "200");
        output.put("content", stringBuffer.toString());
        queryParams.put("output", (HashMap<String, Object>) output);
        System.out.println("final query param" + queryParams);
        // return queryParams;
        // parameters.put("output", (HashMap<String, Object>) output);
        return queryParams;

        // return queryParams;

        // return stringBuffer.toString();

    }

    @Override
    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-UNIQ-0001");

    }

    @Override
    public void setLogTracer(LogTracer log) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, Map<String, Object>> add(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) {
        throw new GenericException(arg0, "ERR-UNIQ-0001");

    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-0000");
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }
}
