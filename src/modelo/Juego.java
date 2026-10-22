package modelo;

import enums.Genero;
import enums.Plataforma;

public class Juego extends Producto{
    private Genero genero;
    private Plataforma plataforma;


    public Juego() {
    }

    public Juego(int id, String nombre, int stock, int precio, Genero genero, Plataforma plataforma) {
        super(id, nombre, stock, precio);
        this.genero = genero;
        this.plataforma = plataforma;
    }

}
