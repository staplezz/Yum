package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClienteDAO;
import dao.OrdenDAO;
import modelo.Cliente;
import modelo.Direccion;

/**
 * Servlet implementation class ModificadorCreaOrden
 */
@WebServlet("/creaOrden")
public class ModificadorCreaOrden extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrdenDAO ordenDAO;
	ClienteDAO clienteDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorCreaOrden() {
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
			ordenDAO = new OrdenDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
		
		HttpSession session = request.getSession(false);
		
		if(session!= null) {
			try {
				switch(action) {
				case "crearOrden": 
					crearOrden(request, response);
					break;
				case "seleccionaDireccion":
					seleccionaDireccion(request, response);
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
	
	// Muestra las direcciones de las cuales el usuario puede escoger para su orden.
	private void seleccionaDireccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session!= null) {
			// Obtenemos el id del cliente.
			Cliente cliente =(Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/ConfirmaOrden.jsp");
			
			//Obtenemos las direcciones.
			List<Direccion> direcciones = clienteDAO.mostrarDireccionesCliente(idCliente);
			
			cliente.setDirecciones(direcciones);
			
			request.setAttribute("direcciones", direcciones);
			session.setAttribute("cliente", cliente);
			
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	private void crearOrden(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session!= null) {
			// Obtenemos el id del cliente.
			Cliente cliente =(Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			// Obtenemos la dirección a la que será pedida la orden.
			int idDireccion = Integer.parseInt(request.getParameter("idDir"));
			
			//Obtenemos el id dirección cliente.
			int idDireccionCliente = ordenDAO.getIdDireccionCliente(idDireccion);
			
			// Hacemos la inserción de la orden.
			ordenDAO.creaOrden(idCliente, idDireccionCliente);
			
			// Regresamos al menú y notificamos que ya insertamos la orden.
			response.sendRedirect("muestraMenu?action=muestraMenuDefault&carrito=3");
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}

}
