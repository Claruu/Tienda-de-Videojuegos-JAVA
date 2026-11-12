package negocio;

import modelo.Producto;
import persistencia.ProductoDao;

import java.util.Map;

public class ServicioCatalogo   {
    private Map<Integer, Producto> catalogo;
    private ProductoDao productoDao;

}
