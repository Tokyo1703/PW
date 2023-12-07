package src.Datos.Comun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConexionBD {




	protected String Usuario;
	protected  String Contrasena;

	protected Connection conexion = null;
	
	protected String url;

	public ConexionBD(Properties config){

		String Nombre = config.getProperty("nombre");
		String Puerto = config.getProperty("puerto");
		url="jdbc:mysql://oraclepr.uco.es:"+Puerto+"/"+Nombre;

		Usuario = config.getProperty("usuario");
		Contrasena=config.getProperty("contrasena");
	}





	public Connection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conexion = (Connection) DriverManager.getConnection(url, Usuario, Contrasena);
		} 
		catch (SQLException e) {
			System.err.println("La conexion a MySQL ha fallado!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver no encontrado.");
			e.printStackTrace();
		}
		return this.conexion;
	}

	// We can include here other methods to encapsulate CRUD commands...

	public void closeConnection() {
		try {
			if(this.conexion != null && !this.conexion.isClosed()) {
				this.conexion.close();
			}
		} catch (SQLException e) {
			System.err.println("Error intentando cerrar la conexion.");
			e.printStackTrace();
		}
	}
}
