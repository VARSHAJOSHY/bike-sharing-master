<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="essentials/css/googleapi.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="essentials/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="essentials/css/adminlte.min.css">
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="welcome.html"><b>Rent & Ride</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>

				<form>
					<div class="input-group mb-3">
						<input id="emailId" type="email" class="form-control"
							placeholder="Email">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input id="password" type="password" class="form-control"
							placeholder="Password">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<!-- <div class="icheck-primary">
              <input type="checkbox" id="remember">
              <label for="remember">
                Remember Me
              </label>
            </div> -->
						</div>
						<!-- /.col -->
						<div class="col-4" style="color: #138496">
							<button id="login" type="button"
								class="btn btn-primary btn-block"
								style="background-color: #138496; border-color: #138496;">Sign
								In</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<!--<div class="social-auth-links text-center mb-3">
        <p>- OR -</p>
        <a href="#" class="btn btn-block btn-primary">
          <i class="fab fa-facebook mr-2"></i> Sign in using Facebook
        </a>
        <a href="#" class="btn btn-block btn-danger">
          <i class="fab fa-google-plus mr-2"></i> Sign in using Google+
        </a>
      </div>-->
				<!-- /.social-auth-links -->

				<!-- <p class="mb-1">
        <a href="forgot-password.html">I forgot my password</a>
      </p>-->
				<p class="mb-0">
					<a href="register.html" class="text-center" style="color: gray;">Register
						as a new customer</a>
				</p>
				<p class="mb-0">
					<a href="operRegister.html" class="text-center" style="color: gray;">Register
						as an operator</a>
				</p>
				<p class="mb-0">
					<a href="mangRegister.html" class="text-center" style="color: gray;">Register
						as a manager</a>
				</p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="essentials/js/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="essentials/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="essentials/js/adminlte.min.js"></script>
	<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>

	<script src="essentials/js/login.js"></script>
</body>
</html>