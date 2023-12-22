<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page errorPage="../comun/error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pagina asistente</title>
    </head>
    <body>
        <header>
        <h1>Pagina Asistente</h1>
        </header>

        <fieldset>
        <legend>Información Inscripcion:</legend>
            <form method="POST" action="/campamentos/inscribirCampamento">
                
                <label for="idCamp">Campamento: </label>
                <input type="text" name="idCamp"><br>

                <label for="tipoInscripcion">Inscripcion: </label>
                <select name="tipoInscripcion">
                    <option value="Parcial">Parcial</option>
                    <option value="Completa">Completa</option>
                </select><br>

                <input type="submit" value="Inscribirse">
            </form>
        </fieldset>

        
        <form method="POST" action="/campamentos/cancelarInscripcionCampamento">
            <label for="idCamp">Campamento: </label>
            <input type="text" name="idCamp"><br>
            <input type="submit" value="Cancelar Inscripcion">
        </form>
        


        
    </body>
        
    </body>
</html>