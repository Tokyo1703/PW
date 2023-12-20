package src.Despliegue.servlets;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

import src.Negocio.gestorCampamentos;

public class servletsCampamento extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        
        int Id = Integer.valueOf(request.getParameter("id"));                              

        String Nombre = request.getParameter("nombre");

        Properties sql=new Properties();
        Properties config=new Properties();

        try{
            sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
            config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));
        }catch(IOException e){
            System.out.println(e);
        }

        gestorCampamentos gestor = new gestorCampamentos(sql, config);

        gestor.AsociarActividadcampamento(Id, Nombre);
    }

}