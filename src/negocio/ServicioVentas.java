package negocio;

import excepciones.OperacionNoPermitidaException;
import excepciones.SaldoInsuficienteException;
import excepciones.StockInsuficienteException;
import modelo.Cliente;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.ItemCarrito;
import modelo.Pedido;
import persistencia.CompraDAO;
import persistencia.ConexionDB;
import persistencia.PedidoDAO;
import persistencia.UsuarioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioVentas {
    private final CompraDAO compraDAO;
    private final UsuarioDAO usuarioDAO;
    private final PedidoDAO pedidoDAO;

    public ServicioVentas() {
        compraDAO = new CompraDAO();
        usuarioDAO = new UsuarioDAO();
        pedidoDAO = new PedidoDAO(usuarioDAO);
    }

    public Compra realizarCompra(Cliente cliente) throws SQLException, SaldoInsuficienteException,
            StockInsuficienteException, OperacionNoPermitidaException {

        if (cliente == null || !cliente.isActivo()) {
            throw new OperacionNoPermitidaException("El cliente no está disponible");
        }

        if (cliente.getCarrito().isEmpty()) {
            throw new OperacionNoPermitidaException("El carrito está vacío");
        }

        List<DetalleCompra> detalles = new ArrayList<>();

        for (ItemCarrito item : cliente.getCarrito()) {
            if (item.getProducto() == null) {
                throw new OperacionNoPermitidaException("Hay un producto inválido en el carrito");
            }

            detalles.add(new DetalleCompra(
                    0,
                    item.getProducto(),
                    item.getCantidad(),
                    item.getProducto().getPrecio()
            ));
        }

        Compra compra = new Compra(0, cliente, detalles, LocalDateTime.now(), 0);
        compra.confirmarPago();

        try (Connection conn = ConexionDB.conectar()) {
            conn.setAutoCommit(false);

            try {
                for (DetalleCompra detalle : detalles) {
                    String sql = "UPDATE productos SET stock = stock - ? WHERE id = ? AND activo = TRUE AND stock >= ?";

                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, detalle.getCantidad());
                        ps.setInt(2, detalle.getProducto().getId());
                        ps.setInt(3, detalle.getCantidad());

                        if (ps.executeUpdate() == 0) {
                            throw new StockInsuficienteException(
                                    "No hay stock suficiente de " + detalle.getProducto().getNombre()
                            );
                        }
                    }
                }

                double nuevoSaldo = cliente.getSaldo() - compra.getTotal();

                usuarioDAO.actualizarSaldo(conn, cliente.getId(), nuevoSaldo);
                compraDAO.insertarCompraYDetalles(conn, compra);

                pedidoDAO.insertarPedido(
                        conn,
                        new Pedido(compra.getId(), compra, LocalDateTime.now())
                );

                conn.commit();

                cliente.setSaldo(nuevoSaldo);

                for (DetalleCompra detalle : detalles) {
                    detalle.getProducto().setStock(
                            detalle.getProducto().getStock() - detalle.getCantidad()
                    );
                }

                cliente.vaciarCarrito();

                return compra;

            } catch (SQLException | StockInsuficienteException e) {
                conn.rollback();
                throw e;
            } catch (RuntimeException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }

    public List<Compra> obtenerHistorialCliente(int idCliente) throws SQLException {
        return compraDAO.listarHistorialCliente(idCliente);
    }

    public List<Compra> obtenerTodasLasVentas() throws SQLException {
        return compraDAO.listarTodasLasVentas();
    }
}
