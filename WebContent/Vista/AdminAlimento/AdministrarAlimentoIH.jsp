<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
	<!-- Bootstrap & DataTables CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.css"/>
	
	<!-- Style CSS para lo demás -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/style.css">
	
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
	      <form action="adminAlimento" method="post" enctype="multipart/form-data">
	      <div class="modal-body">
	      	<!-- Formulario para agregar un registro -->
	      		<div class="form-group row">
	      			<label for="inputAlimento" class="col-sm-2 col-form-label">Alimento</label>
   					<div class="col-sm-10">
   					  <input type="text" class="form-control" name="nombreAl" id="inputAlimento" placeholder="Nombre del Alimento" required/>
   					</div>
	      		</div>

	      		<div class="form-group row">
	      			<label for="inputPrecioA" class="col-sm-2 col-form-label">Precio</label>
	      			<div class="col-sm-5">
   					  <input type="number" class="form-control" name="precio" id="inputPrecioA" min="0" max="500" value="0" step="0.01" placeholder="Precio del Alimento" required/>
   					</div>
	      		</div>
				
				<div class="form-group row">
					<label for="inputTextCategoria" class="col-sm-2 col-form-label">Categoría</label>
					<div class="col-sm-10">
			        	<input id="inputTextCategoria" name="categoria" type="text" class="form-control sm" placeholder="Categoria" required/>
			      	</div>
				</div>

	      		<div class="form-group">
      			    <label for="textoDescripcion">Descripción</label>
					<textarea class="form-control" name="descripcion" id="textoDescripcion" rows="2"></textarea>
	      		</div>
	      		
	      		<div class="form-group row">
	      			<label for="inputImagen" class="col-sm-4 col-form-label">Archivo de imágen</label>
	      				<input type="file" id="inputImagen" name="archivoImagen" />
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
	      <form action="adminAlimento" method="post" enctype="multipart/form-data">
	      <div class="modal-body">
	      	<!-- Formulario para editar un registro -->
	      		<div class="form-group row">
	      			<label for="inputAlimentoE" class="col-sm-2 col-form-label">id</label>
   					<div class="col-sm-3">
   					  <input class="form-control" name="idAl" id="inputAlimentoID" placeholder="Id del Alimento" readonly required/>
   					</div>
	      		</div>
	      		
	      		<div class="form-group row">
	      			<label for="inputAlimentoE" class="col-sm-2 col-form-label">Alimento</label>
   					<div class="col-sm-10">
   					  <input type="text" class="form-control" name="nombre" id="inputAlimentoE" placeholder="Nombre del Alimento" required/>
   					</div>
	      		</div>

	      		<div class="form-group row">
	      			<label for="inputPrecioE" class="col-sm-2 col-form-label">Precio</label>
	      			<div class="col-sm-5">
	      			<input type="number" class="form-control" name="precio" id="inputPrecioE" min="0" max="500" value="0" step="0.01" placeholder="Precio del Alimento" required/>
   					</div>
	      		</div>

	      		<div class="form-group row">
					<label for="inputTextCategoriaE" class="col-sm-2 col-form-label">Categoría</label>
					<div class="col-sm-10">
			        	<input id="inputTextCategoriaE" name="categoria" type="text" class="form-control sm" placeholder="Categoria" required/>
			      	</div>
				</div>

	      		<div class="form-group">
      			    <label for="textoDescripcionE">Descripción</label>
					<textarea class="form-control" name="descripcion" id="textoDescripcionE" rows="2"></textarea>
	      		</div>
	      		
	      		<div class="form-group row">
	      			<label for="inputImagen" class="col-sm-4 col-form-label">Archivo de imágen</label>
	      				<input type="file" id="inputImagen" name="archivoImagen" />
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
					<input type="hidden" class="form-control" name="idAl" id="inputAlimentoDel" placeholder="Id del Alimento" readonly/>
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

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"
		id="top-bar">
		<div class="navbar-brand">
			<img src="${pageContext.request.contextPath}/Icons/admin.svg"
				width="30" height="30" class="" alt=""> <a
				class="navbar-brand text-white">Administración Yum</a>
		</div>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link" href="adminMenu?action=mostrarMenu">Menú</a>
				<a class="nav-item nav-link" href="adminOrden?action=mostrarOrdenes">Órdenes</a> 
				<a class="nav-item nav-link active" href="adminAlimento">Alimentos</a> 
				<a class="nav-item nav-link" href="modificadorRepartidor?action=mostrar">Repartidores</a>
			</div>
		</div>
		<div class="navbar-brand" id="cerrar-sesion">
			<a class="nav-item" href="logout?">Cerrar Sesión</a> <img
				src="${pageContext.request.contextPath}/Icons/cerrar-sesion.svg"
				width="30" height="30" class="ml-3" alt="">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="	#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
				aria-expanded="false" aria-label="	Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>
	
	<!--  Título de la tabla -->
	<div class="container">
		<h1 class="display-4 text-center title mb-0">Alimentos</h1>
	</div>
	
	<div class="container">
		<!-- Herramienta para agregar alimentos -->
		<div class="container pb-2">
			<div class="row justify-content-end">
				<div class="col-sm-auto px-2">
					<button type="button" class="btn btn-primary" data-toggle="modal" 
					data-target="#modalRegistro" id="buttonAgregar">Agregar Alimento</button>
				</div>
			</div>
		</div>
		<!-- Tabla de alimentos -->	
		<table class="table mx-auto text-center table-striped table-bordered" id="tablaAlimentos">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Imágen</th>
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
		    		<td> <img class="img-responsive" src="http://localhost:8080/Yum/imagenes/${alimento.path}" alt="${alimento.nombre}" width="100" height="100"> </td>
		    		<td>${alimento.id}</td>
		    		<td>${alimento.nombre}</td>
		    		<td>${alimento.precio}</td>
		    		<td>${alimento.descripcion}</td>
		    		<td>${alimento.nombreCategoria}</td>
		    		<td></td>
	    		</tr>
	   		</c:forEach>
		  </tbody>
		</table>
	</div>
	
	<!-- Footer -->
	<footer class="page-footer font-small  pt-1">

		<!-- Footer Elements -->

		<!-- Copyright -->
		<div class="footer-copyright text-center py-1 fixed-bottom">
			2020 Copyright: <a href="#"> Eff;cient organization <img
				class="icon" src="${pageContext.request.contextPath}/Icons/Logo.svg"
				class="img-fluid img-thumbnail" alt="Editar" width="40" height="40">
			</a>
		</div>
		<!-- Copyright -->

	</footer>
	<!-- Footer -->
	
	<!-- JS para bootstrap. -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
	<!-- JS para datatable. -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.js"></script>
	
	<!-- Script para DT y modals. -->
	<script src="js/script.js"></script>
</body>
</html>
