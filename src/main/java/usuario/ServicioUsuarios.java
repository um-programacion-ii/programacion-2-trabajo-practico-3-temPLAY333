package usuario;

public class ServicioUsuarios {
    private static ServicioUsuarios instance = null;
    private static int idCounter = 1; // Contador para asignar IDs únicos a los usuarios

    private ServicioUsuarios() {
        // Constructor privado para evitar instanciación externa
    }

    public static ServicioUsuarios getInstance() {
        if (instance == null) {
            instance = new ServicioUsuarios();
        }
        return instance;
    }

    public Usuario crearUsuario(String nombre, String email) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        return new Usuario(idCounter++, nombre, email);
    }

    public Usuario buscarUsuarioPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de usuario inválido");
        }
        return null; // Cambia esto para devolver el usuario encontrado
    }
}
