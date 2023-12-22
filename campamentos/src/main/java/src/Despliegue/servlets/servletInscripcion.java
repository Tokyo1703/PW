package src.Despliegue.servlets;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import src.Negocio.gestorInscripciones;
import src.Negocio.DTO.Enum.Registro;
import src.Negocio.DTO.Enum.TipoInscripcion;

@WebServlet(name = "servletInscripcion", urlPatterns = ("/campamentos/inscribirCampamento"))
public class servletInscripcion extends HttpServlet{

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
            gestorInscripciones Inscripciones = new gestorInscripciones(sql, config);

            TipoInscripcion tipo = null;
            if (tipo_str == "Parcial")
            {
                tipo = TipoInscripcion.Parcial;
            }
            else
            {
                tipo = TipoInscripcion.Completa;
            }
            

            float precio = Inscripciones.precioInscripcion(idCampamento, tipo);
            Registro registro = Inscripciones.tipoRegistro(idCampamento);

            req.setAttribute("precio", precio);
            req.setAttribute("registro", registro.name());
            req.getRequestDispatcher("confirmarInscripcionControlador.jsp").forward(req, resp);
        }catch(Exception e){

            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request: " + e.getMessage());
        }
    }
    
}
