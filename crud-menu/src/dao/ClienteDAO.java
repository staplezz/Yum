package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Cliente;
import modelo.Direccion;
import modelo.Conexion;

public class ClienteDAO {
	private Conexion con;
	private Connection connection;
	
	public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword)throws SQLException{
		System.out.println(jdbcURL); 
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	//Crear cliente 
	public boolean crearCliente(Cliente cliente, Direccion direccion)throws SQLException{
		boolean personInserted = false; 
		boolean direccionInserted = false;
		
		String sqlPersona = "INSERT INTO persona(nombre, apellidoPaterno, apellidoMaterno, password, correoElectronico) VALUES(?,?,?,?,?) ";
		String sqlCliente = "INSERT INTO cliente(salt, telefono, idPersona) VALUES(1, ?, ?)";
		String sqlDireccion;
		String sqlDireccionCliente = "INSERT INTO direccionescliente(idDireccion, idCliente) VALUES (?,?) ";
		PreparedStatement direccionStatement;
		
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
		personaStatement.setString(1, cliente.getTelefono());
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
		
		if(direccion.getNumInterior() != -1 && direccion.getNumExterior() != -1) {
			//tiene ambos números
			sqlDireccion = "INSERT INTO direccion (delegacion, colonia, calle, num_interior, num_exterior) VALUES(?, ?, ?, ?, ?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumInterior());
			direccionStatement.setInt(5, direccion.getNumExterior());
			
		}else if (direccion.getNumInterior() == -1 && direccion.getNumExterior() != -1) {
			//no tiene número interior pero sí exterior
			sqlDireccion= "INSERT INTO direccion (delegacion, colonia, calle, num_exterior) VALUES (?,?,?,?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumExterior());
		}else if(direccion.getNumInterior() != -1 && direccion.getNumExterior() == 1) {
			//tiene número interior y no tiene número exterior
			sqlDireccion= "INSERT INTO direccion (delegacion, colonia, calle, num_interior) VALUES (?,?,?,?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumInterior());
		}else {
			sqlDireccion= "INSERT INTO direccion (delegacion, colonia, calle) VALUES(?,?,?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			
		}
		
		direccionInserted = direccionStatement.executeUpdate() > 0;
		
		if(!direccionInserted) {
			throw new SQLException("Address has not created, no rows affected"); 
		}else {
			System.out.println("direccion con calle "+ direccion.getCalle()+ " creada con éxito");
		}
		
		try(ResultSet generatedKeys = direccionStatement.getGeneratedKeys()){
			if(generatedKeys.next()) {
				direccion.setIdDireccion(generatedKeys.getInt(1));
			}else {
				throw new SQLException("No ID obtained");
			}
		}
		
		direccionStatement = connection.prepareStatement(sqlDireccionCliente); 
		direccionStatement.setInt(1, direccion.getIdDireccion());
		direccionStatement.setInt(2, cliente.getIdCliente());
		direccionInserted = direccionStatement.executeUpdate() > 0;
		
		personaStatement.close();
		direccionStatement.close();
		con.desconectar();
		
		
		return personInserted;
	}
	
	public boolean editarCliente(Cliente cliente)throws SQLException {
		boolean rowActualizar = false;
		String sqlPersona = "UPDATE persona SET nombre = ?, apellidoPaterno=?, apellidoMaterno=?, password=?, correoElectronico=? WHERE idPersona= ?";
		String sqlCliente = "UPDATE cliente SET telefono= ? WHERE idCliente = ?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(sqlPersona);
		
		statement.setString(1, cliente.getNombre());
		statement.setString(2, cliente.getApellidoPaterno());
		statement.setString(3, cliente.getApellidoMaterno());
		statement.setString(4, cliente.getPassword());
		statement.setString(5, cliente.getCorreoElectronico());
		statement.setDouble(6, cliente.getIdPersona());
		
		rowActualizar = statement.executeUpdate() > 0; 
		
		if (!rowActualizar) {
			 throw new SQLException("Persona no modificada, no rows affected.");
	    }else {
	    	System.out.println("Persona modificada");
	    }
		
		statement = connection.prepareStatement(sqlCliente);
		statement.setString(1, cliente.getTelefono());
		statement.setInt(2, cliente.getIdCliente());
		
		rowActualizar = statement.executeUpdate() > 0;
		
		if (!rowActualizar) {
			 throw new SQLException("Cliente no modificado, no rows affected.");
	    }else {
	    	System.out.println("Cliente modificado");
	    }
		
		statement.close();
		con.desconectar();
		
		return rowActualizar;
	}

}
