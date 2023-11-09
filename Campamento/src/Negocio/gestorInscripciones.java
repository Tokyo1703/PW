package Negocio;

import java.time.LocalDate;
import java.util.ArrayList;

import Datos.DAO.AsistenteDAO;
import Datos.DAO.CampamentoDAO;
import Datos.DAO.InscripcionDAO;

import Negocio.DTO.Campamento;
import Negocio.DTO.Inscripcion;
import Negocio.DTO.Enum.Registro;
import Negocio.DTO.Enum.TipoInscripcion;


public class gestorInscripciones
{
    // Codigo de error que se pueda producir, obten el mensaje con mensajeError()
    private int error;

    // Acceso a base de datos
    private InscripcionDAO Inscripcion_DAO; 
    private CampamentoDAO Campamento_DAO;
    private AsistenteDAO Asistente_DAO;

    // Constructor

    public gestorInscripciones()
    {
        Campamento_DAO = new CampamentoDAO();
        Inscripcion_DAO = new InscripcionDAO();
        Asistente_DAO = new AsistenteDAO();
    } 

    // Añadir Inscripciones

    public boolean inscribirParcial(int idAsistente, int idCampamento, LocalDate fecha)
    {
        if (!Asistente_DAO.existeID(idAsistente))
        {
            error = 0;
            return false;
        }
        if (!Campamento_DAO.existeID(idCampamento))
        {
            error = 1;
            return false;
        }

        Campamento camp = Campamento_DAO.buscarCampamento(idCampamento); 
        Registro tipoRegistro;
        
        int dias = (int) (camp.getInicio().getDayOfMonth() - fecha.getDayOfMonth());
        if (dias>=15)
        {
            tipoRegistro = Registro.Temprano;
        }
        else if (dias>2)
        {
            tipoRegistro = Registro.Tardio;
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            error = 2;
            return false;
        }

        Inscripcion ins =  new Inscripcion(idAsistente, idCampamento, fecha, 100 + Campamento_DAO.numeroActividades(idCampamento)*20, tipoRegistro, TipoInscripcion.Parcial);
        
        if (Inscripcion_DAO.existeCompleta(idAsistente, idCampamento))
        {
            error = 3;
            return false;
        }
        else if (Inscripcion_DAO.existeParcial(idAsistente, idCampamento))
        {
            error = -1;
            return true;
        }
        else
        {
            Inscripcion_DAO.agregarParcial(ins);
            error = -1;
            return true;
        }
    }

    public boolean inscribirCompleta(int idAsistente, int idCampamento, LocalDate fecha)
    {
        if (!Asistente_DAO.existeID(idAsistente))
        {
            error = 0;
            return false;
        }
        if (!Campamento_DAO.existeID(idCampamento))
        {
            error = 1;
            return false;
        }

        Campamento camp = Campamento_DAO.buscarCampamento(idCampamento); 
        Registro tipoRegistro;

        int dias = (int) (camp.getInicio().getDayOfMonth() - fecha.getDayOfMonth());
        if (dias>=15)
        {
            tipoRegistro = Registro.Temprano;
        }
        else if (dias>2)
        {
            tipoRegistro = Registro.Tardio;
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            error = 2;
            return false;
        }

        Inscripcion ins =  new Inscripcion(idAsistente, idCampamento, fecha, 300 + Campamento_DAO.numeroActividades(idCampamento)*20, tipoRegistro, TipoInscripcion.Completa);
        
        if (Inscripcion_DAO.existeParcial(idAsistente, idCampamento))
        {
            error = 3;
            return false;
        }
        else if (Inscripcion_DAO.existeCompleta(idAsistente, idCampamento))
        {
            error = -1;
            return true;
        }
        else
        {
            Inscripcion_DAO.agregarCompleta(ins);
            error = -1;
            return true;
        }
    }

    // Campamentos disponibles

    public ArrayList<Campamento> campamentosDisponibles(LocalDate fecha)
    {
        ArrayList<Campamento> camps = new ArrayList<Campamento>();

        camps = Campamento_DAO.buscarCampamentosPorFecha(fecha.plusDays(2));
        
        for (int i = 0; i < camps.size(); i++)
        {
            if (camps.get(i).getMax() > Inscripcion_DAO.numeroAsistentes(camps.get(i).getId()));
            {
                camps.remove(i);
            }   
        }
    
        return camps;
    }

    public String mensajeError()
    {
        String mensaje = new String();
        switch(error)
        {
            case -1:
                mensaje = "No Error";
            break;
            
            case 0:
                mensaje = "Error. No Existe el asistente";
            break;

            case 1:
                mensaje = "Error. No Existe el campamento";
            break;

            case 2:
                mensaje = "Error. Se ha cerrado el periodo de inscripcion";
            break;

            case 3:
                mensaje = "Error. El asistente esta inscrito en otra modalidad";
            break;
        }
        return mensaje;
    }

}