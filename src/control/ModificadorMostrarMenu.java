package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlimentoDAO;
import dao.ClienteDAO;
import modelo.Alimento;
import modelo.Categoria;

/**
 * Servlet implementation class ModificadorMostrarMenu
 */
@WebServlet("/muestraMenu")
public class ModificadorMostrarMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlimentoDAO alimentoDAO;
	ClienteDAO clienteDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorMostrarMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
		try {
			alimentoDAO = new AlimentoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("ENTRA BDD");
		} catch(Exception e) {
			System.out.println("Ocurrio un error al entrar a la BDD");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Obtenemos la acción que quiere realizar el usuario.
		String action = request.getParameter("action");
		
		try {
			switch(action) {
			case "mostrarAlimentos": 
				mostrarAlimentos(request, response);
				break;
			case "agregaAlimentoCarrito":
				agregaAlimentoCarrito(request, response);
				break;
			case "muestraMenuDefault":
				muestraMenuDefault(request, response);
				break;
				
			default: 
				break;
			}
		}catch(SQLException e) {
			e.getStackTrace();
		}
	}
	
	/*
	 * Obtiene los alimentos de la categoría que nos piden.
	 */
	private void mostrarAlimentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//El id de la categoría.
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		//Nombre de la categoria.
		String nombreCategoria = alimentoDAO.getNombreCategoria(idCategoria);
		//Todas las categorias.
		List<Categoria> categorias = alimentoDAO.getNombreCategorias();
		
		System.out.println("aqui entro");
		
		// Obtenemos los alimentos de la categoria.
		List<Alimento> alimentosCategoria = alimentoDAO.listarAlimentosPorCategoria(idCategoria);
		System.out.println("obtengo Alimentos");
		
		//Mandamos el id y el nombre de la categoria.
		request.setAttribute("nombreCategoria", nombreCategoria);
		request.setAttribute("idCategoria", idCategoria);
		
		// Mandamos el nombre de todas las categorias en lista de categorias.
		request.setAttribute("categorias", categorias);
		
		//y mandamos los alimentos de la categoria pedida.
		request.setAttribute("alimentosCategoria", alimentosCategoria);
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/Cliente/MostrarMenu.jsp").forward(request, response);
	}
	
	/*
	 * Obtiene los alimentos por default.
	 */
	private void muestraMenuDefault(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//El id de la categoría.
		int idCategoria = 1;
		//Nombre de la categoria.
		String nombreCategoria = alimentoDAO.getNombreCategoria(idCategoria);
		//Todas las categorias.
		List<Categoria> categorias = alimentoDAO.getNombreCategorias();
		
		System.out.println("aqui entro");
		
		// Obtenemos los alimentos de la categoria.
		List<Alimento> alimentosCategoria = alimentoDAO.listarAlimentosPorCategoria(idCategoria);
		System.out.println("obtengo Alimentos");
		
		//Mandamos el id y el nombre de la categoria.
		request.setAttribute("nombreCategoria", nombreCategoria);
		request.setAttribute("idCategoria", idCategoria);
		
		// Mandamos el nombre de todas las categorias en lista de categorias.
		request.setAttribute("categorias", categorias);
		
		//y mandamos los alimentos de la categoria pedida.
		request.setAttribute("alimentosCategoria", alimentosCategoria);
		
		//Notificación de que se agrego alimento.
		PrintWriter out = response.getWriter();
		
		int carrito = Integer.parseInt(request.getParameter("carrito"));
		
		if (carrito == 1) {
		out.println("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\r\n" + 
				"  <strong>¡Alimento agregado al carrito!</strong>\r\n" + 
				"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
				"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
				"  </button>\r\n" + 
				"</div>");
		} else if (carrito == 0) {
			out.println("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>El carrito se ha vaciado correctamente</strong>\r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>");
		} else if (carrito == 2) {
			out.println("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>Oops! parece que el carrito está vacío, intenta agregar algo de comer.</strong>\r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>");
		} else if (carrito == 3) {
			out.println("<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>¡Orden agregada exitosamente! Gracias por tu preferencia</strong>\r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>");
		}
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/Cliente/MostrarMenu.jsp").include(request, response);
		
		out.close();
	}
	
	/*
	 * Agrega un alimento al carrito del cliente.
	 */
	private void agregaAlimentoCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//El id del alimento.
		int idAlimento = Integer.parseInt(request.getParameter("idAlimento"));
		
		//Id del cliente
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		
		//Obtenemos el id del carrito.
		int idCarrito = clienteDAO.getIdCarrito(idCliente);
		
		//Hacemos la inserción.
		clienteDAO.insertaAlimento(idAlimento, idCarrito); 
		
		//request.getRequestDispatcher("muestraMenu?action=muestraMenuDefault").include(request, response);
		response.sendRedirect("muestraMenu?action=muestraMenuDefault&carrito=1");
	}

}
