package util;

import libro.Libro;
import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestContainer {
    // Container de Libros
    private static final List<Object[]> LIBROS_DATA = Arrays.asList(
            new Object[]{"1-123-45678-9X", "El Quijote", "Miguel de Cervantes"},
            new Object[]{"9-876-54321-0", "Cien años de soledad", "Gabriel García Márquez"},
            new Object[]{"0-123-45678-9", "1984", "George Orwell"}
    );

    // Metodo central que crea los mocks
    private static Stream<Libro> crearLibrosMocks() {
        List<Libro> mocks = new ArrayList<>();

        for (Object[] datos : LIBROS_DATA) {
            Libro libro = mock(Libro.class);
            when(libro.getISBN()).thenReturn((String) datos[0]);
            when(libro.getTitulo()).thenReturn((String) datos[1]);
            when(libro.getAutor()).thenReturn((String) datos[2]);
            mocks.add(libro);
        }

        return mocks.stream();
    }

    // Container de Prestamos
}