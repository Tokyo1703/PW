package Datos.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import com.mysql.jdbc.ResultSet;

import Datos.Comun.ConexionBD;
import Negocio.DTO.Campamento;
import Negocio.DTO.NivelEducativo;

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

	public boolean existeID(int Id){
		String Consulta=getConsulta("buscarCampamento");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,Id);

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

	public Campamento buscarCampamento(int id){
		
		String Consulta=getConsulta("buscarCampamento");
		Campamento campamento=new Campamento();

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, id);
			ResultSet rs=(ResultSet)ps.executeQuery();

			rs.next();
				
			LocalDate fechaInicio= LocalDate.parse(rs.getString("fechaInicio"));
			LocalDate fechaFin= LocalDate.parse(rs.getString("fechaFin"));
			NivelEducativo nivelEducativo = NivelEducativo.valueOf(rs.getString("nivelEducativo"));
			int numMaxAsistentes =rs.getInt("numMaxAsistentes");
			campamento=new Campamento(id,fechaInicio,fechaFin,nivelEducativo,numMaxAsistentes);

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return campamento;

	}

	public ArrayList<Campamento> buscarCampamentosPorFecha(LocalDate fecha){
		String Consulta=getConsulta("buscarCampamentoPorFecha");
		ArrayList<Campamento> lista = new ArrayList<Campamento>();

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setString(1, fecha.toString());
			ResultSet rs=(ResultSet)ps.executeQuery();

			while(rs.next()){
				int id=rs.getInt("Id");
				LocalDate fechaInicio= LocalDate.parse(rs.getString("fechaInicio"));
				LocalDate fechaFin= LocalDate.parse(rs.getString("fechaFin"));
				NivelEducativo nivelEducativo = NivelEducativo.valueOf(rs.getString("nivelEducativo"));
				int numMaxAsistentes =rs.getInt("numMaxAsistentes");
				lista.add(new Campamento(id,fechaInicio,fechaFin,nivelEducativo,numMaxAsistentes));
			}

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return lista;
	}

	public void asociarMonitorResponsable(int idMonitor, int idCampamento){
		String Consulta=getConsulta("asociarMonitorResponsable");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idMonitor);
			ps.setInt(2,idCampamento);
			
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	
	public void asociarMonitorEsp(int idMonitor, int idCampamento){
		String Consulta=getConsulta("asociarMonitorEspecial");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idMonitor);
			ps.setInt(2,idCampamento);
			
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public int numeroActividades(int idCampamento){
		String Consulta=getConsulta("contarActividades");
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
