package clases;

public class RegistroTemprano extends Registro{


    @Override
    public InscripcionCompleta createRegistroC(){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(true);
        return NuevaCompleta;
    }
    
    @Override
    public InscripcionParcial createRegistroP(){

        InscripcionParcial NuevaParcial = new InscripcionParcial(true);
        return NuevaParcial;
    }
}