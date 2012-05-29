package in.nic.cmf.convertors;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 */
public class FormatXml implements Convertor {

    // private static LogTracer log = new LogTracer("formatxml", true,
    // true, true, true, false);
    private LogTracer                         log;
    private ConvertorUtils                    cu;
    private static HashMap<String, FormatXml> hashFormatXml = new HashMap<String, FormatXml>();
    private String                            domain;

    private FormatXml(String domain) {
        cu = ConvertorUtils.getInstance(domain);
        setLogger(domain);
        this.domain = domain;
    }

    private void setLogger(String domain) {
        log = new LogTracer(domain, "FormatFlatten", true, true, true, true,
                false);
    }

    /**
     * Method getInstanceOf.
     * @return FormatXml
     */
    public static FormatXml getInstance(String domain) {
        if (!hashFormatXml.containsKey(domain)) {
            hashFormatXml.put(domain, new FormatXml(domain));
        }
        return hashFormatXml.get(domain);
    }

    /**
     * Method in.
     * @param o
     *            Object
     * @return HashMap
     * @throws GenericException
     * @see in.nic.cmf.convertors.Convertor#in(Object)
     */
    @Override
    public HashMap in(Object o) throws GenericException {
        this.log.info("inside in(Object o)");
        log.methodEntry(this.getClass().getSimpleName() + ". in(Object o)");
        try {
            return in((String) o);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName() + ": in(Object o)");
        }
    }

    /**
     * Method in.
     * @param o
     *            Object
     * @return HashMap
     * @throws GenericException
     * @see in.nic.cmf.convertors.Convertor#in(Object)
     */
    public HashMap in(String o, HashMap<String, String> keyMap)
            throws GenericException {
        this.log.info("inside in(String o, HashMap<String, String> keyMap)");
        log.methodEntry(this.getClass().getSimpleName()
                + "in(String o, HashMap<String, String> keyMap)");
        try {
            Document d = cu.asDoc(o);
            return visit(d, keyMap);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(String o, HashMap<String, String> keyMap)");
        }
    }

    @Override
    public HashMap<String, Object> in(String s) throws GenericException {
        this.log.info("inside HashMap<String, Object> in(String s)");
        log.methodEntry(this.getClass().getSimpleName()
                + "HashMap<String, Object> in(String s)");
        try {
            return in(s, new HashMap<String, String>());
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000");
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": HashMap<String, Object> in(String s)");
        }
    }

    @Override
    public HashMap<String, Object> in(File o) throws GenericException {
        this.log.info("inside HashMap<String, Object> in(File o)");
        log.methodEntry(this.getClass().getSimpleName()
                + ".HashMap<String, Object> in(File o)");
        try {
            String s = cu.getFileAsString(o);
            return in(s);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": HashMap<String, Object> in(File o)");
        }
    }

    /**
     * Method in.
     * @param o
     *            Object
     * @param genmodel
     *            boolean
     * @return GenModel
     * @throws GenericException
     * @see in.nic.cmf.convertors.Convertor#in(Object, boolean)
     */
    @Override
    public GenModel in(String o, GenModel g) throws GenericException {
        this.log.info("inside in(String o, GenModel g)");
        log.methodEntry(this.getClass().getSimpleName()
                + ". in(String o, GenModel g)");
        try {
            Document d = cu.asDoc((String) o);
            return visit(d, true);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(String o, GenModel g)");
        }
    }

    @Override
    public GenModel in(HashMap<String, Object> h, GenModel g)
            throws GenericException {
        this.log.info("inside in(HashMap<String, Object> h, GenModel g)");
        log.methodEntry(this.getClass().getSimpleName()
                + ".in(HashMap<String, Object> h, GenModel g)");
        try {
            return GenModel.newInstance(domain, h);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": in(HashMap<String, Object> h, GenModel g)");
        }
    }

    /**
     * Method out.
     * @param xmlmap
     *            HashMap<Object,Object>
     * @return Object
     * @throws GenericException
     * @see in.nic.cmf.convertors.Convertor#out(HashMap<Object,Object>)
     */
    @Override
    public Object out(HashMap<String, Object> xmlmap) throws GenericException {
        this.log.info("inside out(HashMap<String, Object> xmlmap)");
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(HashMap<String, Object> xmlmap)");
        log.debug("out xmlmap:" + xmlmap.toString());
        try {
            return out(xmlmap, new HashMap<String, Object>());
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(HashMap<String, Object> xmlmap)");
        }
    }

    /**
     * Method out.
     * @param xmlmap
     *            HashMap<Object,Object>
     * @return Object
     * @throws GenericException
     * @see in.nic.cmf.convertors.Convertor#out(HashMap<Object,Object>)
     */
    public Object out(HashMap<String, Object> xmlmap,
            HashMap<String, Object> keyMap) throws GenericException {
        this.log.info("inside getEntityBySearch");
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(HashMap<String, Object> xmlmap,HashMap<String, String> keyMap)");
        log.debug("out xmlmap:" + xmlmap.toString() + "::::::::::::::::::"
                + keyMap);
        try {
            return asXML(xmlmap, keyMap);
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": out(HashMap<String, Object> xmlmap,HashMap<String, String> keyMap)");
        }
    }

    /**
     * Method out.
     * @param genModel
     *            GenModel
     * @return Object
     * @throws GenericException
     * @see in.nic.cmf.convertors.Convertor#out(GenModel)
     */
    @Override
    public Object out(GenModel genModel) throws GenericException {
        this.log.info("inside out(GenModel genModel)");
        log.methodEntry(this.getClass().getSimpleName()
                + ". out(GenModel genModel)");
        try {
            return out(genModel.asHashMap());
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

    /**
     * Method visit.
     * @param node
     *            Node
     * @param genmodel
     *            boolean
     * @return GenModel
     * @throws GenericException
     */
    private GenModel visit(Node node, boolean genmodel) throws GenericException {
        this.log.info("inside visit(Node node, boolean genmodel)");
        log.methodEntry(this.getClass().getSimpleName()
                + ". visit(Node node, boolean genmodel)");
        try {
            NodeList nl = node.getChildNodes();
            GenModel g = GenModel.newInstance(domain);
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.hasChildNodes()) {
                    if (n.getFirstChild().getNodeType() != Node.TEXT_NODE
                            && n.getFirstChild().getNodeType() != Node.CDATA_SECTION_NODE) {
                        g = checkAndPut(g, n.getNodeName(),
                                visit(n, new HashMap<String, String>()));
                    } else {
                        g = checkAndPut(g, n.getNodeName(), n.getFirstChild()
                                .getNodeValue());
                    }
                } else {
                    if (n.getNodeType() != Node.TEXT_NODE
                            && n.getNodeType() != Node.CDATA_SECTION_NODE) {
                        g.set(n.getNodeName(), n.getNodeValue());
                    }
                }
            }
            return g;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CONV-0002",
                    "Issue in XML Paring", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": visit(Node node, boolean genmodel)");
        }
    }

    /**
     * Method checkAndPut.
     * @param g
     *            GenModel
     * @param k
     *            String
     * @param v
     *            Object
     * @return GenModel
     * @throws GenericException
     */
    private GenModel checkAndPut(GenModel g, String k, Object v)
            throws GenericException {
        this.log.info("inside checkAndPut");
        log.methodEntry(this.getClass().getSimpleName()
                + ". checkAndPut(GenModel g, String k, Object v)");
        try {
            if (null != g.get(k)) {// for scalar
                ArrayList a = new ArrayList();
                if (g.get(k).getClass().getSimpleName().equals("ArrayList")) {
                    a = (ArrayList) g.get(k);
                } else {
                    a.add(g.get(k));
                }
                a.add(v);
                g.set(k, a);
            } else {
                g.set(k, v);
            }
            return g;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CONV-0002",
                    "Issue in XML Parsing", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  checkAndPut(GenModel g, String k, Object v)");
        }
    }

    /**
     * Method visit.
     * @param node
     *            Node
     * @return HashMap
     * @throws GenericException
     */
    private HashMap visit(Node node, HashMap<String, String> keyMap)
            throws GenericException {
        this.log.info("inside visit(Node node, HashMap<String, String> keyMap)");
        log.methodEntry(this.getClass().getSimpleName()
                + ". visit(Node node, HashMap<String, String> keyMap)");
        try {
            NodeListImpl nl = new NodeListImpl(node.getChildNodes());
            HashMap h = new HashMap();
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                String nodeName = cu.getAlternateNodeName(n.getNodeName(),
                        keyMap);
                List<Attr> attributes = new ArrayList<Attr>();
                if (n.hasAttributes()) {
                    NamedNodeMap attrs = n.getAttributes();
                    for (int j = 0; j < attrs.getLength(); j++) {
                        Attr attribute = (Attr) attrs.item(j);
                        attributes.add(attribute);
                    }
                }
                if (n.hasChildNodes()) {
                    NodeListImpl nlChild = new NodeListImpl(n.getChildNodes());
                    if (nlChild.getLength() == 1) {
                        if (nlChild.item(0).getNodeType() == Node.TEXT_NODE
                                || nlChild.item(0).getNodeType() == Node.CDATA_SECTION_NODE) {
                            h = checkAndPut(h, nodeName, nlChild.item(0)
                                    .getNodeValue(), attributes);
                        } else {
                            h = checkAndPut(h, nodeName, visit(n, keyMap),
                                    attributes);
                        }
                    } else {
                        h = checkAndPut(h, nodeName, visit(n, keyMap),
                                attributes);
                    }
                } else {
                    if (n.getNodeType() != Node.TEXT_NODE
                            && n.getNodeType() != Node.CDATA_SECTION_NODE) {
                        h.put(nodeName, n.getNodeValue());
                    }
                }
            }
            return h;
        } catch (GenericException ge) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), ge);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CONV-0002",
                    "Issue in XML Paring", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  visit(Node node, HashMap<String, String> keyMap)");
        }
    }

    /**
     * Method checkAndPut.
     * @param h
     *            HashMap
     * @param k
     *            String
     * @param v
     *            Object
     * @return HashMap
     * @throws GenericException
     */
    private HashMap checkAndPut(HashMap h, String k, Object v, List<Attr> attr)
            throws GenericException {
        this.log.info("inside  visit(Node node, HashMap<String, String> keyMap)");
        log.methodEntry(this.getClass().getSimpleName()
                + ". checkAndPut(HashMap h, String k, Object v)");
        try {
            if (h.containsKey(k)) {
                ArrayList a = new ArrayList();
                if (h.get(k).getClass().getSimpleName().equals("ArrayList")) {
                    a = (ArrayList) h.get(k);
                } else {
                    a.add(h.get(k));
                }
                if (!attr.isEmpty()) {
                    Map map = getAttributeMap(attr, v);
                    a.add(map);
                } else {
                    a.add(v);
                }

                h.put(k, a);
            } else {
                if (!attr.isEmpty()) {
                    Map map = getAttributeMap(attr, v);
                    h.put(k, map);
                } else {
                    h.put(k, v);
                }
            }
            return h;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CONV-0002",
                    "Issue in XML Paring", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": checkAndPut(HashMap h, String k, Object v)");
        }
    }

    private Map getAttributeMap(List<Attr> attr, Object v) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (Attr a1 : attr) {
            map.put("@" + a1.getName(), a1.getValue());
        }
        if (v.getClass().getSimpleName().equalsIgnoreCase("String")) {
            map.put("_value_", v);
        } else {
            map.putAll((Map<? extends Object, ? extends Object>) v);
        }
        return map;
    }

    /**
     * Method asXML.
     * @param xmlMap
     *            HashMap<Object,Object>
     * @return String
     * @throws GenericException
     */
    private String asXML(HashMap<String, Object> xmlMap,
            HashMap<String, Object> keyMap) throws GenericException {
        this.log.info("inside asXML(HashMap<String, Object> xmlMap, HashMap keyMap)");
        log.debug("asXML keyMap:::::::::::::::::::" + keyMap);
        log.methodEntry(this.getClass().getSimpleName()
                + ". asXML(HashMap<String, Object> xmlMap, HashMap keyMap)");
        try {
            return transformInToXML(buildXML(xmlMap, keyMap));
        } catch (GenericException ge) {
            throw ge;
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ":  asXML(HashMap<String, Object> xmlMap, HashMap keyMap)");
        }
    }

    /**
     * Method transformInToXML.
     * @param document
     *            Document
     * @return String
     * @throws GenericException
     */
    private String transformInToXML(Document document) throws GenericException {
        this.log.info("inside transformInToXML(Document document)");
        log.methodEntry(this.getClass().getSimpleName()
                + ".transformInToXML(Document document)");
        try {
            StringWriter stw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance()
                    .newTransformer();
            // serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer
                    .setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            serializer
                    .transform(new DOMSource(document), new StreamResult(stw));
            return stw.toString();
        } catch (TransformerException e) {
            throw new GenericException(domain, "ERR-CONV-0004",
                    "Issue in xml transformation", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": transformInToXML(Document document)");
        }
    }

    /**
     * Method buildXML.
     * @param xmlMap
     *            HashMap<Object,Object>
     * @return Document
     * @throws GenericException
     */
    private Document buildXML(HashMap<String, Object> xmlMap,
            HashMap<String, Object> keyMap) throws GenericException {
        this.log.info("inside buildXML(HashMap<String, Object> xmlMap, HashMap keyMap)");
        log.debug("buildXML keyMap:::::" + keyMap);
        log.methodEntry(this.getClass().getSimpleName()
                + ". buildXML(HashMap<String, Object> xmlMap, HashMap keyMap)");
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element rootElement = null;
            rootElement = document.createElement("_DUMMY_");
            rootElement = buildXML(xmlMap, rootElement, keyMap);
            document.appendChild(rootElement.getFirstChild());
            return document;
        } catch (ParserConfigurationException e) {
            throw new GenericException(domain, "ERR-CONV-0004",
                    "Issue in xml transformation", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": buildXML(HashMap<String, Object> xmlMap, HashMap keyMap)");
        }
    }

    /**
     * Method buildXML.
     * @param xmlMap
     *            Map<Object,Object>
     * @param ownerElement
     *            Element
     * @return Element
     * @throws GenericException
     */
    private Element buildXML(Map<String, Object> xmlMap, Element ownerElement,
            HashMap<String, Object> FieldMapping) throws GenericException {
        // System.out
        // .println("Main method buildXML:::::::::::::::" + FieldMapping);

        log.methodEntry(this.getClass().getSimpleName()
                + ". buildXML(Map<String, Object> xmlMap, Element ownerElement,HashMap<String, String> keyMap)");
        try {
            Element element = null;
            HashMap<String, String> keyMap = (HashMap<String, String>) cu
                    .getMappingHashMap(FieldMapping, "fieldNameMapping");

            ArrayList<String> cdataList = cu.getMappingArrayList(FieldMapping,
                    "cdataFieldMapping");
            for (Entry<String, Object> e : xmlMap.entrySet()) {
                String key = (String) e.getKey();
                boolean cdata = cu.isCdataField(key, cdataList);
                if (keyMap.containsKey(key)) {
                    key = (String) keyMap.get(key);
                }
                if (!key.startsWith("@") && !key.matches("_value_")) {
                    element = ownerElement.getOwnerDocument()
                            .createElement(key);

                    if (e.getValue() != null) {
                        if (cu.isHashMap(e.getValue())) {
                            addElement(ownerElement, element,
                                    (Map<String, Object>) e.getValue(),
                                    FieldMapping);
                        }

                        if (cu.isString(e.getValue())) {
                            addElement(ownerElement, element,
                                    (String) e.getValue(), cdata);
                        }

                        if (cu.isArrayList(e.getValue())) {
                            ArrayList<Object> a = (ArrayList<Object>) e
                                    .getValue();
                            Element el = null;
                            for (Object o : a) {
                                if (ownerElement.getNodeName()
                                        .equalsIgnoreCase("_DUMMY_")) {
                                    el = (Element) element;
                                } else {
                                    el = (Element) element.cloneNode(true);
                                }

                                if (cu.isString(o)) {
                                    addElement(ownerElement, el, (String) o,
                                            cdata);
                                } else {
                                    addElement(ownerElement, el,
                                            (Map<String, Object>) o,
                                            FieldMapping);
                                }
                            }
                        }
                    } else {
                        addElement(ownerElement, element, "", cdata);
                    }
                } else if (key.startsWith("@")) {
                    ownerElement.setAttribute(key.replace("@", ""),
                            (String) e.getValue());
                } else if (key.matches("_value_")) {
                    ownerElement.appendChild(ownerElement.getOwnerDocument()
                            .createTextNode((String) e.getValue()));
                }
            }
            return ownerElement;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CONV-0004",
                    "Issue in xml transformation", e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": buildXML(Map<String, Object> xmlMap, Element ownerElement,	HashMap<String, String> keyMap)");
        }
    }

    private void addElement(Element ownerElement, Element element, String e,
            Boolean cdata) {
        if (!cdata) {
            element.appendChild(element.getOwnerDocument().createTextNode(e));
        } else {
            element.appendChild(element.getOwnerDocument()
                    .createCDATASection(e));
        }
        ownerElement.appendChild(element);
    }

    private void addElement(Element ownerElement, Element element,
            Map<String, Object> e, HashMap keyMap) {

        ownerElement.appendChild(buildXML(e, element,
                (HashMap<String, Object>) keyMap));
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // System.out.println("inside main method");
        String domain = "kalpana.in";
        FormatXml formatxml = FormatXml.getInstance(domain);
        // String x =
        // "<Article><id>lievpGhieghha</id><entitytype>Article</entitytype><npiMetadata><npiUrl><![CDATA[http://sample.com]]></npiUrl><AltURI><![CDATA[http://sample.com]]></AltURI><MainTitle><![CDATA[today]]></MainTitle><AltTitle><![CDATA[today]]></AltTitle><NpiDescription><![CDATA[today]]></NpiDescription><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a><Classification><sector>Agriculture</sector></Classification><PublisherOrgNames><PublisherOrgName>Organisation of Animal Husbandry</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>Department of Animal Husbandry and Dairying</PublisherDepartment></PublisherDeptName><publisheraddress>Dubai kurukku sandhu, Dubai</publisheraddress><CreatorOrgNames><CreatorOrgName>Organisation of Agriculture and Co-operation</CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>Department of Agricultural Research and Education</CreatorDepartment></CreatorDeptName><CreatorAddress>today</CreatorAddress><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CoverageSpatial><Spatial>Anantapur</Spatial></CoverageSpatial><CoverageTemporal><Temporal>Acts</Temporal></CoverageTemporal><Audience><AudienceCategory>Contact Directory</AudienceCategory></Audience><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasPart></HasPart><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></npiMetadata></Article>";
        String x = "<Article><id>lievpGhieghha</id><entitytype>Article</entitytype><sourcename><![CDATA[http://sample.com]]></sourcename><shortdescription><![CDATA[http://sample.com]]></shortdescription></Article>";
        x = "<Collections><messages>  <note id='501'>    <to id='tell'>Tove</to>    <from>Jani</from>    <heading fontface='ariel' fontsize='12'>  <Title>Sunday</Title>        <Description fontface='timesnewroman' fontsize='15'>            <Keywords>Jungle</Keywords>            <DetailedNotes reqularfontface='fanta' reqularfontsize = '20'>                <ShortNotes>Sunday</ShortNotes>                <LongNotes>Monday</LongNotes>            </DetailedNotes>                       </Description>     </heading>    <body>Don't forget me this weekend!</body>  </note>  <dnote id='502'>    <to>Arun</to>    <from>Bala</from>    <heading>Subject</heading>    <body>I will not come</body>  </dnote> <enote>    <to>Chandra</to>    <from>Daniel</from>    <heading>Re: Reminder</heading>    <body>I will come</body>  </enote></messages></Collections>";
        // x =
        // "<Collections><City><Id>1234CHghbgbai</Id><EntityType>City</EntityType><CityName>chennai</CityName><CreatedDate>2011-09-18T16:38:35Z</CreatedDate><LastModifiedDate>2011-09-18T16:38:35Z</LastModifiedDate><CreatedBy>igportaladmin</CreatedBy><LastModifiedBy>igportaladmin</LastModifiedBy><Site>nic.in</Site><Version>1.0</Version><Address>test Address</Address></City></Collections>";
        // x =
        // "<map id='123'><parent key='p1'><child key='c1'><value key='v1'>value1</value></child></parent><parent key='p2'><child key='c2'><value key='v2'>value2</value></child></parent></map>";
        // x =
        // "<map><parent key='p1' key1='1p'>p1</parent><parent>p2</parent><parent key='p3'>p3</parent><parent1 key='p11'>p11</parent1><parent1 key='p12'>p12</parent1></map>";
        // x = "<map id='123' id1='345'>test</map>";
        // x =
        // "<Collections><Count>2317</Count><k><a>aa</a><a>ab</a><a>ac</a><a><b><c>cca</c><c>ccb</c><c>ccc</c></b></a></k><News><Id>liynuhahgefih</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynuhahfgiag</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynqffbibdab</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynqffbfadbf</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmgehcifbc</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmgdbiiecg</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmfegcicjc</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmfchadbfd</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmeefagddd</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News><News><Id>liynmecgaigbj</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News></Collections>";
        // x =
        // "<Collections><Count>2317</Count><k><a>aa</a><a>ab</a><a>ac</a><a><b><c>cca</c><c>ccb</c><c>ccc</c></b></a></k><News><Id>liynuhahgefih</Id><EntityType>News</EntityType><AliasId></AliasId><AltNewsCategory><![CDATA[]]></AltNewsCategory><Title><![CDATA[]]></Title><Description><![CDATA[]]></Description><Url><![CDATA[]]></Url><PubDate></PubDate><AssociatedIAID></AssociatedIAID><AssociatedIAName></AssociatedIAName><AssociatedIAPath></AssociatedIAPath><CreatedDate></CreatedDate><LastModifiedDate></LastModifiedDate><CreatedBy></CreatedBy><LastModifiedBy></LastModifiedBy><Site></Site><SeoUrl><![CDATA[]]></SeoUrl><Version></Version></News></Collections>";
        // x = readFile("/home/kavi/solr.xml");
        // System.out.println("solr xml::"+x);

        File file = new File("/opt/cmf/resources/sample.json");
        InputStream is = new FileInputStream(file);
        HashMap h = FormatJson.getInstance(domain).in(IOUtils.toString(is));
        // System.out.println(h);

        // System.out.println(FormatJson.getInstanceof(domain).out(h));

        File file1 = new File("/opt/cmf/resources/FieldNameMapping.json");
        InputStream is1 = new FileInputStream(file1);
        HashMap h1 = FormatJson.getInstance(domain).in(IOUtils.toString(is1));

        String s = (String) formatxml.out(h);
        // System.out.println(s);
        HashMap xmlmap = formatxml.in(x);
        // System.out.println("xmlmap::::::::::" + xmlmap);

        // HashMap xmlmap1 = FormatFlatten.getInstanceOf("kalpana.in").in(s);
        System.out.println(xmlmap);

        // FileInputStream is = new
        // FileInputStream("/tmp/FieldNameMapping.json");
        // String s = IOUtils.toString(is);

        // HashMap keyMap = FormatJson.getInstanceof(domain).in(s);
        // HashMap x1 = (HashMap) keyMap.get("dmsfieldNameMapping");
        /*
         * keyMap.put("fieldNameMapping",
         * (HashMap) keyMap.get("dmsfieldNameMapping"));
         */
        // HashMap test = new HashMap();
        // test.put("cdataFieldMapping", keyMap.get("cdataFieldMapping"));
        // cdataFieldMapping , reverseFieldNameMapping
        // System.out.println("keyMap::::::::::::::" + keyMap);
        // System.out.println(formatxml.out(xmlmap, new HashMap()));
        // System.out.println(formatxml.out(xmlmap, test));
        /*
         * ArrayList<HashMap> l = (ArrayList<HashMap>) f.get("classname");
         * HashMap keys = new HashMap<String, String>(); for (HashMap o : l) {
         * ArrayList<HashMap> fields = (ArrayList<HashMap>) o.get("fields"); for
         * (HashMap eachField : fields) { HashMap jaxb = (HashMap)
         * eachField.get("jaxb"); keys.put(eachField.get("name"),
         * jaxb.get("name")); } }
         */

        // HashMap h = formatxml.in(x);
        // HashMap hCount = new HashMap();
        // hCount.put("Count", "10");
        // ArrayList a = new ArrayList();
        // a.add(h);
        // a.add(hCount);
        /*
         * hCollections.put("Collections", a);
         * System.out.println("collections:::::::::" + hCollections);
         * System.out.println("Solr XML::::" + formatxml.out(hCollections));
         */
    }
}
