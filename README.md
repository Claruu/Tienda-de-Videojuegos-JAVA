# Tienda de Videojuegos - TP Programación II

TP final de Programación II de la UTN Facultad Regional Mar del Plata. Es un programa de consola en Java para manejar
una tienda de videojuegos y consolas: catálogo, favoritos, carrito, ventas, saldo y pedidos.

## Integrantes

- **Clara Domínguez:** usuarios, login, roles y pedidos.
- **Nahuel Saffadi:** catálogo, productos, enums y carrito base.
- **Agustín Vallejos:** ventas, pagos, transacción JDBC e historial de compras.

## Cómo funciona

### Cliente

- Se loguea o se registra.
- Ve el catálogo con el stock disponible.
- Busca por ID o por nombre, y filtra juegos o consolas.
- Maneja una lista de deseos y un carrito con cantidades.
- Puede pasar un producto de la lista de deseos al carrito.
- Carga saldo en su cuenta.
- Confirma la compra si hay stock y saldo. Recibe un comprobante por consola.
- Ve su historial de compras y el estado de sus pedidos.

### Empleado

- Ve el catálogo y el stock.
- Ve los pedidos pendientes con el detalle de productos y cantidades.
- Marca un pedido como preparado. Queda registrado qué empleado lo preparó.
- Ve el historial general de pedidos preparados y los que preparó él mismo.

### Admin

- Hace el ABMCL de usuarios y de productos.
- Ve el catálogo completo, el historial general de ventas y el listado general de pedidos.

## Estructura

```text
TiendaVideojuegos/
├── README.md
├── .gitignore
├── docs/
│   └── uml/
│       └── tienda-videojuegos.mmd
├── sql/ # Scripts de creación de esquemas y precarga de datos
│   ├── schema.sql # Estructura relacional de tablas
│   └── datos_iniciales.sql # Precarga de usuarios, catálogo y transacciones de prueba
└── src/
    ├── enums/ # Enums (Género, Plataformas)
    ├── excepciones/ # Manejo de errores de negocio y validaciones
    ├── interfaces/ # Interfaces utilizadas
    ├── modelo/ # Clases entidad y modelos POJO
    ├── negocio/ # Servicios, validaciones y coordinación de transacciones
    ├── persistencia/ # Objetos DAO y gestión de conexión JDBC
    ├── vista/ # Menús de consola interactivos por rol de usuario
    └── Main.java # Punto de entrada de la aplicación
```