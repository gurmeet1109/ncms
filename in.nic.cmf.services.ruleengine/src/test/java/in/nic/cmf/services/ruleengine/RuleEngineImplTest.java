/*
 * 
 */
package in.nic.cmf.services.ruleengine;

import in.nic.cmf.domain.Article;
import in.nic.cmf.domain.Collections;
import in.nic.cmf.domain.GeoTags;
import in.nic.cmf.domain.Location;
import in.nic.cmf.domain.Storable;
import in.nic.cmf.domain.UserProfile;
import in.nic.cmf.domain.ValidationStatus;
import in.nic.cmf.services.ruleengine.duplication.RedisDuplicationProvider;
import in.nic.cmf.util.Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * The Class RuleEngineImplTest.
 * 
 * @author sunil.
 */
public class RuleEngineImplTest {

    /** The obj rei. */
    RuleEngineImpl objRei = RuleEngineImpl.getInstance();
Utils util = Utils.getInstance();
    @Test
    public void testvalidateforbusinessflowCollections() throws Exception {

        Collections collection = new Collections();
        Article article = new Article();
        article.setEntityType("Article");
        article.setId("1234567dfdfdfdfdfdfdfdfdfdfdfdfdfdfdf");
        // article.setTitle("fdfsdfd");
        article.setSeoUrl("www.kavitha.com");
        article.setSite("ig.in");
        GeoTags geotags = new GeoTags();
        List<Location> lstLoc = new ArrayList<Location>();
        Location location = new Location();
        location.setCity("chennai");
        lstLoc.add(location);
        geotags.setLocation(lstLoc);
        article.setGeoTags(geotags);
        article.setStatus("published");
        /*
         * MetadataInfo metadatainfo = new MetadataInfo();
         * metadatainfo.setIdentifier("345454545");
         * article.setMetadataInfo(metadatainfo);
         */

        collection.add(article);
        Collections coll1 = new Collections();


        coll1 = objRei.validate(getDataForMetadata());


        try {
        	System.out.println("================"+util.getCollectionsAsStringXml(coll1));
        } catch (Exception e) {
            System.out.println("testing validate  :" + e);
        }
    }

    @Test
    public void testvalidateCollections() throws Exception {

        Collections collection = new Collections();
        Article article = new Article();
        article.setEntityType("Article");
        article.setId("1234567dfdfdfdfdfdfdfdfdfdfdfdfdfdfdf");
        // article.setTitle("fdfsdfd");
        article.setSeoUrl("www.kavitha.com");
        article.setSite("ig.in");
        GeoTags geotags = new GeoTags();
        List<Location> lstLoc = new ArrayList<Location>();
        Location location = new Location();
        location.setCity("chennai");
        lstLoc.add(location);
        geotags.setLocation(lstLoc);
        article.setGeoTags(geotags);
        collection.add(article);
        try {
           Map mm = objRei.validate(getDataForMetadata(), getContentFromResource(),
                    new ArrayList());
           System.out.println(mm);
        } catch (Exception e) {
            System.out.println("testing validate  :" + e);
        }
    }
    public Collections getDataForMetadata() {
        String xmlString = "<Collections><Article> <Id>lltlUGfbdfeef</Id><EntityType>Article</EntityType> <AliasId></AliasId>  <Title>sdfsdfdsfdsfdsf</Title>"
                + " <Description>dsfdsfdsfdsfdsf</Description><ArticleType>Newsletter</ArticleType><AssociatedIAPath>/dsfdsfsdfds</AssociatedIAPath>"
                + " <Md5>babef6f6a038b4f0012b66e86d6c5ef7</Md5><Status>Published</Status><GeoTags> <Location> <City></City> <Country></Country> <Lat></Lat>"
                + " <Lng></Lng><LocationId></LocationId><State></State><Ip></Ip><District></District></Location></GeoTags>"
                + "<CreatedDate>2011-11-19T11:56:33Z</CreatedDate><LastModifiedDate>2011-11-19T11:56:33Z</LastModifiedDate>"
                + "<CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version>"
                + "<SeoUrl>http://sify.co.in/dsfdsfsdfds/sdfsdfdsfdsfdsf-article-lltlugfbdfeef.php</SeoUrl><WorkflowInfo><CurrentUser></CurrentUser>"
                + "<CurrentEvent></CurrentEvent><AssignedTo><User></User></AssignedTo><Stage></Stage><WorkflowComments></WorkflowComments>"
                + "</WorkflowInfo><MetadataInfo><Identifier></Identifier><IdentifierAlternate></IdentifierAlternate><TitleMain></TitleMain>"
                + "<TitleAlternate></TitleAlternate><MetadataDescription></MetadataDescription><SubjectKeywords><SubjectKeyword></SubjectKeyword>"
                + "</SubjectKeywords><SubjectClassification><Sector></Sector></SubjectClassification><PublisherOrgNames><PublisherOrgName></PublisherOrgName></PublisherOrgNames><PublisherDeptName>"
                + "<PublisherDepartment></PublisherDepartment></PublisherDeptName><PublisherAddress></PublisherAddress>"
                + "<CreatorOrgNames><CreatorOrgName></CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment></CreatorDepartment>"
                + "</CreatorDeptName> <CreatorAddress></CreatorAddress><Format></Format><LanguageId></LanguageId><Language></Language>"
                + "<MetaDataCreatedDate></MetaDataCreatedDate><PublishedDate></PublishedDate><ValidDate></ValidDate> <CoverageJurisdiction> "
                + "<Jurisdiction>  <JurisdictionName></JurisdictionName>  <JurisdictionStateId></JurisdictionStateId>   <JurisdictionStateName>"
                + "</JurisdictionStateName>  <JurisdictionDistrictId></JurisdictionDistrictId>     <JurisdictionDistrictName></JurisdictionDistrictName>"
                + " </Jurisdiction>  </CoverageJurisdiction>     <CoverageSpatial>    <Spatial>         <SpatialStateId></SpatialStateId>"
                + " <SpatialStateName></SpatialStateName>   <SpatialDistrictId></SpatialDistrictId> <SpatialDistrictName></SpatialDistrictName>"
                + "  </Spatial>         </CoverageSpatial>     <CoverageTemporal></CoverageTemporal>      <ConformsTo></ConformsTo><Audience><AudienceCategory></AudienceCategory></Audience>"
                + "<CategoryTypes><CategoryType></CategoryType></CategoryTypes>     <HasParts>"
                + " <HasPart></HasPart>         </HasParts>         <IsPartOf></IsPartOf>        <HasVersion></HasVersion>  <IsVersionOf></IsVersionOf>"
                + "           <Source></Source>        </MetadataInfo>        <DependentItems/>        <RelatedItems/>    </Article></Collections>";

        Collections<Storable> collections = (Collections<Storable>) util
                .getStringXmlAsCollections(xmlString);
        return collections;
    }

    public Collections getData() {
        String xmlString = "<Collections><Article> <Id>lltlUGfbdfeef</Id><EntityType>Article</EntityType> <AliasId></AliasId>  <Title>sdfsdfdsfdsfdsf</Title>"
                + " <Description>dsfdsfdsfdsfdsf</Description><ArticleType>Newsletter</ArticleType><AssociatedIAPath>/dsfdsfsdfds</AssociatedIAPath>"
                + " <Md5>babef6f6a038b4f0012b66e86d6c5ef7</Md5><Status>Draft</Status><GeoTags> <Location> <City></City> <Country></Country> <Lat></Lat>"
                + " <Lng></Lng><LocationId></LocationId><State></State><Ip></Ip><District></District></Location></GeoTags>"
                + "<CreatedDate>2011-11-19T11:56:33Z</CreatedDate><LastModifiedDate>2011-11-19T11:56:33Z</LastModifiedDate>"
                + "<CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version>"
                + "<SeoUrl>http://sify.co.in/dsfdsfsdfds/sdfsdfdsfdsfdsf-article-lltlugfbdfeef.php</SeoUrl><WorkflowInfo><CurrentUser></CurrentUser>"
                + "<CurrentEvent></CurrentEvent><AssignedTo><User></User></AssignedTo><Stage></Stage><WorkflowComments></WorkflowComments>"
                + "</WorkflowInfo><MetadataInfo><Identifier></Identifier><IdentifierAlternate></IdentifierAlternate><TitleMain></TitleMain>"
                + "<TitleAlternate></TitleAlternate><MetadataDescription></MetadataDescription><SubjectKeywords><SubjectKeyword></SubjectKeyword>"
                + "</SubjectKeywords><SubjectClassification><Sector></Sector></SubjectClassification><PublisherOrgNames><PublisherOrgName></PublisherOrgName></PublisherOrgNames><PublisherDeptName>"
                + "<PublisherDepartment></PublisherDepartment></PublisherDeptName><PublisherAddress></PublisherAddress>"
                + "<CreatorOrgNames><CreatorOrgName></CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment></CreatorDepartment>"
                + "</CreatorDeptName> <CreatorAddress></CreatorAddress><Format></Format><LanguageId></LanguageId><Language></Language>"
                + "<MetaDataCreatedDate></MetaDataCreatedDate><PublishedDate></PublishedDate><ValidDate></ValidDate> <CoverageJurisdiction> "
                + "<Jurisdiction>  <JurisdictionName></JurisdictionName>  <JurisdictionStateId></JurisdictionStateId>   <JurisdictionStateName>"
                + "</JurisdictionStateName>  <JurisdictionDistrictId></JurisdictionDistrictId>     <JurisdictionDistrictName></JurisdictionDistrictName>"
                + " </Jurisdiction>  </CoverageJurisdiction>     <CoverageSpatial>    <Spatial>         <SpatialStateId></SpatialStateId>"
                + " <SpatialStateName></SpatialStateName>   <SpatialDistrictId></SpatialDistrictId> <SpatialDistrictName></SpatialDistrictName>"
                + "  </Spatial>         </CoverageSpatial>     <CoverageTemporal></CoverageTemporal>      <ConformsTo></ConformsTo><Audience><AudienceCategory></AudienceCategory></Audience>"
                + "<CategoryTypes><CategoryType></CategoryType></CategoryTypes>     <HasParts>"
                + " <HasPart></HasPart>         </HasParts>         <IsPartOf></IsPartOf>        <HasVersion></HasVersion>  <IsVersionOf></IsVersionOf>"
                + "           <Source></Source>        </MetadataInfo>        <DependentItems/>        <RelatedItems/>    </Article></Collections>";

        Collections<Storable> collections = (Collections<Storable>) util
                .getStringXmlAsCollections(xmlString);
        return collections;
    }

    @Test
    public void deletekeyfromredis() {
        // JedisHelper hh = JedisHelper.getInstance();

        /*
         * String existed = hh.getValue("kavitha.com_UserProfile_UserName_k4");
         * String existed1 =
         * hh.getValue("kavitha.com_UserProfile_Email_k4@gmail.com"); String
         * existed2 = hh.getValue("kavitha.com_UserProfile_0000000000004");
         * System.out.println(existed+"::"+existed1+"::"+existed2);
         */
        // 1234565432099::1234565432099::kavitha.com_UserProfile_UserName_k;kavitha.com_UserProfile_Email_k@gmail.com;
        // String existed =
        // hh.getValue("kavitha.com_UserProfile_lkwtqUgdhafii");
        // kavitha.com_UserProfile_UserName_kav;kavitha.com_UserProfile_Email_iir@gmail.com;
        // objRei.deleteDuplicate("kavitha.com", "UserProfile",
        // "0000000000004");
        // String existed1 =
        // hh.getValue("kavitha.com_UserProfile_lkwtqUgdhafii");
    }

    @Test
    public void flush() {
        RedisDuplicationProvider red = RedisDuplicationProvider.getInstance();
        red.deleteDuplicate("kavitha.com_UserProfile_0000000000100");
        // JedisHelper hh = JedisHelper.getInstance();
        // hh.flushall();
    }

    @Test
    public void gene() {

        UserProfile up = new UserProfile();
        up.setAgeCategory("26");
        up.setId("0000000000558");
        up.setCity("Chennai");
        up.setEmail("l55k@gmail.com");
        up.setEntityType("UserProfile");
        up.setFirstName("kavitha");
        up.setGender("Female");
        up.setSite("kavitha.com");
        up.setUserName("l55k");

        /*
         * UserProfile up2 = new UserProfile(); up2.setAgeCategory("26");
         * up2.setId("0000000000006"); up2.setCity("Chennai");
         * up2.setEmail("kd@gmail.com"); up2.setEntityType("UserProfile");
         * up2.setFirstName("kavitha"); up2.setGender("Female");
         * up2.setSite("kavitha.com"); up2.setUserName("k4");
         */

        Collections collections = new Collections();
        collections.add(up);
        // collections.add(up2);

        RuleEngineImpl impl = RuleEngineImpl.getInstance();
        Utils util = Utils.getInstance();
        System.out.println("validating..........");
        List<Map> lstMap = new ArrayList<Map>();
        Map map = impl.validate(collections, getContentFromResource(), lstMap);
        // System.out.println(util.getCollectionsAsStringXml(map));
        // tmpDupDetails = (List<Map>) ruleEngineResult.get("tmpdetails");
        lstMap = (List<Map>) map.get("tmpdetails");
        Collections cool = (Collections) map.get("errorcollections");
        boolean isError = false;
        Iterator<?> error = cool.getCollection().iterator();
        while (error.hasNext()) {
            ValidationStatus v = (ValidationStatus) error.next();
            if (v.getStatus().equals("Error")) {
                // result = false;
                // return cool;
                isError = true;
            }
        }
        if (!isError) {

            impl.generateForDuplication(lstMap);

        }

        /*
         * impl.deleteDuplicate("kavitha.in", "UserProfile", "1234565432002");
         * 
         * 
         * UserProfile up1 = new UserProfile(); up1.setAgeCategory("26");
         * up1.setId("1234565432002"); up1.setCity("Chennai");
         * up1.setEmail("k@gmail.com"); up1.setEntityType("UserProfile");
         * up1.setFirstName("kavitha"); up1.setGender("Female");
         * up1.setSite("kavitha.in"); up1.setUserName("k"); Collections
         * collections1 = new Collections(); collections1.add(up1); Collections
         * cool1 = impl.validate(collections1,getContentFromResource());
         * 
         * System.out.println(util.getCollectionsAsStringXml(cool1));
         */
        // System.out.println("generating..................");
        // impl.generateForDuplication("kavitha.in", collections,
        // getContentFromResource()) ;
    }

    @Test
    public void generateForMultipleDuplication() {
        Utils util = Utils.getInstance();
        RuleEngineImpl impl = RuleEngineImpl.getInstance();
        Collections collections = new Collections();
        UserProfile up = new UserProfile();
        up.setAgeCategory("26");
        // up.setBirthDate("06011985");
        up.setId("1234565432123");
        up.setCity("Chennai");
        up.setEmail("kavitha@gmail.com");
        up.setEntityType("UserProfile");
        up.setFirstName("kavitha");
        up.setGender("Female");
        up.setSite("kavitha.in");
        up.setUserName("kavitha");
        collections.add(up);
        System.out.println("----------1----------only validating------------");
        System.out.println(util.getCollectionsAsStringXml(impl
                .validate(collections)));
        System.out
                .println("----------1----------after generating--------------");
        // impl.generateForDuplication("kavitha.in", collections,
        // getContentFromResource());

        Collections collections2 = new Collections();
        UserProfile up1 = new UserProfile();
        up1.setId("1234565432123");
        up1.setAgeCategory("26");
        // up1.setBirthDate("06011985");
        up1.setCity("Chennai");
        up1.setEmail("priya@gmail.com");
        up1.setEntityType("UserProfile");
        up1.setFirstName("kavitha");
        up1.setGender("Female");
        up1.setSite("kavitha.in");
        up1.setUserName("kavitha");
        collections2.add(up1);

        System.out.println("--------2------------only validating------------");
        System.out.println(util.getCollectionsAsStringXml(impl
                .validate(collections2)));
        System.out
                .println("--------2------------after generating--------------");
        // impl.generateForDuplication("kavitha.in", collections2,
        // getContentFromResource()) ;

        Collections collections3 = new Collections();
        UserProfile up3 = new UserProfile();
        up3.setId("12345654321232");
        up3.setAgeCategory("26");
        // up3.setBirthDate("06011985");
        up3.setCity("Chennai");
        up3.setEmail("kavitha@gmail.com");
        up3.setEntityType("UserProfile");
        up3.setFirstName("kavitha");
        up3.setGender("Female");
        up3.setSite("kavitha.in");
        up3.setUserName("kavitha");
        collections3.add(up3);

        System.out.println("--------3------------only validating------------");
        System.out.println(util.getCollectionsAsStringXml(impl
                .validate(collections3)));
        System.out
                .println("--------3------------after generating--------------");
        // impl.generateForDuplication("kavitha.in", collections3,
        // getContentFromResource()) ;

        Collections collections4 = new Collections();
        UserProfile up4 = new UserProfile();
        up4.setId("12345654321232");
        up4.setAgeCategory("26");
        // up3.setBirthDate("06011985");
        up4.setCity("Chennai");
        up4.setEmail("kavitha@gmail.com");
        up4.setEntityType("UserProfile");
        up4.setFirstName("kavitha");
        up4.setGender("Female");
        up4.setSite("kavitha.in");
        up4.setUserName("kavitha");
        collections4.add(up4);

        System.out.println("--------4------------only validating------------");
        System.out.println(util.getCollectionsAsStringXml(impl
                .validate(collections4)));
        System.out
                .println("--------4------------after generating--------------");
        // impl.generateForDuplication("kavitha.in", collections4,
        // getContentFromResource()) ;

    }

    @Test
    public void testPostReturnValidationStatus() throws Exception {
        Collections collection = new Collections();

        /*
         * Address add = new Address(); add.setAddress("1234567654321");
         * add.setAddressName("kavitha"); add.setDistrictId("lh3skkchdfahe");
         * add.setSite("ig.in"); add.setEntityType("Address");
         * collection.add(add);
         */

        Article article = new Article();
        article.setEntityType("Article");
        article.setId("1234567898765");
        article.setTitle("fdfsdfd");
        article.setSeoUrl("www.kavitha.com");
        article.setSite("ig.in");
        GeoTags geotags = new GeoTags();
        List<Location> lstLoc = new ArrayList<Location>();
        Location location = new Location();
        location.setCity("chennai");
        lstLoc.add(location);
        geotags.setLocation(lstLoc);
        article.setGeoTags(geotags);
        collection.add(article);

        System.out.println("start-");
        RuleEngineImpl impl = RuleEngineImpl.getInstance();
        Utils util = Utils.getInstance();
        try {
            Collections<ValidationStatus> collections = impl
                    .validate(collection);
            System.out.println("before for");
            System.out.println("dont show duplicated error.");
            System.out.println(util.getCollectionsAsStringXml(collections));
            System.out.println("----end");
        } catch (Exception ex) {
            System.out.println("--------" + ex.getMessage());
        }

        System.out.println("2 iteration be ready");
        RuleGenerateImpl ruleGen = new RuleGenerateImpl();
        List<String> ls = new ArrayList<String>();
        ls.add(giveRule());
        ruleGen.addRules(ls);
        System.out.println("after adding to knowledgebase");

        // should show rule
        Collections collection1 = new Collections();
        Article article1 = new Article();
        article1.setEntityType("Article");
        article1.setId("1234567898734");
        // article.setTitle("dsfdsf");
        article1.setTitle("fine");
        article1.setSite("ig.in");
        article1.setSeoUrl("www.kavitha.com");
        GeoTags geotags1 = new GeoTags();
        List<Location> lstLoc1 = new ArrayList<Location>();
        Location location1 = new Location();
        location1.setCity("chennai");
        lstLoc1.add(location1);
        geotags1.setLocation(lstLoc1);
        article1.setGeoTags(geotags1);
        collection1.add(article1);
        System.out.println("before calling posstretrunvaldiationstatus");
        Collections<ValidationStatus> collections2 = impl.validate(collection1);
        System.out
                .println("after postreturnvalidationcollectiosn- should show duplicated error");
        System.out.println(util.getCollectionsAsStringXml(collections2));
    }

    public String giveRule() {
        String rule = "";

        rule = "package in.nic.cmf.services.ruleengine"
                + "\n"
                + "import java.util.HashMap;"
                + "\n"
                + "import java.util.List;"
                + "\n"
                + "import java.util.ArrayList;"
                + "\n"
                + "import in.nic.cmf.domain.ValidationReport;"
                + "\n"
                + "global in.nic.cmf.domain.ValidationReport validategreat;"
                + "\n"
                +

                "function ValidationReport addToList(String strContent, ValidationReport report)  "
                + "\n"
                + " {    "
                + "\n"
                + "	List<String> lstMessage = new ArrayList<String>();"
                + "\n"
                + " 	if(report.getMessage()!=null)"
                + "\n"
                + "{  "
                + "\n"
                + "	lstMessage = report.getMessage();  "
                + "\n"
                + "	lstMessage.add(strContent); "
                + "\n"
                + "	report.setMessage(lstMessage);"
                + "\n"
                + " } "
                + "\n"
                + "else "
                + "\n"
                + "{ "
                + "\n"
                + "	lstMessage.add(strContent);"
                + "\n"
                + "	report.setMessage(lstMessage);"
                + "\n"
                + "} "
                + "\n"
                + "return report;  "
                + "\n"
                + "}"
                + "\n"
                + "rule 'ig.in_Article_Title_fine'"
                + "\n"
                + "when"
                + "\n"
                + "map:HashMap(Site=='ig.in',EntityType=='Article',Title=='fine');"
                + "\n"
                + "then "
                + "\n"
                + "System.out.println('i am inside rule Article_Title_fine');"
                + "\n"
                + "System.out.println('welcoemwelcomewelcoem');"
                + "\n"
                + "validategreat = addToList('Title:Title duplication occured.',validategreat);"
                + "\n" + "end;";

        System.out.println(rule);
        return rule;
    }

    /**
     * Test post return validation status.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testPostReturnValidationStatus1() throws Exception {
        Collections collection = new Collections();
        Article article = new Article();
        article.setEntityType("Article");
        article.setId("12");
        article.setTitle("welcoeto sifywelcome");

        GeoTags geotags = new GeoTags();
        List<Location> lstLoc = new ArrayList<Location>();
        Location location = new Location();
        location.setCity("chennai");
        lstLoc.add(location);
        geotags.setLocation(lstLoc);
        article.setGeoTags(geotags);

        collection.add(article);

        RuleEngineImpl impl = RuleEngineImpl.getInstance();
        try {
            Collections<ValidationStatus> collections = impl
                    .validate(collection);

            for (ValidationStatus ss : collections.getCollection()) {
                System.out.println(ss.getErrorFields().getFieldDetails().get(1)
                        .getFieldError());
            }
        } catch (Exception ex) {
            System.out.println("--------" + ex.getMessage());
        }
    }

    /**
     * Testpost.
     * 
     * @throws Exception
     *             the exception
 
    @Test
    public void testpost() throws Exception {

        try {
            objRei.post(null);
        } catch (Exception e) {
            System.out.println("testing post  :" + e);
        }

    }

    @Test
    public void testpostExc() throws Exception {

        Collections collection = new Collections();
        Article article = null;
        collection.add(article);
        try {
            objRei.post(collection);
        } catch (Exception e) {
            System.out.println("testing post  :" + e);
        }

    }

    @Test
    public void testpostCollections() throws Exception {

        try {
            objRei.postCollections(null);
        } catch (Exception e) {
            System.out.println("testing postCollections  :" + e);
        }

    }

    @Test
    public void testpostReturnsList() throws Exception {

        try {
            objRei.postReturnsList(null);
        } catch (Exception e) {
            System.out.println("testing postReturnCollections  :" + e);
        }
    }    */

    /**
     * Testpost returns validation status.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testpostReturnsValidationStatus() throws Exception {

        Collections collection = new Collections();
        Article article = new Article();
        collection.add(article);
        try {
            objRei.validate(collection);
        } catch (Exception e) {
            System.out.println("testing validate  :" + e);
        }

    }

    /**
     * Testpost returns validation status exc.
     * 
     * @throws Exception
     *             the exception
     */
    @Test
    public void testpostReturnsValidationStatusExc() throws Exception {

        Collections collection = new Collections();
        Article article = null;
        collection.add(article);
        try {
            objRei.validate(collection);
        } catch (Exception e) {
            System.out.println("testing validate  :" + e);
        }
    }

    public String getContentFromResource() {
        String jsonTxt = "";
        try {
            // URL domainURI =
            // getClass().getClassLoader().getResource("/home/ram/svnon5thoct/trunk/in.nic.cmf.services.dataservices/src/main/resources/unique.json");
            // String jsonPath = domainURI.getFile();
            InputStream iStream;
            String jsonPath = "/home/ram/svnon21stoct/trunk/in.nic.cmf.services.dataservices/src/main/resources/unique.json";
            iStream = new FileInputStream(jsonPath);
            jsonTxt = IOUtils.toString(iStream);
        } catch (Exception ex) {
            // log.debug("In exception."+ex.getMessage());
        }
        return jsonTxt;
    }

}
