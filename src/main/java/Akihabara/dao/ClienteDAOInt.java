package Akihabara.dao;

import java.util.List;

import Akihabara.model.ClienteOtaku;
/**
 * Clase interfaz que da las funciones a ClienteDAO
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public interface ClienteDAOInt {
	boolean agregarCliente(ClienteOtaku cliente);
	ClienteOtaku obtenerClientePorId(int id);
	List<ClienteOtaku> obtenerTodosLosClientes();
	boolean actualizarCliente(ClienteOtaku cliente);
	boolean eliminarCliente(int id);
	ClienteOtaku buscarPorEmail(String email);
}
