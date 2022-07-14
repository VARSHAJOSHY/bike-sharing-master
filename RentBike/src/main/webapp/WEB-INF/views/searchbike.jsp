<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rent_Ride</title>

<link rel="stylesheet" type="text/css"
	href="essentials/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="essentials/css/dataTables.jqueryui.min.css" />
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" type="text/css"
	href="essentials/css/googleapi.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet" type="text/css" href="essentials/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" type="text/css"
	href="essentials/css/adminlte.min.css">
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<style>
.ui-dialog-buttonset {
	font-size: 80%;
}
</style>

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script type="text/javascript" src="essentials/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="essentials/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="essentials/js/dataTables.jqueryui.min.js"></script>
<script type="text/javascript"
	src="essentials/js/jquery.dataTables.min.js"></script>

<!-- AdminLTE App -->
<script type="text/javascript" src="essentials/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script type="text/javascript" src="essentials/js/demo.js"></script>
<!--icon usage -->
<script type="module" src="essentials/js/ionicons.esm.js"></script>
<script nomodule src="essentials/js/ionicons.js"></script>
<script type="text/javascript" src="essentials/js/searchbike.js"></script>
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
					style="padding-top: 3%;"><a href="searchbike.html"
					class="nav-link" style="color: whitesmoke;">Rent Bike</a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="activeride.html"
					class="nav-link" style="color: whitesmoke;">My Active Ride</a></li>
				<li class="nav-item d-none d-sm-inline-block"
					style="padding-top: 3%;"><a href="allrides.html"
					class="nav-link" style="color: whitesmoke;">My Rides</a></li>
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

	<div id="body" style="font-size: 90%;">
		<br />

		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-1" style="padding-top: 1%;">Enter Zipcode
						:</div>
					<div class="col-lg-3">
						<div class="input-group">
							<input type="search" class="form-control form-control-lg"
								placeholder="ZipCode" style="font-size: 100%;" id="zipcode">
							<div class="input-group-append">
								<button type="button" class="btn btn-lg btn-default"
									id="searchStid">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="stationDiv"
				style="margin-top: 6%; width: 60%; margin-left: 20%;">
				<table id="stationTable" border="0" class="cell-border hover stripe"
					cellpadding="0" cellspacing="0" style="width: 100%;">
					<thead>
						<tr>
							<th width="15%">Station ID</th>
							<th width="20%">Station Name</th>
							<th>Address</th>
							<th width="15%">Zipcode</th>
						</tr>
					</thead>
				</table>
			</div>
			<div id="bikeDiv"
				style="margin-top: 6%; width: 60%; margin-left: 20%;">
				<table id="bikeTable" border="0" class="cell-border hover stripe"
					cellpadding="0" cellspacing="0" style="width: 100%;">
					<thead>
						<tr>
							<th>Bike ID</th>
							<th>Bike Number</th>
							<th>Bike Model</th>
							<th>Specifications</th>
						</tr>
					</thead>
				</table>
			</div>

			<div>
				<p id="msgId"
					style="text-align: center; color: #17a2b8; font-size: 105%; margin-top: 5%;"></p>
			</div>

			<div id="dialog-confirm" title="Confirmation" style="font-size: 80%;">
				<p>
					<span class="ui-icon ui-icon-alert"
						style="float: left; margin: 12px 12px 20px 0;"></span>Do you want
					to start a ride with us?
				</p>
			</div>

			<div id="defect_dialog-confirm" title="Reporting Defect"
				style="font-size: 80%;">
				<p>
					<label for="name">Issue Description (max 100 words)</label>
					<textarea id="issueDesc" cols="51" rows="5"></textarea>

				</p>
			</div>

		</section>
	</div>

	<footer class="main-footer"
		style="font-size: 80%; position: fixed; height: 35px; background-color: #138496; bottom: 0px; left: 0px; right: 0px; margin-bottom: 0px;">
		<p style="color: whitesmoke;">Copyright &copy; O_LB2_Team4. All
			Rights Reserved.</p>
	</footer>
	<input type="hidden" id="selectBikeId">
	<input type="hidden" id="selectStationId">
</body>
</html>