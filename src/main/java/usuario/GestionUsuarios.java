package usuario;

import comun.excepciones.LibroExcepcion;
import comun.excepciones.UsuarioExcepcion;
import libro.Catalogo;
import libro.Libro;
import prestamo.SistemaPrestamos;

import java.util.ArrayList;

public class GestionUsuarios {
    public static GestionUsuarios instance = null;
    public ServicioUsuarios servicioUsuarios = ServicioUsuarios.getInstance();
    public SistemaPrestamos sistemaPrestamos = SistemaPrestamos.getInstance();
    public Catalogo catalogo = Catalogo.getInstance();
    public ArrayList<Usuario> registroUsuarios = new ArrayList<>();

    private GestionUsuarios() {
        // Constructor privado para evitar instanciación externa
    }

    public static GestionUsuarios getInstance() {
        if (instance == null) {
            instance = new GestionUsuarios();
        }
        return instance;
    }

    public boolean registrarPrestamo(String nombre, String isbn) {
        try {
            Usuario usuario = buscarUsuarioPorNombre(nombre);

            Libro libro = catalogo.buscarLibroPorISBN(isbn);

            sistemaPrestamos.prestarLibro(libro, usuario);
            return true;
        } catch (UsuarioExcepcion | LibroExcepcion e) {
            System.out.println(e);
            return false;
        }
    }

    public Usuario buscarUsuarioPorNombre(String nombre) {
        return registroUsuarios.stream()
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new UsuarioExcepcion("Un usuario con ese nombre no existe en el registro"));
    }

    public void registrarUsuario(String nombre, String email) {
        try {
            Usuario usuario = servicioUsuarios.crearUsuario(nombre, email);
            registroUsuarios.add(usuario);
        } catch (UsuarioExcepcion e) {
            System.out.println(e);
        }
    }

    public void eliminarUsuario(String nombre) {
        Usuario usuario = buscarUsuarioPorNombre(nombre);
        if (usuario != null) {
            registroUsuarios.remove(usuario);
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public ArrayList<Usuario> getRegistroUsuarios() {
        return registroUsuarios;
    }

    public void setRegistroUsuarios(ArrayList<Usuario> registroUsuarios) {
        this.registroUsuarios = registroUsuarios;
    }
}
