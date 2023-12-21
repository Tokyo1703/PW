<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Asociación de Monitor a Actividad</title>
</head>
<body>

    <h2>Asociar Monitor a Actividad</h2>

    <form action="/campamentos/asociarMonitor_Actividad" method="post">
        <label for="Id">ID del Monitor:</label>
        <input type="number" id="Id" name="Id" required><br>

        <label for="nombre">Nombre de la Actividad:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <input type="submit" value="Asociar Monitor">
    </form>

</body>
</html>