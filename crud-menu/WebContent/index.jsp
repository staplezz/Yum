<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>crud-menu</title>
</head>
<body>
	<h1> Here we go again</h1>
	<h2> Selecciona lo que quieres hacer</h2>
	<ul>
		<li><a href ="adminMenu?action=mostrarMenu">Mostrar menú</a> </li>
		<li><a href= "modificadorCliente?action=registraCliente">Registrar cliente</a>
		<li><a href= "adminMenu?action=showEditar">Editar categoría</a>
		<li><a href= "adminMenu?action=nuevaCategoria">Añadir categoría</a>
	</ul>
</body>
</html>