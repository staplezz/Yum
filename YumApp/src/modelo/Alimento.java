package modelo;

public class Alimento {
	private int id;
	private String nombre;
	private float precio;
	private String descripcion;
	private String categoria;
	
	public Alimento(int id, String nombre, float precio, String descripcion, String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria= categoria;
		this.precio = precio;
	}
	
	//getters y setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
