package Akihabara.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Akihabara.dao.ClienteDAOImpl;
import Akihabara.dao.ProductoDAOImpl;
import Akihabara.model.ClienteOtaku;
import Akihabara.model.ProductoOtaku;
import Akihabara.services.LlmService;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class InterfazGrafica extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreAnnadir;
	private JTextField categoriaAnnadir;
	private JTextField precioAnnadir;
	private JTextField stockAnnadir;
	private JTextField zonaID;
	private JTextField zonaNombre;
	private JTextField zonaNomAct;
	private JTextField zonaCatAct;
	private JTextField zonaPreAct;
	private JTextField zonaStockAct;
	private JTextField zonaIDAct;
	private JTextField ZonaIDElim;
	private JTextField zonaIDGenerar;
	private JTextField zonaIDSug;

	public InterfazGrafica() {

		ProductoDAOImpl daoproducto = new ProductoDAOImpl();
		ClienteDAOImpl daocliente = new ClienteDAOImpl();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 456);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JTabbedPane TablaPanelPrincipal = new JTabbedPane(JTabbedPane.TOP);
		TablaPanelPrincipal.setBounds(0, 0, 720, 417);
		contentPane.add(TablaPanelPrincipal);

		// Panel de Inventario
		JPanel inventario = new JPanel();
		inventario.setLayout(null);
		TablaPanelPrincipal.addTab("Inventario", null, inventario, null);

		JTabbedPane inventarioCarpeta = new JTabbedPane(JTabbedPane.TOP);
		inventarioCarpeta.setBounds(0, 0, 715, 389);
		inventario.add(inventarioCarpeta);

		JPanel annadirPanelProductos = new JPanel();
		annadirPanelProductos.setBackground(new Color(255, 255, 255));
		annadirPanelProductos.setForeground(new Color(255, 255, 255));
		annadirPanelProductos.setLayout(null);
		inventarioCarpeta.addTab("AÑADIR", null, annadirPanelProductos, null);

		JTextPane txtpnAnnadir = new JTextPane();
		txtpnAnnadir.setEditable(false);
		txtpnAnnadir.setForeground(new Color(0, 0, 0));
		txtpnAnnadir.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnAnnadir.setText("Añadir un producto");
		txtpnAnnadir.setBounds(245, 4, 204, 33);
		annadirPanelProductos.add(txtpnAnnadir);

		JTextArea txtrNombreDelProducto = new JTextArea();
		txtrNombreDelProducto.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNombreDelProducto.setEditable(false);
		txtrNombreDelProducto.setText("Nombre del producto:");
		txtrNombreDelProducto.setBounds(68, 48, 204, 22);
		annadirPanelProductos.add(txtrNombreDelProducto);

		nombreAnnadir = new JTextField();
		nombreAnnadir.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		nombreAnnadir.setBounds(311, 48, 299, 20);
		annadirPanelProductos.add(nombreAnnadir);
		nombreAnnadir.setColumns(10);

		JTextArea txtrCategoriaDelProducto = new JTextArea();
		txtrCategoriaDelProducto.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrCategoriaDelProducto.setEditable(false);
		txtrCategoriaDelProducto.setText("Categoria del producto:");
		txtrCategoriaDelProducto.setBounds(68, 113, 204, 22);
		annadirPanelProductos.add(txtrCategoriaDelProducto);

		categoriaAnnadir = new JTextField();
		categoriaAnnadir.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		categoriaAnnadir.setColumns(10);
		categoriaAnnadir.setBounds(311, 113, 299, 20);
		annadirPanelProductos.add(categoriaAnnadir);

		JTextArea txtrPrecioDelProducto = new JTextArea();
		txtrPrecioDelProducto.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrPrecioDelProducto.setEditable(false);
		txtrPrecioDelProducto.setText("Precio del producto:");
		txtrPrecioDelProducto.setBounds(68, 173, 204, 22);
		annadirPanelProductos.add(txtrPrecioDelProducto);

		precioAnnadir = new JTextField();
		precioAnnadir.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		precioAnnadir.setColumns(10);
		precioAnnadir.setBounds(311, 171, 299, 20);
		annadirPanelProductos.add(precioAnnadir);

		JTextArea txtrStockDelProducto = new JTextArea();
		txtrStockDelProducto.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrStockDelProducto.setEditable(false);
		txtrStockDelProducto.setText("Stock del producto:");
		txtrStockDelProducto.setBounds(68, 226, 204, 22);
		annadirPanelProductos.add(txtrStockDelProducto);

		stockAnnadir = new JTextField();
		stockAnnadir.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		stockAnnadir.setColumns(10);
		stockAnnadir.setBounds(311, 226, 299, 20);
		annadirPanelProductos.add(stockAnnadir);

		// Crear un botón con el texto "Añadir producto"
		JButton btnAnnadir = new JButton("Añadir producto");
		// Establecer la fuente del texto del botón
		btnAnnadir.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		// Agregar un ActionListener para definir qué sucede cuando se hace clic en el
		// botón
		btnAnnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el texto ingresado en los campos de nombre, categoría, precio y stock
				String nombreTexto = nombreAnnadir.getText();
				String categoriaTexto = categoriaAnnadir.getText();
				String precioTexto = precioAnnadir.getText();
				String stockTexto = stockAnnadir.getText();
				// Verificar si alguno de los campos está vacío
				if (nombreTexto.trim().isEmpty() || categoriaTexto.trim().isEmpty() || precioTexto.trim().isEmpty()
						|| stockTexto.trim().isEmpty()) {

					// Mostrar un mensaje de advertencia si hay campos vacíos
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
					return; // Salir del método
				}
				try {
					// Convertir el precio a tipo double y el stock a tipo int
					double precio = Double.parseDouble(precioTexto);
					int stock = Integer.parseInt(stockTexto);

					// Crear un nuevo objeto ProductoOtaku con los datos ingresados
					ProductoOtaku producto = new ProductoOtaku(nombreTexto, categoriaTexto, precio, stock);

					// Llamar al método para agregar el producto a través del DAO (data access
					// object)
					boolean exito = daoproducto.agregarProducto(producto);

					// Mostrar mensaje según si se agregó correctamente o no
					if (exito) {
						JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar el producto.");
					}
				} catch (NumberFormatException ex) {
					// Mostrar mensaje si los datos numéricos no tienen el formato correcto
					JOptionPane.showMessageDialog(null,
							"Precio debe ser decimal (double) y stock debe ser entero (int).");
				}
			}
		});

		btnAnnadir.setBounds(245, 279, 158, 46);
		annadirPanelProductos.add(btnAnnadir);

		JPanel consultarPanelProductos = new JPanel();
		consultarPanelProductos.setBackground(new Color(255, 255, 255));
		consultarPanelProductos.setForeground(new Color(255, 255, 255));
		inventarioCarpeta.addTab("CONSULTAR", null, consultarPanelProductos, null);
		consultarPanelProductos.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 710, 361);
		consultarPanelProductos.add(tabbedPane);

		JPanel consultarID = new JPanel();
		consultarID.setBackground(new Color(255, 255, 255));
		consultarID.setForeground(new Color(255, 255, 255));
		tabbedPane.addTab("Consultar por ID", null, consultarID, null);
		consultarID.setLayout(null);

		JTextPane idBuscar = new JTextPane();
		idBuscar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		idBuscar.setText("Dime el ID del producto: ");
		idBuscar.setBounds(132, 78, 222, 27);
		consultarID.add(idBuscar);

		zonaID = new JTextField();
		zonaID.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaID.setBounds(380, 78, 107, 27);
		consultarID.add(zonaID);
		zonaID.setColumns(10);

		JTextPane txtpnConsultarProductoPor = new JTextPane();
		txtpnConsultarProductoPor.setEditable(false);
		txtpnConsultarProductoPor.setText("Consultar Producto por id");
		txtpnConsultarProductoPor.setForeground(Color.BLACK);
		txtpnConsultarProductoPor.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnConsultarProductoPor.setBounds(196, 11, 277, 33);
		consultarID.add(txtpnConsultarProductoPor);

		// Crear un botón con el texto "Consultar ID"
		JButton btnconsultarID = new JButton("Consultar ID");
		// Agregar un ActionListener para definir la acción al hacer clic en el botón
		btnconsultarID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el texto ingresado en el campo de texto zonaID
				String idTexto = zonaID.getText();
				// Verificar si el campo está vacío
				if (idTexto.isEmpty()) {
					// Mostrar mensaje solicitando que se ingrese un ID
					JOptionPane.showMessageDialog(null, "Por favor, introduzca un ID.");
					return; // Salir del método
				}
				try {
					// Intentar convertir el texto a un número entero (ID)
					int id = Integer.parseInt(idTexto);

					// Obtener el producto correspondiente al ID usando el DAO
					ProductoOtaku producto = daoproducto.obtenerProductoPorId(id);

					// Verificar si el producto existe
					if (producto != null) {
						// Crear un mensaje con los datos del producto
						String mensaje = "Nombre: " + producto.getNombre() + " Categoria: " + producto.getCategoria()
								+ " Precio: " + producto.getPrecio() + " Stock: " + producto.getStock();
						// Mostrar el mensaje con los datos del producto
						JOptionPane.showMessageDialog(null, mensaje);
					} else {
						// Mostrar mensaje si no se encontró un producto con ese ID
						JOptionPane.showMessageDialog(null, "No se encontró un producto con el ID: " + id);
					}

				} catch (NumberFormatException ex) {
					// Mostrar mensaje si el ID no es un número entero válido
					JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
				}
			}
		});

		btnconsultarID.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnconsultarID.setBounds(273, 142, 117, 49);
		consultarID.add(btnconsultarID);

		JPanel consultarNombre = new JPanel();
		consultarNombre.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Consultar por Nombre", null, consultarNombre, null);
		consultarNombre.setLayout(null);

		JTextPane txtpnDimeElNombre = new JTextPane();
		txtpnDimeElNombre.setEditable(false);
		txtpnDimeElNombre.setText("Dime el nombre:");
		txtpnDimeElNombre.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnDimeElNombre.setBounds(155, 89, 184, 27);
		consultarNombre.add(txtpnDimeElNombre);

		zonaNombre = new JTextField();
		zonaNombre.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaNombre.setColumns(10);
		zonaNombre.setBounds(349, 89, 164, 27);
		consultarNombre.add(zonaNombre);

		JTextPane txtpnConsultarListaDe = new JTextPane();
		txtpnConsultarListaDe.setEditable(false);
		txtpnConsultarListaDe.setText("Consultar productos por nombre");
		txtpnConsultarListaDe.setForeground(Color.BLACK);
		txtpnConsultarListaDe.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnConsultarListaDe.setBounds(190, 11, 348, 33);
		consultarNombre.add(txtpnConsultarListaDe);

		// Crear un botón con el texto "Consultar Nombre"
		JButton btnconsultarNombre = new JButton("Consultar Nombre");
		// Añadir un ActionListener para definir la acción al pulsar el botón
		btnconsultarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el texto ingresado en el campo zonaNombre
				String nombreTexto = zonaNombre.getText();

				// Verificar si el campo está vacío o sólo contiene espacios
				if (nombreTexto.trim().isEmpty()) {
					// Mostrar mensaje pidiendo que se introduzca un nombre para buscar
					JOptionPane.showMessageDialog(null, "Por favor, introduzca un nombre para buscar.");
					return; // Salir del método si el campo está vacío
				}

				// Buscar en la base de datos o lista los productos que coincidan con el nombre
				// ingresado
				List<ProductoOtaku> productos = daoproducto.buscarProductosPorNombre(nombreTexto);

				// Verificar si la lista de productos está vacía
				if (productos.isEmpty()) {
					// Mostrar mensaje indicando que no se encontraron productos con ese nombre
					JOptionPane.showMessageDialog(null, "No se encontraron productos con ese nombre.");
				} else {
					// Definir los nombres de las columnas para mostrar en la tabla
					String[] columnas = { "ID", "Nombre", "Categoría", "Precio", "Stock" };

					// Crear una matriz de objetos para almacenar los datos de los productos
					Object[][] datos = new Object[productos.size()][5];

					// Llenar la matriz con los datos de cada producto
					for (int i = 0; i < productos.size(); i++) {
						ProductoOtaku p = productos.get(i);
						datos[i][0] = p.getId();
						datos[i][1] = p.getNombre();
						datos[i][2] = p.getCategoria();
						datos[i][3] = p.getPrecio();
						datos[i][4] = p.getStock();
					}
					// Crear una tabla JTable con los datos y las columnas definidas
					JTable tabla = new JTable(datos, columnas);
					tabla.setEnabled(false); // Hacer la tabla solo de lectura

					// Ajustar el ancho de las columnas para mejor visualización
					tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
					tabla.getColumnModel().getColumn(1).setPreferredWidth(150);

					// Poner la tabla dentro de un JScrollPane para poder desplazarse si hay muchos
					// datos
					JScrollPane scrollPane = new JScrollPane(tabla);
					scrollPane.setPreferredSize(new Dimension(600, 200)); // Definir tamaño preferido del scroll

					// Mostrar la tabla en un cuadro de diálogo (JOptionPane)
					JOptionPane.showMessageDialog(null, scrollPane, "Resultados de búsqueda",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnconsultarNombre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnconsultarNombre.setBounds(279, 152, 151, 49);
		consultarNombre.add(btnconsultarNombre);

		JPanel mostrarTodoPanelProductos = new JPanel();
		mostrarTodoPanelProductos.setBackground(new Color(255, 255, 255));
		mostrarTodoPanelProductos.setForeground(new Color(255, 255, 255));
		inventarioCarpeta.addTab("MOSTRAR TODO", null, mostrarTodoPanelProductos, null);
		mostrarTodoPanelProductos.setLayout(null);

		JTable tablaProductos = new JTable();
		JScrollPane scrollPane = new JScrollPane(tablaProductos);
		scrollPane.setBounds(32, 11, 650, 300);
		mostrarTodoPanelProductos.add(scrollPane);

		// Crear un botón con el texto "Recargar"
		JButton btnRecargar = new JButton("Recargar");

		// Establecer la fuente del texto del botón
		btnRecargar.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		// Establecer la posición y tamaño del botón dentro del panel
		btnRecargar.setBounds(276, 322, 120, 30);

		// Agregar el botón al panel que muestra todos los productos
		mostrarTodoPanelProductos.add(btnRecargar);

		// Definir un método (Runnable) para cargar los datos de los productos y
		// actualizar la tabla
		Runnable cargarDatos = () -> {

			// Obtener la lista completa de productos desde la fuente de datos (DAO)
			List<ProductoOtaku> productos = daoproducto.obtenerTodosLosProductos();

			// Definir los nombres de las columnas para la tabla
			String[] columnas = { "ID", "Nombre", "Categoría", "Precio", "Stock" };

			// Crear una matriz para almacenar los datos de los productos
			Object[][] datos = new Object[productos.size()][5];

			// Recorrer la lista de productos y llenar la matriz con sus datos
			for (int i = 0; i < productos.size(); i++) {
				ProductoOtaku p = productos.get(i);
				datos[i][0] = p.getId();
				datos[i][1] = p.getNombre();
				datos[i][2] = p.getCategoria();
				datos[i][3] = p.getPrecio();
				datos[i][4] = p.getStock();
			}

			// Actualizar el modelo de la tabla con los nuevos datos y columnas
			tablaProductos.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));

			// Deshabilitar la tabla para que sea solo de lectura
			tablaProductos.setEnabled(false);
		};

		// Ejecutar la carga inicial de datos para mostrar al abrir la interfaz
		cargarDatos.run();

		// Asociar la acción de hacer clic en el botón "Recargar" para volver a cargar
		// los datos
		btnRecargar.addActionListener(e -> cargarDatos.run());

		JPanel actualizarPanelProductos = new JPanel();
		actualizarPanelProductos.setBackground(new Color(255, 255, 255));
		actualizarPanelProductos.setForeground(new Color(255, 255, 255));
		inventarioCarpeta.addTab("ACTUALIZAR", null, actualizarPanelProductos, null);
		actualizarPanelProductos.setLayout(null);

		JTextPane txtpnActualizarUnProducto = new JTextPane();
		txtpnActualizarUnProducto.setEditable(false);
		txtpnActualizarUnProducto.setText("Actualizar un producto");
		txtpnActualizarUnProducto.setForeground(Color.BLACK);
		txtpnActualizarUnProducto.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnActualizarUnProducto.setBounds(217, 0, 245, 33);
		actualizarPanelProductos.add(txtpnActualizarUnProducto);

		JTextArea txtrNuevoNombreDel = new JTextArea();
		txtrNuevoNombreDel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNuevoNombreDel.setText("Nuevo nombre del producto:");
		txtrNuevoNombreDel.setEditable(false);
		txtrNuevoNombreDel.setBounds(69, 116, 220, 22);
		actualizarPanelProductos.add(txtrNuevoNombreDel);

		zonaNomAct = new JTextField();
		zonaNomAct.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		zonaNomAct.setColumns(10);
		zonaNomAct.setBounds(328, 118, 299, 20);
		actualizarPanelProductos.add(zonaNomAct);

		JTextArea txtrNuevaCategoriaDel = new JTextArea();
		txtrNuevaCategoriaDel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNuevaCategoriaDel.setText("Nueva categoria del producto:");
		txtrNuevaCategoriaDel.setEditable(false);
		txtrNuevaCategoriaDel.setBounds(69, 149, 220, 22);
		actualizarPanelProductos.add(txtrNuevaCategoriaDel);

		zonaCatAct = new JTextField();
		zonaCatAct.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		zonaCatAct.setColumns(10);
		zonaCatAct.setBounds(328, 151, 299, 20);
		actualizarPanelProductos.add(zonaCatAct);

		JTextArea txtrNuevoPrecioDel = new JTextArea();
		txtrNuevoPrecioDel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNuevoPrecioDel.setText("Nuevo precio del producto:");
		txtrNuevoPrecioDel.setEditable(false);
		txtrNuevoPrecioDel.setBounds(69, 182, 220, 22);
		actualizarPanelProductos.add(txtrNuevoPrecioDel);

		zonaPreAct = new JTextField();
		zonaPreAct.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		zonaPreAct.setColumns(10);
		zonaPreAct.setBounds(328, 184, 299, 20);
		actualizarPanelProductos.add(zonaPreAct);

		JTextArea txtrNuevoStockDel = new JTextArea();
		txtrNuevoStockDel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNuevoStockDel.setText("Nuevo stock del producto:");
		txtrNuevoStockDel.setEditable(false);
		txtrNuevoStockDel.setBounds(69, 215, 220, 22);
		actualizarPanelProductos.add(txtrNuevoStockDel);

		zonaStockAct = new JTextField();
		zonaStockAct.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		zonaStockAct.setColumns(10);
		zonaStockAct.setBounds(328, 217, 299, 20);
		actualizarPanelProductos.add(zonaStockAct);

		// Crear un botón con el texto "Actualizar producto"
		JButton btnActualizar = new JButton("Actualizar producto");
		// Establecer la fuente del texto del botón
		btnActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		// Añadir un ActionListener para definir la acción al hacer clic en el botón
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Validar que ninguno de los campos esté vacío (ID, nombre, categoría, precio,
					// stock)
					if (zonaIDAct.getText().trim().isEmpty() || zonaNomAct.getText().trim().isEmpty()
							|| zonaCatAct.getText().trim().isEmpty() || zonaPreAct.getText().trim().isEmpty()
							|| zonaStockAct.getText().trim().isEmpty()) {
						// Mostrar mensaje si falta completar algún campo
						JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos.");
						return; // Salir del método para evitar continuar con datos incompletos
					}

					// Convertir los campos de texto a los tipos numéricos correspondientes
					int id = Integer.parseInt(zonaIDAct.getText().trim());
					String nombre = zonaNomAct.getText().trim();
					String categoria = zonaCatAct.getText().trim();
					double precio = Double.parseDouble(zonaPreAct.getText().trim());
					int stock = Integer.parseInt(zonaStockAct.getText().trim());

					// Crear un objeto ProductoOtaku con los datos para actualizar
					ProductoOtaku producto = new ProductoOtaku(id, nombre, categoria, precio, stock);

					// Intentar actualizar el producto usando el método del DAO
					boolean actualizado = daoproducto.actualizarProducto(producto);

					// Mostrar mensaje según si la actualización fue exitosa o no
					if (actualizado) {
						JOptionPane.showMessageDialog(null, "Producto actualizado con éxito.");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto. Verifica el ID.");
					}
				} catch (NumberFormatException ex) {
					// Capturar error en formato numérico (ID, precio o stock mal formateados)
					JOptionPane.showMessageDialog(null, "Error en formato numérico. Revisa el ID, precio o stock.");
				} catch (Exception ex) {
					// Capturar cualquier otro error inesperado y mostrar su mensaje
					JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage());
				}
			}
		});

		btnActualizar.setBounds(262, 275, 179, 46);
		actualizarPanelProductos.add(btnActualizar);

		JTextArea txtrNombreDelProducto_1_1 = new JTextArea();
		txtrNombreDelProducto_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNombreDelProducto_1_1.setText("id del producto:");
		txtrNombreDelProducto_1_1.setEditable(false);
		txtrNombreDelProducto_1_1.setBounds(69, 84, 220, 22);
		actualizarPanelProductos.add(txtrNombreDelProducto_1_1);

		zonaIDAct = new JTextField();
		zonaIDAct.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		zonaIDAct.setColumns(10);
		zonaIDAct.setBounds(328, 86, 299, 20);
		actualizarPanelProductos.add(zonaIDAct);

		JPanel eliminarPanelProductos = new JPanel();
		eliminarPanelProductos.setBackground(new Color(255, 255, 255));
		eliminarPanelProductos.setForeground(new Color(255, 255, 255));
		inventarioCarpeta.addTab("ELIMINAR", null, eliminarPanelProductos, null);
		eliminarPanelProductos.setLayout(null);

		JTextPane idBuscar_1 = new JTextPane();
		idBuscar_1.setText("Dime el ID del producto: ");
		idBuscar_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		idBuscar_1.setBounds(133, 92, 203, 27);
		eliminarPanelProductos.add(idBuscar_1);

		ZonaIDElim = new JTextField();
		ZonaIDElim.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		ZonaIDElim.setColumns(10);
		ZonaIDElim.setBounds(349, 92, 147, 27);
		eliminarPanelProductos.add(ZonaIDElim);

		JTextPane txtpnEliminarProducto = new JTextPane();
		txtpnEliminarProducto.setEditable(false);
		txtpnEliminarProducto.setText("Eliminar producto");
		txtpnEliminarProducto.setForeground(Color.BLACK);
		txtpnEliminarProducto.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnEliminarProducto.setBounds(249, 11, 183, 33);
		eliminarPanelProductos.add(txtpnEliminarProducto);
		// Crear un botón con el texto "Eliminar producto"
		JButton btnconsultarID_1 = new JButton("Eliminar producto");

		// Añadir un ActionListener para definir la acción al hacer clic en el botón
		btnconsultarID_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Obtener el texto del campo donde se ingresa el ID para eliminar y eliminar
					// espacios en blanco
					String idTexto = ZonaIDElim.getText().trim();

					// Verificar si el campo está vacío
					if (idTexto.isEmpty()) {
						// Mostrar mensaje solicitando que se ingrese un ID
						JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID.");
						return; // Salir del método para no continuar con ID vacío
					}

					// Convertir el texto a un número entero (ID)
					int id = Integer.parseInt(idTexto);

					// Mostrar cuadro de confirmación para que el usuario confirme la eliminación
					int confirm = JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar el producto con ID " + id + "?",
							"Confirmar eliminación", JOptionPane.YES_NO_OPTION);

					// Si el usuario confirma (clic en "Sí")
					if (confirm == JOptionPane.YES_OPTION) {
						// Llamar al método DAO para eliminar el producto por ID
						boolean eliminado = daoproducto.eliminarProducto(id);

						// Mostrar mensaje según si la eliminación fue exitosa o no
						if (eliminado) {
							JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
						} else {
							JOptionPane.showMessageDialog(null, "No se encontró producto con ese ID.");
						}
					}
				} catch (NumberFormatException ex) {
					// Mostrar mensaje si el ID no es un número válido
					JOptionPane.showMessageDialog(null, "ID inválido. Debe ser un número.");
				} catch (Exception ex) {
					// Capturar y mostrar cualquier otro error inesperado
					JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + ex.getMessage());
				}
			}
		});

		btnconsultarID_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnconsultarID_1.setBounds(276, 165, 183, 49);
		eliminarPanelProductos.add(btnconsultarID_1);

		JPanel assistenteIAProductos = new JPanel();
		assistenteIAProductos.setBackground(new Color(255, 255, 255));
		assistenteIAProductos.setForeground(new Color(255, 255, 255));
		inventarioCarpeta.addTab("ASSISTENTE IA", null, assistenteIAProductos, null);
		assistenteIAProductos.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 0, 710, 361);
		assistenteIAProductos.add(tabbedPane_1);

		JPanel generarDescripcion = new JPanel();
		generarDescripcion.setBackground(new Color(255, 255, 255));
		tabbedPane_1.addTab("Generar descripcion", null, generarDescripcion, null);
		generarDescripcion.setLayout(null);

		JTextPane txtpnGenerarDescripcion = new JTextPane();
		txtpnGenerarDescripcion.setEditable(false);
		txtpnGenerarDescripcion.setText("Generar Descripcion del producto");
		txtpnGenerarDescripcion.setForeground(Color.BLACK);
		txtpnGenerarDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnGenerarDescripcion.setBounds(168, 11, 338, 33);
		generarDescripcion.add(txtpnGenerarDescripcion);

		// Crear un botón con el texto "Generar descripcion"
		JButton btnGenerarDescripcion = new JButton("Generar descripcion");
		// Añadir un ActionListener para definir la acción cuando se hace clic en el
		// botón
		btnGenerarDescripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Obtener el texto ingresado en el campo zonaIDGenerar y eliminar espacios en
					// blanco
					String idTexto = zonaIDGenerar.getText().trim();

					// Verificar si el campo está vacío
					if (idTexto.isEmpty()) {
						// Mostrar mensaje solicitando que se ingrese un ID
						JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID.");
						return; // Salir del método para no continuar con campo vacío
					}

					// Convertir el texto a un número entero (ID)
					int id = Integer.parseInt(idTexto);

					// Obtener el producto correspondiente al ID mediante el DAO
					ProductoOtaku producto = daoproducto.obtenerProductoPorId(id);

					// Verificar si el producto existe
					if (producto != null) {
						// Generar una descripción del producto usando un servicio externo (LlmService)
						String descripcion = LlmService.generarDescripcionProducto(producto.getNombre(),
								producto.getCategoria());

						// Mostrar la descripción generada en un cuadro de diálogo
						JOptionPane.showMessageDialog(null, "Descripción generada:\n" + descripcion);
					} else {
						// Mostrar mensaje si no se encontró un producto con ese ID
						JOptionPane.showMessageDialog(null, "No se encontró un producto con ese ID.");
					}
				} catch (NumberFormatException ex) {
					// Mostrar mensaje si el ID no es un número válido
					JOptionPane.showMessageDialog(null, "ID inválido. Asegúrate de que sea un número.");
				} catch (Exception ex) {
					// Mostrar mensaje para cualquier otro error inesperado
					JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage());
				}
			}
		});

		btnGenerarDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnGenerarDescripcion.setBounds(261, 152, 163, 38);
		generarDescripcion.add(btnGenerarDescripcion);

		JTextPane idBuscar_2 = new JTextPane();
		idBuscar_2.setText("Dime el ID del producto: ");
		idBuscar_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		idBuscar_2.setBounds(158, 90, 195, 27);
		generarDescripcion.add(idBuscar_2);

		zonaIDGenerar = new JTextField();
		zonaIDGenerar.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaIDGenerar.setColumns(10);
		zonaIDGenerar.setBounds(369, 90, 107, 27);
		generarDescripcion.add(zonaIDGenerar);

		JPanel suguerirCategoria = new JPanel();
		suguerirCategoria.setBackground(new Color(255, 255, 255));
		tabbedPane_1.addTab("Suguerir categoria", null, suguerirCategoria, null);
		suguerirCategoria.setLayout(null);

		JTextPane txtpnSuguerirCategoriaDel = new JTextPane();
		txtpnSuguerirCategoriaDel.setText("Suguerir Categoria del producto");
		txtpnSuguerirCategoriaDel.setForeground(Color.BLACK);
		txtpnSuguerirCategoriaDel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnSuguerirCategoriaDel.setBounds(170, 11, 338, 33);
		suguerirCategoria.add(txtpnSuguerirCategoriaDel);

		// Crear un botón con el texto "Suguerir categoria"
		JButton btnSuguerirCategoria = new JButton("Suguerir categoria");
		// Añadir un ActionListener para definir la acción cuando se presiona el botón
		btnSuguerirCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Obtener el texto ingresado en el campo zonaIDSug y eliminar espacios en
					// blanco
					String nombre = zonaIDSug.getText().trim();

					// Verificar si el campo está vacío
					if (nombre.isEmpty()) {
						// Mostrar un mensaje solicitando ingresar el nombre del producto
						JOptionPane.showMessageDialog(null, "Por favor, ingresa el nombre del producto.");
						return; // Salir del método para no continuar con campo vacío
					}

					// Llamar al servicio externo LlmService para sugerir una categoría basada en el
					// nombre del producto
					String categoriaSugerida = LlmService.sugerirCategoriaProducto(nombre);

					// Mostrar la categoría sugerida en un cuadro de diálogo
					JOptionPane.showMessageDialog(null, "Categoría sugerida: " + categoriaSugerida);
				} catch (Exception ex) {
					// Capturar y mostrar cualquier error inesperado ocurrido durante la operación
					JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage());
				}
			}
		});

		btnSuguerirCategoria.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnSuguerirCategoria.setBounds(268, 150, 163, 38);
		suguerirCategoria.add(btnSuguerirCategoria);

		JTextPane idBuscar_2_1 = new JTextPane();
		idBuscar_2_1.setText("Dime el nombre del producto: ");
		idBuscar_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		idBuscar_2_1.setBounds(130, 78, 233, 27);
		suguerirCategoria.add(idBuscar_2_1);

		zonaIDSug = new JTextField();
		zonaIDSug.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaIDSug.setColumns(10);
		zonaIDSug.setBounds(415, 78, 107, 27);
		suguerirCategoria.add(zonaIDSug);

		// Panel de Clientes
		JPanel clientesCarpeta = new JPanel();
		clientesCarpeta.setLayout(null);
		TablaPanelPrincipal.addTab("Clientes", null, clientesCarpeta, null);

		JTabbedPane clientesTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		clientesTabbedPane.setBounds(0, 0, 715, 389);
		clientesCarpeta.add(clientesTabbedPane);

		// --- AÑADIR CLIENTES ---
		JPanel annadirPanelClientes = new JPanel();
		annadirPanelClientes.setBackground(new Color(255, 255, 255));
		annadirPanelClientes.setForeground(new Color(0, 0, 0));
		annadirPanelClientes.setLayout(null);
		clientesTabbedPane.addTab("AÑADIR", null, annadirPanelClientes, null);

		JTextPane txtpnAnnadirCliente = new JTextPane();
		txtpnAnnadirCliente.setEditable(false);
		txtpnAnnadirCliente.setForeground(new Color(0, 0, 0));
		txtpnAnnadirCliente.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnAnnadirCliente.setText("Añadir un cliente");
		txtpnAnnadirCliente.setBounds(245, 4, 204, 33);
		annadirPanelClientes.add(txtpnAnnadirCliente);

		JTextArea txtrNombreCliente = new JTextArea();
		txtrNombreCliente.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrNombreCliente.setEditable(false);
		txtrNombreCliente.setText("Nombre del cliente:");
		txtrNombreCliente.setBounds(68, 48, 204, 22);
		annadirPanelClientes.add(txtrNombreCliente);

		JTextField nombreAnnadirCliente = new JTextField();
		nombreAnnadirCliente.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		nombreAnnadirCliente.setBounds(311, 48, 299, 20);
		annadirPanelClientes.add(nombreAnnadirCliente);
		nombreAnnadirCliente.setColumns(10);

		JTextArea txtrEmailCliente = new JTextArea();
		txtrEmailCliente.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrEmailCliente.setEditable(false);
		txtrEmailCliente.setText("Email del cliente:");
		txtrEmailCliente.setBounds(68, 113, 204, 22);
		annadirPanelClientes.add(txtrEmailCliente);

		JTextField emailAnnadirCliente = new JTextField();
		emailAnnadirCliente.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		emailAnnadirCliente.setColumns(10);
		emailAnnadirCliente.setBounds(311, 113, 299, 20);
		annadirPanelClientes.add(emailAnnadirCliente);

		JTextArea txtrTelefonoCliente = new JTextArea();
		txtrTelefonoCliente.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtrTelefonoCliente.setEditable(false);
		txtrTelefonoCliente.setText("Teléfono del cliente:");
		txtrTelefonoCliente.setBounds(68, 173, 204, 22);
		annadirPanelClientes.add(txtrTelefonoCliente);

		JTextField telefonoAnnadirCliente = new JTextField();
		telefonoAnnadirCliente.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		telefonoAnnadirCliente.setColumns(10);
		telefonoAnnadirCliente.setBounds(311, 171, 299, 20);
		annadirPanelClientes.add(telefonoAnnadirCliente);

		// Crear un botón con el texto "Añadir cliente"
		JButton btnAnnadirCliente = new JButton("Añadir cliente");
		// Establecer la fuente del texto del botón
		btnAnnadirCliente.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		// Añadir un ActionListener para definir la acción al hacer clic en el botón
		btnAnnadirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener los textos de los campos para nombre, email y teléfono, eliminando
				// espacios en blanco
				String nombreTexto = nombreAnnadirCliente.getText().trim();
				String emailTexto = emailAnnadirCliente.getText().trim();
				String telefonoTexto = telefonoAnnadirCliente.getText().trim();

				// Verificar que ningún campo esté vacío
				if (nombreTexto.isEmpty() || emailTexto.isEmpty() || telefonoTexto.isEmpty()) {
					// Mostrar mensaje si falta completar algún campo
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
					return; // Salir para evitar agregar cliente con campos incompletos
				}

				// Crear un nuevo objeto ClienteOtaku con los datos ingresados
				ClienteOtaku nuevoCliente = new ClienteOtaku(nombreTexto, emailTexto, telefonoTexto);

				// Intentar agregar el cliente usando el método del DAO
				boolean exito = daocliente.agregarCliente(nuevoCliente);

				// Mostrar mensaje según si se agregó correctamente o hubo error
				if (exito) {
					JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
					// Opcional: limpiar los campos de texto para permitir agregar otro cliente
					// fácilmente
					nombreAnnadirCliente.setText("");
					emailAnnadirCliente.setText("");
					telefonoAnnadirCliente.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Error al agregar cliente.");
				}
			}
		});

		btnAnnadirCliente.setBounds(245, 279, 158, 46);
		annadirPanelClientes.add(btnAnnadirCliente);

		JPanel consultarPanelClientes = new JPanel();
		consultarPanelClientes.setBackground(new Color(255, 255, 255));
		clientesTabbedPane.addTab("CONSULTAR", null, consultarPanelClientes, null);
		consultarPanelClientes.setLayout(null);

		JTabbedPane tabbedPaneClientes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneClientes.setBounds(0, 0, 710, 361);
		consultarPanelClientes.add(tabbedPaneClientes);

		// --- Consultar por ID ---
		JPanel consultarIDClientes = new JPanel();
		consultarIDClientes.setBackground(new Color(255, 255, 255));
		tabbedPaneClientes.addTab("Consultar por ID", null, consultarIDClientes, null);
		consultarIDClientes.setLayout(null);

		JTextPane idBuscarCliente = new JTextPane();
		idBuscarCliente.setEditable(false);
		idBuscarCliente.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		idBuscarCliente.setText("Dime el ID del cliente: ");
		idBuscarCliente.setBounds(132, 78, 222, 27);
		consultarIDClientes.add(idBuscarCliente);

		JTextField zonaIDCliente = new JTextField();
		zonaIDCliente.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaIDCliente.setEditable(false);
		zonaIDCliente.setBounds(380, 78, 107, 27);
		consultarIDClientes.add(zonaIDCliente);
		zonaIDCliente.setColumns(10);

		JTextPane txtpnConsultarClientePorId = new JTextPane();
		txtpnConsultarClientePorId.setEditable(false);
		txtpnConsultarClientePorId.setText("Consultar Cliente por ID");
		txtpnConsultarClientePorId.setForeground(Color.BLACK);
		txtpnConsultarClientePorId.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnConsultarClientePorId.setBounds(196, 11, 277, 33);
		consultarIDClientes.add(txtpnConsultarClientePorId);

		// Crear un botón con el texto "Consultar ID"
		JButton btnConsultarIDCliente = new JButton("Consultar ID");
		// Establecer la fuente y el tamaño del texto del botón
		btnConsultarIDCliente.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		// Posicionar y dimensionar el botón dentro del panel consultarIDClientes
		btnConsultarIDCliente.setBounds(273, 142, 117, 49);
		consultarIDClientes.add(btnConsultarIDCliente);
		// Añadir un ActionListener para definir la acción al hacer clic en el botón
		btnConsultarIDCliente.addActionListener(e -> {
			// Obtener el texto ingresado en el campo zonaIDCliente (donde se escribe el ID)
			String idTexto = zonaIDCliente.getText();

			// Verificar si el campo está vacío
			if (idTexto.isEmpty()) {
				// Mostrar mensaje solicitando que se introduzca un ID
				JOptionPane.showMessageDialog(null, "Por favor, introduzca un ID.");
				return; // Salir para evitar continuar con campo vacío
			}

			try {
				// Intentar convertir el texto a un número entero (ID)
				int id = Integer.parseInt(idTexto);

				// Buscar el cliente en la base de datos usando el DAO y el ID ingresado
				ClienteOtaku cliente = daocliente.obtenerClientePorId(id);

				// Comprobar si se encontró un cliente con ese ID
				if (cliente != null) {
					// Construir un mensaje con los datos del cliente
					String mensaje = "Nombre: " + cliente.getNombre() + "\nEmail: " + cliente.getEmail()
							+ "\nTeléfono: " + cliente.getTelefono() + "\nFecha Registro: "
							+ cliente.getFechaRegistro();

					// Mostrar la información del cliente en un cuadro de diálogo
					JOptionPane.showMessageDialog(null, mensaje);
				} else {
					// Mostrar mensaje si no se encontró ningún cliente con ese ID
					JOptionPane.showMessageDialog(null, "No se encontró un cliente con el ID: " + id);
				}

			} catch (NumberFormatException ex) {
				// Mostrar mensaje si el ID no es un número entero válido
				JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
			}
		});

		// --- Consultar por Email ---
		JPanel consultarEmailClientes = new JPanel();
		consultarEmailClientes.setBackground(new Color(255, 255, 255));
		tabbedPaneClientes.addTab("Consultar por Email", null, consultarEmailClientes, null);
		consultarEmailClientes.setLayout(null);

		JTextPane txtpnDimeElEmail = new JTextPane();
		txtpnDimeElEmail.setEditable(false);
		txtpnDimeElEmail.setText("Dime el email:");
		txtpnDimeElEmail.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnDimeElEmail.setBounds(180, 88, 121, 27);
		consultarEmailClientes.add(txtpnDimeElEmail);

		JTextField zonaEmail = new JTextField();
		zonaEmail.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaEmail.setColumns(10);
		zonaEmail.setBounds(324, 90, 200, 27);
		consultarEmailClientes.add(zonaEmail);

		JTextPane txtpnConsultarClientePorEmail = new JTextPane();
		txtpnConsultarClientePorEmail.setEditable(false);
		txtpnConsultarClientePorEmail.setText("Consultar cliente por email");
		txtpnConsultarClientePorEmail.setForeground(Color.BLACK);
		txtpnConsultarClientePorEmail.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnConsultarClientePorEmail.setBounds(190, 11, 269, 33);
		consultarEmailClientes.add(txtpnConsultarClientePorEmail);

		// Crear un botón con el texto "Consultar Email"
		JButton btnConsultarEmail = new JButton("Consultar Email");
		// Establecer la fuente y tamaño del texto del botón
		btnConsultarEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		// Posicionar y dimensionar el botón dentro del panel consultarEmailClientes
		btnConsultarEmail.setBounds(273, 142, 151, 49);
		consultarEmailClientes.add(btnConsultarEmail);
		// Añadir un ActionListener para definir qué pasa al hacer clic en el botón
		btnConsultarEmail.addActionListener(e -> {
			// Obtener y limpiar el texto ingresado en el campo zonaEmail (email a buscar)
			String emailTexto = zonaEmail.getText().trim();

			// Verificar si el campo está vacío
			if (emailTexto.isEmpty()) {
				// Mostrar mensaje para que el usuario introduzca un email
				JOptionPane.showMessageDialog(null, "Por favor, introduzca un email para buscar.");
				return; // Salir para no continuar con campo vacío
			}

			// Buscar el cliente en la base de datos usando el DAO por el email ingresado
			ClienteOtaku cliente = daocliente.buscarPorEmail(emailTexto);

			// Verificar si se encontró un cliente con ese email
			if (cliente == null) {
				// Mostrar mensaje indicando que no se encontró ningún cliente
				JOptionPane.showMessageDialog(null, "No se encontró un cliente con ese email.");
			} else {
				// Construir mensaje con datos del cliente encontrado
				String mensaje = "ID: " + cliente.getId() + "\nNombre: " + cliente.getNombre() + "\nTeléfono: "
						+ cliente.getTelefono() + "\nFecha Registro: " + cliente.getFechaRegistro();

				// Mostrar los datos en un cuadro de diálogo
				JOptionPane.showMessageDialog(null, mensaje);
			}
		});

		JPanel mostrarTodoPanelClientes = new JPanel();
		mostrarTodoPanelClientes.setBackground(new Color(255, 255, 255));
		clientesTabbedPane.addTab("MOSTRAR TODO", null, mostrarTodoPanelClientes, null);
		mostrarTodoPanelClientes.setLayout(null);

		JTable tablaClientes = new JTable();
		JScrollPane scrollPaneClientes = new JScrollPane(tablaClientes);
		scrollPaneClientes.setBounds(32, 11, 650, 300);
		mostrarTodoPanelClientes.add(scrollPaneClientes);

		// Botón para recargar la tabla de clientes
		JButton btnRecargarClientes = new JButton("Recargar");
		// Establecer posición y tamaño del botón dentro del panel
		// mostrarTodoPanelClientes
		btnRecargarClientes.setBounds(276, 322, 120, 30);
		mostrarTodoPanelClientes.add(btnRecargarClientes);
		// Definir un Runnable para cargar y mostrar los datos de los clientes en la
		// tabla
		Runnable cargarDatosClientes = () -> {
			// Obtener la lista de todos los clientes desde el DAO
			List<ClienteOtaku> clientes = daocliente.obtenerTodosLosClientes();
			// Definir los nombres de las columnas para la tabla
			String[] columnas = { "ID", "Nombre", "Email", "Teléfono", "Fecha Registro" };
			// Crear una matriz de objetos para almacenar los datos que se mostrarán en la
			// tabla
			Object[][] datos = new Object[clientes.size()][5];
			// Rellenar la matriz con los datos de cada cliente
			for (int i = 0; i < clientes.size(); i++) {
				ClienteOtaku c = clientes.get(i);
				datos[i][0] = c.getId();
				datos[i][1] = c.getNombre();
				datos[i][2] = c.getEmail();
				datos[i][3] = c.getTelefono();
				datos[i][4] = c.getFechaRegistro();
			}
			// Actualizar el modelo de la tabla con los datos y columnas nuevos
			tablaClientes.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));

			// Deshabilitar edición directa en la tabla para que sea solo lectura
			tablaClientes.setEnabled(false);
		};

		// Ejecutar la carga inicial de datos para mostrar la tabla con clientes al
		// iniciar
		cargarDatosClientes.run();

		// Añadir acción al botón para recargar los datos al hacer clic
		btnRecargarClientes.addActionListener(e -> cargarDatosClientes.run());

		JPanel actualizarPanelClientes = new JPanel();
		actualizarPanelClientes.setBackground(new Color(255, 255, 255));
		clientesTabbedPane.addTab("ACTUALIZAR", null, actualizarPanelClientes, null);
		actualizarPanelClientes.setLayout(null);

		JTextPane txtpnActualizarCliente = new JTextPane();
		txtpnActualizarCliente.setEditable(false);
		txtpnActualizarCliente.setText("Actualizar Cliente");
		txtpnActualizarCliente.setForeground(Color.BLACK);
		txtpnActualizarCliente.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnActualizarCliente.setBounds(250, 11, 220, 33);
		actualizarPanelClientes.add(txtpnActualizarCliente);

		JTextPane txtpnIdActualizar = new JTextPane();
		txtpnIdActualizar.setEditable(false);
		txtpnIdActualizar.setText("ID del cliente:");
		txtpnIdActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnIdActualizar.setBounds(150, 70, 150, 27);
		actualizarPanelClientes.add(txtpnIdActualizar);

		JTextField zonaIDActualizar = new JTextField();
		zonaIDActualizar.setBounds(310, 70, 250, 27);
		actualizarPanelClientes.add(zonaIDActualizar);
		zonaIDActualizar.setColumns(10);

		JTextPane txtpnNombreActualizar = new JTextPane();
		txtpnNombreActualizar.setEditable(false);
		txtpnNombreActualizar.setText("Nuevo nombre:");
		txtpnNombreActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnNombreActualizar.setBounds(150, 110, 150, 27);
		actualizarPanelClientes.add(txtpnNombreActualizar);

		JTextField zonaNombreActualizar = new JTextField();
		zonaNombreActualizar.setBounds(310, 110, 250, 27);
		actualizarPanelClientes.add(zonaNombreActualizar);
		zonaNombreActualizar.setColumns(10);

		JTextPane txtpnEmailActualizar = new JTextPane();
		txtpnEmailActualizar.setEditable(false);
		txtpnEmailActualizar.setText("Nuevo email:");
		txtpnEmailActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnEmailActualizar.setBounds(150, 150, 150, 27);
		actualizarPanelClientes.add(txtpnEmailActualizar);

		JTextField zonaEmailActualizar = new JTextField();
		zonaEmailActualizar.setBounds(310, 150, 250, 27);
		actualizarPanelClientes.add(zonaEmailActualizar);
		zonaEmailActualizar.setColumns(10);

		JTextPane txtpnTelefonoActualizar = new JTextPane();
		txtpnTelefonoActualizar.setEditable(false);
		txtpnTelefonoActualizar.setText("Nuevo teléfono:");
		txtpnTelefonoActualizar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnTelefonoActualizar.setBounds(150, 190, 150, 27);
		actualizarPanelClientes.add(txtpnTelefonoActualizar);

		JTextField zonaTelefonoActualizar = new JTextField();
		zonaTelefonoActualizar.setBounds(310, 190, 250, 27);
		actualizarPanelClientes.add(zonaTelefonoActualizar);
		zonaTelefonoActualizar.setColumns(10);

		// Crear el botón para actualizar cliente con texto y fuente personalizada
		JButton btnActualizarCliente = new JButton("Actualizar Cliente");
		btnActualizarCliente.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnActualizarCliente.setBounds(280, 240, 180, 40);
		actualizarPanelClientes.add(btnActualizarCliente);

		// Añadir un ActionListener al botón para manejar el evento de clic
		btnActualizarCliente.addActionListener(e -> {
			// Obtener y limpiar los textos ingresados en los campos de actualización
			String idTexto = zonaIDActualizar.getText().trim();
			String nombre = zonaNombreActualizar.getText().trim();
			String email = zonaEmailActualizar.getText().trim();
			String telefono = zonaTelefonoActualizar.getText().trim();

			// Validar que ningún campo esté vacío antes de continuar
			if (idTexto.isEmpty() || nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
				return; // Salir si falta algún dato obligatorio
			}

			try {
				// Convertir el ID a entero, si falla se captura la excepción
				int id = Integer.parseInt(idTexto);

				// Validar que el email no esté ya asociado a otro cliente diferente
				while (daocliente.emailExiste(email, id)) {
					// Solicitar al usuario que ingrese un email diferente
					email = JOptionPane.showInputDialog(null,
							"El email ya está en uso por otro cliente. Introduzca un email diferente:", email);

					// Validar que el usuario no haya cancelado o dejado el campo vacío
					if (email == null || email.trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Operación cancelada. Email no puede estar vacío.");
						return; // Salir si no hay un email válido para actualizar
					}
				}

				// Crear el objeto ClienteOtaku con los datos a actualizar
				ClienteOtaku cliente = new ClienteOtaku(id, nombre, email, telefono);

				// Llamar al DAO para actualizar el cliente en la base de datos
				boolean actualizado = daocliente.actualizarCliente(cliente);

				// Mostrar mensaje según el resultado de la operación
				if (actualizado) {
					JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
				} else {
					JOptionPane.showMessageDialog(null, "No se encontró un cliente con ese ID para actualizar.");
				}
			} catch (NumberFormatException ex) {
				// Si el ID no es un número entero válido, mostrar advertencia
				JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
			}
		});

		JPanel eliminarPanelClientes = new JPanel();
		eliminarPanelClientes.setBackground(new Color(255, 255, 255));
		clientesTabbedPane.addTab("ELIMINAR", null, eliminarPanelClientes, null);
		eliminarPanelClientes.setLayout(null);

		JTextPane txtpnEliminarCliente = new JTextPane();
		txtpnEliminarCliente.setEditable(false);
		txtpnEliminarCliente.setText("Eliminar Cliente");
		txtpnEliminarCliente.setForeground(Color.BLACK);
		txtpnEliminarCliente.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtpnEliminarCliente.setBounds(280, 11, 180, 33);
		eliminarPanelClientes.add(txtpnEliminarCliente);

		JTextPane txtpnIdEliminar = new JTextPane();
		txtpnIdEliminar.setEditable(false);
		txtpnIdEliminar.setText("ID del cliente a eliminar:");
		txtpnIdEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnIdEliminar.setBounds(150, 90, 220, 27);
		eliminarPanelClientes.add(txtpnIdEliminar);

		JTextField zonaIDEliminar = new JTextField();
		zonaIDEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		zonaIDEliminar.setBounds(380, 90, 150, 27);
		eliminarPanelClientes.add(zonaIDEliminar);
		zonaIDEliminar.setColumns(10);

		// Crear el botón para eliminar cliente con texto y fuente personalizada
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEliminarCliente.setBounds(280, 150, 180, 40);
		eliminarPanelClientes.add(btnEliminarCliente);

		// Añadir un ActionListener al botón para manejar el evento de clic
		btnEliminarCliente.addActionListener(e -> {
			// Obtener y limpiar el texto del campo donde se ingresa el ID a eliminar
			String idTexto = zonaIDEliminar.getText().trim();

			// Validar que el campo no esté vacío
			if (idTexto.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, introduzca un ID para eliminar.");
				return; // Salir si no hay un ID ingresado
			}

			try {
				// Convertir el ID a entero, si falla se captura la excepción
				int id = Integer.parseInt(idTexto);

				// Mostrar cuadro de diálogo para confirmar la eliminación
				int confirm = JOptionPane.showConfirmDialog(null,
						"¿Estás seguro de que quieres eliminar el cliente con ID " + id + "?", "Confirmar eliminación",
						JOptionPane.YES_NO_OPTION);

				// Si el usuario confirma, proceder a eliminar el cliente
				if (confirm == JOptionPane.YES_OPTION) {
					// Llamar al método DAO para eliminar el cliente por ID
					boolean eliminado = daocliente.eliminarCliente(id);

					// Mostrar mensaje dependiendo del resultado
					if (eliminado) {
						JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
					} else {
						JOptionPane.showMessageDialog(null, "No se encontró un cliente con ese ID.");
					}
				}
			} catch (NumberFormatException ex) {
				// Si el ID no es un número entero válido, mostrar advertencia
				JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
			}
		});

	}
}
