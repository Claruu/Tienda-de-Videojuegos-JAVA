package persistencia;
import excepciones.DatoInvalidoException;
import modelo.Producto;
import modelo.Juego;
import modelo.Consola;
import java.sql.*;

public class ProductoDao {
    private Connection conexion;

    public ProductoDao(Connection conexion){
    this.conexion=conexion;
    }


    public void insertarProducto(Producto producto) throws SQLException {
        String sqlProducto = "INSERT INTO productos (nombre, precio, stock, tipo, activo) VALUES (?, ?, ?, ?, ?)";
        String sqlJuego = "INSERT INTO juegos(id_producto, genero, plataforma, edad_minima) VALUES(?, ?, ?, ?)";
        String sqlConsola = "INSERT INTO consolas(id_producto, fabricante, almacenamiento_gb, incluye_joystick) VALUES (?, ?, ?, ?)";

        // El DAO abre su propia conexión para esta operación puntual usando ConexionBD
        try (Connection conn = ConexionDB.conectar()) {

            conn.setAutoCommit(false);

            try (PreparedStatement psPadre = conn.prepareStatement(sqlProducto, Statement.RETURN_GENERATED_KEYS)) {

                psPadre.setString(1, producto.getNombre());
                psPadre.setDouble(2, producto.getPrecio());
                psPadre.setInt(3, producto.getStock());

                if (producto instanceof Juego) {
                    psPadre.setString(4, "JUEGO");
                } else if (producto instanceof Consola) {
                    psPadre.setString(4, "CONSOLA");
                }

                psPadre.setBoolean(5, producto.isActivo());
                psPadre.executeUpdate();

                // Recuperamos ID y hacemos el segundo insert
                try (ResultSet rs = psPadre.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        producto.setId(idGenerado); // Actualiza el objeto Java

                        if (producto instanceof Juego) {
                            Juego juego = (Juego) producto;
                            try (PreparedStatement psHija = conn.prepareStatement(sqlJuego)) {
                                psHija.setInt(1, idGenerado);
                                psHija.setString(2, juego.getGenero().name());
                                psHija.setString(3, juego.getPlataforma().name());
                                psHija.setInt(4, juego.getEdadMinima());
                                psHija.executeUpdate();
                            }
                        } else if (producto instanceof Consola) {
                            Consola consola = (Consola) producto;
                            try (PreparedStatement psHija = conn.prepareStatement(sqlConsola)) {
                                psHija.setInt(1, idGenerado);
                                psHija.setString(2, consola.getFabricante());
                                psHija.setDouble(3, consola.getAlmacenamientoGB()); // Corrección a setInt
                                psHija.setBoolean(4, consola.isIncluyeJoystick());
                                psHija.executeUpdate();
                            }
                        }
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new SQLException("Error al insertar producto, transacción revertida: " + e.getMessage(), e);
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }



}
