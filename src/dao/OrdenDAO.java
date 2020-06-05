package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Conexion;
import modelo.OrdenAdmin;
import modelo.AlimentoOrden;

public class OrdenDAO {
	private Conexion con;
	private Connection connection;
	
	public OrdenDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	// Actualiza la orden al estado siguiente.
	public boolean actualizaOrden(int idOrden, int estadoActual) throws SQLException {
		if (estadoActual == 4)
			return false;
		
		String consultaActualiza = "UPDATE orden\r\n" + 
				"SET estado = ?\r\n" + 
				"WHERE idOrden = ?";
		
		con.conectar();
		connection=con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaActualiza);
		statement.setInt(1, estadoActual + 1);
		statement.setInt(2, idOrden);

		boolean actualiza = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return actualiza;
	}
	
	/*
	* Obtiene la lista de los alimentos de una órden.
	*/
	public List<AlimentoOrden> obtenAlimentosOrden(int idOrden) throws SQLException {
		// La lista de alimentos.
		List<AlimentoOrden> listaAlimentos = new ArrayList<AlimentoOrden>();
		
		// Consulta para obtener los alimentos.
		String consultaAlimentos = "SELECT nombre, precio, cantidad\r\n" + 
				"FROM orden o inner join\r\n" + 
				"alimentosorden a\r\n" + 
				"on o.idOrdenesCliente = a.idOrdenesCliente inner join\r\n" + 
				"alimento c on a.idAlimento = c.idAlimento\r\n" + 
				"where idOrden =  ?";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaAlimentos);
		
		statement.setInt(1, idOrden);
		
		ResultSet res = statement.executeQuery();
		
		//Almacenar los alimentos.
		while(res.next()) {
			// El nombre del alimento.
			String nombre = res.getString("nombre");
			// La cantidad del alimento.
			int cantidad = res.getInt("cantidad");
			// El precio del alimento
			double precio = res.getDouble("precio");
			// Creamos el objeto de alimento.
			AlimentoOrden alimento = new AlimentoOrden(nombre, cantidad, precio);
			
			listaAlimentos.add(alimento);
		}
		
		statement.close();
		con.desconectar();
		
		return listaAlimentos;
	}
	
	/*
	* Obtiene la suma total de los alimentos de la órden.
	*/
	public int totalOrden(int idOrden) throws SQLException {
		int total = 0;
		
		// Consulta para obtener los alimentos.
		String consultaTotal = "SELECT SUM(precio * cantidad) total\r\n" + 
				"FROM orden o inner join \r\n" + 
				"alimentosorden a \r\n" + 
				"on o.idOrdenesCliente = a.idOrdenesCliente inner join\r\n" + 
				"alimento c on a.idAlimento = c.idAlimento\r\n" + 
				"where idOrden = ? \r\n" + 
				"group by idOrden";
		
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaTotal);
		
		statement.setInt(1, idOrden);
		
		ResultSet res = statement.executeQuery();
		
		// Obtenemos el resultado.
		if(res.next()) {
			total = res.getInt("total");
			return total;
		}
		
		statement.close();
		con.desconectar();
		
		return total;
	}
	
	/*
	 * Obtiene las órdenes para el administrador que acaban
	 * de ser creadas y listas para entregar.
	 */
	public List<OrdenAdmin> getOrdenesSinRep() throws SQLException {
		ResultSet res = null;
		// La lista que contiene las ordenes.
		List<OrdenAdmin> listaOrdenes = new ArrayList<OrdenAdmin>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.fecha, p.nombre nombrecliente, o.estado\r\n" + 
				"FROM ((orden o INNER JOIN cliente c\r\n" + 
				"ON c.idCliente = o.idCliente)\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona) INNER JOIN\r\n" + 
				"orden q\r\n" +
				"ON o.idOrden = q.idOrden\r\n" + 
				"WHERE o.estado = ? OR o.estado = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, 1);
		statement.setInt(2, 2);
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			Date fecha = res.getDate("fecha");
			String nombreCliente = res.getString("nombrecliente");
			String nombreRepartidor = "Sin asignar";
			int estado = res.getInt("estado");
			
			OrdenAdmin ordenAdmin = new OrdenAdmin(idOrden, fecha, estado, 1, nombreRepartidor, nombreCliente);
			
			listaOrdenes.add(ordenAdmin);
		}
		
		return listaOrdenes;
	}
	
	/*
	* Obtiene las ordenes para el administrador con estado 3.
	* 3: En proceso de entrega.
	*/
	public List<OrdenAdmin> getOrdenesConRep() throws SQLException {
		ResultSet res = null;
		// La lista que contiene las ordenes.
		List<OrdenAdmin> listaOrdenes = new ArrayList<OrdenAdmin>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.fecha, p.nombre nombrecliente, pr.nombre nombrerepartidor, o.estado\r\n" + 
				"FROM ((orden o INNER JOIN cliente c\r\n" + 
				"ON c.idCliente = o.idCliente)\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona) INNER JOIN\r\n" + 
				"((orden q INNER JOIN repartidor r\r\n" + 
				"ON r.idRepartidor = q.idRepartidor)\r\n" + 
				"INNER JOIN persona pr\r\n" + 
				"ON r.idPersona = pr.idPersona)\r\n" + 
				"ON o.idOrden = q.idOrden\r\n" +
				"WHERE o.estado = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, 3);
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			Date fecha = res.getDate("fecha");
			String nombreCliente = res.getString("nombrecliente");
			String nombreRepartidor = res.getString("nombrerepartidor");
			int estado = res.getInt("estado");
			
			OrdenAdmin ordenAdmin = new OrdenAdmin(idOrden, fecha, estado, 1, nombreRepartidor, nombreCliente);
			
			listaOrdenes.add(ordenAdmin);
		}
		
		return listaOrdenes;
	}
	
	/*
	* Obtiene las ordenes para el administrador.
	* 4: Entregadas.
	*/
	public List<OrdenAdmin> getHistorialOrdenesAdmin() throws SQLException {
		ResultSet res = null;
		// La lista que contiene las ordenes.
		List<OrdenAdmin> listaOrdenes = new ArrayList<OrdenAdmin>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.fecha, p.nombre nombrecliente, pr.nombre nombrerepartidor, o.calificacion\r\n" + 
				"FROM ((orden o INNER JOIN cliente c\r\n" + 
				"ON c.idCliente = o.idCliente)\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona) INNER JOIN\r\n" + 
				"((orden q INNER JOIN repartidor r\r\n" + 
				"ON r.idRepartidor = q.idRepartidor)\r\n" + 
				"INNER JOIN persona pr\r\n" + 
				"ON r.idPersona = pr.idPersona)\r\n" + 
				"ON o.idOrden = q.idOrden\r\n" +
				"WHERE o.estado = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		
		
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, 4);
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			Date fecha = res.getDate("fecha");
			String nombreCliente = res.getString("nombrecliente");
			String nombreRepartidor = res.getString("nombrerepartidor");
			int calificacion = res.getInt("calificacion");
			
			OrdenAdmin ordenAdmin = new OrdenAdmin(idOrden, fecha, 4, calificacion, nombreRepartidor, nombreCliente);
			
			listaOrdenes.add(ordenAdmin);
		}
		
		return listaOrdenes;
	}
	
	/*
	* Obtiene las ordenes actuales para el cliente.
	*/
	public List<OrdenAdmin> getOrdenesCliente(int idCliente) throws SQLException {
		ResultSet res = null;
		// La lista que contiene las ordenes.
		List<OrdenAdmin> listaOrdenes = new ArrayList<OrdenAdmin>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.fecha, o.estado, p.nombre nombrecliente, pr.nombre nombrerepartidor, o.calificacion\r\n" + 
				"FROM ((orden o INNER JOIN cliente c\r\n" + 
				"ON c.idCliente = o.idCliente)\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona) INNER JOIN\r\n" + 
				"((orden q INNER JOIN repartidor r\r\n" + 
				"ON r.idRepartidor = q.idRepartidor)\r\n" + 
				"INNER JOIN persona pr\r\n" + 
				"ON r.idPersona = pr.idPersona)\r\n" + 
				"ON o.idOrden = q.idOrden\r\n" +
				"WHERE o.estado != 4 AND o.idCliente = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		
		
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, idCliente);
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			Date fecha = res.getDate("fecha");
			String nombreCliente = res.getString("nombrecliente");
			String nombreRepartidor = res.getString("nombrerepartidor");
			int estado = res.getInt("estado");
			int calificacion = res.getInt("calificacion");
			
			OrdenAdmin ordenCliente = new OrdenAdmin(idOrden, fecha, estado, calificacion, nombreRepartidor, nombreCliente);
			System.out.println("orden"+ordenCliente.getNombreEstado());
			listaOrdenes.add(ordenCliente);
		}
		
		return listaOrdenes;
	}
	
	/*
	* Obtiene todas las ordenes del cliente.
	* 3: entregada
	*/
	public List<OrdenAdmin> getHistorialOrdenCliente(int idCliente) throws SQLException {
		ResultSet res = null;
		// La lista que contiene las ordenes.
		List<OrdenAdmin> listaOrdenes = new ArrayList<OrdenAdmin>();
		
		String consultaOrdenes = "SELECT o.idOrden, o.fecha, o.estado, p.nombre nombrecliente, pr.nombre nombrerepartidor, o.calificacion\r\n" + 
				"FROM ((orden o INNER JOIN cliente c\r\n" + 
				"ON c.idCliente = o.idCliente)\r\n" + 
				"INNER JOIN persona p\r\n" + 
				"ON c.idPersona = p.idPersona) INNER JOIN\r\n" + 
				"((orden q INNER JOIN repartidor r\r\n" + 
				"ON r.idRepartidor = q.idRepartidor)\r\n" + 
				"INNER JOIN persona pr\r\n" + 
				"ON r.idPersona = pr.idPersona)\r\n" + 
				"ON o.idOrden = q.idOrden\r\n" +
				"WHERE o.estado = 4 AND o.idCliente = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		
		
		
		PreparedStatement statement = connection.prepareStatement(consultaOrdenes);
		
		statement.setInt(1, idCliente);
		
		try {
			res = statement.executeQuery();
		} catch(Exception e) {
			System.out.println("ERRROR: " + e);
		}
		
		while(res.next()) {
			int idOrden = res.getInt("idOrden");
			Date fecha = res.getDate("fecha");
			String nombreCliente = res.getString("nombrecliente");
			String nombreRepartidor = res.getString("nombrerepartidor");
			//int estado = res.getInt("estado");
			int calificacion = res.getInt("calificacion");
			
			OrdenAdmin ordenCliente = new OrdenAdmin(idOrden, fecha, 4, calificacion, nombreRepartidor, nombreCliente);
			System.out.println("orden"+ordenCliente.getNombreEstado());
			listaOrdenes.add(ordenCliente);
		}
		
		return listaOrdenes;
	}
}
