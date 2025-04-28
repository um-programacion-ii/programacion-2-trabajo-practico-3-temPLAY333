package usuario;

import libro.Libro;
import libro.Catalogo;
import prestamo.Prestamo;
import prestamo.SistemaPrestamos;
import util.TestContainer;
import comun.excepciones.UsuarioExcepcion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GestionUsuariosTest {
    private GestionUsuarios gestionUsuarios;

    @BeforeEach
    public void setUp() {
        gestionUsuarios = spy(GestionUsuarios.getInstance());
        gestionUsuarios.servicioUsuarios = mock(ServicioUsuarios.class);
        gestionUsuarios.sistemaPrestamos = mock(SistemaPrestamos.class);
        gestionUsuarios.catalogo = mock(Catalogo.class);

        ArrayList<Usuario> usuarios = TestContainer.crearUsuarios();
        gestionUsuarios.setRegistroUsuarios(usuarios);
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testRegistrarPrestamo_Correcto(Libro libro, Usuario usuario) {
        String nombreUsuario = usuario.getNombre();
        String isbn = libro.getISBN();

        when(gestionUsuarios.catalogo.buscarLibroPorISBN(isbn)).thenReturn(libro);
        when(gestionUsuarios.buscarUsuarioPorNombre(nombreUsuario)).thenReturn(usuario);
        when(gestionUsuarios.sistemaPrestamos.prestarLibro(libro, usuario)).thenReturn(new Prestamo());

        boolean resultado = gestionUsuarios.registrarPrestamo(nombreUsuario, isbn);

        assertTrue(resultado);
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testBuscarUsuarioPorNombre_Correcto(Libro libro, Usuario usuario) {
        String nombreUsuario = usuario.getNombre();

        when(gestionUsuarios.buscarUsuarioPorNombre(nombreUsuario)).thenReturn(usuario);

        Usuario resultado = gestionUsuarios.buscarUsuarioPorNombre(nombreUsuario);

        assertEquals(usuario, resultado);
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void BuscarUsuarioPorNombre_UsuarioNoEncontrado(Libro libro, Usuario usuario) {
        String nombreUsuario = usuario.getNombre() + "no existe";

        Exception exception = assertThrows(UsuarioExcepcion.class,
                () -> gestionUsuarios.buscarUsuarioPorNombre(nombreUsuario));
        assertEquals("Un usuario con ese nombre no existe en el registro", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("util.TestContainer#crearLibrosYUsuariosMocks")
    public void testRegistrarUsuario_Correcto(Libro libro, Usuario usuario) {
        String nombreUsuario = usuario.getNombre();
        String email = usuario.getEmail();

        when(gestionUsuarios.servicioUsuarios.crearUsuario(nombreUsuario, email)).thenReturn(usuario);

        gestionUsuarios.registrarUsuario(nombreUsuario, email);

        assertTrue(gestionUsuarios.registroUsuarios.contains(usuario));
    }
}