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
		String mensajeNextPage = request.getParameter("mensaje");
		if (mensajeNextPage == null){
			mensajeNextPage = "";
		}

		if (customerBean != null && !customerBean.getCorreo().equals("")) {
			//No debería estar aquí -> flujo salta a index.jsp
			nextPage = "../../index.jsp";
		} 
		else{
		%>
		<%= mensajeNextPage %><br/><br/>
		<form method="post" action="../controladores/loginControlador.jsp">
			<label for="correo">Correo: </label>
			<input type="email" name="correo"><br/>
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