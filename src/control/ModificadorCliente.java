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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dao.ClienteDAO;
import modelo.AlimentoOrden;
import modelo.Cliente;
import modelo.Direccion;
import modelo.OrdenAdmin;
import dao.OrdenDAO;

/**
 * Servlet implementation class ModificadorCliente
 */
@WebServlet("/modificadorCliente")
public class ModificadorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteDAO clienteDAO;
	OrdenDAO ordenDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			ordenDAO = new OrdenDAO(jdbcURL, jdbcUsername, jdbcPassword);
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
			case "mostrarEditarCliente": 
				System.out.println("PFFFF"+request.getParameter("idCliente"));
				mostrarEditarCliente(request, response);
				break;
			case "editarCliente": 
				editarCliente(request, response); 
				break;
			case "mostrarDirecciones": 
				mostrarDireccionesCliente(request, response); 
				break; 
			case "mostrarEditarDireccion": 
				mostrarEditarDireccion(request, response); 
				break; 
			case "editarDireccion": 
				editarDireccionCliente(request, response); 
				break; 
			case "eliminarDireccion": 
				eliminarDireccionCliente(request, response);
				break;
			case "mostrarOrdenesActuales":
				System.out.println("entro mostrar �rdenes");
				mostrarOrdenesActuales(request, response);
				break;
			case "verOrden":
				verOrden(request, response);
				break;
			case "mostrarHistorialOrdenes":
				mostrarHistorialOrdenes(request,response);
				break;
			case "agregarDireccion": 
				agregarDireccion(request, response); 
				break;
			case "calificarOrden":
				calificarOrden(request, response); 
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/CreaClienteIH.jsp");
		dispatcher.forward(request, response);
	}
	
	private void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		String nombre = request.getParameter("nombre"); 
		String apePat = request.getParameter("apePat"); 
		String apeMat = request.getParameter("apeMat");
		String email  = request.getParameter("email"); 
		String rawPassword = request.getParameter("password"); 
		String telefono  = request.getParameter("telefono");		
		String delegacion = request.getParameter("delegacion"); 
		String colonia = request.getParameter("colonia"); 
		String calle = request.getParameter("calle"); 

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(rawPassword);
		
		
		if (clienteDAO.existClienteEmail(email)) {
			PrintWriter out = response.getWriter(); 
			
			out.println("<div class=\"alert alert-danger\" role=\"alert\">\r\n" + 
					"  Correo ya registrado, intenta con otro\r\n" + 
					"</div>"); 
			request.getRequestDispatcher("/Vista/Cliente/CreaClienteIH.jsp").include(request, response);
			out.close();
		}else {
			Cliente cliente = new Cliente(nombre, apePat, apeMat, email, telefono,password);
			Direccion direccion = new Direccion(delegacion, colonia, calle);
			
			if (request.getParameter("numInt") != "") {
				int numInterior = Integer.parseInt(request.getParameter("numInt"));
				direccion.setNumInterior(numInterior);
			}
				
			
			if (request.getParameter("numExt") != "") {
				int numExterior = Integer.parseInt(request.getParameter("numExt"));
				direccion.setNumExterior(numExterior);

			}
			
			clienteDAO.crearCliente(cliente, direccion); 
			System.out.println("registro cliente:");
			
			dispatcher.forward(request, response);
		}
		
	}
	
	private void mostrarEditarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			//response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/EditaClienteIH.jsp");
			
			Cliente clienteUno =(Cliente)session.getAttribute("cliente");
			String password = clienteUno.getPassword();
			int idCliente = clienteUno.getIdCliente();
			System.out.println("DOBLEEEE "+ password);
			
			Cliente cliente = clienteDAO.mostrarClienteId(idCliente);
			cliente.setPassword(password);
			request.setAttribute("cliente", cliente);
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			Cliente nuevo = (Cliente)session.getAttribute("cliente");
			String nombre = request.getParameter("nombre");
			System.out.println("Edito nombre cliente: " + nombre);
			String apePat = request.getParameter("apePat"); 
			String apeMat = request.getParameter("apeMat");
			String rawPassword = request.getParameter("password");
			String telefono  = request.getParameter("telefono");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = passwordEncoder.encode(rawPassword);
			
			nuevo.setNombre(nombre);
			nuevo.setApellidoPaterno(apePat);
			nuevo.setApellidoMaterno(apeMat);
			nuevo.setPassword(password);
			nuevo.setTelefono(telefono);
			
			clienteDAO.editarCliente(nuevo);
			nuevo.setPassword(rawPassword);
			session.setAttribute("cliente", nuevo);
			request.setAttribute("cliente", nuevo);
			PrintWriter out = response.getWriter(); 
			
			out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>Cambios guardados</strong> Puedes continuar haciendo cambios en direcciones o regresar.\r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>"); 
			request.getRequestDispatcher("/Vista/Cliente/EditaClienteIH.jsp").include(request, response);
			out.close();
			
			
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	private void mostrarDireccionesCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/MostrarDireccionesIH.jsp");
			List<Direccion> direcciones = clienteDAO.mostrarDireccionesCliente(idCliente);
			cliente.setDirecciones(direcciones);
			
			request.setAttribute("direcciones", direcciones);
			session.setAttribute("cliente", cliente);
			
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}
	
	
	private void mostrarEditarDireccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/EditarDireccionClienteIH.jsp");
			Direccion direccion = clienteDAO.mostrarDireccionId(Integer.parseInt(request.getParameter("idDireccion")));
			request.setAttribute("direccion", direccion);
			session.setAttribute("cliente", cliente);
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}
	private void editarDireccionCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			int idDireccion = Integer.parseInt(request.getParameter("idDireccion"));
			String delegacion = request.getParameter("delegacion"); 
			String colonia = request.getParameter("colonia"); 
			String calle = request.getParameter("calle");
			
			Direccion direccion = new Direccion(delegacion, colonia, calle);
			direccion.setIdDireccion(idDireccion);
			
			if (request.getParameter("numInt") != "") {
				int numInterior = Integer.parseInt(request.getParameter("numInt"));
				direccion.setNumInterior(numInterior);
			}
				
			
			if (request.getParameter("numExt") != "") {
				int numExterior = Integer.parseInt(request.getParameter("numExt"));
				direccion.setNumExterior(numExterior);

			}
			
			clienteDAO.editarDireccion(direccion);
			request.setAttribute("direccion", direccion);
			session.setAttribute("cliente", cliente);
			PrintWriter out = response.getWriter(); 
			
			out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>Cambios guardados</strong> Cambios guardados :) \r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>"); 
			
			request.getRequestDispatcher("/Vista/Cliente/EditarDireccionClienteIH.jsp").include(request, response);
			
			out.close();
			
			
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}
	
	private void eliminarDireccionCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			int idDireccion = Integer.parseInt(request.getParameter("idDireccion"));
			int idCliente = cliente.getIdCliente();
			request.setAttribute("idCliente", idCliente);
			
			clienteDAO.eliminarDireccion(idDireccion); 
			session.setAttribute("cliente", cliente);
			mostrarDireccionesCliente(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
				
	}
	
	
	private void agregarDireccion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null) {
			Cliente cliente = (Cliente)session.getAttribute("cliente");
			int idCliente = cliente.getIdCliente();
			System.out.println("id"+idCliente);
			
			String delegacion = request.getParameter("delegacion"); 
			String colonia = request.getParameter("colonia"); 
			String calle = request.getParameter("calle"); 
			System.out.println("holia"+delegacion);
			
			Direccion direccion = new Direccion(delegacion, colonia, calle);
			
			
			if (request.getParameter("numInt") != null) {
				int numInterior = Integer.parseInt(request.getParameter("numInt"));
				direccion.setNumInterior(numInterior);
			}
				
			
			if (request.getParameter("numExt") != null) {
				int numExterior = Integer.parseInt(request.getParameter("numExt"));
				direccion.setNumExterior(numExterior);

			}
			
			clienteDAO.agregarDireccion(direccion, idCliente);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/MostrarDireccionesIH.jsp");
			List<Direccion> direcciones = clienteDAO.mostrarDireccionesCliente(idCliente);
			cliente.setDirecciones(direcciones);
			
			request.setAttribute("direcciones", direcciones);
			session.setAttribute("cliente", cliente);
			
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
		
	}
	
	/* Natalia */
	private void mostrarOrdenesActuales(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			Cliente clienteUno =(Cliente)session.getAttribute("cliente");
			int idCliente = clienteUno.getIdCliente();
			
			// Ordenes con repartidor
			List<OrdenAdmin> ordenesActuales = ordenDAO.getOrdenesCliente(idCliente);
			request.setAttribute("ordenesActuales", ordenesActuales);
			// Ordenes sin repartidor.
			List<OrdenAdmin> ordenesSinRep = ordenDAO.getOrdenesClienteSinRep(idCliente);
			request.setAttribute("ordenesSinRep", ordenesSinRep);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/ClienteOrdenIH.jsp");
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	/* Natalia */
	private void verOrden(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			int idOrden = Integer.parseInt(request.getParameter("idOrden"));
			
			
			List<AlimentoOrden> listaAlimentos = ordenDAO.obtenAlimentosOrden(idOrden);
			System.out.println("obtengo Alimentos de orden con id: " + idOrden);
			
			//Obtenemos la suma total de costos.
			int total = ordenDAO.totalOrden(idOrden);
			
			//Mandamos al jsp.
			request.setAttribute("listaAlimentos", listaAlimentos);
			//El id de la �rden
			request.setAttribute("ordenId", idOrden);
			//Total de la �rden.
			request.setAttribute("total", total);
			request.getRequestDispatcher("/Vista/Cliente/MostrarOrdenCliente.jsp").forward(request, response);
			
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	/* Natalia */
	private void mostrarHistorialOrdenes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			
			Cliente clienteUno =(Cliente)session.getAttribute("cliente");
			
			int idCliente = clienteUno.getIdCliente();
			System.out.println(idCliente + clienteUno.getApellidoPaterno());
			List<OrdenAdmin> ordenes = ordenDAO.getHistorialOrdenCliente(idCliente);
			request.setAttribute("ordenes", ordenes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/Cliente/ClienteHistorialOrdenIH.jsp");
			dispatcher.forward(request, response);
		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}
	
	/* Alma */
	private void calificarOrden(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		HttpSession session = request.getSession(false); 
		if(session!= null) {
			int idOrden = Integer.parseInt(request.getParameter("idO")); 
			int calificacion = Integer.parseInt(request.getParameter("calificacion"));

			Cliente clienteUno =(Cliente)session.getAttribute("cliente");
			int idCliente = clienteUno.getIdCliente();

			ordenDAO.calificarOrden(idOrden, calificacion); 
			PrintWriter out = response.getWriter(); 

			out.println("<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\r\n" + 
					"  <strong>Cambios guardados</strong> Orden calificada \r\n" + 
					"  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n" + 
					"    <span aria-hidden=\"true\">&times;</span>\r\n" + 
					"  </button>\r\n" + 
					"</div>"); 

			List<OrdenAdmin> ordenes = ordenDAO.getHistorialOrdenCliente(idCliente);
			request.setAttribute("ordenes", ordenes);
			request.getRequestDispatcher("/Vista/Cliente/ClienteHistorialOrdenIH.jsp").include(request, response);
			out.close();

		}else {
			request.getRequestDispatcher("index.jsp").include(request, response);
		}
	}

}
