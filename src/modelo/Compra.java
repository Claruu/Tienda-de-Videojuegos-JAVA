package modelo;

import excepciones.OperacionNoPermitidaException;
import excepciones.SaldoInsuficienteException;
import excepciones.StockInsuficienteException;
import interfaces.IPagable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Compra implements IPagable {

    /*  ATRIBUTOS */
    private int id;
    private Cliente cliente;
    private List<DetalleCompra> detalles = new ArrayList<>();
    private LocalDateTime fecha;
    private double total;


    // CONSTRUCTORES


    public Compra() {
    }

    public Compra(int id, Cliente cliente, List<DetalleCompra> detalles, LocalDateTime fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.detalles = detalles;
        this.fecha = fecha;
        this.total = total;
    }

    // GETTERS AND SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCompra> detalles) {
        this.detalles = detalles;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * METODO TO STRING
     **/
    @Override
    public String toString() {
        return "Compra:" +
                "id=" + id +
                ", cliente=" + cliente +
                ", detalles=" + detalles +
                ", fecha=" + fecha +
                ", total=" + total;
    }

    /**
     * METODO DE LA INTERFACE
     **/

    @Override
    public double calcularTotal() {
        double resultado = 0;

        for (DetalleCompra detalle : detalles) {
            resultado = resultado + detalle.calcularSubtotal();
        }
        return resultado;
    }

    @Override
    public void confirmarPago() throws SaldoInsuficienteException,
            StockInsuficienteException,
            OperacionNoPermitidaException {

        if (detalles.isEmpty()) {
            throw new OperacionNoPermitidaException(
                    "El carrito de compras se encuentra vacio"
            );
        }

        for (DetalleCompra detalle : detalles) {

            Producto producto = detalle.getProducto();

            if (producto == null || !producto.isActivo()) {
                throw new OperacionNoPermitidaException(
                        "Uno de los productos ya no está disponible.");
            }

            if (producto.getStock() < detalle.getCantidad()) {
                throw new StockInsuficienteException(
                        "En este momento no hay stock suficiente del producto " + producto.getNombre() + ", gracias por su comprensión.");
            }
        }

        total = calcularTotal();

        if (cliente.getSaldo() < total) {
            throw new SaldoInsuficienteException(
                    "El saldo actual es insuficiente para realizar esta compra");
        }
    }

    public String generarComprobante() {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String comprobante = "========== TIENDA DE VIDEOJUEGOS ==========\n" +
                "Compra N°: " + id + "\n" +
                "Cliente: " + cliente.getNombre() + "\n" +
                "Fecha: " + fecha.format(formato) + "\n"
                + "-------------------------------------------\n";

        for (DetalleCompra detalle : detalles) {
            comprobante += detalle.toString() + "\n";
        }

        comprobante += "-------------------------------------------\n" +
                "TOTAL: $" + total + "\n" +
                "Gracias por su compra.";
        return comprobante;
    }


}

