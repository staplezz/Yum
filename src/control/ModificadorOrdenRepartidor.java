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

import dao.OrdenRepartidorDAO;
import modelo.AlimentoOrden;
import modelo.OrdenRepartidor;
import dao.OrdenDAO;

/**
 * Servlet implementation class ModificadorOrdenRepartidor
 */
@WebServlet("/ordenRepartidor")
public class ModificadorOrdenRepartidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Data access object para orden.
	OrdenRepartidorDAO ordenRepartidorDAO;
	OrdenDAO ordenDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorOrdenRepartidor() {
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
			ordenRepartidorDAO = new OrdenRepartidorDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
			
			case "misOrdenes":
				mostrarMisOrdenes(request, response);
				break;
				
			default: 
				break;
			}
		}catch(SQLException e) {
			e.getStackTrace();
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
	 * Obtiene las órdenes con estado 2 para que el repartidor las tome.
	 */
	private void mostrarOrdenes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos las órdenes en estado 2 (Sin repartidor asignado).
		List<OrdenRepartidor> ordenesListas = ordenRepartidorDAO.getOrdenesListas();
		System.out.println("obtengo Ordenes");
		
		//Mandamos las órdenes al jsp.
		request.setAttribute("ordenesListas", ordenesListas);
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/Repartidor/MostrarOrdenesRepartidorIH.jsp").forward(request, response);
	}
	
	/*
	 * Obtiene las órdenes con estado 3 para que vea sus órdenes.
	 */
	private void mostrarMisOrdenes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		//El id del repartidor.
		int idRepartidor = Integer.parseInt(request.getParameter("idRepartidor"));
		System.out.println("aqui entro");
		// Obtenemos las órdenes en estado 2 (Sin repartidor asignado).
		List<OrdenRepartidor> misOrdenes = ordenRepartidorDAO.getMisOrdenes(idRepartidor);
		System.out.println("obtengo Ordenes");
		
		//Mandamos las órdenes al jsp.
		request.setAttribute("misOrdenes", misOrdenes);
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/Repartidor/misOrdenes.jsp").forward(request, response);
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
		request.getRequestDispatcher("/Vista/Repartidor/mostrarOrden.jsp").forward(request, response);
	}
	
	/*
	 * Hace una actualización de la orden al estado siguiente que corresponde.
	 */
	private void cambiaEstado(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos el id que nos pasan de la órden.
		int idOrden = Integer.parseInt(request.getParameter("id"));
		
		// Obtenemos el estado actual de la órden.
		int estado = Integer.parseInt(request.getParameter("estado"));
		
		//El id del repartidor.
		int idRepartidor = Integer.parseInt(request.getParameter("idRepartidor"));
		
		//Hacemos la actualización
		ordenRepartidorDAO.actualizaOrden(idOrden, estado, idRepartidor);
		
		request.getRequestDispatcher("ordenRepartidor?action=mostrarOrdenes").forward(request, response);
	}

}
