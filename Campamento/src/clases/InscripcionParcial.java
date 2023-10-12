package clases;

import java.sql.Date;

public class InscripcionParcial extends Inscripcion
{
    InscripcionParcial()
    {
        super();
    }

    InscripcionParcial(boolean cancelacion){

        super(cancelacion);
    }

    @Override
    public String toString(){

        String info = "Id participante: " + this.asis_id + "\nId campamento: " + this.cmp_id
                + "\nFecha de la inscipcion: "
                + this.fecha + "\nTipo de inscripcion: Parcial" + "\nPrecio: " + this.precio;

        return info;
    }

}