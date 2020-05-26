package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import modelo.Cliente;
import modelo.Administrador;
import modelo.Repartidor;
import modelo.Conexion;

public class PersonaDAO {
	private Conexion con; 
	private Connection connection; 
	
	public PersonaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword)throws SQLException{
		System.out.println(jdbcURL); 
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	public Cliente buscaCliente(String email) throws SQLException{
		Cliente cliente = null; 
		String sqlCliente = "SELECT persona.idPersona as idPersona, idCliente, nombre, apellidoPaterno, apellidoMaterno, telefono, password  FROM persona join cliente ";
	    sqlCliente+= "WHERE persona.correoElectronico = ? and ";
	    sqlCliente+= "persona.idPersona = cliente.idPersona";
	    
	    con.conectar();
	    connection = con.getJdbcConnection();
	    
	    
	    PreparedStatement statement = connection.prepareStatement(sqlCliente);
	    statement.setString(1, email);
	  
	    
	    ResultSet result = statement.executeQuery();
	    System.out.println("ejecuta query");
	    if (result.next()) {
	    	String nombre = result.getString("nombre");
	    	String apellidoPaterno = result.getString("apellidoPaterno");
	    	String apellidoMaterno = result.getString("apellidoMaterno");
	    	String telefono  = result.getString("telefono");
	    	String password = result.getString("password");
	    	int idPersona = result.getInt("idPersona"); 
	    	int idCliente = result.getInt("idCliente");
	    	
	    	cliente = new Cliente(nombre, apellidoPaterno, apellidoMaterno, email, telefono, password);
	    	cliente.setIdCliente(idCliente);
	    	cliente.setIdPersona(idPersona);
	    	
	    }
	    statement.close();
	    con.desconectar();
		return cliente;
	}
	
	public Administrador buscaAdministrador(String email)throws SQLException {
		Administrador administrador = null; 
		String sqlAdmin = "SELECT persona.idPersona as idPersona, idAdministrador, nombre, apellidoPaterno, apellidoMaterno, password FROM persona join administrador ";
	    sqlAdmin += "WHERE persona.correoElectronico =?  and ";
	    sqlAdmin += "persona.idPersona = administrador.idPersona";
		
	    con.conectar();
	    connection = con.getJdbcConnection();
	    
	    PreparedStatement statement = connection.prepareStatement(sqlAdmin);
	    statement.setString(1, email);
	   
	    
	    System.out.println(statement);
	    ResultSet result = statement.executeQuery();
	    if (result.next()) {
	    	String nombre = result.getString("nombre");
	    	String apellidoPaterno = result.getString("apellidoPaterno");
	    	String apellidoMaterno = result.getString("apellidoMaterno");
	    	String password = result.getString("password");
	    	int idPersona = result.getInt("idPersona"); 
	    	int idAdministrador = result.getInt("idAdministrador");
	    	
	    	System.out.println(apellidoPaterno);
	    	administrador = new Administrador(nombre, apellidoPaterno, apellidoMaterno, email, password);
	    	administrador.setIdAdministrador(idAdministrador);
	    	administrador.setIdPersona(idPersona);
	    }
	    statement.close();
	    con.desconectar();
		return administrador;
	}
	
	public Repartidor buscaRepartidor(String email)throws SQLException{
		Repartidor repartidor = null; 
		String sqlRepartidor = "SELECT persona.idPersona as idPersona , idRepartidor, nombre, apellidoPaterno, apellidoMaterno, password FROM persona join repartidor ";
		sqlRepartidor += "WHERE persona.correoElectronico =?  and ";
        sqlRepartidor += "persona.idPersona = repartidor.idPersona";
		
        con.conectar();
	    connection = con.getJdbcConnection();
	    
	    PreparedStatement statement = connection.prepareStatement(sqlRepartidor);
	    statement.setString(1, email);
	    
	    
	    ResultSet result = statement.executeQuery();
	    if (result.next()) {
	    	String nombre = result.getString("nombre");
	    	String apellidoPaterno = result.getString("apellidoPaterno");
	    	String apellidoMaterno = result.getString("apellidoMaterno");
	    	String password = result.getString("password");
	    	int idPersona = result.getInt("idPersona"); 
	    	int idRepartidor = result.getInt("idRepartidor");
	    	
	    	repartidor = new Repartidor(nombre, apellidoPaterno, apellidoMaterno, email);
	    	repartidor.setPassword(password);
	    	repartidor.setIdPersona(idPersona);
	    	repartidor.setIdRepartidor(idRepartidor);
	    	
	    }
	    
	    statement.close();
	    con.desconectar();
		return repartidor;
	}

}
