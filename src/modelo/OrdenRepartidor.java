package modelo;

/* 
* Clase auxiliar para mostrar las �rdenes del repartidor
* en su respectiva pantalla de administraci�n.
*/
public class OrdenRepartidor {
	int idOrden;
	int estado;
	String cliente;
	String direccion;
	
	public OrdenRepartidor(int idOrden, int estado, String cliente,
			String direccion) {
		this.idOrden = idOrden;
		this.estado = estado;
		this.cliente = cliente;
		this.direccion = direccion;
	}
	
	public int getId() {
		return idOrden;
	}

	public int getEstado() {
		return estado;
	}
	
	public String getCliente() {
		return cliente;
	}
	
	public String getDireccion() {
		return direccion;
	}
}
