<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset=ISO-8859-1">
<title>Categoría</title>
</head>
<body>
	<h1>${categoria.nombre} :</h1>
	<table>
		<tr>
			<td><a href = "adminMenu?action=crudMenu">Ir al menú</a></td>
		</tr>
	</table>
	<table border="1" width="100%">
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Precio </td>
			<td>Descripcion </td>
			<td colspan = 2> Acciones</td>
		</tr>
		<c:forEach var="alimento" items="${categoria.listaAlimentos}">
			<tr>
				<td><c:out value="${alimento.id}"/></td>
				<td><c:out value="${alimento.nombre}"/></td>
				<td><c:out value="${alimento.getPrecio()}"/></td>
				<td><c:out value="${alimento.getDescripcion()}"/></td>
				<td><a href="adminMenu?action=eliminar&nombre=<c:out value="${alimento.nombre}" />">Editar</a></td>			
			</tr>
		</c:forEach>
	</table>
</body>
</html>