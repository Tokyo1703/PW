package Datos.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import Datos.Comun.ConexionBD;
import Negocio.DTO.Actividad;
import Negocio.DTO.Campamento;

public class ActividadDAO {
    
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

    public void AgregarActividad(Actividad actividad){

		String Consulta=getConsulta("insertarActividad");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1,actividad.GetNombre());
			ps.setString(2,actividad.GetNivel().name());
			ps.setString(3,actividad.GetHora().name());
            ps.setInt(4,actividad.GetCapacidad()); 
            ps.setInt(5,actividad.GetMonitoresMax());

			ps.executeUpdate();

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public void asociarCampamentoActividad(Campamento Campamento, Actividad actividad){
        String Consulta=getConsulta("asociarCampamentoActividad");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			ps.setString(1,actividad.GetNombre());
			ps.setInt(2,Campamento.getId());
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

}
