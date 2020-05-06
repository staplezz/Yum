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

import dao.AlimentoDAO;
import modelo.Alimento;

@WebServlet("/adminAlimento")
public class AdminAlimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlimentoDAO alimentoDAO;
	
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			alimentoDAO = new AlimentoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("ENTRA BDD");
		} catch(Exception e) {
			//TODO: handle exception.
		}
	}
	
	public AdminAlimento() {
		super();
	}
	
    /**
     * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Overriding service() usually isn't needed. - The default implementation mostly
        // does the right thing&reg;
        super.service(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		try {
			//Por defecto mostramos los alimentos.
			List<Alimento> listaAlimentos = alimentoDAO.listarAlimentos();
			System.out.println("Obteniendo");
			request.setAttribute("lista", listaAlimentos);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch(SQLException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ENTRA POST");
		
		//Regresamos a inicio
		try {
			if(request.getParameter("agregar") != null) {
				System.out.println("Agregando registro a BDD");
				registrar(request, response);
			}
			else if (request.getParameter("editar") != null) {
				actualizar(request, response);
				System.out.println("Actualizando registro a BDD");
			}
			else if (request.getParameter("eliminar") != null) {
				eliminar(request, response);
				System.out.println("ELiminando registro a BDD");
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		//Por defecto mostramos los alimentos.
		List<Alimento> listaAlimentos = alimentoDAO.listarAlimentos();
		System.out.println("Obteniendo");
		request.setAttribute("lista", listaAlimentos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alimento alimento = new Alimento(0, request.getParameter("nombreAl"), Float.parseFloat(request.getParameter("precio")), request.getParameter("descripcion"), request.getParameter("categoria"));
		alimentoDAO.insertaAlimento(alimento);
		
		mostrar(request, response);
	}
	
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Alimento alimento = new Alimento(Integer.parseInt(request.getParameter("idAl")), request.getParameter("nombre"), Float.parseFloat(request.getParameter("precio")), request.getParameter("descripcion"), request.getParameter("categoria"));
		alimentoDAO.actualizaAlimento(alimento);
		System.out.println("YA actualice");
		mostrar(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		alimentoDAO.eliminaAlimento(Integer.parseInt(request.getParameter("idAl")));
		System.out.println("YA elimine");
		mostrar(request, response);
	}
	
}
