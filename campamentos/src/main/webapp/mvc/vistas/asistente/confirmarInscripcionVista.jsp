<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../comun/error.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>    
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
		<title>Confirmar Inscripcion</title>
    </head>
    <% 
	String mensajeNextPage = request.getParameter("mensaje");
    if(customerBean!=null && !customerBean.getCorreo().equals("")){
    %>

    <body>
        <header>
        <h1>Inscripcion</h1>
        </header>

        <fieldset>
        <legend>Información Inscripcion:</legend>
            <form method="POST" action="/campamentos/confirmarInscripcionCampamento">
                <%
                    String idCamp = request.getParameter("idCamp");
                    String tipo = request.getParameter("tipoInscripcion");
                    Object precio = request.getAttribute("precio");
                    Object registro = request.getAttribute("registro");
                %>
                <input type="hidden" name="idCamp" value="<%=idCamp%>" >
                <input type="hidden" name="tipoInscripcion" value="<%=tipo%>" >

                <table>
                    <tr>
                        <td>Precio: <%=precio%></td>
                    </tr>
                    <tr>
                        <td>Tipo de Registro: <%=registro%></td>
                    </tr>
                </table>

                <input type="submit" value="Confirmar">
                <a href="/campamentos/mvc/vistas/asistenteVista.jsp">
                    <button>Cancelar</button>
                </a>
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