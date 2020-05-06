<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
		.final{
			margin-bottom: 60px;
		}
	</style>
	<meta charset="ISO-8859-1">
	<title>Yum</title>
	<!-- Icono del título de la página -->
    <link rel = "icon" href = "C:/Users/Alma/eclipse-workspace/crud-menu/WebContent/Icons/admin-icon.svg" type = "image/x-icon"> 
</head>
<body>
	<div class="container">
		<h3 class="text-center">Inicio de sesión Yum</h3>
	</div>
	<div class="container final">
		<div class="row">
			<div class= "col-sm-6 col-md-6 col-lg-6  offset-sm-3 offset-md-3 offset-lg-3">
				<form method="post" action="enter.jsp">
					<table class="table w-55 mx-auto lg-8">
					<tr>
						<td>Correo electronico:</td>
						<td>
							<input type="text" name="email" required class="form-control" placeholder="email">
						</td>
					</tr>
					<tr>
						<td>Contraseña:</td>
						<td>
							<input type="password" name="password" required class="form-control" placeholder="password">
							</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="Ingresar" class="form-control">
						</td>
						<td>
							<a href="modificadorCliente?action=registraCliente" class="form-control">Regístrate aquí</a>
						</td>
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