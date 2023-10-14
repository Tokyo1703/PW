package clases;

import java.util.ArrayList;

public class App {
    public static void main(String[] args){
        Monitor Andres = new Monitor(21,"Andres Sanchez",true);
        Monitor Carlos = new Monitor(32, "Carlos Ruiz Martinez", false );
        Monitor Paula = new Monitor(37, "Paula Gutierrez Aguilar", false );
        ArrayList<Monitor> Encargados = new ArrayList<Monitor>();
        Actividad Escondite = new Actividad("Escondite", NivelEducativo.Infantil,Horario.Maniana,20,2,Encargados);
        Escondite.asociarMonitor(Carlos);
        Escondite.asociarMonitor(Andres);
        Escondite.asociarMonitor(Paula);

        System.out.println(Escondite.toString());
    }
}
