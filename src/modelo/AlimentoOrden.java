package modelo;

/*
 * Clase auxiliar de modelo que sirve para representar
 * un alimento con informaci�n necesaria para el cliente,
 * administrador o repartidor.
 */
public class AlimentoOrden {
	String nombre;
	int cantidad;
	double precio;
	
	public AlimentoOrden(String nombre, int cantidad, double precio) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public double getPrecio() {
		return precio;
	}
}
