package Negocio.DTO;

import java.time.LocalDate;

public abstract class Inscripcion
{
    protected int asis_id;
    protected int cmp_id;
    protected LocalDate fecha;
    protected float precio;
    protected boolean cancelacion;

    // Constructor Vacio
    Inscripcion()
    {

    }

    // Constructor Diferenciador Temprano/Tardia
    Inscripcion(int id_asist, int id_campa, LocalDate fecha, float precio, boolean cancelacion)
    {

        this.cancelacion = cancelacion;
        this.asis_id = id_asist;
        this.cmp_id = id_campa;
        this.fecha = fecha;
        this.precio = precio;
    }

    // Getters

    public int getIdAsis()
    {
        return asis_id;
    }

    public int getIdCamp()
    {
        return cmp_id;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public float getPrecio()
    {
        return precio;
    }

    public String getCancelacion()
    {
        
        String permitido = "No permitida";

        if(cancelacion == true){

            permitido = "Permitida";
        }

        return permitido;
    }
        
    public boolean getBooleanCancel()
    {
        
        return cancelacion;
    }

    // Setters

    public void setIdAsis(int id)
    {
        this.asis_id = id;
    }

    public void setIdCmp(int id)
    {
        this.cmp_id = id;
    }

    public void setFecha(LocalDate fecha_)
    {
        this.fecha = fecha_;
    }

    public void setPrecio(float precio_)
    {
        this.precio = precio_;
    }


    public String toString(){

        String info = "Id participante: " + this.asis_id + "\nId campamento: " + this.cmp_id + "\nFecha de la inscipcion: " 
        + this.fecha + "\nPrecio: " + this.precio;

        return info;
    }
}