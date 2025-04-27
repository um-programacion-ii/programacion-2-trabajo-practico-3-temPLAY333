package util;

import libro.Libro;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            when(libro.getEstado()).thenReturn(null);
            mocks.add(libro);
        }

        return mocks.stream();
    }

    // Lista de Libros
    public static ArrayList<Libro> crearLibros() {
        ArrayList<Libro> libros = new ArrayList<>();
        for (Object[] datos : LIBROS_DATA) {
            Libro libro = new Libro((String) datos[0], (String) datos[1], (String) datos[2]);
            libros.add(libro);
        }
        return libros;
    }

    // Container de Usuarios
    private static final List<Object[]> USUARIOS_DATA = Arrays.asList(
            new Object[]{ 0, "Juan Perez", "juanperez@gmail.com"},
            new Object[]{ 1, "Maria Lopez", "maria.lopez@hotmail.com"},
            new Object[]{ 2, "Carlos Garcia", "c.garcia@outlook.com"}
    );

    // Metodo central que crea los mocks
    private static Stream<Usuario> crearUsuariosMocks() {
        List<Usuario> mocks = new ArrayList<>();

        for (Object[] datos : USUARIOS_DATA) {
            Usuario usuario = mock(Usuario.class);
            when(usuario.getNombre()).thenReturn((String) datos[0]);
            when(usuario.getPrestamos()).thenReturn(new ArrayList<>());
            mocks.add(usuario);
        }

        return mocks.stream();
    }

    // Lista de Usuarios
    public static ArrayList<Usuario> crearUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (Object[] datos : USUARIOS_DATA) {
            Usuario usuario = new Usuario((int) datos[0], (String) datos[1], (String) datos[2]);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    // Metodo para crear Usuarios Mocks y Libros Mocks
    public static Stream<Object[]> crearLibrosYUsuariosMocks() {
        List<Object[]> librosYUsuarios = new ArrayList<>();

        for (int i = 0; i < LIBROS_DATA.size(); i++) {
            Object[] libroDatos = LIBROS_DATA.get(i);
            Object[] usuarioDatos = USUARIOS_DATA.get(i);

            Libro libro = mock(Libro.class);
            when(libro.getISBN()).thenReturn((String) libroDatos[0]);
            when(libro.getTitulo()).thenReturn((String) libroDatos[1]);
            when(libro.getAutor()).thenReturn((String) libroDatos[2]);
            when(libro.getEstado()).thenReturn(null);

            Usuario usuario = mock(Usuario.class);
            when(usuario.getNombre()).thenReturn((String) usuarioDatos[1]);
            when(usuario.getPrestamos()).thenReturn(new ArrayList<>());

            librosYUsuarios.add(new Object[]{libro, usuario});
        }

        return librosYUsuarios.stream();
    }

}