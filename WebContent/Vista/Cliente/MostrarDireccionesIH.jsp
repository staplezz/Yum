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
	</style>
	<title>Direcciones</title>
	<!-- Icono del título de la página -->
	<link rel="icon"
		href="${pageContext.request.contextPath}/Icons/admin-icon.svg"
		type="image/x-icon">
</head>
<body>
	<!-- Barra de navegaci�n -->
	<nav class="navbar navbar-expand-lg navbar-dark"
		style="background-color: #28536B">
		<a class="navbar-brand mr-5" href="#"> <img
			src="${pageContext.request.contextPath}/Icons/bear.svg" width="30"
			height="30" class="d-inline-block align-top mr-2" alt=""> Yum
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#barraNavegacion" aria-controls="barraNavegacion"
			aria-expanded="true" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="barraNavegacion">
			<ul class="navbar-nav mr-auto">
				<li><img
					src="${pageContext.request.contextPath}/Icons/menu.svg" width="30" height="30" 
					class="d-inline-block align-top mr-2" alt="">
				</li>
				<li class="nav-item active">
					<a class="nav-link mr-3" href="#">Menú
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/orden.svg" width="30" height="30" 
					class="d-inline-block align-top mr-2" alt="">
				</li>
				<li class="nav-item"><a class="nav-link mr-3" href="#">Órdenes</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/carro.svg" width="30" height="30"
					class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item mr-3"><a class="nav-link" href="#">Carrito</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/orden.svg" width="30" height="40" 
					class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link" href="modificadorCliente?action=mostrarEditarCliente">Cuenta</a></li>
			</ul>
			<span class="navbar-text"> 
				<img
				src="${pageContext.request.contextPath}/Icons/cerrar-sesion.svg" width="30" height="30"
				 class="d-inline-block align-top mr-2"
				alt="cerrar sesión"> 
				<a href="logout?">Cerrar Sesión</a>
			</span>
		</div>
	</nav>

	<div class="container">
		<h1 class="text-center title">Tus direcciones guardadas :</h1>
	</div>
	
	<div class="container">
		<div class="row pb-2">
			<div class="col-sm-3 col-md-3 col-lg-3 offset-sm-9 offset-md-9 offset-lg-9">
				<button type="button" class="btn btn-outline-info"
						data-toggle="modal" data-target="#agregar">Agregar dirección <img class="icon"
							src="${pageContext.request.contextPath}/Icons/anadir.svg"
							class="img-fluid img-thumbnail" alt="Editar" width="30"
							height="30">
				</button>
				<div class="modal fade" id="agregar" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLongTitle">Agregar dirección</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				       <form action="modificadorCliente?action=agregarDireccion" method="post">
				         <div class="modal-body">
				        	<div class="form-group">
				        		<label for="delegacion">Delegación</label>
				        		<input type="text" class="form-control" name="delegacion" placeholder="Delegación" required>
				        	</div>
				        	<div class="form-group">
				        		<label for="colonia">Colonia</label>
				        		<input type="text" class="form-control" name="colonia" placeholder="Colonia" required>
				        	</div>
				        	<div class="form-group">
				        		<label for="calle">Calle</label>
				        		<input type="text" class="form-control" name="calle" placeholder="Calle" required>
				        	</div>
				        	<div class="form-group">
				        		<label for="numInt">Número interior</label>
				        		<input type="number" class="form-control" name="numInt" placeholder="Número interior" required>
				        	</div>
				        	<div class="form-group">
				        		<label for="numExt">Número exterior</label>
				        		<input type="number" class="form-control" name="numExt" placeholder="Número interior" required>
				        	</div>
				          </div>
				          <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
					        <button type="submit" class="btn btn-primary">Guardar</button>
					      </div>
				        </form>
				    </div>
				  </div>
				</div>
			</div>
			
		</div>
	</div>

	<div class="container final">
		<table
			class="table table-image w-55 mx-auto table-striped table-bordered lg-8">
			<thead class="thead-dark">
				<tr>
					<td>Delegación</td>
					<td>Colonia</td>
					<td>Calle</td>
					<td>Número exterior</td>
					<td colspan=2>Configuración</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="direccion" items="${direcciones}">
					<tr>
						<td><c:out value="${direccion.getDelegacion()}" /></td>
						<td><c:out value="${direccion.getColonia()}" /></td>
						<td><c:out value="${direccion.getCalle()}" /></td>
						<td><c:out value="${direccion.getNumExterior()}" /></td>
						<td><a
							href="modificadorCliente?action=mostrarEditarDireccion&idDireccion=<c:out value="${direccion.getIdDireccion()}"/>">
								Editar <img class="icon"
								src="${pageContext.request.contextPath}/Icons/editar.svg"
								class="img-fluid img-thumbnail" alt="Editar" width="30"
								height="30">
						</a></td>
						<td><a
							href="modificadorCliente?action=eliminarDireccion&idDireccion=<c:out value="${direccion.getIdDireccion()}"/>">
								Eliminar <img class="icon"
								src="${pageContext.request.contextPath}/Icons/borrar.svg"
								class="img-fluid img-thumbnail" alt="Editar" width="30"
								height="30">
						</a></td>
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