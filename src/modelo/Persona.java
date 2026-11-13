package modelo;

import excepciones.DatoInvalidoException;

import java.util.Objects;

public abstract class Persona {
    private int id;
    private String dni;
    private String nombre;
    private String usuario;
    private String contrasenia;
    private boolean activo;

    /* - - - - - - - Constructores - - - - - - - - - - -*/
    //constructor vacío
    public Persona() {
    }

    //constructor completo
    public Persona(int id, String dni, String nombre, String usuario, String contrasenia, boolean activo) throws DatoInvalidoException {
        this.id = id;
        setDni(dni);
        setNombre(nombre);
        setUsuario(usuario);
        setContrasenia(contrasenia);
        this.activo = activo;
    }

    //constructor sin ID para altas, activo en true
    public Persona(String dni, String nombre, String usuario, String contrasenia) throws DatoInvalidoException {
        this(0, dni, nombre, usuario, contrasenia, true);
    }
    /* - - - - - - - - - - - - - - - - - - - - - - - - -*/

    /* - - - - - - Getters y Setters - - - - - - - - -*/
    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DatoInvalidoException {
        if (dni == null || dni.trim().isEmpty()) {
            throw new DatoInvalidoException("El DNI no puede estar vacio");
        }
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws DatoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) throws DatoInvalidoException {
        if (usuario == null || usuario.trim().isEmpty()) {
            throw new DatoInvalidoException("El usuario no puede estar vacío");
        }
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) throws DatoInvalidoException {
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new DatoInvalidoException("La contraseña no puede estar vacía");
        }
        this.contrasenia = contrasenia;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - -*/

    //equals y hashcode con DNI
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return dni != null && dni.equals(persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDni());
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - -*/

    @Override
    public String toString() {
        return "ID: " + id + ", DNI: " + dni + ", nombre: " + nombre + ", usuario: " + usuario + ", rol: " + getRol() + ", activo: " + (activo ? "Sí" : "No");
    }

    public abstract String getRol();

}
