<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../comun/error.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Nuevo Campamento</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
    %>

    <body>
        <header>
        <h1>Nuevo Campamento</h1>
        </header>

        <fieldset>
        <legend>Información campamento:</legend>
            <form method="POST" action="/campamentos/servletCampamento">

                <label for="id">Id: </label>
                <input type="text" name="id" ><br/>

                <label for="fechaInicio">Fecha de inicio (yyyy-mm-dd): </label>
                <input type="text" name="fechaInicio" ><br/>

                <label for="fechaFin">Fecha de fin (yyyy-mm-dd): </label>
                <input type="text" name="fechaFin" ><br/>

                <label for="nivelEducativo">Nivel Educativo (Infantil/Juvenil/Adolescente):</label>
                <input type="text" name="nivelEducativo" ><br/>
                
                <label for="numMaxAsistentes">Número máximo de asistentes: </label>
                <input type="text" name="numMaxAsistentes"><br/>

                <br/>
                <input type="submit" value="Añadir campamento">
            </form>
        </fieldset>


        
    </body>

    <%
    }
    else{
        mensaje="Necesita iniciar sesion para tener acceso";
        paginaSiguiente="../../../index.jsp";
        %>
        <jsp:forward page="<%=paginaSiguiente%>">
        <jsp:param value="<%=mensaje%>" name="mensaje"/>
        </jsp:forward>
        <%
    }%>
</html>