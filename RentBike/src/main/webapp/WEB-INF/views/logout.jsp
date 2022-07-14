<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Register here</title><!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="essentials/css/googleapi.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="essentials/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="essentials/css/adminlte.min.css">
</head>
<body class="hold-transition layout-top-nav">
<!-- Site wrapper -->
<div class="wrapper">
  <!-- Navbar -->
		<nav
			class="main-header navbar navbar-expand-md navbar-light navbar-white">
			<div class="container">
				
  			<div class="register-logo">
   				 <a href="welcome.html"><b>Rent & Ride</b></a>
 			</div>

				<button class="navbar-toggler order-1" type="button"
					data-toggle="collapse" data-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse order-3" id="navbarCollapse">
					<!-- Left navbar links -->
					<ul class="navbar-nav">
						<li class="nav-item"><a href="welcome.html" class="nav-link">Welcome</a>
						</li>
						<li class="nav-item"><a href="login1.html" class="nav-link">Login</a></li>
						<li class="nav-item"><a href="locations.html" class="nav-link">Locations</a>
						</li>
						<li class="nav-item"><a href="#" class="nav-link">How It
								Works</a></li>
					</ul>
				</div>

				<!-- Right navbar links -->
				<ul id = "register" class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
					<a href="register.html" class="btn btn-primary">Sign Up</a>
				</ul>
			</div>
		</nav>
		<!-- /.navbar -->





  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="welcome.jsp">Home</a></li>
              <li class="breadcrumb-item active">Logout</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <section class="content">
      <div class="error-page">
        <h2 class="headline text-warning"> You are logged out. </h2>

        <div class="error-content">

          <h3>
            <i class="fas fa-exclamation-triangle text-warning"></i>You can now safely leave.
           </h3>
           <h3>
            Meanwhile, you may want to 
          </h3>
          <h3>
            <a href="welcome.jsp">return to home page</a>
          </h3>
          <h3>
            or <a href="login1.html">try to login again</a>.
          </h3>

        </div>
        <!-- /.logout-content -->
      </div>
      <!-- /.logout-page -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
		<footer class="main-footer">

			<!-- To the right -->
			<div class="float-right d-none d-sm-inline">
				Follow Us <br />
				<ion-icon name="logo-facebook"></ion-icon>
				<ion-icon name="logo-instagram"></ion-icon>
				<ion-icon name="logo-twitter"></ion-icon>
				<ion-icon name="logo-youtube"></ion-icon>
			</div>

			<div class="row">
				<div class="col-lg-2"></div>
				<div class="col-lg-2"></div>
				<div class="col-lg-2">
					About Us
					<p class="card-text" style="margin-top: 8%;">A bicycle, also
						called a bike or cycle, is a human-powered or motor-powered,
						pedal-driven, single-track vehicle, having two</p>
				</div>

				<div class="col-lg-2">
					Contact Us
					<p class="card-text" style="margin-top: 8%;">A bicycle, also
						called a bike or cycle, is a human-powered or motor-powered,
						pedal-driven, single-track vehicle, having two</p>
				</div>

				<div class="col-lg-2">
					Legal
					<p class="card-text" style="margin-top: 8%;">A bicycle, also
						called a bike or cycle, is a human-powered or motor-powered,
						pedal-driven, single-track vehicle, having two</p>
				</div>
			</div>


		</footer>
  

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<script type="text/javascript">
if(sessionStorage.getItem('emailId')||sessionStorage.getItem('emailId') != undefined){
	sessionStorage.removeItem('emailId') 
	sessionStorage.removeItem('role') 
}
</script>
<!-- jQuery -->
<script src="essentials/js/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="essentials/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="essentials/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="essentials/js/demo.js"></script>
</body>
</html>