<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    <style type="text/css">
      html { height: 100% }
      body { height: 100%; margin: 0; padding: 0 }
      #map_canvas { height: 100% }
    </style>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBdTuWJjEUMvQZ6VVPGksE12XNgQgs__Qk&sensor=true">
    </script>
	<script src="http://maps.googleapis.com/maps/api/js?libraries=visualization&sensor=true"></script>
    <script type="text/javascript">
	var heatMapData = new Array();
	<% String new_value=(String) session.getAttribute("initial"); %>
	var article = <%=new_value%> ;
	function initialize() {
		var mapOptions = {
	    zoom: 3,
	    center: new google.maps.LatLng(8.881928, 76.592758),
	    mapTypeId: google.maps.MapTypeId.ROADMAP
		}
		var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
		
		for (var a = 0; a < article.length; a++) {		
			for (var i = 0; i < article[a].location_string.length; i++) {
	        var index=a+i;
	        var coords = article[a].coords[i];
			var res = coords.split(",");
			var tmpLat = res[0];
			var tmpLng = res[1];
			heatMapData[index] = new google.maps.LatLng(tmpLat, tmpLng);
		}
		}
		var heatmap = new google.maps.visualization.HeatmapLayer({
	       data: heatMapData
		});
		heatmap.setOptions({radius: heatmap.get('30')});
		heatmap.setMap(map);
		}
 </script>
  </head>
  <body onload="initialize()">
    <div id="map_canvas" style="width:100%; height:100%"></div>
  </body>
</html>