package control;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RepartidorDAO;
import modelo.Repartidor;

/**
 * Servlet implementation class AdminArticulo
 */
@WebServlet("/modificadorRepartidor")
public class ModificadorRepartidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RepartidorDAO repartidorDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			repartidorDAO = new RepartidorDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificadorRepartidor() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			System.out.println("Hola Servlet..");
			String action = request.getParameter("action");
			System.out.println(action);
			try {
				switch (action) {
				case "index":
					index(request, response);
					break;
				case "nuevo":
					nuevo(request, response);
					break;
				case "register":
					System.out.println("registrar");
					registrar(request, response);
					break;
				case "registrarRepartidor":
					System.out.println("yendo a página de registro");
					showRegistroRepartidor(request, response);
					break;
				case "mostrar":
					System.out.println("mostrndo repartidores");
					mostrar(request, response);
					break;
				case "showedit":
					System.out.println("yendo a la p�gina de editar");
					showEditorRepartidor(request, response);
					break;	
				case "editar":
					editar(request, response);
					break;
				case "eliminar":
					eliminar(request, response);
					break;
				case "buscar":
					buscarRepartidores(request, response);
					break;
				default:
					break;
				}			
			} catch (SQLException e) {
				e.getStackTrace();
			}
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("Hola Servlet..");
			doGet(request, response);
		}
		
		
		private void showRegistroRepartidor(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("/Vista/AdminRepartidor/RegistroRepartidorIH.jsp");
			dispatcher.forward(request, response);
		}
		
		private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
		
		private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			Boolean existe = repartidorDAO.validarCorreoElectronico(request.getParameter("correoElectronico"));
			System.out.println("existe es " + existe);
			Repartidor repartidor = new Repartidor(request.getParameter("nombre"), request.getParameter("apellidoPaterno"), request.getParameter("apellidoMaterno"),request.getParameter("correoElectronico"));
			repartidor.generarPassword();
			
			if(existe) {
				showRegistroRepartidor(request,response);
			}else {
				repartidorDAO.insertar(repartidor);
				repartidorDAO.enviarRegistroRepartidor(repartidor); 
				mostrar(request,response);
			}
		
			
		}
		
		private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		
		private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/AdminRepartidor/RepartidorAdminIH.jsp");
			List<Repartidor> repartidores= repartidorDAO.listarRepartidores();
			request.setAttribute("lista", repartidores);
			
			dispatcher.forward(request, response);
		}	
		
		
		private void showEditorRepartidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			System.out.println("entra a show Editor Repartidor");
			System.out.println(Integer.parseInt(request.getParameter("idPersona")));
			Repartidor repartidor = repartidorDAO.obtenerPorId(Integer.parseInt(request.getParameter("idPersona")));
			request.setAttribute("repartidor", repartidor);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/AdminRepartidor/EditorRepartidorIH.jsp");
			dispatcher.forward(request, response);
		}
		
		
		private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			int idPersona = Integer.parseInt(request.getParameter("idPersona"));
			Repartidor repartidor = repartidorDAO.obtenerPorId(idPersona);
			repartidor.setNombre(request.getParameter("nombre"));
			repartidor.setApellidoPaterno(request.getParameter("apellidoPaterno"));
			repartidor.setApellidoMaterno(request.getParameter("apellidoMaterno"));
			repartidor.setCorreoElectronico(request.getParameter("correoElectronico"));
			repartidorDAO.actualizar(repartidor);
			mostrar(request,response);
			
		}
		
		private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			Repartidor repartidor = repartidorDAO.obtenerPorId(Integer.parseInt(request.getParameter("idPersona")));
			System.out.println(repartidor.toString());
			repartidorDAO.eliminar(repartidor);
			mostrar(request, response);
			
		}
		
		private void buscarRepartidores(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/AdminRepartidor/BusquedaRepartidor.jsp");
			String repartidorBuscado = request.getParameter("repartidorBuscado");
			List<Repartidor> repartidores = repartidorDAO.buscarRepartidor(repartidorBuscado);
			request.setAttribute("lista", repartidores);
			dispatcher.forward(request, response);
			
		}
	
		
		
	
	
	



}
