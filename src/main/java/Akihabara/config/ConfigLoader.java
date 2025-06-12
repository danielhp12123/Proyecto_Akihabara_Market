package Akihabara.config;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

/**
 * Clase para cargar y acceder a la configuración de la aplicación. carga las
 * propiedades desde un archivo "config.properties" en el momento de la carga de
 * clase y proporciona métodos estáticos para obtener valores específicos de
 * configuración como la URL de la base de datos, usuario, contraseña y las
 * credenciales para la API externa.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class ConfigLoader {

	private static Properties props = new Properties();

	static {
		try {
			props.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			System.out.println("Error al cargar configuración: " + e.getMessage());
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static String getDbUrl() {
		return get("db.url");
	}

	public static String getDbUser() {
		return get("db.user");
	}

	public static String getDbPassword() {
		return get("db.password");
	}

	public static String getApiKey() {
		return get("openrouter.api.key");
	}

	public static String getApiUrl() {
		return get("openrouter.api.url");
	}
}
