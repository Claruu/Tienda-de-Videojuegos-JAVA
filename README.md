# Tienda de Videojuegos - Sistema de Gestión y Ventas

## Propósito del Proyecto
Sistema de gestión y ventas para una tienda de videojuegos desarrollado en Java. 
TP Final Programación II - UTN.

---

## Contexto Académico

- **Institución:** Universidad Tecnológica Nacional (UTN) - Facultad Regional Mar del Plata
- **Asignatura:** Programación II
- **Equipo de desarrollo:**
  - **Agustin Armando Vallejos:** Desarrollo del módulo de ventas, lógica de pagos, transacciones JDBC e historial de compras.
  - **Clara Domínguez:** Desarrollo del módulo de usuarios (roles y autenticación), logística de pedidos y menús de administración/empleados.
  - **Nahuel David Saffadi:** Desarrollo del catálogo de productos, gestión del carrito de compras, configuración de base de datos y mantenimiento de inventario.
    
---

## Tecnologías Utilizadas

- **Lenguaje:** Java (POO, Colecciones, Excepciones personalizadas)
- **Persistencia:** JDBC + Base de Datos Relacional
- **Diseño:** Diagramado UML
- **Patrones/Arquitectura:** Capas (Modelo, Vista, Negocio, Persistencia, Interfaces, Excepciones)

---

## Características Principales

### Experiencia para Clientes
- **Catálogo interactivo:** Exploración de títulos y plataformas con información de stock en tiempo real.
- **Organización personalizada:** Gestión de listas de favoritos y armado de carritos con cantidades dinámicas.
- **Autogestión de compras:** Carga de saldo en cuenta y procesamiento de transacciones con validación de stock y emisión de comprobantes.
- **Seguimiento:** Consulta del historial de compras y visualización del estado de entrega de cada pedido.

### Gestión Logística (Empleados)
- **Control de pedidos:** Visualización en tiempo real de las órdenes generadas automáticamente por las ventas.
- **Despacho y preparación:** Registro del empleado interviniente y actualización de estado operativo de cada solicitud ("Pendiente" a "Preparado").
- **Auditoría:** Historial de pedidos despachados agrupados por responsable.

### Administración y Control
- **Mantenimiento del catálogo:** ABMCL (Altas, bajas lógicas, modificaciones y consultas) de artículos y usuarios.
- **Administración de cuentas:** Control de accesos y roles en el sistema (Admin, Empleado, Cliente).
- **Reportes globales:** Visibilidad integral del historial general de ventas y pedidos consolidados.

---

## Organización del Repositorio

El código fuente está estructurado por capas para separar responsabilidades lógicas:

```text
TiendaVideojuegos/
├── docs/                   # Plan de trabajo y diagrama UML
├── sql/                    # Scripts de creación de esquemas y precarga de datos
│   ├── schema.sql          # Estructura relacional de tablas
│   └── datos_iniciales.sql # Precarga de usuarios, catálogo y transacciones de prueba
└── src/                    
    ├── enums/              # Enums (Género, Plataformas)
    ├── excepciones/        # Manejo de errores de negocio y validaciones
    ├── interfaces/         # Interfaces utilizadas
    ├── modelo/             # Clases entidad y modelos POJO
    ├── negocio/            # Servicios, validaciones y coordinación de transacciones
    ├── persistencia/       # Objetos DAO y gestión de conexión JDBC
    ├── vista/              # Menús de consola interactivos por rol de usuario
    └── Main.java           # Punto de entrada de la aplicación
