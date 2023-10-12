package clases;

public class RegistroTemprano extends Registro{

    public RegistroTemprano(){}

    public InscripcionCompleta createInscripcionC(){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(false);
        return NuevaCompleta;
    }
    
    public InscripcionParcial creaateInscripcionParcial(){

        InscripcionParcial NuevaParcial = new InscripcionParcial(false);
        return NuevaParcial;
    }
}