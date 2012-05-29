package in.nic.cmf.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class GenericExceptionTest {
    private String domain = "bala.in";

    @Test
    public void testGenericExceptionString() throws GenericException {
        throw new GenericException(domain, "ERR-ACL-0003");
    }

    @Test
    public void testGenericExceptionStringString() throws GenericException {
        throw new GenericException(domain, "ERR-ACL-0003",
                "readKnowledgebasefailure");
    }

    @Test
    public void testGenericExceptionStringStringString()
            throws GenericException {
        throw new GenericException(domain, "ERR-ACL-0003",
                "readKnowledgebase:", "article");
    }

    @Test
    public void testGenericExceptionStringStringStringException()
            throws GenericException {
        // throw new
        // GenericException("ERR-ACL-0003","Overall catch of readKnowledgebase: "+ex.getMessage(),"",ex);
        throw new GenericException(domain, "ERR-SA-0004", "readKnowledgebase: ");
    }

    @Test
    public void testGenericExceptionStringStringMapException()
            throws GenericException {
        Map input = new HashMap();
        input.put("domain", "nic.in");
        input.put("entity", "article");
        throw new GenericException(domain, "ERR-ACL-0003",
                "readKnowledgebase: ", input, null);
    }
}
