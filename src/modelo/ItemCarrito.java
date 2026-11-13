package modelo;

import excepciones.DatoInvalidoException;

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
    public int sumarCantidades(int extra)throws DatoInvalidoException {
        if (extra <=0){
            throw new DatoInvalidoException("La cantidad a sumar debe ser mayor a 0");
        }
        return getCantidad()+extra;
    }

    @Override
    public String toString() {
        return "ItemCarrito{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                '}';
    }
}
