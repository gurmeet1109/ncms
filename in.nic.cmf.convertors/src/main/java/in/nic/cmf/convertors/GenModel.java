package in.nic.cmf.convertors;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.util.HashMap;
import java.util.Map;

public class GenModel {

    protected HashMap<Object, Object> aData = new HashMap<Object, Object>();
    // private static LogTracer log = new LogTracer("genmodel", true,
    // true, true, true, false);
    private LogTracer                 log;
    private String                    domain;

    private GenModel(String domain) {
        this.domain = domain;

    }

    private void setLogger() {
        log = new LogTracer(domain, "formatjson", true, true, true, true, false);
    }

    public void add(String k, Object v) {
        aData.put(k, v);
    }

    public Object get(String k) {
        return aData.get(k);
    }

    public void set(String k, Object v) {
        aData.put(k, v);
    }

    public String toString() {
        return aData.toString();
    }

    public HashMap asHashMap(HashMap<Object, Object> xmlmap)
            throws GenericException {
        log.methodEntry(this.getClass().getSimpleName()
                + ". asHashMap(HashMap<Object, Object> xmlmap)");
        try {
            HashMap<String, Object> aResult = new HashMap<String, Object>();
            for (Map.Entry<Object, Object> e : xmlmap.entrySet()) {
                if (e.getValue() != null) {
                    if (e.getValue().getClass().getSimpleName()
                            .equals("HashMap")) {
                        aResult.put((String) e.getKey(),
                                asHashMap((HashMap<Object, Object>) e
                                        .getValue()));
                    } else if (e.getValue().getClass().getSimpleName()
                            .equals("GenModel")) {
                        GenModel g = (GenModel) e.getValue();
                        aResult.put((String) e.getKey(), g.asHashMap());
                    } else {
                        aResult.put((String) e.getKey(), e.getValue());
                    }
                } else {
                    aResult.put((String) e.getKey(), e.getValue());
                }
            }
            return aResult;
        } catch (GenericException ge) {
            throw ge;
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000", getClass()
                    .getSimpleName(), e);
        } finally {
            log.methodExit(this.getClass().getSimpleName()
                    + ": asHashMap(HashMap<Object, Object> xmlmap)");
        }
    }

    public HashMap asHashMap() {
        return asHashMap(aData);
    }

    public static void main(String[] args) {
        String x = "<Article><Id>lievpGhieghha</Id><Entitytype>Article</Entitytype><NpiMetadata><NpiUrl><![CDATA[http://sample.com]]></NpiUrl><AltURI><![CDATA[http://sample.com]]></AltURI><MainTitle><![CDATA[today]]></MainTitle><AltTitle><![CDATA[today]]></AltTitle><NpiDescription><![CDATA[today]]></NpiDescription><Keywords><Keyword>y'day</Keyword><Keyword>today</Keyword><Keyword>tom</Keyword></Keywords><a><b><c><d><e><f>aasdf</f></e><e><f>aasdf2</f></e><e><f>aasdf3</f></e></d></c></b></a><Classification><Sector>Agriculture</Sector></Classification><PublisherOrgNames><PublisherOrgName>Organisation of Animal Husbandry</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment>Department of Animal Husbandry and Dairying</PublisherDepartment></PublisherDeptName><PublisherAddress>today</PublisherAddress><CreatorOrgNames><CreatorOrgName>Organisation of Agriculture and Co-operation</CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>Department of Agricultural Research and Education</CreatorDepartment></CreatorDeptName><CreatorAddress>today</CreatorAddress><Format></Format><LanguageType>en</LanguageType><CreatedDate>2011-08-04T21:15:04Z</CreatedDate><ContentCreatedDate>2011-08-04T21:14:19Z</ContentCreatedDate><PublishedDate>2011-08-04T21:15:09Z</PublishedDate><ValidDate>2011-08-04T21:15:12Z</ValidDate><ModifiedDate>2011-08-04T21:14:19Z</ModifiedDate><Jurisdictions><Jurisdiction>Central</Jurisdiction></Jurisdictions><CoverageSpatial><Spatial>Anantapur</Spatial></CoverageSpatial><CoverageTemporal><Temporal>Acts</Temporal></CoverageTemporal><Audience><AudienceCategory>Contact Directory</AudienceCategory></Audience><CategoryTypes><CategoryType>TempCategory</CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasPart></HasPart><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><CreatedBy>kalpana</CreatedBy><LastModifiedBy>kalpana</LastModifiedBy><Source><![CDATA[DDNews]]></Source></NpiMetadata></Article>";
        String domain = "nic.in";
        HashMap h = FormatXml.getInstance(domain).in(x);
        // /System.out.println("hashmap:::" + h);
        GenModel g = FormatXml.getInstance(domain).in(x, new GenModel(domain));
        System.out.println("hashmap:::" + g.asHashMap());
    }

    public static GenModel newInstance(String domain, HashMap<String, Object> h)
            throws GenericException {
        try {
            GenModel g = new GenModel(domain);
            for (Map.Entry<String, Object> e : h.entrySet()) {
                if (e.getValue() != null) {
                    if (e.getValue().getClass().getSimpleName()
                            .equals("HashMap")) {
                        g.set((String) e.getKey(),
                                GenModel.newInstance(domain,
                                        (HashMap) e.getValue()));
                    } else if (e.getValue().getClass().getSimpleName()
                            .equals("GenModel")) {
                        g.set((String) e.getKey(), (GenModel) e.getValue());
                    } else if (e.getValue().getClass().getSimpleName()
                            .equals("String")) {
                        g.set((String) e.getKey(), e.getValue());
                    }
                } else {
                    g.set((String) e.getKey(), e.getValue());
                }
            }
            return g;
        } catch (GenericException ge) {
            throw new GenericException(domain, "ERR-CON-0000", ge);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CON-0000",
                    "GenModel.newInstance()", e);
        }
    }

    public static GenModel newInstance(String domain) throws GenericException {
        return new GenModel(domain);
    }
}
