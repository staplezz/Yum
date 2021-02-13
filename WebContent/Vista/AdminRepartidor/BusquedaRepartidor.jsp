<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html lang="es">
<html>
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
	<title>Menú</title>
	<!-- Icono del título de la página -->
	<link rel="icon"
		href="${pageContext.request.contextPath}/Icons/admin-icon.svg"
		type="image/x-icon">
</head>

<body>

	<!-- Barra de navegación -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="top-bar">
		<div class="navbar-brand">
			<img src="${pageContext.request.contextPath}/Icons/admin.svg"
				width="30" height="30" class="" alt=""> <a
				class="navbar-brand text-white">Administración Yum</a>
		</div>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="adminMenu?action=mostrarMenu">Menú</a>
				<a class="nav-item nav-link" href="adminOrden?action=mostrarOrdenes">Órdenes</a> 
				<a class="nav-item nav-link" href="adminAlimento">Alimentos</a> 
				<a class="nav-item nav-link active" id="nav-select" href="modificadorRepartidor?action=mostrar">Repartidores</a>
			</div>
		</div>
		<div class="navbar-brand" id="cerrar-sesion">
			<a class="nav-item" href="logout?">Cerrar Sesión</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="	#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="	Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>


	<div class="container">
		<h1 class="display-4 text-center title">Repartidores</h1>
	</div>

	<div class="container">
			<!-- Herramienta para agregar alimentos -->
		<div class="container pb-2">
			<div class="row justify-content-end">
				<div class="col-sm-auto px-2">
					<a type="button" class="btn btn-secondary" href="modificadorRepartidor?action=mostrar">Registrar
					nuevo Repartidor</a>
				</div>
			</div>
		</div>
	
	<table class="table mx-auto table-striped table-bordered">
		<thead class="thead-dark">
			<tr>

				<th class="text-center" scope="col">Nombre</th>
				<th class="text-center" scope="col">Apellido Paterno</th>
				<th class="text-center" scope="col">Apellido Materno</th>
				<th class="text-center" scope="col">Correo electrónico</th>
				<th class="text-center" scope="col">Acción </th>

			</tr>
		</thead>

		<tbody>

			<c:forEach var="repartidor" items="${lista}">
				<tr class="text-center">
					<td><c:out value="${repartidor.nombre}" /></td>
					<td><c:out value="${repartidor.apellidoPaterno}" /></td>
					<td><c:out value="${repartidor.apellidoMaterno}" /></td>
					<td><c:out value="${repartidor.correoElectronico}" /></td>
					<td>
							<a class="btn btn-primary"
								href="modificadorRepartidor?action=showedit&idPersona=<c:out value="${repartidor.idPersona}" />">Editar</a> 
								<a
								class="btn btn-danger"
								href="modificadorRepartidor?action=eliminar&idPersona=<c:out value="${repartidor.idPersona}" />">Borrar</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="container text-center">
		<a class="btn btn-secondary"
			href="modificadorRepartidor?action=mostrar">Cancelar</a>
	</div>
	</div>

	<footer class="page-footer font-small  pt-1 footer fixed-bottom">

	<!-- Footer Elements --> <!-- Copyright -->
	<div class="footer-copyright text-center py-2">
		2020 Copyright: <a href="#"> Eff;cient organization <img
			class="icon" src="${pageContext.request.contextPath}/Icons/Logo.svg"
			class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
		</a>
	</div>
	<!-- Copyright --> </footer>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
</body>
</html>

