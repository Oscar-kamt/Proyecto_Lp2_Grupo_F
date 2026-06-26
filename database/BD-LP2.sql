DROP DATABASE IF EXISTS tienda_ropa;

CREATE DATABASE tienda_ropa;

USE tienda_ropa;

-- ======================
-- TABLA CATEGORIA
-- ======================

CREATE TABLE categoria(

    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL

);

INSERT INTO categoria(nombre) VALUES
('Polos'),
('Pantalones'),
('Casacas'),
('Zapatillas'),
('Accesorios');



-- ======================
-- TABLA PRODUCTO
-- ======================

CREATE TABLE producto(

    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    marca VARCHAR(100),
    talla VARCHAR(20),
    color VARCHAR(50),
    precio DOUBLE,
    stock INT,
    categoria_id INT,

    FOREIGN KEY(categoria_id)
    REFERENCES categoria(id)

);

INSERT INTO producto
(nombre,marca,talla,color,precio,stock,categoria_id)
VALUES

('Polo Oversize Algodon','Nike','M','Negro',59.90,30,1),
('Polo Deportivo','Adidas','L','Blanco',79.90,25,1),
('Jean Clasico','Levis','32','Azul',129.90,15,2),
('Pantalon Cargo','Zara','30','Verde',99.90,20,2),
('Casaca Cuero','Columbia','L','Negro',249.90,10,3),
('Casaca Hoodie','Adidas','M','Gris',159.90,18,3),
('Zapatilla Running','Nike','42','Blanco',299.90,12,4),
('Zapatilla Urbana','Puma','40','Negro',189.90,14,4),
('Gorra Clasica','New Era','Unica','Rojo',49.90,50,5),
('Cinturon Cuero','Guess','Unica','Marron',69.90,22,5);



-- ======================
-- TABLA CLIENTE
-- ======================

CREATE TABLE cliente(

    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    dni VARCHAR(20),
    telefono VARCHAR(20),
    correo VARCHAR(100)

);

INSERT INTO cliente
(nombre,dni,telefono,correo)
VALUES

('Carlos Ramirez','72839401','987654321','carlos@gmail.com'),
('Maria Lopez','74628193','986543210','maria@gmail.com'),
('Juan Perez','70192834','999888777','juan@gmail.com'),
('Ana Torres','76543210','955443322','ana@gmail.com');



-- ======================
-- TABLA USUARIO
-- ======================

CREATE TABLE usuario(

    id INT AUTO_INCREMENT PRIMARY KEY,

    usuario VARCHAR(50) NOT NULL,

    clave VARCHAR(100) NOT NULL,

    rol VARCHAR(30) NOT NULL

);

INSERT INTO usuario(usuario,clave,rol)
VALUES

('admin','123','ADMIN'),

('vendedor','123','VENDEDOR');


-- ======================
-- TABLA VENTA
-- ======================

CREATE TABLE venta(

    id INT AUTO_INCREMENT PRIMARY KEY,

    numero_boleta VARCHAR(20) UNIQUE,

    fecha DATE NOT NULL,

    total DOUBLE NOT NULL,

    cliente_id INT NOT NULL,

    usuario_id INT NOT NULL,

    FOREIGN KEY(cliente_id)
    REFERENCES cliente(id),

    FOREIGN KEY(usuario_id)
    REFERENCES usuario(id)

);



-- ======================
-- TABLA DETALLE VENTA
-- ======================

CREATE TABLE detalle_venta(

    id INT AUTO_INCREMENT PRIMARY KEY,

    venta_id INT NOT NULL,

    producto_id INT NOT NULL,

    cantidad INT NOT NULL,

    precio DOUBLE NOT NULL,

    subtotal DOUBLE NOT NULL,

    FOREIGN KEY(venta_id)
    REFERENCES venta(id),

    FOREIGN KEY(producto_id)
    REFERENCES producto(id)

);



-- ======================
-- DATOS DE PRUEBA VENTA
-- ======================

INSERT INTO venta
(numero_boleta,fecha,total,cliente_id,usuario_id)
VALUES

('B000001','2026-06-01',189.70,1,1),

('B000002','2026-06-02',429.80,2,2);



-- ======================
-- DATOS DE PRUEBA DETALLE
-- ======================

INSERT INTO detalle_venta
(venta_id,producto_id,cantidad,precio,subtotal)
VALUES

(1,1,1,59.90,59.90),
(1,2,1,79.90,79.90),
(1,10,1,49.90,49.90),

(2,7,1,299.90,299.90),
(2,6,1,159.90,159.90);

