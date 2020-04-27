<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alimentos</title>
</head>
<body>
	<h1>Es posible que quieras agregar algunos elementos sin clasificar...</h1>
	<a href ="adminMenu?action=mostrarMenu">Listo</a>
	
	<table border="1">
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Precio </td>
			<td>Descripcion </td>
			<td>Agregar</td>
		</tr>
		<c:forEach var="alimento" items="${alimentos}">
			<tr>
				<td><c:out value="${alimento.id}"/></td>
				<td><c:out value="${alimento.nombre}"/></td>
				<td><c:out value="${alimento.getPrecio()}"/></td>
				<td><c:out value="${alimento.getDescripcion()}"/></td>
				<td><a href="adminMenu?action=agregarAlimento&id=<c:out value="${alimento.id}" />&idCat=<c:out value="${categoria}" />">Elegir</a></td>			
			</tr>
		</c:forEach>
	</table>
</body>
</html>