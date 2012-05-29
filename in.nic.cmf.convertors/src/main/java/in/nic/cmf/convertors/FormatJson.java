package in.nic.cmf.convertors;

// import in.nic.cmf.domainfree.GenModel;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;

public class FormatJson implements Convertor {
    // private static LogTracer log = new LogTracer(
    // "formatjson",
    // true,
    // true,
    // true,
    // true,
    // false);
    private LogTracer                          log;
    private static HashMap<String, FormatJson> hashFormatFlatten = new HashMap<String, FormatJson>();
    private String                             domain;

    private FormatJson(String domain) {
        this.domain = domain;
        setLogger(domain);
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "formatjson", true, true, true, true, false);
    }

    public static FormatJson getInstance(String domain) {
        if (!hashFormatFlatten.containsKey(domain)) {
            hashFormatFlatten.put(domain, new FormatJson(domain));
        }
        return hashFormatFlatten.get(domain);
    }

    @Override
    public HashMap in(Object o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ". in(Object o)");
        try {
            return in((String) o);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ": in(Object o)");
        }
    }

    @Override
    public GenModel in(String o, GenModel genmodel) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(String o, GenModel genmodel)");
        try {
            HashMap h = this.in((String) o);
            return GenModel.newInstance(domain, h);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(String o, GenModel genmodel)");
        }
    }

    @Override
    public GenModel in(HashMap o, GenModel genmodel) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(HashMap o, GenModel genmodel)");
        try {
            return GenModel.newInstance(domain, o);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(HashMap o, GenModel genmodel)");
        }
    }

    @Override
    public HashMap<String, Object> in(String o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ". in(String o)");
        try {
            JSONObject j = (JSONObject) JSONSerializer.toJSON(o);
            return jsonToHashMap(j);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", this.getClass()
                    .getSimpleName() + "in(String o)", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ":  in(String o)");
        }
    }

    @Override
    public HashMap<String, Object> in(File o) throws GenericException {
        throw new GenericException(domain, "ERR-CON-0003",
                "Unsupported Input Format");
    }

    @Override
    public Object out(HashMap map) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ". out(HashMap map)");
        try {
            JSONObject json = new JSONObject();
            JSONObject j = json.fromObject(map);
            return j;
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(HashMap map)");
        }
    }

    @Override
    public Object out(GenModel genModel) throws GenericException {
        throw new GenericException(domain, "ERR-CON-0003",
                "Unsupported Input Format");
    }

    private HashMap jsonToHashMap(JSONObject j) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". jsonToHashMap(JSONObject j)");
        try {
            Iterator i = j.entrySet().iterator();
            HashMap<String, Object> h = new HashMap<String, Object>();
            while (i.hasNext()) {
                Map.Entry e = (Map.Entry) i.next();
                if (e.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("JSONObject")) {
                    HashMap child = jsonToHashMap((JSONObject) e.getValue());
                    h.put((String) e.getKey(), child);
                } else if (e.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("JSONArray")) {
                    JSONArray ja = (JSONArray) e.getValue();
                    List list = jsonToHashMap(ja);
                    h.put((String) e.getKey(), list);
                } else {
                    log.debug("Key ::::: " + e.getKey() + " Value :::: "
                            + e.getValue());
                    String value = "";
                    if (e.getValue() instanceof Boolean) {
                        value = e.getValue().toString();
                    } else if (e.getValue() instanceof Integer) {
                        System.out.println("inside integer:" + e.getValue());
                        value = e.getValue().toString();
                        System.out.println("value:" + value);
                    } else {
                        value = (String) e.getValue();
                    }
                    h.put((String) e.getKey(), value);
                }
            }
            return h;
        } catch (Exception ge) {
            throw new GenericException(domain, "ERR-CON-0000", this.getClass()
                    .getSimpleName() + "jsonToHashMap(JSONObject j)", ge);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": jsonToHashMap(JSONObject j)");
        }
    }

    private List jsonToHashMap(JSONArray ja) {
        log.methodEntry(this.getClass().getSimpleName()
                + ". jsonToHashMap(JSONArray ja)");
        try {
            List l = new ArrayList<Object>();
            for (int i = 0; i < ja.size(); i++) {
                if (ja.get(i).getClass().getSimpleName()
                        .equalsIgnoreCase(("JSONObject"))) {
                    l.add(jsonToHashMap((JSONObject) ja.get(i)));
                } else if (ja.get(i).getClass().getSimpleName()
                        .equalsIgnoreCase(("JSONArray"))) {
                    l.add(jsonToHashMap((JSONArray) ja.get(i)));
                } else {
                    l.add(ja.get(i));
                }
            }
            return l;
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": jsonToHashMap(JSONArray ja)");
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String domain = "kalpana.in";
        HashMap hmap = new HashMap();
        hmap.put("Id", "abcdefghijklm");
        hmap.put("EntityType", "Article");
        List a = new ArrayList();
        a.add("test");
        a.add("test1");
        a.add("test2");
        hmap.put("Categories", a);

        File file = new File("/opt/cmf/resources/sample.json");
        InputStream is = new FileInputStream(file);
        // String s = IOUtils.toString(is);

        HashMap h = FormatJson.getInstance(domain).in(IOUtils.toString(is));
        System.out.println(h);

        String s = (String) FormatXml.getInstance(domain).out(h);
        System.out.println(s);
        // JSONObject j = (JSONObject)
        // FormatJson.getInstanceof(domain).out(hmap);
        // System.out.println(j);

        // x:["a","b","c"], y:[{"ab": "cd"},{"ef":"gh"}]},
        // z:[["a","b"],["b","c"]], d:{a:[["c","d"],["e","f"]]}
    }
}
