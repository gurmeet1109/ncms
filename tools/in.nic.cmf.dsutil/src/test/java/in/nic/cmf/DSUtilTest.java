package in.nic.cmf;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.Test;

/**
 * The Class DSUtilTest.
 */
public class DSUtilTest {

	/**
	 * Test get mapping json.
	 *
	 * @throws ResourceNotFoundException the resource not found exception
	 * @throws ParseErrorException the parse error exception
	 * @throws Exception the exception
	 */
	@Test
	public void testGetMappingJson() throws ResourceNotFoundException,
			ParseErrorException, Exception {
		DSUtil dsUtil = new DSUtil();

		JSONObject object = new JSONObject();
		Map objectsmap = new HashMap();

		VelocityContext context = new VelocityContext();
		InputStream is = new FileInputStream(dsUtil.getClass().getClassLoader()
				.getResource("domain.json").getFile());
		String jsonTxt = IOUtils.toString(is);
		JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
		JSONArray jsonarray = json.getJSONArray("classname");
		// domainName = (String) json.get("domainname");
		for (Object j : jsonarray) {
			JSONObject jsonobj = (JSONObject) j;
			objectsmap.put(jsonobj.get("name"), j);
		}
		context.put("classesobj", objectsmap);

		dsUtil.getExtjsForm(jsonarray, context);
		dsUtil.getExtjsColumn(jsonarray, context);
		dsUtil.getMappingJson(jsonarray, objectsmap);
		dsUtil.getModelData(jsonarray, context);
	}

	
	 /**
 	 * Test get extjs form.
 	 */
 	@Test
	 public void testGetExtjsForm() {
		 DSUtil dsUtil = new DSUtil();
		 
		 try{
			 JSONObject object = new JSONObject();
				Map objectsmap = new HashMap();

				VelocityContext context = new VelocityContext();
				InputStream is = new FileInputStream(dsUtil.getClass().getClassLoader()
						.getResource("domain.json").getFile());
				String jsonTxt = IOUtils.toString(is);
				JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
				JSONArray jsonarray = json.getJSONArray("classname");
				for (Object j : jsonarray) {
					JSONObject jsonobj = (JSONObject) j;
//					objectsmap.put(jsonobj.get("name"), j);
				}
				objectsmap.put("--","--");
				context.put("classesobj", objectsmap);
			 dsUtil.getExtjsForm(jsonarray, null);
			 
		 } catch(Exception e){
			 assertNotNull(e);
		 }
	 }
	
	 /**
 	 * Test get label.
 	 */
 	@Test
	 public void testGetLabel() {
		 DSUtil dsUtil = new DSUtil();
		 dsUtil.getLabel("* ");
	 }
	
	 /**
 	 * Test get extjs column.
 	 */
 	@Test
	 public void testGetExtjsColumn() {
		 DSUtil dsUtil = new DSUtil();
		 
		 try{
				VelocityContext context = new VelocityContext();
				InputStream is = new FileInputStream(dsUtil.getClass().getClassLoader()
						.getResource("domain.json").getFile());
				String jsonTxt = IOUtils.toString(is);
				JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
				JSONArray jsonarray = json.getJSONArray("classname");
				for (Object j : jsonarray) {
					JSONObject jsonobj = (JSONObject) j;
//					objectsmap.put(jsonobj.get("name"), j);
				}
			 dsUtil.getExtjsColumn(jsonarray, null);
			 
		 } catch(Exception e){
			 assertNotNull(e);
		 }
	 }
	 
	 /**
 	 * Test rec.
 	 */
 	@Test
	 public void testRec() {
		 try{
		 DSUtil dsUtil = new DSUtil();
		 dsUtil.rec(null, null, null);
		 } catch (Exception e){
			 assertNotNull(e);
		 }
	 }
	 
	 /**
 	 * Test get model data.
 	 */
 	@Test
	 public void testGetModelData() {
		 DSUtil dsUtil = new DSUtil();
		 
		 try{
			 JSONObject object = new JSONObject();
				Map objectsmap = new HashMap();

				VelocityContext context = new VelocityContext();
				InputStream is = new FileInputStream(dsUtil.getClass().getClassLoader()
						.getResource("domain.json").getFile());
				String jsonTxt = IOUtils.toString(is);
				JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonTxt);
				JSONArray jsonarray = json.getJSONArray("classname");
				for (Object j : jsonarray) {
					JSONObject jsonobj = (JSONObject) j;
				}
			 dsUtil.getModelData(jsonarray, null);
			 
		 } catch(Exception e){
			 assertNotNull(e);
		 }
	 }
 	
 	/**
	 * Test main.
 	 * @throws Exception 
 	 * @throws MethodInvocationException 
 	 * @throws ParseErrorException 
 	 * @throws ResourceNotFoundException 
	 */
	@Test
	public void testMain() throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, Exception {
		DSUtil dsUtil = new DSUtil();
		dsUtil.main(null);
	}
}
