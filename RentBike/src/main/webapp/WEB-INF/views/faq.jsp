<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register here</title>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="essentials/css/googleapi.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="essentials/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="essentials/css/adminlte.min.css">

<!-- jQuery -->
<script src="essentials/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="essentials/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="essentials/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="essentials/js/demo.js"></script>
</head>
<body class="hold-transition layout-top-nav">
	<!-- Site wrapper -->
	<div class="wrapper">
		<!-- Navbar -->
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
			<ul id = "register" class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
				<a href="register.html" class="btn btn-primary"
					style="background-color: whitesmoke; color: black; border-color: whitesmoke;">Sign
					Up</a>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>FAQ</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="welcom.jsp">Home</a></li>
								<li class="breadcrumb-item active">FAQ</li>
							</ol>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12" id="accordion">
						<div class="card card-primary card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseOne">
								<div class="card-header">
									<h4 class="card-title w-100">1. Registration and login
										issues</h4>
								</div>
							</a>
							<div id="collapseOne" class="collapse show"
								data-parent="#accordion">
								<div class="card-body">We offer three pages for
									registration, corresponding to three roles - customer, manager
									and operator.</div>
							</div>
						</div>
						<div class="card card-primary card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseTwo">
								<div class="card-header">
									<h4 class="card-title w-100">2. How to rent a bike</h4>
								</div>
							</a>
							<div id="collapseTwo" class="collapse" data-parent="#accordion">
								<div class="card-body">It's simple! Just register with us as a customer, log in from your user and click on Rent Bike. Just choose the station and bike and you are ready to go!</div>
							</div>
						</div>
						<div class="card card-primary card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseThree">
								<div class="card-header">
									<h4 class="card-title w-100">3. Problems about reporting
										bikes</h4>
								</div>
							</a>
							<div id="collapseThree" class="collapse" data-parent="#accordion">
								<div class="card-body">You can report a defective bike to us. Just go to active bikes, select your station and bike and click on Report Defect button.</div>
							</div>
						</div>
						<div class="card card-warning card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseFour">
								<div class="card-header">
									<h4 class="card-title w-100">4. Problems about payment</h4>
								</div>
							</a>
							<div id="collapseFour" class="collapse" data-parent="#accordion">
								<div class="card-body">You can pay at the station itself. Otherwise Please email us to admin@rent&Ride.com if you have any payment issues.</div>
							</div>
						</div>
						<div class="card card-warning card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseFive">
								<div class="card-header">
									<h4 class="card-title w-100">5. Problems about login.</h4>
								</div>
							</a>
							<div id="collapseFive" class="collapse" data-parent="#accordion">
								<div class="card-body">If you are already registered and facing issues, please email us to admin@rent&Ride.com
								</div>
							</div>
						</div>
						<div class="card card-warning card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseSix">
								<div class="card-header">
									<h4 class="card-title w-100">6. Problems about repairs.</h4>
								</div>
							</a>
							<div id="collapseSix" class="collapse" data-parent="#accordion">
								<div class="card-body">You can report a defect to us via the web application. Just go to rent bike and choose the station and bike.</div>
							</div>
						</div>
						<div class="card card-danger card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseSeven">
								<div class="card-header">
									<h4 class="card-title w-100">7. Questions about refunds</h4>
								</div>
							</a>
							<div id="collapseSeven" class="collapse" data-parent="#accordion">
								<div class="card-body">If you think you are charged more, Please email us to admin@rent&Ride.com
								</div>
							</div>
						</div>
						<div class="card card-danger card-outline">
							<a class="d-block w-100" data-toggle="collapse"
								href="#collapseEight">
								<div class="card-header">
									<h4 class="card-title w-100">8. How to cancel your account
									</h4>
								</div>
							</a>
							<div id="collapseEight" class="collapse" data-parent="#accordion">
								<div class="card-body">Please email us to admin@rent&Ride.com with your email ID</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12 mt-3 text-center">
						<p class="lead">
							<a href="contact-us.html">Contact us</a>, if you could not find the
							right anwser or you have a other question?<br />
						</p>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer"
			style="font-size: 80%; position: fixed; height: 35px; background-color: #138496; bottom: 0px; left: 0px; right: 0px; margin-bottom: 0px;">
			<p style="color: whitesmoke;">Copyright &copy; O_LB2_Team4. All
				Rights Reserved.</p>
		</footer>
		<!-- ./wrapper -->


		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Control sidebar content goes here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->
	<script type="text/javascript">
		if (sessionStorage.getItem('emailId')
				|| sessionStorage.getItem('emailId') != undefined) {
			document.getElementById('register').style.display = 'none'
		}
	</script>
</body>
</html>