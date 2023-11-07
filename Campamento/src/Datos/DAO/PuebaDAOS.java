package Datos.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import Negocio.DTO.Actividad;
import Negocio.DTO.Asistente;
import Negocio.DTO.Campamento;
import Negocio.DTO.Horario;
import Negocio.DTO.Monitor;
import Negocio.DTO.NivelEducativo;

public class PuebaDAOS {
    public static void main(String[] args){
        LocalDate fecha=LocalDate.parse("2001-07-19");
        Asistente asistentePrueba=new Asistente(19,"Juan Gutierrez Ribera",fecha,false);
        //Monitor monitorPrueba=new Monitor(11,"Luis Ramirez Benito",false);
        //Actividad actividadPrueba=new Actividad("Escalada", NivelEducativo.Juvenil,Horario.valueOf("Manana"), 10, 3);
        AsistenteDAO asistenteDAO=new AsistenteDAO();
        //asistenteDAO.AgregarAsistente(asistentePrueba);
        /*if(asistenteDAO.existeID(11)){
            System.out.println("Si existe");
        }
        asistenteDAO.modificar(asistentePrueba);
        ArrayList<Asistente> lista=asistenteDAO.listaAsistentes();
        for(Asistente it:lista){
            System.out.println(it.toString());
        } */
        MonitorDAO monitorDAO=new MonitorDAO();
        //monitorDAO.AgregarMonitor(monitorPrueba);
        ActividadDAO actividadDAO = new ActividadDAO();
        //actividadDAO.AgregarActividad(actividadPrueba);
        //monitorDAO.asociarMonitorActividad(monitorPrueba, actividadPrueba);
        //Campamento campamentoPrueba= new Campamento(21,LocalDate.parse("2023-11-15"),LocalDate.parse("2023-11-20"),NivelEducativo.Juvenil,100);
        CampamentoDAO campamentoDAO=new CampamentoDAO();
        //campamentoDAO.AgregarCampamento(campamentoPrueba);
        /*if(actividadDAO.existeActividad("Escalada")){
            Actividad actividadPrueba=actividadDAO.buscarActividad("Escalada");
            System.out.println(actividadPrueba.toString());
        }
        
        if(campamentoDAO.existeID(21)){
            Campamento campamentoPrueba=campamentoDAO.buscarCampamento(21);
            System.out.println(campamentoPrueba.toString());
        }*/
        campamentoDAO.asociarMonitorResponsable(11, 21);


        
    }   
}
