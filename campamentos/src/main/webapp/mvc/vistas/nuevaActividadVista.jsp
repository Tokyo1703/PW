<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nueva Actividad</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
    %>

    <body>
        <header>
        <h1>Nueva actividad</h1>
        </header>


        <form method="POST" action="/campamentos/servletActividad">

            <label for="nombre">Nombre: </label>
            <input type="text" name="nombre" ><br/>

            <label for="nivelEducativo">Nivel Educativo(Infantil/Juvenil/Adolescente):</label>
            <input type="text" name="nivelEducativo" ><br/>

            <label for="horario">Horario(Manana/Tarde): </label>
            <input type="text" name="horario"><br/>
            
            <label for="numMaxAsistentes">Número máximo de asistentes: </label>
            <input type="text" name="numMaxAsistentes"><br/>
                        
            <label for="numeroMonitores">Numero de monitores necesarios: </label>
            <input type="text" name="numeroMonitores"><br/>

            <br/>
            <input type="submit" value="Añadir actividad">
        </form>


        
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