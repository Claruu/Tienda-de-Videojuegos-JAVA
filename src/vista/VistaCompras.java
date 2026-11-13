package vista;

import excepciones.OperacionNoPermitidaException;
import excepciones.SaldoInsuficienteException;
import excepciones.StockInsuficienteException;
import modelo.Cliente;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.ItemCarrito;
import negocio.ServicioVentas;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class VistaCompras {
    private final ServicioVentas servicioVentas;
    private final Scanner scanner;

    public VistaCompras() {
        servicioVentas = new ServicioVentas();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu(Cliente cliente) {
        int opcion;

        do {
            System.out.println("\n--- COMPRAS ---");
            System.out.println("1. Ver carrito");
            System.out.println("2. Confirmar compra");
            System.out.println("3. Ver historial de compras");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    mostrarCarrito(cliente);
                    break;
                case 2:
                    confirmarCompra(cliente);
                    break;
                case 3:
                    mostrarHistorial(cliente);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void mostrarCarrito(Cliente cliente) {
        if (cliente.getCarrito().isEmpty()) {
            System.out.println("El carrito está vacío");
            return;
        }

        double total = 0;

        for (ItemCarrito item : cliente.getCarrito()) {
            System.out.println(
                    item.getProducto().getNombre() + " x " +
                            item.getCantidad() + " = $" + item.subTotal()
            );

            total += item.subTotal();
        }

        System.out.println("Total: $" + total);
    }

    private void confirmarCompra(Cliente cliente) {
        try {
            Compra compra = servicioVentas.realizarCompra(cliente);
            System.out.println(compra.generarComprobante());

        } catch (SaldoInsuficienteException | StockInsuficienteException |
                 OperacionNoPermitidaException e) {

            System.out.println(e.getMessage());

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la compra: " + e.getMessage());
        }
    }

    private void mostrarHistorial(Cliente cliente) {
        try {
            List<Compra> compras = servicioVentas.obtenerHistorialCliente(cliente.getId());

            if (compras.isEmpty()) {
                System.out.println("Todavía no hay compras registradas");
                return;
            }

            for (Compra compra : compras) {
                System.out.println(
                        "Compra #" + compra.getId() +
                                " - " + compra.getFecha() +
                                " - Total: $" + compra.getTotal()
                );

                for (DetalleCompra detalle : compra.getDetalles()) {
                    System.out.println(
                            detalle.getProducto().getNombre() +
                                    " x " + detalle.getCantidad()
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("No se pudo consultar el historial: " + e.getMessage());
        }
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}