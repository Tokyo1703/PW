<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--<%@ page errorPage="../comun/error.jsp"%>-->
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Asociación de Actividad a Campamento</title>
</head>
<body>

    <h2>Asociar Actividad a Campamento</h2>

    <form action="/campamentos" method="post">
        <label for="Id">ID del Campamento:</label>
        <input type="number" id="Id" name="Id" required><br>

        <label for="nombre">Nombre de la Actividad:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <input type="submit" value="Asociar Actividad a Campamento">
    </form>

</body>
</html>