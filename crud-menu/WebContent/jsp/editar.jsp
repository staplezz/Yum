<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categoría</title>
</head>
<body>
	<form action="adminMenu?action=editarCategoria&id=<c:out value="${categoria.id}"/>" method="post">
		<table>
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="nombre" value="<c:out value="${categoria.nombre}"></c:out>"></td>
				<td><input type="submit" name="registrar" value="Guardar"></td>
			</tr>
		</table>
	</form>
	<table border="1" width="100%">
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Precio </td>
			<td>Descripcion </td>
			<td>Acción</td>
		</tr>
		<c:forEach var="alimento" items="${categoria.listaAlimentos}">
			<tr>
				<td><c:out value="${alimento.id}"/></td>
				<td><c:out value="${alimento.nombre}"/></td>
				<td><c:out value="${alimento.getPrecio()}"/></td>
				<td><c:out value="${alimento.getDescripcion()}"/></td>
				<td><a href="adminArticulo?action=eliminar&categoria=<c:out value="${alimento}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
	<a href ="adminMenu?action=mostrarMenu">Mostrar menú</a>
</body>
</html>