<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../comun/error.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inscribirse a Campamento</title>
    </head>

    <% 
    String mensaje="";
    String paginaSiguiente;
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
    %>

    <body>
        <header>
        <h1>Inscripcion</h1>
        </header>

        <fieldset>
        <legend>Información Inscripcion:</legend>
            <form method="POST" action="/campamentos/cancelarInscripcionCampamento">
                <label for="idCamp">Campamento: </label>
                <input type="text" name="idCamp"><br>
                <input type="submit" value="Cancelar Inscripcion">
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