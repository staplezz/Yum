<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html lang="es">
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<!-- Datatables. -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Administración Yum</title>
	<link rel = "icon" href = "${pageContext.request.contextPath}/Icons/admin-icon.svg" type = "image/x-icon"> 
</head>

<body>
	<!-- Modal para agregar un registro -->
	<div class="modal fade" id="modalRegistro" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Agregar Alimento</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="adminAlimento" method="post">
	      <div class="modal-body">
	      	<!-- Formulario para agregar un registro -->
	      		<div class="form-group row">
	      			<label for="inputAlimento" class="col-sm-2 col-form-label">Alimento</label>
   					<div class="col-sm-10">
   					  <input type="text" class="form-control" name="nombreAl" id="inputAlimento" placeholder="Nombre del Alimento"/>
   					</div>
	      		</div>

	      		<div class="form-group row">
	      			<label for="inputPrecioA" class="col-sm-2 col-form-label">Precio</label>
	      			<div class="col-sm-5">
   					  <input type="text" class="form-control" name="precio" id="inputPrecioA" placeholder="Precio del Alimento"/>
   					</div>
	      		</div>

	      		<label for="inputCategoria">Categoría</label>
     		        <div class="row" id="inputCategoria">
			      <div class="col">
			        <select id="inputSelectCategoria" class="form-control">
				        <option selected>Elegir...</option>
		        		<option>Alimentos</option>
		        		<option>Bebidas	</option>
			      	</select>
			      </div>
			      <div class="col">
			        <input id="inputTextCategoria" name="categoria" type="text" class="form-control" placeholder="Categoria"/>
			      </div>
			    </div>

	      		<div class="form-group">
      			    <label for="textoDescripcion">Descripción</label>
					<textarea class="form-control" name="descripcion" id="textoDescripcion" rows="2"></textarea>
	      		</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	        <input type="submit" class="btn btn-primary" name="agregar" value="Agregar" />
	      </div>
	      </form>
	    </div>
	    
	  </div>
	</div>
	
	<!-- Modal para editar un registro -->
	<div class="modal fade" id="modalEditar" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle">Editar Alimento</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <form action="adminAlimento" method="post">
	      <div class="modal-body">
	      	<!-- Formulario para editar un registro -->
	      		<div class="form-group row">
   					<div class="col-sm-3">
   					  <input class="form-control" name="idAl" id="inputAlimentoID" placeholder="Id del Alimento"/>
   					</div>
	      		</div>
	      		
	      		<div class="form-group row">
	      			<label for="inputAlimentoE" class="col-sm-2 col-form-label">Alimento</label>
   					<div class="col-sm-10">
   					  <input type="text" class="form-control" name="nombre" id="inputAlimentoE" placeholder="Nombre del Alimento"/>
   					</div>
	      		</div>

	      		<div class="form-group row">
	      			<label for="inputPrecioE" class="col-sm-2 col-form-label">Precio</label>
	      			<div class="col-sm-5">
   					  <input type="text" class="form-control" name="precio" id="inputPrecioE" placeholder="Precio del Alimento"/>
   					</div>
	      		</div>

	      		<label for="inputCategoriaE">Categoría</label>
     		        <div class="row" id="inputCategoriaE">
			      <div class="col">
			        <select id="inputSelectCategoriaE" class="form-control">
				        <option selected>Elegir...</option>
		        		<option>Alimentos</option>
			      	</select>
			      </div>
			      <div class="col">
			        <input id="inputTextCategoriaE" type="text" name="categoria" class="form-control" placeholder="Categoria"/>
			      </div>
			    </div>

	      		<div class="form-group">
      			    <label for="textoDescripcionE">Descripción</label>
					<textarea class="form-control" name="descripcion" id="textoDescripcionE" rows="2"></textarea>
	      		</div>

	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	        <input type="submit" class="btn btn-primary" name="editar" value="Guardar cambios" />
	      </div>
	      </form>
	    </div>
	  </div>	  
	</div>
	
	<!-- Modal para eliminar un registro -->
	<div id="modalEliminaRegistro" class="modal fade">
		<div class="modal-dialog modal-dialog-centered modal-confirm">
			<div class="modal-content">
				<div class="modal-header">		
					<h4 class="modal-title">¿Estás seguro?</h4>	
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<form action="adminAlimento" method="post">
				<div class="modal-body">
					<input type="hidden" class="form-control" name="idAl" id="inputAlimentoDel" placeholder="Id del Alimento"/>
					<p>¿Estás seguro de que deseas borrar este registro?</p>
					<p>Este proceso no se puede deshacer.</p>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-danger" name="eliminar" value="Borrar Alimento" />
					<button type="button" class="btn btn-info" data-dismiss="modal">Cancelar</button>
				</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Barra de navegación -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="top-bar">
		<a class="navbar-brand" href="#">
    	<img src="${pageContext.request.contextPath}/Icons/admin.svg" width="30" height="30" class="d-inline-block align-top mr-2" alt="">
    	Administración Yum
  		</a>
  		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#barraNavegacion" aria-controls="barraNavegacion" aria-expanded="false" aria-label="Toggle navigation">
	   		<span class="navbar-toggler-icon"></span>
	  	</button>
	  	
	  	  <div class="collapse navbar-collapse" id="barraNavegacion">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item active">
		        <a class="nav-link" href="#">Alimentos <span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Órdenes</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Menú</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="#">Repartidores</a>
		      </li>
		    </ul>
		    <span class="navbar-text">
		    	<img src="${pageContext.request.contextPath}/Icons/cerrar-sesion.svg" width="30" height="30" class="d-inline-block align-top mr-2" alt="cerrar sesión">
		      <a href=#>Cerrar Sesión</a>
		    </span>
		  </div>
	</nav>
	
	<!--  Título de la tabla -->
	<h1 class="text-center pt-2">Lista de Alimentos</h1>
	
	<!-- Tabla de alimentos -->	
	<div class="container">
		<!-- Herramienta para agregar alimentos -->
		<div class="container pb-2">
			<div class="row justify-content-end">
				<div class="col-sm-auto px-2">
					<img src="${pageContext.request.contextPath}/Icons/anadir.svg" width="30" height="30" alt="">
				</div>
				<div class="col-sm-auto px-2">
					<button type="button" class="btn btn-primary" data-toggle="modal" 
					data-target="#modalRegistro" id="buttonAgregar">Agregar</button>
				</div>
			</div>
		</div>
		<table class="table mx-auto" id="tablaAlimentos">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Nombre</th>
		      <th scope="col">Precio</th>
		      <th scope="col">Descripción</th>
		      <th scope="col">Categoría</th>
		      <th scope="col">Acción</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="alimento" items="${lista}">
		    	<tr>
		    		<td>${alimento.id}</td>
		    		<td>${alimento.nombre}</td>
		    		<td>${alimento.precio}</td>
		    		<td>${alimento.descripcion}</td>
		    		<td>${alimento.categoria}</td>
		    		<td></td>
	    		</tr>
    		</c:forEach>
		  </tbody>
		</table>
	</div>
	
	<!-- JQuery, Popper, Bootstrap y Datatables -->
	<script src="https://code.jquery.com/jquery-3.5.0.js" integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	
	<!-- Script que deshabilita input Agregar -->
	<script src="js/script.js"></script> 
</body>
</html>
