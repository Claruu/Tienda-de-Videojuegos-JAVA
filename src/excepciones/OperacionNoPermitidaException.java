package excepciones;

public class OperacionNoPermitidaException extends Exception {
    public OperacionNoPermitidaException(String mensaje) {
        super(mensaje);
    }
}