package src.Despliegue.servlets;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.Enum.Horario;
import src.Negocio.DTO.Enum.NivelEducativo;
import src.Negocio.DTO.ActividadDTO;

public class servletActividad extends HttpServlet {

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
            
            if(gestor.InsertarActividad(actividad)==false){
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("¡Ya existe esta actividad!");
                RequestDispatcher disp = request.getRequestDispatcher("/mvc/vistas/administrador/nuevaActividadVista.jsp");
                disp.include(request, response);
            }
            else{
                RequestDispatcher disp = request.getRequestDispatcher("/mvc/controladores/administrador/administradorControlador.jsp");
                disp.forward(request, response);
            }

        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    


    
}
