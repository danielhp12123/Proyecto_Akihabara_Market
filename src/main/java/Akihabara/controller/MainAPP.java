package Akihabara.controller;

import Akihabara.dao.ClienteDAOImpl;
import Akihabara.dao.ProductoDAOImpl;
import Akihabara.model.ClienteOtaku;
import Akihabara.model.ProductoOtaku;
import Akihabara.services.LlmService;
import Akihabara.view.InterfazConsola;

/**
 * Clase principal que ejecuta la aplicación en consola de gestión de productos del
 * inventario y gestion de los clientes de la app.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class MainAPP {
	public static void main(String[] args) {
		// Inicializo los daos
		ProductoDAOImpl daoproducto = new ProductoDAOImpl();
		ClienteDAOImpl daocliente = new ClienteDAOImpl();
		// Inicializo la interfaz
		InterfazConsola interfaz = new InterfazConsola();
		int opcMenuPrincipal = 0;
		while (opcMenuPrincipal != 3) {
			interfaz.menuPrincipal();
			opcMenuPrincipal = InterfazConsola.pedirEnteroPositivo("Dime la opcion: ");
			switch (opcMenuPrincipal) {

			case 1:
				// inicializo opc para meternos en el bucle
				int opcMenuProductos = 0;
				// Inicio del bucle
				while (opcMenuProductos != 8) {
					// Uso interfaz para mostrarMenu
					interfaz.mostrarMenuProductos();
					// uso opcion para el poder meterme en los casos del switch
					opcMenuProductos = InterfazConsola.pedirEnteroPositivo("Dime la opcion: ");
					switch (opcMenuProductos) {
					// caso 1 agregar producto
					case 1:
						interfaz.verficarProductoAgregado(daoproducto.agregarProducto(
								new ProductoOtaku(InterfazConsola.pedirTexto("Dime el nombre del producto: "),
										InterfazConsola.pedirTexto("Dime la categoria del producto: "),
										InterfazConsola.pedirDouble("Dime el precio: "),
										InterfazConsola.pedirEnteroPositivo("Dime el stock del producto: "))));
						break;
					// caso 2 mostrar un producto usando su id
					case 2:
						interfaz.mostrarUnProducto(daoproducto.obtenerProductoPorId(
								InterfazConsola.pedirEnteroPositivo("Dime la id del producto: ")));
						break;
					// caso 3 mostrar todos los productos
					case 3:
						interfaz.mostrarTodosLosProductos(daoproducto.obtenerTodosLosProductos());
						break;
					// caso 4 se hace una actualizacion y se muestra mensaje si se hizo
					// correctamente o no
					case 4:
						interfaz.mostrarActualizacion(daoproducto.actualizarProducto(new ProductoOtaku(
								InterfazConsola
										.pedirEnteroPositivo("Dime el id del producto al que deseas actualizar: "),
								InterfazConsola.pedirTexto(" Dime el nuevo nombre del producto: "),
								InterfazConsola.pedirTexto(" Dime la nueva categoria del producto: "),
								InterfazConsola.pedirDouble(" Dime el nuevo precio: "),
								InterfazConsola.pedirEnteroPositivo(" Dime el nuevo stock del producto: "))));
						break;
					// caso 5 Se elimina el el producto y se mostrara por pantalla si se hizo
					// correctamente o no
					case 5:
						interfaz.mostrarEliminar(daoproducto
								.eliminarProducto(InterfazConsola.pedirEnteroPositivo("Dime el id del producto: ")));
						break;
					// caso 6 muestra todos los productos con un nombre relacionado y los muestra
					// por pantalla
					case 6:
						interfaz.mostrarTodosLosProductos(daoproducto
								.buscarProductosPorNombre(InterfazConsola.pedirTexto("Dime el nombre del producto: ")));
						break;
					// caso 7 asistente IA
					case 7:
						int subOpcIA;
						do {
							// inicio el submenu del asistente de ia
							interfaz.subMenuIA();
							subOpcIA = InterfazConsola.pedirEnteroPositivo("Elige opción: ");

							switch (subOpcIA) {
							// caso 1 generar descripcion usando la ia
							case 1:

								int idProd = InterfazConsola.pedirEnteroPositivo("Dime el id del producto: ");
								ProductoOtaku prod = daoproducto.obtenerProductoPorId(idProd);
								if (prod == null) {
									interfaz.mostrarMensaje("Producto no encontrado.");
								} else {
									String descripcion = LlmService.generarDescripcionProducto(prod.getNombre(),
											prod.getCategoria());
									interfaz.mostrarDescripcionGenerada(prod.getNombre(), descripcion);
								}
								break;
							// caso 2 generar categoria usando la ia
							case 2:

								String nombreNuevoProd = InterfazConsola
										.pedirTexto("Dime el nombre del nuevo producto: ");
								String categoriaSugerida = LlmService.sugerirCategoriaProducto(nombreNuevoProd);
								interfaz.mostrarCategoriaSugerida(nombreNuevoProd, categoriaSugerida);
								break;
							// caso 3 salir del submenu
							case 3:
								interfaz.salirIA();
								break;
							}
						} while (subOpcIA != 3);
						break;
					// caso 8 Salir al menu principal
					case 8:
						interfaz.salirMenuPrincipal();
						break;
					}

				}
				break;
			case 2:
				// inicializo opc para meternos en el bucle
				int opcMenuClientes = 0;
				// Inicio del bucle
				while (opcMenuClientes != 7) {
					// Uso interfaz para mostrarMenu
					interfaz.mostrarMenuClientes();
					// uso opcion para el poder meterme en los casos del switch
					opcMenuClientes = InterfazConsola.pedirEnteroPositivo("Dime la opcion: ");
					switch (opcMenuClientes) {
					// caso 1 agregar Cliente
					case 1:
						interfaz.verficarClienteAgregado(daocliente.agregarCliente(
								new ClienteOtaku(InterfazConsola.pedirTexto("Dime el nombre del cliente: "),
										InterfazConsola.pedirTexto("Dime el email del cliente: "),
										InterfazConsola.pedirTexto("Dime el telefono del cliente: "))));
						break;
					// caso 2 mostrar un Cliente usando su id
					case 2:
						interfaz.mostrarUnClienteSinID(daocliente
								.obtenerClientePorId(InterfazConsola.pedirEnteroPositivo("Dime el id del cliente")));
						break;
					// caso 3 mostrar todos los Clientes
					case 3:
						interfaz.mostrarTodosLosClientes(daocliente.obtenerTodosLosClientes());
						break;
					// caso 4 actualizar un cliente
					case 4:
					    int id = InterfazConsola.pedirEnteroPositivo("Dime el id del cliente: ");
					    String nombre = InterfazConsola.pedirTexto("Dime el nuevo nombre del cliente: ");

					    String email;
					    do {
					        email = InterfazConsola.pedirTexto("Dime el nuevo email del cliente: ");
					        if (daocliente.emailExiste(email, id)) {
					        	interfaz.mostrarErrorEmail(email);
					        }
					    } while (daocliente.emailExiste(email, id));

					    String telefono = InterfazConsola.pedirTexto("Dime el nuevo telefono del cliente: ");

					    ClienteOtaku cliente = new ClienteOtaku(id, nombre, email, telefono);
					    boolean actualizado = daocliente.actualizarCliente(cliente);
					    interfaz.mostrarActualizacion(actualizado);
					    break;

					// caso 5 elimina el cliente
					case 5:
						interfaz.mostrarEliminar(daocliente.eliminarCliente(
								InterfazConsola.pedirEnteroPositivo("Dime la id del cliente que deseas eliminar: ")));
						break;
					// caso 6 mostrar un Cliente usando su email
					case 6:
						interfaz.mostrarUnClienteSinEmail(
								daocliente.buscarPorEmail(InterfazConsola.pedirTexto("Dime el email del cliente: ")));
						break;
					// caso 7 Salir al menu principal
					case 7:
						interfaz.salirMenuPrincipal();
						break;
					}
				}
				break;
			case 3:
				interfaz.salir();
				break;

			}
		}
	}
}