package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.RequestDispatcher;
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
			String correo = request.getParameter("email");
			String rawPassword = request.getParameter("password"); 
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			
			Cliente cliente = null; 
			Administrador administrador = null; 
			Repartidor repartidor = null; 
			
			cliente = personaDAO.buscaCliente(correo); 
			administrador = personaDAO.buscaAdministrador(correo);
			System.out.println("AQUíiii");
			System.out.println(administrador);
			repartidor = personaDAO.buscaRepartidor(correo);
			
			if(cliente != null && passwordEncoder.matches(rawPassword, cliente.getPassword())) {
				HttpSession session = request.getSession();
				cliente.setPassword(rawPassword);
				session.setAttribute("cliente", cliente);
				response.sendRedirect("modificadorCliente?action=mostrarEditarCliente");
			}else if(administrador != null && rawPassword.equals(administrador.getPassword())) {
				System.out.println(administrador.getNombre());
				HttpSession session = request.getSession();
				session.setAttribute("administrador", administrador);
				
				response.sendRedirect("adminMenu?action=mostrarMenu");
			}else if(repartidor != null && rawPassword.equals(repartidor.getPassword())) {
				
				HttpSession session = request.getSession();
				session.setAttribute("repartidor", repartidor);
				
				response.sendRedirect("jsp/successRepartidor.jsp");
			}else {
				PrintWriter out = response.getWriter();
				out.println("Contraseña o correo incorrectos <a href='index.jsp'>intenta de nuevo.</a>");
				out.close();
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
