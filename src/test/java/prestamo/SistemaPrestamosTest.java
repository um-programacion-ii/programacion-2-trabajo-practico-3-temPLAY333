package prestamo;

import libro.Libro;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import usuario.Usuario;
import util.TestContainer;
import comun.excepciones.PrestamoExcepcion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaPrestamosTest {
    private SistemaPrestamos sistemaPrestamos;

    @BeforeEach
    public void setUp() {
        sistemaPrestamos = SistemaPrestamos.getInstance();
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testPrestarLibro_LibroyUsuariosCorrectos(Libro libro, Usuario usuario) {
        ArrayList<Prestamo> prestamosMock = mock(ArrayList.class);
        when(libro.estaDisponible()).thenReturn(true);
        when(libro.getEstado()).thenReturn(null);
        when(usuario.getPrestamos()).thenReturn(prestamosMock);
        when(prestamosMock.size()).thenReturn(0);

        Prestamo prestamo = sistemaPrestamos.prestarLibro(libro, usuario);

        // Verificar que el libro y el usuario se han actualizado correctamente
        verify(libro).setEstado(prestamo);
        verify(usuario).agregarPrestamo(prestamo);
        assertEquals(prestamo.getLibro(), libro);
        assertEquals(prestamo.getUsuario(), usuario);
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testPrestarLibro_LibroNulo(Libro libro, Usuario usuario) {
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> sistemaPrestamos.prestarLibro(null, usuario));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testPrestarLibro_UsuarioNulo(Libro libro, Usuario usuario) {
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> sistemaPrestamos.prestarLibro(libro, null));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @Test
    public void testPrestarLibro_LibroyUsuarioNulo() {
        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> sistemaPrestamos.prestarLibro(null, null));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testPrestarLibro_LibroPrestado(Libro libro, Usuario usuario) {
        when(libro.estaDisponible()).thenReturn(false);

        Exception exception = assertThrows(PrestamoExcepcion.class,
                () -> sistemaPrestamos.prestarLibro(null, usuario));
        assertEquals("El libro y el usuario no pueden ser nulos", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testDevolverLibro_LibroyUsuarioCorrecto(Libro libro, Usuario usuario) {
        Prestamo prestamo = new Prestamo(libro, usuario);
        ArrayList<Prestamo> prestamosMock = mock(ArrayList.class);

        when(libro.estaDisponible()).thenReturn(false);
        when(libro.getEstado()).thenReturn(prestamo);
        when(usuario.getPrestamos()).thenReturn(prestamosMock);
        when(prestamosMock.contains(prestamo)).thenReturn(true);

        sistemaPrestamos.devolverLibro(libro, usuario);

        verify(libro).setEstado(null);
    }
}
