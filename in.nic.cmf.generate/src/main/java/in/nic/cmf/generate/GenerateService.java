package in.nic.cmf.generate;

import in.nic.cmf.convertors.Convertor;
import in.nic.cmf.convertors.FormatFlatten;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class GenerateService {

    private PropertiesUtil                          putil;
    private HashMap<String, String>                 iaTreeGridMap       = new HashMap<String, String>();
    private HashMap<String, Object>                 tree                = new HashMap<String, Object>();
    private HashMap<String, Object>                 parentRef           = new HashMap<String, Object>();
    List<String>                                    masterEntities      = new ArrayList<String>();
    List<String>                                    treeEntities        = new ArrayList<String>();
    private static HashMap<String, GenerateService> hashGenerateService = new HashMap<String, GenerateService>();
    private String                                  domain;
    /** Field: logger to hold Logger object. */
    private LogTracer                               log                 = null;

    private GenerateService(String domain) {

        this.domain = domain;
        // log = new LogTracer(domain, "DSMenuTraceLogger");
        putil = PropertiesUtil.getInstanceof(domain, "generate");
        System.out.println("JAVA");
        HashMap<String, Object> masterEntityHash = (HashMap<String, Object>) FormatJson
                .getInstance(domain).in(FileRead("MasterEntities.json"));
        masterEntities = (List<String>) masterEntityHash.get("masterEntities");
        HashMap treeEntityHash = FormatJson.getInstance(domain).in(
                FileRead("TreeEntities.json"));
        treeEntities = (List<String>) treeEntityHash.get("treeEntities");
    }

    public static GenerateService getInstance(String domain) {
        if (!hashGenerateService.containsKey(domain)) {

            hashGenerateService.put(domain, new GenerateService(domain));
        }
        return hashGenerateService.get(domain);
    }

    public Map<String, Map<String, Object>> add(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) throws GenericException {

        log.methodEntry("GenerateService add Starts");
        String inputString = (String) arg2.get("input").get("content");

        HashMap<String, Object> dataHash = FormatXml.getInstance(domain).in(
                inputString);
        HashMap<String, Object> collHash = (HashMap<String, Object>) dataHash
                .get("Collections");
        log.debug("collHash::::::::::" + collHash);
        HashMap<String, Object> fileHash = new HashMap<String, Object>();

        for (Entry<String, Object> e : collHash.entrySet()) {
            String key = (String) e.getKey();
            if (!key.equals("Count")) {
                log.debug("inside if condition masterEntities::::"
                        + masterEntities);

                if (masterEntities.contains(key)) {

                    // fileHash = getFileHash(key, (Object) e.getValue(), arg0,
                    // "jsonmenuPath");
                    fileHash = getFileHash(key, (Object) e.getValue(), arg0);
                    if (!fileHash.isEmpty()) {

                        // FileWrite(arg0, key + ".json",
                        // FormatJson.getInstanceof(domain).out(fileHash)
                        // .toString(), "jsonmenuPath");
                        FileWrite(arg0, key + ".json",
                                FormatJson.getInstance(domain).out(fileHash)
                                        .toString());
                    }
                }
                if ((!fileHash.isEmpty() && treeEntities.contains(key))
                        || (key.equals("Stage"))) {
                    // fileHash = getFileHash(key, (Object) e.getValue(), arg0,
                    // "jsonmenuPath");

                    log.debug("before fileHash::::::::::::" + fileHash);
                    fileHash = getFileHash(key, (Object) e.getValue(), arg0);
                    log.debug("after fileHash::::::::::::" + fileHash);
                    prepareIaDetails(arg0, key, fileHash);
                }
            }
        }
        log.methodEntry("GenerateService add ends");
        return buildResponseMap(arg2, inputString);
    }

    public Map<String, Map<String, Object>> delete(String arg0, String arg1,
            String arg2, Map<String, Map<String, Object>> arg3)
            throws GenericException {
        log.debug("inside delete method of GenerateServie");
        boolean result = false;
        InputStream is;
        try {
            HashMap<String, Object> finalMap = new HashMap<String, Object>();
            log.debug("Master Entities :::" + masterEntities);
            log.debug("Tree Entities :::" + treeEntities);
            log.debug("Incoming Entity :::" + arg1);
            if (masterEntities.contains(arg1)) {
                // is = new FileInputStream(new File(
                // putil.getProperty("jsonmenuPath") + arg0 + "/" + arg1
                // + ".json"));

                log.debug(putil.getProperty("dirlocation") + arg0 + "/data/"
                        + arg1 + ".json");
                is = new FileInputStream(new File(
                        putil.getProperty("dirlocation") + arg0 + "/data/"
                                + arg1 + ".json"));
                HashMap<String, Object> collHash = (HashMap<String, Object>) FormatJson
                        .getInstance(domain).in(IOUtils.toString(is))
                        .get("Collections");
                List<Object> entityHashList = (List<Object>) getHashList(collHash
                        .get(arg1));
                List<Object> newEntityHashList = new ArrayList<Object>();
                for (Object h : entityHashList) {
                    HashMap<String, String> entityHash = (HashMap<String, String>) h;
                    if (!entityHash.get("Id").equals(arg2)) {
                        newEntityHashList.add(entityHash);
                    }
                }
                collHash.put("Count",
                        Integer.toString(newEntityHashList.size()));
                collHash.put(arg1, newEntityHashList);
                finalMap.put("Collections", collHash);
                JSONObject json = (JSONObject) FormatJson.getInstance(domain)
                        .out(finalMap);
                // FileWrite(arg0, arg1 + ".json", json.toString(),
                // "jsonmenuPath");
                FileWrite(arg0, arg1 + ".json", json.toString());
            }
            if (treeEntities.contains(arg1)) {
                prepareIaDetails(arg0, arg1, finalMap);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEN-0002", this.getClass()
                    .getSimpleName() + ":deleteByID()", e);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEN-0004", this.getClass()
                    .getSimpleName() + ":deleteByID()", e);
        }
        return buildResponseMap(arg3, arg2);
    }

    public Map<String, Map<String, Object>> get(String arg0, String arg1,
            Map<String, Map<String, Object>> arg2) throws GenericException {
        HashMap<String, Object> responseMap = new HashMap<String, Object>();
        InputStream is;
        String response = "";
        try {
            String fileName = arg1.substring(0, 1).toUpperCase()
                    + arg1.substring(1);
            is = new FileInputStream(new File(putil.getProperty("dirlocation")
                    + arg0 + "/data/" + fileName + ".json"));
            response = IOUtils.toString(is);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEN-0002", this.getClass()
                    .getSimpleName() + ":find()", e);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEN-0004", this.getClass()
                    .getSimpleName() + ":find()", e);
        }
        return buildResponseMap(arg2, response);
    }

    public boolean prepareIaDetails(String domainName, String entity,
            HashMap<String, Object> hashMap) {
        log.debug("PrepareIaDetails Starts");
        boolean result = true;
        try {
            if (!tree.isEmpty() && !iaTreeGridMap.isEmpty()) {
                iaTreeGridMap.clear();
                tree.clear();
            }
            HashMap<String, Object> collHash = (HashMap<String, Object>) hashMap
                    .get("Collections");
            parentRef = getTreeHash(collHash, entity);
            if (entity.equals("InformationArchitecture")
                    || entity.equals("Stage")) {
                if (!prepareMenu(domainName, parentRef, entity)) {
                    result = false;
                }
            }
            if (!prepareIaTreeGrid(domainName, parentRef, entity)) {
                result = false;
            }

        } catch (Exception e) {

            // log.error(e.getMessage());
            e.printStackTrace();
            throw new GenericException(domain, "ERR-GEN-0000", this.getClass()
                    .getSimpleName() + ":prepareIaDetails()", e);
        }
        log.debug("PrepareIaDetails Ends");
        return result;

    }

    private void FileWrite(String domainName, String fileName, String content) {
        FileWriter fstream = null;
        try {
            log.methodEntry("GenerateService FileWrite Starts");
            log.debug(putil.getProperty("dirlocation") + domainName + "/data/"
                    + fileName);
            fstream = new FileWriter(new File(putil.getProperty("dirlocation")
                    + domainName + "/data/" + fileName));
            // fstream = new FileWriter(new File(putil.getProperty(path)
            // + domainName + "/" + fileName));
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            out.close();

        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new GenericException(domain, "ERR-GEN-0004", this.getClass()
                    .getSimpleName() + ":FileWrite()", ex);
        }
        log.methodEntry("GenerateService FileWrite Ends");
    }

    public boolean createDSDirectory(String pathName) throws GenericException {
        log.debug("GenerateServiec.createDSDirectory() start");
        boolean result = false;
        try {
            log.debug("Path Name::::" + pathName);
            File file = new File(pathName);
            if (!file.exists()) {
                result = file.mkdirs();
            }
        } catch (Exception ex) {
            log.debug("In createDSDirectory exception:" + ex.getMessage());
        } finally {
        }
        log.debug("GenerateServiec.createDSDirectory() end");
        return result;
    }

    private HashMap<String, Object> getFileHash(String fileName,
            Object entityHash, String domainName) {
        try {
            createDSDirectory(putil.getProperty("dirlocation") + domainName
                    + "/data");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("ERR-GEN-0001", this.getClass()
                    .getSimpleName() + ":getFileHash()", e);
        }
        // File file = new File(putil.getProperty(path) + domainName + "/"
        // + fileName + ".json");
        log.debug("GenerateService.getFileHash() Entry");
        log.debug(putil.getProperty("dirlocation") + domainName + "/data/"
                + fileName + ".json");
        File file = new File(putil.getProperty("dirlocation") + domainName
                + "/data/" + fileName + ".json");
        HashMap<String, Object> hash = new HashMap<String, Object>();
        List<Object> entityList = new ArrayList<Object>();
        HashMap<String, Object> collHash = new HashMap<String, Object>();
        HashMap<String, Object> finalMap = new HashMap<String, Object>();
        if (file.exists()) {
            try {
                InputStream is = new FileInputStream(file);
                hash = FormatJson.getInstance(domain).in(IOUtils.toString(is));
                entityList = AddOrUpdateFile(hash, entityHash, fileName);
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
                throw new GenericException(domain, "ERR-GEN-0002", this
                        .getClass().getSimpleName() + ":getFileHash()", e);
            } catch (IOException e) {
                throw new GenericException(domainName, "ERR-GEN-0004", this
                        .getClass().getSimpleName() + ":getFileHash()", e);
            }

        } else {
            entityList = getHashList(entityHash);
        }
        collHash.put("Count", Integer.toString(entityList.size()));
        collHash.put(fileName, entityList);
        finalMap.put("Collections", collHash);
        HashMap<String, Object> fh = FormatFlatten.getInstance(domain).in(
                finalMap);
        log.debug("GenerateService.getFileHash() Exit");
        return fh;
    }

    private List<Object> getHashList(Object entityHash) {
        List<Object> entityHashList = new ArrayList<Object>();
        if (entityHash.getClass().getSimpleName().equalsIgnoreCase("ArrayList")) {
            entityHashList = (List<Object>) entityHash;
        } else if (entityHash.getClass().getSimpleName()
                .equalsIgnoreCase("HashMap")) {
            entityHashList.add(entityHash);
        }
        return entityHashList;
    }

    private List<Object> AddOrUpdateFile(HashMap hash, Object entityHash,
            String entityName) {
        List<Object> entityHashList = getHashList(entityHash);
        List<Object> newHashList = new ArrayList<Object>();
        HashMap<String, Object> collHash = (HashMap<String, Object>) hash
                .get("Collections");
        HashMap<String, Object> newHashMap = new HashMap<String, Object>();
        List<Object> hashList = (List<Object>) collHash.get(entityName);
        if (!hashList.isEmpty()) {
            for (Object entity : entityHashList) {
                HashMap<String, Object> eHash = (HashMap<String, Object>) entity;
                for (Object h : hashList) {
                    HashMap<String, String> h1 = (HashMap<String, String>) h;
                    if (!h1.get("Id").equals(eHash.get("Id"))) {
                        if (!newHashMap.containsKey(h1.get("Id")))
                            newHashMap.put(h1.get("Id"), h);
                    }
                    newHashMap.put((String) eHash.get("Id"), eHash);
                }

            }
        } else {
            HashMap<String, Object> eHash = (HashMap<String, Object>) entityHash;
            newHashMap.put((String) eHash.get("Id"), entityHash);
        }
        for (Entry<String, Object> e : newHashMap.entrySet()) {
            String key = (String) e.getKey();
            newHashList.add(e.getValue());
        }

        return newHashList;
    }

    public HashMap<String, Object> getTreeHash(
            HashMap<String, Object> collHash, String entity) {
        if (!tree.isEmpty() && !iaTreeGridMap.isEmpty()) {
            iaTreeGridMap.clear();
            tree.clear();
        }
        List<Object> entityHash = getHashList(collHash.get(entity));
        List<String> iaTreePathList = new ArrayList<String>();
        for (Object hash : entityHash) {
            HashMap<String, String> h = (HashMap<String, String>) hash;
            String parentName = "";
            if (entity.equals("InformationArchitecture")) {
                String iaPath = (String) h.get("IaPath");
                iaTreeGridMap.put(h.get("IaName").toString().trim() + "_"
                        + h.get("Id").toString().trim(), iaPath);
                iaTreePathList.add(iaPath);
                if (h.get("ParentIAName").toString().trim().equals("")) {
                    parentName = "";
                } else {
                    parentName = h.get("ParentIAName").toString().trim() + "_"
                            + h.get("ParentIAID").toString().trim();
                }
                add(parentName, h.get("IaName").toString().trim() + "_"
                        + h.get("Id").toString().trim(), entity);
            } else if (entity.equals("GoiDirCat")) {
                String goicatPath = (String) h.get("GoiDirCatPath");
                iaTreeGridMap.put(h.get("GoiDirCatName").toString().trim()
                        + "_" + h.get("Id").toString().trim(), goicatPath);
                iaTreePathList.add(goicatPath);
                if (h.get("ParentGoiDirCatName").toString().trim().equals("")) {
                    parentName = "";
                } else {
                    parentName = h.get("ParentGoiDirCatName").toString().trim()
                            + "_"
                            + h.get("ParentGoiDirCatId").toString().trim();
                }
                add(parentName, h.get("GoiDirCatName").toString().trim() + "_"
                        + h.get("Id").toString().trim(), entity);
            } else if (!entity.equals("Stage")) {

                iaTreeGridMap.put(h.get(entity + "Name").toString().trim()
                        + "_" + h.get("Id").toString().trim(),
                        h.get(entity + "Name").toString().trim());
                if (h.get("Parent" + entity + "Name").toString().trim()
                        .equals("")) {
                    parentName = "";
                } else {
                    parentName = h.get("Parent" + entity + "Name").toString()
                            .trim()
                            + "_"
                            + h.get("Parent" + entity + "Id").toString().trim();
                }
                add(parentName, h.get(entity + "Name").toString().trim() + "_"
                        + h.get("Id").toString().trim(), entity);
            }
        }
        parentRef.put(entity, tree);
        return parentRef;
    }

    public boolean add(String parent, String child, String entity) {
        List<String> childList = new ArrayList<String>();
        if (tree.isEmpty()) {
            childList.add(child);
            if (parent.isEmpty()) {
                tree.put(entity, childList);
            } else {
                tree.put(parent, childList);
            }
            tree.put(child, new ArrayList<String>());
        } else {
            if (!parent.isEmpty()) {
                if (tree.containsKey(parent)) {
                    List clist = (List) tree.get(parent);
                    clist.add(child);
                    tree.put(parent, clist);
                    if (!tree.containsKey(child)) {
                        tree.put(child, new ArrayList<String>());
                    }
                } else {
                    childList.add(child);
                    tree.put(parent, childList);
                    if (!tree.containsKey(child)) {
                        tree.put(child, new ArrayList<String>());
                    }
                }
            } else {
                if (tree.containsKey(entity)) {
                    List clist = (List) tree.get(entity);
                    clist.add(child);
                    tree.put(entity, clist);
                    if (!tree.containsKey(child)) {
                        tree.put(child, new ArrayList<String>());
                    }
                } else {
                    childList.add(child);
                    tree.put(entity, childList);
                    if (!tree.containsKey(child)) {
                        tree.put(child, new ArrayList<String>());
                    }
                }
            }
        }
        return true;
    }

    private boolean prepareMenu(String domainName,
            HashMap<String, Object> hash, String entity) throws Exception {
        System.out.println("Inside prepareMenu Starts ::: " + entity);
        log.methodEntry("PrepareMenu Starts");
        String data = "";
        boolean result = false;
        VelocityContext context = new VelocityContext();
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        ve.setProperty("file.resource.loader.class",
                " org.apache.velocity.runtime.resource.loader.FileResourceLoader");

        String dirpath = putil.get("dirlocation") + domainName + "/resources/";

        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, dirpath);

        ve.setProperty("runtime.log.logsystem.class",
                "org.apache.velocity.runtime.log.NullLogSystem");
        ve.init();
        ve.init();
        Template t = ve.getTemplate("menu.vm");
        GenerateService gs = GenerateService.getInstance(domainName);
        List<Object> stageHashList = new ArrayList<Object>();
        File f = new File(putil.getProperty("dirlocation") + domainName
                + "/resources/Stage" + ".json");
        if (f.exists()) {
            InputStream is = new FileInputStream(f);
            HashMap<String, Object> stageHash = (HashMap<String, Object>) FormatJson
                    .getInstance(domain).in(IOUtils.toString(is))
                    .get("Collections");
            stageHashList = getHashList(stageHash.get("Stage"));
        }
        if (entity.equals("Stage")) {
            File f1 = new File(putil.getProperty("dirlocation") + domainName
                    + "/resources/InformationArchitecture" + ".json");
            if (f.exists()) {
                InputStream is = new FileInputStream(f1);
                HashMap<String, Object> iaHash = (HashMap<String, Object>) FormatJson
                        .getInstance(domain).in(IOUtils.toString(is))
                        .get("Collections");

                hash = getTreeHash(iaHash, "InformationArchitecture");
            }
        }
        context.put("cmobj", gs);
        context.put("domainName", domainName);
        context.put("hobj", hash);
        context.put("stageobj", stageHashList);
        context.put("mapping", tree);
        context.put("entity", "InformationArchitecture");
        try {
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            if (!writer.toString().trim().isEmpty()) {
                data = writer.toString().trim();
                Convertor c = FormatJson.getInstance(domain);
                HashMap<String, Object> map = c.in(data.trim());
                JSONObject json = (JSONObject) c.out(map);
                String newdata = json.toString();
                newdata = newdata.substring(0, newdata.lastIndexOf("}"))
                        + ",scope :this });return filterByMenu;}";
                newdata = "function filterByMenu(currentMenuHandler){var filterByMenu = Ext.create('Ext.menu.Menu',"
                        + newdata.replace(
                                "\"Ext.JSON.decode(currentMenuHandler)\"",
                                "Ext.JSON.decode(currentMenuHandler)");
                // FileWrite(domainName, "menu.js", newdata, "jsmenuPath");
                FileWrite(domainName, "menu.js", newdata);
                result = true;
            }
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEN-0000", this.getClass()
                    .getSimpleName() + ":prepareMenu()", e);

        }
        log.methodExit("PrepareMenu Ends");
        return result;
    }

    public boolean prepareIaTreeGrid(String domainName,
            HashMap<String, Object> hash, String entity) throws Exception {
        log.methodEntry(entity + " Tree Generation Starts");
        String data = "";
        boolean result = false;
        VelocityContext context = new VelocityContext();
        VelocityEngine ve = new VelocityEngine();

        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        ve.setProperty("file.resource.loader.class",
                " org.apache.velocity.runtime.resource.loader.FileResourceLoader");

        String dirpath = putil.get("dirlocation") + domainName + "/resources/";

        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, dirpath);

        ve.setProperty("runtime.log.logsystem.class",
                "org.apache.velocity.runtime.log.NullLogSystem");
        ve.init();
        // ve.setProperty(Velocity.RESOURCE_LOADER, "class");
        // ve.setProperty("class.resource.loader.class",
        // "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        // ve.setProperty("runtime.log.logsystem.class",
        // "org.apache.velocity.runtime.log.NullLogSystem");

        ve.init();

        Template t = ve.getTemplate("iatreegrid.vm");
        System.out.println("TEST::::" + hash);
        GenerateService gs = GenerateService.getInstance(domainName);
        context.put("iatreegridmap", iaTreeGridMap);
        context.put("iaobj", hash);
        context.put("mapping", tree);
        context.put("cmobj", gs);
        context.put("entity", entity);
        try {
            StringWriter writer = new StringWriter();
            t.merge(context, writer);
            if (!writer.toString().trim().isEmpty()) {
                data = writer.toString().trim();
                Convertor c = FormatJson.getInstance(domain);
                HashMap<String, Object> map = c.in(data.trim());
                JSONObject json = (JSONObject) c.out(map);
                // FileWrite(domainName, entity + "Tree.json", json.toString(),
                // "jsmenuPath");
                FileWrite(domainName, entity + "Tree.json", json.toString());
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new GenericException(domain, "ERR-GEN-0000", this.getClass()
                    .getSimpleName() + ":prepareIaTreeGrid()", e);
        }
        log.methodExit(entity + " Tree Generation Ends");
        return result;
    }

    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String encodeIAName(String ianame) {
        if (ianame.contains("'")) {
            ianame = ianame.replace("'", "\\\'");
        } else if (ianame.contains("\"")) {
            ianame = ianame.replace("\"", "\\\"");
        }
        return ianame.trim();
    }

    public String removeSymbol(String name) {
        String returnString = name;
        if (name.contains("|")) {
            returnString = returnString.replace("|",
                    putil.getProperty("PathSeperator"));
        }
        return returnString;
    }

    public static String getLowerCase(String name) {
        String value = name.toLowerCase();
        return value;
    }

    public String removeSlash(String incomingString) {
        String value = incomingString.substring(1);
        return value.trim();
    }

    public String getVal(String value, int index) {
        List outval = new ArrayList();
        String[] val = null;
        if (value.contains("_")) {
            val = value.split("_");
        } else {
            val[0] = value;
        }
        if (val[index] == null) {
            val[index] = "NotValidId";
        }
        return val[index];
    }

    public Object FileRead(String fileName) {
        InputStream is;
        String response = "";
        try {
            /*
             * String filePath = Thread.currentThread().getContextClassLoader()
             * .getResource(fileName).toString(); if
             * (filePath.contains("file:")) { filePath =
             * filePath.replace("file:", ""); } if (filePath.contains("jar:")) {
             * filePath = filePath.replace("jar:", ""); }
             */
            // is = new FileInputStream(putil.getProperty("fileReadPath")
            // + fileName);
            System.out.println("File Path:::::"
                    + putil.getProperty("dirlocation") + domain + "/resources/"
                    + fileName);;
            is = new FileInputStream(putil.getProperty("dirlocation") + domain
                    + "/resources/" + fileName);
            response = IOUtils.toString(is);
        } catch (FileNotFoundException e) {
            throw new GenericException(domain, "ERR-GEN-0002", this.getClass()
                    .getSimpleName() + ":FileRead()", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-GEN-0004", this.getClass()
                    .getSimpleName() + ":FileRead()", e);
        }
        return response;
    }

    public void setLogTracer(LogTracer arg0) {
        this.log = arg0;
    }

    private Map<String, Map<String, Object>> buildResponseMap(
            Map<String, Map<String, Object>> arg0, String content) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("statusCode", "200");
        response.put("content", content);
        arg0.put("output", response);
        return arg0;
    }

}
