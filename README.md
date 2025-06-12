# Nombre del Proyecto: Akihabara Market

## Descripción

- Este sistema permite gestionar un inventario de productos otaku mediante una aplicación de consola desarrollada en Java. Además, incorpora funciones inteligentes asistidas por un modelo LLM (Large Language Model) para generar descripciones de productos y sugerencias de categorías automáticamente.
	
## Autor

- **Nombre:** Daniel Hernandez
- **GitHub:** [danielhp12123](https://github.com/danielhp12123/Proyecto_Akihabara_Market)

## Tecnologías Utilizadas
- Java 21
- MySQL
- JDBC (Conector MySQL)
- Librería JSON (Gson o Jackson)
- API de OpenRouter (LLM)
- Java Swing
- junit

## Estructura del Proyecto
``` Estructura
Akihabara/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── Akihabara/
│ │ │ ├── MainAPP.java // Controlador principal
│ │ │ ├── config/
│ │ │ │ └── ConfigLoader.java // Carga de configuración
│ │ │ ├── model/
│ │ │ │ ├── ClienteOtaku.java // POJO de cliente
│ │ │ │ └── ProductoOtaku.java // POJO de producto
│ │ │ ├── dao/
│ │ │ │ ├── ClienteDAOInt.java // Interfaz ClienteDAO
│ │ │ │ ├── ClienteDAOImpl.java // Implementación ClienteDAO
│ │ │ │ ├── ProductoDAOInt.java // Interfaz ProductoDAO
│ │ │ │ ├── ProductoDAOImpl.java // Implementación ProductoDAO
│ │ │ │ └── DatabaseConnection.java // Conexión a la base de datos
│ │ │ ├── view/
│ │ │ │ ├── InterfazConsola.java // Vista modo consola
│ │ │ │ └── InterfazGrafica.java // Vista gráfica (Swing, JavaFX, etc.)
│ │ │ ├── services/
│ │ │ │ └── LlmService.java // Lógica de negocio para uso de LLM
│ │ └── sql/
│ │ └── crear_tabla.sql // Script SQL para crear tablas
├── test/
│ └── java/
│ └── Akihabara/test/
│ └── ProductosDAOTest.java // Pruebas para DAO de producto
│
├── config.properties // Configuraciones de conexión u otras
├── pom.xml // Archivo Maven con dependencias
├── README.md // Documentación del proyecto
```

##  Configuración Requerida

## Base de Datos (MySQL)
- 1º necesitamos tener instaldo MYSQL
- 2º Usamos el SQL que se llama crear_tabla.sql o pillamos el siguiente codigo y lo usamos

``` Base de datos
	# drop database if exists akihabara_db;
	Create database akihabara_db;
	Use akihabara_db;
	# drop table if exists productos;
	CREATE TABLE productos (
	    id INT AUTO_INCREMENT PRIMARY KEY,
	    nombre VARCHAR(255) NOT NULL,
	    categoria VARCHAR(100),
	    precio DECIMAL(10, 2),
	    stock INT
	);
	# drop table if exists clientes;
	CREATE TABLE clientes (
	    id INT AUTO_INCREMENT PRIMARY KEY,
	    nombre VARCHAR(255) NOT NULL,
	    email VARCHAR(255) NOT NULL UNIQUE,
	    telefono VARCHAR(20),
	    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	);
	# Insert para productos
	INSERT INTO productos (nombre, categoria, precio,stock) VALUES 
	("Figura de Anya Forger", "Figura", 59.95, 8),
	("Manga Chainsaw Man Vol.1", "Manga", 9.99, 20),
	("Póster Studio Ghibli Colección", "Póster", 15.50, 15);
	# Insert para clientes
	INSERT INTO clientes (nombre, email, telefono) VALUES 
	('Daniel Henandez', 'daniel@campusfp.es', '+34 612 345 678'),
	('Esteban martin', 'Esteban@campusfp.es', '+34 699 123 456'),
	('Mondongo goku', 'Mondongo.goku@example.com', '+34 620 987 654');
```

## config.properties

Hace falta que este en el mismo nivel que el .jar

```properties
# API
openrouter.api.key=Tu_clave
openrouter.api.url=https://openrouter.ai/api/v1/chat/completions
# Base de datos
db.url=jdbc:Tu_conexion
db.user=Tu_nombre_de_usuario
db.password=Tu_contraseña
```

## Compilar y Ejecutar

**Configurar la base de datos**  
   Crea la base de datos y un usuario con contraseña que permita a la aplicación conectarse y operar correctamente.

**Obtener una API Key**  
   Regístrate en [OpenRouter](https://openrouter.ai/) y genera una API Key para usar en el proyecto.

**Crear el archivo `config.properties`**  
   En la raíz del proyecto, crea un archivo `config.properties` con el formato mostrado anteriormente.

**Preparar los archivos ejecutables**  
   Coloca todos los archivos `.jar`, el archivo `config.properties` y los scripts `.bat` en el mismo directorio.

**Contenido del script `.bat`**  
   El archivo `.bat` debe contener la siguiente línea para ejecutar el `.jar`:
   
	```bat
	java -jar Nombre_del_jar.jar
	pause
	```
**Ejecutar la aplicación**  
   Ejecuta el script `.bat` correspondiente para iniciar la aplicación, por ejemplo, haciendo doble clic sobre `Nombre_del_bat.bat` o ejecutándolo desde la consola.

   


## funcionalidades Implementadas
- La funcionalidad de Productos esta totalmente completa
- La funcionalidad del MainAPP esta completa y revisada
- La funcionalidad de Clientes esta totalmente completa
- La funcionalidad de Swing esta totalmente completa y revisada