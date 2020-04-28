<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<!-- Style CSS para lo demás -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	<meta charset="ISO-8859-1">
	<title>Menú</title>
	<!-- Icono del título de la página -->
    <link rel = "icon" href = "C:/Users/Alma/eclipse-workspace/crud-menu/WebContent/Icons/admin-icon.svg" type = "image/x-icon"> 
</head>
<body>
	<!-- Barra de navegaci�n -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark " id="top-bar">
			<div class="navbar-brand">
				<img src="C:/Users/Alma/eclipse-workspace/crud-menu/WebContent/Icons/admin.svg" width="40" height="40" class="" alt="">
  				<a class="navbar-brand text-white">Administraci�n Yum</a>
			</div>
  			<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		   	 <div class="navbar-nav">
		     	 <a class="nav-item nav-link" href= "adminMenu?action=nuevaCategoria">A�adir categor�a</a>
		   	 </div>
		    </div>
		    <div class="navbar-brand" id="buscar">
		    	<form action ="adminMenu?action=buscarPorCategoria" method= "post" class="form-inline">
		    		<div class="form-group">
		    			<small class="form-text text-muted">Buscar categor�a</small>
		    			<input class="form-control" type="text" name="nombre" value="Nombre">
		    		</div>
		    		<button class="btn btn-outline-primary"  type="submit">Buscar</button>
				</form>	
		    </div>
			<div class="navbar-brand" id="cerrar-sesion">
				<a class="nav-item" href="#">Cerrar Sesi�n</a>
				<img src="C:/Users/Alma/eclipse-workspace/crud-menu/WebContent/Icons/cerrar-sesion.svg" width="30" height="40" class="ml-3" alt="">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="	#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="	Toggle navigation">
  				<span class="navbar-toggler-icon"></span>
  				</button>
  			</div>
		</nav>
	
	<h1>Crear nueva categoría</h1>
	<form action="adminMenu?action=agregarCategoria" method="post">
		<div class="form-group">
			<label>ID:</label>
			<input type="number" name="id" required><br>
			<label>Nombre</label>
			<input type="text" name="nombre" required placeholder="Nombre"><br>
		</div>
		<input type="submit" value="Agregar" name="agregar">
		<a href ="adminMenu?action=mostrarMenu">Cancelar</a>
	</form>
</body>
</html>