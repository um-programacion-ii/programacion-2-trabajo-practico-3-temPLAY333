package libro;

import comun.excepciones.IllegalISBN;

public class LibroService {
    private static LibroService instance = null;

    private LibroService() {
        // Constructor privado para evitar instanciación externa
    }

    public static LibroService getInstance() {
        if (instance == null) {
            instance = new LibroService();
        }
        return instance;
    }

    public Libro crearLibro(String ISBN, String titulo, String autor) {
        this.verificarISBN(ISBN);
        this.verificarTitulo(titulo);
        this.verificarAutor(autor);
        // Crear el libro
        Libro libro = new Libro(ISBN, titulo, autor);
        return libro;
    }

    public void verificarISBN(String ISBN) throws IllegalISBN {
        // Verificar si el ISBN es válido
        if (ISBN == null || ISBN.trim().isEmpty()) {
            throw new IllegalISBN("No puede ser nulo o vacío");
        }

        String isbnLimpio = getIsbnLimpio(ISBN);

        if (!validarDigitoControl(isbnLimpio)) {
            throw new IllegalISBN("Codigo Erroneo (no coincide con el digito de control)");
        }
    }

    public String getIsbnLimpio(String ISBN) throws IllegalISBN {
        String isbnLimpio = ISBN.replaceAll("[-\\s]", "");

        if (isbnLimpio.length() != 10 && isbnLimpio.length() != 13) {
            throw new IllegalISBN("No tiene 10 ni 13 dígitos");
        }

        // Verificar que solo contenga dígitos (y posiblemente 'X' en la última posición para ISBN-10)
        if (!isbnLimpio.matches("[0-9]{9}[0-9X]") && !isbnLimpio.matches("[0-9]{13}")) {
            throw new IllegalISBN("Contiene caracteres inválidos");
        }
        return isbnLimpio;
    }

    public boolean validarDigitoControl(String isbnLimpio) {
        int suma = 0;
        if (isbnLimpio.length() == 10) {
            // Validación para ISBN-10
            for (int i = 0; i < 9; i++) {
                suma += (isbnLimpio.charAt(i) - '0') * (10 - i);
            }
            
            char ultimoCaracter = isbnLimpio.charAt(9);
            int digitoControl = (ultimoCaracter == 'X') ? 10 : (ultimoCaracter - '0');
            
            return (suma + digitoControl) % 11 == 0;
        } else {
            // Validación para ISBN-13
            for (int i = 0; i < 12; i++) {
                suma += (isbnLimpio.charAt(i) - '0') * (i % 2 == 0 ? 1 : 3);
            }
            
            int digitoControl = isbnLimpio.charAt(12) - '0';
            int digitoCalculado = (10 - (suma % 10)) % 10;
            
            return digitoControl == digitoCalculado;
        }
    }

    public void verificarTitulo(String titulo) {
        // Verificar si el título es válido
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío");
        }
    }

    public void verificarAutor(String autor) {
        // Verificar si el autor es válido
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío");
        }
    }
}
