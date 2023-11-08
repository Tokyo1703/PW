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
import Negocio.DTO.InscripcionParcial;
import Negocio.DTO.InscripcionCompleta;

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

	public void agregarParcial(InscripcionParcial inscripcion){

		String Consulta=getConsulta("insertarInscipcion");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, inscripcion.getIdAsis());
			ps.setInt(2,inscripcion.getIdCamp());
			ps.setString(3,inscripcion.getFecha().toString());
			ps.setFloat(4,inscripcion.getPrecio());
			ps.setString(5,"Parcial");
			if(inscripcion.getBooleanCancel())
			{
				ps.setString(6,"Temprano");
			}
			else
			{
				ps.setString(6,"Tardio");
			}

			ps.executeUpdate();

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public void agregarCompleta(InscripcionCompleta inscripcion){

		String Consulta=getConsulta("insertarInscipcion");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, inscripcion.getIdAsis());
			ps.setInt(2,inscripcion.getIdCamp());
			ps.setString(3,inscripcion.getFecha().toString());
			ps.setFloat(4,inscripcion.getPrecio());
			ps.setString(5,"Completa");
			if(inscripcion.getBooleanCancel())
			{
				ps.setString(6,"Temprano");
			}
			else
			{
				ps.setString(6,"Tardio");
			}

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

// Añadir a sql.properties

/*
#Inscripdion
insertarInscipcion=insert into Inscripcion (idAsistente, idCampamento, fecha, precio, tipo, registro) values (?,?,?,?,?,?)
buscarInscripcionParcial=select * from Inscripcion where IdParticipante=? and IdCampamento=? and tipo='Parcial'
buscarInscripcionCompleta=select * from Inscripcion where IdParticipante=? and IdCampamento=? and tipo='Completa'
contarAsistentes=select COUNT(idAsistente) as cantidad from Inscripcion where IdCampamento=?
contarActividades=select COUNT(idCampamento) as cantidad from Actividad_Campamento where idCampamento=?
*/