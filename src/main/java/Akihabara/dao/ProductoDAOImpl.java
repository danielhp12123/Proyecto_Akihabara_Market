package Akihabara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Akihabara.model.ProductoOtaku;

/**
 * Clase DAO con las funcionalidades de productos Proporciona métodos para
 * gestionar productos en la base de datos. Permite agregar, obtener,
 * actualizar, eliminar y buscar productos.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */

public class ProductoDAOImpl implements ProductoDAOInt {
	Connection conn = DatabaseConnection.connection();

	/**
	 * Agrega un nuevo producto a la base de datos.
	 *
	 * @param producto Objeto ProductoOtaku con los datos del producto (nombre,
	 *                 categoría, precio, stock).
	 * @return true si se agregó correctamente; false si no se insertó ningún
	 *         registro.
	 */
	public boolean agregarProducto(ProductoOtaku producto) {
		String sql = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, producto.getNombre());
			stmt.setString(2, producto.getCategoria());
			stmt.setDouble(3, producto.getPrecio());
			stmt.setInt(4, producto.getStock());
			int affected = stmt.executeUpdate();
			if (affected == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Obtiene un producto de la base de datos según su ID.
	 *
	 * @param id ID del producto a buscar (debe ser un entero positivo).
	 * @return Un objeto ProductoOtaku si se encuentra; null si no existe o ocurre
	 *         un error.
	 */

	public ProductoOtaku obtenerProductoPorId(int id) {
		String query = "SELECT * FROM productos WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new ProductoOtaku(rs.getString("nombre"), rs.getString("categoria"), rs.getDouble("precio"),
						rs.getInt("stock"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene todos los productos almacenados en la base de datos.
	 *
	 * @return Una lista de objetos ProductoOtaku. La lista estará vacía si no hay
	 *         productos o si ocurre un error.
	 */

	public List<ProductoOtaku> obtenerTodosLosProductos() {
		List<ProductoOtaku> productos = new ArrayList<>();
		String query = "SELECT * FROM productos";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				productos.add(new ProductoOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("categoria"),
						rs.getDouble("precio"), rs.getInt("stock")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return productos;

	}

	/**
	 * Actualiza un producto existente en la base de datos usando su ID.
	 *
	 * @param producto Objeto ProductoOtaku con la id del producto y los nuevos
	 *                 datos (nombre, categoría, precio, stock).
	 * @return true si se actualizó correctamente; false si no se encontró el
	 *         producto.
	 */

	public boolean actualizarProducto(ProductoOtaku producto) {
		String update = "UPDATE productos SET nombre = ?, categoria= ?, precio = ?, stock = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(update)) {
			stmt.setString(1, producto.getNombre());
			stmt.setString(2, producto.getCategoria());
			stmt.setDouble(3, producto.getPrecio());
			stmt.setInt(4, producto.getStock());
			stmt.setInt(5, producto.getId());
			// Y verifica si se hizo
			int affected = stmt.executeUpdate();
			if (affected == 0) {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Error al actualizar");
		}
		return true;
	}

	/**
	 * Elimina un producto de la base de datos usando su ID.
	 *
	 * @param id ID del producto a eliminar.
	 * @return true si el producto fue eliminado correctamente; false si no se
	 *         encontró ningún producto con ese ID.
	 */

	public boolean eliminarProducto(int id) {
		String delete = "DELETE FROM productos WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(delete)) {
			stmt.setInt(1, id);
			// Y verifica si se hizo
			int affected = stmt.executeUpdate();
			if (affected == 0) {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Error al eliminar");
		}
		return true;
	}

	/**
	 * Busca productos cuyo nombre contenga el texto dado.
	 *
	 * @param nombre Texto a buscar dentro del nombre del producto.
	 * @return Lista de productos que coinciden con el nombre. La lista estará vacía
	 *         si no hay coincidencias o ocurre un error.
	 */

	public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
		List<ProductoOtaku> productos = new ArrayList<>();
		String query = "SELECT * FROM productos where nombre like ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, "%" + nombre + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				productos.add(new ProductoOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("categoria"),
						rs.getDouble("precio"), rs.getInt("stock")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return productos;
	}

}
