package src.Negocio.DTO;

import java.time.LocalDate;

import src.Negocio.DTO.Enum.Registro;
import src.Negocio.DTO.Enum.TipoInscripcion;

public class InscripcionDTO
{
    private int idAsistente;
    private int idCampamento;
    private LocalDate fecha;
    private float precio;
    private Registro tipoRegistro;
    private TipoInscripcion tipoInscripcion;

    // Constructor Vacio
    public InscripcionDTO()
    {

    }

    // Constructor Diferenciador Temprano/Tardia
    public InscripcionDTO(int idAsistente_, int idCampamento_, LocalDate fecha_, float precio_, Registro tipoRegistro_, TipoInscripcion tipoInscripcion_)
    {

        this.idAsistente = idAsistente_;
        this.idCampamento = idCampamento_;
        this.fecha = fecha_;
        this.precio = precio_;
        this.tipoRegistro = tipoRegistro_;
        this.tipoInscripcion = tipoInscripcion_;
    }

    // Getters

    public int getIdAsistente()
    {
        return idAsistente;
    }

    public int getIdCampamento()
    {
        return idCampamento;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public float getPrecio()
    {
        return precio;
    }

    public Registro getRegistro()
    {
        return tipoRegistro;
    }
        
    public TipoInscripcion getTipoInscripcion()
    {
        return tipoInscripcion;
    }

    // Setters

    public void setIdAsistente(int id)
    {
        this.idAsistente = id;
    }

    public void setIdCampamento(int id)
    {
        this.idCampamento = id;
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

        String info = "Id participante: " + this.idAsistente + "\nId campamento: " + this.idCampamento + "\nFecha de la inscipcion: " 
        + this.fecha + "\nPrecio: " + this.precio;

        return info;
    }
}