package comun.excepciones;

public class PrestamoFechasExcepcion extends RuntimeException {
    public PrestamoFechasExcepcion() {
        super("La fecha de entrega no puede ser anterios a la fecha de prestamo.");
    }
}
