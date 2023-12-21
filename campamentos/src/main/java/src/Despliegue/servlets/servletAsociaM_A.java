package src.Despliegue.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.Despliegue.customerBean;
import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.Enum.TipoUsuario;

@WebServlet(name = "servletAsociaM_A", urlPatterns = ("/campamentos/asociarMonitor_Actividad"))
public class servletAsociaM_A extends HttpServlet{
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws FileNotFoundException, IOException{

        HttpSession sesion = req.getSession();

        customerBean customerBean = (customerBean) sesion.getAttribute("customerBean");
        if(customerBean == null || customerBean.getTipo() == TipoUsuario.Asistente){

            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado, solo Administradores");
            return;
        }

        if(req.getParameter("nombre") == null || req.getParameter("Id") == null){
            
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: Faltan parametros");
            return;
        }

        Integer IdMonitor = Integer.parseInt(req.getParameter("Id"));
        String nombre = req.getParameter("nombre");

        try{

            Properties sqlProperties = new Properties();
            Properties configProperties = new Properties();
            sqlProperties.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            configProperties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

            gestorCampamentos gestor = new gestorCampamentos(sqlProperties, configProperties);

            gestor.AsociarMonitorActividad(IdMonitor, nombre);

            res.setStatus(HttpServletResponse.SC_OK);
            res.sendRedirect("/mvc/vistas/AsociadoMonitor_ActividadVista.jsp?mensaje=Vinculacion exitosa");
        }catch(Exception e){

            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }
    }
}