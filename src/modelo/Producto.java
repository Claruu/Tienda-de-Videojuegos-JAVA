package modelo;

import java.util.Objects;

public abstract class Producto {
    private int id ;
    private String nombre;
    private static int contadorid;
    private int stock;
    private int precio;


    public Producto() {
        this.id = ++contadorid;

    }

    public Producto(int id, String nombre, int stock, int precio) {
        this.id = ++ contadorid;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
