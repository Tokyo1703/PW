<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="vistas/comun/error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina principal</title>
    </head>
    <%String mensaje = request.getParameter("mensaje");%>
    <body>
        <%if(mensaje!=null){%>
        <%= mensaje %><br/><br/>
        <%
        }%>

        <h1>Página web de campamentos</h1>
        <a href="/campamentos/mvc/controladores/comun/loginControlador.jsp">
        <button> Login </button>
        </a>
        <a href="/campamentos/mvc/controladores/comun/registroControlador.jsp">
        <button> Registro </button>
        </a>
    </body>
</html>