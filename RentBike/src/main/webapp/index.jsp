<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rent_Ride</title>

<link rel="stylesheet" type="text/css"
	href="essentials/css/star-rating-svg.css">
<link rel="stylesheet" type="text/css"
	href="essentials/css/jquery-ui.css" />
<!-- Theme style -->
<link rel="stylesheet" type="text/css"
	href="essentials/css/adminlte.min.css">

<style>
    /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
    #map {
        height: 100%;
    }

    /* Optional: Makes the sample page fill the window. */
    html,
    body {
        height: 100%;
        margin: 0;
        padding: 0;
    }
</style>

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script type="text/javascript" src="essentials/js/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="essentials/js/jquery.star-rating-svg.min.js"></script>
<!-- AdminLTE App -->
<script type="text/javascript" src="essentials/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script type="text/javascript" src="essentials/js/demo.js"></script>
<!--icon usage -->
<script type="module" src="essentials/js/ionicons.esm.js"></script>
<script nomodule src="essentials/js/ionicons.js"></script>
<script type="text/javascript" src="essentials/js/index.js"></script>

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
	<div class="wrapper">
		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light"
			style="background-color: #138496; height: 68 px;">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item d-none d-sm-inline-block"><a
					href="welcome.jsp" class="nav-link" style="color: whitesmoke;"><img
						class="direct-chat-img" src="essentials/images/logo2.PNG"
						style="height: 50 px; width: 50 px;" /></a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="welcome.jsp"
					class="nav-link" style="color: whitesmoke;">Welcome</a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="login1.html"
					class="nav-link" style="color: whitesmoke;">Login</a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="faq.html" class="nav-link"
					style="color: whitesmoke;">How It Works</a></li>
			</ul>

			<!-- Right navbar links -->
			<ul id="register"
				class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
				<a href="register.html" class="btn btn-primary"
					style="background-color: whitesmoke; color: black; border-color: whitesmoke;">Sign
					Up</a>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main content -->
		<div class="content">
			<!-- Container -->
			<div class="container">
				<div class="row">
					<div class="col-lg-3" style="color: gray;">
						<p class="card-text" style="text-align: center; padding-top: 20%;">
							<b style="color: gray;">Happy Customer...Happy Us...</b>
						</p>
						<div class="row">
							<div class="col-lg-12">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">
											<label id="custName1"
												style="font-weight: 400; font-size: 90%;"></label>
										</h5>

										<div class="my-rating jq-stars" id="star1"
											style="padding-left: 4%;"></div>

										<p class="card-text" id="review1"
											style="font-size: 90%; margin-top: 5%;"></p>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-12">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">
											<label id="custName2"
												style="font-weight: 400; font-size: 90%;"></label>
										</h5>
										<div class="my-rating jq-stars" id="star2"
											style="padding-left: 4%;"></div>
										<p class="card-text" id="review2"
											style="font-size: 90%; margin-top: 5%;"></p>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-12">
								<div class="card">
									<div class="card-body">
										<h5 class="card-title">
											<label id="custName3"
												style="font-weight: 400; font-size: 90%;"></label>
										</h5>
										<div class="my-rating jq-stars" id="star3"
											style="padding-left: 4%;"></div>
										<p class="card-text" id="review3"
											style="font-size: 90%; margin-top: 5%;"></p>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-9" style="padding-top: 5%;">
						<div id="map"></div>

					</div>
				</div>
			</div>
			<!-- ./Container -->
		</div>
		<!-- ./Main content -->
	</div>
	<!-- ./wrapper -->

	<!-- Main Footer -->
	<footer class="main-footer"
		style="font-size: 80%; position: fixed; height: 35px; background-color: #138496; bottom: 0px; left: 0px; right: 0px; margin-bottom: 0px;">
		<p style="color: whitesmoke;">Copyright &copy; O_LB2_Team4. All
			Rights Reserved.</p>
	</footer>
	<!-- ./Main Footer -->
	<script type="text/javascript">
		if (sessionStorage.getItem('emailId')
				|| sessionStorage.getItem('emailId') != undefined) {
			document.getElementById('register').style.display = 'none'
		}
	</script>
</body>
</html>
