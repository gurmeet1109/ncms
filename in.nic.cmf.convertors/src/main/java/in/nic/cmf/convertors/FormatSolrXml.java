package in.nic.cmf.convertors;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.util.ClientUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class FormatSolrXml implements Convertor {
    // private static LogTracer log = new LogTracer(
    // "formatsolrxml",
    // true,
    // true,
    // true,
    // true,
    // false);
    private static HashMap<String, FormatSolrXml> hashFormatSolrXml = new HashMap<String, FormatSolrXml>();
    private LogTracer                             log;
    private String                                domain;

    private FormatSolrXml(String domain) {
        setLogger(domain);
        this.domain = domain;
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "FormatSolrXml", true, true, true, true,
                false);
    }

    public static FormatSolrXml getInstance(String domain) {
        if (!hashFormatSolrXml.containsKey(domain)) {
            hashFormatSolrXml.put(domain, new FormatSolrXml(domain));
        }
        return hashFormatSolrXml.get(domain);
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
    public GenModel in(String o, GenModel g) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(String o, GenModel g)");
        try {
            return FormatXml.getInstance(domain).in(o, g);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(String o, GenModel g)");
        }
    }

    @Override
    public GenModel in(HashMap<String, Object> h, GenModel g)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(HashMap<String, Object> h, GenModel g)");
        try {
            return GenModel.newInstance(domain, h);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(HashMap<String, Object> h, GenModel g)");
        }
    }

    @Override
    public HashMap<String, Object> in(String o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ". in(String o) ");
        try {
            return FormatXml.getInstance(domain).in(o);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ": in(String o)");
        }
    }

    @Override
    public HashMap<String, Object> in(File o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName() + ". in(File o) ");
        try {
            return FormatXml.getInstance(domain).in(o);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ": in(File o)");
        }
    }

    public HashMap in(SolrDocumentList o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(SolrDocumentList o) ");
        try {
            ArrayList<SolrDocument> a = new ArrayList<SolrDocument>();
            ListIterator<SolrDocument> sl = o.listIterator();
            while (sl.hasNext()) {
                a.add(sl.next());
            }
            return in(a);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ": in(Object o)");
        }
    }

    public HashMap in(List<SolrDocument> o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(List<SolrDocument> o) ");
        try {
            HashMap h = new HashMap();
            ArrayList a = new ArrayList();
            for (SolrDocument s : o) {
                a.add(in(s));
            }
            h.put("collections", a);
            return h;
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(List<SolrDocument> o)");
        }
    }

    public HashMap in(SolrDocument o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(SolrDocument o) ");
        try {
            HashMap root = new HashMap();
            HashMap h = new HashMap();
            Iterator i = o.getFieldNames().iterator();
            String rootName = "Entity";
            while (i.hasNext()) {
                String key = i.next().toString();
                log.debug("key ::::: " + key);
                if (o.getFieldValue(key).getClass().getSimpleName()
                        .equals("LinkedList")) {
                    ArrayList a = ConvertorUtils.getInstance(domain)
                            .convertAnyListToArrayList(
                                    (List) o.getFieldValue(key));
                    h.put(key, a);
                    if (key.equalsIgnoreCase("entitytype")) {
                        rootName = a.get(0).toString();
                    }

                } else {
                    String value = "";
                    if (o.getFieldValue(key).getClass().getSimpleName()
                            .equalsIgnoreCase("ArrayList")) {
                        List<String> l = (List<String>) o.getFieldValue(key);
                        value = l.get(0);
                    } else {
                        value = (String) o.getFieldValue(key);
                    }
                    h.put(key, value);
                    if (key.equalsIgnoreCase("entitytype")) {
                        rootName = value;
                        log.debug("Root Name :::: " + rootName);
                    }

                }
            }
            root.put(rootName, h);
            log.debug("Root Map :::: " + root);
            return root;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(SolrDocument o)");
        }
    }

    public Object out(List<SolrDocument> o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(List<SolrDocument> o) ");
        try {
            return out(in(o));
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(List<SolrDocument> o)");
        }
    }

    public Object out(SolrDocumentList o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(SolrDocumentList o) ");
        try {
            ArrayList<SolrDocument> a = new ArrayList<SolrDocument>();
            ListIterator<SolrDocument> sl = o.listIterator();
            while (sl.hasNext()) {
                a.add(sl.next());
            }
            return out((a));
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(SolrDocumentList o)");
        }
    }

    public Object out(SolrDocument o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".  out(SolrDocument o) ");
        try {
            return out(in(o));
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(SolrDocument o)");
        }
    }

    public Object out(SolrInputDocument o) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".  out(SolrInputDocument o) ");
        try {
            return ClientUtils.toXML(o);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(SolrInputDocument o)");
        }
    }

    @Override
    public Object out(HashMap<String, Object> xmlmap) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ".  out(HashMap<String, Object> xmlmap) ");
        try {
            log.debug("xmlmap : " + xmlmap);
            return out(xmlmap, new HashMap());
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(HashMap<String, Object> xmlmap)");
        }
    }

    public Object out(HashMap<String, Object> xmlmap,
            HashMap<String, Object> keyMap) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(HashMap<String, Object> xmlmap,HashMap<String, String> keyMap) ");
        try {
            log.debug("out xmlmap:" + xmlmap.toString());
            return FormatXml.getInstance(domain).out(xmlmap, keyMap);
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(HashMap<String, Object> xmlmap,HashMap<String, String> keyMap)");
        }
    }

    @Override
    public Object out(GenModel genModel) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(GenModel genModel) ");
        try {
            return FormatXml.getInstance(domain).out(genModel);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(GenModel genModel)");
        }
    }

    public ArrayList<SolrInputDocument> out(HashMap<String, Object> xmlmap,
            HashMap<String, String> keyMap, boolean solrformat)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(HashMap<String, Object> xmlmap,HashMap<String, String> keyMap, boolean solrformat) ");
        try {
            ArrayList<SolrInputDocument> a = new ArrayList<SolrInputDocument>();
            if (xmlmap.containsKey("Collections")) {
                Object children = xmlmap.get("Collections");
                if (children.getClass().getSimpleName().equals("HashMap")) {
                    a = processSolrDocHashMap(children, a, keyMap);
                } else if (children.getClass().getSimpleName()
                        .equals("ArrayList")) {
                    a = processSolrDocArrayList(children, a, keyMap);
                }
            } else {
                a.add(getInputDoc(xmlmap, keyMap));
            }
            return a;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(HashMap<String, Object> xmlmap,HashMap<String, String> keyMap, boolean solrformat)");
        }
    }

    private ArrayList<SolrInputDocument> processSolrDocArrayList(
            Object children, ArrayList<SolrInputDocument> a,
            HashMap<String, String> keyMap) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". processSolrDocArrayList(Object children, ArrayList<SolrInputDocument> a,HashMap<String, String> keyMap) ");
        try {
            ArrayList<HashMap> c = (ArrayList) children;
            for (HashMap h : c) {
                a.add(getInputDoc(h, keyMap));
            }
            return a;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": processSolrDocArrayList(Object children, ArrayList<SolrInputDocument> a,HashMap<String, String> keyMap)");
        }
    }

    private ArrayList<SolrInputDocument> processSolrDocHashMap(Object children,
            ArrayList<SolrInputDocument> a, HashMap<String, String> keyMap)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". processSolrDocHashMap(Object children,	ArrayList<SolrInputDocument> a, HashMap<String, String> keyMap) ");
        try {
            HashMap<String, Object> child = (HashMap) children;
            for (Entry<String, Object> e : child.entrySet()) {
                if (e.getValue().getClass().getSimpleName().equals("ArrayList")) {
                    ArrayList<HashMap> c = (ArrayList) e.getValue();
                    a = processSolrDocArrayList(c, a, keyMap);
                } else if (e.getValue().getClass().getSimpleName()
                        .equals("HashMap")) {
                    a.add(getInputDoc((HashMap) e.getValue(), keyMap));
                }
            }
            return a;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": processSolrDocHashMap(Object children,	ArrayList<SolrInputDocument> a, HashMap<String, String> keyMap)");
        }
    }

    public SolrInputDocument getInputDoc(HashMap<String, Object> h,
            HashMap<String, String> keyMap) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". getInputDoc(HashMap<String, Object> h,	HashMap<String, String> keyMap) ");
        try {
            MultivaluedMapImpl resultMap = asHashMap(h,
                    new MultivaluedMapImpl());
            SolrInputDocument s = new SolrInputDocument();
            for (Map.Entry e : resultMap.entrySet()) {
                String dataType = "";
                String key = (String) e.getKey();
                if (key.contains("_")) {
                    String[] newKey = key.split("_");
                    key = newKey[0];
                    dataType = newKey[1];
                }
                if (dataType.equals("")) {
                    s.setField((ConvertorUtils.getInstance(domain)
                            .getAlternateNodeName(key, keyMap)), e.getValue());
                } else {
                    s.setField(
                            (ConvertorUtils.getInstance(domain)
                                    .getAlternateNodeName(key, keyMap))
                                    + "_"
                                    + dataType.toLowerCase(), e.getValue());
                }
            }
            return s;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": getInputDoc(HashMap<String, Object> h,	HashMap<String, String> keyMap)");
        }
    }

    private MultivaluedMapImpl asHashMap(HashMap<String, Object> xmlmap,
            MultivaluedMapImpl master) throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". asHashMap(HashMap<String, Object> xmlmap,MultivaluedMapImpl master) ");
        try {
            for (Map.Entry<String, Object> e : xmlmap.entrySet()) {
                if (e.getValue() != null) {
                    if (e.getValue().getClass().getSimpleName()
                            .equals("HashMap")) {
                        asHashMap((HashMap) e.getValue(), master);
                    } else if (e.getValue().getClass().getSimpleName()
                            .equals("String")) {
                        master.add((String) e.getKey(), e.getValue());
                    } else if (e.getValue().getClass().getSimpleName()
                            .equals("ArrayList")) {
                        for (Object o : (ArrayList) e.getValue()) {
                            if (o.getClass().getSimpleName().equals("HashMap")) {
                                asHashMap((HashMap) o, master);
                            } else {
                                master.add((String) e.getKey(), (String) o);
                            }
                        }
                    }
                }
            }
            return master;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  asHashMap(HashMap<String, Object> xmlmap,MultivaluedMapImpl master)");
        }
    }

    public static void main(String[] args) throws Exception {
        String domain = "kalpana.in";
        FormatSolrXml formatsolr = FormatSolrXml.getInstance(domain);
        String x = "<Article><Id>lievpGhieghha</Id><Entitytype>Article</Entitytype><NpiMetadata><NpiUrl><![CDATA[http://sample.com]]></NpiUrl><AltURI><![CDATA[http://sample.com]]></AltURI><MainTitle><![CDATA[today]]></MainTitle><AltTitle><![CDATA[today]]></AltTitle><NpiDescription><![CDATA[today]]></NpiDescription><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a><Classification><Sector>Agriculture</Sector></Classification><PublisherOrgNames><PublisherOrgName>Organisation of Animal Husbandry</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>Department of Animal Husbandry and Dairying</PublisherDepartment></PublisherDeptName><PublisherAddress>today</PublisherAddress><CreatorOrgNames><CreatorOrgName>Organisation of Agriculture and Co-operation</CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>Department of Agricultural Research and Education</CreatorDepartment></CreatorDeptName><CreatorAddress>today</CreatorAddress><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CoverageSpatial><Spatial>Anantapur</Spatial></CoverageSpatial><CoverageTemporal><Temporal>Acts</Temporal></CoverageTemporal><Audience><AudienceCategory>Contact Directory</AudienceCategory></Audience><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasPart></HasPart><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></NpiMetadata></Article>";
        // x
        // ="<Collections><Count>2317</Count><k><a>aa</a><a>ab</a><a>ac</a><a><b><c>cca</c><c>ccb</c><c>ccc</c></b></a></k><News><Id>liynuhahgefih</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynuhahfgiag</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynqffbibdab</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynqffbfadbf</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmgehcifbc</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmgdbiiecg</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmfegcicjc</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmfchadbfd</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmeefagddd</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmecgaigbj</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News></Collections>";
        // x
        x = "<Collections><Article><Id>lievpGhieghhb</Id><Entitytype>Article</Entitytype><NpiMetadata><NpiUrl><![CDATA[http://sample.com]]></NpiUrl><AltURI><![CDATA[http://sample.com]]></AltURI><MainTitle><![CDATA[today]]></MainTitle><AltTitle><![CDATA[today]]></AltTitle><NpiDescription><![CDATA[today]]></NpiDescription><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a><Classification><Sector>Agriculture</Sector></Classification><PublisherOrgNames><PublisherOrgName>Organisation of Animal Husbandry</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>Department of Animal Husbandry and Dairying</PublisherDepartment></PublisherDeptName><PublisherAddress>today</PublisherAddress><CreatorOrgNames><CreatorOrgName>Organisation of Agriculture and Co-operation</CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>Department of Agricultural Research and Education</CreatorDepartment></CreatorDeptName><CreatorAddress>today</CreatorAddress><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CoverageSpatial><Spatial>Anantapur</Spatial></CoverageSpatial><CoverageTemporal><Temporal>Acts</Temporal></CoverageTemporal><Audience><AudienceCategory>Contact Directory</AudienceCategory></Audience><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasPart></HasPart><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></NpiMetadata></Article><Article><Id>lievpGhieghha</Id><Entitytype>Article</Entitytype><NpiMetadata><NpiUrl><![CDATA[http://sample.com]]></NpiUrl><AltURI><![CDATA[http://sample.com]]></AltURI><MainTitle><![CDATA[today]]></MainTitle><AltTitle><![CDATA[today]]></AltTitle><NpiDescription><![CDATA[today]]></NpiDescription><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a><Classification><Sector>Agriculture</Sector></Classification><PublisherOrgNames><PublisherOrgName>Organisation of Animal Husbandry</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>Department of Animal Husbandry and Dairying</PublisherDepartment></PublisherDeptName><PublisherAddress>today</PublisherAddress><CreatorOrgNames><CreatorOrgName>Organisation of Agriculture and Co-operation</CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>Department of Agricultural Research and Education</CreatorDepartment></CreatorDeptName><CreatorAddress>today</CreatorAddress><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CoverageSpatial><Spatial>Anantapur</Spatial></CoverageSpatial><CoverageTemporal><Temporal>Acts</Temporal></CoverageTemporal><Audience><AudienceCategory>Contact Directory</AudienceCategory></Audience><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasPart></HasPart><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></NpiMetadata></Article></Collections>";
        // x =
        // "<Collections><Article><Id>lievpGhieghhb</Id><Entitytype>Article</Entitytype><NpiMetadata><NpiUrl><![CDATA[http://sample.com]]></NpiUrl><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a></NpiMetadata></Article><Article><Id>lievpGhieghha</Id><Entitytype>Article</Entitytype><NpiMetadata><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></NpiMetadata></Article></Collections>";
        // x =
        // "<Collections><Article><Id>lievpGhiegood</Id><Entitytype>Article</Entitytype></Article><Article><Id>lievpGhiegbad</Id><Entitytype>Article</Entitytype></Article></Collections>";
        x = "<Collections><Role><RoleName_String>PortalAdmin</RoleName_String><DomainEntity_String>AccessControlList,Address,AppAdmin,Article,ArticleType,AudienceCategory,Awards,Banner,Book,Category,City,ClassAttributes,ClassImport,ClassInfo,ClassPackage,ContactNumber,ContactType,ContentPartner,Country,Css,Designation,Domain,DomainEntity,UserProfile,Action,Feedback,Form,Format,Gallery,GoiDirCat,GoiDirItem,InformationArchitecture,InterfaceInfo,Js,JurisdictionType,Language,Media,MediaGroup,News,OfficeLocation,Page,PageAssociator,Parliament,PmsMedia,PortFolio,Prefix,Role,Scheme,Sector,Service,ServiceType,Source,SourceType,Stage,Status,Template,CmsUserProfile,Validations,Viewer,WhosWhoCat,Whoswhoprofile,Widget,Workflow,NpiMetadata,Currency,Crawleddata</DomainEntity_String><LastModifiedDate_String>2012-05-14T19:14:45Z</LastModifiedDate_String><LastModifiedBy_String>bookadmin</LastModifiedBy_String><UiTab_String>Admin,CMS,PMS,Domain,Feed,Automation,Users And Roles,Workflow</UiTab_String><ParentRoleName_String/><CreatedBy_String>bookadmin</CreatedBy_String><ParentRoleId_String/><Version_String>1.0</Version_String><EntityType_String>Role</EntityType_String><Site_String>kalpana.in</Site_String><CreatedDate_String>2012-05-14T19:14:45Z</CreatedDate_String><Id>mfotoJdfeahdf</Id></Role></Collections>";
        HashMap h1 = FormatXml.getInstance("kalpana.in").in(x);

        System.out.println(h1);

        // HashMap h = formatsolr.in(x);
        // System.out.println("hashMap ::" + h);
        // System.out.println(formatsolr.out(h));

        /*
         * HashMap keyMap = FormatJson.getInstance().in(new File(
         * "/home/kavi/SVN/trunk/in.nic.cmf.services.searchengine/src/main/resources"
         * )); System.out.println("keyMap:::"+keyMap);
         * ArrayList<SolrInputDocument> a = formatsolr.out(h,keyMap, true);
         * SolrDocumentList sl = new SolrDocumentList(); for (SolrInputDocument
         * si : a) { sl.add(ClientUtils.toSolrDocument(si)); }
         * System.out.println("sl output:" + sl); HashMap hsh =
         * formatsolr.in(sl); ArrayList c = (ArrayList) hsh.get("collections");
         * HashMap count = new HashMap(); count.put("count", "0"); c.add(count);
         * hsh.put("collections", c);
         * System.out.println("Hsh output:" + hsh);
         * System.out.println("XML output:" + formatsolr.out(hsh));
         */
        // System.out.println("XML output:" + formatsolr.out(sl));

    }
}
