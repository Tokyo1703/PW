package Datos;
import java.util.ArrayList;

import Datos.Comun.Horario;
import Datos.Comun.NivelEducativo;



public class Actividad {
    
    private String Nombre;
    private NivelEducativo Nivel;
    private Horario Hora;
    private int Capacidad;
    private int MonitoresMax;
    private ArrayList<Monitor> MonitoresEncargados; //Implementamos un array para poder meter mas de un Monitor responsable

    public Actividad(){

    }

    public Actividad(String Nombre, NivelEducativo Nivel, Horario Hora, int Capacidad, int Monitores){
        this.Nombre = Nombre;
        this.Nivel = Nivel;
        this.Hora = Hora;
        this.Capacidad = Capacidad;
        MonitoresMax = Monitores;
        MonitoresEncargados = new ArrayList<Monitor>();
    }

    public String GetNombre(){
        return Nombre;
    }

    public NivelEducativo GetNivel(){
        return Nivel;
    }

    public Horario GetHora(){
        return Hora;
    }

    public int GetCapacidad(){
        return Capacidad;
    }

    public int GetMonitoresMax(){
        return MonitoresMax;
    }

    public ArrayList<Monitor> GetMonitoresEncargados(){
        return new ArrayList<Monitor> (MonitoresEncargados);
    }

    public String toString(){
        String informacion = ("Nombre: " + Nombre + "\nNivel: " + Nivel + "\nHora: " + Hora +
                         "\nCapacidad: " + Capacidad + "\nMonitores Maximos: " + MonitoresMax +
                         "\nMonitores: \n");
        
        for (Monitor i : MonitoresEncargados) {
            informacion = informacion + i.toString() + "\n";    
        }
        return informacion;
    }

    public void asociarMonitor(Monitor m){
        if(MonitoresEncargados.size() < MonitoresMax)
            MonitoresEncargados.add(m);
        else
            System.out.println("Esta actividad ya tiene los maximos monitores posibles asignados.");
    }
    
}