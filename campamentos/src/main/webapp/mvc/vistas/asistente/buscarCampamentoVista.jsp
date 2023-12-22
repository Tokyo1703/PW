<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="src.Negocio.DTO.CampamentoDTO" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar Campamentos</title>
</head>
<body>
    <h2>Buscar Campamentos</h2>
    <form action="../../../Despliegue/servlets/servletBuscarCampamentos" method="post">
        <label for="fechaInicio">Fecha de Inicio (yyyy-mm-dd):</label>
        <input type="date" id="fechaInicio" name="fechaInicio" required>
        <br>
        <label for="fechaFin">Fecha de Fin (yyyy-mm-dd):</label>
        <input type="date" id="fechaFin" name="fechaFin" required>
        <br>
        <input type="submit" value="Buscar Campamentos">
    </form>

    <hr>

    <h3>Resultados:</h3>
    <% ArrayList<CampamentoDTO> campamentos = (ArrayList<CampamentoDTO>) request.getAttribute("campamentos");
       
        for(CampamentoDTO camp: campamentos){
          int id = camp.getId();
          String fechaInicio = camp.getInicio().toString();
          String fechaFin =camp.getFinal().toString();
          String nivel =  camp.getNivel();

          %>
            <p>
                <ul>
                    <li> Id: <%= id %> </li>
                    <li> Fecha de inicio: <%=fechaInicio%></li>
                    <li> Fecha de finalizacion: <%=fechaFin%></li>
                    <li> Nivel educativo: <%= nivel%></li>
                </ul>
            </p>
        <%
        }
    %>
</body>
</html>
