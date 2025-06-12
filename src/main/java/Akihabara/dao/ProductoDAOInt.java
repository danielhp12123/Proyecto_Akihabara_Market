package Akihabara.dao;
import java.util.List;
import Akihabara.model.ProductoOtaku;
/**
 * Clase interfaz que da las funciones a ProductoDAO
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public interface ProductoDAOInt {
	
	 boolean agregarProducto(ProductoOtaku producto);
	 ProductoOtaku obtenerProductoPorId(int id);
	 List<ProductoOtaku> obtenerTodosLosProductos();
	 boolean actualizarProducto(ProductoOtaku producto);
	 boolean eliminarProducto(int id);
	 List<ProductoOtaku> buscarProductosPorNombre (String nombre);
}