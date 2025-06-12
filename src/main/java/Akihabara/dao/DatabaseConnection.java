package Akihabara.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Akihabara.config.ConfigLoader;
/**
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class DatabaseConnection {
	private static Connection conn = null;
	/**
	 * Constructor que inicializa la conexion 
	 */
	public static Connection connection() {
		if (conn == null) {
			try {
				// driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				// usuario
                String url = ConfigLoader.getDbUrl();
				// contraseña
                String usr = ConfigLoader.getDbUser();
				// base de datos
                String pwd = ConfigLoader.getDbPassword();

				// Conexión a la base de datos
				conn = DriverManager.getConnection(url, usr, pwd);
			} catch (ClassNotFoundException ex) {
				System.out.println(ex);
			} catch (SQLException ex) {
				System.out.println(ex);
			}
			
		}
		return conn;
	}

	/**
	 * Cierra la conexion
	 */
	public void cerrarConexion() {
		try {
			// Cierra la conexion
			conn.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
