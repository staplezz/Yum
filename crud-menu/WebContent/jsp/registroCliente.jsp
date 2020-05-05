<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registro</title>
</head>
<body>
		<form action = "modificadorCliente?action=agregaCliente" method="post">
		<table 
			style="background-color: skyblue; margin-left: 20px;margin-left: 20px;">
			<tr>
				<td><h3 style="color: black;">Ingresa los datos </h3></td>
			</tr>
		
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre"required></td>
			</tr>
			
			<tr>
				<td>Apellido Paterno:</td>
				<td><input type="text" name="apePat" required></td>
			</tr>
			
			<tr>
				<td>Apellido Materno:</td>
				<td><input type="text" name="apeMat" required></td>
			</tr>
						
			<tr>
				<td>Correo electronico:</td>
				<td><input type="text" name="email" required></td>
			</tr>
			
			<tr>
				<td>Contraseña:</td>
				<td><input type="password" name="password" required></td>
			</tr>
			
			<tr>
				<td>Teléfono:</td>
				<td><input type="text" name="telefono" required></td>
			</tr>
			
			<tr>
				<td>Delegación</td>
				<td>Colonia</td>
				<td>Calle</td>
			</tr>
			<tr>
				<td>	
					<input type="text" name="delegacion" class="form-control" placeholder="Delegación" required>
				</td>
				<td>
					<input type="text" name="colonia" class="form-control" placeholder="Colonia" required>
				</td>
				<td>
					<input type="text" name="calle" class="form-control" placeholder="Calle" required>
				</td>
			</tr>
			<tr>
				<td>Número interior</td>
				<td>Número exterior</td>
			</tr>
			<tr>
				<td>
					<input type="number" name="numInt" class="form-control" placeholder="Número interior">
				</td>
				<td>
					<input type="number" name="numExt" class="form-control"  placeholder="Número exterior">
				</td>
			</tr>
			
			<tr>
				<td><input type="submit" name="submit" value="Registrar"></td>
				<td></td>
			</tr>
			
			
		</table>
	</form>
</body>
</html>