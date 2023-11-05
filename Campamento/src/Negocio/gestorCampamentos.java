package Negocio;

import java.util.ArrayList;

import Datos.DAO.ActividadDAO;
import Datos.DAO.CampamentoDAO;
import Datos.DAO.MonitorDAO;
import Negocio.DTO.Actividad;
import Negocio.DTO.Campamento;
import Negocio.DTO.Horario;
import Negocio.DTO.Monitor;
import Negocio.DTO.NivelEducativo;

import java.time.LocalDate;

import clases.*;

public class gestorCampamentos {
    
    private CampamentoDAO Campamento_DAO;
    private ActividadDAO Actividad_DAO;
    private MonitorDAO Monitor_DAO;


    public gestorCampamentos(){
        Campamento_DAO = new CampamentoDAO();
        Actividad_DAO = new ActividadDAO();
        Monitor_DAO = new MonitorDAO();
    }
    
    public boolean AddCampamento(Campamento campamento){
        if(!Campamento_DAO.existeID(campamento.getId())){
            Campamento_DAO.AgregarCampamento(campamento);
            return true;
        } else {
            return false;
        }
    }

    public boolean AddMonitor(Monitor monitor){
        if(!Monitor_DAO.existeID(monitor.getId())){
            Monitor_DAO.AgregarMonitor(monitor);
            return true;
        } else {
            return false;
        }
    }

    public boolean AddActividad(Actividad actividad){
        if(!Actividad_DAO.existeActividad(actividad.GetNombre())){
            Actividad_DAO.AgregarActividad(actividad);
            return true;
        } else {
            return false;
        }
    }

    public boolean AsociarMonitorActividad(int id, String Nom ){
        Actividad_DAO.asociarMonitorActividad(id,Nom);
    }

    public boolean AsociarActividadcampamento(String Nom, int id ){
        Campamento_DAO.asociarActividadCampamento(Nom,id);
    }

    public boolean AsociarMonitorESPCampamento(int IdMonitor, int IdCampamento ){
        Campamento_DAO.asociarMonitorESP(IdMonitor,IdCampamento);
    }
}

    