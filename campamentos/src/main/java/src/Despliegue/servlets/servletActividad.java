package src.Despliegue.servlets;
import java.io.IOException;

import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.Enum.Horario;
import src.Negocio.DTO.Enum.NivelEducativo;
import src.Negocio.DTO.ActividadDTO;

public class servletActividad extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){


        String Nombre = request.getParameter("nombre");
        NivelEducativo Nivel = NivelEducativo.valueOf(request.getParameter("nivelEducativo"));
        Horario Hora = Horario.valueOf(request.getParameter("horario"));
        int Capacidad = Integer.valueOf(request.getParameter("numMaxAsistentes"));
        int MonitoresMax = Integer.valueOf(request.getParameter("numeroMonitores"));

        ActividadDTO actividad = new ActividadDTO(Nombre,Nivel,Hora,Capacidad,MonitoresMax);

        Properties sql=new Properties();
        Properties config=new Properties();

        try{
            sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
            config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));
            gestorCampamentos gestor = new gestorCampamentos(sql, config);
            gestor.InsertarActividad(actividad);

            RequestDispatcher disp = request.getRequestDispatcher("/mvc/vistas/administradorVista.jsp");
            disp.forward(request, response);
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    


    
}
