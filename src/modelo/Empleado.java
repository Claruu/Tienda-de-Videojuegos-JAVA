package modelo;

import excepciones.DatoInvalidoException;

public class Empleado extends Persona {
    private String legajo;

    /* - - - - - - - Constructores - - - - - - - - - - -*/

    public Empleado() {
    }

    public Empleado(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo, String legajo) throws DatoInvalidoException {
        super(id, dni, nombre, usuario, contrasenia, activo);
        setLegajo(legajo);
    }

    public Empleado(String dni, String nombre, String usuario, String contrasenia, String legajo) throws DatoInvalidoException {
        super(dni, nombre, usuario, contrasenia);
        setLegajo(legajo);
    }

    /* - - - - - - Getters y Setters - - - - - - - - -*/


    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) throws DatoInvalidoException {
        if (legajo == null || legajo.trim().isEmpty()) {
            throw new DatoInvalidoException("El legajo no puede estar vacio");
        }
        this.legajo = legajo;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - -*/

    @Override
    public String getRol() {
        return "EMPLEADO";
    }
}
