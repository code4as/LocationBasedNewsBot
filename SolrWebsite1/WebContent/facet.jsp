<%@ page import="java.util.*;" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
<title>Insert title here</title>
</head>
<body id="top">
<div><p><b>FACETS</b></p></div>
<div class="wrapper">

<% 
if(session.getAttribute("facet_array")==null)
{
%>
<p>
Please search for some news and click on <b>View Facets</b> to view the location facets for your query.
<p>
<%
}
else {
%>
<% 

ArrayList<String> a= (ArrayList<String>) session.getAttribute("facet_array");
ListIterator<String> itr =a.listIterator();
while(itr.hasNext())
{
	out.println("<p>"+itr.next().toString().toUpperCase() + " </p>");
	
}}
//session.setAttribute("facet_array", null);
%>
</div>
</body>
</html>