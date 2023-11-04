package Negocio.DTO;



public class Actividad {
    
    private String Nombre;
    private NivelEducativo Nivel;
    private Horario Hora;
    private int Capacidad;
    private int MonitoresMax;


    public Actividad(){

    }

    public Actividad(String Nombre, NivelEducativo Nivel, Horario Hora, int Capacidad, int Monitores){
        this.Nombre = Nombre;
        this.Nivel = Nivel;
        this.Hora = Hora;
        this.Capacidad = Capacidad;
        MonitoresMax = Monitores;
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


    public String toString(){
        String informacion = ("Nombre: " + Nombre + "\nNivel: " + Nivel + "\nHora: " + Hora +
                         "\nCapacidad: " + Capacidad + "\nMonitores Maximos: " + MonitoresMax);

        return informacion;
    }
    
}