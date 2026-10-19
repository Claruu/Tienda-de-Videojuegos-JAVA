package modelo;

public class Admin extends Persona {
    /* - - - - - - - Constructores - - - - - - - - - - -*/
    public Admin() {}

    public Admin(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo) {
        super(id, dni, nombre, usuario, contrasenia, activo);
    }

    public Admin(String dni, String nombre, String usuario, String contrasenia) {
        super(dni, nombre, usuario, contrasenia);
    }

    @Override
    public String getRol() {
        return "ADMIN";
    }
}
