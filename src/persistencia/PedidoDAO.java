package persistencia;

import modelo.Compra;
import modelo.Empleado;
import modelo.Pedido;
import modelo.Persona;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO {
    private final UsuarioDAO usuarioDAO;
    //TODO cuando esté hecho CompraDAO agregaré compraDAO

    public PedidoDAO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public PedidoDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    //TODO constructor PedidoDAO

    public void insertarPedido(Connection conn, Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (id_compra, preparado, fecha_pedido, id_empleado_preparador) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            int idCompra = pedido.getIdCompra();
            if ((idCompra <= 0) && (pedido.getCompra() != null)) {
                idCompra = pedido.getCompra().getId();
            }


            LocalDateTime fecha = (pedido.getFechaPedido() != null)
                    ? pedido.getFechaPedido()
                    : LocalDateTime.now();

            ps.setInt(1, idCompra);
            ps.setBoolean(2, pedido.isPreparado());
            ps.setTimestamp(3, Timestamp.valueOf(fecha));

            if (pedido.getEmpleadoPreparador() != null) {
                ps.setInt(4, pedido.getEmpleadoPreparador().getId());
            } else {
                ps.setNull(4, Types.INTEGER);
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setId(rs.getInt(1));
                }
            }

        }
    }

    public Pedido obtenerPorId(int idPedido) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPedido);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPedido(rs);
                }
            }
        }

        return null;
    }

    private Pedido mapearPedido(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idCompra = rs.getInt("id_compra");
        boolean preparado = rs.getBoolean("preparado");
        Timestamp ts = rs.getTimestamp("fecha_pedido");
        LocalDateTime fechaPedido = (ts != null) ? ts.toLocalDateTime() : null;
        Empleado empleadoPreparador = null;
        int idEmpleado = rs.getInt("id_empleado_preparador");
        if (!rs.wasNull()) {
            Persona persona = usuarioDAO.obtenerPorId(idEmpleado);
            if (persona instanceof Empleado) {
                empleadoPreparador = (Empleado) persona;
            }
        }
        // TODO: necesito compraDAO
        Compra compra = null;
        return new Pedido(id, idCompra, compra, preparado, fechaPedido, empleadoPreparador);
    }
}

