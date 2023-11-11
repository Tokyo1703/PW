package Datos.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import Negocio.DTO.Actividad;
import Negocio.DTO.Asistente;
import Negocio.DTO.Campamento;
import Negocio.DTO.Horario;
import Negocio.DTO.Inscripcion;
import Negocio.DTO.Monitor;
import Negocio.DTO.NivelEducativo;
import Negocio.DTO.Enum.Registro;
import Negocio.DTO.Enum.TipoInscripcion;

public class PuebaDAOS {
    public static void main(String[] args){
        LocalDate fecha=LocalDate.now();
        Asistente asistentePrueba=new Asistente(16,"Ivan Romero Garcia",fecha,true);
        Monitor monitorPrueba=new Monitor(22,"Pepe Garcia Cruz",false);
        Actividad actividadPrueba=new Actividad("Escalada", NivelEducativo.Juvenil,Horario.valueOf("Manana"), 10, 3);
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
        
        ArrayList<Campamento> lista2= campamentoDAO.buscarCampamentosPorFecha(fecha.plusDays(2));
        for(Campamento it: lista2){
            System.out.println(it.toString());
        }
        
        //System.out.println(monitorDAO.cantidadActividadesMonitor(23));
        InscripcionDAO inscripcionDAO = new InscripcionDAO();
        
        //Inscripcion inscripcionPrueba=new Inscripcion(5, 21, fecha, 300, Registro.Temprano, TipoInscripcion.Completa);
        //inscripcionDAO.agregarCompleta(inscripcionPrueba);
    }   
}
