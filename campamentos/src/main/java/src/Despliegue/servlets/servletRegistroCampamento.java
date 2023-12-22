package src.Despliegue.servlets;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import src.Datos.DAO.AsistenteDAO;
import src.Despliegue.customerBean;

public class servletRegistroCampamento extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties sql=new Properties();
        Properties config=new Properties();
        sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
        config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));

        AsistenteDAO asistenteDAO = new AsistenteDAO(sql, config);
        customerBean bean = (customerBean) req.getSession().getAttribute("customerBean");

        Integer idAsistente = asistenteDAO.buscarNombre(bean.getNombre()).getId();
        Integer idCampamento = Integer.parseInt(req.getParameter("idCamp"));


        
    }
    
}
