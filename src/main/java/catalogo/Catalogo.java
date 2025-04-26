package catalogo;

import comun.excepciones.LibroExcepcion;
import libro.Libro;

import java.util.ArrayList;

public class Catalogo {
    private static Catalogo instance;
    private ArrayList<Libro> libros = new ArrayList<>();

    private Catalogo() {
    }

    public static Catalogo getInstance() {
        if (instance == null) {
            instance = new Catalogo();
        }
        return instance;
    }

    public void agregarLibro(Libro libro) {
        if (libro == null) {
            throw new LibroExcepcion("El libro no puede ser nulo");
        } else if (libros.stream().anyMatch(l -> l.getISBN().equals(libro.getISBN()))) {
            throw new LibroExcepcion("Ese libro ya existe en el catálogo");
        }
        libros.add(libro);
    }

    public void eliminarLibro(Libro libro) {
        if (libro == null) {
            throw new LibroExcepcion("El libro no puede ser nulo");
        } else if (libros.stream().noneMatch(libro::equals)) {
            throw new LibroExcepcion("El libro no existe en el catálogo");
        } else {
            libros.removeIf(libro::equals);
        }
    }

    public Libro buscarLibroPorTitulo (String titulo) {
        return libros.stream()
            .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElseThrow(() -> new LibroExcepcion("Un libro con ese titulo no existe en el catálogo"));
    }

    public Libro buscarLibroPorISBN(String ISBN) {
        return libros.stream()
            .filter(libro -> libro.getISBN().equals(ISBN))
            .findFirst()
            .orElseThrow(() -> new LibroExcepcion("Un libro con ese ISBN no existe en el catálogo"));
    }

    public Libro buscarLibroPorAutor(String autor) {
        return libros.stream()
            .filter(libro -> libro.getAutor().equalsIgnoreCase(autor))
            .findFirst()
            .orElseThrow(() -> new LibroExcepcion("Un libro con ese autor no existe en el catálogo"));
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }
}