
package src.Despliegue;

import src.Negocio.DTO.Enum.TipoUsuario;

public class customerBean implements java.io.Serializable {

	private String correo="";
    private String contrasena;
    private TipoUsuario tipo;
    

    public String getCorreo(){
        return correo;
    }

    public String getContrasena(){
        return contrasena;
    }

    public TipoUsuario getTipo(){
        return tipo;
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
}