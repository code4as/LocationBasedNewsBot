<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="EN" lang="EN" dir="ltr">
<head profile="http://gmpg.org/xfn/11">
<title>Archives..</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="styles/layout.css" type="text/css" />
 <link rel="stylesheet" type="text/css" href="jquery.autocomplete.css" />
    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script src="jquery.autocomplete.js"></script>   

</head>
<body id="top">
<div class="wrapper">
  <div id="topnav">
    <ul class="nav">
      <li class="active"><a href="home.jsp">Homepage</a></li>
         <%
        
    String s="[]";
    request.setAttribute("maps", s); %>
      <li><a href="maps.jsp">Maps</a></li>
      <li><a href="Heatmaps.jsp">Heat Maps</a></li>
    </ul>
    <div class="clear"></div>
  </div>
</div>
<div class="wrapper">
  <div class="container">
    <div class="whitebox" id="hpage_services">
	<center>
	<form action="EssentialServlet" method="post">
        <fieldset>
          <legend>Site Search</legend>
          <input type="text" id="fn" placeholder="Enter query here&hellip;"  onfocus="this.value=(this.value=='Search Our Website&hellip;')? '' : this.value ;" name="fn"/>
          <div class="spacer">&nbsp;</div>
		  <input type="submit" name="submit" id="submit" value="Search" />
        </fieldset>
      </form>
      
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
 <script>
        $("#fn").autocomplete("list.jsp");
    </script>
</body>
</head>