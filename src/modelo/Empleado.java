package modelo;

public class Empleado extends Persona {
    private String legajo;

    /* - - - - - - - Constructores - - - - - - - - - - -*/

    public Empleado() {
    }

    public Empleado(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo, String legajo) {
        super(id, dni, nombre, usuario, contrasenia, activo);
        this.legajo = legajo;
    }

    public Empleado(String dni, String nombre, String usuario, String contrasenia, String legajo) {
        super(dni, nombre, usuario, contrasenia);
        this.legajo = legajo;
    }

    /* - - - - - - Getters y Setters - - - - - - - - -*/


    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - -*/

    @Override public String getRol()
    {
        return "EMPLEADO";
    }
}
