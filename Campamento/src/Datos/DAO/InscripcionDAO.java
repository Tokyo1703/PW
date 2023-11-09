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
import Negocio.DTO.Inscripcion;

public class InscripcionDAO {

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

	public void agregarParcial(Inscripcion inscripcion){

		String Consulta=getConsulta("insertarInscipcion");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, inscripcion.getIdAsistente());
			ps.setInt(2,inscripcion.getIdCampamento());
			ps.setString(3,inscripcion.getFecha().toString());
			ps.setFloat(4,inscripcion.getPrecio());
			ps.setString(5,"Parcial");
			ps.setString(6, inscripcion.getRegistro().name());

			ps.executeUpdate();

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public void agregarCompleta(Inscripcion inscripcion){

		String Consulta=getConsulta("insertarInscipcion");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, inscripcion.getIdAsistente());
			ps.setInt(2,inscripcion.getIdCampamento());
			ps.setString(3,inscripcion.getFecha().toString());
			ps.setFloat(4,inscripcion.getPrecio());
			ps.setString(5,"Completa");
			ps.setString(6, inscripcion.getRegistro().name());

			ps.executeUpdate();

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public boolean existeParcial(int idAsistente, int idCampamento){

		String Consulta=getConsulta("buscarInscripcionParcial");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idAsistente);
			ps.setInt(2,idCampamento);

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

	public boolean existeCompleta(int idAsistente, int idCampamento){

		String Consulta=getConsulta("buscarInscripcionCompleta");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idAsistente);
			ps.setInt(2,idCampamento);

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

	public int numeroAsistentes(int idCampamento){
		String Consulta=getConsulta("contarAsistentes");
		int cantidad = 0;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idCampamento);

			ResultSet rs=(ResultSet)ps.executeQuery();
			if(rs.next()){
				cantidad = rs.getInt("cantidad");
			}

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
		return cantidad;
	}

}
