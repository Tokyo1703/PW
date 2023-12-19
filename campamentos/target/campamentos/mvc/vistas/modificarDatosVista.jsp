<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina de modificacion de datos</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
    %>

    <body>
        <header>
        <h1>Modificacion de datos</h1>
        </header>
        <ul>
            <li>Tipo de usuario: <%= customerBean.getTipo().name()></li>
            <li>Correo: <%= customerBean.getCorreo().name()></li>
            <form method="post" action="../controladores/registroControlador.jsp">
                <li>
                <label for="nombreCompleto">Nombre y apellidos: </label>
                <input type="text" name="nombreCompleto" value=<%=customerBean.getNombre() %> ><br/>
                </li>

                <li>
                <label for="fechaNacimiento">Asistente: Fecha de nacimiento: (yyyy/mm/dd) (Asistente)</label>
                <input type="text" name="fechaNacimiento" value=<%=>><br/>
                </li>

                <li>
                <label for="contrasena">Contraseña: </label>
                <input type="password" name="contrasena"><br/>
                </li>

                <br/>
                <input type="submit" value="Actualizar informacion">
            </form>
        </ul>

        
    </body>

    <%
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