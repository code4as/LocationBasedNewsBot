<%@ page language="java" import="java.util.*,folder.*;" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Archives..</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery-1.4.1.min.js"></script>
</head>
<body id="top">
<div class="wrapper">
  <div id="topnav">
    <ul class="nav">
      <li class="active"><a href="home.jsp">Homepage</a></li>
      <li><a href="maps.jsp">Maps</a></li>
       <li><a href="Heatmaps.jsp">Heat Maps</a></li>
    </ul>
    <div class="clear"></div>
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div class="container">
    <div class="whitebox" id="hpage_services">
	<center>
	<br><br><br>
	<p><center> <a href ="facet.jsp" target="facet">View Facets</a></center></p>
	<p><center> <a href ="clusters.jsp" target="cluster">View Clusters</a></center></p>
	<p><center> <a href ="home.jsp">Back</a></center></p>
	
	<hr>
	<br>
	
<% 



//ArrayList<Document_Class> to_display = new ArrayList<Document_Class>();
TreeSet<Document_Class> to_display= ( TreeSet< Document_Class> ) request.getAttribute("message"); 
//String s=(String) request.getAttribute("message");
 Iterator<Document_Class> itr = to_display.iterator();
while(itr.hasNext())
{   Document_Class dc = (Document_Class) itr.next();
	
     request.setAttribute("document_class", dc);
     request.setAttribute("content", dc.getContent());
 	String myEncodedString = java.net.URLEncoder.encode(dc.getContent());
 	request.setAttribute("content", myEncodedString);
    // out.println("<p><center>" + "<a href = \"blank_initial.jsp\" target=\"content\">  " + dc.getTitle() + "</a>" + "</center></p>" + "<p><center>" + dc.getDate() + "</center></p>" + "<p><center>" + dc.getHighLightedContent() + "<a href = \"display.jsp\" target=\"content\"> See More.. " + "</a>" + "</center></p>" + "<br><br><br>");
 %>   
  <p><center> <a href ="blank_initial.jsp?content=<%= myEncodedString%>" target="content"> <%=dc.getTitle() %></a></center></p>
   <p><center> <%=dc.getDate() %></center></p>
   <p><center> <%=dc.getHighLightedContent() %></center></p>
   <br><br>
    
<% 

} 

   

%>
 <% String s= (String) request.getAttribute("maps_initial");
 System.out.println("value in map is : " + s);
    session.setAttribute("initial", s);
    session.setAttribute("heat_map", s);
    //System.out.println("set value is : " + (String) session.getAttribute("initial"));
 %>

	 </center>
    </div>
  </div>
</div>


    <!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="copyright">
    <p class="fl_left">Copyright &copy; 2013 - All Rights Reserved - <a href="#">VJSA</a></p>
  </div>
</div>
</body>
</head>