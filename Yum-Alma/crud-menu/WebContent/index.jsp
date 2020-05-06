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
		<li><a href= "modificadorCliente?action=mostrarEditarCliente&idCliente=1">Editar cliente</a>
	</ul>
	<form method="post" action="enter.jsp">
		<table style="background-color: skyblue; margin-left: 20px;margin-left: 20px;">
		<tr>
		<td><h3 style="color: black;">Inicio de sesión Yum </h3></td>
		</tr>
		
		<tr><td>Correo electronico:</td><td><input type="text" name="email" required></td></tr>
		<tr><td>Contraseña:</td><td><input type="password" name="password" required></td></tr>
		<tr><td><input type="submit" value="Ingresar"></td>
		<td><a href="modificadorCliente?action=registraCliente">Regístrate aquí</a></td></tr>
		</table>
	</form>
</body>
</html>