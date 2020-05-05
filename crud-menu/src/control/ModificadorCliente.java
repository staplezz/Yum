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

import dao.ClienteDAO;
import modelo.Cliente;
import modelo.Direccion;
/**
 * Servlet implementation class ModificadorCliente
 */
@WebServlet("/modificadorCliente")
public class ModificadorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteDAO clienteDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {

			clienteDAO = new ClienteDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"); 
		System.out.println(action);
		try {
			switch(action) {
			case "registraCliente": 
				infoCliente(request, response);
				break;
			case "agregaCliente": 
				agregarCliente(request, response); 
				break;
			case "principalCliente": 
				principalCliente(request, response);
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
	
	private void infoCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/registroCliente.jsp");
		dispatcher.forward(request, response);
	}
	
	private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/index.jsp");
		String nombre = request.getParameter("nombre"); 
		String apePat = request.getParameter("apePat"); 
		String apeMat = request.getParameter("apeMat");
		String email  = request.getParameter("email"); 
		String password = request.getParameter("password"); 
		String telefono  = request.getParameter("telefono");
		
		System.out.println("AQUIII"+ telefono);
		
		
		String delegacion = request.getParameter("delegacion"); 
		String colonia = request.getParameter("colonia"); 
		String calle = request.getParameter("calle"); 

	
		Cliente cliente = new Cliente(nombre, apePat, apeMat, email, telefono,password);
		Direccion direccion = new Direccion(delegacion, colonia, calle);
		
		if (request.getParameter("numInt") != null) {
			int numInterior = Integer.parseInt(request.getParameter("numInt"));
			direccion.setNumInterior(numInterior);
		}
			
		
		if (request.getParameter("numExt") != null) {
			int numExterior = Integer.parseInt(request.getParameter("numExt"));
			direccion.setNumExterior(numExterior);

		}
					
		clienteDAO.crearCliente(cliente, direccion); 
		
		
		dispatcher.forward(request, response);
	}
	
	private void principalCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

}
