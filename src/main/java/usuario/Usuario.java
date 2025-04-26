package usuario;

import prestamo.Prestamo;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private ArrayList<Prestamo> historialPrestamos;

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.historialPrestamos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Prestamo> getHistorialPrestamos() {
        return historialPrestamos;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return historialPrestamos;
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser nulo");
        }
        historialPrestamos.add(prestamo);
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser nulo");
        }
        historialPrestamos.remove(prestamo);
    }


}
