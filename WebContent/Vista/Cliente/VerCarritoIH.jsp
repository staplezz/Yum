<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<!-- Bootstrap & DataTables CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.css"/>
	
	<!-- Style CSS para lo demás -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	<meta charset="ISO-8859-1">
	<title>Ver Carrito</title>
	<!-- Icono del título de la página -->
	<link rel="icon"
		href="${pageContext.request.contextPath}/Icons/yum.svg"
		type="image/x-icon">
</head>
<body>
	<!-- Barra de navegación -->
	<nav class="navbar navbar-expand-lg navbar-dark sticky-top"
		style="background-color: #28536B">
		<a class="navbar-brand mr-5" href="#"> <img
			src="${pageContext.request.contextPath}/Icons/yum.svg" width="35"
			height="35" class="d-inline-block align-top mr-2" alt=""> Yum
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#barraNavegacion" aria-controls="barraNavegacion"
			aria-expanded="true" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="barraNavegacion">
			<ul class="navbar-nav mr-auto">
				<li><img
					src="${pageContext.request.contextPath}/Icons/menu.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link mr-3" href="muestraMenu?action=mostrarAlimentos&idCategoria=1">Menú
						<span class="sr-only">(current)</span>
				</a></li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/orden.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link mr-3" href="modificadorCliente?action=mostrarOrdenesActuales">Órdenes</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/carro.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item mr-3 active"><a class="nav-link" href="modificadorCarrito?action=verCarrito">Carrito</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/cuenta.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link" href="modificadorCliente?action=mostrarEditarCliente">Cuenta</a></li>
			</ul>
			<span class="navbar-text"> <a href="logout?">Cerrar Sesión</a>
			</span>
		</div>
	</nav>

	<div class="container">
		<h1 class="text-center title display-4 mt-2">Carrito de compras</h1>
	</div>
	
		<div class="container">
			<div class="container-fluid text-right pr-0 mb-2">
				<a class="btn btn-warning" href="modificadorCarrito?action=vaciarCarrito">Vaciar carrito</a>
			</div>
			
		<!-- Tabla de alimentos -->	
		<table class="table mx-auto text-center border" id="tablaAlimentos">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col"></th>
		      <th scope="col">Producto</th>
		      <th scope="col">Precio</th>
		      <th scope="col">Cantidad</th>
		      <th scope="col">Cambiar</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="alimento" items="${lista}">
		    	<tr >
		    		<td> <img class="img-responsive" src="http://localhost:8080/Yum/imagenes/${alimento.path}" alt="${alimento.nombre}" width="100" height="100"> </td>
		    		<td class="align-middle">${alimento.nombre}</td>
		    		<td class="align-middle">${alimento.precio}</td>
		    		<td class="align-middle">${alimento.cantidad}</td>
		    		<td class="align-middle">
		    			<a class="btn btn-success mr-2" href="modificadorCarrito?action=agregaAlimento&id=${alimento.getId()}">Agregar al carrito</a>
		    			<a class="btn btn-danger mr-2" href="modificadorCarrito?action=eliminaAlimento&id=${alimento.getId()}">Quitar del carrito</a>
	    			</td>
	    		</tr>
	   		</c:forEach>
	   		<tr>
				<td colspan="5" align="center" class="table-success">Total: $${total}</td>
			</tr>
		  </tbody>
		</table>
	</div>
	
	<div class="container-fluid text-center">
		<a class="btn btn-primary" href="creaOrden?action=seleccionaDireccion">Confirmar orden</a>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small  pt-1">

		<!-- Footer Elements -->

		<!-- Copyright -->
		<div class="footer-copyright text-center py-2">
			2020 Copyright: <a href="#"> Eff;cient organization <img
				class="icon" src="${pageContext.request.contextPath}/Icons/Logo.svg"
				class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
			</a>
		</div>
		<!-- Copyright -->

	</footer>
	<!-- Footer -->
	
	<!-- Footer -->
	
	<!-- JS para bootstrap. -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
</body>
</html>
