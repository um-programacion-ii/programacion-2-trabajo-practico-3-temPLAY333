package prestamo;

import libro.Libro;
import libro.Catalogo;
import usuario.Usuario;
import usuario.ServicioUsuarios;
import comun.excepciones.LibroExcepcion;

public class SistemaPrestamos {
    private static SistemaPrestamos instance = null;
    private static Catalogo catalogo = Catalogo.getInstance();
    private static ServicioUsuarios usuarioService = ServicioUsuarios.getInstance();

    private SistemaPrestamos() {
        // Constructor privado para evitar instanciación externa
    }

    public static SistemaPrestamos getInstance() {
        if (instance == null) {
            instance = new SistemaPrestamos();
        }
        return instance;
    }

    public void prestarLibro(Libro libro, Usuario usuario) {
        try {
            Prestamo prestamo = new Prestamo(libro, usuario);

            libro.setEstado(prestamo);
            usuario.agregarPrestamo(prestamo);

        } catch (LibroExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void devolverLibro(Libro libro, Usuario usuario) {
        try {
            Prestamo prestamo = libro.getEstado();

        } catch (LibroExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
