package in.nic.cmf.dfbusinessflow;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.logger.LogTracer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

public class BusinessFlowImplTest extends TestCase {

    public void testAdd() {
        HashMap<String, HashMap<String, Object>> requestDetails = new HashMap<String, HashMap<String, Object>>();
        HashMap<String, Object> userContext = new HashMap<String, Object>();
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("content",
                FormatXml.getInstance().in(getWorkFlowXml()).get("Collections"));
        data.put("flattenEntities", getFlattenedArticleXml());
        requestDetails.put("usercontext", userContext);
        requestDetails.put("output", data);

        // bfImpl.add("kavitha.com", "workflowable", requestDetails, false);

    }

    public void testWorkflow() {
        Map<String, Map<String, Object>> requestDetails = new HashMap<String, Map<String, Object>>();
        HashMap<String, Object> userContext = new HashMap<String, Object>();
        userContext.put("authusername", "Thamai");
        userContext.put("aclrole", "PortalAdmin");
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("content", getWorkFlowXml());
        // data.put("flattenEntities", getFlattenedArticleXml());
        requestDetails.put("usercontext", userContext);
        requestDetails.put("input", data);
        BusinessFlowImpl bb = new BusinessFlowImpl();
        bb.setLogTracer(new LogTracer("bflog"));
        bb.add("sify.co.in", "Workflow", requestDetails);
    }

    public void testArticle() {
        Map<String, Map<String, Object>> requestDetails = new HashMap<String, Map<String, Object>>();
        HashMap<String, Object> userContext = new HashMap<String, Object>();
        userContext.put("authusername", "creatoruser");
        userContext.put("aclrole", "PortalAdmin");
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("content", getArticleXml());
        // data.put("flattenEntities", getFlattenedArticleXml());
        // requestDetails.put("userContext", userContext);
        data.put("userContext", userContext);
        requestDetails.put("input", data);

        BusinessFlowImpl bb = new BusinessFlowImpl();
        bb.setLogTracer(new LogTracer("bflog"));
        bb.add("sify.co.in", "Workflow", requestDetails);
    }

    public void testProcess() {
        System.out.println("h");
        /*
         * XMLDecoder xmlDecoder = new XMLDecoder(new
         * ByteArrayInputStream(serializedMap.getBytes()));
         * Map<String, String> parsedMap = (Map<String, String>)
         * xmlDecoder.readObject();
         * for (String key : map.keySet()) {
         * Assert.assertEquals(parsedMap.get(key), map.get(key));
         * }
         */
    }

    public List<HashMap<String, Object>> getFlattenedArticleXml() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("EntityType", "Article");
        data.put("Site", "kavitha.com");
        data.put("WorkflowInfo", new HashMap());

        HashMap<String, Object> data1 = new HashMap<String, Object>();
        data1.put("EntityType", "Article");
        data1.put("Site", "kavitha.com");
        data1.put("WorkflowInfo", new HashMap());

        List<HashMap<String, Object>> lstMap = new ArrayList<HashMap<String, Object>>();
        lstMap.add(data);
        lstMap.add(data1);

        return lstMap;

    }

    private String getWorkFlowXml() {
        return "<Collections><Count>1</Count><Workflow><Id>mbykytbeabijj</Id><EntityType>Workflow</EntityType>"
                + "<WorkflowName>dontdeletethis</WorkflowName><WorkflowType>Manual</WorkflowType><WorkflowStages><WorkflowStage>"
                + "<StageName>Draft</StageName><StageType>Start</StageType><PossibleActions><PossibleAction><PossibleActionName>Forward</PossibleActionName><PossibleActionUsers><PossibleActionUser>creatoruser</PossibleActionUser><PossibleActionUser>suresh</PossibleActionUser></PossibleActionUsers><NextStage>Forwarded</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction><PossibleAction><PossibleActionName>Reject</PossibleActionName><PossibleActionUsers><PossibleActionUser>creatoruser</PossibleActionUser></PossibleActionUsers><NextStage>Rejected</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName>Approved</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Moderate</PossibleActionName><PossibleActionUsers><PossibleActionUser>moderateuser</PossibleActionUser></PossibleActionUsers><NextStage>Moderated</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName>Forwarded</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Approve</PossibleActionName><PossibleActionUsers><PossibleActionUser>approveuser</PossibleActionUser></PossibleActionUsers><NextStage>Approved</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName>Moderated</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Publish</PossibleActionName><PossibleActionUsers><PossibleActionUser>publishuser</PossibleActionUser></PossibleActionUsers><NextStage>Published</NextStage><NextContentStatus>Published</NextContentStatus></PossibleAction><PossibleAction><PossibleActionName>Reject</PossibleActionName><PossibleActionUsers><PossibleActionUser>moderateuser</PossibleActionUser></PossibleActionUsers><NextStage>Rejected</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName>Published</StageName><StageType>Intermediate</StageType><PossibleActions><PossibleAction><PossibleActionName>Reject</PossibleActionName><PossibleActionUsers><PossibleActionUser>publishuser</PossibleActionUser></PossibleActionUsers><NextStage>Rejected</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage><WorkflowStage><StageName>Rejected</StageName><StageType>End</StageType><PossibleActions><PossibleAction><PossibleActionName>Move to draft</PossibleActionName><PossibleActionUsers><PossibleActionUser>publishuser</PossibleActionUser></PossibleActionUsers><NextStage>Draft</NextStage><NextContentStatus>Draft</NextContentStatus></PossibleAction></PossibleActions></WorkflowStage></WorkflowStages><ContentCreators><ContentCreator>creatoruser</ContentCreator><ContentCreator>suresh</ContentCreator></ContentCreators><CreatedDate>2012-01-24T10:18:49Z</CreatedDate><CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><LastModifiedDate>2012-02-07T20:20:17Z</LastModifiedDate><Site>sify.co.in</Site><Version>1.0</Version></Workflow></Collections>";
    }

    public String getArticleXml() {
        return "<Collections><Count>1</Count>"
                + "<Article><Id>mczr4Gieccedf</Id><EntityType>Article</EntityType><AliasId></AliasId>"
                + "<Title></Title><Description></Description><ArticleType>MyTest</ArticleType><AssociatedIAPath><![CDATA[/Sify News]]>"
                + "</AssociatedIAPath><Md5>8747a2af1b3239bae55404afd6723054</Md5><GeoTags><Location><City></City><Country></Country><Lat></Lat>"
                + "<Lng></Lng><LocationId></LocationId><State></State><Ip><![CDATA[]]></Ip><Coords></Coords><District></District></Location></GeoTags>"
                + "<Status>Draft</Status><CreatedDate>2012-02-25T17:30:42Z</CreatedDate><LastModifiedDate>2012-02-25T17:30:42Z</LastModifiedDate>"
                + "<CreatedBy>sifycoadmin</CreatedBy><LastModifiedBy>sifycoadmin</LastModifiedBy><Site>sify.co.in</Site><Version>1.0</Version><SeoUrl>"
                + "<![CDATA[http://sify.co.in/sify_news/--df-article-mczr4gieccedf.php]]></SeoUrl><WorkflowInfo><WorkflowId>mbykytbeabijj</WorkflowId>"
                + "<WorkflowName>dontdeletethis</WorkflowName><CurrentUser>sifycoadmin</CurrentUser><CurrentAction>Forward</CurrentAction><Stage>Draft</Stage>"
                + "<AllowedActions>{\"AllowedActions\":{\"AllowedAction\":[{\"AssignedToUsers\":{\"AssignedToUser\":\"sifycoadmin\"},\"AllowedActionName\":\"Forward\"},{\"AssignedToUsers\":{\"AssignedToUser\":\"sifycoadmin\"},\"AllowedActionName\":\"Reject\"}]}}</AllowedActions><WorkflowComments><![CDATA[]]></WorkflowComments></WorkflowInfo><MetadataInfo>"
                + "<Identifier><![CDATA[]]></Identifier><IdentifierAlternate><![CDATA[]]></IdentifierAlternate><TitleMain><![CDATA[]]></TitleMain>"
                + "<TitleAlternate><![CDATA[]]></TitleAlternate><MetadataDescription><![CDATA[]]></MetadataDescription><SubjectKeywords><SubjectKeyword>"
                + "</SubjectKeyword></SubjectKeywords><SubjectClassification><Sector></Sector></SubjectClassification><PublisherOrgNames><PublisherOrgName>"
                + "</PublisherOrgName></PublisherOrgNames><PublisherDeptName><PublisherDepartment></PublisherDepartment></PublisherDeptName><PublisherAddress>"
                + "<![CDATA[]]></PublisherAddress><CreatorOrgNames><CreatorOrgName></CreatorOrgName></CreatorOrgNames><CreatorDeptName><CreatorDepartment>"
                + "</CreatorDepartment></CreatorDeptName><CreatorAddress><![CDATA[]]></CreatorAddress><Format>"
                + "</Format><LanguageId></LanguageId><Language></Language><MetaDataCreatedDate></MetaDataCreatedDate>"
                + "<PublishedDate></PublishedDate><ValidDate></ValidDate><CoverageJurisdiction><Jurisdiction><JurisdictionName>"
                + "</JurisdictionName><JurisdictionStateId></JurisdictionStateId><JurisdictionStateName></JurisdictionStateName><JurisdictionDistrictId>"
                + "</JurisdictionDistrictId><JurisdictionDistrictName></JurisdictionDistrictName></Jurisdiction></CoverageJurisdiction><CoverageSpatial>"
                + "<Spatial><SpatialStateId></SpatialStateId><SpatialStateName></SpatialStateName><SpatialDistrictId></SpatialDistrictId><SpatialDistrictName>"
                + "</SpatialDistrictName></Spatial></CoverageSpatial><CoverageTemporal></CoverageTemporal><Audience><AudienceCategory></AudienceCategory>"
                + "</Audience><CategoryTypes><CategoryType></CategoryType></CategoryTypes><ConformsTo></ConformsTo><HasParts><HasPart></HasPart></HasParts><IsPartOf></IsPartOf><HasVersion></HasVersion><IsVersionOf></IsVersionOf><Source><![CDATA[]]></Source></MetadataInfo><DependentItems/><RelatedItems/></Article></Collections>";
    }
}
