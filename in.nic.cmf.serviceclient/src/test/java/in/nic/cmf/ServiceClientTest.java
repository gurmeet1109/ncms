package in.nic.cmf;

import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.serviceclient.ServiceClientImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.junit.Assert;
import org.junit.Test;

public class ServiceClientTest {

	
	
	String domain = "bala.in";
    @Test
    public void testGet() throws GenericException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "PortalAdmin");
        map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "balaadmin");

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("usercontext", map);
        inputmap.put("queryString", "q=&entitytype=category");
        parameters.put("input", inputmap);
        ServiceClientImpl scimpl = ServiceClientImpl.getInstance(domain,"appflow");
        try {
        	
            Map<String, Map<String, Object>> result = scimpl.find(domain, "catagory", parameters);
            System.out.println("------------------------------------------------");

          //  result = scimpl.find(domain, "cateogry", parameters);
            
            System.out.println("get xml is :" + result);
            map.clear();
            Assert.assertEquals(200, result.get("output").get("statusCode"));
        } catch (GenericException e) {
            Assert.assertNotNull(e);
        } catch (Exception e) {
            throw new GenericException(domain,"ERR-Sc-0014", "Unable to get data", e);
        }
    }

    @Test
    public void testGetByFileNameDms() throws GenericException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "kavitha");
        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("usercontext", map);
        parameters.put("input", inputmap);
        ServiceClientImpl client = ServiceClientImpl.getInstance(domain,"dms");
        try {
            Map<String, Map<String, Object>> result = client.findById(
                    "sify.co.in", "Media", "mbfnHIejgfdib.jpg", parameters);
            System.out.println("get xml is :" + result);
            map.clear();
            Assert.assertEquals(200, result.get("output").get("statusCode"));
        } catch (GenericException e) {
            Assert.assertNotNull(e);
        } catch (Exception e) {
            throw new GenericException(domain,"ERR-Sc-0014", "Unable to get data", e);
        }
    }

    @Test
    public void testPost() throws GenericException {
        String xmlContent = "<Collections><Sector><Id>oioioioioioll</Id><SectorName>wowwowaa</SectorName><Site>kavitha.in</Site><IsTopLevel>false</IsTopLevel><CreatedBy>kavitha</CreatedBy><CreatedDate>2011-10-05T01:30:15Z</CreatedDate><LastModifiedDate>2011-10-05T01:30:15Z</LastModifiedDate><ParentSectorId>llluROdhbe123</ParentSectorId><EntityType>Sector</EntityType><LastModifiedBy>kavitha</LastModifiedBy><Version>1.0</Version><ParentSectorName>sector2</ParentSectorName></Sector></Collections>";
        // String xmlContent =
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "ceb1ba47e241b497ade11404b7dcb849449ebccb");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "kavitha");

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("format", "application/xml");
        inputmap.put("userContext", map);
        inputmap.put("content", xmlContent);
        parameters.put("input", inputmap);

        ServiceClientImpl client = ServiceClientImpl
                .getInstance(domain,"dms");
        try {
            // HashMap<String, HashMap<String, Object>> result =
            // client.add("kavitha.in","Sector",parameters);

            Map<String, Map<String, Object>> result = client.read(
                    "kavitha.in", "Sector", parameters);
            System.out.println("get xml is :" + result);
            map.clear();
            Assert.assertEquals(200, result.get("output").get("statusCode"));
        } catch (GenericException e) {
            Assert.assertNotNull(e);
        } catch (Exception e) {
            throw new GenericException(domain,"ERR-Sc-0014", "Unable to get data", e);
        }
    }

    @Test
    public void testPostBinary() throws IOException, GenericException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "PortalAdmin");
        map.put("api_key", "c2c02b0e2491d9ec79946aa6f1ad4004f93494de");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "seaadmin");

        File file = new File("/home/kavitha/success.jpg");
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        FileItem item = createFileItem(bytes);
        // FileInputStream imagefileInputStream = (FileInputStream)
        // item.getInputStream();
        // int byteslength = (int) item.getSize();
        String mimeType = item.getContentType();

        ServiceClientImpl serviceclient = ServiceClientImpl.getInstance(domain,"dms");
        Map<String, Object> binaryHash = new HashMap<String, Object>();
        binaryHash.put("momomomomomcc", item.get());

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("format", mimeType);
        inputmap.put("usercontext", map);
        inputmap.put("files", binaryHash);
        parameters.put("input", inputmap);
        parameters = serviceclient.add(domain, "media", parameters);
        System.out.println("output:" + parameters);
        Assert.assertEquals(200, parameters.get("output").get("statusCode"));

    }

    @Test
    public void testDeleteById() throws GenericException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "ceb1ba47e241b497ade11404b7dcb849449ebccb");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "kavitha");

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("format", "application/xml");
        inputmap.put("usercontext", map);
        // inputmap.put("content", xmlContent);
        parameters.put("input", inputmap);
        // ServiceClient client = ServiceClient.getInstance("searchengine");
        ServiceClientImpl client = ServiceClientImpl
                .getInstance(domain,"searchengine");
        try {
            Map<String, Map<String, Object>> result = client.deleteByID(domain, "sector", "oioioioioioio", parameters);
            System.out.println("get xml is :" + result);
            map.clear();
            Assert.assertEquals(200, result.get("output").get("statusCode"));
        } catch (GenericException e) {
            Assert.assertNotNull(e);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-Sc-0014", "Unable to get data", e);
        }
    }

    @Test
    public void testDeleteByUrl() throws GenericException {

        Map<String, String> map = new HashMap<String, String>();
        map.put("aclrole", "portaladmin");
        map.put("api_key", "ceb1ba47e241b497ade11404b7dcb849449ebccb");
        map.put("requester", "124.7.228.200");
        map.put("authusername", "seaadmin");

        Map<String, Map<String, Object>> parameters = new HashMap<String, Map<String, Object>>();
        Map<String, Object> inputmap = new HashMap<String, Object>();
        inputmap.put("format", "application/xml");
        inputmap.put("usercontext", map);
        parameters.put("input", inputmap);
        ServiceClientImpl client = ServiceClientImpl.getInstance(domain, "searchengine");
        try {
            Map<String, Map<String, Object>> result = client.deleteByQuery(domain, "amsai123swe6", parameters);
            System.out.println("get xml is :" + result);
            map.clear();
            Assert.assertEquals(200, result.get("output").get("statusCode"));
        } catch (GenericException e) {
            Assert.assertNotNull(e);
        } catch (Exception e) {
            throw new GenericException(domain,"ERR-Sc-0014", "Unable to get data", e);
        }
    }

    private FileItem createFileItem(byte[] contentBytes)
            throws GenericException, IOException {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "files";

        FileItem item = factory.createItem(textFieldName, "image/jpeg", true,
                "images.jpeg");
        OutputStream os = (OutputStream) item.getOutputStream();
        os.write(contentBytes);
        os.close();
        return item;

    }

}
