package src.Despliegue.servlets;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.MonitorDTO;

public class servletMonitor extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response){

        int Id=Integer.valueOf(request.getParameter("id"));
        String NombreCompleto = request.getParameter("nombreCompleto");
        Boolean AtencionEspecial=false;
        if(request.getParameter("atencionEspecial").equals("Si")){
            AtencionEspecial=true;
        }

        MonitorDTO monitor = new MonitorDTO(Id,NombreCompleto,AtencionEspecial);

        Properties sql=new Properties();
        Properties config=new Properties();

        try{
            sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
            config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));
            gestorCampamentos gestor = new gestorCampamentos(sql, config);

            if(gestor.InsertarMonitor(monitor)==false){
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("¡Ya existe este monitor!");
                RequestDispatcher disp = request.getRequestDispatcher("/mvc/vistas/nuevoMonitorVista.jsp");
                disp.include(request, response);
            }
            else{
                RequestDispatcher disp = request.getRequestDispatcher("/mvc/controladores/administradorControlador.jsp");
                disp.include(request, response);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    
}