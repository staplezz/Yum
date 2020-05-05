package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 

import modelo.Persona;
import modelo.Cliente;
import modelo.Conexion;

public class RegistroClienteDAO {
	private Conexion con;
	private Connection connection;
	
	public RegistroClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword)throws SQLException{
		System.out.println(jdbcURL); 
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	//Crear cliente 
	public boolean crearCliente(Cliente cliente)throws SQLException{
		boolean personInserted = false; 
		
		String sqlPersona = "INSERT INTO persona(nombre, apellidoPaterno, apellidoMaterno, password, correoElectronico) VALUES(?,?,?,?,?) ";
		String sqlCliente = "INSERT INTO cliente(salt, telefono, idPersona) VALUES(1, ?, ?)";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement personaStatement = connection.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);
		personaStatement.setString(1, cliente.getNombre());
		personaStatement.setString(2, cliente.getApellidoPaterno());
		personaStatement.setString(3, cliente.getApellidoMaterno());
		personaStatement.setString(4, cliente.getPassword());
		personaStatement.setString(5, cliente.getCorreoElectronico());
		
		personInserted = personaStatement.executeUpdate() > 0;
		
		if(!personInserted) {
			throw new SQLException("Person has not created, no rows affected"); 
		}else {
			System.out.println("Persona con nombre "+ cliente.getNombre()+ " creada con éxito");
		}
		
		try(ResultSet generatedKeys = personaStatement.getGeneratedKeys()){
			if(generatedKeys.next()) {
				cliente.setIdPersona(generatedKeys.getInt(1));
			}else {
				throw new SQLException("No ID obtained");
			}
		}
		
		personaStatement = connection.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
		personaStatement.setInt(1, cliente.getTelefono());
		personaStatement.setInt(2, cliente.getIdPersona());
		
		personInserted = personaStatement.executeUpdate() > 0;
		
		if(!personInserted) {
			throw new SQLException("Client has not created, no rows affected"); 
		}else {
			System.out.println("Cliente con nombre "+ cliente.getNombre()+ " creada con éxito");
		}
		
		try(ResultSet generatedKeys = personaStatement.getGeneratedKeys()){
			if(generatedKeys.next()) {
				cliente.setIdCliente(generatedKeys.getInt(1));
			}else {
				throw new SQLException("No ID obtained");
			}
		}
		
		personaStatement.close();
		con.desconectar();
		
		return personInserted;
	}

}
