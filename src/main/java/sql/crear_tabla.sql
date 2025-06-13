drop database if exists akihabara_db;
Create database akihabara_db;
Use akihabara_db;
drop table if exists productos;
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT
);
# drop table if exists clientes;
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from productos;
select * from clientes;
# Insert para productos
INSERT INTO productos (nombre, categoria, precio,stock) VALUES 
("Figura de Anya Forger", "Figura", 59.95, 8),
("Manga Chainsaw Man Vol.1", "Manga", 9.99, 20),
("Póster Studio Ghibli Colección", "Póster", 15.50, 15);
# Insert para clientes
INSERT INTO clientes (nombre, email, telefono) VALUES 
('Daniel Henandez', 'daniel@campusfp.es', '+34 612 345 678'),
('Esteban martin', 'Esteban@campusfp.es', '+34 699 123 456'),
('Mondongo goku', 'Mondongo.goku@example.com', '+34 620 987 654');

-- Crear el usuario (si no existe)
CREATE USER IF NOT EXISTS 'DHP_akihabara'@'localhost' IDENTIFIED BY 'campusfp';

-- Darle todos los permisos sobre la base de datos akihabara_db
GRANT ALL PRIVILEGES ON akihabara_db.* TO 'DHP_akihabara'@'localhost';

-- Aplicar los cambios de permisos
FLUSH PRIVILEGES;
