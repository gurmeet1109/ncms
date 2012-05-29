 package in.nic.cmf.security.owasp;
import in.nic.cmf.exceptions.GenericException;

import java.io.IOException;

import java.io.StringReader;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchResult;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nu.xom.Builder;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.Text;
import nu.xom.ValidityException;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.MySQLCodec;

/**
 * The Class Injection to check the the SQL And LDAP
 * Injection.
 */
public class Injection {

	
        /**
         * Encode input for use in a SQL query, according to the  MySQLCodec codec
         * and ANSI_MODE
         * The best approach is to make sure any single-quotes are double-quoted.
         * This syntax does not work with all drivers, and requires
         * modification of all queries.
         * @param input the text to encode for SQL
         * @return input encoded for use in SQL
         */
        public static String getSQLInjectionSafeContentANSIMode(String input) {
           Codec codec = new MySQLCodec(MySQLCodec.ANSI_MODE);
           return ESAPI.encoder().encodeForSQL( codec,input);
        }
        
        /**
         * Encode input for use in a SQL query, according to the  MySQLCodec codec
         * and MYSQL_MODE
         * Gets the sQL injection safe content my sql mode.
         * @param input the input
         * @return the sQL injection safe content my sql mode
         */
        public static String getSQLInjectionSafeContentMySqlMode(String input) {
            Codec codec = new MySQLCodec(MySQLCodec.MYSQL_MODE);
            return ESAPI.encoder().encodeForSQL(codec,input);
        }
        

        /**
         * Gets the lDAP injection safe content.
         *
         * @param input the input
         * @return the lDAP injection safe content
         */
        public static String getLDAPInjectionSafeContent(String input) {
            return ESAPI.encoder().encodeForLDAP(input);
        }
         
        public static String escapeDN(String name) {
            StringBuffer sb = new StringBuffer(); // If using JDK >= 1.5 consider using StringBuilder
            if ((name.length() > 0) && ((name.charAt(0) == ' ') || (name.charAt(0) == '#'))) {
                sb.append('\\'); // add the leading backslash if needed
            }
            for (int i = 0; i < name.length(); i++) {
                char curChar = name.charAt(i);
                switch (curChar) {
                    case '\\':
                        sb.append("\\\\");
                        break;
                    case ',':
                        sb.append("\\,");
                        break;
                    case '+':
                        sb.append("\\+");
                        break;
                    case '"':
                        sb.append("\\\"");
                        break;
                    case '<':
                        sb.append("\\<");
                        break;
                    case '>':
                        sb.append("\\>");
                        break;
                    case ';':
                        sb.append("\\;");
                        break;
                    default:
                        sb.append(curChar);
                }
            }
            if ((name.length() > 1) && (name.charAt(name.length() - 1) == ' ')) {
                sb.insert(sb.length() - 1, '\\'); // add the trailing backslash if needed
            }
            return sb.toString();
        }

        public static final String escapeLDAPSearchFilter(String filter) {
            StringBuffer sb = new StringBuffer(); // If using JDK >= 1.5 consider using StringBuilder
            for (int i = 0; i < filter.length(); i++) {
                char curChar = filter.charAt(i);
                switch (curChar) {
                    case '\\':
                        sb.append("\\5c");
                        break;
                    case '*':
                        sb.append("\\2a");
                        break;
                    case '(':
                        sb.append("\\28");
                        break;
                    case ')':
                        sb.append("\\29");
                        break;
                    case '\u0000': 
                        sb.append("\\00"); 
                        break;
                    default:
                        sb.append(curChar);
                }
            }
            return sb.toString();
        }

        public static void printSearchEnumeration(NamingEnumeration retEnum) {
            try {
                while (retEnum.hasMore()) {
                    SearchResult sr = (SearchResult) retEnum.next();
                    System.out.println(">>" + sr.getNameInNamespace());
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
       /* public String xmlInjectPrevent(String safeColectionsString) throws GenericException, ValidityException, ParsingException, IOException{
        	
        	try{           	        	
        	System.out.println("Before safe:::"+safeColectionsString);           
            Builder builder = new Builder();            
            nu.xom.Document document;          
            document = builder.build(new StringReader(safeColectionsString)); // grab the resulting CDATASection and keep it around as a prototype
            String  cdataText = (String) document.getRootElement().getChild(0).toString(); 
            //cdataText.detach();
            System.out.println("cdataText is what:::"+cdataText);            
            String aftupdate = "<![CDATA[" + cdataText.replaceAll("]]>", "]]>]]><![CDATA[")    + "]]>";            
            safeColectionsString=aftupdate;            
            System.out.println("After update code:::"+safeColectionsString);
            return safeColectionsString;
        	}        	
        	catch(Exception ex){ 
        		System.out.println("Inside exception xml inecjtion");
        		throw new GenericException("ERR-OWASP-0001","Malcious Found:::"+ex.getMessage());        		
        		
        	}
		
           
            //encodeCollections(collections);    
        }*/
        
      /*  
        public static void main(String f[]) throws ValidityException, ParsingException, IOException{
        	Injection injection=new Injection();
        	System.out.println("0");
        	//injection.xmlInjectPrevent("<xml><username><![CDATA[<$userName]]></username></xml>");

        	injection.xmlInjectPrevent("<xml><username><![CDATA[]]>]]></username></xml>");
        	//injection.xmlInjectPrevent("<xml><![CDATA[<]]>script<![CDATA[>]]>alert('xss')<![CDATA[<]]>/script<![CDATA[>]]></xml>");
        }*/
       
}
