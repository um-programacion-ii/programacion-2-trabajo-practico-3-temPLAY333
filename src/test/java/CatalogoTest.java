import catalogo.Catalogo;
import comun.excepciones.LibroExcepcion;
import libro.Libro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CatalogoTest {
    private Catalogo catalogo;
    
    @BeforeEach
    public void setUp() {
        catalogo = Catalogo.getInstance();
        ArrayList<Libro> libros = new ArrayList<>();

        Libro libro1 = new Libro("1-123-45678-9X", "El Quijote", "Miguel de Cervantes");
        Libro libro2 = new Libro("9-876-54321-0", "Cien años de soledad", "Gabriel García Márquez");
        Libro libro3 = new Libro("0-123-45678-9", "1984", "George Orwell");

        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);

        catalogo.getLibros().clear();
        catalogo.setLibros(libros);
    }

    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testAgregarLibro_ConLibrosValidos_AgregarCorrectamente(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN + "1", titulo, autor);

        catalogo.agregarLibro(libro);

        assertTrue(catalogo.getLibros().contains(libro));
        assertEquals(4, catalogo.getLibros().size());
    }

    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testAgregarLibro_ConLibroExistente_LanzaExcepcion(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN, titulo, autor);

        assertThrows(LibroExcepcion.class, () -> catalogo.agregarLibro(libro));
        assertEquals(3, catalogo.getLibros().size());
    }

        @Test
    public void testAgregarLibro_ConLibroNulo_LanzaExcepcion() {
        assertThrows(LibroExcepcion.class, () -> catalogo.agregarLibro(null));
    }

    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testEliminarLibro_LibroExistente_EliminaCorrectamente(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN, titulo, autor);
         
        catalogo.eliminarLibro(libro);

        assertFalse(catalogo.getLibros().contains(libro));
        assertEquals(2, catalogo.getLibros().size());
    }
    
    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testEliminarLibro_LibroNoExistente_LanzaExcepcion(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN, titulo + "s", autor);

        assertThrows(LibroExcepcion.class, () -> catalogo.eliminarLibro(libro));
    }
    
    @Test
    public void testEliminarLibro_LibroNulo_LanzaExcepcion() {
        assertThrows(LibroExcepcion.class, () -> catalogo.eliminarLibro(null));
    }

    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testBuscarLibroPorTitulo_TituloExistente_DevuelveLibro(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN, titulo, autor);
         
        Libro encontrado = catalogo.buscarLibroPorTitulo(titulo);

        assertEquals(libro, encontrado);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"TítuloInexistente", "OtroTítulo"})
    public void testBuscarLibroPorTitulo_TituloInexistente_LanzaExcepcion(String titulo) {
        assertThrows(LibroExcepcion.class, () -> catalogo.buscarLibroPorTitulo(titulo));
    }

    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testBuscarLibroPorISBN_ISBNExistente_DevuelveLibro(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN, titulo, autor);
         
        Libro encontrado = catalogo.buscarLibroPorISBN(ISBN);

        assertEquals(libro, encontrado);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"123456789X", "987654321X"})
    public void testBuscarLibroPorISBN_ISBNInexistente_LanzaExcepcion(String ISBN) {
        assertThrows(LibroExcepcion.class, () -> catalogo.buscarLibroPorISBN(ISBN));
    }

    @ParameterizedTest
    @MethodSource("provideLibros")
    public void testBuscarLibroPorAutor_AutorExistente_DevuelveLibro(String ISBN, String titulo, String autor) {
        Libro libro = new Libro(ISBN, titulo, autor);
         
        Libro encontrado = catalogo.buscarLibroPorAutor(autor);

        assertEquals(libro, encontrado);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"AutorDesconocido", "OtroAutor"})
    public void testBuscarLibroPorAutor_AutorInexistente_LanzaExcepcion(String autor) {
        assertThrows(LibroExcepcion.class, () -> catalogo.buscarLibroPorAutor(autor));
    }
    

    private static Stream<Arguments> provideLibros() {
        return Stream.of(
            Arguments.of("1-123-45678-9X", "El Quijote", "Miguel de Cervantes"),
            Arguments.of("9-876-54321-0", "Cien años de soledad", "Gabriel García Márquez"),
            Arguments.of("0-123-45678-9", "1984", "George Orwell")
        );
    }
}
