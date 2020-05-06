<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
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
	</style>
	<title>Categoría</title>
	<!-- Icono del título de la página -->
    <link rel = "icon" href = "${pageContext.request.contextPath}/Icons/admin-icon.svg" type = "image/x-icon"> 
</head>
<body>
	<div class="container">
		<h1 class="text-center title">Tus direcciones guardadas :</h1>
	</div>
	
	<div class="container final">
		<table class="table table-image w-55 mx-auto table-striped table-bordered lg-8">
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
						<td><c:out value="${direccion.getDelegacion()}"/></td>
						<td><c:out value="${direccion.getColonia()}"/></td>
						<td><c:out value="${direccion.getCalle()}"/></td>
						<td><c:out value="${direccion.getNumExterior()}"/></td>
						<td>
							<a href="modificadorCliente?action=mostrarEditarDireccion&idDireccion=<c:out value="${direccion.getIdDireccion()}" />&idCliente=<c:out value="${idCliente}" />">
								Editar
								<img class="icon" src="${pageContext.request.contextPath}/Icons/editar.svg" class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">			
							</a>
						</td>
						<td>
							<a href="modificadorCliente?action=eliminarDireccion&idDireccion=<c:out value="${direccion.getIdDireccion()}"/>&idCliente=<c:out value="${idCliente}" />">
								Eliminar
								<img class="icon" src="${pageContext.request.contextPath}/Icons/borrar.svg" class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
							</a> 
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
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