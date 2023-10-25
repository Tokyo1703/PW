package Datos;

public abstract class Registro{



    public abstract InscripcionCompleta createRegistroC(int id_asist, int id_campa, LocalDate fecha, float precio, boolean necesidadEspecial);
    
    public abstract InscripcionParcial createRegistroP(int id_asist, int id_campa, LocalDate fecha, float precio, boolean necesidadEspecial);
}