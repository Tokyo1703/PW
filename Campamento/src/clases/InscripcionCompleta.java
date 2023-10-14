package clases;


public class InscripcionCompleta extends Inscripcion {

    InscripcionCompleta() {

        super();
    }

    InscripcionCompleta(boolean cancelacion) {
        
        super(cancelacion);
    }

    @Override
    public String toString() {

        String info = "Id participante: " + this.asis_id + "\nId campamento: " + this.cmp_id
                + "\nFecha de la inscipcion: "
                + this.fecha + "\nTipo de inscripcion: Completa" + "\nPrecio: " + this.precio;

        return info;
    }

}