<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina Administrador</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
        if(customerBean.getTipo().name().equals("Asistente")){
            mensaje="Esta pagina es exclusiva para usuarios administradores";
            paginaSiguiente="asistenteVista.jsp";
            %>
            <jsp:forward page="<%=paginaSiguiente%>">
            <jsp:param value="<%=mensaje%>" name="mensaje"/>
            </jsp:forward>
            <%
        }
        else{
    %>

    <body>
        <header>
        <h1>Página Administrador</h1>
        </header>

        <aside>
            <a href="modificarDatosVista.jsp">
            <button> Modificar datos </button>
            </a>

            <a href="../controladores/desconexionControlador.jsp">
            <button> Desconectar </button>
            </a>
        </aside>
        
    </body>

    <% }
    }
    else{
        mensaje="Necesita iniciar sesion para tener acceso";
        paginaSiguiente="index.jsp";
        %>
        <jsp:forward page="<%=paginaSiguiente%>">
        <jsp:param value="<%=mensaje%>" name="mensaje"/>
        </jsp:forward>
        <%
    }%>
</html>