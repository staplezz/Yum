package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import dao.PersonaDAO;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Repartidor;
/**
 * Servlet implementation class ModificadorLogin
 */
@WebServlet("/login")
public class ModificadorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PersonaDAO personaDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			personaDAO = new PersonaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			// Obtenemos el correo del login.
			String correo = request.getParameter("email");
			String rawPassword = request.getParameter("password"); 
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			
			// Objetos para cada uno de los posibles usuarios.
			Cliente cliente = null; 
			Administrador administrador = null; 
			Repartidor repartidor = null; 
			
			// Vemos si el correo es de cliente.
			cliente = personaDAO.buscaCliente(correo);
			
			// Vemos si el correo es de administrador.
			administrador = personaDAO.buscaAdministrador(correo);
			
			// Vemos si el correo es de administrador.
			repartidor = personaDAO.buscaRepartidor(correo);
			
			
			if(cliente != null && passwordEncoder.matches(rawPassword, cliente.getPassword())) {
				HttpSession session = request.getSession();
				cliente.setPassword(rawPassword);
				session.setAttribute("cliente", cliente);
				response.sendRedirect("muestraMenu?action=mostrarAlimentos&idCategoria=1");
				
			}else if(administrador != null && rawPassword.equals(administrador.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("administrador", administrador);
				response.sendRedirect("adminMenu?action=mostrarMenu");
				
			}else if(repartidor != null && rawPassword.equals(repartidor.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("repartidor", repartidor);
				response.sendRedirect("ordenRepartidor?action=mostrarOrdenes");
				
			}else {
				// En caso de no encontrar un usuario válido, lo indicamos.
				request.setAttribute("message", "El correo o contraseña ingresados son incorrectos"); // Will be available as ${message}
				request.getRequestDispatcher("index.jsp").forward(request,response);
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
}
