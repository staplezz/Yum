<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menú</title>
</head>
<body>
	<h1>Menú:</h1>
	<h4>${mensaje}</h4>
	<table >
		<tr>
			<td>Categoría</td>
			<td colspan=2>Configuración</td>
			<td>Alimentos</td>
		</tr>
		<c:forEach var="categoria" items="${lista}">
			<tr>
				<td rowspan="${categoria.listaAlimentos.size()+1}"><c:out value="${categoria.nombre}"/></td>
				<td rowspan="${categoria.listaAlimentos.size()+1}"><a href="adminMenu?action=showEditar&id=<c:out value="${categoria.id}" />">Editar</a></td>
				<td rowspan="${categoria.listaAlimentos.size()+1}"><a href="adminMenu?action=eliminarCategoria&id=<c:out value="${categoria.id}"/>">Eliminar</a> </td>
				<c:forEach var="alimento" items="${categoria.listaAlimentos}">
					<tr>
						<td><c:out value="${alimento.nombre}"/></td>
					</tr>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>