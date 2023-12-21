<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Asociación de Monitor a Campamento</title>
</head>
<body>

    <h2>Asociar Monitor a Campamento</h2>

    <form action="/campamentos/asociarMonitor" method="post">
        <label for="IdM">ID del Monitor:</label>
        <input type="number" id="IdM" name="IdM" required><br>

        <label for="IdC">ID del Campamento:</label>
        <input type="number" id="IdC" name="IdC" required><br>

        <label for="atencionEspecial">Atención Especial:</label>
        <select id="atencionEspecial" name="atencionEspecial">
            <option value="Si">Sí</option>
            <option value="No">No</option>
        </select><br>

        <input type="submit" value="Asociar Monitor a Campamento">
    </form>

</body>
</html>