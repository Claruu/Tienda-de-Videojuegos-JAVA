CREATE TABLE IF NOT EXISTS pedidos (
  id INT AUTO_INCREMENT PRIMARY KEY,
    id_compra INT NOT NULL UNIQUE,
    preparado BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_pedido DATETIME NOT NULL,
    id_empleado_preparador INT NULL,
    CONSTRAINT fk_pedidos_compra FOREIGN KEY (id_compra) REFERENCES compras(id),
    CONSTRAINT fk_pedidos_empleado FOREIGN KEY (id_empleado_preparador) REFERENCES usuarios(id)
);