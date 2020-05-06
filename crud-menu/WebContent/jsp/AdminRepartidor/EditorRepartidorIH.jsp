
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
    if ((session.getAttribute("id") == null) ) {
%>
No se ha podido iniciar sesi�n<br/>
<a href="index.jsp">Por favor inicia sesi�n</a>
<%} else {
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
		<meta charset="utf-8">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	
	<!-- Style CSS para lo dem�s -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	<style type="text/css">
		.title{
			margin-top: 70px;
		}
		.final{
			margin-bottom: 60px;
		}
	</style>
	<meta charset="ISO-8859-1">
	<title>Men�</title>
	<!-- Icono del t�tulo de la p�gina -->
    <link rel = "icon" href = "${pageContext.request.contextPath}/Icons/admin-icon.svg" type = "image/x-icon"> 
  
  </head>

	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="top-bar">
			<div class="navbar-brand">
				<img src="${pageContext.request.contextPath}/Icons/admin.svg" width="40" height="40" class="" alt="">
  				<a class="navbar-brand text-white">Administración Yum</a>
			</div>
			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			    <div class="navbar-nav">
				      <a class="nav-item nav-link" href="#" >Órdenes</a>
				      <a class="nav-item nav-link" href="adminMenu?action=mostrarMenu">Men�</a>
				      <a class="nav-item nav-link" href="#" >Alimentos</a>
				      <a class="nav-item nav-link" id="nav-select" href="modificadorRepartidor?action=mostrar">Repartidores</a>
			      </div>
		      </div>
			<div class="navbar-brand" id="cerrar-sesion">
				<a class="nav-item" href="./../logout.jsp">Cerrar Sesión</a>
				<img src="${pageContext.request.contextPath}/Icons/cerrar-sesion.svg" width="30" height="40" class="ml-3" alt="">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="	#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="	Toggle navigation">
  				<span class="navbar-toggler-icon"></span>
  				</button>
  			</div>
		</nav>
		
		<div class = "container">
			<h1 class="text-center title">Editar Repartidor</h1>
		</div>
		
  </div>

	
	<form action="final modificadorRepartidor?action=editar" method="post" >
		<div class="  text-center form schedule-assessment col-sm-6 col-md-6 col-lg-6 offset-sm-3 offset-md-3 offset-lg-3" ">
			<div class = "form-group ">
				<label for = "nombre">Nombre</label>
				<input type="text" class="form-control"  id = "nombre" name="nombre" value="${repartidor.nombre}" >
			</div>
				
			<div class = "form-group">
				<label for = "apellidoPaterno">Apellido Paterno</label>
				<input type="text" class="form-control" name="apellidoPaterno" id="apellidoPaterno" value="${repartidor.apellidoPaterno}" >
			</div>		
					
			<div class = "form-group">
				<label for = "apellidoMaterno" >Apellido Materno</label>
				<input type="text" class="form-control" id = "apellidoMaterno" name="apellidoMaterno" value="${repartidor.apellidoMaterno}">
			</div>
			
			<div class = "form-group">
					<label>Correo electr�nico</label>
					<input type="text" class="form-control " name="correoElectronico" value="${repartidor.correoElectronico}" >
			</div>
			<input id=idPersona name="idPersona" type = "hidden" value="${repartidor.idPersona}">
			<div class = "text-center">
				<button type="submit"  name = "registrar" class="btn btn-primary">Guardar</button>
			</div>
		</div>
	</form>
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
		
	</body>

</html>


<%
    }
%>