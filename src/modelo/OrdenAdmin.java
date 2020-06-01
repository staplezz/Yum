package modelo;

import java.sql.Date;

/* 
* Clase auxiliar para mostrar las órdenes del administrador y del repartidor
* en su respectiva pantalla de administración.
*/
public class OrdenAdmin {
	int idOrden;
	Date fecha;
	int estado;
	int calificacion;
	String repartidor;
	String cliente;
	
	public OrdenAdmin(int idOrden, Date fecha, int estado, int calificacion,
			String repartidor, String cliente) {
		this.idOrden = idOrden;
		this.fecha = fecha;
		this.estado = estado;
		this.calificacion = calificacion;
		this.repartidor = repartidor;
		this.cliente = cliente;
	}
	
	public int getId() {
		return idOrden;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public int getEstado() {
		return estado;
	}
	
	public String getNombreEstado() {
		return nombreEstado(estado);
	}
	
	public String getNombreEstadoSiguiente() {
		return nombreEstado(estado + 1);
	}
	
	public String getRepartidor() {
		return repartidor;
	}
	
	public String getCliente() {
		return cliente;
	}
	
	public int getCalificacion() {
		return calificacion;
	}
	
	//Auxiliar para los nombres de estados.
	public String nombreEstado(int estado) {
		if(estado == 1) {
			return "En proceso de preparación.";
		}
		else if(estado == 2) {
			return "Lista para entregar.";
		}
		else if(estado == 3) {
			return "En proceso de entrega.";
		}
		else if(estado == 4) {
			return "Orden entregada.";
		}
		else {
			return "Estado no válido";
		}
	}
	
}