package src.Datos.DAO;

import java.util.ArrayList;
import java.util.Properties;



import src.Datos.Comun.ConexionBD;
import src.Negocio.DTO.AsistenteDTO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.time.LocalDate;

/**
 * Clase DAO de asistente, encargada de obtener los datos de la clase asistente de la base de datos.
 */
public class AsistenteDAO {

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
     * Metodo usado para añadir un asistente a la base de datos
     * @param asistente asistente que se quiere añadir
     */

    public void AgregarAsistente(AsistenteDTO asistente){

		String Consulta=getConsulta("insertarAsistente");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			String AtencionEspecial ="No";
			if(asistente.getAtencionEsp()){
				AtencionEspecial="Si";
			}
			ps.setInt(1,asistente.getId());
			ps.setString(2,asistente.getNombreCompleto());
			ps.setString(3,asistente.getFechaNacimiento().toString());
			ps.setString(4,AtencionEspecial);
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
    }

	/**
     * Metodo usado para buscar si existe un asistente mediante un identificador en la base de datos
     * @param Id identificador del asistente
	 * @return true si se encuentra el asistente, false si no
     */

	public boolean existeID(int Id){
		String Consulta=getConsulta("existeIdAsistente");
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
     * Metodo usado para modificar los datos de una asistente ya existente en la base de datos
     * @param asistente asistente a modificar
     */

	public void modificar(AsistenteDTO asistente){
		String Consulta=getConsulta("modificarAsistente");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			String AtencionEspecial ="No";
			if(asistente.getAtencionEsp()){
				AtencionEspecial="Si";
			}
			ps.setInt(4,asistente.getId());
			ps.setString(1,asistente.getNombreCompleto());
			ps.setString(2,asistente.getFechaNacimiento().toString());
			ps.setString(3,AtencionEspecial);
			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	/**
     * Metodo usado para obtener todos los asistetes de la base de datos
	 * @return ArrayList de las actividades
     */
	
	public ArrayList<AsistenteDTO> listaAsistentes(){

		ArrayList<AsistenteDTO> lista = new ArrayList<AsistenteDTO>();
		String Consulta=getConsulta("listarAsistentes");

		try{
			ConexionBD conexionBD=new ConexionBD("config.properties");
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);
			ResultSet rs=(ResultSet)ps.executeQuery();

			while(rs.next()){
				int id=rs.getInt("Id");
				String nombre=rs.getString("nombreApellidos");
				LocalDate fecha= LocalDate.parse(rs.getString("fechaNacimiento"));
				String atencionEspecial=rs.getString("atencionEspecial");
				boolean atencion=false;
				if(atencionEspecial.equals("Si")){
					atencion=true;
				}
				
				lista.add(new AsistenteDTO(id,nombre,fecha,atencion));

			}
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}

		return lista;
	}
		
}

	
	