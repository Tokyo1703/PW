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

@WebServlet(name = "servletConfirmarInscripcion", urlPatterns = ("/campamentos/confirmarInscripcionCampamento"))
public class servletConfirmarInscripcion extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            
            customerBean bean = (customerBean) req.getSession().getAttribute("customerBean");
            Integer idAsistente = asistenteDAO.buscarNombre(bean.getNombre()).getId();

            TipoInscripcion tipo;
            if (tipo_str == "Parcial")
            {
                tipo = TipoInscripcion.Parcial;
            }
            else
            {
                tipo = TipoInscripcion.Completa;
            }
            

            if (Inscripciones.realizarInscripcion(idAsistente, idCampamento, tipo))
            {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.sendRedirect("/mvc/vistas/asistenteVista.jsp");
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
