package Negocio.DTO;

import java.time.LocalDate;

public abstract class Registro{



    public abstract InscripcionCompleta createInscripcionC(int id_asist, int id_campa, LocalDate fecha, float precio);
    
    public abstract InscripcionParcial createInscripcionP(int id_asist, int id_campa, LocalDate fecha, float precio);
}