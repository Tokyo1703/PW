package Negocio.DTO;

import java.time.LocalDate;

public class Asistente {
    
    private int id;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private boolean atencionEsp;

    public Asistente(){

    }

    public Asistente(int id, String nombreCompleto, LocalDate fechaNacimiento, boolean atencionEsp){
        this.id=id;
        this.nombreCompleto=nombreCompleto;
        this.fechaNacimiento=fechaNacimiento;
        this.atencionEsp=atencionEsp;
    }

    public int getId(){
        return id;
    }

    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    public boolean getAtencionEsp(){
        return atencionEsp;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNombreCompleto(String nombreCompleto){
        this.nombreCompleto=nombreCompleto;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento=fechaNacimiento;
    }

    public void setAtencionEsp(boolean atencionEsp){
        this.atencionEsp=atencionEsp;
    }
    
    public String toString(){

        String atencion;

        if(atencionEsp==false){
            atencion="No";
        }
        else{
            atencion="Si";
        }

        String infoAsistente= "Identificador: " + id + "\n" +
                        "Nombre y apellidos: " + nombreCompleto + "\n" +
                        "Fecha de nacimiento: " + fechaNacimiento + "\n" +
                        "Atencion especial: " + atencion + "\n";

        return infoAsistente;
    
    }
}
