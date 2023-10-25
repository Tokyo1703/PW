package Datos;

import java.time.LocalDate;

public class InscripcionCompleta extends Inscripcion {

    InscripcionCompleta() {

        super();
    }

    InscripcionCompleta(int id_asist, int id_campa, LocalDate fecha, float precio, boolean necesidadEspecial, boolean cancelacion) {
        
        super(id_asist, id_campa, fecha, precio, necesidadEspecial, cancelacion);
    }

    @Override
    public String toString() {

        String info = "Id participante: " + this.asis_id + "\nId campamento: " + this.cmp_id
                + "\nFecha de la inscipcion: "
                + this.fecha + "\nTipo de inscripcion: Completa" + "\nPrecio: " + this.precio;

        return info;
    }

}