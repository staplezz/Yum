package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList; 
import java.util.List;

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
		boolean carritoInserted = false;
		
		String sqlPersona = "INSERT INTO persona(nombre, apellidoPaterno, apellidoMaterno, password, correoElectronico) VALUES(?,?,?,?,?) ";
		String sqlCliente = "INSERT INTO cliente(salt, telefono, idPersona) VALUES(1, ?, ?)";
		String sqlDireccion;
		String sqlDireccionCliente = "INSERT INTO direccionescliente(idDireccion, idCliente) VALUES (?,?)";
		String sqlCarrito = "INSERT INTO carrito(idCliente) VALUES(?)";
		PreparedStatement direccionStatement, carritoStatement;
		
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
			System.out.println("Persona con nombre "+ cliente.getNombre()+ " creada con �xito");
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
			System.out.println("Cliente con nombre "+ cliente.getNombre()+ " creada con �xito");
		}
		
		try(ResultSet generatedKeys = personaStatement.getGeneratedKeys()){
			if(generatedKeys.next()) {
				cliente.setIdCliente(generatedKeys.getInt(1));
			}else {
				throw new SQLException("No ID obtained");
			}
		}
		
		if(direccion.getNumInterior() != -1 && direccion.getNumExterior() != -1) {
			//tiene ambos n�meros
			sqlDireccion = "INSERT INTO direccion (delegacion, colonia, calle, num_interior, num_exterior) VALUES(?, ?, ?, ?, ?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumInterior());
			direccionStatement.setInt(5, direccion.getNumExterior());
			
		}else if (direccion.getNumInterior() == -1 && direccion.getNumExterior() != -1) {
			//no tiene n�mero interior pero s� exterior
			sqlDireccion= "INSERT INTO direccion (delegacion, colonia, calle, num_exterior) VALUES (?,?,?,?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumExterior());
		}else if(direccion.getNumInterior() != -1 && direccion.getNumExterior() == 1) {
			//tiene n�mero interior y no tiene n�mero exterior
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
			System.out.println("direccion con calle "+ direccion.getCalle()+ " creada con �xito");
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
		
		//asociar carrito a cliente
		carritoStatement = connection.prepareStatement(sqlCarrito);
		carritoStatement.setInt(1, cliente.getIdCliente());
		
		carritoInserted = carritoStatement.executeUpdate()> 0; 
		
		if(!carritoInserted) {
			throw new SQLException("Carrito has not created, no rows affected"); 
		}else {
			System.out.println("Carrito has created successfully "); 
		}
		
		
		personaStatement.close();
		direccionStatement.close();
		carritoStatement.close();
		con.desconectar();
		
		
		return personInserted;
	}
	
	public boolean editarCliente(Cliente cliente)throws SQLException {
		boolean rowActualizar = false;
		String sqlPersona = "UPDATE persona SET nombre = ?, apellidoPaterno=?, apellidoMaterno=?, password=?  WHERE idPersona= ?";
		String sqlCliente = "UPDATE cliente SET telefono= ? WHERE idCliente = ?";
		
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(sqlPersona);
		System.out.println(cliente.getNombre());
		
		statement.setString(1, cliente.getNombre());
		statement.setString(2, cliente.getApellidoPaterno());
		statement.setString(3, cliente.getApellidoMaterno());
		statement.setString(4, cliente.getPassword());
		statement.setInt(5, cliente.getIdPersona());
		
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
	
	public Cliente mostrarClienteId(int idCliente)throws SQLException {
		Cliente cliente = null;
		String sql = "SELECT nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, password, cliente.idPersona as idPersona FROM cliente join persona"; 
		sql += " WHERE idCliente=? and persona.idPersona = cliente.idPersona";
		con.conectar();
		connection = con.getJdbcConnection(); 
		System.out.println("TRIPLEEE"+idCliente);
		System.out.println(sql);
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, idCliente);
		ResultSet res = statement.executeQuery(); 
		
		
		if(res.next()) {
			System.out.println("PASA"+res.getString("nombre"));
			String nombre = res.getString("nombre");
			String apePat = res.getString("apellidoPaterno"); 
			String apeMat = res.getString("apellidoMaterno"); 
			String email  = res.getString("correoElectronico");
			String tel    = res.getString("telefono"); 
			String pass   = res.getString("password"); 
			int idPersona = res.getInt("idPersona");
			
			System.out.println("NOMBRE"+nombre);
			cliente = new Cliente(nombre, apePat, apeMat, email, tel, pass);
			cliente.setIdCliente(idCliente);
			cliente.setIdPersona(idPersona);
		}
		statement.close(); 
		con.desconectar();
		
		return cliente;
	}
	
	public boolean existClienteEmail(String email)throws SQLException{
		boolean exist = false;
		String sql = "SELECT cliente.idCliente as idCliente, nombre, apellidoPaterno, apellidoMaterno, correoElectronico, telefono, cliente.idPersona as idPersona FROM cliente join persona"; 
		sql += " WHERE correoElectronico=? and persona.idPersona = cliente.idPersona";
		con.conectar();
		connection = con.getJdbcConnection(); 
		
		System.out.println("TRIPLEEE"+email);
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, email);
		ResultSet res = statement.executeQuery(); 
		
		System.out.println(statement);
		if(res.next()) {
			exist = true;
		}
		
		return exist;
	}
	
	public List<Direccion> mostrarDireccionesCliente(int idCliente) throws SQLException{
		List<Direccion> direcciones = new ArrayList<Direccion>(); 
		Direccion direccion = null;
		String sql = "SELECT direccion.idDireccion as idDireccion, delegacion, colonia, calle, num_interior, num_exterior";
		sql += " FROM direccion join direccionescliente WHERE idCliente = ? and direccion.idDireccion = direccionescliente.idDireccion";
		
		con.conectar();
		connection = con.getJdbcConnection(); 
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, idCliente);
		ResultSet res = statement.executeQuery();
		
		while(res.next()) {
			int idDireccion = res.getInt("idDireccion");
			String delegacion = res.getString("delegacion");
			String colonia    = res.getString("colonia"); 
			String calle 	  = res.getString("calle"); 
			int numExterior   = res.getInt("num_exterior");
			int numInterior   = res.getInt("num_interior");
			
			direccion = new Direccion(delegacion, colonia, calle); 
			direccion.setIdDireccion(idDireccion);
			
			System.out.println("EXTERIOR"+(numExterior != -1));
			if(numInterior != -1) {
				direccion.setNumInterior(numInterior);
			}
			
			if(numExterior != -1) {
				direccion.setNumExterior(numExterior);
			}
			direcciones.add(direccion);
		}
		
		statement.close();
		con.desconectar();
		return direcciones;
	}
	
	public Direccion mostrarDireccionId(int idDireccion) throws SQLException {
		Direccion direccion = null; 
		String sql = "SELECT delegacion, colonia, calle, num_interior, num_exterior FROM direccion "; 
		sql += "WHERE idDireccion = ?"; 
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, idDireccion);
		
		ResultSet res = statement.executeQuery();
		
		while(res.next()) {
			String delegacion = res.getString("delegacion"); 
			String colonia = res.getString("colonia"); 
			String calle   = res.getString("calle"); 
			int numInterior = res.getInt("num_interior");
			int numExterior = res.getInt("num_exterior");
			
			direccion = new Direccion(delegacion, colonia, calle); 
			direccion.setIdDireccion(idDireccion);
			
			if(numInterior != -1) {
				direccion.setNumInterior(numInterior);
			}
			
			if(numExterior != -1) {
				direccion.setNumExterior(numExterior);
			}
		}
		
		statement.close();
		con.desconectar();
		
		return direccion;
		
	}
	
	public boolean editarDireccion(Direccion direccion)throws SQLException{
		boolean rowEditar = false; 
		String sql = "UPDATE direccion SET delegacion = ?, colonia = ?, calle = ?, num_interior=?, num_exterior=? ";
		sql += "WHERE idDireccion = ?";
		System.out.println("EDITAR DIRECCION"+ direccion.getIdDireccion());
		con.conectar();
		connection = con.getJdbcConnection(); 
		System.out.println("PF"+sql);
		PreparedStatement statement = connection.prepareStatement(sql); 
		
		statement.setString(1, direccion.getDelegacion());
		statement.setString(2, direccion.getColonia());
		statement.setString(3, direccion.getCalle());
		statement.setInt(4, direccion.getNumInterior()); 
		statement.setInt(5, direccion.getNumExterior());
		statement.setInt(6, direccion.getIdDireccion());
		
		System.out.println(statement);
		rowEditar = statement.executeUpdate() > 0; 
		System.out.println("AQUUII"+rowEditar);
		
		statement.close();
		con.desconectar();
		
		return rowEditar;
	}
	
	public boolean eliminarDireccion(int idDireccion)throws SQLException{
		boolean rowActualizar = false; 
		boolean rowEliminar = false; 
		
		String sqlDireccionCliente = "DELETE FROM direccionescliente WHERE idDireccion = ?";
		String sqlDireccion= "DELETE FROM direccion WHERE idDireccion = ?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(sqlDireccionCliente);
		statement.setInt(1, idDireccion);
		rowActualizar = statement.executeUpdate() > 0; 
		
		if(rowActualizar) {
			statement = connection.prepareStatement(sqlDireccion); 
			statement.setInt(1, idDireccion);
			rowEliminar = statement.executeUpdate() > 0; 
		}
		
		statement.close();
		con.desconectar();
		
		return rowEliminar;
		
	}
	
	public boolean agregarDireccion(Direccion direccion, int idCliente)throws SQLException {
		boolean direccionInserted = false; 
		
		String sqlDireccion; 
		String sqlDireccionCliente = "INSERT INTO direccionescliente(idDireccion, idCliente) VALUES(?, ?)";
		PreparedStatement direccionStatement;
		
		System.out.println("pasa");
		con.conectar();
		connection = con.getJdbcConnection();
		
		
		//Insertar primero en la tabla "Direccion"
		
		if(direccion.getNumInterior() != -1 && direccion.getNumExterior() != -1) {
			//tiene ambos números
			sqlDireccion = "INSERT INTO direccion (delegacion, colonia, calle, num_interior, num_exterior) VALUES(?, ?, ?, ?, ?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumInterior());
			direccionStatement.setInt(5, direccion.getNumExterior());
			System.out.println(direccionStatement);
			
		}else if (direccion.getNumInterior() == -1 && direccion.getNumExterior() != -1) {
			//no tiene n�mero interior pero sí exterior
			sqlDireccion= "INSERT INTO direccion (delegacion, colonia, calle, num_exterior) VALUES (?,?,?,?)";
			direccionStatement = connection.prepareStatement(sqlDireccion, Statement.RETURN_GENERATED_KEYS);
			direccionStatement.setString(1, direccion.getDelegacion());
			direccionStatement.setString(2, direccion.getColonia());
			direccionStatement.setString(3, direccion.getCalle());
			direccionStatement.setInt(4, direccion.getNumExterior());
		}else if(direccion.getNumInterior() != -1 && direccion.getNumExterior() == 1) {
			//tiene n�mero interior y no tiene número exterior
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
			System.out.println("direccion con calle "+ direccion.getCalle()+ " creada con �xito");
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
		direccionStatement.setInt(2, idCliente);
		direccionInserted = direccionStatement.executeUpdate() > 0;
		
		direccionStatement.close();
		con.desconectar();
		
		return direccionInserted;
	}

}
