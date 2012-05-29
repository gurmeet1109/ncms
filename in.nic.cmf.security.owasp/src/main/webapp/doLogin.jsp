<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="in.nic.cmf.security.owasp.Injection,in.nic.cmf.security.owasp.CrossSiteScripting,in.nic.cmf.security.owasp.BrokenAuthenticationSessionManagement"%>
<%@ page import="org.owasp.validator.html.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="xss"
		class="in.nic.cmf.security.owasp.CrossSiteScripting" />
	<%
		//UITest for injection

		String userName = "sify";
		String password = "sify123";
		String sUserID = request.getParameter("sUserName");
		String sPassword = request.getParameter("sPwd");
		
		if (sUserID.equals(userName) && sPassword.equals(password)) {
			String message = "User login successfully";
			out.println(message);
		} else {
			out.println("Invalid usename AND password");
		}
		out.println("<br>");

		
		
		//UITest for cross site scripting
		
		
		String actual = 
    		  "<script>test</script><p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
    		  
       // String actual1 =  "<p><a href='http://sify.com/' onclick='stealCookies()'><script>alert(hi)</script>Link</a></p>";	
        
        //out.println(actual1);
    		  
    	String expected = "<p><a href=\"http://example.com/\" rel=\"nofollow\">Link</a></p>";
    	
    	//String expected1 = "<p><a href=\"http://sify.com/\" rel=\"nofollow\">Link</a></p>";
    	
		String unsafe = request.getParameter( "url" );
		String safe = CrossSiteScripting.getJsCleanHTML(unsafe);		
		

		if (safe.equals(expected)) {  
		 out.println("Valid url");
		 out.println(safe);
		 }
		else{
			out.println("Invalid url");
		}
	%>

</body>
</html>