package Akihabara.view;

import java.util.List;
import java.util.Scanner;

import Akihabara.model.ProductoOtaku;
import Akihabara.model.ClienteOtaku;

/**
 * Clase que maneja la interfaz de usuario en consola para la aplicación.
 * Proporciona métodos para mostrar menús, mensajes y pedir datos al usuario,
 * así como para mostrar información sobre productos.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class InterfazConsola {
	// Mostrar
	public void menuPrincipal() {
		System.out.println("**********************************************************************************");
		System.out.println("1º Menu productos.");
		System.out.println("2º Menu clientes.");
		System.out.println("3º Salir de la aplicación.");
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra el menú principal con las opciones disponibles.
	 */
	public void mostrarMenuProductos() {
		System.out.println("**********************************************************************************");
		System.out.println("1º Añadir nuevo producto.");
		System.out.println("2º Consultar un producto con la ID.");
		System.out.println("3º Ver todos los productos del inventario");
		System.out.println("4º Actualizar la información de un producto existente");
		System.out.println("5º Eliminar un producto del inventario.");
		System.out.println("6º Buscar producto por nombre");
		System.out.println("7º Utilizar Asistente IA");
		System.out.println("8º Salir al menu principal.");
		System.out.println("**********************************************************************************");
	}

	public void mostrarMenuClientes() {
		System.out.println("**********************************************************************************");
		System.out.println("1º Añadir nuevo cliente.");
		System.out.println("2º Consultar cliente por ID.");
		System.out.println("3º Ver todos los clientes registrados.");
		System.out.println("4º Actualizar datos de un cliente.");
		System.out.println("5º Eliminar un cliente del sistema.");
		System.out.println("6º Consultar cliente por Email.");
		System.out.println("7º Salir al menu principal.");
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra un submenú para opciones del asistente IA.
	 */
	public void subMenuIA() {
		System.out.println("1 - Generar descripción de producto");
		System.out.println("2 - Sugerir categoría para nuevo producto");
		System.out.println("3 - Salir al menu producto");
	}

	/**
	 * Muestra un mensaje que indica si un producto fue agregado correctamente o no.
	 *
	 * @param agr true si el producto se agregó correctamente, false en caso
	 *            contrario.
	 */
	public void verficarProductoAgregado(boolean agr) {
		System.out.println("**********************************************************************************");
		if (agr) {
			System.out.println("Producto agregado correctamente  ");
		} else {
			System.out.println("Producto no se agrego correctamente");
		}
		System.out.println("**********************************************************************************");
	}

	public void verficarClienteAgregado(boolean agr) {
		System.out.println("**********************************************************************************");
		if (agr) {
			System.out.println("Cliente agregado correctamente");
		} else {
			System.out.println("Cliente no se agrego correctamente");
		}
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra los detalles de un producto específico.
	 *
	 * @param producto El producto a mostrar. Si es null, muestra un mensaje
	 *                 indicando que no hay productos.
	 */
	public void mostrarUnProducto(ProductoOtaku producto) {
		System.out.println("**********************************************************************************");
		if (producto == null) {
			System.out.println("\nNo hay productos para mostrar");
			System.out.println("**********************************************************************************");
			return;
		}
		System.out.printf(" Nombre   : %-30s\n", producto.getNombre());
		System.out.printf(" Categoría: %-20s\n", producto.getCategoria());
		System.out.printf(" Precio   : %-10.2f €\n", producto.getPrecio());
		System.out.printf(" Stock    : %-5d\n", producto.getStock());
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra los detalles de un cliente sin mostrar su ID.
	 *
	 * @param cliente El cliente a mostrar. Si es null, muestra un mensaje indicando que no hay clientes.
	 */
	public void mostrarUnClienteSinID(ClienteOtaku cliente) {
		System.out.println("**********************************************************************************");
		if (cliente == null) {
			System.out.println("\nNo hay clientes para mostrar");
			System.out.println("**********************************************************************************");
			return;
		}
		System.out.printf(" Nombre         : %-30s\n", cliente.getNombre());
		System.out.printf(" Email          : %-30s\n", cliente.getEmail());
		System.out.printf(" Teléfono       : %-15s\n", cliente.getTelefono());
		System.out.printf(" Fecha Registro : %s\n", cliente.getFechaRegistro());
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra los detalles de un cliente sin mostrar su email.
	 *
	 * @param cliente El cliente a mostrar. Si es null, muestra un mensaje indicando que no hay clientes.
	 */
	public void mostrarUnClienteSinEmail(ClienteOtaku cliente) {
		System.out.println("**********************************************************************************");
		if (cliente == null) {
			System.out.println("\nNo hay clientes para mostrar");
			System.out.println("**********************************************************************************");
			return;
		}
		System.out.printf(" Nombre         : %-30s\n", cliente.getNombre());
		System.out.printf(" Teléfono       : %-15s\n", cliente.getTelefono());
		System.out.printf(" Fecha Registro : %s\n", cliente.getFechaRegistro());
		System.out.println("**********************************************************************************");
	}


	/**
	 * Muestra la lista de todos los productos en formato de tabla.
	 *
	 * @param productos Lista de productos a mostrar. Si es null o vacía, muestra un
	 *                  mensaje indicándolo.
	 */
	public void mostrarTodosLosProductos(List<ProductoOtaku> productos) {
		System.out.println("**********************************************************************************");
		if (productos == null || productos.isEmpty()) {
			System.out.println("\nNo hay productos para mostrar");
			System.out.println("**********************************************************************************");
			return;
		}

		System.out.printf("%-5s | %-30s | %-20s | %-10s | %-6s\n", "ID", "Nombre", "Categoría", "Precio (€)", "Stock");
		System.out.println("----------------------------------------------------------------------------------");

		for (ProductoOtaku p : productos) {
			System.out.printf("%-5d | %-30s | %-20s | %-10.2f | %-6d\n",
					p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock());
		}
		System.out.println("**********************************************************************************");
	}


	/**
	 * Muestra la lista de todos los clientes en formato de tabla.
	 *
	 * @param clientes Lista de clientes a mostrar. Si es null o vacía, muestra un mensaje indicándolo.
	 */
	public void mostrarTodosLosClientes(List<ClienteOtaku> clientes) {
		System.out.println("**********************************************************************************");
		if (clientes == null || clientes.isEmpty()) {
			System.out.println("\nNo hay clientes para mostrar");
			System.out.println("**********************************************************************************");
			return;
		}

		System.out.printf("%-5s | %-25s | %-30s | %-15s | %-20s\n",
				"ID", "Nombre", "Email", "Teléfono", "Fecha Registro");
		System.out.println("---------------------------------------------------------------------------------------------------------------");

		for (ClienteOtaku c : clientes) {
			System.out.printf("%-5d | %-25s | %-30s | %-15s | %-20s\n",
					c.getId(), c.getNombre(), c.getEmail(), c.getTelefono(), c.getFechaRegistro());
		}
		System.out.println("**********************************************************************************");
	}


	/**
	 * Muestra un mensaje indicando si la actualización de un producto fue exitosa o
	 * no.
	 *
	 * @param Ac true si la actualización se hizo correctamente, false en caso
	 *           contrario.
	 */
	public void mostrarActualizacion(boolean Ac) {
		System.out.println("**********************************************************************************");
		if (Ac) {
			System.out.println("Actualicacion hecha correctamente");
		} else {
			System.out.println("Actualicacion no se hizo correctamente");
		}
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra un mensaje indicando si la eliminación de un producto fue exitosa o
	 * no.
	 *
	 * @param El true si la eliminación se hizo correctamente, false en caso
	 *           contrario.
	 */
	public void mostrarEliminar(boolean El) {
		System.out.println("**********************************************************************************");
		if (El) {
			System.out.println("Eliminacion hecha correctamente");
		} else {
			System.out.println("Eliminacion no se hizo correctamente");
		}
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra una descripción generada para un producto.
	 *
	 * @param nombreProducto Nombre del producto.
	 * @param descripcion    Descripción generada para el producto.
	 */
	public void mostrarDescripcionGenerada(String nombreProducto, String descripcion) {
		System.out.println("**********************************************************************************");
		System.out.println("Descripción generada para \"" + nombreProducto + "\":");
		System.out.println(descripcion);
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra la categoría sugerida para un producto.
	 *
	 * @param nombreProducto Nombre del producto.
	 * @param categoria      Categoría sugerida.
	 */
	public void mostrarCategoriaSugerida(String nombreProducto, String categoria) {
		System.out.println("**********************************************************************************");
		System.out.println("Categoría sugerida para \"" + nombreProducto + "\": " + categoria);
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra un mensaje general al usuario.
	 *
	 * @param mensaje Texto del mensaje a mostrar.
	 */
	public void mostrarMensaje(String mensaje) {
		System.out.println("**********************************************************************************");
		System.out.println(mensaje);
		System.out.println("**********************************************************************************");
	}
	
	/**
	 * Muestra un mensaje de error específico cuando el correo electrónico ya está en uso.
	 *
	 * @param mensaje Texto del mensaje de error (actualmente ignorado en la implementación).
	 */
	public void mostrarErrorEmail(String mensaje) {
	    System.out.println("**********************************************************************************");
	    System.out.println("Ese email ya está en uso. Por favor, introduce uno diferente.");
	    System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra mensaje de salida de la aplicación.
	 */
	public void salir() {
		System.out.println("**********************************************************************************");
		System.out.println("Saliendo............");
		System.out.println("**********************************************************************************");
	}

	/**
	 * Muestra mensaje de salida al menú principal.
	 */
	public void salirMenuPrincipal() {
		System.out.println("**********************************************************************************");
		System.out.println("Saliendo al menu principal............");
		System.out.println("**********************************************************************************");
	}
	/**
	 * Muestra mensaje de salida al menu de productos.
	 */
	public void salirIA() {
		System.out.println("**********************************************************************************");
		System.out.println("Saliendo al menu de producto............");
		System.out.println("**********************************************************************************");
	}

	// Meter
	/**
	 * Pide al usuario un número entero con un mensaje dado.
	 *
	 * @param mensaje Texto que se muestra al usuario para pedir el número.
	 * @return El número entero ingresado por el usuario.
	 */
	public static int pedirEntero(String mensaje) {
		Scanner scan = new Scanner(System.in);
		int valor = 0;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje);
			try {
				valor = Integer.parseInt(scan.next());
			} catch (Exception e) {
				System.out.println("[ERROR] Valor incorrecto");
				error = true;
			}
		} while (error);
		return valor;
	}

	/**
	 * Pide al usuario un número entero positivo con un mensaje dado.
	 *
	 * @param mensaje Texto que se muestra al usuario para pedir el número.
	 * @return El número entero positivo ingresado por el usuario.
	 */
	public static int pedirEnteroPositivo(String mensaje) {
		int valor;
		do {
			valor = pedirEntero(mensaje);
			if (valor <= 0) {
				System.out.println("[ERROR] El número debe ser mayor que cero.");
			}
		} while (valor <= 0);
		return valor;
	}

	/**
	 * Pide al usuario un texto con un mensaje dado.
	 *
	 * @param mensaje Texto que se muestra al usuario para pedir el texto.
	 * @return El texto ingresado por el usuario.
	 */
	/**
	 * Pide al usuario un texto con un mensaje dado. No acepta cadenas vacías ni
	 * solo espacios en blanco.
	 *
	 * @param mensaje Texto que se muestra al usuario para pedir el texto.
	 * @return El texto ingresado por el usuario, garantizando que no está vacío ni
	 *         solo espacios.
	 */
	public static String pedirTexto(String mensaje) {
		Scanner scan = new Scanner(System.in);
		String texto;
		do {
			System.out.print(mensaje);
			texto = scan.nextLine().trim(); // quitamos espacios al inicio y final
			if (texto.isEmpty()) {
				System.out.println("[ERROR] El texto no puede estar vacío.");
			}
		} while (texto.isEmpty());
		return texto;
	}

	/**
	 * Pide al usuario un número decimal (double) con un mensaje dado.
	 *
	 * @param mensaje Texto que se muestra al usuario para pedir el número.
	 * @return El número decimal ingresado por el usuario.
	 */
	public static double pedirDouble(String mensaje) {
		Scanner scan = new Scanner(System.in);
		double valor = 0;
		boolean error = false;
		do {
			error = false;
			System.out.print(mensaje);
			try {
				valor = Double.parseDouble(scan.nextLine());
			} catch (Exception e) {
				System.out.println("[ERROR] Valor incorrecto");
				error = true;
			}
		} while (error);
		return valor;
	}
}
