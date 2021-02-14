<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	
	<!-- Style CSS para lo demás -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/CSS/style.css">
	<style type="text/css">
	.title {
		margin-top: 70px;
	}
	
	.final {
		margin-bottom: 60px;
	}
	</style>
	<title>Yum</title>
	<!-- Icono del título de la página -->
	<link rel="icon"
		href="${pageContext.request.contextPath}/Icons/yum.svg" type="image/x-icon">
	</head>
<body>
	<div class="d-flex flex-column justify-content-center min-vh-100 bg-light pb-0">
	<div class="container">
		<div class="text-center pb-4"><img src="${pageContext.request.contextPath}/Icons/yum.svg" width="100" height="100" alt="YUM"></div>
		<div class="col"><h3 class="text-center">Iniciar sesión en Yum</h3></div>
	</div>
	<div class="container">
		<div class="row">
			<div
				class="container align-items-center w-50">
				<form method="post" action="login?">
					<table class="table">
						<tr>
							<td>Correo electrónico:</td>
							<td><input type="text" name="email" required
								class="form-control" placeholder="Email"></td>
						</tr>
						<tr>
							<td>Contraseña:</td>
							<td><input type="password" name="password" required
								class="form-control" placeholder="Contraseña"></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Ingresar" class="form-control"></td>
						</tr>
						<tr>
							<td colspan="2"><a href="modificadorCliente?action=registraCliente" class="form-control text-center">Regístrate aquí</a></td>
						</tr>
					</table>
				</form>
				<!-- Mensaje en caso de inicio de sesión incorrecto. -->
				<p class="text-center text-danger">${message}</p>
			</div>
		</div>
	</div>
	</div>
	<!-- Footer -->
	<footer class="page-footer font-small footer fixed-bottom footer">

		<!-- Footer Elements -->

		<!-- Copyright -->
		<div class="footer-copyright text-center py-2 bg-light">
			2020 Copyright: <a href="#"> Eff;cient organization <img
				class="icon" src="${pageContext.request.contextPath}/Icons/Logo.svg"
				class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
			</a>
		</div>
		<!-- Copyright -->

	</footer>
	<!-- Footer -->
</body>
</html>
