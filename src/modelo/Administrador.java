package modelo;

public class Administrador extends Persona{

	private int idAdministrador; 
	
	public Administrador() {
		super();
	}
	
	public Administrador(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String password) {
		super(nombre, apellidoPaterno, apellidoMaterno,email); 
		this.setPassword(password);
	}
	
	//getters and setters 
	
	public void setIdAdministrador(int idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	
	public int getIdAministrador() {
		return idAdministrador;
	}
}
