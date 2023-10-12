package clases;

import java.sql.Date;

public abstract class Inscripcion
{
    protected int asis_id;
    protected int cmp_id;
    protected Date fecha;
    protected float precio;

    protected boolean cancelacion;

    Inscripcion()
    {

    }

    Inscripcion(boolean cancelacion){

        this.cancelacion = cancelacion;
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

    public Date getFecha()
    {
        return fecha;
    }

    public float getPrecio()
    {
        return precio;
    }

    public String getCancelacion(){
        
        String permitido = "No poermitida";

        if(cancelacion == true){

            permitido = "Permitida";
        }

        return permitido;
    }
        
    public boolean getBoleanCancel(){
        
        return cancelacion;
    }

    // Setters

    public void setIdAsis(int id)
    {
        asis_id = id;
    }

    public void setIdCmp(int id)
    {
        cmp_id = id;
    }

    public void setFecha(Date fecha_)
    {
        fecha = fecha_;
    }

    public void setIdAsis(float precio_)
    {
        precio = precio_;
    }


    public String toString(){

        String info = "Id participante: " + this.asis_id + "\nId campamento: " + this.cmp_id + "\nFecha de la inscipcion: " 
        + this.fecha + "\nPrecio: " + this.precio;

        return info;
    }
}