-- 15 administradores
INSERT INTO usuarios (id, dni, nombre, usuario, contrasenia, rol, legajo, saldo, activo)
VALUES (1, '10000001', 'Admin Principal', 'admin', 'admin123', 'ADMIN', NULL, 0.0, TRUE),
       (2, '10000002', 'Valeria Gomez', 'vgomez', 'valeria123', 'ADMIN', NULL, 0.0, TRUE),
       (3, '10000003', 'Carlos Ruiz', 'cruiz', 'carlos123', 'ADMIN', NULL, 0.0, TRUE),
       (4, '10000004', 'Mariana Lopez', 'mlopez', 'mariana123', 'ADMIN', NULL, 0.0, TRUE),
       (5, '10000005', 'Santiago Fernandez', 'sfernandez', 'santiago123', 'ADMIN', NULL, 0.0, TRUE),
       (6, '10000006', 'Paula Benitez', 'pbenitez', 'paula123', 'ADMIN', NULL, 0.0, TRUE),
       (7, '10000007', 'Federico Alvarez', 'falvarez', 'federico123', 'ADMIN', NULL, 0.0, TRUE),
       (8, '10000008', 'Lucia Suarez', 'lsuarez', 'lucia123', 'ADMIN', NULL, 0.0, TRUE),
       (9, '10000009', 'Esteban Morales', 'emorales', 'esteban123', 'ADMIN', NULL, 0.0, TRUE),
       (10, '10000010', 'Daniela Romero', 'dromero', 'daniela123', 'ADMIN', NULL, 0.0, TRUE),
       (11, '10000011', 'Gonzalo Herrera', 'gherrera', 'gonzalo123', 'ADMIN', NULL, 0.0, TRUE),
       (12, '10000012', 'Florencia Castro', 'fcastro', 'florencia123', 'ADMIN', NULL, 0.0, TRUE),
       (13, '10000013', 'Ignacio Molina', 'imolina', 'ignacio123', 'ADMIN', NULL, 0.0, TRUE),
       (14, '10000014', 'Camila Ortiz', 'cortiz', 'camila123', 'ADMIN', NULL, 0.0, TRUE),
       (15, '10000015', 'Nicolas Medina', 'nmedina', 'nicolas123', 'ADMIN', NULL, 0.0, TRUE);

-- 15 empleados
INSERT INTO usuarios (id, dni, nombre, usuario, contrasenia, rol, legajo, saldo, activo)
VALUES (16, '20000016', 'Lucas Perez', 'lperez', 'lucas123', 'EMPLEADO', 'LEG001', 0.0, TRUE),
       (17, '20000017', 'Martina Diaz', 'mdiaz', 'martina123', 'EMPLEADO', 'LEG002', 0.0, TRUE),
       (18, '20000018', 'Julian Torres', 'jtorres', 'julian123', 'EMPLEADO', 'LEG003', 0.0, TRUE),
       (19, '20000019', 'Rocio Gutierrez', 'rgutierrez', 'rocio123', 'EMPLEADO', 'LEG004', 0.0, TRUE),
       (20, '20000020', 'Mateo Sosa', 'msosa', 'mateo123', 'EMPLEADO', 'LEG005', 0.0, TRUE),
       (21, '20000021', 'Abril Vega', 'avega', 'abril123', 'EMPLEADO', 'LEG006', 0.0, TRUE),
       (22, '20000022', 'Tomas Gimenez', 'tgimenez', 'tomas123', 'EMPLEADO', 'LEG007', 0.0, TRUE),
       (23, '20000023', 'Micaela Rojas', 'mrojas', 'micaela123', 'EMPLEADO', 'LEG008', 0.0, TRUE),
       (24, '20000024', 'Franco Navarro', 'fnavarro', 'franco123', 'EMPLEADO', 'LEG009', 0.0, TRUE),
       (25, '20000025', 'Julieta Silva', 'jsilva', 'julieta123', 'EMPLEADO', 'LEG010', 0.0, TRUE),
       (26, '20000026', 'Agustin Carrizo', 'acarrizo', 'agustin123', 'EMPLEADO', 'LEG011', 0.0, TRUE),
       (27, '20000027', 'Belen Mendoza', 'bmendoza', 'belen123', 'EMPLEADO', 'LEG012', 0.0, TRUE),
       (28, '20000028', 'Dario Aguilar', 'daguilar', 'dario123', 'EMPLEADO', 'LEG013', 0.0, TRUE),
       (29, '20000029', 'Sol Paredes', 'sparedes', 'sol123', 'EMPLEADO', 'LEG014', 0.0, TRUE),
       (30, '20000030', 'Joaquin Ramos', 'jramos', 'joaquin123', 'EMPLEADO', 'LEG015', 0.0, TRUE);

-- 15 clientes
INSERT INTO usuarios (id, dni, nombre, usuario, contrasenia, rol, legajo, saldo, activo)
VALUES (31, '30000031', 'Luna Rossi', 'luna', 'luna123', 'CLIENTE', NULL, 150000.0, TRUE),
       (32, '30000032', 'Martin Acuña', 'macuna', 'martin123', 'CLIENTE', NULL, 50000.0, TRUE),
       (33, '30000033', 'Sofia Cabrera', 'scabrera', 'sofia123', 'CLIENTE', NULL, 0.0, TRUE),
       (34, '30000034', 'Facundo Rios', 'frios', 'facundo123', 'CLIENTE', NULL, 200000.0, TRUE),
       (35, '30000035', 'Catalina Ponce', 'cponce', 'catalina123', 'CLIENTE', NULL, 12000.0, TRUE),
       (36, '30000036', 'Manuel Miranda', 'mmiranda', 'manuel123', 'CLIENTE', NULL, 0.0, TRUE),
       (37, '30000037', 'Antonella Paz', 'apaz', 'antonella123', 'CLIENTE', NULL, 85000.0, TRUE),
       (38, '30000038', 'Rodrigo Luna', 'rluna', 'rodrigo123', 'CLIENTE', NULL, 30000.0, TRUE),
       (39, '30000039', 'Victoria Bravo', 'vbravo', 'victoria123', 'CLIENTE', NULL, 10000.0, TRUE),
       (40, '30000040', 'Leandro Quiroga', 'lquiroga', 'leandro123', 'CLIENTE', NULL, 0.0, TRUE),
       (41, '30000041', 'Milagros Duarte', 'mduarte', 'milagros123', 'CLIENTE', NULL, 350000.0, TRUE),
       (42, '30000042', 'Ezequiel Cardozo', 'ecardozo', 'ezequiel123', 'CLIENTE', NULL, 45000.0, TRUE),
       (43, '30000043', 'Zoe Peralta', 'zperalta', 'zoe123', 'CLIENTE', NULL, 5000.0, TRUE),
       (44, '30000044', 'Bruno Ojeda', 'bojeda', 'bruno123', 'CLIENTE', NULL, 0.0, TRUE),
       (45, '30000045', 'Malena Varela', 'mvarela', 'malena123', 'CLIENTE', NULL, 75000.0, TRUE);