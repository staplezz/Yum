<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
	<title>Administración Yum</title>
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
				<a class="nav-item nav-link" href="adminMenu?action=mostrarMenu" id="nav-select">Menú</a>
				<a class="nav-item nav-link" href="adminOrden?action=mostrarOrdenes">Órdenes</a> 
				<a class="nav-item nav-link" href="adminAlimento">Alimentos</a> 
				<a class="nav-item nav-link" href="modificadorRepartidor?action=mostrar">Repartidores</a>
			</div>
		</div>
		<div class="navbar-brand" id="cerrar-sesion">
			<a class="nav-item" href="logout?">Cerrar
				Sesión</a> 
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="	#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="	Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>



	<div class="container">
		<h1 class="display-4 text-center title">Menú</h1>
	</div>

	<div class="container">
		<div class="row pb-2">
			<div class="col-sm-3 col-md-3 col-lg-3 offset-sm-6 offset-md-6 offset-lg-6">
				<div class="card">
					<button type="button" class="btn btn-secondary"
						data-toggle="collapse" data-target="#buscar"> Crear categoría.
					</button>
					<div id="buscar" class="collapse">
						<div class="card-body">
							<form action="adminMenu?action=agregarCategoria" method="post">
								<div class="form-group">
									<input class="form-control" id="validationCustom01" type="text" name="nombre"
										placeholder="Nombre de categoría." required>
								</div>
								<button class="btn btn-outline-primary" type="submit">Agregar</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-3 col-md-3 col-lg-3">
				<div class="card">
					<button type="button" class="btn btn-secondary"
						data-toggle="collapse" data-target="#buscar">
						Buscar por categoría.
					</button>
					<div id="buscar" class="collapse">
						<div class="card-body">
							<form action="adminMenu?action=buscarPorCategoria" method="post">
								<div class="form-group">
									<input class="form-control" id="validationCustom01" type="text" name="nombre"
										placeholder="Nombre de categoría." required>
								</div>
								<button class="btn btn-outline-primary" type="submit">Buscar</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container final">
		<table
			class="table table-image w-60 mx-auto table-striped table-bordered lg-8">
			<thead class="thead-dark text-center">
				<tr>
					<th style="width: 40%">Categoría</th>
					<th style="width: 30%">Configuración</th>
					<th style="width: 30%">Alimentos</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="categoria" items="${lista}">
					<tr>
						<td rowspan="${categoria.listaAlimentos.size()+1}" class="text-center align-middle"><c:out
								value="${categoria.nombre}" /></td>
						
						<td rowspan="${categoria.listaAlimentos.size()+1}" class="text-center align-middle">
						<a href="adminMenu?action=showEditar&id=${categoria.id}" class="btn btn-primary"> Editar</a>
						<a href="adminMenu?action=eliminarCategoria&id=${categoria.id}" class="btn btn-danger">Eliminar</a>
						</td>
						<c:forEach var="alimento" items="${categoria.listaAlimentos}">
							<tr>
								<td>${alimento.nombre}</td>
							</tr>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- Footer -->
	<footer class="page-footer font-small  pt-1 footer fixed-bottom footer">

		<!-- Footer Elements -->

		<!-- Copyright -->
		<div class="footer-copyright text-center py-2">
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