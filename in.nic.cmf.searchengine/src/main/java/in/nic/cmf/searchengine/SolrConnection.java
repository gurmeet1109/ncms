package in.nic.cmf.searchengine;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertyManagement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.core.CoreContainer;
import org.xml.sax.SAXException;

/**
 * SolrQueryValidation class will validate the the query parameter.It will be
 * used from SearchAPIResource to validate the the query parameter.
 * @author Kalidoss.M.
 * @author Ramu P converted as cmf component.
 */

public class SolrConnection {
    private HashMap<String, SolrServer> hmSolrServer = new HashMap<String, SolrServer>();
    public LogTracer                    log;
    private PropertyManagement          proputil     = PropertyManagement
                                                             .getInstance();
    private String                      domain;

    /**
     * Constructor for getting the properties.
     * @throws Exception
     *             which is type of {@link java.io.Exception} class.
     */
    public SolrConnection(String domain) {
        this.domain = domain;
        ArrayList<String> alist = new ArrayList<String>();
        alist.add("config");
        alist.add("querystringparser");
        // proputil = PropertiesUtil.getInstanceof(domain, "searchengine",
        // alist);
        // proputil = PropertyManagement.getInstanceOf(domain, "searchengine",
        // "Configuration");

    }

    /**
     * Setting the solr instance.
     * @param url
     *            hold the URL which is a type of {@link java.util.String}
     *            class.
     * @param group
     *            hold the group name which is type of {@link java.util.String}
     *            class.
     * @throws Exception
     *             which is type of {@link java.net.Exception} class.
     */
    private void setSolrInstance(String url, final String group)
            throws GenericException {
        try {
            if (!hmSolrServer.containsKey(group)) {
                if (proputil.getProperties(domain, "searchengine",
                        "Configuration", "solrtype").equals("embedded")) {
                    log.debug("SolrType is embedded");
                    System.setProperty("solr.solr.home", this.getClass()
                            .getClassLoader().getResource("solr").getPath()
                            + "/data/");
                    System.setProperty("solr.data.dir", this.getClass()
                            .getClassLoader().getResource("solr").getPath()
                            + "/data/");
                    CoreContainer.Initializer initializer = new CoreContainer.Initializer();
                    CoreContainer coreContainer = initializer.initialize();
                    hmSolrServer.put(group, new EmbeddedSolrServer(
                            coreContainer, ""));
                    log.debug("Embeded Solr instance created");
                } else {
                    log.debug("SolrType is not embedded");
                    if (null == url) {
                        url = proputil.getProperties(domain, "searchengine",
                                "Configuration", "solrurl");
                    }
                    log.debug("Solr Url : " + url);
                    hmSolrServer.put(group, new CommonsHttpSolrServer(url));
                }
            }
        } catch (MalformedURLException e) {
            log.error("MalformedURLException throws : " + e.toString());
            throw new GenericException(domain, "ERR-SE-0005",
                    "MalformedURLException", group, e);
        } catch (IOException e) {
            log.error("IOException throws : " + e.toString());
            throw new GenericException(domain, "ERR-SE-0002", this.getClass()
                    .getSimpleName() + ".setSolrInstance()", group, e);
        } catch (ParserConfigurationException e) { // need to throw exact
                                                   // exception
            log.error("ParserConfigurationException throws : " + e.toString());
            throw new GenericException(domain, "ERR-SE-0002", this.getClass()
                    .getSimpleName() + ".setSolrInstance()", group, e);
        } catch (SAXException e) {// need to throw exact exception
            log.error("SAXException throws : " + e.toString());
            throw new GenericException(domain, "ERR-SE-0002", this.getClass()
                    .getSimpleName() + ".setSolrInstance()", group, e);
        }
    }

    /**
     * Being called from setSolrInstance in the same class.
     * @param group
     *            hold the group name which is type of {@link java.util.String}
     *            class.
     * @return SolrServer Instance which is object of {@link SolrServer} class.
     * @throws Exception
     *             which is type of {@link java.net.Exception} class.
     */

    public SolrServer getSolrInstance(final String group)
            throws GenericException {
        try {
            if (!hmSolrServer.containsKey(group)) {
                setSolrInstance(proputil.getProperties(domain, "searchengine",
                        "Configuration", group), group);
            }
            return hmSolrServer.get(group);
        } catch (GenericException e) {
            throw e;
        }
    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
    }
}
