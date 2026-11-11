package modelo;

import excepciones.DatoInvalidoException;

public class Admin extends Persona {
    /* - - - - - - - Constructores - - - - - - - - - - -*/
    public Admin() {
    }

    public Admin(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo) throws DatoInvalidoException {
        super(id, dni, nombre, usuario, contrasenia, activo);
    }

    public Admin(String dni, String nombre, String usuario, String contrasenia) throws DatoInvalidoException {
        super(dni, nombre, usuario, contrasenia);
    }

    @Override
    public String getRol() {
        return "ADMIN";
    }
}
