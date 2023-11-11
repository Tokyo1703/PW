package Datos.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Properties;

import com.mysql.jdbc.ResultSet;

import Datos.Comun.ConexionBD;
import Negocio.DTO.Actividad;
import Negocio.DTO.Monitor;



public class MonitorDAO {

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

    public void AgregarMonitor(Monitor monitor){

		String Consulta=getConsulta("insertarMonitor");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			String AtencionEspecial ="No";
			if(monitor.getAtencionEsp()){
				AtencionEspecial="Si";
			}
			ps.setInt(1,monitor.getId());
			ps.setString(2,monitor.getNombreCompleto());
			ps.setString(3,AtencionEspecial);
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	public boolean existeID(int Id){
		String Consulta=getConsulta("buscarMonitor");
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

	public Monitor buscarMonitor(int id){
		
		String Consulta=getConsulta("buscarMonitor");
		Monitor monitor=new Monitor();

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, id);
			ResultSet rs=(ResultSet)ps.executeQuery();

			rs.next();
				
			Boolean atencionEspecial=false;
			String nombreApellidos= rs.getString("nombreApellidos");
			String atencion=rs.getString("atencionEspecial");
			if(atencion.equals("Si")){
				atencionEspecial=true;
			}
			monitor=new Monitor(id,nombreApellidos,atencionEspecial);

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return monitor;

	}			

	public int cantidadActividadesMonitor(int id){
		String Consulta=getConsulta("cantidadActividadesMonitor");
		int cantidad=0;

		try {
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, id);
			ResultSet rs=(ResultSet)ps.executeQuery();

			rs.next();
			cantidad=rs.getInt("cantidad");
			
			conexionBD.closeConnection();

			
		}catch (Exception e) {
			System.out.println(e);
		}	
		return cantidad;
	}
    public void asociarMonitorActividad(Monitor monitor, Actividad actividad){
        String Consulta=getConsulta("asociarMonitorActividad");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			ps.setInt(2,monitor.getId());
			ps.setString(1,actividad.GetNombre());
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }


    public ArrayList<Monitor> listaMonitores(){

		ArrayList<Monitor> lista = new ArrayList<Monitor>();
		String Consulta=getConsulta("listarMonitores");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			ResultSet rs=(ResultSet)ps.executeQuery();

			while(rs.next()){
				int id=rs.getInt("Id");
				String nombre=rs.getString("nombreApellidos");
				String atencionEspecial=rs.getString("atencionEspecial");
				boolean atencion=false;
				if(atencionEspecial=="Si"){
					atencion=true;
				}
				lista.add(new Monitor(id,nombre,atencion));

			}
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}

		return lista;
	}
}
