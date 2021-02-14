package modelo;

public class Direccion {
	private int idDireccion; 
	private String delegacion; 
	private String colonia; 
	private String calle; 
	private int numInterior; 
	private int numExterior; 
	
	public Direccion(String delegacion, String colonia, String calle) {
		this.delegacion = delegacion;
		this.colonia = colonia; 
		this.calle = calle; 
		this.numInterior = -1; 
		this.numExterior = -1;
	}
	
	//getters y setters 
	
	public int getIdDireccion() {
		return this.idDireccion;
	}
	
	public String getDelegacion() {
		return this.delegacion;
	}
	
	public String getColonia() {
		return this.colonia;
	}
	
	public String getCalle() {
		return this.calle;
	}
	
	public int getNumInterior() {
		return this.numInterior; 
	}
	
	public int getNumExterior() {
		return this.numExterior;
	}
	
	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}
	
	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}
	
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public void setNumInterior(int numInt) {
		this.numInterior = numInt;
	}
	
	public void setNumExterior(int numExt) {
		this.numExterior = numExt;
	}

}
