package in.nic.cmf.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RedisCacheTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetInstance() {
        RedisCache rc = RedisCache.getInstance();
        RedisCache rc1 = RedisCache.getInstance();
        assertSame(rc, rc1);
    }

    @Test
    public void testSetStringObject() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        assertEquals("a", rc.get("a"));
    }

    @Test
    public void testSetStringObjectInt() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        assertEquals("a", rc.get("a"));
    }

    @Test
    public void testSetStringString() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        assertEquals("a", rc.get("a"));
    }

    @Test
    public void testSetHashMapOfStringObject() {
        HashMap<String, Object> h = new HashMap<String, Object>();
        h.put("a", "a");
        h.put("b", "b");
        h.put("c", "c");

        RedisCache rc = RedisCache.getInstance();
        rc.set(h);

        ArrayList<String> l = new ArrayList<String>();
        l.add("a");
        l.add("b");
        l.add("c");

        assertEquals(h, rc.get(l));
    }

    @Test
    public void testGetString() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        assertEquals("a", rc.get("a"));
    }

    @Test
    public void testGetArrayListOfString() {
        HashMap<String, Object> h = new HashMap<String, Object>();
        h.put("a", "a");
        h.put("b", "b");
        h.put("c", "c");

        RedisCache rc = RedisCache.getInstance();
        rc.set(h);

        ArrayList<String> l = new ArrayList<String>();
        l.add("a");
        l.add("b");
        l.add("c");

        assertEquals(h, rc.get(l));

    }

    @Test
    public void testDeleteString() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        rc.delete("a");
        assertEquals(null, rc.get("a"));
    }

    @Test
    public void testDeleteArrayListOfString() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        rc.set("b", "b");
        rc.set("c", "c");
        ArrayList<String> l = new ArrayList<String>();
        l.add("a");
        l.add("b");
        l.add("c");

        rc.delete(l);
        assertEquals(null, rc.get("a"));
        assertEquals(null, rc.get("b"));
        assertEquals(null, rc.get("c"));

    }

    @Test
    public void testSetPerformance() {
        RedisCache rc = RedisCache.getInstance();

        Long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            rc.set("a", "a");
        }
        l = System.currentTimeMillis() - l;
        assertTrue("Performance Test: Time Taken for performance:>" + l + "<",
                        (l < 1000L));
    }

    @Test
    public void testGetPerformance() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("a", "a");
        Long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            rc.get("a");
        }
        l = System.currentTimeMillis() - l;
        assertTrue("Performance Test: Time Taken for performance:>" + l + "<",
                        (l < 1000L));
    }

    @Test
    public void testSetCollectionXml() {
        RedisCache rc = RedisCache.getInstance();
        rc.set("junittest1.xml",
                        "<Collections><Article><Id>lknmi2fhjjgga</Id><EntityType>Article</EntityType><AliasId></AliasId><Title><![CDATA[Kanchipuram]]></Title><Description><![CDATA[Kanchipuram]]></Description><ArticleType>Article</ArticleType><AssociatedIAID>lj1qincjdbabi</AssociatedIAID><AssociatedIAName>3608boatdesireantagonized5818armydustslip1425Dodog</AssociatedIAName><AssociatedIAPath>Home/ia name</AssociatedIAPath><Md5>135dd15a011afde805c44949eb7b4419</Md5><SourceUrl><![CDATA[]]></SourceUrl><Status>Published</Status><GeoTags><Location><City>Chennai</City><Country>IN</Country><Lat>13.0833</Lat><Lng>80.2833</Lng><LocationId></LocationId><State>25</State><Ip><![CDATA[]]></Ip><District></District></Location></GeoTags><CreatedDate>2011-10-13T12:07:46Z</CreatedDate><LastModifiedDate>2011-10-13T12:07:46Z</LastModifiedDate><CreatedBy>ramyaadmin</CreatedBy><LastModifiedBy>ramyaadmin</LastModifiedBy><Site>ramya.in</Site><Version>1.0</Version><SeoUrl><![CDATA[http://ramya.in/home/ianame/kanchipuram-article-lknmi2fhjjgga.php]]></SeoUrl><WorkflowInfo><CurrentUser>ramyaadmin</CurrentUser><CurrentEvent>Publish</CurrentEvent><AssignedTo><User></User></AssignedTo><Stage>Published</Stage><NextPossibleEvents><NextPossibleEvent></NextPossibleEvent></NextPossibleEvents><WorkflowComments></WorkflowComments></WorkflowInfo><IsNpiMetadata></IsNpiMetadata><DependentItems/><RelatedItems/></Article></Collections>");
        // assertEquals("a", rc.get("a"));
        rc.set("junittest2.xml",
                        "<Collections><Article><Id>ljxnhqajjgdfb</Id><EntityType>Article</EntityType><AliasId></AliasId><Title><![CDATA[Kanchipuram]]></Title><Description><![CDATA[Kanchipuram]]></Description><ArticleType>Article</ArticleType><AssociatedIAID>lj1qincjdbabi</AssociatedIAID><AssociatedIAName>3608boatdesireantagonized5818armydustslip1425Dodog</AssociatedIAName><AssociatedIAPath>Home/ia name</AssociatedIAPath><Md5>135dd15a011afde805c44949eb7b4419</Md5><SourceUrl><![CDATA[]]></SourceUrl><Status>Published</Status><GeoTags><Location><City>Chennai</City><Country>IN</Country><Lat>13.0833</Lat><Lng>80.2833</Lng><LocationId></LocationId><State>25</State><Ip><![CDATA[]]></Ip><District></District></Location></GeoTags><CreatedDate>2011-10-13T12:07:46Z</CreatedDate><LastModifiedDate>2011-10-13T12:07:46Z</LastModifiedDate><CreatedBy>ramyaadmin</CreatedBy><LastModifiedBy>ramyaadmin</LastModifiedBy><Site>ramya.in</Site><Version>1.0</Version><SeoUrl><![CDATA[http://ramya.in/home/ianame/kanchipuram-article-lknmi2fhjjgga.php]]></SeoUrl><WorkflowInfo><CurrentUser>ramyaadmin</CurrentUser><CurrentEvent>Publish</CurrentEvent><AssignedTo><User></User></AssignedTo><Stage>Published</Stage><NextPossibleEvents><NextPossibleEvent></NextPossibleEvent></NextPossibleEvents><WorkflowComments></WorkflowComments></WorkflowInfo><IsNpiMetadata></IsNpiMetadata><DependentItems/><RelatedItems/></Article></Collections>");

        // System.out.println(rc.get("lknmi2fhjjgga"));
    }

}
