package comun.excepciones;

public class IllegalISBN extends RuntimeException {
    public IllegalISBN(String message) {
        super("El ISBN no es válido: " + message);
    }
}
