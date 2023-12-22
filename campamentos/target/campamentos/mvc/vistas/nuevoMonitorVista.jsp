<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nuevo Monitor</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
    %>

    <body>
        <header>
        <h1>Nuevo Monitor</h1>
        </header>

        <fieldset>
        <legend>Información monitor:</legend>
            <form method="POST" action="/campamentos/servletMonitor">

                <label for="nombreCompleto">Nombre y apellidos: </label>
                <input type="text" name="nombreCompleto" ><br/>

                <label for="id">Id:</label>
                <input type="text" name="id" ><br/>

                <label for="atencionEspecial">Atencion especial(Si/No): </label>
                <input type="text" name="atencionEspecial"><br/>

                <br/>
                <input type="submit" value="Añadir monitor">
            </form>
        </fieldset>


        
    </body>

    <%
    }
    else{
        mensaje="Necesita iniciar sesion para tener acceso";
        paginaSiguiente="../../index.jsp";
        %>
        <jsp:forward page="<%=paginaSiguiente%>">
        <jsp:param value="<%=mensaje%>" name="mensaje"/>
        </jsp:forward>
        <%
    }%>
</html>