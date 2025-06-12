package Akihabara.model;
/**
 * clase con los atributos para crear Clientes
 * @author Daniel
 * @version 1.0
 * @since 2025
 */
public class ClienteOtaku {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String fechaRegistro;

    // Constructor
    public ClienteOtaku(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    public ClienteOtaku(int id,String nombre, String email, String telefono) {
    	this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    public ClienteOtaku(int id, String nombre, String email, String telefono, String fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }
    public ClienteOtaku(String nombre, String email, String telefono, String fechaRegistro) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}

