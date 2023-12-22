package src.Despliegue.servlets;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.Datos.DAO.AsistenteDAO;
import src.Despliegue.customerBean;
import src.Negocio.gestorInscripciones;
import src.Negocio.DTO.Enum.TipoInscripcion;
import src.Negocio.DTO.Enum.TipoUsuario;

@WebServlet(name = "servletConfirmarInscripcion", urlPatterns = ("/confirmarInscripcionCampamento"))
public class servletConfirmarInscripcion extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        customerBean customerBean = (customerBean) req.getSession().getAttribute("customerBean");
        if(customerBean == null || customerBean.getTipo() == TipoUsuario.Administrador){

            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado, solo usuarios Registrados");
            return;
        }

        if(req.getParameter("idCamp") == null || req.getParameter("tipoInscripcion") == null){
            
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: Faltan parametros");
            return;
        }

        Integer idCampamento = Integer.parseInt(req.getParameter("idCamp"));
        String tipo_str = req.getParameter("tipoInscripcion");
        try{
            Properties sql=new Properties();
            Properties config=new Properties();
            sql.load(getServletContext().getResourceAsStream("/WEB-INF/sql.properties"));
            config.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));
            AsistenteDAO asistenteDAO = new AsistenteDAO(sql, config);
            gestorInscripciones Inscripciones = new gestorInscripciones(sql, config);
            
            Integer idAsistente = asistenteDAO.buscarNombre(customerBean.getNombre()).getId();

            TipoInscripcion tipo = TipoInscripcion.valueOf(tipo_str);

            if (Inscripciones.realizarInscripcion(idAsistente, idCampamento, tipo))
            {
                req.getRequestDispatcher("/mvc/vistas/asistente/asistenteVista.jsp").forward(req, resp);
            }
            else
            {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, Inscripciones.mensajeError());
                return;
            }
            
        }catch(Exception e){

            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }  
    }
}
