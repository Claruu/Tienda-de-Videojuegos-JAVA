package interfaces;
import excepciones.OperacionNoPermitidaException;
import excepciones.SaldoInsuficienteException;
import excepciones.StockInsuficienteException;

public interface IPagable {
    double calcularTotal();

    void confirmarPago() throws SaldoInsuficienteException,
            StockInsuficienteException,
            OperacionNoPermitidaException;
}


