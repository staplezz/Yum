package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.AlimentoDAO;
import modelo.Alimento;

/**
 * Servlet implementation class ModificadorAlimento
 */
@MultipartConfig
@WebServlet("/adminAlimento")
public class ModificadorAlimento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlimentoDAO alimentoDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificadorAlimento() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			alimentoDAO = new AlimentoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("ENTRA BDD");
		} catch(Exception e) {
			System.out.println("Ocurrio un error al entrar a la BDD");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Por defecto mostramos los alimentos.
			List<Alimento> listaAlimentos = alimentoDAO.listarAlimentos();
			System.out.println("obtengo Alimentos");
			request.setAttribute("lista", listaAlimentos);
			
			request.getRequestDispatcher("/Vista/AdminAlimento/AdministrarAlimentoIH.jsp").forward(request, response);
		} catch(SQLException e) {
			e.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Manejo de peticiones del usuario.
		try {
			if(request.getParameter("agregar") != null) {
				registrar(request, response);
				System.out.println("Alimento agregado a la BDD.");
			}
			else if (request.getParameter("editar") != null) {
				actualizar(request, response);
				System.out.println("Alimento actualizado en la BDD.");
			}
			else if (request.getParameter("eliminar") != null) {
				eliminar(request, response);
				System.out.println("Alimento eliminado de la BDD.");
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
	}
	
	private void mostrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException , ServletException{
		//Por defecto mostramos los alimentos.
		List<Alimento> listaAlimentos = alimentoDAO.listarAlimentos();
		request.setAttribute("lista", listaAlimentos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Vista/AdminAlimento/AdministrarAlimentoIH.jsp");
		dispatcher.forward(request, response);
	}
	
	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//Obtenemos la ruta donde se guarda la imágen.
		String path = "";
		Part archivo = request.getPart("archivoImagen");
		
		// Si no nos pasan ningún archivo de imágen.
		if (archivo.getSize() == 0)
			path = "sinfoto.png";
		else
			path = guardaArchivo(archivo);
		
		
		Alimento alimento = new Alimento(0, request.getParameter("nombreAl"),
				Double.parseDouble(request.getParameter("precio")), 
				request.getParameter("descripcion"),
				0,
				path,
				request.getParameter("categoria"));
		
		alimentoDAO.insertaAlimento(alimento);
		
		mostrar(request, response);
	}
	
	// Método auxiliar que guarda un archivo en la ruta indicada y regresa
	// la ruta en donde se guardó.
	private String guardaArchivo(Part filepart) throws IOException {
		// Obtenemos el nombre del archivo.
		String fileName = Paths.get(filepart.getSubmittedFileName()).getFileName().toString();
		// Contenido del archivo.
		InputStream fileContent = filepart.getInputStream();
		
		//Guardamos la imágen.
	    String path = getServletContext().getInitParameter("imagenesAlimentos");
		File uploads = new File(path);
		File file = new File(uploads, fileName);

		try {
			// Guardamos el archivo.
		    Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		catch( Exception e) {
			System.out.println("Ocurrio un error al guardar la imágen: " + e);
		}
		
		// Regresamos la ruta. (Se especifíca porque ahí se guardaran
		// las imágenes en el servidor).
		return fileName;
	}
	
	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// El id del alimento a actualizar.
		int idAl = Integer.parseInt(request.getParameter("idAl"));
		
		// El archivo de imágen. (Si nos pasan).
		Part archivo = request.getPart("archivoImagen");
		
		// Obtenemos el path de la imágen actual del alimento.
		String path = alimentoDAO.tieneImagen(idAl);
		
		// Si nos pasan otra imágen la cambiamos.
		if(archivo.getSize() != 0) {
			path = guardaArchivo(archivo);
		}
		
		// En otro caso la dejamos igual.
		// Creamos el objeto de alimento inicial (sin path de imagen).
		Alimento alimento = new Alimento(Integer.parseInt(request.getParameter("idAl")), 
				request.getParameter("nombre"), 
				Double.parseDouble(request.getParameter("precio")), 
				request.getParameter("descripcion"),
				0,
				path,
				request.getParameter("categoria"));
		
		alimentoDAO.actualizaAlimento(alimento);
		
		mostrar(request, response);
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		alimentoDAO.eliminaAlimento(Integer.parseInt(request.getParameter("idAl")));
		
		mostrar(request, response);
	}

}
