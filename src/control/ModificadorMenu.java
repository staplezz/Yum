package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MenuDAO;
import modelo.Alimento;
import modelo.Categoria;

/**
 * Servlet implementation class AdminMenu
 */
@WebServlet("/adminMenu")
public class ModificadorMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MenuDAO menuDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			menuDAO = new MenuDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hola servlet..."); 
		String action = request.getParameter("action"); 
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			System.out.println(session.getAttribute("administrador"));
			System.out.println(action); 
			try {
				switch(action) {
				case "buscarPorCategoria": 
					mostrarCategoria(request, response); 
					break;
				case "nuevaCategoria": 
					informacion(request,response); 
					break;
				case "agregarCategoria": 
					crearCategoria(request, response); 
					break; 
				case "agregarAlimento":
					agregarAlimento(request,response);
					break;
				case "crudMenu": 
					crudMenu(request, response); 
					break; 
				case "editarCategoria": 
					editarCategoria(request, response);
					break; 
				case "showEditar": 
					showEditar(request,response); 
					break;
				case "eliminarCategoria": 
					eliminarCategoria(request, response); 
					break; 
				case "eliminarAlimentoCategoria":
					eliminarAlimentoCategoria(request, response);
				case "mostrarMenu": 
					mostrarMenu(request,response);
					break;
				case "mostrarAlimentos": 
					mostrarAlimentos(request,response);
				default: 
					break;
				}
			}catch(SQLException e) {
				e.getStackTrace();
			}
			
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hola Servlet");
		doGet(request, response);
	}
	
	private void crudMenu(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/MostrarCategoriaIH.jsp"); 
		String nombre = request.getParameter("nombre"); 
		System.out.println("AQUII"+ nombre);
		Categoria categoria = menuDAO.mostrarCategoriaNombre(nombre);
		request.setAttribute("categoria", categoria);
		dispatcher.forward(request, response);
	}
	private void mostrarAlimentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Alimento>alimentos = null; 
		alimentos = menuDAO.mostrarAlimentosSinCategoria();
		if (alimentos != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/MostrarAlimentosMenuIH.jsp");
			request.setAttribute("alimentos", alimentos);
			if(request.getParameter("idCat")!= null) {
				int idCategoria =Integer.parseInt(request.getParameter("idCat"));
				request.setAttribute("idCat", idCategoria);
			}
			dispatcher.forward(request, response);
		}else {
			mostrarMenu(request,response);
		}
		
	}
	
	private void agregarAlimento(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		menuDAO.agregarAlimento(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("idCat")));
		String error = "''";
		if(request.getParameter("idCat")!= null || request.getParameter("idCat").equals(error)) {
			request.setAttribute("categoria", request.getParameter("idCat"));
		}
		mostrarAlimentos(request,response);
	}
	
	private void informacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/registrar.jsp"); 
		dispatcher.forward(request, response);
	}
	
	private void crearCategoria(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException{
		String nombre = request.getParameter("nombre");
		System.out.println("Aquuiii"+nombre);
		
		Categoria categoria = menuDAO.crearCategoria(nombre); 
		
		request.setAttribute("idCat", categoria.getId());
		System.out.println("CATEGORIA"+categoria.getNombre());
		
		mostrarAlimentos(request,response);
	}
	
	private void editarCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Categoria categoria = new Categoria(request.getParameter("nombre"));
		categoria.setId(Integer.parseInt(request.getParameter("id")));
		menuDAO.editarCategoria(categoria); 
		showEditar(request,response);
	}
	
	private void showEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/EditaCategoriaIH.jsp");
		Categoria categoria = menuDAO.mostrarCategoriaId(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("categoria", categoria);
		dispatcher.forward(request, response);
	}
	
	private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Categoria categoria = menuDAO.mostrarCategoriaId(Integer.parseInt(request.getParameter("id"))); 
		menuDAO.eliminarCategoria(categoria); 
		mostrarMenu(request,response);
	}
	
	private void mostrarMenu(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/MenuAdminIH.jsp");
		
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			System.out.println(session.getAttribute("administrador"));
			List<Categoria> menu= null; 
			menu = menuDAO.mostrarMenu(); 
			if (menu == null) {
				request.setAttribute("Mensaje", "No hay alguna categoría aún");
				System.out.println("yeiii");
			}
			request.setAttribute("lista", menu);
			dispatcher.forward(request,response);
		}else {
			PrintWriter out = response.getWriter();
			out.print("Por favor ingresa primero"); 
			request.getRequestDispatcher("index.jsp").include(request, response);
			
		}
		
	}
	
	private void eliminarAlimentoCategoria(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/EditaCategoriaIH.jsp"); 
		int idAlimento= Integer.parseInt(request.getParameter("idAlimento"));
		Categoria categoria = menuDAO.mostrarCategoriaId(Integer.parseInt(request.getParameter("idCategoria")));
		menuDAO.eliminarAlimentosCategoria(idAlimento);
		request.setAttribute("categoria", categoria);
		dispatcher.forward(request, response);
	}
}
