<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="src.Negocio.DTO.UsuarioDTO, src.Datos.DAO.UsuarioDAO" %>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>
<%
    /* Posibles flujos:
        1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp
        2) customerBean no está logado:
            a) Hay parámetros en el request  -> procede de la vista 
            b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
        */
    //Caso 1: Por defecto, vuelve al index
    String nextPage = "../../index.jsp";
    String mensajeNextPage = "";
    //Caso 2
    if (customerBean == null || customerBean.getCorreo().equals("")) {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");

        //Caso 2.a: Hay parámetros -> procede de la VISTA
        if (correo != null) {
            //Se accede a bases de datos para obtener el usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioDTO usuario = new UsuarioDTO(); 
            usuario.setContrasena("cbm");
            //Se realizan todas las comprobaciones necesarias del dominio
            //Aquí sólo comprobamos que exista el usuario
            
            if (usuario != null && usuario.getContrasena().equals(contrasena) ) {
                // Usuario válido
                nextPage = "../vistas/loginCorrecto.jsp";		
%>
                <jsp:setProperty  property="correo" value="<%=correo%>" name="customerBean"/>
                <jsp:setProperty  property="contrasena" value="<%=contrasena%>" name="customerBean"/>
<%              

            } else {
                // Usuario no válido
                nextPage = "../vistas/loginCorrecto.jsp";
                mensajeNextPage = "El usuario que ha indicado no existe o la contraseña es incorrecta";
		    }
        //Caso 2.b -> se debe ir a la vista por primera vez
        }
        else {
            nextPage = "../vistas/loginVista.jsp";		
        }
    }
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>