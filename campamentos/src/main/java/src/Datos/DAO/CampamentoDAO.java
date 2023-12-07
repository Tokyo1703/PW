package src.Datos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;


import src.Datos.Comun.ConexionBD;
import src.Negocio.DTO.CampamentoDTO;
import src.Negocio.DTO.Enum.NivelEducativo;

/**
 * Clase DAO de campamento, encargada de obtener los datos de la clase campamento de la base de datos.
 */
public class CampamentoDAO {


	private Properties sql;
	private Properties config;

    public CampamentoDAO(Properties sql, Properties config){
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
     * Metodo usado para añadir un campamento a la base de datos
     * @param campamento campamento que se quiere añadir
     */

	public void AgregarCampamento(CampamentoDTO campamento){

		String Consulta=getConsulta("insertarCampamento");

		try{
			ConexionBD conexionBD=new ConexionBD(config);
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

	/**
     * Metodo usado para buscar si existe un campamento mediante un identificador en la base de datos
     * @param Id identificador del campamento
	 * @return true si se encuentra el campamento, false si no
     */

	public boolean existeID(int Id){
		String Consulta=getConsulta("buscarCampamento");
		boolean existe=false;
		try{
			
			ConexionBD conexionBD=new ConexionBD(config);
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
     * Metodo usado para buscar un campamento mediante un identificador en la base de datos
     * @param id identificador del campamento
	 * @return campamento que se busca
     */

	public CampamentoDTO buscarCampamento(int id){
		
		String Consulta=getConsulta("buscarCampamento");
		CampamentoDTO campamento=new CampamentoDTO();

		try{
			ConexionBD conexionBD=new ConexionBD(config);
        	Connection conexion=conexionBD.getConnection();
			PreparedStatement ps=conexion.prepareStatement(Consulta);

			ps.setInt(1, id);
			ResultSet rs=(ResultSet)ps.executeQuery();

			rs.next();
				
			LocalDate fechaInicio= LocalDate.parse(rs.getString("fechaInicio"));
			LocalDate fechaFin= LocalDate.parse(rs.getString("fechaFin"));
			NivelEducativo nivelEducativo = NivelEducativo.valueOf(rs.getString("nivelEducativo"));
			int numMaxAsistentes =rs.getInt("numMaxAsistentes");
			campamento=new CampamentoDTO(id,fechaInicio,fechaFin,nivelEducativo,numMaxAsistentes);

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return campamento;

	}

	/**
     * Metodo usado para obtener todos los campamentos con una fecha de inicio superior a una dada
	 * @param fecha fecha de corte para la busqueda
	 * @return ArrayList de los campamentos
     */

	public ArrayList<CampamentoDTO> buscarCampamentosPorFecha(LocalDate fecha){
		String Consulta=getConsulta("buscarCampamentoPorFecha");
		ArrayList<CampamentoDTO> lista = new ArrayList<CampamentoDTO>();

		try{
			ConexionBD conexionBD=new ConexionBD(config);
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
				lista.add(new CampamentoDTO(id,fechaInicio,fechaFin,nivelEducativo,numMaxAsistentes));
			}

			conexionBD.closeConnection();
			
		}catch(Exception e){
			System.out.println(e);
		}
		return lista;
	}

	/**
     * Metodo usado para asociar un monitor a un campamento
	 * @param idMonitor identificador del monitor
	 * @param idCampamento identificador del campamento
     */

	public void asociarMonitorResponsable(int idMonitor, int idCampamento){
		String Consulta=getConsulta("asociarMonitorResponsable");

		try{
			ConexionBD conexionBD=new ConexionBD(config);
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

	/**
     * Metodo usado para asociar un monitor especial a un campamento
	 * @param idMonitor identificador del monitor
	 * @param idCampamento identificador del campamento
     */
	
	public void asociarMonitorEsp(int idMonitor, int idCampamento){
		String Consulta=getConsulta("asociarMonitorEspecial");

		try{
			ConexionBD conexionBD=new ConexionBD(config);
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

	/**
     * Metodo usado para contar el numero de actividades de un campamento
     * @param idCampamento identificador del campamento
	 * @return numero de actividades
     */

	public int numeroActividades(int idCampamento){
		String Consulta=getConsulta("contarActividades");
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
}
