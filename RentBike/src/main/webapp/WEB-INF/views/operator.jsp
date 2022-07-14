<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rent_Ride</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="essentials/css/googleapi.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="essentials/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="essentials/css/adminlte.min.css">

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="essentials/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="essentials/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="essentials/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="essentials/js/demo.js"></script>
<!--icon usage -->
<script type="module" src="essentials/js/ionicons.esm.js"></script>
<script nomodule src="essentials/js/ionicons.js"></script>

<script src="essentials/js/operator.js"></script>

</head>
<body class="hold-transition layout-top-nav">
	<div class="wrapper">


		<nav
			class="main-header navbar navbar-expand navbar-white navbar-light"
			style="background-color: #138496;height: 68 px;">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item d-none d-sm-inline-block"><a
					href="welcome.jsp" class="nav-link" style="color: whitesmoke;"><img
						class="direct-chat-img" src="essentials/images/logo2.PNG"
						style="height: 50 px; width: 50 px;" /></a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="trackbike.html"
					class="nav-link" style="color: whitesmoke;">Track Bike</a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="changelocation.html"
					class="nav-link" style="color: whitesmoke;">Change Location</a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="fixdefect.html"
					class="nav-link" style="color: whitesmoke;">Fix Defect</a></li>
			</ul>

			<ul class="navbar-nav ml-auto">
				<!-- Navbar Search -->
				<li
					style="color: whitesmoke; font-weight: 100; font-size: 90%; text-align: center; margin-top: 5%;"><span
					id="loginInfo" style="font-weight: 100;"> </span> <br /> <span
					id="loginTimeInfo" style="font-weight: 100;"> </span></li>
				<li class="nav-item d-none d-sm-inline-block"><a
					href="logout.html" class="nav-link" style="color: whitesmoke;"><img
						class="direct-chat-img" src="essentials/images/logout.png"
						style="height: 50 px; width: 50 px;" /></a></li>
			</ul>
		</nav>
	</div>
	<footer class="main-footer"
		style="font-size: 80%; position: fixed; height: 35px; background-color: #138496; bottom: 0px; left: 0px; right: 0px; margin-bottom: 0px;">
		<p style="color: whitesmoke;">Copyright &copy; O_LB2_Team4. All
			Rights Reserved.</p>
	</footer>
	<!-- ./wrapper -->


</body>
</html>
