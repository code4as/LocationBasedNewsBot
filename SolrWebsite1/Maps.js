
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=en&region=GB"></script>

<div id="map" style="height: 400px;"></div>
var map;
var zoomLimit = 6;
var dealer_markers = [{
    sales: '1',
    aftersales: '0',
    name: 'Bob One',
    lat: '51.595212',
    lng: '-2.400068',
    adr: 'Some Address',
    pc: 'In Some Place',
    tel: '21312312312',
    url: '.',
    avr: '',
    position: '1'
},
{
    sales: '0',
    aftersales: '1',
    name: 'Bob Two',
    lat: '52.306323',
    lng: '0.000778',
    adr: 'Some Address',
    pc: 'Some Place',
    tel: '213213123123',
    url: '.',
    avr: '',
    position: '2'
},
{
    sales: '1',
    aftersales: '1',
    name: 'Bob Three',
    lat: '51.742384',
    lng: '-0.488795',
    adr: 'Another address',
    pc: 'More place',
    tel: '23423423423',
    url: '',
    avr: '',
    position: '3'
}];

function _newGoogleMapsMarker(param) {
    var r = new google.maps.Marker({
        map: param._map,
        position: new google.maps.LatLng(param._lat, param._lng),
        title: param._head
    });
    if (param._data) {
        google.maps.event.addListener(r, 'click', function() {
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

function init() {
    var mO = {
        center: new google.maps.LatLng(52.041213, -1.325532),
        zoom: zoomLimit,
        streetViewControl: false,
        mapTypeControl: false,
        navigationControlOptions: {
            style: google.maps.NavigationControlStyle.SMALL
        },
        mapTypeId: google.maps.MapTypeId.HYBRID
    };
    map = new google.maps.Map(document.getElementById("map"), mO);
    for (var a = 0; a < dealer_markers.length; a++) {
        var tmpLat = dealer_markers[a].lat;
        var tmpLng = dealer_markers[a].lng;
        var tmpName = dealer_markers[a].name;
        var tmpAdr = dealer_markers[a].adr;
        var tmpTel = dealer_markers[a].pc;
        var tmpPc = dealer_markers[a].tel;
        var marker = _newGoogleMapsMarker({
            _map: map,
            _lat: tmpLat,
            _lng: tmpLng,
            _head: '|' + new google.maps.LatLng(tmpLat, tmpLng),
            _data: '<div id="bg">\
        <h2 class="title">' + tmpName + '</h2>\
        <h3 class="address">' + tmpAdr + '</h3>\
        <h3 class="pc">' + tmpPc + '</h3>\
        <h3 class="telephone">' + tmpTel + '</h3>\
        </div>'
        });
    }
}

window.onload = init;

