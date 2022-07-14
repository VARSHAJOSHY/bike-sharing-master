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
<script src="essentials/js/canvasjs.min.js" type="text/javascript"></script>
<script src="essentials/js/jquery.canvasjs.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="essentials/js/allinonereport.js"></script>
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
				<li class="nav-item d-none d-sm-inline-block"><a
					href="allinonereport.html" class="nav-link"
					style="color: whitesmoke; padding-top: 13%;">All In One Report</a></li>
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


		<div id="tabs" style="font-size: 80%;">
			<ul>
				<li><a href="#tabs-1">Most Active Stations</a></li>
				<li><a href="#tabs-2">Most Active Bikes</a></li>
				<li><a href="#tabs-3">Profit</a></li>
				<li><a href="#tabs-4">Defect Report</a></li>
			</ul>
			<div id="tabs-1">

				<div class="col-lg-6" style="margin-top: 3%;">
					<table style="width: 100%; margin-left: 10%;">
						<tr>
							<td style="text-align: left;">Select Year :</td>
							<td style="text-align: left; width: 8%;">Select Month :</td>
							<td></td>
						</tr>
						<tr>
							<td style="width: 20%;"><select id="year1"
								class="form-control">
									<option value="Select">Select</option>
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>

							</select></td>


							<td style="width: 20%;"><select id="month1"
								class="form-control">
									<option value="Select">Select</option>
									<option value="1">January</option>
									<option value="2">February</option>
									<option value="3">March</option>
									<option value="4">April</option>
									<option value="5">May</option>
									<option value="6">June</option>
									<option value="7">July</option>
									<option value="8">August</option>
									<option value="9">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
							</select></td>

							<td style="width: 22%;"><button type="button"
									class="btn btn-block btn-info" id="generateBtn1"
									style="width: 40%; margin-left: 25%;">Generate</button></td>
						</tr>
					</table>
				</div>

				<div id="station" style="margin-top: 4%; font-size: 60%;">
					<div class="row">
						<div class="col-lg-6">
							<div id="stationBar" style="height: 300px; width: 40%;"></div>
						</div>
						<div class="col-lg-6">
							<div id="stationPie" style="height: 300px; width: 40%;"></div>
						</div>
					</div>
				</div>

			</div>
			<div id="tabs-2">
				<div class="col-lg-6" style="margin-top: 3%;">
					<table style="width: 100%; margin-left: 10%;">
						<tr>
							<td style="text-align: left;">Select Year :</td>
							<td style="text-align: left; width: 8%;">Select Month :</td>
							<td></td>
						</tr>
						<tr>
							<td style="width: 20%;"><select id="year2"
								class="form-control">
									<option value="Select">Select</option>
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>

							</select></td>


							<td style="width: 20%;"><select id="month2"
								class="form-control">
									<option value="Select">Select</option>
									<option value="1">January</option>
									<option value="2">February</option>
									<option value="3">March</option>
									<option value="4">April</option>
									<option value="5">May</option>
									<option value="6">June</option>
									<option value="7">July</option>
									<option value="8">August</option>
									<option value="9">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
							</select></td>

							<td style="width: 22%;"><button type="button"
									class="btn btn-block btn-info" id="generateBtn2"
									style="width: 40%; margin-left: 25%;">Generate</button></td>
						</tr>
					</table>
				</div>
				
				<div id="bike" style="margin-top: 4%; font-size: 60%;">
					<div class="row">
						<div class="col-lg-6">
							<div id="bikeBar" style="height: 300px; width: 40%;"></div>
						</div>
						<div class="col-lg-6">
							<div id="bikePie" style="height: 300px; width: 40%;"></div>
						</div>
					</div>
				</div>
				
				
			</div>
			<div id="tabs-3">
				<div class="col-lg-6" style="margin-top: 3%;">
					<table style="width: 100%; margin-left: 10%;">
						<tr>
							<td style="text-align: left;">Select Year :</td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td style="width: 20%;"><select id="year3"
								class="form-control">
									<option value="Select">Select</option>
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>

							</select></td>

							<td style="width: 22%;"><button type="button"
									class="btn btn-block btn-info" id="generateBtn3"
									style="width: 54%; margin-left: 25%;">Generate</button></td>
						</tr>
					</table>
				</div>

				<div id="profit">
					<div class="row">
						<div class="col-lg-6">
							<div id="profitBar"
								style="height: 300px; width: 40%; margin-top: 4%;"></div>
						</div>
						<div class="col-lg-6">
							<div id="profitPie"
								style="height: 300px; width: 40%;"></div>
						</div>
					</div>
				</div>



			</div>

			<div id="tabs-4">
				<div class="col-lg-6" style="margin-top: 3%;">
					<table style="width: 100%; margin-left: 10%;">
						<tr>
							<td style="text-align: left;">Select Year :</td>
							<td style="text-align: left; width: 8%;">Select Month :</td>
							<td></td>
						</tr>
						<tr>
							<td style="width: 20%;"><select id="year4"
								class="form-control">
									<option value="Select">Select</option>
									<option value="2021">2021</option>
									<option value="2020">2020</option>
									<option value="2019">2019</option>
									<option value="2018">2018</option>
									<option value="2017">2017</option>

							</select></td>


							<td style="width: 20%;"><select id="month4"
								class="form-control">
									<option value="Select">Select</option>
									<option value="1">January</option>
									<option value="2">February</option>
									<option value="3">March</option>
									<option value="4">April</option>
									<option value="5">May</option>
									<option value="6">June</option>
									<option value="7">July</option>
									<option value="8">August</option>
									<option value="9">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
							</select></td>

							<td style="width: 22%;"><button type="button"
									class="btn btn-block btn-info" id="generateBtn4"
									style="width: 40%; margin-left: 25%;">Generate</button></td>
						</tr>
					</table>
				</div>
				
				<div id="defect" style="margin-top: 4%; font-size: 60%;">
					<div class="row">
						<div class="col-lg-6">
							<div id="defectBar" style="height: 300px; width: 40%;"></div>
						</div>
						<div class="col-lg-6">
							<div id="defectPie" style="height: 300px; width: 40%;"></div>
						</div>
					</div>
				</div>
				
				
				
			</div>
		</div>

	</div>
	<footer class="main-footer"
		style="font-size: 80%; position: fixed; height: 35px; background-color: #138496; bottom: 0px; left: 0px; right: 0px; margin-bottom: 0px;">
		<p style="color: whitesmoke;">Copyright &copy; O_LB2_Team4. All
			Rights Reserved.</p>
	</footer>
</body>
</html>
