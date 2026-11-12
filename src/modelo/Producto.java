package modelo;

import excepciones.DatoInvalidoException;
import excepciones.StockInsuficienteException;

import java.util.Objects;

public abstract class Producto {
    private int id ;
    private String nombre;
    private int stock;
    private double precio;
    private boolean activo;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio, int stock, boolean activo) {
        this.id = id; //esta es la ID que usaremos para la bdd, si hacemos un contador static, se pisará la ID autoincremental de la bdd
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.activo = activo; //para baja logica
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    // metodos
    public int hayStock(){
        int stock1=stock;
        if (stock1>0){
            return stock1;
        }else {
            return 0;
        }

    }

    public void descontarStock(int cantidad)throws DatoInvalidoException, StockInsuficienteException {
        if (cantidad<=0){
            throw new DatoInvalidoException("La cantidad a descontar debe ser menor a 0");
        }

        if(cantidad>stock){
            throw new StockInsuficienteException("No hay stock suficiente ");
        }

        stock-=cantidad;
    }

    public abstract String getDetalleEspecifico();

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", activo=" + activo +
                '}';
    }
}
