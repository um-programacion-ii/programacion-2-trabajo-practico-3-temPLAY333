package libro;

import comun.excepciones.IllegalISBN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ServicioLibrosTest {
    private ServicioLibros servicioLibros;

    @BeforeEach
    void setUp() {
        servicioLibros = spy(ServicioLibros.getInstance());
    }

    @ParameterizedTest
    @CsvSource({
            "978-84-376-0494-7",    // ISBN-13 válido
            "0-306-40615-2",        // ISBN-10 válido
            "9780306406157",        // ISBN-13 sin guiones
            "0306406152",           // ISBN-10 sin guiones
            "978-0-7432-4722-1",    // ISBN-13 de Harry Potter
            "0-7475-3269-9"         // ISBN-10 de Harry Potter
    })
    void verificarISBN_conISBNValido_noLanzaExcepcion(String isbn) {
        // Arrange
        doReturn("isbnLimpio").when(servicioLibros).getIsbnLimpio(anyString());
        doReturn(true).when(servicioLibros).validarDigitoControl(anyString());

        // Act & Assert
        assertDoesNotThrow(() -> servicioLibros.verificarISBN(isbn));

        // Verify
        verify(servicioLibros).getIsbnLimpio(isbn);
        verify(servicioLibros).validarDigitoControl("isbnLimpio");
    }

    @ParameterizedTest
    @CsvSource({
            "'', El ISBN no es válido: No puede ser nulo o vacío",
            "'          ', El ISBN no es válido: No puede ser nulo o vacío",
            "123456, El ISBN no es válido: No tiene 10 ni 13 dígitos",
            "123456789A, El ISBN no es válido: Contiene caracteres inválidos"
    })
    void verificarISBN_conISBNInvalido_lanzaExcepcionConMensaje(String isbn, String mensajeEsperado) {
        // Act
        Exception exception = assertThrows(IllegalISBN.class, () ->
                servicioLibros.verificarISBN(isbn));

        // Assert
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "978-84-376-0494-7, 9788437604947",
            "0-306-40615-2, 0306406152",
            "9780306406157, 9780306406157",
            "0306406152, 0306406152",
            "978-0-7432-4722-1, 9780743247221",
            "0-7475-3269-9, 0747532699"
    })
    void getIsbnLimpio_conISBNValido(String isbn, String isbnLimpioEsperado) {
        String isbnLimpio = servicioLibros.getIsbnLimpio(isbn);

        assertEquals(isbnLimpioEsperado, isbnLimpio);
    }

    @ParameterizedTest
    @CsvSource({
            "123456, El ISBN no es válido: No tiene 10 ni 13 dígitos",
            "12 345 67 89, El ISBN no es válido: No tiene 10 ni 13 dígitos",
            "123456789A, El ISBN no es válido: Contiene caracteres inválidos",

    })
    void getIsbnLimpio_conISBNInvalido_lanzaExcepcion(String isbnInvalido, String mensajeEsperado) {

        Exception exception = assertThrows(IllegalISBN.class, () -> servicioLibros.getIsbnLimpio(isbnInvalido));

        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "9788437604947, true",
            "0306406152, true",
            "9780306406157, true",
            "0306406152, true",
            "9780743247221, true",
            "0747532699, true"
    })
    void validarDigitoControl_conISBNValido(String isbn, boolean digitoControlEsperado) {
        String isbnLimpio = servicioLibros.getIsbnLimpio(isbn);

        boolean digitoControl = servicioLibros.validarDigitoControl(isbnLimpio);

        assertEquals(digitoControlEsperado, digitoControl);
    }

    @ParameterizedTest
    @CsvSource({
            "9788437604942, false",
            "0306406154, false",
            "9780306406154, false",
            "0306406158, false",
            "9780743247225, false",
            "0747532690, false"
    })
    void validarDigitoControl_conISBNInvalido(String isbn, boolean digitoControlEsperado) {
        String isbnLimpio = servicioLibros.getIsbnLimpio(isbn);

        boolean digitoControl = servicioLibros.validarDigitoControl(isbnLimpio);

        assertEquals(digitoControlEsperado, digitoControl);
    }

    @ParameterizedTest
    @CsvSource({
            "'', El título no puede ser nulo o vacío",
            "'          ', El título no puede ser nulo o vacío"
    })
    void verificarTitulo_conTituloInvalido_lanzaExcepcion(String titulo, String mensajeEsperado) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                servicioLibros.verificarTitulo(titulo));

        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'', El autor no puede ser nulo o vacío",
            "'          ', El autor no puede ser nulo o vacío"
    })
    void verificarAutor_conAutorInvalido_lanzaExcepcion(String autor, String mensajeEsperado) {
       Exception exception = assertThrows(IllegalArgumentException.class, () ->
                servicioLibros.verificarAutor(autor));

       assertEquals(mensajeEsperado, exception.getMessage());
    }

    // Mockear el crearLibro
    @Test
    void crearLibro_conISBNValido_creaLibroCorrectamente() {
        String isbn = "978-84-376-0494-7";
        String titulo = "El título";
        String autor = "El autor";

        doNothing().when(servicioLibros).verificarISBN(isbn);
        doNothing().when(servicioLibros).verificarTitulo(titulo);
        doNothing().when(servicioLibros).verificarAutor(autor);

        servicioLibros.crearLibro(isbn, titulo, autor);

        verify(servicioLibros).verificarISBN(isbn);
        verify(servicioLibros).verificarTitulo(titulo);
        verify(servicioLibros).verificarAutor(autor);
    }
}