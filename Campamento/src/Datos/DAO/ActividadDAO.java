package Datos.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.mysql.jdbc.ResultSet;

import Datos.Comun.ConexionBD;
import Negocio.DTO.Actividad;
import Negocio.DTO.Campamento;
import Negocio.DTO.Horario;
import Negocio.DTO.NivelEducativo;


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

	public boolean existeActividad(String nombre){
		String Consulta=getConsulta("buscarActividad");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1,nombre);

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

	public Actividad buscarActividad(String nombre){
		
		String Consulta=getConsulta("buscarActividad");
		Actividad actividad=new Actividad();
		
		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1, nombre);
			ResultSet rs=(ResultSet)ps.executeQuery();

			rs.next();

			NivelEducativo nivelEducativo = NivelEducativo.valueOf(rs.getString("nivelEducativo"));
			Horario horario = Horario.valueOf(rs.getString("horario"));
			int numMaxAsistentes =rs.getInt("numMaxAsistentes");
			int numeroMonitores=rs.getInt("numeroMonitores");
			actividad=new Actividad(nombre,nivelEducativo,horario,numMaxAsistentes,numeroMonitores);

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return actividad;

	}

	public int cantidadMonitoresActividad(String nombre){
		
		String Consulta=getConsulta("cantidadMonitoresActividad");
		int cantidad=0;

		try {
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1, nombre);
			ResultSet rs=(ResultSet)ps.executeQuery();

			rs.next();
			cantidad=rs.getInt("cantidad");
			
			conexionBD.closeConnection();

			
		}catch (Exception e) {
			System.out.println(e);
		}	
		return cantidad;
		
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
