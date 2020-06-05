package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Conexion;
import modelo.OrdenRepartidor;

public class OrdenRepartidorDAO {
	private Conexion con;
	private Connection connection;
	
	public OrdenRepartidorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	/*
	* Obtiene las órdenes que ya están listas para el repartidor.
	*/
	public List<OrdenRepartidor> getOrdenesListas() throws SQLException {
		ResultSet res = null;
		
		// La lista que contiene las ordenes.
		List<OrdenRepartidor> listaOrdenes = new ArrayList<OrdenRepartidor>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.estado, p.nombre nombrecliente, calle, delegacion, colonia, num_interior, num_exterior\r\n" + 
				"FROM orden o INNER JOIN direccionescliente d\r\n" + 
				"ON o.idDireccionCliente = d.idDireccionCliente\r\n" + 
				"INNER JOIN direccion dc\r\n" + 
				"ON d.idDireccion = dc.idDireccion\r\n" + 
				"INNER JOIN cliente c \r\n" + 
				"ON c.idCliente = o.idCliente\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona \r\n" +
				"WHERE o.estado = ?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, 2);
		
		System.out.println("entra repartidor sql");
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			int estado = res.getInt("estado");
			String nombreCliente = res.getString("nombrecliente");
			String direccion = res.getString("calle") + " " + res.getString("delegacion") + " " +
					res.getString("colonia") + " " + Integer.toString(res.getInt("num_interior")) + " "
					+ Integer.toString(res.getInt("num_exterior"));
			
			System.out.println(direccion);
			
			OrdenRepartidor ordenRepartidor = new OrdenRepartidor(idOrden, estado, nombreCliente, direccion);
			
			listaOrdenes.add(ordenRepartidor);
		}
		
		return listaOrdenes;
	}
	
	// Actualiza la orden al estado siguiente.
	public boolean actualizaOrden(int idOrden, int estadoActual, int idRepartidor) throws SQLException {
		if (estadoActual == 4)
			return false;
		
		String consultaActualiza = "UPDATE orden\r\n" + 
				"SET estado = ?, idRepartidor = ?\r\n" + 
				"WHERE idOrden = ?";
		
		con.conectar();
		connection=con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaActualiza);
		statement.setInt(1, estadoActual + 1);
		statement.setInt(2, idRepartidor);
		statement.setInt(3, idOrden);

		boolean actualiza = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return actualiza;
	}
	
	/*
	* Obtiene las órdenes que están siendo entregadas por el repartidor
	*/
	public List<OrdenRepartidor> getMisOrdenes(int idRepartidor) throws SQLException {
		ResultSet res = null;
		
		// La lista que contiene las ordenes.
		List<OrdenRepartidor> listaOrdenes = new ArrayList<OrdenRepartidor>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.estado, p.nombre nombrecliente, calle, delegacion, colonia, num_interior, num_exterior\r\n" + 
				"FROM orden o INNER JOIN direccionescliente d\r\n" + 
				"ON o.idDireccionCliente = d.idDireccionCliente\r\n" + 
				"INNER JOIN direccion dc\r\n" + 
				"ON d.idDireccion = dc.idDireccion\r\n" + 
				"INNER JOIN cliente c \r\n" + 
				"ON c.idCliente = o.idCliente\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona \r\n" +
				"WHERE o.estado = ? AND o.idRepartidor = ?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, 3);
		statement.setInt(2, idRepartidor);
		
		System.out.println("entra repartidor sql");
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			int estado = res.getInt("estado");
			String nombreCliente = res.getString("nombrecliente");
			String direccion = res.getString("calle") + " " + res.getString("delegacion") + " " +
					res.getString("colonia") + " " + Integer.toString(res.getInt("num_interior")) + " "
					+ Integer.toString(res.getInt("num_exterior"));
			
			System.out.println(direccion);
			
			OrdenRepartidor ordenRepartidor = new OrdenRepartidor(idOrden, estado, nombreCliente, direccion);
			
			listaOrdenes.add(ordenRepartidor);
		}
		
		return listaOrdenes;
	}
}
