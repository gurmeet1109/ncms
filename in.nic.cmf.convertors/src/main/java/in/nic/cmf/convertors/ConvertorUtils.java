package in.nic.cmf.convertors;

import in.nic.cmf.exceptions.GenericException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ConvertorUtils {
    private static final DocumentBuilderFactory    dbf                = DocumentBuilderFactory
                                                                              .newInstance();
    private static HashMap<String, ConvertorUtils> hashConvertorUtils = new HashMap<String, ConvertorUtils>();
    private String                                 domain;

    private ConvertorUtils() {
    }

    public static ConvertorUtils getInstance(String domain) {
        if (!hashConvertorUtils.containsKey(domain)) {
            hashConvertorUtils.put(domain, new ConvertorUtils());
        }
        return hashConvertorUtils.get(domain);
    }

    public Document asDoc(File xmlfile) throws GenericException {

        try {
            return dbf.newDocumentBuilder().parse(xmlfile);
        } catch (SAXException e) {
            throw new GenericException(domain, "ERR-CON-0002", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-CON-0002", e);
        } catch (ParserConfigurationException e) {
            throw new GenericException(domain, "ERR-CON-0002", e);
        }
    }

    public Document asDoc(String xml) throws GenericException {

        try {
            InputStream is = new ByteArrayInputStream(xml.getBytes());
            return dbf.newDocumentBuilder().parse(is);
        } catch (SAXException e) {
            throw new GenericException(domain, "ERR-CON-0002", e);
        } catch (IOException e) {
            throw new GenericException(domain, "ERR-CON-0002", e);
        } catch (ParserConfigurationException e) {
            throw new GenericException(domain, "ERR-CON-0002", e);
        }
    }

    public String transformInToXML(Document document) throws GenericException {
        try {
            StringWriter stw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance()
                    .newTransformer();
            serializer
                    .transform(new DOMSource(document), new StreamResult(stw));
            return stw.toString();
        } catch (TransformerException e) {
            throw new GenericException(domain, "ERR-CON-0004",
                    "Issue in xml transformation", e);
        }
    }

    private HashMap checkAndPut(HashMap h, String k, Object v) {
        if (h.containsKey(k)) {
            ArrayList a = new ArrayList();
            if (h.get(k).getClass().getSimpleName().equals("ArrayList")) {
                a = (ArrayList) h.get(k);
            } else {
                a.add(h.get(k));
            }
            a.add(v);
            h.put(k, a);
        } else {
            h.put(k, v);
        }
        return h;
    }

    public ArrayList convertAnyListToArrayList(List l) {
        ArrayList a = new ArrayList();
        for (Object o : l) {
            if (o instanceof List) {
                ArrayList c = convertAnyListToArrayList((List) o);
                a.add(c);
            } else {
                a.add(o);
            }
        }
        return a;
    }

    public String getFileAsString(String fileName) {
        return getFileAsString(new File(fileName));
    }

    public String getFileAsString(File f) {
        FileInputStream is;
        String s = "";
        try {
            is = new FileInputStream(f);
            s = IOUtils.toString(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public String getAlternateNodeName(String key,
            HashMap<String, String> keyMap) {
        if (keyMap.containsKey(key)) {
            return keyMap.get(key);
        }
        return key;
    }

    public Boolean isHashMap(Object o) {
        return o.getClass().getSimpleName().equals("HashMap");
    }

    public Boolean isString(Object o) {
        return o.getClass().getSimpleName().equals("String");
    }

    public Boolean isArrayList(Object o) {
        return o.getClass().getSimpleName().equals("ArrayList");
    }

    public HashMap<String, String> getMappingHashMap(
            HashMap<String, Object> inputMapping, String mappingString) {
        if (inputMapping == null) return new HashMap();
        if (inputMapping.containsKey(mappingString))
            return (HashMap<String, String>) inputMapping.get(mappingString);
        return new HashMap();
    }

    public ArrayList<String> getMappingArrayList(
            HashMap<String, Object> inputMapping, String mappingString) {
        if (inputMapping == null) return new ArrayList();
        if (inputMapping.containsKey(mappingString))
            return (ArrayList<String>) inputMapping.get(mappingString);
        return new ArrayList();
    }

    public Boolean isCdataField(String fieldName, ArrayList<String> inputMapping) {
        if (inputMapping == null) return false;

        if (inputMapping.contains(fieldName)) return true;
        return false;
    }

    public boolean isEmpty(String value) {
        if (value != null) {
            value = value.trim();
            return value.isEmpty(); // true if length() is 0, otherwise false
        }
        return true;
    }

    public static void main(String[] args) {
        /*
         * System.out.println(ConvertorUtils.getInstanceOf().asDoc( new
         * File("/home/kavi/article1.xml")));
         */

        // System.out.println(ConvertorUtils.getInstance().getFileAsString("/home/ram/rss.xml"));

        /*
         * String x =
         * "<Article><Id>lievpGhieghha</Id><Entitytype>Article</Entitytype><NpiMetadata><NpiUrl><![CDATA[http://sample.com]]></NpiUrl><AltURI><![CDATA[http://sample.com]]></AltURI><MainTitle><![CDATA[today]]></MainTitle><AltTitle><![CDATA[today]]></AltTitle><NpiDescription><![CDATA[today]]></NpiDescription><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a><Classification><Sector>Agriculture</Sector></Classification><PublisherOrgNames><PublisherOrgName>Organisation of Animal Husbandry</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>Department of Animal Husbandry and Dairying</PublisherDepartment></PublisherDeptName><PublisherAddress>today</PublisherAddress><CreatorOrgNames><CreatorOrgName>Organisation of Agriculture and Co-operation</CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>Department of Agricultural Research and Education</CreatorDepartment></CreatorDeptName><CreatorAddress>today</CreatorAddress><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CoverageSpatial><Spatial>Anantapur</Spatial></CoverageSpatial><CoverageTemporal><Temporal>Acts</Temporal></CoverageTemporal><Audience><AudienceCategory>Contact Directory</AudienceCategory></Audience><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasPart></HasPart><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></NpiMetadata></Article>"
         * ; System.out.println(ConvertorUtils.getInstanceOf().asDoc(x));
         */

    }
}
