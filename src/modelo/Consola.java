package modelo;

import enums.Plataforma;

public class Consola extends Producto{

    private Plataforma plataforma;

    public Consola() {
    }

    public Consola(int id, String nombre, double precio, int stock, boolean activo, Plataforma plataforma) {
        super(id, nombre, precio, stock, activo);
        this.plataforma = plataforma;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return "Consola{" +
                "plataforma=" + plataforma +
                "} " + super.toString();
    }


}
