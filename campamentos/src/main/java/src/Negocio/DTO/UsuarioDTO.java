package src.Negocio.DTO;

import src.Negocio.DTO.Enum.TipoUsuario;

public class UsuarioDTO {

    private String nombreCompleto;
    private String correo;
    private String contrasena;
    private TipoUsuario tipo;
    

    public UsuarioDTO(){

    }
    public UsuarioDTO(String nombre, String correo, String contrasena, TipoUsuario tipo){
        this.nombreCompleto = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public String getNombre(){
        return nombreCompleto;
    }

    public String getCorreo(){
        return correo;
    }

    public String getContrasena(){
        return contrasena;
    }

    public TipoUsuario getTipo(){
        return tipo;
    }

    public void setNombre(String nombre){
        this.nombreCompleto = nombre;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public void setTipo(TipoUsuario tipo){
        this.tipo=tipo;
    }

    public String toString(){

        String info = "Correo: " + this.correo + "\nContraseña: " + this.contrasena + "\nTipo de usuario: " + this.tipo;

        return info;
    }

}
