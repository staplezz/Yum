package modelo;

public class Alimento {
	private int id; 
	private String nombre; 
	private double precio; 
	private String descripcion; 
	private int idCategoria; 
	
	public Alimento(int id, String nombre, double precio, String descripcion, int idCategoria){
		this.id = id; 
		this.nombre = nombre; 
		this.precio = precio; 
		this.descripcion = descripcion; 
		this.idCategoria = idCategoria;
	}
	
	//getters and setters 
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
		this.nombre= nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getCategoria() {
		return idCategoria;
	}
	
	public void setCategoria(int categoria) {
		this.idCategoria = categoria;
	}

}
