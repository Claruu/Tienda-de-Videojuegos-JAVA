package modelo;

import excepciones.OperacionNoPermitidaException;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int idCompra;
    private Compra compra;
    private boolean preparado;
    private LocalDateTime fechaPedido;
    private Empleado empleadoPreparador;

    /* - - - - - - - Constructores - - - - - - - - - - -*/

    public Pedido() {
    }

    public Pedido(int id, int idCompra, Compra compra, boolean preparado, LocalDateTime fechaPedido, Empleado empleadoPreparador)/*sumar Compra en el constructor cuando esté subida la entidad (TODO)*/ {
        this.id = id;
        this.idCompra = idCompra;
        this.compra = compra;
        this.preparado = preparado;
        this.fechaPedido = fechaPedido;
        this.empleadoPreparador = empleadoPreparador;
    }

    public Pedido(int id, int idCompra, Compra compra, LocalDateTime fechaPedido) {
        this.id = id;
        this.idCompra = idCompra;
        this.compra = compra;
        this.preparado = false;
        this.fechaPedido = fechaPedido;
        this.empleadoPreparador = null;
    }
    /* - - - - - - - Constructores - - - - - - - - - - -*/

    /* - - - - - - Getters y Setters - - - - - - - - -*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public boolean isPreparado() {
        return preparado;
    }

    public void setPreparado(boolean preparado) {
        this.preparado = preparado;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Empleado getEmpleadoPreparador() {
        return empleadoPreparador;
    }

    public void setEmpleadoPreparador(Empleado empleadoPreparador) {
        this.empleadoPreparador = empleadoPreparador;
    }
    /* - - - - - - Getters y Setters - - - - - - - - -*/

    public void marcarPreparado(Empleado e) throws OperacionNoPermitidaException {
        if (this.preparado) {
            throw new OperacionNoPermitidaException("El pedido ya fue preparado.");
        }
        this.preparado = true;
        this.empleadoPreparador = e;
    }

    public String getEstadoTexto() {
        if (this.preparado) {
            return "PREPARADO";
        } else {
            return "PENDIENTE";
        }
    }

    public String toString() {
        String info = "Pedido #" + id +
                " | Compra # " + idCompra +
                " | Fecha Pedido #" + fechaPedido +
                " | Estado: " + getEstadoTexto();

        if (preparado && empleadoPreparador != null) {
            info += " | Preparado por: " + empleadoPreparador.getNombre() + " (Legajo: " + empleadoPreparador.getLegajo() + ")";
        }

        return info;
    }
}
