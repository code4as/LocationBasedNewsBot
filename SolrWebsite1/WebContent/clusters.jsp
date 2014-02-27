<%@page import="java.util.*"%>
<%@page import="folder.Document_Class;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
<title>Cluster map</title>
</head>
<body id="top">
<div><p><b>CLUSTERS</b></p></div>
<% 
if(session.getAttribute("cluster_of_docs")==null)
{
%>
<p>
Please search for some news and click on <b>View Clusters</b> to view the clusters for your query.
<p>
<%
}
else{
Map<String,String[]> hm = new HashMap<String, String[]>();
TreeSet<Document_Class> dc=new TreeSet<Document_Class>();
dc = (TreeSet)session.getAttribute("cluster_of_docs");
System.out.println("dc value "+dc.isEmpty());
HashMap<String,String> hss = new HashMap<String,String>();
Iterator id = dc.iterator();
while(id.hasNext())
{
	Document_Class dco = (Document_Class)id.next();
	hss.put(dco.getId().toString().trim(), dco.getTitle().toString());
}
Document_Class dcobj = dc.first();
hm=dcobj.cluster_map;
Set<String> keys = hm.keySet();
Iterator ik = keys.iterator();
while(ik.hasNext())
{ String label = ik.next().toString();
    out.println("<p><b><u>"+label+"</b></u></p>");
	String[] sa=hm.get(label);
	for(String s:sa)
	{if(hss.get(s.trim())!=null)
		out.println("<p>"+hss.get(s.trim())+"</p>");
	}
}

}

%>


<div class="wrapper">
</div>
</body>
</html>