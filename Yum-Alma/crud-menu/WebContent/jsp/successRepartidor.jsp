<%
    if (session.getAttribute("idRepartidor")!= null){
%>
No se ha podido iniciar sesión<br/>
<a href="index.jsp">Por favor inicia sesión</a>
<%} else {
%>
Bienvenida(o) <%=session.getAttribute("nombre")%>
<a href='./../logout.jsp'>Cerrar sesión</a>
<%
    }
%>
