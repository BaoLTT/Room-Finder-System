// maps.js

var map;
var marker;

function initMap() {
    var latitude = parseFloat(document.getElementById('latitude').value);
    var longitude = parseFloat(document.getElementById('longitude').value);

    // Tạo map với vị trí ban đầu
    map = new google.maps.Map(document.getElementById('map-item'), {
        // center: { lat: latitude, lng: longitude },
        center: {lat: 21.009642384941973, lng:105.53736090660095},
        zoom: 16
    });


    //marker ban dau
    marker = new google.maps.Marker({
        position:{lat: 21.009642384941973, lng:105.53736090660095},
        map: map
    });







}
