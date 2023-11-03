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
import Negocio.DTO.Campamento;

public class CampamentoDAO {

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

	public void AgregarCampamento(Campamento campamento){

		String Consulta=getConsulta("insertarCampamento");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, campamento.getId());
			ps.setString(2,campamento.getInicio().toString());
			ps.setString(3,campamento.getFinal().toString());
			ps.setString(4,campamento.getNivel().name());
			ps.setInt(5,campamento.getMax());

			ps.executeUpdate();

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }
}
