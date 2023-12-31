package src.Despliegue.servlets;


import javax.servlet.*;
import javax.servlet.http.*;

import src.Negocio.gestorCampamentos;
import src.Negocio.DTO.CampamentoDTO;
import src.Negocio.DTO.Enum.NivelEducativo;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Properties;

public class servletCampamento extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.valueOf(request.getParameter("id"));
        LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
        LocalDate fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
        NivelEducativo nivel = NivelEducativo.valueOf(request.getParameter("nivelEducativo"));
        int numMaxAsistentes = Integer.valueOf(request.getParameter("numMaxAsistentes"));

        CampamentoDTO campamento = new CampamentoDTO(id,fechaInicio,fechaFin,nivel,numMaxAsistentes);

        Properties sql=new Properties();
        Properties config=new Properties();

        try{
            sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
            config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));
            gestorCampamentos gestor = new gestorCampamentos(sql, config);

            if(gestor.InsertarCampamento(campamento)==false){
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("¡Ya existe un campamento con este id!");
                RequestDispatcher disp = request.getRequestDispatcher("/mvc/vistas/administrador/nuevoCampamentoVista.jsp");
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
