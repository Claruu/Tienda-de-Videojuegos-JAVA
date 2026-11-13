package modelo;

import enums.Plataforma;

public class Consola extends Producto{

    private String fabricante;
    private double almacenamientoGB;
    private boolean incluyeJoystick;


    public Consola() {
    }

    public Consola(int id, String nombre, double precio, int stock, boolean activo, String fabricante, double almacenamientoGB, boolean incluyeJoystick) {
        super(id, nombre, precio, stock, activo);
        this.fabricante = fabricante;
        this.almacenamientoGB = almacenamientoGB;
        this.incluyeJoystick = incluyeJoystick;
    }


    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    public void setAlmacenamientoGB(double almacenamientoGB) {
        this.almacenamientoGB = almacenamientoGB;
    }

    public boolean isIncluyeJoystick() {
        return incluyeJoystick;
    }

    public void setIncluyeJoystick(boolean incluyeJoystick) {
        this.incluyeJoystick = incluyeJoystick;
    }

    @Override
    public String getDetalleEspecifico() {
        return "Fabricante: " + fabricante
                + "\nAlmacenamiento: " + almacenamientoGB + " GB"
                + "\nIncluye joystick: " + (incluyeJoystick ? "Sí" : "No");
    }
}
