package persistencia;

import excepciones.DatoInvalidoException;
import modelo.Admin;
import modelo.Empleado;
import modelo.Cliente;
import modelo.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Persona mapearPersona(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String dni = rs.getString("dni");
        String nombre = rs.getString("nombre");
        String usuario = rs.getString("usuario");
        String contrasenia = rs.getString("contrasenia");
        String rol = rs.getString("rol");
        String legajo = rs.getString("legajo");
        double saldo = rs.getDouble("saldo");
        boolean activo = rs.getBoolean("activo");

        try {
            switch (rol) {
                case "ADMIN":
                    return new Admin(id, dni, nombre, usuario, contrasenia, activo);
                case "EMPLEADO":
                    return new Empleado(id, dni, nombre, usuario, contrasenia, activo, legajo);
                case "CLIENTE":
                    return new Cliente(id, dni, nombre, usuario, contrasenia, activo, saldo);
                default:
                    throw new SQLException("Rol desconocido: " + rol);
            }
        } catch (DatoInvalidoException e) {
            throw new SQLException("Datos invalidos en la BD para el usuario con ID " + id, e);
        }


    }

    public Persona buscarPorCredenciales(String usuario, String contrasenia) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasenia = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPersona(rs);
                }
            }
        }

        return null;
    }

    public int insertarUsuario(Persona persona) throws SQLException {
        String sql = "INSERT INTO usuarios (dni, nombre, usuario, contrasenia, rol, legajo, saldo, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, persona.getDni());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getUsuario());
            ps.setString(4, persona.getContrasenia());
            ps.setString(5, persona.getRol());


            if (persona instanceof Empleado) {
                ps.setString(6, ((Empleado) persona).getLegajo());
            } else {
                ps.setNull(6, Types.VARCHAR);
            }

            if (persona instanceof Cliente) {
                ps.setDouble(7, ((Cliente) persona).getSaldo());
            } else {
                ps.setDouble(7, 0.0);
            }

            ps.setBoolean(8, persona.isActivo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    persona.setId(idGenerado);
                    return idGenerado;
                }
            }

        }

        return -1;
    }


    public Persona obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPersona(rs);
                }
            }
        }

        return null;
    }

    public List<Persona> listarTodos() throws SQLException {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearPersona(rs));
            }
        }

        return lista;
    }

    public List<Persona> listarPorRol(String rol) throws SQLException {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE rol = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, rol);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPersona(rs));
                }
            }
        }

        return lista;
    }

    public void modificarUsuario(Persona persona) throws SQLException {
        String sql = "UPDATE usuarios SET dni = ?, nombre = ?, usuario = ?, contrasenia = ?, rol = ?, legajo= ?, saldo= ?, activo = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getDni());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getUsuario());
            ps.setString(4, persona.getContrasenia());
            ps.setString(5, persona.getRol());

            if (persona instanceof Empleado) {
                ps.setString(6, ((Empleado) persona).getLegajo());
            } else {
                ps.setNull(6, Types.VARCHAR);
            }

            if (persona instanceof Cliente) {
                ps.setDouble(7, ((Cliente) persona).getSaldo());
            } else {
                ps.setDouble(7, 0.0);
            }


            ps.setBoolean(8, persona.isActivo());
            ps.setInt(9, persona.getId());
            ps.executeUpdate();

        }
    }

    public void cambiarEstadoActivo(int id, boolean activo) throws SQLException {
        String sql = "UPDATE usuarios SET activo = ? WHERE id = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, activo);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void actualizarSaldo(int idCliente, double nuevoSaldo) throws SQLException {
        try (Connection con = ConexionDB.conectar()) {
            actualizarSaldo(con, idCliente, nuevoSaldo);
        }
    }

    public void actualizarSaldo(Connection conn, int idCliente, double nuevoSaldo) throws SQLException {
        String sql = "UPDATE usuarios SET saldo = ? WHERE id = ? AND rol = 'CLIENTE'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, idCliente);
            ps.executeUpdate();
        }
    }


}
