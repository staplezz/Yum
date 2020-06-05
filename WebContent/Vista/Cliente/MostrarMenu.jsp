<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	
	<!-- Style CSS para lo demás -->
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/CSS/style.css">
	
	  <!-- Custom styles for this template -->
  	<link href="${pageContext.request.contextPath}/CSS/simple-sidebar.css" rel="stylesheet">

	<title>Menú Yum</title>
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
				<li class="nav-item active"><a class="nav-link mr-3" href="muestraMenu?action=mostrarAlimentos&idCategoria=1">Menú
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
				<li class="nav-item mr-3"><a class="nav-link" href="modificadorCarrito?action=verCarrito">Carrito</a>
				</li>
				<li><img
					src="${pageContext.request.contextPath}/Icons/cuenta.svg" width="30"
					height="30" class="d-inline-block align-top mr-2" alt=""></li>
				<li class="nav-item"><a class="nav-link" href="modificadorCliente?action=mostrarEditarCliente">Cuenta</a></li>
			</ul>
			<span class="navbar-text"> <a href="logout?">Cerrar
					Sesión</a>
			</span>
		</div>
	</nav>
	
	<div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <div class="sidebar-heading">Categorías</div>
      <div class="list-group list-group-flush">
		<c:forEach var="categoria" items="${categorias}">
	 		<a href="muestraMenu?action=mostrarAlimentos&idCategoria=${categoria.getId()}" class="list-group-item list-group-item-action bg-light">${categoria.getNombre()}</a>
		</c:forEach>
      </div>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
    	<!--  Título de la página. -->
   		<div class="d-flex">
   			<div class= "px-2 py-2">
   				<button class="btn btn-outline-info" id="menu-toggle">Categorías</button>
   			</div>
   			<div class="container-fluid text-center">
			<h1 class="display-4 text-center title border-bottom">${nombreCategoria}</h1>
			</div>
		</div>
		
		<div class="container text-center">
		  <div class="row justify-content-center">
		  	<c:forEach var="alimento" items="${alimentosCategoria}" varStatus="i">
		  		<c:if test="${i.getCount() % 2 == 0}">
				  	<!--  Un alimento. -->
				    <div class="col">
				    	<div class="container-fluid border-bottom">
							<div class="d-flex flex-column bd-highlight mb-3">
								
							  	<div class="p-2 bd-highlight">
							  		<img class="rounded" src="http://localhost:8080/Yum/imagenes/${alimento.path}" width="250" height="250" class="d-inline-block align-top mr-2" alt="">
						  		</div>
						  		<div class="p-2 bd-highlight">
						  			<h3>${alimento.getNombre()}</h3>
					  			</div>
							  	<div class="d-flex justify-content-center">
							  		<p class="mb-1 w-75 text-center">${alimento.getDescripcion()}</p>
							  	</div>
							  	<div class="p-2 bd-highlight">
						  			<h5 class="font-weight-light">Precio: $${alimento.getPrecio()}</h5>
					  			</div>
					  			<div class="p-2 bd-highlight">
						  			<a class="btn btn-warning" href="muestraMenu?action=agregaAlimentoCarrito&idAlimento=${alimento.getId()}&idCliente=${cliente.getIdCliente()}">Agregar al carrito</a>
					  			</div>
							</div>
			    		</div>
				    </div>
				    <div class="w-100"></div>
    			</c:if>
    			
		  		<c:if test="${i.getCount() % 2 != 0}">
				  	<!--  Un alimento. -->
				    <div class="col">
				    	<div class="container-fluid border-bottom">
							<div class="d-flex flex-column bd-highlight mb-3">
								
							  	<div class="p-2 bd-highlight">
							  		<img class="rounded" src="http://localhost:8080/Yum/imagenes/${alimento.path}" width="250" height="250" class="d-inline-block align-top mr-2" alt="">
						  		</div>
						  		<div class="p-2 bd-highlight">
						  			<h3>${alimento.getNombre()}</h3>
					  			</div>
							  	<div class="d-flex justify-content-center">
							  		<p class="mb-1 w-75 text-center">${alimento.getDescripcion()}</p>
							  	</div>
							  	<div class="p-2 bd-highlight">
						  			<h5 class="font-weight-light">Precio: $${alimento.getPrecio()}</h5>
					  			</div>
					  			<div class="p-2 bd-highlight">
						  			<a class="btn btn-warning" href="muestraMenu?action=agregaAlimentoCarrito&idAlimento=${alimento.getId()}&idCliente=${cliente.getIdCliente()}">Agregar al carrito</a>
					  			</div>
							</div>
			    		</div>
				    </div>
    			</c:if>		  	
			</c:forEach>
		  </div>
		</div>
		
	  <!-- 
      <div class="container-fluid">
        <h1 class="mt-4">Simple Sidebar</h1>
        <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p>
        <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>. The top navbar is optional, and just for demonstration. Just create an element with the <code>#menu-toggle</code> ID which will toggle the menu when clicked.</p>
      </div>
    
    <!-- /#page-content-wrapper -->

 	</div>
  </div>
  <!-- /#wrapper -->
  	
  	  <!-- Menu Toggle Script -->
	  <script>
	    $("#menu-toggle").click(function(e) {
	      e.preventDefault();
	      $("#wrapper").toggleClass("toggled");
	    });
	  </script>


</body>
</html>