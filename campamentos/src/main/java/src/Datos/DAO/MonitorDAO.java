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
import Negocio.DTO.ActividadDTO;
import Negocio.DTO.MonitorDTO;

/**
 * Clase DAO de monitor, encargada de obtener los datos de la clase monitor de la base de datos.
 */
public class MonitorDAO {

	/**
     * Metodo usado para extraer la sentencia sql del fichero sql.properties
     * @param clave cadena utilizada como clave de busqueda
     * @return string que almacena la sentencia sql
     */

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

	/**
     * Metodo usado para añadir un monitor a la base de datos
     * @param monitor monitor que se quiere añadir
     */

    public void AgregarMonitor(MonitorDTO monitor){

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

	/**
     * Metodo usado para buscar si existe un monitor mediante un identificador en la base de datos
	 * @param Id identificador del monitor
	 * @return true si se encuentra el monitor, false si no
     */

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

	/**
     * Metodo usado para buscar un monitor mediante un identificador en la base de datos
	 * @param id identificador del monitor
	 * @return monitor que se busca
     */

	public MonitorDTO buscarMonitor(int id){
		
		String Consulta=getConsulta("buscarMonitor");
		MonitorDTO monitor=new MonitorDTO();

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
			monitor=new MonitorDTO(id,nombreApellidos,atencionEspecial);

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return monitor;

	}			

	/**
     * Metodo usado para contar el numero de actividades a las que esta asociado un monitor
     * @param id identificador del monitor
	 * @return numero de actividades
     */

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

	/**
     * Metodo usado para asociar un monitor a una actividad
	 * @param monitor monitor que se va a asociar
	 * @param actividad actividad a la que se va a asociar
     */

    public void asociarMonitorActividad(MonitorDTO monitor, ActividadDTO actividad){
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

	/**
     * Metodo usado para comprobar si un monitor esta asociado a una actividad
	 * @param monitor monitor 
	 * @param actividad actividad
	 * @return true si esta asociado, false si no
     */

	public boolean existeMonitorEnActividad(MonitorDTO monitor, ActividadDTO actividad){
		String Consulta=getConsulta("buscarActividadMonitor");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,monitor.getId());
			ps.setString(2,actividad.GetNombre());

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

	/**
     * Metodo usado para obtener todos los monitores de la base de datos
	 * @return ArrayList de los monitores
     */

    public ArrayList<MonitorDTO> listaMonitores(){

		ArrayList<MonitorDTO> lista = new ArrayList<MonitorDTO>();
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
				lista.add(new MonitorDTO(id,nombre,atencion));

			}
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}

		return lista;
	}
}
