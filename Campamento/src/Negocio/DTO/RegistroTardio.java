package Negocio.DTO;

import java.time.LocalDate;

public class RegistroTardio extends Registro{


    @Override
    public InscripcionCompleta createRegistroC(int id_asist, int id_campa, LocalDate fecha, float precio, boolean necesidadEspecial){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(id_asist, id_campa, fecha, precio, necesidadEspecial, false);
        return NuevaCompleta;
    }
    
    @Override
    public InscripcionParcial createRegistroP(int id_asist, int id_campa, LocalDate fecha, float precio, boolean necesidadEspecial){

        InscripcionParcial NuevaParcial = new InscripcionParcial(id_asist, id_campa, fecha, precio, necesidadEspecial, false);
        return NuevaParcial;
    }
}