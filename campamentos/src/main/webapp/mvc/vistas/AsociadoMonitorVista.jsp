<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado de Asociar Monitor a Campamento</title>
</head>
<body>
    <h2>Resultado de Asociar Monitor a Campamento</h2>

    <%
        String mensaje = request.getParameter("mensaje");

        if (mensaje != null && !mensaje.isEmpty()) {
    %>
            <p><strong><%= mensaje %></strong></p>
    <%
        }
    %>

    <a href="/mvc/vistas/modificarDatosVista.jsp">Volver</a>
</body>
</html>
