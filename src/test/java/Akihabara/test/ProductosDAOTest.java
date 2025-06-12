package Akihabara.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Akihabara.dao.ProductoDAOImpl;
import Akihabara.model.ProductoOtaku;

/**
 * Clase de pruebas unitarias para {@link ProductoDAOImpl}.
 * Valida las operaciones CRUD sobre productos de tipo {@link ProductoOtaku}.
 */
class ProductosDAOTest {

    private static final ProductoDAOImpl dao = new ProductoDAOImpl();
    private ProductoOtaku productoTest;

    /**
     * Inicializa un producto temporal antes de cada prueba.
     * Se agrega a la base de datos y se recupera su ID.
     */
    @BeforeEach
    void setUp() {
        productoTest = new ProductoOtaku();
        productoTest.setNombre("Demo Producto " + System.currentTimeMillis());
        productoTest.setCategoria("Ejemplo");
        productoTest.setPrecio(99.99);
        productoTest.setStock(10);

        dao.agregarProducto(productoTest);

        List<ProductoOtaku> encontrados = dao.buscarProductosPorNombre(productoTest.getNombre());
        if (!encontrados.isEmpty()) {
            productoTest.setId(encontrados.get(0).getId());
        }
    }

    /**
     * Elimina el producto temporal creado después de cada prueba.
     * Previene contaminación entre casos de prueba.
     */
    @AfterEach
    void tearDown() {
        if (productoTest.getId() > 0) {
            dao.eliminarProducto(productoTest.getId());
        }
    }

    /**
     * Verifica que al insertar un producto se le asigna un identificador único.
     */
    @Test
    @DisplayName("Insertar producto: el ID debe generarse correctamente")
    void testAgregarProducto() {
        assertTrue(productoTest.getId() > 0, "El ID del producto debería haberse generado correctamente");
    }

    /**
     * Comprueba que los datos del producto recuperado por ID coinciden con los insertados.
     */
    @Test
    @DisplayName("Consultar producto por ID: los datos deben coincidir")
    void testObtenerProductoPorId() {
        ProductoOtaku obtenido = dao.obtenerProductoPorId(productoTest.getId());

        assertNotNull(obtenido, "No se encontró ningún producto con el ID especificado");
        assertEquals(productoTest.getNombre(), obtenido.getNombre(), "El nombre del producto obtenido no coincide con el original");
    }

    /**
     * Asegura que la lista de productos no está vacía tras realizar una consulta general.
     */
    @Test
    @DisplayName("Listar productos: la colección debe contener elementos")
    void testObtenerTodosLosProductos() {
        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();

        assertNotNull(productos, "La colección de productos no debe ser nula");
        assertFalse(productos.isEmpty(), "Se esperaba al menos un producto en la base de datos");
    }

    /**
     * Evalúa si la actualización del stock del producto se refleja correctamente en la base de datos.
     */
    @Test
    @DisplayName("Actualizar producto: los cambios deben guardarse correctamente")
    void testActualizarProducto() {
        productoTest.setStock(99);
        boolean actualizado = dao.actualizarProducto(productoTest);

        assertTrue(actualizado, "No se logró actualizar el producto en la base de datos");

        ProductoOtaku actualizadoProducto = dao.obtenerProductoPorId(productoTest.getId());
        assertEquals(99, actualizadoProducto.getStock(), "El valor de stock no fue actualizado correctamente");
    }

    /**
     * Verifica que al eliminar un producto, ya no pueda ser recuperado por ID.
     */
    @Test
    @DisplayName("Eliminar producto: no debe estar disponible tras la eliminación")
    void testEliminarProducto() {
        boolean eliminado = dao.eliminarProducto(productoTest.getId());

        assertTrue(eliminado, "Fallo al eliminar el producto de la base de datos");

        ProductoOtaku eliminadoProducto = dao.obtenerProductoPorId(productoTest.getId());
        assertNull(eliminadoProducto, "El producto eliminado aún se encuentra disponible");

        // Evita que el @AfterEach intente eliminarlo nuevamente
        productoTest.setId(-1);
    }

    /**
     * Valida que la búsqueda por nombre devuelve productos que coincidan con el nombre esperado.
     */
    @Test
    @DisplayName("Buscar por nombre: debe devolver coincidencias exactas")
    void testBuscarProductosPorNombre() {
        List<ProductoOtaku> encontrados = dao.buscarProductosPorNombre(productoTest.getNombre());

        assertFalse(encontrados.isEmpty(), "No se encontraron productos con el nombre buscado");
        assertEquals(productoTest.getNombre(), encontrados.get(0).getNombre(), "El nombre recuperado no coincide con el esperado");
    }
}

