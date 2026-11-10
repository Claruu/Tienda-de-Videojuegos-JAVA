package modelo;

import excepciones.DatoInvalidoException;
import excepciones.OperacionNoPermitidaException;
import excepciones.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cliente extends Persona {
    private double saldo;
    private List<ItemCarrito> carrito = new ArrayList<>();
    private Set<Producto> favoritos = new HashSet<>();

    /* - - - - - - - Constructores - - - - - - - - - - -*/
    public Cliente(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo, double saldo) {
        super(id, dni, nombre, usuario, contrasenia, activo);
        this.saldo = saldo;
    }

    public Cliente(String dni, String nombre, String usuario, String contrasenia) {
        super(dni, nombre, usuario, contrasenia);
        this.saldo = 0.0;
    }

    /* - - - - - - Getters y Setters - - - - - - - - -*/
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<ItemCarrito> getCarrito() {
        return carrito;
    }

    public Set<Producto> getFavoritos() {
        return favoritos;
    }

    @Override
    public String getRol() {
        return "CLIENTE";
    }

    public void cargarSaldo(double monto) throws DatoInvalidoException {
        if (monto <= 0) {
            throw new DatoInvalidoException("El monto a cargar debe ser mayor a 0");
        }
        saldo += monto;
    }

    public void descontarSaldo(double monto) throws DatoInvalidoException, SaldoInsuficienteException {
        if (monto <= 0) {
            throw new DatoInvalidoException("El monto a descontar debe ser mayor a 0");
        }
        if (saldo < monto) {
            throw new SaldoInsuficienteException("Su saldo es insuficiente como para poder descontar tal monto.");
        }
        saldo -= monto;
    }

    public void agregarAlCarrito(Producto p, int cantidad) throws OperacionNoPermitidaException, DatoInvalidoException {
        //excepciones en carga al producto, se analiza si se intenta agregar un producto con cantidad mayor a 0, y si el producto a agregar al carrito está activo y no es nulo.
        if (cantidad <= 0) {
            throw new DatoInvalidoException("No se puede agregar al carrito un producto con cantidad menor a 1.");
        }
        if (p == null || !p.isActivo()) {
            throw new OperacionNoPermitidaException("No se puede agregar al carrito un producto inactivo o inexistente.");
        }

        boolean yaExiste = false;
        for (ItemCarrito item : carrito) {
            if (item.getProducto().equals(p)) {
                item.setCantidad(item.getCantidad() + cantidad);
                yaExiste = true;
                break;
            }
        }

        if (!yaExiste) {
            carrito.add(new ItemCarrito(cantidad, p));
        }

    }

    public Producto buscarFavoritoPorId(int idProducto) {
        for (Producto p : favoritos) {
            if (p.getId() == idProducto) {
                return p;
            }
        }
        return null;
    }


    public void quitarDelCarrito(int idProducto) {
        ItemCarrito aEliminar = null;
        for (ItemCarrito item : carrito) {
            if (item.getProducto() != null && item.getProducto().getId() == idProducto) {
                aEliminar = item;
                break;
            }
        }

        if (aEliminar != null) {
            carrito.remove(aEliminar);
        }
    }

    public void vaciarCarrito() {
        carrito.clear();
    }

    public boolean agregarAFavoritos(Producto p) throws OperacionNoPermitidaException {
        if (p == null || !p.isActivo()) {
            throw new OperacionNoPermitidaException("No se puede agregar a la lista de Favoritos un producto inactivo.");
        }

        return favoritos.add(p);
    }

    public void quitarDeFavoritos(int idProducto) {
        Producto p = buscarFavoritoPorId(idProducto);

        if (p != null) {
            favoritos.remove(p);
        }
    }

    public void pasarFavoritoACarrito(int idProducto, int cantidad) throws OperacionNoPermitidaException, DatoInvalidoException {
        Producto p = buscarFavoritoPorId(idProducto);
        if (p != null) {
            agregarAlCarrito(p, cantidad);
            favoritos.remove(p);
        } else {
            throw new DatoInvalidoException("El producto con ID " + idProducto + " no está en tu lista de favoritos");
        }
    }
}
