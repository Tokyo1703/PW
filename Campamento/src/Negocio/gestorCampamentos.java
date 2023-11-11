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

    //0 --> Exito 1--> Fallo no esite ese monitor 2--> Fallo no exite esa actividad
    public int AsociarMonitorActividad(int ID_Monitor, String Nombre_Actividad){

        Monitor monitor = new Monitor();
        Actividad actividad = new Actividad();

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

        Monitor_DAO.asociarMonitorActividad(monitor,actividad);
        return 0;
    }

    //0 --> Exito 1--> Fallo no esite ese campamento 2--> Fallo no exite esa actividad
    public int AsociarActividadcampamento(int ID_Campamento, String Nombre_Actividad){

        Campamento campamento = new Campamento();
        Actividad actividad = new Actividad();

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

        Actividad_DAO.asociarCampamentoActividad(campamento,actividad);
        return 0;
    }

    //0 --> Exito 1--> Fallo no esite ese monitor 2--> Fallo no cumple los requisitos 3--> Fallo no exite ese campamento
    public int AsociarMonitorESPCampamento(int IdMonitor, int IdCampamento ){

        if(!Monitor_DAO.existeID(IdMonitor)){

            return 1;
        }else{

            Monitor especial = new Monitor();
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

    //0 --> Exito 1--> Fallo no esite ese monitor 2--> Fallo no cumple los requisitos 3--> Fallo no exite ese campamento
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

    