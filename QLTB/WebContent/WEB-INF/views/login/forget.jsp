<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên Mật Khẩu</title>
<base href="${pageContext.servletContext.contextPath }/" />
<link rel="icon" href="https://shopcoinusa.com/wp-content/uploads/2021/12/cropped-logo-2-180x180.png" type="image/x-icon" />
<!-- <link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet"> -->
<link href="resource/assets/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="resource/assets/dist/css/login.css" rel="stylesheet">
<link href="resource/assets/dist/css/pageacc.css" rel="stylesheet">
<script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body>
	<div class="header fixed-top">
		<nav class="navbar navbar-expand-md   ">
			<div id="logo" class="container-fluid">

				<a class="navbar-brand" href="homepage/index.htm"><img
					alt="Home"
					src="https://my.shopcoinusa.com/_nuxt/img/logo.b1a090f.png"></a>
			</div>


		</nav>

	</div>
	<div class="container" style="margin-top: 94px">
		<div class="container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto">
			<div class="card card0 border-0">
				<div class="row d-flex">
					<div class="col-lg-12">
						<div class="card2 card border-0 px-4 "
							style="padding-top: 2.5rem !important; padding-bottom: 2.5rem !important";
}">
							<div class="row px-3 mb-4">
								<span style="width: 100%; font-size: 18px"
									class="or text-center">FORGOT YOUR PASSWORD ?</span>
							</div>
							<div
								style="font-size: 15.5px; margin-top: 16px; margin-bottom:20px; line-height: 1rem;">To
								reset your password, enter the email address that you used to
								set up your account. We'll send you a link to help you get back
								into your account.</div>
							<form action="forget-pass.htm" method="post">
								<div class="row px-3 mb-2 mb-2">
									</label> <input type="text" name="email" placeholder="Nhập Email" />
								</div>
								
								<div class="g-recaptcha d-flex justify-content-center"
									data-sitekey="6LcyNQMfAAAAANfcuBz9e1G4FIbW755pqOfvY6X_"></div>

								<label class="mb-1">
									<h6 class="text-sm errors">${tb }</h6>
								</label>
								<div class="row mb-3 px-3 d-flex justify-content-center">
									<button type="submit" class="btn-hover color-1 text-center">Send</button>
								</div>
							</form>
						</div>
					</div>
				</div>
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