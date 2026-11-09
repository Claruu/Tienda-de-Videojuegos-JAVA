package modelo;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito() {
    }

    public ItemCarrito(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
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

  //Metodos

    public double subTotal(){
        return producto.getPrecio()* cantidad;
    }

    //extra seria la cantidad que le queremos sumar ya existentes
    public int sumarCantidades(int extra){
        return getCantidad()+extra;
    }


}
