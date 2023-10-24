package Datos;

public class RegistroTardio extends Registro{


    @Override
    public InscripcionCompleta createRegistroC(){

        InscripcionCompleta NuevaCompleta = new InscripcionCompleta(false);
        return NuevaCompleta;
    }
    
    @Override
    public InscripcionParcial createRegistroP(){

        InscripcionParcial NuevaParcial = new InscripcionParcial(false);
        return NuevaParcial;
    }
}