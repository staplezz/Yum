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

import dao.CarritoDAO;
import dao.ClienteDAO;
import modelo.Cliente;
import modelo.AlimentoCarrito;

/**
 * Servlet implementation class ModificadorCliente
 */
@WebServlet("/modificadorCarrito")
public class ModificadorCarrito extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// Data access object para carrito.
	CarritoDAO carritoDAO;
	ClienteDAO clienteDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			carritoDAO = new CarritoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
			case "verCarrito": 
				verCarrito(request, response);
				break;
			case "vaciarCarrito": 
				vaciarCarrito(request, response);
				break;
			case "agregaAlimento":
				agregaAlimentoCarrito(request, response);
				break;
			case "eliminaAlimento":
				eliminaAlimento(request, response);
				break;
			case "confirmarOrden":
				confirmarOrden(request, response);
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
	
	
	
	private void verCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/VerCarritoIH.jsp");
			
			// Info del cliente.
			Cliente cliente =(Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			List<AlimentoCarrito> alimentos = carritoDAO.getAlimentos(idCliente);
			
			if (alimentos.isEmpty()) {
				response.sendRedirect("muestraMenu?action=muestraMenuDefault&carrito=2");
				
			} else {
				// Mandamos lista de alimentos.
				request.setAttribute("lista", alimentos);				
				
				// Calculamos el total del carrito.
				int idCarrito = carritoDAO.obtenerCarrito(idCliente);
				int total = carritoDAO.totalCarrito(idCarrito);
				request.setAttribute("total", total);
			
				dispatcher.forward(request,response);
			}
		}else {
			PrintWriter out = response.getWriter();
			out.print("Por favor ingresa primero"); 
			request.getRequestDispatcher("index.jsp").include(request, response);	
		}
	}
	
	/*
	 * Agrega un alimento al carrito del cliente.
	 */
	private void agregaAlimentoCarrito(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session!= null) {
			//El id del alimento.
			int idAlimento = Integer.parseInt(request.getParameter("id"));
			
			// Info del cliente.
			Cliente cliente =(Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			//Obtenemos el id del carrito.
			int idCarrito = clienteDAO.getIdCarrito(idCliente);
			
			//Hacemos la inserción.
			clienteDAO.insertaAlimento(idAlimento, idCarrito); 
			
			request.getRequestDispatcher("modificadorCarrito?action=verCarrito").include(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.print("Por favor ingresa primero"); 
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	private void vaciarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession(false);
		
		if(session!= null) {
			// Info del cliente.
			Cliente cliente =(Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			//Obtenemos el id del carrito.
			int idCarrito = clienteDAO.getIdCarrito(idCliente);
			
			//Hacemos la eliminación.
			carritoDAO.vaciarCarrito(idCarrito); 
			
			response.sendRedirect("muestraMenu?action=muestraMenuDefault&carrito=0");
		} else {
			PrintWriter out = response.getWriter();
			out.print("Por favor ingresa primero"); 
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	// Elimina un alimento del carrito.
	private void eliminaAlimento(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		
		if(session!= null) {
			//El id del alimento.
			int idAlimento = Integer.parseInt(request.getParameter("id"));
			
			// Info del cliente.
			Cliente cliente =(Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			//Obtenemos el id del carrito.
			int idCarrito = clienteDAO.getIdCarrito(idCliente);
			
			//Hacemos la eliminación.
			int numAlimentos = carritoDAO.eliminarAlimentoCarrito(idAlimento, idCarrito);
			System.out.println("numali " + numAlimentos);
			
			if (numAlimentos == 0) {
				response.sendRedirect("muestraMenu?action=muestraMenuDefault&carrito=2");
			} else {
				request.getRequestDispatcher("modificadorCarrito?action=verCarrito").include(request, response);
			}
		} else {
			PrintWriter out = response.getWriter();
			out.print("Por favor ingresa primero"); 
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	
	
	/*
	 * Obtiene las órdenes con estado 1, 2 y 3 para que el administrador
	 * las vea.
	 */
	private void confirmarOrden(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		// Obtenemos las órdenes en estado 2 (Sin repartidor asignado).
		//List<OrdenRepartidor> ordenesListas = ordenRepartidorDAO.getOrdenesListas();
		System.out.println("confirmo la orden");
		
		//Mandamos las órdenes al jsp.
		//request.setAttribute("ordenesListas", ordenesListas);
		
		//Mostramos vista.
		request.getRequestDispatcher("/Vista/Cliente/ConfirmarPedidoIH.jsp").forward(request, response);
	}

}

