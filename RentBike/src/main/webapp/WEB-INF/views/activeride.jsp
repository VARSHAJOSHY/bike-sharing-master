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

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script type="text/javascript" src="essentials/js/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="essentials/js/jquery.star-rating-svg.min.js"></script>
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
<script type="text/javascript" src="essentials/js/activeride.js"></script>

<style>

#dialog .ui-button-text {
    font-size: 80%; /* Or whatever smaller value works for you. */
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
					href="welcome.html" class="nav-link" style="color: whitesmoke;"><img
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
		<p id="successMsg"
			style="text-align: center; color: #17a2b8; font-size: 105%; margin-top: 5%;"></p>

		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-7" style="margin-top: 3%; margin-left: 20%;">
						<table style="width: 100%;">
							<tr>
								<td>Ride ID:</td>
								<td><input id="rideId" disabled="disabled"></td>
								<td>Bike ID:</td>
								<td><input id="bikeId" disabled="disabled"></td>
								<td><button id="rideBtn" type="button"
										class="btn btn-block btn-info" disabled
										style="font-size: 90%;">IN PROGRESS</button></td>
							</tr>
							<tr>
								<td>Bike Model:</td>
								<td><input id="model" disabled="disabled"></td>
								<td>Start Location:</td>
								<td><input id="sLoc" disabled="disabled"></td>
								<td><button type="button" class="btn btn-block btn-info"
										id="stopBtn" style="font-size: 90%;">STOP</button></td>
							</tr>
						</table>
					</div>
				</div>

				<div class="row" style="margin-top: 2%;" id="destinationDiv">
					<div class="col-lg-6" style="margin-top: 3%; margin-left: 20%;">
						<table style="width: 100%;">
							<tr>
								<td style="padding-left: 15%;">Please Select Your
									Destination :</td>

								<td style="width: 30%"><select id="destStationId"
									class="form-control" style="font-size: 90%;">

								</select></td>

								<td><button type="button" class="btn btn-block btn-info"
										id="goBtn" style="font-size: 90%;">GO</button></td>
							</tr>
						</table>
					</div>
				</div>

				<div id="dialog-form" title="Payment Page">
					<form>
						<fieldset>
							<table style="width: 85%; margin-left: 7%; font-size: 70%;">

								<tr>
									<td colspan="2"><label>CARD NUMBER</label></td>
									<td colspan="2"><label>CARD VENDOR</label></td>
								</tr>
								<tr>
									<td colspan="2"><input type="text" class="form-control"
										placeholder="XXXX XXXX XXXX 1234" disabled=""></td>
									<td colspan="2"><img src="essentials/images/cardimg.png" /></td>
								</tr>
								<tr>
									<td><label>EXP-DATE</label></td>
									<td><label>CVV</label></td>
									<td colspan="2"><label>CARDHOLDER NAME</label></td>
								</tr>
								<tr>
									<td><input type="text" class="form-control"
										placeholder="Aug 2017" disabled=""></td>

									<td><input type="text" class="form-control"
										placeholder="007" disabled=""></td>
									<td colspan="2"><input type="text" class="form-control"
										placeholder="Sam Issac" disabled=""></td>
								</tr>
							</table>
							<div>
								<table>

									<tr>
										<td style="padding-left: 43%;" width="50%"><img alt=""
											src="essentials/images/poundimg.png" style="width: 97%;"></td>
										<td width="50%"
											style="padding-top: 5%; font-size: 169%; color: blue; font-weight: bolder;"><p
												id="cost"></p></td>
									</tr>
									<tr>
										<td colspan="2">Cost Per hour: £1</td>
									</tr>
								</table>
							</div>
						</fieldset>
					</form>
				</div>


				<div id="rate-dialog-form" title="Leave us a review">
					<form>
						<fieldset>
							<table style="width: 85%; margin-left: 7%; font-size: 70%;">
								<tr>
									<td colspan="2"><div class="my-rating jq-stars"
											id="reviewStar" style="margin-left: 45%;"></div></td>
								</tr>
								<tr>
									<td><label for="name">Comments</label></td>
									<td><textarea id="issueDesc" cols="51" rows="5"></textarea></td>

								</tr>

							</table>
						</fieldset>
					</form>
				</div>
			</div>
		</section>
	</div>
	<input type="hidden" id="reqId">
	<input type="hidden" id="starMark">
	<footer class="main-footer"
		style="font-size: 80%; position: fixed; height: 35px; background-color: #138496; bottom: 0px; left: 0px; right: 0px; margin-bottom: 0px;">
		<p style="color: whitesmoke;">Copyright &copy; O_LB2_Team4. All
			Rights Reserved.</p>
	</footer>
</body>
</html>