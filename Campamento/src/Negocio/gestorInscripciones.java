package Negocio;

import java.time.LocalDate;
import java.util.ArrayList;

import Datos.DAO.AsistenteDAO;
import Datos.DAO.CampamentoDAO;
import Datos.DAO.InscripcionDAO;
import Negocio.DTO.Asistente;
import Negocio.DTO.Campamento;
import Negocio.DTO.InscripcionCompleta;
import Negocio.DTO.InscripcionParcial;
import Negocio.DTO.NivelEducativo;
import Negocio.DTO.Registro;
import Negocio.DTO.RegistroTardio;
import Negocio.DTO.RegistroTemprano;


public class gestorInscripciones
{
    // Clases Factoria
    private Registro reg;

    // Acceso a base de datos
    private InscripcionDAO Inscripcion_DAO; 
    private CampamentoDAO Campamento_DAO;
    private AsistenteDAO Asistente_DAO; 
    
    // Constructor

    public gestorInscripciones()
    {
        Campamento_DAO = new CampamentoDAO();
        Asistente_DAO = new AsistenteDAO();
        Inscripcion_DAO = new InscripcionDAO();
    } 

    // Añadir Inscripciones

    public boolean inscribirParcial(int id_as, int id_camp, LocalDate fecha)
    {
        // Campamento CampamentoDAO::getCampamento(int id); Busca un campamento por id
        Campamento camp = Campamento_DAO.buscarCampamento(id_camp); 
        //Comprobar que exista el campamento
        if (camp == null)
        {
            return false;
        }

        int dias = (int) (camp.getInicio().getDayOfMonth() - fecha.getDayOfMonth());
        if (dias>=15)
        {
            reg = new RegistroTemprano();
        }
        else if (dias>2)
        {
            reg = new RegistroTardio();
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            return false;
        }

        // int CampamentoDAO::numeroActividades(int id); Devuelve el numero de actividades asignadas al campamento con ese id
        // boolean AsistenteDAO::getAtencionEspecial(int id); Devuelve si el asistente necesita atencion especial
        InscripcionParcial ins = reg.createInscripcionP(id_as, id_camp, fecha, 100 + Campamento_DAO.numeroActividades(id_camp)*20);
        
        if (Inscripcion_DAO.existeCompleta(id_as, id_camp))
        {
            return false;
        }
        else if (Inscripcion_DAO.existeParcial(id_as, id_camp))
        {
            return true;
        }
        else
        {
            // void InscripcionDAO::agregarParcial(InscripcionParcial inscripcion);
            Inscripcion_DAO.agregarParcial(ins);
            return true;
        }
    }

    public boolean inscribirCompleta(int id_as, int id_camp, LocalDate fecha)
    {
        Campamento camp = Campamento_DAO.buscarCampamento(id_camp); 
        //Comprobar que exista el campamento
        if (camp == null)
        {
            return false;
        }

        int dias = (int) (camp.getInicio().getDayOfMonth() - fecha.getDayOfMonth());
        if (dias>=15)
        {
            reg = new RegistroTemprano();
        }
        else if (dias>2)
        {
            reg = new RegistroTardio();
        }
        else // Menos de 48h del inicio no se puede registrar
        {
            return false;
        }


        InscripcionCompleta ins = reg.createInscripcionC(id_as, id_camp, fecha, 100 + Campamento_DAO.numeroActividades(id_camp)*20);
        
        if (Inscripcion_DAO.existeParcial(id_as, id_camp))
        {
            return false;
        }
        else if (Inscripcion_DAO.existeCompleta(id_as, id_camp))
        {
            return true;
        }
        else
        {
            // void InscripcionDAO::agregarCompleta(InscripcionCompleta inscripcion);
            Inscripcion_DAO.agregarCompleta(ins);
            return true;
        }
    }

    // Campamentos disponibles

    public ArrayList<Campamento> campamentos(LocalDate fecha)
    {
        ArrayList<Campamento> camps = new ArrayList<Campamento>();

        // ArrayList<Campamento> CampamentoDAO::getCampamentos(LocalDate fecha); Busca todos los campamentos cuya fecha de inicio sea mayor que fecha + 2 dias
        camps = Campamento_DAO.buscarCampamentosPorFecha(fecha);
        
        for (int i = 0; i < camps.size(); i++)
        {
            // int CampamentoDAO::numeroAsistentes(int idCampamento); Devuelve la cantidad de asistentes al capamento
            if (camps.get(i).getMax() > Inscripcion_DAO.numeroAsistentes(camps.get(i).getId()));
            {
                camps.remove(i);
            }   
        }
    
        return camps;
    }

}