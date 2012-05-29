package in.nic.cmf.security.owasp;

import static org.junit.Assert.*;

import org.junit.Test;

public class InjectionTest {

	Injection injection=new Injection();
	
	@Test
	public void testGetLDAPInjectionSafeContent() {
		assertEquals("\\28cn=Raja\\29",Injection.getLDAPInjectionSafeContent("(cn=" + "Raja" + ")"));
	}

	@Test
	public void testGetSQLInjectionSafeContentANSIMode() {
		assertEquals("username=''raja'' OR ''1=1''",Injection.getSQLInjectionSafeContentANSIMode("username='raja' OR '1=1'"));
	}

	@Test
	public void testGetSQLInjectionSafeContentMySqlMode() {
		System.out.println(Injection.getSQLInjectionSafeContentMySqlMode("username = 1' or '1' = '1"));
		//assertEquals("username \= 1\' or \'1\' \= \'1", Injection.getSQLInjectionSafeContentMySqlMode("username = 1' or '1' = '1"));
		assertEquals(
				"username\\=\\'ramesh\\' OR \\'1\\=1\\'",
				Injection
						.getSQLInjectionSafeContentMySqlMode("username='ramesh' OR '1=1'"));
	}

}
