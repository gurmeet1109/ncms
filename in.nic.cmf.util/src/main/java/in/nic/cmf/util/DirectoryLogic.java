package in.nic.cmf.util;

import in.nic.cmf.logger.LogTracer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectoryLogic {

    // private LogTracer log = new LogTracer("util");
    private LogTracer                              log;
    private static HashMap<String, DirectoryLogic> hashDirectoryLogic = new HashMap<String, DirectoryLogic>();

    private void setLogger(String domain) {
        log = new LogTracer(domain, "UtilTraceLogger", true, true, true, true,
                false);
    }

    private DirectoryLogic(String domain) {
        setLogger(domain);
    }

    public static DirectoryLogic getInstanceOf(String domain) {

        if (!hashDirectoryLogic.containsKey(domain)) {
            hashDirectoryLogic.put(domain, new DirectoryLogic(domain));
        }
        return hashDirectoryLogic.get(domain);
    }

    public List<String> listFilesFromDirectory(String directoryPath,
            String entityType) {
    	directoryPath = "/opt/cmf/domains";
        List<String> fullpath = new ArrayList<String>();
        log.debug("Incoming path:" + directoryPath);
        File dir = new File(directoryPath);
        String[] childrens = dir.list();
        log.debug("childrens:"+childrens);
        if (childrens != null && !(childrens.length <= 0)) {
        	log.debug("childrens !=null:"+childrens.length);
            for (String eachDomain : childrens) {
            	log.debug("eachdomain:"+eachDomain);
                String eachfullpath = directoryPath + "/" + eachDomain
                        + "/rules/" + entityType + "/";
                log.debug("eachfullpath:"+eachfullpath);
                File innerdir = new File(eachfullpath);
                String[] innerchildrens = innerdir.list();
                log.debug("innerchilds:"+innerchildrens);
                if (innerchildrens != null && !(innerchildrens.length <= 0)) {
                	log.debug("innerchilds !=null:"+innerchildrens.length);
                    for (String inner : findInnerchild(eachfullpath)) {
                    	log.debug("in:"+inner);
                        fullpath.add(inner);
                    }
                }
            }
        }
        log.debug("All files are : " + fullpath);
        return fullpath;
    }

    /*
     * public void listFiles(String directoryName){
     * File dir = new File(directoryName);
     * String[] children = dir.list();
     * System.out.println("entered into listfiles.");
     * if (children.length <= 0) {
     * System.out.println("there is no child directory in this path.");
     * } else {
     * System.out.println("children is not equal to null");
     * System.out.println("------------------------");
     * for(String eachFile:children){
     * System.out.println("Fullpath:"+directoryName+eachFile);
     * }
     * }
     * }
     * public List<String> listFilesFromDirectory(String directoryName,String
     * entityAsfolderName){
     * List<String> fullpath = new ArrayList<String>();
     * System.out.println("incoming path:"+directoryName+"entity:"+
     * entityAsfolderName);
     * File dir = new File(directoryName);
     * String[] children = dir.list();
     * System.out.println("Children:"+children);
     * if(children!=null){
     * if (children.length <= 0) {
     * System.out.println("there is no child directory in this path.");
     * } else {
     * System.out.println("children is not equal to null");
     * for(String eachFile:children){
     * String eachfullpath = directoryName +entityAsfolderName+"/"+eachFile;
     * System.out.println("Fullpath:" +eachfullpath);
     * //fullpath = findInnerchild(eachfullpath);
     * for(String inner : findInnerchild(eachfullpath)){
     * fullpath.add(inner);
     * }
     * }
     * }
     * }
     * System.out.println("------------------------");
     * for(String test:fullpath)
     * {
     * System.out.println("eachfile:"+test);
     * }
     * return fullpath;
     * }
     * public List<String> findInnerchild(String eachfullpath){
     * File dir1 = new File(eachfullpath);
     * String[] children1 = dir1.list();
     * List<String> fullpath= new ArrayList();
     * if(children1!=null){
     * if (children1.length <= 0) {
     * System.out.println("2there is no child directory in this path.");
     * } else{
     * for(String eachFile1:children1){
     * String eachfullpath1 = eachfullpath+eachFile1;
     * System.out.println("innerFullpath:" +eachfullpath1);
     * fullpath.add(eachfullpath1);
     * }
     * }
     * }
     * return fullpath;
     * }
     */

    public boolean createDirectoryIfNotExists(String directoryName) {
        boolean result = false;
        File file = new File(directoryName);
        if (!file.exists()) {
            result = file.mkdirs();
            System.out.println("directory created:" + directoryName);
        } else {
            result = true;
            System.out.println(directoryName + " file exists");
        }
        return result;
    }

    // public List<String> listFilesFromDirectory(String directoryPath,
    // String entityType) {
    // List<String> fullpath = new ArrayList<String>();
    // log.debug("Incoming path:" + directoryPath);
    // File dir = new File(directoryPath);
    // String[] childrens = dir.list();
    // log.debug("Childrens in " + directoryPath + " : " + childrens);
    // if (childrens != null && !(childrens.length <= 0)) {
    // log.debug("Child directory is there");
    // for (String eachDomain : childrens) {
    // log.debug("eachDomain:" + eachDomain);
    // String eachfullpath = directoryPath + "/" + eachDomain;
    // log.debug("eachFullPath:" + directoryPath + ";eachDomain"
    // + eachDomain);
    // log.debug("Fullpath:" + eachfullpath);
    // // for (String inner : findInnerchild(eachfullpath)) {
    // fullpath.add(eachfullpath);
    // // }
    // }
    // }
    // log.debug("All files are : " + fullpath);
    // return fullpath;
    // }

    public List<String> findInnerchild(String eachfullpath) {
        File dir = new File(eachfullpath);
        String[] children = dir.list();
        List<String> fullpath = new ArrayList();
        if (children != null && !(children.length <= 0)) {
            for (String eachFile1 : children) {
                String eachfullpath1 = eachfullpath + eachFile1;
                System.out.println("innerFullpath:" + eachfullpath1);
                fullpath.add(eachfullpath1);
            }
        }
        return fullpath;
    }
}
