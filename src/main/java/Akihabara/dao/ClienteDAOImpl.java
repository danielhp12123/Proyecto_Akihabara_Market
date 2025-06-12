package Akihabara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Akihabara.model.ClienteOtaku;

/**
 * Clase DAO con las funcionalidades de clientes
 * Proporciona métodos para gestionar clientes en la base de datos.
 * Permite agregar, obtener, actualizar, eliminar y buscar clientes.
 * 
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class ClienteDAOImpl implements ClienteDAOInt {
	Connection conn = DatabaseConnection.connection();

	/**
	 * Agrega un nuevo cliente a la base de datos.
	 *
	 * @param cliente Objeto ClienteOtaku con los datos del cliente (nombre, email, teléfono).
	 * @return true si se agregó correctamente; false si no se insertó ningún registro.
	 */
	public boolean agregarCliente(ClienteOtaku cliente) {
		String sql = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getTelefono());
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
	 * Obtiene un cliente de la base de datos según su ID.
	 *
	 * @param id ID del cliente a buscar (debe ser un entero positivo).
	 * @return Un objeto ClienteOtaku si se encuentra; null si no existe o ocurre un error.
	 */
	public ClienteOtaku obtenerClientePorId(int id) {
		String query = "SELECT * FROM clientes WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new ClienteOtaku(rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"),
						rs.getString("fecha_registro"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene todos los clientes almacenados en la base de datos.
	 *
	 * @return Una lista de objetos ClienteOtaku. La lista estará vacía si no hay clientes o si ocurre un error.
	 */
	public List<ClienteOtaku> obtenerTodosLosClientes() {
		List<ClienteOtaku> clientes = new ArrayList<>();
		String query = "SELECT * FROM clientes";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				clientes.add(new ClienteOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("telefono"), rs.getString("fecha_registro")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	/**
	 * Actualiza un cliente existente en la base de datos usando su ID.
	 *
	 * @param cliente Objeto ClienteOtaku con la id del cliente y los nuevos datos (nombre, email, teléfono).
	 * @return true si se actualizó correctamente; false si no se encontró el cliente.
	 */
	public boolean actualizarCliente(ClienteOtaku cliente) {
		String update = "UPDATE clientes SET nombre = ?, email= ?, telefono = ? WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(update)) {
			stmt.setString(1, cliente.getNombre());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getTelefono());
			stmt.setInt(4, cliente.getId());
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
	 * Elimina un cliente de la base de datos usando su ID.
	 *
	 * @param id ID del cliente a eliminar.
	 * @return true si el cliente fue eliminado correctamente; false si no se encontró ningún cliente con ese ID.
	 */
	public boolean eliminarCliente(int id) {
		String delete = "DELETE FROM clientes WHERE id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(delete)) {
			stmt.setInt(1, id);
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
	 * Busca un cliente utilizando su correo electrónico.
	 *
	 * @param email Correo electrónico del cliente a buscar.
	 * @return Un objeto ClienteOtaku si se encuentra; null si no existe o ocurre un error.
	 */
	public ClienteOtaku buscarPorEmail(String email) {
		String query = "SELECT * FROM clientes WHERE email = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new ClienteOtaku(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"),
						rs.getString("telefono"), rs.getString("fecha_registro"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Verifica si un correo electrónico ya está registrado en la base de datos,
	 * excluyendo al cliente con el ID proporcionado.
	 *
	 * @param email Correo electrónico a verificar.
	 * @param idClienteActual ID del cliente actual que se está editando (será excluido de la búsqueda).
	 * @return true si el correo ya está en uso por otro cliente; false si no está en uso o si ocurre un error.
	 */
	public boolean emailExiste(String email, int idClienteActual) {
	    String query = "SELECT COUNT(*) FROM clientes WHERE email = ? AND id != ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, email);
	        stmt.setInt(2, idClienteActual); // excluye al cliente actual
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


}

