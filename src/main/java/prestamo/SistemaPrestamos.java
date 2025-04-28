package prestamo;

import comun.excepciones.PrestamoExcepcion;
import libro.Libro;
import usuario.Usuario;
import comun.excepciones.LibroExcepcion;

public class SistemaPrestamos {
    private static SistemaPrestamos instance = null;
    private static ServicioPrestamos servicioPrestamos = ServicioPrestamos.getInstance();

    private SistemaPrestamos() {
        // Constructor privado para evitar instanciación externa
    }

    public static SistemaPrestamos getInstance() {
        if (instance == null) {
            instance = new SistemaPrestamos();
        }
        return instance;
    }

    public Prestamo prestarLibro(Libro libro, Usuario usuario) {
        if (libro == null || usuario == null) {
            throw new PrestamoExcepcion("El libro y el usuario no pueden ser nulos");
        }

        Prestamo prestamo = servicioPrestamos.crearPrestamo(libro, usuario);

        libro.setEstado(prestamo);
        usuario.agregarPrestamo(prestamo);

        return prestamo;
    }

    public void devolverLibro(Libro libro, Usuario usuario) {
        Prestamo prestamo = verificarLibroPrestado(libro);
        verificarUsuario(usuario, prestamo);

        libro.setEstado(null);
    }

    private Prestamo verificarLibroPrestado(Libro libro) throws PrestamoExcepcion {
        if (libro == null || libro.estaDisponible()) {
            throw new PrestamoExcepcion("El libro no está prestado");
        }
        return libro.getEstado();
    }

    private void verificarUsuario(Usuario usuario, Prestamo prestamo) throws PrestamoExcepcion {
        if (usuario == null) {
            throw new PrestamoExcepcion("El usuario es nulo");
        } else if (!usuario.getPrestamos().contains(prestamo)) {
            throw new PrestamoExcepcion("El usuario no tiene este préstamo");
        }
    }
}
