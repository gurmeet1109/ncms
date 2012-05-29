package in.nic.cmf.seplugin.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.apache.solr.response.SolrQueryResponse;

import org.junit.Test;

import junit.framework.Assert;

public class CustomXMLResponseWriterTest {
	CustomXMLResponseWriter writer = new CustomXMLResponseWriter();
	
	@Test
	public void testCustomXMLResponseWriterInstanceNotNull() throws FileNotFoundException, IOException {
		Assert.assertNotNull(writer);
		writer.write(new PrintWriter("/home/premananda/out"),"Test");
		SolrQueryResponse response = new SolrQueryResponse();
		writer.write(new PrintWriter("/home/premananda/out"), null, response);
		response.setException(new  Exception());
		NamedList<SimpleOrderedMap> namedList = new NamedList<SimpleOrderedMap>();
		SimpleOrderedMap map = new SimpleOrderedMap();
		map.add("status", 400);
		map.add("QTime", 1);
		namedList.add("responseHeader", map);
		response.setAllValues(namedList);
		writer.write(new PrintWriter("/home/premananda/out"), null, response);
	}
	
	@Test
	public void testCustomXMLResponseWriterContentTypeNotNull() {
		Assert.assertNotNull(writer.getContentType(null, new SolrQueryResponse()));
	}	
}
