<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Crear nueva categoría</h1>
	<form action="adminMenu?action=agregarCategoria" method="post">
		<label>ID:</label>
		<input type="number" name="id" required placehorder="Id"><br>
		<label>Nombre</label>
		<input type="text" name="nombre" required placeholder="Nombre"><br>
		<input type="submit" value="Agregar" name="agregar">
		<a href ="adminMenu?action=mostrarMenu">Cancelar</a>
	</form>
</body>
</html>