/**
 * Script para el CRUD de alimento.
 */

// Deshabilita la selección de input cuando se escribe una categoría.
$("#inputTextCategoria").keyup(function() {
    if ($("#inputTextCategoria").val().length != '') {
    	$("#inputSelectCategoria" ).attr('disabled', 'true');
    } else {
    	$("#inputSelectCategoria" ).removeAttr('disabled');
    }
});

//Igual pero para editar.
$("#inputTextCategoriaE").keyup(function() {
    if ($("#inputTextCategoriaE").val().length != '') {
    	$("#inputSelectCategoriaE" ).attr('disabled', 'true');
    } else {
    	$("#inputSelectCategoriaE" ).removeAttr('disabled');
    }
});

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
		    { "targets": -1, "width": "17%" }
	  	]
	});

	//Obtenemos los datos del registro que queremos editar y lo mostramos.
	$('#tablaAlimentos tbody').on( 'click', '#buttonEditar', function () {
        var data = table.row( $(this).parents('tr') ).data();
        $('#inputAlimentoID').val(data[0]);
        $('#inputAlimentoE').val(data[1]);
        $('#inputPrecioE').val(data[2]);
        $('#textoDescripcionE').val(data[3]);
        $('#inputTextCategoriaE').val(data[4]);

    });
	
	//Obtenemos los datos del registro que queremos borrar .
	$('#tablaAlimentos tbody').on( 'click', '#buttonBorrar', function () {
        var data = table.row( $(this).parents('tr') ).data();
        $('#inputAlimentoDel').val(data[0]);
    });

    $('#mydatatable tbody').on( 'click', '#buttonAgregar', function () {
        var data = table.row( $(this).parents('tr') ).data();
        $('#inputAlimento').val(placeholder);
    } );
});
