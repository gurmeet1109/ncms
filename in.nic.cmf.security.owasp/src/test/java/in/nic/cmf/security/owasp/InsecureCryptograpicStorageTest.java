package in.nic.cmf.security.owasp;

import org.junit.*;
import static org.junit.Assert.*;

public class InsecureCryptograpicStorageTest {

	/*@Test
	public void testEncrypt() throws Exception {
		String user = "nicadmin";
		
		System.out.println("1111111111111111"+InsecureCryptograpicStorage.encrypt(user));
		 Assert.assertEquals(null,InsecureCryptograpicStorage.encrypt(user));
	}*/

	@Test
	public void testDecrypt() throws Exception {
		// InsecureCryptograpicStorage.encrypt(user);
		String user = "nsms";
		String userenecryptedcode = "uAPqT6yljE7Lp7jE";
		String status = InsecureCryptograpicStorage.decrypt(userenecryptedcode);		
		Assert.assertEquals(user,status);
	}

	
	/**
	 * Run the InsecureCryptograpicStorage() constructor test.
	 *
	 
	 *//*
	@Test
	public void testInsecureCryptograpicStorage_1()
		throws Exception {
		InsecureCryptograpicStorage result = new InsecureCryptograpicStorage();
		assertNotNull(result);
		
	}

	*//**
	 * Run the String decrypt(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 *//*
//	@Test
//	public void testDecrypt_1()
//		throws Exception {
//		String encryptedKey = "";
//
//		String result = InsecureCryptograpicStorage.decrypt(encryptedKey);
//
//		assertNotNull(result);
//	}

	*//**
	 * Run the String encrypt(String) method test.
	 *
	 * @throws Exception
	 *
	 
	 *//*
	@Test
	public void testEncrypt_1()
		throws Exception {
		String valueToEnc = "";

		String result = InsecureCryptograpicStorage.encrypt(valueToEnc);

		
		assertEquals(null, result);
	}

	*//**
	 * Run the String encrypt(String,String,int) method test.
	 *
	 * @throws Exception
	 *
	 
	 *//*
	@Test
	public void testEncrypt_2()
		throws Exception {
		String ciphertext = "";
		String password = "";
		int nBits = 1;

		String result = InsecureCryptograpicStorage.encrypt(ciphertext, password, nBits);

		
		assertEquals(null, result);
	}

	

	*//**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 
	 *//*
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(InsecureCryptograpicStorageTest.class);
	}*/
}
