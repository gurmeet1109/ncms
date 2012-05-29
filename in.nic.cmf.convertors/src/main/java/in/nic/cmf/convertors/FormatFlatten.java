package in.nic.cmf.convertors;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

public class FormatFlatten implements Convertor {
    private ConvertorUtils                        cu                  = null;
    private HashMap                               mapInfo             = null;

    private LogTracer                             log;
    private String                                domain;
    private static HashMap<String, FormatFlatten> hashFormatFlatten   = new HashMap<String, FormatFlatten>();
    private static boolean                        isProperFlattenFlag = false;

    private void setLogger(String domain) {
        log = new LogTracer(domain, "FormatFlatten", true, true, true, true,
                false);
    }

    private FormatFlatten(String domain) {
        System.out.println("inside constr with one param");
        cu = ConvertorUtils.getInstance(domain);
        setLogger(domain);
        this.domain = domain;
    }

    public static FormatFlatten getInstance(String domain) {
        System.out.println("inside getinstance of 1 param");
        if (!hashFormatFlatten.containsKey(domain)) {
            System.out.println("inside getinstance of 1 param - notcontains");
            hashFormatFlatten.put(domain, new FormatFlatten(domain));
            System.out
                    .println("inside getinstance of 1 param - after putting to hash");
        }
        return hashFormatFlatten.get(domain);
    }

    public static FormatFlatten getInstance(String domain, boolean flag) {
        System.out.println("inside constr with 2 param");
        isProperFlattenFlag = flag;
        System.out.println("after setting flag");
        return getInstance(domain);
    }

    @Override
    public HashMap<String, Object> in(Object o) throws GenericException {
        HashMap<String, Object> inputHash = (HashMap<String, Object>) o;
        HashMap<String, Object> xmlmap = (HashMap<String, Object>) inputHash
                .get("Collections");

        HashMap<String, Object> hashMap = new HashMap();
        HashMap<String, Object> rootMap = new HashMap();
        String entityName = "";
        int i = 1;
        for (Entry<String, Object> e : xmlmap.entrySet()) {
            String key = (String) e.getKey();
            if (key.equals("Count")) {
                hashMap.put("Count", e.getValue());
            } else {
                entityName = key;
                HashMap<String, String> fl = new HashMap<String, String>();
                HashMap<String, Object> fl1 = new HashMap<String, Object>();
                if (cu.isArrayList(e.getValue())) {
                    log.debug("inside arraylist");
                    List<HashMap<String, String>> flattenedListOfHash = new ArrayList<HashMap<String, String>>();
                    List<HashMap<String, Object>> entityListOfHash = (List<HashMap<String, Object>>) e
                            .getValue();
                    for (HashMap<String, Object> eachEntityHash : entityListOfHash) {
                        fl = new HashMap<String, String>();
                        try {
                            flatten(eachEntityHash, fl, fl1);
                        } catch (JSONException e1) {
                            throw new GenericException(domain, "ERR-CON-0000",
                                    getClass().getSimpleName());
                        }
                        flattenedListOfHash.add((HashMap<String, String>) fl);
                    }
                    hashMap.put(entityName, flattenedListOfHash);
                } else if (cu.isHashMap(e.getValue())) {
                    try {
                        log.debug("inside hashmap");
                        flatten((HashMap<String, Object>) e.getValue(), fl, fl1);
                    } catch (JSONException e1) {
                        throw new GenericException(domain, "ERR-CON-0000",
                                getClass().getSimpleName());
                    }
                    hashMap.put(entityName, fl);
                }
            }
            i++;
        }

        if (hashMap.size() != 0) {
            rootMap.put("Collections", hashMap);
        } else {
            return xmlmap;
        }

        return rootMap;
    }

    public HashMap<String, Object> in(Object o, boolean hasArrayListFlag)
            throws GenericException {
        HashMap<String, Object> inputHash = (HashMap<String, Object>) o;
        HashMap<String, Object> xmlmap = (HashMap<String, Object>) inputHash
                .get("Collections");

        HashMap<String, Object> hashMap = new HashMap();
        HashMap<String, Object> rootMap = new HashMap();
        String entityName = "";
        int i = 1;
        for (Entry<String, Object> e : xmlmap.entrySet()) {
            String key = (String) e.getKey();
            if (key.equals("Count")) {
                hashMap.put("Count", e.getValue());
            } else {
                entityName = key;
                HashMap<String, String> fl = new HashMap<String, String>();
                HashMap<String, Object> fl1 = new HashMap<String, Object>();
                if (cu.isArrayList(e.getValue())) {
                    List<HashMap<String, String>> flattenedListOfHash = new ArrayList<HashMap<String, String>>();
                    List<HashMap<String, Object>> flattenedListOfHash1 = new ArrayList<HashMap<String, Object>>();
                    List<HashMap<String, Object>> entityListOfHash = (List<HashMap<String, Object>>) e
                            .getValue();
                    for (HashMap<String, Object> eachEntityHash : entityListOfHash) {
                        fl = new HashMap<String, String>();
                        fl1 = new HashMap<String, Object>();
                        try {
                            flatten(eachEntityHash, fl, fl1);
                        } catch (JSONException e1) {
                            throw new GenericException(domain, "ERR-CON-0000",
                                    getClass().getSimpleName());
                        }
                        if (hasArrayListFlag) {
                            flattenedListOfHash1
                                    .add((HashMap<String, Object>) fl1);
                        } else {
                            flattenedListOfHash
                                    .add((HashMap<String, String>) fl);
                        }

                    }
                    if (hasArrayListFlag) {
                        hashMap.put(entityName, flattenedListOfHash1);
                    } else {
                        hashMap.put(entityName, flattenedListOfHash);
                    }
                } else if (cu.isHashMap(e.getValue())) {
                    try {
                        flatten((HashMap<String, Object>) e.getValue(), fl, fl1);
                    } catch (JSONException e1) {
                        throw new GenericException(domain, "ERR-CON-0000",
                                getClass().getSimpleName());
                    }
                    if (hasArrayListFlag) {
                        hashMap.put(entityName, fl1);
                    } else {
                        hashMap.put(entityName, fl);
                    }
                }
            }
            i++;
        }

        if (hashMap.size() != 0) {
            rootMap.put("Collections", hashMap);
        } else {
            return xmlmap;
        }
        return rootMap;
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
    public HashMap<String, Object> in(String o) throws GenericException {
        FormatXml formatXml = FormatXml.getInstance(domain);
        HashMap<String, Object> xmlmap = (HashMap<String, Object>) formatXml
                .in(o).get("Collections");
        HashMap<String, Object> finalHash = in(formatXml.in(o));
        return finalHash;
    }

    public HashMap<String, Object> in(String o, boolean flag)
            throws GenericException {
        FormatXml formatXml = FormatXml.getInstance(domain);
        HashMap<String, Object> xmlmap = (HashMap<String, Object>) formatXml
                .in(o).get("Collections");
        HashMap<String, Object> finalHash = in(formatXml.in(o), flag);
        return finalHash;
    }

    @Override
    public HashMap<String, Object> in(File o) throws GenericException {
        throw new GenericException(domain, "ERR-CON-0003",
                "Unsupported Input Format");
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
    public HashMap<String, String> out(HashMap<String, Object> map)
            throws GenericException {
        throw new GenericException(domain, "ERR-CON-0002",
                "This class needs a Mapper. Use in(Flattened Hash, Mapper).");
    }

    @Override
    public Object out(GenModel genModel) throws GenericException {
        throw new GenericException(domain, "ERR-CON-0003",
                "Unsupported Input Format");
    }

    public HashMap out(Object o, HashMap mapper) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(Object o, HashMap mapper)");
        try {
            return FlatHashToProperHash((Map) o, mapper);
        } catch (GenericException ge) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), ge);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(Object o, HashMap mapper)");
        }
    }

    private ArrayList getListFromString(String key, String name, Object value,
            HashMap h) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getListFromString(String key, String name, Object value,HashMap h)");
        try {
            HashMap h1 = new HashMap();
            List objlist = new ArrayList();
            if (h.get(key) != null) {
                List slist = (List) h.get(key);
                int j = 0;
                for (Object s : slist) {
                    if (j == 0) {
                        h1 = (HashMap) slist.get(j);
                        h1.put(name, value);
                        objlist.add(h1);
                    } else {
                        objlist.add(slist.get(j));
                    }
                    j++;
                }
                mapInfo.put(name, new ArrayList());
            } else {
                // System.out.println(value);
                h1 = new HashMap();
                h1.put(name, value);
                objlist.add(h1);
            }
            return (ArrayList) objlist;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  getListFromString(String key, String name, Object value,HashMap h)");
        }
    }

    private ArrayList getListFromList(String objectListKey, String name,
            List<String> value, HashMap h) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".  getListFromList(String objectListKey, String name,List<String> value, HashMap h)");
        try {
            HashMap h1 = new HashMap();
            List objlist = new ArrayList();
            if (h.get(objectListKey) != null) {
                List slist = (List) h.get(objectListKey);
                int j = 0;
                for (String v : value) {
                    if (j < slist.size()) {
                        h1 = (HashMap) slist.get(j);
                        h1.put(name, v);
                    } else {
                        h1 = new HashMap();
                        h1.put(name, v);
                    }
                    objlist.add(h1);
                    j++;
                }
                mapInfo.put(name, new ArrayList());
            } else {
                for (String v : value) {
                    h1 = new HashMap();
                    h1.put(name, v);
                    objlist.add(h1);
                }
            }
            return (ArrayList) objlist;
        } catch (GenericException ge) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), ge);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getListFromList(String objectListKey, String name,List<String> value, HashMap h)");
        }
    }

    private HashMap getMultiObjectHashMap(Map hmap, HashMap fieldTypes,
            HashMap fieldMaps) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getMultiObjectHashMap(Map hmap, HashMap fieldTypes,	HashMap fieldMaps)");
        try {
            HashMap objfieldsMap = getObjectFieldsHashMap(fieldTypes, fieldMaps);
            Iterator it = hmap.entrySet().iterator();
            HashMap h = new HashMap();
            HashMap rootMulti = new HashMap();
            while (it.hasNext()) {
                String objectListKey = "";
                Map.Entry pairs = (Map.Entry) it.next();
                String name = pairs.getKey().toString();
                // System.out.println(name);
                Iterator i = objfieldsMap.entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry fpairs = (Map.Entry) i.next();
                    List<String> flist = (ArrayList) fpairs.getValue();
                    if (flist.contains(name)) {
                        objectListKey = fpairs.getKey().toString();
                        List val = (List) hmap.get(name);
                        if (fieldTypes.get(objectListKey).equals(
                                "IsMultiObject")) {
                            if (fieldTypes.get(name).equals("String")
                                    || fieldTypes.get(name).equals("Boolean")) {
                                List objlist = getListFromString(objectListKey,
                                        name, val.get(0), h);
                                h.put(objectListKey, objlist);
                            } else if (fieldTypes.get(name).equals("List")) {
                                List<String> value = null;
                                if (val.toString().contains(",")
                                        && val.size() <= 1) {
                                    String sval = (String) val.get(0);
                                    value = getValueAsList((Object) sval);

                                } else {
                                    value = getValueAsList(hmap.get(name));
                                }
                                // System.out.println(value.size());
                                List objlist = getListFromList(objectListKey,
                                        name, value, h);
                                // System.out.println(objlist);
                                h.put(objectListKey, objlist);
                            } else if (fieldTypes.get(name).equals("IsMulti")) {
                                // System.out
                                // .println("Inside Multi Object IsMulti");
                                List<String> value = null;
                                if (val.toString().contains(",")
                                        && val.size() <= 1) {
                                    String sval = (String) val.get(0);
                                    value = getValueAsList((Object) sval);

                                } else {
                                    value = getValueAsList(hmap.get(name));
                                }
                                List objlist = getListFromString(objectListKey,
                                        name, value, h);
                                h.put(objectListKey, objlist);
                            }
                        }
                    }
                }
            }
            return h;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getMultiObjectHashMap(Map hmap, HashMap fieldTypes,	HashMap fieldMaps)");
        }
    }

    private HashMap getObjectFieldsHashMap(HashMap fieldTypes, HashMap fieldMaps)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getObjectFieldsHashMap(HashMap fieldTypes, HashMap fieldMaps)");
        try {
            Iterator i = fieldTypes.entrySet().iterator();
            HashMap h = new HashMap();
            while (i.hasNext()) {
                Map.Entry typePairs = (Map.Entry) i.next();
                String key = typePairs.getKey().toString();
                String value = typePairs.getValue().toString();
                if (value.equals("IsMultiObject") || value.equals("Object")) {
                    Iterator it = fieldMaps.entrySet().iterator();
                    List<String> fieldList = new ArrayList();
                    while (it.hasNext()) {
                        Map.Entry mapPairs = (Map.Entry) it.next();
                        String name = mapPairs.getValue().toString();
                        List<String> a = Arrays.asList(name.split("/"));
                        for (int j = 0; j < a.size(); j++) {
                            if (a.get(j).equals(key)) {
                                if (!fieldList.contains(a.get(j + 1)))
                                    fieldList.add(a.get(j + 1));
                            }
                        }
                    }
                    h.put(key, fieldList);
                }
            }
            return h;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getObjectFieldsHashMap(HashMap fieldTypes, HashMap fieldMaps)");
        }
    }

    public HashMap FlatHashToProperHash(Map hmap, HashMap mapper)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".  formDataToHashMap(Map hmap, HashMap mapper)");
        try {
            // System.out.println("Inside FormDatatoHashMap");
            HashMap fieldTypes = (HashMap) mapper.get("typeMapping");
            HashMap fieldMaps = (HashMap) mapper.get("fieldMapping");
            // System.out.println("Field Types **** " + fieldTypes);
            HashMap rootValMap = new HashMap();
            mapInfo = (HashMap) hmap;
            rootValMap = getMultiObjectHashMap(hmap, fieldTypes, fieldMaps);
//            Object value = null;
            // System.out.println("Map Info **** " + mapInfo);
            Iterator it = mapInfo.entrySet().iterator();
            while (it.hasNext()) {
                Object value = null;
                Map.Entry pairs = (Map.Entry) it.next();
                String name = pairs.getKey().toString();
                if (fieldTypes.containsKey(name)) {
                    // System.out.println(name + "*****" +
                    // fieldTypes.get(name));
                    List<String> val = (List) hmap.get(name);
                    // System.out.println(val.size());
                    // if (!val.isEmpty() && val != null) {
                    if (fieldMaps.containsKey(name)) {
                        if (fieldTypes.get(name).equals("List")) {
                            if (val.toString().contains(",") && val.size() <= 1) {
                                String sval = (String) val.get(0);
                                value = getValueAsList((Object) sval);

                            } else {
                                value = getValueAsList(val);
                            }
                        } else if (fieldTypes.get(name).equals("String")
                                || fieldTypes.get(name).equals("Boolean")
                                || fieldTypes.get(name).equals("File")) {
                            value = val.get(0);
                        } else if (fieldTypes.get(name).equals("JsonString")) {
                            // System.out.println("JSONString *************** "
                            // + val.get(0));
                            if (!val.get(0).isEmpty()) {
                                HashMap v = FormatJson.getInstance(domain).in(
                                        val.get(0));
                                value = v.get(name);
                            }
                        }

                        HashMap children = fillData(value,
                                (String) fieldMaps.get(name), rootValMap,
                                fieldTypes);
                        Iterator i = children.entrySet().iterator();
                        while (i.hasNext()) {
                            Map.Entry childKeyVal = (Map.Entry) i.next();
                            rootValMap.put(childKeyVal.getKey().toString(),
                                    childKeyVal.getValue());
                        }
                    } else {
                        if (fieldTypes.get(name).equals("List")) {
                            if (val.toString().contains(",") && val.size() <= 1) {
                                String sval = (String) val.get(0);
                                value = getValueAsList((Object) sval);

                            } else {
                                value = getValueAsList(hmap.get(name));
                            }
                            rootValMap.put(name, value);
                        } else if (fieldTypes.get(name).equals("String")
                                || fieldTypes.get(name).equals("Boolean")
                                || fieldTypes.get(name).equals("File")) {
                            rootValMap.put(name, val.get(0));
                        } else if (fieldTypes.get(name).equals("JsonString")) {
                            if (!val.get(0).isEmpty()) {
                                HashMap v = FormatJson.getInstance(domain).in(
                                        val.get(0));
                                rootValMap.put(name, v.get(name));
                            } else {
                                rootValMap.put(name, value);
                            }
                        } else {
                            rootValMap.put(name, value);
                        }
                    }
                    // }
                }
            }
            System.out.println(rootValMap);
            return rootValMap;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  formDataToHashMap(Map hmap, HashMap mapper)");
        }
    }

    private ArrayList getValueAsList(Object values) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getValueAsList(Object values)");
        try {
            // System.out.println("Inside getvalueaslist object");
            if (values.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
                return getValueAsList((List) values);
            } else if (values.getClass().getSimpleName()
                    .equalsIgnoreCase("String")) {
                // System.out.println("Inside else *** " + values);
                return getValueAsList((String) values);
            }
            return null;
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":getValueAsList(Object values)");
        }
    }

    private ArrayList getValueAsList(List values) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getValueAsList(List values)");
        try {
            // System.out.println("Inside getvalueaslist list");
            return cu.convertAnyListToArrayList(values);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getValueAsList(List values)");
        }
    }

    private ArrayList getValueAsList(String listvalue) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getValueAsList(String listvalue)");
        try {
            // System.out.println("Inside getvalueaslist string");
            ArrayList a = new ArrayList<String>();
            if (!listvalue.equals("")) {
                if (listvalue.contains(",")) {
                    a = cu.convertAnyListToArrayList(Arrays.asList(listvalue
                            .split(",")));
                } else {
                    a.add(listvalue);
                }
            } else {
                return null;
            }
            return a;
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getValueAsList(String listvalue)");
        }
    }

    private HashMap fillData(Object value, String path, HashMap master,
            HashMap fieldTypes) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". fillData(Object value, String path, HashMap master,HashMap fieldTypes)");
        try {
            List<String> a = Arrays.asList(path.split("/"));
            ArrayList<String> lPath = cu.convertAnyListToArrayList(Arrays
                    .asList(path.split("/")));
            Object data = value;
            boolean isTopParent = false;
            for (int i = a.size() - 1; i >= 0; i--) {
                HashMap parent = getHashMapFromMaster((String) a.get(i),
                        fieldTypes, lPath, master);
                if (i == 0) {
                    isTopParent = true;
                }
                if (fieldTypes.get(a.get(i)).equals("IsMultiObject")) {
                    List multiData = (ArrayList) master.get(a.get(i));
                    HashMap data1 = new HashMap();
                    data1.put(a.get(i), multiData);
                    data = data1;
                    master.remove(a.get(i));
                } else {
                    data = fillData((String) a.get(i), data, parent,
                            isTopParent);

                }
                lPath.remove(a.get(i));
            }
            return (HashMap) data;
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  fillData(Object value, String path, HashMap master,HashMap fieldTypes)");
        }
    }

    private HashMap getValueAsObjectList(String key, Object value,
            HashMap parent) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getValueAsObjectList(String key, Object value,HashMap parent)");
        try {
            HashMap l = new HashMap();
            if (parent != null) {
                l = parent;
            }
            Iterator i = ((HashMap) value).entrySet().iterator();
            List list = new ArrayList();
            while (i.hasNext()) {
                Map.Entry childKeyVal = (Map.Entry) i.next();
                if (childKeyVal.getValue().getClass().getSimpleName()
                        .equalsIgnoreCase("ArrayList")) {
                    List<String> a = (ArrayList) childKeyVal.getValue();
                    for (String v : a) {
                        HashMap h = new HashMap();
                        h.put(childKeyVal.getKey(), v);
                        list.add(h);
                    }
                }
            }
            l.put(key, list);
            return l;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  getValueAsObjectList(String key, Object value,HashMap parent)");
        }

    }

    private HashMap getHashMapFromMaster(String currentKey, HashMap fieldTypes,
            List<String> path, HashMap master) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getHashMapFromMaster(String currentKey, HashMap fieldTypes,List<String> path, HashMap master)");
        try {
            String parent = path.get(path.size() - 1);

            if (path.size() >= 2) {
                parent = path.get(path.size() - 2);
            }
            HashMap h = master;
            for (String key : path) {
                if (h.containsKey(key)) {
                    if (!fieldTypes.get(key).equals("IsMultiObject")) {
                        h = (HashMap) h.get(key);
                        if (key.equalsIgnoreCase(parent)) {
                            return h;
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getHashMapFromMaster(String currentKey, HashMap fieldTypes,List<String> path, HashMap master)");
        }
    }

    private HashMap fillData(String key, final Object value,
            HashMap leafButOne, Boolean isTopParent) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ". fillData()");
        try {
            HashMap child = new HashMap();
            if (leafButOne != null && isTopParent == false) {
                child = leafButOne;
            }
            child.put(key, value);
            return child;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ": fillData()");
        }
    }

    // public HashMap<String, Object> flatten(Object o) throws GenericException,
    // JSONException {
    // log.methodEntry("Start flatten(String collectionString)");
    // log.debug("Input collections string ::: " + o);
    // HashMap<String, Object> collectionMap = (HashMap<String, Object>) o;
    // HashMap<String, Object> xmlmap = (HashMap<String, Object>) collectionMap
    // .get("Collections");
    // log.debug("Collection Hash :::: " + xmlmap);
    // HashMap<String, Object> hashMap = new HashMap();
    // HashMap<String, Object> rootMap = new HashMap();
    // String entityName = "";
    // for (Entry<String, Object> e : xmlmap.entrySet()) {
    // String key = (String) e.getKey();
    // if (key.equals("Count")) {
    // hashMap.put("Count", e.getValue());
    // } else if (!key.equals("Count")) {
    // entityName = key;
    // HashMap<String, String> fl = new HashMap<String, String>();
    // if (cu.isArrayList(e.getValue())) {
    // List<HashMap<String, String>> flattenedListOfHash = new
    // ArrayList<HashMap<String, String>>();
    // List<HashMap<String, Object>> entityListOfHash = (List<HashMap<String,
    // Object>>) e
    // .getValue();
    // for (HashMap<String, Object> eachEntityHash : entityListOfHash) {
    // fl = new HashMap<String, String>();
    // flatten((HashMap<String, Object>) eachEntityHash, fl);
    // flattenedListOfHash.add(fl);
    // }
    // hashMap.put(entityName, flattenedListOfHash);
    // } else if (cu.isHashMap(e.getValue())) {
    // flatten((HashMap<String, Object>) e.getValue(), fl);
    // hashMap.put(entityName, fl);
    // }
    // }
    // }
    // rootMap.put("Collections", hashMap);
    // log.debug("Root Map ::: " + rootMap);
    // log.methodExit("End flatten(String collectionString)");
    // return rootMap;
    // }

    public Object FileRead(String fileName) {
        InputStream is;
        String response = "";
        try {
            log.debug("inside file read");
            String path = "/opt/cmf/domains/" + domain + "/resources/"
                    + fileName;
            log.debug("picking json from : " + path);
            is = new FileInputStream(path);
            response = IOUtils.toString(is);
            log.debug("after converting to string");
        } catch (FileNotFoundException e) {
            throw new GenericException(domain, "ERR-GEN-0012", this.getClass()
                    .getSimpleName() + ":FileRead()", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-GEN-0000", this.getClass()
                    .getSimpleName() + ":FileRead()", e);
        }
        return response;
    }

    public void flatten(HashMap<String, Object> xmlmap,
            HashMap<String, String> flattened,
            HashMap<String, Object> flattened1) throws GenericException,
            JSONException {
        log.debug("Xml Map :::newone " + xmlmap);
        List<String> jsonStringList = new ArrayList<String>();
        HashMap<String, Object> jsonStringHash = (HashMap<String, Object>) FormatJson
                .getInstance(domain).in(FileRead("JsonStringFields.json"));
        jsonStringList = (List<String>) jsonStringHash.get("JsonStringFields");
        log.debug("JsonString List ::: " + jsonStringList);
        for (Entry<String, Object> e : xmlmap.entrySet()) {
            String key = (String) e.getKey();
            Object value = e.getValue();
            if (null == value) {
                flattened.put(key, "");
                flattened1.put(key, "");
                continue;
            }

            if (cu.isString(value)) {
                flatten(key, (String) value, flattened, flattened1);
            }

            if (cu.isHashMap(value)) {
                if (jsonStringList.contains(key) && !isProperFlattenFlag) {
                    objectItemProcessor(key, (HashMap<String, Object>) value,
                            flattened, flattened1);
                } else {
                    flatten((HashMap<String, Object>) value, flattened,
                            flattened1);
                }
            }

            if (cu.isArrayList(value)) {
                flatten(key, (ArrayList<Object>) value, flattened, flattened1);
            }
        }
    }

    public void flatten(String k, String v, HashMap<String, String> flattened,
            HashMap<String, Object> flattened1) {
        String newval = v;
        if (flattened.containsKey(k)) {
            v = flattened.get(k) + "," + v;
        }
        /*
         * else if (k.endsWith("Date") && !cu.isEmpty(v)) { v =
         * getISTFormatDate(v); }
         */
        flattened.put(k, v);

        if (flattened1.containsKey(k)) {
            ArrayList a = new ArrayList();
            if (cu.isArrayList(flattened1.get(k))) {
                a = (ArrayList) flattened1.get(k);

            } else {
                a.add(flattened1.get(k));
            }
            a.add(newval);
            flattened1.put(k, a);
        } else {
            flattened1.put(k, v);
        }

    }

    public void flatten(String key, ArrayList<Object> a,
            HashMap<String, String> flattened,
            HashMap<String, Object> flattened1) throws GenericException,
            JSONException {
        for (Object o : a) {
            if (cu.isString(o)) {
                flatten(key, (String) o, flattened, flattened1);
            }

            if (cu.isHashMap(o)) {
                flatten((HashMap<String, Object>) o, flattened, flattened1);
            }

            if (cu.isArrayList(o)) {
                flatten(key, (ArrayList<Object>) o, flattened, flattened1);
            }
        }

    }

    public void objectItemProcessor(String key, HashMap<String, Object> value,
            HashMap<String, String> flattened,
            HashMap<String, Object> flattened1) throws JSONException {
        System.out.println("HashMap Value :::: " + value);
        FormatJson f = FormatJson.getInstance(domain);
        JSONObject json = (JSONObject) f.out(value);
        String jsonString = json.toString();
        String replaceString = jsonString.replaceAll("null", "\\\"\\\"");
        flatten(key, replaceString, flattened, flattened1);
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String domain = "kalpana.in";
        FormatJson formatJson = FormatJson.getInstance(domain);
        InputStream is = new FileInputStream(formatJson.getClass()
                .getClassLoader().getResource("mapping.json").getFile());
        HashMap h = formatJson.in(IOUtils.toString(is));
        FormatFlatten f = FormatFlatten.getInstance(domain);
        Map hmap = new HashMap();

        hmap.put("Id", "abcdefghijklm");
        hmap.put("EntityType", "Article");
        ArrayList a = new ArrayList();
        a.add("Sports");
        a.add("News");
        a.add("Movies");
        hmap.put("Category", a);
        hmap.put("Category1", "a,b,c");
        ArrayList b = new ArrayList();
        b.add("acheck");
        b.add("bcheck");
        hmap.put("Property", "prop");
        hmap.put("Property1", b);
        hmap.put("Property2", "prop2");
        hmap.put("Property3", b);

        ArrayList c = new ArrayList();
        c.add("India");
        c.add("US");
        hmap.put("City", "Chennai,Delhi,Test"); // hmap.put("City", c);
        hmap.put("Country", c);
        hmap.put("Lat", "India,US");
        hmap.put("PropList", "p1, p2");

        /*
         * List a1 = Arrays.asList(); hmap.put("User", a1);
         * hmap.put("RelatedItems", a1); List a = new ArrayList();
         * a.add("HowDoI/Others"); hmap.put("AssociatedIAPath", a);
         * hmap.put("CurrentUser", a1); hmap.put("AliasId", a1); List b = new
         * ArrayList(); b.add("li5sA1idhdahf"); hmap.put("AssociatedIAID", b);
         * List c = new ArrayList(); c.add("2011-09-05 11:26:39");
         * hmap.put("LastModifiedDate", c); List d = new ArrayList();
         * d.add("igportaladmin"); hmap.put("LastModifiedBy", d);
         * hmap.put("Version", a1); hmap.put("SeoUrl", a1); List e = new
         * ArrayList(); e.add("Testing Description"); hmap.put("Description",
         * e); hmap.put("Site", a1); List f1 = new ArrayList();
         * f1.add("today25"); hmap.put("ArticleType", f1); List g = new
         * ArrayList(); g.add("Test"); hmap.put("AssociatedIAName", g); List h1
         * = new ArrayList(); h1.add("Chennai,Delhi"); hmap.put("City", h1);
         * List i = new ArrayList(); i.add("TestTitle"); hmap.put("Title", i);
         * hmap.put("CurrentEvent", a1); List j = new ArrayList();
         * j.add("Draft"); hmap.put("Status", j); hmap.put("SourceUrl", a1);
         * List k = new ArrayList(); k.add("2011-09-05 11:26:39");
         * hmap.put("CreatedDate", k); List l = new ArrayList();
         * l.add("igportaladmin"); hmap.put("CreatedBy", l);
         * hmap.put("WorkflowComments", a1); List m = new ArrayList();
         * m.add("Article"); hmap.put("EntityType", m); hmap.put("Id", a1);
         * hmap.put("Stage", a1); List a2 = new ArrayList(); a2.add(
         * "{\"DependentItems\":{\"DependentItem\":[{\"ItemId\":\"liqkaifjhjded\",\"ItemEntityType\":\"Media\",\"ItemTitle\":\"Sony-3d LedTV\",\"ItemDescription\":\"Sony-3d LedTV\",\"ItemFilePath\":\"/ig.in/media/liqkaifjhjded.jpg\",\"ItemThumbnailPath\":\"/ig.in/media/liqkaifjhjded_thumb.jpg\"},{\"ItemId\":\"ljfs2obdbjjee\",\"ItemEntityType\":\"Media\",\"ItemTitle\":\"aaa\",\"ItemDescription\":\"aa\",\"ItemFilePath\":\"/ig.in/media/ljfs2obdbjjee.jpg\",\"ItemThumbnailPath\":\"/ig.in/media/ljfs2obdbjjee_thumb.jpg\"},{\"ItemId\":\"liqjSegjhfiig\",\"ItemEntityType\":\"Media\",\"ItemTitle\":\"samsung-5.1 soundsystem\",\"ItemDescription\":\"samsung-5.1-soundsystem\",\"ItemFilePath\":\"/ig.in/media/liqjSegjhfiig.jpg\",\"ItemThumbnailPath\":\"/ig.in/media/liqjSegjhfiig_thumb.jpg\"}]}}"
         * ); hmap.put("DependentItems", a2); hmap.put("RelatedItems", a2);
         */
        // System.out.println(hmap);
        // HashMap<String, Object> hm = new HashMap();
        // System.exit(0);
        // System.out.println("FormatForm Data Article Mapping Value *** "
        // + h.get("Article"));
        // h = f.out(hmap, (HashMap) h.get("Article"));
        // hm.put("Article", hmap);
        // HashMap<String, Object> root = new HashMap<String, Object>();
        // root.put("Collections", hm);
        // System.out.println(root);
        // h = f.in(root);

        String x = "<Collections><Role><ParentRoleId/><Site>kalpana.in</Site><LastModifiedDate>2012-05-14T19:14:45Z</LastModifiedDate><ParentRoleName/><LastModifiedBy>bookadmin</LastModifiedBy><DomainEntities><DomainEntity>AccessControlList</DomainEntity><DomainEntity>Address</DomainEntity><DomainEntity>AppAdmin</DomainEntity><DomainEntity>Article</DomainEntity><DomainEntity>ArticleType</DomainEntity><DomainEntity>AudienceCategory</DomainEntity><DomainEntity>Awards</DomainEntity><DomainEntity>Banner</DomainEntity><DomainEntity>Book</DomainEntity><DomainEntity>Category</DomainEntity><DomainEntity>City</DomainEntity><DomainEntity>ClassAttributes</DomainEntity><DomainEntity>ClassImport</DomainEntity><DomainEntity>ClassInfo</DomainEntity><DomainEntity>ClassPackage</DomainEntity><DomainEntity>ContactNumber</DomainEntity><DomainEntity>ContactType</DomainEntity><DomainEntity>ContentPartner</DomainEntity><DomainEntity>Country</DomainEntity><DomainEntity>Css</DomainEntity><DomainEntity>Designation</DomainEntity><DomainEntity>Domain</DomainEntity><DomainEntity>DomainEntity</DomainEntity><DomainEntity>UserProfile</DomainEntity><DomainEntity>Action</DomainEntity><DomainEntity>Feedback</DomainEntity><DomainEntity>Form</DomainEntity><DomainEntity>Format</DomainEntity><DomainEntity>Gallery</DomainEntity><DomainEntity>GoiDirCat</DomainEntity><DomainEntity>GoiDirItem</DomainEntity><DomainEntity>InformationArchitecture</DomainEntity><DomainEntity>InterfaceInfo</DomainEntity><DomainEntity>Js</DomainEntity><DomainEntity>JurisdictionType</DomainEntity><DomainEntity>Language</DomainEntity><DomainEntity>Media</DomainEntity><DomainEntity>MediaGroup</DomainEntity><DomainEntity>News</DomainEntity><DomainEntity>OfficeLocation</DomainEntity><DomainEntity>Page</DomainEntity><DomainEntity>PageAssociator</DomainEntity><DomainEntity>Parliament</DomainEntity><DomainEntity>PmsMedia</DomainEntity><DomainEntity>PortFolio</DomainEntity><DomainEntity>Prefix</DomainEntity><DomainEntity>Role</DomainEntity><DomainEntity>Scheme</DomainEntity><DomainEntity>Sector</DomainEntity><DomainEntity>Service</DomainEntity><DomainEntity>ServiceType</DomainEntity><DomainEntity>Source</DomainEntity><DomainEntity>SourceType</DomainEntity><DomainEntity>Stage</DomainEntity><DomainEntity>Status</DomainEntity><DomainEntity>Template</DomainEntity><DomainEntity>CmsUserProfile</DomainEntity><DomainEntity>Validations</DomainEntity><DomainEntity>Viewer</DomainEntity><DomainEntity>WhosWhoCat</DomainEntity><DomainEntity>Whoswhoprofile</DomainEntity><DomainEntity>Widget</DomainEntity><DomainEntity>Workflow</DomainEntity><DomainEntity>NpiMetadata</DomainEntity><DomainEntity>Currency</DomainEntity><DomainEntity>Crawleddata</DomainEntity></DomainEntities><RoleName>PortalAdmin</RoleName><CreatedBy>bookadmin</CreatedBy><CreatedDate>2012-05-14T19:14:45Z</CreatedDate><EntityType>Role</EntityType><Id>mfotoJdfeahdf</Id><UiTabs><UiTab>Admin</UiTab><UiTab>CMS</UiTab><UiTab>PMS</UiTab><UiTab>Domain</UiTab><UiTab>Feed</UiTab><UiTab>Automation</UiTab><UiTab>Users And Roles</UiTab><UiTab>Workflow</UiTab></UiTabs><Version>1.0</Version></Role><Role><ParentRoleId/><Site>kalpana.in</Site><LastModifiedDate>2012-05-14T19:14:45Z</LastModifiedDate><ParentRoleName/><LastModifiedBy>bookadmin</LastModifiedBy><DomainEntities><DomainEntity>AccessControlList</DomainEntity><DomainEntity>Address</DomainEntity><DomainEntity>AppAdmin</DomainEntity><DomainEntity>Article</DomainEntity><DomainEntity>ArticleType</DomainEntity><DomainEntity>AudienceCategory</DomainEntity><DomainEntity>Awards</DomainEntity><DomainEntity>Banner</DomainEntity><DomainEntity>Book</DomainEntity><DomainEntity>Category</DomainEntity><DomainEntity>City</DomainEntity><DomainEntity>ClassAttributes</DomainEntity><DomainEntity>ClassImport</DomainEntity><DomainEntity>ClassInfo</DomainEntity><DomainEntity>ClassPackage</DomainEntity><DomainEntity>ContactNumber</DomainEntity><DomainEntity>ContactType</DomainEntity><DomainEntity>ContentPartner</DomainEntity><DomainEntity>Country</DomainEntity><DomainEntity>Css</DomainEntity><DomainEntity>Designation</DomainEntity><DomainEntity>Domain</DomainEntity><DomainEntity>DomainEntity</DomainEntity><DomainEntity>UserProfile</DomainEntity><DomainEntity>Action</DomainEntity><DomainEntity>Feedback</DomainEntity><DomainEntity>Form</DomainEntity><DomainEntity>Format</DomainEntity><DomainEntity>Gallery</DomainEntity><DomainEntity>GoiDirCat</DomainEntity><DomainEntity>GoiDirItem</DomainEntity><DomainEntity>InformationArchitecture</DomainEntity><DomainEntity>InterfaceInfo</DomainEntity><DomainEntity>Js</DomainEntity><DomainEntity>JurisdictionType</DomainEntity><DomainEntity>Language</DomainEntity><DomainEntity>Media</DomainEntity><DomainEntity>MediaGroup</DomainEntity><DomainEntity>News</DomainEntity><DomainEntity>OfficeLocation</DomainEntity><DomainEntity>Page</DomainEntity><DomainEntity>PageAssociator</DomainEntity><DomainEntity>Parliament</DomainEntity><DomainEntity>PmsMedia</DomainEntity><DomainEntity>PortFolio</DomainEntity><DomainEntity>Prefix</DomainEntity><DomainEntity>Role</DomainEntity><DomainEntity>Scheme</DomainEntity><DomainEntity>Sector</DomainEntity><DomainEntity>Service</DomainEntity><DomainEntity>ServiceType</DomainEntity><DomainEntity>Source</DomainEntity><DomainEntity>SourceType</DomainEntity><DomainEntity>Stage</DomainEntity><DomainEntity>Status</DomainEntity><DomainEntity>Template</DomainEntity><DomainEntity>CmsUserProfile</DomainEntity><DomainEntity>Validations</DomainEntity><DomainEntity>Viewer</DomainEntity><DomainEntity>WhosWhoCat</DomainEntity><DomainEntity>Whoswhoprofile</DomainEntity><DomainEntity>Widget</DomainEntity><DomainEntity>Workflow</DomainEntity><DomainEntity>NpiMetadata</DomainEntity><DomainEntity>Currency</DomainEntity><DomainEntity>Crawleddata</DomainEntity></DomainEntities><RoleName>PortalAdmin</RoleName><CreatedBy>bookadmin</CreatedBy><CreatedDate>2012-05-14T19:14:45Z</CreatedDate><EntityType>Role</EntityType><Id>mfotoJdfeahdf</Id><UiTabs><UiTab>Admin</UiTab><UiTab>CMS</UiTab><UiTab>PMS</UiTab><UiTab>Domain</UiTab><UiTab>Feed</UiTab><UiTab>Automation</UiTab><UiTab>Users And Roles</UiTab><UiTab>Workflow</UiTab></UiTabs><Version>1.0</Version></Role></Collections>";
        x = "<Collections><Success>true</Success></Collections>";
        // "<Collections><Count>1</Count><Article><Id>me1sV3fbfcdeb</Id><EntityType>Article</EntityType><AliasId></AliasId><Title><![CDATA[test tile]]></Title><Description><![CDATA[Sample description<br>]]></Description><ArticleType>ArticleType</ArticleType><AssociatedIAPath><![CDATA[/home]]></AssociatedIAPath><Md5>815b7d491895e09f1d95ce0370cae8d8</Md5><GeoTags><Location><City>Chennai</City><Country><![CDATA[]]></Country><Lat></Lat><Lng></Lng><LocationId></LocationId><State></State><Ip><![CDATA[]]></Ip><Coords></Coords><District></District></Location><Location><City>kanchi</City><Country><![CDATA[]]></Country><Lat></Lat><Lng></Lng><LocationId></LocationId><State></State><Ip><![CDATA[]]></Ip><Coords></Coords><District></District></Location></GeoTags><SeoUrl><![CDATA[http://small.heaven.in/test-tile-article-me1sv3fbfcdeb.php]]></SeoUrl><WorkflowInfo><WorkflowId>me1sIyjhccbdc</WorkflowId><WorkflowName><![CDATA[Default]]></WorkflowName><CurrentUser>admin</CurrentUser><CurrentAction>Publish</CurrentAction><Stage>Published</Stage><AllowedActions><AllowedAction><AllowedActionName></AllowedActionName><AssignedToUsers><AssignedToUser></AssignedToUser></AssignedToUsers></AllowedAction></AllowedActions><WorkflowComments><![CDATA[<b>admin [Forward]</b> : forwarded by me PM requested</br><b>admin [Publish]</b> : </br>]]></WorkflowComments></WorkflowInfo><Status>Published</Status><CreatedDate>2012-04-27T18:57:29Z</CreatedDate><LastModifiedDate>2012-05-15T13:01:20Z</LastModifiedDate><CreatedBy>admin</CreatedBy><LastModifiedBy>admin</LastModifiedBy><Site>small.heaven.in</Site><Version>1.0</Version><Weightage>1</Weightage><MetadataInfo><Identifier><![CDATA[http://sify.com]]></Identifier><IdentifierAlternate><![CDATA[]]></IdentifierAlternate><TitleMain><![CDATA[state sector]]></TitleMain><TitleAlternate><![CDATA[]]></TitleAlternate><MetadataDescription><![CDATA[state sector]]></MetadataDescription><SubjectKeywords><SubjectKeyword>state sector</SubjectKeyword></SubjectKeywords><SubjectClassification><Sector>state sector</Sector></SubjectClassification><PublisherOrgNames><PublisherOrgName></PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>IT</PublisherDepartment></PublisherDeptName><PublisherAddress><![CDATA[]]></PublisherAddress><CreatorOrgNames><CreatorOrgName></CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>IT</CreatorDepartment></CreatorDeptName><CreatorAddress><![CDATA[]]></CreatorAddress><Format>Graphic interchange format</Format><LanguageId>me4oQoabdehed</LanguageId><Language>English</Language><MetaDataCreatedDate>2012-04-27T18:58:04Z</MetaDataCreatedDate><PublishedDate>2012-04-27T18:58:04Z</PublishedDate><ValidDate>2012-04-27T18:58:04Z</ValidDate><CoverageJurisdiction><Jurisdiction><JurisdictionName>allindia</JurisdictionName><JurisdictionStateId></JurisdictionStateId><JurisdictionStateName></JurisdictionStateName><JurisdictionDistrictId></JurisdictionDistrictId><JurisdictionDistrictName></JurisdictionDistrictName></Jurisdiction></CoverageJurisdiction><CoverageSpatial><Spatial><SpatialStateId>me2lb7ihhjbcd</SpatialStateId><SpatialStateName>TamilNadu</SpatialStateName><SpatialDistrictId>mfcrWAecddgei</SpatialDistrictId><SpatialDistrictName>Vellore</SpatialDistrictName></Spatial></CoverageSpatial><CoverageTemporal><![CDATA[]]></CoverageTemporal><Audience><AudienceCategory>Journalism</AudienceCategory></Audience><CategoryTypes><CategoryType>communication Department</CategoryType></CategoryTypes><ConformsTo><![CDATA[]]></ConformsTo><HasParts><HasPart><![CDATA[]]></HasPart></HasParts><IsPartOf><![CDATA[]]></IsPartOf><HasVersion><![CDATA[]]></HasVersion><IsVersionOf><![CDATA[]]></IsVersionOf><Source><![CDATA[state sector]]></Source></MetadataInfo><DependentItems/><RelatedItems/></Article></Collections>";
        HashMap h1 = FormatXml.getInstance("kalpana.in").in(x);
        System.out.println(h1);
        h = f.in(h1, true);

        HashMap h2 = f.in(h1, false);
        // hm.put("Article", h);
        // System.out.println("Final HashMap **** " + h);

        // String x = (String) FormatXml.getInstance().out(hm);
        System.out.println(h);
        System.out.println(h2);
    }
}
