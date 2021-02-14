package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrdenDAO;
import modelo.AlimentoOrden;
import modelo.OrdenAdmin;

/**
 * Servlet para la administración de las órdenes dentro del CRUD.
 */
@WebServlet("/adminOrden")
public class ModificadorOrden extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Data access object para orden.
	OrdenDAO ordenDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorOrden() {
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
		} catch(Exception e) {
			System.out.println("Ocurrio un error al entrar a la BDD: " + e);
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
				case "mostrarOrdenes": 
					mostrarOrdenes(request, response);
					break;
				
				case "verOrden":
					verOrden(request, response);
					break;
					
				case "cambiaEstado":
					cambiaEstado(request, response);
					break;
					
				case "verHistorial":
					verHistorial(request, response);
					break;
					
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/*
	 * Muestra el historial de órdenes, es decir las que ya fueron entregadas y están
	 * en un estado 4.
	 */
	private void verHistorial(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos las órdenes.
		List<OrdenAdmin> historialOrdenes = ordenDAO.getHistorialOrdenesAdmin();
		
		//Mandamos lista al jsp.
		request.setAttribute("historialOrdenes", historialOrdenes);
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/AdminOrden/historialOrdenes.jsp").forward(request, response);
	}
	
	/*
	 * Obtiene las órdenes con estado 1, 2 y 3 para que el administrador
	 * las vea.
	 */
	private void mostrarOrdenes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos las órdenes en estado 1 y 2 (Sin repartidor asignado).
		List<OrdenAdmin> ordenesSinRep = ordenDAO.getOrdenesSinRep();
		System.out.println("obtengo Ordenes");
		
		// Obtenemos las órdenes en estado 3. (Con repartidor asignado).
		List<OrdenAdmin> ordenesConRep = ordenDAO.getOrdenesConRep();
		
		//Mandamos las órdenes al jsp.
		request.setAttribute("ordenesListas", ordenesSinRep);
		request.setAttribute("ordenesConRep", ordenesConRep);
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/AdminOrden/AdministrarOrdenAdministrador.jsp").forward(request, response);
	}
	
	/*
	 * Obtiene los alimentos de la orden y el costo total que tendrá la orden.
	 */
	private void verOrden(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos el id que nos pasan de la órden.
		int idOrden = Integer.parseInt(request.getParameter("id"));
		
		//Obtenemos los alimentos.
		List<AlimentoOrden> listaAlimentos = ordenDAO.obtenAlimentosOrden(idOrden);
		System.out.println("obtengo Alimentos de orden con id: " + idOrden);
		
		//Obtenemos la suma total de costos.
		int total = ordenDAO.totalOrden(idOrden);
		
		//Mandamos al jsp.
		request.setAttribute("listaAlimentos", listaAlimentos);
		//El id de la órden
		request.setAttribute("ordenId", idOrden);
		//Total de la órden.
		request.setAttribute("total", total);
		request.getRequestDispatcher("/Vista/AdminOrden/mostrarOrden.jsp").forward(request, response);
	}
	
	/*
	 * Hace una actualización de la orden al estado siguiente que corresponde.
	 */
	private void cambiaEstado(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos el id que nos pasan de la órden.
		int idOrden = Integer.parseInt(request.getParameter("id"));
		
		// Obtenemos el estado actual de la órden.
		int estado = Integer.parseInt(request.getParameter("estado"));
		
		//Hacemos la actualización
		ordenDAO.actualizaOrden(idOrden, estado);
		
		request.getRequestDispatcher("adminOrden?action=mostrarOrdenes").forward(request, response);
	}
}
