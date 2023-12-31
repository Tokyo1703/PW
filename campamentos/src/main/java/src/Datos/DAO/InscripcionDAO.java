package src.Datos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;


import src.Datos.Comun.ConexionBD;
import src.Negocio.DTO.InscripcionDTO;
import src.Negocio.DTO.Enum.Registro;
import src.Negocio.DTO.Enum.TipoInscripcion;

/**
 * Clase DAO de inscripcion, encargada de obtener los datos de la clase inscripcion de la base de datos.
 */
public class InscripcionDAO {

	private Properties sql;
	private Properties config;

    public InscripcionDAO(Properties sql, Properties config){
		this.sql=sql;
		this.config=config;
	}

	/**
     * Metodo usado para extraer la sentencia sql del fichero sql.properties
     * @param clave cadena utilizada como clave de busqueda
     * @return string que almacena la sentencia sql
     */

    private String getConsulta(String clave){

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
			ConexionBD conexionBD=new ConexionBD(config);
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
			
			ConexionBD conexionBD=new ConexionBD(config);
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
     * Metodo usado para buscar una inscripcion mediante un campamento y un asistente en la base de datos
     * @param idAsistente identificador del asistente
	 * @param idCampamento identificador del campamento
	 * @return inscripcion
     */

	public InscripcionDTO buscarInscripcion(int idAsistente, int idCampamento){

		String Consulta=getConsulta("buscarInscripcion");
		InscripcionDTO inscripcion=null;
		try{
			
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idAsistente);
			ps.setInt(2,idCampamento);

			ResultSet rs=(ResultSet)ps.executeQuery();
			if(rs.next()){
				LocalDate fecha = LocalDate.parse(rs.getString(3));
				float precio = rs.getFloat(4);
				TipoInscripcion tipo = TipoInscripcion.valueOf(rs.getString(5));
				Registro registro = Registro.valueOf(rs.getString(6));

				inscripcion = new InscripcionDTO(idAsistente, idCampamento, fecha, precio, registro, tipo);

			}

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
		return inscripcion;
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
			
			ConexionBD conexionBD=new ConexionBD(config);
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
	
	public ArrayList<Integer> contarTiposAsistentes(int id){
		String Consulta=getConsulta("contarTiposAsistentes");
		ArrayList<Integer> inscripciones= new ArrayList<Integer>();
		try{
			
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,id);
			ps.setInt(2,id);

			ResultSet rs=(ResultSet)ps.executeQuery();
			if(rs.next()){
				inscripciones.add(rs.getInt("Completa"));
				inscripciones.add(rs.getInt("Parcial"));
			}

			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
		return inscripciones;
	}

	/**
     * Metodo usado para borrar una inscripcion
     * @param idCampamento identificador del campamento
	 * @param idAsistente identificador del asistente
     */
	public void borrar(int idCampamento, int idAsistente){
		String Consulta=getConsulta("borrarInscripcion");
		try{
			
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();	
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1,idAsistente);
			ps.setInt(2,idCampamento);

			ps.executeUpdate();
			conexionBD.closeConnection();
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
