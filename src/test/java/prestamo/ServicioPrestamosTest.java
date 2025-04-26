package prestamo;

import comun.excepciones.PrestamoExcepcion;
import libro.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Usuario;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioPrestamosTest {
    private ServicioPrestamos servicioPrestamos;
    private Libro libro;
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        servicioPrestamos = ServicioPrestamos.getInstance();
        libro = mock(Libro.class);
        usuario = mock(Usuario.class);
    }

    @Test
    public void testCrearPrestamo() {
        when(libro.getEstado()).thenReturn(null);
        when(usuario.getPrestamos()).thenReturn(new ArrayList<>());

        Prestamo prestamo = assertDoesNotThrow(() -> servicioPrestamos.crearPrestamo(libro, usuario));

        assertNotNull(prestamo);
    }

    @Test
    public void testCrearPrestamo_LibroNulo() {
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> servicioPrestamos.crearPrestamo(null, usuario));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @Test
    public void testCrearPrestamo_UsuarioNulo() {
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> servicioPrestamos.crearPrestamo(libro, null));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @Test
    public void testCrearPrestamo_LibroyUsuarioNulos() {
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> servicioPrestamos.crearPrestamo(null, null));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @Test
    public void testCrearPrestamo_LibroPrestado() {
        when(libro.getEstado()).thenReturn(new Prestamo());

        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> servicioPrestamos.crearPrestamo(libro, usuario));
        assertEquals("El libro ya está prestado", exception.getMessage());

        verify(libro).getEstado();
    }

    @Test
    public void testCrearPrestamo_UsuarioMuchosPrestados() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            prestamos.add(new Prestamo());
        }
        when(usuario.getPrestamos()).thenReturn(prestamos);

        assertEquals(5, usuario.getPrestamos().size());
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> servicioPrestamos.crearPrestamo(libro, usuario));
        assertEquals("El usuario ya tiene el máximo de préstamos permitidos", exception.getMessage());
    }
}
