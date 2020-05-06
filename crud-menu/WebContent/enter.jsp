<%@ page import="java.sql.*"%>
<%
    String correo = request.getParameter("email");    
    String password = request.getParameter("password");
    System.out.println("CORREEEO"+ correo); 
    System.out.println("PAAASSSWORD"+ password); 
 
    
    // querys para todos
    String sqlCliente = "SELECT idCliente, nombre FROM persona join cliente ";
    sqlCliente+= "WHERE persona.correoElectronico = ? and ";
    sqlCliente+= "persona.password = ? and persona.idPersona = cliente.idPersona";
   
    String sqlAdmin = "SELECT idAdministrador, nombre FROM persona join administrador ";
    sqlAdmin += "WHERE persona.correoElectronico =? and persona.password = ? and ";
    sqlAdmin += "persona.idPersona = administrador.idPersona";
    
    String sqlRepartidor = "SELECT idRepartidor, nombre FROM persona join repartidor ";
    sqlRepartidor += "WHERE persona.correoElectronico =? and persona.password = ? and ";
    sqlRepartidor += "persona.idPersona = repartidor.idPersona";
    
    System.out.println(sqlCliente); 
    System.out.println(sqlAdmin); 
    System.out.println(sqlRepartidor);
    
    //Conexión
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yum_db", "root", "Mdfnll#04a");
    
    PreparedStatement clienteStatement = con.prepareStatement(sqlCliente);
    clienteStatement.setString(1, correo); 
    clienteStatement.setString(2, password);
    System.out.println(clienteStatement);
    ResultSet rsU = clienteStatement.executeQuery();
 
    
    PreparedStatement adminStatement = con.prepareStatement(sqlAdmin);
    adminStatement.setString(1, correo); 
    adminStatement.setString(2, password);
    System.out.println(adminStatement);
    ResultSet rsA = adminStatement.executeQuery();
   
    PreparedStatement repartidorStatement = con.prepareStatement(sqlRepartidor);
    repartidorStatement.setString(1, correo); 
    repartidorStatement.setString(2, password);
    System.out.println(repartidorStatement); 
    ResultSet rsR = repartidorStatement.executeQuery();
    
    
    if(rsU.next()){
    	System.out.println("USUARIO");
    	
    	int idCliente = rsU.getInt("idCliente");
    	String nombre = rsU.getString("nombre"); 
    	
    	session.setAttribute("id", idCliente); 
    	session.setAttribute("nombre", nombre);
    	
    	response.sendRedirect("modificadorCliente?action=mostrarEditarCliente&idCliente="+idCliente);
    }else if(rsA.next()){
    	System.out.println("ADDDDDMINN");
    	
    	int idAdmin = rsA.getInt("idAdministrador"); 
    	String nombre = rsA.getString("nombre");
    	
   	    session.setAttribute("id", idAdmin);
   	    session.setAttribute("nombre", nombre);
   	    
   	    System.out.println(idAdmin+nombre);
   	    
        //aqui va a ser lo de ver órdenes
        response.sendRedirect("adminMenu?action=mostrarMenu");
    }else if(rsR.next()){
    	 System.out.println("REPARTIDOR");
    	 
    	 int idRepartidor = rsR.getInt("idRepartidor");
    	 String nombre = rsR.getString("nombre");
    	 
    	 session.setAttribute("id", idRepartidor);
    	 session.setAttribute("nombre", nombre);
    	 
         response.sendRedirect("jsp/successRepartidor.jsp");		
    }else{
    	out.println("Contraseña incorrecta <a href='index.jsp'>intenta de nuevo.</a>");	
    }
    
    clienteStatement.close(); 
    adminStatement.close(); 
    repartidorStatement.close();
    
%>