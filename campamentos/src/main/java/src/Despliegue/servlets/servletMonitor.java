package src.Despliegue.servlets;
import java.io.IOException;

import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.http.HttpServlet;

import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.MonitorDTO;

public class servletMonitor extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){

        int Id=Integer.valueOf(request.getParameter("id"));
        String NombreCompleto = request.getParameter("nombreCompleto");
        Boolean AtencionEspecial=false;
        if(request.getParameter("nombreCompleto").equals("Si")){
            AtencionEspecial=true;
        }

        MonitorDTO monitor = new MonitorDTO(Id,NombreCompleto,AtencionEspecial);

        Properties sql=new Properties();
        Properties config=new Properties();

        try{
            sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
            config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));
            gestorCampamentos gestor = new gestorCampamentos(sql, config);
            gestor.InsertarMonitor(monitor);

            RequestDispatcher disp = request.getRequestDispatcher("/mvc/vistas/administradorVista.jsp");
            disp.forward(request, response);
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
    


    
}