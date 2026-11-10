package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public class GestionDB {
        private static final String url = "jdbc:mysql://localhost:3306/prueba";
        private static final String user = "root";
        private static final String pass = "prueba";

        public static Connection conectar()throws SQLException {
            return DriverManager.getConnection(url,user,pass);
        }

    }
}
