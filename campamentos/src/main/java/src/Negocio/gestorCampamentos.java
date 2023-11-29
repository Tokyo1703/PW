package Negocio;

import Datos.DAO.ActividadDAO;
import Datos.DAO.CampamentoDAO;
import Datos.DAO.MonitorDAO;
import Negocio.DTO.ActividadDTO;
import Negocio.DTO.CampamentoDTO;
import Negocio.DTO.MonitorDTO;

/**
 * Clase gestor campamentos, encargada de comprobar y gestionar los datos de los campamentos, las actividades y 
 * los monitores.
 */
public class gestorCampamentos {

    /**
     * Variable CampamentoDAO, representa una instancia de la clase CampamentoDAO usada para la conexion con la base de datos.
     * Variable ActividadDAO, representa una instancia de la clase ActividadDAO usada para la conexion con la base de datos.
     * Variable MonitorDAO, representa una instancia de la clase MonitorDAO usada para la conexion con la base de datos.
     */
    
    private CampamentoDAO Campamento_DAO=new CampamentoDAO();
    private ActividadDAO Actividad_DAO=new ActividadDAO();
    private MonitorDAO Monitor_DAO=new MonitorDAO();
    
    /**
     * Metodo encargado de Insertar un campamento si no existe con anterioridad
     * @param campamento Campamento que se quiere insertar
     * @return  true si se inserto correctamente, false si no
     */

    public boolean InsertarCampamento(CampamentoDTO campamento){
        if(!Campamento_DAO.existeID(campamento.getId())){
            Campamento_DAO.AgregarCampamento(campamento);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de Insertar un monitor si no existe con anterioridad
     * @param monitor Monitor que se quiere insertar 
     * @return true si se inserto correctamente, false si no 
     */

    public boolean InsertarMonitor(MonitorDTO monitor){
        if(!Monitor_DAO.existeID(monitor.getId())){
            Monitor_DAO.AgregarMonitor(monitor);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de Insertar una actividad si no existe previamente
     * @param actividad Actividad que se quiere insertar 
     * @return true si se inserto correctamente, false si no
     */

    public boolean InsertarActividad(ActividadDTO actividad){
        if(!Actividad_DAO.existeActividad(actividad.GetNombre())){
            Actividad_DAO.AgregarActividad(actividad);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo encargado de Vincular un monitor con una actividad, comprovando que existan y que no este llena
     * @param ID_Monitor Id del monito que se quiere asociar 
     * @param Nombre_Actividad  Nombre de la actividad a la que se asociara
     * @return 0 si exito, 1 si no existe monitor 2 si no existe actividad, 3 si ya existe ese monitor en la actividad 
     * 4 si la actividad tiene el maxino Nº de monitores
     */
    public int AsociarMonitorActividad(int ID_Monitor, String Nombre_Actividad){

        MonitorDTO monitor = new MonitorDTO();
        ActividadDTO actividad = new ActividadDTO();

        if(Monitor_DAO.existeID(ID_Monitor)){

            monitor = Monitor_DAO.buscarMonitor(ID_Monitor);    
        }else{

            return 1;
        }

        if(Actividad_DAO.existeActividad(Nombre_Actividad)){

            actividad = Actividad_DAO.buscarActividad(Nombre_Actividad);
        }else{

            return 2;
        }

        if(Monitor_DAO.existeMonitorEnActividad(monitor,actividad)){
            return 3;
        }

        if(actividad.GetMonitoresMax()<=Actividad_DAO.cantidadMonitoresActividad(actividad.GetNombre())){
            return 4;
        }

        Monitor_DAO.asociarMonitorActividad(monitor,actividad);
        return 0;
    }

    /**
     * Metodo que se encarga de vincular una Actividad a un Campamento si existen los 2 
     * @param ID_Campamento Id del campamento al que se vincula la actividad
     * @param Nombre_Actividad Nombre de la actividad a vincular
     * @return 0 Existo, 1 si no existe el campamento, 2 si no existe la actividad
     */
    public int AsociarActividadcampamento(int ID_Campamento, String Nombre_Actividad){

        CampamentoDTO campamento = new CampamentoDTO();
        ActividadDTO actividad = new ActividadDTO();

        if(Campamento_DAO.existeID(ID_Campamento)){

            campamento = Campamento_DAO.buscarCampamento(ID_Campamento);
        }else{

            return 1;
        }

        if(Actividad_DAO.existeActividad(Nombre_Actividad)){

            actividad = Actividad_DAO.buscarActividad(Nombre_Actividad);
        }else{

            return 2;
        }

        if(Actividad_DAO.existeActividadEnCampamento(actividad,campamento)){
            return 3;
        }

        if(actividad.GetNivel()!=campamento.getNivel()){
            return 4;
        }

        Actividad_DAO.asociarCampamentoActividad(campamento,actividad);
        return 0;
    }

    /**
     * Metodo que Vincula un monitor especial al campamento si se cumplen los requisitos
     * @param IdMonitor Id del monitor que se quiere asociar 
     * @param IdCampamento Id del campamento al que se le asocia
     * @return 0 si Existo, 1 si no existe el monitor 2 si el monitor no cumple los requisitos 3 si no existe el campamento
     */
    public int AsociarMonitorESPCampamento(int IdMonitor, int IdCampamento ){

        if(!Monitor_DAO.existeID(IdMonitor)){

            return 1;
        }else{

            MonitorDTO especial = new MonitorDTO();
            especial = Monitor_DAO.buscarMonitor(IdMonitor);

            if(!especial.getAtencionEsp()){

                return 2;
            }else if(Monitor_DAO.cantidadActividadesMonitor(IdMonitor) != 0){
                
                return 2;
            }
        }

        if(!Campamento_DAO.existeID(IdCampamento)){

            return 3;
        }
        
        Campamento_DAO.asociarMonitorEsp(IdMonitor,IdCampamento);

        return 0;
    }

    /**
     * Metodo encargado de vincular el monitor responsable del campamento.
     * @param IdMonitor Id del monito que se quiere vincular
     * @param IdCampamento Id del campamento al que se le vincula
     * @return 0 si Exito, 1 si no existe el monitor, 2 si no cumple los requisitos, 3 si no existe el campamento
     */
    
    public int AsociarMonitorResponsable(int IdMonitor, int IdCampamento){
        
        if(!Monitor_DAO.existeID(IdMonitor)){

            return 1;
        }else{

            if(Monitor_DAO.cantidadActividadesMonitor(IdMonitor) == 0){

                return 2;
            }
        }

        if(!Campamento_DAO.existeID(IdCampamento)){

            return 3;
        }
        
        Campamento_DAO.asociarMonitorResponsable(IdMonitor, IdCampamento);

        return 0;
    }
}

    