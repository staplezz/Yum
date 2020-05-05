<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

	<!-- Style CSS para lo demás -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	<style type="text/css">
		.title{
			margin-top: 70px;
		}
		.final{
			margin-bottom: 60px;
		}
	</style>
	<title>Registro</title>
	<!-- Icono del título de la página -->
    <link rel = "icon" href = "${pageContext.request.contextPath}/Icons/Logo.svg" type = "image/x-icon">
</head>
<body>
		<div class="container">
			<h1 class="text-center title">Registrarse</h1>
		</div>
		<form action = "modificadorCliente?action=agregaCliente" method="post" class="final">
		<table class="table  lg-8">
			<tr>
				<td><h3 style="color: black;">Ingresa los datos </h3></td>
			</tr>

			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre"required placeholder="Nombre" class="form-control"></td>
			</tr>

			<tr>
				<td>Apellido Paterno:</td>
				<td><input type="text" name="apePat" required placeholder="Apellido paterno" class="form-control"></td>
			</tr>

			<tr>
				<td>Apellido Materno:</td>
				<td><input type="text" name="apeMat" required placeholder="Apellido materno" class="form-control"></td>
			</tr>

			<tr>
				<td>Correo electronico:</td>
				<td><input type="text" name="email" required placeholder="Correo electrónico" class="form-control"></td>
			</tr>

			<tr>
				<td>Contraseña:</td>
				<td><input type="password" name="password" required placeholder="Contraseña" class="form-control"></td>
			</tr>

			<tr>
				<td>Teléfono:</td>
				<td><input type="text" name="telefono" required placeholder="Teléfono" class="form-control"></td>
			</tr>

			<tr>
				<td>Delegación</td>
				<td>Colonia</td>
				<td>Calle</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="delegacion" class="form-control" placeholder="Delegación" required>
				</td>
				<td>
					<input type="text" name="colonia" class="form-control" placeholder="Colonia" required>
				</td>
				<td>
					<input type="text" name="calle" class="form-control" placeholder="Calle" required>
				</td>
			</tr>
			<tr>
				<td>Número interior</td>
				<td>Número exterior</td>
			</tr>
			<tr>
				<td>
					<input type="number" name="numInt" class="form-control" placeholder="Número interior">
				</td>
				<td>
					<input type="number" name="numExt" class="form-control"  placeholder="Número exterior">
				</td>
			</tr>

			<tr>
				<td><input type="submit" name="submit" value="Registrar"></td>
				<td></td>
			</tr>
		</table>
	</form>

		<!-- Footer -->
	<footer class="page-footer font-small  pt-1 footer fixed-bottom footer">

		  <!-- Footer Elements -->

		  <!-- Copyright -->
		  <div class="footer-copyright text-center py-2">2020 Copyright:
		    <a href="#">
		  	  Eff;cient organization
		  	  <img class="icon" src="${pageContext.request.contextPath}/Icons/Logo.svg"  class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
		    </a>
		  </div>
		  <!-- Copyright -->

	</footer>
		<!-- Footer -->
</body>
</html>
