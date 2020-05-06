<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if ((session.getAttribute("id") == null) ) {
%>
No se ha podido iniciar sesión<br/>
<a href="index.jsp">Por favor inicia sesión</a>
<%} else {
%>

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
	<title>Editar perfil</title>
	<!-- Icono del título de la página -->
    <link rel = "icon" href = "${pageContext.request.contextPath}/Icons/Logo.svg" type = "image/x-icon"> 
</head>
<body>

	<div class="container">
			<h1 class="text-center title">Modifica tu perfil, ${cliente.getNombre()}</h1>
			<a href='./../logout.jsp'>Cerrar sesión</a>
	</div>
		<div class="container" >
	  <div class="row">
	    <div class="col-sm-3 col-md-3 col-lg-3 offset-sm-9 offset-md-9 offset-lg-9">
	      <div class="accordion">
	        <div class="card">
	        	
		         	 <button type="button" class="btn btn-outline-info" onclick="window.location.href='modificadorCliente?action=mostrarDirecciones&idCliente=<c:out value="${cliente.getIdCliente()}"/>';">
		          		Editar direcciones
		             	 <img class="icon" src="${pageContext.request.contextPath}/Icons/editar.svg"  class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
		          	 </button>
	          	
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="container final">
		<div class="row">
			<div class= "col-sm-6 col-md-6 col-lg-6  offset-sm-3 offset-md-3 offset-lg-3">
				<form action = "modificadorCliente?action=editarCliente&idC=<c:out value="${cliente.getIdCliente()}"/>&idP=<c:out value="${cliente.getIdPersona()}"/>" method="post" class="final">
					<table class="table w-55 mx-auto lg-8">
						
						<tr>
							<td>Nombre:</td>
							<td><input type="text" name="nombre"required placeholder="Nombre" value = "<c:out value="${cliente.getNombre()}"></c:out>" class="form-control"></td>
						</tr>
			
						<tr>
							<td>Apellido Paterno:</td>
							<td><input type="text" name="apePat" required placeholder="Apellido paterno" value = "<c:out value="${cliente.getApellidoPaterno()}"></c:out>"  class="form-control"></td>
						</tr>
			
						<tr>
							<td>Apellido Materno:</td>
							<td><input type="text" name="apeMat" required placeholder="Apellido materno" value = "<c:out value="${cliente.getApellidoMaterno()}"></c:out>" class="form-control"></td>
						</tr>
			
						<tr>
							<td>Correo electronico:</td>
							<td><input type="text" name="email" required placeholder="Correo electrónico"  value = "<c:out value="${cliente.getNombre()}"></c:out>" class="form-control" disabled></td>
						</tr>
			
						<tr>
							<td>Contraseña:</td>
							<td><input type="password" name="password" required placeholder="Contraseña" value = "<c:out value="${cliente.getPassword()}"></c:out>" class="form-control"></td>
						</tr>
			
						<tr>
							<td>Teléfono:</td>
							<td><input type="text" name="telefono" required placeholder="Teléfono" value = "<c:out value="${cliente.getTelefono()}"></c:out>" class="form-control"></td>
						</tr>
						<tr>
							<td><input type="submit" name="submit" value="Guadar"></td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
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
<%
    }
%>