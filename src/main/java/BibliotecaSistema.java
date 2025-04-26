import libro.Catalogo;
import usuario.ServicioUsuarios;

import prestamo.SistemaPrestamos;

public class BibliotecaSistema {
    private static BibliotecaSistema instance = null;
    private static Catalogo catalogo = Catalogo.getInstance();
    private static SistemaPrestamos sistemaPrestamos = SistemaPrestamos.getInstance();
    private static ServicioUsuarios usuarioService = ServicioUsuarios.getInstance();

    private BibliotecaSistema() {
        // Constructor privado para evitar instanciación externa
    }

    public static BibliotecaSistema getInstance() {
        if (instance == null) {
            instance = new BibliotecaSistema();
        }
        return instance;
    }
}
