package src.Datos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import src.Datos.Comun.ConexionBD;
import src.Negocio.DTO.UsuarioDTO;
import src.Negocio.DTO.Enum.TipoUsuario;

public class UsuarioDAO {
    
	private Properties sql;
	private Properties config;

    public UsuarioDAO(Properties sql, Properties config){
		this.sql=sql;
		this.config=config;
	}

    private String getConsulta(String clave){

		String Consulta = sql.getProperty(clave);

		return Consulta;
	}

    public void AgregarUsuario(UsuarioDTO usuario){

		String Consulta=getConsulta("insertarUsuario");

		try{
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			ps.setString(2,usuario.getNombre());
			ps.setString(1,usuario.getCorreo());
			ps.setString(3,usuario.getContrasena());
			ps.setString(4,usuario.getTipo().name());
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public boolean existeCorreo(String correo){
		String Consulta=getConsulta("buscarUsuarioPorCorreo");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1,correo);

			ResultSet rs=(ResultSet)ps.executeQuery();
			if(rs.next()){
				existe=true;
			}

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
		return existe;
	}

	public UsuarioDTO buscarUsuarioPorCorreo(String correo){
		String Consulta=getConsulta("buscarUsuarioPorCorreo");
		UsuarioDTO usuario=new UsuarioDTO();

		try{
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1, correo);
			ResultSet rs=(ResultSet)ps.executeQuery();

			if(rs.next()){
				String nombre=rs.getString("nombreApellidos");
				String contrasena=rs.getString("contrasena");
				TipoUsuario tipo= TipoUsuario.valueOf(rs.getString("tipo"));
				usuario=new UsuarioDTO(nombre,correo,contrasena,tipo);
			}

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return usuario;
	}

	public void modificar(UsuarioDTO usuario){
		String Consulta=getConsulta("modificarAsistente");

		try{
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			
			ps.setString(3,usuario.getCorreo());
			ps.setString(1,usuario.getNombre());
			ps.setString(2,usuario.getContrasena());
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
