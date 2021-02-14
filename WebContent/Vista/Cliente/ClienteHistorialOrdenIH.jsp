<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet"
		href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
		
	<!-- Para calificar -->
	<script src="https://kit.fontawesome.com/cdcab3bf36.js" crossorigin="anonymous"></script>
	<script data-search-pseudo-elements defer src="https://use.fontawesome.com/releases/latest/js/all.js" 
		integrity="sha384-L469/ELG4Bg9sDQbl0hvjMq8pOcqFgkSpwhwnslzvVVGpDjYJ6wJJyYjvG3u8XW7" crossorigin="anonymous"></script>
	
	<!-- Style CSS para lo dem�s -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/CSS/style.css">
	<style type="text/css">
	.title {
		margin-top: 70px;
	}
	
	.final {
		margin-bottom: 60px;
		
	.icon::before {
  display: inline-block;
  font-style: normal;
  font-variant: normal;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
}
.stars-outer {
  display: inline-block;
  position: relative;
  font-family: "Font Awesome 5 Free";
}
.stars-outer::before {
  content: "\f005 \f005 \f005 \f005 \f005";
}
.stars-inner {
  position: absolute;
  top: 0;
  left: 0;
  white-space: nowrap;
  overflow: hidden;
  width : 0;
}
.stars-inner::before {
  content: "\f005 \f005 \f005 \f005 \f005";
  color: #f8ce0b;
}
	}
	</style>
	<title>Historial �rdenes</title>
	<!-- Icono del t�tulo de la p�gina -->
	<link rel="icon"
		href="${pageContext.request.contextPath}/Icons/yum.svg"
		type="image/x-icon">
</head>
<body>
	<!-- Barra de navegaci�n -->
	<nav class="navbar navbar-expand-lg navbar-dark"
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
				<li class="nav-item"><a class="nav-link mr-3" href="muestraMenu?action=mostrarAlimentos&idCategoria=1">Men�
				
				</a></li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/orden.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item active"><a class="nav-link mr-3" href="modificadorCliente?action=mostrarOrdenesActuales">�rdenes</a>
						<span class="sr-only">(current)</span>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/carro.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item mr-3"><a class="nav-link" href="modificadorCarrito?action=verCarrito">Carrito</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/cuenta.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link" href="modificadorCliente?action=mostrarEditarCliente">Cuenta</a></li>
			</ul>
			<span class="navbar-text"><a href="logout?">Cerrar
					Sesi�n</a>
			</span>
		</div>
	</nav>

	<div class="container">
		<h1 class="text-center title">Tu historial de �rdenes</h1>
	
		<div class="container ">
			<table class="table mx-auto text-center table-striped table-bordered" id="tablaOrdenes">
				<thead class="thead-light">
					<tr>
					<th class="text-center" scope="col">Acci�n</th>
						<th class="text-center" scope="col">Fecha</th>
						<th class="text-center" scope="col">Estado</th>
					 	<th class="text-center" scope="col">Repartidor</th>
					 	<th class="text-center" scope="col">Calificaci�n <i class="far fa-star"></i> </th>
					 </tr>
						 
				</thead>
				
					<tbody>
						<c:forEach var="orden" items="${ordenes}">
							<tr class="orden${orden.getId()}" id ="ref">
							<td><a class="btn btn-primary"  href="modificadorCliente?action=verOrden&idOrden=${orden.getId()}">
								Ver orden</a></td>
								<td><c:out value="${orden.fecha}" /></td>
								<td><c:out value="${orden.getNombreEstado()}" /></td>
								<td ><c:out value="${orden.repartidor}" /></td>
								<td class="w-25">
									<form action= "modificadorCliente?action=calificarOrden&idO=${orden.getId()}" method="post" class="form-inline">
										<select  id="calificacion" class="custom-select my-1 mr-sm-2"  name="calificacion" >
										    <option selected  ><c:out value="${orden.getCalificacion()}" /></option>
										    <option value="1">1</option>
										    <option value="2">2</option>
										    <option value="3">3</option>
										    <option value="4">4</option>
										    <option value="5">5</option>
										  </select>
										<button class="btn btn-outline-primary "   type="submit">Calificar orden</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div class="container px-0 final">
			<!-- Herramienta para regresar a �rdenes -->
			<div class="container">
				<div class="text-center">
					<h5><a href="modificadorCliente?action=mostrarOrdenesActuales" class="btn btn-secondary">Regresar</a></h5>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small  pt-1 footer fixed-bottom footer">

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
</body>
</html>