package persistencia;

import enums.Genero;
import enums.Plataforma;
import modelo.Cliente;
import modelo.Compra;
import modelo.Consola;
import modelo.DetalleCompra;
import modelo.Juego;
import modelo.Persona;
import modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void insertarCompraYDetalles(Connection conn, Compra compra) throws SQLException {
        String sqlCompra = "INSERT INTO compras (id_cliente, fecha, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_compra (id_compra, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, compra.getCliente().getId());
            ps.setTimestamp(2, Timestamp.valueOf(compra.getFecha()));
            ps.setDouble(3, compra.getTotal());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    compra.setId(rs.getInt(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID de la compra");
                }
            }
        }

        try (PreparedStatement ps = conn.prepareStatement(sqlDetalle)) {
            for (DetalleCompra detalle : compra.getDetalles()) {
                ps.setInt(1, compra.getId());
                ps.setInt(2, detalle.getProducto().getId());
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioUnitario());
                ps.addBatch();
            }

            ps.executeBatch();
        }
    }

    public Compra obtenerPorId(int id) throws SQLException {
        try (Connection conn = ConexionDB.conectar()) {
            return obtenerPorId(conn, id);
        }
    }

    private Compra obtenerPorId(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM compras WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Compra compra = mapearCompra(rs);
                    compra.setDetalles(obtenerDetalles(conn, id));
                    return compra;
                }
            }
        }

        return null;
    }

    public List<Compra> listarHistorialCliente(int idCliente) throws SQLException {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT id FROM compras WHERE id_cliente = ? ORDER BY fecha DESC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Compra compra = obtenerPorId(conn, rs.getInt("id"));

                    if (compra != null) {
                        compras.add(compra);
                    }
                }
            }
        }

        return compras;
    }

    public List<Compra> listarTodasLasVentas() throws SQLException {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT id FROM compras ORDER BY fecha DESC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = obtenerPorId(conn, rs.getInt("id"));

                if (compra != null) {
                    compras.add(compra);
                }
            }
        }

        return compras;
    }

    private Compra mapearCompra(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idCliente = rs.getInt("id_cliente");

        Persona persona = usuarioDAO.obtenerPorId(idCliente);

        if (!(persona instanceof Cliente)) {
            throw new SQLException("El cliente de la compra no existe");
        }

        Timestamp fecha = rs.getTimestamp("fecha");
        LocalDateTime fechaCompra = fecha == null ? null : fecha.toLocalDateTime();

        return new Compra(
                id,
                (Cliente) persona,
                new ArrayList<>(),
                fechaCompra,
                rs.getDouble("total")
        );
    }

    private List<DetalleCompra> obtenerDetalles(Connection conn, int idCompra) throws SQLException {
        List<DetalleCompra> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detalle_compra WHERE id_compra = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("id_producto");
                    Producto producto = obtenerProducto(conn, idProducto);

                    detalles.add(new DetalleCompra(
                            rs.getInt("id"),
                            idCompra,
                            producto,
                            rs.getInt("cantidad"),
                            rs.getDouble("precio_unitario")
                    ));
                }
            }
        }

        return detalles;
    }

    private Producto obtenerProducto(Connection conn, int id) throws SQLException {
        String sql = "SELECT p.*, j.genero, j.plataforma, j.edad_minima, " +
                "c.fabricante, c.almacenamiento_gb, c.incluye_joystick " +
                "FROM productos p " +
                "LEFT JOIN juegos j ON p.id = j.id_producto " +
                "LEFT JOIN consolas c ON p.id = c.id_producto " +
                "WHERE p.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idProducto = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    double precio = rs.getDouble("precio");
                    int stock = rs.getInt("stock");
                    boolean activo = rs.getBoolean("activo");
                    String tipo = rs.getString("tipo");

                    if ("JUEGO".equals(tipo)) {
                        return new Juego(
                                idProducto,
                                nombre,
                                precio,
                                stock,
                                activo,
                                Genero.valueOf(rs.getString("genero")),
                                Plataforma.valueOf(rs.getString("plataforma")),
                                rs.getInt("edad_minima")
                        );
                    }

                    return new Consola(
                            idProducto,
                            nombre,
                            precio,
                            stock,
                            activo,
                            rs.getString("fabricante"),
                            rs.getDouble("almacenamiento_gb"),
                            rs.getBoolean("incluye_joystick")
                    );
                }
            }
        }

        return null;
    }
}