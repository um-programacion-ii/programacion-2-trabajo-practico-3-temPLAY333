package libro;

import comun.excepciones.LibroExcepcion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import util.TestContainer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CatalogoTest {
    private Catalogo catalogo;

    @BeforeEach
    public void setUp() {
        catalogo = Catalogo.getInstance();
        catalogo.getLibros().clear();

        ArrayList<Libro> libros = TestContainer.crearLibros();
        catalogo.setLibros(libros);
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testAgregarLibro_ConLibroValido(Libro libro) {
        when(libro.getISBN()).thenReturn("1234");

        assertTrue(catalogo.agregarLibro(libro));
        assertTrue(catalogo.getLibros().contains(libro));
        assertEquals(4, catalogo.getLibros().size());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testAgregarLibro_ConLibroExistente(Libro libro) {
        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.agregarLibro(libro));
        assertEquals("Ese libro ya existe en el catálogo", exception.getMessage());
        assertEquals(3, catalogo.getLibros().size());
    }

    @Test
    public void testAgregarLibro_ConLibroNulo() {
        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.agregarLibro(null));
        assertEquals("El libro no puede ser nulo", exception.getMessage());
        assertEquals(3, catalogo.getLibros().size());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testEliminarLibro_LibroExistente_EliminaCorrectamente(Libro libro) {
        assertTrue(catalogo.eliminarLibro(libro));
        assertFalse(catalogo.getLibros().contains(libro));
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testEliminarLibro_LibroNoExistente(Libro libro) {
        when(libro.getISBN()).thenReturn("1234");

        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.eliminarLibro(libro));
        assertEquals("El libro no existe en el catálogo", exception.getMessage());
    }

    @Test
    public void testEliminarLibro_LibroNulo() {
        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.eliminarLibro(null));
        assertEquals("El libro no puede ser nulo", exception.getMessage());
        assertEquals(3, catalogo.getLibros().size());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testBuscarLibroPorTitulo_TituloExistente_DevuelveLibro(Libro libro) {
        Libro encontrado = catalogo.buscarLibroPorTitulo(libro.getTitulo());

        assertEquals(libro.getISBN(), encontrado.getISBN());
        assertEquals(libro.getTitulo(), encontrado.getTitulo());
        assertEquals(libro.getAutor(), encontrado.getAutor());
    }

    @ParameterizedTest
    @ValueSource(strings = {"TítuloInexistente", "OtroTítulo"})
    public void testBuscarLibroPorTitulo_TituloInexistente(String titulo) {
        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.buscarLibroPorTitulo(titulo));
        assertEquals("Un libro con ese titulo no existe en el catálogo", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testBuscarLibroPorISBN_ISBNExistente_DevuelveLibro(Libro libro) {
        Libro encontrado = catalogo.buscarLibroPorISBN(libro.getISBN());

        assertEquals(libro.getISBN(), encontrado.getISBN());
        assertEquals(libro.getTitulo(), encontrado.getTitulo());
        assertEquals(libro.getAutor(), encontrado.getAutor());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456789X", "987654321X"})
    public void testBuscarLibroPorISBN_ISBNInexistente_LanzaExcepcion(String isbn) {
        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.buscarLibroPorISBN(isbn));
        assertEquals("Un libro con ese ISBN no existe en el catálogo", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosMocks")
    public void testBuscarLibroPorAutor_AutorExistente_DevuelveLibro(Libro libro) {
        Libro encontrado = catalogo.buscarLibroPorAutor(libro.getAutor());

        assertEquals(libro.getISBN(), encontrado.getISBN());
        assertEquals(libro.getTitulo(), encontrado.getTitulo());
        assertEquals(libro.getAutor(), encontrado.getAutor());
    }

    @ParameterizedTest
    @ValueSource(strings = {"AutorDesconocido", "OtroAutor"})
    public void testBuscarLibroPorAutor_AutorInexistente_LanzaExcepcion(String autor) {
        Exception exception = assertThrows(LibroExcepcion.class, () ->
                catalogo.buscarLibroPorAutor(autor));
        assertEquals("Un libro con ese autor no existe en el catálogo", exception.getMessage());
    }
}
