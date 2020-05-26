package modelo;

public class Alimento {
	private int id; 
	private String nombre; 
	private double precio; 
	private String descripcion; 
	private int idCategoria;
	// Donde se guardará la imágen del alimento.
	private String path;
	private String nombreCategoria;
	
	public Alimento(int id, String nombre, double precio, 
			String descripcion, int idCategoria, String path,
			String nombreCategoria){
		this.id = id;
		this.nombre = nombre; 
		this.precio = precio; 
		this.descripcion = descripcion; 
		this.idCategoria = idCategoria;
		this.path = path;
		this.nombreCategoria = nombreCategoria;
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
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

}
