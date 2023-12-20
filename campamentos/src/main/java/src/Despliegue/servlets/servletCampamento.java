package src.Despliegue.servlets;

import javax.servlet.http.*;

import src.Despliegue.customerBean;
import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.Enum.TipoUsuario;

import javax.servlet.annotation.WebServlet;

import java.io.IOException;

import java.io.FileNotFoundException;
import java.util.Properties;

@WebServlet(name = "ActivitiesServlet", urlPatterns = "/activities")

public class servletCampamento extends HttpServlet{

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException {
        HttpSession session = req.getSession();

        customerBean customerBean = (customerBean) session.getAttribute("customerBean");
        if (customerBean == null || customerBean.getTipo() == TipoUsuario.Asistente) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado, Solo administradores");
            return;
        }

        if (req.getParameter("id_") == null ||
                req.getParameter("nombre_") == null) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: Faltan parametros");
            return;
        }

        int Id = Integer.parseInt(req.getParameter("id_"));
        String Nombre = req.getParameter("nombre_");
    
        try {
            Properties sqlProperties = new Properties();
            Properties configProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            gestorCampamentos Gestor = new gestorCampamentos(sqlProperties, configProperties);
            Gestor.AsociarActividadcampamento(Id, Nombre);

            res.setStatus(HttpServletResponse.SC_CREATED);
            res.sendRedirect("/mvc/vistas/vinculacionActividadVista.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }
    }
}
