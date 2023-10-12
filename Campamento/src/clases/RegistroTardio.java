package clases;

public class RegistroTardio extends Registro{

    public RegistroTardio(){}

    public InscripcionCompleta createInscripcionC(){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(false);
        return NuevaCompleta;
    }
    
    public InscripcionParcial creaateInscripcionParcial(){

        InscripcionParcial NuevaParcial = new InscripcionParcial(false);
        return NuevaParcial;
    }
}