package src.Despliegue.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import src.Datos.DAO.AsistenteDAO;
import src.Datos.DAO.UsuarioDAO;
import src.Despliegue.customerBean;
import src.Negocio.DTO.AsistenteDTO;
import src.Negocio.DTO.UsuarioDTO;
import src.Negocio.DTO.Enum.TipoUsuario;


public class servletUsuario extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreCompleto = req.getParameter("nombre");
        LocalDate fechaNacimiento = LocalDate.parse(req.getParameter("fecha"));
        String contrasena = req.getParameter("contrasena");
        customerBean bean = (customerBean) req.getSession().getAttribute("customerBean");

        String correo = bean.getCorreo(); 
        TipoUsuario tipo = bean.getTipo();


        Properties sql=new Properties();
        Properties config=new Properties();
        sql.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("sql")));
        config.load(getServletContext().getResourceAsStream(getServletContext().getInitParameter("config")));

        UsuarioDAO usuarioDAO = new UsuarioDAO(sql, config);
        AsistenteDAO asistenteDAO = new AsistenteDAO(sql, config);

        usuarioDAO.modificar(new UsuarioDTO(nombreCompleto, correo, contrasena, tipo));
        AsistenteDTO asistente = asistenteDAO.buscarNombre(nombreCompleto);
        asistente.setFechaNacimiento(fechaNacimiento);
        asistenteDAO.modificar(asistente);
    }
}
