package usuario;

import comun.excepciones.UsuarioExcepcion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioUsuariosTest {
    private ServicioUsuarios servicioUsuarios;

    @BeforeEach
    public void setUp() {
        servicioUsuarios = ServicioUsuarios.getInstance();

    }

    @ParameterizedTest
    @CsvSource({
        "Juan Perez, juanperz@yahoo.com",
        "Maria Lopez, mariaLopez@gmail.com",
        "Carlos Garcia, carlos.gar@outlook.com"
    })
    public void testCrearUsuario_Valido(String nombre, String email) {
        Usuario usuario = servicioUsuarios.crearUsuario(nombre, email);

        assertNotNull(usuario);
        assertEquals(nombre, usuario.getNombre());
        assertEquals(email, usuario.getEmail());
    }

    @Test
    public void testVerificarNombre_NombreNulo() {
        Exception exception = assertThrows(UsuarioExcepcion.class,
                () -> servicioUsuarios.verificarNombre(null));
        assertEquals("El nombre no puede estar vacío", exception.getMessage());
    }

    @Test
    public void testVerificarNombre_NombreVacio() {
        Exception exception = assertThrows(UsuarioExcepcion.class,
                () -> servicioUsuarios.verificarNombre(""));
        assertEquals("El nombre no puede estar vacío", exception.getMessage());
    }

    @Test
    public void testVerificarEmail_EmailNulo() {
        Exception exception = assertThrows(UsuarioExcepcion.class,
                () -> servicioUsuarios.verificarEmail(null));
        assertEquals("El email no puede estar vacío", exception.getMessage());
    }

    @Test
    public void testVerificarEmail_EmailVacio() {
        Exception exception = assertThrows(UsuarioExcepcion.class,
                () -> servicioUsuarios.verificarEmail(""));
        assertEquals("El email no puede estar vacío", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
        "juanperez@invalid",
        "juanperez@.com",
        "@yahoo.com",
        "juanperez@"
    })
    public void testVerificarEmail_EmailInvalido(String email) {
        Exception exception = assertThrows(UsuarioExcepcion.class,
                () -> servicioUsuarios.verificarEmail(email));
        assertEquals("El email no es válido", exception.getMessage());
    }
}
