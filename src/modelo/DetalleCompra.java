package modelo;

public class DetalleCompra {
    private int idCompra;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    // Constructores

    public DetalleCompra() {
    }

    public DetalleCompra(int idCompra, Producto producto, int cantidad, double precioUnitario) {
        this.idCompra = idCompra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /// GETTERS AND SETTERS

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double CalcularSubtotal(){

    }
}
