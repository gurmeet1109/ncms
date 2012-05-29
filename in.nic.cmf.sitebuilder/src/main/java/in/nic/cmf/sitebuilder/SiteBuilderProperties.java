package in.nic.cmf.sitebuilder;

import in.nic.cmf.properties.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiteBuilderProperties {

    private SiteBuilderProperties                         bpProperties;

    private String                                        domain;

    private PropertiesUtil                                proputil;

    private static HashMap<String, SiteBuilderProperties> hashsbproperties = new HashMap<String, SiteBuilderProperties>();

    private List<String>                                  fileType         = new ArrayList<String>();

    private List<String>                                  cmsType          = new ArrayList<String>();

    private List<String>                                  workFlowStatus   = new ArrayList<String>();

    private List<String>                                  archivedStatus   = new ArrayList<String>();

    private SiteBuilderProperties(String domain) {
        this.domain = domain;
        proputil = PropertiesUtil.getInstanceof(domain, "sitebuilder");
        String filetypes[] = ((String) proputil.getProperty("filetype"))
                .split(",");
        for (String fileExtn : filetypes) {
            fileType.add(fileExtn);
        }
        String cmstypes[] = ((String) proputil.getProperty("cmstype"))
                .split(",");
        for (String cmstype : cmstypes) {
            cmsType.add(cmstype);
        }
        String workflowstates[] = ((String) proputil.getProperty("status"))
                .split(",");
        for (String workflowstate : workflowstates) {
            workFlowStatus.add(workflowstate);
        }
        String archivedstatus[] = ((String) proputil.getProperty("exstatus"))
                .split(",");
        for (String archivedstate : archivedstatus) {
            archivedStatus.add(archivedstate);
        }
    }

    public String getProperty(String key) {
        return proputil.getProperty(key);
    }

    public List<String> getFileType() {
        return fileType;
    }

    public List<String> getCmsTypes() {
        return cmsType;
    }

    public List<String> getWorkFlowStates() {
        return workFlowStatus;
    }

    public List<String> getArchivedStatus() {

        return archivedStatus;
    }

    public static SiteBuilderProperties getInstanceof(String domain) {
        if (!hashsbproperties.containsKey(domain)) {
            hashsbproperties.put(domain, new SiteBuilderProperties(domain));
        }
        return hashsbproperties.get(domain);
    }

}
