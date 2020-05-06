package modelo;
import java.util.Random;
public class Repartidor extends Persona {
	private int idRepartidor;

	/**
	* Default empty Repartidor constructor
	*/
	public Repartidor() {
		super();
	}

	/**
	* Default Repartidor constructor
	*/
	public Repartidor(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico) {
		super(nombre, apellidoPaterno, apellidoMaterno, correoElectronico);
		
	}
	

	/**
	* Returns value of idRepartidor
	* @return
	*/
	public int getIdRepartidor() {
		return this.idRepartidor;
	}

	/**
	* Sets new value of idRepartidor
	* @param
	*/
	public void setIdRepartidor(int idRepartidor) {
		this.idRepartidor = idRepartidor;
	}
	
	/**
	 * Genera y asigna una contraseña automática al repartidor
	 */
	
	public void generarPassword() {
        String charsCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String chars = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String symbols = "!@#$%^&*_=+-/€.?<>)";

        String passSymbols = charsCaps + chars + nums + symbols;
        Random rnd = new Random();
        
        char[] passwordGenerada = new char[10];
        String password = "";
       
        for (int i = 0; i < 10; i++) 
        {
            passwordGenerada[i] = passSymbols.charAt(rnd.nextInt(passSymbols.length()));
            password += passwordGenerada[i];
            
            
        }
        
        this.setPassword(password);
	}
	
	public Boolean equals(Repartidor repartidor) {
		return this.idRepartidor == repartidor.idRepartidor;
	}
	
	public String toString() {
		String string = "---------------------------------------------------------------------\n";
		string += "| " + this.getIdPersona() + "| " + this.getNombre() +"| " +  this.getApellidoPaterno() +"| " + this.getApellidoMaterno() +"| "  + this.getCorreoElectronico() +"| " + idRepartidor+"| " + this.getPassword()+ "|\n "  ;
		 string += "---------------------------------------------------------------------\n";
		return string;
	}
}
