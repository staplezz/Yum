<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html lang="es">
<html>
<head>
<!-- Bootstrap & DataTables CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.css"/>
	
	<!-- Style CSS para lo dem�s -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	
	<title>Repartidor Yum</title>
	<link rel = "icon" href = "${pageContext.request.contextPath}/Icons/repartidor.svg" type = "image/x-icon"> 
</head>
<body>
	<!-- Barra de navegaci�n. -->
	<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #364156;"
	id="top-bar">
		<div class="navbar-brand mr-0">
			<img src="${pageContext.request.contextPath}/Icons/repartidor.svg"
				width="30" height="30" class="" alt=""> 
				<a class="navbar-brand text-black mx-2">Repartidor Yum</a>
		</div>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link active" href="ordenRepartidor?action=mostrarOrdenes">�rdenes listas</a>
				<a class="nav-item nav-link" href="#">Mis �rdenes</a> 
			</div>
		</div>
		<div class="navbar-brand" id="cerrar-sesion">
			<a class="nav-item" href="logout?">Cerrar Sesi�n</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="	#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="	Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>
	
	<!--  T�tulo de la tabla -->
	<div class="container">
		<h1 class="display-4 text-center title mb-2 mt-2">Orden #${ordenId}</h1>
	</div>
	
		<!--  Tabla con la informaci�n de la �rden. -->
	<div class="container w-50">
		<table class="table text-center">
	  	<thead class="thead-dark">
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
		
		<div class="container px-0">
			<!-- Herramienta para regresar a �rdenes -->
			<div class="container">
				<div class="row justify-content-end">
					<h5><a href="ordenRepartidor?action=mostrarOrdenes" class="btn btn-secondary">Regresar</a></h5>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small  pt-1">

		<!-- Footer Elements -->

		<!-- Copyright -->
		<div class="footer-copyright text-center py-2 fixed-bottom">
			2020 Copyright: <a href="#"> Eff;cient organization <img
				class="icon" src="${pageContext.request.contextPath}/Icons/Logo.svg"
				class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
			</a>
		</div>
		<!-- Copyright -->

	</footer>
	<!-- Footer -->
	
	<!-- JS para bootstrap. -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	

</body>
</html>