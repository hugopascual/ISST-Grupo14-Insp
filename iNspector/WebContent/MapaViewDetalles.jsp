<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<head>
    <meta charset="utf-8" />
    <title>Add a marker using a place name</title>
    <meta name="viewport" content="initial-scale=1,maximum-scale=1,user-scalable=no" />
    <script src="https://api.mapbox.com/mapbox-gl-js/v1.9.1/mapbox-gl.js"></script>
    <link href="https://api.mapbox.com/mapbox-gl-js/v1.9.1/mapbox-gl.css" rel="stylesheet" />
    <script src="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.4.2/mapbox-gl-geocoder.min.js"></script>
    <link
            rel="stylesheet"
            href="https://api.mapbox.com/mapbox-gl-js/plugins/mapbox-gl-geocoder/v4.4.2/mapbox-gl-geocoder.css"
            type="text/css"
    />
    <style>
        body { margin: 0; padding: 0; }
        #map { position: absolute; top: 0; bottom: 0; width: 50%; }
    </style>
</head>
<body>
<div id="map"></div>
<script src="https://unpkg.com/es6-promise@4.2.4/dist/es6-promise.auto.min.js"></script>
<script src="https://unpkg.com/@mapbox/mapbox-sdk/umd/mapbox-sdk.min.js"></script>


<script>

    mapboxgl.accessToken = 'pk.eyJ1IjoicGF0cmlja2xhbiIsImEiOiJjazkzNmY2cjUwMXkxM2hxbm9qNW80OHI5In0.IKHP7SaRBzaau_80UF7pLg';

    var mymap = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [-3.7025600, 40.4165000], // [Longitud,Latitud]
        zoom: 13
    });

    mymap.addControl(
        new MapboxGeocoder({
            accessToken: mapboxgl.accessToken,
            mapboxgl: mapboxgl
        })
    );

    var mapboxClient = mapboxSdk({ accessToken: mapboxgl.accessToken });

    mapboxClient.geocoding
        .forwardGeocode({
            query: '${establecimiento.direccion},28047,${establecimiento.ciudad}',
            autocomplete: false,
            limit: 1
        })
        .send()
        .then(function(response) {
            if (
                response &&
                response.body &&
                response.body.features &&
                response.body.features.length
            ) {
                var feature = response.body.features[0];
                
                var popup = new mapboxgl.Popup({ offset: 25 }).setText(
                		'${establecimiento.nombre}' + '${establecimiento.direccion}'
                		);
                
                var map = new mapboxgl.Map({
                    container: 'map',
                    style: 'mapbox://styles/mapbox/streets-v11',
                    center: feature.center, // [Longitud,Latitud]
                    zoom: 13
                });


                new mapboxgl.Marker().setLngLat(feature.center)
                					 .setPopup(popup)
                					 .addTo(map);
                console.log(feature.center);
            }
        });

</script>

</body>
</html>