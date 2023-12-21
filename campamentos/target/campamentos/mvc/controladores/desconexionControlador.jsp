<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>

<%
    request.getSession().removeAttribute("customerBean");
%>
<jsp:forward page="../../index.jsp">
<jsp:param value="Desconectado con exito" name="mensaje"/>
</jsp:forward>
