<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="customerBean" scope="session" class="src.Despliegue.customerBean"></jsp:useBean>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="src.Negocio.DTO.CampamentoDTO" %>
<%@ page import="src.Negocio.DTO.Enum.TipoUsuario" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página asistente</title>
</head>
<body>

    <% 
        String nextPage;
        String nombreAsistente;

        if (customerBean == null || customerBean.getCorreo().equals("")) {
            // No debería estar aquí -> flujo salta a index.jsp
            nextPage = "../../index.jsp";
            response.sendRedirect(nextPage);
        } else if(customerBean.getTipo() != TipoUsuario.Asistente){
            // No debería estar aquí -> redirigido a la vista de los administradores
            nextPage = "./administradorVista.jsp";
            response.sendRedirect(nextPage);
        } else {
            // customerBean está logueado y es asistente -> se muestra el saludo,
            // la fecha, los campamentos y las opciones
    %>  <h1>Página asistente</h1>
        
        <%
            // Obtener el nombre del usuario desde el bean (ajusta según tu bean)
            String nombreUsuario = customerBean.getNombre(); 

            // Obtener la fecha actual
            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
            String fechaFormateada = formatoFecha.format(fechaActual);
        %>
            <p><strong>Hola, <%=nombreUsuario%></strong></p>
            <p>Fecha actual: <%=fechaFormateada%></p>

            <h2>Campamentos Inscritos:</h2>
        <%
            ArrayList<CampamentoDTO> campamentos = (ArrayList<CampamentoDTO>) request.getAttribute("campamentos");
        
            //CAMPAMENTOS NULOS
            if( campamentos == null ||campamentos.size() == 0 ){
              %>
                No estas inscrito a ningun campamento
              <%
            } else {
               %>
                <ul>
                <%
                for (CampamentoDTO camp: campamentos){
                    int id = camp.getId();
                    String fecha = camp.getInicio().toString();
            
                    %>
                    <li>Campamento: <%=id%><br>
                    <li>Fecha de inicio: <%=fecha%></li>
                    <%  
                } 
                %>
                </ul>
            <%
            }
            %>

        <h2>Opciones disponibles:</h2>
        <ol>
            <li><a href="consultarCampamentosVista.jsp"> <button>Consultar campamentos disponibles</button> </a></li>
            <li><a href="buscarCampamentoVista.jsp"> <button>Buscar campamentos</button> </a></li>
            <li><a href="inscripcionVista.jsp"> <button>Realizar un registro a un campamento</button></a></li>
            <li><a href="cancelarRegistro.jsp"> <button>Cancelar el registro a campamentos</button></a></li>
        </ol>
    <% } %>
</body>
</html>
