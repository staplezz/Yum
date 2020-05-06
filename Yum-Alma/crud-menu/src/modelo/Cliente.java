package modelo;

public class Cliente extends Persona {
	
	//telephone 
	private int idCliente;
	private String telefono; 
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono, String password) {
		super(nombre, apellidoPaterno, apellidoMaterno, email); 
		this.telefono = telefono;	
		this.setPassword(password);
	}
	
	//getters and setters 
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getIdCliente() {
		return this.idCliente;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono; 
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
}
