package Datos.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import Negocio.DTO.Asistente;

public class PuebaDAOS {
    public static void main(String[] args){
        LocalDate fecha=LocalDate.parse("2001-07-19");
        Asistente asistentePrueba=new Asistente(19,"Juan Gutierrez Ribera",fecha,false);
        AsistenteDAO asistenteDAO=new AsistenteDAO();
        asistenteDAO.AgregarAsistente(asistentePrueba);
        /*if(asistenteDAO.existeID(11)){
            System.out.println("Si existe");
        }
        asistenteDAO.modificar(asistentePrueba); */
        ArrayList<Asistente> lista=asistenteDAO.listaAsistentes();
        for(Asistente it:lista){
            System.out.println(it.toString());
        }

    }
}
