<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<jsp:useBean  id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registro</title>
	</head>
	<body>
		<h1>Registro</h1>
		<%
		/* Posibles flujos:
			1) customerBean está logado (!= null && != "") -> Se redirige al index.jsp (no debería estar aquí pero hay que comprobarlo)
			2) customerBean no está logado:
				a) Hay parámetros en el request  -> procede del controlador /con mensaje 
				b) No hay parámetros en el request -> procede del controlador /sin mensaje
			*/
		String nextPage = "../controladores/registroControlador.jsp";
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
		<fieldset>
        <legend>Campos registro:</legend>
			<form method="post" action="../controladores/registroControlador.jsp">
				<label for="tipo">Tipo de usuario (Asistente/Administrador): </label>
				<input type="text" name="tipo" required><br/>

				<label for="nombreCompleto">Nombre y apellidos: </label>
				<input type="text" name="nombreCompleto" required><br/>

				<label for="fechaNacimiento">Asistente: Fecha de nacimiento: (yyyy/mm/dd) (Asistente)</label>
				<input type="text" name="fechaNacimiento" ><br/>

				<label for="atencionEspecial">Asistente: Seleccione este boton si necesita atencion especial</label>
				<input type="checkbox" name="atencionEspecial" value="Si"><br/>

				<label for="correo">Correo: </label>
				<input type="email" name="correo" required><br/>

				<label for="contrasena">Contraseña: </label>
				<input type="password" name="contrasena" required><br/>

				<br/>
				<input type="submit" value="Enviar">
			</form>
		</fieldset>
		<%
		}
		%>

	</body>
</html>