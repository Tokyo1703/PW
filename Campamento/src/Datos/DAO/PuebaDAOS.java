package Datos.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import Negocio.DTO.ActividadDTO;
import Negocio.DTO.AsistenteDTO;
import Negocio.DTO.CampamentoDTO;
import Negocio.DTO.InscripcionDTO;
import Negocio.DTO.MonitorDTO;
import Negocio.DTO.Enum.Horario;
import Negocio.DTO.Enum.NivelEducativo;
import Negocio.DTO.Enum.Registro;
import Negocio.DTO.Enum.TipoInscripcion;

public class PuebaDAOS {
    public static void main(String[] args){
        LocalDate fecha=LocalDate.now();
        AsistenteDTO asistentePrueba=new AsistenteDTO(16,"Ivan Romero Garcia",fecha,true);
        MonitorDTO monitorPrueba=new MonitorDTO(22,"Pepe Garcia Cruz",false);
        ActividadDTO actividadPrueba=new ActividadDTO("Escalada", NivelEducativo.Juvenil,Horario.valueOf("Manana"), 10, 3);
        AsistenteDAO asistenteDAO=new AsistenteDAO();
        //asistenteDAO.AgregarAsistente(asistentePrueba);
        /*if(asistenteDAO.existeID(11)){
            System.out.println("Si existe");
        }
        //asistenteDAO.modificar(asistentePrueba);
        ArrayList<Asistente> lista=asistenteDAO.listaAsistentes();
        for(Asistente it:lista){
            System.out.println(it.toString());
        }*/
        MonitorDAO monitorDAO=new MonitorDAO();
        //monitorDAO.AgregarMonitor(monitorPrueba);
        ActividadDAO actividadDAO = new ActividadDAO();
        //actividadDAO.AgregarActividad(actividadPrueba);
        //monitorDAO.asociarMonitorActividad(monitorPrueba, actividadPrueba);
        //Campamento campamentoPrueba= new Campamento(11,LocalDate.parse("2024-01-09"),LocalDate.parse("2024-01-13"),NivelEducativo.Juvenil,100);
        CampamentoDAO campamentoDAO=new CampamentoDAO();
        //campamentoDAO.AgregarCampamento(campamentoPrueba);
        /*if(actividadDAO.existeActividad("Escalada")){
            Actividad actividadPrueba=actividadDAO.buscarActividad("Escalada");
            System.out.println(actividadPrueba.toString());
        }
        
        if(campamentoDAO.existeID(21)){
            Campamento campamentoPrueba=campamentoDAO.buscarCampamento(21);
            System.out.println(campamentoPrueba.toString());
        }
        campamentoDAO.asociarMonitorResponsable(11, 21);
        */
        
        ArrayList<CampamentoDTO> lista2= campamentoDAO.buscarCampamentosPorFecha(fecha.plusDays(2));
        for(CampamentoDTO it: lista2){
            System.out.println(it.toString());
        }
        
        //System.out.println(monitorDAO.cantidadActividadesMonitor(23));
        InscripcionDAO inscripcionDAO = new InscripcionDAO();
        
        //Inscripcion inscripcionPrueba=new Inscripcion(5, 21, fecha, 300, Registro.Temprano, TipoInscripcion.Completa);
        //inscripcionDAO.agregarCompleta(inscripcionPrueba);
    }   
}
