<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>SolrWebsite1</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <% String new_value=(String) session.getAttribute("initial"); %>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script>
//alert('hi');
function initialize() {
  var myLatlng = new google.maps.LatLng(40.4169,-3.7036);
  var mapOptions = {
    zoom: 2,
    center: myLatlng
  };
  var article = <%=new_value%> ;
  //alert(article);
  //document.write("----------------------------------------------------------------------------");
  //document.write(article);
  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

  for (var a = 0; a < article.length; a++) {
        var title = article[a].title;
		var date = article[a].date;
        var content = article[a].content;
		for (var i = 0; i < article[a].location_string.length; i++) {
        var loaction = article[a].location[i];
        var coords = article[a].coords[i];
		var res = coords.split(",");
		var tmpLat = res[0];
		var tmpLng = res[1];
		var myLatlng = new google.maps.LatLng(tmpLat, tmpLng);
		var marker = _newGoogleMapsMarker({
            _map: map,
            _lat: tmpLat,
            _lng: tmpLng,
            _title: title,
            _data: '<div id="bg">\
        <h2 class="title">' + title + '</h2>\
        <h3 class="address">' + date + '</h3>\
        <h3 class="pc">' + content + '</h3>\
        </div>'
        });
    }
	}
}
  function _newGoogleMapsMarker(param) {
    var r = new google.maps.Marker({
        map: param._map,
        position: new google.maps.LatLng(param._lat, param._lng),
        title: param._title
    });
    if (param._data) {
        google.maps.event.addListener(r, 'click', function () {
            // this -> the marker on which the onclick event is being attached
            if (!this.getMap()._infoWindow) {
                this.getMap()._infoWindow = new google.maps.InfoWindow();
            }
            this.getMap()._infoWindow.close();
            this.getMap()._infoWindow.setContent(param._data);
            this.getMap()._infoWindow.open(this.getMap(), this);
        });
    }
    return r;
}
google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>