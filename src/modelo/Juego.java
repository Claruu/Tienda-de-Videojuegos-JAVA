package modelo;

import enums.Genero;
import enums.Plataforma;

public class Juego extends Producto{
    private Genero genero;
    private Plataforma plataforma;


    public Juego() {
    }

    public Juego(int id, String nombre, double precio, int stock, boolean activo, Genero genero, Plataforma plataforma) {
        super(id, nombre, precio, stock, activo);
        this.genero = genero;
        this.plataforma = plataforma;
    }

    public Juego(String nombre, double precio, int stock, Genero genero, Plataforma plataforma) {
        super(0, nombre, precio, stock, true);
        this.genero = genero;
        this.plataforma = plataforma;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

}
