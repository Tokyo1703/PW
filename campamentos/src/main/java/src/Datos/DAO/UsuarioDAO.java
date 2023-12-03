package src.Datos.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import src.Datos.Comun.ConexionBD;
import src.Negocio.DTO.UsuarioDTO;
import src.Negocio.DTO.Enum.TipoUsuario;

public class UsuarioDAO {
    
    private String getConsulta(String clave){

		Properties sql = new Properties();
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(new File("sql.properties")));
			sql.load(lector);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}

		String Consulta = sql.getProperty(clave);

		return Consulta;
	}

    public void AgregarUsuario(UsuarioDTO usuario){

		String Consulta=getConsulta("insertarUsuario");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			ps.setString(1,usuario.getCorreo());
			ps.setString(2,usuario.getContrasena());
			ps.setString(3,usuario.getTipo().name());
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public UsuarioDTO buscarUsuarioPorCorreo(String correo){
		String Consulta=getConsulta("buscarUsuarioPorCorreo");
		UsuarioDTO usuario=new UsuarioDTO();

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1, correo);
			ResultSet rs=(ResultSet)ps.executeQuery();

			if(rs.next()){
			
				String contrasena=rs.getString("contraseña");
				TipoUsuario tipo= TipoUsuario.valueOf(rs.getString("tipo"));
				usuario=new UsuarioDTO(correo,contrasena,tipo);
			}

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return usuario;
	}

}
