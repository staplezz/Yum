<%
session.setAttribute("id", null);
session.setAttribute("nombre", null); 
session.invalidate();
response.sendRedirect("index.jsp");
%>