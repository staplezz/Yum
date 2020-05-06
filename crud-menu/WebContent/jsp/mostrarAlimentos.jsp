<%@ page language="java" contentType="text/html; utf-8"
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
	</style>
	<title>Alimentos</title>
	<!-- Icono del título de la página -->
    <link rel = "icon" href = "${pageContext.request.contextPath}/Icons/admin-icon.svg" type = "image/x-icon"> 
</head>
<body>
	<div class="container">
		<h1 class="text-center title">Es posible que quieras agregar algunos elementos sin clasificar...</h1>
	</div>
	
	<div class="container" >
	  <div class="row">
	    <div class="col-sm-3 col-md-3 col-lg-3 offset-sm-9 offset-md-9 offset-lg-9">
	      <div class="accordion">
	        <div class="card">
	          <div class="card-header">
	            <a href="adminMenu?action=mostrarMenu">
	              Listo
	              <img class="icon" src="${pageContext.request.contextPath}/Icons/editar.svg"  class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
	            </a>
	          </div>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="container final">
		<table class="table w-55 mx-auto table-striped table-bordered lg-8">
			<thead>
				<tr>
					<td>Id</td>
					<td>Nombre</td>
					<td>Precio </td>
					<td>Descripcion </td>
					<td>Agregar</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="alimento" items="${alimentos}">
					<tr>
						<td><c:out value="${alimento.id}"/></td>
						<td><c:out value="${alimento.nombre}"/></td>
						<td><c:out value="${alimento.getPrecio()}"/></td>
						<td><c:out value="${alimento.getDescripcion()}"/></td>
						<td><a href="adminMenu?action=agregarAlimento&id=<c:out value="${alimento.id}" />&idCat=<c:out value="${categoria}" />">Elegir</a></td>			
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		<!-- Footer -->
	<footer class="page-footer font-small  pt-1 footer fixed-bottom">
		
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