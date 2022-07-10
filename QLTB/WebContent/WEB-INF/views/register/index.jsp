<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
<base href="${pageContext.servletContext.contextPath }/" />
<link rel="icon" href="resource/images/logo.png" type="image/x-icon" />
<!-- <link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet"> -->
<link href="resource/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="resource/assets/dist/css/login.css" rel="stylesheet">

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/56d73d7086.js"
	crossorigin="anonymous"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

#logo img {
	max-height: 4rem;
	height: 74px;
}

.header {
	box-shadow: 2px 6px 5px #dbdbde;
	background: #fff;
	height: 75px;
}

.navbar, .navbar a {
	padding: 0 27px;
}

@media ( min-width : 992px) {
	.container, .container-lg, .container-md, .container-sm {
		max-width: 520px !important;
	}
}

@media only screen and (min-width: 576px ) and (max-width: 992px) {
	.container, .container-lg, .container-md, .container-sm {
		min-width: 100% !important;
	}
}
</style>
<body>
	<div class="header fixed-top ">
		<nav class="navbar navbar-expand-md  ">
			<div id="logo" class="container-fluid">

				<a class="navbar-brand" href="homepage/index.htm"><img
					alt="Home"
					src="https://my.shopcoinusa.com/_nuxt/img/logo.b1a090f.png"></a>
			</div>


		</nav>

	</div>
	<div class="container" style="margin-top: 48px">
		<div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
			<div class="card card0 border-0">
				<div class="row d-flex">
					<div class="col-lg-12">
						<div class="card2 card border-0 px-4 "
							style="padding-top: 2.5rem !important; padding-bottom: 2.5rem !important";
}">
							<div class="row px-3 mb-4">
								<span style="width: 100%; font-size: 18px"
									class="or text-center">REGISTER</span>
							</div>
							<form:form action="register.htm" method="post"
								modelAttribute="account">
								<div class="row px-3 mb-2 mb-2">
									<label style="padding-left: 0px" class="mb-1">
										<h6 class="mb-0 text-sm">Username</h6>
									</label>
									<form:input type="text" path="id" placeholder="Nhập Username" />
									<form:errors path="id" cssClass="errors"></form:errors>
								</div>
								<div class="row px-3 mb-2 mb-2">
									<label style="padding-left: 0px" class="mb-1">
										<h6 class="mb-0 text-sm">Email</h6>
									</label>
									<form:input type="text" path="email" placeholder="Nhập Username" />
									<form:errors path="email" cssClass="errors"></form:errors>
								</div>
								<div class="row px-3">
									<label style="padding-left: 0px" class="mb-1">
										<h6 class="mb-0 text-sm">Password</h6>
									</label>

									<form:input type="password" path="pass"
										placeholder="Nhập Password" />
									<form:errors path="pass" cssClass="errors"></form:errors>
								</div>
								<div class="row px-3 mb-4"></div>

								<div class="g-recaptcha d-flex justify-content-center"
									data-sitekey="6LcyNQMfAAAAANfcuBz9e1G4FIbW755pqOfvY6X_"></div>

								<label class="mb-1">
									<h6 class="text-sm errors">${tb }</h6>
								</label>
								<div class="row mb-3 px-3 d-flex justify-content-center">
									<button type="submit" class="btn-hover color-1 text-center">Register</button>
								</div>
							</form:form>
							<div style="font-size: 13.5px; margin-top: 7px;line-height: 1rem;">By registering I confirm I have read and agree to
								Terms of Use. We send occasional marketing messages which can be
								switched off in account settings. We manage personal data as set
								out in our Privacy Notice.</div>

						</div>
					</div>
				</div>
			</div>
			<div class="row text-center mt-4">
				<small class="font-weight-bold">Already have an account? <a
					class="text-danger " href="login.htm"> Login</a>
				</small>
			</div>
			<div class="d-flex justify-content-around mt-4"
				style="font-size: 15.75px">
				<a>Privacy Notice</a> <a>Cookies Notice</a> <a>Cookies Settings</a>

			</div>
		</div>

	</div>
	
	<%@include file="/WEB-INF/views/include/footer.jsp"%>



</body>
</html>