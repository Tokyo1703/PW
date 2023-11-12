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
import Negocio.DTO.InscripcionDTO;

/**
 * Clase DAO de inscripcion, encargada de obtener los datos de la clase inscripcion de la base de datos.
 */
public class InscripcionDAO {

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
     * Metodo usado para añadir una inscripcion a la base de datos
     * @param inscripcion inscripcion que se quiere añadir
     */

	public void agregarInscripcion(InscripcionDTO inscripcion){

		String Consulta=getConsulta("insertarInscipcion");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, inscripcion.getIdAsistente());
			ps.setInt(2,inscripcion.getIdCampamento());
			ps.setString(3,inscripcion.getFecha().toString());
			ps.setFloat(4,inscripcion.getPrecio());
			ps.setString(5,inscripcion.getTipoInscripcion().name());
			ps.setString(6, inscripcion.getRegistro().name());

			ps.executeUpdate();

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	/**
     * Metodo usado para buscar si existe una inscripcion mediante un campamento y un asistente en la base de datos
     * @param idAsistente identificador del asistente
	 * @param idCampamento identificador del campamento
	 * @return true si se encuentra la inscripcion, false si no
     */

	public boolean existeInscripcion(int idAsistente, int idCampamento){

		String Consulta=getConsulta("buscarInscripcion");
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

	/**
     * Metodo usado para contar el numero de asistentes a un campamento
     * @param idCampamento identificador del campamento
	 * @return numero de asistentes
     */

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
