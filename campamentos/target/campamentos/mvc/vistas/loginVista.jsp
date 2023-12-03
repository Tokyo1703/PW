<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Log in</title>
	</head>
	<body>
		Inicio de sesión:
		<%
		/* Posibles flujos:
			1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
			2) customerBean no está logado:
				a) Hay parámetros en el request  -> procede del controlador /con mensaje 
				b) No hay parámetros en el request -> procede del controlador /sin mensaje
			*/
		String nextPage = "../controladores/loginControlador.jsp";
		String messageNextPage = request.getParameter("message");
		if (messageNextPage == null){
			messageNextPage = "";
		}

		if (customerBean != null && !customerBean.getCorreo().equals("")) {
			//No debería estar aquí -> flujo salta a index.jsp
			nextPage = "../../index.jsp";
		} 
		else{
		%>
		<%= messageNextPage %><br/><br/>
		<form method="post" action="../controladores/loginControlador.jsp">
			<label for="correo">Correo: </label>
			<input type="email" name="name"><br/>
			<label for="contrasena">Contraseña: </label>
			<input type="password" name="contrasena">	
			<br/>
			<input type="submit" value="Enviar">
		</form>
		<%
		}
		%>

	</body>
</html>