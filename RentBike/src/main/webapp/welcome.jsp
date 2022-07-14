<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome Page</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="essentials/css/welcome/reset.css">
	<link href='http://fonts.googleapis.com/css?family=Titillium+Web:300,400' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Teko:300,400' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="essentials/css/welcome/main.css">
</head>
<body style = "background-image:url(essentials/images/background.jpg);">
	<main>
		<header>
			<img style="width:70px; height:70px;border-radius: 50%;" src="essentials/images/logo2.PNG">
						
		</header>
		<div class="content">
			<section>
				<header>
					<h1>Rent & Ride</h1>
					<h2>Introduction</h2>
				</header>
				<p>
					Get around Glasgow and enjoy the city in a fun way. Rent bike from anywhere 24*7
					</p>
			</section>
			<aside>
				<h3>Get in Touch</h3>
				<p>Email : admin@rent&Ride.com</p>

				<ul>
					<li id = "index" style = "display: none">
						<a href="index.jsp"><h4 class="link">Rent a bike</h4></a>
					</li>
					<li id = "customer" style = "display: none">
						<a href="customer.html"><h4 class="link">Rent a bike</h4></a>
					</li>
					<li id = "manager" style = "display: none">
						<a href="manager.html"><h4 class="link">Management</h4></a>
					</li>
					<li id = "operator" style = "display: none">
						<a href="operator.html"><h4 class="link">Operate bikes</h4></a>
					</li>
					<li>
						<a href="register.html"><h4 class="link">Register as a customer</h4></a>
					</li>
					<li>
						<a href="operRegister.html"><h4 class="link">Register as an operator</h4></a>
					</li>
					<li>
						<a href="mangRegister.html"><h4 class="link">Register as a manager</h4></a>
					</li>
					<li>
						<h4 class="twitter">Twitter</h4>
					</li>
					<li>
						<h4 class="facebook">Facebook</h4>
					</li>
					<li>
						<h4 class="linkedin">LinkedIn</h4>
					</li>
				</ul>
			</aside>
			<footer>
				<p class="copyright"></p>
			</footer>
		</div>
	</main>
	<script type="text/javascript">

		if (sessionStorage.getItem('emailId')
				|| sessionStorage.getItem('emailId') != undefined) {
			if(sessionStorage.getItem('role') == "C"){
				document.getElementById('customer').style.display = ''
			} else if (sessionStorage.getItem('role') == "M"){
				document.getElementById('manager').style.display = ''
			} else {
				document.getElementById('operator').style.display = ''
			}
		} else {
			document.getElementById('index').style.display = ''
		}
	</script>
</body>
</html>