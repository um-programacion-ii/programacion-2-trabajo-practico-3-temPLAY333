package libro;

import prestamo.Prestamo;

public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private Prestamo estado;

    public Libro(String ISBN, String titulo, String autor) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Libro libro = (Libro) obj;
        return this.ISBN.equals(libro.ISBN) &&
                this.titulo.equals(libro.titulo) &&
                this.autor.equals(libro.autor) &&
                this.estado == libro.estado;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Prestamo getEstado() {
        return estado;
    }

    public void setEstado(Prestamo estado) {
        this.estado = estado;
    }

    public boolean estaDisponible() {
        return estado == null;
    }
}
