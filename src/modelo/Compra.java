package modelo;

import interfaces.IPagable;

import java.time.LocalDateTime;
import java.util.List;

public class Compra implements IPagable {

    /*  ATRIBUTOS */
    private int id;
    private Cliente cliente;
    private List<DetalleCompra> detalles;
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

    /** METODO TO STRING**/
    @Override
    public String toString() {
        return "Compra:" +
                "id=" + id +
                ", cliente=" + cliente +
                ", detalles=" + detalles +
                ", fecha=" + fecha +
                ", total=" + total;
    }

    /** METODO DE LA INTERFACE**/

    @Override
    public double calcularTotal() {
        return 0;
    }

    @Override
    public void confirmarPago() {



    }
}
