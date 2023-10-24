package Datos.Comun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConexionBD {

	protected Connection conexion = null;
	
	protected String url = "jdbc:mysql://oraclepr.uco.es:3306/i12brmac";

	protected String usuario = "i12brmac";

	protected String contrasenia = "PWCamp1";

	public Connection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conexion = (Connection) DriverManager.getConnection(url, usuario, contrasenia);
			System.out.println("¡Conexión a la base de datos con exito!");
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
				System.out.println("¡La conexion a la base de datos se ha cerrado corectamente!");
			}
		} catch (SQLException e) {
			System.err.println("Error intentando cerrar la conexion.");
			e.printStackTrace();
		}
	}
}
