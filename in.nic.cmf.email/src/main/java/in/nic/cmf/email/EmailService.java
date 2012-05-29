package in.nic.cmf.email;

import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.PropertiesUtil;
import in.nic.cmf.serviceclient.ServiceClientImpl;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * The Class EmailService.
 */
public class EmailService {

   // private static final EmailService emailService = new EmailService();
    private static HashMap<String, EmailService> hashEmailService= new HashMap<String, EmailService>();
    private String domain;
    private final PropertiesUtil propUtil;
  
    
   
    
    public static EmailService getInstanceof(String domain) {
        if (!hashEmailService.containsKey(domain)) {
            hashEmailService.put(domain, new EmailService(domain));
        }
        return hashEmailService.get(domain);
    }
    
    private static LogTracer log ;
    private EmailService(String domain) {
        setLogger(domain);
         propUtil = PropertiesUtil.getInstanceof(domain, "email");

    }
    private void setLogger(String domain) {
        log = new LogTracer(domain, "emaillogger");
    }
   

    /**
     * Send ssl message.
     * 
     * @param recipients
     *            the recipients
     * @param subject
     *            the subject
     * @param message
     *            the message
     * @param from
     *            the from
     * @return true, if successful
     * @throws MessagingException
     *             the messaging exception
     */
    public boolean sendSSLMessage(List<String> recipients, String subject,
	    String message, String from) throws MessagingException {
	log.methodEntry("sendSSLMessage entry");
	boolean debug = true;
	try {

	    log.debug("Input of sendSSLMessage:" + subject + ";" + from + ";"
		    + recipients);

	    Properties props = new Properties();
	    props.put("mail.smtp.host", propUtil.getProperty("SMTP_HOST_NAME"));
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.port", propUtil.getProperty("SMTP_PORT"));
	    props.put("mail.smtp.socketFactory.port",
		    propUtil.getProperty("SMTP_PORT"));
	    props.put("mail.smtp.socketFactory.class",
		    propUtil.getProperty("SSL_FACTORY"));
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    // props.put("mail.smtp.ssl.trust", "*");
	    log.debug("prop details pushed from the properties.");

	    Session session = Session.getDefaultInstance(props,
		    new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
			    return new PasswordAuthentication(propUtil
				    .getProperty("fromemailid"), propUtil
				    .getProperty("password"));
			}
		    });
	    log.debug("after getting defaultinstance and creating passwordAuthentication instance.");
	    session.setDebug(debug);
	    Message msg = new MimeMessage(session);
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.size()];
	    for (int i = 0; i < recipients.size(); i++) {
		addressTo[i] = new InternetAddress(recipients.get(i));
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);
	    msg.setSubject(subject);

	    MimeBodyPart textPart = new MimeBodyPart();
	    textPart.setContent(message, "text/html");

	    /*
	     * MimeBodyPart attachFilePart = new MimeBodyPart(); FileDataSource
	     * fds = new FileDataSource("SimpleMailWithAttachment.java");
	     * attachFilePart.setDataHandler(new DataHandler(fds));
	     * attachFilePart.setFileName(fds.getName());
	     */

	    Multipart mp = new MimeMultipart();
	    mp.addBodyPart(textPart);
	    // mp.addBodyPart(attachFilePart);

	    msg.setContent(mp);

	    // msg.setContent(message, "text/html");
	    System.out.println("before sending email.");
	    Transport.send(msg);
	    System.out.println("after send email.");
	    log.methodExit("sendSSLMessage exit");
	    return true;

	} catch (Exception ex) {
	    throw new GenericException(domain,"ERR-EMAIL-0002",
		    "Exception occurred at sendSSLException.",
		    "Input is subject,msg,from and recipient.", ex);
	}
    }

    public boolean sendEmail(Map<String, Object> storable,
	    Map userContext, String user,Map<String, Map<String, Object>> parameters) {
	try {
	    if (storable != null) {
		//ServiceClient client = ServiceClient.getInstance("applicationflow");
	    	
	    	ServiceClientImpl client = ServiceClientImpl.getInstance(domain, "applicationflow");
		Map<String, Map<String,Object>> userProfileMap = client.find(domain, propUtil.getProperty("entity"), parameters);
		/*Map<String, Object> userProfileMap = client.
				.get((String) storable.get("Site"),
					propUtil.getProperty("userprofile") + user,
					userContext);*/
		FormatXml formatXml = FormatXml.getInstanceof(domain);
		Map<String, Object> userProfile = formatXml.in(userProfileMap
			.get("responseStringBody"));

		List<String> recipients = new ArrayList<String>();
		Map<String, Object> coll = (Map<String, Object>) userProfile.get("Collections");
		Map<String, Object> profile =  (Map<String, Object>) coll.get("CmsUserProfile");

		recipients.add((String) profile.get("Email"));
		try {
		    log.debug("before SSLMessage:"
			    + (String) propUtil.getProperty("fromemailid"));
		    sendSSLMessage(recipients, getSubject(storable),
			    getContent(storable, user),
			    (String) propUtil.getProperty("fromemailid"));
		    log.debug("after SSLMessage");
		} catch (GenericException ge) {
		    log.error("In GE: sendSSLMessage failed:" + ge.getMessage());
		    throw ge;
		} catch (Exception ex) {
		    log.error("In Ex: sendSSLMessage failed:" + ex.getMessage());
		    throw new GenericException(domain,
			    "ERR-EMAIL-0002",
			    "Exception occurred at sendSSLException.",
			    "Input is eachentity as map and usercontext as map.",
			    ex);
		}
	    }
	    return true;
	} catch (GenericException ge) {
	    log.error("In GE:" + ge.getMessage());
	    throw ge;
	} catch (Exception ex) {
	    log.error("In EX:" + ex.getMessage());
	    throw new GenericException("ERR-EMAIL-0002",domain,
		    "Exception occurred at sendSSLException.",
		    "Input is eachentity as map and usercontext as map.", ex);
	}
    }

    private String getSubject(Map<String, Object> storable) {
	String subject = "";
	List<String> subjects = getSubjectKeys();
	for (String field : subjects) {
	    if (storable.containsKey(field)) {
		subject = (String) storable.get(field);
		break;
	    }
	}
	if (subject.equals("")) {
	    subject = "Content ";
	}
	String emailSubject = subject + " "
		+ (String) propUtil.getProperty("emailSubjectSuffix");
	return emailSubject;
    }

    public List<String> getSubjectKeys() {
	return Arrays.asList(propUtil.getProperty("subjectKeys").split(","));
    }

    private String getContent(Map storableMap, String userName) {
	Map emailDetails = new HashMap();
	emailDetails.put("user", userName);
	emailDetails.put("id", (String) storableMap.get("Id"));
	emailDetails
		.put("entitytype", (String) storableMap.get("EntityType"));
	emailDetails.put("site", (String) storableMap.get("Site"));
	emailDetails.put("status", (String) storableMap.get("Status"));
	emailDetails.put("createdby", (String) storableMap.get("CreatedBy"));
	emailDetails.put("appurl", (String) propUtil.getProperty("appurl")
		+ (String) storableMap.get("Site"));
	emailDetails.put("appurllink",
		(String) propUtil.getProperty("appurl"));
	return generateEmail("emailTemplate.vm", emailDetails);
    }

    public String generateEmail(String templateName, Map emailDetails)
	    throws GenericException {
	boolean result = false;
	try {
	    VelocityEngine ve = new VelocityEngine();
	    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
	    ve.setProperty("class.resource.loader.class",
		    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	    ve.setProperty("runtime.log.logsystem.class",
		    "org.apache.velocity.runtime.log.NullLogSystem");
	    ve.init();
	    Template t = ve.getTemplate(templateName);
	    VelocityContext context = new VelocityContext();
	    context.put("map", emailDetails);
	    StringWriter writer = new StringWriter();
	    t.merge(context, writer);
	    log.debug("writer:" + writer.toString());
	    return writer.toString();
	} catch (GenericException ex) {
	    throw ex;
	} catch (Exception ex) {
	    log.error("Error while generating Email message:" + ex.getMessage());
	    throw new GenericException(domain,"ERR-EAMIL-0000",
		    "generateMessage failed.", "input:" + "templatName:"
			    + templateName, ex);
	} finally {
	    log.methodExit("generateDRL exit");
	}
    }

}
