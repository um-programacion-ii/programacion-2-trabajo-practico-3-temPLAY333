package usuario;

import comun.excepciones.UsuarioExcepcion;

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
        this.verificarNombre(nombre);
        this.verificarEmail(email);

        return new Usuario(idCounter++, nombre, email);
    }

    public void verificarNombre(String nombre) throws UsuarioExcepcion {
        if (nombre == null || nombre.isEmpty()) {
            throw new UsuarioExcepcion("El nombre no puede estar vacío");
        }
    }

    public void verificarEmail(String email) throws UsuarioExcepcion {
        if (email == null || email.isEmpty()) {
            throw new UsuarioExcepcion("El email no puede estar vacío");
        }
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new UsuarioExcepcion("El email no es válido");
        }
    }
}
