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
import Negocio.DTO.ActividadDTO;
import Negocio.DTO.CampamentoDTO;
import Negocio.DTO.Enum.Horario;
import Negocio.DTO.Enum.NivelEducativo;

/**
 * Clase DAO de actividad, encargada de obtener los datos de la clase actividad de la base de datos.
 */
public class ActividadDAO {

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
     * Metodo usado para añadir una actividad a la base de datos
     * @param actividad actividad que se quiere añadir
     */

    public void AgregarActividad(ActividadDTO actividad){

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

	/**
     * Metodo usado para buscar si existe una actividad mediante un nombre en la base de datos
     * @param nombre nombre de la actividad
	 * @return true si se encuentra la actividad, false si no
     */

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

	/**
     * Metodo usado para buscar una actividad mediante un nombre en la base de datos
     * @param nombre nombre de la actividad
	 * @return actividad que se busca
     */

	public ActividadDTO buscarActividad(String nombre){
		
		String Consulta=getConsulta("buscarActividad");
		ActividadDTO actividad=new ActividadDTO();
		
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
			actividad=new ActividadDTO(nombre,nivelEducativo,horario,numMaxAsistentes,numeroMonitores);

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return actividad;

	}

	/**
     * Metodo usado para comprobar si una actividad pertenece a un campamento
     * @param actividad actividad que se busca
	 * @param campamento campamento donde buscar
	 * @return true si se encuentra la actividad, false si no
     */

	public boolean existeActividadEnCampamento(ActividadDTO actividad, CampamentoDTO campamento){
		String Consulta=getConsulta("buscarActividadCampamento");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,campamento.getId());
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
     * Metodo usado para contar el numero de monitores de una actividad
     * @param nombre nombre de la actividad
	 * @return numero de monitores
     */

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

	/**
     * Metodo usado para añadir una actividad a un campamento
	 * @param campamento campamento donde se añade
	 * @param actividad actividad que se añade
     */
	
	public void asociarCampamentoActividad(CampamentoDTO Campamento, ActividadDTO actividad){
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
