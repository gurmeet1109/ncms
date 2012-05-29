/*package in.nic.cmf.services.ruleengine;

import static org.junit.Assert.*;
import junit.framework.Assert;
import in.nic.cmf.domain.Article;

import org.junit.Test;

public class DroolsResourceTest {
	@Test
	public void testPostArticleWithDrl() {
		DroolsResource ds = new DroolsResource();
		Article a = new Article();
		a.setId("");
		a.setTitle("");
		try {
			ds.postArticle("drl", a);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			final String msg = "rule: Id Should not be null";
			Assert.assertEquals(msg, e.getMessage().trim());
		}
	}

	@Test
	public void testPostArticleWithXls() {
		DroolsResource ds = new DroolsResource();
		Article a = new Article();
		a.setId("");
		a.setTitle("");
		try {
			ds.postArticle("xls", a);
		} catch (final Exception e) {
			final String msg = "rule: Article Id Should not be null_13";
			System.out.println("******" + msg + "*******"
					+ e.getMessage().trim() + "********");
			Assert.assertEquals(msg, e.getMessage().trim());
		}
	}

	@Test
	public void testPostArticleWithoutFilename() {
		DroolsResource ds = new DroolsResource();
		Article a = new Article();
		a.setId("");
		a.setTitle("");
		try {
			ds.postArticle("dfdf", a);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			final String msg = "Not Valid Extension";
			// System.out.println("******"+msg+"*******"+e.getMessage().trim()+"********");
			Assert.assertEquals(msg, e.getMessage().trim());
		}
	}

	@Test
	public void testPostArticleWithValidDetails() {
		DroolsResource ds = new DroolsResource();
		Article a = new Article();
		a.setId("abcdfghijkl");
		a.setTitle("testing djfdkfj");
		String msg = "No Error";
		Assert.assertEquals(msg, ds.postArticle("drl", a));
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

}
*/