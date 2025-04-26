package prestamo;

import comun.excepciones.PrestamoExcepcion;
import libro.Libro;
import usuario.Usuario;

public class ServicioPrestamos {
    private static ServicioPrestamos instance = null;

    private ServicioPrestamos() {
        // Constructor privado para evitar instanciación externa
    }

    public static ServicioPrestamos getInstance() {
        if (instance == null) {
            instance = new ServicioPrestamos();
        }
        return instance;
    }

    public Prestamo crearPrestamo(Libro libro, Usuario usuario) {
        if (libro == null || usuario == null) {
            throw new PrestamoExcepcion("El libro y el usuario no pueden ser nulos");
        }

        if (libro.getEstado() != null) {
            throw new PrestamoExcepcion("El libro ya está prestado");
        }

        if (usuario.getPrestamos().size() >= 5) {
            throw new PrestamoExcepcion("El usuario ya tiene el máximo de préstamos permitidos");
        }

        return new Prestamo(libro, usuario);
    }
}