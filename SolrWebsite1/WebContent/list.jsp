<%@ page import="folder.SolrQuery2,java.util.*;" language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%      System.out.println("here in list jsp.");
        ArrayList<String> to_display = null;
        String set;
		String s1=(String)request.getParameter("q");
		System.out.println(s1);
		to_display = SolrQuery2.query(s1);
		Iterator<String> iterator = to_display.iterator();
		out.println("<b>");
	    while(iterator.hasNext()) {
	        String entry = (String)iterator.next();
	        out.println(entry);
	    }
		out.println("</b>");
%>
</body>
</html>