package Datos.DAO;

import java.util.Properties;

import Datos.Comun.ConexionBD;
import Negocio.DTO.Asistente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AsistenteDAO {

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

    public void AgregarAsistente(Asistente asistente){

		String Consulta=getConsulta("insertarAsistente");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			String AtencionEspecial ="No";
			if(asistente.getAtencionEsp()){
				AtencionEspecial="Si";
			}
			ps.setInt(1,asistente.getId());
			ps.setString(2,asistente.getNombreCompleto());
			ps.setString(3,asistente.getFechaNacimiento().toString());
			ps.setString(4,AtencionEspecial);
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}

       
    }
}

	
	