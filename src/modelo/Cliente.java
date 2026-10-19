package modelo;

import excepciones.DatoInvalidoException;
import excepciones.OperacionNoPermitidaException;
import excepciones.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Cliente extends Persona{
    private double saldo;
    // private List <ItemCarrito> carrito;
    //private Set<Producto> favoritos;

    /* - - - - - - - Constructores - - - - - - - - - - -*/
    public Cliente(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo, double saldo) {
        super(id, dni, nombre, usuario, contrasenia, activo);
        this.saldo = saldo;
       // this.carrito = new ArrayList<>();
        //this.favoritos = new HashSet<>();
    }

    public Cliente(String dni, String nombre, String usuario, String contrasenia) {
        super(dni, nombre, usuario, contrasenia);
        this.saldo = 0.0;
        //this.carrito = new ArrayList<>();
        //this.favoritos = new HashSet<>();
    }

    /* - - - - - - Getters y Setters - - - - - - - - -*/
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

   // public List<ItemCarrito> getCarrito() { return carrito; }

    //public Set<Producto> getFavoritos() { return favoritos; }

    @Override
    public String getRol() {
        return "CLIENTE";
    }

    public void cargarSaldo(double monto) throws DatoInvalidoException {
        // TODO
    }

    public void descontarSaldo(double monto) throws DatoInvalidoException, SaldoInsuficienteException {
        // TODO
    }

   /* public void agregarAlCarrito(Producto p, int cantidad) throws OperacionNoPermitidaException, DatoInvalidoException {
        // TODO, me falta Producto
    }*/

    public void quitarDelCarrito(int idProducto) {
        // TODO
    }

   /* public void vaciarCarrito() {
        carrito.clear();
    }*/

  /*  public boolean agregarAFavoritos(Producto p) {
        // TODO
        return false;
    }*/

    public void quitarDeFavoritos(int idProducto) {
        // TODO
    }

    public void pasarFavoritoACarrito(int idProducto, int cantidad) throws OperacionNoPermitidaException, DatoInvalidoException {
        // TODO
    }
}
