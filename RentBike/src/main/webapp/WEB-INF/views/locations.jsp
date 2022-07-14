<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rent_Ride</title>


<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
}

/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script type="text/javascript" src="essentials/js/jquery-3.5.1.js"></script>


<!-- Async script executes immediately and must be after any DOM elements used in callback. -->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCG0xgUVkBT1YMpGGjnp-QwO-0zDacKUMo&callback=initMap&v=weekly"
	async></script>
<script type="text/javascript" src="essentials/js/locations.js"></script>
<style>
.checked {
	color: orange;
}
</style>

</head>
<body class="hold-transition layout-top-nav">


	<div id="map"></div>


</body>
</html>
