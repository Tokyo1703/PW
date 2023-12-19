<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Desconectar usuario</title>
</head>
<body>
    <%
        request.getSession().removeAttribute("customerBean");
    %>
    <jsp:forward page="../../index.jsp">
    <jsp:param value="Desconectado con exito" name="mensaje"/>
    </jsp:forward>
</body>
</html>