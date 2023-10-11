package clases;

public class RegistroTemprano extends Registro{

    public RegistroTemprano(){}

    public InscripcionCompleta createInscripcionC(){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(true);
        return NuevaCompleta;
    }
    
    public InscripcionParcial creaateInscripcionParcial(){

        InscripcionParcial NuevaParcial = new InscripcionParcial(true);
        return NuevaParcial;
    }
}