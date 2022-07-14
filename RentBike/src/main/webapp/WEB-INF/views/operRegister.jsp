<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset = UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Operator Register here</title>
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="essentials/css/googleapi.css">
<!-- Font Awesome Icons -->
<link rel="stylesheet" href="essentials/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="essentials/css/adminlte.min.css">
</head>
<body class="hold-transition register-page">
	<div class="register-box">
		<div class="register-logo">
			<a href="welcome.jsp"><b>Rent & Ride</b></a>
		</div>

		<div class="card">
			<div class="card-body register-card-body">
				<p class="login-box-msg">Operator Registration</p>

				<form>
					<div class="input-group mb-3">
						<input type="text" id="firstName" class="form-control"
							placeholder="First name">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="text" id="lastName" class="form-control"
							placeholder="Last name">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
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
						<input id="ph_no" type="text" class="form-control"
							placeholder="Phone Number">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-phone"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<input id="dob" type="text" class="form-control"
							placeholder="Birthday">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-birthday-cake"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3" style="display: none;">
						<input id="role" type="text" class="form-control" value="O">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
					</div>
					<div class="input-group mb-3">
						<select id="gender" type="text" class="form-control"
							placeholder="Gender">
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-user"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<a href="login1.html" class="text-center" style="color: gray;">I already have an
								account</a>
							<!-- <div class="icheck-primary">
              <input type="checkbox" id="agreeTerms" name="terms" value="agree">
              <label for="agreeTerms">
               I agree to the <a href="#">terms</a>
              </label>
            </div> -->
						</div>
						<!-- /.col -->
						<div class="col-4" style="color: #138496">
							<button id="signup" type="button"
								class="btn btn-primary btn-block"
								style="background-color: #138496; border-color: #138496;">Register</button>
						</div>
						<!-- /.col -->
					</div>
				</form>


			</div>
			<!-- /.form-box -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->

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
	<script src="essentials/js/register.js"></script>
</body>
</html>