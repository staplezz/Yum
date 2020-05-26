/**
* Scripts para el CRUD de alimento.
*/

//Utiliza la biblioteca de datatables para darle formato a la tabla y añade características.
$(document).ready( function () {
	var table = $('#tablaAlimentos').DataTable( {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/1.10.20/i18n/Spanish.json'
		},
		info: false,
		"pagingType": "numbers",
		"columnDefs": [
		    {
		      "data": null,
		      "defaultContent": "<button class='btn btn-primary' data-toggle='modal' data-target='#modalEditar' id='buttonEditar'>Editar</button> <button class='btn btn-danger' data-toggle='modal' data-target='#modalEliminaRegistro' id='buttonBorrar'>Eliminar</button>",
		      "targets": -1
		    },
		    { "targets": -1, "width": "17%" },
		    {"className": "dt-center", "targets": "_all"}
	  	]
	});

	//Obtiene los datos del registro que queremos editar y lo mostramos.
	$('#tablaAlimentos tbody').on( 'click', '#buttonEditar', function () {
	    var data = table.row( $(this).parents('tr') ).data();
	    $('#inputAlimentoID').val(data[1]);
	    $('#inputAlimentoE').val(data[2]);
	    $('#inputPrecioE').val(data[3]);
	    $('#textoDescripcionE').val(data[4]);
	    $('#inputTextCategoriaE').val(data[5]);
	
	});
		
	//Obtiene los datos del registro que queremos borrar .
	$('#tablaAlimentos tbody').on( 'click', '#buttonBorrar', function () {
	    var data = table.row( $(this).parents('tr') ).data();
	    $('#inputAlimentoDel').val(data[1]);
	});
	
	/**
	 //No sé que hace
	$('#mydatatable tbody').on( 'click', '#buttonAgregar', function () {
	    var data = table.row( $(this).parents('tr') ).data();
	    $('#inputAlimento').val(placeholder);
	});
	 */
});

// Quita los datos del modal al hacer data dismiss para evitar bugs
// al recargar la página.
$('#modalRegistro').on('hidden.bs.modal', function () {
    $(this).find('form').trigger('reset');
})
