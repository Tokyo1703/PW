package Negocio.DTO;

import java.time.LocalDate;

public class RegistroTardio extends Registro{


    @Override
    public InscripcionCompleta createInscripcionC(int id_asist, int id_campa, LocalDate fecha, float precio){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(id_asist, id_campa, fecha, precio, false);
        return NuevaCompleta;
    }
    
    @Override
    public InscripcionParcial createInscripcionP(int id_asist, int id_campa, LocalDate fecha, float precio){

        InscripcionParcial NuevaParcial = new InscripcionParcial(id_asist, id_campa, fecha, precio, false);
        return NuevaParcial;
    }
}