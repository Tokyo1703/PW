<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="src.Negocio.DTO.UsuarioDTO, src.Datos.DAO.UsuarioDAO, src.Negocio.DTO.Enum.TipoUsuario" %>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>
<%

    String fileSQL = application.getInitParameter("sql");
    String fileCONFIG = application.getInitParameter("config");
    java.io.InputStream myIOsql = application.getResourceAsStream(fileSQL);
    java.io.InputStream myIOconfig = application.getResourceAsStream(fileCONFIG);
    java.util.Properties sqlProperties = new java.util.Properties();
    java.util.Properties configProperties = new java.util.Properties();
    sqlProperties.load(myIOsql);
    configProperties.load(myIOconfig);
    /* Posibles flujos:
        1) customerBean está logado (!= null && != "") -> Se redirige a la vista del tipo de usuario
        2) customerBean no está logado:
            a) Hay parámetros en el request  -> procede de la vista 
            b) No hay parámetros en el request -> procede de otra funcionalidad o index.jsp
        */
    //Caso 1
    String nextPage="../../index.jsp";
    String mensajeNextPage = "";
    if(customerBean != null && !customerBean.getCorreo().equals("")){
        if(customerBean.getTipo()==TipoUsuario.Asistente){
            nextPage = "../vistas/asistenteVista.jsp";
        }
        else{
            nextPage = "../vistas/administradorVista.jsp";
        }
    }
    //Caso 2
    else if (customerBean == null || customerBean.getCorreo().equals("")) {
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contrasena");
        String tipo = request.getParameter("tipo"); 
        //Caso 2.a: Hay parámetros -> procede de la VISTA
        if (correo != null) {

            UsuarioDAO usuarioDAO = new UsuarioDAO(sqlProperties,configProperties);
            

            //Se realizan todas las comprobaciones necesarias del dominio

            //Aqui comprobamos que no exista el usuario
            if(usuarioDAO.existeCorreo(correo)){
                nextPage = "../vistas/registroVista.jsp";
                mensajeNextPage = "Ya existe un usuario con ese correo";
            }
            //Aqui ya sabemos que no existe ese usuario
            else{
                UsuarioDTO usuario = new UsuarioDTO(correo,contrasena,TipoUsuario.valueOf(tipo));
                usuarioDAO.AgregarUsuario(usuario);
              
%>
                <jsp:setProperty property="correo" value="<%=correo%>" name="customerBean"/>
                <jsp:setProperty property="contrasena" value="<%=contrasena%>" name="customerBean"/>
                <jsp:setProperty property="tipo" value="<%=usuario.getTipo()%>" name="customerBean"/>                
<%              
                if(usuario.getTipo()==TipoUsuario.Asistente){
                    nextPage = "../vistas/asistenteVista.jsp";
                }
                else{
                    nextPage = "../vistas/administradorVista.jsp";
                }
                
                
            }
            
        //Caso 2.b -> se debe ir a la vista por primera vez
        }
        else {
            nextPage = "../vistas/registroVista.jsp";		
        }
    }
%>
<jsp:forward page="<%=nextPage%>">
	<jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>