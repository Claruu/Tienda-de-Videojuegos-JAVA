package modelo;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int idCompra;
    //private Compra compra; /*Necesito Compra para poder hacer esto*/
    private boolean preparado;
    private LocalDateTime fechaPedido;
    private Empleado empleadoPreparador;
}
