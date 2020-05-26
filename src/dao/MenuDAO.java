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
	public Categoria crearCategoria(String nombre)throws SQLException {
		Categoria categoria = new Categoria(nombre);
		String sql = "INSERT INTO categoria (nombre) VALUES(?)";
		boolean rowInserted = false;
		
		con.conectar();
		connection = con.getJdbcConnection(); 
		
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, nombre);

		rowInserted = statement.executeUpdate() > 0; 
		
		if(!rowInserted) {
			throw new SQLException("Categoría no creada, no rows affected");
		}else {
			System.out.println("Categoría "+ nombre + "creada con Éxito");
		}
		
		//Obtener el id de la categor�a e insertarlo en el objeto 
		try (ResultSet generatedKey = statement.getGeneratedKeys()){
			if(generatedKey.next())
				categoria.setId(generatedKey.getInt(1));
			else
				throw new SQLException("No ID obtained");
		}
		
		statement.close();
		con.desconectar();

		
		return categoria;
		
	}
	//Editar categorÍa(nombre)
	public boolean editarCategoria(Categoria categoria)throws SQLException {
		boolean rowEditar = false; 
		String sql = "UPDATE categoria SET nombre = ? WHERE idCategoria = ? "; 
		
		con.conectar();
		connection = con.getJdbcConnection(); 
		
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, categoria.getNombre());
		statement.setInt(2, categoria.getId());
		 
		rowEditar = statement.executeUpdate()> 0; 
		statement.close();
		con.desconectar();
		
		return rowEditar;
	}
	//Eliminar categor�a 
	
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
			//Ahora s� eliminar categoria 
			String eliminar = "DELETE FROM categoria WHERE idCategoria = ?"; 
			PreparedStatement statementDelete = connection.prepareStatement(eliminar); 
			statementDelete.setInt(1, categoria.getId());
			
			System.out.println("Se ha eliminado la categor�a "+ categoria.getNombre()+ " con �xito"); 
			
			rowEliminar = statementDelete.executeUpdate() > 0; 
			statementDelete.close();
		}
		
		statement.close(); 
		con.desconectar();
		
		return rowEliminar;
		
	}
	
	//Eliminar alimentos de categor�a 
	public boolean eliminarAlimentosCategoria(int idAlimento)throws SQLException{
		boolean rowActualizar = false;
		String sql = "UPDATE alimento SET idCategoria = 100 WHERE idAlimento = ?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, idAlimento);
		rowActualizar = statement.executeUpdate()>0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	//Agregar alimentos a la categor�a 
	
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
	 
	
	//mostrar alimentos sin categor�a
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
			Alimento alimento = new Alimento(0,nombre, precio, descripcion, 100, "" ,"");
			alimento.setId(id);
			alimentos.add(alimento);
		}
		statement.close();
		return alimentos;
	}
	//mostrar alimentos por categor�a(id)
	public Categoria mostrarCategoriaId(int id) throws SQLException {
		Categoria categoria = null; 
		String sql = "SELECT * FROM categoria WHERE idCategoria = ?"; 
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setInt(1, id);
		ResultSet res = statement.executeQuery(); 
		
		//Si hay esa categor�a: 
		while(res.next()) {
			String categoriaNombre = res.getString("nombre");
			int idCategoria = res.getInt("idCategoria");
			categoria = new Categoria(categoriaNombre);
			categoria.setId(idCategoria);
			System.out.println(idCategoria);
			//Obtener los alimentos 
			String sqlAlimentos = "SELECT idAlimento, nombre, precio, descripcion FROM alimento WHERE idCategoria = ?"; 
			PreparedStatement alimentos = connection.prepareStatement(sqlAlimentos);
			alimentos.setInt(1, idCategoria);
			ResultSet resultAlimentos = alimentos.executeQuery(); 
			List<Alimento> lista = new ArrayList<Alimento>(); 
			
			//a�adir a la lista 
			while(resultAlimentos.next()) {
				int idAlimento = resultAlimentos.getInt("idAlimento"); 
				String nombreA = resultAlimentos.getString("nombre"); 
				double precio = resultAlimentos.getDouble("precio"); 
				String descripcion = resultAlimentos.getString("descripcion");
				Alimento alimento = new Alimento(0,nombreA, precio, descripcion, 
						res.getInt("idCategoria"), "", "");
				alimento.setId(idAlimento);
				
				lista.add(alimento); 
			}
			alimentos.close();
			categoria.setListaAlimentos(lista);
			
		}
		res.close();
		con.desconectar();
		return categoria;
	}
	
	//mostrar alimentos por categor�a (nombre)
	
	public Categoria mostrarCategoriaNombre(String nombre) throws SQLException {
		Categoria categoria = null; 
		String sql = "SELECT * FROM categoria WHERE nombre = ?"; 
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, nombre);
		ResultSet res = statement.executeQuery(); 
		
		
		
		//Si hay esa categor�a: 
		while(res.next()) {
			int idCategoria = res.getInt("idCategoria");
			categoria = new Categoria(res.getString("nombre"));
			categoria.setId(idCategoria);
			
			//Obtener los alimentos 
			String sqlAlimentos = "SELECT idAlimento, nombre, precio, descripcion FROM alimento WHERE idCategoria = ?"; 
			PreparedStatement alimentos = connection.prepareStatement(sqlAlimentos);
			alimentos.setInt(1,idCategoria);
			ResultSet resultAlimentos = alimentos.executeQuery(); 
			List<Alimento> lista = new ArrayList<Alimento>(); 
			
			//a�adir a la lista 
			while(resultAlimentos.next()) {
				int idAlimento = resultAlimentos.getInt("idAlimento"); 
				String nombreA = resultAlimentos.getString("nombre"); 
				double precio = resultAlimentos.getDouble("precio"); 
				String descripcion = resultAlimentos.getString("descripcion");
				Alimento alimento = new Alimento(0,nombreA, precio, descripcion, 
						res.getInt("idCategoria"), "", "");
				alimento.setId(idAlimento);
				
				lista.add(alimento); 
			}
			alimentos.close();
			categoria.setListaAlimentos(lista);
			
		}
		statement.close();
		con.desconectar();
		return categoria;
	}
	
	//mostrar Men� 
	
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
			Categoria categoria = new Categoria(categoriaNombre); 
			categoria.setId(idCategoria);
			
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
				
				Alimento alimento = new Alimento(0,nombre, precio, descripcion,
						idCategoria, "", ""); 
				alimento.setId(idAlimento);
				listaAlimentos.add(alimento); 
			}
			// La agregamos a la categoria 
			categoria.setListaAlimentos(listaAlimentos);
			
			//agregamos la categoria a la lista
			listaCategorias.add(categoria);
			
			statementA.close();
			
		}
		statement.close();
		con.desconectar();
		return listaCategorias;
		
	}

}
