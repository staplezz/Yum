<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	<title>Ver Orden</title>
	<!-- Icono del título de la página -->
	<link rel="icon"
		href="${pageContext.request.contextPath}/Icons/yum.svg"
		type="image/x-icon">
</head>
<body>
	<!-- Barra de navegación -->
	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: #28536B">
		<a class="navbar-brand mr-5" href="#"> <img
			src="${pageContext.request.contextPath}/Icons/yum.svg" width="35"
			height="35" class="d-inline-block align-top mr-2" alt=""> Yum
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#barraNavegacion" aria-controls="barraNavegacion"
			aria-expanded="true" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="barraNavegacion">
			<ul class="navbar-nav mr-auto">
				<li><img
					src="${pageContext.request.contextPath}/Icons/menu.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link mr-3" href="muestraMenu?action=mostrarAlimentos&idCategoria=1">Menú
						<span class="sr-only">(current)</span>
				</a></li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/orden.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item active"><a class="nav-link mr-3" href="modificadorCliente?action=mostrarOrdenesActuales">Órdenes</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/carro.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item mr-3"><a class="nav-link" href="modificadorCarrito?action=verCarrito">Carrito</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/cuenta.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link" href="modificadorCliente?action=mostrarEditarCliente">Cuenta</a></li>
			</ul>
			<span class="navbar-text"> <a href="logout?">Cerrar
					Sesión</a>
			</span>
		</div>
	</nav>

	<div class="container">
		<h1 class="text-center title">Tu orden</h1>
	</div>
	
	
	<div class="container">
		
		<table class="table text-center">
	  	<thead class="thead-light">
		    <tr>
		      <th scope="col" style="width: 70%">Alimento</th>
		      <th scope="col" style="width: 15%">Cantidad</th>
		      <th scope="col" style="width: 15%">Precio</th>
		    </tr>
		  </thead>
		  
		  <c:forEach var="alimento" items="${listaAlimentos}">
				<tr>
			 		<td>${alimento.getNombre()}</td>
					<td>${alimento.getCantidad()}</td>
					<td>${alimento.getPrecio()}</td>
				</tr>
			</c:forEach>
				<tr class="table-success">
					<td></td>
	   		  		<td colspan="2" class="text-center">Total: $${total}</td>
				</tr>
		  <tbody>
		  </tbody>
		</table>
			
			<div class="container px-0 final">
			<!-- Herramienta para regresar a órdenes -->
			<div class="container">
				<div class="row justify-content-end">
					<h5><a href="modificadorCliente?action=mostrarOrdenesActuales" class="btn btn-secondary">Regresar</a></h5>
				</div>
			</div>
		</div>
		
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