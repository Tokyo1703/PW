package Negocio;

import Datos.DAO.ActividadDAO;
import Datos.DAO.CampamentoDAO;
import Datos.DAO.MonitorDAO;
import Negocio.DTO.Actividad;
import Negocio.DTO.Campamento;
import Negocio.DTO.Monitor;

public class gestorCampamentos {
    
    private CampamentoDAO Campamento_DAO;
    private ActividadDAO Actividad_DAO;
    private MonitorDAO Monitor_DAO;


    public gestorCampamentos(){
        Campamento_DAO = new CampamentoDAO();
        Actividad_DAO = new ActividadDAO();
        Monitor_DAO = new MonitorDAO();
    }
    
    public boolean InsertarCampamento(Campamento campamento){
        if(!Campamento_DAO.existeID(campamento.getId())){
            Campamento_DAO.AgregarCampamento(campamento);
            return true;
        } else {
            return false;
        }
    }

    public boolean InsertarMonitor(Monitor monitor){
        if(!Monitor_DAO.existeID(monitor.getId())){
            Monitor_DAO.AgregarMonitor(monitor);
            return true;
        } else {
            return false;
        }
    }

    public boolean InsertarActividad(Actividad actividad){
        if(!Actividad_DAO.existeActividad(actividad.GetNombre())){
            Actividad_DAO.AgregarActividad(actividad);
            return true;
        } else {
            return false;
        }
    }

    public void AsociarMonitorActividad(Monitor monitor, Actividad actividad){
        Monitor_DAO.asociarMonitorActividad(monitor,actividad);
    }

    public void AsociarActividadcampamento(Campamento campamento, Actividad actividad){
        Actividad_DAO.asociarCampamentoActividad(campamento,actividad);
    }

    public void AsociarMonitorESPCampamento(int IdMonitor, int IdCampamento ){
        Campamento_DAO.asociarMonitorEsp(IdMonitor,IdCampamento);
    }

    public void AsociarMonitorResponsable(int idMonitor, int idCampamento){
        Campamento_DAO.asociarMonitorResponsable(idMonitor, idCampamento);
    }
}

    