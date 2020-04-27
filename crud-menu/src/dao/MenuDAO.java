package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 

import modelo.Categoria; 
import modelo.Alimento;
import modelo.Conexion;

public class MenuDAO {
	private Conexion con; 
	private Connection connection; 
	
	public MenuDAO(String jdbcURL, String jdbcUsername, String jdbcPassword)throws SQLException{
		System.out.println(jdbcURL); 
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	
	//Crear categoría 
	public boolean crearCategoria(Categoria categoria)throws SQLException {
		String sql = "INSERT INTO categoria (idCategoria, nombre) VALUES(?,?)";
		con.conectar();
		connection = con.getJdbcConnection(); 
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, categoria.getId());
		statement.setString(2, categoria.getNombre());
		System.out.println("se ha creado una nueva categoría "+ categoria.getNombre());
		
		boolean rowInserted = statement.executeUpdate() > 0; 
		statement.close();
		con.desconectar();
		
		return rowInserted; 
		
	}
	//Editar categoría(nombre)
	public boolean editarCategoria(Categoria categoria)throws SQLException {
		boolean rowEditar = false; 
		String sql = "UPDATE categoria SET nombre = ? WHERE idCategoria = ? "; 
		con.conectar();
		connection = con.getJdbcConnection(); 
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, categoria.getNombre());
		statement.setInt(2, categoria.getId());
		System.out.println("Se ha actualizado el nombre de la categoría"); 
		
		rowEditar = statement.executeUpdate()> 0; 
		statement.close();
		con.desconectar();
		
		return rowEditar;
	}
	//Eliminar categoría 
	
	public boolean eliminarCategoria(Categoria categoria)throws SQLException{
		boolean rowActualizar = false;
		boolean rowEliminar = false; 
		//obtener todos los alimentos de la categoria y actualizarlos para que se queden sin categoria
		String sql = "UPDATE alimento SET idCategoria = 100  WHERE idCategoria = ? ";
		con.conectar();
		connection = con.getJdbcConnection(); 
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, categoria.getId());
		rowActualizar = statement.executeUpdate()>0;
		
		if(rowActualizar || statement.executeUpdate() == 0) {
			//Ahora sí eliminar categoria 
			String eliminar = "DELETE FROM categoria WHERE idCategoria = ?"; 
			PreparedStatement statementDelete = connection.prepareStatement(eliminar); 
			statementDelete.setInt(1, categoria.getId());
			
			System.out.println("Se ha eliminado la categoría "+ categoria.getNombre()+ " con éxito"); 
			
			rowEliminar = statementDelete.executeUpdate() > 0; 
			statementDelete.close();
		}
		
		statement.close(); 
		con.desconectar();
		
		return rowEliminar;
		
	}
	//Agregar alimentos a la categoría 
	
	public boolean agregarAlimento(int idAlimento, int idCategoria)throws SQLException{
		boolean rowActualizar = false;
		String sql="UPDATE alimento SET idCategoria=? WHERE idAlimento = ?";
		con.conectar();
		connection = con.getJdbcConnection(); 
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idCategoria);
		statement.setInt(2, idAlimento);
		System.out.println("Se ha agregado a la categoria"+idCategoria+ " el alimento "+ idAlimento);
		rowActualizar = statement.executeUpdate()>0;
		
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//Quitar alimentos de la categoría 
	//mostrar alimentos sin categoría
	
	public List<Alimento> mostrarAlimentosSinCategoria()throws SQLException{
		List<Alimento> alimentos = new ArrayList<Alimento>();
		String sql = "SELECT idAlimento, nombre, precio, descripcion FROM alimento WHERE idCategoria = 100";
		con.conectar();
		connection = con.getJdbcConnection(); 
		
		Statement statement = connection.createStatement(); 
		ResultSet resultSet = statement.executeQuery(sql); 
		
		while(resultSet.next()) {
			int id = resultSet.getInt("idAlimento"); 
			String nombre = resultSet.getString("nombre"); 
			Double precio = resultSet.getDouble("precio");
			String descripcion = resultSet.getString("descripcion");
			Alimento alimento = new Alimento(id, nombre, precio, descripcion, 100);
			alimentos.add(alimento);
		}
		statement.close();
		return alimentos;
	}
	//mostrar alimentos por categoría(id)
	public Categoria mostrarCategoriaId(int id) throws SQLException {
		Categoria categoria = null; 
		String sql = "SELECT * FROM categoria WHERE idCategoria = ?"; 
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, id);
		ResultSet res = statement.executeQuery(); 
		
		
		
		//Si hay esa categoría: 
		while(res.next()) {
			categoria = new Categoria(res.getInt("idCategoria"), res.getString("nombre"));
			System.out.println(res.getInt("idCategoria"));
			//Obtener los alimentos 
			String sqlAlimentos = "SELECT idAlimento, nombre, precio, descripcion FROM alimento WHERE idCategoria = ?"; 
			PreparedStatement alimentos = connection.prepareStatement(sqlAlimentos);
			alimentos.setInt(1, res.getInt("idCategoria"));
			ResultSet resultAlimentos = alimentos.executeQuery(); 
			List<Alimento> lista = new ArrayList<Alimento>(); 
			
			//añadir a la lista 
			while(resultAlimentos.next()) {
				int idAlimento = resultAlimentos.getInt("idAlimento"); 
				String nombreA = resultAlimentos.getString("nombre"); 
				double precio = resultAlimentos.getDouble("precio"); 
				String descripcion = resultAlimentos.getString("descripcion");
				Alimento alimento = new Alimento(idAlimento, nombreA, precio, descripcion, res.getInt("idCategoria"));
				
				lista.add(alimento); 
			}
			alimentos.close();
			categoria.setListaAlimentos(lista);
			
		}
		con.desconectar();
		return categoria;
	}
	
	//mostrar alimentos por categoría (nombre)
	
	public Categoria mostrarCategoriaNombre(String nombre) throws SQLException {
		Categoria categoria = null; 
		String sql = "SELECT * FROM categoria WHERE nombre = ?"; 
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, nombre);
		ResultSet res = statement.executeQuery(); 
		
		
		
		//Si hay esa categoría: 
		while(res.next()) {
			categoria = new Categoria(res.getInt("idCategoria"), res.getString("nombre"));
			System.out.println(res.getInt("idCategoria"));
			//Obtener los alimentos 
			String sqlAlimentos = "SELECT idAlimento, nombre, precio, descripcion FROM alimento WHERE idCategoria = ?"; 
			PreparedStatement alimentos = connection.prepareStatement(sqlAlimentos);
			alimentos.setInt(1, res.getInt("idCategoria"));
			ResultSet resultAlimentos = alimentos.executeQuery(); 
			List<Alimento> lista = new ArrayList<Alimento>(); 
			
			//añadir a la lista 
			while(resultAlimentos.next()) {
				int id = resultAlimentos.getInt("idAlimento"); 
				String nombreA = resultAlimentos.getString("nombre"); 
				double precio = resultAlimentos.getDouble("precio"); 
				String descripcion = resultAlimentos.getString("descripcion");
				Alimento alimento = new Alimento(id, nombreA, precio, descripcion, res.getInt("idCategoria"));
				
				lista.add(alimento); 
			}
			alimentos.close();
			categoria.setListaAlimentos(lista);
			
		}
		con.desconectar();
		return categoria;
	}
	
	//mostrar Menú 
	
	public List<Categoria> mostrarMenu()throws SQLException{
		
		List<Categoria> listaCategorias = new ArrayList<Categoria>();
		String sql ="SELECT idCategoria, nombre FROM categoria";
		
		con.conectar();
		connection = con.getJdbcConnection(); 
		Statement statement = connection.createStatement(); 
		ResultSet resultSet = statement.executeQuery(sql); 
		
		while(resultSet.next()) {
			int idCategoria = resultSet.getInt("idCategoria");
			String categoriaNombre = resultSet.getString("nombre"); 
			Categoria categoria = new Categoria(idCategoria, categoriaNombre); 
			//Obtenemos la lista de alimentos por categoria 
			List<Alimento> listaAlimentos = new ArrayList<Alimento>();
			String alimentosSql = "SELECT idAlimento, nombre, precio, descripcion FROM alimento WHERE idCategoria = ?";
			PreparedStatement statementA = connection.prepareStatement(alimentosSql); 
			statementA.setInt(1, idCategoria);
			ResultSet alimentos = statementA.executeQuery(); 
			while(alimentos.next()) {
				int idAlimento = alimentos.getInt("idAlimento"); 
				String nombre = alimentos.getString("nombre"); 
				Double precio = alimentos.getDouble("precio"); 
				String descripcion = alimentos.getString("descripcion"); 
				
				Alimento alimento = new Alimento(idAlimento, nombre, precio, descripcion, idCategoria); 
				listaAlimentos.add(alimento); 
			}
			// La agregamos a la categoria 
			categoria.setListaAlimentos(listaAlimentos);
			
			//agregamos la categoria a la lista
			listaCategorias.add(categoria);
			
		}
		
		con.desconectar();
		return listaCategorias;
		
	}

}
