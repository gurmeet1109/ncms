package in.nic.cmf.transformers;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.transformer.TransformerImpl;
import in.nic.cmf.transformer.helper.ContentCreatorHelper;
import in.nic.cmf.transformer.providers.Provider;
import in.nic.cmf.transformer.providers.ProviderFactory;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TransformerImplTest {

    Provider            provider;
    TransformerImpl     impl   = new TransformerImpl();
    static final String DOMAIN = "sify.co.in";
    static final String ENTITY = "Article";
    static final String ID     = "abckdfijhiwet";

    @Before
    public void setUp() throws Exception {
        provider = ProviderFactory.getProvider(DOMAIN);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAdd() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "kavitha");
        String xmlContent = "<Collections><Workflow><Id></Id><EntityType>Workflow</EntityType><WorkflowName><![CDATA[Fun_workflow]]>"
                + "</WorkflowName><WorkflowType>Manual</WorkflowType><WorkflowStages><WorkflowStage><StageName><![CDATA[Draft]]></StageName>"
                + "<StageType>Start</StageType><PossibleActions><PossibleAction><PossibleActionName>Forward</PossibleActionName><PossibleActionUsers>"
                + "<PossibleActionUser>eclipseadmin</PossibleActionUser></PossibleActionUsers><NextStage>Forwarded</NextStage><NextContentStatus>Draft"
                + "</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName><![CDATA[Forwarded]]></StageName>"
                + "<StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Publish</PossibleActionName><PossibleActionUsers>"
                + "<PossibleActionUser>eclipseadmin</PossibleActionUser></PossibleActionUsers><NextStage>Published</NextStage><NextContentStatus>Published"
                + "</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName><![CDATA[Published]]></StageName>"
                + "<StageType>End</StageType><PossibleActions><PossibleAction><PossibleActionName></PossibleActionName><PossibleActionUsers><PossibleActionUser>"
                + "</PossibleActionUser></PossibleActionUsers><NextStage></NextStage><NextContentStatus></NextContentStatus></PossibleAction></PossibleActions>"
                + "</WorkflowStage></WorkflowStages><CreatedDate>2012-04-12T15:34:00Z"
                + "</CreatedDate><CreatedBy>eclipseadmin</CreatedBy><LastModifiedBy>eclipseadmin</LastModifiedBy><LastModifiedDate>2012-04-12T15:34:00Z</LastModifiedDate>"
                + "<Site></Site><Version></Version></Workflow></Collections>";
        // String xmlContent =
        // "<Collections><Article><Id></Id><EntityType>Article</EntityType><AliasId/><Title>Article Title</Title><Description>desc</Description><ArticleType>MyTest</ArticleType><AssociatedIAPath>/Sify News</AssociatedIAPath><Md5>8747a2af1b3239bae55404afd6723054</Md5><GeoTags><Location><City/><Country></Country><Lat/><Lng/><LocationId/><State/><Ip></Ip><Coords/><District/></Location></GeoTags><SeoUrl></SeoUrl><WorkflowInfo><WorkflowId>mbykytbeabijj</WorkflowId><WorkflowName>dontdeletethis</WorkflowName><CurrentUser>creatoruser</CurrentUser><CurrentAction/><Stage>Draft</Stage><AllowedActions><AllowedAction><AllowedActionName>Forward</AllowedActionName><AssignedToUsers><AssignedToUser>sifycoadmin</AssignedToUser></AssignedToUsers></AllowedAction><AllowedAction><AllowedActionName>Reject</AllowedActionName><AssignedToUsers><AssignedToUser>sifycoadmin</AssignedToUser></AssignedToUsers></AllowedAction></AllowedActions><WorkflowComments></WorkflowComments></WorkflowInfo><Status>Draft</Status><CreatedDate>2012-02-25T17:30:42Z</CreatedDate><LastModifiedDate>2012-02-28T12:38:38Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version><Weightage/><MetadataInfo><Identifier></Identifier><IdentifierAlternate></IdentifierAlternate><TitleMain></TitleMain><TitleAlternate></TitleAlternate><MetadataDescription></MetadataDescription><SubjectKeywords><SubjectKeyword/></SubjectKeywords><SubjectClassification><Sector/></SubjectClassification><PublisherOrgNames><PublisherOrgName/></PublisherOrgNames><PublisherDeptName><PublisherDepartment/></PublisherDeptName><PublisherAddress></PublisherAddress><CreatorOrgNames><CreatorOrgName/></CreatorOrgNames><CreatorDeptName><CreatorDepartment/></CreatorDeptName><CreatorAddress></CreatorAddress><Format/><LanguageId/><Language/><MetaDataCreatedDate/><PublishedDate/><ValidDate/><CoverageJurisdiction><Jurisdiction><JurisdictionName/><JurisdictionStateId/><JurisdictionStateName/><JurisdictionDistrictId/><JurisdictionDistrictName/></Jurisdiction></CoverageJurisdiction><CoverageSpatial><Spatial><SpatialStateId/><SpatialStateName/><SpatialDistrictId/><SpatialDistrictName/></Spatial></CoverageSpatial><CoverageTemporal></CoverageTemporal><Audience><AudienceCategory/></Audience><CategoryTypes><CategoryType/></CategoryTypes><ConformsTo></ConformsTo><HasParts><HasPart></HasPart></HasParts><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><Source></Source></MetadataInfo><DependentItems/><RelatedItems/></Article><Article><Id>mczr4Giecc456</Id><EntityType>Article</EntityType><AliasId/><Title>Article Title</Title><Description>desc</Description><ArticleType>MyTest</ArticleType><AssociatedIAPath>/Sify News</AssociatedIAPath><Md5>8747a2af1b3239bae55404afd6723054</Md5><GeoTags><Location><City/><Country></Country><Lat/><Lng/><LocationId/><State/><Ip></Ip><Coords/><District/></Location></GeoTags><SeoUrl>http://sify.co.in/sify_news/article-title-article-mczr4gieccedf.php</SeoUrl><WorkflowInfo><WorkflowId>mbykytbeabijj</WorkflowId><WorkflowName>dontdeletethis</WorkflowName><CurrentUser>creatoruser</CurrentUser><CurrentAction/><Stage>Draft</Stage><AllowedActions><AllowedAction><AllowedActionName>Forward</AllowedActionName><AssignedToUsers><AssignedToUser>sifycoadmin</AssignedToUser></AssignedToUsers></AllowedAction><AllowedAction><AllowedActionName>Reject</AllowedActionName><AssignedToUsers><AssignedToUser>sifycoadmin</AssignedToUser></AssignedToUsers></AllowedAction></AllowedActions><WorkflowComments></WorkflowComments></WorkflowInfo><Status>Draft</Status><CreatedDate>2012-02-25T17:30:42Z</CreatedDate><LastModifiedDate>2012-02-28T12:38:38Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version><Weightage/><MetadataInfo><Identifier></Identifier><IdentifierAlternate></IdentifierAlternate><TitleMain></TitleMain><TitleAlternate></TitleAlternate><MetadataDescription></MetadataDescription><SubjectKeywords><SubjectKeyword/></SubjectKeywords><SubjectClassification><Sector/></SubjectClassification><PublisherOrgNames><PublisherOrgName/></PublisherOrgNames><PublisherDeptName><PublisherDepartment/></PublisherDeptName><PublisherAddress></PublisherAddress><CreatorOrgNames><CreatorOrgName/></CreatorOrgNames><CreatorDeptName><CreatorDepartment/></CreatorDeptName><CreatorAddress></CreatorAddress><Format/><LanguageId/><Language/><MetaDataCreatedDate/><PublishedDate/><ValidDate/><CoverageJurisdiction><Jurisdiction><JurisdictionName/><JurisdictionStateId/><JurisdictionStateName/><JurisdictionDistrictId/><JurisdictionDistrictName/></Jurisdiction></CoverageJurisdiction><CoverageSpatial><Spatial><SpatialStateId/><SpatialStateName/><SpatialDistrictId/><SpatialDistrictName/></Spatial></CoverageSpatial><CoverageTemporal></CoverageTemporal><Audience><AudienceCategory/></Audience><CategoryTypes><CategoryType/></CategoryTypes><ConformsTo></ConformsTo><HasParts><HasPart></HasPart></HasParts><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><Source></Source></MetadataInfo><DependentItems/><RelatedItems/></Article></Collections>";
        // String xmlContent =
        // "<Collections><Article><Id></Id><EntityType>Article</EntityType><AliasId/><Title>Article Title</Title><Description>desc</Description><ArticleType>MyTest</ArticleType><AssociatedIAPath>/Sify News</AssociatedIAPath><Md5>8747a2af1b3239bae55404afd6723054</Md5><GeoTags><Location><City/><Country></Country><Lat/><Lng/><LocationId/><State/><Ip></Ip><Coords/><District/></Location></GeoTags><SeoUrl></SeoUrl><WorkflowInfo><WorkflowId>mbykytbeabijj</WorkflowId><WorkflowName>dontdeletethis</WorkflowName><CurrentUser>creatoruser</CurrentUser><CurrentAction/><Stage>Draft</Stage><AllowedActions><AllowedAction><AllowedActionName>Forward</AllowedActionName><AssignedToUsers><AssignedToUser>sifycoadmin</AssignedToUser></AssignedToUsers></AllowedAction><AllowedAction><AllowedActionName>Reject</AllowedActionName><AssignedToUsers><AssignedToUser>sifycoadmin</AssignedToUser></AssignedToUsers></AllowedAction></AllowedActions><WorkflowComments></WorkflowComments></WorkflowInfo><Status>Draft</Status><CreatedDate>2012-02-25T17:30:42Z</CreatedDate><LastModifiedDate>2012-02-28T12:38:38Z</LastModifiedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site></Site><Version></Version><Weightage/><MetadataInfo><Identifier></Identifier><IdentifierAlternate></IdentifierAlternate><TitleMain></TitleMain><TitleAlternate></TitleAlternate><MetadataDescription></MetadataDescription><SubjectKeywords><SubjectKeyword/></SubjectKeywords><SubjectClassification><Sector/></SubjectClassification><PublisherOrgNames><PublisherOrgName/></PublisherOrgNames><PublisherDeptName><PublisherDepartment/></PublisherDeptName><PublisherAddress></PublisherAddress><CreatorOrgNames><CreatorOrgName/></CreatorOrgNames><CreatorDeptName><CreatorDepartment/></CreatorDeptName><CreatorAddress></CreatorAddress><Format/><LanguageId/><Language/><MetaDataCreatedDate/><PublishedDate/><ValidDate/><CoverageJurisdiction><Jurisdiction><JurisdictionName/><JurisdictionStateId/><JurisdictionStateName/><JurisdictionDistrictId/><JurisdictionDistrictName/></Jurisdiction></CoverageJurisdiction><CoverageSpatial><Spatial><SpatialStateId/><SpatialStateName/><SpatialDistrictId/><SpatialDistrictName/></Spatial></CoverageSpatial><CoverageTemporal></CoverageTemporal><Audience><AudienceCategory/></Audience><CategoryTypes><CategoryType/></CategoryTypes><ConformsTo></ConformsTo><HasParts><HasPart></HasPart></HasParts><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><Source></Source></MetadataInfo><DependentItems/><RelatedItems/></Article></Collections>";
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("content", xmlContent);
        inputmap.put("usercontext", map);
        parameters.put("input", (Map<String, Object>) inputmap);

        Map result = provider.setDefaults("sify.co.in", parameters);
        System.out.println(result);

    }

    @Test
    public void testDeleteByID() {
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        try {
            impl.deleteByID(DOMAIN, ENTITY, ID, parameters);
        } catch (GenericException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void testDeleteByQuery() {
        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            impl.deleteByQuery(DOMAIN, ENTITY, parameters);
        } catch (GenericException ex) {

        }
    }

    @Test
    public void testFind() {
        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            impl.find(DOMAIN, ENTITY, parameters);
        } catch (GenericException ex) {

        }
    }

    @Test
    public void testFindById() {
        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            impl.findById(DOMAIN, ENTITY, ID, parameters);
        } catch (GenericException ex) {

        }
    }

    @Test
    public void testRead() {
        try {
            Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
            impl.read(DOMAIN, ENTITY, parameters);
        } catch (GenericException ex) {

        }
    }

    @Test
    public void testContentCreators() {

        String w = "<Workflow><Id>memp8aehjcjcb</Id><EntityType>Workflow</EntityType><WorkflowName><![CDATA[Fun_workflow]]>"
                + "</WorkflowName><WorkflowType>Manual</WorkflowType><WorkflowStages><WorkflowStage><StageName><![CDATA[Draft]]></StageName>"
                + "<StageType>Start</StageType><PossibleActions><PossibleAction><PossibleActionName>Forward</PossibleActionName><PossibleActionUsers>"
                + "<PossibleActionUser>eclipseadmin</PossibleActionUser></PossibleActionUsers><NextStage>Forwarded</NextStage><NextContentStatus>Draft"
                + "</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName><![CDATA[Forwarded]]></StageName>"
                + "<StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Publish</PossibleActionName><PossibleActionUsers>"
                + "<PossibleActionUser>eclipseadmin</PossibleActionUser></PossibleActionUsers><NextStage>Published</NextStage><NextContentStatus>Published"
                + "</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName><![CDATA[Published]]></StageName>"
                + "<StageType>End</StageType><PossibleActions><PossibleAction><PossibleActionName></PossibleActionName><PossibleActionUsers><PossibleActionUser>"
                + "</PossibleActionUser></PossibleActionUsers><NextStage></NextStage><NextContentStatus></NextContentStatus></PossibleAction></PossibleActions>"
                + "</WorkflowStage></WorkflowStages><CreatedDate>2012-04-12T15:34:00Z"
                + "</CreatedDate><CreatedBy>eclipseadmin</CreatedBy><LastModifiedBy>eclipseadmin</LastModifiedBy><LastModifiedDate>2012-04-12T15:34:00Z</LastModifiedDate>"
                + "<Site>eclipse.in</Site><Version>1.0</Version></Workflow>";

        ContentCreatorHelper cc = ContentCreatorHelper
                .getInstance("sify.co.in");
        HashMap<String, Object> coll = FormatXml.getInstanceof("sify.co.in")
                .in(w);

        System.out.println(cc
                .addWorkflowContentCreators((HashMap<String, Object>) coll
                        .get("Workflow")));
    }
}
