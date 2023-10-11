package clases;


import java.sql.Date;
import java.util.ArrayList;

public class App {
    public static void main(String[] args){
        Asistente Andres = new Asistente(21,"Andres Sanchez",Date.valueOf("2023-10-01"),true);
        Monitor Carlos = new Monitor(32, "Carlos Ruiz Martinez", false );
        ArrayList<Monitor> Encargados = new ArrayList<Monitor>();
        Encargados.add(Carlos);
        Actividad Escondite = new Actividad("Escondite", NivelEducativo.Infantil,Horario.Maniana,20,2,Encargados);
        System.out.println(Andres.toString());
        System.out.println(Carlos.toString());
        System.out.println(Escondite.toString());
    }
}
