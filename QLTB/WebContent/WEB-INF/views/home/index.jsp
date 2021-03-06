<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<script src="resource/assets/dist/js/bootstrap.bundle.min.js"></script>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

#logo img {
	min-width: 110px;
	max-width: 100%;
	max-height: 90px;
	max-width: 100%;
}

.header {
	background: #fff;
	height: 90px;
}

.navbar {
	font-weight: 500;
	max-width: 1210px;
	margin: 0 auto;
}

.navbar-nav li a {
	font-size: 16.2px;
	letter-spacing: .02em;
	text-transform: uppercase;
}

.navbar-nav li {
	width: max-content !important;
}

.navbar-nav li .nav-link {
	color: rgba(102, 102, 102, .85);
	transition: all .2s;
}

#btn-login a {
	text-decoration: none;
	color: #fff;
}

#btn-login {
	background: #f29e33;
	color: #fff;
	min-width: 86px;
	width: 86px;
	height: 38px;
	border-radius: 99px;
	order: 3;
	line-height: 38px;
	text-align: center;
}

#section1 {
	position: relative;
	padding-top: 70px;
	padding-bottom: 70px;
	height: 600px;
}

.section-bg {
	background-image:
		url(https://shopcoinusa.com/wp-content/uploads/2021/12/z3059361473346_23e24c2fb4b09ab90e584573746c7296.jpg);
}

.section-bg-overplay {
	background-color: rgba(242, 158, 51, 0.25);
}

.section-content {
	width: 100%;
	height: calc(100% - 140px);
	position: absolute;
	color: #fff;
}

.bg-fill {
	background-size: cover !important;
	background-repeat: no-repeat !important;
	background-position: 50% 50%;
}

#content {
	padding-top: 94px;
	overflow-x: hidden
}

.fill {
	position: absolute;
	top: 0;
	left: 0;
	height: 100%;
	right: 0;
	bottom: 0;
	padding: 0 !important;
	margin: 0 !important;
}

.in-out {
	display: flex;
	flex-direction: row;
	align-content: center;
	justify-content: center;
	order: 3;
	align-items: center;
	margin-right: 20px;
}

#btn-login {
	color: #000;
	border-radius: 8px;
}

#btn-logout {
	order: 4;
	padding: 10px;
}

@media screen and (max-width: 768px) {
	.navbar-nav {
		background: #f1f1fb;
		margin: 0 !important;
	}
	.navbar {
		padding-left: 0px !important;
		padding-right: 0px !important;
	}
	.navbar-nav li {
		height: 36px;
		border: 0px;
		border-bottom: 2px;
		border-style: inset;
		width: 100% !important;
	}
	.navbar-nav li a {
		height: 100%;
		line-height: 200%;
		padding: 0 13.5px !important;
	}
	#btn-login {
		order: 0;
	}
	#logo {
		width: fit-content;
		min-width: 10%;
		width: 50%;
	}
	#logo img {
		max-height: 70px;
	}
	#logo a {
		margin: 0 auto;
	}
	#btn-login {
		position: relative;
	}
	.content-main {
		display: flex;
		flex-direction: column;
	}
	.content1 {
		height: 30px !important;
	}
	.content2 {
		width: 100%;
		padding: 0 24px 30px;
	}
	.content2 h3 strong {
		font-size: 33px !important;
	}
}
</style>



<!-- Custom styles for this template -->

</head>
<body>
	<div class="header fixed-top">
		<nav class="navbar navbar-expand-md  ">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
				aria-controls="navbarCollapse" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fa-solid fa-bars"></i>
			</button>
			<div id="logo" class="container-fluid">

				<a class="navbar-brand" href="homepage/index.htm"><img
					alt="Home" width="200" height=""
					src="https://shopcoinusa.com/wp-content/uploads/2021/12/logo-e1640592718566.png"></a>
			</div>
			<div class="in-out">
				<span>${acc }</span>
				<div id="btn-login" style="margin-left: 5px">
					<a href="login.htm">Standard</a>
				</div>
				<div id="btn-logout">
					<a href="logout.htm"><i class="fa-solid fa-right-from-bracket"></i></a>

				</div>

			</div>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav me-auto mb-2 mb-md-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">My Control</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Partner</a></li>
					<li class="nav-item"><a class="nav-link " href="#">Contact</a>
				</ul>

			</div>


		</nav>

	</div>

	<main>

		<div id="content"></div>
	</main>



</body>
</html>
